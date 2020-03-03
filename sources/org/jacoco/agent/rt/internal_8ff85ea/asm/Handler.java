package org.jacoco.agent.rt.internal_8ff85ea.asm;

class Handler {

    /* renamed from: a  reason: collision with root package name */
    Label f3594a;
    Label b;
    Label c;
    String d;
    int e;
    Handler f;

    Handler() {
    }

    static Handler a(Handler handler, Label label, Label label2) {
        int i;
        if (handler == null) {
            return null;
        }
        handler.f = a(handler.f, label, label2);
        int i2 = handler.f3594a.p;
        int i3 = handler.b.p;
        int i4 = label.p;
        if (label2 == null) {
            i = Integer.MAX_VALUE;
        } else {
            i = label2.p;
        }
        if (i4 >= i3 || i <= i2) {
            return handler;
        }
        if (i4 <= i2) {
            if (i >= i3) {
                return handler.f;
            }
            handler.f3594a = label2;
            return handler;
        } else if (i >= i3) {
            handler.b = label;
            return handler;
        } else {
            Handler handler2 = new Handler();
            handler2.f3594a = label2;
            handler2.b = handler.b;
            handler2.c = handler.c;
            handler2.d = handler.d;
            handler2.e = handler.e;
            handler2.f = handler.f;
            handler.b = label;
            handler.f = handler2;
            return handler;
        }
    }
}
