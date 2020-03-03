package com.xiaomi.smarthome.framework.plugin.web;

import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import org.json.JSONArray;

public class H5PluginApi {
    public static void a(PluginRecord pluginRecord, String str, String str2, long j, String str3, boolean z) {
    }

    public static void a(PluginRecord pluginRecord, String str, JSONArray jSONArray) {
    }

    public static void a(PluginRecord pluginRecord, Device device) {
        new Intent();
        Intent intent = new Intent(SHApplication.getAppContext(), PluginWebViewActivity.class);
        intent.putExtra("did", device.did);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        try {
            SHApplication.getAppContext().startActivity(intent);
        } catch (Exception unused) {
        }
    }
}
