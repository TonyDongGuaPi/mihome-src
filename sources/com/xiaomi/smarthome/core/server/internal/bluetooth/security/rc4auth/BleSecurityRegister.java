package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleDeviceBinder;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;

public class BleSecurityRegister extends BleRc4Launcher {
    private BleRegisterConnector n = new BleRegisterConnector(this.l);
    private final BleDeviceBinder.BleBindResponse o = new BleDeviceBinder.BleBindResponse() {
        public void a(int i, Void voidR) {
            if (i == 0) {
                BleSecurityRegister.this.f();
                BleSecurityRegister.this.a(BleSecurityRegister.this.f);
            }
            BluetoothCache.d(BleSecurityRegister.this.f, i == 0 ? 2 : 0);
            BleSecurityRegister.this.a(BleSecurityLauncher.d, i);
            BleSecurityRegister.this.b(i);
        }
    };

    public BleSecurityRegister(String str, int i, BleConnectOptions bleConnectOptions) {
        super(str, i, bleConnectOptions);
    }

    /* access modifiers changed from: protected */
    public BleSecurityConnector d() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public void a(int i, Bundle bundle) {
        this.g = this.j.getByteArray("key_token");
        if (!e()) {
            f();
            a(BleSecurityLauncher.d, 0);
            b(i);
            return;
        }
        a(this.o);
    }

    /* access modifiers changed from: private */
    public void f() {
        BluetoothCache.b(this.f, this.g);
    }
}
