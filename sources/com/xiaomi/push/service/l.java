package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.push.ab;
import com.xiaomi.push.fv;
import com.xiaomi.push.i;
import com.xiaomi.push.o;
import com.xiaomi.stat.c.c;

public class l {

    /* renamed from: a  reason: collision with root package name */
    private static k f12931a;

    /* renamed from: a  reason: collision with other field name */
    private static a f347a;

    public interface a {
        void a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a7, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.xiaomi.push.service.k a(android.content.Context r12) {
        /*
            java.lang.Class<com.xiaomi.push.service.l> r0 = com.xiaomi.push.service.l.class
            monitor-enter(r0)
            com.xiaomi.push.service.k r1 = f12931a     // Catch:{ all -> 0x00a8 }
            if (r1 == 0) goto L_0x000b
            com.xiaomi.push.service.k r12 = f12931a     // Catch:{ all -> 0x00a8 }
            monitor-exit(r0)
            return r12
        L_0x000b:
            java.lang.String r1 = "mipush_account"
            r2 = 0
            android.content.SharedPreferences r1 = r12.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "uuid"
            r3 = 0
            java.lang.String r5 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "token"
            java.lang.String r6 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "security"
            java.lang.String r7 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "app_id"
            java.lang.String r8 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "app_token"
            java.lang.String r9 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "package_name"
            java.lang.String r10 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r2 = "device_id"
            java.lang.String r2 = r1.getString(r2, r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r4 = "env_type"
            r11 = 1
            int r11 = r1.getInt(r4, r11)     // Catch:{ all -> 0x00a8 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a8 }
            if (r4 != 0) goto L_0x0061
            boolean r4 = com.xiaomi.push.i.a((java.lang.String) r2)     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x0061
            java.lang.String r2 = com.xiaomi.push.i.k(r12)     // Catch:{ all -> 0x00a8 }
            android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch:{ all -> 0x00a8 }
            java.lang.String r4 = "device_id"
            android.content.SharedPreferences$Editor r1 = r1.putString(r4, r2)     // Catch:{ all -> 0x00a8 }
            r1.commit()     // Catch:{ all -> 0x00a8 }
        L_0x0061:
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00a8 }
            if (r1 != 0) goto L_0x00a6
            boolean r1 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x00a8 }
            if (r1 != 0) goto L_0x00a6
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00a8 }
            if (r1 != 0) goto L_0x00a6
            java.lang.String r1 = com.xiaomi.push.i.k(r12)     // Catch:{ all -> 0x00a8 }
            java.lang.String r3 = "com.xiaomi.xmsf"
            java.lang.String r12 = r12.getPackageName()     // Catch:{ all -> 0x00a8 }
            boolean r12 = r3.equals(r12)     // Catch:{ all -> 0x00a8 }
            if (r12 != 0) goto L_0x009a
            boolean r12 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00a8 }
            if (r12 != 0) goto L_0x009a
            boolean r12 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a8 }
            if (r12 != 0) goto L_0x009a
            boolean r12 = r2.equals(r1)     // Catch:{ all -> 0x00a8 }
            if (r12 != 0) goto L_0x009a
            java.lang.String r12 = "read_phone_state permission changes."
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r12)     // Catch:{ all -> 0x00a8 }
        L_0x009a:
            com.xiaomi.push.service.k r12 = new com.xiaomi.push.service.k     // Catch:{ all -> 0x00a8 }
            r4 = r12
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x00a8 }
            f12931a = r12     // Catch:{ all -> 0x00a8 }
            com.xiaomi.push.service.k r12 = f12931a     // Catch:{ all -> 0x00a8 }
            monitor-exit(r0)
            return r12
        L_0x00a6:
            monitor-exit(r0)
            return r3
        L_0x00a8:
            r12 = move-exception
            monitor-exit(r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.l.a(android.content.Context):com.xiaomi.push.service.k");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0242, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006d A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x007c A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0087 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0090 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0099 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ba A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c1 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ea A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x015b A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x016e A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01b6 A[Catch:{ Exception -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01c0 A[Catch:{ Exception -> 0x00b1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.xiaomi.push.service.k a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.Class<com.xiaomi.push.service.l> r0 = com.xiaomi.push.service.l.class
            monitor-enter(r0)
            java.util.TreeMap r1 = new java.util.TreeMap     // Catch:{ all -> 0x0243 }
            r1.<init>()     // Catch:{ all -> 0x0243 }
            r2 = 0
            java.lang.String r2 = com.xiaomi.push.i.a((android.content.Context) r9, (boolean) r2)     // Catch:{ all -> 0x0243 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r3.<init>()     // Catch:{ all -> 0x0243 }
            java.lang.String r4 = "account register:"
            r3.append(r4)     // Catch:{ all -> 0x0243 }
            r3.append(r2)     // Catch:{ all -> 0x0243 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0243 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r3)     // Catch:{ all -> 0x0243 }
            java.lang.String r3 = "devid"
            r1.put(r3, r2)     // Catch:{ all -> 0x0243 }
            java.lang.String r2 = "devid1"
            java.lang.String r3 = com.xiaomi.push.i.a((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            r1.put(r2, r3)     // Catch:{ all -> 0x0243 }
            com.xiaomi.push.service.k r2 = f12931a     // Catch:{ all -> 0x0243 }
            r3 = 0
            if (r2 == 0) goto L_0x005f
            com.xiaomi.push.service.k r2 = f12931a     // Catch:{ all -> 0x0243 }
            java.lang.String r2 = r2.f346a     // Catch:{ all -> 0x0243 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0243 }
            if (r2 != 0) goto L_0x005f
            java.lang.String r2 = "uuid"
            com.xiaomi.push.service.k r4 = f12931a     // Catch:{ all -> 0x0243 }
            java.lang.String r4 = r4.f346a     // Catch:{ all -> 0x0243 }
            r1.put(r2, r4)     // Catch:{ all -> 0x0243 }
            com.xiaomi.push.service.k r2 = f12931a     // Catch:{ all -> 0x0243 }
            java.lang.String r2 = r2.f346a     // Catch:{ all -> 0x0243 }
            java.lang.String r4 = "/"
            int r2 = r2.lastIndexOf(r4)     // Catch:{ all -> 0x0243 }
            r4 = -1
            if (r2 == r4) goto L_0x005f
            com.xiaomi.push.service.k r4 = f12931a     // Catch:{ all -> 0x0243 }
            java.lang.String r4 = r4.f346a     // Catch:{ all -> 0x0243 }
            int r2 = r2 + 1
            java.lang.String r2 = r4.substring(r2)     // Catch:{ all -> 0x0243 }
            goto L_0x0060
        L_0x005f:
            r2 = r3
        L_0x0060:
            com.xiaomi.push.au r4 = com.xiaomi.push.au.a((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            r4.a((java.util.Map<java.lang.String, java.lang.String>) r1)     // Catch:{ all -> 0x0243 }
            java.lang.String r4 = com.xiaomi.push.i.c((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            if (r4 == 0) goto L_0x0072
            java.lang.String r5 = "vdevid"
            r1.put(r5, r4)     // Catch:{ all -> 0x0243 }
        L_0x0072:
            java.lang.String r4 = com.xiaomi.push.i.b((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0243 }
            if (r5 != 0) goto L_0x0081
            java.lang.String r5 = "gaid"
            r1.put(r5, r4)     // Catch:{ all -> 0x0243 }
        L_0x0081:
            boolean r4 = a((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            if (r4 == 0) goto L_0x0089
            java.lang.String r11 = "1000271"
        L_0x0089:
            r5 = r11
            boolean r11 = a((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            if (r11 == 0) goto L_0x0092
            java.lang.String r12 = "420100086271"
        L_0x0092:
            r6 = r12
            boolean r11 = a((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            if (r11 == 0) goto L_0x009b
            java.lang.String r10 = "com.xiaomi.xmsf"
        L_0x009b:
            r7 = r10
            java.lang.String r10 = "appid"
            r1.put(r10, r5)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "apptoken"
            r1.put(r10, r6)     // Catch:{ all -> 0x0243 }
            android.content.pm.PackageManager r10 = r9.getPackageManager()     // Catch:{ Exception -> 0x00b1 }
            r11 = 16384(0x4000, float:2.2959E-41)
            android.content.pm.PackageInfo r10 = r10.getPackageInfo(r7, r11)     // Catch:{ Exception -> 0x00b1 }
            goto L_0x00b6
        L_0x00b1:
            r10 = move-exception
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r10)     // Catch:{ all -> 0x0243 }
            r10 = r3
        L_0x00b6:
            java.lang.String r11 = "appversion"
            if (r10 == 0) goto L_0x00c1
            int r10 = r10.versionCode     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x0243 }
            goto L_0x00c3
        L_0x00c1:
            java.lang.String r10 = "0"
        L_0x00c3:
            r1.put(r11, r10)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "sdkversion"
            r11 = 30702(0x77ee, float:4.3023E-41)
            java.lang.String r11 = java.lang.Integer.toString(r11)     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "packagename"
            r1.put(r10, r7)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "model"
            java.lang.String r11 = android.os.Build.MODEL     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "board"
            java.lang.String r11 = android.os.Build.BOARD     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            boolean r10 = com.xiaomi.push.l.g()     // Catch:{ all -> 0x0243 }
            if (r10 != 0) goto L_0x0138
            java.lang.String r10 = ""
            java.lang.String r11 = com.xiaomi.push.i.f(r9)     // Catch:{ all -> 0x0243 }
            boolean r12 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x0243 }
            if (r12 != 0) goto L_0x0109
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r12.<init>()     // Catch:{ all -> 0x0243 }
            r12.append(r10)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = com.xiaomi.push.bf.a((java.lang.String) r11)     // Catch:{ all -> 0x0243 }
            r12.append(r10)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = r12.toString()     // Catch:{ all -> 0x0243 }
        L_0x0109:
            java.lang.String r11 = com.xiaomi.push.i.h(r9)     // Catch:{ all -> 0x0243 }
            boolean r12 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0243 }
            if (r12 != 0) goto L_0x012d
            boolean r12 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x0243 }
            if (r12 != 0) goto L_0x012d
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r12.<init>()     // Catch:{ all -> 0x0243 }
            r12.append(r10)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = ","
            r12.append(r10)     // Catch:{ all -> 0x0243 }
            r12.append(r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = r12.toString()     // Catch:{ all -> 0x0243 }
        L_0x012d:
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0243 }
            if (r11 != 0) goto L_0x0138
            java.lang.String r11 = "imei_md5"
            r1.put(r11, r10)     // Catch:{ all -> 0x0243 }
        L_0x0138:
            java.lang.String r10 = "os"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r11.<init>()     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0243 }
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = "-"
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = android.os.Build.VERSION.INCREMENTAL     // Catch:{ all -> 0x0243 }
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            int r10 = com.xiaomi.push.i.b()     // Catch:{ all -> 0x0243 }
            if (r10 < 0) goto L_0x0164
            java.lang.String r11 = "space_id"
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ all -> 0x0243 }
            r1.put(r11, r10)     // Catch:{ all -> 0x0243 }
        L_0x0164:
            java.lang.String r10 = com.xiaomi.push.i.n(r9)     // Catch:{ all -> 0x0243 }
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x0243 }
            if (r11 != 0) goto L_0x0177
            java.lang.String r11 = "mac_address"
            java.lang.String r10 = com.xiaomi.push.bf.a((java.lang.String) r10)     // Catch:{ all -> 0x0243 }
            r1.put(r11, r10)     // Catch:{ all -> 0x0243 }
        L_0x0177:
            java.lang.String r10 = "android_id"
            java.lang.String r11 = com.xiaomi.push.i.e(r9)     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "brand"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r11.<init>()     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = android.os.Build.BRAND     // Catch:{ all -> 0x0243 }
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = ""
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "ram"
            java.lang.String r11 = com.xiaomi.push.i.c()     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = "rom"
            java.lang.String r11 = com.xiaomi.push.i.d()     // Catch:{ all -> 0x0243 }
            r1.put(r10, r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = a((android.content.Context) r9)     // Catch:{ all -> 0x0243 }
            com.xiaomi.push.ax r10 = com.xiaomi.push.az.a(r9, r10, r1)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = ""
            if (r10 == 0) goto L_0x01ba
            java.lang.String r11 = r10.a()     // Catch:{ all -> 0x0243 }
        L_0x01ba:
            boolean r10 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x0243 }
            if (r10 != 0) goto L_0x0241
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ all -> 0x0243 }
            r10.<init>(r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = "code"
            int r12 = r10.getInt(r12)     // Catch:{ all -> 0x0243 }
            if (r12 != 0) goto L_0x022f
            java.lang.String r11 = "data"
            org.json.JSONObject r10 = r10.getJSONObject(r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = "ssecurity"
            java.lang.String r4 = r10.getString(r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = "token"
            java.lang.String r3 = r10.getString(r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = "userId"
            java.lang.String r11 = r10.getString(r11)     // Catch:{ all -> 0x0243 }
            boolean r12 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0243 }
            if (r12 == 0) goto L_0x0201
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r12.<init>()     // Catch:{ all -> 0x0243 }
            java.lang.String r1 = "an"
            r12.append(r1)     // Catch:{ all -> 0x0243 }
            r1 = 6
            java.lang.String r1 = com.xiaomi.push.bf.a((int) r1)     // Catch:{ all -> 0x0243 }
            r12.append(r1)     // Catch:{ all -> 0x0243 }
            java.lang.String r2 = r12.toString()     // Catch:{ all -> 0x0243 }
        L_0x0201:
            com.xiaomi.push.service.k r12 = new com.xiaomi.push.service.k     // Catch:{ all -> 0x0243 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r1.<init>()     // Catch:{ all -> 0x0243 }
            r1.append(r11)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = "@xiaomi.com/"
            r1.append(r11)     // Catch:{ all -> 0x0243 }
            r1.append(r2)     // Catch:{ all -> 0x0243 }
            java.lang.String r2 = r1.toString()     // Catch:{ all -> 0x0243 }
            int r8 = com.xiaomi.push.ab.c()     // Catch:{ all -> 0x0243 }
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0243 }
            a(r9, r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = "vdevid"
            java.lang.String r10 = r10.optString(r11)     // Catch:{ all -> 0x0243 }
            com.xiaomi.push.i.a((android.content.Context) r9, (java.lang.String) r10)     // Catch:{ all -> 0x0243 }
            f12931a = r12     // Catch:{ all -> 0x0243 }
            monitor-exit(r0)
            return r12
        L_0x022f:
            java.lang.String r12 = "code"
            int r12 = r10.getInt(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r1 = "description"
            java.lang.String r10 = r10.optString(r1)     // Catch:{ all -> 0x0243 }
            com.xiaomi.push.service.o.a(r9, r12, r10)     // Catch:{ all -> 0x0243 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r11)     // Catch:{ all -> 0x0243 }
        L_0x0241:
            monitor-exit(r0)
            return r3
        L_0x0243:
            r9 = move-exception
            monitor-exit(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.l.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String):com.xiaomi.push.service.k");
    }

    /* renamed from: a  reason: collision with other method in class */
    public static String m335a(Context context) {
        StringBuilder sb;
        String str;
        String a2 = a.a(context).a();
        if (ab.b()) {
            sb = new StringBuilder();
            sb.append(ConnectionHelper.HTTP_PREFIX);
            sb.append(fv.b);
            str = ":9085";
        } else if (o.China.name().equals(a2)) {
            sb = new StringBuilder();
            str = "https://cn.register.xmpush.xiaomi.com";
        } else if (o.Global.name().equals(a2)) {
            sb = new StringBuilder();
            str = "https://register.xmpush.global.xiaomi.com";
        } else if (o.Europe.name().equals(a2)) {
            sb = new StringBuilder();
            str = "https://fr.register.xmpush.global.xiaomi.com";
        } else if (o.Russia.name().equals(a2)) {
            sb = new StringBuilder();
            str = "https://ru.register.xmpush.global.xiaomi.com";
        } else if (o.India.name().equals(a2)) {
            sb = new StringBuilder();
            str = "https://idmb.register.xmpush.global.xiaomi.com";
        } else {
            sb = new StringBuilder();
            sb.append("https://");
            str = ab.a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com";
        }
        sb.append(str);
        sb.append("/pass/v2/register");
        return sb.toString();
    }

    public static void a() {
        if (f347a != null) {
            f347a.a();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public static void m336a(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        f12931a = null;
        a();
    }

    public static void a(Context context, k kVar) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", kVar.f346a);
        edit.putString("security", kVar.c);
        edit.putString("token", kVar.b);
        edit.putString("app_id", kVar.d);
        edit.putString("package_name", kVar.f);
        edit.putString("app_token", kVar.e);
        edit.putString("device_id", i.k(context));
        edit.putInt("env_type", kVar.f12930a);
        edit.commit();
        a();
    }

    public static void a(a aVar) {
        f347a = aVar;
    }

    /* renamed from: a  reason: collision with other method in class */
    private static boolean m337a(Context context) {
        return context.getPackageName().equals(c.f23036a);
    }
}
