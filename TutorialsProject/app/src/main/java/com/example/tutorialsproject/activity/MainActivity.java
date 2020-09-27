package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorialsproject.Interface.PhoneInterface;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.util.AsyncTaskUtil;
import com.example.tutorialsproject.util.SingletonClass;
import com.example.tutorialsproject.util.UiUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.btn1: intent = new Intent(MainActivity.this,DataBindingActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn2: intent = new Intent(MainActivity.this,DIActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn3: intent = new Intent(MainActivity.this,IntentsActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn4: intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn5: intent = new Intent(MainActivity.this,SignupActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn6: intent = new Intent(MainActivity.this,SingletonActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn7: intent = new Intent(MainActivity.this,ContentProviderActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn8: intent = new Intent(MainActivity.this,LifecycleActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn9: intent = new Intent(MainActivity.this,PermissionsActivity.class);
                            startActivity(intent);
                            break;
            case R.id.btn10: intent = new Intent(MainActivity.this,ServiceActivity.class);
                             startActivity(intent);
                             break;
            case R.id.btn11: intent = new Intent(MainActivity.this,NetworkingActivity.class);
                             startActivity(intent);
                             break;
            case R.id.btn12: intent = new Intent(MainActivity.this,LVandRV.class);
                             startActivity(intent);
                             break;
             default: break;
        }
    }
}
