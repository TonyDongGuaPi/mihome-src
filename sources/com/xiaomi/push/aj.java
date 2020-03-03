package com.xiaomi.push;

import com.xiaomi.push.ai;

class aj extends ai.b {
    final /* synthetic */ String b;
    final /* synthetic */ ai c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    aj(ai aiVar, ai.a aVar, String str) {
        super(aVar);
        this.c = aiVar;
        this.b = str;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        super.a();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.c.e.edit().putLong(this.b, System.currentTimeMillis()).commit();
    }
}
