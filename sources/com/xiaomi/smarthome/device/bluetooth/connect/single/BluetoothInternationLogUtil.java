package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.text.TextUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;

public class BluetoothInternationLogUtil {
    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (!SHApplication.shouldEnableBugly()) {
                    return;
                }
                if (HomeManager.A()) {
                    CrashReport.postCatchedException(new InternationalBluetoothException(str));
                } else {
                    CrashReport.postCatchedException(new MainlandBluetoothException(str));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class InternationalBluetoothException extends Exception {
        public InternationalBluetoothException(String str) {
            super(str);
        }
    }

    public static class MainlandBluetoothException extends Exception {
        public MainlandBluetoothException(String str) {
            super(str);
        }
    }
}
