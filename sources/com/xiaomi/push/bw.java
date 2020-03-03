package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.cd;
import java.lang.ref.WeakReference;

public class bw implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private String f12661a;
    private WeakReference<Context> b;

    public bw(String str, WeakReference<Context> weakReference) {
        this.f12661a = str;
        this.b = weakReference;
    }

    public void run() {
        Context context;
        if (this.b != null && (context = (Context) this.b.get()) != null) {
            if (cj.a(this.f12661a) > bv.b) {
                bz a2 = bz.a(this.f12661a);
                by a3 = by.a(this.f12661a);
                a2.a((cd.a) a3);
                a3.a((cd.a) bx.a(context, this.f12661a, 1000));
                cd.a(context).a((cd.a) a2);
                return;
            }
            b.b("=====> do not need clean db");
        }
    }
}
