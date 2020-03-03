package com.tencent.smtt.utils;

import android.text.TextUtils;
import java.lang.reflect.Method;

public class p {

    /* renamed from: a  reason: collision with root package name */
    private static Class f9213a;
    private static Method b;

    static {
        try {
            f9213a = Class.forName("android.os.SystemProperties");
            b = f9213a.getDeclaredMethod("get", new Class[]{String.class, String.class});
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String a(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : b(str, str2);
    }

    private static String b(String str, String str2) {
        if (f9213a == null || b == null) {
            return str2;
        }
        try {
            return (String) b.invoke(f9213a, new Object[]{str, str2});
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }
}
