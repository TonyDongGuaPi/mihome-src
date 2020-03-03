package org.cybergarage.upnp;

import org.cybergarage.upnp.xml.ArgumentData;
import org.cybergarage.xml.Node;

public class Argument {
    private static final String DIRECTION = "direction";
    public static final String ELEM_NAME = "argument";
    public static final String IN = "in";
    private static final String NAME = "name";
    public static final String OUT = "out";
    private static final String RELATED_STATE_VARIABLE = "relatedStateVariable";
    private Node argumentNode;
    private Node serviceNode;
    private Object userData;

    public Node getArgumentNode() {
        return this.argumentNode;
    }

    private Node getServiceNode() {
        return this.serviceNode;
    }

    public Service getService() {
        return new Service(getServiceNode());
    }

    /* access modifiers changed from: package-private */
    public void setService(Service service) {
        service.getServiceNode();
    }

    public Node getActionNode() {
        Node parentNode;
        Node parentNode2 = getArgumentNode().getParentNode();
        if (parentNode2 == null || (parentNode = parentNode2.getParentNode()) == null || !Action.isActionNode(parentNode)) {
            return null;
        }
        return parentNode;
    }

    public Action getAction() {
        return new Action(getServiceNode(), getActionNode());
    }

    public Argument() {
        this.userData = null;
        this.argumentNode = new Node(ELEM_NAME);
        this.serviceNode = null;
    }

    public Argument(Node node) {
        this.userData = null;
        this.argumentNode = new Node(ELEM_NAME);
        this.serviceNode = node;
    }

    public Argument(Node node, Node node2) {
        this.userData = null;
        this.serviceNode = node;
        this.argumentNode = node2;
    }

    public Argument(String str, String str2) {
        this();
        setName(str);
        setValue(str2);
    }

    public static boolean isArgumentNode(Node node) {
        return ELEM_NAME.equals(node.getName());
    }

    public void setName(String str) {
        getArgumentNode().setNode("name", str);
    }

    public String getName() {
        return getArgumentNode().getNodeValue("name");
    }

    public void setDirection(String str) {
        getArgumentNode().setNode("direction", str);
    }

    public String getDirection() {
        return getArgumentNode().getNodeValue("direction");
    }

    public boolean isInDirection() {
        String direction = getDirection();
        if (direction == null) {
            return false;
        }
        return direction.equalsIgnoreCase("in");
    }

    public boolean isOutDirection() {
        return !isInDirection();
    }

    public void setRelatedStateVariableName(String str) {
        getArgumentNode().setNode(RELATED_STATE_VARIABLE, str);
    }

    public String getRelatedStateVariableName() {
        return getArgumentNode().getNodeValue(RELATED_STATE_VARIABLE);
    }

    public StateVariable getRelatedStateVariable() {
        Service service = getService();
        if (service == null) {
            return null;
        }
        return service.getStateVariable(getRelatedStateVariableName());
    }

    private ArgumentData getArgumentData() {
        Node argumentNode2 = getArgumentNode();
        ArgumentData argumentData = (ArgumentData) argumentNode2.getUserData();
        if (argumentData != null) {
            return argumentData;
        }
        ArgumentData argumentData2 = new ArgumentData();
        argumentNode2.setUserData(argumentData2);
        argumentData2.setNode(argumentNode2);
        return argumentData2;
    }

    public void setValue(String str) {
        getArgumentData().setValue(str);
    }

    public void setValue(int i) {
        setValue(Integer.toString(i));
    }

    public String getValue() {
        return getArgumentData().getValue();
    }

    public int getIntegerValue() {
        try {
            return Integer.parseInt(getValue());
        } catch (Exception unused) {
            return 0;
        }
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}
