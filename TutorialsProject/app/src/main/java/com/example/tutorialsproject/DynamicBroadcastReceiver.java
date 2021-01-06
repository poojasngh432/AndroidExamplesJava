package com.example.tutorialsproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DynamicBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Broadcast Received Message: ",Toast.LENGTH_LONG).show();

        String url = "https://static.boredpanda.com/blog/wp-content/uploads/2015/10/game-of-thrones-memes-9__605.jpg";

        //To start a Service fropm here bcz onReceive works on Main Thread
        Intent i = new Intent(context, DownloadIntentService.class);
        i.putExtra(DownloadIntentService.URL, url);
        context.startService(i);
    }
}
