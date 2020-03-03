package com.alipay.zoloz.android.phone.mrpc.core;

import android.content.Context;
import android.util.Log;

public final class MiscUtils {
    public static final String RC_PACKAGE_NAME = "com.eg.android.AlipayGphoneRC";

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f1190a;

    public static final boolean isDebugger(Context context) {
        if (f1190a != null) {
            return f1190a.booleanValue();
        }
        try {
            f1190a = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            return f1190a.booleanValue();
        } catch (Exception e) {
            Log.e("MiscUtils", "", e);
            return false;
        }
    }
}
