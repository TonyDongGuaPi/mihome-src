package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.jacoco.agent.rt.internal_8ff85ea.asm.MethodVisitor;

public class InsnList {

    /* renamed from: a  reason: collision with root package name */
    AbstractInsnNode[] f3605a;
    private int b;
    private AbstractInsnNode c;
    private AbstractInsnNode d;

    public int a() {
        return this.b;
    }

    public AbstractInsnNode b() {
        return this.c;
    }

    public AbstractInsnNode c() {
        return this.d;
    }

    public AbstractInsnNode a(int i) {
        if (i < 0 || i >= this.b) {
            throw new IndexOutOfBoundsException();
        }
        if (this.f3605a == null) {
            this.f3605a = e();
        }
        return this.f3605a[i];
    }

    public boolean a(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode abstractInsnNode2 = this.c;
        while (abstractInsnNode2 != null && abstractInsnNode2 != abstractInsnNode) {
            abstractInsnNode2 = abstractInsnNode2.u;
        }
        return abstractInsnNode2 != null;
    }

    public int b(AbstractInsnNode abstractInsnNode) {
        if (this.f3605a == null) {
            this.f3605a = e();
        }
        return abstractInsnNode.v;
    }

    public void a(MethodVisitor methodVisitor) {
        for (AbstractInsnNode abstractInsnNode = this.c; abstractInsnNode != null; abstractInsnNode = abstractInsnNode.u) {
            abstractInsnNode.a(methodVisitor);
        }
    }

    public ListIterator<AbstractInsnNode> d() {
        return b(0);
    }

    public ListIterator<AbstractInsnNode> b(int i) {
        return new InsnListIterator(i);
    }

    public AbstractInsnNode[] e() {
        AbstractInsnNode abstractInsnNode = this.c;
        AbstractInsnNode[] abstractInsnNodeArr = new AbstractInsnNode[this.b];
        int i = 0;
        while (abstractInsnNode != null) {
            abstractInsnNodeArr[i] = abstractInsnNode;
            abstractInsnNode.v = i;
            abstractInsnNode = abstractInsnNode.u;
            i++;
        }
        return abstractInsnNodeArr;
    }

    public void a(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.u;
        abstractInsnNode2.u = abstractInsnNode3;
        if (abstractInsnNode3 != null) {
            abstractInsnNode3.t = abstractInsnNode2;
        } else {
            this.d = abstractInsnNode2;
        }
        AbstractInsnNode abstractInsnNode4 = abstractInsnNode.t;
        abstractInsnNode2.t = abstractInsnNode4;
        if (abstractInsnNode4 != null) {
            abstractInsnNode4.u = abstractInsnNode2;
        } else {
            this.c = abstractInsnNode2;
        }
        if (this.f3605a != null) {
            int i = abstractInsnNode.v;
            this.f3605a[i] = abstractInsnNode2;
            abstractInsnNode2.v = i;
        } else {
            abstractInsnNode2.v = 0;
        }
        abstractInsnNode.v = -1;
        abstractInsnNode.t = null;
        abstractInsnNode.u = null;
    }

    public void c(AbstractInsnNode abstractInsnNode) {
        this.b++;
        if (this.d == null) {
            this.c = abstractInsnNode;
            this.d = abstractInsnNode;
        } else {
            this.d.u = abstractInsnNode;
            abstractInsnNode.t = this.d;
        }
        this.d = abstractInsnNode;
        this.f3605a = null;
        abstractInsnNode.v = 0;
    }

    public void a(InsnList insnList) {
        if (insnList.b != 0) {
            this.b += insnList.b;
            if (this.d == null) {
                this.c = insnList.c;
                this.d = insnList.d;
            } else {
                AbstractInsnNode abstractInsnNode = insnList.c;
                this.d.u = abstractInsnNode;
                abstractInsnNode.t = this.d;
                this.d = insnList.d;
            }
            this.f3605a = null;
            insnList.a(false);
        }
    }

    public void d(AbstractInsnNode abstractInsnNode) {
        this.b++;
        if (this.c == null) {
            this.c = abstractInsnNode;
            this.d = abstractInsnNode;
        } else {
            this.c.t = abstractInsnNode;
            abstractInsnNode.u = this.c;
        }
        this.c = abstractInsnNode;
        this.f3605a = null;
        abstractInsnNode.v = 0;
    }

    public void b(InsnList insnList) {
        if (insnList.b != 0) {
            this.b += insnList.b;
            if (this.c == null) {
                this.c = insnList.c;
                this.d = insnList.d;
            } else {
                AbstractInsnNode abstractInsnNode = insnList.d;
                this.c.t = abstractInsnNode;
                abstractInsnNode.u = this.c;
                this.c = insnList.c;
            }
            this.f3605a = null;
            insnList.a(false);
        }
    }

    public void b(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        this.b++;
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.u;
        if (abstractInsnNode3 == null) {
            this.d = abstractInsnNode2;
        } else {
            abstractInsnNode3.t = abstractInsnNode2;
        }
        abstractInsnNode.u = abstractInsnNode2;
        abstractInsnNode2.u = abstractInsnNode3;
        abstractInsnNode2.t = abstractInsnNode;
        this.f3605a = null;
        abstractInsnNode2.v = 0;
    }

    public void a(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        if (insnList.b != 0) {
            this.b += insnList.b;
            AbstractInsnNode abstractInsnNode2 = insnList.c;
            AbstractInsnNode abstractInsnNode3 = insnList.d;
            AbstractInsnNode abstractInsnNode4 = abstractInsnNode.u;
            if (abstractInsnNode4 == null) {
                this.d = abstractInsnNode3;
            } else {
                abstractInsnNode4.t = abstractInsnNode3;
            }
            abstractInsnNode.u = abstractInsnNode2;
            abstractInsnNode3.u = abstractInsnNode4;
            abstractInsnNode2.t = abstractInsnNode;
            this.f3605a = null;
            insnList.a(false);
        }
    }

