package com.lcj.commonlib.utils.http;


import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    private final static int TIMEOUT_BY_SECONDS = 20;
    private static final OkHttpClient globalOkHttpClient = new OkHttpClient.Builder()
            .readTimeout(TIMEOUT_BY_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_BY_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_BY_SECONDS, TimeUnit.SECONDS)
            .build();

    public interface HttpResponseCallBack {
        void onSuccess(String result);
        void onFail();
    }


    public static void getRequest(final String url,
                                  final HttpResponseCallBack httpResponseCallback) {
        Logger.d("getRequest====>url: " +url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response;
                try {
                    response = globalOkHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Logger.d("getRequest====>response.body(): " +response.body());
                            //string() method can be called only once.
                            httpResponseCallback.onSuccess(response.body().string());
                        }
                    } else {
                        httpResponseCallback.onFail();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
