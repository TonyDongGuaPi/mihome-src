package com.xiaomi.youpin.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.taobao.weex.annotation.JSMethod;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class Device {
    public static String A = "";
    public static String B = "";
    public static String C = "";
    public static String D = "";
    public static boolean E = false;
    private static final String F = "installTime";
    private static final long G = 604800000;
    private static List<PackageInfo> H = null;

    /* renamed from: a  reason: collision with root package name */
    public static int f23764a = 0;
    public static int b = 0;
    public static int c = 0;
    public static String d = null;
    public static int e = 0;
    public static String f = "";
    public static String g = "";
    public static String h = "";
    public static String i = "";
    public static String j = "";
    public static String k = "";
    public static String l = "";
    public static String m = "";
    public static int n = 0;
    public static String o = "";
    public static String p = "";
    public static boolean q = false;
    public static String r = "";
    public static int s = 0;
    public static String t = "";
    public static boolean u = false;
    public static String v = "";
    public static String w = "";
    public static String x = "";
    public static String y = "";
    public static String z = "";

    public static void a(Context context, String str) {
        a(context);
        b(context, str);
        b(context);
        c(context);
        d(context);
        e(context);
    }

    public static String a() {
        return Coder.a(b());
    }

    public static String b() {
        return d + b + c + e + f + g + h + i + j + k + l + m + n + o + p + q + s + t + u + v + w + x + y + z + A;
    }

    private static void a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            if (i3 > i2) {
                b = i2;
                c = i3;
            } else {
                b = i3;
                c = i2;
            }
            d = c + "*" + b;
            e = displayMetrics.densityDpi;
        }
    }

    private static void b(Context context, String str) {
        f = Build.MODEL;
        g = Build.DEVICE;
        h = Build.PRODUCT;
        i = Build.BOARD;
        j = Build.HARDWARE;
        k = Build.MANUFACTURER;
        l = Build.BRAND;
        m = Build.TYPE;
        C = Build.SERIAL + ";KEY=" + str;
        p = Build.VERSION.RELEASE;
        o = Build.VERSION.INCREMENTAL;
        n = Build.VERSION.SDK_INT;
        q = f(context);
        B = WifiUtil.d(context.getApplicationContext());
        D = l();
    }

    private static String l() {
        Class<?> cls;
        try {
            cls = Class.forName("android.os.SystemProperties");
        } catch (Exception unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"permanent.hw.custom.serialno"});
        } catch (Exception unused2) {
            return null;
        }
    }

    private static void b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            r = packageInfo.packageName;
            s = packageInfo.versionCode;
            t = packageInfo.versionName;
            boolean z2 = true;
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                z2 = false;
            }
            u = z2;
        } catch (PackageManager.NameNotFoundException unused) {
            s = 0;
            t = "";
            u = false;
        }
    }

    private static void c(Context context) {
        v = Locale.getDefault().getCountry();
        w = Locale.getDefault().getLanguage();
        x = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    private static void d(Context context) {
        if (context != null) {
            try {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = "";
                }
                z = deviceId;
            } catch (Exception e2) {
                e2.printStackTrace();
                z = "";
            }
            try {
                String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                StringBuffer stringBuffer = new StringBuffer();
                if (!TextUtils.isEmpty(z)) {
                    stringBuffer.append(z);
                }
                if (!TextUtils.isEmpty(macAddress)) {
                    stringBuffer.append(JSMethod.NOT_SET);
                    stringBuffer.append(macAddress);
                }
                y = Coder.a(stringBuffer.toString());
            } catch (Exception e3) {
                e3.printStackTrace();
                y = "";
            }
            A = "1.1.1";
        }
    }

    private static void e(Context context) {
        long a2 = a(context, F, 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 > 0) {
            long j2 = currentTimeMillis - a2;
            if (j2 >= 0) {
                if (j2 < 604800000) {
                    E = true;
                    return;
                } else {
                    E = false;
                    return;
                }
            }
        }
        E = true;
        b(context, F, currentTimeMillis);
    }

    private static long a(Context context, String str, long j2) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(str, j2);
    }

    private static void b(Context context, String str, long j2) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(str, j2).apply();
    }

    private static boolean f(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.miui.cloudservice", 0);
            if (packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static boolean d() {
        return Environment.getExternalStorageState().equals("removed");
    }

    public static boolean e() {
        return !Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean f() {
        return h() <= 102400;
    }

    public static boolean g() {
        return !e() && !f() && !d();
    }

    public static long h() {
        if (e()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }

    public static String i() {
        if (!j()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static boolean j() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean a(String str) {
        if (H == null) {
            return false;
        }
        for (PackageInfo next : H) {
            if (str != null && str.equals(next.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static int k() {
        return f23764a;
    }

    public static void a(int i2) {
        f23764a = i2 - ((int) (System.currentTimeMillis() / 1000));
    }
}
