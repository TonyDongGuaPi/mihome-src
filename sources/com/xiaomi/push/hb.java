package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;

class hb implements am.b.a {

    /* renamed from: a  reason: collision with root package name */
    private XMPushService f12767a;
    private am.b b;
    private fu c;
    private am.c d;
    private int e;
    private boolean f = false;

    hb(XMPushService xMPushService, am.b bVar) {
        this.f12767a = xMPushService;
        this.d = am.c.binding;
        this.b = bVar;
    }

    private void b() {
        this.b.b(this);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c() {
        /*
            r3 = this;
            r3.b()
            boolean r0 = r3.f
            if (r0 != 0) goto L_0x0008
            return
        L_0x0008:
            int r0 = r3.e
            r1 = 11
            if (r0 != r1) goto L_0x000f
            return
        L_0x000f:
            com.xiaomi.push.hg r0 = com.xiaomi.push.hg.a()
            com.xiaomi.push.fk r0 = r0.f()
            int[] r1 = com.xiaomi.push.hd.f12768a
            com.xiaomi.push.service.am$c r2 = r3.d
            int r2 = r2.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L_0x002e;
                case 2: goto L_0x005b;
                case 3: goto L_0x0025;
                default: goto L_0x0024;
            }
        L_0x0024:
            goto L_0x005b
        L_0x0025:
            com.xiaomi.push.fj r1 = com.xiaomi.push.fj.BIND_SUCCESS
        L_0x0027:
            int r1 = r1.a()
            r0.f73a = r1
            goto L_0x005b
        L_0x002e:
            int r1 = r3.e
            r2 = 17
            if (r1 != r2) goto L_0x0037
            com.xiaomi.push.fj r1 = com.xiaomi.push.fj.BIND_TCP_READ_TIMEOUT
            goto L_0x0027
        L_0x0037:
            int r1 = r3.e
            r2 = 21
            if (r1 != r2) goto L_0x0040
            com.xiaomi.push.fj r1 = com.xiaomi.push.fj.BIND_TIMEOUT
            goto L_0x0027
        L_0x0040:
            com.xiaomi.push.hf r1 = com.xiaomi.push.hg.b()     // Catch:{ NullPointerException -> 0x005a }
            java.lang.Exception r1 = r1.a()     // Catch:{ NullPointerException -> 0x005a }
            com.xiaomi.push.he$a r1 = com.xiaomi.push.he.c(r1)     // Catch:{ NullPointerException -> 0x005a }
            com.xiaomi.push.fj r2 = r1.f12769a     // Catch:{ NullPointerException -> 0x005a }
            int r2 = r2.a()     // Catch:{ NullPointerException -> 0x005a }
            r0.f73a = r2     // Catch:{ NullPointerException -> 0x005a }
            java.lang.String r1 = r1.b     // Catch:{ NullPointerException -> 0x005a }
            r0.c((java.lang.String) r1)     // Catch:{ NullPointerException -> 0x005a }
            goto L_0x005b
        L_0x005a:
            r0 = 0
        L_0x005b:
            if (r0 == 0) goto L_0x0083
            com.xiaomi.push.fu r1 = r3.c
            java.lang.String r1 = r1.e()
            r0.b((java.lang.String) r1)
            com.xiaomi.push.service.am$b r1 = r3.b
            java.lang.String r1 = r1.f293b
            r0.d((java.lang.String) r1)
            r1 = 1
            r0.b = r1
            com.xiaomi.push.service.am$b r1 = r3.b     // Catch:{ NumberFormatException -> 0x007c }
            java.lang.String r1 = r1.g     // Catch:{ NumberFormatException -> 0x007c }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x007c }
            byte r1 = (byte) r1     // Catch:{ NumberFormatException -> 0x007c }
            r0.a((byte) r1)     // Catch:{ NumberFormatException -> 0x007c }
        L_0x007c:
            com.xiaomi.push.hg r1 = com.xiaomi.push.hg.a()
            r1.a((com.xiaomi.push.fk) r0)
        L_0x0083:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.hb.c():void");
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.b.a((am.b.a) this);
        this.c = this.f12767a.a();
    }

    public void a(am.c cVar, am.c cVar2, int i) {
        if (!this.f && cVar == am.c.binding) {
            this.d = cVar2;
            this.e = i;
            this.f = true;
        }
        this.f12767a.a((XMPushService.i) new hc(this, 4));
    }
}
