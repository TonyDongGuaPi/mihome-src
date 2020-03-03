package org.cybergarage.upnp.control;

import org.cybergarage.soap.SOAPResponse;
import org.cybergarage.upnp.StateVariable;
import org.cybergarage.xml.Node;

public class QueryResponse extends ControlResponse {
    public QueryResponse() {
    }

    public QueryResponse(SOAPResponse sOAPResponse) {
        super(sOAPResponse);
    }

    private Node getReturnNode() {
        Node node;
        Node bodyNode = getBodyNode();
        if (bodyNode != null && bodyNode.hasNodes() && (node = bodyNode.getNode(0)) != null && node.hasNodes()) {
            return node.getNode(0);
        }
        return null;
    }

    public String getReturnValue() {
        Node returnNode = getReturnNode();
        if (returnNode == null) {
            return "";
        }
        return returnNode.getValue();
    }

    public void setResponse(StateVariable stateVariable) {
        String value = stateVariable.getValue();
        setStatusCode(200);
        getBodyNode().addNode(createResponseNode(value));
        setContent(getEnvelopeNode());
    }

    private Node createResponseNode(String str) {
        Node node = new Node();
        node.setName("u", Control.QUERY_STATE_VARIABLE_RESPONSE);
        node.setNameSpace("u", Control.XMLNS);
        Node node2 = new Node();
        node2.setName("return");
        node2.setValue(str);
        node.addNode(node2);
        return node;
    }
}
