package org.cybergarage.xml.parser;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.Parser;
import org.cybergarage.xml.ParserException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;

public class JaxpParser extends Parser {
    public Node parse(Node node, org.w3c.dom.Node node2, int i) {
        short nodeType = node2.getNodeType();
        String nodeName = node2.getNodeName();
        String nodeValue = node2.getNodeValue();
        NamedNodeMap attributes = node2.getAttributes();
        if (attributes != null) {
            attributes.getLength();
        }
        if (nodeType == 3) {
            node.addValue(nodeValue);
            return node;
        } else if (nodeType != 1) {
            return node;
        } else {
            Node node3 = new Node();
            node3.setName(nodeName);
            node3.setValue(nodeValue);
            if (node != null) {
                node.addNode(node3);
            }
            NamedNodeMap attributes2 = node2.getAttributes();
            if (attributes2 != null) {
                int length = attributes2.getLength();
                for (int i2 = 0; i2 < length; i2++) {
                    org.w3c.dom.Node item = attributes2.item(i2);
                    node3.setAttribute(item.getNodeName(), item.getNodeValue());
                }
            }
            org.w3c.dom.Node firstChild = node2.getFirstChild();
            if (firstChild == null) {
                node3.setValue("");
                return node3;
            }
            do {
                parse(node3, firstChild, i + 1);
                firstChild = firstChild.getNextSibling();
            } while (firstChild != null);
            return node3;
        }
    }

    public Node parse(Node node, org.w3c.dom.Node node2) {
        return parse(node, node2, 0);
    }

    public Node parse(InputStream inputStream) throws ParserException {
        try {
            Element documentElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(inputStream)).getDocumentElement();
            if (documentElement != null) {
                return parse((Node) null, documentElement);
            }
            return null;
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
