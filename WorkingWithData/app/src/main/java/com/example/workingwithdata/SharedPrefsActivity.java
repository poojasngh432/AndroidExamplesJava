package com.example.workingwithdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrefsActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button b1, b2;
    TextView nameValTV, pwdValTV, emailValTV;
    LinearLayout ll;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);

        nameValTV = findViewById(R.id.nameValueTV);
        pwdValTV = findViewById(R.id.pwdValueTV);
        emailValTV = findViewById(R.id.emailValueTV);

        ll = findViewById(R.id.prev_data_ll);
        ll.setVisibility(View.GONE);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.apply();
                /*
                If we want to remove a key-val from Shared Preferences
                editor.remove(Email);
                editor.apply();      apply again is important after removing
                Or
                To delete everything in shared prefs file
                editor.clear();
                editor.apply();
                    */
                Toast.makeText(SharedPrefsActivity.this,"success",Toast.LENGTH_LONG).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sharedpreferences != null)
                 ll.setVisibility(View.VISIBLE);

                //Read from Shared Preferences
                nameValTV.setText(sharedpreferences.getString(Name, null));
                pwdValTV.setText(sharedpreferences.getString(Phone, null));
                emailValTV.setText(sharedpreferences.getString(Email, null));
            }
        });
    }
}
