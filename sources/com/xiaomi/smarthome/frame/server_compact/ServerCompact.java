package com.xiaomi.smarthome.frame.server_compact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Pair;
import com.mi.global.bbs.manager.Region;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingConst;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.globalsetting.R;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

public class ServerCompact {
    private static final String A = "us_true";
    private static final String B = "US";
    private static final String C = "us";
    private static final String D = "us";
    private static final String E = "US_SG";
    private static final String F = "sg";
    private static final String G = "de";
    private static final String H = "DE";
    private static final String I = "de";
    private static final String J = "kr";
    private static final String K = "KR";
    private static final String L = "sg";
    private static final String M = "ru";
    private static final String N = "ru";
    private static final String O = "tr";
    private static final String P = "TK";
    private static final String Q = "sg";
    private static final String R = "st";
    private static final String S = "st";
    private static final String T = "st";
    private static final ServerBean U = new ServerBean("cn", m, "");
    private static final ServerBean V = new ServerBean("sg", f1531a, "");
    private static final ServerBean W = new ServerBean("sg", b, "");
    private static final ServerBean X = new ServerBean("sg", t, "");
    private static final ServerBean Y = new ServerBean(d.F, "IN", "");
    private static final ServerBean Z = new ServerBean("us", B, "");

    /* renamed from: a  reason: collision with root package name */
    public static final String f1531a = "TW";
    private static final ServerBean aa = new ServerBean("de", H, "");
    private static final ServerBean ab = new ServerBean("sg", K, "");
    private static final ServerBean ac = new ServerBean(Region.RU, d, "");
    private static final ServerBean ad = new ServerBean("st", "st", "");
    private static final List<ServerBean> ae = Arrays.asList(new ServerBean[]{U, V, W, X, Y, Z, aa, ab, ac, ad});
    private static final Map<String, Pair<String, String>> af = new ArrayMap();
    private static final Map<String, String> ag = new ArrayMap();
    private static final Map<String, ServerBean> ah = new ArrayMap();
    private static AtomicReference<ServerBean> ai = new AtomicReference<>();
    public static final String b = "HK";
    public static final String c = "IN";
    public static final String d = "RU";
    public static final String e = "FR";
    public static final String f = "ES";
    public static final String g = "ID";
    public static final String h = "IT";
    public static final String i = "GB";
    static volatile Boolean j = null;
    private static final String k = "ServerCompact";
    private static final String l = "cn";
    private static final String m = "CN";
    private static final String n = "cn";
    private static final String o = "tw";
    private static final String p = "sg";
    private static final String q = "hk";
    private static final String r = "sg";
    private static final String s = "sg";
    private static final String t = "SG";
    private static final String u = "sg";
    private static final String v = "in";
    private static final String w = "IN";
    private static final String x = "sg";
    private static final String y = "i2";
    private static final String z = "i2";

    static {
        af.put("in", Pair.create(t, "sg"));
        af.put("us", Pair.create(t, "sg"));
        af.put("de", Pair.create(H, "de"));
        af.put("tr", Pair.create(t, "sg"));
        af.put("cn", Pair.create(m, "cn"));
        af.put(o, Pair.create(f1531a, "sg"));
        af.put(q, Pair.create(b, "sg"));
        af.put("sg", Pair.create(t, "sg"));
        af.put(d.F, Pair.create("IN", d.F));
        af.put(A, Pair.create(B, "us"));
        af.put(J, Pair.create(K, "sg"));
        af.put(Region.RU, Pair.create(d, Region.RU));
        af.put("st", Pair.create("st", "st"));
        ag.put(m, "cn");
        ag.put(f1531a, o);
        ag.put(b, q);
        ag.put(t, "sg");
        ag.put("IN", d.F);
        ag.put(B, A);
        ag.put(H, "de");
        ag.put(K, J);
        ag.put(d, Region.RU);
        ag.put("st", "st");
        ah.put("IR", X);
        ah.put("CU", Z);
        ah.put("KP", X);
        ah.put("SY", X);
        ah.put("SD", X);
        ah.put("VE", Z);
    }

    public static String a(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (TextUtils.isEmpty(str)) {
            return "";
        } else {
            if (str.equals(ad.b)) {
                return context.getString(R.string.inter_sub_domain_st);
            }
            try {
                for (ServerBean next : a(c(context), context)) {
                    if (TextUtils.equals(next.b, str)) {
                        return next.c;
                    }
                }
            } catch (Exception unused) {
            }
            return "";
        }
    }

    @WorkerThread
    public static List<ServerBean> a(Locale locale, Context context) {
        ArrayList arrayList = new ArrayList();
        if (locale == null) {
            return arrayList;
        }
        String a2 = ServersConfig.a(context, locale);
        if (TextUtils.isEmpty(a2)) {
            return arrayList;
        }
        try {
            return ServerBean.a(new JSONObject(a2));
        } catch (Exception unused) {
            return Collections.emptyList();
        }
    }

