package com.xiaomi.base.utils;

public class AndroidUtil {

    /* renamed from: a  reason: collision with root package name */
    public static volatile DispatchQueue f10013a = new DispatchQueue("aliverequest");
    public static volatile DispatchQueue b = new DispatchQueue("highestQueue");
    public static volatile DispatchQueue c = new DispatchQueue("logQueue");
    public static volatile DispatchQueue d = new DispatchQueue("LogStatViewQueue");
    public static volatile DispatchQueue e = new DispatchQueue("pluginInit");
    public static volatile DispatchQueue f = new DispatchQueue("pluginQueue");
    public static volatile DispatchQueue g = new DispatchQueue("stageQueue");
    public static volatile DispatchQueue h = new DispatchQueue("uiHandleQueue");

    public static void a(Runnable runnable) {
        a(runnable, 0);
    }

    public static void a(Runnable runnable, long j) {
        if (j == 0) {
            MainThreadHandler.c(runnable);
        } else {
            MainThreadHandler.a(runnable, j);
        }
    }
}
