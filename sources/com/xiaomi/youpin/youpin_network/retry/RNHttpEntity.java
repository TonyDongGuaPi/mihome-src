package com.xiaomi.youpin.youpin_network.retry;

import com.xiaomi.youpin.youpin_network.ISendRnRequest;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RNHttpEntity {

    /* renamed from: a  reason: collision with root package name */
    public OkHttpClient f23863a;
    public Request b;
    public Callback c;
    public ISendRnRequest d;

    public RNHttpEntity() {
    }

    public RNHttpEntity(OkHttpClient okHttpClient, Request request, Callback callback, ISendRnRequest iSendRnRequest) {
        this.f23863a = okHttpClient;
        this.b = request;
        this.c = callback;
        this.d = iSendRnRequest;
    }
}
