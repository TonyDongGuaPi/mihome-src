package com.mi.util;

import android.content.Context;
import com.mi.util.Utils;

public class HotfixUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7413a = "hotfix_url";

    public static String a(Context context) {
        return Utils.Preference.getStringPref(context, f7413a, "");
    }

    public static void a(Context context, String str) {
        Utils.Preference.setStringPref(context, f7413a, str);
    }
}
