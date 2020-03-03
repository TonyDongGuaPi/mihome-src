package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

public class ScannerService extends Service {
    static final String EXTRA_FILTERS = "no.nordicsemi.android.support.v18.EXTRA_FILTERS";
    static final String EXTRA_PENDING_INTENT = "no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT";
    static final String EXTRA_SETTINGS = "no.nordicsemi.android.support.v18.EXTRA_SETTINGS";
    static final String EXTRA_START = "no.nordicsemi.android.support.v18.EXTRA_START";

    /* renamed from: a  reason: collision with root package name */
    private static final String f3188a = "ScannerService";
    @NonNull
    private final Object b = new Object();
    private HashMap<PendingIntent, ScanCallback> c;
    private Handler d;

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.c = new HashMap<>();
        this.d = new Handler();
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public int onStartCommand(Intent intent, int i, int i2) {
        boolean containsKey;
        boolean isEmpty;
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(EXTRA_PENDING_INTENT);
        boolean booleanExtra = intent.getBooleanExtra(EXTRA_START, false);
        boolean z = !booleanExtra;
        if (pendingIntent == null) {
            synchronized (this.b) {
                isEmpty = this.c.isEmpty();
            }
            if (isEmpty) {
                stopSelf();
            }
            return 2;
        }
        synchronized (this.b) {
            containsKey = this.c.containsKey(pendingIntent);
        }
        if (booleanExtra && !containsKey) {
            List parcelableArrayListExtra = intent.getParcelableArrayListExtra(EXTRA_FILTERS);
            ScanSettings scanSettings = (ScanSettings) intent.getParcelableExtra(EXTRA_SETTINGS);
            if (parcelableArrayListExtra == null) {
                parcelableArrayListExtra = Collections.emptyList();
            }
            if (scanSettings == null) {
                scanSettings = new ScanSettings.Builder().a();
            }
            a(parcelableArrayListExtra, scanSettings, pendingIntent);
        } else if (z && containsKey) {
            a(pendingIntent);
        }
        return 2;
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public void onDestroy() {
        BluetoothLeScannerCompat a2 = BluetoothLeScannerCompat.a();
        for (ScanCallback b2 : this.c.values()) {
            try {
                a2.b(b2);
            } catch (Exception unused) {
            }
        }
        this.c.clear();
        this.c = null;
        this.d = null;
        super.onDestroy();
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    private void a(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull PendingIntent pendingIntent) {
        PendingIntentExecutor pendingIntentExecutor = new PendingIntentExecutor(pendingIntent, scanSettings, this);
        synchronized (this.b) {
            this.c.put(pendingIntent, pendingIntentExecutor);
        }
        try {
            BluetoothLeScannerCompat.a().b(list, scanSettings, (ScanCallback) pendingIntentExecutor, this.d);
        } catch (Exception e) {
            Log.e(f3188a, "Starting scanning failed", e);
        }
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    private void a(@NonNull PendingIntent pendingIntent) {
        ScanCallback remove;
        boolean isEmpty;
        synchronized (this.b) {
            remove = this.c.remove(pendingIntent);
            isEmpty = this.c.isEmpty();
        }
        if (remove != null) {
            try {
                BluetoothLeScannerCompat.a().b(remove);
            } catch (Exception e) {
                Log.e(f3188a, "Stopping scanning failed", e);
            }
            if (isEmpty) {
                stopSelf();
            }
        }
    }
}
