package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.k;
import java.util.Timer;
import java.util.TimerTask;

public class d {
    private static volatile d b;

    /* renamed from: a  reason: collision with root package name */
    private Timer f9325a = null;
    /* access modifiers changed from: private */
    public Context c = null;

    private d(Context context) {
        this.c = context.getApplicationContext();
        this.f9325a = new Timer(false);
    }

    public static d a(Context context) {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d(context);
                }
            }
        }
        return b;
    }

    public void a() {
        if (StatConfig.a() == StatReportStrategy.PERIOD) {
            long m = (long) (StatConfig.m() * 60 * 1000);
            if (StatConfig.b()) {
                StatLogger b2 = k.b();
                b2.b((Object) "setupPeriodTimer delay:" + m);
            }
            a(new e(this), m);
        }
    }

    public void a(TimerTask timerTask, long j) {
        if (this.f9325a != null) {
            if (StatConfig.b()) {
                StatLogger b2 = k.b();
                b2.b((Object) "setupPeriodTimer schedule delay:" + j);
            }
            this.f9325a.schedule(timerTask, j);
        } else if (StatConfig.b()) {
            k.b().f("setupPeriodTimer schedule timer == null");
        }
    }
}
