package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class TableSwitchInsnNode extends AbstractInsnNode {
    public int w;
    public int x;
    public LabelNode y;
    public List<LabelNode> z = new ArrayList();

    public int b() {
        return 11;
    }

    public TableSwitchInsnNode(int i, int i2, LabelNode labelNode, LabelNode... labelNodeArr) {
        super(170);
        this.w = i;
        this.x = i2;
        this.y = labelNode;
        if (labelNodeArr != null) {
            this.z.addAll(Arrays.asList(labelNodeArr));
        }
    }

    public void a(MethodVisitor methodVisitor) {
        Label[] labelArr = new Label[this.z.size()];
        for (int i = 0; i < labelArr.length; i++) {
            labelArr[i] = this.z.get(i).e();
        }
        methodVisitor.a(this.w, this.x, this.y.e(), labelArr);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new TableSwitchInsnNode(this.w, this.x, a(this.y, map), a(this.z, map)).a((AbstractInsnNode) this);
    }
}
