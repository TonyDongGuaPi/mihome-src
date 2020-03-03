package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.ECCPointConvert;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import com.xiaomi.smarthome.library.crypto.rc4coder.Coder;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import javax.crypto.SecretKey;

public class BleSecurityChipRegisterConnector extends BleSecurityChipConnector {
    private static final String e = "BleSecurityChipRegisterConnector";
    private static final int f = 4098;
    private static final int g = 4099;
    private static final int h = 30000;
    private static final int i = 2001;
    /* access modifiers changed from: private */
    public static final byte[] j = {16, 0, 0, 0};
    /* access modifiers changed from: private */
    public static final byte[] k = {16, 1, 0, 0};
    private static final byte[] l = {17, 0, 0, 0};
    private static final byte[] m = {18, 0, 0, 0};
    private static final byte[] n = {19, 0, 0, 0};
    private static final byte[] o = {20, 0, 0, 0};
    private static final byte[] p = {-31, 0, 0, 0};
    private static final short q = 257;
    private static final short r = 512;
    private static final short s = 514;
    private static final short t = 515;
    private static final short u = 516;
    private byte[] A;
    private X509Certificate B;
    private X509Certificate C;
    /* access modifiers changed from: private */
    public byte[] D;
    private byte[] E;
    private byte[] F;
    private byte[] G;
    private SecretKey H;
    private byte[] I;
    private byte[] J;
    private byte[] K;
    private byte[] L;
    private int M = -7;
    private String N;
    private String O;
    private String P;
    /* access modifiers changed from: private */
    public String Q;
    private KeyPair v;
    private PublicKey w;
    private byte[] x;
    private byte[] y;
    private byte[] z;

