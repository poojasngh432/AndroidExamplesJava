package com.example.broadcastreceiverex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    //will be called when broadcast receiver gets triggered
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "broadcast receiver triggered", Toast.LENGTH_LONG).show();
    }
}
