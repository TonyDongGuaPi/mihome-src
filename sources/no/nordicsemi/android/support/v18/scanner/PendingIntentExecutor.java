package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PendingIntentExecutor extends ScanCallback {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    private final PendingIntent f3181a;
    @Nullable
    private Context i;
    @Nullable
    private Context j;
    private long k;
    private long l;

    PendingIntentExecutor(@NonNull PendingIntent pendingIntent, @NonNull ScanSettings scanSettings) {
        this.f3181a = pendingIntent;
        this.l = scanSettings.m();
    }

    PendingIntentExecutor(@NonNull PendingIntent pendingIntent, @NonNull ScanSettings scanSettings, @NonNull Service service) {
        this.f3181a = pendingIntent;
        this.l = scanSettings.m();
        this.j = service;
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable Context context) {
        this.i = context;
    }

    public void a(int i2, @NonNull ScanResult scanResult) {
        Context context = this.i != null ? this.i : this.j;
        if (context != null) {
            try {
                Intent intent = new Intent();
                intent.putExtra(BluetoothLeScannerCompat.c, i2);
                intent.putParcelableArrayListExtra(BluetoothLeScannerCompat.f3166a, new ArrayList(Collections.singletonList(scanResult)));
                this.f3181a.send(context, 0, intent);
            } catch (PendingIntent.CanceledException unused) {
            }
        }
    }

    public void a(@NonNull List<ScanResult> list) {
        Context context = this.i != null ? this.i : this.j;
        if (context != null) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.k <= (elapsedRealtime - this.l) + 5) {
                this.k = elapsedRealtime;
                try {
                    Intent intent = new Intent();
                    intent.putExtra(BluetoothLeScannerCompat.c, 1);
                    intent.putParcelableArrayListExtra(BluetoothLeScannerCompat.f3166a, new ArrayList(list));
                    intent.setExtrasClassLoader(ScanResult.class.getClassLoader());
                    this.f3181a.send(context, 0, intent);
                } catch (PendingIntent.CanceledException unused) {
                }
            }
        }
    }

    public void a(int i2) {
        Context context = this.i != null ? this.i : this.j;
        if (context != null) {
            try {
                Intent intent = new Intent();
                intent.putExtra(BluetoothLeScannerCompat.b, i2);
                this.f3181a.send(context, 0, intent);
            } catch (PendingIntent.CanceledException unused) {
            }
        }
    }
}