    public void c(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        this.b++;
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.t;
        if (abstractInsnNode3 == null) {
            this.c = abstractInsnNode2;
        } else {
            abstractInsnNode3.u = abstractInsnNode2;
        }
        abstractInsnNode.t = abstractInsnNode2;
        abstractInsnNode2.u = abstractInsnNode;
        abstractInsnNode2.t = abstractInsnNode3;
        this.f3605a = null;
        abstractInsnNode2.v = 0;
    }

    public void b(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        if (insnList.b != 0) {
            this.b += insnList.b;
            AbstractInsnNode abstractInsnNode2 = insnList.c;
            AbstractInsnNode abstractInsnNode3 = insnList.d;
            AbstractInsnNode abstractInsnNode4 = abstractInsnNode.t;
            if (abstractInsnNode4 == null) {
                this.c = abstractInsnNode2;
            } else {
                abstractInsnNode4.u = abstractInsnNode2;
            }
            abstractInsnNode.t = abstractInsnNode3;
            abstractInsnNode3.u = abstractInsnNode;
            abstractInsnNode2.t = abstractInsnNode4;
            this.f3605a = null;
            insnList.a(false);
        }
    }

    public void e(AbstractInsnNode abstractInsnNode) {
        this.b--;
        AbstractInsnNode abstractInsnNode2 = abstractInsnNode.u;
        AbstractInsnNode abstractInsnNode3 = abstractInsnNode.t;
        if (abstractInsnNode2 == null) {
            if (abstractInsnNode3 == null) {
                this.c = null;
                this.d = null;
            } else {
                abstractInsnNode3.u = null;
                this.d = abstractInsnNode3;
            }
        } else if (abstractInsnNode3 == null) {
            this.c = abstractInsnNode2;
            abstractInsnNode2.t = null;
        } else {
            abstractInsnNode3.u = abstractInsnNode2;
            abstractInsnNode2.t = abstractInsnNode3;
        }
        this.f3605a = null;
        abstractInsnNode.v = -1;
        abstractInsnNode.t = null;
        abstractInsnNode.u = null;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (z) {
            AbstractInsnNode abstractInsnNode = this.c;
            while (abstractInsnNode != null) {
                AbstractInsnNode abstractInsnNode2 = abstractInsnNode.u;
                abstractInsnNode.v = -1;
                abstractInsnNode.t = null;
                abstractInsnNode.u = null;
                abstractInsnNode = abstractInsnNode2;
            }
        }
        this.b = 0;
        this.c = null;
        this.d = null;
        this.f3605a = null;
    }

    public void f() {
        a(false);
    }

    public void g() {
        for (AbstractInsnNode abstractInsnNode = this.c; abstractInsnNode != null; abstractInsnNode = abstractInsnNode.u) {
            if (abstractInsnNode instanceof LabelNode) {
                ((LabelNode) abstractInsnNode).f();
            }
        }
    }

    private final class InsnListIterator implements ListIterator {

        /* renamed from: a  reason: collision with root package name */
        AbstractInsnNode f3606a;
        AbstractInsnNode b;
        AbstractInsnNode c;

        InsnListIterator(int i) {
            if (i == InsnList.this.a()) {
                this.f3606a = null;
                this.b = InsnList.this.c();
                return;
            }
            this.f3606a = InsnList.this.a(i);
            this.b = this.f3606a.t;
        }

        public boolean hasNext() {
            return this.f3606a != null;
        }

        public Object next() {
            if (this.f3606a != null) {
                AbstractInsnNode abstractInsnNode = this.f3606a;
                this.b = abstractInsnNode;
                this.f3606a = abstractInsnNode.u;
                this.c = abstractInsnNode;
                return abstractInsnNode;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.c != null) {
                if (this.c == this.f3606a) {
                    this.f3606a = this.f3606a.u;
                } else {
                    this.b = this.b.t;
                }
                InsnList.this.e(this.c);
                this.c = null;
                return;
            }
            throw new IllegalStateException();
        }

        public boolean hasPrevious() {
            return this.b != null;
        }

        public Object previous() {
            AbstractInsnNode abstractInsnNode = this.b;
            this.f3606a = abstractInsnNode;
            this.b = abstractInsnNode.t;
            this.c = abstractInsnNode;
            return abstractInsnNode;
        }

        public int nextIndex() {
            if (this.f3606a == null) {
                return InsnList.this.a();
            }
            if (InsnList.this.f3605a == null) {
                InsnList.this.f3605a = InsnList.this.e();
            }
            return this.f3606a.v;
        }

        public int previousIndex() {
            if (this.b == null) {
                return -1;
            }
            if (InsnList.this.f3605a == null) {
                InsnList.this.f3605a = InsnList.this.e();
            }
            return this.b.v;
        }

        public void add(Object obj) {
            if (this.f3606a != null) {
                InsnList.this.c(this.f3606a, (AbstractInsnNode) obj);
            } else if (this.b != null) {
                InsnList.this.b(this.b, (AbstractInsnNode) obj);
            } else {
                InsnList.this.c((AbstractInsnNode) obj);
            }
            this.b = (AbstractInsnNode) obj;
            this.c = null;
        }

        public void set(Object obj) {
            if (this.c != null) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode) obj;
                InsnList.this.a(this.c, abstractInsnNode);
                if (this.c == this.b) {
                    this.b = abstractInsnNode;
                } else {
                    this.f3606a = abstractInsnNode;
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
