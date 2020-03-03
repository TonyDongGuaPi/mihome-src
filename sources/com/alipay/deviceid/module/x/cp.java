package com.alipay.deviceid.module.x;

import android.util.Log;
import com.alipay.rds.v2.face.RDSClient;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

public final class cp extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f917a = {"t", "num", "action"};
    public Map<String, Object> b;
    public AtomicInteger c;
    public AtomicInteger d;
    public cu e;
    public ch f;
    private long g;

    public cp() {
        this((byte) 0);
        this.e = new cu();
        this.b.put("t", String.valueOf(this.g));
        this.b.put("num", String.valueOf(this.c));
        this.b.put("action", this.e);
    }

    private cp(byte b2) {
        this.b = new HashMap();
        this.g = System.currentTimeMillis();
        this.c = new AtomicInteger(0);
        this.d = new AtomicInteger(0);
        this.f = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f917a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException e2) {
                    if (RDSClient.isDebug()) {
                        e2.toString();
                    }
                }
            } else if (obj != null && (obj instanceof ch)) {
                try {
                    jSONObject.put(str, ((ch) obj).a());
                } catch (JSONException e3) {
                    if (RDSClient.isDebug()) {
                        e3.toString();
                    }
                }
            }
        }
        return jSONObject;
    }

    public final synchronized void a(String str, String str2) {
        String b2 = b(str, str2);
        this.c.incrementAndGet();
        if (this.f == null) {
            Log.e("RDSInfo", "[-] pageEnter first");
        } else if (this.f == null || !(this.f instanceof cr)) {
            cr crVar = new cr(str, str2, b2, this.d.incrementAndGet());
            crVar.c();
            this.e.a(crVar);
            this.f = crVar;
        } else {
            this.e.a("", false, 0.0d);
        }
    }

    public final synchronized void a(String str, String str2, String str3) {
        String b2 = b(str, str2);
        this.c.incrementAndGet();
        if (this.f == null) {
            Log.e("RDSInfo", "[-] pageEnter first");
        } else if (this.f == null || !(this.f instanceof cs)) {
            cs csVar = new cs(str, str2, b2, this.d.incrementAndGet());
            csVar.b(str3);
            this.e.a(csVar);
            this.f = csVar;
        } else {
            this.e.a(str3, false, 0.0d);
        }
    }

    public final synchronized void a(String str, String str2, boolean z) {
        String b2 = b(str, str2);
        this.c.incrementAndGet();
        if (this.f == null) {
            Log.e("RDSInfo", "[-] pageEnter first");
        } else if (this.f == null || !(this.f instanceof ct)) {
            ct ctVar = new ct(str, str2, b2, this.d.incrementAndGet());
            ctVar.a(z);
            this.e.a(ctVar);
            this.f = ctVar;
        } else {
            this.e.a("", z, 0.0d);
        }
    }

    public static String b(String str, String str2) {
        if (cd.a(str)) {
            return "2";
        }
        return cd.a(str2) ? "1" : "0";
    }
}
