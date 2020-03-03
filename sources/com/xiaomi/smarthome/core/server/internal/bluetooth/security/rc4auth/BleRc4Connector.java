package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.os.Bundle;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityConnector;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattService;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;

public abstract class BleRc4Connector extends BleSecurityConnector {
    protected boolean e = true;

    protected BleRc4Connector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        this.e = b(bundle);
    }

    /* access modifiers changed from: protected */
    public boolean b(Bundle bundle) {
        BleGattService a2;
        if (bundle == null || (a2 = ((BleGattProfile) bundle.getParcelable("key_gatt_profile")).a(BluetoothConstants.i)) == null || !a2.a(BluetoothConstants.P) || !a2.a(BluetoothConstants.Q)) {
            return false;
        }
        return true;
    }

    public boolean k() {
        return this.e;
    }

    private boolean m() {
        return k();
    }

    /* access modifiers changed from: protected */
    public boolean l() {
        return k();
    }

    /* access modifiers changed from: protected */
    public void a(final byte[] bArr, final int i) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("readFirmwareVersionFromDevice: ");
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.J, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                if (i == 0 && !ByteUtils.e(bArr)) {
                    String str = new String(ByteUtils.b(BLECipher.a(bArr, bArr), (byte) 0));
                    BluetoothMyLogger.f("firmWare version " + str);
                    BluetoothCache.p(BleRc4Connector.this.e(), str);
                    BleRc4Connector.this.c.putString("key_version", str);
                }
                BleRc4Connector.this.a(i);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void b(final BleNotifyResponse bleNotifyResponse) {
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.H, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                bleNotifyResponse.a(i, null);
            }
        });
    }
}
