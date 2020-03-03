package com.xiaomi.ai.streamplayer;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9942a;

    h(f fVar) {
        this.f9942a = fVar;
    }

    public void run() {
        this.f9942a.f();
        synchronized (this) {
            this.f9942a.b.release();
            boolean unused = this.f9942a.f = true;
        }
    }
}
