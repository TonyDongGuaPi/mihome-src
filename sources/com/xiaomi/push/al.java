package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class al {

    /* renamed from: a  reason: collision with root package name */
    private a f12628a;
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public volatile boolean c;
    private final boolean d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public volatile b f;

    private class a extends Thread {
        private final LinkedBlockingQueue<b> b = new LinkedBlockingQueue<>();

        public a() {
            super("PackageProcessor");
        }

        private void a(int i, b bVar) {
            try {
                al.this.b.sendMessage(al.this.b.obtainMessage(i, bVar));
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
            }
        }

        public void a(b bVar) {
            try {
                this.b.add(bVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            long b2 = al.this.e > 0 ? (long) al.this.e : Long.MAX_VALUE;
            while (!al.this.c) {
                try {
                    b poll = this.b.poll(b2, TimeUnit.SECONDS);
                    b unused = al.this.f = poll;
                    if (poll != null) {
                        a(0, poll);
                        poll.b();
                        a(1, poll);
                    } else if (al.this.e > 0) {
                        al.this.a();
                    }
                } catch (InterruptedException e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                }
            }
        }
    }

    public static abstract class b {
        public void a() {
        }

        public abstract void b();

        public void c() {
        }
    }

    public al() {
        this(false);
    }

    public al(boolean z) {
        this(z, 0);
    }

    public al(boolean z, int i) {
        this.b = null;
        this.c = false;
        this.e = 0;
        this.b = new am(this, Looper.getMainLooper());
        this.d = z;
        this.e = i;
    }

    /* access modifiers changed from: private */
    public synchronized void a() {
        this.f12628a = null;
        this.c = true;
    }

    public synchronized void a(b bVar) {
        if (this.f12628a == null) {
            this.f12628a = new a();
            this.f12628a.setDaemon(this.d);
            this.c = false;
            this.f12628a.start();
        }
        this.f12628a.a(bVar);
    }

    public void a(b bVar, long j) {
        this.b.postDelayed(new an(this, bVar), j);
    }
}
