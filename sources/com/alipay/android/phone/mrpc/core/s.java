package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class s {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f842a;

    public static final boolean a(Context context) {
        if (f842a != null) {
            return f842a.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f842a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
