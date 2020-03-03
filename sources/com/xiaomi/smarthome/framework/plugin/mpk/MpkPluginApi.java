package com.xiaomi.smarthome.framework.plugin.mpk;

import android.content.Intent;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.log.LogUtil;
import org.json.JSONArray;

public class MpkPluginApi {
    static final String TAG = "MpkPluginApi";

    public static void onReceiveDevicePush(PluginRecord pluginRecord, String str, JSONArray jSONArray) {
        Device b = SmartHomeDeviceManager.a().b(str);
        if (b == null) {
            b = SmartHomeDeviceManager.a().l(str);
        }
        if (b == null) {
            b = SmartHomeDeviceManager.a().a(str);
        }
        if (b != null && pluginRecord != null) {
            Intent intent = new Intent();
            intent.putExtra("did", str);
            intent.putExtra("data", jSONArray.toString());
            intent.putExtra("type", "DevicePush");
            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), pluginRecord, 2, intent, b.newDeviceStat(), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
        }
    }

    public static Intent getIntent(String str, String str2, long j, String str3, boolean z) {
        Intent intent = new Intent();
        intent.putExtra("did", str);
        intent.putExtra("event", str2);
        intent.putExtra("time", j);
        intent.putExtra("extra", str3);
        intent.putExtra("isNotified", z);
        intent.putExtra("type", "ScenePush");
        return intent;
    }

    public static void onReceiveScenePush(PluginRecord pluginRecord, String str, String str2, long j, String str3, boolean z) {
        String str4 = str;
        Device b = SmartHomeDeviceManager.a().b(str);
        if (b == null) {
            b = SmartHomeDeviceManager.a().l(str);
        }
        if (b == null) {
            b = SmartHomeDeviceManager.a().a(str);
        }
        if (b == null || pluginRecord == null) {
            LogUtil.c(TAG, "device and pluginRecord is null");
            return;
        }
        LogUtil.c(TAG, "onReceiveScenePush:" + str);
        Intent intent = new Intent();
        intent.putExtra("did", str);
        String str5 = str2;
        intent.putExtra("event", str2);
        long j2 = j;
        intent.putExtra("time", j);
        intent.putExtra("extra", str3);
        intent.putExtra("isNotified", z);
        intent.putExtra("type", "ScenePush");
        PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), pluginRecord, 2, intent, b.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
            public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        try {
                            Toast.makeText(SHApplication.getAppContext(), SHApplication.getAppContext().getString(R.string.plugin_scene_push_downloading), 1).show();
                        } catch (Exception unused) {
                        }
                    }
                });
            }
        });
    }

    @Deprecated
    public static void callPlugin(String str, int i, Intent intent, DeviceStat deviceStat, PluginApi.SendMessageCallback sendMessageCallback) {
        Device b = SmartHomeDeviceManager.a().b(str);
        if (b != null) {
            PluginRecord d = CoreApi.a().d(b.model);
            if (d != null) {
                PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, i, intent, deviceStat, (RunningProcess) null, true, sendMessageCallback);
            } else if (sendMessageCallback != null) {
                sendMessageCallback.onMessageFailure(-1, "not found pluginRecord");
            }
        } else if (sendMessageCallback != null) {
            sendMessageCallback.onMessageFailure(-1, "not found device");
        }
    }
}
