package org.cybergarage.soap;

import org.cybergarage.http.HTTPResponse;
import org.cybergarage.util.Debug;
import org.cybergarage.xml.Node;

public class SOAPResponse extends HTTPResponse {
    private Node rootNode;

    public SOAPResponse() {
        setRootNode(SOAP.createEnvelopeBodyNode());
        setContentType("text/xml; charset=\"utf-8\"");
    }

    public SOAPResponse(HTTPResponse hTTPResponse) {
        super(hTTPResponse);
        setRootNode(SOAP.createEnvelopeBodyNode());
        setContentType("text/xml; charset=\"utf-8\"");
    }

    public SOAPResponse(SOAPResponse sOAPResponse) {
        super((HTTPResponse) sOAPResponse);
        setEnvelopeNode(sOAPResponse.getEnvelopeNode());
        setContentType("text/xml; charset=\"utf-8\"");
    }

    private void setRootNode(Node node) {
        this.rootNode = node;
    }

    private Node getRootNode() {
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
        if (envelopeNode == null) {
            return null;
        }
        return envelopeNode.getNodeEndsWith(SOAP.BODY);
    }

    public Node getMethodResponseNode(String str) {
        Node bodyNode = getBodyNode();
        if (bodyNode == null) {
            return null;
        }
        return bodyNode.getNodeEndsWith(str + SOAP.RESPONSE);
    }

    public Node getFaultNode() {
        Node bodyNode = getBodyNode();
        if (bodyNode == null) {
            return null;
        }
        return bodyNode.getNodeEndsWith(SOAP.FAULT);
    }

    public Node getFaultCodeNode() {
        Node faultNode = getFaultNode();
        if (faultNode == null) {
            return null;
        }
        return faultNode.getNodeEndsWith(SOAP.FAULT_CODE);
    }

    public Node getFaultStringNode() {
        Node faultNode = getFaultNode();
        if (faultNode == null) {
            return null;
        }
        return faultNode.getNodeEndsWith(SOAP.FAULT_STRING);
    }

    public Node getFaultActorNode() {
        Node faultNode = getFaultNode();
        if (faultNode == null) {
            return null;
        }
        return faultNode.getNodeEndsWith(SOAP.FAULTACTOR);
    }

    public Node getFaultDetailNode() {
        Node faultNode = getFaultNode();
        if (faultNode == null) {
            return null;
        }
        return faultNode.getNodeEndsWith("detail");
    }

    public String getFaultCode() {
        Node faultCodeNode = getFaultCodeNode();
        if (faultCodeNode == null) {
            return "";
        }
        return faultCodeNode.getValue();
    }

    public String getFaultString() {
        Node faultStringNode = getFaultStringNode();
        if (faultStringNode == null) {
            return "";
        }
        return faultStringNode.getValue();
    }

    public String getFaultActor() {
        Node faultActorNode = getFaultActorNode();
        if (faultActorNode == null) {
            return "";
        }
        return faultActorNode.getValue();
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
