package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import android.text.TextUtils;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class YeelightFilter extends BluetoothDeviceFilter {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f14288a = {"4e20", "0248", "012f"};
    private static final String b = "XMCTD_";

    public String b() {
        return "yeelink.light.ble1";
    }

    private YeelightFilter() {
    }

    public static YeelightFilter a() {
        return new YeelightFilter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        String k = bluetoothSearchResult.k();
        if (TextUtils.isEmpty(k)) {
            return false;
        }
        String lowerCase = k.toLowerCase();
        for (String contains : f14288a) {
            if (lowerCase.contains(contains)) {
                return false;
            }
        }
        if (k.contains(b)) {
            return true;
        }
        return false;
    }
}
