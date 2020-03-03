package org.cybergarage.upnp.xml;

import org.cybergarage.upnp.event.SubscriberList;
import org.cybergarage.util.ListenerList;
import org.cybergarage.xml.Node;

public class ServiceData extends NodeData {
    private ListenerList controlActionListenerList = new ListenerList();
    private String descriptionURL = "";
    private Node scpdNode = null;
    private String sid = "";
    private SubscriberList subscriberList = new SubscriberList();
    private long timeout = 0;

    public ListenerList getControlActionListenerList() {
        return this.controlActionListenerList;
    }

    public Node getSCPDNode() {
        return this.scpdNode;
    }

    public void setSCPDNode(Node node) {
        this.scpdNode = node;
    }

    public SubscriberList getSubscriberList() {
        return this.subscriberList;
    }

    public String getDescriptionURL() {
        return this.descriptionURL;
    }

    public void setDescriptionURL(String str) {
        this.descriptionURL = str;
    }

    public String getSID() {
        return this.sid;
    }

    public void setSID(String str) {
        this.sid = str;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setTimeout(long j) {
        this.timeout = j;
    }
}
