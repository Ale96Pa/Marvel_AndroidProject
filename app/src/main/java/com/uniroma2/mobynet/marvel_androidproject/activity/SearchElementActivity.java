package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.listeners.ListenerSearch;

/**
 * Nell'Activity "activity_search_element.xml" viene implementata la logica di ricerca nel database;
 * vista la complessita' di tale attivita' si sono usati due listener con delle classi:
 *          1) ListenerSearch: implementa il listener del bottone di ricerca;
 *          2) ListenerSearchItem: implementa il listener della ListView;
 */
public class SearchElementActivity extends AppCompatActivity {

    /* Attributi */
    EditText etSearch;
    ListView lvElements;
    TextView tvElement;
    Button btnSearch;

    private int type; // 1 per c"haracters" e 2 per "creator"
    Context context;
    Activity activity;

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
        activity = this;

        ListenerSearch listener = new ListenerSearch(etSearch, type, lvElements, context, activity);
        btnSearch.setOnClickListener(listener);

    }

}