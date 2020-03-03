package com.xiaomi.market.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

public class Client {

    /* renamed from: a  reason: collision with root package name */
    static final int f11094a = 8;
    static int b = 0;
    static int c = 0;
    static String d = null;
    static int e = 0;
    static int f = 0;
    static String g = null;
    static ArrayList<String> h = null;
    static ArrayList<String> i = null;
    static ArrayList<String> j = null;
    static int k = 0;
    static String l = null;
    static String m = null;
    static String n = null;
    private static final String o = "MarketSdkClient";
    private static boolean p = false;
    private static final Object q = new Object();

    static void a(Context context) {
        if (!p) {
            b(context);
            c(context);
            d(context);
            e(context);
            t();
            f(context);
            g(context);
            p = true;
        }
    }

    private static void b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        c = displayMetrics.heightPixels;
        b = displayMetrics.widthPixels;
        d = c + "*" + b;
        e = displayMetrics.densityDpi;
    }

    private static void c(Context context) {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
        f = deviceConfigurationInfo.reqTouchScreen;
        g = deviceConfigurationInfo.getGlEsVersion();
    }

    private static void d(Context context) {
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        synchronized (q) {
            h = new ArrayList<>();
            if (systemAvailableFeatures != null) {
                for (FeatureInfo featureInfo : systemAvailableFeatures) {
                    if (!TextUtils.isEmpty(featureInfo.name)) {
                        h.add(featureInfo.name);
                    }
                }
            }
            Collections.sort(h);
        }
    }

    private static void e(Context context) {
        String[] systemSharedLibraryNames = context.getPackageManager().getSystemSharedLibraryNames();
        synchronized (q) {
            i = new ArrayList<>();
            if (systemSharedLibraryNames != null) {
                for (String str : systemSharedLibraryNames) {
                    if (!TextUtils.isEmpty(str)) {
                        i.add(str);
                    }
                }
            }
            Collections.sort(i);
        }
    }

    private static void t() {
        String u = u();
        synchronized (q) {
            j = new ArrayList<>();
            if (!TextUtils.isEmpty(u)) {
                for (String str : TextUtils.split(u, " ")) {
                    if (!TextUtils.isEmpty(str)) {
                        j.add(str);
                    }
                }
            }
            Collections.sort(j);
        }
    }

    private static void f(Context context) {
        m = Build.VERSION.RELEASE;
        l = Build.VERSION.INCREMENTAL;
        k = Build.VERSION.SDK_INT;
    }

    public static String a() {
        return Locale.getDefault().getCountry();
    }

    public static String b() {
        return Locale.getDefault().getLanguage();
    }

    public static String c() {
        return SystemProperties.a("ro.miui.region", "CN");
    }

    public static String d() {
        return SystemProperties.a("ro.miui.ui.version.code", "-1");
    }

    public static boolean e() {
        return SystemProperties.a("ro.product.mod_device", "").endsWith("_alpha") || SystemProperties.a("ro.product.mod_device", "").endsWith("_alpha_global");
    }

    public static boolean f() {
        return !TextUtils.isEmpty(Build.VERSION.INCREMENTAL) && Build.VERSION.INCREMENTAL.matches("\\d+.\\d+.\\d+(-internal)?");
    }

    public static String g() {
        String a2 = SystemProperties.a("ro.miui.ui.version.name", "");
        if (e()) {
            return a2 + "-alpha";
        } else if (!f()) {
            return a2;
        } else {
            return a2 + "-dev";
        }
    }

    public static String h() {
        return Build.MODEL;
    }

    public static String i() {
        return Build.DEVICE;
    }

    public static int j() {
        return k() ? 1 : 0;
    }

    public static boolean k() {
        return SystemProperties.a("ro.build.characteristics", "").contains("tablet");
    }

    public static String l() {
        ArrayList arrayList = new ArrayList();
        if (p()) {
            String a2 = SystemProperties.a("ro.product.cpu.abilist", "");
            if (!TextUtils.isEmpty(a2)) {
                arrayList = new ArrayList(Arrays.asList(TextUtils.split(a2, ",")));
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(Build.CPU_ABI);
            arrayList.add(Build.CPU_ABI2);
        }
        return TextUtils.join(",", arrayList);
    }

    private static void g(Context context) {
        n = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
    }

    public static String m() {
        return Coder.a(((TelephonyManager) AppGlobal.a().getSystemService("phone")).getDeviceId());
    }

    private static String u() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (eglGetDisplay == EGL10.EGL_NO_DISPLAY || !egl10.eglInitialize(eglGetDisplay, new int[2])) {
            return null;
        }
        int[] iArr = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!egl10.eglChooseConfig(eglGetDisplay, new int[]{12339, 1, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12344}, eGLConfigArr, 1, iArr)) {
            return null;
        }
        EGLConfig eGLConfig = iArr[0] > 0 ? eGLConfigArr[0] : null;
        EGLContext eglCreateContext = egl10.eglCreateContext(eglGetDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, (int[]) null);
        EGLSurface eglCreatePbufferSurface = egl10.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 480, 12374, 800, 12344});
        if (eglCreatePbufferSurface == null || eglCreatePbufferSurface == EGL10.EGL_NO_SURFACE) {
            return null;
        }
        egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext);
        if (!egl10.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext)) {
            return null;
        }
        String glGetString = ((GL10) eglCreateContext.getGL()).glGetString(7939);
        egl10.eglDestroySurface(eglGetDisplay, eglCreatePbufferSurface);
        egl10.eglDestroyContext(eglGetDisplay, eglCreateContext);
        egl10.eglTerminate(eglGetDisplay);
        if (glGetString != null) {
            return glGetString.trim();
        }
        return null;
    }

    static boolean n() {
        return k >= 11;
    }

    public static int o() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean p() {
        return o() >= 21;
    }

    public static boolean q() {
        return k >= 24;
    }

    public static boolean r() {
        return new File("/system/app/miui.apk").exists() || new File("/system/app/miui/miui.apk").exists();
    }

    public static boolean s() {
        return r() && SystemProperties.a("ro.product.mod_device", "").contains("_global");
    }
}
