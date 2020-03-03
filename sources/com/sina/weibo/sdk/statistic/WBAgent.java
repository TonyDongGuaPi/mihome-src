package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import java.util.Map;

public class WBAgent {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8835a = "WBAgent";

    public static void a(boolean z) {
        StatisticConfig.b = z;
    }

    public static void a(long j) {
        StatisticConfig.c = j;
    }

    public static void a(String str) {
        StatisticConfig.a(str);
    }

    public static void b(String str) {
        StatisticConfig.b(str);
    }

    public static void b(long j) throws Exception {
        StatisticConfig.a(j);
    }

    public static void c(long j) {
        StatisticConfig.b(j);
    }

    public static void b(boolean z) {
        StatisticConfig.a(z);
    }

    public static void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            WBAgentHandler.a().a(str);
        }
    }

    public static void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            WBAgentHandler.a().b(str);
        }
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.c(f8835a, "unexpected null context in onResume");
        } else {
            WBAgentHandler.a().a(context);
        }
    }

    public static void b(Context context) {
        if (context == null) {
            LogUtil.c(f8835a, "unexpected null context in onResume");
        } else {
            WBAgentHandler.a().b(context);
        }
    }

    public static void a(Object obj, String str) {
        a(obj, str, (Map<String, String>) null);
    }

    public static void a(Object obj, String str, Map<String, String> map) {
        if (obj == null) {
            LogUtil.c(f8835a, "unexpected null page or activity in onEvent");
        } else if (str == null) {
            LogUtil.c(f8835a, "unexpected null eventId in onEvent");
        } else {
            if (obj instanceof Context) {
                obj = obj.getClass().getName();
            }
            WBAgentHandler.a().a((String) obj, str, map);
        }
    }

    public static void c(Context context) {
        if (context == null) {
            LogUtil.c(f8835a, "unexpected null context in uploadAppLogs");
        } else {
            WBAgentHandler.a().c(context);
        }
    }

    public static void d(Context context) {
        if (context == null) {
            LogUtil.c(f8835a, "unexpected null context in onStop");
        } else {
            WBAgentHandler.a().d(context);
        }
    }

    public static void a() {
        WBAgentHandler.a().b();
    }

    public static void a(boolean z, boolean z2) {
        LogUtil.f8845a = z;
        StatisticConfig.a(z2);
    }

    public static void a(Context context, String str, String str2, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c(f8835a, "registerApptoAd appKey is  null  ");
            return;
        }
        a(str);
        b(str2);
        WBAgentHandler.a().a(context, str, map);
    }
}
