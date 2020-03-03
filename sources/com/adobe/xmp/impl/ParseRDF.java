package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPError;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.PropertyOptions;
import com.facebook.places.model.PlaceFields;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ParseRDF implements XMPConst, XMPError {
    public static final int aA = 11;
    public static final int aB = 12;
    public static final int aC = 1;
    public static final int aD = 7;
    public static final int aE = 1;
    public static final int aF = 9;
    public static final int aG = 10;
    public static final int aH = 12;
    public static final String aI = "_dflt";
    static final /* synthetic */ boolean aJ = (!ParseRDF.class.desiredAssertionStatus());
    public static final int ap = 0;
    public static final int aq = 1;
    public static final int ar = 2;
    public static final int as = 3;
    public static final int at = 4;
    public static final int au = 5;
    public static final int av = 6;
    public static final int aw = 7;
    public static final int ax = 8;
    public static final int ay = 9;
    public static final int az = 10;

    static XMPMetaImpl a(Node node) throws XMPException {
        XMPMetaImpl xMPMetaImpl = new XMPMetaImpl();
        a(xMPMetaImpl, node);
        return xMPMetaImpl;
    }

    private static XMPNode a(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, String str, boolean z) throws XMPException {
        XMPSchemaRegistry a2 = XMPMetaFactory.a();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI != null) {
            if (XMPConst.S.equals(namespaceURI)) {
                namespaceURI = "http://purl.org/dc/elements/1.1/";
            }
            String a3 = a2.a(namespaceURI);
            if (a3 == null) {
                a3 = a2.a(namespaceURI, node.getPrefix() != null ? node.getPrefix() : aI);
            }
            String str2 = a3 + node.getLocalName();
            PropertyOptions propertyOptions = new PropertyOptions();
            boolean z2 = false;
            if (z) {
                xMPNode = XMPNodeUtils.a(xMPMetaImpl.f(), namespaceURI, aI, true);
                xMPNode.b(false);
                if (a2.e(str2) != null) {
                    xMPMetaImpl.f().c(true);
                    xMPNode.c(true);
                    z2 = true;
                }
            }
            boolean equals = "rdf:li".equals(str2);
            boolean equals2 = "rdf:value".equals(str2);
            XMPNode xMPNode2 = new XMPNode(str2, str, propertyOptions);
            xMPNode2.d(z2);
            if (!equals2) {
                xMPNode.a(xMPNode2);
            } else {
                xMPNode.a(1, xMPNode2);
            }
            if (equals2) {
                if (z || !xMPNode.n().f()) {
                    throw new XMPException("Misplaced rdf:value element", 202);
                }
                xMPNode.e(true);
            }
            if (equals) {
                if (xMPNode.n().k()) {
                    xMPNode2.c(XMPConst.ai);
                } else {
                    throw new XMPException("Misplaced rdf:li element", 202);
                }
            }
            return xMPNode2;
        }
        throw new XMPException("XML namespace required for all elements and attributes", 202);
    }

    private static XMPNode a(XMPNode xMPNode, String str, String str2) throws XMPException {
        if (XMPConst.ak.equals(str)) {
            str2 = Utils.a(str2);
        }
        XMPNode xMPNode2 = new XMPNode(str, str2, (PropertyOptions) null);
        xMPNode.c(xMPNode2);
        return xMPNode2;
    }

    private static void a() throws XMPException {
        throw new XMPException("ParseTypeLiteral property element not allowed", 203);
    }

    private static void a(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node) throws XMPException {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node item = node.getChildNodes().item(i);
            if (!b(item)) {
                a(xMPMetaImpl, xMPNode, item, true);
            }
        }
    }

    private static void a(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        int c = c(node);
        if (c != 8 && c != 0) {
            throw new XMPException("Node element must be rdf:Description or typed node", 202);
        } else if (!z || c != 0) {
            b(xMPMetaImpl, xMPNode, node, z);
            c(xMPMetaImpl, xMPNode, node, z);
        } else {
            throw new XMPException("Top level typed node not allowed", 203);
        }
    }

    static void a(XMPMetaImpl xMPMetaImpl, Node node) throws XMPException {
        if (node.hasAttributes()) {
            a(xMPMetaImpl, xMPMetaImpl.f(), node);
            return;
        }
        throw new XMPException("Invalid attributes of rdf:RDF element", 202);
    }

    private static void a(XMPNode xMPNode) throws XMPException {
        if (aJ || (xMPNode.n().f() && xMPNode.h())) {
            XMPNode a2 = xMPNode.a(1);
            if (aJ || "rdf:value".equals(a2.l())) {
                if (a2.n().d()) {
                    if (!xMPNode.n().d()) {
                        XMPNode c = a2.c(1);
                        a2.d(c);
                        xMPNode.c(c);
                    } else {
                        throw new XMPException("Redundant xml:lang for rdf:value element", 203);
                    }
                }
                for (int i = 1; i <= a2.f(); i++) {
                    xMPNode.c(a2.c(i));
                }
                for (int i2 = 2; i2 <= xMPNode.e(); i2++) {
                    xMPNode.c(xMPNode.a(i2));
                }
                if (aJ || xMPNode.n().f() || xMPNode.r()) {
                    xMPNode.e(false);
                    xMPNode.n().f(false);
                    xMPNode.n().b(a2.n());
                    xMPNode.d(a2.m());
                    xMPNode.d();
                    Iterator i3 = a2.i();
                    while (i3.hasNext()) {
                        xMPNode.a((XMPNode) i3.next());
                    }
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static boolean a(int i) {
        if (i == 8 || b(i)) {
            return false;
        }
        return !c(i);
    }

    private static void b() throws XMPException {
        throw new XMPException("ParseTypeCollection property element not allowed", 203);
    }

    private static void b(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        int i = 0;
        for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
            Node item = node.getAttributes().item(i2);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                int c = c(item);
                if (c != 0) {
                    if (c != 6) {
                        switch (c) {
                            case 2:
                            case 3:
                                break;
                            default:
                                throw new XMPException("Invalid nodeElement attribute", 202);
                        }
                    }
                    if (i <= 0) {
                        i++;
                        if (z && c == 3) {
                            if (xMPNode.l() == null || xMPNode.l().length() <= 0) {
                                xMPNode.c(item.getNodeValue());
                            } else if (!xMPNode.l().equals(item.getNodeValue())) {
                                throw new XMPException("Mismatched top level rdf:about values", 203);
                            }
                        }
                    } else {
                        throw new XMPException("Mutally exclusive about, ID, nodeID attributes", 202);
                    }
                } else {
                    a(xMPMetaImpl, xMPNode, item, item.getNodeValue(), z);
                }
            }
        }
    }

    private static boolean b(int i) {
        return 10 <= i && i <= 12;
    }

    private static boolean b(Node node) {
        if (node.getNodeType() != 3) {
            return false;
        }
        String nodeValue = node.getNodeValue();
        for (int i = 0; i < nodeValue.length(); i++) {
            if (!Character.isWhitespace(nodeValue.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int c(Node node) {
        String localName = node.getLocalName();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null && ((PlaceFields.ABOUT.equals(localName) || "ID".equals(localName)) && (node instanceof Attr) && XMPConst.b_.equals(((Attr) node).getOwnerElement().getNamespaceURI()))) {
            namespaceURI = XMPConst.b_;
        }
        if (!XMPConst.b_.equals(namespaceURI)) {
            return 0;
        }
        if ("li".equals(localName)) {
            return 9;
        }
        if ("parseType".equals(localName)) {
            return 4;
        }
        if ("Description".equals(localName)) {
            return 8;
        }
        if (PlaceFields.ABOUT.equals(localName)) {
            return 3;
        }
        if ("resource".equals(localName)) {
            return 5;
        }
        if ("RDF".equals(localName)) {
            return 1;
        }
        if ("ID".equals(localName)) {
            return 2;
        }
        if ("nodeID".equals(localName)) {
            return 6;
        }
        if ("datatype".equals(localName)) {
            return 7;
        }
        if ("aboutEach".equals(localName)) {
            return 10;
        }
        if ("aboutEachPrefix".equals(localName)) {
            return 11;
        }
        return "bagID".equals(localName) ? 12 : 0;
    }

    private static void c() throws XMPException {
        throw new XMPException("ParseTypeOther property element not allowed", 203);
    }

    private static void c(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node item = node.getChildNodes().item(i);
            if (!b(item)) {
                if (item.getNodeType() == 1) {
                    d(xMPMetaImpl, xMPNode, item, z);
                } else {
                    throw new XMPException("Expected property element node not found", 202);
                }
            }
        }
    }

    private static boolean c(int i) {
        return 1 <= i && i <= 7;
    }

    private static void d(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        if (a(c(node))) {
            NamedNodeMap attributes = node.getAttributes();
            ArrayList<String> arrayList = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                Node item = attributes.item(i);
                if ("xmlns".equals(item.getPrefix()) || (item.getPrefix() == null && "xmlns".equals(item.getNodeName()))) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(item.getNodeName());
                }
            }
            if (arrayList != null) {
                for (String removeNamedItem : arrayList) {
                    attributes.removeNamedItem(removeNamedItem);
                }
            }
            if (attributes.getLength() <= 3) {
                int i2 = 0;
                while (i2 < attributes.getLength()) {
                    Node item2 = attributes.item(i2);
                    String localName = item2.getLocalName();
                    String namespaceURI = item2.getNamespaceURI();
                    String nodeValue = item2.getNodeValue();
                    if (XMPConst.ak.equals(item2.getNodeName()) && (!"ID".equals(localName) || !XMPConst.b_.equals(namespaceURI))) {
                        i2++;
                    } else if ("datatype".equals(localName) && XMPConst.b_.equals(namespaceURI)) {
                        f(xMPMetaImpl, xMPNode, node, z);
                        return;
                    } else if (!"parseType".equals(localName) || !XMPConst.b_.equals(namespaceURI)) {
                        h(xMPMetaImpl, xMPNode, node, z);
                        return;
                    } else if ("Literal".equals(nodeValue)) {
                        a();
                        return;
                    } else if ("Resource".equals(nodeValue)) {
                        g(xMPMetaImpl, xMPNode, node, z);
                        return;
                    } else if ("Collection".equals(nodeValue)) {
                        b();
                        return;
                    } else {
                        c();
                        return;
                    }
                }
                if (node.hasChildNodes()) {
                    for (int i3 = 0; i3 < node.getChildNodes().getLength(); i3++) {
                        if (node.getChildNodes().item(i3).getNodeType() != 3) {
                            e(xMPMetaImpl, xMPNode, node, z);
                            return;
                        }
                    }
                    f(xMPMetaImpl, xMPNode, node, z);
                    return;
                }
            }
            h(xMPMetaImpl, xMPNode, node, z);
            return;
        }
        throw new XMPException("Invalid property element name", 202);
    }

    private static void e(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        if (!z || !"iX:changes".equals(node.getNodeName())) {
            XMPNode a2 = a(xMPMetaImpl, xMPNode, node, "", z);
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node item = node.getAttributes().item(i);
                if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                    String localName = item.getLocalName();
                    String namespaceURI = item.getNamespaceURI();
                    if (XMPConst.ak.equals(item.getNodeName())) {
                        a(a2, XMPConst.ak, item.getNodeValue());
                    } else if (!"ID".equals(localName) || !XMPConst.b_.equals(namespaceURI)) {
                        throw new XMPException("Invalid attribute for resource property element", 202);
                    }
                }
            }
            boolean z2 = false;
            for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
                Node item2 = node.getChildNodes().item(i2);
                if (!b(item2)) {
                    if (item2.getNodeType() == 1 && !z2) {
                        boolean equals = XMPConst.b_.equals(item2.getNamespaceURI());
                        String localName2 = item2.getLocalName();
                        if (equals && "Bag".equals(localName2)) {
                            a2.n().g(true);
                        } else if (equals && "Seq".equals(localName2)) {
                            a2.n().g(true).h(true);
                        } else if (!equals || !"Alt".equals(localName2)) {
                            a2.n().f(true);
                            if (!equals && !"Description".equals(localName2)) {
                                String namespaceURI2 = item2.getNamespaceURI();
                                if (namespaceURI2 != null) {
                                    a(a2, XMPConst.al, namespaceURI2 + Operators.CONDITION_IF_MIDDLE + localName2);
                                } else {
                                    throw new XMPException("All XML elements must be in a namespace", 203);
                                }
                            }
                        } else {
                            a2.n().g(true).h(true).i(true);
                        }
                        a(xMPMetaImpl, a2, item2, false);
                        if (a2.r()) {
                            a(a2);
                        } else if (a2.n().m()) {
                            XMPNodeUtils.c(a2);
                        }
                        z2 = true;
                    } else if (z2) {
                        throw new XMPException("Invalid child of resource property element", 202);
                    } else {
                        throw new XMPException("Children of resource property element must be XML elements", 202);
                    }
                }
            }
            if (!z2) {
                throw new XMPException("Missing child of resource property element", 202);
            }
        }
    }

    private static void f(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        XMPNode a2 = a(xMPMetaImpl, xMPNode, node, (String) null, z);
        int i = 0;
        for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
            Node item = node.getAttributes().item(i2);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String namespaceURI = item.getNamespaceURI();
                String localName = item.getLocalName();
                if (XMPConst.ak.equals(item.getNodeName())) {
                    a(a2, XMPConst.ak, item.getNodeValue());
                } else if (!XMPConst.b_.equals(namespaceURI) || (!"ID".equals(localName) && !"datatype".equals(localName))) {
                    throw new XMPException("Invalid attribute for literal property element", 202);
                }
            }
        }
        String str = "";
        while (i < node.getChildNodes().getLength()) {
            Node item2 = node.getChildNodes().item(i);
            if (item2.getNodeType() == 3) {
                str = str + item2.getNodeValue();
                i++;
            } else {
                throw new XMPException("Invalid child of literal property element", 202);
            }
        }
        a2.d(str);
    }

    private static void g(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        XMPNode a2 = a(xMPMetaImpl, xMPNode, node, "", z);
        a2.n().f(true);
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node item = node.getAttributes().item(i);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String localName = item.getLocalName();
                String namespaceURI = item.getNamespaceURI();
                if (XMPConst.ak.equals(item.getNodeName())) {
                    a(a2, XMPConst.ak, item.getNodeValue());
                } else if (!XMPConst.b_.equals(namespaceURI) || (!"ID".equals(localName) && !"parseType".equals(localName))) {
                    throw new XMPException("Invalid attribute for ParseTypeResource property element", 202);
                }
            }
        }
        c(xMPMetaImpl, a2, node, false);
        if (a2.r()) {
            a(a2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void h(com.adobe.xmp.impl.XMPMetaImpl r14, com.adobe.xmp.impl.XMPNode r15, org.w3c.dom.Node r16, boolean r17) throws com.adobe.xmp.XMPException {
        /*
            r0 = r14
            boolean r1 = r16.hasChildNodes()
            r2 = 202(0xca, float:2.83E-43)
            if (r1 != 0) goto L_0x015f
            r1 = 0
            r3 = 0
            r6 = r3
            r3 = 0
            r4 = 0
            r5 = 0
            r7 = 0
            r8 = 0
        L_0x0011:
            org.w3c.dom.NamedNodeMap r9 = r16.getAttributes()
            int r9 = r9.getLength()
            r10 = 2
            r11 = 1
            if (r3 >= r9) goto L_0x00b9
            org.w3c.dom.NamedNodeMap r9 = r16.getAttributes()
            org.w3c.dom.Node r9 = r9.item(r3)
            java.lang.String r12 = "xmlns"
            java.lang.String r13 = r9.getPrefix()
            boolean r12 = r12.equals(r13)
            if (r12 != 0) goto L_0x00b5
            java.lang.String r12 = r9.getPrefix()
            if (r12 != 0) goto L_0x0047
            java.lang.String r12 = "xmlns"
            java.lang.String r13 = r9.getNodeName()
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x0047
            goto L_0x00b5
        L_0x0047:
            int r12 = c((org.w3c.dom.Node) r9)
            r13 = 203(0xcb, float:2.84E-43)
            if (r12 == 0) goto L_0x0081
            if (r12 == r10) goto L_0x00b5
            switch(r12) {
                case 5: goto L_0x0068;
                case 6: goto L_0x005c;
                default: goto L_0x0054;
            }
        L_0x0054:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Unrecognized attribute of empty property element"
            r0.<init>(r1, r2)
            throw r0
        L_0x005c:
            if (r5 != 0) goto L_0x0060
            r8 = 1
            goto L_0x00b5
        L_0x0060:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Empty property element can't have both rdf:resource and rdf:nodeID"
            r0.<init>(r1, r2)
            throw r0
        L_0x0068:
            if (r8 != 0) goto L_0x0079
            if (r4 != 0) goto L_0x0071
            if (r4 != 0) goto L_0x006f
            r6 = r9
        L_0x006f:
            r5 = 1
            goto L_0x00b5
        L_0x0071:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Empty property element can't have both rdf:value and rdf:resource"
            r0.<init>(r1, r13)
            throw r0
        L_0x0079:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Empty property element can't have both rdf:resource and rdf:nodeID"
            r0.<init>(r1, r2)
            throw r0
        L_0x0081:
            java.lang.String r10 = "value"
            java.lang.String r12 = r9.getLocalName()
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x00a7
            java.lang.String r10 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            java.lang.String r12 = r9.getNamespaceURI()
            boolean r10 = r10.equals(r12)
            if (r10 == 0) goto L_0x00a7
            if (r5 != 0) goto L_0x009f
            r6 = r9
            r4 = 1
            goto L_0x00b5
        L_0x009f:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Empty property element can't have both rdf:value and rdf:resource"
            r0.<init>(r1, r13)
            throw r0
        L_0x00a7:
            java.lang.String r10 = "xml:lang"
            java.lang.String r9 = r9.getNodeName()
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00b5
            r7 = 1
        L_0x00b5:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x00b9:
            java.lang.String r3 = ""
            r8 = r15
            r9 = r16
            r12 = r17
            com.adobe.xmp.impl.XMPNode r3 = a(r14, r15, r9, r3, r12)
            if (r4 != 0) goto L_0x00d3
            if (r5 == 0) goto L_0x00c9
            goto L_0x00d3
        L_0x00c9:
            if (r7 == 0) goto L_0x00e8
            com.adobe.xmp.options.PropertyOptions r4 = r3.n()
            r4.f(r11)
            goto L_0x00e9
        L_0x00d3:
            if (r6 == 0) goto L_0x00da
            java.lang.String r5 = r6.getNodeValue()
            goto L_0x00dc
        L_0x00da:
            java.lang.String r5 = ""
        L_0x00dc:
            r3.d((java.lang.String) r5)
            if (r4 != 0) goto L_0x00e8
            com.adobe.xmp.options.PropertyOptions r4 = r3.n()
            r4.a((boolean) r11)
        L_0x00e8:
            r11 = 0
        L_0x00e9:
            r4 = 0
        L_0x00ea:
            org.w3c.dom.NamedNodeMap r5 = r16.getAttributes()
            int r5 = r5.getLength()
            if (r4 >= r5) goto L_0x015e
            org.w3c.dom.NamedNodeMap r5 = r16.getAttributes()
            org.w3c.dom.Node r5 = r5.item(r4)
            if (r5 == r6) goto L_0x015b
            java.lang.String r7 = "xmlns"
            java.lang.String r8 = r5.getPrefix()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x015b
            java.lang.String r7 = r5.getPrefix()
            if (r7 != 0) goto L_0x011f
            java.lang.String r7 = "xmlns"
            java.lang.String r8 = r5.getNodeName()
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x011f
            goto L_0x015b
        L_0x011f:
            int r7 = c((org.w3c.dom.Node) r5)
            if (r7 == 0) goto L_0x013c
            if (r7 == r10) goto L_0x015b
            switch(r7) {
                case 5: goto L_0x0132;
                case 6: goto L_0x015b;
                default: goto L_0x012a;
            }
        L_0x012a:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Unrecognized attribute of empty property element"
            r0.<init>(r1, r2)
            throw r0
        L_0x0132:
            java.lang.String r7 = "rdf:resource"
        L_0x0134:
            java.lang.String r5 = r5.getNodeValue()
            a((com.adobe.xmp.impl.XMPNode) r3, (java.lang.String) r7, (java.lang.String) r5)
            goto L_0x015b
        L_0x013c:
            if (r11 != 0) goto L_0x0143
            java.lang.String r7 = r5.getNodeName()
            goto L_0x0134
        L_0x0143:
            java.lang.String r7 = "xml:lang"
            java.lang.String r8 = r5.getNodeName()
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0154
            java.lang.String r7 = "xml:lang"
            goto L_0x0134
        L_0x0154:
            java.lang.String r7 = r5.getNodeValue()
            a(r14, r3, r5, r7, r1)
        L_0x015b:
            int r4 = r4 + 1
            goto L_0x00ea
        L_0x015e:
            return
        L_0x015f:
            com.adobe.xmp.XMPException r0 = new com.adobe.xmp.XMPException
            java.lang.String r1 = "Nested content not allowed with rdf:resource or property attributes"
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ParseRDF.h(com.adobe.xmp.impl.XMPMetaImpl, com.adobe.xmp.impl.XMPNode, org.w3c.dom.Node, boolean):void");
    }
}
