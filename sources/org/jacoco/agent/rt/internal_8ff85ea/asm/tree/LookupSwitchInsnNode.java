package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class LookupSwitchInsnNode extends AbstractInsnNode {
    public LabelNode w;
    public List<Integer> x;
    public List<LabelNode> y;

    public int b() {
        return 12;
    }

    public LookupSwitchInsnNode(LabelNode labelNode, int[] iArr, LabelNode[] labelNodeArr) {
        super(171);
        this.w = labelNode;
        this.x = new ArrayList(iArr == null ? 0 : iArr.length);
        this.y = new ArrayList(labelNodeArr == null ? 0 : labelNodeArr.length);
        if (iArr != null) {
            for (int valueOf : iArr) {
                this.x.add(Integer.valueOf(valueOf));
            }
        }
        if (labelNodeArr != null) {
            this.y.addAll(Arrays.asList(labelNodeArr));
        }
    }

    public void a(MethodVisitor methodVisitor) {
        int[] iArr = new int[this.x.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = this.x.get(i).intValue();
        }
        Label[] labelArr = new Label[this.y.size()];
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            labelArr[i2] = this.y.get(i2).e();
        }
        methodVisitor.a(this.w.e(), iArr, labelArr);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        LookupSwitchInsnNode lookupSwitchInsnNode = new LookupSwitchInsnNode(a(this.w, map), (int[]) null, a(this.y, map));
        lookupSwitchInsnNode.x.addAll(this.x);
        return lookupSwitchInsnNode.a((AbstractInsnNode) this);
    }
}
