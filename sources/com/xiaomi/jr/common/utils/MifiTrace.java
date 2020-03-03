package com.xiaomi.jr.common.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.os.Trace;
import java.io.File;

public final class MifiTrace {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f10366a = false;

    static {
        boolean z = f10366a;
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append("/mifi_trace");
        f10366a = z | (new File(sb.toString()).exists() && Build.VERSION.SDK_INT >= 18);
    }

    @SuppressLint({"NewApi"})
    public static void a(String str) {
        if (f10366a) {
            Trace.beginSection(str);
        }
    }

    @SuppressLint({"NewApi"})
    public static void a() {
        if (f10366a) {
            Trace.endSection();
        }
    }
}
