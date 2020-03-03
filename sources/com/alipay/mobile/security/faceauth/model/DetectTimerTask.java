package com.alipay.mobile.security.faceauth.model;

import java.util.Timer;

public class DetectTimerTask {

    /* renamed from: a  reason: collision with root package name */
    Timer f1041a = null;
    int b = 30000;
    int c = 1000;
    int d = 1000;
    TimerListener e;
    private int f = 30000;

    public interface TimerListener {
        void countdown(int i);
    }

    public DetectTimerTask(int i) {
        this.f = i;
        this.b = i;
    }

    public DetectTimerTask(int i, int i2, int i3) {
        this.f = i;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public void setTimerTaskListener(TimerListener timerListener) {
        this.e = timerListener;
    }

    public int getLeftTime() {
        return this.b;
    }

    public boolean isTimeOut() {
        return this.b == 0;
    }

    public void reset() {
        this.b = this.f;
    }

    public void start() {
        this.b = this.f;
        if (this.e != null) {
            this.e.countdown(this.b);
        }
        stop();
        this.f1041a = new Timer();
        this.f1041a.schedule(new a(this), (long) this.c, (long) this.d);
    }

    public void stop() {
        this.b = this.f;
        if (this.f1041a != null) {
            this.f1041a.cancel();
            this.f1041a = null;
        }
    }
}
