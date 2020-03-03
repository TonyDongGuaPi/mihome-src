package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.os.Message;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.Arrays;
import java.util.UUID;

public class BleLoginConnector extends BleRc4Connector {
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = -851198976;
    private static final int i = -1816155127;
    private static final int j = 916084937;
    private static final int k = 4;
    private byte[] l;
    private final BleNotifyResponse m = new BleNotifyResponse() {
        public void a(int i, Void voidR) {
            BluetoothMyLogger.d("Step 1 onResponse: " + Code.a(i));
            if (i == 0) {
                BleLoginConnector.this.m();
            } else {
                BleLoginConnector.this.a(-27);
            }
        }
    };

    protected BleLoginConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 1 ...");
        b(this.m);
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.H)) {
            if (this.d.hasMessages(1)) {
                this.d.removeMessages(1);
                a(bArr);
            } else if (this.d.hasMessages(2)) {
                this.d.removeMessages(2);
                b(bArr);
            }
        }
    }

    private void a(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            a(-31);
            return;
        }
        byte[] g2 = g();
        byte[] a2 = BLECipher.a(g2, bArr);
        this.l = Arrays.copyOfRange(g2, 0, g2.length);
        for (int i2 = 0; i2 < 4; i2++) {
            byte[] bArr2 = this.l;
            bArr2[i2] = (byte) (bArr2[i2] ^ a2[i2]);
        }
        c(BLECipher.a(this.l, ByteUtils.a((int) i)));
    }

    private void b(byte[] bArr) {
        if (ByteUtils.a(Arrays.copyOfRange(BLECipher.a(this.l, bArr), 0, 4), ByteUtils.a((int) j))) {
            a(g(), 0);
        } else {
            a(-10);
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 2");
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.M, ByteUtils.a((int) h), (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
            }
        });
        this.d.removeMessages(1);
        this.d.sendEmptyMessageDelayed(1, 15000);
    }

    private void c(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 3");
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.H, bArr, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
            }
        });
        this.d.removeMessages(2);
        this.d.sendEmptyMessageDelayed(2, 15000);
    }

    private void n() {
        if (l()) {
            a(-7);
        } else {
            a(-10);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 1:
                BluetoothMyLogger.f("tick notify timeout");
                this.d.removeMessages(1);
                a(-7);
                return;
            case 2:
                BluetoothMyLogger.f("confirm notify timeout");
                this.d.removeMessages(2);
                n();
                return;
            default:
                return;
        }
    }
}
