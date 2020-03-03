package org.jsoup.helper;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class W3CDom {

    /* renamed from: a  reason: collision with root package name */
    protected DocumentBuilderFactory f3653a = DocumentBuilderFactory.newInstance();

    public Document a(org.jsoup.nodes.Document document) {
        Validate.a((Object) document);
        try {
            this.f3653a.setNamespaceAware(true);
            Document newDocument = this.f3653a.newDocumentBuilder().newDocument();
            a(document, newDocument);
            return newDocument;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void a(org.jsoup.nodes.Document document, Document document2) {
        if (!StringUtil.a(document.b())) {
            document2.setDocumentURI(document.b());
        }
        NodeTraversor.a((NodeVisitor) new W3CBuilder(document2), (Node) document.a(0));
    }

    protected static class W3CBuilder implements NodeVisitor {

        /* renamed from: a  reason: collision with root package name */
        private static final String f3654a = "xmlns";
        private static final String b = "xmlns:";
        private final Document c;
        private final Stack<HashMap<String, String>> d = new Stack<>();
        private Element e;

        public W3CBuilder(Document document) {
            this.c = document;
            this.d.push(new HashMap());
        }

        public void a(Node node, int i) {
            this.d.push(new HashMap(this.d.peek()));
            if (node instanceof org.jsoup.nodes.Element) {
                org.jsoup.nodes.Element element = (org.jsoup.nodes.Element) node;
                String a2 = a(element);
                Element createElementNS = this.c.createElementNS((String) this.d.peek().get(a2), element.t());
                a((Node) element, createElementNS);
                if (this.e == null) {
                    this.c.appendChild(createElementNS);
                } else {
                    this.e.appendChild(createElementNS);
                }
                this.e = createElementNS;
            } else if (node instanceof TextNode) {
                this.e.appendChild(this.c.createTextNode(((TextNode) node).f()));
            } else if (node instanceof Comment) {
                this.e.appendChild(this.c.createComment(((Comment) node).b()));
            } else if (node instanceof DataNode) {
                this.e.appendChild(this.c.createTextNode(((DataNode) node).b()));
            }
        }

        public void b(Node node, int i) {
            if ((node instanceof org.jsoup.nodes.Element) && (this.e.getParentNode() instanceof Element)) {
                this.e = (Element) this.e.getParentNode();
            }
            this.d.pop();
        }

        private void a(Node node, Element element) {
            Iterator<Attribute> it = node.s().iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                String replaceAll = next.getKey().replaceAll("[^-a-zA-Z0-9_:.]", "");
                if (replaceAll.matches("[a-zA-Z_:][-a-zA-Z0-9_:.]*")) {
                    element.setAttribute(replaceAll, next.getValue());
                }
            }
        }

        private String a(org.jsoup.nodes.Element element) {
            String str;
            Iterator<Attribute> it = element.s().iterator();
            while (it.hasNext()) {
                Attribute next = it.next();
                String a2 = next.getKey();
                if (a2.equals(f3654a)) {
                    str = "";
                } else if (a2.startsWith(b)) {
                    str = a2.substring(b.length());
                }
                this.d.peek().put(str, next.getValue());
            }
            int indexOf = element.t().indexOf(":");
            return indexOf > 0 ? element.t().substring(0, indexOf) : "";
        }
    }

    public String a(Document document) {
        try {
            DOMSource dOMSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(dOMSource, new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }
}
