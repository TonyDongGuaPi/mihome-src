package com.xiaomi.push;

import com.xiaomi.push.al;
import com.xiaomi.push.dk;

class dm extends al.b {

    /* renamed from: a  reason: collision with root package name */
    al.b f12695a;
    final /* synthetic */ dk b;

    dm(dk dkVar) {
        this.b = dkVar;
    }

    public void b() {
        dk.b bVar = (dk.b) this.b.f12691a.peek();
        if (bVar != null && bVar.d()) {
            if (this.b.f12691a.remove(bVar)) {
                this.f12695a = bVar;
            }
            if (this.f12695a != null) {
                this.f12695a.b();
            }
        }
    }

    public void c() {
        if (this.f12695a != null) {
            this.f12695a.c();
        }
    }
}
