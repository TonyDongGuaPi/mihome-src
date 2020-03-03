package com.xiaomi.stat;

import android.database.Cursor;
import com.xiaomi.stat.ab;
import java.util.concurrent.Callable;

class ac implements Callable<Cursor> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ab f23014a;

    ac(ab abVar) {
        this.f23014a = abVar;
    }

    /* renamed from: a */
    public Cursor call() throws Exception {
        try {
            return this.f23014a.g.getWritableDatabase().query(ab.a.b, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
