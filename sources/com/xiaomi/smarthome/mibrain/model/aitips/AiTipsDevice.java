package com.xiaomi.smarthome.mibrain.model.aitips;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AiTipsDevice {

    /* renamed from: a  reason: collision with root package name */
    private String f10658a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private List<Tips> h;

    public static AiTipsDevice a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        AiTipsDevice aiTipsDevice = new AiTipsDevice();
        aiTipsDevice.a(jSONObject.optString("category"));
        aiTipsDevice.b(jSONObject.optString("name"));
        aiTipsDevice.c(jSONObject.optString("device_type"));
        aiTipsDevice.d(jSONObject.optString("model"));
        aiTipsDevice.e(jSONObject.optString("icon_url"));
        aiTipsDevice.f(jSONObject.optString("icon_url_480"));
        aiTipsDevice.g(jSONObject.optString("did"));
        if (!jSONObject.isNull("tips") && (optJSONArray = jSONObject.optJSONArray("tips")) != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                Tips a2 = Tips.a(optJSONArray.optJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            aiTipsDevice.a((List<Tips>) arrayList);
        }
        if (TextUtils.isEmpty(aiTipsDevice.f) || TextUtils.isEmpty(aiTipsDevice.g) || aiTipsDevice.h == null || aiTipsDevice.h.isEmpty()) {
            return null;
        }
        return aiTipsDevice;
    }

    public String a() {
        return this.f10658a;
    }

    public void a(String str) {
        this.f10658a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public void f(String str) {
        this.f = str;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public void g(String str) {
        this.g = str;
    }

    public List<Tips> h() {
        return this.h;
    }

    public void a(List<Tips> list) {
        this.h = list;
    }
}
