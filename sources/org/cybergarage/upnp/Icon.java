package org.cybergarage.upnp;

import org.cybergarage.xml.Node;

public class Icon {
    private static final String DEPTH = "depth";
    public static final String ELEM_NAME = "icon";
    private static final String HEIGHT = "height";
    private static final String MIME_TYPE = "mimeType";
    private static final String URL = "url";
    private static final String WIDTH = "width";
    private Node iconNode;
    private Object userData = null;

    public Node getIconNode() {
        return this.iconNode;
    }

    public Icon(Node node) {
        this.iconNode = node;
    }

    public static boolean isIconNode(Node node) {
        return "icon".equals(node.getName());
    }

    public void setMimeType(String str) {
        getIconNode().setNode(MIME_TYPE, str);
    }

    public String getMimeType() {
        return getIconNode().getNodeValue(MIME_TYPE);
    }

    public void setWidth(String str) {
        getIconNode().setNode("width", str);
    }

    public void setWidth(int i) {
        try {
            setWidth(Integer.toString(i));
        } catch (Exception unused) {
        }
    }

    public int getWidth() {
        try {
            return Integer.parseInt(getIconNode().getNodeValue("width"));
        } catch (Exception unused) {
            return 0;
        }
    }

    public void setHeight(String str) {
        getIconNode().setNode("height", str);
    }

    public void setHeight(int i) {
        try {
            setHeight(Integer.toString(i));
        } catch (Exception unused) {
        }
    }

    public int getHeight() {
        try {
            return Integer.parseInt(getIconNode().getNodeValue("height"));
        } catch (Exception unused) {
            return 0;
        }
    }

    public void setDepth(String str) {
        getIconNode().setNode("depth", str);
    }

    public String getDepth() {
        return getIconNode().getNodeValue("depth");
    }

    public void setURL(String str) {
        getIconNode().setNode("url", str);
    }

    public String getURL() {
        return getIconNode().getNodeValue("url");
    }

    public void setUserData(Object obj) {
        this.userData = obj;
    }

    public Object getUserData() {
        return this.userData;
    }
}
