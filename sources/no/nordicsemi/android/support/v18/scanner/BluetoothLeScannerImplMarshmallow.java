package no.nordicsemi.android.support.v18.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanSettings;
import androidx.annotation.NonNull;

@TargetApi(23)
class BluetoothLeScannerImplMarshmallow extends BluetoothLeScannerImplLollipop {
    BluetoothLeScannerImplMarshmallow() {
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public ScanSettings a(@NonNull BluetoothAdapter bluetoothAdapter, @NonNull ScanSettings scanSettings, boolean z) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (z || (bluetoothAdapter.isOffloadedScanBatchingSupported() && scanSettings.f())) {
            builder.setReportDelay(scanSettings.m());
        }
        if (z || scanSettings.g()) {
            builder.setCallbackType(scanSettings.b()).setMatchMode(scanSettings.c()).setNumOfMatches(scanSettings.d());
        }
        builder.setScanMode(scanSettings.a());
        return builder.build();
    }
}
