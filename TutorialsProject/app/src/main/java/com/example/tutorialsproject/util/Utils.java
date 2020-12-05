package com.example.tutorialsproject.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.util.Patterns;
import android.view.WindowManager;

import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;
import com.example.tutorialsproject.R;
import com.example.tutorialsproject.activity.SecondActivity;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    private static String EMAIL_EXPRESSION = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    private static   final Pattern IP_ADDRESS
            = Pattern.compile(
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9]))");


    public static void log(String TAG , String message) {
        Log.d(TAG, message);
    }

    public static String getWifiIpAddress(Context context) {
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();

        return Formatter.formatIpAddress(ip);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isValidIP(String ip ){
        boolean validate = false ;
        Matcher matcher = IP_ADDRESS.matcher(ip);
        if (matcher.matches()) {
            return true ;
        }
        return validate  ;
    }

    public static void showMessageDialog(Context context, String title, String message, int imageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setIcon(imageId)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean isGPSEnable(Context context){

        final LocationManager manager = (LocationManager)context.getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            return false ;
        }
        return  true ;
    }


    public static void showGPSEnableDialog(final Context context){
        showGPSEnableDialog(context,"Your GPS seems to be disabled, do you want to enable it?","Yes","No");
    }

    public static  void showGPSEnableDialog(final Context context , String message , String positiveButtonText, String negativeButtonText){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sendNotification(Context context) {

        NotificationManager mNotificationManager;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext(), "notify_001");
        Intent i = new Intent(context.getApplicationContext(), SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Hi");
        bigText.setBigContentTitle("Today's Bible Verse");
        bigText.setSummaryText("Text in detail");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.dribble);
        mBuilder.setContentTitle("Your Title");
        mBuilder.setContentText("Your text");
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }

    public boolean isEmailvalid(String emailId) {
        return Pattern.compile(EMAIL_EXPRESSION, Pattern.CASE_INSENSITIVE).matcher(emailId).matches();
    }

    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);

        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }

    public int appVersion(Context context) {
        int version = -1;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * Checks if the SD Card is mounted on the device.
     **/
    public static boolean isSdCardMounted() {
        String status = Environment.getExternalStorageState();

        if (status != null && status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }

        return false;
    }

    @SuppressLint("MissingPermission")
    public static String getDeviceIMEI(Context context){
        String imei="";
        try{
            TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= 26) {
                imei=telephonyManager.getImei();
            }
            else
            {
                imei=telephonyManager.getDeviceId();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return imei ;
    }

    /**
     * Disable screenshot functionality.
     */
    public static void disableScreenshotFunctionality(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     **/
    public static void showAlertDialog(Context context, @StringRes int titleResId, @StringRes int bodyResId) {
        showAlertDialog(context, context.getString(titleResId), context.getString(bodyResId), null);
    }

    /**
     * Shows an alert dialog with the OK button. When the user presses OK button, the dialog
     * dismisses.
     **/
    public static void showAlertDialog(Context context, String title, String body) {
        showAlertDialog(context, title, body, null);
    }

    /**
     * Shows an alert dialog with OK button
     **/
    public static void showAlertDialog(Context context, String title, String body, DialogInterface.OnClickListener okListener) {

        if (okListener == null) {
            okListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(body).setPositiveButton("OK", okListener);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        builder.show();
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     */
    public static int getDip(int px, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     **/
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirmDialog(ctx, message, yesListener, noListener, "Yes", "No");
    }

    /**
     * Creates a confirmation dialog with Yes-No Button. By default the buttons just dismiss the
     * dialog.
     *
     * @param ctx
     * @param message     Message to be shown in the dialog.
     * @param yesListener Yes click handler
     * @param noListener
     * @param yesLabel    Label for yes button
     * @param noLabel     Label for no button
     **/
    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener, String yesLabel, String noLabel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        builder.setMessage(message).setPositiveButton(yesLabel, yesListener).setNegativeButton(noLabel, noListener).show();
    }

    /**
     * Creates a confirmation dialog that show a pop-up with button labeled as parameters labels.
     **/
    public static void showDialog(Context ctx, String message, String positiveBtnLabel, String negativeBtnLabel, DialogInterface.OnClickListener dialogClickListener) {

        if (dialogClickListener == null) {
            throw new NullPointerException("Action listener cannot be null");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage(message).setPositiveButton(positiveBtnLabel, dialogClickListener).setNegativeButton(negativeBtnLabel, dialogClickListener).show();
    }

    /**
     * Gets the version name of the application. For e.g. 1.9.3
     **/
    public static String getApplicationVersionNumber(Context context) {

        String versionName = null;

        if (context == null) {
            return versionName;
        }

        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * Gets the version code of the application. For e.g. Maverick Meerkat or 2013050301
     **/
    public static int getApplicationVersionCode(Context ctx) {

        int versionCode = 0;

        try {
            versionCode = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * Gets the version number of the Android OS For e.g. 2.3.4 or 4.1.2
     **/
    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Checks if the service with the given name is currently running on the device.
     *
     * @param serviceName Fully qualified name of the server. <br/>
     *                    For e.g. nl.changer.myservice.name
     **/
    public static boolean isServiceRunning(Context ctx, String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("Service name cannot be null");
        }

        // use application level context to avoid unnecessary leaks.
        ActivityManager manager = (ActivityManager) ctx.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.service.getClassName().equals(serviceName)) {
                return true;
            }
        }

        return false;
    }

    public static Bitmap getBitmapFromFile(String path) {
        File imageFile = new File(path);
        Bitmap bitmap = null;
        if (imageFile.exists()) {
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromURI(Uri uri, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * Shares an application over the social network like Facebook, Twitter etc.
     *
     * @param sharingMsg   Message to be pre-populated when the 3rd party app dialog opens up.
     * @param emailSubject Message that shows up as a subject while sharing through email.
     * @param title        Title of the sharing options prompt. For e.g. "Share via" or "Share using"
     **/
    public static void share(Context ctx, String sharingMsg, String emailSubject, String title) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharingMsg);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);

        ctx.startActivity(Intent.createChooser(sharingIntent, title));
    }

    /**
     * Checks the type of data connection that is currently available on the device.
     *
     * @return <code>ConnectivityManager.TYPE_*</code> as a type of internet connection on the
     * device. Returns -1 in case of error or none of
     * <code>ConnectivityManager.TYPE_*</code> is found.
     **/
    public static int getDataConnectionType(Context ctx) {

        // use application level context to avoid unnecessary leaks.
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return ConnectivityManager.TYPE_MOBILE;
            } else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return ConnectivityManager.TYPE_WIFI;
            } else
                return -1;
        } else
            return -1;
    }

    /**
     * Checks if the DB with the given name is present on the device.
     */
    public static boolean isDatabasePresent(String packageName, String dbName) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase("/data/data/" + packageName + "/databases/" + dbName, null, SQLiteDatabase.OPEN_READONLY);
            sqLiteDatabase.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            e.printStackTrace();
            Log.e(TAG, "The database does not exist." + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Exception " + e.getMessage());
        }

        return (sqLiteDatabase != null);
    }

    public static ArrayList<String> toStringArray(JSONArray jsonArr) {

        if (jsonArr == null || jsonArr.length() == 0) {
            return null;
        }

        ArrayList<String> stringArray = new ArrayList<String>();

        for (int i = 0, count = jsonArr.length(); i < count; i++) {
            try {
                String str = jsonArr.getString(i);
                stringArray.add(str);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringArray;
    }

    /**
     * Convert a given list of {@link String} into a {@link org.json.JSONArray}
     **/
    public static JSONArray toJSONArray(ArrayList<String> stringArr) {
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < stringArr.size(); i++) {
            String value = stringArr.get(i);
            jsonArr.put(value);
        }

        return jsonArr;
    }

    /**
     * Gets the data storage directory(pictures dir) for the device. If the external storage is not
     * available, this returns the reserved application data storage directory. SD Card storage will
     * be preferred over internal storage.
     *
     * @param dirName if the directory name is specified, it is created inside the DIRECTORY_PICTURES
     *                directory.
     * @return Data storage directory on the device. Maybe be a directory on SD Card or internal
     * storage of the device.
     **/
    public static File getStorageDirectory(Context ctx, String dirName) {

        if (TextUtils.isEmpty(dirName)) {
            dirName = "atemp";
        }

        File f = null;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + dirName);
        } else {
            // media is removed, unmounted etc
            // Store image in
            // /data/data/<package-name>/cache/atemp/photograph.jpeg
            f = new File(ctx.getCacheDir() + "/" + dirName);
        }

        if (!f.exists()) {
            f.mkdirs();
        }

        return f;
    }

    /**
     * Given a file name, this method creates a {@link java.io.File} on best chosen device storage and
     * returns the file object. You can get the file path using {@link java.io.File#getAbsolutePath()}
     **/
    public static File getFile(Context ctx, String fileName) {
        File dir = getStorageDirectory(ctx, null);
        File f = new File(dir, fileName);
        return f;
    }

    /**
     * Gets the name of the application that has been defined in AndroidManifest.xml
     *
     * @throws android.content.pm.PackageManager.NameNotFoundException
     **/
    public static String getApplicationName(Context ctx) throws PackageManager.NameNotFoundException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        final PackageManager packageMgr = ctx.getPackageManager();
        ApplicationInfo appInfo = null;

        try {
            appInfo = packageMgr.getApplicationInfo(ctx.getPackageName(), PackageManager.MATCH_SYSTEM_ONLY);
        } catch (final PackageManager.NameNotFoundException e) {
            throw new PackageManager.NameNotFoundException(e.getMessage());
        }

        final String applicationName = (String) (appInfo != null ? packageMgr.getApplicationLabel(appInfo) : "UNKNOWN");

        return applicationName;
    }

    /**
     * Returns the URL without the query string
     **/
    public static URL getPathFromUrl(URL url) {

        if (url != null) {
            String urlStr = url.toString();
            String urlWithoutQueryString = urlStr.split("\\?")[0];
            try {
                return new URL(urlWithoutQueryString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Set Mock Location for test device. DDMS cannot be used to mock location on an actual device.
     * So this method should be used which forces the GPS Provider to mock the location on an actual
     * device.
     **/
    public static void setMockLocation(Context ctx, double longitude, double latitude) {
        // use application level context to avoid unnecessary leaks.
        LocationManager locationManager = (LocationManager) ctx.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.addTestProvider(LocationManager.GPS_PROVIDER, "requiresNetwork" == "", "requiresSatellite" == "", "requiresCell" == "", "hasMonetaryCost" == "", "supportsAltitude" == "", "supportsSpeed" == "", "supportsBearing" == "",

                android.location.Criteria.POWER_LOW, android.location.Criteria.ACCURACY_FINE);

        Location newLocation = new Location(LocationManager.GPS_PROVIDER);

        newLocation.setLongitude(longitude);
        newLocation.setLatitude(latitude);
        newLocation.setTime(new Date().getTime());

        newLocation.setAccuracy(500);

        locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        locationManager.setTestProviderStatus(LocationManager.GPS_PROVIDER, LocationProvider.AVAILABLE, null, System.currentTimeMillis());

        // http://jgrasstechtips.blogspot.it/2012/12/android-incomplete-location-object.html
        makeLocationObjectComplete(newLocation);

        locationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);
    }

    private static void makeLocationObjectComplete(Location newLocation) {
        Method locationJellyBeanFixMethod = null;
        try {
            locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (locationJellyBeanFixMethod != null) {
            try {
                locationJellyBeanFixMethod.invoke(newLocation);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the url is valid
     */
    public static boolean isValidURL(String url) {
        URL urlObj;

        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }

        try {
            urlObj.toURI();
        } catch (URISyntaxException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidURL2(String urlStr) {
        return  Patterns.WEB_URL.matcher(urlStr).matches();
    }

    /**
     * Formats given size in bytes to KB, MB, GB or whatever. This will work up to 1000 TB
     */
    public static String formatSize(long size) {

        if (size <= 0) return "0";

        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * Formats given size in bytes to KB, MB, GB or whatever. Preferably use this method for
     * performance efficiency.
     *
     * @param si Controls byte value precision. If true, formatting is done using approx. 1000 Uses
     *           1024 if false.
     **/
    public static String formatSize(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;

        if (bytes < unit) {
            return bytes + " B";
        }

        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    /**
     * Creates the uri to a file located on external storage or application internal storage.
     */
    public static Uri createUri(Context ctx) {
        File root = getStorageDirectory(ctx, null);
        root.mkdirs();
        File file = new File(root, Long.toString(new Date().getTime()));
        Uri uri = Uri.fromFile(file);

        return uri;
    }

    /**
     * Checks if given url is a relative path.
     */
    public static final boolean isRelativeUrl(String url) {

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        Uri uri = Uri.parse(url);

        return uri.getScheme() == null;
    }

    /**
     * Checks if the parameter {@link android.net.Uri} is a content uri.
     **/
    public static boolean isContentUri(Uri uri) {
        if (!uri.toString().contains("content://")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the build version passed as the parameter is
     * lower than the current build version.
     *
     * @param buildVersion One of the values from {@link android.os.Build.VERSION_CODES}
     * @return
     */
    public static boolean isBuildBelow(int buildVersion) {
        if (Build.VERSION.SDK_INT < buildVersion) return true;
        else return false;
    }

    /**
     * Register a listener for getting updates of device charging, discharging or completely charged.
     */
    public static void registerBatteryChangeBroadcastReceiver(Context context, final BatteryChargeListener batteryChargeListener) {
        final IntentFilter theFilter = new IntentFilter();
        /* System Defined Broadcast */
        theFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        BroadcastReceiver batteryChargeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);

                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    // Is Charging
                    batteryChargeListener.isCharging();
                }
                if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    // Is Full
                    batteryChargeListener.isFull();
                } else {
                    // Is Discharging
                    batteryChargeListener.isDiscahrging();
                }
            }
        };
        context.getApplicationContext().registerReceiver(batteryChargeReceiver, theFilter);
    }

    /**
     * The interface Battery charge listener.
     */
    public interface BatteryChargeListener {

        /**
         * Is charging.
         */
        void isCharging();

        /**
         * Is discahrging.
         */
        void isDiscahrging();

        /**
         * Is full.
         */
        void isFull();
    }

}
