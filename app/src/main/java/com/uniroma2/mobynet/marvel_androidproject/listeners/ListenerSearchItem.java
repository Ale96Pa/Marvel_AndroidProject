
package com.uniroma2.mobynet.marvel_androidproject.listeners;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.uniroma2.mobynet.marvel_androidproject.activity.SearchElementActivity;
import com.uniroma2.mobynet.marvel_androidproject.activity.ShowElementActivity;

public class ListenerSearchItem  implements AdapterView.OnItemClickListener  {

    private Context context;
    private int type;

    /**
     * @param context : Rappresenta il contesto della classe che lo invoca(ListenerSearch)
     * @param type : Rappresenta il tipo di ricerca che e' stata fatta: characters o creators
     */
    public ListenerSearchItem(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    /**
     * Questa funzione permette di alla ShowElementActivity dopo il click su un elemento della listView
     * @param adapterView : AdapterView dove si è verificato il click
     * @param view : La vista all'interno di AdapterView su cui è stato fatto click
     * @param i :  La posizione della vista nell'adattatore.
     * @param l :  L'ID della riga dell'elemento su cui è stato fatto click.
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(context, ShowElementActivity.class);
        intent.putExtra("search_value", adapterView.getItemAtPosition(i).toString());
        intent.putExtra("type", type);
        context.startActivity(intent);

    }


}
