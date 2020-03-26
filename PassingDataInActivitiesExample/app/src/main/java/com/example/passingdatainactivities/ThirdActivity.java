package com.example.passingdatainactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {
    private Intent intent;
    private int data = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String receivedStringData = intent.getStringExtra("SEND_THIS_TO_THIRD_ACTIVITY_STRING");
        int receivedSIntData = intent.getIntExtra("SEND_THIS_TO_THIRD_ACTIVITY_INT", 0);
        intent.putExtra("key", data);
        setResult(RESULT_OK, intent);

        Bundle b = intent.getBundleExtra("personB");
        String name = b.getString("name");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent2 = new Intent();
        intent2.putExtra("key", "This data is returned when user click back menu in target activity.");
        setResult(RESULT_OK, intent2);
        finish();
    }
}
