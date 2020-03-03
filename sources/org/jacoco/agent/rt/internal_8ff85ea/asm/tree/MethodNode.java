package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.AnnotationVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Attribute;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Handle;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Type;
import org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath;

public class MethodNode extends MethodVisitor {
    public List<AnnotationNode> A_;
    public List<AnnotationNode> B_;
    public List<TypeAnnotationNode> C_;
    public List<TypeAnnotationNode> D_;
    public List<Attribute> E_;
    public Object F_;
    public List<AnnotationNode>[] G_;
    public List<AnnotationNode>[] H_;
    public InsnList I_;
    public List<TryCatchBlockNode> J_;
    public int K_;
    public int L_;
    public List<LocalVariableNode> M_;
    public List<LocalVariableAnnotationNode> N_;
    public List<LocalVariableAnnotationNode> O_;
    public int u_;
    public String v_;
    public String w_;
    private boolean x;
    public String x_;
    public List<String> y_;
    public List<ParameterNode> z_;

    public void b() {
    }

    public void c() {
    }

    public MethodNode() {
        this(327680);
        if (getClass() != MethodNode.class) {
            throw new IllegalStateException();
        }
    }

    public MethodNode(int i) {
        super(i);
        this.I_ = new InsnList();
    }

    public MethodNode(int i, String str, String str2, String str3, String[] strArr) {
        this(327680, i, str, str2, str3, strArr);
        if (getClass() != MethodNode.class) {
            throw new IllegalStateException();
        }
    }

    public MethodNode(int i, int i2, String str, String str2, String str3, String[] strArr) {
        super(i);
        this.u_ = i2;
        this.v_ = str;
        this.w_ = str2;
        this.x_ = str3;
        boolean z = false;
        this.y_ = new ArrayList(strArr == null ? 0 : strArr.length);
        if (!((i2 & 1024) != 0 ? true : z)) {
            this.M_ = new ArrayList(5);
        }
        this.J_ = new ArrayList();
        if (strArr != null) {
            this.y_.addAll(Arrays.asList(strArr));
        }
        this.I_ = new InsnList();
    }

    public void a(String str, int i) {
        if (this.z_ == null) {
            this.z_ = new ArrayList(5);
        }
        this.z_.add(new ParameterNode(str, i));
    }

    public AnnotationVisitor a() {
        return new AnnotationNode((List<Object>) new ArrayList<Object>(0) {
            public boolean add(Object obj) {
                MethodNode.this.F_ = obj;
                return super.add(obj);
            }
        });
    }

    public AnnotationVisitor a(String str, boolean z) {
        AnnotationNode annotationNode = new AnnotationNode(str);
        if (z) {
            if (this.A_ == null) {
                this.A_ = new ArrayList(1);
            }
            this.A_.add(annotationNode);
        } else {
            if (this.B_ == null) {
                this.B_ = new ArrayList(1);
            }
            this.B_.add(annotationNode);
        }
        return annotationNode;
    }

