package com.xiaomi.jr.stats;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.DeviceInfoHelper;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.stat.NetAvailableEvent;
import java.util.HashMap;
import java.util.Map;

public class StatUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1451a = "monitor_";
    private static final String b = "StatUtils";
    private static final String c = "record_time";
    private static Map<String, Long> d = new HashMap();

    public static void a(Activity activity, String str) {
    }

    public static void b(Activity activity, String str) {
    }

    public static void b(String str) {
    }

    public static void c(String str) {
    }

    public static void a(Context context, String str, String str2, String str3, HttpRequester httpRequester) {
        MiStatSdk.a(context, str, str2, str3);
        MifiStatUtils.a(httpRequester);
    }

    public static void a(String str) {
        MiStatSdk.a(str);
    }

    public static void a(Context context, int i, int i2, Map<String, String> map, Bundle bundle) {
        a(context, context.getString(i), context.getString(i2), map, bundle);
    }

    public static void a(Context context, String str, String str2, Map<String, String> map, Bundle bundle) {
        HashMap hashMap = new HashMap(DeviceInfoHelper.a(context));
        if (map != null) {
            hashMap.putAll(map);
        }
        if (bundle != null) {
            String string = bundle.getString("from");
            if (!TextUtils.isEmpty(string)) {
                hashMap.put("from", string);
            }
            String string2 = bundle.getString("source");
            if (!TextUtils.isEmpty(string2)) {
                hashMap.put("source", string2);
            }
        }
        a(str, str2, (Map<String, String>) hashMap);
        MifiStatUtils.a(str, str2, hashMap);
    }

    public static void a(String str, String... strArr) {
        if (strArr == null || strArr.length < 2) {
            a("debug", str, (Map<String, String>) null);
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < strArr.length; i += 2) {
            hashMap.put(strArr[i], strArr[i + 1]);
        }
        a("debug", str, (Map<String, String>) hashMap);
    }

    public static void a(String str, String str2, Map<String, String> map) {
        MiStatSdk.a(str, str2, map);
    }

    public static void d(String str) {
        MifiLog.b(b, "startRecordTime " + str);
        d.put(str, Long.valueOf(System.currentTimeMillis()));
    }

    public static void e(String str) {
        if (!d.containsKey(str)) {
            MifiLog.b(b, "pauseRecordTime " + str + " not exist.");
            return;
        }
        long longValue = d.get(str).longValue();
        if (longValue > 0) {
            d.put(str, Long.valueOf(longValue - System.currentTimeMillis()));
            MifiLog.b(b, "pauseRecordTime " + str + " " + d.get(str));
            return;
        }
        MifiLog.b(b, "pauseRecordTime " + str + " already paused.");
    }

    public static void f(String str) {
        if (!d.containsKey(str)) {
            MifiLog.b(b, "resumeRecordTime " + str + " not exist.");
            return;
        }
        long longValue = d.get(str).longValue();
        if (longValue > 0) {
            MifiLog.b(b, "resumeRecordTime " + str + " already running.");
            return;
        }
        d.put(str, Long.valueOf(System.currentTimeMillis() + longValue));
        MifiLog.b(b, "resumeRecordTime " + str);
    }

    public static void g(String str) {
        MifiLog.b(b, "cancelRecordTime " + str);
        d.remove(str);
    }

    public static void a(Context context, String str) {
        a(context, str, (Map<String, String>) null);
    }

    public static void a(Context context, String str, Map<String, String> map) {
        a(context, str, map, 1000, 1);
    }

    public static void a(Context context, String str, Map<String, String> map, long j, int i) {
        if (!d.containsKey(str)) {
            MifiLog.b(b, "endRecordTime " + str + " not exist.");
            return;
        }
        if (d.get(str).longValue() <= 0) {
            MifiLog.b(b, "endRecordTime " + str + " paused, resume it.");
            d.put(str, Long.valueOf(System.currentTimeMillis() + d.get(str).longValue()));
        }
        long currentTimeMillis = System.currentTimeMillis() - d.get(str).longValue();
        MifiLog.b(b, "endRecordTime " + str + ": " + currentTimeMillis);
        b(context, str, currentTimeMillis, map, j, i);
        d.remove(str);
    }

    public static void a(Context context, String str, long j) {
        a(context, str, j, (Map<String, String>) null, 1000, 1);
    }

    public static void a(Context context, String str, long j, Map<String, String> map) {
        a(context, str, j, map, 1000, 1);
    }

    public static void a(Context context, String str, long j, Map<String, String> map, long j2, int i) {
        MifiLog.b(b, "setRecordTime " + str + ": " + j);
        b(context, str, j, map, j2, i);
        d.remove(str);
    }

    private static void b(Context context, String str, long j, Map<String, String> map, long j2, int i) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("duration", String.format("%." + i + "f", new Object[]{Float.valueOf((((float) j) * 1.0f) / ((float) j2))}));
        a(context, c, str, map, (Bundle) null);
    }

    public static boolean h(String str) {
        return d.containsKey(str);
    }

    private static String a(@NonNull String str, @NonNull String str2, Activity activity) {
        String queryParameter = Uri.parse(str2).getQueryParameter(str);
        return (!TextUtils.isEmpty(queryParameter) || activity == null) ? queryParameter : activity.getIntent().getStringExtra(str);
    }

    public static String a(@NonNull String str, Activity activity) {
        return activity != null ? UrlUtils.a(UrlUtils.a(str, "from", activity.getIntent().getStringExtra("from")), "source", activity.getIntent().getStringExtra("source")) : str;
    }

    public static Bundle a(@NonNull Bundle bundle, @NonNull String str, Activity activity) {
        String a2 = a("from", str, activity);
        if (!TextUtils.isEmpty(a2)) {
            bundle.putString("from", a2);
        }
        String a3 = a("source", str, activity);
        if (!TextUtils.isEmpty(a3)) {
            bundle.putString("source", a3);
        }
        return bundle;
    }

    public static void a(String str, long j, int i, int i2, int i3, int i4, String str2) {
        MiStatSdk.a(new NetAvailableEvent.Builder().flag(str).requestStartTime(j).resultType(i).responseCode(i2).statusCode(i3).retryCount(i4).exception(str2).build());
    }
}
