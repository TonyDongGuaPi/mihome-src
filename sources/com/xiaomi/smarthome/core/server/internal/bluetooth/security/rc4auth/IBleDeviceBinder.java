package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleDeviceBinder;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;

public interface IBleDeviceBinder {
    int J_();

    void a(BleDeviceBinder.BleBindResponse bleBindResponse);

    void a(BleReadResponse bleReadResponse);

    void a(byte[] bArr, BleWriteResponse bleWriteResponse);

    void b(BleReadResponse bleReadResponse);
}
