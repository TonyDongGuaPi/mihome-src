package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Handle;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

class DuplicateFrameEliminator extends MethodVisitor {
    private boolean c = true;

    public DuplicateFrameEliminator(MethodVisitor methodVisitor) {
        super(327680, methodVisitor);
    }

    public void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        if (this.c) {
            this.c = false;
            this.Q_.a(i, i2, objArr, i3, objArr2);
        }
    }

    public void e_(int i) {
        this.c = true;
        this.Q_.e_(i);
    }

    public void a(int i, int i2) {
        this.c = true;
        this.Q_.a(i, i2);
    }

    public void b(int i, int i2) {
        this.c = true;
        this.Q_.b(i, i2);
    }

    public void a(int i, String str) {
        this.c = true;
        this.Q_.a(i, str);
    }

    public void a(int i, String str, String str2, String str3) {
        this.c = true;
        this.Q_.a(i, str, str2, str3);
    }

    public void a(int i, String str, String str2, String str3, boolean z) {
        this.c = true;
        this.Q_.a(i, str, str2, str3, z);
    }

    public void a(String str, String str2, Handle handle, Object... objArr) {
        this.c = true;
        this.Q_.a(str, str2, handle, objArr);
    }

    public void a(int i, Label label) {
        this.c = true;
        this.Q_.a(i, label);
    }

    public void a(Object obj) {
        this.c = true;
        this.Q_.a(obj);
    }

    public void c(int i, int i2) {
        this.c = true;
        this.Q_.c(i, i2);
    }

    public void a(int i, int i2, Label label, Label... labelArr) {
        this.c = true;
        this.Q_.a(i, i2, label, labelArr);
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        this.c = true;
        this.Q_.a(label, iArr, labelArr);
    }

    public void b(String str, int i) {
        this.c = true;
        this.Q_.b(str, i);
    }
}
