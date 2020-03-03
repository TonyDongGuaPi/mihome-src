package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class VarInsnNode extends AbstractInsnNode {
    public int w;

    public int b() {
        return 2;
    }

    public VarInsnNode(int i, int i2) {
        super(i);
        this.w = i2;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.b(this.q, this.w);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new VarInsnNode(this.q, this.w).a((AbstractInsnNode) this);
    }
}
