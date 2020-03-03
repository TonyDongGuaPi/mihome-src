package com.loc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.util.i;
import com.drew.metadata.exif.ExifDirectoryBase;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class x {

    /* renamed from: a  reason: collision with root package name */
    static String f6648a = "";
    static String b = "";
    public static boolean c = false;
    static String d = "";
    static boolean e = false;
    static int f = -1;
    static String g = "";
    static String h = "";
    private static String i = null;
    private static boolean j = false;
    private static String k = "";
    private static String l = "";
    private static String m = "";
    private static String n = "";
    private static String o = "";
    private static String p = "";
    private static boolean q = false;
    private static long r = 0;
    private static int s = 0;
    private static String t = null;
    private static String u = "";

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0099 A[SYNTHETIC, Splitter:B:45:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String A(android.content.Context r8) {
        /*
            r0 = 0
            java.lang.String r1 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r8 = com.loc.ad.a((android.content.Context) r8, (java.lang.String) r1)     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            if (r8 == 0) goto L_0x0090
            java.lang.String r8 = "mounted"
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            boolean r8 = r8.equals(r1)     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            if (r8 == 0) goto L_0x0090
            java.io.File r8 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.lang.String r8 = r8.getAbsolutePath()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            r1.<init>()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            r1.append(r8)     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.lang.String r8 = "/.UTSystemConfig/Global/Alvin2.xml"
            r1.append(r8)     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.lang.String r8 = r1.toString()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            r1.<init>(r8)     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            org.xmlpull.v1.XmlPullParser r8 = android.util.Xml.newPullParser()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            int r2 = r8.getEventType()     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            r3.<init>(r1)     // Catch:{ Throwable -> 0x009d, all -> 0x0096 }
            java.lang.String r0 = "utf-8"
            r8.setInput(r3, r0)     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            r0 = 0
            r1 = 0
        L_0x0048:
            r4 = 1
            if (r4 == r2) goto L_0x0089
            if (r2 == 0) goto L_0x0084
            switch(r2) {
                case 2: goto L_0x005d;
                case 3: goto L_0x005b;
                case 4: goto L_0x0051;
                default: goto L_0x0050;
            }     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
        L_0x0050:
            goto L_0x0084
        L_0x0051:
            if (r1 == 0) goto L_0x0084
            java.lang.String r8 = r8.getText()     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            r3.close()     // Catch:{ Throwable -> 0x005a }
        L_0x005a:
            return r8
        L_0x005b:
            r1 = 0
            goto L_0x0084
        L_0x005d:
            int r2 = r8.getAttributeCount()     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            if (r2 <= 0) goto L_0x0084
            int r2 = r8.getAttributeCount()     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            r5 = r1
            r1 = 0
        L_0x0069:
            if (r1 >= r2) goto L_0x0083
            java.lang.String r6 = r8.getAttributeValue(r1)     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            java.lang.String r7 = "UTDID2"
            boolean r7 = r7.equals(r6)     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            if (r7 != 0) goto L_0x007f
            java.lang.String r7 = "UTDID"
            boolean r6 = r7.equals(r6)     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            if (r6 == 0) goto L_0x0080
        L_0x007f:
            r5 = 1
        L_0x0080:
            int r1 = r1 + 1
            goto L_0x0069
        L_0x0083:
            r1 = r5
        L_0x0084:
            int r2 = r8.next()     // Catch:{ Throwable -> 0x008e, all -> 0x008b }
            goto L_0x0048
        L_0x0089:
            r0 = r3
            goto L_0x0090
        L_0x008b:
            r8 = move-exception
            r0 = r3
            goto L_0x0097
        L_0x008e:
            r0 = r3
            goto L_0x009d
        L_0x0090:
            if (r0 == 0) goto L_0x00a0
        L_0x0092:
            r0.close()     // Catch:{ Throwable -> 0x00a0 }
            goto L_0x00a0
        L_0x0096:
            r8 = move-exception
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            r0.close()     // Catch:{ Throwable -> 0x009c }
        L_0x009c:
            throw r8
        L_0x009d:
            if (r0 == 0) goto L_0x00a0
            goto L_0x0092
        L_0x00a0:
            java.lang.String r8 = ""
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.x.A(android.content.Context):java.lang.String");
    }

    private static String B(Context context) throws InvocationTargetException, IllegalAccessException {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (u != null && !"".equals(u)) {
            return u;
        }
        if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return u;
        }
        TelephonyManager G = G(context);
        if (G == null) {
            return "";
        }
        Method a2 = ad.a((Class) G.getClass(), "UZ2V0U3Vic2NyaWJlcklk", (Class<?>[]) new Class[0]);
        if (a2 != null) {
            u = (String) a2.invoke(G, new Object[0]);
        }
        if (u == null) {
            u = "";
        }
        return u;
    }

    private static String C(Context context) {
        if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return null;
        }
        TelephonyManager G = G(context);
        if (G == null) {
            return "";
        }
        String simOperatorName = G.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? G.getNetworkOperatorName() : simOperatorName;
    }

    private static int D(Context context) {
        ConnectivityManager E;
        NetworkInfo activeNetworkInfo;
        if (context == null || !b(context, ad.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) || (E = E(context)) == null || (activeNetworkInfo = E.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    private static ConnectivityManager E(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static int F(Context context) {
        TelephonyManager G;
        if (b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU=")) && (G = G(context)) != null) {
            return G.getNetworkType();
        }
        return -1;
    }

    private static TelephonyManager G(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public static String a() {
        return i;
    }

    public static String a(final Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String a2 = a.a(context);
        if (!TextUtils.isEmpty(a2)) {
            b = a2;
            return a2;
        } else if (j) {
            return "";
        } else {
            j = true;
            aq.d().submit(new Runnable() {
                public final void run() {
                    byte[] bArr;
                    Map<String, String> a2 = a.a();
                    if (a2 != null) {
                        String a3 = a.a(x.g(context), x.u(context), x.l(context), x.x(context));
                        if (!TextUtils.isEmpty(a3)) {
                            byte[] bArr2 = new byte[0];
                            try {
                                bg.a();
                                bArr = bg.a(new bf(a3.getBytes(), a2));
                            } catch (t e) {
                                e.printStackTrace();
                                bArr = bArr2;
                            }
                            a.a(context, new String(bArr));
                        }
                    }
                }
            });
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r7 = r1.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0083, code lost:
        if (r7.length() == 0) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0085, code lost:
        f = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0089, code lost:
        return "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008a, code lost:
        r7 = r7.substring(0, r7.length() - 1);
        g = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0095, code lost:
        return r7;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0042 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x007b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r7, java.lang.String r8) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto L_0x0009
            java.lang.String r7 = ""
            return r7
        L_0x0009:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x0096 }
            r1 = 21
            if (r0 >= r1) goto L_0x0012
            java.lang.String r7 = ""
            return r7
        L_0x0012:
            java.lang.String r0 = g     // Catch:{ Throwable -> 0x0096 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0096 }
            if (r0 != 0) goto L_0x001d
            java.lang.String r7 = g     // Catch:{ Throwable -> 0x0096 }
            return r7
        L_0x001d:
            android.telephony.TelephonyManager r7 = G(r7)     // Catch:{ Throwable -> 0x0096 }
            int r0 = f     // Catch:{ Throwable -> 0x0096 }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0044
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.String r1 = "UZ2V0UGhvbmVDb3VudA="
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x0096 }
            java.lang.reflect.Method r0 = com.loc.ad.a((java.lang.Class) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3)     // Catch:{ Throwable -> 0x0096 }
            if (r0 == 0) goto L_0x0042
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0042 }
            java.lang.Object r0 = r0.invoke(r7, r1)     // Catch:{ Throwable -> 0x0042 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Throwable -> 0x0042 }
            int r0 = r0.intValue()     // Catch:{ Throwable -> 0x0042 }
            f = r0     // Catch:{ Throwable -> 0x0042 }
            goto L_0x0044
        L_0x0042:
            f = r2     // Catch:{ Throwable -> 0x0096 }
        L_0x0044:
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.String r1 = "MZ2V0SW1laQ="
            r3 = 1
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ Throwable -> 0x0096 }
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x0096 }
            r4[r2] = r5     // Catch:{ Throwable -> 0x0096 }
            java.lang.reflect.Method r0 = com.loc.ad.a((java.lang.Class) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r4)     // Catch:{ Throwable -> 0x0096 }
            if (r0 != 0) goto L_0x005a
            f = r2     // Catch:{ Throwable -> 0x0096 }
            java.lang.String r7 = ""
            return r7
        L_0x005a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0096 }
            r1.<init>()     // Catch:{ Throwable -> 0x0096 }
            r4 = 0
        L_0x0060:
            int r5 = f     // Catch:{ Throwable -> 0x007b }
            if (r4 >= r5) goto L_0x007b
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x007b }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x007b }
            r5[r2] = r6     // Catch:{ Throwable -> 0x007b }
            java.lang.Object r5 = r0.invoke(r7, r5)     // Catch:{ Throwable -> 0x007b }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x007b }
            r1.append(r5)     // Catch:{ Throwable -> 0x007b }
            r1.append(r8)     // Catch:{ Throwable -> 0x007b }
            int r4 = r4 + 1
            goto L_0x0060
        L_0x007b:
            java.lang.String r7 = r1.toString()     // Catch:{ Throwable -> 0x0096 }
            int r8 = r7.length()     // Catch:{ Throwable -> 0x0096 }
            if (r8 != 0) goto L_0x008a
            f = r2     // Catch:{ Throwable -> 0x0096 }
            java.lang.String r7 = ""
            return r7
        L_0x008a:
            int r8 = r7.length()     // Catch:{ Throwable -> 0x0096 }
            int r8 = r8 - r3
            java.lang.String r7 = r7.substring(r2, r8)     // Catch:{ Throwable -> 0x0096 }
            g = r7     // Catch:{ Throwable -> 0x0096 }
            return r7
        L_0x0096:
            java.lang.String r7 = ""
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.x.a(android.content.Context, java.lang.String):java.lang.String");
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size - 1; i2++) {
            for (int i3 = 1; i3 < size - i2; i3++) {
                int i4 = i3 - 1;
                if (list.get(i4).level > list.get(i3).level) {
                    list.set(i4, list.get(i3));
                    list.set(i3, list.get(i4));
                }
            }
        }
        return list;
    }

    public static void a(String str) {
        i = str;
    }

    public static String b() {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        String b2 = a.b();
        d = b2;
        return b2;
    }

    public static String b(Context context) {
        try {
            return C(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static boolean b(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String c(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            String x = x(context);
            if (x != null) {
                if (x.length() >= 5) {
                    return x.substring(3, 5);
                }
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static void c() {
        try {
            if (Build.VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke((Object) null, new Object[]{Integer.valueOf(ExifDirectoryBase.bv)});
            }
        } catch (Throwable unused) {
        }
    }

    public static int d(Context context) {
        try {
            return F(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static long d() {
        long j2;
        long j3;
        if (r != 0) {
            return r;
        }
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                j3 = (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1048576;
                j2 = (statFs2.getBlockCountLong() * statFs2.getBlockSizeLong()) / 1048576;
            } else {
                j3 = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1048576;
                j2 = (((long) statFs2.getBlockCount()) * ((long) statFs2.getBlockSize())) / 1048576;
            }
            r = j3 + j2;
        } catch (Throwable unused) {
        }
        return r;
    }

    public static int e(Context context) {
        try {
            return D(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String e() {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        String property = System.getProperty("os.arch");
        t = property;
        return property;
    }

    private static String f() {
        try {
            for (T t2 : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t2.getName().equalsIgnoreCase("wlan0")) {
                    byte[] bArr = null;
                    if (Build.VERSION.SDK_INT >= 9) {
                        bArr = t2.getHardwareAddress();
                    }
                    if (bArr == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : bArr) {
                        String upperCase = Integer.toHexString(b2 & 255).toUpperCase();
                        if (upperCase.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(upperCase);
                        sb.append(":");
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String f(Context context) {
        try {
            return B(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:7|(1:9)|10|(2:14|15)|16|17|18|(1:21)(2:22|23)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x003a */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String g(android.content.Context r2) {
        /*
            java.lang.String r0 = f6648a     // Catch:{ Throwable -> 0x003a }
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = ""
            java.lang.String r1 = f6648a     // Catch:{ Throwable -> 0x003a }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x003a }
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = f6648a     // Catch:{ Throwable -> 0x003a }
            return r0
        L_0x0011:
            java.lang.String r0 = "WYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFVFRJTkdT"
            java.lang.String r0 = com.loc.ad.c((java.lang.String) r0)     // Catch:{ Throwable -> 0x003a }
            boolean r0 = b(r2, r0)     // Catch:{ Throwable -> 0x003a }
            if (r0 == 0) goto L_0x0029
            android.content.ContentResolver r0 = r2.getContentResolver()     // Catch:{ Throwable -> 0x003a }
            java.lang.String r1 = "mqBRboGZkQPcAkyk"
            java.lang.String r0 = android.provider.Settings.System.getString(r0, r1)     // Catch:{ Throwable -> 0x003a }
            f6648a = r0     // Catch:{ Throwable -> 0x003a }
        L_0x0029:
            java.lang.String r0 = f6648a     // Catch:{ Throwable -> 0x003a }
            if (r0 == 0) goto L_0x003a
            java.lang.String r0 = ""
            java.lang.String r1 = f6648a     // Catch:{ Throwable -> 0x003a }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x003a }
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = f6648a     // Catch:{ Throwable -> 0x003a }
            return r0
        L_0x003a:
            java.lang.String r2 = A(r2)     // Catch:{ Throwable -> 0x0040 }
            f6648a = r2     // Catch:{ Throwable -> 0x0040 }
        L_0x0040:
            java.lang.String r2 = f6648a
            if (r2 != 0) goto L_0x0047
            java.lang.String r2 = ""
            return r2
        L_0x0047:
            java.lang.String r2 = f6648a
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.x.g(android.content.Context):java.lang.String");
    }

    public static String h(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        if (!TextUtils.isEmpty(l)) {
            return l;
        }
        if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
            return "";
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                return (String) ad.a(Build.class, "MZ2V0U2VyaWFs", (Class<?>[]) new Class[0]).invoke(Build.class, new Object[0]);
            }
            if (Build.VERSION.SDK_INT >= 9) {
                l = Build.SERIAL;
            }
            return l == null ? "" : l;
        } catch (Throwable unused) {
        }
    }

    public static String i(Context context) {
        if (!TextUtils.isEmpty(k)) {
            return k;
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), ad.c(new String(al.a(13))));
            k = string;
            return string == null ? "" : k;
        } catch (Throwable unused) {
            return k;
        }
    }

    static String j(Context context) {
        if (context == null) {
            return "";
        }
        try {
            if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                return "";
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            return (wifiManager != null && wifiManager.isWifiEnabled()) ? wifiManager.getConnectionInfo().getBSSID() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    static String k(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            try {
                if (b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager == null) {
                        return "";
                    }
                    if (wifiManager.isWifiEnabled()) {
                        List<ScanResult> scanResults = wifiManager.getScanResults();
                        if (scanResults != null) {
                            if (scanResults.size() != 0) {
                                List<ScanResult> a2 = a(scanResults);
                                int i2 = 0;
                                boolean z = true;
                                while (i2 < a2.size() && i2 < 7) {
                                    ScanResult scanResult = a2.get(i2);
                                    if (z) {
                                        z = false;
                                    } else {
                                        sb.append(i.b);
                                    }
                                    sb.append(scanResult.BSSID);
                                    i2++;
                                }
                            }
                        }
                        return sb.toString();
                    }
                    return sb.toString();
                }
            } catch (Throwable unused) {
            }
        }
        return sb.toString();
    }

    public static String l(Context context) {
        try {
            if (m != null && !"".equals(m)) {
                return m;
            }
            if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF"))) {
                return m;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            m = wifiManager.getConnectionInfo().getMacAddress();
            if (ad.c("YMDI6MDA6MDA6MDA6MDA6MDA").equals(m) || ad.c("YMDA6MDA6MDA6MDA6MDA6MDA").equals(m)) {
                m = f();
            }
            return m;
        } catch (Throwable unused) {
        }
    }

    static String[] m(Context context) {
        try {
            if (b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                if (b(context, ad.c("EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19DT0FSU0VfTE9DQVRJT04="))) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager == null) {
                        return new String[]{"", ""};
                    }
                    CellLocation cellLocation = telephonyManager.getCellLocation();
                    if (cellLocation instanceof GsmCellLocation) {
                        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                        int cid = gsmCellLocation.getCid();
                        int lac = gsmCellLocation.getLac();
                        return new String[]{lac + "||" + cid, "gsm"};
                    }
                    if (cellLocation instanceof CdmaCellLocation) {
                        CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                        int systemId = cdmaCellLocation.getSystemId();
                        int networkId = cdmaCellLocation.getNetworkId();
                        int baseStationId = cdmaCellLocation.getBaseStationId();
                        return new String[]{systemId + "||" + networkId + "||" + baseStationId, "cdma"};
                    }
                    return new String[]{"", ""};
                }
            }
            return new String[]{"", ""};
        } catch (Throwable unused) {
        }
    }

    static String n(Context context) {
        try {
            TelephonyManager G = G(context);
            if (G == null) {
                return "";
            }
            String networkOperator = G.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                if (networkOperator.length() >= 3) {
                    return networkOperator.substring(0, 3);
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    static String o(Context context) {
        try {
            TelephonyManager G = G(context);
            if (G == null) {
                return "";
            }
            String networkOperator = G.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                if (networkOperator.length() >= 3) {
                    return networkOperator.substring(3);
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int p(Context context) {
        try {
            return F(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int q(Context context) {
        try {
            return D(context);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static NetworkInfo r(Context context) {
        ConnectivityManager E;
        if (b(context, ad.c("AYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19ORVRXT1JLX1NUQVRF")) && (E = E(context)) != null) {
            return E.getActiveNetworkInfo();
        }
        return null;
    }

    static String s(Context context) {
        try {
            NetworkInfo r2 = r(context);
            if (r2 == null) {
                return null;
            }
            return r2.getExtraInfo();
        } catch (Throwable unused) {
            return null;
        }
    }

    static String t(Context context) {
        StringBuilder sb;
        try {
            if (n != null && !"".equals(n)) {
                return n;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            if (i3 > i2) {
                sb = new StringBuilder();
                sb.append(i2);
                sb.append("*");
                sb.append(i3);
            } else {
                sb = new StringBuilder();
                sb.append(i3);
                sb.append("*");
                sb.append(i2);
            }
            n = sb.toString();
            return n;
        } catch (Throwable unused) {
        }
    }

    public static String u(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                return "";
            }
            if (o != null && !"".equals(o)) {
                return o;
            }
            if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return o;
            }
            TelephonyManager G = G(context);
            if (G == null) {
                return "";
            }
            Method a2 = ad.a((Class) G.getClass(), "QZ2V0RGV2aWNlSWQ", (Class<?>[]) new Class[0]);
            if (Build.VERSION.SDK_INT >= 26) {
                a2 = ad.a((Class) G.getClass(), "QZ2V0SW1laQ==", (Class<?>[]) new Class[0]);
            }
            if (a2 != null) {
                o = (String) a2.invoke(G, new Object[0]);
            }
            if (o == null) {
                o = "";
            }
            return o;
        } catch (Throwable unused) {
        }
    }

    public static String v(Context context) {
        return u(context) + "#" + a(context);
    }

    public static String w(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return "";
        }
        try {
            if (p != null && !"".equals(p)) {
                return p;
            }
            if (!b(context, ad.c("WYW5kcm9pZC5wZXJtaXNzaW9uLlJFQURfUEhPTkVfU1RBVEU="))) {
                return p;
            }
            TelephonyManager G = G(context);
            if (G == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT >= 26) {
                Method a2 = ad.a((Class) G.getClass(), "QZ2V0TWVpZA==", (Class<?>[]) new Class[0]);
                if (a2 != null) {
                    p = (String) a2.invoke(G, new Object[0]);
                }
                if (p == null) {
                    p = "";
                }
            }
            return p;
        } catch (Throwable unused) {
        }
    }

    public static String x(Context context) {
        try {
            return B(context);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d A[SYNTHETIC, Splitter:B:25:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0063 A[SYNTHETIC, Splitter:B:31:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int y(android.content.Context r4) {
        /*
            int r0 = s
            if (r0 == 0) goto L_0x0007
            int r4 = s
            return r4
        L_0x0007:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            r2 = 0
            if (r0 < r1) goto L_0x0028
            java.lang.String r0 = "activity"
            java.lang.Object r4 = r4.getSystemService(r0)
            android.app.ActivityManager r4 = (android.app.ActivityManager) r4
            if (r4 != 0) goto L_0x0019
            return r2
        L_0x0019:
            android.app.ActivityManager$MemoryInfo r0 = new android.app.ActivityManager$MemoryInfo
            r0.<init>()
            r4.getMemoryInfo(r0)
            long r0 = r0.totalMem
            r2 = 1024(0x400, double:5.06E-321)
            long r0 = r0 / r2
            int r2 = (int) r0
            goto L_0x0066
        L_0x0028:
            r4 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0061, all -> 0x005a }
            java.lang.String r1 = "/proc/meminfo"
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0061, all -> 0x005a }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0061, all -> 0x005a }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0061, all -> 0x005a }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0061, all -> 0x005a }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0061, all -> 0x005a }
            java.lang.String r4 = r1.readLine()     // Catch:{ Throwable -> 0x0058, all -> 0x0054 }
            java.lang.String r0 = "\\s+"
            java.lang.String[] r4 = r4.split(r0)     // Catch:{ Throwable -> 0x0058, all -> 0x0054 }
            r0 = 1
            r4 = r4[r0]     // Catch:{ Throwable -> 0x0058, all -> 0x0054 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0058, all -> 0x0054 }
            int r4 = r4.intValue()     // Catch:{ Throwable -> 0x0058, all -> 0x0054 }
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            r2 = r4
            goto L_0x0066
        L_0x0054:
            r4 = move-exception
            r0 = r4
            r4 = r1
            goto L_0x005b
        L_0x0058:
            r4 = r1
            goto L_0x0061
        L_0x005a:
            r0 = move-exception
        L_0x005b:
            if (r4 == 0) goto L_0x0060
            r4.close()     // Catch:{ IOException -> 0x0060 }
        L_0x0060:
            throw r0
        L_0x0061:
            if (r4 == 0) goto L_0x0066
            r4.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            int r2 = r2 / 1024
            s = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.x.y(android.content.Context):int");
    }

    static String z(Context context) {
        try {
            return C(context);
        } catch (Throwable unused) {
            return "";
        }
    }
}
