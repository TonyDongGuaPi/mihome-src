package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class LdcInsnNode extends AbstractInsnNode {
    public Object w;

    public int b() {
        return 9;
    }

    public LdcInsnNode(Object obj) {
        super(18);
        this.w = obj;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.w);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new LdcInsnNode(this.w).a((AbstractInsnNode) this);
    }
}
