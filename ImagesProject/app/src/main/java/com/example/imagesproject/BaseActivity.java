package com.example.imagesproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import com.onechannel.database.dao.PreferencesDao;
import com.onechannel.database.dao.TblProjectsDao;
import com.onechannel.database.dao.TblUsersDao;
import com.onechannel.edge.CurrentUserDetails;
import com.onechannel.edge.Gac;
import com.onechannel.edge.PlannedStoreListHelper;
import com.onechannel.location.LocationTracking;
import com.onechannel.location.OnLocationListener;
import com.onechannel.util.DialogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shivansh on 15/04/15.
 */
public class BaseActivity extends AppCompatActivity implements OnLocationListener {
    private static final int PERMISSION_ALL = 5;
    private static final String TAG = BaseActivity.class.getSimpleName();
    public static boolean showAttendanceDialog;
    public static int tempProjectId;
    public static boolean appSwipe;
    private boolean locationPermission = true;
    private AlertDialog autotimeAlertDialog;
    private AlertDialog permissionDialog;
    private AlertDialog flightAlertDialog;
    private int detectGps = 1;
    private Context mContext;
    private LocationTracking locationTracking;
    private boolean isNotificationsEnabled;
    private AlertDialog notificationDialog;
    private AlertDialog attendanceDialog;

