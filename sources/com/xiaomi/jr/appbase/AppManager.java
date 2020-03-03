package com.xiaomi.jr.appbase;

import com.xiaomi.jr.http.MifiHttpManager;

public class AppManager {

    /* renamed from: a  reason: collision with root package name */
    private static MifiApi f10305a;

    public static MifiApi a() {
        if (f10305a == null) {
            f10305a = (MifiApi) MifiHttpManager.a().a(MifiApi.class);
        }
        return f10305a;
    }
}
