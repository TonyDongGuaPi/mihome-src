package com.xiaomi.payment.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.mibi.common.base.BaseBroadcastReceiver;
import java.util.concurrent.CountDownLatch;

public class SmsBroadcastReceiver extends BaseBroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12402a = "SmsBroadcastReceiver";
    private volatile CountDownLatch b;
    private volatile int c;

    public void onReceive(Context context, Intent intent) {
        this.c = getResultCode();
        Log.d(f12402a, "sms sent, result:" + this.c);
        this.b.countDown();
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.b = countDownLatch;
    }

    public int getResult() {
        return this.c;
    }

    public void reset() {
        this.c = 4;
    }
}
