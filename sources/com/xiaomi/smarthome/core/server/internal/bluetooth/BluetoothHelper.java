package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.RandUtils;

public class BluetoothHelper {
    public static int a(int i) {
        return a(PluginManager.a().a(i));
    }

    public static int a(String str) {
        PluginRecord c = PluginManager.a().c(str);
        if (c != null) {
            return c.c().y();
        }
        return 0;
    }

    public static byte[] a(String str, int i) {
        return BLECipher.a(BluetoothCache.k(str), i);
    }

    public static byte[] b(String str, int i) {
        return BLECipher.b(BluetoothCache.k(str), i);
    }

    public static byte[] a() {
        return MD5.c(String.format("token.%d.%f", new Object[]{Long.valueOf(System.currentTimeMillis()), Double.valueOf(RandUtils.a())}));
    }

    public static boolean b(int i) {
        int a2 = a(i);
        return a2 == 1 || a2 == 2;
    }

    public static int b(String str) {
        int g = BluetoothCache.g(str);
        if (g <= 0) {
            String j = BluetoothCache.j(str);
            if (!TextUtils.isEmpty(j)) {
                return PluginManager.a().d(j);
            }
        }
        return g;
    }
}
