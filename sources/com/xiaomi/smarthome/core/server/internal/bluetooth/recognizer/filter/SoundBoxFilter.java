package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter;

import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class SoundBoxFilter extends BluetoothDeviceFilter {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f14287a = {"Music Alarm Clock", "Mi Music Alarm Clock"};

    public String b() {
        return "onemore.soundbox.sm001";
    }

    public boolean c() {
        return false;
    }

    private SoundBoxFilter() {
    }

    public static SoundBoxFilter a() {
        return new SoundBoxFilter();
    }

    public boolean a(BluetoothSearchResult bluetoothSearchResult) {
        for (String equals : f14287a) {
            if (equals.equals(bluetoothSearchResult.k())) {
                return true;
            }
        }
        return false;
    }
}
