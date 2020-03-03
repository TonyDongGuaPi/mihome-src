package no.nordicsemi.android.support.v18.scanner;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

public abstract class BluetoothLeScannerCompat {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3166a = "android.bluetooth.le.extra.LIST_SCAN_RESULT";
    public static final String b = "android.bluetooth.le.extra.ERROR_CODE";
    public static final String c = "android.bluetooth.le.extra.CALLBACK_TYPE";
    private static BluetoothLeScannerCompat d;

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public abstract void b(@NonNull Context context, @NonNull PendingIntent pendingIntent);

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public abstract void b(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull Context context, @NonNull PendingIntent pendingIntent);

    /* access modifiers changed from: package-private */
    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public abstract void b(@NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull ScanCallback scanCallback, @NonNull Handler handler);

    /* access modifiers changed from: package-private */
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public abstract void c(@NonNull ScanCallback scanCallback);

    public abstract void d(@NonNull ScanCallback scanCallback);

    @NonNull
    public static synchronized BluetoothLeScannerCompat a() {
        synchronized (BluetoothLeScannerCompat.class) {
            if (d != null) {
                BluetoothLeScannerCompat bluetoothLeScannerCompat = d;
                return bluetoothLeScannerCompat;
            } else if (Build.VERSION.SDK_INT >= 26) {
                BluetoothLeScannerImplOreo bluetoothLeScannerImplOreo = new BluetoothLeScannerImplOreo();
                d = bluetoothLeScannerImplOreo;
                return bluetoothLeScannerImplOreo;
            } else if (Build.VERSION.SDK_INT >= 23) {
                BluetoothLeScannerImplMarshmallow bluetoothLeScannerImplMarshmallow = new BluetoothLeScannerImplMarshmallow();
                d = bluetoothLeScannerImplMarshmallow;
                return bluetoothLeScannerImplMarshmallow;
            } else if (Build.VERSION.SDK_INT >= 21) {
                BluetoothLeScannerImplLollipop bluetoothLeScannerImplLollipop = new BluetoothLeScannerImplLollipop();
                d = bluetoothLeScannerImplLollipop;
                return bluetoothLeScannerImplLollipop;
            } else {
                BluetoothLeScannerImplJB bluetoothLeScannerImplJB = new BluetoothLeScannerImplJB();
                d = bluetoothLeScannerImplJB;
                return bluetoothLeScannerImplJB;
            }
        }
    }

