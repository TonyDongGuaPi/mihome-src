package com.alipay.deviceid.module.x;

import android.content.Context;
import android.util.Log;

public final class bd {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f890a;

    public static final boolean a(Context context) {
        if (f890a != null) {
            return f890a.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f890a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception e) {
            Log.e("MiscUtils", "", e);
            return false;
        }
    }
}
