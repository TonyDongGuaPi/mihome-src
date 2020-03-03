package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public final class InstrSupport {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3625a = 327680;
    public static final String b = "$jacocoData";
    public static final int c = 4234;
    public static final int d = 4121;
    public static final String e = "[Z";
    public static final String f = "$jacocoInit";
    public static final String g = "()[Z";
    public static final int h = 4106;
    static final String i = "<clinit>";
    static final String j = "()V";
    static final int k = 4104;

    private InstrSupport() {
    }

    public static void a(String str, String str2) throws IllegalStateException {
        if (str.equals(b) || str.equals(f)) {
            throw new IllegalStateException(String.format("Class %s is already instrumented.", new Object[]{str2}));
        }
    }

    public static void a(MethodVisitor methodVisitor, int i2) {
        if (i2 >= -1 && i2 <= 5) {
            methodVisitor.e_(i2 + 3);
        } else if (i2 >= -128 && i2 <= 127) {
            methodVisitor.a(16, i2);
        } else if (i2 < -32768 || i2 > 32767) {
            methodVisitor.a((Object) Integer.valueOf(i2));
        } else {
            methodVisitor.a(17, i2);
        }
    }
}
