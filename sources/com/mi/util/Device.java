package com.mi.util;

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
import com.mi.log.LogUtil;
import com.mi.util.Utils;
import com.mi.util.permission.PermissionUtil;
import com.taobao.weex.annotation.JSMethod;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

public class Device {
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static boolean D = false;
    private static final String E = "device";
    private static final String F = "installTime";
    private static final long G = 604800000;

    /* renamed from: a  reason: collision with root package name */
    public static int f1349a;
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

    public static native String getDToken();

    public static void a(Context context, boolean z2) {
        a(context);
        b(context);
        c(context);
        d(context);
        b(context, z2);
        e(context);
    }

    public static String a() {
        return Coder.a(b());
    }

    public static String b() {
        return c + f1349a + b + d + e + f + g + h + i + j + k + l + m + n + o + p + r + s + t + u + v + w + x + y + B;
    }

    private static void a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        b = displayMetrics.heightPixels;
        f1349a = displayMetrics.widthPixels;
        c = b + "*" + f1349a;
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
        if (Build.VERSION.SDK_INT >= 28) {
            if (PermissionUtil.a(context, "android.permission.READ_PHONE_STATE")) {
                z = Build.getSerial();
            }
        }
        o = Build.VERSION.RELEASE;
        n = Build.VERSION.INCREMENTAL;
        m = Build.VERSION.SDK_INT;
        p = f(context);
        C = Utils.Network.getMacAddress();
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
        LogUtil.b("device", "acquireAppInfo");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            q = packageInfo.packageName;
            r = packageInfo.versionCode;
            LogUtil.b("device", "acquireAppInfo get APP_VERSION:" + r);
            s = packageInfo.versionName;
            boolean z2 = true;
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                z2 = false;
            }
            t = z2;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.b("device", "acquireAppInfo NameNotFoundException:" + e2.toString());
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

    public static void b(Context context, boolean z2) {
        if (context != null) {
            String deviceId = z2 ? ((TelephonyManager) context.getSystemService("phone")).getDeviceId() : "";
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
            B = ChannelUtil.getChannelCode(context);
        }
    }

    private static void e(Context context) {
        long longPref = Utils.Preference.getLongPref(context, F, 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (longPref > 0) {
            long j2 = currentTimeMillis - longPref;
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
        Utils.Preference.setLongPref(context, F, Long.valueOf(currentTimeMillis));
    }

    private static boolean f(Context context) {
        try {
            if ((context.getPackageManager().getPackageInfo("com.miui.cloudservice", 0).applicationInfo.flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (Error e2) {
            e2.printStackTrace();
            return false;
        } catch (PackageManager.NameNotFoundException e3) {
            LogUtil.a("device", "meet name not found exception!!!");
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
        } catch (SocketException e2) {
            LogUtil.a("device", e2.toString());
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
}
