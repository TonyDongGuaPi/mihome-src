package com.mibi.common.data;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class MistatisticUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7532a = "MistatisticUtils";

    public static void a(Activity activity, String str) {
    }

    public static void a(Context context, String str, String str2) {
    }

    public static void a(Fragment fragment, String str) {
        str + fragment.getClass().getSimpleName();
    }

    public static void b(Fragment fragment, String str) {
        str + fragment.getClass().getSimpleName();
    }

    public static void b(Activity activity, String str) {
        str + activity.getClass().getSimpleName();
    }

    public static void a(String str) {
        a(str, (Map<String, String>) new HashMap());
    }

    public static void a(String str, Map<String, String> map) {
        a(CommonConstants.bo, str, map);
    }

    public static void a(String str, String str2) {
        a(str, str2, (Map<String, String>) new HashMap());
    }

    public static void a(String str, String str2, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("miui_version", Client.n());
        map.put("app_version", Client.F().a());
    }
}
