package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import org.cybergarage.http.HTTP;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class Document extends Element {
    private OutputSettings b = new OutputSettings();
    private QuirksMode c = QuirksMode.noQuirks;
    private String g;
    private boolean h = false;

    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks
    }

    public String a() {
        return "#document";
    }

    public Document(String str) {
        super(Tag.a("#root", ParseSettings.f3680a), str);
        this.g = str;
    }

    public static Document e(String str) {
        Validate.a((Object) str);
        Document document = new Document(str);
        Element n = document.n("html");
        n.n(TtmlNode.TAG_HEAD);
        n.n("body");
        return document;
    }

    public String b() {
        return this.g;
    }

    public Element e() {
        return a(TtmlNode.TAG_HEAD, (Node) this);
    }

    public Element f() {
        return a("body", (Node) this);
    }

    public String g() {
        Element first = w("title").first();
        return first != null ? StringUtil.c(first.N()).trim() : "";
    }

    public void f(String str) {
        Validate.a((Object) str);
        Element first = w("title").first();
        if (first == null) {
            e().n("title").h(str);
        } else {
            first.h(str);
        }
    }

    public Element g(String str) {
        return new Element(Tag.a(str, ParseSettings.b), d());
    }

    public Document h() {
        Element a2 = a("html", (Node) this);
        if (a2 == null) {
            a2 = n("html");
        }
        if (e() == null) {
            a2.o(TtmlNode.TAG_HEAD);
        }
        if (f() == null) {
            a2.n("body");
        }
        c(e());
        c(a2);
        c(this);
        a(TtmlNode.TAG_HEAD, a2);
        a("body", a2);
        ao();
        return this;
    }

    private void c(Element element) {
        ArrayList arrayList = new ArrayList();
        for (Node next : element.f3665a) {
            if (next instanceof TextNode) {
                TextNode textNode = (TextNode) next;
                if (!textNode.g()) {
                    arrayList.add(textNode);
                }
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Node node = (Node) arrayList.get(size);
            element.j(node);
            f().b((Node) new TextNode(" "));
            f().b(node);
        }
    }

    private void a(String str, Element element) {
        Elements w = w(str);
        Element first = w.first();
        if (w.size() > 1) {
            ArrayList<Node> arrayList = new ArrayList<>();
            for (int i = 1; i < w.size(); i++) {
                Node node = (Node) w.get(i);
                arrayList.addAll(node.q());
                node.ah();
            }
            for (Node a2 : arrayList) {
                first.a(a2);
            }
        }
        if (!first.Y().equals(element)) {
            element.a((Node) first);
        }
    }

    private Element a(String str, Node node) {
        if (node.a().equals(str)) {
            return (Element) node;
        }
        int c2 = node.c();
        for (int i = 0; i < c2; i++) {
            Element a2 = a(str, node.e(i));
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    public String i() {
        return super.V();
    }

    public Element h(String str) {
        f().h(str);
        return this;
    }

    public void a(Charset charset) {
        a(true);
        this.b.a(charset);
        ao();
    }

    public Charset j() {
        return this.b.b();
    }

    public void a(boolean z) {
        this.h = z;
    }

    public boolean k() {
        return this.h;
    }

    /* renamed from: l */
    public Document p() {
        Document document = (Document) super.p();
        document.b = this.b.clone();
        return document;
    }

    private void ao() {
        if (this.h) {
            OutputSettings.Syntax e = m().e();
            if (e == OutputSettings.Syntax.html) {
                Element first = k("meta[charset]").first();
                if (first != null) {
                    first.a(HTTP.CHARSET, j().displayName());
                } else {
                    Element e2 = e();
                    if (e2 != null) {
                        e2.n("meta").a(HTTP.CHARSET, j().displayName());
                    }
                }
                k("meta[name=charset]").remove();
            } else if (e == OutputSettings.Syntax.xml) {
                Node node = ab().get(0);
                if (node instanceof XmlDeclaration) {
                    XmlDeclaration xmlDeclaration = (XmlDeclaration) node;
                    if (xmlDeclaration.b().equals("xml")) {
                        xmlDeclaration.a("encoding", j().displayName());
                        if (xmlDeclaration.d("version") != null) {
                            xmlDeclaration.a("version", "1.0");
                            return;
                        }
                        return;
                    }
                    XmlDeclaration xmlDeclaration2 = new XmlDeclaration("xml", false);
                    xmlDeclaration2.a("version", "1.0");
                    xmlDeclaration2.a("encoding", j().displayName());
                    b((Node) xmlDeclaration2);
                    return;
                }
                XmlDeclaration xmlDeclaration3 = new XmlDeclaration("xml", false);
                xmlDeclaration3.a("version", "1.0");
                xmlDeclaration3.a("encoding", j().displayName());
                b((Node) xmlDeclaration3);
            }
        }
    }

    public static class OutputSettings implements Cloneable {

        /* renamed from: a  reason: collision with root package name */
        Entities.CoreCharset f3663a;
        private Entities.EscapeMode b = Entities.EscapeMode.base;
        private Charset c;
        private ThreadLocal<CharsetEncoder> d = new ThreadLocal<>();
        private boolean e = true;
        private boolean f = false;
        private int g = 1;
        private Syntax h = Syntax.html;

        public enum Syntax {
            html,
            xml
        }

        public OutputSettings() {
            a(Charset.forName("UTF8"));
        }

        public Entities.EscapeMode a() {
            return this.b;
        }

        public OutputSettings a(Entities.EscapeMode escapeMode) {
            this.b = escapeMode;
            return this;
        }

        public Charset b() {
            return this.c;
        }

        public OutputSettings a(Charset charset) {
            this.c = charset;
            return this;
        }

        public OutputSettings a(String str) {
            a(Charset.forName(str));
            return this;
        }

        /* access modifiers changed from: package-private */
        public CharsetEncoder c() {
            CharsetEncoder newEncoder = this.c.newEncoder();
            this.d.set(newEncoder);
            this.f3663a = Entities.CoreCharset.byName(newEncoder.charset().name());
            return newEncoder;
        }

        /* access modifiers changed from: package-private */
        public CharsetEncoder d() {
            CharsetEncoder charsetEncoder = this.d.get();
            return charsetEncoder != null ? charsetEncoder : c();
        }

        public Syntax e() {
            return this.h;
        }

        public OutputSettings a(Syntax syntax) {
            this.h = syntax;
            return this;
        }

        public boolean f() {
            return this.e;
        }

        public OutputSettings a(boolean z) {
            this.e = z;
            return this;
        }

        public boolean g() {
            return this.f;
        }

        public OutputSettings b(boolean z) {
            this.f = z;
            return this;
        }

        public int h() {
            return this.g;
        }

        public OutputSettings a(int i) {
            Validate.a(i >= 0);
            this.g = i;
            return this;
        }

        /* renamed from: i */
        public OutputSettings clone() {
            try {
                OutputSettings outputSettings = (OutputSettings) super.clone();
                outputSettings.a(this.c.name());
                outputSettings.b = Entities.EscapeMode.valueOf(this.b.name());
                return outputSettings;
            } catch (CloneNotSupportedException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public OutputSettings m() {
        return this.b;
    }

    public Document a(OutputSettings outputSettings) {
        Validate.a((Object) outputSettings);
        this.b = outputSettings;
        return this;
    }

    public QuirksMode n() {
        return this.c;
    }

    public Document a(QuirksMode quirksMode) {
        this.c = quirksMode;
        return this;
    }
}
