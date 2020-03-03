package com.xiaomi.smarthome.scenenew.lumiscene;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.json.JSONException;

public class CommonRequest extends DeviceRequest {
    public static DeviceRequest a(LmBaseDevice lmBaseDevice, String str, HashMap<String, Object> hashMap) {
        DeviceRequest deviceRequest = new DeviceRequest();
        deviceRequest.e = 1;
        deviceRequest.m = lmBaseDevice.getDid();
        deviceRequest.n = lmBaseDevice.getModel();
        deviceRequest.g = str;
        deviceRequest.m = a(lmBaseDevice, str);
        JSONObject jSONObject = new JSONObject();
        jSONObject.putAll(hashMap);
        try {
            deviceRequest.i = new org.json.JSONObject(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceRequest;
    }

    public static String a(LmBaseDevice lmBaseDevice, String str) {
        return lmBaseDevice.getDid();
    }
}
