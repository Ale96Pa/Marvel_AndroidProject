package com.uniroma2.mobynet.marvel_androidproject.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.uniroma2.mobynet.marvel_androidproject.activity.ShowElementActivity;

/**
 * Tale classe implementa il listener degli elementi all'interno della ListView, ossia permette di
 * passare all'Activity successiva gli elementi necessari per la sua esecuzione, ossia i parametri
 * per eseguire la richiesta REST (tipo di ricerca e nome da ricercare)
 */
public class ListenerSearchItem  implements AdapterView.OnItemClickListener  {

    /* Attributi */
    private Context context;
    private int type;

    /* Costruttore */
    ListenerSearchItem(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    /**
     * Questa funzione permette di cambiare intent alla ShowElementActivity dopo il click su un
     * elemento della listView (itemClick)
     *
     * @param adapterView : AdapterView dove si puo' cliccare uno degli elementi (itemclick);
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