package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import com.amap.location.common.model.CellStatus;
import com.xiaomi.smarthome.download.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class l {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4727a = "l";
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public Handler c;
    /* access modifiers changed from: private */
    public final ReentrantReadWriteLock d;
    private TelephonyManager e;
    /* access modifiers changed from: private */
    public CellLocation f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public SignalStrength h;
    /* access modifiers changed from: private */
    public boolean i;
    private CellLocation j;
    private CellInfo k;
    private Location l;
    private q m = new q();
    private q n = new q();
    private final List<CellStatus.HistoryCell> o = new ArrayList(3);
    private BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent != null && (action = intent.getAction()) != null) {
                char c = 65535;
                if (action.hashCode() == -1076576821 && action.equals("android.intent.action.AIRPLANE_MODE")) {
                    c = 0;
                }
                if (c == 0) {
                    boolean unused = l.this.i = !ax.a(l.this.b);
                    if (!l.this.i) {
                        CellLocation unused2 = l.this.f = null;
                        long unused3 = l.this.g = 0;
                    }
                }
            }
        }
    };
    private PhoneStateListener q = new PhoneStateListener() {
        public void onCellInfoChanged(List<CellInfo> list) {
            l.this.d.readLock().lock();
            try {
                if (l.this.c != null) {
                    l.this.c.post(new Runnable() {
                        public void run() {
                            l.this.e();
                        }
                    });
                }
            } finally {
                l.this.d.readLock().unlock();
            }
        }

        public void onCellLocationChanged(final CellLocation cellLocation) {
            l.this.d.readLock().lock();
            try {
                if (l.this.c != null) {
                    l.this.c.post(new Runnable() {
                        public void run() {
                            CellLocation unused = l.this.f = cellLocation;
                            long unused2 = l.this.g = SystemClock.elapsedRealtime();
                            l.this.e();
                        }
                    });
                }
            } finally {
                l.this.d.readLock().unlock();
            }
        }

        public void onSignalStrengthsChanged(final SignalStrength signalStrength) {
            l.this.d.readLock().lock();
            try {
                if (l.this.c != null) {
                    l.this.c.post(new Runnable() {
                        public void run() {
                            SignalStrength unused = l.this.h = signalStrength;
                            l.this.e();
                        }
                    });
                }
            } finally {
                l.this.d.readLock().unlock();
            }
        }
    };

    public l(Context context, Looper looper) {
        this.b = context;
        this.e = (TelephonyManager) this.b.getSystemService("phone");
        this.c = new Handler(looper);
        this.d = new ReentrantReadWriteLock();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        com.amap.location.common.util.f.a(r2, r1, 3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.amap.openapi.q r6) {
        /*
            r5 = this;
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r0 = r5.o
            monitor-enter(r0)
            java.util.ArrayList<com.amap.openapi.r> r6 = r6.c     // Catch:{ all -> 0x00ee }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x00ee }
        L_0x0009:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x00ee }
            if (r1 == 0) goto L_0x00dc
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x00ee }
            com.amap.openapi.r r1 = (com.amap.openapi.r) r1     // Catch:{ all -> 0x00ee }
            r2 = 1
            byte r3 = r1.b     // Catch:{ all -> 0x00ee }
            if (r2 != r3) goto L_0x0009
            com.amap.location.common.model.CellStatus$HistoryCell r2 = new com.amap.location.common.model.CellStatus$HistoryCell     // Catch:{ all -> 0x00ee }
            r2.<init>()     // Catch:{ all -> 0x00ee }
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x00ee }
            r2.h = r3     // Catch:{ all -> 0x00ee }
            byte r3 = r1.f4741a     // Catch:{ all -> 0x00ee }
            r2.f4586a = r3     // Catch:{ all -> 0x00ee }
            byte r3 = r1.f4741a     // Catch:{ all -> 0x00ee }
            r4 = 3
            switch(r3) {
                case 1: goto L_0x00b4;
                case 2: goto L_0x0081;
                case 3: goto L_0x005a;
                case 4: goto L_0x0030;
                default: goto L_0x002f;
            }     // Catch:{ all -> 0x00ee }
        L_0x002f:
            goto L_0x0009
        L_0x0030:
            T r3 = r1.f     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            T r1 = r1.f     // Catch:{ all -> 0x00ee }
            com.amap.openapi.z r1 = (com.amap.openapi.z) r1     // Catch:{ all -> 0x00ee }
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.a((int) r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.d     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.b(r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            r2.c = r3     // Catch:{ all -> 0x00ee }
            int r3 = r1.d     // Catch:{ all -> 0x00ee }
            r2.d = r3     // Catch:{ all -> 0x00ee }
            int r1 = r1.f     // Catch:{ all -> 0x00ee }
            r2.b = r1     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r1 = r5.o     // Catch:{ all -> 0x00ee }
        L_0x0056:
            com.amap.location.common.util.f.a(r2, r1, r4)     // Catch:{ all -> 0x00ee }
            goto L_0x0009
        L_0x005a:
            T r3 = r1.f     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            T r1 = r1.f     // Catch:{ all -> 0x00ee }
            com.amap.openapi.x r1 = (com.amap.openapi.x) r1     // Catch:{ all -> 0x00ee }
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.a((int) r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.d     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.b(r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            r2.c = r3     // Catch:{ all -> 0x00ee }
            int r3 = r1.d     // Catch:{ all -> 0x00ee }
            r2.d = r3     // Catch:{ all -> 0x00ee }
            int r1 = r1.f     // Catch:{ all -> 0x00ee }
            r2.b = r1     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r1 = r5.o     // Catch:{ all -> 0x00ee }
            goto L_0x0056
        L_0x0081:
            T r3 = r1.f     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            T r1 = r1.f     // Catch:{ all -> 0x00ee }
            com.amap.openapi.p r1 = (com.amap.openapi.p) r1     // Catch:{ all -> 0x00ee }
            int r3 = r1.f4739a     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.c(r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.b     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.d(r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.e(r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.f4739a     // Catch:{ all -> 0x00ee }
            r2.e = r3     // Catch:{ all -> 0x00ee }
            int r3 = r1.b     // Catch:{ all -> 0x00ee }
            r2.f = r3     // Catch:{ all -> 0x00ee }
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            r2.g = r3     // Catch:{ all -> 0x00ee }
            int r1 = r1.f     // Catch:{ all -> 0x00ee }
            r2.b = r1     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r1 = r5.o     // Catch:{ all -> 0x00ee }
            goto L_0x0056
        L_0x00b4:
            T r3 = r1.f     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            T r1 = r1.f     // Catch:{ all -> 0x00ee }
            com.amap.openapi.w r1 = (com.amap.openapi.w) r1     // Catch:{ all -> 0x00ee }
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.a((int) r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.d     // Catch:{ all -> 0x00ee }
            boolean r3 = com.amap.location.common.util.f.b(r3)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x0009
            int r3 = r1.c     // Catch:{ all -> 0x00ee }
            r2.c = r3     // Catch:{ all -> 0x00ee }
            int r3 = r1.d     // Catch:{ all -> 0x00ee }
            r2.d = r3     // Catch:{ all -> 0x00ee }
            int r1 = r1.e     // Catch:{ all -> 0x00ee }
            r2.b = r1     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r1 = r5.o     // Catch:{ all -> 0x00ee }
            goto L_0x0056
        L_0x00dc:
            com.amap.openapi.q r6 = r5.m     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r6 = r6.d     // Catch:{ all -> 0x00ee }
            r6.clear()     // Catch:{ all -> 0x00ee }
            com.amap.openapi.q r6 = r5.m     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r6 = r6.d     // Catch:{ all -> 0x00ee }
            java.util.List<com.amap.location.common.model.CellStatus$HistoryCell> r1 = r5.o     // Catch:{ all -> 0x00ee }
            r6.addAll(r1)     // Catch:{ all -> 0x00ee }
            monitor-exit(r0)     // Catch:{ all -> 0x00ee }
            return
        L_0x00ee:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ee }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.l.a(com.amap.openapi.q):void");
    }

    private boolean b(Location location) {
        return location.distanceTo(this.l) > ((location.getSpeed() > 10.0f ? 1 : (location.getSpeed() == 10.0f ? 0 : -1)) > 0 ? 2000.0f : (location.getSpeed() > 2.0f ? 1 : (location.getSpeed() == 2.0f ? 0 : -1)) > 0 ? 500.0f : 100.0f);
    }

    private CellLocation c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!((this.f == null || this.g == 0 || elapsedRealtime - this.g > Constants.x) ? false : true)) {
            try {
                this.f = this.e != null ? this.e.getCellLocation() : null;
                this.g = elapsedRealtime;
            } catch (Exception unused) {
                this.f = null;
                this.g = 0;
            }
        }
        return this.f;
    }

    private List<CellInfo> d() {
        try {
            if (this.e == null || Build.VERSION.SDK_INT < 17) {
                return null;
            }
            return this.e.getAllCellInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.i) {
            try {
                CellLocation c2 = c();
                CellInfo cellInfo = null;
                if ((c2 instanceof CdmaCellLocation) && -1 == ((CdmaCellLocation) c2).getNetworkId()) {
                    c2 = null;
                }
                List<CellInfo> d2 = d();
                if (d2 != null) {
                    cellInfo = ax.a(d2);
                }
                if (c2 != null || cellInfo != null) {
                    ax.a(this.b, this.n, c2, this.h, d2);
                    as.a((List<r>) this.n.c);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public q a(Location location) {
        if (!this.i) {
            return null;
        }
        CellLocation c2 = c();
        if ((c2 instanceof CdmaCellLocation) && -1 == ((CdmaCellLocation) c2).getNetworkId()) {
            c2 = null;
        }
        List<CellInfo> d2 = d();
        CellInfo a2 = d2 != null ? ax.a(d2) : null;
        if (c2 == null && a2 == null) {
            return null;
        }
        if (!(this.l == null || b(location) || !ax.a(c2, this.j) || !ax.a(a2, this.k))) {
            return null;
        }
        ax.a(this.b, this.m, c2, this.h, d2);
        this.j = c2;
        this.k = a2;
        this.l = location;
        as.a((List<r>) this.m.c);
        a(this.m);
        return this.m;
    }

    public void a() {
        this.i = !ax.a(this.b);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        try {
            this.b.registerReceiver(this.p, intentFilter, (String) null, this.c);
            if (this.e != null) {
                int i2 = 272;
                if (Build.VERSION.SDK_INT >= 17) {
                    i2 = 1296;
                }
                this.e.listen(this.q, i2);
            }
        } catch (Exception unused) {
        }
    }

    public void b() {
        try {
            this.b.unregisterReceiver(this.p);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.e != null) {
            this.e.listen(this.q, 0);
        }
        this.d.writeLock().lock();
        try {
            this.c.removeCallbacksAndMessages((Object) null);
            this.c = null;
        } finally {
            this.d.writeLock().unlock();
        }
    }
}
