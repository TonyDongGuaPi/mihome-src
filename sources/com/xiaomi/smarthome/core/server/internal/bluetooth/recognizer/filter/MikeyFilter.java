package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import com.xiaomi.smarthome.core.server.internal.bluetooth.MiuiSDKHelper;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class MikeyFilter extends BluetoothDeviceFilter {
    public String b() {
        return "xiaomi.mikey.v1";
    }

    private MikeyFilter() {
    }

    public static MikeyFilter a() {
        return new MikeyFilter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        return MiuiSDKHelper.a().b(bluetoothSearchResult.g());
    }
}
