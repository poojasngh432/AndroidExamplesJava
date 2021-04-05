//package com.example.tutorialsproject.util;
//
//import com.example.tutorialsproject.BuildConfig;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.ConnectionPool;
//import okhttp3.Dispatcher;
//import okhttp3.HttpUrl;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RetrofitClientInstance {
//
//    private static Retrofit mRetrofit;
//
//    private static Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
//            .create();
//
//    public RetrofitClientInstance() {
//
//    }
//
////    public static Retrofit getRetrofitInstance(String baseUrl) {
////
////        if (mRetrofit == null) {
////            mRetrofit = new Retrofit.Builder()
////                    .client(getOkHttpClient().build())
////                    .baseUrl(baseUrl)
////                    .addConverterFactory(ScalarsConverterFactory.create())
////                    .addConverterFactory(GsonConverterFactory.create(gson))
////                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                    .build();
////        }
////        return mRetrofit;
////    }
//
//    public static Retrofit getRetrofit() {
//        return mRetrofit;
//    }
//
//    private static OkHttpClient.Builder getOkHttpClient() {
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        int NETWORK_CALL_TIMEOUT = 60;
//
//        httpClient.readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
//        httpClient.connectTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
//        httpClient.writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);
//
//        Dispatcher dispatcher = new Dispatcher(Executors.newFixedThreadPool(20));
//        dispatcher.setMaxRequests(20);
//        dispatcher.setMaxRequestsPerHost(10);
//
//        int maxConnections = 5;
//        int keepAliveDuration = 15000;
//        ConnectionPool cp = new ConnectionPool(maxConnections, keepAliveDuration, TimeUnit.MILLISECONDS);
//        httpClient.connectionPool(cp);
//
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClient.addInterceptor(interceptor);
//        }
//
//        httpClient.addInterceptor(chain -> {
//
//            HttpUrl url = chain.request().url().newBuilder()
//                    .addQueryParameter("lang", SharedPreferenceUtils.getStringPreference(SharedPreferenceUtils.KEY_CURRENT_LANG, "hi"))
//                    .addQueryParameter("ver", String.format("%s", BuildConfig.VERSION_CODE))
//                    .build();
//
//            Request request = chain.request().newBuilder()
//                    .addHeader("Accept", "application/json;charset=UTF-8")
//                    .addHeader("Content-Type", "application/json;charset=UTF-8")
//                    .url(url)
//                    .build();
//
//            return chain.proceed(request);
//
//        });
//        return httpClient;
//    }
//
//    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
//        return getRetrofitInstance(baseUrl).create(serviceClass);
//    }
//
//}