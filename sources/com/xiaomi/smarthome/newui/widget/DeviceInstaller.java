package com.xiaomi.smarthome.newui.widget;

import com.xiaomi.smarthome.device.Device;

public interface DeviceInstaller {

    public interface InstallCallback {
        void a();

        void b();

        void c();
    }

    void install(Device device, InstallCallback installCallback);
}
