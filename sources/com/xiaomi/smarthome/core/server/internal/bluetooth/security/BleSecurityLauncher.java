package com.xiaomi.smarthome.core.server.internal.bluetooth.security;

import android.os.Bundle;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.BleSecurityLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleMeshLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipLogin;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipSharedLogin;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;

public abstract class BleSecurityLauncher {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14301a = "launcher_type_statis";
    public static final String b = "action.ble.connect";
    public static final String c = "action.ble.auth";
    public static final String d = "action.ble.bind";
    protected int e;
    /* access modifiers changed from: protected */
    public String f;
    protected byte[] g;
    protected BleConnectOptions h;
    protected IBleSecureConnectResponse i;
    protected Bundle j;
    protected volatile boolean k;
    protected IBleDeviceLauncher l;

    /* access modifiers changed from: protected */
    public void a(int i2) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(int i2, Bundle bundle);

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public abstract BleSecurityConnector d();

    public BleSecurityLauncher(String str, int i2, BleConnectOptions bleConnectOptions) {
        this(str, i2, (byte[]) null, bleConnectOptions);
    }

    public BleSecurityLauncher(String str, int i2, byte[] bArr, BleConnectOptions bleConnectOptions) {
        this.l = new IBleDeviceLauncher() {
            public String a() {
                return BleSecurityLauncher.this.f;
            }

            public int b() {
                return BleSecurityLauncher.this.e;
            }

            public byte[] c() {
                return BleSecurityLauncher.this.g;
            }

            public void a(int i, Bundle bundle) {
                BleSecurityLauncher.this.a(BleSecurityLauncher.b, i, bundle);
            }

            public void b(int i, Bundle bundle) {
                BleSecurityLauncher.this.a(BleSecurityLauncher.c, i, bundle);
            }
        };
        this.f = str;
        this.e = i2;
        this.g = bArr;
        this.h = bleConnectOptions;
        this.j = new Bundle();
        this.j.setClassLoader(getClass().getClassLoader());
        this.j.putString(f14301a, ((this instanceof BleSecurityLogin) || (this instanceof BleMeshLogin) || (this instanceof BleSecurityChipLogin) || (this instanceof BleSecurityChipSharedLogin)) ? "login" : MiPushClient.f11511a);
    }

    public void a(IBleSecureConnectResponse iBleSecureConnectResponse) {
        BluetoothMyLogger.d(String.format("%s for %s", new Object[]{getClass().getSimpleName(), BluetoothMyLogger.a(this.f)}));
        this.i = iBleSecureConnectResponse;
        BleSecurityConnector d2 = d();
        if (d2 != null) {
            d2.a(this.h, (BleConnectResponse) new BleConnectResponse() {
                public void a(int i, Bundle bundle) {
                    if (i == 0) {
                        BleSecurityLauncher.this.a(BleSecurityLauncher.c, i, bundle);
                        if (bundle != null) {
                            BleSecurityLauncher.this.j.putAll(bundle);
                        }
                        BleSecurityLauncher.this.a(i, bundle);
                        return;
                    }
                    boolean z = false;
                    if (bundle != null) {
                        z = bundle.getBoolean(BluetoothConstants.ad, false);
                    }
                    if (!z) {
                        BleSecurityLauncher.this.a(BleSecurityLauncher.c, i, bundle);
                    }
                    BleSecurityLauncher.this.a(i);
                    BleSecurityLauncher.this.b(i);
                }
            });
            return;
        }
        throw new IllegalArgumentException("Ble security connector should not be null");
    }

    public void a() {
        this.k = true;
        BleSecurityConnector d2 = d();
        if (d2 != null) {
            d2.b();
        }
        c();
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return this.k;
    }

    /* access modifiers changed from: protected */
    public void b(int i2) {
        if (b()) {
            i2 = -2;
        }
        BluetoothMyLogger.d(String.format("SecureConnect onResponse: code = %s", new Object[]{Code.a(i2)}));
        byte[] p = BluetoothCache.p(this.f);
        if (!ByteUtils.e(p)) {
            this.j.putByteArray("token", p);
        }
        if (this.i != null) {
            try {
                this.j.putString("key_version", BluetoothCache.A(this.f));
                this.i.d(i2, this.j);
            } catch (Exception e2) {
                BluetoothLog.b((Throwable) e2);
            }
        }
        this.i = null;
        if (i2 != 0 && !BluetoothUtils.e(this.f)) {
            BleConnectManager.a().a(this.f);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, int i2) {
        a(str, i2, this.j);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, int r7, android.os.Bundle r8) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = -1886663917(0xffffffff8f8bcb13, float:-1.378468E-29)
            r2 = 1
            r3 = 0
            r4 = 2
            if (r0 == r1) goto L_0x002b
            r1 = -1886645848(0xffffffff8f8c11a8, float:-1.3811867E-29)
            if (r0 == r1) goto L_0x0021
            r1 = -64745569(0xfffffffffc240f9f, float:-3.407414E36)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "action.ble.connect"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0035
            r0 = 0
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "action.ble.bind"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0035
            r0 = 2
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "action.ble.auth"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0035
            r0 = 1
            goto L_0x0036
        L_0x0035:
            r0 = -1
        L_0x0036:
            switch(r0) {
                case 0: goto L_0x0061;
                case 1: goto L_0x0057;
                case 2: goto L_0x004d;
                default: goto L_0x0039;
            }
        L_0x0039:
            java.lang.String r8 = "notifyBindProcess: unknown action = %s, code = %d"
            java.lang.Object[] r0 = new java.lang.Object[r4]
            r0[r3] = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            r0[r2] = r6
            java.lang.String r6 = java.lang.String.format(r8, r0)
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.c(r6)
            goto L_0x006a
        L_0x004d:
            com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse r6 = r5.i
            if (r6 == 0) goto L_0x006a
            com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse r6 = r5.i
            r6.c(r7, r8)
            goto L_0x006a
        L_0x0057:
            com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse r6 = r5.i
            if (r6 == 0) goto L_0x006a
            com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse r6 = r5.i
            r6.b(r7, r8)
            goto L_0x006a
        L_0x0061:
            com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse r6 = r5.i
            if (r6 == 0) goto L_0x006a
            com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse r6 = r5.i
            r6.a(r7, r8)
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecurityLauncher.a(java.lang.String, int, android.os.Bundle):void");
    }
}
