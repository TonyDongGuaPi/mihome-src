package com.alibaba.android.bindingx.core.internal;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Choreographer;

abstract class AnimationFrame {

    interface Callback {
        void c_();
    }

    /* access modifiers changed from: package-private */
    public abstract void a(@NonNull Callback callback);

    /* access modifiers changed from: package-private */
    public abstract void b();

    /* access modifiers changed from: package-private */
    public abstract void c();

    AnimationFrame() {
    }

    static AnimationFrame a() {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ChoreographerAnimationFrameImpl();
        }
        return new HandlerAnimationFrameImpl();
    }

    @TargetApi(16)
    private static class ChoreographerAnimationFrameImpl extends AnimationFrame implements Choreographer.FrameCallback {

        /* renamed from: a  reason: collision with root package name */
        private Choreographer f750a = Choreographer.getInstance();
        private Callback b;
        private boolean c;

        @TargetApi(16)
        ChoreographerAnimationFrameImpl() {
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.f750a != null) {
                this.f750a.removeFrameCallback(this);
            }
            this.c = false;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            b();
            this.f750a = null;
        }

        /* access modifiers changed from: package-private */
        public void a(@NonNull Callback callback) {
            this.b = callback;
            this.c = true;
            if (this.f750a != null) {
                this.f750a.postFrameCallback(this);
            }
        }

        public void doFrame(long j) {
            if (this.b != null) {
                this.b.c_();
            }
            if (this.f750a != null && this.c) {
                this.f750a.postFrameCallback(this);
            }
        }
    }

    private static class HandlerAnimationFrameImpl extends AnimationFrame implements Handler.Callback {
        private static final int e = 100;
        private static final long f = 16;

        /* renamed from: a  reason: collision with root package name */
        private HandlerThread f751a;
        private Handler b;
        private Callback c;
        private boolean d;

        HandlerAnimationFrameImpl() {
            if (this.f751a != null) {
                c();
            }
            this.f751a = new HandlerThread("expression-timing-thread");
            this.f751a.start();
            this.b = new Handler(this.f751a.getLooper(), this);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.b != null) {
                this.b.removeCallbacksAndMessages((Object) null);
            }
            this.d = false;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            b();
            if (Build.VERSION.SDK_INT >= 18) {
                this.f751a.quitSafely();
            } else {
                this.f751a.quit();
            }
            this.b = null;
            this.f751a = null;
        }

        /* access modifiers changed from: package-private */
        public void a(@NonNull Callback callback) {
            this.c = callback;
            this.d = true;
            if (this.b != null) {
                this.b.sendEmptyMessage(100);
            }
        }

        public boolean handleMessage(Message message) {
            if (message == null || message.what != 100 || this.b == null) {
                return false;
            }
            if (this.c != null) {
                this.c.c_();
            }
            if (!this.d) {
                return true;
            }
            this.b.sendEmptyMessageDelayed(100, 16);
            return true;
        }
    }
}
