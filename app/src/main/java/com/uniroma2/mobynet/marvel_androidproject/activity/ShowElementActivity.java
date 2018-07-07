package com.uniroma2.mobynet.marvel_androidproject.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.uniroma2.mobynet.marvel_androidproject.R;

public class ShowElementActivity extends AppCompatActivity {

    private String research;
    private int type;
    private TextView  tvUriLastname,  tvDescriptionSuffix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_element);
        tvUriLastname = findViewById(R.id.tv_uriORlastname);
        tvDescriptionSuffix = findViewById(R.id.tv_descriptionORsuffix);

        Bundle type_value = getIntent().getExtras();
        if (type_value != null)
            type = type_value.getInt("type");

        Bundle request_value = getIntent().getExtras();
        if (request_value != null)
            research = request_value.getString("search_value");

        if(type == 1){
            tvUriLastname.setText(R.string.url);
            tvDescriptionSuffix.setText(R.string.description);
        } else {
            tvUriLastname.setText(R.string.lastname);
            tvDescriptionSuffix.setText(R.string.suffix);
        }


    }
}
