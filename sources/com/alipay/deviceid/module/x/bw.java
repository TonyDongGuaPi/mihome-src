package com.alipay.deviceid.module.x;

import android.content.Context;
import org.json.JSONObject;

public final class bw {
    public static void a(Context context, bx bxVar) {
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", e.c(bxVar.f900a));
                jSONObject.put("imsi", e.c(bxVar.b));
                jSONObject.put("mac", e.c(bxVar.c));
                jSONObject.put("bluetoothmac", e.c(bxVar.d));
                jSONObject.put("gsi", e.c(bxVar.e));
                String jSONObject2 = jSONObject.toString();
                if (!e.a("device_feature_file_name") && !e.a("device_feature_file_key")) {
                    try {
                        String a2 = i.a(i.a(), jSONObject2);
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("device_feature_file_key", a2);
                        t.a("device_feature_file_name", jSONObject3.toString());
                    } catch (Exception unused) {
                    }
                }
                cb.a(context, "device_feature_prefs_name", "device_feature_prefs_key", jSONObject2);
            } catch (Exception e) {
                z.a((Throwable) e);
            }
        }
    }

    public static bx a(Context context) {
        if (context == null) {
            return null;
        }
        String a2 = cb.a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (e.a(a2)) {
            a2 = cb.a("device_feature_file_name", "device_feature_file_key");
        }
        if (e.a(a2)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a2);
            bx bxVar = new bx();
            bxVar.f900a = jSONObject.getString("imei");
            bxVar.b = jSONObject.getString("imsi");
            bxVar.c = jSONObject.getString("mac");
            bxVar.d = jSONObject.getString("bluetoothmac");
            bxVar.e = jSONObject.getString("gsi");
            return bxVar;
        } catch (Exception e) {
            z.a((Throwable) e);
            return null;
        }
    }
}
