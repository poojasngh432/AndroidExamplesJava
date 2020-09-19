package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tutorialsproject.BaseClass;
import com.example.tutorialsproject.DownloadIntentService;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.util.UiUtil;
import com.example.tutorialsproject.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_WRITE;

public class IntentsActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1: //Implicit Intent
                //share image
                String imgUrl = "https://i.pinimg.com/564x/7f/98/f1/7f98f1405d192f64c5d9548253ab4c66.jpg";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this photo on this link " + imgUrl);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
            case R.id.button2: //Explicit Intent
                Intent i2 = new Intent(IntentsActivity.this, SecondActivity.class);
                startActivity(i2);
                break;
            case R.id.button3: //Start a Service (also an EXPLICIT INTENT)
                String url = "https://static.boredpanda.com/blog/wp-content/uploads/2015/10/game-of-thrones-memes-9__605.jpg";
                Intent intent = new Intent(this, DownloadIntentService.class);
                intent.putExtra(DownloadIntentService.URL, url);
                if (checkPermission()){
                    startService(intent);
                    UiUtil.showToast(IntentsActivity.this,"Download Service started");
                } else {
                    UiUtil.showToast(this, "Need Permission to access storage for Downloading Image");
                }
                break;
            case R.id.button4: //Pending Intent
                Utils.sendNotification(getApplicationContext());
                break;
            case R.id.button5: //Start Content Provider
                break;
        }
    }

    //runtime storage permission
    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE);
                return false;
            }
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 16) {
            int index = 0;
            Map<String, Integer> permissionsMap = new HashMap<>();
            for (String permission : permissions) {
                permissionsMap.put(permission, grantResults[index]);
                index++;
            }

            if(permissionsMap.containsKey(Manifest.permission.WRITE_EXTERNAL_STORAGE) && permissionsMap.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == 0) {

            }
        }
    }

}
