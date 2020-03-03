package org.cybergarage.upnp.control;

import org.cybergarage.http.HTTP;
import org.cybergarage.soap.SOAP;
import org.cybergarage.soap.SOAPResponse;
import org.cybergarage.upnp.Action;
import org.cybergarage.upnp.Argument;
import org.cybergarage.upnp.ArgumentList;
import org.cybergarage.upnp.Service;
import org.cybergarage.xml.Node;

public class ActionResponse extends ControlResponse {
    public ActionResponse() {
        setHeader(HTTP.EXT, "");
    }

    public ActionResponse(SOAPResponse sOAPResponse) {
        super(sOAPResponse);
        setHeader(HTTP.EXT, "");
    }

    public void setResponse(Action action) {
        setStatusCode(200);
        getBodyNode().addNode(createResponseNode(action));
        setContent(getEnvelopeNode());
    }

    private Node createResponseNode(Action action) {
        String name = action.getName();
        Node node = new Node("u:" + name + SOAP.RESPONSE);
        Service service = action.getService();
        if (service != null) {
            node.setAttribute("xmlns:u", service.getServiceType());
        }
        ArgumentList argumentList = action.getArgumentList();
        int size = argumentList.size();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            if (argument.isOutDirection()) {
                Node node2 = new Node();
                node2.setName(argument.getName());
                node2.setValue(argument.getValue());
                node.addNode(node2);
            }
        }
        return node;
    }

    private Node getActionResponseNode() {
        Node bodyNode = getBodyNode();
        if (bodyNode == null || !bodyNode.hasNodes()) {
            return null;
        }
        return bodyNode.getNode(0);
    }

    public ArgumentList getResponse() {
        ArgumentList argumentList = new ArgumentList();
        Node actionResponseNode = getActionResponseNode();
        if (actionResponseNode == null) {
            return argumentList;
        }
        int nNodes = actionResponseNode.getNNodes();
        for (int i = 0; i < nNodes; i++) {
            Node node = actionResponseNode.getNode(i);
            argumentList.add(new Argument(node.getName(), node.getValue()));
        }
        return argumentList;
    }
}
