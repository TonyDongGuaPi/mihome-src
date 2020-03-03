package com.tencent.wxop.stat.common;

import com.alipay.mobile.security.bio.api.BioDetector;
import com.mobikwik.sdk.lib.Constants;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f9315a = null;
    private String b = null;
    private String c = null;
    private String d = "0";
    private int e;
    private int f = 0;
    private long g = 0;

    public a() {
    }

    public a(String str, String str2, int i) {
        this.f9315a = str;
        this.b = str2;
        this.e = i;
    }

    /* access modifiers changed from: package-private */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            q.a(jSONObject, BioDetector.EXT_KEY_UI, this.f9315a);
            q.a(jSONObject, "mc", this.b);
            q.a(jSONObject, Constants.MID, this.d);
            q.a(jSONObject, "aid", this.c);
            jSONObject.put("ts", this.g);
            jSONObject.put(DeviceTagInterface.m, this.f);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void a(int i) {
        this.e = i;
    }

    public String b() {
        return this.f9315a;
    }

    public String c() {
        return this.b;
    }

    public int d() {
        return this.e;
    }

    public String toString() {
        return a().toString();
    }
}
