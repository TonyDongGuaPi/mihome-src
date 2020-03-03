package com.xiaomi.smarthome.mibrain.model.aitips;

import org.json.JSONObject;

public class SlotValue {

    /* renamed from: a  reason: collision with root package name */
    private boolean f10661a;

    public static SlotValue a(JSONObject jSONObject) {
        SlotValue slotValue = new SlotValue();
        if (jSONObject != null && !jSONObject.isNull("slot_value")) {
            slotValue.a(jSONObject.optBoolean("on"));
        }
        return slotValue;
    }

    public boolean a() {
        return this.f10661a;
    }

    public void a(boolean z) {
        this.f10661a = z;
    }
}
