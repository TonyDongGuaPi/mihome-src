package com.xiaomi.push;

public class jn {

    /* renamed from: a  reason: collision with root package name */
    private static int f12829a = Integer.MAX_VALUE;

    public static void a(jk jkVar, byte b) {
        a(jkVar, b, f12829a);
    }

    public static void a(jk jkVar, byte b, int i) {
        if (i > 0) {
            int i2 = 0;
            switch (b) {
                case 2:
                    jkVar.p();
                    return;
                case 3:
                    jkVar.q();
                    return;
                case 4:
                    jkVar.u();
                    return;
                case 6:
                    jkVar.r();
                    return;
                case 8:
                    jkVar.s();
                    return;
                case 10:
                    jkVar.t();
                    return;
                case 11:
                    jkVar.w();
                    return;
                case 12:
                    jkVar.f();
                    while (true) {
                        jh h = jkVar.h();
                        if (h.b == 0) {
                            jkVar.g();
                            return;
                        } else {
                            a(jkVar, h.b, i - 1);
                            jkVar.i();
                        }
                    }
                case 13:
                    jj j = jkVar.j();
                    while (i2 < j.c) {
                        int i3 = i - 1;
                        a(jkVar, j.f12827a, i3);
                        a(jkVar, j.b, i3);
                        i2++;
                    }
                    jkVar.k();
                    return;
                case 14:
                    jo n = jkVar.n();
                    while (i2 < n.b) {
                        a(jkVar, n.f12830a, i - 1);
                        i2++;
                    }
                    jkVar.o();
                    return;
                case 15:
                    ji l = jkVar.l();
                    while (i2 < l.b) {
                        a(jkVar, l.f12826a, i - 1);
                        i2++;
                    }
                    jkVar.m();
                    return;
                default:
                    return;
            }
        } else {
            throw new je("Maximum skip depth exceeded");
        }
    }
}
