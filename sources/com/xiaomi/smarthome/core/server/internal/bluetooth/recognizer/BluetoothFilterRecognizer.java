package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.BalanceCarFilter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.IBluetoothFilter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.MikeyFilter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.RoidmiBtFmFilter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.RoidmiBtFmV2Filter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.RoidmiBtFmV3Filter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.SoundBoxFilter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.filter.YeelightFilter;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public class BluetoothFilterRecognizer implements IBluetoothRecognizer {

    /* renamed from: a  reason: collision with root package name */
    private static final IBluetoothFilter[] f14268a = {YeelightFilter.a(), RoidmiBtFmFilter.a(), RoidmiBtFmV2Filter.a(), RoidmiBtFmV3Filter.a(), MikeyFilter.a(), BalanceCarFilter.a(), SoundBoxFilter.a()};

    private BluetoothFilterRecognizer() {
    }

    public static BluetoothFilterRecognizer a() {
        return new BluetoothFilterRecognizer();
    }

    public BluetoothRecognizeResult a(BluetoothSearchResult bluetoothSearchResult) {
        for (IBluetoothFilter iBluetoothFilter : f14268a) {
            if (!iBluetoothFilter.c() && iBluetoothFilter.a(bluetoothSearchResult)) {
                return new BluetoothRecognizeResult(iBluetoothFilter.b());
            }
        }
        return null;
    }
}
