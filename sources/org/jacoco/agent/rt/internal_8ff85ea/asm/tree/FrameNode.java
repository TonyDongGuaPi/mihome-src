package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.Arrays;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class FrameNode extends AbstractInsnNode {
    public int w;
    public List<Object> x;
    public List<Object> y;

    public int b() {
        return 14;
    }

    private FrameNode() {
        super(-1);
    }

    public FrameNode(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        super(-1);
        this.w = i;
        switch (i) {
            case -1:
            case 0:
                this.x = a(i2, objArr);
                this.y = a(i3, objArr2);
                return;
            case 1:
                this.x = a(i2, objArr);
                return;
            case 2:
                this.x = Arrays.asList(new Object[i2]);
                return;
            case 4:
                this.y = a(1, objArr2);
                return;
            default:
                return;
        }
    }

    public void a(MethodVisitor methodVisitor) {
        switch (this.w) {
            case -1:
            case 0:
                methodVisitor.a(this.w, this.x.size(), a(this.x), this.y.size(), a(this.y));
                return;
            case 1:
                methodVisitor.a(this.w, this.x.size(), a(this.x), 0, (Object[]) null);
                return;
            case 2:
                methodVisitor.a(this.w, this.x.size(), (Object[]) null, 0, (Object[]) null);
                return;
            case 3:
                methodVisitor.a(this.w, 0, (Object[]) null, 0, (Object[]) null);
                return;
            case 4:
                methodVisitor.a(this.w, 0, (Object[]) null, 1, a(this.y));
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.util.Map<org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode, org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode>, java.util.Map] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jacoco.agent.rt.internal_8ff85ea.asm.tree.AbstractInsnNode a(java.util.Map<org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode, org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode> r6) {
        /*
            r5 = this;
            org.jacoco.agent.rt.internal_8ff85ea.asm.tree.FrameNode r0 = new org.jacoco.agent.rt.internal_8ff85ea.asm.tree.FrameNode
            r0.<init>()
            int r1 = r5.w
            r0.w = r1
            java.util.List<java.lang.Object> r1 = r5.x
            r2 = 0
            if (r1 == 0) goto L_0x0034
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.x = r1
            r1 = 0
        L_0x0016:
            java.util.List<java.lang.Object> r3 = r5.x
            int r3 = r3.size()
            if (r1 >= r3) goto L_0x0034
            java.util.List<java.lang.Object> r3 = r5.x
            java.lang.Object r3 = r3.get(r1)
            boolean r4 = r3 instanceof org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode
            if (r4 == 0) goto L_0x002c
            java.lang.Object r3 = r6.get(r3)
        L_0x002c:
            java.util.List<java.lang.Object> r4 = r0.x
            r4.add(r3)
            int r1 = r1 + 1
            goto L_0x0016
        L_0x0034:
            java.util.List<java.lang.Object> r1 = r5.y
            if (r1 == 0) goto L_0x005d
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.y = r1
        L_0x003f:
            java.util.List<java.lang.Object> r1 = r5.y
            int r1 = r1.size()
            if (r2 >= r1) goto L_0x005d
            java.util.List<java.lang.Object> r1 = r5.y
            java.lang.Object r1 = r1.get(r2)
            boolean r3 = r1 instanceof org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode
            if (r3 == 0) goto L_0x0055
            java.lang.Object r1 = r6.get(r1)
        L_0x0055:
            java.util.List<java.lang.Object> r3 = r0.y
            r3.add(r1)
            int r2 = r2 + 1
            goto L_0x003f
        L_0x005d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_8ff85ea.asm.tree.FrameNode.a(java.util.Map):org.jacoco.agent.rt.internal_8ff85ea.asm.tree.AbstractInsnNode");
    }

    private static List<Object> a(int i, Object[] objArr) {
        return Arrays.asList(objArr).subList(0, i);
    }

    private static Object[] a(List<Object> list) {
        Object[] objArr = new Object[list.size()];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = list.get(i);
            if (obj instanceof LabelNode) {
                obj = ((LabelNode) obj).e();
            }
            objArr[i] = obj;
        }
        return objArr;
    }
}
