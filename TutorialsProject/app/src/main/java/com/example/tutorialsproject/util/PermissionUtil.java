package com.example.tutorialsproject.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.WindowManager;

public class PermissionUtil {

    /**
     * Check if permission granted boolean.
     */
    public static boolean checkIfPermissionGranted(Context context, String permission) {
        return (context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
    }




}
