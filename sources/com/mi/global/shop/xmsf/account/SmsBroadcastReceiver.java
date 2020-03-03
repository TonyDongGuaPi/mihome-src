package com.mi.global.shop.xmsf.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mi.log.LogUtil;
import java.util.concurrent.CountDownLatch;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7312a = "SmsBroadcastReceiver";
    private volatile CountDownLatch b;
    private volatile int c;

    public void onReceive(Context context, Intent intent) {
        this.c = getResultCode();
        LogUtil.b(f7312a, "sms sent, result:" + this.c);
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
