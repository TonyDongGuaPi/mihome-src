package com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class BleStandardAuthLoginConnector extends BleStandardAuthConnector {
    private static final String e = "BleStandardAuthLoginConnector";
    private static final int f = 4103;
    private static final byte[] g = {Constants.TagName.USER_LOGIN_FAIL_COUNT, 0, 0, 0};
    private static final byte[] h = {Framer.ENTER_FRAME_PREFIX, 0, 0, 0};
    private static final byte[] i = {34, 0, 0, 0};
    private static final byte[] j = {35, 0, 0, 0};
    private static final byte[] k = {-32, 0, 0, 0};
    private static final byte[] l = {-30, 0, 0, 0};
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    private byte[] q;
    private byte[] r;
    private byte[] s;
    private byte[] t;

    protected BleStandardAuthLoginConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i2) {
        if (d()) {
            a(-2);
            return;
        }
        switch (i2) {
            case 12:
                b(bArr);
                return;
            case 13:
                a(bArr);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthLoginConnector Process Step 1 ...");
        BluetoothCache.d(e(), "".getBytes());
        b(new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthLoginConnector Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthLoginConnector.this.k();
                } else {
                    BleStandardAuthLoginConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void k() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthLoginConnector Process Step 1 plus ...");
        a((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthLoginConnector Step 1 plus onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthLoginConnector.this.l();
                } else {
                    BleStandardAuthLoginConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void l() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthLoginConnector sendLoginStart ...");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, g, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthLoginConnector sendRegStart onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthLoginConnector.this.m();
                } else {
                    BleStandardAuthLoginConnector.this.a(-28);
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
        BluetoothMyLogger.d("BleStandardAuthLoginConnector sendAppRandom ...");
        this.m = o();
        if (!a(this.m, 11, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleStandardAuthLoginConnector sendAppPublicKey onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthLoginConnector.this.d.removeMessages(4103);
                    BleStandardAuthLoginConnector.this.d.sendEmptyMessageDelayed(4103, 15000);
                    return;
                }
                BleStandardAuthLoginConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void a(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.e("BleStandardAuthLoginConnector recvDevRandom ...");
        this.d.removeMessages(4103);
        this.o = bArr;
        byte[] bArr2 = new byte[(this.m.length + this.o.length)];
        System.arraycopy(this.m, 0, bArr2, 0, this.m.length);
        System.arraycopy(this.o, 0, bArr2, this.m.length, this.o.length);
        this.p = a(g(), bArr2);
        if (this.p == null) {
            a(-52);
            return;
        }
        this.q = new byte[16];
        System.arraycopy(this.p, 0, this.q, 0, 16);
        this.r = new byte[16];
        System.arraycopy(this.p, 16, this.r, 0, 16);
        this.s = new byte[4];
        System.arraycopy(this.p, 32, this.s, 0, 4);
        this.t = new byte[4];
        System.arraycopy(this.p, 36, this.t, 0, 4);
        this.n = SecurityChipUtil.a(this.r, bArr2);
        this.d.sendEmptyMessageDelayed(4103, 15000);
    }

    private void n() {
        BluetoothMyLogger.d("BleStandardAuthLoginConnector sendAppConfirm ...");
        if (d()) {
            a(-2);
        } else if (ByteUtils.e(this.n)) {
            BluetoothMyLogger.d("BleStandardAuthLoginConnector sendAppConfirm mAppConfirm is empty");
            a(-52);
        } else if (!a(this.n, 10, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleStandardAuthLoginConnector sendAppConfirm onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthLoginConnector.this.d.removeMessages(4103);
                    BleStandardAuthLoginConnector.this.d.sendEmptyMessageDelayed(4103, 15000);
                    return;
                }
                BleStandardAuthLoginConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void b(byte[] bArr) {
        BluetoothMyLogger.d("BleStandardAuthLoginConnector recvDevConfirmation ...");
        if (d()) {
            a(-2);
            return;
        }
        this.d.removeMessages(4103);
        byte[] bArr2 = new byte[(this.o.length + this.m.length)];
        System.arraycopy(this.o, 0, bArr2, 0, this.o.length);
        System.arraycopy(this.m, 0, bArr2, this.o.length, this.m.length);
        if (ByteUtils.b(bArr, SecurityChipUtil.a(this.q, bArr2))) {
            BluetoothMyLogger.d("BleStandardAuthLoginConnector verify device confirm success ...");
            n();
            this.d.removeMessages(4103);
            this.d.sendEmptyMessageDelayed(4103, 15000);
            return;
        }
        a(-52);
    }

    private void c(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthLoginConnector receiveDeviceResult ..., value = " + ByteUtils.d(bArr));
        if (ByteUtils.b(bArr, h)) {
            this.d.removeMessages(4103);
            this.c.putByteArray(BluetoothConstants.aj, this.p);
            a(0);
        } else if (ByteUtils.b(bArr, i) || ByteUtils.b(bArr, j)) {
            this.d.removeMessages(4103);
            a(-52);
        } else if (ByteUtils.b(bArr, k)) {
            this.d.removeMessages(4103);
            a(-16);
        } else if (ByteUtils.b(bArr, l)) {
            this.d.removeMessages(4103);
            a(-53);
        }
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.M)) {
            c(bArr);
        } else if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.V)) {
            super.a(uuid, uuid2, bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 4103) {
            BluetoothMyLogger.f("BleStandardAuthLoginConnector notify timeout");
            a(-7);
        }
    }

    private byte[] o() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, bArr2);
            return a2.a("mible-login-info".getBytes(), 64);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
