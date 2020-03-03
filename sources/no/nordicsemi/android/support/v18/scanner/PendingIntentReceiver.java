package no.nordicsemi.android.support.v18.scanner;

import android.content.BroadcastReceiver;

public class PendingIntentReceiver extends BroadcastReceiver {
    static final String ACTION = "no.nordicsemi.android.support.v18.ACTION_FOUND";
    static final String EXTRA_FILTERS = "no.nordicsemi.android.support.v18.EXTRA_FILTERS";
    static final String EXTRA_MATCH_LOST_INTERVAL = "no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_INTERVAL";
    static final String EXTRA_MATCH_LOST_TIMEOUT = "no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_TIMEOUT";
    static final String EXTRA_MATCH_MODE = "no.nordicsemi.android.support.v18.EXTRA_MATCH_MODE";
    static final String EXTRA_NUM_OF_MATCHES = "no.nordicsemi.android.support.v18.EXTRA_NUM_OF_MATCHES";
    static final String EXTRA_PENDING_INTENT = "no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT";
    static final String EXTRA_SETTINGS = "no.nordicsemi.android.support.v18.EXTRA_SETTINGS";
    static final String EXTRA_USE_HARDWARE_BATCHING = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_BATCHING";
    static final String EXTRA_USE_HARDWARE_CALLBACK_TYPES = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_CALLBACK_TYPES";
    static final String EXTRA_USE_HARDWARE_FILTERING = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_FILTERING";