    @SuppressLint("NewApi")
    private static boolean isAirplaneModeOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    public static boolean checkDistanceTL(String storeGps) {
        int toleranceValue = new TblProjectsDao().fetchToleranceValueTl();
        if (toleranceValue == 0 || new TblUsersDao().fetchDetectGpsStatus() == 0)
            return true;
        else {
            float storeDistance = new PlannedStoreListHelper().calculateStoreDistance(storeGps);
            if (storeDistance < 0) {
                return true;
            } else if (storeDistance * 1000 > toleranceValue) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
        showAttendanceDialog = false;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        detectGps = new TblUsersDao().fetchDetectGpsStatus();
        if (detectGps == 1 && locationTracking != null) {
            Log.i(TAG, "onUserInteraction");
            locationTracking.checkValidationGPSAccuracyStatus();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new PreferencesDao().insertAppStatus(getString(R.string.running));
        appSwipe = false;
        DialogUtil.cancelGPSAlert(BaseActivity.this);

        isNotificationsEnabled = NotificationManagerCompat.from(this).areNotificationsEnabled();
        if (!isNotificationsEnabled)
            showNotificationEnable(this);
        //If user has clicked on the action button for the attendance notification then show him the alert dialog
        if (showAttendanceDialog) {
            markAttendanceDialog();
        }

        detectGps = new TblUsersDao().fetchDetectGpsStatus();

      /*  if (assistant == null) {
            assistant = new LocationAssistant(mContext, this, LocationAssistant.Accuracy.HIGH, 3000, false, detectGps);
            assistant.setVerbose(true);
        }*/

        locationTracking = new LocationTracking(BaseActivity.this, detectGps);
        locationTracking.setListener(this);

        if (!(isAutoTimeEnabled() && isAutoTimeZoneEnabled())) {
            showDateTimeErrorMessage(getString(R.string.auto_date_time_setting));
        }

        if (isAirplaneModeOn(this)) {
            showFlightModeErrorMessage();
        }
        disableAutoFill();

        if (detectGps == 1) { // location fetch
            String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECORD_AUDIO};
            if (!hasPermissions(PERMISSIONS) && (permissionDialog == null || !permissionDialog.isShowing())) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            } else {
                //assistant.start();

                try {
                    locationTracking.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

               /* final LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if (!gps || !network) {
                    if (!isMockLocationOn) {
                        DialogUtil.showGPSAlert(getString(R.string.enable_location_services), BaseActivity.this);
                    }

                }*/
            }
        } else {

            CurrentUserDetails.setBlankGpsLocation("");
            CurrentUserDetails.setBlankGpsAccuracy(0);

            String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECORD_AUDIO};
            if (!hasPermissions(PERMISSIONS) && (permissionDialog == null || !permissionDialog.isShowing())) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            } else {
                try {
                    locationTracking.stopLocationUpdates();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // assistant.stop();
            }
        }
//        setTheme(R.style.AppThemeCamera);
//        Gac.getInstance().showRandomAttendanceMessage(mContext);
/*        FloatingActionButton fab = new FloatingActionButton(mContext);
        fab.setBackgroundColor(Color.RED);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Gac.getInstance().showSnackBarMessage(":", fab);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        // assistant.stop();

        if (autotimeAlertDialog != null && autotimeAlertDialog.isShowing()) {
            autotimeAlertDialog.dismiss();
        }
        if (permissionDialog != null && permissionDialog.isShowing()) {
            permissionDialog.dismiss();
        }
        if (flightAlertDialog != null && flightAlertDialog.isShowing()) {
            flightAlertDialog.dismiss();
        }
        if (notificationDialog != null && notificationDialog.isShowing()) {
            notificationDialog.dismiss();
        }
        if (attendanceDialog != null && attendanceDialog.isShowing()) {
            attendanceDialog.dismiss();
        }

        try {
            locationTracking.stopLocationUpdates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableAutoFill() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            getWindow().getDecorView().setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS);
    }

    @SuppressLint("NewApi")
    private boolean isAutoTimeEnabled() {
        return Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME, 0) > 0;
    }

    @SuppressLint("NewApi")
    private boolean isAutoTimeZoneEnabled() {
        PackageManager pm = getPackageManager();
        boolean isAPhone = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        return !isAPhone || Settings.Global.getInt(getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0) > 0;
    }

    private void showDateTimeErrorMessage(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.error);

        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.go_to_settings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
                        dialog.dismiss();
                    }
                });
        autotimeAlertDialog = alertDialogBuilder.create();
        autotimeAlertDialog.show();
    }

    private void showFlightModeErrorMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.flight_mode_on);

        alertDialogBuilder
                .setMessage(R.string.disable_flight_mode)
                .setCancelable(false)
                .setPositiveButton(R.string.disable, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                        dialog.dismiss();
                    }
                });

        flightAlertDialog = alertDialogBuilder.create();
        flightAlertDialog.show();
    }

    private boolean hasPermissions(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(BaseActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION) || permission.equals(Manifest.permission.ACCESS_FINE_LOCATION))
                        locationPermission = false;
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ALL: {
                if (detectGps == 1)
                    checkPermissionForGpsAlso(permissions, grantResults);
                else
                    checkPermissionWithoutGps(permissions, grantResults);
            }
        }

    }

    private void checkPermissionWithoutGps(@NonNull final String permissions[], @NonNull int[] grantResults) {
        Map<String, Integer> perms = new HashMap<>();
        // Initialize the map with both permissions
        perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);
        // Fill with actual results from user
        if (grantResults.length > 0) {
            for (int i = 0; i < permissions.length; i++)
                perms.put(permissions[i], grantResults[i]);
            // Check for both permissions
            if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                // process the normal flow
                //else any one or both the permissions are not granted
                // assistant.stop();
                try {
                    locationTracking.stopLocationUpdates();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                    showDialogOK(getResources().getString(R.string.permissions_required),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            permissionDialog.dismiss();
                                            if (!hasPermissions(permissions))
                                                ActivityCompat.requestPermissions(BaseActivity.this, permissions, PERMISSION_ALL);
                                            break;
                                    }
                                }
                            });
                } else {
                    showDialogOK(getResources().getString(R.string.enable_permissions),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            permissionDialog.dismiss();
                                            final Intent i = new Intent();
                                            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            i.addCategory(Intent.CATEGORY_DEFAULT);
                                            i.setData(Uri.parse("package:" + getPackageName()));
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                            startActivity(i);
                                            break;
                                    }
                                }
                            });
                }
            }
        }
    }

    private void checkPermissionForGpsAlso(@NonNull final String permissions[], @NonNull int[] grantResults) {
        Map<String, Integer> perms = new HashMap<>();
        // Initialize the map with both permissions
        perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);
        // Fill with actual results from user
        if (grantResults.length > 0) {
            for (int i = 0; i < permissions.length; i++)
                perms.put(permissions[i], grantResults[i]);
            // Check for both permissions
            if (perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                // process the normal flow else any one or both the permissions are not granted
                //assistant.start();

                try {
                    locationTracking.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                    showDialogOK(getResources().getString(R.string.permissions_required),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            permissionDialog.dismiss();
                                            if (!hasPermissions(permissions))
                                                ActivityCompat.requestPermissions(BaseActivity.this, permissions, PERMISSION_ALL);
                                            break;
                                    }
                                }
                            });
                } else {
                    showDialogOK(getResources().getString(R.string.enable_permissions),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            permissionDialog.dismiss();
                                            final Intent i = new Intent();
                                            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            i.addCategory(Intent.CATEGORY_DEFAULT);
                                            i.setData(Uri.parse("package:" + getPackageName()));
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                            startActivity(i);
                                            break;
                                    }
                                }
                            });
                }
            }
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        if (permissionDialog != null && permissionDialog.isShowing())
            permissionDialog.dismiss();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.alert_dialog_ok_label), okListener);

        permissionDialog = alertDialogBuilder.create();
        permissionDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (assistant != null) {
            assistant.onActivityResult(requestCode, resultCode);
        }*/
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewLocation(Location location) {

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        //Toast.makeText(BaseActivity.this, "Callback Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
//        Log.e("CallBack", "Callback Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude));

        if (detectGps == 1 && location != null) {
            CurrentUserDetails.setGpsLocation(location.getLatitude() + " " + location.getLongitude());
            CurrentUserDetails.setGpsAccuracy(location.getAccuracy());

            if (mContext instanceof ManageRouteActivity || mContext instanceof RoutePlanner)
                CurrentUserDetails.setAddress(Gac.getInstance().getCurrentAddress(location.getLatitude(), location.getLongitude()));
        }

        if (detectGps == 0) {
            try {
                CurrentUserDetails.setBlankGpsLocation("");
                CurrentUserDetails.setBlankGpsAccuracy(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onDestroy() {
        //if the app is in background then put the status to the closed else dont do anything.
        if (appSwipe) {
            Gac.getInstance().getSharedPreferences().edit().putBoolean("FOREGROUND", false).apply();
            new PreferencesDao().insertAppStatus(getString(R.string.closed));
            Log.i(TAG, "onDestroy: ServiceTest" + new PreferencesDao().fetchPreference("APP_STATUS").getPreferenceValue());
        }
        super.onDestroy();
    }

    private void showNotificationEnable(final Context context) {
        //Function to show the notification enable dialog
        if (notificationDialog != null && notificationDialog.isShowing())
            notificationDialog.dismiss();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.headerErrorText))
                .setMessage(getString(R.string.please_enable_notifications_for_1channel))
                .setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + context.getPackageName()));
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                            intent.putExtra("app_package", context.getPackageName());
                            intent.putExtra("app_uid", context.getApplicationInfo().uid);
                        } else {
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + context.getPackageName()));
                        }
                        startActivity(intent);
                    }
                })
                .setCancelable(false);
        notificationDialog = builder.create();
        notificationDialog.show();
    }

    public void markAttendanceDialog() {
        //Function to show th alert dialog for attendance with buttons cancel and mark now
        if (attendanceDialog != null && attendanceDialog.isShowing())
            attendanceDialog.dismiss();
        showAttendanceDialog = false;
        final Intent intent = new Intent(mContext, AttendanceActivityNew.class);
        int projectId = tempProjectId;
        tempProjectId = 0;
        if (projectId != 0) {
            intent.putExtra("PROJECT_ID", projectId);
        }
        String projectName = new TblProjectsDao(this).fetchProjectNameByProjectId(projectId);
        intent.putExtra("actionType", getIntent().getStringExtra("actionType"));
        intent.putExtra("AlertActivity", true);
        intent.putExtra("USER_ID", getIntent().getIntExtra("USER_ID", -1));
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(getString(R.string.warning))
                .setMessage(getString(R.string.please_finish_all_incomplete_process_before_making_attendance_for) + projectName + ".")
                .setPositiveButton(getString(R.string.mark_now), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(intent);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (attendanceDialog != null && attendanceDialog.isShowing()) {
                            attendanceDialog.dismiss();
                        }
                    }
                })
                .setCancelable(true)
                .setIcon(R.drawable.ic_error);
        attendanceDialog = builder.create();
        attendanceDialog.show();
    }

}