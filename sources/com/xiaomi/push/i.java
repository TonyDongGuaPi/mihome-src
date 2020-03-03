package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.io.File;
import org.apache.commons.cli.HelpFormatter;

public class i {

    /* renamed from: a  reason: collision with root package name */
    private static String f12792a = null;
    private static String b = "";
    private static String c;
    private static String d;
    private static final String e = String.valueOf(2);
    private static final String[] f = {HelpFormatter.f, "a-", "u-", "v-", "o-", "g-"};
    private static String g = null;
    private static volatile boolean h = false;

    private static double a(double d2) {
        int i = 1;
        while (true) {
            double d3 = (double) i;
            if (d3 >= d2) {
                return d3;
            }
            i <<= 1;
        }
    }

    private static long a(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
    }

    public static String a() {
        if (Build.VERSION.SDK_INT > 8 && Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return (String) ba.a("android.os.Build", "getSerial", (Object[]) null);
        }
        return null;
    }

    private static String a(int i) {
        return (i <= 0 || i >= f.length) ? f[0] : f[i];
    }

    public static String a(Context context) {
        String e2 = e(context);
        return "a-" + bf.b(null + e2 + null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
        r7 = com.xiaomi.push.au.a(r6).b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        if (android.text.TextUtils.isEmpty(r7) != false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        r0 = r7 + r0;
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0098, code lost:
        com.xiaomi.channel.commonutils.logger.b.b("devid rule select:" + r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ac, code lost:
        if (r7 != 3) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ae, code lost:
        c = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b1, code lost:
        c = a(r7) + com.xiaomi.push.bf.b(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ca, code lost:
        b(r6, c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        r7 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r6, boolean r7) {
        /*
            java.lang.String r0 = c
            if (r0 != 0) goto L_0x00cf
            java.lang.String r0 = e(r6)
            java.lang.String r1 = ""
            r2 = 3
            r3 = 1
            switch(r3) {
                case 1: goto L_0x0013;
                case 2: goto L_0x0044;
                case 3: goto L_0x0063;
                case 4: goto L_0x0070;
                case 5: goto L_0x0082;
                default: goto L_0x000f;
            }
        L_0x000f:
            r0 = r1
        L_0x0010:
            r7 = 1
            goto L_0x0098
        L_0x0013:
            java.lang.String r1 = ""
            boolean r4 = com.xiaomi.push.l.g()
            if (r4 != 0) goto L_0x0028
            if (r7 == 0) goto L_0x0023
            java.lang.String r7 = f(r6)
        L_0x0021:
            r1 = r7
            goto L_0x0028
        L_0x0023:
            java.lang.String r7 = q(r6)
            goto L_0x0021
        L_0x0028:
            java.lang.String r7 = a()
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 26
            if (r4 >= r5) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = 0
        L_0x0035:
            if (r4 != 0) goto L_0x0084
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 == 0) goto L_0x0084
            boolean r4 = android.text.TextUtils.isEmpty(r7)
            if (r4 != 0) goto L_0x0044
            goto L_0x0084
        L_0x0044:
            com.xiaomi.push.au r7 = com.xiaomi.push.au.a((android.content.Context) r6)
            java.lang.String r7 = r7.b()
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L_0x0063
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r7 = 2
            goto L_0x0098
        L_0x0063:
            java.lang.String r7 = p(r6)
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L_0x0070
            r0 = r7
            r7 = 3
            goto L_0x0098
        L_0x0070:
            com.xiaomi.push.au r7 = com.xiaomi.push.au.a((android.content.Context) r6)
            java.lang.String r7 = r7.c()
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 != 0) goto L_0x0082
            r0 = 4
            r0 = r7
            r7 = 4
            goto L_0x0098
        L_0x0082:
            r7 = 5
            goto L_0x0098
        L_0x0084:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r1)
            r4.append(r0)
            r4.append(r7)
            java.lang.String r0 = r4.toString()
            goto L_0x0010
        L_0x0098:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "devid rule select:"
            r1.append(r3)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.xiaomi.channel.commonutils.logger.b.b(r1)
            if (r7 != r2) goto L_0x00b1
            c = r0
            goto L_0x00ca
        L_0x00b1:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r7 = a((int) r7)
            r1.append(r7)
            java.lang.String r7 = com.xiaomi.push.bf.b(r0)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            c = r7
        L_0x00ca:
            java.lang.String r7 = c
            b(r6, r7)
        L_0x00cf:
            java.lang.String r6 = c
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.a(android.content.Context, boolean):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r3, java.lang.String r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "update vdevid = "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.b.c(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x001b
            return
        L_0x001b:
            g = r4
            r4 = 0
            boolean r0 = o(r3)     // Catch:{ IOException -> 0x0071 }
            if (r0 == 0) goto L_0x0059
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0071 }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x0071 }
            java.lang.String r2 = "/.vdevdir/"
            r0.<init>(r1, r2)     // Catch:{ IOException -> 0x0071 }
            boolean r1 = r0.exists()     // Catch:{ IOException -> 0x0071 }
            if (r1 == 0) goto L_0x003e
            boolean r1 = r0.isFile()     // Catch:{ IOException -> 0x0071 }
            if (r1 == 0) goto L_0x003e
            r0.delete()     // Catch:{ IOException -> 0x0071 }
        L_0x003e:
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0071 }
            java.lang.String r2 = ".vdevid"
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x0071 }
            com.xiaomi.push.u r0 = com.xiaomi.push.u.a(r3, r1)     // Catch:{ IOException -> 0x0071 }
            com.xiaomi.push.y.a((java.io.File) r1)     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            java.lang.String r4 = g     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            com.xiaomi.push.y.a((java.io.File) r1, (java.lang.String) r4)     // Catch:{ IOException -> 0x0056, all -> 0x0053 }
            r4 = r0
            goto L_0x0059
        L_0x0053:
            r3 = move-exception
            r4 = r0
            goto L_0x008e
        L_0x0056:
            r3 = move-exception
            r4 = r0
            goto L_0x0072
        L_0x0059:
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0071 }
            java.io.File r3 = r3.getFilesDir()     // Catch:{ IOException -> 0x0071 }
            java.lang.String r1 = ".vdevid"
            r0.<init>(r3, r1)     // Catch:{ IOException -> 0x0071 }
            java.lang.String r3 = g     // Catch:{ IOException -> 0x0071 }
            com.xiaomi.push.y.a((java.io.File) r0, (java.lang.String) r3)     // Catch:{ IOException -> 0x0071 }
            if (r4 == 0) goto L_0x008d
        L_0x006b:
            r4.a()
            goto L_0x008d
        L_0x006f:
            r3 = move-exception
            goto L_0x008e
        L_0x0071:
            r3 = move-exception
        L_0x0072:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r0.<init>()     // Catch:{ all -> 0x006f }
            java.lang.String r1 = "update vdevid failure :"
            r0.append(r1)     // Catch:{ all -> 0x006f }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x006f }
            r0.append(r3)     // Catch:{ all -> 0x006f }
            java.lang.String r3 = r0.toString()     // Catch:{ all -> 0x006f }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r3)     // Catch:{ all -> 0x006f }
            if (r4 == 0) goto L_0x008d
            goto L_0x006b
        L_0x008d:
            return
        L_0x008e:
            if (r4 == 0) goto L_0x0093
            r4.a()
        L_0x0093:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.a(android.content.Context, java.lang.String):void");
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String startsWith : f) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(17)
    public static int b() {
        Object a2;
        if (Build.VERSION.SDK_INT >= 17 && (a2 = ba.a("android.os.UserHandle", "myUserId", new Object[0])) != null) {
            return Integer.class.cast(a2).intValue();
        }
        return -1;
    }

    private static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 31) + str.charAt(i2);
        }
        return i;
    }

    public static String b(Context context) {
        try {
            return j.a(context).a();
        } catch (Exception e2) {
            b.a("failure to get gaid:" + e2.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(android.content.Context r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "write lvdd = "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.b.c(r0)
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x001b
            return
        L_0x001b:
            r5 = 0
            boolean r0 = o(r4)     // Catch:{ IOException -> 0x0099 }
            if (r0 == 0) goto L_0x008b
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0099 }
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x0099 }
            java.lang.String r2 = "/.vdevdir/"
            r0.<init>(r1, r2)     // Catch:{ IOException -> 0x0099 }
            boolean r1 = r0.exists()     // Catch:{ IOException -> 0x0099 }
            if (r1 == 0) goto L_0x003c
            boolean r1 = r0.isFile()     // Catch:{ IOException -> 0x0099 }
            if (r1 == 0) goto L_0x003c
            r0.delete()     // Catch:{ IOException -> 0x0099 }
        L_0x003c:
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0099 }
            java.lang.String r2 = ".vdevidlocal"
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x0099 }
            boolean r0 = r1.exists()     // Catch:{ IOException -> 0x0099 }
            if (r0 == 0) goto L_0x0055
            boolean r0 = r1.isFile()     // Catch:{ IOException -> 0x0099 }
            if (r0 == 0) goto L_0x0055
            java.lang.String r4 = "vdr exists, not rewrite."
            com.xiaomi.channel.commonutils.logger.b.b(r4)     // Catch:{ IOException -> 0x0099 }
            return
        L_0x0055:
            com.xiaomi.push.u r4 = com.xiaomi.push.u.a(r4, r1)     // Catch:{ IOException -> 0x0099 }
            com.xiaomi.push.y.a((java.io.File) r1)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            r5.<init>()     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            java.lang.String r0 = c     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            r5.append(r0)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            java.lang.String r0 = e     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            r5.append(r0)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            java.lang.String r0 = c     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            int r0 = b((java.lang.String) r0)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            r5.append(r0)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            com.xiaomi.push.y.a((java.io.File) r1, (java.lang.String) r5)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            java.lang.String r5 = "lvdd write succ."
            com.xiaomi.channel.commonutils.logger.b.b(r5)     // Catch:{ IOException -> 0x0086, all -> 0x0081 }
            goto L_0x0091
        L_0x0081:
            r5 = move-exception
            r3 = r5
            r5 = r4
            r4 = r3
            goto L_0x00b8
        L_0x0086:
            r5 = move-exception
            r3 = r5
            r5 = r4
            r4 = r3
            goto L_0x009a
        L_0x008b:
            java.lang.String r4 = "not support write lvdd."
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r4)     // Catch:{ IOException -> 0x0099 }
            r4 = r5
        L_0x0091:
            if (r4 == 0) goto L_0x00b7
            r4.a()
            goto L_0x00b7
        L_0x0097:
            r4 = move-exception
            goto L_0x00b8
        L_0x0099:
            r4 = move-exception
        L_0x009a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r0.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r1 = "write lvdd failure :"
            r0.append(r1)     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0097 }
            r0.append(r4)     // Catch:{ all -> 0x0097 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0097 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r4)     // Catch:{ all -> 0x0097 }
            if (r5 == 0) goto L_0x00b7
            r5.a()
        L_0x00b7:
            return
        L_0x00b8:
            if (r5 == 0) goto L_0x00bd
            r5.a()
        L_0x00bd:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.b(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006b A[SYNTHETIC, Splitter:B:24:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c() {
        /*
            java.lang.String r0 = "0"
            java.lang.String r1 = "/proc/meminfo"
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x0075
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x0067 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0067 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0067 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r1.<init>(r3, r4)     // Catch:{ Exception -> 0x0067 }
            java.lang.String r2 = r1.readLine()     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            if (r3 != 0) goto L_0x005c
            java.lang.String r3 = "\\s+"
            java.lang.String[] r2 = r2.split(r3)     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            if (r2 == 0) goto L_0x005c
            int r3 = r2.length     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            r4 = 2
            if (r3 < r4) goto L_0x005c
            r0 = 1
            r0 = r2[r0]     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            java.lang.Double r0 = java.lang.Double.valueOf(r0)     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            double r2 = r0.doubleValue()     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            r4 = 4652218415073722368(0x4090000000000000, double:1024.0)
            double r2 = r2 / r4
            double r2 = r2 / r4
            r4 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x004b
            double r2 = java.lang.Math.ceil(r2)     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
        L_0x004b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            r0.<init>()     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            r0.append(r2)     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            java.lang.String r2 = ""
            r0.append(r2)     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0062, all -> 0x0060 }
        L_0x005c:
            r1.close()     // Catch:{ IOException -> 0x0075 }
            goto L_0x0075
        L_0x0060:
            r0 = move-exception
            goto L_0x006f
        L_0x0062:
            r2 = r1
            goto L_0x0067
        L_0x0064:
            r0 = move-exception
            r1 = r2
            goto L_0x006f
        L_0x0067:
            java.lang.String r0 = "0"
            if (r2 == 0) goto L_0x0075
            r2.close()     // Catch:{ IOException -> 0x0075 }
            goto L_0x0075
        L_0x006f:
            if (r1 == 0) goto L_0x0074
            r1.close()     // Catch:{ IOException -> 0x0074 }
        L_0x0074:
            throw r0
        L_0x0075:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "GB"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.c():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(android.content.Context r4) {
        /*
            boolean r0 = o(r4)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r0 = g
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0013
            java.lang.String r4 = g
            return r4
        L_0x0013:
            java.io.File r0 = new java.io.File
            java.io.File r2 = r4.getFilesDir()
            java.lang.String r3 = ".vdevid"
            r0.<init>(r2, r3)
            java.lang.String r0 = com.xiaomi.push.y.b(r0)
            g = r0
            java.lang.String r0 = g
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x002f
            java.lang.String r4 = g
            return r4
        L_0x002f:
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0061 }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x0061 }
            java.lang.String r3 = "/.vdevdir/"
            r0.<init>(r2, r3)     // Catch:{ IOException -> 0x0061 }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0061 }
            java.lang.String r3 = ".vdevid"
            r2.<init>(r0, r3)     // Catch:{ IOException -> 0x0061 }
            com.xiaomi.push.u r4 = com.xiaomi.push.u.a(r4, r2)     // Catch:{ IOException -> 0x0061 }
            java.lang.String r0 = ""
            g = r0     // Catch:{ IOException -> 0x005c, all -> 0x0059 }
            java.lang.String r0 = com.xiaomi.push.y.b(r2)     // Catch:{ IOException -> 0x005c, all -> 0x0059 }
            if (r0 == 0) goto L_0x0051
            g = r0     // Catch:{ IOException -> 0x005c, all -> 0x0059 }
        L_0x0051:
            java.lang.String r0 = g     // Catch:{ IOException -> 0x005c, all -> 0x0059 }
            if (r4 == 0) goto L_0x0058
            r4.a()
        L_0x0058:
            return r0
        L_0x0059:
            r0 = move-exception
            r1 = r4
            goto L_0x0082
        L_0x005c:
            r0 = move-exception
            r1 = r4
            goto L_0x0062
        L_0x005f:
            r0 = move-exception
            goto L_0x0082
        L_0x0061:
            r0 = move-exception
        L_0x0062:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r4.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r2 = "getVDevID failure :"
            r4.append(r2)     // Catch:{ all -> 0x005f }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x005f }
            r4.append(r0)     // Catch:{ all -> 0x005f }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x005f }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r4)     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x007f
            r1.a()
        L_0x007f:
            java.lang.String r4 = g
            return r4
        L_0x0082:
            if (r1 == 0) goto L_0x0087
            r1.a()
        L_0x0087:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.c(android.content.Context):java.lang.String");
    }

    private static boolean c(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 15 && str.length() >= 14 && bf.e(str) && !bf.f(str);
    }

    public static String d() {
        double a2 = (double) a(Environment.getDataDirectory());
        Double.isNaN(a2);
        double a3 = a(((a2 / 1024.0d) / 1024.0d) / 1024.0d);
        return a3 + ServerCompact.i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d(android.content.Context r6) {
        /*
            boolean r0 = o(r6)
            r1 = 0
            if (r0 == 0) goto L_0x00cd
            boolean r0 = h
            if (r0 == 0) goto L_0x000d
            goto L_0x00cd
        L_0x000d:
            r0 = 1
            h = r0
            java.io.File r0 = new java.io.File
            java.io.File r2 = r6.getFilesDir()
            java.lang.String r3 = ".vdevid"
            r0.<init>(r2, r3)
            java.lang.String r0 = com.xiaomi.push.y.b(r0)
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0045, all -> 0x0041 }
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x0045, all -> 0x0041 }
            java.lang.String r4 = "/.vdevdir/"
            r2.<init>(r3, r4)     // Catch:{ IOException -> 0x0045, all -> 0x0041 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0045, all -> 0x0041 }
            java.lang.String r4 = ".vdevid"
            r3.<init>(r2, r4)     // Catch:{ IOException -> 0x0045, all -> 0x0041 }
            com.xiaomi.push.u r2 = com.xiaomi.push.u.a(r6, r3)     // Catch:{ IOException -> 0x0045, all -> 0x0041 }
            java.lang.String r3 = com.xiaomi.push.y.b(r3)     // Catch:{ IOException -> 0x003f }
            if (r2 == 0) goto L_0x0065
            r2.a()
            goto L_0x0065
        L_0x003f:
            r3 = move-exception
            goto L_0x0047
        L_0x0041:
            r6 = move-exception
            r2 = r1
            goto L_0x00c7
        L_0x0045:
            r3 = move-exception
            r2 = r1
        L_0x0047:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c6 }
            r4.<init>()     // Catch:{ all -> 0x00c6 }
            java.lang.String r5 = "check id failure :"
            r4.append(r5)     // Catch:{ all -> 0x00c6 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x00c6 }
            r4.append(r3)     // Catch:{ all -> 0x00c6 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x00c6 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r3)     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x0064
            r2.a()
        L_0x0064:
            r3 = r1
        L_0x0065:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x00be
            g = r0
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L_0x0088
            int r2 = r3.length()
            r4 = 128(0x80, float:1.794E-43)
            if (r2 <= r4) goto L_0x007c
            goto L_0x0088
        L_0x007c:
            boolean r6 = android.text.TextUtils.equals(r0, r3)
            if (r6 != 0) goto L_0x009f
            java.lang.String r6 = "vid changed, need sync"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r6)
            return r3
        L_0x0088:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "recover vid :"
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r2)
            a((android.content.Context) r6, (java.lang.String) r0)
        L_0x009f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "vdevid = "
            r6.append(r0)
            java.lang.String r0 = g
            r6.append(r0)
            java.lang.String r0 = " "
            r6.append(r0)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            com.xiaomi.channel.commonutils.logger.b.c(r6)
            return r1
        L_0x00be:
            java.lang.String r6 = "empty local vid"
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r6)
            java.lang.String r6 = "F*"
            return r6
        L_0x00c6:
            r6 = move-exception
        L_0x00c7:
            if (r2 == 0) goto L_0x00cc
            r2.a()
        L_0x00cc:
            throw r6
        L_0x00cd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.d(android.content.Context):java.lang.String");
    }

    private static int e() {
        return Build.VERSION.SDK_INT < 29 ? 10 : 0;
    }

    public static String e(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static String f(Context context) {
        int e2 = e();
        String g2 = g(context);
        while (g2 == null) {
            int i = e2 - 1;
            if (e2 <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
            g2 = g(context);
            e2 = i;
        }
        return g2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r1 = com.xiaomi.push.ba.a((r1 = com.xiaomi.push.ba.a("miui.telephony.TelephonyManager", "getDefault", new java.lang.Object[0])), "getMiuiDeviceId", new java.lang.Object[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String g(android.content.Context r5) {
        /*
            boolean r0 = com.xiaomi.push.l.g()
            if (r0 == 0) goto L_0x0009
            java.lang.String r5 = ""
            return r5
        L_0x0009:
            java.lang.String r0 = f12792a
            if (r0 == 0) goto L_0x0010
            java.lang.String r5 = f12792a
            return r5
        L_0x0010:
            r0 = 0
            boolean r1 = com.xiaomi.push.l.a()     // Catch:{ Throwable -> 0x0088 }
            if (r1 == 0) goto L_0x003b
            java.lang.String r1 = "miui.telephony.TelephonyManager"
            java.lang.String r2 = "getDefault"
            r3 = 0
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0088 }
            java.lang.Object r1 = com.xiaomi.push.ba.a((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ Throwable -> 0x0088 }
            if (r1 == 0) goto L_0x003b
            java.lang.String r2 = "getMiuiDeviceId"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0088 }
            java.lang.Object r1 = com.xiaomi.push.ba.a((java.lang.Object) r1, (java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ Throwable -> 0x0088 }
            if (r1 == 0) goto L_0x003b
            boolean r2 = r1 instanceof java.lang.String     // Catch:{ Throwable -> 0x0088 }
            if (r2 == 0) goto L_0x003b
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            java.lang.Object r1 = r2.cast(r1)     // Catch:{ Throwable -> 0x0088 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0088 }
            goto L_0x003c
        L_0x003b:
            r1 = r0
        L_0x003c:
            if (r1 != 0) goto L_0x007c
            boolean r2 = r(r5)     // Catch:{ Throwable -> 0x0088 }
            if (r2 == 0) goto L_0x007c
            java.lang.String r2 = "phone"
            java.lang.Object r5 = r5.getSystemService(r2)     // Catch:{ Throwable -> 0x0088 }
            android.telephony.TelephonyManager r5 = (android.telephony.TelephonyManager) r5     // Catch:{ Throwable -> 0x0088 }
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0088 }
            r3 = 26
            if (r2 >= r3) goto L_0x0057
            java.lang.String r1 = r5.getDeviceId()     // Catch:{ Throwable -> 0x0088 }
            goto L_0x007c
        L_0x0057:
            r2 = 1
            int r3 = r5.getPhoneType()     // Catch:{ Throwable -> 0x0088 }
            if (r2 != r3) goto L_0x006b
            java.lang.String r1 = "getImei"
            r2 = r0
            java.lang.Object[] r2 = (java.lang.Object[]) r2     // Catch:{ Throwable -> 0x0088 }
            java.lang.Object r5 = com.xiaomi.push.ba.a((java.lang.Object) r5, (java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x0088 }
        L_0x0067:
            r1 = r5
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0088 }
            goto L_0x007c
        L_0x006b:
            r2 = 2
            int r3 = r5.getPhoneType()     // Catch:{ Throwable -> 0x0088 }
            if (r2 != r3) goto L_0x007c
            java.lang.String r1 = "getMeid"
            r2 = r0
            java.lang.Object[] r2 = (java.lang.Object[]) r2     // Catch:{ Throwable -> 0x0088 }
            java.lang.Object r5 = com.xiaomi.push.ba.a((java.lang.Object) r5, (java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ Throwable -> 0x0088 }
            goto L_0x0067
        L_0x007c:
            boolean r5 = c((java.lang.String) r1)     // Catch:{ Throwable -> 0x0088 }
            if (r5 == 0) goto L_0x0085
            f12792a = r1     // Catch:{ Throwable -> 0x0088 }
            return r1
        L_0x0085:
            java.lang.String r5 = ""
            return r5
        L_0x0088:
            r5 = move-exception
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.Throwable) r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.g(android.content.Context):java.lang.String");
    }

    public static String h(Context context) {
        int e2 = e();
        String j = j(context);
        while (j == null) {
            int i = e2 - 1;
            if (e2 <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
            j = j(context);
            e2 = i;
        }
        return j;
    }

    public static String i(Context context) {
        Object a2;
        if (l.g() || Build.VERSION.SDK_INT < 22) {
            return "";
        }
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (!r(context)) {
            return "";
        }
        g(context);
        if (TextUtils.isEmpty(f12792a)) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Integer num = (Integer) ba.a((Object) telephonyManager, "getPhoneCount", new Object[0]);
            if (num == null || num.intValue() <= 1) {
                return "";
            }
            String str = null;
            for (int i = 0; i < num.intValue(); i++) {
                if (Build.VERSION.SDK_INT < 26) {
                    a2 = ba.a((Object) telephonyManager, "getDeviceId", Integer.valueOf(i));
                } else if (1 == telephonyManager.getPhoneType()) {
                    a2 = ba.a((Object) telephonyManager, "getImei", Integer.valueOf(i));
                } else {
                    if (2 == telephonyManager.getPhoneType()) {
                        a2 = ba.a((Object) telephonyManager, "getMeid", Integer.valueOf(i));
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(f12792a, str) && c(str)) {
                        b += str + ",";
                    }
                }
                str = (String) a2;
                b += str + ",";
            }
            int length = b.length();
            if (length > 0) {
                b = b.substring(0, length - 1);
            }
            return b;
        } catch (Exception e2) {
            b.d(e2.toString());
            return "";
        }
    }

    public static String j(Context context) {
        i(context);
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        String str = "";
        for (String str2 : b.split(",")) {
            if (c(str2)) {
                str = str + bf.a(str2) + ",";
            }
        }
        int length = str.length();
        return length > 0 ? str.substring(0, length - 1) : str;
    }

    public static synchronized String k(Context context) {
        synchronized (i.class) {
            if (d != null) {
                String str = d;
                return str;
            }
            String e2 = e(context);
            String a2 = a();
            d = bf.b(e2 + a2);
            String str2 = d;
            return str2;
        }
    }

    public static synchronized String l(Context context) {
        String b2;
        synchronized (i.class) {
            String e2 = e(context);
            b2 = bf.b(e2 + null);
        }
        return b2;
    }

    public static String m(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static String n(Context context) {
        return "";
    }

    private static boolean o(Context context) {
        boolean z = false;
        if (!m.a(context, "android.permission.WRITE_EXTERNAL_STORAGE") || l.a()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            z = true;
        }
        return !z ? t.b(context) : z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.xiaomi.push.u} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:9|10|11|(2:13|(5:15|16|17|18|(1:20)))|21|22|(1:24)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0054 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005a A[Catch:{ IOException -> 0x0066, all -> 0x0063 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String p(android.content.Context r6) {
        /*
            boolean r0 = o(r6)
            r1 = 0
            if (r0 != 0) goto L_0x000d
            java.lang.String r6 = "not support read lvdd."
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r6)
            return r1
        L_0x000d:
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x007a }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ IOException -> 0x007a }
            java.lang.String r3 = "/.vdevdir/"
            r0.<init>(r2, r3)     // Catch:{ IOException -> 0x007a }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x007a }
            java.lang.String r3 = ".vdevidlocal"
            r2.<init>(r0, r3)     // Catch:{ IOException -> 0x007a }
            boolean r0 = r2.exists()     // Catch:{ IOException -> 0x007a }
            if (r0 == 0) goto L_0x006b
            boolean r0 = r2.isFile()     // Catch:{ IOException -> 0x007a }
            if (r0 == 0) goto L_0x006b
            com.xiaomi.push.u r6 = com.xiaomi.push.u.a(r6, r2)     // Catch:{ IOException -> 0x007a }
            java.lang.String r0 = com.xiaomi.push.y.b(r2)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            if (r3 != 0) goto L_0x0054
            java.lang.String r3 = e     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            java.lang.String[] r0 = r0.split(r3)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            int r3 = r0.length     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            r4 = 2
            if (r3 != r4) goto L_0x0054
            r3 = 0
            r3 = r0[r3]     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            r4 = 1
            r0 = r0[r4]     // Catch:{ Exception -> 0x0054 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0054 }
            int r4 = b((java.lang.String) r3)     // Catch:{ Exception -> 0x0054 }
            if (r4 != r0) goto L_0x0054
            r1 = r3
        L_0x0054:
            boolean r0 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            if (r0 == 0) goto L_0x0071
            com.xiaomi.push.y.a((java.io.File) r2)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            java.lang.String r0 = "lvdd content invalid, remove it."
            com.xiaomi.channel.commonutils.logger.b.b(r0)     // Catch:{ IOException -> 0x0066, all -> 0x0063 }
            goto L_0x0071
        L_0x0063:
            r0 = move-exception
            r1 = r6
            goto L_0x009a
        L_0x0066:
            r0 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x007c
        L_0x006b:
            java.lang.String r6 = "lvdf not exists"
            com.xiaomi.channel.commonutils.logger.b.b(r6)     // Catch:{ IOException -> 0x007a }
            r6 = r1
        L_0x0071:
            if (r6 == 0) goto L_0x0076
            r6.a()
        L_0x0076:
            r6 = r1
            goto L_0x0099
        L_0x0078:
            r0 = move-exception
            goto L_0x009a
        L_0x007a:
            r0 = move-exception
            r6 = r1
        L_0x007c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r2.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r3 = "get lvdd failure :"
            r2.append(r3)     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0078 }
            r2.append(r0)     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0078 }
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r0)     // Catch:{ all -> 0x0078 }
            if (r1 == 0) goto L_0x0099
            r1.a()
        L_0x0099:
            return r6
        L_0x009a:
            if (r1 == 0) goto L_0x009f
            r1.a()
        L_0x009f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.i.p(android.content.Context):java.lang.String");
    }

    private static String q(Context context) {
        int e2 = e();
        String g2 = g(context);
        while (TextUtils.isEmpty(g2)) {
            int i = e2 - 1;
            if (e2 <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
            g2 = g(context);
            e2 = i;
        }
        return g2;
    }

    private static boolean r(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }
}
