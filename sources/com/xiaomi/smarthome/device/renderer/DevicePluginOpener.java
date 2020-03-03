package com.xiaomi.smarthome.device.renderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;

public class DevicePluginOpener {
    public static void a(Context context) {
        if (!CoreApi.a().q()) {
            LoginApi.a().a(context, 1, (LoginApi.LoginCallback) null);
            return;
        }
        String a2 = IRDeviceUtil.a();
        if (!TextUtils.isEmpty(a2)) {
            if (!CoreApi.a().c(a2)) {
                CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                    public void a(PluginError pluginError) {
                    }

                    public void a(boolean z, boolean z2) {
                        if (z) {
                            SmartHomeDeviceManager.a().r();
                        }
                    }
                });
                Toast.makeText(context, R.string.toast_failed_retry, 0).show();
                return;
            }
            IRDeviceUtil.f();
            IRDeviceUtil.a(context, (Intent) null);
        }
    }

    public static void a(Context context, Device device, Intent intent) {
        if (device != null) {
            if (CoreApi.a().c(device.model)) {
                Context context2 = context;
                PluginApi.getInstance().sendMessage(context2, CoreApi.a().d(device.model), 1, intent, device.newDeviceStat(), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
                return;
            }
            Bundle bundle = null;
            if (intent != null) {
                bundle = intent.getExtras();
            }
            Intent a2 = DeviceRenderer.a(device).a(device, context, bundle, false, (DeviceRenderer.LoadingCallback) null);
            if (a2 != null) {
                if (!(context instanceof Activity)) {
                    a2.setFlags(C.ENCODING_PCM_MU_LAW);
                }
                context.startActivity(a2);
            }
        }
    }
}
