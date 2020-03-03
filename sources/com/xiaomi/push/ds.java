package com.xiaomi.push;

public class ds {
    private static volatile ds b;

    /* renamed from: a  reason: collision with root package name */
    private dr f12700a;

    public static ds a() {
        if (b == null) {
            synchronized (ds.class) {
                if (b == null) {
                    b = new ds();
                }
            }
        }
        return b;
    }

    public void a(dr drVar) {
        this.f12700a = drVar;
    }

    public dr b() {
        return this.f12700a;
    }
}
