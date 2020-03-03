package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.commons.JSRInlinerAdapter;

class MethodSanitizer extends JSRInlinerAdapter {
    MethodSanitizer(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr) {
        super(327680, methodVisitor, i, str, str2, str3, strArr);
    }

    public void a(String str, String str2, String str3, Label label, Label label2, int i) {
        if (label.m != null && label2.m != null) {
            super.a(str, str2, str3, label, label2, i);
        }
    }

    public void b(int i, Label label) {
        if (label.m != null) {
            super.b(i, label);
        }
    }
}
