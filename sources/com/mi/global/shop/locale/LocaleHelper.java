package com.mi.global.shop.locale;

import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.mi.global.bbs.manager.Region;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.SplashUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.stat.d;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LocaleHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6931a = "applocale";
    public static String b = null;
    public static String c = null;
    public static String d = "none";
    public static String e = "in";
    public static String f = "tw";
    public static String g = "hk";
    public static String h = "id";
    public static String i = "es";
    public static String j = "fr";
    public static String k = "it";
    public static String l = "ru";
    public static String m = "uk";
    public static String n = "gb";
    public static final String[][] o = {new String[]{e, "en", "India"}, new String[]{f, "zh", "Taiwan 繁體中文"}, new String[]{g, "zh", "Hong Kong 繁體中文"}, new String[]{i, d.u, "España"}, new String[]{j, "fr", "France"}, new String[]{h, "id", "Indonesia"}, new String[]{l, Region.RU, "Россия"}, new String[]{k, "it", "Italy"}, new String[]{m, "en", "United Kingdom"}};
    public static final String[][] p = {new String[]{e, "i18n_in_mo_pro"}, new String[]{f, "mi_mo_overseatw"}, new String[]{g, "mi_mo_overseahk"}, new String[]{i, "mi_mo_overseaes"}, new String[]{j, "mi_mo_overseafr"}, new String[]{h, "mi_mo_overseaid_new"}, new String[]{l, "i18n_ru_mo_pro"}, new String[]{k, "mi_mo_overseait"}, new String[]{m, "mi_mo_overseauk"}};
    public static final String q = "/register?callback=http%3A%2F%2Fm.buy.mi.com%2Fin%2Flogin%2Fcallback%3Ffollowup%3Dhttp%253A%252F%252Fm.buy.mi.com%252Fin%252Fuser%252F%26sign%3DODhhODI4YjA0ZmYxMDM5MTgwOGQ4YjBjODE1MjU4MzhlMTNkNTQ0Yg%2C%2C&sid=mi_mo_overseain&_locale=en_IN&";
    public static final String r = "/register?callback=http%3A%2F%2Fm.buy.mi.com%2Ftw%2Flogin%2Fcallback%3Ffollowup%3Dhttp%253A%252F%252Fm.buy.mi.com%252Ftw%252Fuser%252F%26sign%3DNTMyZDQ2MGNmM2RiMjg5MTU3MDAwOTA2MjFlNmYyZmYxZTlmOTE2NA%2C%2C&sid=mi_mo_overseatw&_locale=zh_TW&";
    public static final String s = "/register?callback=http%3A%2F%2Fm.buy.mi.com%2Fid%2Flogin%2Fcallback%3Ffollowup%3Dhttp%253A%252F%252Fm.buy.mi.com%252Fid%252Fuser%252F%26sign%3DODhlODBhMWExZWRjNjI1ODMzMzc0ODk1MzdjYTdmZDlmMmIwOGIxMw%2C%2C&sid=mi_mo_overseaid&_locale=in_ID&";

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i2 = 0;
            while (true) {
                if (i2 >= o.length) {
                    break;
                } else if (o[i2][0].equals(str)) {
                    b = o[i2][0];
                    c = o[i2][1];
                    Utils.Preference.setStringPref(ShopApp.g(), "pref_locale", b);
                    Utils.Preference.setStringPref(ShopApp.g(), "pref_lang", c);
                    Utils.Preference.removePref(ShopApp.g(), "pref_key_user_center_list");
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (b == null || c == null) {
            b = Utils.Preference.getStringPref(ShopApp.g(), "pref_locale", (String) null);
            c = Utils.Preference.getStringPref(ShopApp.g(), "pref_lang", (String) null);
        }
        if (b == null || c == null) {
            r();
            if (b == null || c == null) {
                b = e;
                c = "en";
            }
            Utils.Preference.setStringPref(ShopApp.g(), "pref_locale", b);
            Utils.Preference.setStringPref(ShopApp.g(), "pref_lang", c);
        }
        LogUtil.b("applocale", " locale is :" + b);
        LogUtil.b("applocale", " lang is :" + c);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        for (int i2 = 0; i2 < p.length; i2++) {
            if (p[i2][0].equals(str)) {
                return p[i2][1];
            }
        }
        return "";
    }

    private static void r() {
        TelephonyManager telephonyManager;
        String str = null;
        try {
            str = (String) Class.forName("miui.os.Build").getMethod("getRegion", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (str == null) {
            str = ShopApp.g().getResources().getConfiguration().locale.getCountry();
        }
        LogUtil.b("applocale", " current country is :" + str);
        for (String[] strArr : o) {
            if (strArr[0].equalsIgnoreCase(str) || (strArr[0].equalsIgnoreCase(m) && n.equalsIgnoreCase(str))) {
                b = strArr[0];
                c = strArr[1];
            }
        }
        if ((b == null || c == null) && (telephonyManager = (TelephonyManager) ShopApp.g().getSystemService("phone")) != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            for (String[] strArr2 : o) {
                if (strArr2[0].equalsIgnoreCase(simCountryIso)) {
                    b = strArr2[0];
                    c = strArr2[1];
                }
            }
        }
    }

    public static void a() {
        Locale locale;
        try {
            if (b.equalsIgnoreCase(m)) {
                locale = new Locale(c, n);
            } else {
                locale = new Locale(c, b);
            }
            Configuration configuration = ShopApp.g().getBaseContext().getResources().getConfiguration();
            Locale.setDefault(locale);
            configuration.locale = locale;
            LogUtil.b("applocale", " set locale to :" + locale.toString());
            ShopApp.g().getBaseContext().getResources().updateConfiguration(configuration, ShopApp.g().getBaseContext().getResources().getDisplayMetrics());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a(int i2) {
        if (i2 < 0 || i2 >= o.length) {
            return false;
        }
        if (o[i2][0].equals(b) && o[i2][1].equals(c)) {
            return false;
        }
        LoginManager.u().logout();
        CookieManager.getInstance().removeAllCookie();
        SplashUtil.a();
        b = o[i2][0];
        c = o[i2][1];
        Utils.Preference.setStringPref(ShopApp.g(), "pref_locale", b);
        Utils.Preference.setStringPref(ShopApp.g(), "pref_lang", c);
        Utils.Preference.removePref(ShopApp.g(), "pref_key_user_center_list");
        return true;
    }

    public static TimeZone b() {
        return TimeZone.getDefault();
    }

    public static String c() {
        if (j()) {
            return "in_ID";
        }
        return c + JSMethod.NOT_SET + b.toUpperCase();
    }

    public static String d() {
        if (b.equalsIgnoreCase(e)) {
            return q;
        }
        if (b.equalsIgnoreCase(f)) {
            return r;
        }
        return b.equalsIgnoreCase(h) ? s : q;
    }

    public static String e() {
        if (b.equalsIgnoreCase(e)) {
            return "₹";
        }
        if (b.equalsIgnoreCase(f)) {
            return "NT$ ";
        }
        if (b.equalsIgnoreCase(h)) {
            return "Rp ";
        }
        if (b.equalsIgnoreCase(g)) {
            return "HK$ ";
        }
        if (b.equalsIgnoreCase(i) || b.equalsIgnoreCase(j)) {
            return "€ ";
        }
        if (b.equalsIgnoreCase(l)) {
            return "₽ ";
        }
        if (b.equalsIgnoreCase(k)) {
            return "€ ";
        }
        return b.equalsIgnoreCase(m) ? "£ " : "";
    }

    public static String c(String str) {
        String str2 = "#,###.00";
        if (b.equalsIgnoreCase(e)) {
            str2 = "#,###";
        } else if (b.equalsIgnoreCase(f)) {
            str2 = "#,### ";
        } else if (b.equalsIgnoreCase(g)) {
            str2 = "#,### ";
        } else if (b.equalsIgnoreCase(i)) {
            str2 = "#,### ";
        } else if (b.equalsIgnoreCase(j)) {
            str2 = "#,### ";
        }
        return new DecimalFormat(str2).format(Double.parseDouble(str));
    }

    public static String f() {
        return g() ? "dd MMM, KK:mm a" : "yyyy-MM-dd HH:mm";
    }

    public static String a(Long l2) {
        String format = new SimpleDateFormat(f()).format(new Date(l2.longValue()));
        return g() ? format.replace("pm", "PM").replace("am", "AM") : format;
    }

    public static boolean g() {
        return e.equalsIgnoreCase(b);
    }

    public static boolean h() {
        return f.equalsIgnoreCase(b);
    }

    public static boolean i() {
        return g.equalsIgnoreCase(b);
    }

    public static boolean j() {
        return h.equalsIgnoreCase(b);
    }

    public static boolean k() {
        return i.equalsIgnoreCase(b);
    }

    public static boolean l() {
        return j.equalsIgnoreCase(b);
    }

    public static boolean m() {
        return k.equalsIgnoreCase(b);
    }

    public static boolean n() {
        return l.equalsIgnoreCase(b);
    }

    public static boolean o() {
        return m.equalsIgnoreCase(b);
    }

    public static boolean p() {
        return !j();
    }

    public static boolean q() {
        return k() || l() || m() || o();
    }
}
