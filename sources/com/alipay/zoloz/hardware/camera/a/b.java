package com.alipay.zoloz.hardware.camera.a;

import android.os.Build;
import com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting;

public class b {
    public static DeviceSetting a(DeviceSetting[] deviceSettingArr) {
        DeviceSetting deviceSetting;
        if (deviceSettingArr != null) {
            int parseInt = Integer.parseInt(Build.VERSION.SDK);
            int length = deviceSettingArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                deviceSetting = deviceSettingArr[i];
                if (parseInt >= deviceSetting.getMinApiLevel() && parseInt <= deviceSetting.getMaxApiLevel()) {
                    break;
                }
                i++;
            }
        }
        deviceSetting = null;
        return deviceSetting == null ? new DeviceSetting() : deviceSetting;
    }
}
