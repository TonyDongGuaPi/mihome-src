package com.xiaomi.smarthome.core.server.internal.bluetooth.decorator;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.sdk.R;

public class BluetoothDeviceNameDecorator implements Decorator {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothDeviceNameDecorator f14180a;

    public static BluetoothDeviceNameDecorator a() {
        if (f14180a == null) {
            synchronized (BluetoothDeviceNameDecorator.class) {
                if (f14180a == null) {
                    f14180a = new BluetoothDeviceNameDecorator();
                }
            }
        }
        return f14180a;
    }

    public void a(BtDevice btDevice) {
        BluetoothDevice b;
        String a2 = BluetoothCache.a(btDevice.n());
        if (TextUtils.isEmpty(a2)) {
            a2 = a(btDevice.l());
        }
        if (!TextUtils.isEmpty(a2)) {
            btDevice.c(a2);
        } else if (TextUtils.isEmpty(btDevice.m()) && (b = BluetoothUtils.b(btDevice.n())) != null) {
            btDevice.c(b.getName());
        }
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if ("xiaomi.mikey.v1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CoreService.getAppContext(), R.string.mi_key_title);
        }
        if ("yeelink.light.ble1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CoreService.getAppContext(), R.string.yeelight_name);
        }
        if ("zimi.powerbank.v1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CoreService.getAppContext(), R.string.zimi_power_name);
        }
        if ("xiaomi.ble.v1".equalsIgnoreCase(str)) {
            return XMStringUtils.a(CoreService.getAppContext(), R.string.xiaomi_bracelet);
        }
        PluginRecord c = PluginManager.a().c(str);
        return c != null ? c.p() : "";
    }
}
