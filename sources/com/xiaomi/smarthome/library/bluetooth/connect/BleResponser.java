package com.xiaomi.smarthome.library.bluetooth.connect;

import android.os.Bundle;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadRssiResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;

public class BleResponser implements BleResponse<Bundle> {

    /* renamed from: a  reason: collision with root package name */
    public BleResponse f18515a;

    private BleResponser(BleResponse bleResponse) {
        this.f18515a = bleResponse;
    }

    public static BleResponser a(BleResponse bleResponse) {
        return new BleResponser(bleResponse);
    }

    public void a(int i, Bundle bundle) {
        if (this.f18515a != null) {
            if (this.f18515a instanceof BleConnectResponse) {
                this.f18515a.a(i, bundle);
                return;
            }
            byte[] bArr = null;
            if (this.f18515a instanceof BleReadResponse) {
                BleResponse bleResponse = this.f18515a;
                if (bundle != null) {
                    bArr = bundle.getByteArray("key_bytes");
                }
                bleResponse.a(i, bArr);
            } else if (this.f18515a instanceof BleReadRssiResponse) {
                this.f18515a.a(i, Integer.valueOf(bundle != null ? bundle.getInt("key_rssi") : 0));
            } else if (this.f18515a instanceof BleRequestMtuResponse) {
                this.f18515a.a(i, Integer.valueOf(bundle != null ? bundle.getInt(BluetoothConstants.l) : 23));
            } else {
                this.f18515a.a(i, null);
            }
        }
    }
}
