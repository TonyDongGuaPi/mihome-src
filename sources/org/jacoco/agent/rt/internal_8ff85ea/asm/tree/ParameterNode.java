package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class ParameterNode {

    /* renamed from: a  reason: collision with root package name */
    public String f3608a;
    public int b;

    public ParameterNode(String str, int i) {
        this.f3608a = str;
        this.b = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.f3608a, this.b);
    }
}
