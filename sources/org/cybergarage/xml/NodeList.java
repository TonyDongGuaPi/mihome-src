package org.cybergarage.xml;

import java.util.Vector;

public class NodeList extends Vector {
    public Node getNode(int i) {
        return (Node) get(i);
    }

    public Node getNode(String str) {
        if (str == null) {
            return null;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            Node node = getNode(i);
            if (str.compareTo(node.getName()) == 0) {
                return node;
            }
        }
        return null;
    }

    public Node getEndsWith(String str) {
        if (str == null) {
            return null;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            Node node = getNode(i);
            String name = node.getName();
            if (name != null && name.endsWith(str)) {
                return node;
            }
        }
        return null;
    }
}
