package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static String f8996a;
    private static String b;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        if (!AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            x.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return null;
        }
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (str == null) {
                return str;
            }
            try {
                return str.toLowerCase();
            } catch (Throwable unused) {
                x.a("Failed to get IMEI.", new Object[0]);
                return str;
            }
        } catch (Throwable unused2) {
            str = null;
            x.a("Failed to get IMEI.", new Object[0]);
            return str;
        }
    }

    public static String b(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        if (!AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            x.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return null;
        }
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (str == null) {
                return str;
            }
            try {
                return str.toLowerCase();
            } catch (Throwable unused) {
                x.a("Failed to get IMSI.", new Object[0]);
                return str;
            }
        } catch (Throwable unused2) {
            str = null;
            x.a("Failed to get IMSI.", new Object[0]);
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(android.content.Context r3) {
        /*
            java.lang.String r0 = "fail"
            if (r3 != 0) goto L_0x0005
            return r0
        L_0x0005:
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch:{ Throwable -> 0x001e }
            java.lang.String r1 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r3, r1)     // Catch:{ Throwable -> 0x001e }
            if (r3 != 0) goto L_0x0019
            java.lang.String r0 = "null"
            goto L_0x002d
        L_0x0014:
            r0 = move-exception
            r2 = r0
            r0 = r3
            r3 = r2
            goto L_0x001f
        L_0x0019:
            java.lang.String r0 = r3.toLowerCase()     // Catch:{ Throwable -> 0x0014 }
            goto L_0x002d
        L_0x001e:
            r3 = move-exception
        L_0x001f:
            boolean r3 = com.tencent.bugly.proguard.x.a(r3)
            if (r3 != 0) goto L_0x002d
            java.lang.String r3 = "Failed to get Android ID."
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.a(r3, r1)
        L_0x002d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.c(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d(android.content.Context r3) {
        /*
            java.lang.String r0 = "fail"
            if (r3 != 0) goto L_0x0005
            return r0
        L_0x0005:
            java.lang.String r1 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch:{ Throwable -> 0x0028 }
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3     // Catch:{ Throwable -> 0x0028 }
            if (r3 == 0) goto L_0x0032
            android.net.wifi.WifiInfo r3 = r3.getConnectionInfo()     // Catch:{ Throwable -> 0x0028 }
            if (r3 == 0) goto L_0x0032
            java.lang.String r3 = r3.getMacAddress()     // Catch:{ Throwable -> 0x0028 }
            if (r3 != 0) goto L_0x0023
            java.lang.String r0 = "null"
            goto L_0x0032
        L_0x001e:
            r0 = move-exception
            r2 = r0
            r0 = r3
            r3 = r2
            goto L_0x0029
        L_0x0023:
            java.lang.String r0 = r3.toLowerCase()     // Catch:{ Throwable -> 0x001e }
            goto L_0x0032
        L_0x0028:
            r3 = move-exception
        L_0x0029:
            boolean r1 = com.tencent.bugly.proguard.x.a(r3)
            if (r1 != 0) goto L_0x0032
            r3.printStackTrace()
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.d(android.content.Context):java.lang.String");
    }

    private static boolean o() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x007b A[Catch:{ all -> 0x009f }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0080 A[SYNTHETIC, Splitter:B:46:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0090 A[SYNTHETIC, Splitter:B:53:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a2 A[SYNTHETIC, Splitter:B:62:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00b2 A[SYNTHETIC, Splitter:B:69:0x00b2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String p() {
        /*
            java.lang.String r0 = "/system/build.prop"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0072, all -> 0x006d }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x006d }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x006a, all -> 0x0065 }
        L_0x000f:
            java.lang.String r3 = r0.readLine()     // Catch:{ Throwable -> 0x0063 }
            r4 = 0
            if (r3 == 0) goto L_0x003b
            java.lang.String r5 = "="
            r6 = 2
            java.lang.String[] r3 = r3.split(r5, r6)     // Catch:{ Throwable -> 0x0063 }
            int r5 = r3.length     // Catch:{ Throwable -> 0x0063 }
            if (r5 != r6) goto L_0x000f
            r5 = r3[r4]     // Catch:{ Throwable -> 0x0063 }
            java.lang.String r6 = "ro.product.cpu.abilist"
            boolean r5 = r5.equals(r6)     // Catch:{ Throwable -> 0x0063 }
            r6 = 1
            if (r5 == 0) goto L_0x002e
            r3 = r3[r6]     // Catch:{ Throwable -> 0x0063 }
            goto L_0x003c
        L_0x002e:
            r5 = r3[r4]     // Catch:{ Throwable -> 0x0063 }
            java.lang.String r7 = "ro.product.cpu.abi"
            boolean r5 = r5.equals(r7)     // Catch:{ Throwable -> 0x0063 }
            if (r5 == 0) goto L_0x000f
            r3 = r3[r6]     // Catch:{ Throwable -> 0x0063 }
            goto L_0x003c
        L_0x003b:
            r3 = r1
        L_0x003c:
            if (r3 == 0) goto L_0x0046
            java.lang.String r5 = ","
            java.lang.String[] r3 = r3.split(r5)     // Catch:{ Throwable -> 0x0063 }
            r3 = r3[r4]     // Catch:{ Throwable -> 0x0063 }
        L_0x0046:
            r0.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x0054
        L_0x004a:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0054
            r0.printStackTrace()
        L_0x0054:
            r2.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x0062
        L_0x0058:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0062
            r0.printStackTrace()
        L_0x0062:
            return r3
        L_0x0063:
            r3 = move-exception
            goto L_0x0075
        L_0x0065:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x00a0
        L_0x006a:
            r3 = move-exception
            r0 = r1
            goto L_0x0075
        L_0x006d:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x00a0
        L_0x0072:
            r3 = move-exception
            r0 = r1
            r2 = r0
        L_0x0075:
            boolean r4 = com.tencent.bugly.proguard.x.a(r3)     // Catch:{ all -> 0x009f }
            if (r4 != 0) goto L_0x007e
            r3.printStackTrace()     // Catch:{ all -> 0x009f }
        L_0x007e:
            if (r0 == 0) goto L_0x008e
            r0.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x008e
        L_0x0084:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x008e
            r0.printStackTrace()
        L_0x008e:
            if (r2 == 0) goto L_0x009e
            r2.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x009e
        L_0x0094:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x009e
            r0.printStackTrace()
        L_0x009e:
            return r1
        L_0x009f:
            r1 = move-exception
        L_0x00a0:
            if (r0 == 0) goto L_0x00b0
            r0.close()     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00b0
        L_0x00a6:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x00b0
            r0.printStackTrace()
        L_0x00b0:
            if (r2 == 0) goto L_0x00c0
            r2.close()     // Catch:{ IOException -> 0x00b6 }
            goto L_0x00c0
        L_0x00b6:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x00c0
            r0.printStackTrace()
        L_0x00c0:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.p():java.lang.String");
    }

    public static String a(boolean z) {
        String str = null;
        if (z) {
            try {
                str = p();
            } catch (Throwable th) {
                if (x.a(th)) {
                    return "fail";
                }
                th.printStackTrace();
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return str;
    }

    public static long d() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static long e() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x008d A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0092 A[SYNTHETIC, Splitter:B:51:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a2 A[SYNTHETIC, Splitter:B:58:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b6 A[SYNTHETIC, Splitter:B:66:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00c6 A[SYNTHETIC, Splitter:B:73:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long f() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0083, all -> 0x007e }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0083, all -> 0x007e }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0079, all -> 0x0074 }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0079, all -> 0x0074 }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x0072 }
            if (r1 != 0) goto L_0x0034
            r0.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0023
        L_0x0019:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0023
            r0.printStackTrace()
        L_0x0023:
            r2.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0031
        L_0x0027:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0031
            r0.printStackTrace()
        L_0x0031:
            r0 = -1
            return r0
        L_0x0034:
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r1 = r1.split(r3, r4)     // Catch:{ Throwable -> 0x0072 }
            r3 = 1
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r3 = "kb"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.replace(r3, r4)     // Catch:{ Throwable -> 0x0072 }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0072 }
            long r3 = java.lang.Long.parseLong(r1)     // Catch:{ Throwable -> 0x0072 }
            r1 = 10
            long r3 = r3 << r1
            r0.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0063
        L_0x0059:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0063
            r0.printStackTrace()
        L_0x0063:
            r2.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x0071
        L_0x0067:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0071
            r0.printStackTrace()
        L_0x0071:
            return r3
        L_0x0072:
            r1 = move-exception
            goto L_0x0087
        L_0x0074:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00b4
        L_0x0079:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0087
        L_0x007e:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x00b4
        L_0x0083:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x0087:
            boolean r3 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x00b3 }
            if (r3 != 0) goto L_0x0090
            r1.printStackTrace()     // Catch:{ all -> 0x00b3 }
        L_0x0090:
            if (r0 == 0) goto L_0x00a0
            r0.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x00a0
        L_0x0096:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00a0
            r0.printStackTrace()
        L_0x00a0:
            if (r2 == 0) goto L_0x00b0
            r2.close()     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00b0
        L_0x00a6:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00b0
            r0.printStackTrace()
        L_0x00b0:
            r0 = -2
            return r0
        L_0x00b3:
            r1 = move-exception
        L_0x00b4:
            if (r0 == 0) goto L_0x00c4
            r0.close()     // Catch:{ IOException -> 0x00ba }
            goto L_0x00c4
        L_0x00ba:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x00c4
            r0.printStackTrace()
        L_0x00c4:
            if (r2 == 0) goto L_0x00d4
            r2.close()     // Catch:{ IOException -> 0x00ca }
            goto L_0x00d4
        L_0x00ca:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x00d4
            r0.printStackTrace()
        L_0x00d4:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.f():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x013e A[SYNTHETIC, Splitter:B:102:0x013e] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x014e A[SYNTHETIC, Splitter:B:109:0x014e] */
    /* JADX WARNING: Removed duplicated region for block: B:117:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0115 A[Catch:{ all -> 0x013b }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x011a A[SYNTHETIC, Splitter:B:87:0x011a] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x012a A[SYNTHETIC, Splitter:B:94:0x012a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long g() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x010b, all -> 0x0106 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x010b, all -> 0x0106 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0101, all -> 0x00fc }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0101, all -> 0x00fc }
            r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            r3 = -1
            if (r1 != 0) goto L_0x0037
            r0.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x0028
        L_0x001e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0028
            r0.printStackTrace()
        L_0x0028:
            r2.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0036
        L_0x002c:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0036
            r0.printStackTrace()
        L_0x0036:
            return r3
        L_0x0037:
            java.lang.String r5 = ":\\s+"
            r6 = 2
            java.lang.String[] r1 = r1.split(r5, r6)     // Catch:{ Throwable -> 0x00fa }
            r5 = 1
            r1 = r1[r5]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r7 = "kb"
            java.lang.String r8 = ""
            java.lang.String r1 = r1.replace(r7, r8)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x00fa }
            r7 = 0
            long r9 = java.lang.Long.parseLong(r1)     // Catch:{ Throwable -> 0x00fa }
            r1 = 10
            long r9 = r9 << r1
            long r9 = r9 + r7
            java.lang.String r7 = r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            if (r7 != 0) goto L_0x007e
            r0.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x006f
        L_0x0065:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x006f
            r0.printStackTrace()
        L_0x006f:
            r2.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x007d
        L_0x0073:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x007d
            r0.printStackTrace()
        L_0x007d:
            return r3
        L_0x007e:
            java.lang.String r8 = ":\\s+"
            java.lang.String[] r7 = r7.split(r8, r6)     // Catch:{ Throwable -> 0x00fa }
            r7 = r7[r5]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r7 = r7.toLowerCase()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r8 = "kb"
            java.lang.String r11 = ""
            java.lang.String r7 = r7.replace(r8, r11)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r7 = r7.trim()     // Catch:{ Throwable -> 0x00fa }
            long r7 = java.lang.Long.parseLong(r7)     // Catch:{ Throwable -> 0x00fa }
            long r7 = r7 << r1
            long r9 = r9 + r7
            java.lang.String r7 = r0.readLine()     // Catch:{ Throwable -> 0x00fa }
            if (r7 != 0) goto L_0x00bf
            r0.close()     // Catch:{ IOException -> 0x00a6 }
            goto L_0x00b0
        L_0x00a6:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00b0
            r0.printStackTrace()
        L_0x00b0:
            r2.close()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00be
        L_0x00b4:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00be
            r0.printStackTrace()
        L_0x00be:
            return r3
        L_0x00bf:
            java.lang.String r3 = ":\\s+"
            java.lang.String[] r3 = r7.split(r3, r6)     // Catch:{ Throwable -> 0x00fa }
            r3 = r3[r5]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r4 = "kb"
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r3 = r3.trim()     // Catch:{ Throwable -> 0x00fa }
            long r3 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x00fa }
            long r3 = r3 << r1
            long r9 = r9 + r3
            r0.close()     // Catch:{ IOException -> 0x00e1 }
            goto L_0x00eb
        L_0x00e1:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00eb
            r0.printStackTrace()
        L_0x00eb:
            r2.close()     // Catch:{ IOException -> 0x00ef }
            goto L_0x00f9
        L_0x00ef:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00f9
            r0.printStackTrace()
        L_0x00f9:
            return r9
        L_0x00fa:
            r1 = move-exception
            goto L_0x010f
        L_0x00fc:
            r0 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
            goto L_0x013c
        L_0x0101:
            r0 = move-exception
            r12 = r1
            r1 = r0
            r0 = r12
            goto L_0x010f
        L_0x0106:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
            goto L_0x013c
        L_0x010b:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x010f:
            boolean r3 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x013b }
            if (r3 != 0) goto L_0x0118
            r1.printStackTrace()     // Catch:{ all -> 0x013b }
        L_0x0118:
            if (r0 == 0) goto L_0x0128
            r0.close()     // Catch:{ IOException -> 0x011e }
            goto L_0x0128
        L_0x011e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0128
            r0.printStackTrace()
        L_0x0128:
            if (r2 == 0) goto L_0x0138
            r2.close()     // Catch:{ IOException -> 0x012e }
            goto L_0x0138
        L_0x012e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0138
            r0.printStackTrace()
        L_0x0138:
            r0 = -2
            return r0
        L_0x013b:
            r1 = move-exception
        L_0x013c:
            if (r0 == 0) goto L_0x014c
            r0.close()     // Catch:{ IOException -> 0x0142 }
            goto L_0x014c
        L_0x0142:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x014c
            r0.printStackTrace()
        L_0x014c:
            if (r2 == 0) goto L_0x015c
            r2.close()     // Catch:{ IOException -> 0x0152 }
            goto L_0x015c
        L_0x0152:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x015c
            r0.printStackTrace()
        L_0x015c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.g():long");
    }

    public static long h() {
        if (!o()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    public static long i() {
        if (!o()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    public static String j() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String k() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String e(Context context) {
        TelephonyManager telephonyManager;
        String str = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                int networkType = telephonyManager.getNetworkType();
                switch (networkType) {
                    case 1:
                        return "GPRS";
                    case 2:
                        return "EDGE";
                    case 3:
                        return "UMTS";
                    case 4:
                        return "CDMA";
                    case 5:
                        return "EVDO_0";
                    case 6:
                        return "EVDO_A";
                    case 7:
                        return "1xRTT";
                    case 8:
                        return "HSDPA";
                    case 9:
                        return "HSUPA";
                    case 10:
                        return "HSPA";
                    case 11:
                        return "iDen";
                    case 12:
                        return "EVDO_B";
                    case 13:
                        return "LTE";
                    case 14:
                        return "eHRPD";
                    case 15:
                        return "HSPA+";
                    default:
                        str = "MOBILE(" + networkType + Operators.BRACKET_END_STR;
                        break;
                }
            }
            return str;
        } catch (Exception e) {
            if (!x.a(e)) {
                e.printStackTrace();
            }
        }
    }

    public static String f(Context context) {
        String a2 = z.a(context, "ro.miui.ui.version.name");
        if (z.a(a2) || a2.equals("fail")) {
            String a3 = z.a(context, "ro.build.version.emui");
            if (z.a(a3) || a3.equals("fail")) {
                String a4 = z.a(context, "ro.lenovo.series");
                if (z.a(a4) || a4.equals("fail")) {
                    String a5 = z.a(context, "ro.build.nubia.rom.name");
                    if (z.a(a5) || a5.equals("fail")) {
                        String a6 = z.a(context, "ro.meizu.product.model");
                        if (z.a(a6) || a6.equals("fail")) {
                            String a7 = z.a(context, "ro.build.version.opporom");
                            if (z.a(a7) || a7.equals("fail")) {
                                String a8 = z.a(context, "ro.vivo.os.build.display.id");
                                if (z.a(a8) || a8.equals("fail")) {
                                    String a9 = z.a(context, "ro.aa.romver");
                                    if (z.a(a9) || a9.equals("fail")) {
                                        String a10 = z.a(context, "ro.lewa.version");
                                        if (z.a(a10) || a10.equals("fail")) {
                                            String a11 = z.a(context, "ro.gn.gnromvernumber");
                                            if (z.a(a11) || a11.equals("fail")) {
                                                String a12 = z.a(context, "ro.build.tyd.kbstyle_version");
                                                if (z.a(a12) || a12.equals("fail")) {
                                                    return z.a(context, "ro.build.fingerprint") + "/" + z.a(context, "ro.build.rom.id");
                                                }
                                                return "dido/" + a12;
                                            }
                                            return "amigo/" + a11 + "/" + z.a(context, "ro.build.display.id");
                                        }
                                        return "tcl/" + a10 + "/" + z.a(context, "ro.build.display.id");
                                    }
                                    return "htc/" + a9 + "/" + z.a(context, "ro.build.description");
                                }
                                return "vivo/FUNTOUCH/" + a8;
                            }
                            return "Oppo/COLOROS/" + a7;
                        }
                        return "Meizu/FLYME/" + z.a(context, "ro.build.display.id");
                    }
                    return "Zte/NUBIA/" + a5 + JSMethod.NOT_SET + z.a(context, "ro.build.nubia.rom.code");
                }
                String a13 = z.a(context, "ro.build.version.incremental");
                return "Lenovo/VIBE/" + a13;
            }
            return "HuaWei/EMOTION/" + a3;
        }
        return "XiaoMi/MIUI/" + a2;
    }

    public static String g(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static boolean h(Context context) {
        boolean z;
        boolean z2;
        try {
            z = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            z = false;
        }
        Boolean bool = null;
        ArrayList<String> a2 = z.a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a2 != null && a2.size() > 0) {
            Iterator<String> it = a2.iterator();
            while (it.hasNext()) {
                String next = it.next();
                x.c(next, new Object[0]);
                if (next.contains("not found")) {
                    bool = false;
                }
            }
            if (bool == null) {
                bool = true;
            }
        }
        if (bool == null) {
            z2 = false;
        } else {
            z2 = bool.booleanValue();
        }
        Boolean valueOf = Boolean.valueOf(z2);
        if ((Build.TAGS != null && Build.TAGS.contains("test-keys")) || z || valueOf.booleanValue()) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x009c A[SYNTHETIC, Splitter:B:46:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a8 A[SYNTHETIC, Splitter:B:55:0x00a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String l() {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            r1.<init>()     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            java.lang.String r3 = "/sys/block/mmcblk0/device/type"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            if (r2 == 0) goto L_0x002c
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            java.lang.String r4 = "/sys/block/mmcblk0/device/type"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00a5, all -> 0x0097 }
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            if (r3 == 0) goto L_0x0028
            r1.append(r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
        L_0x0028:
            r2.close()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            goto L_0x002d
        L_0x002c:
            r2 = r0
        L_0x002d:
            java.lang.String r3 = ","
            r1.append(r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r4 = "/sys/block/mmcblk0/device/name"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            boolean r3 = r3.exists()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            if (r3 == 0) goto L_0x005e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r5 = "/sys/block/mmcblk0/device/name"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            if (r2 == 0) goto L_0x0054
            r1.append(r2)     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
        L_0x0054:
            r3.close()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            r2 = r3
            goto L_0x005e
        L_0x0059:
            r0 = move-exception
            r2 = r3
            goto L_0x009a
        L_0x005c:
            r2 = r3
            goto L_0x00a6
        L_0x005e:
            java.lang.String r3 = ","
            r1.append(r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r4 = "/sys/block/mmcblk0/device/cid"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            boolean r3 = r3.exists()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            if (r3 == 0) goto L_0x0086
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r5 = "/sys/block/mmcblk0/device/cid"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
            if (r2 == 0) goto L_0x0085
            r1.append(r2)     // Catch:{ Throwable -> 0x005c, all -> 0x0059 }
        L_0x0085:
            r2 = r3
        L_0x0086:
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00a6, all -> 0x0095 }
            if (r2 == 0) goto L_0x0094
            r2.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x0094:
            return r1
        L_0x0095:
            r0 = move-exception
            goto L_0x009a
        L_0x0097:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x009a:
            if (r2 == 0) goto L_0x00a4
            r2.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00a4
        L_0x00a0:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00a4:
            throw r0
        L_0x00a5:
            r2 = r0
        L_0x00a6:
            if (r2 == 0) goto L_0x00b0
            r2.close()     // Catch:{ IOException -> 0x00ac }
            goto L_0x00b0
        L_0x00ac:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00b0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.l():java.lang.String");
    }

    public static String i(Context context) {
        StringBuilder sb = new StringBuilder();
        String a2 = z.a(context, "ro.genymotion.version");
        if (a2 != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(a2);
            sb.append("\n");
        }
        String a3 = z.a(context, "androVM.vbox_dpi");
        if (a3 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(a3);
            sb.append("\n");
        }
        String a4 = z.a(context, "qemu.sf.fake_camera");
        if (a4 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(a4);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006a A[Catch:{ Throwable -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009d A[SYNTHETIC, Splitter:B:36:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad A[SYNTHETIC, Splitter:B:44:0x00ad] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String j(android.content.Context r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = f8996a
            if (r1 != 0) goto L_0x0011
            java.lang.String r1 = "ro.secure"
            java.lang.String r1 = com.tencent.bugly.proguard.z.a((android.content.Context) r5, (java.lang.String) r1)
            f8996a = r1
        L_0x0011:
            java.lang.String r1 = f8996a
            if (r1 == 0) goto L_0x0029
            java.lang.String r1 = "ro.secure"
            r0.append(r1)
            java.lang.String r1 = "|"
            r0.append(r1)
            java.lang.String r1 = f8996a
            r0.append(r1)
            java.lang.String r1 = "\n"
            r0.append(r1)
        L_0x0029:
            java.lang.String r1 = b
            if (r1 != 0) goto L_0x0035
            java.lang.String r1 = "ro.debuggable"
            java.lang.String r5 = com.tencent.bugly.proguard.z.a((android.content.Context) r5, (java.lang.String) r1)
            b = r5
        L_0x0035:
            java.lang.String r5 = b
            if (r5 == 0) goto L_0x004d
            java.lang.String r5 = "ro.debuggable"
            r0.append(r5)
            java.lang.String r5 = "|"
            r0.append(r5)
            java.lang.String r5 = b
            r0.append(r5)
            java.lang.String r5 = "\n"
            r0.append(r5)
        L_0x004d:
            r5 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0094, all -> 0x0090 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0094, all -> 0x0090 }
            java.lang.String r3 = "/proc/self/status"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0094, all -> 0x0090 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0094, all -> 0x0090 }
        L_0x005a:
            java.lang.String r5 = r1.readLine()     // Catch:{ Throwable -> 0x008e }
            if (r5 == 0) goto L_0x0068
            java.lang.String r2 = "TracerPid:"
            boolean r2 = r5.startsWith(r2)     // Catch:{ Throwable -> 0x008e }
            if (r2 == 0) goto L_0x005a
        L_0x0068:
            if (r5 == 0) goto L_0x0081
            r2 = 10
            java.lang.String r5 = r5.substring(r2)     // Catch:{ Throwable -> 0x008e }
            java.lang.String r5 = r5.trim()     // Catch:{ Throwable -> 0x008e }
            java.lang.String r2 = "tracer_pid"
            r0.append(r2)     // Catch:{ Throwable -> 0x008e }
            java.lang.String r2 = "|"
            r0.append(r2)     // Catch:{ Throwable -> 0x008e }
            r0.append(r5)     // Catch:{ Throwable -> 0x008e }
        L_0x0081:
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x008e }
            r1.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x008d:
            return r5
        L_0x008e:
            r5 = move-exception
            goto L_0x0098
        L_0x0090:
            r0 = move-exception
            r1 = r5
            r5 = r0
            goto L_0x00ab
        L_0x0094:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
        L_0x0098:
            com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x00aa }
            if (r1 == 0) goto L_0x00a5
            r1.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x00a5
        L_0x00a1:
            r5 = move-exception
            com.tencent.bugly.proguard.x.a(r5)
        L_0x00a5:
            java.lang.String r5 = r0.toString()
            return r5
        L_0x00aa:
            r5 = move-exception
        L_0x00ab:
            if (r1 == 0) goto L_0x00b5
            r1.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00b5
        L_0x00b1:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x00b5:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.j(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b1 A[SYNTHETIC, Splitter:B:42:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00bc A[SYNTHETIC, Splitter:B:49:0x00bc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m() {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r3 = "/sys/class/power_supply/ac/online"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            if (r2 == 0) goto L_0x003e
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r4 = "/sys/class/power_supply/ac/online"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            if (r1 == 0) goto L_0x0032
            java.lang.String r3 = "ac_online"
            r0.append(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            java.lang.String r3 = "|"
            r0.append(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            r0.append(r1)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
        L_0x0032:
            r2.close()     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            r1 = r2
            goto L_0x003e
        L_0x0037:
            r0 = move-exception
            r1 = r2
            goto L_0x00af
        L_0x003b:
            r1 = r2
            goto L_0x00ba
        L_0x003e:
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r3 = "/sys/class/power_supply/usb/online"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            if (r2 == 0) goto L_0x0073
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r4 = "/sys/class/power_supply/usb/online"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            if (r1 == 0) goto L_0x006f
            java.lang.String r3 = "usb_online"
            r0.append(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            java.lang.String r3 = "|"
            r0.append(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            r0.append(r1)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
        L_0x006f:
            r2.close()     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            r1 = r2
        L_0x0073:
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r3 = "/sys/class/power_supply/battery/capacity"
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            boolean r2 = r2.exists()     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            if (r2 == 0) goto L_0x00a8
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r4 = "/sys/class/power_supply/battery/capacity"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00ba, all -> 0x00ae }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            if (r1 == 0) goto L_0x00a4
            java.lang.String r3 = "battery_capacity"
            r0.append(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            java.lang.String r3 = "|"
            r0.append(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            r0.append(r1)     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
        L_0x00a4:
            r2.close()     // Catch:{ Throwable -> 0x003b, all -> 0x0037 }
            r1 = r2
        L_0x00a8:
            if (r1 == 0) goto L_0x00c4
            r1.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00c4
        L_0x00ae:
            r0 = move-exception
        L_0x00af:
            if (r1 == 0) goto L_0x00b9
            r1.close()     // Catch:{ IOException -> 0x00b5 }
            goto L_0x00b9
        L_0x00b5:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00b9:
            throw r0
        L_0x00ba:
            if (r1 == 0) goto L_0x00c4
            r1.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00c4
        L_0x00c0:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x00c4:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.m():java.lang.String");
    }

    public static String k(Context context) {
        StringBuilder sb = new StringBuilder();
        String a2 = z.a(context, "gsm.sim.state");
        if (a2 != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(a2);
        }
        sb.append("\n");
        String a3 = z.a(context, "gsm.sim.state2");
        if (a3 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(a3);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A[SYNTHETIC, Splitter:B:20:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004a A[SYNTHETIC, Splitter:B:26:0x004a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long n() {
        /*
            r0 = 0
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0039, all -> 0x0036 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0039, all -> 0x0036 }
            java.lang.String r4 = "/proc/uptime"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0039, all -> 0x0036 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0039, all -> 0x0036 }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x0034 }
            if (r1 == 0) goto L_0x002b
            java.lang.String r3 = " "
            java.lang.String[] r1 = r1.split(r3)     // Catch:{ Throwable -> 0x0034 }
            r3 = 0
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0034 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0034 }
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 / r5
            float r3 = (float) r3     // Catch:{ Throwable -> 0x0034 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ Throwable -> 0x0034 }
            float r3 = r3 - r1
            r0 = r3
        L_0x002b:
            r2.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0045
        L_0x002f:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
            goto L_0x0045
        L_0x0034:
            r1 = move-exception
            goto L_0x003d
        L_0x0036:
            r0 = move-exception
            r2 = r1
            goto L_0x0048
        L_0x0039:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L_0x003d:
            com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x0045:
            long r0 = (long) r0
            return r0
        L_0x0047:
            r0 = move-exception
        L_0x0048:
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x0052:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.n():long");
    }
}
