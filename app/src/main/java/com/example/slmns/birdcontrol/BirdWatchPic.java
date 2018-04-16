package com.example.slmns.birdcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.HashMap;

public class BirdWatchPic extends AppCompatActivity {

    ImageView ImageView;
    TextView BirdName, BirdCreated;
    String Url;
    String Name;
    String Created;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_watch_pic);

        ImageView = findViewById(R.id.bird_iv);
        BirdName = findViewById(R.id.birdname_tv);
        BirdCreated = findViewById(R.id.birdcreated_tv);

        Intent intent = getIntent();
        HashMap<String,String> hashMap = (HashMap<String,String>)intent.getSerializableExtra("bird");

        Log.v("Hashmap",hashMap.get("PhotoUrl"));
        Url = hashMap.get("PhotoUrl");
        Name = hashMap.get("NameDanish");
        BirdName.setText(Name);
        Created = hashMap.get("Created");
        BirdCreated.setText(Created);

        Glide.with(this).load(Url).into(ImageView);
    }
}
