package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IExecutionDataAccessorGenerator;

class LocalProbeArrayStrategy implements IProbeArrayStrategy {

    /* renamed from: a  reason: collision with root package name */
    private final String f3627a;
    private final long b;
    private final int c;
    private final IExecutionDataAccessorGenerator d;

    public void a(ClassVisitor classVisitor, int i) {
    }

    LocalProbeArrayStrategy(String str, long j, int i, IExecutionDataAccessorGenerator iExecutionDataAccessorGenerator) {
        this.f3627a = str;
        this.b = j;
        this.c = i;
        this.d = iExecutionDataAccessorGenerator;
    }

    public int a(MethodVisitor methodVisitor, boolean z, int i) {
        int a2 = this.d.a(this.b, this.f3627a, this.c, methodVisitor);
        methodVisitor.b(58, i);
        return a2;
    }
}
