package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.listeners.ListenerSearch;

public class SearchElementActivity extends AppCompatActivity {

    private EditText etSearch;
    private ListView lvElements;
    private TextView tvElement;
    private Button btnSearch;
    private String user_search;
    Context context;
    int type; // 1 if characters, 2 if creator

    public SearchElementActivity() {
        context = SearchElementActivity.this;
    }

    public Context getContext() {
        return this.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_element);

        etSearch = findViewById(R.id.etSearch);
        lvElements = findViewById(R.id.lvElementsSearched);
        tvElement = findViewById(R.id.tvInstructionSearch);
        btnSearch = findViewById(R.id.btnSearch);

        Bundle requested = getIntent().getExtras();
        if (requested != null)
            type = requested.getInt("type");
        if(type == 1) {
            tvElement.setText(R.string.searching_characters);
        }
        else {
            tvElement.setText(R.string.searching_creators);
        }

        context = SearchElementActivity.this;

        ListenerSearch listener = new ListenerSearch(user_search, etSearch, type, lvElements, context);
        btnSearch.setOnClickListener(listener);

    }

}