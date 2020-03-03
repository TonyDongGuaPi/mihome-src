package com.xiaomi.stat.a;

import java.util.ArrayList;

class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ArrayList f23003a;
    final /* synthetic */ c b;

    f(c cVar, ArrayList arrayList) {
        this.b = cVar;
        this.f23003a = arrayList;
    }

    public void run() {
        this.b.b((ArrayList<Long>) this.f23003a);
    }
}
