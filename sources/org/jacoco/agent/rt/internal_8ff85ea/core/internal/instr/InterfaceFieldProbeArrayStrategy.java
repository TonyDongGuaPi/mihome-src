package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IExecutionDataAccessorGenerator;

class InterfaceFieldProbeArrayStrategy implements IProbeArrayStrategy {

    /* renamed from: a  reason: collision with root package name */
    private static final Object[] f3626a = {InstrSupport.e};
    private static final Object[] b = new Object[0];
    private final String c;
    private final long d;
    private final int e;
    private final IExecutionDataAccessorGenerator f;
    private boolean g = false;

    InterfaceFieldProbeArrayStrategy(String str, long j, int i, IExecutionDataAccessorGenerator iExecutionDataAccessorGenerator) {
        this.c = str;
        this.d = j;
        this.e = i;
        this.f = iExecutionDataAccessorGenerator;
    }

    public int a(MethodVisitor methodVisitor, boolean z, int i) {
        if (z) {
            int a2 = this.f.a(this.d, this.c, this.e, methodVisitor);
            methodVisitor.e_(89);
            methodVisitor.a(179, this.c, InstrSupport.b, InstrSupport.e);
            methodVisitor.b(58, i);
            this.g = true;
            return Math.max(a2, 2);
        }
        methodVisitor.a(184, this.c, InstrSupport.f, InstrSupport.g, true);
        methodVisitor.b(58, i);
        return 1;
    }

    public void a(ClassVisitor classVisitor, int i) {
        a(classVisitor);
        b(classVisitor, i);
        if (!this.g) {
            c(classVisitor, i);
        }
    }

    private void a(ClassVisitor classVisitor) {
        classVisitor.a(4121, InstrSupport.b, InstrSupport.e, (String) null, (Object) null);
    }

    private void b(ClassVisitor classVisitor, int i) {
        MethodVisitor a2 = classVisitor.a(4106, InstrSupport.f, InstrSupport.g, (String) null, (String[]) null);
        a2.b();
        a2.a(178, this.c, InstrSupport.b, InstrSupport.e);
        a2.e_(89);
        Label label = new Label();
        a2.a(199, label);
        a2.e_(87);
        int a3 = this.f.a(this.d, this.c, i, a2);
        a2.a(-1, 0, b, 1, f3626a);
        a2.a(label);
        a2.e_(176);
        a2.d(Math.max(a3, 2), 0);
        a2.c();
    }

    private void c(ClassVisitor classVisitor, int i) {
        MethodVisitor a2 = classVisitor.a(4104, "<clinit>", "()V", (String) null, (String[]) null);
        a2.b();
        int a3 = this.f.a(this.d, this.c, i, a2);
        a2.a(179, this.c, InstrSupport.b, InstrSupport.e);
        a2.e_(177);
        a2.d(a3, 0);
        a2.c();
    }
}
