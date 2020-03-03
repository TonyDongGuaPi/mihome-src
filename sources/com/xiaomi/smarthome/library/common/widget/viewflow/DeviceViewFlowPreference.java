package com.xiaomi.smarthome.library.common.widget.viewflow;

import android.content.SharedPreferences;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import java.util.HashSet;
import java.util.Map;

public class DeviceViewFlowPreference {
    private static DeviceViewFlowPreference c;

    /* renamed from: a  reason: collision with root package name */
    SharedPreferences f19078a = SHApplication.getAppContext().getSharedPreferences("view_flow_pref", 0);
    HashSet<String> b = new HashSet<>();

    private DeviceViewFlowPreference() {
        Map<String, ?> all = this.f19078a.getAll();
        for (String next : all.keySet()) {
            if (all.get(next) != null && (all.get(next) instanceof Boolean) && ((Boolean) all.get(next)).booleanValue()) {
                this.b.add(next);
            }
        }
    }

    public static synchronized DeviceViewFlowPreference a() {
        DeviceViewFlowPreference deviceViewFlowPreference;
        synchronized (DeviceViewFlowPreference.class) {
            if (c == null) {
                c = new DeviceViewFlowPreference();
            }
            deviceViewFlowPreference = c;
        }
        return deviceViewFlowPreference;
    }

    public boolean a(Device device) {
        return this.b.contains(device.did);
    }

    public void a(Device device, boolean z) {
        this.f19078a.edit().putBoolean(device.did, z).apply();
        if (z) {
            this.b.add(device.did);
        } else {
            this.b.remove(device.did);
        }
    }
}
