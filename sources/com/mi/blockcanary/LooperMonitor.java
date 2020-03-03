package com.mi.blockcanary;

import android.os.Debug;
import android.os.SystemClock;
import android.util.Printer;

class LooperMonitor implements Printer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6748a = 3000;
    private long b = 3000;
    private long c = 0;
    private long d = 0;
    /* access modifiers changed from: private */
    public BlockListener e = null;
    private boolean f = false;
    private final boolean g;

    public interface BlockListener {
        void a(long j, long j2, long j3, long j4);
    }

    public LooperMonitor(BlockListener blockListener, long j, boolean z) {
        if (blockListener != null) {
            this.e = blockListener;
            this.b = j;
            this.g = z;
            return;
        }
        throw new IllegalArgumentException("blockListener should not be null.");
    }

    public void println(String str) {
        if (this.g && Debug.isDebuggerConnected()) {
            return;
        }
        if (!this.f) {
            this.c = System.currentTimeMillis();
            this.d = SystemClock.currentThreadTimeMillis();
            this.f = true;
            a();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.f = false;
        if (b(currentTimeMillis)) {
            c(currentTimeMillis);
        }
        b();
    }

    public void a(long j) {
        this.b = j;
    }

    private boolean b(long j) {
        return j - this.c > this.b;
    }

    private void c(long j) {
        final long j2 = this.c;
        final long j3 = this.d;
        final long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        final long j4 = j;
        HandlerThreadFactory.b().post(new Runnable() {
            public void run() {
                LooperMonitor.this.e.a(j2, j4, j3, currentThreadTimeMillis);
            }
        });
    }

    private void a() {
        if (BlockCanaryInternals.a().b != null) {
            BlockCanaryInternals.a().b.a();
        }
        if (BlockCanaryInternals.a().c != null) {
            BlockCanaryInternals.a().c.a();
        }
    }

    private void b() {
        if (BlockCanaryInternals.a().b != null) {
            BlockCanaryInternals.a().b.b();
        }
        if (BlockCanaryInternals.a().c != null) {
            BlockCanaryInternals.a().c.b();
        }
    }
}
