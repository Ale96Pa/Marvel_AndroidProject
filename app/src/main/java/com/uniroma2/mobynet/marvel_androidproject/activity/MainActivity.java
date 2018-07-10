package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.uniroma2.mobynet.marvel_androidproject.R;


/**
 * La logica dell'Activity "activity_main.xml" permette all'utente di selezionare il tipo di ricerca
 * tra due possibili: characters (personaggi) e creators (creatori).
 * In essa vengono implementati i listeners dei bottoni che semplicemente rimandano all'Activity
 * successiva passando come dato il tipo di ricerca scelta dall'utente.
 */
public class MainActivity extends AppCompatActivity {

    /* Attributi */
    Button btnCreator;
    Button btnCharacter;

    /* Metodi */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCharacter = findViewById(R.id.btnCharacter);
        btnCreator = findViewById(R.id.btnCreator);

        btnCharacter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchElementActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });
        btnCreator.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchElementActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

    }
}