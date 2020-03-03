package com.xiaomi.smarthome.library.bluetooth.search;

import android.bluetooth.BluetoothAdapter;
import com.xiaomi.smarthome.library.bluetooth.search.classic.BluetoothClassicSearcher;
import com.xiaomi.smarthome.library.bluetooth.search.le.BluetoothLESearcher;
import java.util.UUID;

public class BluetoothSearcher {

    /* renamed from: a  reason: collision with root package name */
    protected BluetoothAdapter f18540a;
    protected BluetoothSearchResponse b;
    protected UUID[] c;

    public static BluetoothSearcher a(int i) {
        switch (i) {
            case 1:
                return BluetoothClassicSearcher.c();
            case 2:
                return BluetoothLESearcher.c();
            default:
                throw new IllegalStateException(String.format("unknown search type %d", new Object[]{Integer.valueOf(i)}));
        }
    }

    /* access modifiers changed from: protected */
    public void a(BluetoothSearchResponse bluetoothSearchResponse) {
        a((UUID[]) null, bluetoothSearchResponse);
    }

    /* access modifiers changed from: protected */
    public void a(UUID[] uuidArr, BluetoothSearchResponse bluetoothSearchResponse) {
        this.c = uuidArr;
        this.b = bluetoothSearchResponse;
        c();
    }

    /* access modifiers changed from: protected */
    public void a() {
        d();
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public void b() {
        e();
        this.b = null;
    }

    private void c() {
        if (this.b != null) {
            this.b.a();
        }
    }

    /* access modifiers changed from: protected */
    public void a(BluetoothSearchResult bluetoothSearchResult) {
        if (this.b != null) {
            this.b.a(bluetoothSearchResult);
        }
    }

    private void d() {
        if (this.b != null) {
            this.b.b();
        }
    }

    private void e() {
        if (this.b != null) {
            this.b.c();
        }
    }
}
