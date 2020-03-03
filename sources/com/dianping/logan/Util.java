package com.dianping.logan;

import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleDateFormat f5174a = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean a(String str, Class cls) {
        try {
            ClassLoader classLoader = cls.getClassLoader();
            Class<?> cls2 = Runtime.getRuntime().getClass();
            Class[] clsArr = new Class[2];
            if (Build.VERSION.SDK_INT > 24) {
                clsArr[0] = ClassLoader.class;
                clsArr[1] = String.class;
                Method declaredMethod = cls2.getDeclaredMethod("loadLibrary0", clsArr);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(Runtime.getRuntime(), new Object[]{classLoader, str});
            } else {
                clsArr[0] = String.class;
                clsArr[1] = ClassLoader.class;
                Method declaredMethod2 = cls2.getDeclaredMethod("loadLibrary", clsArr);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(Runtime.getRuntime(), new Object[]{str, classLoader});
            }
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return false;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static String a() {
        return f5174a.format(new Date(System.currentTimeMillis()));
    }

    public static long a(String str) {
        try {
            return f5174a.parse(str).getTime();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String a(long j) {
        return f5174a.format(new Date(j));
    }
}
