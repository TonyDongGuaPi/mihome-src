package com.alipay.mobile.security.zim.api;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.alipay.mobile.security.bio.utils.BioLog;

final class a implements com.alipay.mobile.security.zim.b.a {
    public String a() {
        return "android";
    }

    a() {
    }

    public String a(Context context) {
        return ZIMFacade.b(context);
    }

    public String b() {
        return Build.MODEL;
    }

    public String b(Context context) {
        return context == null ? "" : context.getPackageName();
    }

    public String c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            BioLog.w((Throwable) e);
            return "";
        }
    }

    public String c() {
        return Build.VERSION.RELEASE;
    }
}
