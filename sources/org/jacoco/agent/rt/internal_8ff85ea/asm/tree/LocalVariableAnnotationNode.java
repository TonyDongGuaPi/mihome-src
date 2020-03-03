package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath;

public class LocalVariableAnnotationNode extends TypeAnnotationNode {
    public List<LabelNode> e;
    public List<LabelNode> f;
    public List<Integer> g;

    public LocalVariableAnnotationNode(int i, TypePath typePath, LabelNode[] labelNodeArr, LabelNode[] labelNodeArr2, int[] iArr, String str) {
        this(327680, i, typePath, labelNodeArr, labelNodeArr2, iArr, str);
    }

    public LocalVariableAnnotationNode(int i, int i2, TypePath typePath, LabelNode[] labelNodeArr, LabelNode[] labelNodeArr2, int[] iArr, String str) {
        super(i, i2, typePath, str);
        this.e = new ArrayList(labelNodeArr.length);
        this.e.addAll(Arrays.asList(labelNodeArr));
        this.f = new ArrayList(labelNodeArr2.length);
        this.f.addAll(Arrays.asList(labelNodeArr2));
        this.g = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            this.g.add(Integer.valueOf(valueOf));
        }
    }

    public void a(MethodVisitor methodVisitor, boolean z) {
        Label[] labelArr = new Label[this.e.size()];
        Label[] labelArr2 = new Label[this.f.size()];
        int[] iArr = new int[this.g.size()];
        for (int i = 0; i < labelArr.length; i++) {
            labelArr[i] = this.e.get(i).e();
            labelArr2[i] = this.f.get(i).e();
            iArr[i] = this.g.get(i).intValue();
        }
        a(methodVisitor.a(this.h, this.i, labelArr, labelArr2, iArr, this.c, true));
    }
}
