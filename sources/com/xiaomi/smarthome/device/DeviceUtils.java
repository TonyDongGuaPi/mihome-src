package com.xiaomi.smarthome.device;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.miio.device.GeneralAPDevice;
import com.xiaomi.smarthome.miio.device.SmartBulbDevice;

public class DeviceUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14848a = "DeviceUtils";

    public static String a() {
        return Build.MODEL + "_ir";
    }

    public static boolean a(Device device) {
        if (device == null) {
            return false;
        }
        if (device instanceof SmartBulbDevice) {
            return true;
        }
        if (TextUtils.isEmpty(device.model)) {
            return false;
        }
        if (!b(device) && !device.model.equals("ge.light.mono1") && !device.model.equals("chuangmi.plug.v1") && !device.model.equals("lumi.plug.v1")) {
            return device.isSupportCommonSwitch();
        }
        return true;
    }

    public static boolean b(Device device) {
        if (device == null || TextUtils.isEmpty(device.model)) {
            return false;
        }
        if (TextUtils.equals(device.model, "zhimi.airpurifier.v1") || TextUtils.equals(device.model, "zhimi.airpurifier.v2")) {
            return true;
        }
        if (!TextUtils.equals(device.model, "zhimi.airpurifier.v3") || device.isSupportCommonSwitch()) {
            return false;
        }
        return true;
    }

    public static boolean c(Device device) {
        if (device == null || device.isOwner() || a(device.did) || device.isVirtualDevice() || (device instanceof MiTVDevice) || (device instanceof RouterDevice) || (device instanceof GeneralAPDevice)) {
            return false;
        }
        if (!(device instanceof BleDevice) || !((BleDevice) device).k()) {
            return true;
        }
        return false;
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && str.equals(a())) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("xiaomi.tv");
    }

    public static int a(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        String[] split = str.split("[._]");
        String[] split2 = str2.split("[._]");
        int min = Math.min(split.length, split2.length);
        int i = 0;
        while (i < min) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt != parseInt2) {
                    return parseInt - parseInt2;
                }
                i++;
            } catch (Exception e) {
                BluetoothLog.a((Throwable) e);
                return 0;
            }
        }
        return split.length - split2.length;
    }

    public static String a(Device device, boolean z) {
        if (device == null) {
            return null;
        }
        if (device instanceof MiTVDevice) {
            String c = c(((MiTVDevice) device).a());
            return TextUtils.isEmpty(c) ? c(device.model) : c;
        } else if (z) {
            return a(device.model, device.isOnline, device.isOpen());
        } else {
            return c(device.model);
        }
    }

    public static String c(String str) {
        PluginRecord d;
        if (str == null || !CoreApi.a().c(str) || (d = CoreApi.a().d(str)) == null) {
            return null;
        }
        return d.t();
    }

    public static String a(String str, boolean z, boolean z2) {
        if (str == null || !CoreApi.a().c(str)) {
            return null;
        }
        PluginRecord d = CoreApi.a().d(str);
        if (!z) {
            return d.s();
        }
        if (z2) {
            return d.q();
        }
        return d.r();
    }

    public static boolean d(String str) {
        return "xiaomi.mikey.v1".equalsIgnoreCase(str);
    }

    public static boolean a(BleDevice bleDevice) {
        return bleDevice != null && d(bleDevice.model);
    }

    public static String d(Device device) {
        if (device == null) {
            return null;
        }
        return device.specUrn;
    }

    public static String e(Device device) {
        try {
            if (!TextUtils.isEmpty(device.specUrn)) {
                return device.specUrn.split(":")[3];
            }
            return null;
        } catch (Exception e) {
            Log.e("DeviceUtils", "getTypeName", e);
            return null;
        }
    }
}
