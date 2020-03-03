package com.huawei.hms.support.b;

import android.content.Context;
import android.text.TextUtils;
import com.hianalytics.android.v1.HiAnalytics;
import com.huawei.hms.c.g;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f5893a;
    private static final Object b = new Object();

    public static a a() {
        a aVar;
        synchronized (b) {
            if (f5893a == null) {
                f5893a = new a();
            }
            aVar = f5893a;
        }
        return aVar;
    }

    public void a(Context context, String str, Map<String, String> map) {
        if (!b()) {
            String a2 = a(map);
            if (!TextUtils.isEmpty(a2)) {
                HiAnalytics.a(context, str, a2);
            }
        }
    }

    private String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry next : map.entrySet()) {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
        } catch (JSONException e) {
            com.huawei.hms.support.log.a.d("HiAnalyticsUtil", "AnalyticsHelper create json exception" + e.getMessage());
        }
        return jSONObject.toString();
    }

    public boolean b() {
        if (g.a()) {
            return false;
        }
        com.huawei.hms.support.log.a.a("HiAnalyticsUtil", "not ChinaROM ");
        return true;
    }
}
