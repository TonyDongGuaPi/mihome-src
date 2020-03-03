package com.alipay.mobile.security.faceauth.model;

import java.util.TimerTask;

class a extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DetectTimerTask f1042a;

    a(DetectTimerTask detectTimerTask) {
        this.f1042a = detectTimerTask;
    }

    public void run() {
        this.f1042a.b -= this.f1042a.d;
        if (this.f1042a.b <= 0) {
            this.f1042a.b = 0;
            this.f1042a.f1041a.cancel();
        }
        if (this.f1042a.e != null) {
            this.f1042a.e.countdown(this.f1042a.b);
        }
    }
}
