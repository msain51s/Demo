package com.goldshop.service;

/**
 * Created by Administrator on 1/28/2016.
 */
public interface ResponseListener {
    public static final int REQUEST_TEXTPAD = 111;
    public static final int REQUEST_LOGIN=1;
    public static final int REQUEST_FORGET_PASSWORD=2;
    public static final int REQUEST_SIGN_UP=3;
    public static final int REQUEST_ALL_CATEGORY=4;
    public static final int REQUEST_CATEGORY_INFO=5;

    public void onResponse(Response response, int rid);
}
