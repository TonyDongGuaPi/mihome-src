package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static String f9201a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";

    private static PackageInfo a(String str, int i) {
        Class cls;
        try {
            Class<?> cls2 = Class.forName("android.content.pm.PackageParser");
            Class[] declaredClasses = cls2.getDeclaredClasses();
            int length = declaredClasses.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    cls = null;
                    break;
                }
                cls = declaredClasses[i2];
                if (cls.getName().compareTo("android.content.pm.PackageParser$Package") == 0) {
                    break;
                }
                i2++;
            }
            Constructor<?> constructor = cls2.getConstructor(new Class[]{String.class});
            Method declaredMethod = cls2.getDeclaredMethod("parsePackage", new Class[]{File.class, String.class, DisplayMetrics.class, Integer.TYPE});
            Method declaredMethod2 = cls2.getDeclaredMethod("collectCertificates", new Class[]{cls, Integer.TYPE});
            Method declaredMethod3 = cls2.getDeclaredMethod("generatePackageInfo", new Class[]{cls, int[].class, Integer.TYPE, Long.TYPE, Long.TYPE});
            constructor.setAccessible(true);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            declaredMethod3.setAccessible(true);
            Object newInstance = constructor.newInstance(new Object[]{str});
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Object invoke = declaredMethod.invoke(newInstance, new Object[]{new File(str), str, displayMetrics, 0});
            if (invoke == null) {
                return null;
            }
            if ((i & 64) != 0) {
                declaredMethod2.invoke(newInstance, new Object[]{invoke, 0});
            }
            return (PackageInfo) declaredMethod3.invoke((Object) null, new Object[]{invoke, null, Integer.valueOf(i), 0, 0});
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0041 */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069 A[SYNTHETIC, Splitter:B:32:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006e A[SYNTHETIC, Splitter:B:36:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0076 A[SYNTHETIC, Splitter:B:43:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x007b A[SYNTHETIC, Splitter:B:47:0x007b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a() {
        /*
            java.lang.String r0 = c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = c
            return r0
        L_0x000b:
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x0056, all -> 0x0051 }
            java.lang.String r2 = "getprop ro.product.cpu.abi"
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Throwable -> 0x0056, all -> 0x0051 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0056, all -> 0x0051 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ Throwable -> 0x0056, all -> 0x0051 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0056, all -> 0x0051 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x004c, all -> 0x0047 }
            java.lang.String r0 = r1.readLine()     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r3 = "x86"
            boolean r0 = r0.contains(r3)     // Catch:{ Throwable -> 0x0045 }
            if (r0 == 0) goto L_0x0037
            java.lang.String r0 = "i686"
        L_0x0032:
            java.lang.String r0 = a((java.lang.String) r0)     // Catch:{ Throwable -> 0x0045 }
            goto L_0x003e
        L_0x0037:
            java.lang.String r0 = "os.arch"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ Throwable -> 0x0045 }
            goto L_0x0032
        L_0x003e:
            r1.close()     // Catch:{ IOException -> 0x0041 }
        L_0x0041:
            r2.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x0072
        L_0x0045:
            r0 = move-exception
            goto L_0x005a
        L_0x0047:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0074
        L_0x004c:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x005a
        L_0x0051:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L_0x0074
        L_0x0056:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x005a:
            java.lang.String r3 = "os.arch"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ all -> 0x0073 }
            java.lang.String r3 = a((java.lang.String) r3)     // Catch:{ all -> 0x0073 }
            r0.printStackTrace()     // Catch:{ all -> 0x0073 }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x006c }
        L_0x006c:
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ IOException -> 0x0071 }
        L_0x0071:
            r0 = r3
        L_0x0072:
            return r0
        L_0x0073:
            r0 = move-exception
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.close()     // Catch:{ IOException -> 0x007e }
        L_0x007e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a():java.lang.String");
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(Context context, File file) {
        try {
            if (context.getApplicationContext().getPackageName().contains("com.jd.jrapp")) {
                TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #1");
                String a2 = a(file);
                if (a2 != null) {
                    TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #2");
                    return a2;
                }
            }
        } catch (Throwable unused) {
            TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #3");
        }
        TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #4");
        String a3 = a(context, file, false);
        TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  android api signature=" + a3);
        if (a3 == null) {
            a3 = a(file);
            TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  java get signature=" + a3);
        }
        if (a3 != null) {
            return a3;
        }
        String a4 = a(context, file, true);
        TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  android reflection signature=" + a4);
        return a4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r1, java.io.File r2, boolean r3) {
        /*
            r0 = 65
            if (r3 == 0) goto L_0x000d
            java.lang.String r1 = r2.getAbsolutePath()
            android.content.pm.PackageInfo r1 = a((java.lang.String) r1, (int) r0)
            goto L_0x0019
        L_0x000d:
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.lang.String r2 = r2.getAbsolutePath()
            android.content.pm.PackageInfo r1 = r1.getPackageArchiveInfo(r2, r0)
        L_0x0019:
            r2 = 0
            if (r1 == 0) goto L_0x0032
            android.content.pm.Signature[] r3 = r1.signatures
            if (r3 == 0) goto L_0x002b
            android.content.pm.Signature[] r3 = r1.signatures
            int r3 = r3.length
            if (r3 <= 0) goto L_0x002b
            android.content.pm.Signature[] r1 = r1.signatures
            r3 = 0
            r1 = r1[r3]
            goto L_0x0033
        L_0x002b:
            java.lang.String r1 = "AppUtil"
            java.lang.String r3 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.w(r1, r3)
        L_0x0032:
            r1 = r2
        L_0x0033:
            if (r1 == 0) goto L_0x0039
            java.lang.String r2 = r1.toCharsString()
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, java.io.File, boolean):java.lang.String");
    }

    public static String a(Context context, String str) {
        try {
            String valueOf = String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str));
            try {
                return String.valueOf(Integer.toHexString(Integer.parseInt(valueOf)));
            } catch (Exception unused) {
                return valueOf;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    private static String a(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            byte[] bArr = new byte[8192];
            String a2 = a(a(jarFile, jarFile.getJarEntry(ShareConstants.K), bArr)[0].getEncoded());
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                String name = nextElement.getName();
                if (name != null) {
                    Certificate[] a3 = a(jarFile, nextElement, bArr);
                    String a4 = a3 != null ? a(a3[0].getEncoded()) : null;
                    if (a4 == null) {
                        if (!name.startsWith("META-INF/")) {
                            return null;
                        }
                    } else if (!a4.equals(a2)) {
                        return null;
                    }
                }
            }
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            byte b2 = bArr[i];
            int i2 = (b2 >> 4) & 15;
            int i3 = i * 2;
            cArr[i3] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            byte b3 = b2 & 15;
            cArr[i3 + 1] = (char) (b3 >= 10 ? (b3 + 97) - 10 : b3 + 48);
        }
        return new String(cArr);
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, bArr.length) != -1);
        inputStream.close();
        if (jarEntry != null) {
            return jarEntry.getCertificates();
        }
        return null;
    }

    public static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String c(Context context) {
        if (!TextUtils.isEmpty(f9201a)) {
            return f9201a;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String d(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String e(Context context) {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager == null ? null : wifiManager.getConnectionInfo();
            return connectionInfo == null ? "" : connectionInfo.getMacAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        try {
            e = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return e;
    }
}
