package com.ximalaya.ting.android.opensdk.auth.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyConstants;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1846a = 118;

    public static boolean a(Context context, Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return false;
        }
        try {
            return a(packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures, "22a001357629de32518a24508149689f");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean b(Context context, Intent intent) {
        String stringExtra = intent != null ? intent.getStringExtra(XmlyConstants.z) : null;
        return stringExtra != null && a(context, stringExtra);
    }

    public static boolean a(Context context, String str) {
        PackageManager packageManager;
        if (TextUtils.isEmpty(str) || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        try {
            return a(packageManager.getPackageInfo(str, 64).signatures, "22a001357629de32518a24508149689f");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null) {
            return false;
        }
        for (Signature byteArray : signatureArr) {
            if (b.a(byteArray.toByteArray()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(Context context, String str) {
        return b(context, str) >= 118;
    }

    public static int b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
