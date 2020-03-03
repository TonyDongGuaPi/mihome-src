package com.xiaomi.smarthome.listcamera.receiver;

import com.xiaomi.smarthome.camera.VideoFrame;

public abstract class Receiver {
    public static final String c = "receiver_thread";
    protected WorkThread d = new WorkThread(c, this);
    protected Callback e;

    public interface Callback {
        void a(String str, VideoFrame videoFrame);
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public abstract void c();

    /* access modifiers changed from: protected */
    public abstract void d();

    /* access modifiers changed from: protected */
    public abstract void e();

    public static class WorkThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        protected volatile boolean f19348a = false;
        private Receiver b;

        public WorkThread(String str, Receiver receiver) {
            super(str);
            setPriority(1);
            this.b = receiver;
        }

        public void run() {
            this.b.a();
            while (this.f19348a) {
                this.b.c();
            }
            this.b.d();
        }

        public synchronized void start() {
            if (!this.f19348a) {
                this.f19348a = true;
                super.start();
            }
        }

        public synchronized void a() {
            if (this.f19348a) {
                this.f19348a = false;
                this.b.e();
            }
        }

        public synchronized void b() {
            if (this.f19348a) {
                this.f19348a = false;
                this.b.e();
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }

        public boolean c() {
            return this.f19348a;
        }
    }

    public void a(Callback callback) {
        this.e = callback;
    }

    public void f() {
        if (!this.d.c()) {
            this.d.start();
        }
    }

    public void g() {
        this.d.a();
    }

    public void h() {
        this.d.b();
    }
}
