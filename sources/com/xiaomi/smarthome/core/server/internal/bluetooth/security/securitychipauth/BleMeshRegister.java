package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;

public class BleMeshRegister extends BleSecurityLauncher {
    private BleMeshRegisterConnector m = new BleMeshRegisterConnector(this.l);

    public BleMeshRegister(String str, int i, BleConnectOptions bleConnectOptions) {
        super(str, i, bleConnectOptions);
    }

    /* access modifiers changed from: protected */
    public BleSecurityConnector d() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void a(final int i, Bundle bundle) {
        if (i == 0) {
            BluetoothCache.e(this.f, this.m.l());
        }
        DeviceApi.b(this.f, this.m.l(), new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                BluetoothMyLogger.e("report version success");
                BleMeshRegister.this.c(i);
            }

            public void onFailure(Error error) {
                if (error != null) {
                    BluetoothMyLogger.e("report version failed: " + error.toString());
                }
                BleMeshRegister.this.c(i);
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(int i) {
        a(BleSecurityLauncher.d, 0);
        b(i);
        this.m.a();
    }
}
