package com.dianping.logan;

class LoganModel {

    /* renamed from: a  reason: collision with root package name */
    Action f5165a;
    WriteAction b;
    SendAction c;

    enum Action {
        WRITE,
        SEND,
        FLUSH
    }

    LoganModel() {
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        if (this.f5165a != null) {
            if (this.f5165a == Action.SEND && this.c != null && this.c.a()) {
                return true;
            }
            if ((this.f5165a != Action.WRITE || this.b == null || !this.b.a()) && this.f5165a != Action.FLUSH) {
                return false;
            }
            return true;
        }
        return false;
    }
}
