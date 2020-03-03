package com.xiaomi.smarthome.device.authorization;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;

public class DeviceAuthManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15015a = "DeviceAuthManager";
    private static DeviceAuthManager b = null;
    private static final int f = 3;
    private volatile boolean c = false;
    private VoiceAuthManagerInternal d = new VoiceAuthManagerInternal();
    private int e = 3;

    private DeviceAuthManager() {
    }

    public static DeviceAuthManager a() {
        if (b == null) {
            synchronized (DeviceAuthManager.class) {
                if (b == null) {
                    b = new DeviceAuthManager();
                }
            }
        }
        return b;
    }

    public boolean a(String str) {
        return this.d.a(str);
    }

    public boolean b(String str) {
        Device b2;
        PluginRecord d2;
        PluginDeviceInfo c2;
        if (CoreApi.a().D() || (b2 = SmartHomeDeviceManager.a().b(str)) == null || (d2 = CoreApi.a().d(b2.model)) == null || (c2 = d2.c()) == null) {
            return false;
        }
        if (c2.D() == 1 || c2.D() == 2) {
            return true;
        }
        return false;
    }

    public void b() {
        this.c = true;
        b = null;
    }

    private class VoiceAuthManagerInternal {
        private List<DeviceAuthData> b;

        private VoiceAuthManagerInternal() {
            this.b = new ArrayList();
        }

        public List<DeviceAuthData> a() {
            return this.b;
        }

        public boolean a(String str) {
            if (this.b.size() == 0) {
                return false;
            }
            int i = 0;
            while (i < this.b.size()) {
                DeviceAuthData deviceAuthData = this.b.get(i);
                if (deviceAuthData == null || !TextUtils.equals(str, deviceAuthData.e())) {
                    i++;
                } else if (deviceAuthData.f() != -1) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }
}
