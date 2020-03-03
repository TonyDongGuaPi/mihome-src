package com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;

public class BleStandardAuthLogin extends BleSecurityLauncher {
    private BleStandardAuthLoginConnector m = new BleStandardAuthLoginConnector(this.l);

    public BleStandardAuthLogin(String str, int i, byte[] bArr, BleConnectOptions bleConnectOptions) {
        super(str, i, bArr, bleConnectOptions);
    }

    /* access modifiers changed from: protected */
    public BleSecurityConnector d() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void a(final int i, final Bundle bundle) {
        if (i == 0) {
            this.m.a(this.f, BluetoothCache.f(this.f), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    BleStandardAuthLogin.this.b(i, bundle);
                }

                public void onFailure(Error error) {
                    BleStandardAuthLogin.this.b(i, bundle);
                }
            });
            return;
        }
        b(i, bundle);
    }

    /* access modifiers changed from: private */
    public void b(int i, Bundle bundle) {
        BluetoothCache.d(this.f, bundle.getByteArray(BluetoothConstants.aj));
        BleSecurityChipEncrypt.a(this.f);
        a(BleSecurityLauncher.d, i);
        b(i);
    }
}
