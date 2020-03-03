package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class LineNumberNode extends AbstractInsnNode {
    public int w;
    public LabelNode x;

    public int b() {
        return 15;
    }

    public LineNumberNode(int i, LabelNode labelNode) {
        super(-1);
        this.w = i;
        this.x = labelNode;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.b(this.w, this.x.e());
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new LineNumberNode(this.w, a(this.x, map));
    }
}
