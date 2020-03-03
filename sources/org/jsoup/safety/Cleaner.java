package org.jsoup.safety;

import java.util.Collection;
import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class Cleaner {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Whitelist f3688a;

    public Cleaner(Whitelist whitelist) {
        Validate.a((Object) whitelist);
        this.f3688a = whitelist;
    }

    public Document a(Document document) {
        Validate.a((Object) document);
        Document e = Document.e(document.d());
        if (document.f() != null) {
            a(document.f(), e.f());
        }
        return e;
    }

    public boolean b(Document document) {
        Validate.a((Object) document);
        return a(document.f(), Document.e(document.d()).f()) == 0 && document.e().ab().size() == 0;
    }

    public boolean a(String str) {
        Document e = Document.e("");
        Document e2 = Document.e("");
        ParseErrorList tracking = ParseErrorList.tracking(1);
        e2.f().a(0, (Collection<? extends Node>) Parser.a(str, e2.f(), "", tracking));
        if (a(e2.f(), e.f()) == 0 && tracking.size() == 0) {
            return true;
        }
        return false;
    }

    private final class CleaningVisitor implements NodeVisitor {
        /* access modifiers changed from: private */
        public int b;
        private final Element c;
        private Element d;

        private CleaningVisitor(Element element, Element element2) {
            this.b = 0;
            this.c = element;
            this.d = element2;
        }

        public void a(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (Cleaner.this.f3688a.a(element.t())) {
                    ElementMeta a2 = Cleaner.this.a(element);
                    Element element2 = a2.f3690a;
                    this.d.a((Node) element2);
                    this.b += a2.b;
                    this.d = element2;
                } else if (node != this.c) {
                    this.b++;
                }
            } else if (node instanceof TextNode) {
                this.d.a((Node) new TextNode(((TextNode) node).f()));
            } else if (!(node instanceof DataNode) || !Cleaner.this.f3688a.a(node.Y().a())) {
                this.b++;
            } else {
                this.d.a((Node) new DataNode(((DataNode) node).b()));
            }
        }

        public void b(Node node, int i) {
            if ((node instanceof Element) && Cleaner.this.f3688a.a(node.a())) {
                this.d = this.d.Y();
            }
        }
    }

    private int a(Element element, Element element2) {
        CleaningVisitor cleaningVisitor = new CleaningVisitor(element, element2);
        NodeTraversor.a((NodeVisitor) cleaningVisitor, (Node) element);
        return cleaningVisitor.b;
    }

    /* access modifiers changed from: private */
    public ElementMeta a(Element element) {
        String t = element.t();
        Attributes attributes = new Attributes();
        Element element2 = new Element(Tag.valueOf(t), element.d(), attributes);
        Iterator<Attribute> it = element.s().iterator();
        int i = 0;
        while (it.hasNext()) {
            Attribute next = it.next();
            if (this.f3688a.a(t, element, next)) {
                attributes.a(next);
            } else {
                i++;
            }
        }
        attributes.a(this.f3688a.b(t));
        return new ElementMeta(element2, i);
    }

    private static class ElementMeta {

        /* renamed from: a  reason: collision with root package name */
        Element f3690a;
        int b;

        ElementMeta(Element element, int i) {
            this.f3690a = element;
            this.b = i;
        }
    }
}
