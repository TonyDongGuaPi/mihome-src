package com.xiaomi.smarthome.library.bluetooth.connect;

import com.xiaomi.smarthome.library.bluetooth.connect.listener.GattResponseListener;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import java.util.UUID;

public interface IBleConnectWorker {
    void a(GattResponseListener gattResponseListener);

    void a(UUID uuid, UUID uuid2, BleResponse<Void> bleResponse);

    boolean a();

    boolean a(UUID uuid, UUID uuid2);

    boolean a(UUID uuid, UUID uuid2, boolean z);

    boolean a(UUID uuid, UUID uuid2, byte[] bArr);

    void b();

    void b(GattResponseListener gattResponseListener);

    boolean b(UUID uuid, UUID uuid2, boolean z);

    boolean b(UUID uuid, UUID uuid2, byte[] bArr);

    boolean c(int i);

    boolean d();

    boolean d(int i);

    int e();

    boolean f();

    boolean g();

    BleGattProfile h();
}
