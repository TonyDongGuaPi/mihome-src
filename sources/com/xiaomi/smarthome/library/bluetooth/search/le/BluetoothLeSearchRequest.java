package com.xiaomi.smarthome.library.bluetooth.search.le;

import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchRequest;

public class BluetoothLeSearchRequest extends BluetoothSearchRequest {
    private BluetoothLeSearchRequest() {
    }

    public static BluetoothSearchRequest c() {
        return new BluetoothSearchRequest.Builder().a().b();
    }

    public static BluetoothSearchRequest a(int i) {
        return new BluetoothSearchRequest.Builder().a(i).b();
    }
}
