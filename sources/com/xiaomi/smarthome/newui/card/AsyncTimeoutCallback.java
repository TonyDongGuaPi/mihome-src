package com.xiaomi.smarthome.newui.card;

import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;

public abstract class AsyncTimeoutCallback<R> extends AsyncCallback<R, Error> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f20456a;

    public AsyncTimeoutCallback(int i) {
        CommonApplication.getGlobalWorkerHandler().postDelayed(new Runnable(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AsyncTimeoutCallback.this.a(this.f$1);
            }
        }, (long) i);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(int i) {
        sendFailureMessage(new Error(-1, "AyncTimeoutCallback timeout " + i));
    }

    public void sendSuccessMessage(R r) {
        if (!this.f20456a) {
            this.f20456a = true;
            super.sendSuccessMessage(r);
        }
    }

    public synchronized void sendFailureMessage(Error error) {
        if (!this.f20456a) {
            this.f20456a = true;
            super.sendFailureMessage(error);
        }
    }
}
