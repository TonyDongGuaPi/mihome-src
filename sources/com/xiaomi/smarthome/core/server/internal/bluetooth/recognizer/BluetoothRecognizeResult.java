package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;

public class BluetoothRecognizeResult {

    /* renamed from: a  reason: collision with root package name */
    public String f14269a;
    public int b;
    public MiotBleAdvPacket c;

    public BluetoothRecognizeResult() {
    }

    public BluetoothRecognizeResult(String str) {
        this.f14269a = str;
    }

    public BluetoothRecognizeResult(String str, MiotBleAdvPacket miotBleAdvPacket) {
        this.f14269a = str;
        this.c = miotBleAdvPacket;
    }

    public void a(BluetoothRecognizeResult bluetoothRecognizeResult) {
        this.f14269a = bluetoothRecognizeResult.f14269a;
        this.c = bluetoothRecognizeResult.c;
    }
}