    /* JADX WARNING: Can't wrap try/catch for region: R(3:32|33|34) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0095, code lost:
        r4.e.a(r0);
        r0 = r1.getParcelableArrayListExtra(no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat.f3166a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a1, code lost:
        if (r0 == null) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a3, code lost:
        r0 = r11.b(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00af, code lost:
        if (r9.m() <= 0) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b1, code lost:
        r4.a((java.util.List<no.nordicsemi.android.support.v18.scanner.ScanResult>) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b9, code lost:
        if (r0.isEmpty() != false) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00bb, code lost:
        r4.a(r1.getIntExtra(no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat.c, 1), r0.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00cc, code lost:
        r0 = r1.getIntExtra(no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat.b, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d2, code lost:
        if (r0 == 0) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d4, code lost:
        r4.a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d7, code lost:
        r4.e.a((android.content.Context) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00dd, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e1, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x00e0 */
    @androidx.annotation.RequiresApi(api = 26)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r21, android.content.Intent r22) {
        /*
            r20 = this;
            r0 = r21
            r1 = r22
            if (r0 == 0) goto L_0x00e5
            if (r1 != 0) goto L_0x000a
            goto L_0x00e5
        L_0x000a:
            java.lang.String r2 = "no.nordicsemi.android.support.v18.EXTRA_PENDING_INTENT"
            android.os.Parcelable r2 = r1.getParcelableExtra(r2)
            android.app.PendingIntent r2 = (android.app.PendingIntent) r2
            if (r2 != 0) goto L_0x0015
            return
        L_0x0015:
            java.lang.String r3 = "no.nordicsemi.android.support.v18.EXTRA_FILTERS"
            java.util.ArrayList r3 = r1.getParcelableArrayListExtra(r3)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_SETTINGS"
            android.os.Parcelable r4 = r1.getParcelableExtra(r4)
            r6 = r4
            android.bluetooth.le.ScanSettings r6 = (android.bluetooth.le.ScanSettings) r6
            if (r3 == 0) goto L_0x00e4
            if (r6 != 0) goto L_0x002a
            goto L_0x00e4
        L_0x002a:
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_BATCHING"
            r15 = 1
            boolean r7 = r1.getBooleanExtra(r4, r15)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_FILTERING"
            boolean r8 = r1.getBooleanExtra(r4, r15)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_USE_HARDWARE_CALLBACK_TYPES"
            boolean r9 = r1.getBooleanExtra(r4, r15)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_TIMEOUT"
            r10 = 10000(0x2710, double:4.9407E-320)
            long r12 = r1.getLongExtra(r4, r10)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_MATCH_LOST_INTERVAL"
            long r16 = r1.getLongExtra(r4, r10)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_MATCH_MODE"
            int r14 = r1.getIntExtra(r4, r15)
            java.lang.String r4 = "no.nordicsemi.android.support.v18.EXTRA_NUM_OF_MATCHES"
            r5 = 3
            int r4 = r1.getIntExtra(r4, r5)
            no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat r18 = no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat.a()
            r10 = r18
            no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo r10 = (no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo) r10
            java.util.ArrayList r19 = r10.c(r3)
            r5 = r10
            r3 = r10
            r10 = r12
            r12 = r16
            r15 = r4
            no.nordicsemi.android.support.v18.scanner.ScanSettings r9 = r5.a(r6, r7, r8, r9, r10, r12, r14, r15)
            android.bluetooth.BluetoothAdapter r4 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            boolean r5 = r4.isOffloadedScanBatchingSupported()
            boolean r6 = r4.isOffloadedFilteringSupported()
            monitor-enter(r18)
            no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo$PendingIntentExecutorWrapper r4 = r3.a((android.app.PendingIntent) r2)     // Catch:{ IllegalStateException -> 0x00e0 }
            if (r4 != 0) goto L_0x0093
            no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo$PendingIntentExecutorWrapper r10 = new no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerImplOreo$PendingIntentExecutorWrapper     // Catch:{ all -> 0x00de }
            r11 = r3
            r3 = r10
            r4 = r5
            r5 = r6
            r6 = r19
            r7 = r9
            r8 = r2
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00de }
            r11.a(r2, r10)     // Catch:{ all -> 0x00de }
            r4 = r10
            goto L_0x0094
        L_0x0093:
            r11 = r3
        L_0x0094:
            monitor-exit(r18)     // Catch:{ all -> 0x00de }
            no.nordicsemi.android.support.v18.scanner.PendingIntentExecutor r2 = r4.e
            r2.a((android.content.Context) r0)
            java.lang.String r0 = "android.bluetooth.le.extra.LIST_SCAN_RESULT"
            java.util.ArrayList r0 = r1.getParcelableArrayListExtra(r0)
            r2 = 0
            if (r0 == 0) goto L_0x00cc
            java.util.ArrayList r0 = r11.b(r0)
            long r5 = r9.m()
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x00b5
            r4.a((java.util.List<no.nordicsemi.android.support.v18.scanner.ScanResult>) r0)
            goto L_0x00d7
        L_0x00b5:
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L_0x00d7
            java.lang.String r3 = "android.bluetooth.le.extra.CALLBACK_TYPE"
            r5 = 1
            int r1 = r1.getIntExtra(r3, r5)
            java.lang.Object r0 = r0.get(r2)
            no.nordicsemi.android.support.v18.scanner.ScanResult r0 = (no.nordicsemi.android.support.v18.scanner.ScanResult) r0
            r4.a(r1, r0)
            goto L_0x00d7
        L_0x00cc:
            java.lang.String r0 = "android.bluetooth.le.extra.ERROR_CODE"
            int r0 = r1.getIntExtra(r0, r2)
            if (r0 == 0) goto L_0x00d7
            r4.a((int) r0)
        L_0x00d7:
            no.nordicsemi.android.support.v18.scanner.PendingIntentExecutor r0 = r4.e
            r1 = 0
            r0.a((android.content.Context) r1)
            return
        L_0x00de:
            r0 = move-exception
            goto L_0x00e2
        L_0x00e0:
            monitor-exit(r18)     // Catch:{ all -> 0x00de }
            return
        L_0x00e2:
            monitor-exit(r18)     // Catch:{ all -> 0x00de }
            throw r0
        L_0x00e4:
            return
        L_0x00e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.support.v18.scanner.PendingIntentReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
