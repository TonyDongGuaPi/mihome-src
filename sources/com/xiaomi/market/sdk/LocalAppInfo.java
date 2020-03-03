package com.xiaomi.market.sdk;

import android.text.TextUtils;
import java.io.File;

public class LocalAppInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f11109a = "";
    public String b = "";
    public int c = 0;
    public String d = "";
    public String e = "";
    public String f = "";
    public String g = "";
    public boolean h = false;

    private LocalAppInfo() {
    }

    public static LocalAppInfo a(String str) {
        LocalAppInfo localAppInfo = new LocalAppInfo();
        localAppInfo.f11109a = str;
        return localAppInfo;
    }

    public String a() {
        if (TextUtils.isEmpty(this.f)) {
            return null;
        }
        if (TextUtils.isEmpty(this.g)) {
            return Coder.a(new File(this.f));
        }
        return this.g;
    }
}
