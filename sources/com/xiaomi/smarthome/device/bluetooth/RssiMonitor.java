package com.xiaomi.smarthome.device.bluetooth;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RssiMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<BleDevice, RssiHolder> f15111a = new HashMap<>();
    /* access modifiers changed from: private */
    public static RssiCalculator b = new RssiCalculator() {
        public int a(RssiHolder rssiHolder) {
            super.b(rssiHolder);
            for (int i = this.c; i < this.d; i++) {
                this.f += a(i);
            }
            return this.f / this.e;
        }
    };
    private static RssiCalculator c = new RssiCalculator() {
        public int a(RssiHolder rssiHolder) {
            super.b(rssiHolder);
            return a((this.c + this.d) >>> 1);
        }
    };
    private static RssiCalculator d = new RssiCalculator() {
        public int a(RssiHolder rssiHolder) {
            super.b(rssiHolder);
            for (int i = this.c; i < this.d; i++) {
                this.f += a(i) * a(i);
            }
            return ((int) Math.sqrt((double) this.f)) * -1;
        }
    };

    private interface IRssiCalculator {

        /* renamed from: a  reason: collision with root package name */
        public static final float f15112a = 0.1f;

        int a(RssiHolder rssiHolder);
    }

    public static void a() {
        d();
    }

    private static void d() {
        if (f15111a != null) {
            f15111a.clear();
        }
    }

    public static void a(BleDevice bleDevice) {
        BluetoothLog.e(String.format("addRssiRecord for %s: rssi = %d", new Object[]{bleDevice.mac, Integer.valueOf(bleDevice.rssi)}));
        RssiHolder rssiHolder = f15111a.get(bleDevice);
        if (rssiHolder == null) {
            rssiHolder = new RssiHolder(bleDevice);
            f15111a.put(bleDevice, rssiHolder);
        }
        rssiHolder.a(bleDevice.rssi);
    }

    public static RssiHolder b() {
        RssiHolder rssiHolder = null;
        int i = Integer.MIN_VALUE;
        for (Map.Entry next : f15111a.entrySet()) {
            RssiHolder rssiHolder2 = (RssiHolder) next.getValue();
            int d2 = rssiHolder2.d();
            BluetoothLog.c(String.format("Rssi Avg for %s: %d", new Object[]{next.getKey(), Integer.valueOf(d2)}));
            if (d2 > i) {
                rssiHolder = rssiHolder2;
                i = d2;
            }
        }
        return rssiHolder;
    }

    public static class RssiHolder {

        /* renamed from: a  reason: collision with root package name */
        private static final int f15113a = 64;
        private static final RssiCalculator b = RssiMonitor.b;
        private BleDevice c;
        private int d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int[] f = new int[64];

        public RssiHolder(BleDevice bleDevice) {
            this.c = bleDevice;
        }

        public boolean a(int i) {
            if (this.e >= 64) {
                return false;
            }
            int[] iArr = this.f;
            int i2 = this.e;
            this.e = i2 + 1;
            iArr[i2] = i;
            return true;
        }

        public String a() {
            return this.c.mac;
        }

        public int b() {
            return this.d;
        }

        public BleDevice c() {
            return this.c;
        }

        public int d() {
            this.d = b.a(this);
            return this.d;
        }

        public String toString() {
            return "RssiHolder{mac=" + a() + ", avg=" + b() + Operators.BLOCK_END;
        }
    }

    private static abstract class RssiCalculator implements IRssiCalculator {
        RssiHolder b;
        int c;
        int d;
        int e;
        int f;

        private RssiCalculator() {
        }

        /* access modifiers changed from: package-private */
        public void b(RssiHolder rssiHolder) {
            this.b = rssiHolder;
            this.f = 0;
            this.e = rssiHolder.e;
            Arrays.sort(rssiHolder.f, 0, rssiHolder.e);
            this.c = (int) Math.floor((double) (((float) this.e) * 0.1f));
            this.d = (int) Math.ceil((double) (((float) this.e) * 0.9f));
            this.e = this.d - this.c;
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            return this.b.f[i];
        }
    }
}
