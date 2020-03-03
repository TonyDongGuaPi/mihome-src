package com.ximalaya.ting.android.opensdk.auth.model;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyConstants;
import com.ximalaya.ting.android.opensdk.auth.utils.a;

public class XmlyAuthInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f1835a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public XmlyAuthInfo(Context context, String str, String str2, String str3, String str4) {
        this.f1835a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.f1835a = str;
        this.b = str3;
        this.c = TextUtils.isEmpty(str4) ? "" : str4;
        this.d = a.a(context);
        this.e = str2;
        this.g = "token";
        String a2 = a.a(context, context.getPackageName());
        this.h = TextUtils.isEmpty(a2) ? "" : a2;
    }

    public XmlyAuthInfo(Context context, String str, String str2, String str3) {
        this(context, str, context.getPackageName(), str2, str3);
    }

    private XmlyAuthInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.f1835a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.f1835a = str;
        this.b = str2;
        this.d = str3;
        this.e = str4;
        this.c = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
    }

    public String a() {
        return this.f1835a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.c;
    }

    public String f() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public Bundle i() {
        Bundle bundle = new Bundle();
        bundle.putString("app_key", this.f1835a);
        bundle.putString("redirect_uri", this.b);
        bundle.putString("state", this.c);
        bundle.putString("device_id", this.d);
        bundle.putString("pack_id", this.e);
        bundle.putString("client_os_type", this.f);
        bundle.putString("obtain_auth_type", this.g);
        bundle.putString(XmlyConstants.y, this.h);
        return bundle;
    }

    public static XmlyAuthInfo a(Context context, Bundle bundle) {
        return new XmlyAuthInfo(bundle.getString("app_key"), bundle.getString("redirect_uri"), a.a(context), bundle.getString("pack_id"), bundle.getString("state"), bundle.getString("client_os_type"), bundle.getString("obtain_auth_type"), bundle.getString(XmlyConstants.y));
    }
}
