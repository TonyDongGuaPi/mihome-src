package com.xiaomi.smarthome.international;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.stat.d;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class SelectServerUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, ServerBean> f18409a = new ArrayMap();
    private static final Map<String, ServerBean> b = new ArrayMap();
    private static final Map<String, ServerBean> c = new ArrayMap();
    private static final Map<String, ServerBean> d = new ArrayMap();
    private static final Map<String, ServerBean> e = new ArrayMap();
    private static final Map<String, ServerBean> f = new ArrayMap();
    private static final Map<String, ServerBean> g = new ArrayMap();
    private static final Map<String, ServerBean> h = new ArrayMap();
    private static final Map<String, ServerBean> i = new ArrayMap();

    public static void a() {
        if (f.isEmpty()) {
            e.put("Asia/Taipei".toLowerCase(), ServerCompact.c());
            b.put("Asia/Hong_Kong".toLowerCase(), ServerCompact.d());
            c.put("Asia/Seoul".toLowerCase(), ServerCompact.i());
            ServerBean f2 = ServerCompact.f();
            g.put("Asia/Kathmandu".toLowerCase(), f2);
            g.put("Asia/Kolkata".toLowerCase(), f2);
            g.put("ASIA/CALCUTTA".toLowerCase(), f2);
            ServerBean b2 = ServerCompact.b();
            f18409a.put("Asia/Shanghai".toLowerCase(), b2);
            f18409a.put("Asia/Chungking".toLowerCase(), b2);
            f18409a.put("Asia/Chongqing".toLowerCase(), b2);
            f18409a.put("Asia/Urumqi".toLowerCase(), b2);
            f18409a.put("Asia/Kashgar".toLowerCase(), b2);
            f18409a.put("Asia/Harbin".toLowerCase(), b2);
            ServerBean g2 = ServerCompact.g();
            h.put("America/Adak".toLowerCase(), g2);
            h.put("America/Anchorage".toLowerCase(), g2);
            h.put("America/Los_Angeles".toLowerCase(), g2);
            h.put("America/Denver".toLowerCase(), g2);
            h.put("America/Chicago".toLowerCase(), g2);
            h.put("America/New_York".toLowerCase(), g2);
            h.put("America/Yakutat".toLowerCase(), g2);
            h.put("America/Sitka".toLowerCase(), g2);
            h.put("America/Nome".toLowerCase(), g2);
            h.put("America/North_Dakota/New_Salem".toLowerCase(), g2);
            h.put("America/Menominee".toLowerCase(), g2);
            h.put("America/Metlakatla".toLowerCase(), g2);
            h.put("America/Indiana/Vevay".toLowerCase(), g2);
            h.put("America/Indiana/Winamac".toLowerCase(), g2);
            h.put("America/Indiana/Vincennes".toLowerCase(), g2);
            h.put("America/Anchorage".toLowerCase(), g2);
            h.put("America/Juneau".toLowerCase(), g2);
            h.put("America/Detroit".toLowerCase(), g2);
            h.put("America/North_Dakota/Center".toLowerCase(), g2);
            h.put("America/Indiana/Indianapolis".toLowerCase(), g2);
            h.put("America/Phoenix".toLowerCase(), g2);
            h.put("America/Kentucky/Monticello".toLowerCase(), g2);
            h.put("America/Kentucky/Louisville".toLowerCase(), g2);
            h.put("America/Indiana/Marengo".toLowerCase(), g2);
            h.put("America/Honolulu".toLowerCase(), g2);
            h.put("America/Indiana/Petersburg".toLowerCase(), g2);
            h.put("America/Indiana/Tell_City".toLowerCase(), g2);
            h.put("America/Indiana/Knox".toLowerCase(), g2);
            h.put("America/North_Dakota/Beulah".toLowerCase(), g2);
            h.put("America/Boise".toLowerCase(), g2);
            ServerBean j = ServerCompact.j();
            d.put("Europe/Volgograd".toLowerCase(), j);
            d.put("Asia/Vladivostok".toLowerCase(), j);
            d.put("Asia/Ust-Nera".toLowerCase(), j);
            d.put("Europe/Simferopol".toLowerCase(), j);
            d.put("Asia/Yekaterinburg".toLowerCase(), j);
            d.put("Asia/Omsk".toLowerCase(), j);
            d.put("Europe/Moscow".toLowerCase(), j);
            d.put("Asia/Irkutsk".toLowerCase(), j);
            d.put("Asia/Krasnoyarsk".toLowerCase(), j);
            d.put("Asia/Magadan".toLowerCase(), j);
            d.put("Asia/Novosibirsk".toLowerCase(), j);
            d.put("Europe/Kaliningrad".toLowerCase(), j);
            d.put("Europe/Samara".toLowerCase(), j);
            d.put("Asia/Sakhalin".toLowerCase(), j);
            d.put("Asia/Khandyga".toLowerCase(), j);
            d.put("Asia/Novokuznetsk".toLowerCase(), j);
            d.put("Asia/Yakutsk".toLowerCase(), j);
            d.put("Asia/Anadyr".toLowerCase(), j);
            d.put("Asia/Kamchatka".toLowerCase(), j);
            ServerBean h2 = ServerCompact.h();
            f.put("Europe/Vienna".toLowerCase(), h2);
            f.put("Europe/Brussels".toLowerCase(), h2);
            f.put("Europe/Sofia".toLowerCase(), h2);
            f.put("Asia/Nicosia".toLowerCase(), h2);
            f.put("Europe/Zagreb".toLowerCase(), h2);
            f.put("Europe/Prague".toLowerCase(), h2);
            f.put("Europe/Copenhagen".toLowerCase(), h2);
            f.put("Europe/Tallinn".toLowerCase(), h2);
            f.put("Europe/Helsinki".toLowerCase(), h2);
            f.put("Europe/Paris".toLowerCase(), h2);
            f.put("Europe/Busingen".toLowerCase(), h2);
            f.put("Europe/Berlin".toLowerCase(), h2);
            f.put("Europe/Athens".toLowerCase(), h2);
            f.put("Europe/Budapest".toLowerCase(), h2);
            f.put("Europe/Dublin".toLowerCase(), h2);
            f.put("Europe/Rome".toLowerCase(), h2);
            f.put("Europe/Riga".toLowerCase(), h2);
            f.put("Europe/Vilnius".toLowerCase(), h2);
            f.put("Europe/Luxembourg".toLowerCase(), h2);
            f.put("Europe/Malta".toLowerCase(), h2);
            f.put("Europe/Amsterdam".toLowerCase(), h2);
            f.put("Europe/Warsaw".toLowerCase(), h2);
            f.put("Atlantic/Madeira".toLowerCase(), h2);
            f.put("Europe/Lisbon".toLowerCase(), h2);
            f.put("Atlantic/Azores".toLowerCase(), h2);
            f.put("Europe/Bucharest".toLowerCase(), h2);
            f.put("Europe/Bratislava".toLowerCase(), h2);
            f.put("Europe/Ljubljana".toLowerCase(), h2);
            f.put("Europe/Madrid".toLowerCase(), h2);
            f.put("Atlantic/Canary".toLowerCase(), h2);
            f.put("Africa/Ceuta".toLowerCase(), h2);
            f.put("Europe/Stockholm".toLowerCase(), h2);
            f.put("Europe/London".toLowerCase(), h2);
        }
    }

    @Nullable
    public static ServerBean b() {
        if (SystemApi.c() && CommonUtils.o()) {
            String q = CommonUtils.q();
            if (!"CN".equalsIgnoreCase(q)) {
                return a(q);
            }
        }
        if (!f()) {
            return g();
        }
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (I == null) {
            return null;
        }
        String country = I.getCountry();
        try {
            for (ServerBean next : ServerCompact.a(ServerCompact.c(SHApplication.getAppContext()), SHApplication.getAppContext())) {
                if (next.b.equalsIgnoreCase(country)) {
                    return (ServerBean) next.clone();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static boolean c() {
        Locale locale;
        if ((SystemApi.c() && CommonUtils.o() && !"CN".equalsIgnoreCase(CommonUtils.q())) || !f()) {
            return false;
        }
        if (CoreApi.a().l()) {
            locale = CoreApi.a().I();
        } else {
            locale = ServerCompact.c(SHApplication.getAppContext());
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (LanguageUtil.a(locale, Locale.CHINA) || LanguageUtil.a(locale, Locale.CHINESE)) {
            return true;
        }
        return false;
    }

    private static boolean f() {
        return ServerCompact.c(g());
    }

    private static ServerBean g() {
        a();
        String id = TimeZone.getDefault().getID();
        LogUtil.a("hzd1", "id=" + id);
        if (TextUtils.isEmpty(id)) {
            return ServerCompact.b();
        }
        String lowerCase = id.toLowerCase();
        if (f18409a.containsKey(lowerCase)) {
            return ServerCompact.b();
        }
        if (f.containsKey(lowerCase)) {
            return ServerCompact.h();
        }
        if (b.containsKey(lowerCase)) {
            return ServerCompact.d();
        }
        if (g.containsKey(lowerCase)) {
            return ServerCompact.f();
        }
        if (c.containsKey(lowerCase)) {
            return ServerCompact.i();
        }
        if (d.containsKey(lowerCase)) {
            return ServerCompact.j();
        }
        if (e.containsKey(lowerCase)) {
            return ServerCompact.c();
        }
        if (h.containsKey(lowerCase)) {
            return ServerCompact.g();
        }
        return ServerCompact.e();
    }

    private static ServerBean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (ServerBean next : ServerCompact.a(ServerCompact.c(SHApplication.getAppContext()), SHApplication.getAppContext())) {
            if (next.b.equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    public static String d() {
        ServerBean F = CoreApi.a().F();
        if (F == null) {
            return ServerCompact.b().b;
        }
        return F.b;
    }

    public static String e() {
        ServerBean F = CoreApi.a().F();
        if (F == null || ServerCompact.c(F)) {
            return CoreApi.a().E() ? d.c : "";
        }
        return F.f1530a;
    }
}
