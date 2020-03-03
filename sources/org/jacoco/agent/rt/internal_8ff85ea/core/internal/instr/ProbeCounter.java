package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.ClassProbesVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.MethodProbesVisitor;

class ProbeCounter extends ClassProbesVisitor {
    private int c = 0;
    private boolean d = false;

    ProbeCounter() {
    }

    /* renamed from: b */
    public MethodProbesVisitor a(int i, String str, String str2, String str3, String[] strArr) {
        if ("<clinit>".equals(str) || (i & 1024) != 0) {
            return null;
        }
        this.d = true;
        return null;
    }

    public void a(int i) {
        this.c = i;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.d;
    }
}
