package com.xiaomi.push;

import com.xiaomi.push.jg;

public class jd {

    /* renamed from: a  reason: collision with root package name */
    private final jk f12821a;
    private final jt b;

    public jd() {
        this(new jg.a());
    }

    public jd(jm jmVar) {
        this.b = new jt();
        this.f12821a = jmVar.a(this.b);
    }

    public void a(iz izVar, byte[] bArr) {
        try {
            this.b.a(bArr);
            izVar.a(this.f12821a);
        } finally {
            this.f12821a.x();
        }
    }
}
