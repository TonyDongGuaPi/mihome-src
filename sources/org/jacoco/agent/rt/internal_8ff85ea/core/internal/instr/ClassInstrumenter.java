package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.FieldVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.ClassProbesVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.MethodProbesVisitor;

public class ClassInstrumenter extends ClassProbesVisitor {
    private final IProbeArrayStrategy c;
    private String d;

    public ClassInstrumenter(IProbeArrayStrategy iProbeArrayStrategy, ClassVisitor classVisitor) {
        super(classVisitor);
        this.c = iProbeArrayStrategy;
    }

    public void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.d = str;
        super.a(i, i2, str, str2, str3, strArr);
    }

    public FieldVisitor a(int i, String str, String str2, String str3, Object obj) {
        InstrSupport.a(str, this.d);
        return super.a(i, str, str2, str3, obj);
    }

    /* renamed from: b */
    public MethodProbesVisitor a(int i, String str, String str2, String str3, String[] strArr) {
        InstrSupport.a(str, this.d);
        MethodVisitor a2 = this.b.a(i, str, str2, str3, strArr);
        if (a2 == null) {
            return null;
        }
        ProbeInserter probeInserter = new ProbeInserter(i, str, str2, new DuplicateFrameEliminator(a2), this.c);
        return new MethodInstrumenter(probeInserter, probeInserter);
    }

    public void a(int i) {
        this.c.a(this.b, i);
    }
}
