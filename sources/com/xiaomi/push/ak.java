package com.xiaomi.push;

import com.xiaomi.push.ai;

class ak extends ai.b {
    final /* synthetic */ ai b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ak(ai aiVar, ai.a aVar) {
        super(aVar);
        this.b = aiVar;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        synchronized (this.b.d) {
            this.b.c.remove(this.f12627a.a());
        }
    }
}
