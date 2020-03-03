package com.xiaomi.market.sdk;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppGlobal {

    /* renamed from: a  reason: collision with root package name */
    public static Context f11092a;

    public static Context a() {
        return f11092a;
    }

    public static void a(Context context) {
        f11092a = context.getApplicationContext();
    }

    public static PackageManager b() {
        return f11092a.getPackageManager();
    }
}
