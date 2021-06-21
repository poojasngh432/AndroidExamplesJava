package com.example.tutorialsproject.mvp1mg;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.tutorialsproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DiagnosticsRequestHandler {

    private static String apiErrorMessage;
    private static int errorCode;

    public static String makeRequest(Request request, Context context) throws Exception {
        if (NetworkUtils.isNetworkAvailable(context))
        {
            return doRequest(request);
        } else
        {
            throw new NoNetworkException(context.getResources().getString(R.string.no_network_message));
        }
    }

    private static String doRequest(Request request) throws Exception {
        OkHttpClient httpClient = Factory.getOkHTTPClient();
        Response response = httpClient.newCall(request).execute();
        String body = response.body().string();
        if (!response.isSuccessful())
        {
            String url = "";

            parseErrorResponse(body);
            response.body().close();
            String message;
            switch (response.code())
            {
                case HttpCodes.CODE_403:
                    message = getErrorMessage(apiErrorMessage, HkpConstants.UNAUTHORIZED_ACCESS);
                    throw new Exception(message);
                case HttpCodes.CODE_401:
                    message = getErrorMessage(apiErrorMessage, HkpConstants.ACCESS_DENIED);
                    throw new Exception(message);
                case HttpCodes.CODE_400:
                case HttpCodes.CODE_404:
                case HttpCodes.CODE_409:
                case HttpCodes.CODE_415:
                case HttpCodes.CODE_500:
                    message = getErrorMessage(apiErrorMessage, HkpConstants.MESSAGE_SERVER_ERROR);
                    throw new Exception(message);
                default:
                    message = getErrorMessage(apiErrorMessage, HkpConstants.COULDNT_REACH_OUR_SERVERS);
                    throw new Exception(message);
            }
        } else
        {
            return body;
        }
    }

    private static void parseErrorResponse(String body) throws JSONException
    {
        try
        {
            JSONObject response = new JSONObject(body);
            if (!response.isNull("error"))
            {
                JSONObject errorJson = response.getJSONObject("error");
                apiErrorMessage = parseString(errorJson);
            }
        } catch (JSONException ignored)
        {
            throw new JSONException(body);
        }
    }

    @Nullable
    private static String parseString(JSONObject jsonObject) throws JSONException
    {
        if (!jsonObject.isNull("user_message"))
        {
            return jsonObject.getString("user_message");
        } else
        {
            return null;
        }
    }

    private static String getErrorMessage(String apiErrorMessage, String defaultErrorMessage)
    {
        if (!(apiErrorMessage == null || apiErrorMessage.length() == 0))
        {
            return apiErrorMessage;
        } else
        {
            return defaultErrorMessage;
        }
    }
}
