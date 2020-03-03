package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class JumpInsnNode extends AbstractInsnNode {
    public LabelNode w;

    public int b() {
        return 7;
    }

    public JumpInsnNode(int i, LabelNode labelNode) {
        super(i);
        this.w = labelNode;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.q, this.w.e());
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new JumpInsnNode(this.q, a(this.w, map)).a((AbstractInsnNode) this);
    }
}
