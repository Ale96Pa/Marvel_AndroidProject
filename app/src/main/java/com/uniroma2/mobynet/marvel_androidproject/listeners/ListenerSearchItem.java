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

    Context context;
    ShowElementActivity activity = new ShowElementActivity();
    int type;
    public ListenerSearchItem(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SearchElementActivity searchElementActivity = new SearchElementActivity();

        Intent intent = new Intent(searchElementActivity, activity.getClass());
        //intent.putExtra("search_value", adapterView.getItemAtPosition(i).toString());
        //intent.putExtra("type", type);
        context.startActivity(intent);

    }


}
