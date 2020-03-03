package org.mp4parser.muxer.tracks.ttml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TtmlSegmenter {
    public static List<Document> a(Document document, int i) throws XPathExpressionException {
        ArrayList arrayList;
        XPathExpression xPathExpression;
        int i2 = i * 1000;
        XPath newXPath = XPathFactory.newInstance().newXPath();
        XPathExpression compile = newXPath.compile("//*[name()='p']");
        ArrayList arrayList2 = new ArrayList();
        while (true) {
            long size = (long) (arrayList2.size() * i2);
            long size2 = (long) ((arrayList2.size() + 1) * i2);
            Document document2 = (Document) document.cloneNode(true);
            NodeList nodeList = (NodeList) compile.evaluate(document2, XPathConstants.NODESET);
            int i3 = 0;
            boolean z = false;
            while (i3 < nodeList.getLength()) {
                Node item = nodeList.item(i3);
                long a2 = TtmlHelpers.a(item);
                long b = TtmlHelpers.b(item);
                if (a2 >= size || b <= size) {
                    xPathExpression = compile;
                    arrayList = arrayList2;
                } else {
                    xPathExpression = compile;
                    arrayList = arrayList2;
                    a(item, "begin", size - a2);
                    a2 = size;
                }
                if (a2 >= size && a2 < size2 && b > size2) {
                    a(item, "end", size2 - b);
                    a2 = size;
                    b = size2;
                }
                if (a2 > size2) {
                    z = true;
                }
                if (a2 < size || b > size2) {
                    item.getParentNode().removeChild(item);
                } else {
                    long j = -size;
                    a(item, "begin", j);
                    a(item, "end", j);
                }
                i3++;
                compile = xPathExpression;
                arrayList2 = arrayList;
                Document document3 = document;
            }
            XPathExpression xPathExpression2 = compile;
            ArrayList arrayList3 = arrayList2;
            a((Node) document2);
            Element element = (Element) newXPath.compile("/*[name()='tt']/*[name()='body'][1]").evaluate(document2, XPathConstants.NODE);
            String attribute = element.getAttribute("begin");
            String attribute2 = element.getAttribute("end");
            if (attribute == null || "".equals(attribute)) {
                element.setAttribute("begin", TtmlHelpers.a(size));
            } else {
                a(element, "begin", size);
            }
            if (attribute2 == null || "".equals(attribute2)) {
                element.setAttribute("end", TtmlHelpers.a(size2));
            } else {
                a(element, "end", size2);
            }
            ArrayList arrayList4 = arrayList3;
            arrayList4.add(document2);
            if (!z) {
                return arrayList4;
            }
            arrayList2 = arrayList4;
            compile = xPathExpression2;
        }
    }

    public static void a(Node node, String str, long j) {
        int i;
        if (node.getAttributes() != null && node.getAttributes().getNamedItem(str) != null) {
            String nodeValue = node.getAttributes().getNamedItem(str).getNodeValue();
            long a2 = TtmlHelpers.a(nodeValue) + j;
            if (nodeValue.contains(".")) {
                i = -1;
            } else {
                i = ((int) (a2 - ((a2 / 1000) * 1000))) / 44;
            }
            node.getAttributes().getNamedItem(str).setNodeValue(TtmlHelpers.a(a2, i));
        }
    }

    public static Document a(Document document) throws XPathExpressionException {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        newXPath.setNamespaceContext(TtmlHelpers.c);
        NodeList nodeList = (NodeList) newXPath.compile("//*[name()='p']").evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            b(nodeList.item(i));
        }
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            Node item = nodeList.item(i2);
            a(item, "begin");
            a(item, "end");
        }
        return document;
    }

    private static void b(Node node) {
        long j = 0;
        Node node2 = node;
        while (true) {
            node2 = node2.getParentNode();
            if (node2 == null) {
                break;
            } else if (!(node2.getAttributes() == null || node2.getAttributes().getNamedItem("begin") == null)) {
                j += TtmlHelpers.a(node2.getAttributes().getNamedItem("begin").getNodeValue());
            }
        }
        if (!(node.getAttributes() == null || node.getAttributes().getNamedItem("begin") == null)) {
            node.getAttributes().getNamedItem("begin").setNodeValue(TtmlHelpers.a(TtmlHelpers.a(node.getAttributes().getNamedItem("begin").getNodeValue()) + j));
        }
        if (node.getAttributes() != null && node.getAttributes().getNamedItem("end") != null) {
            node.getAttributes().getNamedItem("end").setNodeValue(TtmlHelpers.a(j + TtmlHelpers.a(node.getAttributes().getNamedItem("end").getNodeValue())));
        }
    }

    private static void a(Node node, String str) {
        while (true) {
            node = node.getParentNode();
            if (node == null) {
                return;
            }
            if (!(node.getAttributes() == null || node.getAttributes().getNamedItem(str) == null)) {
                node.getAttributes().removeNamedItem(str);
            }
        }
    }

    public static void a(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 3) {
                item.setTextContent(item.getTextContent().trim());
            }
            a(item);
        }
    }
}
