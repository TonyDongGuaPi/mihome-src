package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class BluetoothCacheRecognizer implements IBluetoothRecognizer {
    private BluetoothCacheRecognizer() {
    }

    public static BluetoothCacheRecognizer a() {
        return new BluetoothCacheRecognizer();
    }

    public BluetoothRecognizeResult a(BluetoothSearchResult bluetoothSearchResult) {
        if (GlobalDynamicSettingManager.a().e()) {
            return null;
        }
        boolean z = true;
        if (BluetoothCache.m(bluetoothSearchResult.g()) != 1) {
            z = false;
        }
        if (z) {
            return null;
        }
        String j = BluetoothCache.j(bluetoothSearchResult.g());
        BluetoothRecognizeResult bluetoothRecognizeResult = !TextUtils.isEmpty(j) ? new BluetoothRecognizeResult(j) : null;
        if (!TextUtils.equals(j, DeviceFactory.v) || !TextUtils.equals(bluetoothSearchResult.k(), " ")) {
            return bluetoothRecognizeResult;
        }
        return null;
    }
}
