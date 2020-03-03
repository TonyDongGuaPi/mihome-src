package com.huawei.hms.update.a;

import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.update.a.a.b;
import com.huawei.hms.update.a.a.c;
import java.io.File;

final class l implements b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f5913a;

    l(b bVar) {
        this.f5913a = bVar;
    }

    public void a(int i, c cVar) {
        new Handler(Looper.getMainLooper()).post(new m(this, i, cVar));
    }

    public void a(int i, int i2, int i3, File file) {
        new Handler(Looper.getMainLooper()).post(new n(this, i, i2, i3, file));
    }
}
