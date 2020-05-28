package com.example.savetostorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 88888;
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn1){
            Intent i1 = new Intent(this, SaveToInternalStorageActivity.class);
            startActivity(i1);
        }else if(view.getId() == R.id.btn2){
            Intent i2 = new Intent(this, ClickImgAndSaveActivity.class);
            startActivity(i2);
        }else if(view.getId() == R.id.btn3){
            Intent i3 = new Intent(this, SaveToInternalStorageActivity.class);
            startActivity(i3);
        }else if(view.getId() == R.id.btn4){
            Intent i4 = new Intent(this, SaveToInternalStorageActivity.class);
            startActivity(i4);
        }
    }
}
