package org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow;

import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.jacoco.agent.rt.internal_8ff85ea.asm.commons.AnalyzerAdapter;

public final class MethodProbesAdapter extends MethodVisitor {
    private final MethodProbesVisitor c;
    private final IProbeIdGenerator d;
    private AnalyzerAdapter e;
    private final Map<Label, Label> f = new HashMap();

    private int b(int i) {
        if (i == 167) {
            return 0;
        }
        switch (i) {
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
                return 1;
            default:
                switch (i) {
                    case Opcodes.dh:
                    case 199:
                        return 1;
                    default:
                        return 2;
                }
        }
    }

    public MethodProbesAdapter(MethodProbesVisitor methodProbesVisitor, IProbeIdGenerator iProbeIdGenerator) {
        super(327680, methodProbesVisitor);
        this.c = methodProbesVisitor;
        this.d = iProbeIdGenerator;
    }

    public void a(AnalyzerAdapter analyzerAdapter) {
        this.e = analyzerAdapter;
    }

    public void a(Label label, Label label2, Label label3, String str) {
        if (this.f.containsKey(label)) {
            label = this.f.get(label);
        } else if (LabelInfo.g(label)) {
            Label label4 = new Label();
            LabelInfo.b(label4);
            this.f.put(label, label4);
            label = label4;
        }
        this.c.a(label, label2, label3, str);
    }

    public void a(Label label) {
        if (LabelInfo.g(label)) {
            if (this.f.containsKey(label)) {
                this.c.a(this.f.get(label));
            }
            this.c.b(this.d.b());
        }
        this.c.a(label);
    }

    public void e_(int i) {
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
                    this.c.e_(i);
                    return;
            }
        }
        this.c.e(i, this.d.b());
    }

    public void a(int i, Label label) {
        if (LabelInfo.c(label)) {
            this.c.a(i, label, this.d.b(), c(b(i)));
        } else {
            this.c.a(i, label);
        }
    }

    public void a(Label label, int[] iArr, Label[] labelArr) {
        if (a(label, labelArr)) {
            this.c.a(label, iArr, labelArr, c(1));
        } else {
            this.c.a(label, iArr, labelArr);
        }
    }

    public void a(int i, int i2, Label label, Label... labelArr) {
        if (a(label, labelArr)) {
            this.c.a(i, i2, label, labelArr, c(1));
            return;
        }
        this.c.a(i, i2, label, labelArr);
    }

    private boolean a(Label label, Label[] labelArr) {
        boolean z;
        LabelInfo.a(labelArr);
        if (LabelInfo.c(label)) {
            LabelInfo.a(label, this.d.b());
            z = true;
        } else {
            z = false;
        }
        LabelInfo.h(label);
        for (Label label2 : labelArr) {
            if (LabelInfo.c(label2) && !LabelInfo.j(label2)) {
                LabelInfo.a(label2, this.d.b());
                z = true;
            }
            LabelInfo.h(label2);
        }
        return z;
    }

    private IFrame c(int i) {
        return FrameSnapshot.a(this.e, i);
    }
}
