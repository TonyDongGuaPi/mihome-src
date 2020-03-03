package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.os.Message;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothHelper;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.UUID;

public class BleRegisterConnector extends BleRc4Connector {
    private static final int f = 1;
    private static final int g = -561657200;
    private static final int h = -95114350;
    private byte[] i;
    private final BleNotifyResponse j = new BleNotifyResponse() {
        public void a(int i, Void voidR) {
            BluetoothMyLogger.d("Step 2 onResponse: " + Code.a(i));
            if (i == 0) {
                BleRegisterConnector.this.n();
            } else {
                BleRegisterConnector.this.a(-27);
            }
        }
    };

    protected BleRegisterConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 1 ...");
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.M, ByteUtils.a((int) g), (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleRegisterConnector.this.m();
                } else {
                    BleRegisterConnector.this.a(-28);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void m() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 2 ...");
        b(this.j);
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.H) && this.d.hasMessages(1)) {
            this.d.removeMessages(1);
            if (ByteUtils.a(BLECipher.a(BluetoothHelper.b(e(), f()), BLECipher.a(BluetoothHelper.a(e(), f()), bArr)), this.i)) {
                o();
                return;
            }
            BluetoothMyLogger.f("token not match");
            a(-31);
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 3");
        this.i = BluetoothHelper.a();
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.H, BLECipher.a(BluetoothHelper.a(e(), f()), this.i), (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
            }
        });
        this.d.removeMessages(1);
        this.d.sendEmptyMessageDelayed(1, 15000);
    }

    private void o() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("Process Step 4 ...");
        BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.H, BLECipher.a(this.i, ByteUtils.a((int) h)), (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("Step 4 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleRegisterConnector.this.a(BleRegisterConnector.this.i(), i);
                } else {
                    BleRegisterConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 1) {
            BluetoothMyLogger.f("notify timeout");
            a(-55);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] i() {
        return this.i;
    }
}
