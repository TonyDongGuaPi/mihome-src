package org.cybergarage.upnp.xml;

import org.cybergarage.xml.Node;

public class NodeData {
    private Node node;

    public NodeData() {
        setNode((Node) null);
    }

    public void setNode(Node node2) {
        this.node = node2;
    }

    public Node getNode() {
        return this.node;
    }
}
