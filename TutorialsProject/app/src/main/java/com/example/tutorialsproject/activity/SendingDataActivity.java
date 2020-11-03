package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.Product;

import java.util.ArrayList;

public class SendingDataActivity extends AppCompatActivity {
    Product product;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_data);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        product = (Product) getIntent().getSerializableExtra("key_serializable");
        String title = product.getTitle();

        textView1.setText(title);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        ArrayList<String> object = (ArrayList<String>) bundle.getSerializable("ARRAYLIST");
        String name = object.get(1);
        textView2.setText(name);

    }
}
