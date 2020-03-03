package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer;

import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;

public interface IBluetoothRecognizer {
    BluetoothRecognizeResult a(BluetoothSearchResult bluetoothSearchResult);
}
