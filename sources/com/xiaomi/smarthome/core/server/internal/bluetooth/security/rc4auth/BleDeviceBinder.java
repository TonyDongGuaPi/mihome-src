package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothHelper;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.IBleDeviceLauncher;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ByteUtils;

public class BleDeviceBinder implements IBleDeviceBinder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14323a = -1402883792;
    public static final int b = -381567184;
    private IBleDeviceLauncher c;
    /* access modifiers changed from: private */
    public byte[] d;
    /* access modifiers changed from: private */
    public byte[] e;
    private BleBindResponse f;
    private boolean g;
    /* access modifiers changed from: private */
    public int h = 1;
    private final BleReadResponse i = new BleReadResponse() {
        public void a(int i, byte[] bArr) {
            if (i == 0 && !ByteUtils.e(bArr)) {
                BluetoothLog.e("local did(encrypted): " + ByteUtils.d(bArr));
                BluetoothLog.e("local token is " + BleDeviceBinder.this.f());
                byte[] a2 = BLECipher.a(BleDeviceBinder.this.e(), bArr);
                BluetoothLog.e("local did(decrypted): " + ByteUtils.d(a2));
                if (!ByteUtils.f(a2)) {
                    byte[] unused = BleDeviceBinder.this.d = ByteUtils.a(a2, (byte) 0);
                    if (ByteUtils.b(BleDeviceBinder.this.d).startsWith("blt.")) {
                        BluetoothCache.e(BleDeviceBinder.this.c(), BleDeviceBinder.this.g());
                    } else {
                        byte[] unused2 = BleDeviceBinder.this.d = ByteUtils.b;
                    }
                }
            }
            BleDeviceBinder.this.b(BleDeviceBinder.this.j);
        }
    };
    /* access modifiers changed from: private */
    public final BleReadResponse j = new BleReadResponse() {
        public void a(int i, byte[] bArr) {
            if (i == 0 && !ByteUtils.e(bArr)) {
                byte[] unused = BleDeviceBinder.this.e = BLECipher.a(BleDeviceBinder.this.e(), bArr);
            }
            BleDeviceBinder.this.c(BleDeviceBinder.this.k);
        }
    };
    /* access modifiers changed from: private */
    public final BleReadResponse k = new BleReadResponse() {
        public void a(final int i, byte[] bArr) {
            if (i != 0) {
                BleDeviceBinder.this.a(i, (BleWriteResponse) new BleWriteResponse() {
                    public void a(int i, Void voidR) {
                        BleDeviceBinder.this.a(i);
                    }
                });
            } else if (ByteUtils.e(BleDeviceBinder.this.d) && !ByteUtils.e(bArr)) {
                byte[] unused = BleDeviceBinder.this.d = bArr;
                byte[] a2 = ByteUtils.a(bArr, 20, (byte) 0);
                BluetoothLog.e("write SN to Device: " + ByteUtils.d(a2));
                BleDeviceBinder.this.a(BLECipher.a(BleDeviceBinder.this.e(), a2), BleDeviceBinder.this.l);
                BluetoothCache.e(BleDeviceBinder.this.c(), BleDeviceBinder.this.g());
            } else if (ByteUtils.e(BleDeviceBinder.this.d) || !ByteUtils.a(BleDeviceBinder.this.d, bArr)) {
                throw new IllegalStateException("strange exception");
            } else {
                BleDeviceBinder.this.a(BleDeviceBinder.this.m);
            }
        }
    };
    /* access modifiers changed from: private */
    public final BleWriteResponse l = new BleWriteResponse() {
        public void a(int i, Void voidR) {
            if (i == 0) {
                BleDeviceBinder.this.a(BleDeviceBinder.this.m);
            } else {
                BleDeviceBinder.this.a(i);
            }
        }
    };
    /* access modifiers changed from: private */
    public final BleWriteResponse m = new BleWriteResponse() {
        public void a(final int i, Void voidR) {
            if (i == 0) {
                BluetoothCache.d(BleDeviceBinder.this.c(), AccountManager.a().m());
                BluetoothCache.d(BleDeviceBinder.this.c(), 2);
            } else {
                BluetoothCache.d(BleDeviceBinder.this.c(), 1);
            }
            BleDeviceBinder.this.a(i, (BleWriteResponse) new BleWriteResponse() {
                public void a(int i, Void voidR) {
                    BleDeviceBinder.this.a(i);
                }
            });
        }
    };

    public interface BleBindResponse extends BleResponse<Void> {
    }

    static /* synthetic */ int k(BleDeviceBinder bleDeviceBinder) {
        int i2 = bleDeviceBinder.h;
        bleDeviceBinder.h = i2 - 1;
        return i2;
    }

    public BleDeviceBinder(IBleDeviceLauncher iBleDeviceLauncher) {
        this.c = iBleDeviceLauncher;
        if (iBleDeviceLauncher == null) {
            throw new NullPointerException("launcher should not be null");
        }
    }

    /* access modifiers changed from: private */
    public String c() {
        return this.c.a();
    }

    private int d() {
        return this.c.b();
    }

    /* access modifiers changed from: private */
    public byte[] e() {
        return this.c.c();
    }

    /* access modifiers changed from: private */
    public String f() {
        return b(e());
    }

    private String a(byte[] bArr) {
        if (ByteUtils.e(bArr)) {
            return "";
        }
        return new String(bArr);
    }

    private String b(byte[] bArr) {
        if (ByteUtils.e(bArr)) {
            return "";
        }
        return ByteUtils.d(bArr);
    }

    /* access modifiers changed from: private */
    public String g() {
        return a(this.d);
    }

    private String h() {
        return b(this.e);
    }

    public void a(BleReadResponse bleReadResponse) {
        if (this.g) {
            a(-2);
        } else {
            BleConnectManager.a().a(c(), BluetoothConstants.i, BluetoothConstants.P, bleReadResponse);
        }
    }

    public void a(byte[] bArr, BleWriteResponse bleWriteResponse) {
        if (this.g) {
            a(-2);
        } else {
            BleConnectManager.a().a(c(), BluetoothConstants.i, BluetoothConstants.P, bArr, bleWriteResponse);
        }
    }

    public void b(BleReadResponse bleReadResponse) {
        if (this.g) {
            a(-2);
        } else {
            BleConnectManager.a().a(c(), BluetoothConstants.i, BluetoothConstants.Q, bleReadResponse);
        }
    }

    public int J_() {
        return BluetoothHelper.a(d());
    }

    public void b() {
        this.g = true;
    }

    public void a(BleBindResponse bleBindResponse) {
        this.f = bleBindResponse;
        if (bleBindResponse != null) {
            a(this.i);
            return;
        }
        throw new NullPointerException("bind response should not be null");
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.f.a(i2, null);
    }

    /* access modifiers changed from: private */
    public void a(int i2, BleWriteResponse bleWriteResponse) {
        if (this.g) {
            a(-2);
            return;
        }
        if (J_() == 2) {
            i2 = 0;
        }
        byte[] a2 = ByteUtils.a(i2 == 0 ? f14323a : b);
        BluetoothMyLogger.f(String.format("writeBindResultToDevice %d: %s", new Object[]{Integer.valueOf(i2), ByteUtils.d(a2)}));
        BleConnectManager.a().a(c(), BluetoothConstants.i, BluetoothConstants.M, BLECipher.a(e(), a2), bleWriteResponse);
    }

    public void c(final BleReadResponse bleReadResponse) {
        if (this.g) {
            a(-2);
            return;
        }
        DeviceApi.a(g(), c(), PluginManager.a().a(d()), f(), (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                BluetoothMyLogger.d(">>> onSuccess " + str);
                if (!TextUtils.isEmpty(str)) {
                    bleReadResponse.a(0, str.getBytes());
                } else if (BleDeviceBinder.this.h > 0) {
                    BleDeviceBinder.k(BleDeviceBinder.this);
                    BleDeviceBinder.this.c(bleReadResponse);
                } else {
                    int unused = BleDeviceBinder.this.h = 1;
                    bleReadResponse.a(-29, null);
                }
            }

            public void onFailure(Error error) {
                BluetoothMyLogger.d(">>> onFailure " + error);
                if (BleDeviceBinder.this.h > 0) {
                    BleDeviceBinder.k(BleDeviceBinder.this);
                    BleDeviceBinder.this.c(bleReadResponse);
                    return;
                }
                int unused = BleDeviceBinder.this.h = 1;
                bleReadResponse.a(-29, null);
            }
        });
    }

    public void a(final BleWriteResponse bleWriteResponse) {
        if (this.g) {
            a(-2);
            return;
        }
        DeviceApi.b(g(), h(), f(), BluetoothCache.k(c()), new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                BluetoothMyLogger.d("bindSNToServer return " + bool);
                bleWriteResponse.a(bool.booleanValue() ? 0 : -30, null);
            }

            public void onFailure(Error error) {
                BluetoothMyLogger.d("bindSNToServer return failed: " + error);
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
}
