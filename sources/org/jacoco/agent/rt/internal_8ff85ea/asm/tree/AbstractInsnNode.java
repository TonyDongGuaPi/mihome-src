package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public abstract class AbstractInsnNode {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3604a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = 10;
    public static final int l = 11;
    public static final int m = 12;
    public static final int n = 13;
    public static final int o = 14;
    public static final int p = 15;
    protected int q;
    public List<TypeAnnotationNode> r;
    public List<TypeAnnotationNode> s;
    AbstractInsnNode t;
    AbstractInsnNode u;
    int v = -1;

    public abstract AbstractInsnNode a(Map<LabelNode, LabelNode> map);

    public abstract void a(MethodVisitor methodVisitor);

    public abstract int b();

    protected AbstractInsnNode(int i2) {
        this.q = i2;
    }

    public int a() {
        return this.q;
    }

    public AbstractInsnNode c() {
        return this.t;
    }

    public AbstractInsnNode d() {
        return this.u;
    }

    /* access modifiers changed from: protected */
    public final void b(MethodVisitor methodVisitor) {
        int i2;
        int i3;
        if (this.r == null) {
            i2 = 0;
        } else {
            i2 = this.r.size();
        }
        for (int i4 = 0; i4 < i2; i4++) {
            TypeAnnotationNode typeAnnotationNode = this.r.get(i4);
            typeAnnotationNode.a(methodVisitor.b(typeAnnotationNode.h, typeAnnotationNode.i, typeAnnotationNode.c, true));
        }
        if (this.s == null) {
            i3 = 0;
        } else {
            i3 = this.s.size();
        }
        for (int i5 = 0; i5 < i3; i5++) {
            TypeAnnotationNode typeAnnotationNode2 = this.s.get(i5);
            typeAnnotationNode2.a(methodVisitor.b(typeAnnotationNode2.h, typeAnnotationNode2.i, typeAnnotationNode2.c, false));
        }
    }

    static LabelNode a(LabelNode labelNode, Map<LabelNode, LabelNode> map) {
        return map.get(labelNode);
    }

    static LabelNode[] a(List<LabelNode> list, Map<LabelNode, LabelNode> map) {
        LabelNode[] labelNodeArr = new LabelNode[list.size()];
        for (int i2 = 0; i2 < labelNodeArr.length; i2++) {
            labelNodeArr[i2] = map.get(list.get(i2));
        }
        return labelNodeArr;
    }

    /* access modifiers changed from: protected */
    public final AbstractInsnNode a(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode.r != null) {
            this.r = new ArrayList();
            for (int i2 = 0; i2 < abstractInsnNode.r.size(); i2++) {
                TypeAnnotationNode typeAnnotationNode = abstractInsnNode.r.get(i2);
                TypeAnnotationNode typeAnnotationNode2 = new TypeAnnotationNode(typeAnnotationNode.h, typeAnnotationNode.i, typeAnnotationNode.c);
                typeAnnotationNode.a((AnnotationVisitor) typeAnnotationNode2);
                this.r.add(typeAnnotationNode2);
            }
        }
        if (abstractInsnNode.s != null) {
            this.s = new ArrayList();
            for (int i3 = 0; i3 < abstractInsnNode.s.size(); i3++) {
                TypeAnnotationNode typeAnnotationNode3 = abstractInsnNode.s.get(i3);
                TypeAnnotationNode typeAnnotationNode4 = new TypeAnnotationNode(typeAnnotationNode3.h, typeAnnotationNode3.i, typeAnnotationNode3.c);
                typeAnnotationNode3.a((AnnotationVisitor) typeAnnotationNode4);
                this.s.add(typeAnnotationNode4);
            }
        }
        return this;
    }
}
