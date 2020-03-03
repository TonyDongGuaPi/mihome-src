package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.al;
import java.util.ArrayList;

final class ha extends al.b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f12766a;

    ha(Context context) {
        this.f12766a = context;
    }

    public void b() {
        ArrayList arrayList;
        synchronized (gz.d) {
            arrayList = new ArrayList(gz.e);
            gz.e.clear();
        }
        gz.b(this.f12766a, arrayList);
    }
}
