package org.cybergarage.xml.parser;

import java.io.InputStream;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.Parser;
import org.cybergarage.xml.ParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlPullParser extends Parser {
    public Node parse(org.xmlpull.v1.XmlPullParser xmlPullParser, InputStream inputStream) throws ParserException {
        Node node;
        Node node2 = null;
        try {
            xmlPullParser.setInput(inputStream, (String) null);
            int eventType = xmlPullParser.getEventType();
            Node node3 = null;
            while (eventType != 1) {
                switch (eventType) {
                    case 2:
                        node = new Node();
                        String prefix = xmlPullParser.getPrefix();
                        String name = xmlPullParser.getName();
                        StringBuffer stringBuffer = new StringBuffer();
                        if (prefix != null && prefix.length() > 0) {
                            stringBuffer.append(prefix);
                            stringBuffer.append(":");
                        }
                        if (name != null && name.length() > 0) {
                            stringBuffer.append(name);
                        }
                        node.setName(stringBuffer.toString());
                        int attributeCount = xmlPullParser.getAttributeCount();
                        for (int i = 0; i < attributeCount; i++) {
                            node.setAttribute(xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                        }
                        if (node2 != null) {
                            node2.addNode(node);
                        }
                        if (node3 == null) {
                            node3 = node;
                            break;
                        }
                        break;
                    case 3:
                        node = node2.getParentNode();
                        break;
                    case 4:
                        String text = xmlPullParser.getText();
                        if (!(text == null || node2 == null)) {
                            node2.setValue(text);
                            break;
                        }
                    default:
                        continue;
                }
                node2 = node;
                eventType = xmlPullParser.next();
            }
            return node3;
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }

    public Node parse(InputStream inputStream) throws ParserException {
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            return parse(newInstance.newPullParser(), inputStream);
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
