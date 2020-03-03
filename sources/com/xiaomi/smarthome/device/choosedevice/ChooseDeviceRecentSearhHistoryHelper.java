package com.xiaomi.smarthome.device.choosedevice;

import android.content.Context;
import android.util.Log;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.MD5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChooseDeviceRecentSearhHistoryHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15333a = "ChooseDeviceRecentSearh";
    private static final String b = "spfs_key_choose_device_search_history";

    public static List<String> a(Context context) {
        try {
            return new ArrayList(context.getSharedPreferences(b(context), 0).getAll().keySet());
        } catch (Exception e) {
            Log.w(f15333a, "getRecentHistory: " + Log.getStackTraceString(e));
            return Collections.emptyList();
        }
    }

    public static void a(Context context, String str) {
        context.getSharedPreferences(b(context), 0).edit().putString(str, "").apply();
    }

    private static String b(Context context) {
        Locale c = ServerCompact.c(context);
        ServerBean a2 = ServerCompact.a(context);
        String s = CoreApi.a().s();
        String d = MD5.d(LocaleUtil.b(c) + a2 + s);
        return "spfs_key_choose_device_search_history_" + d;
    }
}
