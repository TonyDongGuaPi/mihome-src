package com.xiaomi.smarthome.miio;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

public class LauncherUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11571a = "source";
    public static final String b = "smarthome_launch";

    public static boolean a(Context context, String str, String str2, String str3) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            Toast.makeText(context, str3, 0).show();
        }
        return false;
    }

    public static void a(Intent intent) {
        intent.putExtra("source", b);
    }
}
