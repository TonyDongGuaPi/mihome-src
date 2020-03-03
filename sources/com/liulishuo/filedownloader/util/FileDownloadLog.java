package com.liulishuo.filedownloader.util;

import android.util.Log;

public class FileDownloadLog {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f6465a = false;
    private static final String b = "FileDownloader.";

    public static void a(Object obj, Throwable th, String str, Object... objArr) {
        a(6, obj, th, str, objArr);
    }

    public static void a(Object obj, String str, Object... objArr) {
        a(6, obj, str, objArr);
    }

    public static void b(Object obj, String str, Object... objArr) {
        a(4, obj, str, objArr);
    }

    public static void c(Object obj, String str, Object... objArr) {
        a(3, obj, str, objArr);
    }

    public static void d(Object obj, String str, Object... objArr) {
        a(5, obj, str, objArr);
    }

    public static void e(Object obj, String str, Object... objArr) {
        a(2, obj, str, objArr);
    }

    private static void a(int i, Object obj, String str, Object... objArr) {
        a(i, obj, (Throwable) null, str, objArr);
    }

    private static void a(int i, Object obj, Throwable th, String str, Object... objArr) {
        if ((i >= 5) || f6465a) {
            Log.println(i, a(obj), FileDownloadUtils.a(str, objArr));
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private static String a(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(b);
        sb.append((obj instanceof Class ? (Class) obj : obj.getClass()).getSimpleName());
        return sb.toString();
    }
}
