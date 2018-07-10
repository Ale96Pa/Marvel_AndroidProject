package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.uniroma2.mobynet.marvel_androidproject.R;

/**
 * L'Activity "activity_welcome.xml" e' la prima ad essere visualizzata dall'utente; in essa viene
 * esposto brevemente il focus dell'applicazione (per guidare l'utente ad una user-experience chiara.
 * In essa vengono implementati:
 *         1) Listener del bottone di inizio: per passare all'Activity successiva;
 *         2) Listener del bottone di uscita: per uscire dall'applicazione;
 */
public class WelcomeActivity extends AppCompatActivity {

    /* Attributi */
    Button btnStart;
    Button btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnExit);

        // Con tale istruzione si implementa l'uscita di tutte le activity che fanno riferimento a questa
        if(getIntent().getBooleanExtra("EXIT", false))
            finish();

        btnStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEnd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}