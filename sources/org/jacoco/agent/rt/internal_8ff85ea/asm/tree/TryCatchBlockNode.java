package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class TryCatchBlockNode {

    /* renamed from: a  reason: collision with root package name */
    public LabelNode f3609a;
    public LabelNode b;
    public LabelNode c;
    public String d;
    public List<TypeAnnotationNode> e;
    public List<TypeAnnotationNode> f;

    public TryCatchBlockNode(LabelNode labelNode, LabelNode labelNode2, LabelNode labelNode3, String str) {
        this.f3609a = labelNode;
        this.b = labelNode2;
        this.c = labelNode3;
        this.d = str;
    }

    public void a(int i) {
        int i2 = (i << 8) | 1107296256;
        if (this.e != null) {
            for (TypeAnnotationNode typeAnnotationNode : this.e) {
                typeAnnotationNode.h = i2;
            }
        }
        if (this.f != null) {
            for (TypeAnnotationNode typeAnnotationNode2 : this.f) {
                typeAnnotationNode2.h = i2;
            }
        }
    }

    public void a(MethodVisitor methodVisitor) {
        Label label;
        int i;
        int i2;
        Label e2 = this.f3609a.e();
        Label e3 = this.b.e();
        if (this.c == null) {
            label = null;
        } else {
            label = this.c.e();
        }
        methodVisitor.a(e2, e3, label, this.d);
        if (this.e == null) {
            i = 0;
        } else {
            i = this.e.size();
        }
        for (int i3 = 0; i3 < i; i3++) {
            TypeAnnotationNode typeAnnotationNode = this.e.get(i3);
            typeAnnotationNode.a(methodVisitor.c(typeAnnotationNode.h, typeAnnotationNode.i, typeAnnotationNode.c, true));
        }
        if (this.f == null) {
            i2 = 0;
        } else {
            i2 = this.f.size();
        }
        for (int i4 = 0; i4 < i2; i4++) {
            TypeAnnotationNode typeAnnotationNode2 = this.f.get(i4);
            typeAnnotationNode2.a(methodVisitor.c(typeAnnotationNode2.h, typeAnnotationNode2.i, typeAnnotationNode2.c, false));
        }
    }
}
