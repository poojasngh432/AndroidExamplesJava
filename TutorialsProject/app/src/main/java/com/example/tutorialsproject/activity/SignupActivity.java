package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.helper.UserHelperClass;
import com.example.tutorialsproject.util.UiUtil;
import com.example.tutorialsproject.util.Utils;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This Line will hide the status bar from the screen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_signup);

        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        //Store data inside our firebase
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To get the instance of our firebase database
                rootNode = FirebaseDatabase.getInstance();
                //to specify which reference we want. For now we have Courses and users
                reference = rootNode.getReference("users");

                //get all the values entered by user
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                if(name.isEmpty())
                    regName.setError("Field cannot be empty");
                else if(username.isEmpty())
                    regUsername.setError("Field cannot be empty");
                else if(email.isEmpty())
                    regEmail.setError("Field cannot be empty");
                else if(phoneNo.isEmpty())
                    regPhoneNo.setError("Field cannot be empty");
                else if(password.isEmpty())
                    regPassword.setError("Field cannot be empty");
                else{
                    UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
                    //to set the values of all users using phoneNo as their id
                    reference.child(username).setValue(helperClass);

                    UiUtil.showToast(SignupActivity.this,"Registration completed");

                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                    intent.putExtra("name", name);
                    intent.putExtra("username", username);
                    intent.putExtra("email", email);
                    intent.putExtra("phoneNo", phoneNo);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
            }
        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
