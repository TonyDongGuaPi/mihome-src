package com.xiaomi.smarthome.mibrain.model.aitips;

import org.json.JSONObject;

public class ContentDetail {

    /* renamed from: a  reason: collision with root package name */
    private String f10659a;
    private SlotValue b;

    public static ContentDetail a(JSONObject jSONObject) {
        ContentDetail contentDetail = new ContentDetail();
        if (jSONObject == null) {
            return contentDetail;
        }
        contentDetail.a(jSONObject.optString("content"));
        contentDetail.a(SlotValue.a(jSONObject.optJSONObject("slot_value")));
        return contentDetail;
    }

    public String a() {
        return this.f10659a;
    }

    public void a(String str) {
        this.f10659a = str;
    }

    public SlotValue b() {
        return this.b;
    }

    public void a(SlotValue slotValue) {
        this.b = slotValue;
    }
}
