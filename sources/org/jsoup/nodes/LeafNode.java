package org.jsoup.nodes;

import java.util.List;
import org.jsoup.helper.Validate;

abstract class LeafNode extends Node {
    Object c;

    public int c() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void i(String str) {
    }

    LeafNode() {
    }

    /* access modifiers changed from: protected */
    public final boolean r() {
        return this.c instanceof Attributes;
    }

    public final Attributes s() {
        b();
        return (Attributes) this.c;
    }

    private void b() {
        if (!r()) {
            Object obj = this.c;
            Attributes attributes = new Attributes();
            this.c = attributes;
            if (obj != null) {
                attributes.a(a(), (String) obj);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return d(a());
    }

    /* access modifiers changed from: package-private */
    public void f(String str) {
        a(a(), str);
    }

    public String d(String str) {
        Validate.a((Object) str);
        if (!r()) {
            return str.equals(a()) ? (String) this.c : "";
        }
        return super.d(str);
    }

    public Node a(String str, String str2) {
        if (r() || !str.equals(a())) {
            b();
            super.a(str, str2);
        } else {
            this.c = str2;
        }
        return this;
    }

    public boolean c(String str) {
        b();
        return super.c(str);
    }

    public Node b(String str) {
        b();
        return super.b(str);
    }

    public String a(String str) {
        b();
        return super.a(str);
    }

    public String d() {
        return Z() ? Y().d() : "";
    }

    /* access modifiers changed from: protected */
    public List<Node> q() {
        throw new UnsupportedOperationException("Leaf Nodes do not have child nodes.");
    }
}
