package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Handle;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

public class InvokeDynamicInsnNode extends AbstractInsnNode {
    public String w;
    public String x;
    public Handle y;
    public Object[] z;

    public int b() {
        return 6;
    }

    public InvokeDynamicInsnNode(String str, String str2, Handle handle, Object... objArr) {
        super(Opcodes.cW);
        this.w = str;
        this.x = str2;
        this.y = handle;
        this.z = objArr;
    }

    public void a(MethodVisitor methodVisitor) {
        methodVisitor.a(this.w, this.x, this.y, this.z);
        b(methodVisitor);
    }

    public AbstractInsnNode a(Map<LabelNode, LabelNode> map) {
        return new InvokeDynamicInsnNode(this.w, this.x, this.y, this.z).a((AbstractInsnNode) this);
    }
}
