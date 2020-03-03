package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Type;

class ProbeInserter extends MethodVisitor implements IProbeInserter {
    private final IProbeArrayStrategy c;
    private final boolean d;
    private final int e;
    private int f;

    ProbeInserter(int i, String str, String str2, MethodVisitor methodVisitor, IProbeArrayStrategy iProbeArrayStrategy) {
        super(327680, methodVisitor);
        this.d = "<clinit>".equals(str);
        this.c = iProbeArrayStrategy;
        int i2 = (i & 8) == 0 ? 1 : 0;
        for (Type j : Type.d(str2)) {
            i2 += j.j();
        }
        this.e = i2;
    }

    public void a(int i) {
        this.Q_.b(25, this.e);
        InstrSupport.a(this.Q_, i);
        this.Q_.e_(4);
        this.Q_.e_(84);
    }

    public void b() {
        this.f = this.c.a(this.Q_, this.d, this.e);
        this.Q_.b();
    }

    public final void b(int i, int i2) {
        this.Q_.b(i, b(i2));
    }

    public final void c(int i, int i2) {
        this.Q_.c(b(i), i2);
    }

    public final void a(String str, String str2, String str3, Label label, Label label2, int i) {
        this.Q_.a(str, str2, str3, label, label2, b(i));
    }

    public void d(int i, int i2) {
        this.Q_.d(Math.max(i + 3, this.f), i2 + 1);
    }

    private int b(int i) {
        return i < this.e ? i : i + 1;
    }

    public final void a(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        if (i == -1) {
            Object[] objArr3 = new Object[(Math.max(i2, this.e) + 1)];
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                if (i5 < i2 || i6 <= this.e) {
                    if (i6 == this.e) {
                        i4 = i7 + 1;
                        objArr3[i7] = InstrSupport.e;
                    } else if (i5 < i2) {
                        int i8 = i5 + 1;
                        Integer num = objArr[i5];
                        int i9 = i7 + 1;
                        objArr3[i7] = num;
                        i6++;
                        if (num == Opcodes.af || num == Opcodes.ae) {
                            i6++;
                        }
                        i5 = i8;
                        i7 = i9;
                    } else {
                        i4 = i7 + 1;
                        objArr3[i7] = Opcodes.ab;
                    }
                    i6++;
                    i7 = i4;
                } else {
                    this.Q_.a(i, i7, objArr3, i3, objArr2);
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
        }
    }
}
