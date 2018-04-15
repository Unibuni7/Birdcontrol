package com.example.slmns.birdcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FrontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
    }

    public void watchbirds(View view) {
        startActivity(new Intent(this,BirdWatch.class));
    }

    public void PictureBirds(View view) {
    }

    public void Information(View view) {
    }
}
