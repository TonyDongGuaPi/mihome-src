package com.alipay.deviceid.module.x;

import com.xiaomi.stat.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cr extends ch {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f919a = {"type", "pn", "cn", "et", "ad", "t", "seq", "num"};
    private Map<String, Object> b = new HashMap();
    private List<Map<String, String>> c = new ArrayList();

    public cr(String str, String str2, String str3, int i) {
        this.b.put("type", str3);
        this.b.put("pn", cd.a(str) ? "-" : str);
        this.b.put("cn", cd.a(str2) ? "-" : str2);
        this.b.put("et", d.p);
        this.b.put("ad", this.c);
        this.b.put("t", String.valueOf(System.currentTimeMillis()));
        this.b.put("seq", String.valueOf(i));
        this.b.put("num", 1);
    }

    public final synchronized void b() {
        this.b.put("num", Integer.valueOf(((Integer) this.b.get("num")).intValue() + 1));
        c();
    }

    public final synchronized void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("t", String.valueOf(System.currentTimeMillis()));
        this.c.add(hashMap);
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        this.b.put("num", String.valueOf(this.b.get("num")));
        for (String str : f919a) {
            Object obj = this.b.get(str);
            if (obj != null && (obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException unused) {
                }
            } else if (obj != null && (obj instanceof ch)) {
                jSONObject.put(str, ((ch) obj).a());
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
}
