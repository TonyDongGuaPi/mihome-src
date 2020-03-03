package com.mi.blockcanary;

import android.os.Handler;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class AbstractSampler {
    private static final int c = 300;

    /* renamed from: a  reason: collision with root package name */
    protected AtomicBoolean f6734a = new AtomicBoolean(false);
    protected long b;
    /* access modifiers changed from: private */
    public Runnable d = new Runnable() {
        public void run() {
            AbstractSampler.this.c();
            if (AbstractSampler.this.f6734a.get()) {
                HandlerThreadFactory.a().postDelayed(AbstractSampler.this.d, AbstractSampler.this.b);
            }
        }
    };

    /* access modifiers changed from: package-private */
    public abstract void c();

    public AbstractSampler(long j) {
        this.b = 0 == j ? 300 : j;
    }

    public void a() {
        if (!this.f6734a.get()) {
            this.f6734a.set(true);
            HandlerThreadFactory.a().removeCallbacks(this.d);
            Handler a2 = HandlerThreadFactory.a();
            Runnable runnable = this.d;
            double d2 = (double) this.b;
            Double.isNaN(d2);
            a2.postDelayed(runnable, (long) (d2 * 0.8d));
        }
    }

    public void b() {
        if (this.f6734a.get()) {
            this.f6734a.set(false);
            HandlerThreadFactory.a().removeCallbacks(this.d);
        }
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        this.b = j;
        Log.e("AbstractSampler", "setmSampleInterval mSampleInterval " + j);
    }
}
