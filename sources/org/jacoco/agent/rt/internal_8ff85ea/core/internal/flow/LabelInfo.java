package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;

public final class LabelInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3623a = -1;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private int g = -1;
    private Label h = null;
    private Instruction i = null;

    private LabelInfo() {
    }

    public static void a(Label label) {
        LabelInfo o = o(label);
        if (o.b || o.d) {
            o.c = true;
        } else {
            o.b = true;
        }
    }

    public static void b(Label label) {
        LabelInfo o = o(label);
        o.d = true;
        if (o.b) {
            o.c = true;
        }
    }

    public static boolean c(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return false;
        }
        return n.c;
    }

    public static boolean d(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return false;
        }
        return n.d;
    }

    public static void e(Label label) {
        o(label).e = true;
    }

    public static boolean f(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return false;
        }
        return n.e;
    }

    public static boolean g(Label label) {
        LabelInfo n = n(label);
        return n != null && n.d && (n.c || n.e);
    }

    public static void h(Label label) {
        o(label).f = true;
    }

    public static void i(Label label) {
        LabelInfo n = n(label);
        if (n != null) {
            n.f = false;
        }
    }

    public static void a(Label[] labelArr) {
        for (Label i2 : labelArr) {
            i(i2);
        }
    }

    public static boolean j(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return false;
        }
        return n.f;
    }

    public static void a(Label label, int i2) {
        o(label).g = i2;
    }

    public static int k(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return -1;
        }
        return n.g;
    }

    public static void a(Label label, Label label2) {
        o(label).h = label2;
    }

    public static Label l(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return null;
        }
        return n.h;
    }

    public static void a(Label label, Instruction instruction) {
        o(label).i = instruction;
    }

    public static Instruction m(Label label) {
        LabelInfo n = n(label);
        if (n == null) {
            return null;
        }
        return n.i;
    }

    private static LabelInfo n(Label label) {
        Object obj = label.m;
        if (obj instanceof LabelInfo) {
            return (LabelInfo) obj;
        }
        return null;
    }

    private static LabelInfo o(Label label) {
        LabelInfo n = n(label);
        if (n != null) {
            return n;
        }
        LabelInfo labelInfo = new LabelInfo();
        label.m = labelInfo;
        return labelInfo;
    }
}
