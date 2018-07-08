package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.model.Character;
import com.uniroma2.mobynet.marvel_androidproject.model.Creator;
import com.uniroma2.mobynet.marvel_androidproject.restAPI.JSONManager;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ShowElementActivity extends AppCompatActivity {

    private Button btnAgain;
    private Button btnExit;

    private String research;
    private int type;
    private TextView  tvUriLastname,  tvDescriptionSuffix;
    private TextView tv_IDValue, tv_NameValue, tv_UriLastnameValue, tv_DescriptionSuffixValue;
    private ImageView iv_thumbnailValue;
    private TextView tv_nameComic;
    private TextView tv_nameEvent;
    private TextView tv_nameStory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_element);

        btnAgain = findViewById(R.id.btnAgain);
        btnExit = findViewById(R.id.btnExitFinal);

        tvUriLastname = findViewById(R.id.tv_uriORlastname);
        tvDescriptionSuffix = findViewById(R.id.tv_descriptionORsuffix);

        iv_thumbnailValue = findViewById(R.id.iv_thumbnail);
        tv_IDValue = findViewById(R.id.tv_todo_ID);
        tv_NameValue = findViewById(R.id.tv_todo_name);
        tv_UriLastnameValue = findViewById(R.id.tv_todo_urlORlastname);
        tv_DescriptionSuffixValue = findViewById(R.id.tv_todo_descriptionORsuffix);

        tv_nameComic = findViewById(R.id.tv_todo_nameComic);
        tv_nameEvent = findViewById(R.id.tv_todo_nameEvent);
        tv_nameStory = findViewById(R.id.tv_todo_nameStory);

        Bundle type_value = getIntent().getExtras();
        if (type_value != null)
            type = type_value.getInt("type");

        Bundle request_value = getIntent().getExtras();
        if (request_value != null)
            research = request_value.getString("search_value");

        JSONManager jsonManager = new JSONManager(this);


        if(type == 1){
            tvUriLastname.setText(R.string.url);
            tvDescriptionSuffix.setText(R.string.description);

            Character character = jsonManager.get_json_character(research);
            Uri uri = Uri.parse(character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension());
            System.out.println("********URI********: " + uri);
            try {
                InputStream is = this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                iv_thumbnailValue.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            tv_IDValue.setText(String.valueOf(character.getId()));
            tv_NameValue.setText(character.getName());
            tv_UriLastnameValue.setText(character.getResourceURI());
            tv_DescriptionSuffixValue.setText(character.getDescription());

            StringBuilder comicsNames = new StringBuilder();
            for(int i=0; i<character.getComics().getItems().size(); i++){
                comicsNames.append(character.getComics().getItems().get(i).getName());
                comicsNames.append("\n");
            }
            tv_nameComic.setText(comicsNames);

            StringBuilder eventsNames = new StringBuilder();
            for(int i=0; i<character.getEvents().getItems().size(); i++){
                eventsNames.append(character.getEvents().getItems().get(i).getName());
                eventsNames.append("\n");
            }
            tv_nameEvent.setText(eventsNames);

            StringBuilder storiesNames = new StringBuilder();
            for(int i=0; i<character.getStories().getItems().size(); i++){
                storiesNames.append(character.getStories().getItems().get(i).getName());
                storiesNames.append("\n");
            }
            tv_nameStory.setText(storiesNames);

        } else {
            tvUriLastname.setText(R.string.lastname);
            tvDescriptionSuffix.setText(R.string.suffix);

            Creator creator = jsonManager.get_json_creator(research);
            Uri uri = Uri.parse(creator.getThumbnail().getPath() + "." + creator.getThumbnail().getExtension());
            System.out.println("********URI********: " + uri);
            try {
                InputStream is = this.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                iv_thumbnailValue.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            tv_IDValue.setText(String.valueOf(creator.getId()));
            tv_NameValue.setText(creator.getFullName());
            tv_UriLastnameValue.setText(creator.getLastName());
            tv_DescriptionSuffixValue.setText(creator.getSuffix());

            StringBuilder comicsNames = new StringBuilder();
            for(int i=0; i<creator.getComics().getItems().size(); i++){
                comicsNames.append(creator.getComics().getItems().get(i).getName());
                comicsNames.append("\n");
            }
            tv_nameComic.setText(comicsNames);

            StringBuilder eventsNames = new StringBuilder();
            for(int i=0; i<creator.getEvents().getItems().size(); i++){
                eventsNames.append(creator.getEvents().getItems().get(i).getName());
                eventsNames.append("\n");
            }
            tv_nameEvent.setText(eventsNames);

            StringBuilder storiesNames = new StringBuilder();
            for(int i=0; i<creator.getStories().getItems().size(); i++){
                storiesNames.append(creator.getStories().getItems().get(i).getName());
                storiesNames.append("\n");
            }
            tv_nameStory.setText(storiesNames);

        }

        btnAgain.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowElementActivity.this, MainActivity.class);
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
