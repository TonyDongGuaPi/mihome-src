package org.cybergarage.upnp.event;

import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.soap.SOAPRequest;
import org.cybergarage.upnp.device.NT;
import org.cybergarage.upnp.device.NTS;
import org.cybergarage.xml.Node;

public class NotifyRequest extends SOAPRequest {
    private static final String PROPERTY = "property";
    private static final String PROPERTYSET = "propertyset";
    private static final String XMLNS = "e";

    public NotifyRequest() {
    }

    public NotifyRequest(HTTPRequest hTTPRequest) {
        set(hTTPRequest);
    }

    public void setNT(String str) {
        setHeader(HTTP.NT, str);
    }

    public void setNTS(String str) {
        setHeader(HTTP.NTS, str);
    }

    public void setSID(String str) {
        setHeader(HTTP.SID, Subscription.toSIDHeaderString(str));
    }

    public String getSID() {
        return Subscription.getSID(getHeaderValue(HTTP.SID));
    }

    public void setSEQ(long j) {
        setHeader(HTTP.SEQ, Long.toString(j));
    }

    public long getSEQ() {
        return getLongHeaderValue(HTTP.SEQ);
    }

    public boolean setRequest(Subscriber subscriber, String str, String str2) {
        subscriber.getDeliveryURL();
        String sid = subscriber.getSID();
        long notifyCount = subscriber.getNotifyCount();
        String deliveryHost = subscriber.getDeliveryHost();
        String deliveryPath = subscriber.getDeliveryPath();
        int deliveryPort = subscriber.getDeliveryPort();
        setMethod(HTTP.NOTIFY);
        setURI(deliveryPath);
        setHost(deliveryHost, deliveryPort);
        setNT(NT.EVENT);
        setNTS(NTS.PROPCHANGE);
        setSID(sid);
        setSEQ(notifyCount);
        setContentType("text/xml; charset=\"utf-8\"");
        setContent(createPropertySetNode(str, str2));
        return true;
    }

    private Node createPropertySetNode(String str, String str2) {
        Node node = new Node(PROPERTYSET);
        node.setNameSpace("e", Subscription.XMLNS);
        Node node2 = new Node("property");
        node.addNode(node2);
        Node node3 = new Node(str);
        node3.setValue(str2);
        node2.addNode(node3);
        return node;
    }

    private Node getVariableNode() {
        Node envelopeNode = getEnvelopeNode();
        if (envelopeNode == null || !envelopeNode.hasNodes()) {
            return null;
        }
        Node node = envelopeNode.getNode(0);
        if (!node.hasNodes()) {
            return null;
        }
        return node.getNode(0);
    }

    private Property getProperty(Node node) {
        Property property = new Property();
        if (node == null) {
            return property;
        }
        String name = node.getName();
        int lastIndexOf = name.lastIndexOf(58);
        if (lastIndexOf != -1) {
            name = name.substring(lastIndexOf + 1);
        }
        property.setName(name);
        property.setValue(node.getValue());
        return property;
    }

    public PropertyList getPropertyList() {
        PropertyList propertyList = new PropertyList();
        Node envelopeNode = getEnvelopeNode();
        for (int i = 0; i < envelopeNode.getNNodes(); i++) {
            Node node = envelopeNode.getNode(i);
            if (node != null) {
                propertyList.add(getProperty(node.getNode(0)));
            }
        }
        return propertyList;
    }
}
