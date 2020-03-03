package com.xiaomi.smarthome.device.bluetooth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import java.util.UUID;

public class BLEBytesWriter {
    private static final int g = 16;
    private static final int h = 20;
    private static final int i = 254;

    /* renamed from: a  reason: collision with root package name */
    private String f15060a;
    private UUID b;
    private int c;
    /* access modifiers changed from: private */
    public byte[] d;
    private byte[] e;
    private BLEBytesWriteResponse f;
    /* access modifiers changed from: private */
    public int j;
    /* access modifiers changed from: private */
    public Handler k = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 16) {
                if (ByteUtils.e(BLEBytesWriter.this.d)) {
                    BLEBytesWriter.this.d();
                } else if (BLEBytesWriter.this.j < BLEBytesWriter.this.d.length) {
                    BLEBytesWriter.this.e();
                } else {
                    BLEBytesWriter.this.b();
                }
            }
        }
    };

    public interface BLEBytesWriteResponse extends Response.BleResponse<Void> {
    }

    private BLEBytesWriter(String str, UUID uuid, int i2, byte[] bArr, byte[] bArr2, BLEBytesWriteResponse bLEBytesWriteResponse) {
        this.f15060a = str;
        this.b = uuid;
        this.c = i2;
        this.e = bArr2;
        this.d = bArr;
        this.f = bLEBytesWriteResponse;
    }

    public static void a(String str, UUID uuid, int i2, byte[] bArr, byte[] bArr2, BLEBytesWriteResponse bLEBytesWriteResponse) {
        new BLEBytesWriter(str, uuid, i2, bArr, bArr2, bLEBytesWriteResponse).a();
    }

    private void a() {
        this.k.obtainMessage(16, 0).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.f != null) {
            this.f.onResponse(0, null);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f != null) {
            this.f.onResponse(-1, null);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        byte[] bArr = {0, (byte) this.c};
        if (!ByteUtils.e(this.e)) {
            bArr = BLECipher.a(this.e, bArr);
        }
        XmBluetoothManager.getInstance().write(this.f15060a, BluetoothConstants.w, this.b, bArr, new Response.BleWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BLEBytesWriter.this.b();
                } else {
                    BLEBytesWriter.this.c();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        int length = this.d.length - this.j;
        int min = Math.min(18, length);
        byte[] bArr = new byte[(min + 2)];
        bArr[0] = (byte) length;
        bArr[1] = (byte) this.c;
        ByteUtils.a(bArr, this.d, 2, this.j);
        this.j += min;
        if (!ByteUtils.e(this.e)) {
            bArr = BLECipher.a(this.e, bArr);
        }
        XmBluetoothManager.getInstance().write(this.f15060a, BluetoothConstants.w, this.b, bArr, new Response.BleWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (i == 0) {
                    BLEBytesWriter.this.k.obtainMessage(16).sendToTarget();
                } else {
                    BLEBytesWriter.this.c();
                }
            }
        });
    }
}
