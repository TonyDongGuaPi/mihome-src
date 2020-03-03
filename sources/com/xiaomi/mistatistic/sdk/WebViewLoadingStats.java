package com.xiaomi.mistatistic.sdk;

import com.xiaomi.mistatistic.sdk.controller.c;
import com.xiaomi.mistatistic.sdk.controller.h;
import com.xiaomi.mistatistic.sdk.controller.k;

public class WebViewLoadingStats {
    public static void a(String str) {
        k.b(c.a(), str, System.currentTimeMillis());
    }

    public static void b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long a2 = k.a(c.a(), str, 0);
        long j = currentTimeMillis - a2;
        if (a2 <= 0 || j < 0) {
            h.b("", "web_view_page_loading record time is invalid, record startTime is : %d,record end time is : %d", Long.valueOf(a2), Long.valueOf(currentTimeMillis));
            return;
        }
        k.b(c.a(), str, 0);
        h.b("", "web_view_page_loading time is:%d, url is:%s", Long.valueOf(j), str);
        MiStatInterface.a("web_view_page_loading_time", str, j);
    }
}
