package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.ECCPointConvert;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.CRC32;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.util.Arrays;
import java.util.UUID;

public class BleSecurityChipLoginConnector extends BleSecurityChipConnector {
    public static final byte[] e = {-32, 0, 0, 0};
    private static final String f = "BleSecurityChipLoginConnector";
    private static final int g = 4099;
    private static final byte[] h = {32, 0, 0, 0};
    private static final byte[] i = {Framer.ENTER_FRAME_PREFIX, 0, 0, 0};
    private static final byte[] j = {34, 0, 0, 0};
    private KeyPair k;
    private PublicKey l;
    private byte[] m;
    private byte[] n;

    protected BleSecurityChipLoginConnector(IBleDeviceLauncher iBleDeviceLauncher, byte[] bArr) {
        super(iBleDeviceLauncher);
        this.n = bArr;
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i2) {
        if (i2 == 3) {
            a(bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.M)) {
            c(bArr);
        } else if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.S)) {
            super.a(uuid, uuid2, bArr);
        }
    }

    public void a(Runnable runnable, long j2) {
        if (runnable != null) {
            this.d.postDelayed(runnable, j2);
        }
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 1 ...");
        BluetoothCache.d(e(), "".getBytes());
        b((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipLoginConnector Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipLoginConnector.this.l();
                } else {
                    BleSecurityChipLoginConnector.this.a(-27);
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
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 2 ...");
        a((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipLoginConnector Step 2 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipLoginConnector.this.m();
                } else {
                    BleSecurityChipLoginConnector.this.a(-27);
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
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 3 ...");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, h, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipLoginConnector Step 3 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipLoginConnector.this.n();
                } else {
                    BleSecurityChipLoginConnector.this.a(-28);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void n() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 4 ...");
        this.k = SecurityChipUtil.a();
        if (!a(SecurityChipUtil.a(this.k.getPublic()), 3, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleSecurityChipLoginConnector Step 4 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipLoginConnector.this.d.removeMessages(4099);
                    BleSecurityChipLoginConnector.this.d.sendEmptyMessageDelayed(4099, 15000);
                    return;
                }
                BleSecurityChipLoginConnector.this.a(-28);
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
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 5 ...");
        this.d.removeMessages(4099);
        b(a(bArr, this.n));
    }

    private void b(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 6 ...");
        if (bArr != null) {
            this.d.removeMessages(4099);
            this.d.sendEmptyMessageDelayed(4099, 15000);
            if (!a(bArr, 5, (IBleResponse) new IBleResponse() {
                public IBinder asBinder() {
                    return null;
                }

                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    if (i != 0) {
                        BleSecurityChipLoginConnector.this.a(-28);
                    }
                }
            })) {
                a(-28);
                return;
            }
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 6 encryptData is null ... ");
        a(-21);
    }

    private void c(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipLoginConnector Process Step 7 ..., value = " + ByteUtils.d(bArr));
        if (ByteUtils.b(bArr, i)) {
            this.d.removeMessages(4099);
            this.c.putByteArray(BluetoothConstants.aj, this.m);
            a(0);
        } else if (ByteUtils.b(bArr, j)) {
            this.d.removeMessages(4099);
            a(-22);
        } else if (ByteUtils.b(bArr, e)) {
            this.d.removeMessages(4099);
            a(-16);
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[65];
        bArr3[0] = 4;
        System.arraycopy(bArr, 0, bArr3, 1, bArr.length);
        try {
            this.l = ECCPointConvert.a(bArr3, ((ECPublicKey) this.k.getPublic()).getParams());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            byte[] bArr4 = new byte[64];
            System.arraycopy(SecurityChipUtil.a(this.l, this.k.getPrivate()).getEncoded(), 0, bArr4, 0, 32);
            System.arraycopy(bArr2, 0, bArr4, 32, 32);
            this.m = d(bArr4);
            return SecurityChipUtil.a(Arrays.copyOfRange(this.m, 16, 32), new byte[]{16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}, CRC32.a(bArr));
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private byte[] d(byte[] bArr) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, "smartcfg-login-salt".getBytes());
            return a2.a("smartcfg-login-info".getBytes(), 64);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 4099) {
            BluetoothMyLogger.f("BleSecurityChipLoginConnector notify timeout");
            a(-7);
        }
    }
}
