package com.example.tutorialsproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.tutorialsproject.util.UiUtil;
import com.example.tutorialsproject.util.Utils;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(SMS_RECEIVED)){
            UiUtil.showToast(context,"broadcast receiver: You have a message.");
        }

    }
}
