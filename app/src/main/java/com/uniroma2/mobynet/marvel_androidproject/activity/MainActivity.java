package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.uniroma2.mobynet.marvel_androidproject.R;

public class MainActivity extends AppCompatActivity {

    private Button btnCreator;
    private Button btnCharacter;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCharacter = findViewById(R.id.btnCharacter);
        btnCreator = findViewById(R.id.btnCreator);
        btnExit = findViewById(R.id.btn_exit);

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
        btnExit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }
}