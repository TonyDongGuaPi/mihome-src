package org.cybergarage.xml;

import java.util.Vector;

public class AttributeList extends Vector {
    public Attribute getAttribute(int i) {
        return (Attribute) get(i);
    }

    public Attribute getAttribute(String str) {
        if (str == null) {
            return null;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            Attribute attribute = getAttribute(i);
            if (str.compareTo(attribute.getName()) == 0) {
                return attribute;
            }
        }
        return null;
    }
}
