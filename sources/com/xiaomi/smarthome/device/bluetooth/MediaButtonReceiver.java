package com.xiaomi.smarthome.device.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;

public class MediaButtonReceiver extends BroadcastReceiver {
    private String a() {
        BluetoothManager bluetoothManager = (BluetoothManager) XmBluetoothManager.getInstance();
        if (bluetoothManager != null) {
            return bluetoothManager.a();
        }
        BluetoothLog.c(">>> BluetoothManager null");
        return "";
    }

    public void onReceive(Context context, final Intent intent) {
        if (intent != null && "android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) {
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent != null) {
                BluetoothLog.c(String.format("MediaButtonReceiver.onReceive " + keyEvent.getAction(), new Object[0]));
            }
            final String a2 = a();
            BluetoothLog.c(String.format(">>> model = %s", new Object[]{a2}));
            CoreApi.a().a(context, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    boolean c2 = CoreApi.a().c(a2);
                    if (!TextUtils.isEmpty(a2) && c2) {
                        PluginRecord d = CoreApi.a().d(a2);
                        if (d == null) {
                            BluetoothLog.e("not found plugin record:" + a2);
                        } else if (!d.A()) {
                            BluetoothLog.e("plugin is not installed:" + a2);
                        } else {
                            Intent intent = new Intent();
                            if (intent != null) {
                                intent.putExtras(intent);
                            }
                            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, 15, intent, (DeviceStat) null, (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
                        }
                    }
                }
            });
        }
    }
}
