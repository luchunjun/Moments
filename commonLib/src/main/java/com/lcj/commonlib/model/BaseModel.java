package com.lcj.commonlib.model;

import com.lcj.commonlib.utils.http.HttpUtil;


public class BaseModel {
    private final String url;

    protected BaseModel(String url) {
        this.url = url;
    }

    public void sendGetRequest(HttpUtil.HttpResponseCallBack httpResponseCallBack) {
        HttpUtil.getRequest(url, httpResponseCallBack);
    }
}
