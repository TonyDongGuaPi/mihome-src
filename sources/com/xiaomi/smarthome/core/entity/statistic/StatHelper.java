package com.xiaomi.smarthome.core.entity.statistic;

import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class StatHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13997a = "Operation";

    public static void a(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_open_share_page");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_open_share_page", (Map<String, String>) hashMap);
    }

    public static void b(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_open_more_page");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_open_more_page", (Map<String, String>) hashMap);
    }

    public static void c(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_open_feedback_page");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_open_feedback_page", (Map<String, String>) hashMap);
    }

    public static void d(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_open_help_page");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_open_help_page", (Map<String, String>) hashMap);
    }

    public static void e(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_open_scene_page");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_open_scene_page", (Map<String, String>) hashMap);
    }

    public static void f(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, "plugin_show_offline_float", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_show_offline_float", (Map<String, String>) hashMap);
    }

    public static void g(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_close_offline_float_click");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_close_offline_float_click", (Map<String, String>) hashMap);
    }

    public static void h(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_offline_float_detail");
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_offline_float_detail", (Map<String, String>) hashMap);
    }

    public static void i(DeviceStat deviceStat) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", deviceStat.model);
            jSONObject.put("did", deviceStat.did);
            jSONObject.put("mac", deviceStat.mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, "plugin_visual_secure_bind", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", deviceStat.model);
        hashMap.put("did", deviceStat.did);
        hashMap.put("mac", deviceStat.mac);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_visual_secure_bind", (Map<String, String>) hashMap);
    }

    public static void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "ForgetPW");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.ACCOUNT, f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "ForgetPW");
    }

    public static void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", MiPushClient.f11511a);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.ACCOUNT, f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), MiPushClient.f11511a);
    }

    public static void c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "LoginByWX");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.ACCOUNT, f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "LoginByWX");
    }

    public static void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "Logout");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.ACCOUNT, f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "Logout");
    }

    public static void a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "repeated_crash");
            jSONObject.put("extra", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.GENERAL, f13997a, jSONObject.toString(), (String) null, false);
    }
}
