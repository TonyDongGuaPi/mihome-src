package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.nodes.Document;

public class DataNode extends LeafNode {
    public String a() {
        return "#data";
    }

    /* access modifiers changed from: package-private */
    public void b(Appendable appendable, int i, Document.OutputSettings outputSettings) {
    }

    public /* bridge */ /* synthetic */ String a(String str) {
        return super.a(str);
    }

    public /* bridge */ /* synthetic */ Node a(String str, String str2) {
        return super.a(str, str2);
    }

    public /* bridge */ /* synthetic */ Node b(String str) {
        return super.b(str);
    }

    public /* bridge */ /* synthetic */ int c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ boolean c(String str) {
        return super.c(str);
    }

    public /* bridge */ /* synthetic */ String d() {
        return super.d();
    }

    public /* bridge */ /* synthetic */ String d(String str) {
        return super.d(str);
    }

    public DataNode(String str) {
        this.c = str;
    }

    public DataNode(String str, String str2) {
        this(str);
    }

    public String b() {
        return e();
    }

    public DataNode e(String str) {
        f(str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void a(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(b());
    }

    public String toString() {
        return i();
    }

    public static DataNode b(String str, String str2) {
        return new DataNode(Entities.f(str));
    }
}
