package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleDeviceBinder;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;

public class BleSecurityLogin extends BleRc4Launcher {
    private BleLoginConnector n = new BleLoginConnector(this.l);

    public BleSecurityLogin(String str, int i, byte[] bArr, BleConnectOptions bleConnectOptions) {
        super(str, i, bArr, bleConnectOptions);
    }

    /* access modifiers changed from: protected */
    public BleSecurityConnector d() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public void a(int i, Bundle bundle) {
        if (!e()) {
            a(BleSecurityLauncher.d, 0);
            b(i);
            return;
        }
        switch (J_()) {
            case 1:
                d(i);
                break;
            case 2:
                e(i);
                break;
            default:
                throw new IllegalStateException("impossible here");
        }
        if (i == 0) {
            a(this.f);
        }
    }

    private void d(int i) {
        BluetoothMyLogger.f("loginForStrongBind " + i);
        a(BleSecurityLauncher.d, 0);
        b(i);
    }

    private void e(final int i) {
        if (BluetoothCache.n(this.f) != 2) {
            BluetoothMyLogger.f("loginForWeakBind, remoteBinded false");
            a((BleDeviceBinder.BleBindResponse) new BleDeviceBinder.BleBindResponse() {
                public void a(int i, Void voidR) {
                    BleSecurityLogin.this.c(i);
                    BleSecurityLogin.this.a(BleSecurityLauncher.d, i);
                    BleSecurityLogin.this.b(i);
                }
            });
            return;
        }
        BluetoothMyLogger.f("loginForWeakBind, remoteBinded true");
        a(BleSecurityLauncher.d, 0);
        b(i);
    }
}
