package org.cybergarage.upnp;

import java.util.Vector;

public class ActionList extends Vector {
    public static final String ELEM_NAME = "actionList";

    public Action getAction(int i) {
        return (Action) get(i);
    }
}
