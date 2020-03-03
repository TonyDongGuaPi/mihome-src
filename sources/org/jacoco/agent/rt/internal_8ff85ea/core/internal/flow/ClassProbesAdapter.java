package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.commons.AnalyzerAdapter;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.MethodNode;

public class ClassProbesAdapter extends ClassVisitor implements IProbeIdGenerator {
    private static final MethodProbesVisitor c = new MethodProbesVisitor() {
    };
    private final ClassProbesVisitor d;
    /* access modifiers changed from: private */
    public final boolean e;
    private int f = 0;
    /* access modifiers changed from: private */
    public String g;

    public ClassProbesAdapter(ClassProbesVisitor classProbesVisitor, boolean z) {
        super(327680, classProbesVisitor);
        this.d = classProbesVisitor;
        this.e = z;
    }

    public void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.g = str;
        super.a(i, i2, str, str2, str3, strArr);
    }

    public final MethodVisitor a(int i, String str, String str2, String str3, String[] strArr) {
        MethodProbesVisitor b = this.d.a(i, str, str2, str3, strArr);
        if (b == null) {
            b = c;
        }
        final MethodProbesVisitor methodProbesVisitor = b;
        return new MethodSanitizer((MethodVisitor) null, i, str, str2, str3, strArr) {
            public void c() {
                super.c();
                LabelFlowAnalyzer.a((MethodNode) this);
                MethodProbesAdapter methodProbesAdapter = new MethodProbesAdapter(methodProbesVisitor, ClassProbesAdapter.this);
                if (ClassProbesAdapter.this.e) {
                    AnalyzerAdapter analyzerAdapter = new AnalyzerAdapter(ClassProbesAdapter.this.g, this.u_, this.v_, this.w_, methodProbesAdapter);
                    methodProbesAdapter.a(analyzerAdapter);
                    a((MethodVisitor) analyzerAdapter);
                    return;
                }
                a((MethodVisitor) methodProbesAdapter);
            }
        };
    }

    public void a() {
        this.d.a(this.f);
        super.a();
    }

    public int b() {
        int i = this.f;
        this.f = i + 1;
        return i;
    }
}
