package com.mi.global.shop.db;

import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Utils;

public class Setting {

    /* renamed from: a  reason: collision with root package name */
    private static Boolean f6901a;

    public static boolean a() {
        if (f6901a == null) {
            f6901a = Boolean.valueOf(Utils.Preference.getBooleanPref(ShopApp.g(), "pref_data_saver_mode", true));
        }
        return f6901a.booleanValue();
    }

    public static void a(boolean z) {
        f6901a = Boolean.valueOf(z);
        Utils.Preference.setBooleanPref(ShopApp.g(), "pref_data_saver_mode", z);
    }

    public static void b() {
        Utils.Preference.a(ShopApp.g(), "pref_data_saver_mode", true);
    }
}
