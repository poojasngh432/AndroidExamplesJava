package com.example.tutorialsproject.mvp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tutorialsproject.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MVPMainActivity extends AppCompatActivity implements MVPMainActivityContract.View {
    MVPMainActivityContract.Presenter presenter;
    TextInputEditText emailET, passwordET;
    Button btn_login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new MVPMAinActivityPresenter(this);

        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        btn_login = findViewById(R.id.login_btn);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    onError("Field(s) required");
                } else {
                    presenter.doLogin(email, password);
                }
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
