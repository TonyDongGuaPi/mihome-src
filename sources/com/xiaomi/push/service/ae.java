package com.xiaomi.push.service;

import com.xiaomi.push.hi;
import java.util.Iterator;
import java.util.List;

final class ae implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f12871a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ boolean f273a;

    ae(List list, boolean z) {
        this.f12871a = list;
        this.f273a = z;
    }

    public void run() {
        int i;
        boolean a2 = ad.a("www.baidu.com:80");
        Iterator it = this.f12871a.iterator();
        while (true) {
            i = 1;
            if (!it.hasNext()) {
                break;
            }
            a2 = a2 || ad.a((String) it.next());
            if (a2 && !this.f273a) {
                break;
            }
        }
        if (!a2) {
            i = 2;
        }
        hi.a(i);
    }
}
