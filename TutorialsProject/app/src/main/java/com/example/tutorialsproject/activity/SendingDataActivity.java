package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorialsproject.DynamicBroadcastReceiver;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.Product;

import java.util.ArrayList;

public class SendingDataActivity extends AppCompatActivity {
    Product product;
    Button btn, btn2;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_data);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        btn = findViewById(R.id.intent_filter_btn);
        btn2 = findViewById(R.id.send_broadcast_btn);

        product = (Product) getIntent().getSerializableExtra("key_serializable");
        String title = product.getTitle();

        textView1.setText(title);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        ArrayList<String> object = (ArrayList<String>) bundle.getSerializable("ARRAYLIST");
        String name = object.get(1);
        textView2.setText(name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setClass(SendingDataActivity.this,DynamicBroadcastReceiver.class); //Without this receiver was not working
                intent.setAction("com.example.tutorialsproject.activity.CUSTOM_INTENT");
                sendBroadcast(intent);

            }
        });

    }

}
