package com.ximalaya.ting.android.opensdk.auth.model;

import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONException;
import org.json.JSONObject;

public class XmlyAuth2AccessToken {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1834a = "uid";
    public static final String b = "access_token";
    public static final String c = "expires_in";
    public static final String d = "refresh_token";
    public static final String e = "scope";
    public static final String f = "device_id";
    private String g = "";
    private String h = "";
    private String i = "";
    private long j = 0;
    private String k = "";
    private String l = "";

    public XmlyAuth2AccessToken() {
    }

    public XmlyAuth2AccessToken(String str, String str2) {
        this.h = str;
        this.j = System.currentTimeMillis();
        if (str2 != null) {
            this.j += Long.parseLong(str2) * 1000;
        }
    }

    public static XmlyAuth2AccessToken a(String str) {
        if (TextUtils.isEmpty(str) || str.indexOf(Operators.BLOCK_START_STR) < 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken();
            xmlyAuth2AccessToken.b(String.valueOf(jSONObject.optInt("uid")));
            xmlyAuth2AccessToken.c(jSONObject.optString("access_token"));
            xmlyAuth2AccessToken.e(jSONObject.optString("expires_in"));
            xmlyAuth2AccessToken.d(jSONObject.optString("refresh_token"));
            xmlyAuth2AccessToken.f(jSONObject.optString("scope"));
            xmlyAuth2AccessToken.g(jSONObject.optString("device_id"));
            return xmlyAuth2AccessToken;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static XmlyAuth2AccessToken a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        XmlyAuth2AccessToken xmlyAuth2AccessToken = new XmlyAuth2AccessToken();
        xmlyAuth2AccessToken.b(a(bundle, "uid", ""));
        xmlyAuth2AccessToken.c(a(bundle, "access_token", ""));
        xmlyAuth2AccessToken.e(a(bundle, "expires_in", ""));
        xmlyAuth2AccessToken.d(a(bundle, "refresh_token", ""));
        xmlyAuth2AccessToken.f(a(bundle, "scope", ""));
        xmlyAuth2AccessToken.g(a(bundle, "device_id", ""));
        return xmlyAuth2AccessToken;
    }

    public boolean a() {
        return !TextUtils.isEmpty(this.h);
    }

    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putString("uid", this.g);
        bundle.putString("access_token", this.h);
        bundle.putString("refresh_token", this.i);
        bundle.putString("expires_in", Long.toString(this.j));
        bundle.putString("scope", this.k);
        bundle.putString("device_id", this.l);
        return bundle;
    }

    public String toString() {
        return "uid:" + this.g + ", access_token: " + this.h + ", refresh_token: " + this.i + ", scope: " + this.k + ", expires_in: " + Long.toString(this.j) + ", device_id: " + this.l;
    }

    public String c() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String d() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public String e() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public long f() {
        return this.j;
    }

    public void a(long j2) {
        this.j = j2;
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("0")) {
            a(System.currentTimeMillis() + (Long.parseLong(str) * 1000));
        }
    }

    public String g() {
        return this.k;
    }

    public void f(String str) {
        this.k = str;
    }

    public String h() {
        return this.l;
    }

    public void g(String str) {
        this.l = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r0.getString(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.os.Bundle r0, java.lang.String r1, java.lang.String r2) {
        /*
            if (r0 == 0) goto L_0x000a
            java.lang.String r0 = r0.getString(r1)
            if (r0 == 0) goto L_0x0009
            return r0
        L_0x0009:
            return r2
        L_0x000a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken.a(android.os.Bundle, java.lang.String, java.lang.String):java.lang.String");
    }
}
