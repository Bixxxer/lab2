package com.example.lab2;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class StartActivity extends AppCompatActivity {

    static final String IsResumed = "is resumed";
    static final String IsOnBackPressed = "is on back pressed";

    private boolean isResumed = false;
    private volatile boolean isOnBackPressed = false;

    public ArrayList<HashMap<String, String>> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(savedInstanceState != null) {
            isResumed = savedInstanceState.getBoolean(IsResumed);
            isOnBackPressed = savedInstanceState.getBoolean(IsOnBackPressed);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isResumed) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        DownloadJSON();

                        if (!isOnBackPressed) {
                            Intent intent = new Intent(StartActivity.this, MainActivity.class);
                            intent.putExtra("Downloaded", arraylist);
                            startActivity(intent);
                            finish();
                        }
                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                    isResumed = true;
                }
            });
            thread.start();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(IsResumed, isResumed);
        savedInstanceState.putBoolean(IsOnBackPressed, isOnBackPressed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isOnBackPressed = true;
    }

    void DownloadJSON() throws JSONException {
        // Create an array
        arraylist = new ArrayList<>();

        // Retrieve JSON Objects from the given URL address
        String jsonstr = JSONfunctions.getJSONStringfromURL("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json");
        JSONArray jsonarray;
        JSONObject jsonobject;
        jsonarray = new JSONArray(jsonstr);
        for (int i = 0; i < jsonarray.length(); i++) {
            HashMap<String, String> technologies = new HashMap<>();
            jsonobject = jsonarray.getJSONObject(i);
            // Retrieve JSON Objects
            if (jsonobject.has("name"))
                technologies.put("name", jsonobject.getString("name"));
            if (jsonobject.has("helptext"))
                technologies.put("helptext", jsonobject.getString("helptext"));
            if (jsonobject.has("graphic"))
                technologies.put("graphic", jsonobject.getString("graphic"));
            // Set the JSON Objects into the array
            if ((jsonobject != null) && jsonobject.has("name"))
                arraylist.add(technologies);
        }
    }
}