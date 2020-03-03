package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.processor.d;

public class bk implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private d f12651a;
    private Context b;

    public bk(Context context, d dVar) {
        this.b = context;
        this.f12651a = dVar;
    }

    public void run() {
        try {
            if (this.f12651a != null) {
                this.f12651a.b();
            }
        } catch (Exception e) {
            b.a((Throwable) e);
        }
    }
}
