package com.example.tutorialsproject.mvp1mg;

import android.app.Application;

import org.json.JSONException;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.Request;

public class DiagnosticsCartInteractorImpl implements DiagnosticsCartInteractor {

    @Override
    public Observable<Test> getCart() {
        return Observable.fromCallable(this::get);
    }

    private Test get() throws Exception {
        String url = String.format(HkpApi.Diagnostics.Cart.GET_CART_URL, URLEncoder.encode("Delhi", "UTF-8"));
        Request request = RequestGenerator.get(url);
        String response = DiagnosticsRequestHandler.makeRequest(request, Application.class.newInstance().getBaseContext());
        return new Test();
    }

}
