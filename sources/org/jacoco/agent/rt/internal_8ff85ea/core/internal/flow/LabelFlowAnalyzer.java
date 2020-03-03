package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Handle;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.MethodNode;

public final class LabelFlowAnalyzer extends MethodVisitor {
    boolean c = false;
    boolean d = true;
    Label e = null;

    public static void a(MethodNode methodNode) {
        LabelFlowAnalyzer labelFlowAnalyzer = new LabelFlowAnalyzer();
        int size = methodNode.J_.size();
        while (true) {
            size--;
            if (size >= 0) {
                methodNode.J_.get(size).a((MethodVisitor) labelFlowAnalyzer);
            } else {
                methodNode.I_.a((MethodVisitor) labelFlowAnalyzer);
                return;
            }
        }
    }

    public LabelFlowAnalyzer() {
        super(327680);
    }

    public void a(Label label, Label label2, Label label3, String str) {
        LabelInfo.a(label);
        LabelInfo.a(label3);
    }

    public void a(int i, Label label) {
        LabelInfo.a(label);
        if (i != 168) {
            this.c = i != 167;
            this.d = false;
            return;
        }
        throw new AssertionError("Subroutines not supported.");
    }

    public void a(Label label) {
        if (this.d) {
            LabelInfo.a(label);
        }
        if (this.c) {
            LabelInfo.b(label);
        }
    }

    public void b(int i, Label label) {
        this.e = label;
    }

    public void a(int i, int i2, Label label, Label... labelArr) {
        a(label, labelArr);
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        a(label, labelArr);
    }

    private void a(Label label, Label[] labelArr) {
        LabelInfo.i(label);
        LabelInfo.a(labelArr);
        b(label);
        for (Label b : labelArr) {
            b(b);
        }
        this.c = false;
        this.d = false;
    }

    private static void b(Label label) {
        if (!LabelInfo.j(label)) {
            LabelInfo.a(label);
            LabelInfo.h(label);
        }
    }

    public void e_(int i) {
        if (i != 169) {
            if (i != 191) {
                switch (i) {
                    case 172:
                    case 173:
                    case 174:
                    case 175:
                    case 176:
                    case 177:
                        break;
                    default:
                        this.c = true;
                        break;
                }
            }
            this.c = false;
            this.d = false;
            return;
        }
        throw new AssertionError("Subroutines not supported.");
    }

    public void a(int i, int i2) {
        this.c = true;
        this.d = false;
    }

    public void b(int i, int i2) {
        this.c = true;
        this.d = false;
    }

    public void a(int i, String str) {
        this.c = true;
        this.d = false;
    }

    public void a(int i, String str, String str2, String str3) {
        this.c = true;
        this.d = false;
    }

    public void a(int i, String str, String str2, String str3, boolean z) {
        this.c = true;
        this.d = false;
        d();
    }

    public void a(String str, String str2, Handle handle, Object... objArr) {
        this.c = true;
        this.d = false;
        d();
    }

    private void d() {
        if (this.e != null) {
            LabelInfo.e(this.e);
        }
    }

    public void a(Object obj) {
        this.c = true;
        this.d = false;
    }

    public void c(int i, int i2) {
        this.c = true;
        this.d = false;
    }

    public void b(String str, int i) {
        this.c = true;
        this.d = false;
    }
}
