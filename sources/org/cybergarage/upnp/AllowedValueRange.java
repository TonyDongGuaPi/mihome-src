package org.cybergarage.upnp;

import org.cybergarage.xml.Node;

public class AllowedValueRange {
    public static final String ELEM_NAME = "allowedValueRange";
    private static final String MAXIMUM = "maximum";
    private static final String MINIMUM = "minimum";
    private static final String STEP = "step";
    private Node allowedValueRangeNode;

    public Node getAllowedValueRangeNode() {
        return this.allowedValueRangeNode;
    }

    public AllowedValueRange(Node node) {
        this.allowedValueRangeNode = node;
    }

    public AllowedValueRange() {
        this.allowedValueRangeNode = new Node(ELEM_NAME);
    }

    public AllowedValueRange(Number number, Number number2, Number number3) {
        this.allowedValueRangeNode = new Node(ELEM_NAME);
        if (number != null) {
            setMaximum(number.toString());
        }
        if (number2 != null) {
            setMinimum(number2.toString());
        }
        if (number3 != null) {
            setStep(number3.toString());
        }
    }

    public static boolean isAllowedValueRangeNode(Node node) {
        return ELEM_NAME.equals(node.getName());
    }

    public void setMinimum(String str) {
        getAllowedValueRangeNode().setNode(MINIMUM, str);
    }

    public String getMinimum() {
        return getAllowedValueRangeNode().getNodeValue(MINIMUM);
    }

    public void setMaximum(String str) {
        getAllowedValueRangeNode().setNode(MAXIMUM, str);
    }

    public String getMaximum() {
        return getAllowedValueRangeNode().getNodeValue(MAXIMUM);
    }

    public void setStep(String str) {
        getAllowedValueRangeNode().setNode(STEP, str);
    }

    public String getStep() {
        return getAllowedValueRangeNode().getNodeValue(STEP);
    }
}
