package org.jsoup.nodes;

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

public class XmlDeclaration extends LeafNode {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f3673a;

    public String a() {
        return "#declaration";
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

    public XmlDeclaration(String str, boolean z) {
        Validate.a((Object) str);
        this.c = str;
        this.f3673a = z;
    }

    public XmlDeclaration(String str, String str2, boolean z) {
        this(str, z);
    }

    public String b() {
        return e();
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        try {
            a((Appendable) sb, new Document.OutputSettings());
            return sb.toString().trim();
        } catch (IOException e) {
            throw new SerializationException((Throwable) e);
        }
    }

    private void a(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        Iterator<Attribute> it = s().iterator();
        while (it.hasNext()) {
            Attribute next = it.next();
            if (!next.getKey().equals(a())) {
                appendable.append(' ');
                next.a(appendable, outputSettings);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        appendable.append("<").append(this.f3673a ? "!" : "?").append(e());
        a(appendable, outputSettings);
        appendable.append(this.f3673a ? "!" : "?").append(">");
    }

    public String toString() {
        return i();
    }
}
