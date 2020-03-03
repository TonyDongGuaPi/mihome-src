package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class LabelNode extends AbstractInsnNode {
    private Label w;

    public int b() {
        return 8;
    }

    public LabelNode() {
        super(-1);
    }

    public LabelNode(Label label) {
        super(-1);
        this.w = label;
    }

    public Label e() {
        if (this.w == null) {
            this.w = new Label();
        }
        return this.w;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(e());
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return map.get(this);
    }

    public void f() {
        this.w = null;
    }
}
