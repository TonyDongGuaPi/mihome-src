package org.cybergarage.upnp;

import java.util.Vector;

public class IconList extends Vector {
    public static final String ELEM_NAME = "iconList";

    public Icon getIcon(int i) {
        return (Icon) get(i);
    }
}
