package org.cybergarage.xml;

import com.alipay.sdk.sys.a;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

public class Node {
    private AttributeList attrList;
    private String name;
    private NodeList nodeList;
    private Node parentNode;
    private Object userData;
    private String value;

    public Node() {
        this.parentNode = null;
        this.name = new String();
        this.value = new String();
        this.attrList = new AttributeList();
        this.nodeList = new NodeList();
        this.userData = null;
        setUserData((Object) null);
        setParentNode((Node) null);
    }

    public Node(String str) {
        this();
        setName(str);
    }

    public Node(String str, String str2) {
        this();
        setName(str, str2);
    }

    public void setParentNode(Node node) {
        this.parentNode = node;
    }

    public Node getParentNode() {
        return this.parentNode;
    }

    public Node getRootNode() {
        Node parentNode2 = getParentNode();
        Node node = null;
        while (true) {
            Node node2 = node;
            node = parentNode2;
            Node node3 = node2;
            if (node == null) {
                return node3;
            }
            parentNode2 = node.getParentNode();
        }
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setName(String str, String str2) {
        this.name = str + ":" + str2;
    }

    public String getName() {
        return this.name;
    }

    public boolean isName(String str) {
        return this.name.equals(str);
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setValue(int i) {
        setValue(Integer.toString(i));
    }

    public void addValue(String str) {
        if (this.value == null) {
            this.value = str;
        } else if (str != null) {
            this.value += str;
        }
    }

    public String getValue() {
        return this.value;
    }

    public int getNAttributes() {
        return this.attrList.size();
    }

    public Attribute getAttribute(int i) {
        return this.attrList.getAttribute(i);
    }

    public Attribute getAttribute(String str) {
        return this.attrList.getAttribute(str);
    }

    public void addAttribute(Attribute attribute) {
        this.attrList.add(attribute);
    }

    public void insertAttributeAt(Attribute attribute, int i) {
        this.attrList.insertElementAt(attribute, i);
    }

    public void addAttribute(String str, String str2) {
        addAttribute(new Attribute(str, str2));
    }

    public boolean removeAttribute(Attribute attribute) {
        return this.attrList.remove(attribute);
    }

    public boolean removeAttribute(String str) {
        return removeAttribute(getAttribute(str));
    }

    public boolean hasAttributes() {
        return getNAttributes() > 0;
    }

    public void setAttribute(String str, String str2) {
        Attribute attribute = getAttribute(str);
        if (attribute != null) {
            attribute.setValue(str2);
        } else {
            addAttribute(new Attribute(str, str2));
        }
    }

    public void setAttribute(String str, int i) {
        setAttribute(str, Integer.toString(i));
    }

    public String getAttributeValue(String str) {
        Attribute attribute = getAttribute(str);
        return attribute != null ? attribute.getValue() : "";
    }

    public int getAttributeIntegerValue(String str) {
        try {
            return Integer.parseInt(getAttributeValue(str));
        } catch (Exception unused) {
            return 0;
        }
    }

    public void setNameSpace(String str, String str2) {
        setAttribute("xmlns:" + str, str2);
    }

    public int getNNodes() {
        return this.nodeList.size();
    }

    public Node getNode(int i) {
        return this.nodeList.getNode(i);
    }

    public Node getNode(String str) {
        return this.nodeList.getNode(str);
    }

    public Node getNodeEndsWith(String str) {
        return this.nodeList.getEndsWith(str);
    }

    public void addNode(Node node) {
        node.setParentNode(this);
        this.nodeList.add(node);
    }

    public void insertNode(Node node, int i) {
        node.setParentNode(this);
        this.nodeList.insertElementAt(node, i);
    }

    public int getIndex(String str) {
        Iterator it = this.nodeList.iterator();
        int i = -1;
        while (it.hasNext()) {
            i++;
            if (((Node) it.next()).getName().equals(str)) {
                return i;
            }
        }
        return i;
    }

    public boolean removeNode(Node node) {
        node.setParentNode((Node) null);
        return this.nodeList.remove(node);
    }

    public boolean removeNode(String str) {
        return this.nodeList.remove(getNode(str));
    }

    public void removeAllNodes() {
        this.nodeList.clear();
    }

    public boolean hasNodes() {
        return getNNodes() > 0;
    }

    public void setNode(String str, String str2) {
        Node node = getNode(str);
        if (node != null) {
            node.setValue(str2);
            return;
        }
        Node node2 = new Node(str);
        node2.setValue(str2);
        addNode(node2);
    }

    public String getNodeValue(String str) {
        Node node = getNode(str);
        return node != null ? node.getValue() : "";
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }

    public String getIndentLevelString(int i) {
        return getIndentLevelString(i, "   ");
    }

    public String getIndentLevelString(int i, String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length() * i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public void outputAttributes(PrintWriter printWriter) {
        int nAttributes = getNAttributes();
        for (int i = 0; i < nAttributes; i++) {
            Attribute attribute = getAttribute(i);
            printWriter.print(" " + attribute.getName() + "=\"" + XML.escapeXMLChars(attribute.getValue()) + "\"");
        }
    }

    public void output(PrintWriter printWriter, int i, boolean z) {
        String indentLevelString = getIndentLevelString(i);
        String name2 = getName();
        String value2 = getValue();
        if (!hasNodes() || !z) {
            printWriter.print(indentLevelString + "<" + name2);
            outputAttributes(printWriter);
            if (value2 == null || value2.length() == 0) {
                printWriter.println("></" + name2 + ">");
                return;
            }
            printWriter.println(">" + XML.escapeXMLChars(value2) + "</" + name2 + ">");
            return;
        }
        printWriter.print(indentLevelString + "<" + name2);
        outputAttributes(printWriter);
        printWriter.println(">");
        int nNodes = getNNodes();
        for (int i2 = 0; i2 < nNodes; i2++) {
            getNode(i2).output(printWriter, i + 1, true);
        }
        printWriter.println(indentLevelString + "</" + name2 + ">");
    }

    public String toString(String str, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
        output(printWriter, 0, z);
        printWriter.flush();
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return byteArrayOutputStream.toString(str);
                }
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return byteArrayOutputStream.toString();
    }

    public String toString() {
        return toString("utf-8", true);
    }

    public String toXMLString(boolean z) {
        return toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(a.b, "&amp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
    }

    public String toXMLString() {
        return toXMLString(true);
    }

    public void print(boolean z) {
        PrintWriter printWriter = new PrintWriter(System.out);
        output(printWriter, 0, z);
        printWriter.flush();
    }

    public void print() {
        print(true);
    }
}
