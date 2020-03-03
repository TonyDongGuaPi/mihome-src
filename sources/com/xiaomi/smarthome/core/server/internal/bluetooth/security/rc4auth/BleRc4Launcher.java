package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothHelper;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleDeviceBinder;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;

public abstract class BleRc4Launcher extends BleSecurityLauncher implements IBleDeviceBinder {
    protected BleDeviceBinder m = new BleDeviceBinder(this.l);

    public BleRc4Launcher(String str, int i, BleConnectOptions bleConnectOptions) {
        super(str, i, bleConnectOptions);
    }

    public BleRc4Launcher(String str, int i, byte[] bArr, BleConnectOptions bleConnectOptions) {
        super(str, i, bArr, bleConnectOptions);
    }

    public boolean e() {
        BleSecurityConnector d = d();
        boolean z = true;
        if ((d instanceof BleRc4Connector) && (!((BleRc4Connector) d).k() || !BluetoothHelper.b(this.e))) {
            z = false;
        }
        BluetoothMyLogger.d("ShouldBindToServer: " + z);
        return z;
    }

    /* access modifiers changed from: package-private */
    public void c(int i) {
        int i2 = 0;
        int i3 = 1;
        boolean z = i == 0;
        switch (J_()) {
            case 1:
                String str = this.f;
                if (z) {
                    i2 = 2;
                }
                BluetoothCache.d(str, i2);
                return;
            case 2:
                String str2 = this.f;
                if (z) {
                    i3 = 2;
                }
                BluetoothCache.d(str2, i3);
                return;
            default:
                return;
        }
    }

    public void a(BleReadResponse bleReadResponse) {
        this.m.a(bleReadResponse);
    }

    public void a(byte[] bArr, BleWriteResponse bleWriteResponse) {
        this.m.a(bArr, bleWriteResponse);
    }

    public void b(BleReadResponse bleReadResponse) {
        this.m.b(bleReadResponse);
    }

    public int J_() {
        return this.m.J_();
    }

    public void a(BleDeviceBinder.BleBindResponse bleBindResponse) {
        this.m.a(bleBindResponse);
    }

    /* access modifiers changed from: protected */
    public void c() {
        if (this.m != null) {
            this.m.b();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        final String f = BluetoothCache.f(str);
        final byte[] p = BluetoothCache.p(str);
        if (!TextUtils.isEmpty(f) && !ByteUtils.e(p)) {
            BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.J, (BleReadResponse) new BleReadResponse() {
                public void a(int i, byte[] bArr) {
                    if (i == 0 && !ByteUtils.e(bArr)) {
                        String str = new String(ByteUtils.b(BLECipher.a(p, bArr), (byte) 0));
                        if (!TextUtils.isEmpty(str)) {
                            DeviceApi.c(f, str, new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                }

                                public void onFailure(Error error) {
                                }
                            });
                        }
                    }
                }
            });
        }
    }
}
