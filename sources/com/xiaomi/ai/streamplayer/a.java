package com.xiaomi.ai.streamplayer;

abstract class a {

    /* renamed from: com.xiaomi.ai.streamplayer.a$a  reason: collision with other inner class name */
    static class C0083a {

        /* renamed from: a  reason: collision with root package name */
        int f9934a;
        int b;
        int c;
        boolean d;
        byte[] e;

        C0083a() {
        }

        /* access modifiers changed from: package-private */
        public boolean a(C0083a aVar) {
            return aVar != null && aVar.f9934a == this.f9934a && aVar.c == this.c && aVar.b == this.b;
        }
    }

    a() {
    }

    /* access modifiers changed from: package-private */
    public abstract void a();

    /* access modifiers changed from: package-private */
    public abstract void a(byte[] bArr, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void b();

    /* access modifiers changed from: package-private */
    public abstract void c();

    /* access modifiers changed from: package-private */
    public abstract void d();

    /* access modifiers changed from: package-private */
    public abstract C0083a e();
}
