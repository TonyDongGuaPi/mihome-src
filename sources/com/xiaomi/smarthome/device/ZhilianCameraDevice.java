package com.xiaomi.smarthome.device;

import android.net.wifi.ScanResult;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.smartconfig.SmartConfigConstants;
import com.xiaomi.smarthomedevice.R;

public class ZhilianCameraDevice extends Device {
    public boolean canBeDeleted() {
        return false;
    }

    public boolean canBeShared() {
        return false;
    }

    public boolean canRename() {
        return false;
    }

    public boolean hasShortcut() {
        return false;
    }

    public ZhilianCameraDevice(ScanResult scanResult) {
        if (scanResult.SSID.contains("isa-camera-isc5")) {
            this.name = CommonApplication.getAppContext().getString(R.string.smart_config_xiaofang_name);
            this.model = "isa.camera.isc5";
        } else if (scanResult.SSID.contains("mijia-camera-v1")) {
            this.name = CommonApplication.getAppContext().getString(R.string.smart_config_mijia_camera_zhilian);
            this.model = "mijia.camera.v1";
        }
        this.did = SmartConfigConstants.f;
        this.isOnline = true;
        this.permitLevel = 16;
    }
}
