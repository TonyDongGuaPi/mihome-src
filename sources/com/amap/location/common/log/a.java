package com.amap.location.common.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.amap.location.common.HeaderConfig;

class a {

    /* renamed from: a  reason: collision with root package name */
    private static int f4579a = 0;
    private static String b = "";
    private static String c = "";

    public static String a(Context context) {
        if (TextUtils.isEmpty(c)) {
            b(context);
            StringBuilder sb = new StringBuilder();
            if (f4579a != 0) {
                sb.append("versionCode:" + f4579a + "\n");
            }
            if (!TextUtils.isEmpty(b)) {
                sb.append("versionName:" + b + "\n");
            }
            sb.append("pid:" + Process.myPid() + "\n");
            sb.append("uid:" + Process.myUid() + "\n");
            sb.append("processName:" + HeaderConfig.e() + "\n");
            sb.append("packageName:" + context.getPackageName() + "\n");
            sb.append("-----------------------------\n");
            c = sb.toString();
        }
        return c;
    }

    public static void b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            b = packageInfo.versionName;
            f4579a = packageInfo.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
    }
}