    BluetoothLeScannerCompat() {
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public final void a(@NonNull ScanCallback scanCallback) {
        if (scanCallback != null) {
            b((List<ScanFilter>) Collections.emptyList(), new ScanSettings.Builder().a(), scanCallback, new Handler(Looper.getMainLooper()));
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public final void a(@Nullable List<ScanFilter> list, @Nullable ScanSettings scanSettings, @NonNull ScanCallback scanCallback) {
        if (scanCallback != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            if (list == null) {
                list = Collections.emptyList();
            }
            if (scanSettings == null) {
                scanSettings = new ScanSettings.Builder().a();
            }
            b(list, scanSettings, scanCallback, handler);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public final void a(@Nullable List<ScanFilter> list, @Nullable ScanSettings scanSettings, @NonNull ScanCallback scanCallback, @Nullable Handler handler) {
        if (scanCallback != null) {
            if (list == null) {
                list = Collections.emptyList();
            }
            if (scanSettings == null) {
                scanSettings = new ScanSettings.Builder().a();
            }
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            b(list, scanSettings, scanCallback, handler);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public final void b(@NonNull ScanCallback scanCallback) {
        if (scanCallback != null) {
            c(scanCallback);
            return;
        }
        throw new IllegalArgumentException("callback is null");
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public final void a(@Nullable List<ScanFilter> list, @Nullable ScanSettings scanSettings, @NonNull Context context, @NonNull PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            throw new IllegalArgumentException("callbackIntent is null");
        } else if (context != null) {
            if (list == null) {
                list = Collections.emptyList();
            }
            if (scanSettings == null) {
                scanSettings = new ScanSettings.Builder().a();
            }
            b(list, scanSettings, context, pendingIntent);
        } else {
            throw new IllegalArgumentException("context is null");
        }
    }

    @RequiresPermission(allOf = {"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"})
    public final void a(@NonNull Context context, @NonNull PendingIntent pendingIntent) {
        if (pendingIntent == null) {
            throw new IllegalArgumentException("callbackIntent is null");
        } else if (context != null) {
            b(context, pendingIntent);
        } else {
            throw new IllegalArgumentException("context is null");
        }
    }

    static class ScanCallbackWrapper {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        final List<ScanFilter> f3167a;
        @NonNull
        final ScanSettings b;
        @NonNull
        final ScanCallback c;
        @NonNull
        final Handler d;
        /* access modifiers changed from: private */
        @NonNull
        public final Object e = new Object();
        private final boolean f;
        private final boolean g;
        private final boolean h;
        /* access modifiers changed from: private */
        public boolean i;
        @NonNull
        private final List<ScanResult> j = new ArrayList();
        @NonNull
        private final Set<String> k = new HashSet();
        /* access modifiers changed from: private */
        @NonNull
        public final Map<String, ScanResult> l = new HashMap();
        @NonNull
        private final Runnable m = new Runnable() {
            public void run() {
                if (!ScanCallbackWrapper.this.i) {
                    ScanCallbackWrapper.this.b();
                    ScanCallbackWrapper.this.d.postDelayed(this, ScanCallbackWrapper.this.b.m());
                }
            }
        };
        @NonNull
        private final Runnable n = new Runnable() {
            public void run() {
                long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                synchronized (ScanCallbackWrapper.this.e) {
                    Iterator it = ScanCallbackWrapper.this.l.values().iterator();
                    while (it.hasNext()) {
                        final ScanResult scanResult = (ScanResult) it.next();
                        if (scanResult.d() < elapsedRealtimeNanos - ScanCallbackWrapper.this.b.i()) {
                            it.remove();
                            ScanCallbackWrapper.this.d.post(new Runnable() {
                                public void run() {
                                    ScanCallbackWrapper.this.c.a(4, scanResult);
                                }
                            });
                        }
                    }
                    if (!ScanCallbackWrapper.this.l.isEmpty()) {
                        ScanCallbackWrapper.this.d.postDelayed(this, ScanCallbackWrapper.this.b.j());
                    }
                }
            }
        };

        ScanCallbackWrapper(boolean z, boolean z2, @NonNull List<ScanFilter> list, @NonNull ScanSettings scanSettings, @NonNull ScanCallback scanCallback, @NonNull Handler handler) {
            this.f3167a = Collections.unmodifiableList(list);
            this.b = scanSettings;
            this.c = scanCallback;
            this.d = handler;
            boolean z3 = false;
            this.i = false;
            this.h = scanSettings.b() != 1 && (!(Build.VERSION.SDK_INT >= 23) || !scanSettings.g());
            this.f = !list.isEmpty() && (!z2 || !scanSettings.e());
            long m2 = scanSettings.m();
            if (m2 > 0 && (!z || !scanSettings.f())) {
                z3 = true;
            }
            this.g = z3;
            if (this.g) {
                handler.postDelayed(this.m, m2);
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.i = true;
            this.d.removeCallbacksAndMessages((Object) null);
            synchronized (this.e) {
                this.l.clear();
                this.k.clear();
                this.j.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.g && !this.i) {
                synchronized (this.e) {
                    this.c.a((List<ScanResult>) new ArrayList(this.j));
                    this.j.clear();
                    this.k.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(int i2, @NonNull ScanResult scanResult) {
            boolean isEmpty;
            ScanResult put;
            if (this.i) {
                return;
            }
            if (this.f3167a.isEmpty() || a(scanResult)) {
                String address = scanResult.a().getAddress();
                if (this.h) {
                    synchronized (this.l) {
                        isEmpty = this.l.isEmpty();
                        put = this.l.put(address, scanResult);
                    }
                    if (put == null && (this.b.b() & 2) > 0) {
                        this.c.a(2, scanResult);
                    }
                    if (isEmpty && (this.b.b() & 4) > 0) {
                        this.d.removeCallbacks(this.n);
                        this.d.postDelayed(this.n, this.b.j());
                    }
                } else if (this.g) {
                    synchronized (this.e) {
                        if (!this.k.contains(address)) {
                            this.j.add(scanResult);
                            this.k.add(address);
                        }
                    }
                } else {
                    this.c.a(i2, scanResult);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(@NonNull List<ScanResult> list) {
            if (!this.i) {
                if (this.f) {
                    ArrayList arrayList = new ArrayList();
                    for (ScanResult next : list) {
                        if (a(next)) {
                            arrayList.add(next);
                        }
                    }
                    list = arrayList;
                }
                this.c.a(list);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(int i2) {
            this.c.a(i2);
        }

        private boolean a(@NonNull ScanResult scanResult) {
            for (ScanFilter a2 : this.f3167a) {
                if (a2.a(scanResult)) {
                    return true;
                }
            }
            return false;
        }
    }
}
