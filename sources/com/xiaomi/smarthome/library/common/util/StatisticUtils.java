package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import com.xiaomi.smarthome.application.SHApplication;

public class StatisticUtils {

    /* renamed from: a  reason: collision with root package name */
    protected static StatisticUtils f18735a = new StatisticUtils();

    /* access modifiers changed from: protected */
    public void b(Context context, String str) {
    }

    /* access modifiers changed from: protected */
    public void b(Context context, String str, int i) {
    }

    /* access modifiers changed from: protected */
    public void b(Context context, String str, String str2, String str3) {
    }

    public static void a(StatisticUtils statisticUtils) {
        f18735a = statisticUtils;
    }

    public static StatisticUtils a() {
        return f18735a;
    }

    public static void a(Context context, String str) {
        a().b(context, str);
    }

    public static void a(Context context, String str, int i) {
        a().b(context, str, i);
    }

    public static void a(Context context, String str, String str2, String str3) {
        a().b(context, str, str2, str3);
    }

    public static void a(String str) {
        a(SHApplication.getAppContext(), str);
    }
}