    protected BleSecurityChipRegisterConnector(IBleDeviceLauncher iBleDeviceLauncher, String str) {
        super(iBleDeviceLauncher);
        this.N = str;
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i2) {
        if (d()) {
            a(-2);
            return;
        }
        switch (i2) {
            case 1:
                c(bArr);
                return;
            case 2:
                d(bArr);
                return;
            case 3:
                b(bArr);
                return;
            case 4:
                if (!e(bArr)) {
                    a(false);
                    return;
                } else if (ByteUtils.e(this.J)) {
                    a(true);
                    return;
                } else {
                    return;
                }
            default:
                switch (i2) {
                    case 12:
                        f(bArr);
                        return;
                    case 13:
                        g(bArr);
                        return;
                    default:
                        return;
                }
        }
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.M)) {
            i(bArr);
        } else if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.S)) {
            super.a(uuid, uuid2, bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void j() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector Process Step 1 ...");
        BluetoothCache.d(e(), "".getBytes());
        b((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipRegisterConnector.this.o();
                } else {
                    BleSecurityChipRegisterConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void o() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector Process Step 1 plus ...");
        a((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector Step 1 plus onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipRegisterConnector.this.p();
                } else {
                    BleSecurityChipRegisterConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void p() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector Process Step 2 ...");
        a(e(), (BleResponse<String>) new BleResponse<String>() {
            public void a(int i, String str) {
                if (i != 0 || TextUtils.isEmpty(str)) {
                    BleSecurityChipRegisterConnector.this.a(-36);
                    return;
                }
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector version: " + str);
                String[] split = str.split("[._]");
                int i2 = 0;
                try {
                    if (split.length == 1) {
                        i2 = Integer.valueOf(split[0]).intValue();
                    } else {
                        i2 = (Integer.valueOf(split[0]).intValue() * 1000) + Integer.valueOf(split[1]).intValue();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BluetoothCache.p(BleSecurityChipRegisterConnector.this.e(), str);
                if (i2 < 2001) {
                    BleSecurityChipRegisterConnector.this.a(BleSecurityChipRegisterConnector.j);
                } else {
                    BleSecurityChipRegisterConnector.this.a(BleSecurityChipRegisterConnector.k);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector sendRegStart ...");
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector sendRegStart onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipRegisterConnector.this.q();
                } else {
                    BleSecurityChipRegisterConnector.this.a(-28);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void q() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector Process Step 3 ...");
        this.v = SecurityChipUtil.a();
        if (!a(SecurityChipUtil.a(this.v.getPublic()), 3, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector Step 3 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipRegisterConnector.this.d.removeMessages(4098);
                    BleSecurityChipRegisterConnector.this.d.sendEmptyMessageDelayed(4098, 15000);
                    return;
                }
                BleSecurityChipRegisterConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private short r() {
        return ByteBuffer.wrap(Arrays.copyOfRange(this.y, 10, 12)).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    private boolean s() {
        short r2 = r();
        if (!(r2 == 257 || r2 == 512)) {
            switch (r2) {
                case 514:
                case 515:
                case 516:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private void b(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDevicePubKey ...");
        this.y = Arrays.copyOfRange(bArr, 0, 12);
        this.x = Arrays.copyOfRange(bArr, 12, 76);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 11, 76);
        copyOfRange[0] = 4;
        try {
            this.w = ECCPointConvert.a(copyOfRange, ((ECPublicKey) this.v.getPublic()).getParams());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!s()) {
            a(-37);
        } else if (516 == r()) {
            DeviceApi.c(new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    if (TextUtils.isEmpty(str)) {
                        BluetoothMyLogger.e("BleSecurityChipRegisterConnector get bind key is empty");
                        BleSecurityChipRegisterConnector.this.a(-46);
                        return;
                    }
                    String unused = BleSecurityChipRegisterConnector.this.Q = str;
                    BleSecurityChipRegisterConnector.this.h(str.getBytes());
                }

                public void onFailure(Error error) {
                    BluetoothMyLogger.e("BleSecurityChipRegisterConnector get bind key failed: " + error.toString());
                    BleSecurityChipRegisterConnector.this.a(-46);
                }
            });
        } else {
            this.d.removeMessages(4098);
            this.d.sendEmptyMessageDelayed(4098, 15000);
        }
    }

    private void c(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDeviceCert ...");
        this.z = Arrays.copyOf(bArr, bArr.length);
        try {
            this.B = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e2) {
            e2.printStackTrace();
        }
        this.d.removeMessages(4098);
        this.d.sendEmptyMessageDelayed(4098, 15000);
    }

    private String t() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i2 = 0; i2 < 6; i2++) {
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }

    private byte[] u() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private void d(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDeviceManuCert ...");
        this.A = Arrays.copyOf(bArr, bArr.length);
        try {
            this.C = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e2) {
            e2.printStackTrace();
        }
        this.d.removeMessages(4098);
        this.d.sendEmptyMessageDelayed(4098, 15000);
    }

    public String l() {
        return this.B != null ? this.B.getSerialNumber().toString() : "";
    }

    private boolean e(byte[] bArr) {
        byte[] bArr2;
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDeviceSignature ...");
        this.d.removeMessages(4098);
        if (this.B == null || this.C == null || this.w == null) {
            return false;
        }
        if (!SecurityChipUtil.a(SecurityChipUtil.b(), this.C)) {
            BluetoothMyLogger.d("BleSecurityChipRegisterConnector MijiaManufactureCERT is invalid");
            return false;
        } else if (!SecurityChipUtil.a(this.C, this.B)) {
            BluetoothMyLogger.d("BleSecurityChipRegisterConnector MijiaDeviceCert is invalid");
            return false;
        } else {
            this.H = SecurityChipUtil.a(this.w, this.v.getPrivate());
            if (this.H == null) {
                return false;
            }
            short r2 = r();
            try {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector protocol version = " + r2);
                if (257 != r2) {
                    if (512 != r2) {
                        byte[] a2 = SecurityChipUtil.a(bArr);
                        if (TextUtils.isEmpty(this.Q)) {
                            bArr2 = this.H.getEncoded();
                        } else {
                            byte[] a3 = BleMeshRegisterConnector.a(this.H.getEncoded());
                            byte[] bytes = this.Q.getBytes();
                            byte[] bArr3 = new byte[(a3.length + bytes.length)];
                            System.arraycopy(a3, 0, bArr3, 0, a3.length);
                            System.arraycopy(bytes, 0, bArr3, a3.length, bytes.length);
                            bArr2 = bArr3;
                        }
                        if (SecurityChipUtil.a(bArr2, a2, this.B.getPublicKey())) {
                            this.J = new byte[16];
                            BluetoothMyLogger.d("BleSecurityChipRegisterConnector qrcode oob: " + this.N);
                            if (TextUtils.isEmpty(this.N)) {
                                String t2 = t();
                                a(t2);
                                this.d.sendEmptyMessageDelayed(4099, 30000);
                                System.arraycopy(t2.getBytes(), 0, this.J, 0, t2.getBytes().length);
                            } else {
                                this.d.removeMessages(4098);
                                this.d.sendEmptyMessageDelayed(4098, 15000);
                                byte[] a4 = ByteUtils.a(this.N);
                                System.arraycopy(a4, 0, this.J, 0, Math.min(a4.length, 16));
                            }
                            this.I = u();
                            byte[] bArr4 = new byte[32];
                            System.arraycopy(this.I, 0, bArr4, 0, 16);
                            System.arraycopy(this.J, 0, bArr4, 16, 16);
                            this.K = SecurityChipUtil.a(this.H.getEncoded(), bArr4);
                            this.O = Base64Coder.a(bArr, 24);
                            this.P = Base64Coder.a(BleMeshRegisterConnector.a(this.H.getEncoded()), 24);
                            if (514 == r2) {
                                this.D = a(this.H.getEncoded(), this.J);
                            } else {
                                byte[] b = b(this.H.getEncoded(), this.J);
                                this.D = new byte[32];
                                this.E = new byte[16];
                                this.F = new byte[16];
                                System.arraycopy(b, 0, this.D, 0, 32);
                                System.arraycopy(b, 32, this.E, 0, 16);
                                System.arraycopy(b, 48, this.F, 0, 16);
                            }
                            String s2 = BluetoothCache.s(e());
                            int t3 = BluetoothCache.t(e());
                            if (!TextUtils.isEmpty(s2) && t3 != 0) {
                                BluetoothMyLogger.e("BleSecurityChipRegisterConnector encryptLtmk");
                                this.D = ByteUtils.a(LtmkEncryptUtil.a(s2, ByteUtils.d(this.D), t3));
                            }
                            return true;
                        }
                        return false;
                    }
                }
                byte[] b2 = SecurityChipUtil.b(Arrays.copyOfRange(k(this.H.getEncoded()), 0, 16), new byte[]{16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}, bArr);
                if (b2 != null) {
                    byte[] a5 = SecurityChipUtil.a(e());
                    ByteBuffer allocate = ByteBuffer.allocate(82);
                    allocate.put(this.y);
                    allocate.put(a5);
                    allocate.put(this.x);
                    if (SecurityChipUtil.a(allocate.array(), SecurityChipUtil.a(b2), this.B.getPublicKey())) {
                        this.O = Base64Coder.a(b2, 24);
                        this.P = Base64Coder.a(BleMeshRegisterConnector.a(allocate.array()), 24);
                        this.D = l(this.H.getEncoded());
                        return true;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return false;
        }
    }

    private void f(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDevConfirmation ...");
        this.L = bArr;
        if (!a(this.K, 10, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDevConfirmation onResponse: " + Code.a(i));
                if (i != 0) {
                    BleSecurityChipRegisterConnector.this.a(-28);
                }
            }
        })) {
            a(-28);
        }
    }

    private void g(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDevRandom ...");
        this.d.removeMessages(4098);
        this.d.removeMessages(4099);
        if (!a(this.I, 11, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector recvDevRandom onResponse: " + Code.a(i));
                if (i != 0) {
                    BleSecurityChipRegisterConnector.this.a(-28);
                }
            }
        })) {
            a(-28);
        }
        byte[] bArr2 = new byte[32];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        System.arraycopy(this.J, 0, bArr2, 16, 16);
        if (ByteUtils.a(this.L, SecurityChipUtil.a(this.H.getEncoded(), bArr2))) {
            a(true);
        } else {
            a(false);
        }
    }

    /* access modifiers changed from: private */
    public void h(byte[] bArr) {
        if (!a(bArr, 14, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector sendBindKey onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipRegisterConnector.this.d.removeMessages(4098);
                    BleSecurityChipRegisterConnector.this.d.sendEmptyMessageDelayed(4098, 15000);
                    return;
                }
                BleSecurityChipRegisterConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void a(boolean z2) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector processDeviceAuthComplete ..., isRegisterSuccess = " + z2);
        if (z2) {
            v();
        } else if (ByteUtils.e(this.J)) {
            a(false, -20);
        } else {
            a(false, -34);
        }
    }

    /* access modifiers changed from: private */
    public void a(final boolean z2, final int i2) {
        byte[] bArr;
        byte[] bArr2;
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector Process Step 9 ..., isRegisterSuccess = " + z2);
        if (257 == r()) {
            if (z2) {
                bArr2 = l;
            } else {
                bArr2 = m;
            }
            BleConnectManager.a().a(e(), BluetoothConstants.i, BluetoothConstants.M, bArr2, (BleWriteResponse) new BleWriteResponse() {
                public void a(int i, Void voidR) {
                    BluetoothMyLogger.d("BleSecurityChipRegisterConnector Step 9 onResponse: " + Code.a(i));
                    if (!z2) {
                        BleSecurityChipRegisterConnector.this.a(i2);
                    } else if (i == 0) {
                        BleSecurityChipRegisterConnector.this.c.putByteArray(BluetoothConstants.ai, BleSecurityChipRegisterConnector.this.D);
                        BleSecurityChipRegisterConnector.this.a(0);
                    } else {
                        BleSecurityChipRegisterConnector.this.a(-28);
                    }
                }
            });
            return;
        }
        if (z2) {
            bArr = n;
        } else {
            bArr = o;
            this.M = i2;
        }
        byte[] bArr3 = bArr;
        this.d.removeMessages(4098);
        this.d.sendEmptyMessageDelayed(4098, 15000);
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, bArr3, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipRegisterConnector Step 9 onResponse: " + Code.a(i));
            }
        });
    }

    private void i(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipRegisterConnector receiveDeviceResult ..., value = " + ByteUtils.d(bArr));
        if (ByteUtils.b(bArr, l)) {
            this.d.removeMessages(4098);
            this.c.putByteArray(BluetoothConstants.ai, this.D);
            a(0);
        } else if (ByteUtils.b(bArr, m)) {
            this.d.removeMessages(4098);
            a(this.M);
        } else if (ByteUtils.b(bArr, p)) {
            this.d.removeMessages(4098);
            a(-17);
        }
    }

    private static byte[] j(byte[] bArr) {
        try {
            return Arrays.copyOfRange(Coder.d(bArr), 0, 12);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return Arrays.copyOfRange(bArr, 0, 12);
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return Arrays.copyOfRange(bArr, 0, 12);
        }
    }

    private void v() {
        this.G = j(this.D);
        String j2 = BluetoothCache.j(e());
        String a2 = Base64Coder.a(this.z, 24);
        String a3 = Base64Coder.a(this.A, 24);
        String str = "";
        PluginRecord c = PluginManager.a().c(j2);
        if (!(c == null || c.c() == null)) {
            str = c.c().k();
        }
        final String a4 = a(str, j2);
        BluetoothMyLogger.d(String.format("bindLtmkToServer name = %s, did = %s, mac = %s, model = %s", new Object[]{a4, l(), BluetoothMyLogger.a(e()), j2}));
        String str2 = "";
        if (!ByteUtils.e(this.E)) {
            str2 = ByteUtils.d(this.E);
        }
        String str3 = str2;
        String str4 = "";
        if (!ByteUtils.e(this.F)) {
            str4 = ByteUtils.d(this.F);
        }
        final int t2 = BluetoothCache.t(e());
        DeviceApi.a(e(), j2, ByteUtils.d(this.G), ByteUtils.d(this.D), a4, a2, a3, str3, str4, t2, this.O, this.P, this.Q, new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                BluetoothMyLogger.d(">>> securityChipBindLtmkToServer onSuccess " + bool);
                if (bool.booleanValue()) {
                    BluetoothCache.a(BleSecurityChipRegisterConnector.this.e(), a4);
                    BluetoothCache.d(BleSecurityChipRegisterConnector.this.e(), 2);
                    BleSecurityChipRegisterConnector.this.a(true, 0);
                    if (t2 != 0) {
                        DeviceApi.a(BleSecurityChipRegisterConnector.this.l(), BluetoothCache.u(BleSecurityChipRegisterConnector.this.e()));
                        return;
                    }
                    return;
                }
                BleSecurityChipRegisterConnector.this.a(false, -26);
            }

            public void onFailure(Error error) {
                BluetoothMyLogger.c(">>> securityChipBindLtmkToServer onFailure: " + error);
                if (error.a() == -1) {
                    String b2 = error.b();
                    if (TextUtils.isEmpty(b2) || b2.contains("Unable to resolve")) {
                        BleSecurityChipRegisterConnector.this.a(false, -26);
                        return;
                    }
                    BleSecurityChipRegisterConnector.this.c.putString(BluetoothConstants.ae, BleSecurityChipRegisterConnector.this.l());
                    BleSecurityChipRegisterConnector.this.a(false, -14);
                    return;
                }
                BleSecurityChipRegisterConnector.this.a(false, -26);
            }
        });
    }

    private String a(String str, String str2) {
        int i2;
        String str3;
        ArrayList<BtDevice> d = BluetoothApi.d();
        ArrayList arrayList = new ArrayList();
        Iterator<BtDevice> it = d.iterator();
        while (it.hasNext()) {
            BtDevice next = it.next();
            if (TextUtils.equals(str2, next.l())) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0) {
            boolean z2 = false;
            i2 = 1;
            while (!z2) {
                if (i2 == 1) {
                    str3 = str;
                } else {
                    str3 = str + i2;
                }
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (TextUtils.equals(str3, ((BtDevice) it2.next()).m())) {
                            i2++;
                            z2 = false;
                            break;
                        }
                    } else {
                        z2 = true;
                        break;
                    }
                }
            }
        } else {
            i2 = 1;
        }
        if (i2 == 1) {
            return str;
        }
        return str + i2;
    }

    private byte[] k(byte[] bArr) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, "smartcfg-setup-salt".getBytes());
            return a2.a("smartcfg-setup-info".getBytes(), 64);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private byte[] l(byte[] bArr) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, "smartcfg-masterkey-salt".getBytes());
            return a2.a("smartcfg-masterkey-info".getBytes(), 32);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, bArr2);
            return a2.a("smartcfg-setup-info".getBytes(), 32);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, bArr2);
            return a2.a("smartcfg-setup-info".getBytes(), 64);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        switch (message.what) {
            case 4098:
                BluetoothMyLogger.f("BleSecurityChipRegisterConnector notify timeout");
                a(this.M);
                return;
            case 4099:
                BluetoothMyLogger.f("BleSecurityChipRegisterConnector paircode timeout");
                a(-35);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public byte[] i() {
        return this.G;
    }
}
