package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.SerializationException;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public abstract class Node implements Cloneable {
    static final String d = "";
    Node e;
    int f;

    /* access modifiers changed from: package-private */
    public void B() {
    }

    public abstract String a();

    /* access modifiers changed from: package-private */
    public abstract void a(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void b(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException;

    public abstract int c();

    public abstract String d();

    public boolean equals(Object obj) {
        return this == obj;
    }

    /* access modifiers changed from: protected */
    public abstract void i(String str);

    /* access modifiers changed from: protected */
    public abstract List<Node> q();

    /* access modifiers changed from: protected */
    public abstract boolean r();

    public abstract Attributes s();

    protected Node() {
    }

    public boolean Z() {
        return this.e != null;
    }

    public String d(String str) {
        Validate.a((Object) str);
        if (!r()) {
            return "";
        }
        String d2 = s().d(str);
        if (d2.length() > 0) {
            return d2;
        }
        return str.startsWith("abs:") ? a(str.substring("abs:".length())) : "";
    }

    public Node a(String str, String str2) {
        s().b(str, str2);
        return this;
    }

    public boolean c(String str) {
        Validate.a((Object) str);
        if (str.startsWith("abs:")) {
            String substring = str.substring("abs:".length());
            if (s().h(substring) && !a(substring).equals("")) {
                return true;
            }
        }
        return s().h(str);
    }

    public Node b(String str) {
        Validate.a((Object) str);
        s().f(str);
        return this;
    }

    public Node aa() {
        Iterator<Attribute> it = s().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        return this;
    }

    public void O(final String str) {
        Validate.a((Object) str);
        a((NodeVisitor) new NodeVisitor() {
            public void b(Node node, int i) {
            }

            public void a(Node node, int i) {
                node.i(str);
            }
        });
    }

    public String a(String str) {
        Validate.a(str);
        if (!c(str)) {
            return "";
        }
        return StringUtil.a(d(), d(str));
    }

    public Node e(int i) {
        return q().get(i);
    }

    public List<Node> ab() {
        return Collections.unmodifiableList(q());
    }

    public List<Node> ac() {
        List<Node> q = q();
        ArrayList arrayList = new ArrayList(q.size());
        for (Node p : q) {
            arrayList.add(p.clone());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public Node[] ad() {
        return (Node[]) q().toArray(new Node[c()]);
    }

    public Node Y() {
        return this.e;
    }

    public final Node ae() {
        return this.e;
    }

    public Node af() {
        Node node = this;
        while (node.e != null) {
            node = node.e;
        }
        return node;
    }

    public Document ag() {
        Node af = af();
        if (af instanceof Document) {
            return (Document) af;
        }
        return null;
    }

    public void ah() {
        Validate.a((Object) this.e);
        this.e.j(this);
    }

    public Node N(String str) {
        a(this.f, str);
        return this;
    }

    public Node i(Node node) {
        Validate.a((Object) node);
        Validate.a((Object) this.e);
        this.e.b(this.f, node);
        return this;
    }

    public Node M(String str) {
        a(this.f + 1, str);
        return this;
    }

    public Node h(Node node) {
        Validate.a((Object) node);
        Validate.a((Object) this.e);
        this.e.b(this.f + 1, node);
        return this;
    }

    private void a(int i, String str) {
        Validate.a((Object) str);
        Validate.a((Object) this.e);
        List<Node> a2 = Parser.a(str, Y() instanceof Element ? (Element) Y() : null, d());
        this.e.b(i, (Node[]) a2.toArray(new Node[a2.size()]));
    }

    public Node L(String str) {
        Validate.a(str);
        List<Node> a2 = Parser.a(str, Y() instanceof Element ? (Element) Y() : null, d());
        Node node = a2.get(0);
        if (node == null || !(node instanceof Element)) {
            return null;
        }
        Element element = (Element) node;
        Element a3 = a(element);
        this.e.a(this, (Node) element);
        a3.a(this);
        if (a2.size() > 0) {
            for (int i = 0; i < a2.size(); i++) {
                Node node2 = a2.get(i);
                node2.e.j(node2);
                element.a(node2);
            }
        }
        return this;
    }

    public Node ai() {
        Validate.a((Object) this.e);
        List<Node> q = q();
        Node node = q.size() > 0 ? q.get(0) : null;
        this.e.b(this.f, ad());
        ah();
        return node;
    }

    private Element a(Element element) {
        Elements A = element.A();
        return A.size() > 0 ? a((Element) A.get(0)) : element;
    }

    public void k(Node node) {
        Validate.a((Object) node);
        Validate.a((Object) this.e);
        this.e.a(this, node);
    }

    /* access modifiers changed from: protected */
    public void l(Node node) {
        Validate.a((Object) node);
        if (this.e != null) {
            this.e.j(this);
        }
        this.e = node;
    }

    /* access modifiers changed from: protected */
    public void a(Node node, Node node2) {
        Validate.a(node.e == this);
        Validate.a((Object) node2);
        if (node2.e != null) {
            node2.e.j(node2);
        }
        int i = node.f;
        q().set(i, node2);
        node2.e = this;
        node2.f(i);
        node.e = null;
    }

    /* access modifiers changed from: protected */
    public void j(Node node) {
        Validate.a(node.e == this);
        int i = node.f;
        q().remove(i);
        a(i);
        node.e = null;
    }

    /* access modifiers changed from: protected */
    public void a(Node... nodeArr) {
        List<Node> q = q();
        for (Node node : nodeArr) {
            m(node);
            q.add(node);
            node.f(q.size() - 1);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i, Node... nodeArr) {
        Validate.a((Object[]) nodeArr);
        List<Node> q = q();
        for (Node m : nodeArr) {
            m(m);
        }
        q.addAll(i, Arrays.asList(nodeArr));
        a(i);
    }

    /* access modifiers changed from: protected */
    public void m(Node node) {
        node.l(this);
    }

    private void a(int i) {
        List<Node> q = q();
        while (i < q.size()) {
            q.get(i).f(i);
            i++;
        }
    }

    public List<Node> aj() {
        if (this.e == null) {
            return Collections.emptyList();
        }
        List<Node> q = this.e.q();
        ArrayList arrayList = new ArrayList(q.size() - 1);
        for (Node next : q) {
            if (next != this) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public Node ak() {
        if (this.e == null) {
            return null;
        }
        List<Node> q = this.e.q();
        int i = this.f + 1;
        if (q.size() > i) {
            return q.get(i);
        }
        return null;
    }

    public Node al() {
        if (this.e != null && this.f > 0) {
            return this.e.q().get(this.f - 1);
        }
        return null;
    }

    public int am() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void f(int i) {
        this.f = i;
    }

    public Node a(NodeVisitor nodeVisitor) {
        Validate.a((Object) nodeVisitor);
        NodeTraversor.a(nodeVisitor, this);
        return this;
    }

    public Node a(NodeFilter nodeFilter) {
        Validate.a((Object) nodeFilter);
        NodeTraversor.a(nodeFilter, this);
        return this;
    }

    public String i() {
        StringBuilder sb = new StringBuilder(128);
        b((Appendable) sb);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void b(Appendable appendable) {
        NodeTraversor.a((NodeVisitor) new OuterHtmlVisitor(appendable, an()), this);
    }

    /* access modifiers changed from: package-private */
    public Document.OutputSettings an() {
        Document ag = ag();
        if (ag == null) {
            ag = new Document("");
        }
        return ag.m();
    }

    public <T extends Appendable> T a(T t) {
        b((Appendable) t);
        return t;
    }

    public String toString() {
        return i();
    }

    /* access modifiers changed from: protected */
    public void c(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(10).append(StringUtil.a(i * outputSettings.h()));
    }

    public boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return i().equals(((Node) obj).i());
    }

    /* renamed from: p */
    public Node clone() {
        Node g = g((Node) null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(g);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            int c = node.c();
            for (int i = 0; i < c; i++) {
                List<Node> q = node.q();
                Node g2 = q.get(i).g(node);
                q.set(i, g2);
                linkedList.add(g2);
            }
        }
        return g;
    }

    public Node X() {
        return g((Node) null);
    }

    /* access modifiers changed from: protected */
    public Node g(Node node) {
        int i;
        try {
            Node node2 = (Node) super.clone();
            node2.e = node;
            if (node == null) {
                i = 0;
            } else {
                i = this.f;
            }
            node2.f = i;
            return node2;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static class OuterHtmlVisitor implements NodeVisitor {

        /* renamed from: a  reason: collision with root package name */
        private Appendable f3672a;
        private Document.OutputSettings b;

        OuterHtmlVisitor(Appendable appendable, Document.OutputSettings outputSettings) {
            this.f3672a = appendable;
            this.b = outputSettings;
            outputSettings.c();
        }

        public void a(Node node, int i) {
            try {
                node.a(this.f3672a, i, this.b);
            } catch (IOException e) {
                throw new SerializationException((Throwable) e);
            }
        }

        public void b(Node node, int i) {
            if (!node.a().equals("#text")) {
                try {
                    node.b(this.f3672a, i, this.b);
                } catch (IOException e) {
                    throw new SerializationException((Throwable) e);
                }
            }
        }
    }
}