    @Deprecated
    public static List<ServerBean> a() {
        ArrayList arrayList = new ArrayList();
        for (ServerBean clone : ae) {
            arrayList.add((ServerBean) clone.clone());
        }
        return arrayList;
    }

    @SuppressLint({"ApplySharedPref"})
    @Deprecated
    public static void a(Context context, Locale locale, String str) {
        if (locale != null && context != null && !TextUtils.isEmpty(str)) {
            ServersConfig.a(context, LocaleUtil.b(locale), str);
        }
    }

    public static String a(ServerBean serverBean) {
        return serverBean == null ? "" : ag.get(serverBean.b);
    }

    @Nullable
    public static ServerBean a(Context context) {
        if (p(context)) {
            ServerBean serverBean = ai.get();
            if (serverBean != null) {
                LogUtil.a(k, "get server from cache: " + serverBean.b());
                return serverBean;
            }
            ServerBean b2 = b(context);
            if (b2 != null) {
                do {
                } while (!ai.compareAndSet((Object) null, b2));
            }
            return b2;
        } else if (CoreApi.a().l()) {
            return CoreApi.a().F();
        } else {
            return b(context);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0 = af.get(r1);
        r0 = com.xiaomi.smarthome.frame.server_compact.ServerBean.a((java.lang.String) r0.first, (java.lang.String) r0.second);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007d, code lost:
        if (p(r6) != false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007f, code lost:
        a(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0082, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0083, code lost:
        com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(k, "parseCurrentServerFromSpfs: null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008a, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0065 */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.frame.server_compact.ServerBean b(android.content.Context r6) {
        /*
            java.lang.String r0 = "com.xiaomi.smarthome.globaldynamicsetting"
            r1 = 0
            android.content.SharedPreferences r0 = r6.getSharedPreferences(r0, r1)
            java.lang.String r1 = "server_new"
            java.lang.String r2 = ""
            java.lang.String r1 = r0.getString(r1, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x001d
            java.lang.String r1 = "server"
            java.lang.String r2 = ""
            java.lang.String r1 = r0.getString(r1, r2)
        L_0x001d:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            r2 = 0
            if (r0 == 0) goto L_0x0025
            return r2
        L_0x0025:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x0065 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0065 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = com.xiaomi.smarthome.frame.server_compact.ServerBean.b(r0)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r3 = "ServerCompact"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0065 }
            r4.<init>()     // Catch:{ Exception -> 0x0065 }
            java.lang.String r5 = "parseCurrentServerFromSpfs: new "
            r4.append(r5)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r5 = r0.b()     // Catch:{ Exception -> 0x0065 }
            r4.append(r5)     // Catch:{ Exception -> 0x0065 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0065 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r3, r4)     // Catch:{ Exception -> 0x0065 }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.frame.server_compact.ServerBean> r3 = ah     // Catch:{ Exception -> 0x0065 }
            java.lang.String r4 = r0.b     // Catch:{ Exception -> 0x0065 }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ Exception -> 0x0065 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r3 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r3     // Catch:{ Exception -> 0x0065 }
            if (r3 == 0) goto L_0x0064
            boolean r0 = p(r6)     // Catch:{ Exception -> 0x0065 }
            if (r0 == 0) goto L_0x005d
            a((android.content.Context) r6, (com.xiaomi.smarthome.frame.server_compact.ServerBean) r3)     // Catch:{ Exception -> 0x0065 }
        L_0x005d:
            java.lang.Object r0 = r3.clone()     // Catch:{ Exception -> 0x0065 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r0     // Catch:{ Exception -> 0x0065 }
            return r0
        L_0x0064:
            return r0
        L_0x0065:
            java.util.Map<java.lang.String, android.util.Pair<java.lang.String, java.lang.String>> r0 = af     // Catch:{ Exception -> 0x0083 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x0083 }
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ Exception -> 0x0083 }
            java.lang.Object r1 = r0.first     // Catch:{ Exception -> 0x0083 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x0083 }
            java.lang.Object r0 = r0.second     // Catch:{ Exception -> 0x0083 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0083 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = com.xiaomi.smarthome.frame.server_compact.ServerBean.a(r1, r0)     // Catch:{ Exception -> 0x0083 }
            boolean r1 = p(r6)     // Catch:{ Exception -> 0x0083 }
            if (r1 == 0) goto L_0x0082
            a((android.content.Context) r6, (com.xiaomi.smarthome.frame.server_compact.ServerBean) r0)     // Catch:{ Exception -> 0x0083 }
        L_0x0082:
            return r0
        L_0x0083:
            java.lang.String r6 = "ServerCompact"
            java.lang.String r0 = "parseCurrentServerFromSpfs: null"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r6, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.server_compact.ServerCompact.b(android.content.Context):com.xiaomi.smarthome.frame.server_compact.ServerBean");
    }

    @NonNull
    public static Locale c(Context context) {
        Locale d2 = d(context);
        if (d2 != null) {
            return d2;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        }
        return Locale.getDefault();
    }

    @Nullable
    public static Locale d(Context context) {
        if (CoreApi.ab() && CoreApi.a().l()) {
            return CoreApi.a().I();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalDynamicSettingManager.f14576a, 0);
        String b2 = SharePrefsManager.b(sharedPreferences, GlobalDynamicSettingConst.g, "");
        String b3 = SharePrefsManager.b(sharedPreferences, GlobalDynamicSettingConst.h, "");
        if (TextUtils.isEmpty(b2) || TextUtils.isEmpty(b3)) {
            return null;
        }
        return new Locale(b2, b3);
    }

    @Nullable
    public static ServerBean b(Context context, String str) {
        if (context != null) {
            try {
                for (ServerBean next : a(c(context), context)) {
                    if (next.b.equalsIgnoreCase(str)) {
                        return next;
                    }
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("context is null");
        }
    }

    public static boolean e(Context context) {
        return b(a(context));
    }

    public static boolean b(ServerBean serverBean) {
        return serverBean != null && !c(serverBean);
    }

    public static boolean c(ServerBean serverBean) {
        return a(serverBean, m);
    }

    public static boolean f(Context context) {
        return a(a(context), m);
    }

    public static boolean g(Context context) {
        ServerBean a2 = a(context);
        return a2 != null && TextUtils.equals(a2.f1530a, "de");
    }

    public static boolean d(ServerBean serverBean) {
        return serverBean != null && TextUtils.equals(serverBean.f1530a, "de");
    }

    public static boolean h(Context context) {
        return a(a(context), K);
    }

    public static boolean e(ServerBean serverBean) {
        return a(serverBean, K);
    }

    public static boolean f(ServerBean serverBean) {
        return a(serverBean, f1531a);
    }

    public static boolean i(Context context) {
        return a(a(context), f1531a);
    }

    public static boolean g(ServerBean serverBean) {
        return a(serverBean, "IN");
    }

    public static boolean j(Context context) {
        return a(a(context), "IN");
    }

    public static boolean h(ServerBean serverBean) {
        return a(serverBean, i);
    }

    public static boolean k(Context context) {
        return a(a(context), i);
    }

    public static boolean i(ServerBean serverBean) {
        return a(serverBean, B);
    }

    public static boolean l(Context context) {
        return a(a(context), B);
    }

    public static boolean j(ServerBean serverBean) {
        return a(serverBean, d);
    }

    public static boolean m(Context context) {
        return a(a(context), d);
    }

    public static boolean k(ServerBean serverBean) {
        return a(serverBean, t);
    }

    public static boolean n(Context context) {
        return a(a(context), t);
    }

    public static boolean l(ServerBean serverBean) {
        return a(serverBean, "st");
    }

    public static boolean o(Context context) {
        return a(a(context), "st");
    }

    private static boolean a(ServerBean serverBean, String str) {
        return (serverBean == null || str == null || !str.equalsIgnoreCase(serverBean.b)) ? false : true;
    }

    @SuppressLint({"ApplySharedPref"})
    public static void a(Context context, @Nullable ServerBean serverBean) {
        if (p(context)) {
            ServerBean serverBean2 = ai.get();
            if (serverBean2 != null) {
                do {
                } while (!ai.compareAndSet(serverBean2, serverBean));
            }
            try {
                context.getSharedPreferences(GlobalDynamicSettingManager.f14576a, 0).edit().putString(GlobalDynamicSettingConst.e, serverBean != null ? serverBean.a() : new JSONObject().toString()).commit();
                StringBuilder sb = new StringBuilder();
                sb.append("saveServer: ");
                sb.append(serverBean == null ? "clear server " : serverBean.b());
                LogUtil.a(k, sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    public static ServerBean b() {
        return (ServerBean) U.clone();
    }

    public static ServerBean c() {
        return (ServerBean) V.clone();
    }

    public static ServerBean d() {
        return (ServerBean) W.clone();
    }

    public static ServerBean e() {
        return (ServerBean) X.clone();
    }

    public static ServerBean f() {
        return (ServerBean) Y.clone();
    }

    public static ServerBean g() {
        return (ServerBean) Z.clone();
    }

    public static ServerBean h() {
        return (ServerBean) aa.clone();
    }

    public static ServerBean i() {
        return (ServerBean) ab.clone();
    }

    public static ServerBean j() {
        return (ServerBean) ac.clone();
    }

    public static ServerBean k() {
        return (ServerBean) ad.clone();
    }

    public static boolean p(Context context) {
        if (j == null) {
            synchronized (ServerCompact.class) {
                if (j == null) {
                    j = Boolean.valueOf(ProcessUtil.h(context));
                    boolean booleanValue = j.booleanValue();
                    return booleanValue;
                }
            }
        }
        return j.booleanValue();
    }
}
