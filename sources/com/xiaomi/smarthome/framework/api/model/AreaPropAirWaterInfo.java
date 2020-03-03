package com.xiaomi.smarthome.framework.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AreaPropAirWaterInfo {
    private static final String A = "prop.tds_out.cnt";
    private static final String B = "temp_range";
    private static final String C = "weather";
    private static final String D = "sd";
    private static final String E = "temperature";
    private static final String F = "temp_time";
    private static final String G = "area_id";

    /* renamed from: a  reason: collision with root package name */
    public static final String f16446a = "prop.humidity";
    public static final String b = "prop.humidity.cnt";
    private static final String s = "-";
    private static final String t = "aqi_out";
    private static final String u = "pm2.5";
    private static final String v = "prop.aqi";
    private static final String w = "prop.tds_in";
    private static final String x = "prop.tds_out";
    private static final String y = "prop.aqi.cnt";
    private static final String z = "prop.tds_in.cnt";
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;

    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        return "-";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r3 = java.lang.Integer.parseInt(r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "-"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x0009
            return r3
        L_0x0009:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0025
            double r0 = java.lang.Double.parseDouble(r3)     // Catch:{ Throwable -> 0x0019 }
            long r0 = java.lang.Math.round(r0)     // Catch:{ Throwable -> 0x0019 }
            int r3 = (int) r0
            goto L_0x001d
        L_0x0019:
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Throwable -> 0x0022 }
        L_0x001d:
            java.lang.String r3 = java.lang.String.valueOf(r3)
            return r3
        L_0x0022:
            java.lang.String r3 = "-"
            return r3
        L_0x0025:
            java.lang.String r3 = "-"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.model.AreaPropAirWaterInfo.a(java.lang.String):java.lang.String");
    }

    public String a() {
        return a(this.d);
    }

    public String b() {
        return a(this.e);
    }

    public String c() {
        return a(this.c);
    }

    public static JSONObject a(AreaPropAirWaterInfo areaPropAirWaterInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(v, areaPropAirWaterInfo.c);
            jSONObject.put(w, areaPropAirWaterInfo.d);
            jSONObject.put(x, areaPropAirWaterInfo.e);
            jSONObject.put(y, areaPropAirWaterInfo.f);
            jSONObject.put(z, areaPropAirWaterInfo.g);
            jSONObject.put(A, areaPropAirWaterInfo.h);
            jSONObject.put("pm2.5", areaPropAirWaterInfo.i);
            jSONObject.put("aqi_out", areaPropAirWaterInfo.j);
            jSONObject.put("temp_range", areaPropAirWaterInfo.k);
            jSONObject.put("weather", areaPropAirWaterInfo.l);
            jSONObject.put("sd", areaPropAirWaterInfo.m);
            jSONObject.put("prop.humidity", areaPropAirWaterInfo.n);
            jSONObject.put("prop.humidity.cnt", areaPropAirWaterInfo.o);
            jSONObject.put("temperature", areaPropAirWaterInfo.p);
            jSONObject.put(F, areaPropAirWaterInfo.q);
            jSONObject.put(G, areaPropAirWaterInfo.r);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    private static String a(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optString(str, "-") : "-";
    }

    public static AreaPropAirWaterInfo a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        AreaPropAirWaterInfo areaPropAirWaterInfo = new AreaPropAirWaterInfo();
        areaPropAirWaterInfo.c = a(jSONObject, v);
        areaPropAirWaterInfo.d = a(jSONObject, w);
        areaPropAirWaterInfo.e = a(jSONObject, x);
        areaPropAirWaterInfo.f = a(jSONObject, y);
        areaPropAirWaterInfo.g = a(jSONObject, z);
        areaPropAirWaterInfo.h = a(jSONObject, A);
        areaPropAirWaterInfo.i = a(jSONObject, "pm2.5");
        areaPropAirWaterInfo.j = a(jSONObject, "aqi_out");
        areaPropAirWaterInfo.k = a(jSONObject, "temp_range");
        areaPropAirWaterInfo.l = a(jSONObject, "weather");
        areaPropAirWaterInfo.m = AreaPropInfo.b(jSONObject.optString("sd"));
        areaPropAirWaterInfo.n = AreaPropInfo.b(jSONObject.optString("prop.humidity"));
        areaPropAirWaterInfo.o = jSONObject.optString("prop.humidity.cnt", "-");
        areaPropAirWaterInfo.p = a(jSONObject, "temperature");
        areaPropAirWaterInfo.q = a(jSONObject, F);
        return areaPropAirWaterInfo;
    }

    public String toString() {
        return a(this).toString();
    }
}
