package com.xiaomi.smarthome.family.api.entity;

import com.xiaomi.smarthome.framework.openapi.ApiConst;
import org.json.JSONObject;

public class WXDeviceShareLinkResult {

    /* renamed from: a  reason: collision with root package name */
    public String f15876a;
    public int b = -999;
    public String c;

    public static WXDeviceShareLinkResult a(JSONObject jSONObject) {
        WXDeviceShareLinkResult wXDeviceShareLinkResult = new WXDeviceShareLinkResult();
        if (jSONObject != null) {
            if (jSONObject.has("code")) {
                wXDeviceShareLinkResult.b = jSONObject.optInt("code", -999);
            }
            if (jSONObject.has("msg")) {
                wXDeviceShareLinkResult.c = jSONObject.optString("msg");
            }
            if (jSONObject.has(ApiConst.l)) {
                wXDeviceShareLinkResult.f15876a = jSONObject.optString(ApiConst.l);
            }
        }
        return wXDeviceShareLinkResult;
    }
}
