package com.xiaomi.stat.a;

import android.database.DatabaseUtils;
import java.util.concurrent.Callable;

class i implements Callable<Long> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f23006a;

    i(c cVar) {
        this.f23006a = cVar;
    }

    /* renamed from: a */
    public Long call() {
        return Long.valueOf(DatabaseUtils.queryNumEntries(this.f23006a.l.getReadableDatabase(), j.b));
    }
}
