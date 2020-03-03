package com.xiaomi.youpin.utils;

import android.os.SystemClock;
import java.util.Stack;
import org.cybergarage.http.HTTP;

public class TimeTraceUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23777a = "YouPinTime";
    static final boolean b = true;
    static Stack<Long> c = new Stack<>();

    public static void a() {
        c.clear();
    }

    public static void b() {
        c.push(Long.valueOf(SystemClock.elapsedRealtime()));
    }

    public static void a(String str) {
        if (!c.empty()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long longValue = c.pop().longValue();
            LogUtils.b(f23777a, str + HTTP.HEADER_LINE_DELIM + (elapsedRealtime - longValue));
        }
    }
}
