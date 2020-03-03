package com.xiaomi.mistatistic.sdk.data;

import android.text.TextUtils;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class h extends AbstractEvent {
    private String b;
    private String c;
    private String d;

    public h(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public String a() {
        return this.b;
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("category", this.b);
        jSONObject.put("key", this.c);
        jSONObject.put("type", BindingXConstants.j);
        jSONObject.put("value", this.d);
        return jSONObject;
    }

    public StatEventPojo c() {
        StatEventPojo statEventPojo = new StatEventPojo();
        statEventPojo.f12065a = this.b;
        statEventPojo.c = this.c;
        statEventPojo.b = this.f12063a;
        statEventPojo.e = this.d;
        statEventPojo.d = BindingXConstants.j;
        return statEventPojo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (!TextUtils.equals(this.b, hVar.b) || !TextUtils.equals(this.c, hVar.c) || !TextUtils.equals(this.d, hVar.d)) {
            return false;
        }
        return true;
    }
}
