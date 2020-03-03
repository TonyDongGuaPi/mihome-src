package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

public class MultiANewArrayInsnNode extends AbstractInsnNode {
    public String w;
    public int x;

    public int b() {
        return 13;
    }

    public MultiANewArrayInsnNode(String str, int i) {
        super(Opcodes.dg);
        this.w = str;
        this.x = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.b(this.w, this.x);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new MultiANewArrayInsnNode(this.w, this.x).a((AbstractInsnNode) this);
    }
}
