package org.cybergarage.upnp;

import java.util.Iterator;
import java.util.Vector;

public class AllowedValueList extends Vector {
    public static final String ELEM_NAME = "allowedValueList";

    public AllowedValueList() {
    }

    public AllowedValueList(String[] strArr) {
        for (String allowedValue : strArr) {
            add(new AllowedValue(allowedValue));
        }
    }

    public AllowedValue getAllowedValue(int i) {
        return (AllowedValue) get(i);
    }

    public boolean isAllowed(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((AllowedValue) it.next()).getValue().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
