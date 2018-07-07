package com.uniroma2.mobynet.marvel_androidproject.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniroma2.mobynet.marvel_androidproject.R;
import com.uniroma2.mobynet.marvel_androidproject.model.Character;
import com.uniroma2.mobynet.marvel_androidproject.model.Creator;
import com.uniroma2.mobynet.marvel_androidproject.restAPI.JSONManager;


public class ShowElementActivity extends AppCompatActivity {

    private String research;
    private int type;
    private TextView  tvUriLastname,  tvDescriptionSuffix;
    private TextView tv_IDValue, tv_NameValue, tv_UriLastnameValue, tv_DescriptionSuffixValue;
    private ImageView iv_thumbnailValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_element);
        tvUriLastname = findViewById(R.id.tv_uriORlastname);
        tvDescriptionSuffix = findViewById(R.id.tv_descriptionORsuffix);

        iv_thumbnailValue = findViewById(R.id.iv_thumbnail);
        tv_IDValue = findViewById(R.id.tv_todo_ID);
        tv_NameValue = findViewById(R.id.tv_todo_name);
        tv_UriLastnameValue = findViewById(R.id.tv_todo_urlORlastname);
        tv_DescriptionSuffixValue = findViewById(R.id.tv_todo_descriptionORsuffix);

        Bundle type_value = getIntent().getExtras();
        if (type_value != null)
            type = type_value.getInt("type");
/*
        Bundle request_value = getIntent().getExtras();
        if (request_value != null)
            research = request_value.getString("search_value");
*/
        JSONManager jsonManager = new JSONManager(this);
        research = "Hulk";
        type = 1;

        if(type == 1){
            tvUriLastname.setText(R.string.url);
            tvDescriptionSuffix.setText(R.string.description);

            Character character = jsonManager.get_json_character(research);
            Uri uri = Uri.parse(character.getThumbnail().getPath() + "." + character.getThumbnail().getExtension());
            iv_thumbnailValue.setImageURI(uri);

            tv_IDValue.setText(character.getId());
            tv_NameValue.setText(character.getName());
            tv_UriLastnameValue.setText(character.getResourceURI());
            tv_DescriptionSuffixValue.setText(character.getDescription());



        } else {
            tvUriLastname.setText(R.string.lastname);
            tvDescriptionSuffix.setText(R.string.suffix);

            Creator creator = jsonManager.get_json_creator(research);
            Uri uri = Uri.parse(creator.getThumbnail().getPath()+ "." +creator.getThumbnail().getExtension());
            iv_thumbnailValue.setImageURI(uri);

            tv_IDValue.setText(creator.getId());
            tv_NameValue.setText(creator.getFullName());
            tv_UriLastnameValue.setText(creator.getLastName());
            tv_DescriptionSuffixValue.setText(creator.getSuffix());


        }


    }
}
