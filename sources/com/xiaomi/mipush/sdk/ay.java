package com.xiaomi.mipush.sdk;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.push.az;
import com.xiaomi.push.service.at;

class ay extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aw f11542a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ay(aw awVar, Handler handler) {
        super(handler);
        this.f11542a = awVar;
    }

    public void onChange(boolean z) {
        Integer unused = this.f11542a.l = Integer.valueOf(at.a(this.f11542a.c).a());
        if (this.f11542a.l.intValue() != 0) {
            this.f11542a.c.getContentResolver().unregisterContentObserver(this);
            if (az.c(this.f11542a.c)) {
                this.f11542a.d();
            }
        }
    }
}
