package com.xiaomi.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.base.utils.Utils;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@SuppressLint({"MissingPermission"})
public class Device {
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static boolean D = false;
    private static final String E = "device";
    private static final String F = "installTime";
    private static final long G = 604800000;
    private static Boolean H;
    private static List<PackageInfo> I;

    /* renamed from: a  reason: collision with root package name */
    public static int f10020a;
    public static int b;
    public static String c;
    public static int d;
    public static String e;
    public static String f;
    public static String g;
    public static String h;
    public static String i;
    public static String j;
    public static String k;
    public static String l;
    public static int m;
    public static String n;
    public static String o;
    public static boolean p;
    public static String q;
    public static int r;
    public static String s;
    public static boolean t;
    public static String u;
    public static String v;
    public static String w;
    public static String x;
    public static String y;
    public static String z;

    public static void a(Context context, boolean z2) {
        H = Boolean.valueOf(z2);
        a(context);
        b(context);
        c(context);
        d(context);
        e(context);
        f(context);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                I = packageManager.getInstalledPackages(64);
            } catch (Exception unused) {
            }
        }
    }

    public static String a() {
        return Coder.a(b());
    }

    public static String b() {
        return c + f10020a + b + d + e + f + g + h + i + j + k + l + m + n + o + p + r + s + t + u + v + w + x + y + B;
    }

    private static void a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        b = displayMetrics.heightPixels;
        f10020a = displayMetrics.widthPixels;
        c = b + "*" + f10020a;
        d = displayMetrics.densityDpi;
    }

    private static void b(Context context) {
        e = Build.MODEL;
        f = Build.DEVICE;
        g = Build.PRODUCT;
        h = Build.BOARD;
        i = Build.HARDWARE;
        j = Build.MANUFACTURER;
        k = Build.BRAND;
        l = Build.TYPE;
        z = Build.SERIAL;
        o = Build.VERSION.RELEASE;
        n = Build.VERSION.INCREMENTAL;
        m = Build.VERSION.SDK_INT;
        p = g(context);
        C = Utils.Network.f(context);
        A = i();
    }

    private static String i() {
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

    private static void c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            q = packageInfo.packageName;
            r = packageInfo.versionCode;
            s = packageInfo.versionName;
            boolean z2 = true;
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                z2 = false;
            }
            t = z2;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            r = 0;
            s = "";
            t = false;
        }
    }

    private static void d(Context context) {
        u = Locale.getDefault().getCountry();
        v = Locale.getDefault().getLanguage();
        w = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    private static void e(Context context) {
        if (context != null) {
            String deviceId = H.booleanValue() ? ((TelephonyManager) context.getSystemService("phone")).getDeviceId() : "";
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = "";
            }
            y = deviceId;
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            StringBuffer stringBuffer = new StringBuffer();
            if (!TextUtils.isEmpty(deviceId)) {
                stringBuffer.append(deviceId);
            }
            if (!TextUtils.isEmpty(macAddress)) {
                stringBuffer.append(JSMethod.NOT_SET);
                stringBuffer.append(macAddress);
            }
            x = Coder.a(stringBuffer.toString());
        }
    }

    private static void f(Context context) {
        long a2 = Utils.Preference.a(context, F, 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (a2 > 0) {
            long j2 = currentTimeMillis - a2;
            if (j2 >= 0) {
                if (j2 < 604800000) {
                    D = true;
                    return;
                } else {
                    D = false;
                    return;
                }
            }
        }
        D = true;
        Utils.Preference.a(context, F, Long.valueOf(currentTimeMillis));
    }

    private static boolean g(Context context) {
        try {
            if ((context.getPackageManager().getPackageInfo("com.miui.cloudservice", 0).applicationInfo.flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (Error e2) {
            e2.printStackTrace();
            return false;
        } catch (PackageManager.NameNotFoundException e3) {
            e3.printStackTrace();
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

    public static boolean a(String str) {
        if (I == null) {
            return false;
        }
        for (PackageInfo next : I) {
            if (str != null && str.equals(next.packageName)) {
                return true;
            }
        }
        return false;
    }
}
