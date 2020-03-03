package com.xiaomi.smarthome.core.server.internal.bluetooth.security.standardauth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.ECCPointConvert;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPublicKey;
import java.util.Arrays;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class BleStandardAuthRegisterConnector extends BleStandardAuthConnector {
    private static final String e = "BleStandardAuthRegisterConnector";
    private static final int f = 1;
    private static final int g = 4102;
    private static final byte[] h = {ScriptToolsConst.TagName.ResponseSingle, 0, 0, 0};
    private static final byte[] i = {17, 0, 0, 0};
    private static final byte[] j = {18, 0, 0, 0};
    private static final byte[] k = {19, 0, 0, 0};
    private static final byte[] l = {20, 0, 0, 0};
    private static final byte[] m = {-31, 0, 0, 0};
    private byte[] A;
    private byte[] B;
    /* access modifiers changed from: private */
    public byte[] C;
    private final BleReadResponse D = new BleReadResponse() {
        public void a(final int i, byte[] bArr) {
            if (i != 0) {
                BleStandardAuthRegisterConnector.this.a(false, (BleWriteResponse) new BleWriteResponse() {
                    public void a(int i, Void voidR) {
                        BleStandardAuthRegisterConnector.this.a(i);
                    }
                });
            } else if (!ByteUtils.e(bArr)) {
                if (!ByteUtils.e(BleStandardAuthRegisterConnector.this.q) && !ByteUtils.a(BleStandardAuthRegisterConnector.this.q, bArr)) {
                    BluetoothMyLogger.c("BleStandardAuthRegisterConnector device did don't match server did, device did = " + ByteUtils.d(BleStandardAuthRegisterConnector.this.q) + ", server did = " + ByteUtils.d(bArr));
                }
                byte[] unused = BleStandardAuthRegisterConnector.this.q = bArr;
                byte[] a2 = ByteUtils.a(bArr, 20, (byte) 0);
                BluetoothMyLogger.f("BleStandardAuthRegisterConnector write Did to Device: " + ByteUtils.d(a2));
                BleStandardAuthRegisterConnector.this.a(a2, BleStandardAuthRegisterConnector.this.E);
            } else {
                throw new IllegalStateException("strange exception");
            }
        }
    };
    /* access modifiers changed from: private */
    public final BleWriteResponse E = new BleWriteResponse() {
        public void a(int i, Void voidR) {
            if (i == 0) {
                BleStandardAuthRegisterConnector.this.a(BleStandardAuthRegisterConnector.this.F);
            } else {
                BleStandardAuthRegisterConnector.this.a(i);
            }
        }
    };
    /* access modifiers changed from: private */
    public final BleWriteResponse F = new BleWriteResponse() {
        public void a(final int i, Void voidR) {
            if (i == 0) {
                BluetoothCache.d(BleStandardAuthRegisterConnector.this.e(), 2);
                BleStandardAuthRegisterConnector.this.a(true, (BleWriteResponse) new BleWriteResponse() {
                    public void a(int i, Void voidR) {
                        BleStandardAuthRegisterConnector.this.d.removeMessages(4102);
                        BleStandardAuthRegisterConnector.this.d.sendEmptyMessageDelayed(4102, 15000);
                    }
                });
                return;
            }
            BleStandardAuthRegisterConnector.this.a(false, (BleWriteResponse) new BleWriteResponse() {
                public void a(int i, Void voidR) {
                    BleStandardAuthRegisterConnector.this.a(i);
                }
            });
        }
    };
    private int n;
    private int o;
    private int p;
    /* access modifiers changed from: private */
    public byte[] q;
    private String r;
    private KeyPair s;
    private PublicKey t;
    private byte[] u;
    /* access modifiers changed from: private */
    public SecretKey v;
    private byte[] w;
    /* access modifiers changed from: private */
    public byte[] x;
    private byte[] y;
    private byte[] z;

    public int a(int i2, int i3, int i4) {
        return (i2 >> i3) & ((1 << ((i4 - i3) + 1)) - 1);
    }

    public BleStandardAuthRegisterConnector(IBleDeviceLauncher iBleDeviceLauncher, String str) {
        super(iBleDeviceLauncher);
        this.r = str;
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i2) {
        if (d()) {
            a(-2);
        } else if (i2 == 0) {
            b(bArr);
        } else if (i2 != 3) {
            switch (i2) {
                case 12:
                    e(bArr);
                    return;
                case 13:
                    f(bArr);
                    return;
                default:
                    return;
            }
        } else {
            c(bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector Process Step 1 ...");
        BluetoothCache.d(e(), "".getBytes());
        b(new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthRegisterConnector.this.l();
                } else {
                    BleStandardAuthRegisterConnector.this.a(-27);
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
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector Process Step 1 plus ...");
        a((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector Step 1 plus onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthRegisterConnector.this.m();
                } else {
                    BleStandardAuthRegisterConnector.this.a(-27);
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
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector requestDeviceInfo ...");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, h, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector requestDeviceInfo onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthRegisterConnector.this.d.removeMessages(4102);
                    BleStandardAuthRegisterConnector.this.d.sendEmptyMessageDelayed(4102, 15000);
                    return;
                }
                BleStandardAuthRegisterConnector.this.a(-28);
            }
        });
    }

    private void a(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        byte[] bArr2 = new byte[4];
        bArr2[0] = 21;
        bArr2[1] = bArr[0];
        bArr2[2] = bArr[1];
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector sendRegStart ...");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, bArr2, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector sendRegStart onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthRegisterConnector.this.n();
                } else {
                    BleStandardAuthRegisterConnector.this.a(-28);
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
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector sendAppPublicKey ...");
        this.s = SecurityChipUtil.a();
        if (!a(SecurityChipUtil.a(this.s.getPublic()), 3, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector sendAppPublicKey onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthRegisterConnector.this.d.removeMessages(4102);
                    BleStandardAuthRegisterConnector.this.d.sendEmptyMessageDelayed(4102, 15000);
                    return;
                }
                BleStandardAuthRegisterConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void b(byte[] bArr) {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector recvDeviceInfo ...");
        if (bArr.length >= 4) {
            this.n = bArr[0] + (bArr[1] << 8);
            if (this.n > 1) {
                a(-37);
                return;
            }
            byte[] bArr2 = {bArr[2], bArr[3]};
            this.o = a((int) bArr2[0], 0, 3);
            this.p = a((int) bArr2[0], 4, 7);
            if (bArr.length > 4) {
                this.q = ByteUtils.a(Arrays.copyOfRange(bArr, 4, bArr.length), (byte) 0);
            }
            a(new byte[]{bArr[2], bArr[3]});
            return;
        }
        BluetoothMyLogger.c("BleStandardAuthRegisterConnector recvDeviceInfo data format error");
        a(-47);
    }

    private void c(byte[] bArr) {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector recvDevicePubKey ...");
        this.d.removeMessages(4102);
        this.u = bArr;
        byte[] bArr2 = new byte[65];
        bArr2[0] = 4;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        try {
            this.t = ECCPointConvert.a(bArr2, ((ECPublicKey) this.s.getPublic()).getParams());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        o();
    }

    private void o() {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector verifyDeviceParams ...");
        if (this.t == null) {
            BluetoothMyLogger.d("BleStandardAuthRegisterConnector mDevicePubKey is null ...");
            a(-51);
            return;
        }
        this.v = SecurityChipUtil.a(this.t, this.s.getPrivate());
        if (this.v == null) {
            BluetoothMyLogger.d("BleStandardAuthRegisterConnector eShareKey is null ...");
            a(-51);
        } else if (this.o != 0) {
            BluetoothMyLogger.d("BleStandardAuthRegisterConnectordon't support this input capability: " + this.o);
            a(-37);
        } else if (this.p == 0) {
            this.x = new byte[16];
            p();
        } else if (this.p != 8) {
            BluetoothMyLogger.d("BleStandardAuthRegisterConnector don't support this output capability: " + this.p);
            a(-37);
        } else if (TextUtils.isEmpty(this.r)) {
            BluetoothMyLogger.d("BleStandardAuthRegisterConnector  get qrcode oob failed");
            a(-50);
        } else {
            this.x = new byte[16];
            byte[] a2 = ByteUtils.a(this.r);
            System.arraycopy(a2, 0, this.x, 0, Math.min(a2.length, 16));
            this.w = t();
            byte[] bArr = new byte[32];
            System.arraycopy(this.w, 0, bArr, 0, 16);
            System.arraycopy(this.x, 0, bArr, 16, 16);
            this.B = SecurityChipUtil.a(this.v.getEncoded(), bArr);
            this.d.removeMessages(4102);
            this.d.sendEmptyMessageDelayed(4102, 15000);
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector startBindAfterAuthSuccess");
        byte[] a2 = a(this.v.getEncoded(), this.x);
        this.y = new byte[12];
        System.arraycopy(a2, 0, this.y, 0, 12);
        this.z = new byte[16];
        System.arraycopy(a2, 12, this.z, 0, 16);
        this.A = new byte[16];
        System.arraycopy(a2, 28, this.A, 0, 16);
        a(this.D);
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, BleWriteResponse bleWriteResponse) {
        if (d()) {
            a(-2);
            return;
        }
        byte[] bArr = z2 ? k : l;
        BluetoothMyLogger.f(String.format("BleStandardAuthRegisterConnector writeBindResultToDevice : %s", new Object[]{ByteUtils.d(bArr)}));
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, bArr, bleWriteResponse);
    }

    private void d(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector receiveDeviceResult ..., value = " + ByteUtils.d(bArr));
        if (ByteUtils.b(bArr, i)) {
            this.d.removeMessages(4102);
            a(0);
        } else if (ByteUtils.b(bArr, j)) {
            this.d.removeMessages(4102);
            a(-51);
        } else if (ByteUtils.b(bArr, m)) {
            this.d.removeMessages(4102);
            a(-17);
        }
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.M)) {
            d(bArr);
        } else if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.V)) {
            super.a(uuid, uuid2, bArr);
        }
    }

    private void a(final BleReadResponse bleReadResponse) {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector applyDid");
        DeviceApi.a(k(), this.f14295a.a(), PluginManager.a().a(f()), r(), (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector applyDid >>> onSuccess " + str);
                if (!TextUtils.isEmpty(str)) {
                    bleReadResponse.a(0, str.getBytes());
                } else {
                    bleReadResponse.a(-29, null);
                }
            }

            public void onFailure(Error error) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector applyDid >>> onFailure " + error);
                bleReadResponse.a(-29, null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(byte[] bArr, final BleWriteResponse bleWriteResponse) {
        byte[] bArr2;
        if (d()) {
            a(-2);
            return;
        }
        try {
            bArr2 = SecurityChipUtil.a((SecretKey) new SecretKeySpec(this.A, a.b), new byte[]{16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}, bArr, "devID".getBytes()).c();
        } catch (Exception e2) {
            e2.printStackTrace();
            bArr2 = null;
        }
        if (bArr2 == null) {
            BluetoothMyLogger.c("BleStandardAuthRegisterConnector encrypt did failed");
            a(-51);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector start writeDidToDevice");
        if (!a(bArr2, 0, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector writeDidToDevice onResponse: " + Code.a(i));
                if (bleWriteResponse != null) {
                    bleWriteResponse.a(i, null);
                }
            }
        })) {
            a(-28);
        }
    }

    /* access modifiers changed from: private */
    public void a(final BleWriteResponse bleWriteResponse) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector bindDidToServer start");
        DeviceApi.b(k(), s(), r(), BluetoothCache.k(e()), new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector bindDidToServer return " + bool);
                bleWriteResponse.a(bool.booleanValue() ? 0 : -30, null);
            }

            public void onFailure(Error error) {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector bindDidToServer return failed: " + error);
                if (error.a() == -1) {
                    String b2 = error.b();
                    if (TextUtils.isEmpty(b2) || b2.contains("Unable to resolve")) {
                        bleWriteResponse.a(-30, null);
                    } else {
                        bleWriteResponse.a(-14, null);
                    }
                } else {
                    bleWriteResponse.a(-30, null);
                }
            }
        });
    }

    private void e(byte[] bArr) {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector recvDevConfirmation ...");
        this.C = bArr;
        this.d.removeMessages(4102);
        if (ByteUtils.e(this.B)) {
            a(-48);
        } else {
            q();
        }
    }

    private void q() {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector sendAppConfirmation ...");
        if (d()) {
            a(-2);
        } else if (ByteUtils.e(this.B)) {
            BluetoothMyLogger.d("BleStandardAuthRegisterConnector mAppConfirmation is null ...");
            a(-51);
        } else if (!a(this.B, 10, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector sendAppConfirmation onResponse: " + Code.a(i));
                if (i == 0) {
                    BleStandardAuthRegisterConnector.this.d.removeMessages(4102);
                    BleStandardAuthRegisterConnector.this.d.sendEmptyMessageDelayed(4102, 15000);
                    return;
                }
                BleStandardAuthRegisterConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void f(final byte[] bArr) {
        BluetoothMyLogger.d("BleStandardAuthRegisterConnector recvDevRandom ...");
        this.d.removeMessages(4102);
        if (!a(this.w, 11, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleStandardAuthRegisterConnector recvDevRandom onResponse: " + Code.a(i));
                if (i != 0) {
                    BleStandardAuthRegisterConnector.this.a(-28);
                } else if (ByteUtils.e(BleStandardAuthRegisterConnector.this.x)) {
                    BluetoothMyLogger.d("BleStandardAuthRegisterConnector recvDevRandom mOOB is empty");
                    BleStandardAuthRegisterConnector.this.a(false, (BleWriteResponse) new BleWriteResponse() {
                        public void a(int i, Void voidR) {
                            BleStandardAuthRegisterConnector.this.a(-49);
                        }
                    });
                } else {
                    byte[] bArr = new byte[32];
                    System.arraycopy(bArr, 0, bArr, 0, 16);
                    System.arraycopy(BleStandardAuthRegisterConnector.this.x, 0, bArr, 16, 16);
                    if (ByteUtils.a(BleStandardAuthRegisterConnector.this.C, SecurityChipUtil.a(BleStandardAuthRegisterConnector.this.v.getEncoded(), bArr))) {
                        BleStandardAuthRegisterConnector.this.p();
                    } else {
                        BleStandardAuthRegisterConnector.this.a(false, (BleWriteResponse) new BleWriteResponse() {
                            public void a(int i, Void voidR) {
                                BleStandardAuthRegisterConnector.this.a(-49);
                            }
                        });
                    }
                }
            }
        })) {
            a(-28);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 4102) {
            BluetoothMyLogger.f("BleStandardAuthRegisterConnector notify timeout");
            a(-55);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] i() {
        return this.y;
    }

    /* access modifiers changed from: protected */
    public String k() {
        if (ByteUtils.e(this.q)) {
            return "";
        }
        return new String(this.q);
    }

    private String r() {
        if (ByteUtils.e(this.y)) {
            return "";
        }
        return ByteUtils.d(this.y);
    }

    private String s() {
        if (ByteUtils.e(this.z)) {
            return "";
        }
        return ByteUtils.d(this.z);
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, bArr2);
            return a2.a("mible-setup-info".getBytes(), 64);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private byte[] t() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}
