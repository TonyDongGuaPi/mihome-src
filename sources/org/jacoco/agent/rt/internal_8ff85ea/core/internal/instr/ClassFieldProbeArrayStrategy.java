package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IExecutionDataAccessorGenerator;

class ClassFieldProbeArrayStrategy implements IProbeArrayStrategy {

    /* renamed from: a  reason: collision with root package name */
    private static final Object[] f3624a = {InstrSupport.e};
    private static final Object[] b = new Object[0];
    private final String c;
    private final long d;
    private final boolean e;
    private final IExecutionDataAccessorGenerator f;

    ClassFieldProbeArrayStrategy(String str, long j, boolean z, IExecutionDataAccessorGenerator iExecutionDataAccessorGenerator) {
        this.c = str;
        this.d = j;
        this.e = z;
        this.f = iExecutionDataAccessorGenerator;
    }

    public int a(MethodVisitor methodVisitor, boolean z, int i) {
        methodVisitor.a(184, this.c, InstrSupport.f, InstrSupport.g, false);
        methodVisitor.b(58, i);
        return 1;
    }

    public void a(ClassVisitor classVisitor, int i) {
        a(classVisitor);
        b(classVisitor, i);
    }

    private void a(ClassVisitor classVisitor) {
        classVisitor.a((int) InstrSupport.c, InstrSupport.b, InstrSupport.e, (String) null, (Object) null);
    }

    private void b(ClassVisitor classVisitor, int i) {
        MethodVisitor a2 = classVisitor.a(4106, InstrSupport.f, InstrSupport.g, (String) null, (String[]) null);
        a2.b();
        a2.a(178, this.c, InstrSupport.b, InstrSupport.e);
        a2.e_(89);
        Label label = new Label();
        a2.a(199, label);
        a2.e_(87);
        int a3 = a(a2, i);
        if (this.e) {
            a2.a(-1, 0, b, 1, f3624a);
        }
        a2.a(label);
        a2.e_(176);
        a2.d(Math.max(a3, 2), 0);
        a2.c();
    }

    private int a(MethodVisitor methodVisitor, int i) {
        int a2 = this.f.a(this.d, this.c, i, methodVisitor);
        methodVisitor.e_(89);
        methodVisitor.a(179, this.c, InstrSupport.b, InstrSupport.e);
        return Math.max(a2, 2);
    }
}
