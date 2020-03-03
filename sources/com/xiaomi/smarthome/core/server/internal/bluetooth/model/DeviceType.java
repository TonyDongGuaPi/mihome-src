package com.xiaomi.smarthome.core.server.internal.bluetooth.model;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import java.util.Arrays;

public class DeviceType {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14265a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final String[] d = {"yeelink.light.ble1", DeviceFactory.t, DeviceFactory.u, DeviceFactory.v, "xiaomi.ble.v1", "onemore.soundbox.sm001"};
    private static final String[] e = {"zimi.powerbank.v1", "haiii.pettag.v1", "xiaomi.mikey.v1", DeviceFactory.B};

    public static int a(String str) {
        if (Arrays.asList(d).contains(str)) {
            return 0;
        }
        return Arrays.asList(e).contains(str) ? 1 : 2;
    }

    public static boolean b(String str) {
        switch (a(str)) {
            case 0:
            case 1:
                return true;
            default:
                return false;
        }
    }

    public static boolean c(String str) {
        if (!TextUtils.isEmpty(str) && a(str) == 2) {
            return true;
        }
        return false;
    }
}
