package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.ECCPointConvert;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.util.Arrays;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.json.JSONObject;

public class BleSecurityChipSharedLoginConnector extends BleSecurityChipConnector {
    private static final String e = "BleSecurityChipSharedLoginConnector";
    private static final int f = 4100;
    private static final byte[] g = {48, 0, 0, 0};
    private static final byte[] h = {49, 0, 0, 0};
    private static final byte[] i = {50, 0, 0, 0};
    private static final byte[] j = {51, 0, 0, 0};
    private static final byte[] k = {52, 0, 0, 0};
    private static final String l = "pref_device_cert";
    /* access modifiers changed from: private */
    public String m;
    private KeyPair n;
    private byte[] o;
    private PublicKey p;
    private X509Certificate q;
    private X509Certificate r;
    private PublicKey s;
    private byte[] t;

    protected BleSecurityChipSharedLoginConnector(IBleDeviceLauncher iBleDeviceLauncher) {
        super(iBleDeviceLauncher);
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i2) {
        if (d()) {
            a(-2);
            return;
        }
        switch (i2) {
            case 1:
                a(bArr);
                return;
            case 2:
                b(bArr);
                return;
            case 3:
                c(bArr);
                return;
            case 4:
                if (d(bArr)) {
                    p();
                    return;
                } else {
                    a(-23);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(BluetoothConstants.i) && uuid2.equals(BluetoothConstants.M)) {
            e(bArr);
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
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 1 ...");
        String x = BluetoothCache.x(e());
        if (TextUtils.isEmpty(x)) {
            BluetoothMyLogger.c("BleSecurityChipSharedLoginConnector shared key id is empty");
            a(-25);
            return;
        }
        String f2 = BluetoothCache.f(e());
        if (TextUtils.isEmpty(f2)) {
            BluetoothMyLogger.c("BleSecurityChipSharedLoginConnector did is empty");
            a(-12);
            return;
        }
        DeviceApi.a(f2, x, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                String str = "";
                if (jSONObject != null) {
                    str = jSONObject.optString("key");
                }
                if (TextUtils.isEmpty(str)) {
                    BluetoothMyLogger.c("BleSecurityChipSharedLoginConnector shared key is empty......");
                    BleSecurityChipSharedLoginConnector.this.a(-19);
                    return;
                }
                String unused = BleSecurityChipSharedLoginConnector.this.m = str;
                BleSecurityChipSharedLoginConnector.this.l();
            }

            public void onFailure(Error error) {
                BluetoothMyLogger.c("BleSecurityChipSharedLoginConnector fetch share key failed");
                if (error == null || error.a() != -6 || error.b() == null || !error.b().contains("key is out of date")) {
                    BleSecurityChipSharedLoginConnector.this.a(-19);
                } else {
                    BleSecurityChipSharedLoginConnector.this.a(-18);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void l() {
        BluetoothCache.d(e(), "".getBytes());
        b((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Step 1 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipSharedLoginConnector.this.m();
                } else {
                    BleSecurityChipSharedLoginConnector.this.a(-27);
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
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 2 ...");
        a((BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Step 2 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipSharedLoginConnector.this.n();
                } else {
                    BleSecurityChipSharedLoginConnector.this.a(-27);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void n() {
        byte[] bArr;
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 3 ...");
        this.n = SecurityChipUtil.a();
        String string = SharePrefsManager.a(BluetoothContextManager.n(), l).getString(e(), "");
        if (!TextUtils.isEmpty(string)) {
            try {
                this.s = ECCPointConvert.a(ByteUtils.a(string), ((ECPublicKey) this.n.getPublic()).getParams());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.s != null) {
            bArr = g;
        } else {
            bArr = k;
        }
        BleConnectManager.a().b(e(), BluetoothConstants.i, BluetoothConstants.M, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Step 3 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipSharedLoginConnector.this.o();
                } else {
                    BleSecurityChipSharedLoginConnector.this.a(-28);
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
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 4 ...");
        if (!a(SecurityChipUtil.a(this.n.getPublic()), 3, (IBleResponse) new IBleResponse() {
            public IBinder asBinder() {
                return null;
            }

            public void onResponse(int i, Bundle bundle) throws RemoteException {
                BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Step 4 onResponse: " + Code.a(i));
                if (i == 0) {
                    BleSecurityChipSharedLoginConnector.this.d.removeMessages(4100);
                    BleSecurityChipSharedLoginConnector.this.d.sendEmptyMessageDelayed(4100, 15000);
                    return;
                }
                BleSecurityChipSharedLoginConnector.this.a(-28);
            }
        })) {
            a(-28);
        }
    }

    private void c(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 5 ...");
        this.o = Arrays.copyOf(bArr, bArr.length);
        byte[] bArr2 = new byte[65];
        bArr2[0] = 4;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        try {
            this.p = ECCPointConvert.a(bArr2, ((ECPublicKey) this.n.getPublic()).getParams());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.d.removeMessages(4100);
        this.d.sendEmptyMessageDelayed(4100, 15000);
    }

    public void a(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 6 ...");
        try {
            this.q = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e2) {
            e2.printStackTrace();
        }
        this.d.removeMessages(4100);
        this.d.sendEmptyMessageDelayed(4100, 15000);
    }

    public void b(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 7 ...");
        try {
            this.r = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e2) {
            e2.printStackTrace();
        }
        this.d.removeMessages(4100);
        this.d.sendEmptyMessageDelayed(4100, 15000);
    }

    private boolean d(byte[] bArr) {
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 8 ...");
        this.d.removeMessages(4100);
        if (this.p == null) {
            return false;
        }
        if (this.s == null && (this.q == null || this.r == null)) {
            return false;
        }
        if (this.s == null) {
            if (!SecurityChipUtil.a(SecurityChipUtil.b(), this.r)) {
                BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector MijiaManufactureCERT is invalid");
                return false;
            } else if (!SecurityChipUtil.a(this.r, this.q)) {
                BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector MijiaDeviceCert is invalid");
                return false;
            } else {
                SharedPreferences.Editor edit = SharePrefsManager.a(BluetoothContextManager.n(), l).edit();
                this.s = this.q.getPublicKey();
                edit.putString(e(), ByteUtils.d(ECCPointConvert.b((ECPublicKey) this.s)));
                edit.apply();
            }
        }
        SecretKey a2 = SecurityChipUtil.a(this.p, this.n.getPrivate());
        if (a2 == null) {
            return false;
        }
        try {
            if (!SecurityChipUtil.a(this.o, SecurityChipUtil.a(bArr), this.s)) {
                return false;
            }
            this.t = f(a2.getEncoded());
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void p() {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 9 ...");
        byte[] a2 = SecurityChipUtil.a(Arrays.copyOfRange(this.t, 16, 32), new byte[]{16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}, ByteUtils.a(this.m));
        if (a2 != null) {
            this.d.removeMessages(4100);
            this.d.sendEmptyMessageDelayed(4100, 15000);
            if (!a(a2, 6, (IBleResponse) new IBleResponse() {
                public IBinder asBinder() {
                    return null;
                }

                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    if (i != 0) {
                        BleSecurityChipSharedLoginConnector.this.a(-28);
                    }
                }
            })) {
                a(-28);
                return;
            }
            return;
        }
        a(-28);
    }

    private void e(byte[] bArr) {
        if (d()) {
            a(-2);
            return;
        }
        BluetoothMyLogger.d("BleSecurityChipSharedLoginConnector Process Step 10 ..., value = " + ByteUtils.d(bArr));
        if (ByteUtils.b(bArr, h)) {
            this.d.removeMessages(4100);
            this.c.putByteArray(BluetoothConstants.aj, this.t);
            a(0);
        } else if (ByteUtils.b(bArr, i)) {
            this.d.removeMessages(4100);
            a(-24);
        } else if (ByteUtils.b(bArr, j)) {
            this.d.removeMessages(4100);
            a(-18);
        } else if (ByteUtils.b(bArr, BleSecurityChipLoginConnector.e)) {
            this.d.removeMessages(4100);
            a(-16);
        }
    }

    private byte[] f(byte[] bArr) {
        try {
            Hkdf a2 = Hkdf.a("HmacSHA256");
            a2.a(bArr, "smartcfg-share-salt".getBytes());
            return a2.a("smartcfg-share-info".getBytes(), 64);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Message message) {
        if (message.what == 4100) {
            BluetoothMyLogger.f("BleSecurityChipSharedLoginConnector notify timeout");
            a(-7);
        }
    }
}
