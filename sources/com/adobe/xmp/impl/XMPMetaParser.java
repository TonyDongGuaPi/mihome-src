package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.options.ParseOptions;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMPMetaParser {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f694a = new Object();
    private static DocumentBuilderFactory b = a();

    private XMPMetaParser() {
    }

    public static XMPMeta a(Object obj, ParseOptions parseOptions) throws XMPException {
        ParameterAsserts.a(obj);
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        Object[] a2 = a(b(obj, parseOptions), parseOptions.a(), new Object[3]);
        if (a2 == null || a2[1] != f694a) {
            return new XMPMetaImpl();
        }
        XMPMetaImpl a3 = ParseRDF.a((Node) a2[0]);
        a3.b((String) a2[2]);
        return !parseOptions.e() ? XMPNormalizer.a(a3, parseOptions) : a3;
    }

    private static DocumentBuilderFactory a() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setIgnoringComments(true);
        try {
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setFeature("http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities", false);
            newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
        } catch (Exception unused) {
        }
        return newInstance;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(2:4|5)|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.w3c.dom.Document a(com.adobe.xmp.impl.ByteBuffer r4, com.adobe.xmp.options.ParseOptions r5) throws com.adobe.xmp.XMPException {
        /*
            org.xml.sax.InputSource r0 = new org.xml.sax.InputSource
            java.io.InputStream r1 = r4.a()
            r0.<init>(r1)
            boolean r1 = r5.f()     // Catch:{ XMPException -> 0x001c }
            if (r1 == 0) goto L_0x0017
            javax.xml.parsers.DocumentBuilderFactory r1 = b     // Catch:{ Throwable -> 0x0017 }
            java.lang.String r2 = "http://apache.org/xml/features/disallow-doctype-decl"
            r3 = 1
            r1.setFeature(r2, r3)     // Catch:{ Throwable -> 0x0017 }
        L_0x0017:
            org.w3c.dom.Document r0 = a(r0)     // Catch:{ XMPException -> 0x001c }
            return r0
        L_0x001c:
            r0 = move-exception
            int r1 = r0.getErrorCode()
            r2 = 201(0xc9, float:2.82E-43)
            if (r1 == r2) goto L_0x002f
            int r1 = r0.getErrorCode()
            r2 = 204(0xcc, float:2.86E-43)
            if (r1 != r2) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            throw r0
        L_0x002f:
            boolean r1 = r5.d()
            if (r1 == 0) goto L_0x0039
            com.adobe.xmp.impl.ByteBuffer r4 = com.adobe.xmp.impl.Latin1Converter.a((com.adobe.xmp.impl.ByteBuffer) r4)
        L_0x0039:
            boolean r5 = r5.c()
            if (r5 == 0) goto L_0x0065
            java.lang.String r5 = r4.c()     // Catch:{ UnsupportedEncodingException -> 0x005b }
            com.adobe.xmp.impl.FixASCIIControlsReader r1 = new com.adobe.xmp.impl.FixASCIIControlsReader     // Catch:{ UnsupportedEncodingException -> 0x005b }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x005b }
            java.io.InputStream r4 = r4.a()     // Catch:{ UnsupportedEncodingException -> 0x005b }
            r2.<init>(r4, r5)     // Catch:{ UnsupportedEncodingException -> 0x005b }
            r1.<init>(r2)     // Catch:{ UnsupportedEncodingException -> 0x005b }
            org.xml.sax.InputSource r4 = new org.xml.sax.InputSource     // Catch:{ UnsupportedEncodingException -> 0x005b }
            r4.<init>(r1)     // Catch:{ UnsupportedEncodingException -> 0x005b }
            org.w3c.dom.Document r4 = a(r4)     // Catch:{ UnsupportedEncodingException -> 0x005b }
            return r4
        L_0x005b:
            com.adobe.xmp.XMPException r4 = new com.adobe.xmp.XMPException
            r5 = 9
            java.lang.String r1 = "Unsupported Encoding"
            r4.<init>(r1, r5, r0)
            throw r4
        L_0x0065:
            org.xml.sax.InputSource r5 = new org.xml.sax.InputSource
            java.io.InputStream r4 = r4.a()
            r5.<init>(r4)
            org.w3c.dom.Document r4 = a(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPMetaParser.a(com.adobe.xmp.impl.ByteBuffer, com.adobe.xmp.options.ParseOptions):org.w3c.dom.Document");
    }

    private static Document a(InputStream inputStream, ParseOptions parseOptions) throws XMPException {
        if (!parseOptions.d() && !parseOptions.c()) {
            return a(new InputSource(inputStream));
        }
        try {
            return a(new ByteBuffer(inputStream), parseOptions);
        } catch (IOException e) {
            throw new XMPException("Error reading the XML-file", 204, e);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:0|1|2|(2:4|5)|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.w3c.dom.Document a(java.lang.String r3, com.adobe.xmp.options.ParseOptions r4) throws com.adobe.xmp.XMPException {
        /*
            org.xml.sax.InputSource r0 = new org.xml.sax.InputSource
            java.io.StringReader r1 = new java.io.StringReader
            r1.<init>(r3)
            r0.<init>(r1)
            boolean r0 = r4.f()     // Catch:{ XMPException -> 0x0027 }
            if (r0 == 0) goto L_0x0018
            javax.xml.parsers.DocumentBuilderFactory r0 = b     // Catch:{ Throwable -> 0x0018 }
            java.lang.String r1 = "http://apache.org/xml/features/disallow-doctype-decl"
            r2 = 1
            r0.setFeature(r1, r2)     // Catch:{ Throwable -> 0x0018 }
        L_0x0018:
            org.xml.sax.InputSource r0 = new org.xml.sax.InputSource     // Catch:{ XMPException -> 0x0027 }
            java.io.StringReader r1 = new java.io.StringReader     // Catch:{ XMPException -> 0x0027 }
            r1.<init>(r3)     // Catch:{ XMPException -> 0x0027 }
            r0.<init>(r1)     // Catch:{ XMPException -> 0x0027 }
            org.w3c.dom.Document r0 = a(r0)     // Catch:{ XMPException -> 0x0027 }
            return r0
        L_0x0027:
            r0 = move-exception
            int r1 = r0.getErrorCode()
            r2 = 201(0xc9, float:2.82E-43)
            if (r1 != r2) goto L_0x004a
            boolean r4 = r4.c()
            if (r4 == 0) goto L_0x004a
            org.xml.sax.InputSource r4 = new org.xml.sax.InputSource
            com.adobe.xmp.impl.FixASCIIControlsReader r0 = new com.adobe.xmp.impl.FixASCIIControlsReader
            java.io.StringReader r1 = new java.io.StringReader
            r1.<init>(r3)
            r0.<init>(r1)
            r4.<init>(r0)
            org.w3c.dom.Document r3 = a(r4)
            return r3
        L_0x004a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPMetaParser.a(java.lang.String, com.adobe.xmp.options.ParseOptions):org.w3c.dom.Document");
    }

    private static Document a(InputSource inputSource) throws XMPException {
        try {
            DocumentBuilder newDocumentBuilder = b.newDocumentBuilder();
            newDocumentBuilder.setErrorHandler((ErrorHandler) null);
            return newDocumentBuilder.parse(inputSource);
        } catch (SAXException e) {
            throw new XMPException("XML parsing failure", 201, e);
        } catch (ParserConfigurationException e2) {
            throw new XMPException("XML Parser not correctly configured", 0, e2);
        } catch (IOException e3) {
            throw new XMPException("Error reading the XML-file", 204, e3);
        }
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object[] a(org.w3c.dom.Node r7, boolean r8, java.lang.Object[] r9) {
        /*
            org.w3c.dom.NodeList r7 = r7.getChildNodes()
            r0 = 0
            r1 = 0
        L_0x0006:
            int r2 = r7.getLength()
            if (r1 >= r2) goto L_0x008c
            org.w3c.dom.Node r2 = r7.item(r1)
            short r3 = r2.getNodeType()
            r4 = 7
            if (r4 != r3) goto L_0x0031
            java.lang.String r3 = "xpacket"
            r5 = r2
            org.w3c.dom.ProcessingInstruction r5 = (org.w3c.dom.ProcessingInstruction) r5
            java.lang.String r6 = r5.getTarget()
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x0031
            if (r9 == 0) goto L_0x0088
            r2 = 2
            java.lang.String r3 = r5.getData()
            r9[r2] = r3
            goto L_0x0088
        L_0x0031:
            r3 = 3
            short r5 = r2.getNodeType()
            if (r3 == r5) goto L_0x0088
            short r3 = r2.getNodeType()
            if (r4 == r3) goto L_0x0088
            java.lang.String r3 = r2.getNamespaceURI()
            java.lang.String r4 = r2.getLocalName()
            java.lang.String r5 = "xmpmeta"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x0058
            java.lang.String r5 = "xapmeta"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0065
        L_0x0058:
            java.lang.String r5 = "adobe:ns:meta/"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0065
            java.lang.Object[] r7 = a(r2, r0, r9)
            return r7
        L_0x0065:
            if (r8 != 0) goto L_0x0081
            java.lang.String r5 = "RDF"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0081
            java.lang.String r4 = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0081
            if (r9 == 0) goto L_0x0080
            r9[r0] = r2
            java.lang.Object r7 = f694a
            r8 = 1
            r9[r8] = r7
        L_0x0080:
            return r9
        L_0x0081:
            java.lang.Object[] r2 = a(r2, r8, r9)
            if (r2 == 0) goto L_0x0088
            return r2
        L_0x0088:
            int r1 = r1 + 1
            goto L_0x0006
        L_0x008c:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPMetaParser.a(org.w3c.dom.Node, boolean, java.lang.Object[]):java.lang.Object[]");
    }

    private static Document b(Object obj, ParseOptions parseOptions) throws XMPException {
        return obj instanceof InputStream ? a((InputStream) obj, parseOptions) : obj instanceof byte[] ? a(new ByteBuffer((byte[]) obj), parseOptions) : a((String) obj, parseOptions);
    }
}
