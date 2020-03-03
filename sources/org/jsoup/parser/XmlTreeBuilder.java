package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Token;

public class XmlTreeBuilder extends TreeBuilder {
    public /* bridge */ /* synthetic */ boolean a(String str, Attributes attributes) {
        return super.a(str, attributes);
    }

    /* access modifiers changed from: package-private */
    public ParseSettings a() {
        return ParseSettings.b;
    }

    /* access modifiers changed from: package-private */
    public Document a(Reader reader, String str) {
        return b(reader, str, ParseErrorList.noTracking(), ParseSettings.b);
    }

    /* access modifiers changed from: package-private */
    public Document a(String str, String str2) {
        return b(new StringReader(str), str2, ParseErrorList.noTracking(), ParseSettings.b);
    }

    /* access modifiers changed from: protected */
    public void a(Reader reader, String str, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        super.a(reader, str, parseErrorList, parseSettings);
        this.m.add(this.l);
        this.l.m().a(Document.OutputSettings.Syntax.xml);
    }

    /* access modifiers changed from: protected */
    public boolean a(Token token) {
        switch (token.f3683a) {
            case StartTag:
                a(token.f());
                return true;
            case EndTag:
                a(token.h());
                return true;
            case Comment:
                a(token.j());
                return true;
            case Character:
                a(token.l());
                return true;
            case Doctype:
                a(token.d());
                return true;
            case EOF:
                return true;
            default:
                Validate.b("Unexpected token type: " + token.f3683a);
                return true;
        }
    }

    private void a(Node node) {
        A().a(node);
    }

    /* access modifiers changed from: package-private */
    public Element a(Token.StartTag startTag) {
        Tag a2 = Tag.a(startTag.q(), this.q);
        Element element = new Element(a2, this.n, this.q.a(startTag.e));
        a((Node) element);
        if (!startTag.s()) {
            this.m.add(element);
        } else if (!a2.i()) {
            a2.m();
        }
        return element;
    }

    /* JADX WARNING: type inference failed for: r1v11, types: [org.jsoup.nodes.Node, org.jsoup.nodes.XmlDeclaration] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.jsoup.parser.Token.Comment r5) {
        /*
            r4 = this;
            org.jsoup.nodes.Comment r0 = new org.jsoup.nodes.Comment
            java.lang.String r1 = r5.n()
            r0.<init>(r1)
            boolean r5 = r5.c
            if (r5 == 0) goto L_0x0077
            java.lang.String r5 = r0.b()
            int r1 = r5.length()
            r2 = 1
            if (r1 <= r2) goto L_0x0077
            java.lang.String r1 = "!"
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L_0x0028
            java.lang.String r1 = "?"
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L_0x0077
        L_0x0028:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "<"
            r0.append(r1)
            int r1 = r5.length()
            int r1 = r1 - r2
            java.lang.String r1 = r5.substring(r2, r1)
            r0.append(r1)
            java.lang.String r1 = ">"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = r4.n
            org.jsoup.parser.Parser r2 = org.jsoup.parser.Parser.f()
            org.jsoup.nodes.Document r0 = org.jsoup.Jsoup.a((java.lang.String) r0, (java.lang.String) r1, (org.jsoup.parser.Parser) r2)
            r1 = 0
            org.jsoup.nodes.Element r0 = r0.a((int) r1)
            org.jsoup.nodes.XmlDeclaration r1 = new org.jsoup.nodes.XmlDeclaration
            org.jsoup.parser.ParseSettings r2 = r4.q
            java.lang.String r3 = r0.t()
            java.lang.String r2 = r2.a((java.lang.String) r3)
            java.lang.String r3 = "!"
            boolean r5 = r5.startsWith(r3)
            r1.<init>(r2, r5)
            org.jsoup.nodes.Attributes r5 = r1.s()
            org.jsoup.nodes.Attributes r0 = r0.s()
            r5.a((org.jsoup.nodes.Attributes) r0)
            r0 = r1
        L_0x0077:
            r4.a((org.jsoup.nodes.Node) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.XmlTreeBuilder.a(org.jsoup.parser.Token$Comment):void");
    }

    /* access modifiers changed from: package-private */
    public void a(Token.Character character) {
        a((Node) new TextNode(character.n()));
    }

    /* access modifiers changed from: package-private */
    public void a(Token.Doctype doctype) {
        DocumentType documentType = new DocumentType(this.q.a(doctype.n()), doctype.p(), doctype.q());
        documentType.e(doctype.o());
        a((Node) documentType);
    }

    private void a(Token.EndTag endTag) {
        Element element;
        String q = endTag.q();
        int size = this.m.size() - 1;
        while (true) {
            if (size < 0) {
                element = null;
                break;
            }
            element = (Element) this.m.get(size);
            if (element.a().equals(q)) {
                break;
            }
            size--;
        }
        if (element != null) {
            int size2 = this.m.size() - 1;
            while (size2 >= 0) {
                Element element2 = (Element) this.m.get(size2);
                this.m.remove(size2);
                if (element2 != element) {
                    size2--;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<Node> a(String str, String str2, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        a((Reader) new StringReader(str), str2, parseErrorList, parseSettings);
        z();
        return this.l.ab();
    }
}
