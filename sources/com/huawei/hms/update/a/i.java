package com.huawei.hms.update.a;

import android.content.Context;
import com.huawei.hms.update.a.a.a;
import com.huawei.hms.update.a.a.b;
import com.huawei.hms.update.a.a.c;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class i implements a {
    private static final Executor b = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final a f5910a;

    public i(a aVar) {
        com.huawei.hms.c.a.a(aVar, "update must not be null.");
        this.f5910a = aVar;
    }

    public Context a() {
        return this.f5910a.a();
    }

    public void b() {
        this.f5910a.b();
    }

    public void a(b bVar) {
        b.execute(new j(this, bVar));
    }

    public void a(b bVar, c cVar) {
        b.execute(new k(this, bVar, cVar));
    }

    /* access modifiers changed from: private */
    public static b c(b bVar) {
        return new l(bVar);
    }
}
