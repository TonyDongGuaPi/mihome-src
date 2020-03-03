package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.security.mobile.module.http.model.c;
import com.alipay.security.mobile.module.http.model.d;
import com.miui.tsmclient.net.TSMAuthContants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private Context f848a;
    private com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    private int c = 4;

    public a(Context context) {
        this.f848a = context;
    }

    public static String a(Context context) {
        String b2 = b(context);
        return com.alipay.security.mobile.module.a.a.a(b2) ? h.f(context) : b2;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String a2 = i.a(str);
            if (!com.alipay.security.mobile.module.a.a.a(a2)) {
                return a2;
            }
            String a3 = g.a(context, str);
            i.a(str, a3);
            return !com.alipay.security.mobile.module.a.a.a(a3) ? a3 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        int i = 0;
        while (i < 3) {
            try {
                String[] split = strArr[i].split(" ");
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
                i++;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private c b(Map<String, String> map) {
        b b2;
        b c2;
        try {
            Context context = this.f848a;
            d dVar = new d();
            String a2 = com.alipay.security.mobile.module.a.a.a(map, "appName", "");
            String a3 = com.alipay.security.mobile.module.a.a.a(map, TSMAuthContants.PARAM_SESSION_ID, "");
            String a4 = com.alipay.security.mobile.module.a.a.a(map, "rpcVersion", "");
            String a5 = a(context, a2);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String d = h.d(context);
            if (com.alipay.security.mobile.module.a.a.b(a3)) {
                dVar.d(a3);
            } else {
                dVar.d(a5);
            }
            dVar.e(securityToken);
            dVar.f(d);
            dVar.b("android");
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            com.alipay.apmobilesecuritysdk.e.c c3 = com.alipay.apmobilesecuritysdk.e.d.c(context);
            if (c3 != null) {
                str2 = c3.a();
                str3 = c3.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(str2) && (c2 = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str2 = c2.a();
                str3 = c2.c();
            }
            com.alipay.apmobilesecuritysdk.e.c b3 = com.alipay.apmobilesecuritysdk.e.d.b();
            if (b3 != null) {
                str = b3.a();
                str4 = b3.c();
            }
            if (com.alipay.security.mobile.module.a.a.a(str) && (b2 = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str = b2.a();
                str4 = b2.c();
            }
            dVar.h(str2);
            dVar.g(str);
            dVar.a(a4);
            if (com.alipay.security.mobile.module.a.a.a(str2)) {
                dVar.c(str);
                dVar.i(str4);
            } else {
                dVar.c(str2);
                dVar.i(str3);
            }
            dVar.a(e.a(context, map));
            return com.alipay.security.mobile.module.http.d.b(this.f848a, this.b.c()).a(dVar);
        } catch (Throwable th) {
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    private static String b(Context context) {
        try {
            String b2 = i.b();
            if (!com.alipay.security.mobile.module.a.a.a(b2)) {
                return b2;
            }
            com.alipay.apmobilesecuritysdk.e.c b3 = com.alipay.apmobilesecuritysdk.e.d.b(context);
            if (b3 != null) {
                i.a(b3);
                String a2 = b3.a();
                if (com.alipay.security.mobile.module.a.a.b(a2)) {
                    return a2;
                }
            }
            b b4 = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (b4 == null) {
                return "";
            }
            i.a(b4);
            String a3 = b4.a();
            return com.alipay.security.mobile.module.a.a.b(a3) ? a3 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File file = new File(externalStorageDirectory, ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e4 A[Catch:{ Exception -> 0x026f }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0232 A[Catch:{ Exception -> 0x026f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            r7 = this;
            android.content.Context r0 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = "tid"
            java.lang.String r2 = ""
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.a(r8, r1, r2)     // Catch:{ Exception -> 0x026f }
            java.lang.String r2 = "utdid"
            java.lang.String r3 = ""
            java.lang.String r2 = com.alipay.security.mobile.module.a.a.a(r8, r2, r3)     // Catch:{ Exception -> 0x026f }
            android.content.Context r3 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r3 = a((android.content.Context) r3)     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.c.a.a(r0, r1, r2, r3)     // Catch:{ Exception -> 0x026f }
            java.lang.String r0 = "appName"
            java.lang.String r1 = ""
            java.lang.String r0 = com.alipay.security.mobile.module.a.a.a(r8, r0, r1)     // Catch:{ Exception -> 0x026f }
            b()     // Catch:{ Exception -> 0x026f }
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            b((android.content.Context) r1)     // Catch:{ Exception -> 0x026f }
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            a(r1, r0)     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.i.a()     // Catch:{ Exception -> 0x026f }
            boolean r1 = a()     // Catch:{ Exception -> 0x026f }
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x00b3
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.apmobilesecuritysdk.common.a.a((android.content.Context) r1)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x0047
            goto L_0x00b3
        L_0x0047:
            com.alipay.apmobilesecuritysdk.d.e.a()     // Catch:{ Exception -> 0x026f }
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.d.e.b(r1, r8)     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.e.i.c()     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r1, r4)     // Catch:{ Exception -> 0x026f }
            r1 = r1 ^ r3
            if (r1 == 0) goto L_0x005c
            goto L_0x00bf
        L_0x005c:
            java.lang.String r1 = "tid"
            java.lang.String r4 = ""
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.a(r8, r1, r4)     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = "utdid"
            java.lang.String r5 = ""
            java.lang.String r4 = com.alipay.security.mobile.module.a.a.a(r8, r4, r5)     // Catch:{ Exception -> 0x026f }
            boolean r5 = com.alipay.security.mobile.module.a.a.b(r1)     // Catch:{ Exception -> 0x026f }
            if (r5 == 0) goto L_0x007f
            java.lang.String r5 = com.alipay.apmobilesecuritysdk.e.i.d()     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r1, r5)     // Catch:{ Exception -> 0x026f }
            if (r1 != 0) goto L_0x007f
            goto L_0x00bf
        L_0x007f:
            boolean r1 = com.alipay.security.mobile.module.a.a.b(r4)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x0090
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.e.i.e()     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r4, r1)     // Catch:{ Exception -> 0x026f }
            if (r1 != 0) goto L_0x0090
            goto L_0x00bf
        L_0x0090:
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.apmobilesecuritysdk.e.i.a((android.content.Context) r1, (java.lang.String) r0)     // Catch:{ Exception -> 0x026f }
            if (r1 != 0) goto L_0x0099
            goto L_0x00bf
        L_0x0099:
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = a(r1, r0)     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x00a6
            goto L_0x00bf
        L_0x00a6:
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = b((android.content.Context) r1)     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x00ce
            goto L_0x00bf
        L_0x00b3:
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = a(r1, r0)     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x00c1
        L_0x00bf:
            r1 = 1
            goto L_0x00cf
        L_0x00c1:
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = b((android.content.Context) r1)     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x00ce
            goto L_0x00bf
        L_0x00ce:
            r1 = 0
        L_0x00cf:
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.security.mobile.module.b.b.a()     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = com.alipay.security.mobile.module.b.b.p()     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.b(r4, r5)     // Catch:{ Exception -> 0x026f }
            if (r1 != 0) goto L_0x00e4
        L_0x00e1:
            r8 = 0
            goto L_0x0217
        L_0x00e4:
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.c.b r1 = new com.alipay.apmobilesecuritysdk.c.b     // Catch:{ Exception -> 0x026f }
            r1.<init>()     // Catch:{ Exception -> 0x026f }
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.b.a r4 = com.alipay.apmobilesecuritysdk.b.a.a()     // Catch:{ Exception -> 0x026f }
            int r4 = r4.b()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper.startUmidTaskSync(r1, r4)     // Catch:{ Exception -> 0x026f }
            com.alipay.security.mobile.module.http.model.c r1 = r7.b((java.util.Map<java.lang.String, java.lang.String>) r8)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x0103
            int r4 = r1.d()     // Catch:{ Exception -> 0x026f }
            goto L_0x0104
        L_0x0103:
            r4 = 2
        L_0x0104:
            if (r4 == r3) goto L_0x0136
            r8 = 3
            if (r4 == r8) goto L_0x0133
            if (r1 == 0) goto L_0x0121
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = "Server error, result:"
            r8.<init>(r4)     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = r1.b()     // Catch:{ Exception -> 0x026f }
            r8.append(r1)     // Catch:{ Exception -> 0x026f }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x026f }
        L_0x011d:
            com.alipay.apmobilesecuritysdk.c.a.a((java.lang.String) r8)     // Catch:{ Exception -> 0x026f }
            goto L_0x0124
        L_0x0121:
            java.lang.String r8 = "Server error, returned null"
            goto L_0x011d
        L_0x0124:
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r8 = a(r8, r0)     // Catch:{ Exception -> 0x026f }
            boolean r8 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r8)     // Catch:{ Exception -> 0x026f }
            if (r8 == 0) goto L_0x00e1
            r8 = 4
            goto L_0x0217
        L_0x0133:
            r8 = 1
            goto L_0x0217
        L_0x0136:
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            boolean r5 = r1.e()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.a((android.content.Context) r4, (boolean) r5)     // Catch:{ Exception -> 0x026f }
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = r1.f()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.d(r4, r5)     // Catch:{ Exception -> 0x026f }
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = r1.j()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.e(r4, r5)     // Catch:{ Exception -> 0x026f }
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = r1.l()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.a((android.content.Context) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x026f }
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = r1.k()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.f(r4, r5)     // Catch:{ Exception -> 0x026f }
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = r1.c()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.g(r4, r5)     // Catch:{ Exception -> 0x026f }
            android.content.Context r4 = r7.f848a     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.d.e.b(r4, r8)     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.i.c(r4)     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = r1.h()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.i.a((java.lang.String) r0, (java.lang.String) r4)     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = r1.g()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.i.b(r4)     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = r1.m()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.i.d(r1)     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = "tid"
            java.lang.String r4 = ""
            java.lang.String r1 = com.alipay.security.mobile.module.a.a.a(r8, r1, r4)     // Catch:{ Exception -> 0x026f }
            boolean r4 = com.alipay.security.mobile.module.a.a.b(r1)     // Catch:{ Exception -> 0x026f }
            if (r4 == 0) goto L_0x01a7
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.e.i.d()     // Catch:{ Exception -> 0x026f }
            boolean r4 = com.alipay.security.mobile.module.a.a.a(r1, r4)     // Catch:{ Exception -> 0x026f }
            if (r4 != 0) goto L_0x01a7
            com.alipay.apmobilesecuritysdk.e.i.e(r1)     // Catch:{ Exception -> 0x026f }
            goto L_0x01ab
        L_0x01a7:
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.e.i.d()     // Catch:{ Exception -> 0x026f }
        L_0x01ab:
            com.alipay.apmobilesecuritysdk.e.i.e(r1)     // Catch:{ Exception -> 0x026f }
            java.lang.String r1 = "utdid"
            java.lang.String r4 = ""
            java.lang.String r8 = com.alipay.security.mobile.module.a.a.a(r8, r1, r4)     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.b(r8)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x01cb
            java.lang.String r1 = com.alipay.apmobilesecuritysdk.e.i.e()     // Catch:{ Exception -> 0x026f }
            boolean r1 = com.alipay.security.mobile.module.a.a.a(r8, r1)     // Catch:{ Exception -> 0x026f }
            if (r1 != 0) goto L_0x01cb
            com.alipay.apmobilesecuritysdk.e.i.f(r8)     // Catch:{ Exception -> 0x026f }
            goto L_0x01cf
        L_0x01cb:
            java.lang.String r8 = com.alipay.apmobilesecuritysdk.e.i.e()     // Catch:{ Exception -> 0x026f }
        L_0x01cf:
            com.alipay.apmobilesecuritysdk.e.i.f(r8)     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.i.a()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.c r8 = com.alipay.apmobilesecuritysdk.e.i.g()     // Catch:{ Exception -> 0x026f }
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.d.a(r1, r8)     // Catch:{ Exception -> 0x026f }
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.d.a()     // Catch:{ Exception -> 0x026f }
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.b r1 = new com.alipay.apmobilesecuritysdk.e.b     // Catch:{ Exception -> 0x026f }
            java.lang.String r4 = com.alipay.apmobilesecuritysdk.e.i.b()     // Catch:{ Exception -> 0x026f }
            java.lang.String r5 = com.alipay.apmobilesecuritysdk.e.i.c()     // Catch:{ Exception -> 0x026f }
            java.lang.String r6 = com.alipay.apmobilesecuritysdk.e.i.f()     // Catch:{ Exception -> 0x026f }
            r1.<init>(r4, r5, r6)     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.a.a(r8, r1)     // Catch:{ Exception -> 0x026f }
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.a.a()     // Catch:{ Exception -> 0x026f }
            java.lang.String r8 = com.alipay.apmobilesecuritysdk.e.i.a((java.lang.String) r0)     // Catch:{ Exception -> 0x026f }
            android.content.Context r1 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.g.a(r1, r0, r8)     // Catch:{ Exception -> 0x026f }
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.g.a()     // Catch:{ Exception -> 0x026f }
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.e.h.a((android.content.Context) r8, (java.lang.String) r0, (long) r4)     // Catch:{ Exception -> 0x026f }
            goto L_0x00e1
        L_0x0217:
            r7.c = r8     // Catch:{ Exception -> 0x026f }
            android.content.Context r8 = r7.f848a     // Catch:{ Exception -> 0x026f }
            com.alipay.apmobilesecuritysdk.b.a r0 = r7.b     // Catch:{ Exception -> 0x026f }
            java.lang.String r0 = r0.c()     // Catch:{ Exception -> 0x026f }
            com.alipay.security.mobile.module.http.v2.a r8 = com.alipay.security.mobile.module.http.d.b(r8, r0)     // Catch:{ Exception -> 0x026f }
            android.content.Context r0 = r7.f848a     // Catch:{ Exception -> 0x026f }
            r1 = 0
            java.lang.String r4 = "connectivity"
            java.lang.Object r4 = r0.getSystemService(r4)     // Catch:{ Exception -> 0x026f }
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch:{ Exception -> 0x026f }
            if (r4 == 0) goto L_0x0236
            android.net.NetworkInfo r1 = r4.getActiveNetworkInfo()     // Catch:{ Exception -> 0x026f }
        L_0x0236:
            if (r1 == 0) goto L_0x0245
            boolean r4 = r1.isConnected()     // Catch:{ Exception -> 0x026f }
            if (r4 == 0) goto L_0x0245
            int r1 = r1.getType()     // Catch:{ Exception -> 0x026f }
            if (r1 != r3) goto L_0x0245
            r2 = 1
        L_0x0245:
            if (r2 == 0) goto L_0x0273
            boolean r1 = com.alipay.apmobilesecuritysdk.e.h.c(r0)     // Catch:{ Exception -> 0x026f }
            if (r1 == 0) goto L_0x0273
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x026f }
            r1.<init>()     // Catch:{ Exception -> 0x026f }
            java.io.File r0 = r0.getFilesDir()     // Catch:{ Exception -> 0x026f }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Exception -> 0x026f }
            r1.append(r0)     // Catch:{ Exception -> 0x026f }
            java.lang.String r0 = "/log/ap"
            r1.append(r0)     // Catch:{ Exception -> 0x026f }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x026f }
            com.alipay.security.mobile.module.d.b r1 = new com.alipay.security.mobile.module.d.b     // Catch:{ Exception -> 0x026f }
            r1.<init>(r0, r8)     // Catch:{ Exception -> 0x026f }
            r1.a()     // Catch:{ Exception -> 0x026f }
            goto L_0x0273
        L_0x026f:
            r8 = move-exception
            com.alipay.apmobilesecuritysdk.c.a.a((java.lang.Throwable) r8)
        L_0x0273:
            int r8 = r7.c
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }
}
