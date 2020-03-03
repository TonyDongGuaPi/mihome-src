package com.sina.weibo.sdk.utils;

import android.content.Context;
import com.alipay.android.phone.a.a.a;
import com.mi.util.permission.Permission;
import com.taobao.weex.annotation.JSMethod;

public class NetworkHelper {
    public static boolean a(Context context) {
        return context == null || context.checkCallingOrSelfPermission(Permission.y) == 0;
    }

    public static String b(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.f813a);
        sb.append("__");
        sb.append("weibo");
        sb.append("__");
        sb.append("sdk");
        sb.append("__");
        try {
            sb.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName.replaceAll("\\s+", JSMethod.NOT_SET));
        } catch (Exception unused) {
            sb.append("unknown");
        }
        return sb.toString();
    }
}
