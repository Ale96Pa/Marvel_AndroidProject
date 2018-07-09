package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.uniroma2.mobynet.marvel_androidproject.R;


public class WelcomeActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnExit);

        if(getIntent().getBooleanExtra("EXIT", false)){
            finish();
        }

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