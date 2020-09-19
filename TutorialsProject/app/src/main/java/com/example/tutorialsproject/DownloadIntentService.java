package com.example.tutorialsproject;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import com.example.tutorialsproject.util.UiUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.bluetooth.BluetoothGattCharacteristic.PERMISSION_WRITE;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * helper methods.
 */
public class DownloadIntentService extends IntentService {
    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlString";

    public DownloadIntentService() {
        super("DownloadIntentService");
    }

    //// Will be called asynchronously by OS.
    @Override
    protected void onHandleIntent(Intent intent) {
        String url = intent.getStringExtra(URL);
        startDownload(url);
    }

    public void startDownload(String url){
        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("Image Download").setDescription("Downloading...")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url.substring(0,5) + ".jpg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);;

        mgr.enqueue(request);
    }



}
