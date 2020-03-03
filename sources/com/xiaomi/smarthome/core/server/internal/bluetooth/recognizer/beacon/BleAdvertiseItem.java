package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon;

import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;

public class BleAdvertiseItem {

    /* renamed from: a  reason: collision with root package name */
    public int f14274a;
    public int b;
    public byte[] c;

    public int a() {
        return this.f14274a;
    }

    public void a(int i) {
        this.f14274a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public byte[] c() {
        return this.c;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Type: 0x%02x, ", new Object[]{Integer.valueOf(this.b)}));
        sb.append(String.format("Len: %d, ", new Object[]{Integer.valueOf(this.f14274a)}));
        switch (this.b) {
            case 8:
            case 9:
                str = "%c";
                break;
            default:
                str = "%02x ";
                break;
        }
        try {
            byte[] bArr = this.c;
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format(str, new Object[]{Byte.valueOf(bArr[i])}));
            }
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
        return sb.toString();
    }
}
