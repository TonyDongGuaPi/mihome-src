package com.alipay.deviceid.module.x;

import android.content.Context;
import com.coloros.mcssdk.mode.CommandMessage;
import java.util.HashMap;
import java.util.Map;
import org.cybergarage.upnp.RootDescription;
import org.json.JSONException;
import org.json.JSONObject;

public final class cj extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f908a = {"asdk", "board", "brand", "device", "displayid", "em", "manufacturer", "model", "name", "incremental", "os", "qemu", "osRelease", "kerver", RootDescription.ROOT_ELEMENT, CommandMessage.TYPE_TAGS, "processor", "pf", "pn", "pm"};
    private Map<String, Object> b;

    public cj(Context context) {
        this();
        l.a();
        k a2 = k.a();
        this.b.put("asdk", l.m());
        this.b.put("board", l.d());
        this.b.put("brand", l.e());
        this.b.put("device", l.f());
        this.b.put("displayid", l.g());
        this.b.put("em", l.a(context) ? "true" : "false");
        this.b.put("manufacturer", l.i());
        this.b.put("model", l.j());
        this.b.put("name", l.k());
        this.b.put("incremental", l.h());
        this.b.put("os", "android");
        this.b.put("qemu", l.a("ro.kernel.qemu", "0"));
        this.b.put("osRelease", l.l());
        this.b.put("kerver", k.n());
        this.b.put(RootDescription.ROOT_ELEMENT, l.c() ? "true" : "false");
        this.b.put(CommandMessage.TYPE_TAGS, l.n());
        this.b.put("processor", k.i());
        this.b.put("pf", k.h());
        this.b.put("pn", a2.g());
        this.b.put("pm", k.f());
    }

    private cj() {
        this.b = new HashMap();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        for (String str : f908a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            }
        }
        return jSONObject;
    }
}
