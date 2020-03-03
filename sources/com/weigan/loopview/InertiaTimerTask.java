package com.weigan.loopview;

final class InertiaTimerTask implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    float f9865a = 2.14748365E9f;
    final float b;
    final LoopView c;

    InertiaTimerTask(LoopView loopView, float f) {
        this.c = loopView;
        this.b = f;
    }

    public final void run() {
        if (this.f9865a == 2.14748365E9f) {
            if (Math.abs(this.b) <= 2000.0f) {
                this.f9865a = this.b;
            } else if (this.b > 0.0f) {
                this.f9865a = 2000.0f;
            } else {
                this.f9865a = -2000.0f;
            }
        }
        if (Math.abs(this.f9865a) < 0.0f || Math.abs(this.f9865a) > 20.0f) {
            this.c.totalScrollY -= (int) ((this.f9865a * 10.0f) / 1000.0f);
            if (!this.c.isLoop) {
                float f = this.c.lineSpacingMultiplier * ((float) this.c.maxTextHeight);
                if (this.c.totalScrollY <= ((int) (((float) (-this.c.initPosition)) * f))) {
                    this.f9865a = 40.0f;
                    this.c.totalScrollY = (int) (((float) (-this.c.initPosition)) * f);
                } else if (this.c.totalScrollY >= ((int) (((float) ((this.c.items.size() - 1) - this.c.initPosition)) * f))) {
                    this.c.totalScrollY = (int) (((float) ((this.c.items.size() - 1) - this.c.initPosition)) * f);
                    this.f9865a = -40.0f;
                }
            }
            if (this.f9865a < 0.0f) {
                this.f9865a += 20.0f;
            } else {
                this.f9865a -= 20.0f;
            }
            this.c.handler.sendEmptyMessage(1000);
            return;
        }
        this.c.cancelFuture();
        this.c.handler.sendEmptyMessage(2000);
    }
}
