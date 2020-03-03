package com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.device.DeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import java.util.ArrayList;
import java.util.List;

public class BleStandardAuthRegister extends BleSecurityLauncher {
    private BleStandardAuthRegisterConnector m;

    public BleStandardAuthRegister(String str, int i, BleConnectOptions bleConnectOptions) {
        super(str, i, bleConnectOptions);
        this.m = new BleStandardAuthRegisterConnector(this.l, bleConnectOptions.e());
    }

    /* access modifiers changed from: protected */
    public BleSecurityConnector d() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void a(final int i, Bundle bundle) {
        if (i == 0) {
            byte[] byteArray = bundle.getByteArray("key_token");
            BluetoothCache.d(this.f, 2);
            BluetoothCache.e(this.f, this.m.k());
            BluetoothCache.b(this.f, byteArray);
            this.m.a(this.f, this.m.k(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    BluetoothMyLogger.e("report version success");
                    BleStandardAuthRegister.this.c(i);
                }

                public void onFailure(Error error) {
                    if (error != null) {
                        BluetoothMyLogger.e("report version failed: " + error.toString());
                    }
                    BleStandardAuthRegister.this.c(i);
                }
            });
            return;
        }
        b(i);
    }

    /* access modifiers changed from: private */
    public void c(int i) {
        a(BleSecurityLauncher.d, 0);
        b(i);
        this.m.a();
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (i != 0 && BluetoothCache.n(this.f) == 2 && !TextUtils.isEmpty(this.m.k())) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.m.k());
            DeviceManager.a().a((List<String>) arrayList, (IClientCallback) new IClientCallback() {
                public IBinder asBinder() {
                    return null;
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                }

                public void onSuccess(Bundle bundle) throws RemoteException {
                    BluetoothCache.d(BleStandardAuthRegister.this.f, 0);
                }
            });
        }
    }
}
