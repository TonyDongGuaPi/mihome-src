package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.manager.a;
import com.xiaomi.push.ai;

public class bh extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f12648a;

    public bh(Context context) {
        this.f12648a = context;
    }

    private boolean b() {
        return a.a(this.f12648a).a().c();
    }

    public int a() {
        return 100886;
    }

    public void run() {
        try {
            if (b()) {
                b.c(this.f12648a.getPackageName() + " begin upload event");
                a.a(this.f12648a).c();
            }
        } catch (Exception e) {
            b.a((Throwable) e);
        }
    }
}
