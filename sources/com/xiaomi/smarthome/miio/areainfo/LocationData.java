package com.xiaomi.smarthome.miio.areainfo;

import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;

public class LocationData {

    /* renamed from: a  reason: collision with root package name */
    public String f11898a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;

    public LocationData() {
    }

    public LocationData(String str, String str2, String str3, String str4, String str5) {
        this.h = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = str5;
        if (!TextUtils.isEmpty(str5)) {
            this.f11898a = str5;
        } else if (!TextUtils.isEmpty(str4)) {
            this.f11898a = str4;
        } else if (!TextUtils.isEmpty(str3)) {
            this.f11898a = str3;
        }
        this.b = MainlandAreaInfo.a(SHApplication.getAppContext(), str2, str3, str4);
    }

    public String a() {
        return this.b;
    }
}
