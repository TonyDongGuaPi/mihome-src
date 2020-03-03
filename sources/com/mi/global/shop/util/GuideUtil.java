package com.mi.global.shop.util;

import android.content.Context;
import com.mi.global.shop.util.Utils;

public class GuideUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7095a = 3;
    public static final String b = "pref_last_guide_version";

    public static int a(Context context) {
        return Utils.Preference.getIntPref(context, b, 0);
    }

    public static void b(Context context) {
        Utils.Preference.setIntPref(context, b, 3);
    }
}
