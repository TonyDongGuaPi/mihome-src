package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class BalanceCarFilter extends BluetoothDeviceFilter {

    /* renamed from: a  reason: collision with root package name */
    private String[] f14283a = {" "};

    public String b() {
        return DeviceFactory.v;
    }

    private BalanceCarFilter() {
    }

    public static BalanceCarFilter a() {
        return new BalanceCarFilter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        String k = bluetoothSearchResult.k();
        if (TextUtils.isEmpty(k)) {
            return false;
        }
        for (String startsWith : this.f14283a) {
            if (k.startsWith(startsWith) && k.length() > 1) {
                return true;
            }
        }
        return false;
    }
}
