package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class InsnNode extends AbstractInsnNode {
    public int b() {
        return 0;
    }

    public InsnNode(int i) {
        super(i);
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.e_(this.q);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new InsnNode(this.q).a((AbstractInsnNode) this);
    }
}
