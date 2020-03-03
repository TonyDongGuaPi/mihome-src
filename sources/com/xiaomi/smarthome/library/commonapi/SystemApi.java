package com.xiaomi.smarthome.library.commonapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.android.phone.a.a.a;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.library.crypto.SHA1Util;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;
import com.xiaomi.smarthome.library.http.util.HeaderUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;
import java.util.TimeZone;
import org.json.JSONObject;

public class SystemApi {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19085a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 10;
    public static final int e = 11;
    public static final int f = 12;
    public static final int g = 20;
    public static final int h = 21;
    public static final int i = 22;
    public static final int j = 23;
    public static final int k = 30;
    private static final String l = "com.xiaomi.mihome";
    private static String m;
    private static String n;
    private static int o;
    private static String p;
    private static SystemApi q;
    private static Object r = new Object();
    private static Boolean s = null;
    private static String t = null;

    public String d() {
        return a.f813a;
    }

    public String k() {
        return l;
    }

    public String l() {
        return "phone";
    }

    private SystemApi() {
    }

    public static SystemApi a() {
        if (q == null) {
            synchronized (r) {
                if (q == null) {
                    q = new SystemApi();
                }
            }
        }
        return q;
    }

    private static Class<?> a(String str) throws ClassNotFoundException {
        Stack stack = new Stack();
        Class<?> cls = null;
        while (true) {
            if (str == null) {
                break;
            }
            try {
                cls = Class.forName(str);
            } catch (ClassNotFoundException unused) {
            }
            if (cls != null) {
                while (!stack.isEmpty() && cls != null) {
                    Class<?>[] classes = cls.getClasses();
                    String str2 = (String) stack.pop();
                    Class<?> cls2 = null;
                    for (Class<?> cls3 : classes) {
                        if (cls3.getSimpleName().equals(str2)) {
                            cls2 = cls3;
                        }
                    }
                    cls = cls2;
                }
            } else {
                int lastIndexOf = str.lastIndexOf(46);
                if (lastIndexOf <= 0 || lastIndexOf >= str.length() - 1) {
                    str = null;
                } else {
                    stack.add(str.substring(lastIndexOf + 1));
                    str = str.substring(0, lastIndexOf);
                }
            }
        }
        if (cls != null) {
            return cls;
        }
        throw new ClassNotFoundException("failed to guess class: " + str);
    }

    public static String b() {
        Class<?> cls;
        try {
            cls = a("android.os.SystemProperties");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        if (cls == null) {
            return "";
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("get", new Class[]{String.class, String.class});
            if (declaredMethod == null) {
                return "";
            }
            return (String) declaredMethod.invoke((Object) null, new Object[]{"ro.miui.ui.version.code", ""});
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return "";
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return "";
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return "";
        }
    }

    public static boolean c() {
        if (s == null) {
            Class<?> cls = null;
            try {
                cls = a("miui.os.Build");
            } catch (ClassNotFoundException unused) {
            }
            if (cls != null) {
                s = true;
            } else {
                s = false;
            }
        }
        return s.booleanValue();
    }

    public String a(Context context) {
        return a(context, false);
    }

    public String b(Context context) {
        return a(context, true);
    }

    public String a(Context context, boolean z) {
        return z ? SHA1Util.b(DeviceIdCompat.a(context)) : SHA1Util.a(DeviceIdCompat.a(context));
    }

    public String c(Context context) {
        return DeviceIdCompat.a(context);
    }

    public String e() {
        return Integer.toString(Build.VERSION.SDK_INT);
    }

    public String f() {
        return !TextUtils.isEmpty(Build.VERSION.RELEASE) ? Build.VERSION.RELEASE : "unknown";
    }

    public String g() {
        try {
            HeaderUtil.a(Build.MODEL);
            return Build.MODEL;
        } catch (Exception unused) {
            return "unknown";
        }
    }

    public String h() {
        try {
            HeaderUtil.a(Build.BRAND);
            return Build.BRAND;
        } catch (Exception unused) {
            return "UnknownBrand";
        }
    }

    public String i() {
        if (t == null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("manu", Build.MANUFACTURER);
                jSONObject.put("hard", Build.HARDWARE);
            } catch (Exception unused) {
            }
            t = g() + "|" + jSONObject.toString();
        }
        return t;
    }

    public String j() {
        return Build.VERSION.INCREMENTAL;
    }

    public String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "1.0.0";
        }
    }

    public int e(Context context) {
        if (o <= 0) {
            try {
                o = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                o = 0;
            }
        }
        return o;
    }

    public String f(Context context) {
        if (TextUtils.isEmpty(p)) {
            try {
                p = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                p = "";
            }
        }
        return p;
    }

    public float g(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public int h(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int i(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public String j(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getBSSID() : "";
        } catch (Exception unused) {
            return null;
        }
    }

    public int k(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return -1;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return 1;
        }
        if (type == 0) {
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                    return 12;
                case 3:
                case 8:
                case 15:
                    return 23;
                case 4:
                    return 11;
                case 5:
                case 6:
                case 12:
                    return 21;
            }
        }
        return 0;
    }

    public String m() {
        try {
            int rawOffset = TimeZone.getDefault().getRawOffset() / 60000;
            char c2 = '+';
            if (rawOffset < 0) {
                c2 = '-';
                rawOffset = -rawOffset;
            }
            StringBuilder sb = new StringBuilder(9);
            sb.append("GMT");
            sb.append(c2);
            a(sb, 2, rawOffset / 60);
            sb.append(Operators.CONDITION_IF_MIDDLE);
            a(sb, 2, rawOffset % 60);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public String n() {
        try {
            return TimeZone.getDefault().getID();
        } catch (Exception unused) {
            return "";
        }
    }

    private void a(StringBuilder sb, int i2, int i3) {
        String num = Integer.toString(i3);
        for (int i4 = 0; i4 < i2 - num.length(); i4++) {
            sb.append('0');
        }
        sb.append(num);
    }
}
