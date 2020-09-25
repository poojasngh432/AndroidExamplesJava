package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tutorialsproject.R;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {
    TextView fullName;
    TextInputLayout phoneNo, password, fullNameET, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks
        fullName = findViewById(R.id.full_name_profile);
        fullNameET = findViewById(R.id.user_name);
        phoneNo = findViewById(R.id.user_phoneNo);
        password = findViewById(R.id.user_password);
        email = findViewById(R.id.user_email);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        //String user_username = intent.getStringExtra("username");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        fullName.setText(user_name);
        fullNameET.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);
    }
}
