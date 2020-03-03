package com.xiaomi.smarthome.config.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SupportBleGatewayFirmwareVersion {

    /* renamed from: a  reason: collision with root package name */
    public String f13958a;
    public String b;

    public static List<SupportBleGatewayFirmwareVersion> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("model");
                String optString2 = optJSONObject.optString("firmware_version");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                    SupportBleGatewayFirmwareVersion supportBleGatewayFirmwareVersion = new SupportBleGatewayFirmwareVersion();
                    supportBleGatewayFirmwareVersion.f13958a = optString;
                    supportBleGatewayFirmwareVersion.b = optString2;
                    arrayList.add(supportBleGatewayFirmwareVersion);
                }
            }
        }
        return arrayList;
    }
}
