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

public interface IBleConnectManager {
    void a(String str);

    void a(String str, int i);

    void a(String str, int i, BleRequestMtuResponse bleRequestMtuResponse);

    void a(String str, BleConnectOptions bleConnectOptions, BleConnectResponse bleConnectResponse);

    void a(String str, BleReadRssiResponse bleReadRssiResponse);

    void a(String str, UUID uuid, UUID uuid2);

    void a(String str, UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse);

    void a(String str, UUID uuid, UUID uuid2, BleReadResponse bleReadResponse);

    void a(String str, UUID uuid, UUID uuid2, BleResponse<Void> bleResponse);

    void a(String str, UUID uuid, UUID uuid2, List<byte[]> list, BleWriteResponse bleWriteResponse);

    void a(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);

    void b();

    void b(String str);

    void b(String str, UUID uuid, UUID uuid2);

    void b(String str, UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse);

    void b(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);

    void c(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse);
}
