package com.xiaomi.youpin.youpin_common.statistic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.alibaba.android.arouter.utils.Consts;
import com.alipay.android.phone.a.a.a;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.youpin.common.util.NetTypeUtil;
import com.xiaomi.youpin.common.util.SysUtils;
import com.xiaomi.youpin.cookie.YouPinCookieManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.api.IAppStatApi;
import com.xiaomi.youpin.youpin_common.statistic.StatApi;
import com.xiaomi.youpin.youpin_common.statistic.params.RecordParams;
import com.xiaomi.youpin.youpin_common.statistic.params.TouchParams;
import com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams;
import com.xiaomi.youpin.youpin_common.statistic.params.VisibleParams;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatManager {
    private static String A = "";
    private static volatile StatManager B = null;

    /* renamed from: a  reason: collision with root package name */
    public static final int f23812a = 1;
    public static final int b = 2;
    public static final String c = "A";
    public static final String d = "RN";
    public static final String e = "W";
    public static final String f = "com.xiaomi.youpin.statistic.install";
    private static final String g = "StatManager";
    private static final String h = "com.xiaomi.youpin.statistic.pref";
    private static final String i = "VIEW";
    private static final String j = "TOUCH";
    private static final String k = "PAYSUCCESS";
    private static final String l = "PAYFAIL";
    private static final String m = "VIEWEND";
    private static final String n = "ADDCART";
    private static final String o = "VISIBLE";
    private static final String p = "INSTALL";
    private static final String q = "WAKEUP";
    private static final long r = 600000;
    private static final long s = 5000;
    private static final int t = 3000;
    private static final int u = 1000;
    private static final long v = 432000000;
    private static final int w = 1;
    private static final int x = 3;
    private static final int y = 4;
    private static final String z = " ";
    private final Random C = new Random();
    private final Set<String> D = new HashSet();
    private final Context E = StoreApiManager.a().c();
    private final long F = 5000;
    private final int G = 1000;
    private final Map<String, Node> H = new HashMap(10);
    private final List<ActionNode> I = new ArrayList();
    private MessageHandlerThread J;
    /* access modifiers changed from: private */
    public WorkerHandler K;
    private String L = "";
    private String M = "";
    private String N = "";
    private String O = "";
    private String P = "";
    private IAppStatApi Q;
    /* access modifiers changed from: private */
    public volatile boolean R = false;
    /* access modifiers changed from: private */
    public SharedPreferences S;
    private SharedPreferences T;
    private boolean U = false;
    /* access modifiers changed from: private */
    public long V;
    private long W;
    private long X = 0;
    private int Y;
    private String Z = null;
    private String aa = null;
    private String ab = null;
    private AddRecordListener ac;
    private Node ad;
    private boolean ae = false;
    private String af = null;
    private String ag;

    private StatManager() {
        l();
    }

    public static void a(String str) {
        A = str;
    }

    public static StatManager a() {
        if (B == null) {
            synchronized (StatManager.class) {
                if (B == null) {
                    B = new StatManager();
                }
            }
        }
        return B;
    }

    private static String a(RecordInfo recordInfo) {
        return recordInfo.b + " " + System.currentTimeMillis();
    }

    public static void b() {
        if (B != null) {
            B.X = System.currentTimeMillis();
        }
    }

    public static void c() {
        if (B != null) {
            if (B.X > 0 && System.currentTimeMillis() - B.X > 300000) {
                B.N = "";
            }
            B.X = 0;
        }
    }

    public static void d() {
        if (B != null) {
            B.e("");
            B.N = "";
        }
    }

    public static void e() {
        if (B != null) {
            YouPinCookieManager.a().a("youpin_sessionid", B.N, "shopapi.io.mi.com");
            YouPinCookieManager.a().a("youpin_sessionid", B.N, PageUrl.f);
            YouPinCookieManager.a().a("mijiasn", B.L, "shopapi.io.mi.com");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void k() {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.U     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r8)
            return
        L_0x0007:
            android.content.SharedPreferences r0 = r8.T     // Catch:{ all -> 0x004a }
            r1 = 0
            if (r0 != 0) goto L_0x0016
            android.content.Context r0 = r8.E     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "com.xiaomi.youpin.statistic.install"
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r2, r1)     // Catch:{ all -> 0x004a }
            r8.T = r0     // Catch:{ all -> 0x004a }
        L_0x0016:
            android.content.SharedPreferences r0 = r8.T     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "has_installed"
            boolean r0 = r0.getBoolean(r2, r1)     // Catch:{ all -> 0x004a }
            r8.U = r0     // Catch:{ all -> 0x004a }
            boolean r0 = r8.U     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x0048
            java.lang.String r2 = "INSTALL"
            java.lang.String r3 = ""
            java.lang.String r4 = ""
            java.lang.String r5 = ""
            java.lang.String r6 = "A"
            r7 = 0
            r1 = r8
            r1.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (java.util.Map<java.lang.String, java.lang.Object>) r7)     // Catch:{ all -> 0x004a }
            r0 = 1
            r8.a((boolean) r0)     // Catch:{ all -> 0x004a }
            r8.U = r0     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences r1 = r8.T     // Catch:{ all -> 0x004a }
            android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "has_installed"
            android.content.SharedPreferences$Editor r0 = r1.putBoolean(r2, r0)     // Catch:{ all -> 0x004a }
            r0.apply()     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r8)
            return
        L_0x004a:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_common.statistic.StatManager.k():void");
    }

    private void l() {
        try {
            this.Q = StoreApiManager.a().b().b();
            this.O = StoreApiManager.a().b().d() + "_A";
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.J = new MessageHandlerThread("StatWorker");
        this.J.start();
        this.K = new WorkerHandler(this.J.getLooper());
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public final void run() {
                StatManager.this.s();
            }
        }, 7000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void s() {
        a(false);
    }

    private IAppStatApi m() {
        try {
            if (this.Q == null) {
                this.Q = StoreApiManager.a().b().b();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this.Q;
    }

    private String n() {
        try {
            if (TextUtils.isEmpty(this.O)) {
                this.O = StoreApiManager.a().b().d() + "_A";
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this.O;
    }

    public void a(boolean z2) {
        this.K.sendMessageDelayed(this.K.obtainMessage(1, Boolean.valueOf(z2)), 1000);
    }

    /* access modifiers changed from: private */
    public void b(boolean z2) {
        if (z2 || System.currentTimeMillis() - this.V >= 5000) {
            if (this.S == null) {
                this.S = this.E.getSharedPreferences(h, 0);
            }
            Pattern compile = Pattern.compile(" ");
            JSONArray jSONArray = new JSONArray();
            final ArrayList arrayList = new ArrayList();
            ArrayList<String> arrayList2 = new ArrayList<>();
            Map<String, ?> all = this.S.getAll();
            if (all != null && all.size() > 0) {
                for (Map.Entry next : all.entrySet()) {
                    String str = (String) next.getKey();
                    String[] split = compile.split(str);
                    if (split.length >= 2) {
                        String str2 = split[0];
                        long j2 = 0;
                        try {
                            j2 = Long.parseLong(split[1]);
                        } catch (Exception unused) {
                        }
                        if (System.currentTimeMillis() - j2 > v) {
                            arrayList2.add(str);
                        } else if (jSONArray.length() > 1000) {
                            break;
                        } else {
                            arrayList.add(str);
                            JSONObject jSONObject = null;
                            try {
                                jSONObject = new JSONObject((String) next.getValue());
                            } catch (Exception unused2) {
                            }
                            if (jSONObject != null) {
                                jSONArray.put(jSONObject);
                            }
                        }
                    }
                }
            }
            if (arrayList2.size() > 0) {
                SharedPreferences.Editor edit = this.S.edit();
                for (String remove : arrayList2) {
                    edit.remove(remove);
                }
                edit.apply();
            }
            if (jSONArray.length() != 0) {
                LogUtils.d(g, jSONArray.toString());
                this.R = true;
                StatApi.a().b(jSONArray, new StatApi.CallBack() {
                    public void a(String str) {
                        StatManager.this.K.obtainMessage(4, arrayList).sendToTarget();
                    }

                    public void a(int i, String str) {
                        LogUtils.e(StatManager.g, "uploadStatV2 onFailed:" + str);
                        StatManager.this.K.obtainMessage(4, (Object) null).sendToTarget();
                    }
                });
                return;
            }
            return;
        }
        this.K.sendMessageDelayed(this.K.obtainMessage(1, false), 1000);
    }

    /* access modifiers changed from: private */
    public void b(RecordInfo recordInfo) {
        String a2 = a(recordInfo);
        JSONObject c2 = c(recordInfo);
        if (c2 != null) {
            String jSONObject = c2.toString();
            if (this.S == null) {
                this.S = this.E.getSharedPreferences(h, 0);
            }
            PreferenceUtils.a(this.S, a2, jSONObject);
        }
    }

    private String o() {
        if (this.aa == null) {
            this.aa = a.f813a + Build.VERSION.RELEASE;
        }
        if (this.Z == null) {
            this.Z = SysUtils.h() + SysUtils.i();
        }
        if (this.ab == null) {
            this.ab = Build.MODEL;
        }
        return "net=" + NetTypeUtil.a(this.E) + "&osv=" + this.aa + "&anv=" + this.Z + "&model=" + this.ab;
    }

    private void p() {
        LogUtils.d(g, "generateSessionId");
        this.W = System.currentTimeMillis();
        Random random = new Random();
        random.setSeed(this.W);
        this.N = "" + this.W + JSMethod.NOT_SET + Math.abs(random.nextInt());
        this.Y = -1;
        YouPinCookieManager.a().a("youpin_sessionid", this.N, "shopapi.io.mi.com");
        YouPinCookieManager.a().a("youpin_sessionid", this.N, PageUrl.f);
    }

    private String q() {
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(this.N)) {
            p();
            return this.N;
        }
        this.W = currentTimeMillis;
        return this.N;
    }

    public String f() {
        String r2 = r();
        return r2.startsWith("Start_") ? "" : r2;
    }

    private String r() {
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(this.L) || currentTimeMillis - this.V > 1800000) {
            this.V = System.currentTimeMillis();
            e("Start_" + this.V);
            YouPinCookieManager.a().a("mijiasn", this.L, "shopapi.io.mi.com");
            return this.L;
        }
        this.V = System.currentTimeMillis();
        return this.L;
    }

    private void e(String str) {
        this.L = str;
    }

    private Node f(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            str = "A";
        }
        Node node = this.H.get(str);
        if (node != null) {
            return node;
        }
        return this.ad;
    }

    private void a(String str, Node node) {
        if (TextUtils.isEmpty(str)) {
            str = "A";
        }
        this.H.put(str, node);
        this.ad = node;
    }

    public String b(String str) {
        if (TextUtils.isEmpty(this.ag)) {
            return "";
        }
        String[] split = this.ag.split("\\.");
        if (split.length < 5) {
            return "";
        }
        String[] split2 = str.split("\\.");
        return (split2.length >= 2 && TextUtils.equals(split2[0], split[2]) && TextUtils.equals(split2[1], split[3])) ? this.ag : "";
    }

    private String g(String str) {
        String str2;
        if (TextUtils.isEmpty(str) || !TextUtils.equals(UrlConstants.parseShortPath(str), UrlConstants.cart)) {
            return str;
        }
        String b2 = b("tab.4");
        if (!str.contains("?")) {
            str2 = str + "?";
        } else {
            str2 = str + com.alipay.sdk.sys.a.b;
        }
        return str2 + "spmref=" + b2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, org.json.JSONObject r13) {
        /*
            r7 = this;
            int r0 = r8.hashCode()
            switch(r0) {
                case -719524261: goto L_0x0044;
                case -430160095: goto L_0x003a;
                case -68911194: goto L_0x0030;
                case 2634405: goto L_0x0026;
                case 80013087: goto L_0x001c;
                case 1172216822: goto L_0x0012;
                case 1184726098: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x004e
        L_0x0008:
            java.lang.String r0 = "VISIBLE"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 6
            goto L_0x004f
        L_0x0012:
            java.lang.String r0 = "VIEWEND"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 1
            goto L_0x004f
        L_0x001c:
            java.lang.String r0 = "TOUCH"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 2
            goto L_0x004f
        L_0x0026:
            java.lang.String r0 = "VIEW"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 0
            goto L_0x004f
        L_0x0030:
            java.lang.String r0 = "PAYFAIL"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 4
            goto L_0x004f
        L_0x003a:
            java.lang.String r0 = "ADDCART"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 5
            goto L_0x004f
        L_0x0044:
            java.lang.String r0 = "PAYSUCCESS"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x004e
            r0 = 3
            goto L_0x004f
        L_0x004e:
            r0 = -1
        L_0x004f:
            switch(r0) {
                case 0: goto L_0x0098;
                case 1: goto L_0x0094;
                case 2: goto L_0x0073;
                case 3: goto L_0x006f;
                case 4: goto L_0x006b;
                case 5: goto L_0x0067;
                case 6: goto L_0x005d;
                default: goto L_0x0052;
            }
        L_0x0052:
            r6 = 0
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r0.a((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (java.util.Map<java.lang.String, java.lang.Object>) r6)
            goto L_0x00bc
        L_0x005d:
            r0 = r7
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r0.a((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (org.json.JSONObject) r5)
            goto L_0x00bc
        L_0x0067:
            r7.b(r9, r10, r11, r12)
            goto L_0x00bc
        L_0x006b:
            r7.d(r9, r10, r11, r12)
            goto L_0x00bc
        L_0x006f:
            r7.c(r9, r10, r11, r12)
            goto L_0x00bc
        L_0x0073:
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder r8 = new com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder
            r8.<init>()
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder r8 = r8.a((java.lang.String) r9)
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder r8 = r8.b(r10)
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder r8 = r8.c(r11)
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder r8 = r8.d(r12)
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams$Builder r8 = r8.a((org.json.JSONObject) r13)
            com.xiaomi.youpin.youpin_common.statistic.params.TouchParams r8 = r8.a()
            r7.a((com.xiaomi.youpin.youpin_common.statistic.params.TouchParams) r8)
            goto L_0x00bc
        L_0x0094:
            r7.j(r12)
            goto L_0x00bc
        L_0x0098:
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder r8 = new com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder
            r8.<init>()
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder r8 = r8.a((java.lang.String) r9)
            java.lang.String r9 = r7.g(r10)
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder r8 = r8.b(r9)
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder r8 = r8.c(r11)
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder r8 = r8.d(r12)
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams$Builder r8 = r8.a((org.json.JSONObject) r13)
            com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams r8 = r8.a()
            r7.a((com.xiaomi.youpin.youpin_common.statistic.params.ViewRecordParams) r8)
        L_0x00bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_common.statistic.StatManager.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.json.JSONObject):void");
    }

    public void a(String str, String str2, String str3, int i2) {
        a(new ViewRecordParams.Builder().a(str).b(str2).c(str3).d("A").a(i2).a());
    }

    public void c(String str) {
        this.P = str;
        d(str);
    }

    public String g() {
        String str = this.P;
        this.P = "";
        return str;
    }

    public void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            Pair<String, HashMap<String, String>> a2 = UrlParse.a(str);
            if (a2.second != null && ((HashMap) a2.second).containsKey("source")) {
                e((String) ((HashMap) a2.second).get("source"));
                YouPinCookieManager.a().a("mijiasn", this.L, "shopapi.io.mi.com");
                this.V = System.currentTimeMillis();
            }
            if (a2.second != null && ((HashMap) a2.second).containsKey(Tags.Order.ORDER_TRACE)) {
                this.M = (String) ((HashMap) a2.second).get(Tags.Order.ORDER_TRACE);
            }
        }
    }

    private void a(ViewRecordParams viewRecordParams) {
        String str;
        JSONArray jSONArray;
        String str2 = viewRecordParams.b;
        if (str2 == null) {
            str2 = "";
        }
        if (str2.length() > 512) {
            str2 = UrlParse.c(str2);
        }
        Node f2 = f(viewRecordParams.d);
        if (f2 != null && !TextUtils.isEmpty(viewRecordParams.f23819a) && viewRecordParams.f23819a.replace(Operators.DOLLAR_STR, "").toLowerCase().equals("web")) {
            if (str2.contains("root=1")) {
                this.ag = null;
                return;
            } else if (this.ae && f2.b != null && f2.b.equals(str2)) {
                this.ag = null;
                return;
            }
        }
        if (this.ae && f2 != null) {
            LogUtils.e(g, "add auto ViewEndRecord:" + f2.f23805a);
            j(f2.g);
        }
        this.ae = true;
        if (m() != null && !TextUtils.isEmpty(viewRecordParams.f23819a)) {
            String replace = viewRecordParams.f23819a.replace(Operators.DOLLAR_STR, "");
            LogUtils.d(g, "addViewRecord:" + replace);
            this.Q.a(this.E, replace, str2);
        }
        if (!TextUtils.isEmpty(str2)) {
            d(str2);
        } else {
            str2 = "";
        }
        String str3 = viewRecordParams.g;
        if (TextUtils.isEmpty(str3)) {
            str3 = UrlParse.a(str2, "scm");
        }
        String a2 = UrlParse.a(str2, "spmref");
        if (TextUtils.isEmpty(a2)) {
            a2 = h(UrlParse.a(str2, Tags.Order.ORDER_TRACE));
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = this.ag;
        }
        String i2 = i(a2);
        String str4 = "";
        if (viewRecordParams.e != null) {
            str4 = viewRecordParams.e.optString("spm");
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = UrlParse.a(str2, "spm");
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = UrlParse.a(str2, "page_id");
        }
        if (TextUtils.isEmpty(str4) && viewRecordParams.e != null) {
            str4 = viewRecordParams.e.optString("page_id", "");
        }
        if (TextUtils.isEmpty(str)) {
            str = viewRecordParams.f23819a;
            if (!str.startsWith(Operators.DOLLAR_STR)) {
                str = Operators.DOLLAR_STR + str + Operators.DOLLAR_STR;
            }
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1018230412:
                    if (str.equals("$GoodsCategory$")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -471485528:
                    if (str.equals("$GroupBuyDetail$")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -271018798:
                    if (str.equals("$Goods$")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1100557665:
                    if (str.equals("$Home$")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 1539037487:
                    if (str.equals("$Detail$")) {
                        c2 = 1;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    str = viewRecordParams.c;
                    break;
                case 1:
                case 2:
                    String a3 = UrlParse.a(str2, ApiConst.j);
                    if (!TextUtils.isEmpty(a3)) {
                        str = str + JSMethod.NOT_SET + a3;
                        break;
                    }
                    break;
                case 3:
                case 4:
                    str = str + JSMethod.NOT_SET + viewRecordParams.c;
                    break;
            }
        }
        String i3 = i(str);
        Node node = new Node();
        node.f23805a = viewRecordParams.f23819a;
        node.b = str2;
        node.c = System.currentTimeMillis();
        node.g = viewRecordParams.d;
        node.f = i3;
        LogUtils.d(g, "node.spm:" + node.f);
        LogUtils.d(g, "spmref.spm" + i2);
        HashMap hashMap = new HashMap();
        hashMap.put("id", str2);
        if (!TextUtils.isEmpty(viewRecordParams.c)) {
            hashMap.put("iid", viewRecordParams.c);
            node.e = viewRecordParams.c;
        }
        a(viewRecordParams.d, node);
        if (this.I.size() > 0) {
            jSONArray = new JSONArray();
            jSONArray.put(this.I.get(this.I.size() - 1).b);
        } else {
            jSONArray = null;
        }
        int i4 = viewRecordParams.f;
        if (viewRecordParams.e != null) {
            i4 = viewRecordParams.e.optInt("isback");
        }
        node.d = a(new RecordParams.Builder().a("VIEW").b(viewRecordParams.f23819a).a((Map<String, Object>) hashMap).a(jSONArray).c(node.f).d(i2).e(viewRecordParams.d).f(str3).a(i4).a());
        k();
        this.D.clear();
        this.ag = null;
    }

    private String a(int i2) {
        String valueOf = String.valueOf(Math.abs(this.C.nextLong()));
        return valueOf.length() > i2 ? valueOf.substring(0, i2) : valueOf;
    }

    private String h(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String[] split = str.split("-");
        if (split.length >= 4) {
            return split[0] + "-" + split[1] + "." + split[2] + "." + split[3];
        } else if (split.length != 3) {
            return str;
        } else {
            return split[0] + "-" + split[1] + "." + split[2];
        }
    }

    private String i(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return "";
        }
        if (split.length >= 5) {
            return str;
        }
        String[] strArr = new String[5];
        if (!split[0].equals(n())) {
            strArr[0] = this.O;
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i3 = 0;
        while (i2 < 5) {
            if (i3 < split.length) {
                strArr[i2] = split[i3];
            } else if (i2 == 4) {
                strArr[i2] = a(8);
            } else {
                strArr[i2] = "0";
            }
            i2++;
            i3++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < strArr.length; i4++) {
            if (i4 != 0) {
                sb.append(".");
            }
            sb.append(strArr[i4]);
        }
        return sb.toString();
    }

    public void h() {
        j("A");
    }

    private void j(String str) {
        Node f2 = f(str);
        if (f2 == null) {
            LogUtils.e(g, "addViewEndRecord view stack is null");
        } else if (!this.ae) {
            LogUtils.e(g, "multi ViewEndRecord:" + f2.f23805a);
        } else {
            this.ae = false;
            if (m() != null) {
                String replace = f2.f23805a.replace(Operators.DOLLAR_STR, "");
                LogUtils.d(g, "addViewEndRecord:" + replace);
                this.Q.b(this.E, replace);
            }
            long currentTimeMillis = System.currentTimeMillis() - f2.c;
            HashMap hashMap = new HashMap();
            hashMap.put("id", f2.b);
            if (!TextUtils.isEmpty(f2.e)) {
                hashMap.put("iid", f2.e);
            }
            hashMap.put("spend", Long.valueOf(currentTimeMillis));
            a(new RecordParams.Builder().a("VIEWEND").b(f2.f23805a).a((Map<String, Object>) hashMap).e(str).a());
            a(true);
            this.D.clear();
        }
    }

    public void a(String str, String str2, String str3) {
        a(str, str2, str3, "A");
    }

    public void a(String str, String str2, String str3, String str4) {
        a(new VisibleParams.Builder().a(str).b(str2).c(str3).e(str4).a());
    }

    private void a(String str, String str2, String str3, String str4, JSONObject jSONObject) {
        String str5;
        String str6 = null;
        if (jSONObject != null) {
            str6 = jSONObject.optString("spm", "");
            str5 = jSONObject.optString("scm", "");
            if ("W".equals(str4)) {
                String optString = jSONObject.optString("area", "");
                if (!TextUtils.isEmpty(optString)) {
                    str = optString;
                }
            }
        } else {
            str5 = null;
        }
        a(new VisibleParams.Builder().a(str).b(str2).c(str3).d(str6).e(str4).f(str5).a());
    }

    public void a(VisibleParams visibleParams) {
        if (visibleParams != null) {
            Node f2 = f(visibleParams.e);
            if (f2 == null) {
                LogUtils.e(g, "addVisibleRecord view stack is null");
                return;
            }
            String str = visibleParams.d;
            String str2 = visibleParams.f;
            if (!TextUtils.isEmpty(str)) {
                String str3 = visibleParams.b + str;
                if (!TextUtils.isEmpty(visibleParams.f)) {
                    str3 = str3 + visibleParams.f;
                }
                if (!this.D.contains(str3)) {
                    this.D.add(str3);
                } else {
                    return;
                }
            }
            String a2 = a(f2.f, str);
            LogUtils.d(g, "addVisibleRecord :  *******   SPM  : " + a2);
            LogUtils.d(g, "addVisibleRecord :  *******   SCM  : " + str2);
            HashMap hashMap = new HashMap();
            hashMap.put("id", f2.b);
            hashMap.put("area", visibleParams.f23821a != null ? visibleParams.f23821a : "");
            hashMap.put("iid", visibleParams.b);
            if (!TextUtils.isEmpty(visibleParams.c)) {
                hashMap.put("idlist", visibleParams.c);
            }
            if (!TextUtils.isEmpty(a2) && !f2.f23805a.contains(Constants.WebViewURL.PAGE_HOME) && a2.startsWith("YouPin_A.1.")) {
                LogUtils.e(g, "visible:" + f2.f23805a + " spm:" + a2);
            }
            a(new RecordParams.Builder().a(o).b(f2.f23805a).a((Map<String, Object>) hashMap).c(a2).f(str2).e(visibleParams.e).a());
            if (m() != null) {
                String replace = f2.f23805a.replace(Operators.DOLLAR_STR, "");
                this.Q.a(o, replace + JSMethod.NOT_SET + visibleParams.f23821a);
            }
        }
    }

    public void i() {
        a("STARTUP", "push_open", NotificationManagerCompat.from(this.E).areNotificationsEnabled() ? "1" : "0", (String) null, (String) null, (Map<String, Object>) null);
    }

    public void b(String str, String str2, String str3) {
        a(q, str, str2, str3, (String) null, (String) null, (Map<String, Object>) null);
    }

    private void a(String str, String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable Map<String, Object> map) {
        a(str, "", str2, str3, str4, str5, map);
    }

    private void a(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable String str6, @Nullable Map<String, Object> map) {
        Node f2 = f(str6);
        String str7 = "";
        if (f2 != null) {
            str7 = f2.b;
            if (TextUtils.isEmpty(str2)) {
                str2 = f2.f23805a;
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", str7);
        hashMap.put("area", str3 != null ? str3 : "");
        if (str4 == null) {
            str4 = "";
        }
        hashMap.put("iid", str4);
        if (!TextUtils.isEmpty(str5)) {
            hashMap.put("idlist", str5);
        }
        if (map != null && !map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                hashMap.put(next.getKey(), next.getValue());
            }
        }
        a(new RecordParams.Builder().a(str).b(str2).a((Map<String, Object>) hashMap).e(str6).a());
        if (m() != null) {
            String replace = str2.replace(Operators.DOLLAR_STR, "");
            IAppStatApi iAppStatApi = this.Q;
            iAppStatApi.a(str, replace + JSMethod.NOT_SET + str3);
        }
    }

    public void a(TouchParams touchParams) {
        String str;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        if (touchParams != null) {
            Node f2 = f(touchParams.d);
            if (f2 == null) {
                LogUtils.e(g, "addTouchRecord  view stack is null");
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("id", f2.b);
            hashMap.put("area", touchParams.f23817a != null ? touchParams.f23817a : "");
            hashMap.put("iid", touchParams.b != null ? touchParams.b : "");
            if (!TextUtils.isEmpty(touchParams.c)) {
                hashMap.put("idlist", touchParams.c);
            }
            String str2 = touchParams.f;
            String str3 = touchParams.g;
            int i2 = 0;
            String str4 = null;
            if (touchParams.e != null) {
                if (!touchParams.e.optBoolean("fullref") || this.I.size() <= 0) {
                    jSONArray2 = null;
                } else {
                    jSONArray2 = new JSONArray();
                    for (int i3 = 0; i3 < this.I.size(); i3++) {
                        jSONArray2.put(this.I.get(i3).b);
                    }
                }
                String optString = touchParams.e.optString("spm", "");
                str = touchParams.e.optString("scm", "");
                String str5 = optString;
                jSONArray = jSONArray2;
                str2 = str5;
            } else {
                str = str3;
                jSONArray = null;
            }
            String a2 = a(f2.f, str2);
            LogUtils.d(g, "addTouchRecord :  *******   SPM  : " + a2);
            this.ag = a2;
            String a3 = a(new RecordParams.Builder().a("TOUCH").b(f2.f23805a).a((Map<String, Object>) hashMap).a(jSONArray).c(a2).e(touchParams.d).f(str).a());
            if (f2.f23805a != null) {
                ActionNode actionNode = new ActionNode();
                actionNode.f23800a = f2.f23805a;
                actionNode.b = a3;
                while (true) {
                    if (i2 >= this.I.size()) {
                        break;
                    } else if (this.I.get(i2) == null || this.I.get(i2).f23800a == null || !this.I.get(i2).f23800a.equals(f2.f23805a)) {
                        i2++;
                    } else {
                        for (int size = this.I.size() - 1; size >= i2; size--) {
                            this.I.remove(size);
                        }
                    }
                }
                this.I.add(actionNode);
            }
            if (m() != null) {
                if (f2.f23805a != null) {
                    str4 = f2.f23805a.replace(Operators.DOLLAR_STR, "");
                }
                HashMap hashMap2 = new HashMap();
                hashMap2.put("iid", touchParams.b != null ? touchParams.b : "");
                this.Q.a("TOUCH", str4 + JSMethod.NOT_SET + touchParams.f23817a, (Map) hashMap2);
            }
        }
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String[] split = str2.split("\\.");
        if (split.length == 0 || split.length < 2) {
            return str;
        }
        if (split.length == 5) {
            return str2;
        }
        String[] split2 = str.split("\\.");
        if (split2.length == 0) {
            return "";
        }
        if (split2.length != 5) {
            return str;
        }
        if (split.length == 4) {
            split2[0] = split[0];
            split2[1] = split[1];
            split2[2] = split[2];
            split2[3] = split[3];
        } else {
            split2[2] = split[split.length - 2];
            split2[3] = split[split.length - 1];
        }
        split2[4] = a(8);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < split2.length; i2++) {
            if (i2 != 0) {
                sb.append(".");
            }
            sb.append(split2[i2]);
        }
        return sb.toString();
    }

    public void c(String str, String str2, String str3) {
        c(str, str2, str3, "A");
    }

    public void d(String str, String str2, String str3) {
        b(str, str2, str3, "A");
    }

    private void b(String str, String str2, String str3, String str4) {
        a(n, str, str2, str3, str4);
    }

    private void c(String str, String str2, String str3, String str4) {
        a("PAYSUCCESS", str, str2, str3, str4);
    }

    private void a(String str, String str2, String str3, String str4, String str5) {
        Node f2 = f(str5);
        if (f2 == null) {
            LogUtils.e(g, "addPayRecord view stack is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("id", f2.b);
        hashMap.put("method", str2);
        if (str3 == null) {
            str3 = "";
        }
        hashMap.put("iid", str3);
        hashMap.put("price", str4);
        JSONArray jSONArray = null;
        if (this.I.size() > 0) {
            jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.I.size(); i2++) {
                jSONArray.put(this.I.get(i2).b);
            }
        }
        a(new RecordParams.Builder().a(str).b(f2.f23805a).a((Map<String, Object>) hashMap).a(jSONArray).e(str5).a());
    }

    public String j() {
        JSONArray jSONArray = new JSONArray();
        if (this.I.size() > 0) {
            for (int i2 = 0; i2 < this.I.size(); i2++) {
                jSONArray.put(this.I.get(i2).b);
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ref", jSONArray);
            jSONObject.put("channel", StoreApiManager.a().b().c());
            jSONObject.put("source", this.L);
            jSONObject.put(Tags.Order.ORDER_TRACE, this.M);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public void e(String str, String str2, String str3) {
        d(str, str2, str3, "A");
    }

    private void d(String str, String str2, String str3, String str4) {
        a("PAYFAIL", str, str2, str3, str4);
    }

    private JSONObject c(RecordInfo recordInfo) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(recordInfo.f23807a)) {
                jSONObject.put("u", recordInfo.f23807a);
            }
            jSONObject.put("id", String.valueOf(recordInfo.b));
            jSONObject.put("ys", recordInfo.c == null ? "" : recordInfo.c);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("et", recordInfo.d);
            jSONObject2.put("ref", recordInfo.e == null ? "" : recordInfo.e);
            if (!TextUtils.isEmpty(recordInfo.f)) {
                jSONObject2.put(Downloads.COLUMN_REFERER, URLEncoder.encode(recordInfo.f, "UTF-8"));
            }
            if (recordInfo.g != null) {
                jSONObject2.put("fullref", recordInfo.g);
            }
            jSONObject2.put("ext", o());
            if (recordInfo.h != null) {
                jSONObject2.put("ext2", recordInfo.h);
            }
            if (!TextUtils.isEmpty(recordInfo.i)) {
                jSONObject2.put("spm", recordInfo.i);
            }
            if (!TextUtils.isEmpty(recordInfo.j)) {
                jSONObject2.put("spmref", recordInfo.j);
            }
            if (!TextUtils.isEmpty(recordInfo.k)) {
                jSONObject2.put("s", recordInfo.k);
            }
            if (!TextUtils.isEmpty(recordInfo.l)) {
                jSONObject2.put(Tags.Order.ORDER_TRACE, recordInfo.l);
            }
            if (recordInfo.m != 0) {
                jSONObject2.put("isback", recordInfo.m);
            }
            if (!TextUtils.isEmpty(recordInfo.n)) {
                jSONObject2.put("scm", recordInfo.n);
            }
            jSONObject.put("e", jSONObject2);
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String a(RecordParams recordParams) {
        if (recordParams == null || TextUtils.isEmpty(recordParams.f23815a)) {
            return "";
        }
        String str = recordParams.b;
        String str2 = recordParams.f23815a;
        Node f2 = f(recordParams.h);
        if (!TextUtils.isEmpty(str)) {
            if (!str.startsWith(Operators.DOLLAR_STR)) {
                str = Operators.DOLLAR_STR + str + Operators.DOLLAR_STR;
            }
        } else if (f2 == null) {
            LogUtils.e(g, "addRecord view stack is null");
            str = Consts.c;
        } else {
            str = Operators.DOLLAR_STR + f2.f23805a + Operators.DOLLAR_STR;
        }
        if (this.ac != null) {
            this.ac.a(str2, str, r(), "", recordParams.c, recordParams.d, recordParams.h);
        }
        RecordInfo recordInfo = new RecordInfo();
        recordInfo.d = str2;
        int i2 = this.Y;
        this.Y = i2 + 1;
        recordInfo.b = i2;
        recordInfo.f23807a = IDMaps.a(StoreApiManager.a().b() == null ? "0" : StoreApiManager.a().b().i());
        recordInfo.e = a(str2, str, recordParams.c, recordParams.h);
        recordInfo.c = q();
        String str3 = this.af == null ? "" : this.af;
        if (!TextUtils.isEmpty(A) && str2.equals("VIEW") && f2 != null && A.equals(f2.b)) {
            A = "";
            if (str3.contains("?")) {
                str3 = str3 + "&ispush=1";
            } else {
                str3 = str3 + "?ispush=1";
            }
        }
        recordInfo.f = str3;
        recordInfo.g = recordParams.d;
        recordInfo.k = r();
        recordInfo.l = this.M;
        recordInfo.h = recordParams.g;
        recordInfo.j = recordParams.f;
        recordInfo.i = recordParams.e;
        recordInfo.m = recordParams.j;
        recordInfo.n = recordParams.i;
        this.K.obtainMessage(3, recordInfo).sendToTarget();
        if (str2.equals("VIEWEND")) {
            this.af = (String) recordParams.c.get("id");
        }
        return recordInfo.e;
    }

    private String a(String str, String str2, Map<String, Object> map, String str3) {
        return a(9, str, str2, map, str3);
    }

    private String a(int i2, String str, String str2, Map<String, Object> map, String str3) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.e(g, "buildRef event is empty");
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "A";
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            if (!str2.startsWith(Operators.DOLLAR_STR)) {
                str2 = Operators.DOLLAR_STR + str2 + Operators.DOLLAR_STR;
            }
            sb.append(str2);
            sb.append("?");
        }
        if (map == null) {
            map = new HashMap<>();
        }
        map.remove("page");
        map.put("event", str);
        map.put("t", Long.valueOf(System.currentTimeMillis()));
        map.put("c", str3);
        map.put("v", Integer.valueOf(i2));
        boolean z2 = true;
        for (String next : map.keySet()) {
            if (map.get(next) != null) {
                if (!z2) {
                    sb.append(com.alipay.sdk.sys.a.b);
                } else {
                    z2 = false;
                }
                sb.append(next);
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(map.get(next).toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public void a(AddRecordListener addRecordListener) {
        this.ac = addRecordListener;
    }

    private class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            boolean z = false;
            if (i != 1) {
                switch (i) {
                    case 3:
                        if (message.obj instanceof RecordInfo) {
                            StatManager.this.b((RecordInfo) message.obj);
                            if (!StatManager.this.K.hasMessages(1)) {
                                StatManager.this.K.sendEmptyMessageDelayed(1, 5000);
                                return;
                            }
                            return;
                        }
                        return;
                    case 4:
                        boolean unused = StatManager.this.R = false;
                        if (message.obj instanceof List) {
                            List<String> list = (List) message.obj;
                            if (StatManager.this.S != null) {
                                SharedPreferences.Editor edit = StatManager.this.S.edit();
                                for (String remove : list) {
                                    edit.remove(remove);
                                }
                                edit.apply();
                            }
                            long unused2 = StatManager.this.V = System.currentTimeMillis();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else if (StatManager.this.R) {
                StatManager.this.K.sendEmptyMessageDelayed(1, 5000);
            } else {
                StatManager.this.K.removeMessages(1);
                if (message.obj instanceof Boolean) {
                    z = ((Boolean) message.obj).booleanValue();
                }
                StatManager.this.b(z);
            }
        }
    }
}
