package com.example.slmns.birdcontrol.Objects;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by slmns on 15-04-2018.
 */

public class Http {

    private static final String TAG =Http.class.getSimpleName();


public String JSon(String REQURL){
    String response = null;

    try {
        URL url = new URL(REQURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        response = ConvertStreamToString(inputStream);

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return response;
}

private String ConvertStreamToString(InputStream IS){
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS));
    StringBuilder stringBuilder = new StringBuilder();
    String Line;


    try {
        while ((Line = bufferedReader.readLine()) !=null) {
            stringBuilder.append(Line).append("\n");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            IS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
return stringBuilder.toString();
}

}
