package com.ximalaya.ting.android.opensdk.model;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class SercretPubKey extends XimalayaResponse {
    @SerializedName("s_pub_key")

    /* renamed from: a  reason: collision with root package name */
    private String f2007a;
    @SerializedName("expired_time")
    private int b;
    @SerializedName("is_fallback")
    private boolean c;

    public String a() {
        return this.f2007a;
    }

    public void a(String str) {
        this.f2007a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public boolean c() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
