package no.nordicsemi.android.support.v18.scanner;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanFilter;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

@TargetApi(26)
class BluetoothLeScannerImplOreo extends BluetoothLeScannerImplMarshmallow {
    @NonNull
    private final HashMap<PendingIntent, PendingIntentExecutorWrapper> d = new HashMap<>();

    BluetoothLeScannerImplOreo() {
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public PendingIntentExecutorWrapper a(@NonNull PendingIntent pendingIntent) {
        synchronized (this.d) {
            if (!this.d.containsKey(pendingIntent)) {
                return null;
            }
            PendingIntentExecutorWrapper pendingIntentExecutorWrapper = this.d.get(pendingIntent);
            if (pendingIntentExecutorWrapper != null) {
                return pendingIntentExecutorWrapper;
            }
            throw new IllegalStateException("Scanning has been stopped");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(@NonNull PendingIntent pendingIntent, @NonNull PendingIntentExecutorWrapper pendingIntentExecutorWrapper) {
        synchronized (this.d) {
            this.d.put(pendingIntent, pendingIntentExecutorWrapper);
        }
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void b(@Nullable List<ScanFilter> list, @Nullable ScanSettings scanSettings, @NonNull Context context, @NonNull PendingIntent pendingIntent) {
        List<ScanFilter> list2;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            if (scanSettings == null) {
                scanSettings = new ScanSettings.Builder().a();
            }
            if (list != null) {
                list2 = list;
            } else {
                list2 = Collections.emptyList();
            }
            android.bluetooth.le.ScanSettings a2 = a(defaultAdapter, scanSettings, false);
            ArrayList<ScanFilter> arrayList = null;
            if (list != null && defaultAdapter.isOffloadedFilteringSupported() && scanSettings.e()) {
                arrayList = a(list);
            }
            synchronized (this.d) {
                this.d.remove(pendingIntent);
            }
            bluetoothLeScanner.startScan(arrayList, a2, c(list2, scanSettings, context, pendingIntent));
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    /* access modifiers changed from: package-private */
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public void b(@NonNull Context context, @NonNull PendingIntent pendingIntent) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothLeUtils.a(defaultAdapter);
        BluetoothLeScanner bluetoothLeScanner = defaultAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            bluetoothLeScanner.stopScan(c(context, pendingIntent));
            synchronized (this.d) {
                this.d.put(pendingIntent, (Object) null);
            }
            return;
        }
        throw new IllegalStateException("BT le scanner not available");
    }

    @NonNull
    private PendingIntent c(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull Context context, @NonNull PendingIntent pendingIntent) {
        int hashCode = pendingIntent.hashCode();
        Intent intent = new Intent(context, PendingIntentReceiver.class);
        intent.setAction("no.nordicsemi.android.support.v18.ACTION_FOUND");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT", pendingIntent);
        intent.putParcelableArrayListExtra("no.nordicsemi.android.support.v18.EXTRA_FILTERS", a(list));
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_SETTINGS", a(defaultAdapter, scanSettings, true));
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_BATCHING", scanSettings.f());
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_FILTERING", scanSettings.e());
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_CALLBACK_TYPES", scanSettings.g());
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_MATCH_MODE", scanSettings.c());
        intent.putExtra("no.nordicsemi.android.support.v18.EXTRA_NUM_OF_MATCHES", scanSettings.d());
        return PendingIntent.getBroadcast(context, hashCode, intent, 134217728);
    }

    @NonNull
    private PendingIntent c(@NonNull Context context, @NonNull PendingIntent pendingIntent) {
        int hashCode = pendingIntent.hashCode();
        Intent intent = new Intent(context, PendingIntentReceiver.class);
        intent.setAction("no.nordicsemi.android.support.v18.ACTION_FOUND");
        return PendingIntent.getBroadcast(context, hashCode, intent, C.ENCODING_PCM_MU_LAW);
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public android.bluetooth.le.ScanSettings a(@NonNull BluetoothAdapter bluetoothAdapter, @NonNull ScanSettings scanSettings, boolean z) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (z || (bluetoothAdapter.isOffloadedScanBatchingSupported() && scanSettings.f())) {
            builder.setReportDelay(scanSettings.m());
        }
        if (z || scanSettings.g()) {
            builder.setCallbackType(scanSettings.b()).setMatchMode(scanSettings.c()).setNumOfMatches(scanSettings.d());
        }
        builder.setScanMode(scanSettings.a()).setLegacy(scanSettings.k()).setPhy(scanSettings.l());
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ScanSettings a(@NonNull android.bluetooth.le.ScanSettings scanSettings, boolean z, boolean z2, boolean z3, long j, long j2, int i, int i2) {
        return new ScanSettings.Builder().a(scanSettings.getLegacy()).e(scanSettings.getPhy()).b(scanSettings.getCallbackType()).a(scanSettings.getScanMode()).a(scanSettings.getReportDelayMillis()).c(z).b(z2).d(z3).a(j, j2).d(i).c(i2).a();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ArrayList<ScanFilter> c(@NonNull List<ScanFilter> list) {
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
        builder.b(scanFilter.getDeviceAddress()).a(scanFilter.getDeviceName()).a(scanFilter.getServiceUuid(), scanFilter.getServiceUuidMask()).a(scanFilter.getManufacturerId(), scanFilter.getManufacturerData(), scanFilter.getManufacturerDataMask());
        if (scanFilter.getServiceDataUuid() != null) {
            builder.a(scanFilter.getServiceDataUuid(), scanFilter.getServiceData(), scanFilter.getServiceDataMask());
        }
        return builder.a();
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ScanResult a(@NonNull ScanResult scanResult) {
        return new ScanResult(scanResult.getDevice(), ((scanResult.getDataStatus() << 5) | (scanResult.isLegacy() ? 16 : 0)) | scanResult.isConnectable() ? 1 : 0, scanResult.getPrimaryPhy(), scanResult.getSecondaryPhy(), scanResult.getAdvertisingSid(), scanResult.getTxPower(), scanResult.getRssi(), scanResult.getPeriodicAdvertisingInterval(), ScanRecord.a(scanResult.getScanRecord() != null ? scanResult.getScanRecord().getBytes() : null), scanResult.getTimestampNanos());
    }

    static class PendingIntentExecutorWrapper extends BluetoothLeScannerCompat.ScanCallbackWrapper {
        @NonNull
        final PendingIntentExecutor e = ((PendingIntentExecutor) this.c);

        PendingIntentExecutorWrapper(boolean z, boolean z2, @NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull PendingIntent pendingIntent) {
            super(z, z2, list, scanSettings, new PendingIntentExecutor(pendingIntent, scanSettings), new Handler());
        }
    }
}
