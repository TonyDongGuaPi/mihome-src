package cn.com.xm.bt.c;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import cn.com.xm.bt.a.a;
import cn.com.xm.bt.c.d;
import cn.com.xm.bt.profile.b.c;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SuppressLint({"NewApi"})
public class b extends a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f561a = true;
    private boolean b = false;
    private d.a c = null;

    /* access modifiers changed from: protected */
    public boolean o() {
        return true;
    }

    public /* bridge */ /* synthetic */ void a(boolean z) {
        super.a(z);
    }

    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void e() {
        a.a();
        if (this.c != null) {
            this.c.b(d());
        }
        if (this.b) {
            c();
        }
        boolean discoverServices = i().discoverServices();
        a.a("GattPeripheral", "discoverServices return " + discoverServices);
        if (!discoverServices) {
            l();
        }
    }

    /* access modifiers changed from: protected */
    public void f() {
        a.a();
        if (this.c != null) {
            this.c.c(d());
        }
        l();
    }

    /* access modifiers changed from: protected */
    public void g() {
        a.a();
        if (this.c != null) {
            this.c.d(d());
        }
        l();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void h() {
        a.a();
        for (BluetoothGattService a2 : i().getServices()) {
            c.a(a2);
        }
        boolean n = n();
        a.b(n);
        if (!n) {
            k();
        }
    }

    public List<BluetoothGattService> m() {
        return i().getServices();
    }

    /* access modifiers changed from: protected */
    public void c(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        a.a();
    }

    /* access modifiers changed from: protected */
    public void d(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        a.a();
    }

    /* access modifiers changed from: protected */
    public void e(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        a.a();
    }

    /* access modifiers changed from: protected */
    public void a(BluetoothGattDescriptor bluetoothGattDescriptor) {
        a.a();
    }

    /* access modifiers changed from: protected */
    public void b(BluetoothGattDescriptor bluetoothGattDescriptor) {
        a.a();
    }

    protected b(Context context, BluetoothDevice bluetoothDevice, d.a aVar) {
        super(context, bluetoothDevice);
        a.a();
        this.c = aVar;
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"NewApi"})
    public final void j() {
        a.a();
        a.a("device:");
        a.a("         name: " + d().getName());
        a.a("      address: " + d().getAddress());
        switch (d().getBondState()) {
            case 10:
                a.a("   bond state: NONE");
                break;
            case 11:
                a.a("   bond state: BONDING");
                break;
            case 12:
                a.a("   bond state: BONDED");
                break;
        }
        switch (d().getType()) {
            case 0:
                a.a("         type: UNKNOWN");
                break;
            case 1:
                a.a("         type: CLASSIC");
                break;
            case 2:
                a.a("         type: LE");
                break;
            case 3:
                a.a("         type: DUAL");
                break;
        }
        super.j();
    }

    /* access modifiers changed from: package-private */
    public final void k() {
        a.a();
        super.k();
    }

    /* access modifiers changed from: package-private */
    public void l() {
        a.a();
        p();
        super.l();
        int state = BluetoothAdapter.getDefaultAdapter().getState();
        a.e("BluetoothAdapter state:" + state);
        if (!this.f561a) {
            return;
        }
        if (state == 12 || state == 11) {
            try {
                a.a("GattPeripheral", "Delay 5 second before reconnect...");
                Thread.sleep(5000);
            } catch (InterruptedException unused) {
            }
            j();
        }
    }

    public boolean n() {
        a.a();
        boolean o = o();
        a.b(o);
        if (o) {
            a.d("=================================================");
            a.d("============= INITIALIZATION SUCCESS ============");
            a.d("=================================================");
            cn.com.xm.bt.profile.c.a.a(this, true);
            if (this.c != null) {
                this.c.a(d());
            }
            return true;
        }
        a.c("=================================================");
        a.c("============= INITIALIZATION FAILED =============");
        a.c("=================================================");
        if (this.c == null) {
            return false;
        }
        this.c.e(d());
        return false;
    }

    public void p() {
        a.a();
    }

    @SuppressLint({"NewApi"})
    public byte[] g(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        a.a();
        a.a((Object) bluetoothGattCharacteristic);
        if (bluetoothGattCharacteristic == null) {
            return null;
        }
        int a2 = a(bluetoothGattCharacteristic);
        a.b(a2 == 0);
        if (a2 == 0) {
            return bluetoothGattCharacteristic.getValue();
        }
        return null;
    }

    public boolean b(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        a.a();
        a.a((Object) bluetoothGattCharacteristic);
        a.a((Object) bArr);
        if (bluetoothGattCharacteristic == null || bArr == null) {
            return false;
        }
        int a2 = a(bluetoothGattCharacteristic, bArr);
        a.b(a2 == 0);
        if (a2 == 0) {
            return true;
        }
        return false;
    }

    public void b(boolean z) {
        this.f561a = z;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public final boolean c(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        a.a("GattPeripheral", "cmd:" + c.a(bArr));
        if (bluetoothGattCharacteristic == null) {
            return false;
        }
        boolean b2 = b(bluetoothGattCharacteristic, bArr);
        a.a("GattPeripheral", "result:" + b2);
        return b2;
    }

    public final c a(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        a.a("GattPeripheral", "cmd:" + c.a(bArr));
        if (bluetoothGattCharacteristic == null) {
            return null;
        }
        c cVar = new c();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        if (!a(bluetoothGattCharacteristic, (d.b) new d.b(countDownLatch) {
            private final /* synthetic */ CountDownLatch f$1;

            {
                this.f$1 = r2;
            }

            public final void notify(byte[] bArr) {
                b.a(c.this, this.f$1, bArr);
            }
        })) {
            return null;
        }
        if (b(bluetoothGattCharacteristic, bArr)) {
            try {
                countDownLatch.await((long) i, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                a.a("GattPeripheral", "await exception:" + e.getMessage());
            }
        }
        b(bluetoothGattCharacteristic);
        a.a("GattPeripheral", "result:" + cVar);
        return cVar;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(c cVar, CountDownLatch countDownLatch, byte[] bArr) {
        a.a("GattPeripheral", "notify:" + c.a(bArr));
        cVar.b(bArr);
        countDownLatch.countDown();
    }
}
