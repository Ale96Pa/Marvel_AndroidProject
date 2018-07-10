package com.uniroma2.mobynet.marvel_androidproject.listeners;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.database.DbHelper;
import com.uniroma2.mobynet.marvel_androidproject.database.DbManager;

/**
 * Tale classe implementa il listener del bottone di ricerca; si noti che nell'inserimento della
 * stringa dell'utente possono esserci 3 casi anomali:
 *          1) Presenza di apostrofo: causa la scorretta esecuzione della query;
 *          2) Presenza di stringa nulla: causa la mancata ricerca;
 *          3) Prensenza di una stringa che indica l'inserimento di TUTTI gli elementi;
 */
public class ListenerSearch implements View.OnClickListener {

    /* Attributi */
    private EditText etSearch;
    private ListView lvElements;

    private Context context;
    private Activity activity;

    private final String all = "0"; //Stringa usata per fare un inserimento e ricerca TOTALE
    private int type;

    private String query;
    private ArrayList<String> queryResults;


    /* Costruttore */
    public ListenerSearch(EditText etSearch, int type, ListView lvElements, Context context, Activity activity) {
        this.etSearch = etSearch;
        this.type = type;
        this.lvElements = lvElements;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        hideKeyboard(activity); // Si nasconde la tastiera virtuale dopo aver confermato la ricerca
        String user_search = String.valueOf(etSearch.getText());

        // Si inizializza il database
        DbHelper DBhelper = new DbHelper(context);
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        final DbManager manager = new DbManager(context);

        // Si verificano e gestiscono i casi anomali
        if(user_search.contains("'")){
            user_search = user_search.replace('\'', ' ');
            Toast.makeText(context, R.string.apix, Toast.LENGTH_LONG).show();
        }
        if(Objects.equals(user_search, "")){
            user_search = "null";
            Toast.makeText(context, R.string.empty_search, Toast.LENGTH_LONG).show();
        }
        if(Objects.equals(user_search, all) && type == 1){
            try {
                manager.insertDataCharacters(db, user_search);
            } catch (IOException e) {
                e.printStackTrace();
            }
            query = "SELECT DISTINCT * FROM characters ;";

        }
        else if(Objects.equals(user_search, all) && type == 2){
            try {
                manager.insertDataCreators(db, user_search);
            } catch (IOException e) {
                e.printStackTrace();
            }
            query = "SELECT DISTINCT * FROM creators ;";
        }

        // Si verificano e gestiscono i casi NON anomali
        else {

            if (type == 1) {
                try {
                    manager.insertDataCharacters(db, user_search);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String user_search_upper = user_search.substring(0, 1).toUpperCase() +
                        user_search.substring(1, user_search.length()).toLowerCase();
                query = "SELECT DISTINCT * FROM characters WHERE name like '%" + user_search + "%' or name like '%" + user_search_upper + "%';";

            } else {
                try {
                    manager.insertDataCreators(db, user_search);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String user_search_upper = user_search.substring(0, 1).toUpperCase() +
                        user_search.substring(1, user_search.length()).toLowerCase();
                query = "SELECT DISTINCT * FROM creators WHERE name like '%" + user_search + "%' or name like '%" + user_search_upper + "%';";
            }
        }

        // Si elabora e analizza la query
        Cursor cursor = db.rawQuery(query, new String[]{});
        queryResults = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                queryResults.add(name);
            } while(cursor.moveToNext());
        }
        cursor.close();

        // Vengono eliminati i doppioni dall'insieme di elementi ritornati
        List<String> allSearchedElements = new ArrayList<>(new LinkedHashSet<>(queryResults));

        // Si istanzia adapter e listener per la ListView
        ArrayAdapter<String> adapater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, allSearchedElements);
        lvElements.setAdapter(adapater);
        ListenerSearchItem listenerSearchItem = new ListenerSearchItem(context, type);
        lvElements.setOnItemClickListener(listenerSearchItem);

    }

    /**
     * Tale funzione permette di nascondere la tastiera dopo la ricerca dell'utente
     * @param activity: Activity da cui nascondere la tastiera
     */
    private static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        // Trova la View corrente in modo da "afferrare" la giusta finestra su essa
        View view = activity.getCurrentFocus();
        // Se la View corrente non è disponibile se ne crea una cosi' da poter nascondere la tastiera
        if (view == null) {
            view = new View(activity);
        }
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}

