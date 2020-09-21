package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tutorialsproject.Interface.PhoneInterface;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.util.AsyncTaskUtil;
import com.example.tutorialsproject.util.SingletonClass;
import com.example.tutorialsproject.util.UiUtil;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.test_text);
        displayState();
        findViewById(R.id.test_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingletonClass.getInstance().setState(5);
                displayState();
            }
        });
    }

    private void displayState() {
        int state = SingletonClass.getInstance().getState();
        textView.setText(String.valueOf(state));
    }

    private void asyncMethod(){
        new AsyncTaskUtil(this, new PhoneInterface() {
            @Override
            public void isNetworkAvailable() {

            }

            @Override
            public void isAirplaneModeOn() {

            }
        });
    }
}
