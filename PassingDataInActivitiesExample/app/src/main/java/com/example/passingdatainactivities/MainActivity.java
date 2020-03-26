package com.example.passingdatainactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String data = "Pass this String to Second Activity";
    private final static int REQ_CODE = 5;
    private String dataString = "hello";
    private int dataInt = 1;
    private Button btn;  // for normal startActivity(intent)
    private Button btn2; //for startActivityForResult
    private Button btn3; //Using Bundle
    private String data2 = "magenta";
    private HashMap<Integer, ArrayList<String>> mapList;

    //1. using static method
    //2. Using Intent
    //3. Get Response Result Data Back From Target Activity.
    //4. Using Bundle: Android has a class called Bundle where we can store our data and it supports several data types like strings, chars, boolean, integer and so on. Instead of using the Direct Intent as data container, we can store our data directly into the bundle and then save the bundle into the Intent.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        mapList = new HashMap<>();
        ArrayList<String> dataList = new ArrayList<>();

        //Adding data in mapList
        for (int j = 0; j < 10; j++){
            for(int i = 0; i < 10; i++){
                dataList.add("Item " + i);
            }
            mapList.put(j, dataList);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("SEND_THIS_TO_THIRD_ACTIVITY_STRING", dataString);
                intent.putExtra("SEND_THIS_TO_THIRD_ACTIVITY_INT", dataInt);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, ThirdActivity.class);
                intent2.putExtra("key", data2);
                startActivityForResult(intent2, REQ_CODE);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, ThirdActivity.class);
                Bundle b = new Bundle();
                b.putString("name", "Pooja");
                intent3.putExtra("personB", b);

                //Sending an array using Bundle
                b.putStringArrayList("expireList", mapList.get(1));

                startActivity(intent3);
            }
        });

    }

    public static String getData(){
        return data;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // The returned result data is identified by requestCode.
        // The request code is specified in startActivityForResult(intent, REQUEST_CODE_1); method.
        switch (requestCode)
        {
            // This request code is set by startActivityForResult(intent, REQUEST_CODE_1) method.
            case REQ_CODE:
                TextView textView = (TextView)findViewById(R.id.resultDataTextView);
                if(resultCode == RESULT_OK)
                {
                    String messageReturn = data.getStringExtra("message_return");
                    textView.setText(messageReturn);
                }
        }
    }
}
