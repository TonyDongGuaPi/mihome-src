package com.xiaomi.payment.recharge;

import org.json.JSONException;
import org.json.JSONObject;

public interface RechargeMethodParser {
    RechargeMethod a(String str, JSONObject jSONObject) throws JSONException;
}
