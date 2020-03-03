package com.mi.mistatistic.sdk;

import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.Logger;
import com.mi.mistatistic.sdk.controller.PrefPersistUtils;
import com.mi.mistatistic.sdk.controller.TimeUtil;

public class WebViewLoadingStats {
    public static void a(String str) {
        PrefPersistUtils.b(ApplicationContextHolder.a(), str, TimeUtil.a().b());
    }

    public static void b(String str) {
        long b = TimeUtil.a().b();
        long a2 = PrefPersistUtils.a(ApplicationContextHolder.a(), str, 0);
        long j = b - a2;
        if (a2 <= 0 || j < 0) {
            Logger.c("", "web_view_page_loading record time is invalid, record startTime is : %d,record end time is : %d", Long.valueOf(a2), Long.valueOf(b));
            return;
        }
        PrefPersistUtils.b(ApplicationContextHolder.a(), str, 0);
        Logger.c("", "web_view_page_loading time is:%d, url is:%s", Long.valueOf(j), str);
    }
}
