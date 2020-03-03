package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class TypeInsnNode extends AbstractInsnNode {
    public String w;

    public int b() {
        return 3;
    }

    public TypeInsnNode(int i, String str) {
        super(i);
        this.w = str;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.q, this.w);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new TypeInsnNode(this.q, this.w).a((AbstractInsnNode) this);
    }
}
