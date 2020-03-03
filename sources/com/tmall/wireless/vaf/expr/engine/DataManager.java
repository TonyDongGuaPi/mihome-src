package com.tmall.wireless.vaf.expr.engine;

import android.text.TextUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DataManager {

    /* renamed from: a  reason: collision with root package name */
    private JSONObject f9346a = new JSONObject();

    public DataManager() {
        try {
            this.f9346a.put("time", 10);
        } catch (JSONException unused) {
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.f9346a = jSONObject;
        }
    }

    public void a(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.f9346a.put(str, obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(Map<String, Object> map) {
        if (map != null) {
            for (String next : map.keySet()) {
                try {
                    this.f9346a.put(next, map.get(next));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object a(String str) {
        return this.f9346a.opt(str);
    }

    public void b(String str, Object obj) {
        try {
            this.f9346a.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
