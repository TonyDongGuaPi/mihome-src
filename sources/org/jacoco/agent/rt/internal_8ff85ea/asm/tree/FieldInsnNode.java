package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class FieldInsnNode extends AbstractInsnNode {
    public String w;
    public String x;
    public String y;

    public int b() {
        return 4;
    }

    public FieldInsnNode(int i, String str, String str2, String str3) {
        super(i);
        this.w = str;
        this.x = str2;
        this.y = str3;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.q, this.w, this.x, this.y);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new FieldInsnNode(this.q, this.w, this.x, this.y).a((AbstractInsnNode) this);
    }
}
