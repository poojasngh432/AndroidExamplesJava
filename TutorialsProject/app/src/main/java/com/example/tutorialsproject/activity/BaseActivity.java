package com.example.tutorialsproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.tutorialsproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public static final String ANDROIDID = "751553f36becc655";
    protected int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;
    protected int MY_PERMISSION_REQUEST_WRITE_CONTACTS = 30;
    protected int MY_PHOTO_TAGGING_PERMISSIONS = 40;

    protected final String [] permissionsNeededForPhotoTagging = new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION};

    String [] deniedPermissionsAmongPhotoTagging;

    protected void requestRunTimePermissions(final Activity activity, final String [] permissions, final int customPermissionConstant){
        if(permissions.length==1){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])){

                Snackbar.make(findViewById(android.R.id.content),"App needs permission to work",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(activity,permissions,customPermissionConstant);
                            }
                        }).show();
            }else {
                ActivityCompat.requestPermissions(this,new String[]{permissions[0]},customPermissionConstant);
            }
        }else if(permissions.length > 1 && customPermissionConstant== MY_PHOTO_TAGGING_PERMISSIONS){
            if(getDeniedPermissionsAmongPhototaggingPermissions().length==1){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, deniedPermissionsAmongPhotoTagging[0])){
                    Snackbar.make(findViewById(android.R.id.content),"App needs permission to work",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    ActivityCompat.requestPermissions(activity, deniedPermissionsAmongPhotoTagging,customPermissionConstant);
                                }
                            }).show();
                }else {
                    ActivityCompat.requestPermissions(activity, deniedPermissionsAmongPhotoTagging,customPermissionConstant);
                }
            }else if(getDeniedPermissionsAmongPhototaggingPermissions().length > 1){
                if(isFirstTimeAskForPhotoTaggingPermission()){
                    ActivityCompat.requestPermissions(activity, deniedPermissionsAmongPhotoTagging,customPermissionConstant);
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"This functionality needs multiple app permissions",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ActivityCompat.requestPermissions(activity, deniedPermissionsAmongPhotoTagging,customPermissionConstant);
                                }
                            }).show();
                }

            }
        }
    }

    protected boolean isFirstTimeAskForPhotoTaggingPermission(){
        SharedPreferences sharedPreferences = getSharedPreferences("permissionasks", MODE_PRIVATE);
        //to read
        boolean isFirstTime = sharedPreferences.getBoolean("PHOTO_FIRST_PERMISSION",true);
        if(isFirstTime){
            //to write
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("PHOTO_FIRST_PERMISSION",false);
            editor.commit();
        }
        return isFirstTime;
    }

    protected boolean checkWhetherAllPermissionsPresentForPhotoTagging(){
        for(String permission: permissionsNeededForPhotoTagging){
            if(ActivityCompat.checkSelfPermission(this, permission)== PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    protected String[] getDeniedPermissionsAmongPhototaggingPermissions(){

        final List<String> deniedPermissions = new ArrayList<String>();
        for(String permission: permissionsNeededForPhotoTagging){
            if(ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_DENIED){
                deniedPermissions.add(permission);
            }
        }
        this.deniedPermissionsAmongPhotoTagging = deniedPermissions.toArray(new String[deniedPermissions.size()]);
        return deniedPermissionsAmongPhotoTagging;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.appPermissions: startAppPermissions();
                break;
            default: break;
        }
        return true;
    }

    private void startAppPermissions(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}