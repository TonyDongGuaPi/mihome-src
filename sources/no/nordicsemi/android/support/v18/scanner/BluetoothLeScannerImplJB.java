package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;

class BluetoothLeScannerImplJB extends BluetoothLeScannerCompat {
    /* access modifiers changed from: private */
    @NonNull
    public final Map<ScanCallback, BluetoothLeScannerCompat.ScanCallbackWrapper> d = new HashMap();
    @Nullable
    private HandlerThread e;
    /* access modifiers changed from: private */
    @Nullable
    public Handler f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public long h;
    /* access modifiers changed from: private */
    public final Runnable i = new Runnable() {
        @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
        public void run() {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && BluetoothLeScannerImplJB.this.g > 0 && BluetoothLeScannerImplJB.this.h > 0) {
                defaultAdapter.stopLeScan(BluetoothLeScannerImplJB.this.k);
                BluetoothLeScannerImplJB.this.f.postDelayed(BluetoothLeScannerImplJB.this.j, BluetoothLeScannerImplJB.this.g);
            }
        }
    };
    /* access modifiers changed from: private */
    public final Runnable j = new Runnable() {
        @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
        public void run() {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && BluetoothLeScannerImplJB.this.g > 0 && BluetoothLeScannerImplJB.this.h > 0) {
                defaultAdapter.startLeScan(BluetoothLeScannerImplJB.this.k);
                BluetoothLeScannerImplJB.this.f.postDelayed(BluetoothLeScannerImplJB.this.i, BluetoothLeScannerImplJB.this.h);
            }
        }
    };
    /* access modifiers changed from: private */
    public final BluetoothAdapter.LeScanCallback k = new BluetoothAdapter.LeScanCallback() {
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            final ScanResult scanResult = new ScanResult(bluetoothDevice, ScanRecord.a(bArr), i, SystemClock.elapsedRealtimeNanos());
            synchronized (BluetoothLeScannerImplJB.this.d) {
                for (final BluetoothLeScannerCompat.ScanCallbackWrapper scanCallbackWrapper : BluetoothLeScannerImplJB.this.d.values()) {
                    scanCallbackWrapper.d.post(new Runnable() {
                        public void run() {
                            scanCallbackWrapper.a(1, scanResult);
                        }
                    });
                }
            }
        }
    };

    BluetoothLeScannerImplJB() {
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull ScanCallback scanCallback, @NonNull Handler handler) {
        boolean isEmpty;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        synchronized (this.d) {
            if (!this.d.containsKey(scanCallback)) {
                BluetoothLeScannerCompat.ScanCallbackWrapper scanCallbackWrapper = new BluetoothLeScannerCompat.ScanCallbackWrapper(false, false, list, scanSettings, scanCallback, handler);
                isEmpty = this.d.isEmpty();
                this.d.put(scanCallback, scanCallbackWrapper);
            } else {
                throw new IllegalArgumentException("scanner already started with given scanCallback");
            }
        }
        if (this.e == null) {
            this.e = new HandlerThread(BluetoothLeScannerImplJB.class.getName());
            this.e.start();
            this.f = new Handler(this.e.getLooper());
        }
        b();
        if (isEmpty) {
            defaultAdapter.startLeScan(this.k);
        }
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void c(@NonNull ScanCallback scanCallback) {
        BluetoothLeScannerCompat.ScanCallbackWrapper remove;
        boolean isEmpty;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        synchronized (this.d) {
            remove = this.d.remove(scanCallback);
            isEmpty = this.d.isEmpty();
        }
        if (remove != null) {
            remove.a();
            b();
            if (isEmpty) {
                defaultAdapter.stopLeScan(this.k);
                if (this.f != null) {
                    this.f.removeCallbacksAndMessages((Object) null);
                }
                if (this.e != null) {
                    this.e.quitSafely();
                    this.e = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull Context context, @NonNull PendingIntent pendingIntent) {
        BluetoothLeUtils.a(BluetoothAdapter.getDefaultAdapter());
        Intent intent = new Intent(context, ScannerService.class);
        intent.putParcelableArrayListExtra("no.nordicsemi.android.support.v18.EXTRA_FILTERS", new ArrayList(list));
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_SETTINGS", scanSettings);
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT", pendingIntent);
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_START", true);
        context.startService(intent);
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@NonNull Context context, @NonNull PendingIntent pendingIntent) {
        BluetoothLeUtils.a(BluetoothAdapter.getDefaultAdapter());
        Intent intent = new Intent(context, ScannerService.class);
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT", pendingIntent);
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_START", false);
        context.startService(intent);
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    public void d(@NonNull ScanCallback scanCallback) {
        BluetoothLeScannerCompat.ScanCallbackWrapper scanCallbackWrapper;
        BluetoothLeUtils.a(BluetoothAdapter.getDefaultAdapter());
        if (scanCallback != null) {
            synchronized (this.d) {
                scanCallbackWrapper = this.d.get(scanCallback);
            }
            if (scanCallbackWrapper != null) {
                scanCallbackWrapper.b();
                return;
            }
            throw new IllegalArgumentException("callback not registered!");
        }
        throw new IllegalArgumentException("callback cannot be null!");
    }

    private void b() {
        long j2;
        long j3;
        synchronized (this.d) {
            j2 = Long.MAX_VALUE;
            j3 = Long.MAX_VALUE;
            for (BluetoothLeScannerCompat.ScanCallbackWrapper scanCallbackWrapper : this.d.values()) {
                ScanSettings scanSettings = scanCallbackWrapper.b;
                if (scanSettings.n()) {
                    if (j2 > scanSettings.o()) {
                        j2 = scanSettings.o();
                    }
                    if (j3 > scanSettings.p()) {
                        j3 = scanSettings.p();
                    }
                }
            }
        }
        if (j2 >= Long.MAX_VALUE || j3 >= Long.MAX_VALUE) {
            this.h = 0;
            this.g = 0;
            if (this.f != null) {
                this.f.removeCallbacks(this.j);
                this.f.removeCallbacks(this.i);
                return;
            }
            return;
        }
        this.g = j2;
        this.h = j3;
        if (this.f != null) {
            this.f.removeCallbacks(this.j);
            this.f.removeCallbacks(this.i);
            this.f.postDelayed(this.i, this.h);
        }
    }
}
