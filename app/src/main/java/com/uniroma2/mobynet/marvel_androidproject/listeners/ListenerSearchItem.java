package com.uniroma2.mobynet.marvel_androidproject.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.uniroma2.mobynet.marvel_androidproject.activity.ShowElementActivity;

public class ListenerSearchItem  implements AdapterView.OnItemClickListener  {

    private Context context;
    private int type;

    /**
     * Costruttore
     *
     * @param context : contesto della classe che lo invoca
     * @param type : tipo di ricerca da effettuare: characters-(1) o creators-(2)
     */
    public ListenerSearchItem(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    /**
     * Questa funzione permette di cambiare intent alla ShowElementActivity dopo il click su un
     * elemento della listView (itemClick)
     *
     * @param adapterView : AdapterView dove si puo' il cliccare;
     * @param view : View all'interno di AdapterView su cui e' stato fatto click;
     * @param i :  posizione della View nell'Adapter;
     * @param l :  ID della riga dell'elemento su cui e' stato fatto click;
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(context, ShowElementActivity.class);
        intent.putExtra("search_value", adapterView.getItemAtPosition(i).toString());
        intent.putExtra("type", type);
        context.startActivity(intent);

    }

}
