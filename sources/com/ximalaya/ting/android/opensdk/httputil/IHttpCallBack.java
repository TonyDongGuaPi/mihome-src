package com.ximalaya.ting.android.opensdk.httputil;

import okhttp3.Response;

public interface IHttpCallBack {
    void a(int i, String str);

    void a(Response response);
}
