package com.example.slmns.birdcontrol;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.slmns.birdcontrol.Objects.Http;
import org.json.JSONArray;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class BirdWatch extends AppCompatActivity {
    ListView listView;
    private String TAG;

    ArrayList<HashMap<String,String>> BirdList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_watch);
        BirdList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvbirdwatch);

        new GetBirds().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "onItemClick: " + position);

                Intent birdPictureIntent = new Intent(BirdWatch.this, BirdWatchPic.class);
                birdPictureIntent.putExtra("bird", BirdList.get(position));
                startActivity(birdPictureIntent);
            }
        });
    }

    private class GetBirds extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(BirdWatch.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
          Http http = new Http();
            String url = "http://birdobservationservice.azurewebsites.net/Service1.svc/birds";
            String JsonString = http.JSon(url);

            Log.e(TAG, "Response from url: " + JsonString);
            if (JsonString != null) {


                try {
                    JSONArray birds = null;
                    birds = new JSONArray(JsonString);

                    Log.d(TAG, "doInBackground: " + birds.length());

                    for (int i = 0; i < birds.length(); i++) {
                        JSONObject JsonO = birds.getJSONObject(i);
                        String id = JsonO.getString("Id");
                        String nameDanish = JsonO.getString("NameDanish");
                        String nameEnglish = JsonO.getString("NameEnglish");
                        String created = JsonO.getString("Created");
                        String photoUrl = JsonO.getString("PhotoUrl");

                        HashMap<String, String> bird = new HashMap<>();

                        bird.put("Id", id);
                        bird.put("NameDanish", nameDanish);
                        bird.put("NameEnglish", nameEnglish);
                        bird.put("Created", created);
                        bird.put("PhotoUrl", photoUrl);

                        BirdList.add(bird);
                        Log.d(TAG, "doInBackground: " + bird);
                    }
                }catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                    }

                });
            }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(BirdWatch.this, BirdList,
                    R.layout.list, new String[]{ "Id","NameDanish", "NameEnglish"},
                    new int[]{R.id.id, R.id.nameDanish, R.id.nameEnglish});
            listView.setAdapter(adapter);
        }
    }
}


