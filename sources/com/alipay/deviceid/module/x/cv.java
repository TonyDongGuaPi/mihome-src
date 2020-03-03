package com.alipay.deviceid.module.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cv extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f923a = {"type", "pn", "cn", "et", "ad", "t", "seq", "num"};
    private Map<String, Object> b = new HashMap();
    private List<Map<String, String>> c = new ArrayList();

    public cv(String str, String str2, int i) {
        this.b.put("type", str2);
        this.b.put("pn", cd.a(str) ? "-" : str);
        this.b.put("cn", "-");
        this.b.put("et", "pe");
        this.b.put("ad", this.c);
        this.b.put("seq", String.valueOf(i));
        this.b.put("num", 1);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        this.b.put("num", String.valueOf(this.b.get("num")));
        for (String str : f923a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            } else if (obj != null && (obj instanceof ch)) {
                jSONObject.put(str, ((ch) obj).a());
            } else if (obj != null && (obj instanceof Map)) {
                JSONObject jSONObject2 = new JSONObject();
                Map map = (Map) obj;
                for (String str2 : map.keySet()) {
                    jSONObject2.put(str2, map.get(str2));
                }
                jSONObject.put(str, jSONObject2);
            } else if (obj != null && (obj instanceof List)) {
                List list = (List) obj;
                if (list.size() > 20) {
                    list = list.subList(list.size() - 20, list.size());
                }
                jSONObject.put(str, cd.a((List<Map<String, String>>) list));
            }
        }
        return jSONObject;
    }

    public final synchronized void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("pr", str);
        hashMap.put("t", String.valueOf(System.currentTimeMillis()));
        this.c.add(hashMap);
    }
}
