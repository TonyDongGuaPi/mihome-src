package com.unionpay.mobile.android.net;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.unionpay.mobile.android.utils.c;
import java.net.URL;
import java.util.HashMap;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    private int f9581a;
    private String b;
    private HashMap<String, String> c;
    private byte[] d;
    private String e;
    private String f;

    public d(int i, String str, byte[] bArr) {
        this.f9581a = i;
        this.b = str;
        this.c = null;
        this.d = bArr;
    }

    public d(String str) {
        this.f9581a = 1;
        this.b = str;
        this.c = null;
        this.d = null;
    }

    public final URL a() {
        String str;
        try {
            if (!TextUtils.isEmpty(this.f)) {
                str = this.b + this.f;
            } else {
                str = this.b;
            }
            return new URL(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public final void a(Context context, String str, String str2) {
        this.f = "?" + c.d(str) + "&0," + c.b(context) + a.b + c.e(str2);
    }

    public final void a(String str) {
        if (str != null) {
            this.e = str;
            this.d = str.getBytes();
        }
    }

    public final void a(HashMap<String, String> hashMap) {
        this.c = hashMap;
    }

    public final String b() {
        return this.f9581a == 1 ? "POST" : "GET";
    }

    public final String c() {
        return this.e;
    }

    public final HashMap<String, String> d() {
        return this.c;
    }
}
