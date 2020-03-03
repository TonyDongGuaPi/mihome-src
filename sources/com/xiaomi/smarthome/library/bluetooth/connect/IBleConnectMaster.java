package com.xiaomi.smarthome.library.bluetooth.connect;

import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadRssiResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import java.util.List;
import java.util.UUID;

public interface IBleConnectMaster {
    void a();

    void a(int i);

    void a(int i, BleRequestMtuResponse bleRequestMtuResponse);

    void a(BleConnectOptions bleConnectOptions, BleConnectResponse bleConnectResponse);

    void a(BleReadRssiResponse bleReadRssiResponse);

    void a(String str, UUID uuid, UUID uuid2, List<byte[]> list, BleWriteResponse bleWriteResponse);

    void a(UUID uuid, UUID uuid2);

    void a(UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse);

    void a(UUID uuid, UUID uuid2, BleReadResponse bleReadResponse);

    void a(UUID uuid, UUID uuid2, BleResponse<Void> bleResponse);

    void a(UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);

    void b();

    void b(UUID uuid, UUID uuid2);

    void b(UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse);

    void b(UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);

    void c(UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);
}
