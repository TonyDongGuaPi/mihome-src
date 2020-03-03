package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;

public class BleMeshLogin extends BleSecurityLauncher {
    private BleMeshLoginConnector m;

    public BleMeshLogin(String str, int i, byte[] bArr, BleConnectOptions bleConnectOptions) {
        super(str, i, bArr, bleConnectOptions);
        this.m = new BleMeshLoginConnector(this.l, bArr);
    }

    /* access modifiers changed from: protected */
    public BleSecurityConnector d() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void a(int i, Bundle bundle) {
        BluetoothCache.d(this.f, bundle.getByteArray(BluetoothConstants.aj));
        BleSecurityChipEncrypt.a(this.f);
        a(BleSecurityLauncher.d, i);
        b(i);
    }
}
