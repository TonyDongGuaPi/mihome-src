package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class IincInsnNode extends AbstractInsnNode {
    public int w;
    public int x;

    public int b() {
        return 10;
    }

    public IincInsnNode(int i, int i2) {
        super(132);
        this.w = i;
        this.x = i2;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.c(this.w, this.x);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new IincInsnNode(this.w, this.x).a((AbstractInsnNode) this);
    }
}
