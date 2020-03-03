package com.xiaomi.smarthome.setting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingConst;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;

public class ServerRouteUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22092a = "api.io.mi.com";
    private static final String b = "api.io.mi.com";
    private static final String c = "home.mi.com";
    private static final String d = "";

    public static String a(Context context) {
        return a(context, "api.io.mi.com", "api.io.mi.com");
    }

    public static String b(Context context) {
        return a(context, "home.mi.com", "");
    }

    public static String a(Context context, @NonNull String str, @Nullable String str2) {
        ServerBean a2 = ServerCompact.a(context);
        String c2 = c(context);
        if (TextUtils.isEmpty(str2)) {
            c2 = "release";
            str2 = str;
        }
        if (a2 == null || ServerCompact.c(a2)) {
            if (TextUtils.isEmpty(c2) || c2.equalsIgnoreCase("release") || !c2.equalsIgnoreCase("preview")) {
                return "https://" + str;
            }
            return "https://pv." + str2;
        } else if (!TextUtils.isEmpty(c2) && !c2.equalsIgnoreCase("release") && c2.equalsIgnoreCase("preview")) {
            return "https://pv-" + a2.f1530a + "." + str2;
        } else if (!TextUtils.equals("st", a2.f1530a.toLowerCase()) || (!str.startsWith("home.mi.com") && !str.startsWith("mi.com"))) {
            return "https://" + a2.f1530a + "." + str;
        } else {
            return ConnectionHelper.HTTP_PREFIX + a2.f1530a + ".iot." + str;
        }
    }

    private static String c(Context context) {
        if (CoreApi.a().l()) {
            return CoreApi.a().H();
        }
        return context != null ? context.getSharedPreferences(GlobalDynamicSettingManager.f14576a, 0).getString(GlobalDynamicSettingConst.f, "release") : "release";
    }
}
