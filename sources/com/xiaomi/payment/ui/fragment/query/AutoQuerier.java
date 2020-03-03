package com.xiaomi.payment.ui.fragment.query;

import com.xiaomi.payment.task.rxjava.RxCountDown;

public class AutoQuerier {

    /* renamed from: a  reason: collision with root package name */
    private int f12486a = 0;
    private int b = 0;
    private int[] c;
    /* access modifiers changed from: private */
    public AutoQuerierCallback d;
    private RxCountDown.ICountDownListener e = new RxCountDown.ICountDownListener() {
        public void a() {
        }

        public void a(long j) {
            AutoQuerier.this.d.a(j);
        }

        public void b() {
            AutoQuerier.this.d.a();
        }

        public void b(long j) {
            AutoQuerier.this.d.b(j);
        }
    };

    public interface AutoQuerierCallback {
        void a();

        void a(long j);

        void b(long j);
    }

    public AutoQuerier(int[] iArr, AutoQuerierCallback autoQuerierCallback) {
        this.c = iArr;
        this.d = autoQuerierCallback;
    }

    public boolean a() {
        return this.b < this.c.length;
    }

    public int b() {
        return this.f12486a;
    }

    public boolean c() {
        boolean d2 = d();
        if (d2) {
            this.b++;
        }
        return d2;
    }

    public boolean d() {
        if (!a()) {
            return false;
        }
        RxCountDown.a(this.c[this.b], this.e);
        this.f12486a++;
        return true;
    }
}
