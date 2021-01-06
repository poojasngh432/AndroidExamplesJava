package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tutorialsproject.R;
import com.example.tutorialsproject.database.model.User;
import com.example.tutorialsproject.databinding.ActivityObservableBinding;

public class ObservableActivity extends AppCompatActivity {
    private User user;
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_observable);

        ActivityObservableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_observable);

        user = new User();
        user.setFirstName("Jessica");
        user.setLastName("Day");
        binding.setUser(user);

        clickHandler = new ClickHandler(binding.firstnameET,binding.lastnameET);
        binding.setClickHandler(clickHandler);
    }

    public class ClickHandler{
        private EditText fName, lName;

        public ClickHandler(EditText fName, EditText lName) {
            this.fName = fName;
            this.lName = lName;
        }

        public void updateUser(View view){
            user.setFirstName(fName.getText().toString());
            user.setLastName(lName.getText().toString());
            fName.setText("");
            lName.setText("");
        }
    }
}
