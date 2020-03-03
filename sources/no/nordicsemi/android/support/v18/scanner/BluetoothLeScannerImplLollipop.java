package no.nordicsemi.android.support.v18.scanner;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;

@TargetApi(21)
class BluetoothLeScannerImplLollipop extends BluetoothLeScannerCompat {
    @NonNull
    private final Map<ScanCallback, ScanCallbackWrapperLollipop> d = new HashMap();

    BluetoothLeScannerImplLollipop() {
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull ScanCallback scanCallback, @NonNull Handler handler) {
        ScanCallbackWrapperLollipop scanCallbackWrapperLollipop;
        ScanCallback scanCallback2 = scanCallback;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            boolean isOffloadedScanBatchingSupported = defaultAdapter.isOffloadedScanBatchingSupported();
            boolean isOffloadedFilteringSupported = defaultAdapter.isOffloadedFilteringSupported();
            synchronized (this.d) {
                if (!this.d.containsKey(scanCallback2)) {
                    scanCallbackWrapperLollipop = new ScanCallbackWrapperLollipop(isOffloadedScanBatchingSupported, isOffloadedFilteringSupported, list, scanSettings, scanCallback, handler);
                    this.d.put(scanCallback2, scanCallbackWrapperLollipop);
                } else {
                    throw new IllegalArgumentException("scanner already started with given callback");
                }
            }
            ScanSettings a2 = a(defaultAdapter, scanSettings, false);
            ArrayList<ScanFilter> arrayList = null;
            if (!list.isEmpty() && isOffloadedFilteringSupported && scanSettings.e()) {
                arrayList = a(list);
            }
            bluetoothLeScanner.startScan(arrayList, a2, scanCallbackWrapperLollipop.e);
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void c(@NonNull ScanCallback scanCallback) {
        ScanCallbackWrapperLollipop remove;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            synchronized (this.d) {
                remove = this.d.remove(scanCallback);
            }
            if (remove != null) {
                remove.a();
                bluetoothLeScanner.stopScan(remove.e);
                return;
            }
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull Context context, @NonNull PendingIntent pendingIntent) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        if (defaultAdapter.getBluetoothLeScanner() != null) {
            Intent intent = new Intent(context, ScannerService.class);
            intent.putParcelableArrayListExtra("no.nordicsemi.android.support.v18.EXTRA_FILTERS", new ArrayList(list));
            intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_SETTINGS", scanSettings);
            intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT", pendingIntent);
            intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_START", true);
            context.startService(intent);
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@NonNull Context context, @NonNull PendingIntent pendingIntent) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        if (defaultAdapter.getBluetoothLeScanner() != null) {
            Intent intent = new Intent(context, ScannerService.class);
            intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT", pendingIntent);
            intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_START", false);
            context.startService(intent);
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    public void d(@NonNull ScanCallback scanCallback) {
        ScanCallbackWrapperLollipop scanCallbackWrapperLollipop;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        if (scanCallback != null) {
            synchronized (this.d) {
                scanCallbackWrapperLollipop = this.d.get(scanCallback);
            }
            if (scanCallbackWrapperLollipop != null) {
                ScanSettings scanSettings = scanCallbackWrapperLollipop.b;
                if (!defaultAdapter.isOffloadedScanBatchingSupported() || !scanSettings.f()) {
                    scanCallbackWrapperLollipop.b();
                    return;
                }
                BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.flushPendingScanResults(scanCallbackWrapperLollipop.e);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("callback not registered!");
        }
        throw new IllegalArgumentException("callback cannot be null!");
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ScanSettings a(@NonNull BluetoothAdapter bluetoothAdapter, @NonNull ScanSettings scanSettings, boolean z) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (z || (bluetoothAdapter.isOffloadedScanBatchingSupported() && scanSettings.f())) {
            builder.setReportDelay(scanSettings.m());
        }
        if (scanSettings.a() != -1) {
            builder.setScanMode(scanSettings.a());
        } else {
            builder.setScanMode(0);
        }
        scanSettings.h();
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ArrayList<ScanFilter> a(@NonNull List<ScanFilter> list) {
        ArrayList<ScanFilter> arrayList = new ArrayList<>();
        for (ScanFilter a2 : list) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ScanFilter a(@NonNull ScanFilter scanFilter) {
        ScanFilter.Builder builder = new ScanFilter.Builder();
        builder.setDeviceAddress(scanFilter.d()).setDeviceName(scanFilter.a()).setServiceUuid(scanFilter.b(), scanFilter.c()).setManufacturerData(scanFilter.h(), scanFilter.i(), scanFilter.j());
        if (scanFilter.g() != null) {
            builder.setServiceData(scanFilter.g(), scanFilter.e(), scanFilter.f());
        }
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ScanResult a(@NonNull ScanResult scanResult) {
        return new ScanResult(scanResult.getDevice(), ScanRecord.a(scanResult.getScanRecord() != null ? scanResult.getScanRecord().getBytes() : null), scanResult.getRssi(), scanResult.getTimestampNanos());
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ArrayList<ScanResult> b(@NonNull List<ScanResult> list) {
        ArrayList<ScanResult> arrayList = new ArrayList<>();
        for (ScanResult a2 : list) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    static class ScanCallbackWrapperLollipop extends BluetoothLeScannerCompat.ScanCallbackWrapper {
        /* access modifiers changed from: private */
        @NonNull
        public final ScanCallback e;

        private ScanCallbackWrapperLollipop(boolean z, boolean z2, @NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull ScanCallback scanCallback, @NonNull Handler handler) {
            super(z, z2, list, scanSettings, scanCallback, handler);
            this.e = new ScanCallback() {
                /* access modifiers changed from: private */
                public long b;

                public void onScanResult(final int i, final ScanResult scanResult) {
                    ScanCallbackWrapperLollipop.this.d.post(new Runnable() {
                        public void run() {
                            ScanCallbackWrapperLollipop.this.a(i, ((BluetoothLeScannerImplLollipop) BluetoothLeScannerCompat.a()).a(scanResult));
                        }
                    });
                }

                public void onBatchScanResults(final List<ScanResult> list) {
                    ScanCallbackWrapperLollipop.this.d.post(new Runnable() {
                        public void run() {
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            if (AnonymousClass1.this.b <= (elapsedRealtime - ScanCallbackWrapperLollipop.this.b.m()) + 5) {
                                long unused = AnonymousClass1.this.b = elapsedRealtime;
                                ScanCallbackWrapperLollipop.this.a((List<ScanResult>) ((BluetoothLeScannerImplLollipop) BluetoothLeScannerCompat.a()).b(list));
                            }
                        }
                    });
                }

                public void onScanFailed(final int i) {
                    ScanCallbackWrapperLollipop.this.d.post(new Runnable() {
                        /* JADX WARNING: Failed to process nested try/catch */
                        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002f */
                        @androidx.annotation.RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run() {
                            /*
                                r5 = this;
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this
                                no.nordicsemi.android.support.v18.scanner.ScanSettings r0 = r0.b
                                boolean r0 = r0.g()
                                if (r0 == 0) goto L_0x004b
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this
                                no.nordicsemi.android.support.v18.scanner.ScanSettings r0 = r0.b
                                int r0 = r0.b()
                                r1 = 1
                                if (r0 == r1) goto L_0x004b
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this
                                no.nordicsemi.android.support.v18.scanner.ScanSettings r0 = r0.b
                                r0.h()
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat.a()
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r1 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this     // Catch:{ Exception -> 0x002f }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r1 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this     // Catch:{ Exception -> 0x002f }
                                no.nordicsemi.android.support.v18.scanner.ScanCallback r1 = r1.c     // Catch:{ Exception -> 0x002f }
                                r0.b(r1)     // Catch:{ Exception -> 0x002f }
                            L_0x002f:
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r1 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r1 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this     // Catch:{ Exception -> 0x004a }
                                java.util.List r1 = r1.f3167a     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r2 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r2 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.ScanSettings r2 = r2.b     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r3 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r3 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.ScanCallback r3 = r3.c     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r4 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this     // Catch:{ Exception -> 0x004a }
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r4 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this     // Catch:{ Exception -> 0x004a }
                                android.os.Handler r4 = r4.d     // Catch:{ Exception -> 0x004a }
                                r0.b((java.util.List<no.nordicsemi.android.support.v18.scanner.ScanFilter>) r1, (no.nordicsemi.android.support.v18.scanner.ScanSettings) r2, (no.nordicsemi.android.support.v18.scanner.ScanCallback) r3, (android.os.Handler) r4)     // Catch:{ Exception -> 0x004a }
                            L_0x004a:
                                return
                            L_0x004b:
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop$1 r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.this
                                no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop$ScanCallbackWrapperLollipop r0 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.this
                                int r1 = r3
                                r0.a((int) r1)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplLollipop.ScanCallbackWrapperLollipop.AnonymousClass1.AnonymousClass3.run():void");
                        }
                    });
                }
            };
        }
    }
}
