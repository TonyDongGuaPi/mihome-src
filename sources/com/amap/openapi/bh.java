package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.location.collection.CollectionConfig;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class bh {

    /* renamed from: a  reason: collision with root package name */
    private Context f4624a;
    private Looper b;
    /* access modifiers changed from: private */
    public df c;
    /* access modifiers changed from: private */
    public ReentrantReadWriteLock d;
    private boolean e = false;
    private BroadcastReceiver f;
    /* access modifiers changed from: private */
    public Handler g = null;
    /* access modifiers changed from: private */
    public boolean h = true;
    private boolean i = true;
    /* access modifiers changed from: private */
    public int j = 20000;
    private CollectionConfig.FpsCollectorConfig k;
    /* access modifiers changed from: private */
    public final Object l = new Object();
    /* access modifiers changed from: private */
    public List<ScanResult> m = new ArrayList();
    /* access modifiers changed from: private */
    public long n = 0;
    private Comparator<ScanResult> o = new Comparator<ScanResult>() {
        /* renamed from: a */
        public int compare(ScanResult scanResult, ScanResult scanResult2) {
            int compareTo = scanResult.BSSID.compareTo(scanResult.BSSID);
            if (compareTo > 0) {
                return 1;
            }
            return compareTo == 0 ? 0 : -1;
        }
    };

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public List<ScanResult> f4627a = new ArrayList();
        public long b;
    }

    private final class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        bh.this.g();
                        bh.this.i();
                        return;
                    case 1:
                        bh.this.h();
                        bh.this.d.writeLock().lock();
                        if (bh.this.g != null) {
                            bh.this.g.removeCallbacksAndMessages((Object) null);
                            Handler unused = bh.this.g = null;
                        }
                        bh.this.d.writeLock().unlock();
                        return;
                    case 2:
                        bh.this.i();
                        return;
                    default:
                        return;
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public bh(Context context, CollectionConfig.FpsCollectorConfig fpsCollectorConfig, Looper looper) {
        this.f4624a = context;
        this.h = fpsCollectorConfig.b();
        this.j = fpsCollectorConfig.e();
        this.i = fpsCollectorConfig.c();
        this.k = fpsCollectorConfig;
        this.b = looper;
        this.d = new ReentrantReadWriteLock();
        this.c = df.a(this.f4624a);
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null && this.f4624a != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            try {
                this.f4624a.registerReceiver(broadcastReceiver, intentFilter);
            } catch (Throwable unused) {
            }
        }
    }

    private void b(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null && this.f4624a != null) {
            try {
                this.f4624a.unregisterReceiver(broadcastReceiver);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        this.f = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    try {
                        if (intent.getAction() != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                            boolean z = true;
                            try {
                                if (bh.this.j() && intent.getExtras() != null) {
                                    z = intent.getExtras().getBoolean("resultsUpdated", true);
                                }
                            } catch (Throwable unused) {
                            }
                            dl.a(100067);
                            if (z) {
                                synchronized (bh.this.l) {
                                    long unused2 = bh.this.n = System.currentTimeMillis();
                                    List unused3 = bh.this.m = bh.this.c.b();
                                    as.b((List<ScanResult>) bh.this.m);
                                }
                            } else {
                                dl.a(100068);
                            }
                            if (bh.this.h) {
                                bh.this.d.readLock().lock();
                                if (bh.this.g != null) {
                                    bh.this.g.removeMessages(2);
                                    bh.this.g.sendEmptyMessageDelayed(2, (long) bh.this.j);
                                }
                                bh.this.d.readLock().unlock();
                            }
                        }
                    } catch (Throwable unused4) {
                    }
                }
            }
        };
        a(this.f);
    }

    /* access modifiers changed from: private */
    public void h() {
        synchronized (this.l) {
            this.n = 0;
            if (this.m != null) {
                this.m.clear();
            }
        }
        if (this.f != null) {
            b(this.f);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        Object a2;
        if (this.h && this.c != null && this.c.c()) {
            boolean z = false;
            try {
                if (Build.VERSION.SDK_INT < 18 && this.i && (a2 = bc.a(this.c, "startScanActive", new Object[0])) != null && "true".equals(String.valueOf(a2))) {
                    z = true;
                }
            } catch (Exception unused) {
            }
            if (!z) {
                try {
                    this.c.a();
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean j() {
        CollectionConfig.FpsCollectorConfig fpsCollectorConfig = this.k;
        if (this.k != null) {
            return fpsCollectorConfig.d();
        }
        return true;
    }

    public void a() {
        if (!this.e) {
            this.e = true;
            this.d.writeLock().lock();
            try {
                if (this.g == null) {
                    this.g = new b(this.b);
                }
                this.g.sendEmptyMessage(0);
            } finally {
                this.d.writeLock().unlock();
            }
        }
    }

    public void b() {
        if (this.e) {
            this.e = false;
            this.d.readLock().lock();
            try {
                if (this.g != null) {
                    this.g.sendEmptyMessage(1);
                }
            } finally {
                this.d.readLock().unlock();
            }
        }
    }

    public void c() {
        this.d.readLock().lock();
        try {
            if (this.g != null && !this.g.hasMessages(2)) {
                this.g.sendEmptyMessage(2);
            }
        } finally {
            this.d.readLock().unlock();
        }
    }

    public void d() {
        this.d.readLock().lock();
        try {
            if (this.g != null) {
                this.g.removeMessages(2);
            }
        } finally {
            this.d.readLock().unlock();
        }
    }

    public boolean e() {
        return this.e;
    }

    public a f() {
        a aVar = new a();
        synchronized (this.l) {
            if (this.m == null) {
                return aVar;
            }
            for (ScanResult add : this.m) {
                aVar.f4627a.add(add);
            }
            aVar.b = this.n;
            return aVar;
        }
    }
}
