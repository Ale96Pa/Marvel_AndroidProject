package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.RestRequest;

public class MainActivity extends AppCompatActivity {

    private Button btnCreator;
    private Button btnCharacter;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestRequest rs = new RestRequest("creators", null);
        try {
            rs.sendGet();
            String res = rs.getResult();
            System.out.println("****** RESULT JSON *******");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnCreator = (Button) findViewById(R.id.btnCreator);
        btnCharacter =(Button) findViewById(R.id.btnCharacter);
        btnCreator.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchCreatorActivity.class);
                startActivity(intent);

            }
        });

    }
}