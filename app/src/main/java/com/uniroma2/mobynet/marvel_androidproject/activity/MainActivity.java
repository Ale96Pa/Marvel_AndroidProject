package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.uniroma2.mobynet.marvel_androidproject.R;
//TODO: Modifica la stringa in alto alla textview in base a type
public class MainActivity extends AppCompatActivity {

    private Button btnCreator;
    private Button btnCharacter;
    private Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCreator = (Button) findViewById(R.id.btnCreator);
        btnCharacter =(Button) findViewById(R.id.btnCharacter);
        btnCreator.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchElementActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });
        btnCharacter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchElementActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });

    }
}