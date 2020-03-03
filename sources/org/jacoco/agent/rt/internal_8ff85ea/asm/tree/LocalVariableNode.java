package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class LocalVariableNode {

    /* renamed from: a  reason: collision with root package name */
    public String f3607a;
    public String b;
    public String c;
    public LabelNode d;
    public LabelNode e;
    public int f;

    public LocalVariableNode(String str, String str2, String str3, LabelNode labelNode, LabelNode labelNode2, int i) {
        this.f3607a = str;
        this.b = str2;
        this.c = str3;
        this.d = labelNode;
        this.e = labelNode2;
        this.f = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.f3607a, this.b, this.c, this.d.e(), this.e.e(), this.f);
    }
}
