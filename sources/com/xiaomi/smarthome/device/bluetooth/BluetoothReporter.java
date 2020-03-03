package com.xiaomi.smarthome.device.bluetooth;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.internal.bluetooth.IBluetoothService;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class BluetoothReporter {
    private static void b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString(IBluetoothService.T, str);
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString(IBluetoothService.V, str2);
            }
            CoreApi.a().a(StatType.ADD_DEVICE, str, str2, (String) null, false);
        }
    }

    private static void a(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            new Bundle().putString(IBluetoothService.T, str);
            JSONObject jSONObject = new JSONObject();
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    try {
                        jSONObject.put((String) next.getKey(), next.getValue());
                    } catch (JSONException e) {
                        BluetoothLog.a((Throwable) e);
                    }
                }
            }
            c(str, jSONObject.toString());
            map.put("key", str);
            MobclickAgent.a((Context) SHApplication.getApplication(), MiStatType.CLICK.getValue(), map);
        }
    }

    private static void b(String str, Map<String, String> map) {
        try {
            BluetoothLog.c(String.format("report key = %s", new Object[]{str}));
            a(str, map);
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
    }

    private static void c(String str, String str2) {
        try {
            b(str, str2);
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
    }

    public static void a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("mac", str);
        b(str2, (Map<String, String>) hashMap);
    }

    public static void a(String str) {
        a(str, "bluetooth_connect_timeout");
    }

    public static void a(String str, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("mac", str);
        hashMap.put("status", bundle.getInt(BluetoothConstants.B) + "");
        hashMap.put("newState", bundle.getInt(BluetoothConstants.C) + "");
        b("bluetooth_connect_failure", (Map<String, String>) hashMap);
    }

    public static void b(String str) {
        a(str, "bluetooth_connect_success");
    }

    public static void b(String str, Bundle bundle) {
        a(str, "bluetooth_auth_failure");
    }

    public static void c(String str, Bundle bundle) {
        a(str, "bluetooth_auth_token_unmatch");
    }

    public static void c(String str) {
        a(str, "bluetooth_auth_success");
    }

    public static void d(String str) {
        a(str, "bluetooth_bind_failure");
    }

    public static void e(String str) {
        a(str, "bluetooth_bind_denied");
    }

    public static void f(String str) {
        a(str, "bluetooth_bind_success");
    }

    public static void g(String str) {
        a(str, "bluetooth_download_plugin_failure");
    }
}
