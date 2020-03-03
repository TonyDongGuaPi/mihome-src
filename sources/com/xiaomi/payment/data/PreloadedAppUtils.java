package com.xiaomi.payment.data;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.ReflectUtils;
import com.mibi.common.data.Utils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PreloadedAppUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12229a = 1;
    public static final int b = 2;
    private static final String c = "PreloadedAppUtils";
    private static String d = "preload";
    private static String e = "preloadPackageName";
    private static String f = "preloadFlags";

    public interface PackageInstallListener {

        /* renamed from: a  reason: collision with root package name */
        public static final int f12230a = 1;

        void a(String str, int i);
    }

    private PreloadedAppUtils() {
    }

    private static String a(EntryData entryData) {
        String str = (String) entryData.getMetaData(e, "");
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return entryData.mPackageName;
    }

    public static boolean a(Context context, EntryData entryData) {
        if (!((Boolean) entryData.getMetaData(d, false)).booleanValue()) {
            return false;
        }
        String a2 = a(entryData);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        return !Utils.b(context, a2);
    }

    public static boolean a(Context context, EntryData entryData, PackageInstallListener packageInstallListener) {
        return a(context, a(entryData), packageInstallListener, ((Integer) entryData.getMetaData(f, 2)).intValue());
    }

    public static boolean a(Context context, String str, PackageInstallListener packageInstallListener, int i) {
        Class<?> a2;
        Method a3;
        Class<?> a4 = ReflectUtils.a("miui.content.pm.PreloadedAppPolicy");
        if (a4 == null || (a2 = ReflectUtils.a("android.content.pm.IPackageInstallObserver")) == null || (a3 = ReflectUtils.a(a4, "installPreloadedDataApp", (Class<?>[]) new Class[]{Context.class, String.class, a2, Integer.TYPE})) == null) {
            return false;
        }
        Object a5 = a(a2, packageInstallListener);
        try {
            a3.setAccessible(true);
            a3.invoke(a4, new Object[]{context, str, a5, Integer.valueOf(i)});
            return true;
        } catch (Exception e2) {
            Log.d(c, "failed to invoke install method", e2);
            return false;
        }
    }

    private static Object a(Class<?> cls, PackageInstallListener packageInstallListener) {
        if (cls == null) {
            return null;
        }
        try {
            return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new PackageInstallObserver(packageInstallListener));
        } catch (Exception e2) {
            Log.d(c, "failed to create install proxy", e2);
            return null;
        }
    }

    private static class PackageInstallObserver implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private PackageInstallListener f12231a;

        public PackageInstallObserver(PackageInstallListener packageInstallListener) {
            this.f12231a = packageInstallListener;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if (!TextUtils.equals(method.getName(), "packageInstalled")) {
                    return null;
                }
                this.f12231a.a(objArr[0], objArr[1].intValue());
                return null;
            } catch (Exception e) {
                Log.d(PreloadedAppUtils.c, "failed to invoke listener", e);
                return null;
            }
        }
    }
}
