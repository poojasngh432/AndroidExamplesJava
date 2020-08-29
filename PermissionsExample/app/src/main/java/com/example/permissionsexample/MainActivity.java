package com.example.permissionsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button btnLoadContacts;
    private int MY_PERMISSION_REQUEST_READ_CONTACTS = 20;
    private int MY_PERMISSION_REQUEST_WRITE_CONTACTS = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_load_contacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadContacts();
            }
        });
    }

    private void loadContacts() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "All permissions given", Toast.LENGTH_SHORT).show();   //means permission is granted
        }else{
           //it reaches here if it's the first run of the appl or was denied permission before
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){
                //return the rationale
                Snackbar.make(findViewById(android.R.id.content),"Need permission for loading data",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSION_REQUEST_READ_CONTACTS);
                    }
                }).show();

            }else{
                //or directly ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSION_REQUEST_READ_CONTACTS);  //an array for permissions needed, here we only need to read contacts
            }

        }
    }
}
