package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.ai;
import com.xiaomi.push.ho;
import com.xiaomi.push.ib;
import com.xiaomi.push.in;
import com.xiaomi.push.service.ak;

final class o extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ in f11560a;
    final /* synthetic */ Context b;

    o(in inVar, Context context) {
        this.f11560a = inVar;
        this.b = context;
    }

    public int a() {
        return 22;
    }

    public void run() {
        if (this.f11560a != null) {
            this.f11560a.a(ak.a());
            aw.a(this.b.getApplicationContext()).a(this.f11560a, ho.Notification, true, (ib) null, true);
        }
    }
}
