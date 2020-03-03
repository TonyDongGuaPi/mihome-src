package com.weigan.loopview;

final class SmoothScrollTimerTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    int f9870a = Integer.MAX_VALUE;
    int b = 0;
    int c;
    final LoopView d;

    SmoothScrollTimerTask(LoopView loopView, int i) {
        this.d = loopView;
        this.c = i;
    }

    public final void run() {
        if (this.f9870a == Integer.MAX_VALUE) {
            this.f9870a = this.c;
        }
        this.b = (int) (((float) this.f9870a) * 0.1f);
        if (this.b == 0) {
            if (this.f9870a < 0) {
                this.b = -1;
            } else {
                this.b = 1;
            }
        }
        if (Math.abs(this.f9870a) <= 0) {
            this.d.cancelFuture();
            this.d.handler.sendEmptyMessage(3000);
            return;
        }
        this.d.totalScrollY += this.b;
        this.d.handler.sendEmptyMessage(1000);
        this.f9870a -= this.b;
    }
}
