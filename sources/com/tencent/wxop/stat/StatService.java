package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Map;
import java.util.Properties;

public class StatService {
    public static Properties a(String str) {
        return StatServiceImpl.b(str);
    }

    public static void a() {
        StatServiceImpl.c();
    }

    public static void a(Context context) {
        StatServiceImpl.b(context, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, int i) {
        StatServiceImpl.a(context, i);
    }

    public static void a(Context context, int i, String str, Properties properties) {
        StatServiceImpl.a(context, str, properties, i, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, StatAccount statAccount) {
        StatServiceImpl.a(context, statAccount, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, StatAppMonitor statAppMonitor) {
        StatServiceImpl.a(context, statAppMonitor, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, StatGameUser statGameUser) {
        StatServiceImpl.a(context, statGameUser, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, String str) {
        StatServiceImpl.a(context, str, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, String str, Properties properties) {
        StatServiceImpl.a(context, str, properties, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, String str, String... strArr) {
        StatServiceImpl.a(context, str, (StatSpecifyReportedInfo) null, strArr);
    }

    public static void a(Context context, Throwable th) {
        StatServiceImpl.a(context, th, (StatSpecifyReportedInfo) null);
    }

    public static void a(Context context, Map<String, String> map) {
        StatServiceImpl.a(context, map);
    }

    public static void a(String str, Properties properties) {
        StatServiceImpl.a(str, properties);
    }

    public static boolean a(Context context, String str, String str2) {
        return StatServiceImpl.a(context, str, str2, (StatSpecifyReportedInfo) null);
    }

    public static void b(Context context) {
        StatServiceImpl.c(context, (StatSpecifyReportedInfo) null);
    }

    public static void b(Context context, String str) {
        StatServiceImpl.b(context, str, (StatSpecifyReportedInfo) null);
    }

    public static void b(Context context, String str, Properties properties) {
        StatServiceImpl.b(context, str, properties, (StatSpecifyReportedInfo) null);
    }

    public static void b(Context context, String str, String... strArr) {
        StatServiceImpl.b(context, str, (StatSpecifyReportedInfo) null, strArr);
    }

    public static void b(Context context, Map<String, Integer> map) {
        StatServiceImpl.a(context, map, (StatSpecifyReportedInfo) null);
    }

    public static void c(Context context) {
        StatServiceImpl.d(context, (StatSpecifyReportedInfo) null);
    }

    public static void c(Context context, String str) {
        StatServiceImpl.c(context, str, (StatSpecifyReportedInfo) null);
    }

    public static void c(Context context, String str, Properties properties) {
        StatServiceImpl.c(context, str, properties, (StatSpecifyReportedInfo) null);
    }

    public static void c(Context context, String str, String... strArr) {
        StatServiceImpl.c(context, str, (StatSpecifyReportedInfo) null, strArr);
    }

    public static void d(Context context) {
        StatServiceImpl.h(context);
    }

    public static void d(Context context, String str) {
        StatServiceImpl.d(context, str, (StatSpecifyReportedInfo) null);
    }

    public static void e(Context context) {
        StatServiceImpl.i(context);
    }

    public static void f(Context context) {
        StatServiceImpl.b(context);
    }

    public static void g(Context context) {
        StatServiceImpl.e(context, (StatSpecifyReportedInfo) null);
    }

    public static void h(Context context) {
        StatServiceImpl.f(context);
    }
}
