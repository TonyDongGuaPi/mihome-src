package com.xiaomi.market.sdk;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.List;

public class PkgUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11111a = "PkgUtils";

    public static String a(Intent intent) {
        for (ResolveInfo resolveInfo : a(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo.enabled && activityInfo.exported) {
                return activityInfo.packageName;
            }
        }
        return null;
    }

    public static List<ResolveInfo> a(Intent intent, int i) {
        List<ResolveInfo> list;
        try {
            list = AppGlobal.b().queryIntentActivities(intent, i);
        } catch (Exception e) {
            Log.b(f11111a, e.getMessage(), (Throwable) e);
            list = null;
        }
        return list != null ? list : new ArrayList();
    }
}
