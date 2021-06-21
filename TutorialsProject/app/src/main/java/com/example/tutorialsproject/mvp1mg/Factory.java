package com.example.tutorialsproject.mvp1mg;

import android.os.Build;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class Factory
{
    private static final int CONNECT_TIMEOUT_IN_MS = 30000;
    private static final int READ_TIMEOUT_IN_MS = 30000;
    private static final int WRITE_TIMEOUT_IN_MS = 30000;
    private static final int SHORT_READ_TIMEOUT_IN_MS = 10000;
    private static final int SHORT_WRITE_TIMEOUT_IN_MS = 10000;

    private static final Object LOCK = new Object();
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOkHTTPClient()
    {
        synchronized (LOCK)
        {
            if (okHttpClient == null)
            {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (isSSLEnabled())
                {
                    CertificatePinner certPinner = new CertificatePinner.Builder()
                            .build();
                    builder.certificatePinner(certPinner);
                }

                if (true)
                {
//                    OkhttpUtility.setLevel(logging, HttpLoggingInterceptor.Level.BODY);
//                    builder.addInterceptor(logging);
//                    builder.addInterceptor(new ChuckerInterceptor(BaseApp.getContext()));
                } else
                {
//                    OkhttpUtility.setLevel(logging, HttpLoggingInterceptor.Level.NONE);
                }
                configureTimeOut(builder);
//                okHttpClient = Tls12SocketFactory.enableTls12OnPreLollipop(builder)
//                        .cookieJar(getClearableCookieJar())
//                        .cache(addCacheDirectory())
//                        .addInterceptor(new SamplingInterceptor()).build();
            }
        }
        return okHttpClient;
    }

    static boolean isSSLEnabled() {
        return false;
    }

    private static void configureTimeOut(OkHttpClient.Builder builder)
    {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
        {
            builder.connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                    .readTimeout(SHORT_READ_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                    .writeTimeout(SHORT_WRITE_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
        } else
        {
            builder.connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                    .readTimeout(READ_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                    .writeTimeout(WRITE_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS);
        }
    }

}