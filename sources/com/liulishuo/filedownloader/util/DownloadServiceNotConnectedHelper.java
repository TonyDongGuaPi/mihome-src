package com.liulishuo.filedownloader.util;

import android.app.Notification;

public class DownloadServiceNotConnectedHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6461a = ", but the download service isn't connected yet.";
    private static final String b = "\nYou can use FileDownloader#isServiceConnected() to check whether the service has been connected, \nbesides you can use following functions easier to control your code invoke after the service has been connected: \n1. FileDownloader#bindService(Runnable)\n2. FileDownloader#insureServiceBind()\n3. FileDownloader#insureServiceBindAsync()";

    public static boolean a(String str, String str2, boolean z) {
        a("request start the task([%s], [%s], [%B]) in the download service", str, str2, Boolean.valueOf(z));
        return false;
    }

    public static boolean a(int i) {
        a("request pause the task[%d] in the download service", Integer.valueOf(i));
        return false;
    }

    public static boolean a(String str, String str2) {
        a("request check the task([%s], [%s]) is downloading in the download service", str, str2);
        return false;
    }

    public static long b(int i) {
        a("request get the downloaded so far byte for the task[%d] in the download service", Integer.valueOf(i));
        return 0;
    }

    public static long c(int i) {
        a("request get the total byte for the task[%d] in the download service", Integer.valueOf(i));
        return 0;
    }

    public static byte d(int i) {
        a("request get the status for the task[%d] in the download service", Integer.valueOf(i));
        return 0;
    }

    public static void a() {
        a("request pause all tasks in the download service", new Object[0]);
    }

    public static boolean b() {
        a("request check the download service is idle", new Object[0]);
        return true;
    }

    public static void a(int i, Notification notification) {
        a("request set the download service as the foreground service([%d],[%s]),", Integer.valueOf(i), notification);
    }

    public static void a(boolean z) {
        a("request cancel the foreground status[%B] for the download service", Boolean.valueOf(z));
    }

    public static boolean e(int i) {
        a("request set the max network thread count[%d] in the download service", Integer.valueOf(i));
        return false;
    }

    public static boolean f(int i) {
        a("request clear the task[%d] data in the database", Integer.valueOf(i));
        return false;
    }

    public static boolean c() {
        a("request clear all tasks data in the database", new Object[0]);
        return false;
    }

    private static void a(String str, Object... objArr) {
        FileDownloadLog.d(DownloadServiceNotConnectedHelper.class, str + f6461a + b, objArr);
    }
}
