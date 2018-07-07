package com.uniroma2.mobynet.marvel_androidproject.listeners;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.uniroma2.mobynet.marvel_androidproject.activity.SearchElementActivity;
import com.uniroma2.mobynet.marvel_androidproject.activity.ShowElementActivity;

public class ListenerSearchItem extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    Context context;
    int type;
    public ListenerSearchItem(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Context otherContext = null;
        try {
            otherContext = createPackageContext("SearchElementActivity.pack",
                    Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(otherContext, ShowElementActivity.class);
        intent.putExtra("search_value", adapterView.getItemAtPosition(i).toString());
        intent.putExtra("type", type);
        startActivity(intent);
    }


}
