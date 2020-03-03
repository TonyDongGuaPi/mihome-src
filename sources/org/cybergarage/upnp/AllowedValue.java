package org.cybergarage.upnp;

import org.cybergarage.xml.Node;

public class AllowedValue {
    public static final String ELEM_NAME = "allowedValue";
    private Node allowedValueNode;

    public Node getAllowedValueNode() {
        return this.allowedValueNode;
    }

    public AllowedValue(Node node) {
        this.allowedValueNode = node;
    }

    public AllowedValue(String str) {
        this.allowedValueNode = new Node(ELEM_NAME);
        setValue(str);
    }

    public static boolean isAllowedValueNode(Node node) {
        return ELEM_NAME.equals(node.getName());
    }

    public void setValue(String str) {
        getAllowedValueNode().setValue(str);
    }

    public String getValue() {
        return getAllowedValueNode().getValue();
    }
}
