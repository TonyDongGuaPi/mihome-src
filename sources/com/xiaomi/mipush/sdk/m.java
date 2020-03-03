package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ba;

public class m {

    /* renamed from: a  reason: collision with root package name */
    private static int f11559a = -1;

    public static an a(Context context) {
        try {
            return (context.getPackageManager().getServiceInfo(new ComponentName(HuaweiApiAvailability.SERVICES_PACKAGE, "com.huawei.hms.core.service.HMSCoreService"), 128) == null || !a()) ? an.OTHER : an.HUAWEI;
        } catch (Exception unused) {
            return an.OTHER;
        }
    }

    private static boolean a() {
        try {
            String str = (String) ba.a("android.os.SystemProperties", "get", "ro.build.hw_emui_api_level", "");
            return !TextUtils.isEmpty(str) && Integer.parseInt(str) >= 9;
        } catch (Exception e) {
            b.a((Throwable) e);
        }
    }

    public static boolean b(Context context) {
        Object a2 = ba.a(ba.a("com.google.android.gms.common.GoogleApiAvailability", "getInstance", new Object[0]), "isGooglePlayServicesAvailable", context);
        Object a3 = ba.a("com.google.android.gms.common.ConnectionResult", "SUCCESS");
        if (a3 == null || !(a3 instanceof Integer)) {
            b.c("google service is not avaliable");
            f11559a = 0;
            return false;
        }
        int intValue = Integer.class.cast(a3).intValue();
        if (a2 != null) {
            if (a2 instanceof Integer) {
                f11559a = Integer.class.cast(a2).intValue() == intValue ? 1 : 0;
            } else {
                f11559a = 0;
                b.c("google service is not avaliable");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("is google service can be used");
        sb.append(f11559a > 0);
        b.c(sb.toString());
        return f11559a > 0;
    }

    public static boolean c(Context context) {
        boolean z = false;
        Object a2 = ba.a("com.xiaomi.assemble.control.COSPushManager", "isSupportPush", context);
        if (a2 != null && (a2 instanceof Boolean)) {
            z = Boolean.class.cast(a2).booleanValue();
        }
        b.c("color os push  is avaliable ? :" + z);
        return z;
    }

    public static boolean d(Context context) {
        boolean z = false;
        Object a2 = ba.a("com.xiaomi.assemble.control.FTOSPushManager", "isSupportPush", context);
        if (a2 != null && (a2 instanceof Boolean)) {
            z = Boolean.class.cast(a2).booleanValue();
        }
        b.c("fun touch os push  is avaliable ? :" + z);
        return z;
    }
}
