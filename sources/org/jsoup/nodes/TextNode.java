package org.jsoup.nodes;

import java.io.IOException;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

public class TextNode extends LeafNode {
    public String a() {
        return "#text";
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

    public TextNode(String str) {
        this.c = str;
    }

    public TextNode(String str, String str2) {
        this(str);
    }

    public String b() {
        return h(f());
    }

    public TextNode e(String str) {
        f(str);
        return this;
    }

    public String f() {
        return e();
    }

    public boolean g() {
        return StringUtil.a(e());
    }

    public TextNode a(int i) {
        String e = e();
        Validate.a(i >= 0, "Split offset must be not be negative");
        Validate.a(i < e.length(), "Split offset must not be greater than current text length");
        String substring = e.substring(0, i);
        String substring2 = e.substring(i);
        e(substring);
        TextNode textNode = new TextNode(substring2);
        if (Y() != null) {
            Y().b(am() + 1, textNode);
        }
        return textNode;
    }

    /* access modifiers changed from: package-private */
    public void a(Appendable appendable, int i, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.f() && ((am() == 0 && (this.e instanceof Element) && ((Element) this.e).u().c() && !g()) || (outputSettings.g() && aj().size() > 0 && !g()))) {
            c(appendable, i, outputSettings);
        }
        Entities.a(appendable, e(), outputSettings, false, outputSettings.f() && (Y() instanceof Element) && !Element.e(Y()), false);
    }

    public String toString() {
        return i();
    }

    public static TextNode b(String str, String str2) {
        return new TextNode(Entities.f(str));
    }

    public static TextNode g(String str) {
        return new TextNode(Entities.f(str));
    }

    static String h(String str) {
        return StringUtil.c(str);
    }

    static String j(String str) {
        return str.replaceFirst("^\\s+", "");
    }

    static boolean a(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ';
    }
}
