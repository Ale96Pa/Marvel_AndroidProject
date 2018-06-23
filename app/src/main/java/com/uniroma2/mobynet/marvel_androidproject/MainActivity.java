package com.uniroma2.mobynet.marvel_androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCreator;
    private Button btnCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreator= (Button) findViewById(R.id.btnCreator);
        btnCharacter=(Button) findViewById(R.id.btnCharacter);
        btnCreator.setOnClickListener(this);
        btnCharacter.setOnClickListener(this);


    }

    public void onClick(View view){
        startActivity(new Intent(MainActivity.this,SearchCreatorActivity.class));
    }
}