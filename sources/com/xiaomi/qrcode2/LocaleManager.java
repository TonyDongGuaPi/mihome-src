package com.xiaomi.qrcode2;

import android.content.Context;
import android.preference.PreferenceManager;
import com.mi.global.bbs.manager.Region;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.stat.a.l;
import com.xiaomi.stat.d;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class LocaleManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13016a = "com";
    private static final String b = "US";
    private static final String c = "en";
    private static final Map<String, String> d = new HashMap();
    private static final Map<String, String> e = new HashMap();
    private static final Map<String, String> f = d;
    private static final Collection<String> g = Arrays.asList(new String[]{"de", "en", d.u, "fr", "it", "ja", "ko", "nl", "pt", Region.RU, "uk", "zh-rCN", "zh-rTW", "zh-rHK"});

    static {
        d.put("AR", "com.ar");
        d.put("AU", "com.au");
        d.put("BR", "com.br");
        d.put("BG", "bg");
        d.put(Locale.CANADA.getCountry(), l.a.x);
        d.put(Locale.CHINA.getCountry(), "cn");
        d.put("CZ", "cz");
        d.put("DK", "dk");
        d.put("FI", "fi");
        d.put(Locale.FRANCE.getCountry(), "fr");
        d.put(Locale.GERMANY.getCountry(), "de");
        d.put("GR", "gr");
        d.put("HU", "hu");
        d.put("ID", "co.id");
        d.put("IL", "co.il");
        d.put(Locale.ITALY.getCountry(), "it");
        d.put(Locale.JAPAN.getCountry(), "co.jp");
        d.put(Locale.KOREA.getCountry(), "co.kr");
        d.put("NL", "nl");
        d.put("PL", d.U);
        d.put("PT", "pt");
        d.put("RO", "ro");
        d.put(ServerCompact.d, Region.RU);
        d.put("SK", d.af);
        d.put("SI", "si");
        d.put(ServerCompact.f, d.u);
        d.put("SE", "se");
        d.put("CH", "ch");
        d.put(Locale.TAIWAN.getCountry(), "tw");
        d.put("TR", "com.tr");
        d.put("UA", "com.ua");
        d.put(Locale.UK.getCountry(), "co.uk");
        d.put(Locale.US.getCountry(), f13016a);
        e.put("AU", "com.au");
        e.put(Locale.FRANCE.getCountry(), "fr");
        e.put(Locale.GERMANY.getCountry(), "de");
        e.put(Locale.ITALY.getCountry(), "it");
        e.put(Locale.JAPAN.getCountry(), "co.jp");
        e.put("NL", "nl");
        e.put(ServerCompact.f, d.u);
        e.put("CH", "ch");
        e.put(Locale.UK.getCountry(), "co.uk");
        e.put(Locale.US.getCountry(), f13016a);
    }

    private LocaleManager() {
    }

    public static String a(Context context) {
        return a(d, context);
    }

    public static String b(Context context) {
        return a(e, context);
    }

    public static String c(Context context) {
        return a(f, context);
    }

    public static boolean a(String str) {
        return str.startsWith("http://google.com/books") || str.startsWith("http://books.google.");
    }

    private static String b() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return b;
        }
        return locale.getCountry();
    }

    private static String c() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return "en";
        }
        String language = locale.getLanguage();
        if (!Locale.SIMPLIFIED_CHINESE.getLanguage().equals(language)) {
            return language;
        }
        return language + "-r" + b();
    }

    public static String a() {
        String c2 = c();
        return g.contains(c2) ? c2 : "en";
    }

    private static String a(Map<String, String> map, Context context) {
        String str = map.get(d(context));
        return str == null ? f13016a : str;
    }

    public static String d(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context);
        if ("-".isEmpty() || "-".equals("-")) {
            return b();
        }
        return "-";
    }
}
