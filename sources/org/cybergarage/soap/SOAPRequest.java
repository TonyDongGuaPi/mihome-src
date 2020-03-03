package org.cybergarage.soap;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.util.Debug;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.ParserException;

public class SOAPRequest extends HTTPRequest {
    private static final String SOAPACTION = "SOAPACTION";
    private Node rootNode;

    public SOAPRequest() {
        setContentType("text/xml; charset=\"utf-8\"");
        setMethod("POST");
    }

    public SOAPRequest(HTTPRequest hTTPRequest) {
        set(hTTPRequest);
    }

    public void setSOAPAction(String str) {
        setStringHeader("SOAPACTION", str);
    }

    public String getSOAPAction() {
        return getStringHeaderValue("SOAPACTION");
    }

    public boolean isSOAPAction(String str) {
        String headerValue = getHeaderValue("SOAPACTION");
        if (headerValue == null) {
            return false;
        }
        if (headerValue.equals(str)) {
            return true;
        }
        String sOAPAction = getSOAPAction();
        if (sOAPAction == null) {
            return false;
        }
        return sOAPAction.equals(str);
    }

    public SOAPResponse postMessage(String str, int i) {
        SOAPResponse sOAPResponse = new SOAPResponse(post(str, i));
        byte[] content = sOAPResponse.getContent();
        if (content.length <= 0) {
            return sOAPResponse;
        }
        try {
            sOAPResponse.setEnvelopeNode(SOAP.getXMLParser().parse((InputStream) new ByteArrayInputStream(content)));
        } catch (Exception e) {
            Debug.warning(e);
        }
        return sOAPResponse;
    }

    private void setRootNode(Node node) {
        this.rootNode = node;
    }

    private synchronized Node getRootNode() {
        if (this.rootNode != null) {
            return this.rootNode;
        }
        try {
            this.rootNode = SOAP.getXMLParser().parse((InputStream) new ByteArrayInputStream(getContent()));
        } catch (ParserException e) {
            Debug.warning((Exception) e);
        }
        return this.rootNode;
    }

    public void setEnvelopeNode(Node node) {
        setRootNode(node);
    }

    public Node getEnvelopeNode() {
        return getRootNode();
    }

    public Node getBodyNode() {
        Node envelopeNode = getEnvelopeNode();
        if (envelopeNode != null && envelopeNode.hasNodes()) {
            return envelopeNode.getNode(0);
        }
        return null;
    }

    public void setContent(Node node) {
        setContent((("" + "<?xml version=\"1.0\" encoding=\"utf-8\"?>") + "\n") + node.toString());
    }

    public void print() {
        Node rootNode2;
        Debug.message(toString());
        if (!hasContent() && (rootNode2 = getRootNode()) != null) {
            Debug.message(rootNode2.toString());
        }
    }
}
