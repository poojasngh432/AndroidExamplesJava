package com.example.tutorialsproject.mvp1mg;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } else
        {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) return false;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            return isNetworkCapabilityPresent(networkCapabilities);
        }
    }

    public static boolean isNetworkCapabilityPresent(NetworkCapabilities networkCapabilities)
    {
        return networkCapabilities != null &&
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN));
    }

    private static NetworkType getNetworkType(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return NetworkType.UNKNOWN;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return getNetworkTypeForBelowMarshmallow(connectivityManager,context);
        } else
        {
            return getNetworkTypeForAboveMarshmallow(connectivityManager, context);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static NetworkType getNetworkTypeForAboveMarshmallow(ConnectivityManager connectivityManager, Context context)
    {
        Network activeNetwork = connectivityManager.getActiveNetwork();
        NetworkCapabilities networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork);
        if (networkCapabilities != null)
        {
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            {
                return NetworkType.WIFI;
            } else
            {
                getMobileNetworkType(context);
            }
        }
        return NetworkType.UNKNOWN;
    }

    private static NetworkType getNetworkTypeForBelowMarshmallow(ConnectivityManager connectivityManager, Context context)
    {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected())
        {
            int netType = activeNetworkInfo.getType();
            if (netType == ConnectivityManager.TYPE_WIFI)
            {
                return NetworkType.WIFI;
            } else
            {
                return getMobileNetworkType(context);
            }
        }
        return NetworkType.UNKNOWN;
    }

    private static NetworkType getMobileNetworkType(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = telephonyManager.getNetworkType();
        switch (networkType)
        {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetworkType.EDGE;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetworkType.HSPA;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetworkType.LTE;
            default:
                return NetworkType.UNKNOWN;
        }
    }
}
