package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.IFrame;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.LabelInfo;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.MethodProbesVisitor;

class MethodInstrumenter extends MethodProbesVisitor {
    private final IProbeInserter c;

    public MethodInstrumenter(MethodVisitor methodVisitor, IProbeInserter iProbeInserter) {
        super(methodVisitor);
        this.c = iProbeInserter;
    }

    public void b(int i) {
        this.c.a(i);
    }

    public void e(int i, int i2) {
        this.c.a(i2);
        this.Q_.e_(i);
    }

    public void a(int i, Label label, int i2, IFrame iFrame) {
        if (i == 167) {
            this.c.a(i2);
            this.Q_.a(167, label);
            return;
        }
        Label label2 = new Label();
        this.Q_.a(c(i), label2);
        this.c.a(i2);
        this.Q_.a(167, label);
        this.Q_.a(label2);
        iFrame.a(this.Q_);
    }

    private int c(int i) {
        switch (i) {
            case 153:
                return 154;
            case 154:
                return 153;
            case 155:
                return 156;
            case 156:
                return 155;
            case 157:
                return 158;
            case 158:
                return 157;
            case 159:
                return 160;
            case 160:
                return 159;
            case 161:
                return 162;
            case 162:
                return 161;
            case 163:
                return 164;
            case 164:
                return 163;
            case 165:
                return 166;
            case 166:
                return 165;
            default:
                switch (i) {
                    case Opcodes.dh:
                        return 199;
                    case 199:
                        return Opcodes.dh;
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }

    public void a(int i, int i2, Label label, Label[] labelArr, IFrame iFrame) {
        LabelInfo.i(label);
        LabelInfo.a(labelArr);
        this.Q_.a(i, i2, b(label), a(labelArr));
        a(label, labelArr, iFrame);
    }

    public void a(Label label, int[] iArr, Label[] labelArr, IFrame iFrame) {
        LabelInfo.i(label);
        LabelInfo.a(labelArr);
        this.Q_.a(b(label), iArr, a(labelArr));
        a(label, labelArr, iFrame);
    }

    private Label[] a(Label[] labelArr) {
        Label[] labelArr2 = new Label[labelArr.length];
        for (int i = 0; i < labelArr.length; i++) {
            labelArr2[i] = b(labelArr[i]);
        }
        return labelArr2;
    }

    private Label b(Label label) {
        if (LabelInfo.k(label) == -1) {
            return label;
        }
        if (LabelInfo.j(label)) {
            return LabelInfo.l(label);
        }
        Label label2 = new Label();
        LabelInfo.a(label, label2);
        LabelInfo.h(label);
        return label2;
    }

    private void a(Label label, IFrame iFrame) {
        int k = LabelInfo.k(label);
        if (k != -1 && !LabelInfo.j(label)) {
            this.Q_.a(LabelInfo.l(label));
            iFrame.a(this.Q_);
            this.c.a(k);
            this.Q_.a(167, label);
            LabelInfo.h(label);
        }
    }

    private void a(Label label, Label[] labelArr, IFrame iFrame) {
        LabelInfo.i(label);
        LabelInfo.a(labelArr);
        a(label, iFrame);
        for (Label a2 : labelArr) {
            a(a2, iFrame);
        }
    }
}