    public AnnotationVisitor a(int i, TypePath typePath, String str, boolean z) {
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(i, typePath, str);
        if (z) {
            if (this.C_ == null) {
                this.C_ = new ArrayList(1);
            }
            this.C_.add(typeAnnotationNode);
        } else {
            if (this.D_ == null) {
                this.D_ = new ArrayList(1);
            }
            this.D_.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public AnnotationVisitor a(int i, String str, boolean z) {
        AnnotationNode annotationNode = new AnnotationNode(str);
        if (z) {
            if (this.G_ == null) {
                this.G_ = (List[]) new List[Type.d(this.w_).length];
            }
            if (this.G_[i] == null) {
                this.G_[i] = new ArrayList(1);
            }
            this.G_[i].add(annotationNode);
        } else {
            if (this.H_ == null) {
                this.H_ = (List[]) new List[Type.d(this.w_).length];
            }
            if (this.H_[i] == null) {
                this.H_[i] = new ArrayList(1);
            }
            this.H_[i].add(annotationNode);
        }
        return annotationNode;
    }

    public void a(Attribute attribute) {
        if (this.E_ == null) {
            this.E_ = new ArrayList(1);
        }
        this.E_.add(attribute);
    }

    public void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        Object[] objArr3;
        Object[] objArr4;
        InsnList insnList = this.I_;
        if (objArr == null) {
            objArr3 = null;
        } else {
            objArr3 = a(objArr);
        }
        if (objArr2 == null) {
            objArr4 = null;
        } else {
            objArr4 = a(objArr2);
        }
        insnList.c(new FrameNode(i, i2, objArr3, i3, objArr4));
    }

    public void e_(int i) {
        this.I_.c(new InsnNode(i));
    }

    public void a(int i, int i2) {
        this.I_.c(new IntInsnNode(i, i2));
    }

    public void b(int i, int i2) {
        this.I_.c(new VarInsnNode(i, i2));
    }

    public void a(int i, String str) {
        this.I_.c(new TypeInsnNode(i, str));
    }

    public void a(int i, String str, String str2, String str3) {
        this.I_.c(new FieldInsnNode(i, str, str2, str3));
    }

    @Deprecated
    public void b(int i, String str, String str2, String str3) {
        if (this.P_ >= 327680) {
            super.b(i, str, str2, str3);
        } else {
            this.I_.c(new MethodInsnNode(i, str, str2, str3));
        }
    }

    public void a(int i, String str, String str2, String str3, boolean z) {
        if (this.P_ < 327680) {
            super.a(i, str, str2, str3, z);
        } else {
            this.I_.c(new MethodInsnNode(i, str, str2, str3, z));
        }
    }

    public void a(String str, String str2, Handle handle, Object... objArr) {
        this.I_.c(new InvokeDynamicInsnNode(str, str2, handle, objArr));
    }

    public void a(int i, Label label) {
        this.I_.c(new JumpInsnNode(i, b(label)));
    }

    public void a(Label label) {
        this.I_.c(b(label));
    }

    public void a(Object obj) {
        this.I_.c(new LdcInsnNode(obj));
    }

    public void c(int i, int i2) {
        this.I_.c(new IincInsnNode(i, i2));
    }

    public void a(int i, int i2, Label label, Label... labelArr) {
        this.I_.c(new TableSwitchInsnNode(i, i2, b(label), a(labelArr)));
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        this.I_.c(new LookupSwitchInsnNode(b(label), iArr, a(labelArr)));
    }

    public void b(String str, int i) {
        this.I_.c(new MultiANewArrayInsnNode(str, i));
    }

    public AnnotationVisitor b(int i, TypePath typePath, String str, boolean z) {
        AbstractInsnNode c = this.I_.c();
        while (c.a() == -1) {
            c = c.c();
        }
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(i, typePath, str);
        if (z) {
            if (c.r == null) {
                c.r = new ArrayList(1);
            }
            c.r.add(typeAnnotationNode);
        } else {
            if (c.s == null) {
                c.s = new ArrayList(1);
            }
            c.s.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public void a(Label label, Label label2, Label label3, String str) {
        this.J_.add(new TryCatchBlockNode(b(label), b(label2), b(label3), str));
    }

    public AnnotationVisitor c(int i, TypePath typePath, String str, boolean z) {
        TryCatchBlockNode tryCatchBlockNode = this.J_.get((16776960 & i) >> 8);
        TypeAnnotationNode typeAnnotationNode = new TypeAnnotationNode(i, typePath, str);
        if (z) {
            if (tryCatchBlockNode.e == null) {
                tryCatchBlockNode.e = new ArrayList(1);
            }
            tryCatchBlockNode.e.add(typeAnnotationNode);
        } else {
            if (tryCatchBlockNode.f == null) {
                tryCatchBlockNode.f = new ArrayList(1);
            }
            tryCatchBlockNode.f.add(typeAnnotationNode);
        }
        return typeAnnotationNode;
    }

    public void a(String str, String str2, String str3, Label label, Label label2, int i) {
        this.M_.add(new LocalVariableNode(str, str2, str3, b(label), b(label2), i));
    }

    public AnnotationVisitor a(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        LocalVariableAnnotationNode localVariableAnnotationNode = new LocalVariableAnnotationNode(i, typePath, a(labelArr), a(labelArr2), iArr, str);
        if (z) {
            if (this.N_ == null) {
                this.N_ = new ArrayList(1);
            }
            this.N_.add(localVariableAnnotationNode);
        } else {
            if (this.O_ == null) {
                this.O_ = new ArrayList(1);
            }
            this.O_.add(localVariableAnnotationNode);
        }
        return localVariableAnnotationNode;
    }

    public void b(int i, Label label) {
        this.I_.c(new LineNumberNode(i, b(label)));
    }

    public void d(int i, int i2) {
        this.K_ = i;
        this.L_ = i2;
    }

    /* access modifiers changed from: protected */
    public LabelNode b(Label label) {
        if (!(label.m instanceof LabelNode)) {
            label.m = new LabelNode();
        }
        return (LabelNode) label.m;
    }

    private LabelNode[] a(Label[] labelArr) {
        LabelNode[] labelNodeArr = new LabelNode[labelArr.length];
        for (int i = 0; i < labelArr.length; i++) {
            labelNodeArr[i] = b(labelArr[i]);
        }
        return labelNodeArr;
    }

    private Object[] a(Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            LabelNode labelNode = objArr[i];
            if (labelNode instanceof Label) {
                labelNode = b((Label) labelNode);
            }
            objArr2[i] = labelNode;
        }
        return objArr2;
    }

    public void b(int i) {
        if (i != 262144) {
            return;
        }
        if (this.C_ != null && this.C_.size() > 0) {
            throw new RuntimeException();
        } else if (this.D_ == null || this.D_.size() <= 0) {
            int size = this.J_ == null ? 0 : this.J_.size();
            int i2 = 0;
            while (i2 < size) {
                TryCatchBlockNode tryCatchBlockNode = this.J_.get(i2);
                if (tryCatchBlockNode.e != null && tryCatchBlockNode.e.size() > 0) {
                    throw new RuntimeException();
                } else if (tryCatchBlockNode.f == null || tryCatchBlockNode.f.size() <= 0) {
                    i2++;
                } else {
                    throw new RuntimeException();
                }
            }
            int i3 = 0;
            while (i3 < this.I_.a()) {
                AbstractInsnNode a2 = this.I_.a(i3);
                if (a2.r != null && a2.r.size() > 0) {
                    throw new RuntimeException();
                } else if (a2.s == null || a2.s.size() <= 0) {
                    if (a2 instanceof MethodInsnNode) {
                        if (((MethodInsnNode) a2).z != (a2.q == 185)) {
                            throw new RuntimeException();
                        }
                    }
                    i3++;
                } else {
                    throw new RuntimeException();
                }
            }
            if (this.N_ != null && this.N_.size() > 0) {
                throw new RuntimeException();
            } else if (this.O_ != null && this.O_.size() > 0) {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException();
        }
    }

    public void a(ClassVisitor classVisitor) {
        String[] strArr = new String[this.y_.size()];
        this.y_.toArray(strArr);
        MethodVisitor a2 = classVisitor.a(this.u_, this.v_, this.w_, this.x_, strArr);
        if (a2 != null) {
            a(a2);
        }
    }

    public void a(MethodVisitor methodVisitor) {
        int i;
        int i2;
        int i3;
        int size = this.z_ == null ? 0 : this.z_.size();
        for (int i4 = 0; i4 < size; i4++) {
            ParameterNode parameterNode = this.z_.get(i4);
            methodVisitor.a(parameterNode.f3608a, parameterNode.b);
        }
        if (this.F_ != null) {
            AnnotationVisitor a2 = methodVisitor.a();
            AnnotationNode.a(a2, (String) null, this.F_);
            if (a2 != null) {
                a2.a();
            }
        }
        int size2 = this.A_ == null ? 0 : this.A_.size();
        for (int i5 = 0; i5 < size2; i5++) {
            AnnotationNode annotationNode = this.A_.get(i5);
            annotationNode.a(methodVisitor.a(annotationNode.c, true));
        }
        int size3 = this.B_ == null ? 0 : this.B_.size();
        for (int i6 = 0; i6 < size3; i6++) {
            AnnotationNode annotationNode2 = this.B_.get(i6);
            annotationNode2.a(methodVisitor.a(annotationNode2.c, false));
        }
        int size4 = this.C_ == null ? 0 : this.C_.size();
        for (int i7 = 0; i7 < size4; i7++) {
            TypeAnnotationNode typeAnnotationNode = this.C_.get(i7);
            typeAnnotationNode.a(methodVisitor.a(typeAnnotationNode.h, typeAnnotationNode.i, typeAnnotationNode.c, true));
        }
        if (this.D_ == null) {
            i = 0;
        } else {
            i = this.D_.size();
        }
        for (int i8 = 0; i8 < i; i8++) {
            TypeAnnotationNode typeAnnotationNode2 = this.D_.get(i8);
            typeAnnotationNode2.a(methodVisitor.a(typeAnnotationNode2.h, typeAnnotationNode2.i, typeAnnotationNode2.c, false));
        }
        int length = this.G_ == null ? 0 : this.G_.length;
        for (int i9 = 0; i9 < length; i9++) {
            List<AnnotationNode> list = this.G_[i9];
            if (list != null) {
                for (int i10 = 0; i10 < list.size(); i10++) {
                    AnnotationNode annotationNode3 = list.get(i10);
                    annotationNode3.a(methodVisitor.a(i9, annotationNode3.c, true));
                }
            }
        }
        int length2 = this.H_ == null ? 0 : this.H_.length;
        for (int i11 = 0; i11 < length2; i11++) {
            List<AnnotationNode> list2 = this.H_[i11];
            if (list2 != null) {
                for (int i12 = 0; i12 < list2.size(); i12++) {
                    AnnotationNode annotationNode4 = list2.get(i12);
                    annotationNode4.a(methodVisitor.a(i11, annotationNode4.c, false));
                }
            }
        }
        if (this.x) {
            this.I_.g();
        }
        int size5 = this.E_ == null ? 0 : this.E_.size();
        for (int i13 = 0; i13 < size5; i13++) {
            methodVisitor.a(this.E_.get(i13));
        }
        if (this.I_.a() > 0) {
            methodVisitor.b();
            int size6 = this.J_ == null ? 0 : this.J_.size();
            for (int i14 = 0; i14 < size6; i14++) {
                this.J_.get(i14).a(i14);
                this.J_.get(i14).a(methodVisitor);
            }
            this.I_.a(methodVisitor);
            int size7 = this.M_ == null ? 0 : this.M_.size();
            for (int i15 = 0; i15 < size7; i15++) {
                this.M_.get(i15).a(methodVisitor);
            }
            if (this.N_ == null) {
                i2 = 0;
            } else {
                i2 = this.N_.size();
            }
            for (int i16 = 0; i16 < i2; i16++) {
                this.N_.get(i16).a(methodVisitor, true);
            }
            if (this.O_ == null) {
                i3 = 0;
            } else {
                i3 = this.O_.size();
            }
            for (int i17 = 0; i17 < i3; i17++) {
                this.O_.get(i17).a(methodVisitor, false);
            }
            methodVisitor.d(this.K_, this.L_);
            this.x = true;
        }
        methodVisitor.c();
    }
}
