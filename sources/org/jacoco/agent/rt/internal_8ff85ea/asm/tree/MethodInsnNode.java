package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class MethodInsnNode extends AbstractInsnNode {
    public String w;
    public String x;
    public String y;
    public boolean z;

    public int b() {
        return 5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public MethodInsnNode(int i, String str, String str2, String str3) {
        this(i, str, str2, str3, i == 185);
    }

    public MethodInsnNode(int i, String str, String str2, String str3, boolean z2) {
        super(i);
        this.w = str;
        this.x = str2;
        this.y = str3;
        this.z = z2;
    }

    public void a(int i) {
        this.q = i;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.q, this.w, this.x, this.y, this.z);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new MethodInsnNode(this.q, this.w, this.x, this.y, this.z);
    }
}
