package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.model.Character;
import com.uniroma2.mobynet.marvel_androidproject.model.Creator;
import com.uniroma2.mobynet.marvel_androidproject.restAPI.JSONManager;

import java.util.Objects;


public class ShowElementActivity extends AppCompatActivity {

    private Button btnAgain;
    private Button btnExit;

    private String research;
    private int type;
    private TextView tvDescriptionSuffix;
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
            tvDescriptionSuffix.setText(R.string.description);

            Character character = jsonManager.get_json_character(research);
            if(character != null) {
                if (character.getThumbnail() == null) {
                    Drawable drawable = getResources().getDrawable(R.drawable.moby);
                    iv_thumbnailValue.setImageDrawable(drawable);
                } else {
                    String url = character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension();
                    loadImageFromURL(url);
                }

                tv_IDValue.setText(String.valueOf(character.getId()));
                if (character.getName() == null || Objects.equals(character.getName(), "")) {
                    String notFound = R.string.name + " " + R.string.not_found;
                    tv_NameValue.setText(notFound);
                } else
                    tv_NameValue.setText(character.getName());

                if (character.getResourceURI() == null || Objects.equals(character.getResourceURI(), "")) {
                    String notFound = R.string.resourceURI + " " + R.string.not_found;
                    tv_UriLastnameValue.setText(notFound);
                } else
                    tv_UriLastnameValue.setText(character.getResourceURI());

                if (character.getDescription() == null || Objects.equals(character.getDescription(), "")) {
                    String notFound = R.string.description + " " + R.string.not_found;
                    tv_DescriptionSuffixValue.setText(notFound);
                } else
                    tv_DescriptionSuffixValue.setText(character.getDescription());


                StringBuilder comicsNames = new StringBuilder();
                for (int i = 0; i < character.getComics().getItems().size(); i++) {
                    comicsNames.append(character.getComics().getItems().get(i).getName());
                    comicsNames.append("\n");
                }
                if (comicsNames.length() <= 0) {
                    String notFound = R.string.comics + " " + R.string.not_found;
                    tv_nameComic.setText(notFound);
                } else
                    tv_nameComic.setText(comicsNames);

                StringBuilder eventsNames = new StringBuilder();
                for (int i = 0; i < character.getEvents().getItems().size(); i++) {
                    eventsNames.append(character.getEvents().getItems().get(i).getName());
                    eventsNames.append("\n");
                }
                if (eventsNames.length() <= 0) {
                    String notFound = R.string.elements + " " + R.string.not_found;
                    tv_nameEvent.setText(notFound);
                } else
                    tv_nameEvent.setText(eventsNames);

                StringBuilder storiesNames = new StringBuilder();
                for (int i = 0; i < character.getStories().getItems().size(); i++) {
                    storiesNames.append(character.getStories().getItems().get(i).getName());
                    storiesNames.append("\n");
                }
                if (storiesNames.length() <= 0) {
                    String notFound = R.string.stories + " " + R.string.not_found;
                    tv_nameStory.setText(notFound);
                } else
                    tv_nameStory.setText(storiesNames);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.moby);
                iv_thumbnailValue.setImageDrawable(drawable);
                tv_IDValue.setText(R.string.not_found);

                tv_NameValue.setText(R.string.not_found);
                tv_UriLastnameValue.setText(R.string.not_found);
                tv_DescriptionSuffixValue.setText(R.string.not_found);
                tv_nameComic.setText(R.string.not_found);
                tv_nameEvent.setText(R.string.not_found);
                tv_nameStory.setText(R.string.not_found);
            }
        } else {
            tvDescriptionSuffix.setText(R.string.suffix);
            String[] lastname = research.split(",");

            Creator creator = jsonManager.get_json_creator(lastname[0]);
            if(creator != null) {
                if (creator.getThumbnail() == null) {
                    Drawable drawable = getResources().getDrawable(R.drawable.moby);
                    iv_thumbnailValue.setImageDrawable(drawable);
                } else {
                    String url = creator.getThumbnail().getPath() + "." + creator.getThumbnail().getExtension();
                    loadImageFromURL(url);
                }
                tv_IDValue.setText(String.valueOf(creator.getId()));

                String fullname = creator.getFirstName() + "  " + creator.getMiddleName() + "\n" + creator.getLastName();
                if (Objects.equals(fullname, "")) {
                    String notFound = R.string.name + " " + R.string.not_found;
                    tv_NameValue.setText(notFound);
                } else
                    tv_NameValue.setText(fullname);

                tv_UriLastnameValue.setText("");

                if (creator.getSuffix() == null || Objects.equals(creator.getSuffix(), "")) {
                    String notFound = R.string.suffix + " " + R.string.not_found;
                    tv_DescriptionSuffixValue.setText(notFound);
                } else
                    tv_DescriptionSuffixValue.setText(creator.getSuffix());


                StringBuilder comicsNames = new StringBuilder();
                for (int i = 0; i < creator.getComics().getItems().size(); i++) {
                    comicsNames.append(creator.getComics().getItems().get(i).getName());
                    comicsNames.append("\n");
                }
                if (comicsNames.length() <= 0) {
                    String notFound = R.string.comics + " " + R.string.not_found;
                    tv_nameComic.setText(notFound);
                } else
                    tv_nameComic.setText(comicsNames);

                StringBuilder eventsNames = new StringBuilder();
                for (int i = 0; i < creator.getEvents().getItems().size(); i++) {
                    eventsNames.append(creator.getEvents().getItems().get(i).getName());
                    eventsNames.append("\n");
                }
                if (eventsNames.length() <= 0) {
                    String notFound = R.string.elements + " " + R.string.not_found;
                    tv_nameEvent.setText(notFound);
                } else
                    tv_nameEvent.setText(eventsNames);

                StringBuilder storiesNames = new StringBuilder();
                for (int i = 0; i < creator.getStories().getItems().size(); i++) {
                    storiesNames.append(creator.getStories().getItems().get(i).getName());
                    storiesNames.append("\n");
                }
                if (storiesNames.length() <= 0) {
                    String notFound = R.string.stories + " " + R.string.not_found;
                    tv_nameStory.setText(notFound);
                } else
                    tv_nameStory.setText(storiesNames);
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.moby);
                iv_thumbnailValue.setImageDrawable(drawable);
                tv_IDValue.setText(R.string.not_found);

                tv_NameValue.setText(R.string.not_found);
                tv_UriLastnameValue.setText(R.string.not_found);
                tv_DescriptionSuffixValue.setText(R.string.not_found);
                tv_nameComic.setText(R.string.not_found);
                tv_nameEvent.setText(R.string.not_found);
                tv_nameStory.setText(R.string.not_found);
            }
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
                Intent intent = new Intent(ShowElementActivity.this, WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });


    }

    private void loadImageFromURL(String url) {

        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv_thumbnailValue, new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess(){

                    }

                    @Override
                    public void onError(){

                    }
                });
    }


}