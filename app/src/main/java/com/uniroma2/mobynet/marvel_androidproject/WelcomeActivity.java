package com.uniroma2.mobynet.marvel_androidproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
/*
        RestRequest rs = new RestRequest("characters");
        try {
            rs.sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}