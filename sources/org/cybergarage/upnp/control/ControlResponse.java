package org.cybergarage.upnp.control;

import org.cybergarage.soap.SOAP;
import org.cybergarage.soap.SOAPResponse;
import org.cybergarage.upnp.UPnP;
import org.cybergarage.upnp.UPnPStatus;
import org.cybergarage.xml.Node;

public class ControlResponse extends SOAPResponse {
    public static final String FAULT_CODE = "Client";
    public static final String FAULT_STRING = "UPnPError";
    private UPnPStatus upnpErr = new UPnPStatus();

    public ControlResponse() {
        setServer(UPnP.getServerName());
    }

    public ControlResponse(SOAPResponse sOAPResponse) {
        super(sOAPResponse);
    }

    public void setFaultResponse(int i, String str) {
        setStatusCode(500);
        getBodyNode().addNode(createFaultResponseNode(i, str));
        setContent(getEnvelopeNode());
    }

    public void setFaultResponse(int i) {
        setFaultResponse(i, UPnPStatus.code2String(i));
    }

    private Node createFaultResponseNode(int i, String str) {
        Node node = new Node("s:Fault");
        Node node2 = new Node(SOAP.FAULT_CODE);
        node2.setValue("s:Client");
        node.addNode(node2);
        Node node3 = new Node(SOAP.FAULT_STRING);
        node3.setValue("UPnPError");
        node.addNode(node3);
        Node node4 = new Node("detail");
        node.addNode(node4);
        Node node5 = new Node("UPnPError");
        node5.setAttribute("xmlns", Control.XMLNS);
        node4.addNode(node5);
        Node node6 = new Node("errorCode");
        node6.setValue(i);
        node5.addNode(node6);
        Node node7 = new Node(SOAP.ERROR_DESCRIPTION);
        node7.setValue(str);
        node5.addNode(node7);
        return node;
    }

    private Node createFaultResponseNode(int i) {
        return createFaultResponseNode(i, UPnPStatus.code2String(i));
    }

    private Node getUPnPErrorNode() {
        Node faultDetailNode = getFaultDetailNode();
        if (faultDetailNode == null) {
            return null;
        }
        return faultDetailNode.getNodeEndsWith("UPnPError");
    }

    private Node getUPnPErrorCodeNode() {
        Node uPnPErrorNode = getUPnPErrorNode();
        if (uPnPErrorNode == null) {
            return null;
        }
        return uPnPErrorNode.getNodeEndsWith("errorCode");
    }

    private Node getUPnPErrorDescriptionNode() {
        Node uPnPErrorNode = getUPnPErrorNode();
        if (uPnPErrorNode == null) {
            return null;
        }
        return uPnPErrorNode.getNodeEndsWith(SOAP.ERROR_DESCRIPTION);
    }

    public int getUPnPErrorCode() {
        Node uPnPErrorCodeNode = getUPnPErrorCodeNode();
        if (uPnPErrorCodeNode == null) {
            return -1;
        }
        try {
            return Integer.parseInt(uPnPErrorCodeNode.getValue());
        } catch (Exception unused) {
            return -1;
        }
    }

    public String getUPnPErrorDescription() {
        Node uPnPErrorDescriptionNode = getUPnPErrorDescriptionNode();
        if (uPnPErrorDescriptionNode == null) {
            return "";
        }
        return uPnPErrorDescriptionNode.getValue();
    }

    public UPnPStatus getUPnPError() {
        int uPnPErrorCode = getUPnPErrorCode();
        String uPnPErrorDescription = getUPnPErrorDescription();
        this.upnpErr.setCode(uPnPErrorCode);
        this.upnpErr.setDescription(uPnPErrorDescription);
        return this.upnpErr;
    }
}
