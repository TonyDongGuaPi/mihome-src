package com.xiaomi.smarthome.stat;

import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import org.json.JSONObject;

public class MiscStat {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22745a = 1;
    public static final int b = 2;

    public static void a(String str, int i, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("login_type", str);
            jSONObject.put("err_code", i);
            jSONObject.put("extra", str2);
            STAT.i.a(1, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(long j, String str, String str2, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("delay", j);
            jSONObject.put("rpc", str);
            jSONObject.put("did", str2);
            jSONObject.put("succ", z ? 1 : 0);
            DeviceStat cachedDeviceStat = PluginBridgeService.getCachedDeviceStat(str2);
            if (cachedDeviceStat != null) {
                jSONObject.put("model", cachedDeviceStat.model);
            }
            STAT.i.a(2, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
