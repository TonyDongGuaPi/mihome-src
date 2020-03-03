package com.xiaomi.smarthome.library.bluetooth.search;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.UUID;

public abstract class BluetoothSearchTask {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18538a = 34;
    private int b;
    private int c;
    private UUID[] d;
    private BluetoothSearcher e;
    private Handler f;

    public BluetoothSearchTask(int i) {
        this(i, 10000);
    }

    public BluetoothSearchTask(int i, int i2) {
        a(i);
        b(i2);
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int b() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public void a(UUID[] uuidArr) {
        this.d = uuidArr;
    }

    public UUID[] c() {
        return this.d;
    }

    public boolean d() {
        return this.b == 2;
    }

    public boolean e() {
        return this.b == 1;
    }

    /* access modifiers changed from: private */
    public BluetoothSearcher g() {
        if (this.e == null) {
            this.e = BluetoothSearcher.a(this.b);
        }
        return this.e;
    }

    public void a(BluetoothSearchResponse bluetoothSearchResponse) {
        g().a(this.d, bluetoothSearchResponse);
        a(34, this.c);
    }

    private void a(int i, int i2) {
        if (this.f == null) {
            this.f = new Handler(Looper.myLooper()) {
                public void handleMessage(Message message) {
                    if (message.what == 34) {
                        BluetoothSearchTask.this.g().a();
                    }
                }
            };
        }
        this.f.sendMessageDelayed(this.f.obtainMessage(i), (long) i2);
    }

    public void f() {
        this.f.removeCallbacksAndMessages((Object) null);
        g().b();
    }

    public String toString() {
        String str;
        if (d()) {
            str = "Ble";
        } else {
            str = e() ? "classic" : "unknown";
        }
        if (this.c >= 1000) {
            return String.format("%s search (%ds)", new Object[]{str, Integer.valueOf(this.c / 1000)});
        }
        double d2 = (double) this.c;
        Double.isNaN(d2);
        return String.format("%s search (%.1fs)", new Object[]{str, Double.valueOf((d2 * 1.0d) / 1000.0d)});
    }
}
