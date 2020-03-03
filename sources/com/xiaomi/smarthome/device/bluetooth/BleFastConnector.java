package com.xiaomi.smarthome.device.bluetooth;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.device.bluetooth.BLEBytesWriter;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.international.SelectServerUtils;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class BleFastConnector {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15070a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final int k = 10;
    private static final byte[] l = "restore".getBytes();
    private static final byte[] m = "retry".getBytes();
    /* access modifiers changed from: private */
    public String n;
    private byte[] o;
    private byte[] p;
    /* access modifiers changed from: private */
    public byte[] q;
    private byte[] r;
    private byte[] s;
    private BleFastConnectResponse t;
    /* access modifiers changed from: private */
    public Bundle u;
    private final Response.BleConnectResponse v = new Response.BleConnectResponse() {
        /* renamed from: a */
        public void onResponse(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleFastConnector onResponse, code = %d", new Object[]{Integer.valueOf(i)}));
            Bundle unused = BleFastConnector.this.u = bundle;
            if (i == 0) {
                byte[] unused2 = BleFastConnector.this.q = BleCacheUtils.q(BleFastConnector.this.n);
                BleFastConnector.this.a();
                return;
            }
            BleFastConnector.this.a(i);
        }
    };

    public interface BleFastConnectResponse extends Response.BleResponse<Bundle> {
    }

    public BleFastConnector(String str, String str2, String str3, String str4, String str5) {
        str2 = str2 == null ? "" : str2;
        str3 = str3 == null ? "" : str3;
        str4 = str4 == null ? "" : str4;
        str5 = str5 == null ? "" : str5;
        this.n = str;
        this.o = str2.getBytes();
        this.p = str3.getBytes();
        this.r = str4.getBytes();
        this.s = str5.getBytes();
    }

    public void a(BleConnectOptions bleConnectOptions, BleFastConnectResponse bleFastConnectResponse) {
        this.t = bleFastConnectResponse;
        BluetoothHelper.a(this.n, bleConnectOptions, this.v);
    }

    /* access modifiers changed from: private */
    public void a() {
        long j2;
        String s2 = CoreApi.a().s();
        try {
            j2 = Long.parseLong(s2);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            j2 = 0;
        }
        if (j2 > 0) {
            BluetoothLog.c(String.format("sendUserId %s", new Object[]{s2}));
            BLEBytesWriter.a(this.n, BluetoothConstants.z, 0, ByteUtils.a(j2), this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
                /* renamed from: a */
                public void onResponse(int i, Void voidR) {
                    if (i == 0) {
                        BleFastConnector.this.e();
                    } else {
                        BleFastConnector.this.a(i);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        int convert = (int) TimeUnit.SECONDS.convert((long) new GregorianCalendar().getTimeZone().getRawOffset(), TimeUnit.MILLISECONDS);
        BluetoothLog.c(String.format("sendTimeZone %ds", new Object[]{Integer.valueOf(convert)}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 5, ByteUtils.a(convert), this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.c();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        String e2 = SelectServerUtils.e();
        byte[] bArr = {0, 0, 0};
        if (!TextUtils.isEmpty(e2)) {
            ByteUtils.a(bArr, e2.getBytes(), 0, 0);
        }
        BluetoothLog.c(String.format("sendZoneCode %s", new Object[]{e2}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 6, bArr, this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.d();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, "");
        BluetoothLog.c(String.format("timeArea %s", new Object[]{str}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 7, str.getBytes(), this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.f();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        BluetoothLog.c(String.format("sendAPSSID %s", new Object[]{new String(this.o)}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 1, this.o, this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.b();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        BluetoothLog.c("sendConfigType " + new String(this.s));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 8, this.s, this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.g();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        BluetoothLog.c("sendBindKey " + new String(this.r));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 9, this.r, this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    String d = SelectServerUtils.d();
                    if (TextUtils.isEmpty(d)) {
                        BleFastConnector.this.h();
                    } else {
                        BleFastConnector.this.a(d);
                    }
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        BluetoothLog.c("sendCountryCode, countryCode = " + str);
        byte[] bArr = {0, 0, 0};
        ByteUtils.a(bArr, str.getBytes(), 0, 0);
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 10, bArr, this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.h();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void h() {
        BluetoothLog.c(String.format("sendAPPWD %s", new Object[]{new String(this.p)}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 2, this.p, this.q, new BLEBytesWriter.BLEBytesWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BleFastConnector.this.i();
                } else {
                    BleFastConnector.this.a(i);
                }
            }
        });
    }

    public void a(BLEBytesWriter.BLEBytesWriteResponse bLEBytesWriteResponse) {
        BluetoothLog.c(String.format("sendRetryCommand %s", new Object[]{ByteUtils.d(m)}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 3, m, this.q, bLEBytesWriteResponse);
    }

    public void b(BLEBytesWriter.BLEBytesWriteResponse bLEBytesWriteResponse) {
        BluetoothLog.c(String.format("sendRestoreCommand %s", new Object[]{ByteUtils.d(l)}));
        BLEBytesWriter.a(this.n, BluetoothConstants.z, 4, l, this.q, bLEBytesWriteResponse);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.t != null) {
            this.t.onResponse(0, this.u);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.t != null) {
            this.t.onResponse(i2, this.u);
        }
    }
}
