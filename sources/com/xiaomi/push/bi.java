package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.clientreport.manager.a;
import com.xiaomi.push.ai;

public class bi extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f12649a;

    public bi(Context context) {
        this.f12649a = context;
    }

    private boolean b() {
        return a.a(this.f12649a).a().d();
    }

    public int a() {
        return 100887;
    }

    public void run() {
        try {
            if (b()) {
                a.a(this.f12649a).d();
                b.c(this.f12649a.getPackageName() + "perf  begin upload");
            }
        } catch (Exception e) {
            b.a((Throwable) e);
        }
    }
}
