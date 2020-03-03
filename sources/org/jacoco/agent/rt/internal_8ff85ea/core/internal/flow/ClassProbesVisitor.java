package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;

public abstract class ClassProbesVisitor extends ClassVisitor {
    public abstract void a(int i);

    /* renamed from: b */
    public abstract MethodProbesVisitor a(int i, String str, String str2, String str3, String[] strArr);

    public ClassProbesVisitor() {
        this((ClassVisitor) null);
    }

    public ClassProbesVisitor(ClassVisitor classVisitor) {
        super(327680, classVisitor);
    }
}
