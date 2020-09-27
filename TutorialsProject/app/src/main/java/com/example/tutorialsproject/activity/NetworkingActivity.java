package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.GithubUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTV();
            }
        });
    }

    private void updateTV() {
        //Make the network call here

        NetworkTask networkTask = new NetworkTask();
        networkTask.execute("https://api.github.com/search/users?q=jake+wharton");
    }

    class NetworkTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = strings[0];
            try {
                URL url = new URL(stringUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()){
                    String s = scanner.next();
                    return s;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Failed to load";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<GithubUser> users = parseJson(s);
            Log.e("TAG","onPostExecute: " + users.size());
            TextView tv = findViewById(R.id.textView);
            tv.setText(s);
        }
    }

    ArrayList<GithubUser> parseJson(String s){
        ArrayList<GithubUser> githubUsers = new ArrayList<>();

        //Parse the JSON
        try{
            JSONObject root = new JSONObject(s);
            JSONArray items = root.getJSONArray("items");

            for(int i = 0; i < items.length(); i++){
                JSONObject object = items.getJSONObject(i);
                String login = object.getString("login"); //to simple get a particular value from an object of the api
                Integer id = object.getInt("id");
                String avatar_url = object.getString("avatar_url");
                Double score = object.getDouble("score");
                String html_url = object.getString("html_url");

                GithubUser githubUser = new GithubUser(login, id, html_url, score, avatar_url);
                githubUsers.add(githubUser);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return githubUsers;
    }

}
