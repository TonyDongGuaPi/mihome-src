package com.xiaomi.smarthome.framework.plugin.specific;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import java.util.ArrayList;
import java.util.List;

public class RouterStartHandler {

    /* renamed from: a  reason: collision with root package name */
    public final int f1544a = 10001;
    private final String b = "xiaomi.router.v1";
    private final String c = "xiaomi.router.mv1";
    private final String d = "xiaomi.router.v2";
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public boolean f = false;
    private BroadcastReceiver g = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean unused = RouterStartHandler.this.e = true;
            RouterStartHandler.this.b();
        }
    };
    private SmartHomeDeviceManager.IClientDeviceListener h = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            boolean unused = RouterStartHandler.this.f = true;
            RouterStartHandler.this.b();
        }
    };

    /* access modifiers changed from: private */
    public void b() {
        if (this.e && this.f) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.g);
            SmartHomeDeviceManager.a().c(this.h);
            List<Device> d2 = SmartHomeDeviceManager.a().d();
            ArrayList arrayList = new ArrayList();
            for (Device next : d2) {
                if (!TextUtils.isEmpty(next.model) && (next.model.equalsIgnoreCase("xiaomi.router.v1") || next.model.equalsIgnoreCase("xiaomi.router.mv1") || next.model.equalsIgnoreCase("xiaomi.router.v2"))) {
                    arrayList.add(next.did);
                }
            }
            if (arrayList.size() > 0) {
                a((List<String>) arrayList);
            }
        }
    }

    private void a(List<String> list) {
        PluginRecord d2 = CoreApi.a().d("xiaomi.router.v1");
        if (d2 != null) {
            PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d2, 10001, new Intent("action_app_start"), (DeviceStat) null, (RunningProcess) null, true, (PluginApi.SendMessageCallback) null);
        }
    }

    public void a() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.g, new IntentFilter(SHApplication.ACTION_ON_APPLICATION_START));
        SmartHomeDeviceManager.a().a(this.h);
    }
}
