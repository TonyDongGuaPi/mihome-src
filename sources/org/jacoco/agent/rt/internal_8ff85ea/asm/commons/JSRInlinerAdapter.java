package org.jacoco.agent.rt.internal_8ff85ea.asm.commons;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Label;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.AbstractInsnNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.InsnList;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.InsnNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.JumpInsnNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LabelNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LocalVariableNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.LookupSwitchInsnNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.MethodNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.TableSwitchInsnNode;
import org.jacoco.agent.rt.internal_8ff85ea.asm.tree.TryCatchBlockNode;

public class JSRInlinerAdapter extends MethodNode implements Opcodes {

    /* renamed from: dk  reason: collision with root package name */
    private static final boolean f3602dk = false;
    final BitSet dj;
    private final Map<LabelNode, BitSet> dl;
    private final BitSet dm;

    public JSRInlinerAdapter(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr) {
        this(327680, methodVisitor, i, str, str2, str3, strArr);
        if (getClass() != JSRInlinerAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected JSRInlinerAdapter(int i, MethodVisitor methodVisitor, int i2, String str, String str2, String str3, String[] strArr) {
        super(i, i2, str, str2, str3, strArr);
        this.dl = new HashMap();
        this.dm = new BitSet();
        this.dj = new BitSet();
        this.Q_ = methodVisitor;
    }

    public void a(int i, Label label) {
        super.a(i, label);
        LabelNode labelNode = ((JumpInsnNode) this.I_.c()).w;
        if (i == 168 && !this.dl.containsKey(labelNode)) {
            this.dl.put(labelNode, new BitSet());
        }
    }

    public void c() {
        if (!this.dl.isEmpty()) {
            d();
            e();
        }
        if (this.Q_ != null) {
            a(this.Q_);
        }
    }

    private void d() {
        BitSet bitSet = new BitSet();
        a(this.dm, 0, bitSet);
        for (Map.Entry next : this.dl.entrySet()) {
            a((BitSet) next.getValue(), this.I_.b((AbstractInsnNode) (LabelNode) next.getKey()), bitSet);
        }
    }

    private void a(BitSet bitSet, int i, BitSet bitSet2) {
        b(bitSet, i, bitSet2);
        boolean z = true;
        while (z) {
            z = false;
            for (TryCatchBlockNode tryCatchBlockNode : this.J_) {
                int b = this.I_.b((AbstractInsnNode) tryCatchBlockNode.c);
                if (!bitSet.get(b)) {
                    int b2 = this.I_.b((AbstractInsnNode) tryCatchBlockNode.f3609a);
                    int b3 = this.I_.b((AbstractInsnNode) tryCatchBlockNode.b);
                    int nextSetBit = bitSet.nextSetBit(b2);
                    if (nextSetBit != -1 && nextSetBit < b3) {
                        b(bitSet, b, bitSet2);
                        z = true;
                    }
                }
            }
        }
    }

    private void b(BitSet bitSet, int i, BitSet bitSet2) {
        do {
            AbstractInsnNode a2 = this.I_.a(i);
            if (!bitSet.get(i)) {
                bitSet.set(i);
                if (bitSet2.get(i)) {
                    this.dj.set(i);
                }
                bitSet2.set(i);
                if (a2.b() == 7 && a2.a() != 168) {
                    b(bitSet, this.I_.b((AbstractInsnNode) ((JumpInsnNode) a2).w), bitSet2);
                }
                if (a2.b() == 11) {
                    TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode) a2;
                    b(bitSet, this.I_.b((AbstractInsnNode) tableSwitchInsnNode.y), bitSet2);
                    for (int size = tableSwitchInsnNode.z.size() - 1; size >= 0; size--) {
                        b(bitSet, this.I_.b((AbstractInsnNode) tableSwitchInsnNode.z.get(size)), bitSet2);
                    }
                }
                if (a2.b() == 12) {
                    LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode) a2;
                    b(bitSet, this.I_.b((AbstractInsnNode) lookupSwitchInsnNode.w), bitSet2);
                    for (int size2 = lookupSwitchInsnNode.y.size() - 1; size2 >= 0; size2--) {
                        b(bitSet, this.I_.b((AbstractInsnNode) lookupSwitchInsnNode.y.get(size2)), bitSet2);
                    }
                }
                int a3 = this.I_.a(i).a();
                if (a3 != 167 && a3 != 191) {
                    switch (a3) {
                        case 169:
                        case 170:
                        case 171:
                        case 172:
                        case 173:
                        case 174:
                        case 175:
                        case 176:
                        case 177:
                            return;
                        default:
                            i++;
                            break;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (i < this.I_.a());
    }

    private void e() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Instantiation((Instantiation) null, this.dm));
        InsnList insnList = new InsnList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (!linkedList.isEmpty()) {
            a((Instantiation) linkedList.removeFirst(), linkedList, insnList, arrayList, arrayList2);
        }
        this.I_ = insnList;
        this.J_ = arrayList;
        this.M_ = arrayList2;
    }

    private void a(Instantiation instantiation, List<Instantiation> list, InsnList insnList, List<TryCatchBlockNode> list2, List<LocalVariableNode> list3) {
        int a2 = this.I_.a();
        LabelNode labelNode = null;
        for (int i = 0; i < a2; i++) {
            AbstractInsnNode a3 = this.I_.a(i);
            Instantiation a4 = instantiation.a(i);
            if (a3.b() == 8) {
                LabelNode b = instantiation.b((LabelNode) a3);
                if (b != labelNode) {
                    insnList.c(b);
                    labelNode = b;
                }
            } else if (a4 != instantiation) {
                continue;
            } else if (a3.a() == 169) {
                LabelNode labelNode2 = null;
                for (Instantiation instantiation2 = instantiation; instantiation2 != null; instantiation2 = instantiation2.f3603a) {
                    if (instantiation2.b.get(i)) {
                        labelNode2 = instantiation2.d;
                    }
                }
                if (labelNode2 != null) {
                    insnList.c(new JumpInsnNode(167, labelNode2));
                } else {
                    throw new RuntimeException("Instruction #" + i + " is a RET not owned by any subroutine");
                }
            } else if (a3.a() == 168) {
                LabelNode labelNode3 = ((JumpInsnNode) a3).w;
                Instantiation instantiation3 = new Instantiation(instantiation, this.dl.get(labelNode3));
                LabelNode a5 = instantiation3.a(labelNode3);
                insnList.c(new InsnNode(1));
                insnList.c(new JumpInsnNode(167, a5));
                insnList.c(instantiation3.d);
                list.add(instantiation3);
            } else {
                insnList.c(a3.a((Map<LabelNode, LabelNode>) instantiation));
            }
        }
        for (TryCatchBlockNode tryCatchBlockNode : this.J_) {
            LabelNode b2 = instantiation.b(tryCatchBlockNode.f3609a);
            LabelNode b3 = instantiation.b(tryCatchBlockNode.b);
            if (b2 != b3) {
                LabelNode a6 = instantiation.a(tryCatchBlockNode.c);
                if (b2 == null || b3 == null || a6 == null) {
                    throw new RuntimeException("Internal error!");
                }
                list2.add(new TryCatchBlockNode(b2, b3, a6, tryCatchBlockNode.d));
            }
        }
        for (LocalVariableNode localVariableNode : this.M_) {
            LabelNode b4 = instantiation.b(localVariableNode.d);
            LabelNode b5 = instantiation.b(localVariableNode.e);
            if (b4 != b5) {
                list3.add(new LocalVariableNode(localVariableNode.f3607a, localVariableNode.b, localVariableNode.c, b4, b5, localVariableNode.f));
            }
        }
    }

    private static void a(String str) {
        System.err.println(str);
    }

    private class Instantiation extends AbstractMap<LabelNode, LabelNode> {

        /* renamed from: a  reason: collision with root package name */
        final Instantiation f3603a;
        public final BitSet b;
        public final Map<LabelNode, LabelNode> c = new HashMap();
        public final LabelNode d;

        public Set<Map.Entry<LabelNode, LabelNode>> entrySet() {
            return null;
        }

        Instantiation(Instantiation instantiation, BitSet bitSet) {
            this.f3603a = instantiation;
            this.b = bitSet;
            Instantiation instantiation2 = instantiation;
            while (instantiation2 != null) {
                if (instantiation2.b != bitSet) {
                    instantiation2 = instantiation2.f3603a;
                } else {
                    throw new RuntimeException("Recursive invocation of " + bitSet);
                }
            }
            if (instantiation != null) {
                this.d = new LabelNode();
            } else {
                this.d = null;
            }
            int a2 = JSRInlinerAdapter.this.I_.a();
            LabelNode labelNode = null;
            for (int i = 0; i < a2; i++) {
                AbstractInsnNode a3 = JSRInlinerAdapter.this.I_.a(i);
                if (a3.b() == 8) {
                    LabelNode labelNode2 = (LabelNode) a3;
                    labelNode = labelNode == null ? new LabelNode() : labelNode;
                    this.c.put(labelNode2, labelNode);
                } else if (a(i) == this) {
                    labelNode = null;
                }
            }
        }

        public Instantiation a(int i) {
            if (!this.b.get(i)) {
                return null;
            }
            if (!JSRInlinerAdapter.this.dj.get(i)) {
                return this;
            }
            Instantiation instantiation = this;
            for (Instantiation instantiation2 = this.f3603a; instantiation2 != null; instantiation2 = instantiation2.f3603a) {
                if (instantiation2.b.get(i)) {
                    instantiation = instantiation2;
                }
            }
            return instantiation;
        }

        public LabelNode a(LabelNode labelNode) {
            return a(JSRInlinerAdapter.this.I_.b((AbstractInsnNode) labelNode)).c.get(labelNode);
        }

        public LabelNode b(LabelNode labelNode) {
            return this.c.get(labelNode);
        }

        /* renamed from: a */
        public LabelNode get(Object obj) {
            return a((LabelNode) obj);
        }
    }
}
