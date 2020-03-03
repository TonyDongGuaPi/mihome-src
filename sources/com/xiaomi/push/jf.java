package com.xiaomi.push;

import com.xiaomi.push.jg;
import java.io.ByteArrayOutputStream;

public class jf {

    /* renamed from: a  reason: collision with root package name */
    private final ByteArrayOutputStream f12822a;
    private final jr b;
    private jk c;

    public jf() {
        this(new jg.a());
    }

    public jf(jm jmVar) {
        this.f12822a = new ByteArrayOutputStream();
        this.b = new jr(this.f12822a);
        this.c = jmVar.a(this.b);
    }

    public byte[] a(iz izVar) {
        this.f12822a.reset();
        izVar.b(this.c);
        return this.f12822a.toByteArray();
    }
}
