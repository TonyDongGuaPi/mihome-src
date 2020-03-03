package com.tencent.wxop.stat.common;

import android.content.Context;
import com.tencent.wxop.stat.a;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class b {

    /* renamed from: a  reason: collision with root package name */
    static d f9316a;
    private static StatLogger d = k.b();
    private static JSONObject e = new JSONObject();
    Integer b = null;
    String c = null;

    public b(Context context) {
        try {
            a(context);
            this.b = k.m(context.getApplicationContext());
            this.c = a.a(context).b();
        } catch (Throwable th) {
            d.b(th);
        }
    }

    static synchronized d a(Context context) {
        d dVar;
        synchronized (b.class) {
            if (f9316a == null) {
                f9316a = new d(context.getApplicationContext());
            }
            dVar = f9316a;
        }
        return dVar;
    }

    public static void a(Context context, Map<String, String> map) {
        if (map != null) {
            for (Map.Entry entry : new HashMap(map).entrySet()) {
                e.put((String) entry.getKey(), entry.getValue());
            }
        }
    }

    public void a(JSONObject jSONObject, Thread thread) {
        String str;
        Object obj;
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (f9316a != null) {
                f9316a.a(jSONObject2, thread);
            }
            q.a(jSONObject2, "cn", this.c);
            if (this.b != null) {
                jSONObject2.put(BaseInfo.KEY_THREAD_NAME, this.b);
            }
            if (thread == null) {
                str = "ev";
                obj = jSONObject2;
            } else {
                str = "errkv";
                obj = jSONObject2.toString();
            }
            jSONObject.put(str, obj);
            if (e != null && e.length() > 0) {
                jSONObject.put("eva", e);
            }
        } catch (Throwable th) {
            d.b(th);
        }
    }
}
