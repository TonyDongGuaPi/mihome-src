package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon;

import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class BleAdvertisement {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14275a;
    private List<BleAdvertiseItem> b = new ArrayList();

    public BleAdvertisement(byte[] bArr) {
        if (!ByteUtils.e(bArr)) {
            this.f14275a = bArr;
            try {
                List<BleAdvertiseItem> a2 = a(this.f14275a);
                if (!ListUtils.a(a2)) {
                    this.b.addAll(a2);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private List<BleAdvertiseItem> a(byte[] bArr) {
        BleAdvertiseItem a2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < bArr.length && (a2 = a(bArr, i)) != null) {
            arrayList.add(a2);
            i += a2.f14274a + 1;
        }
        return arrayList;
    }

    private BleAdvertiseItem a(byte[] bArr, int i) {
        byte b2;
        if (bArr.length - i >= 2 && (b2 = bArr[i]) > 0) {
            byte b3 = bArr[i + 1];
            int i2 = i + 2;
            if (i2 < bArr.length) {
                BleAdvertiseItem bleAdvertiseItem = new BleAdvertiseItem();
                int i3 = (i2 + b2) - 2;
                if (i3 >= bArr.length) {
                    i3 = bArr.length - 1;
                }
                bleAdvertiseItem.b = b3 & 255;
                bleAdvertiseItem.f14274a = b2;
                bleAdvertiseItem.c = ByteUtils.a(bArr, i2, i3);
                return bleAdvertiseItem;
            }
        }
        return null;
    }

    public String a() {
        if (ListUtils.a(this.b)) {
            return "";
        }
        for (BleAdvertiseItem next : this.b) {
            if (next.b == 9) {
                return !ByteUtils.e(next.c) ? new String(next.c) : "";
            }
        }
        return "";
    }

    public List<BleAdvertiseItem> b() {
        return this.b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BleAdvertiseItem bleAdvertiseItem : this.b) {
            sb.append(bleAdvertiseItem.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
