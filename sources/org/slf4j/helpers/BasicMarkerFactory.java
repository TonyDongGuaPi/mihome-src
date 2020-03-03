package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

public class BasicMarkerFactory implements IMarkerFactory {

    /* renamed from: a  reason: collision with root package name */
    Map f4175a = new HashMap();

    public synchronized Marker a(String str) {
        Marker marker;
        if (str != null) {
            marker = (Marker) this.f4175a.get(str);
            if (marker == null) {
                marker = new BasicMarker(str);
                this.f4175a.put(str, marker);
            }
        } else {
            throw new IllegalArgumentException("Marker name cannot be null");
        }
        return marker;
    }

    public synchronized boolean b(String str) {
        if (str == null) {
            return false;
        }
        return this.f4175a.containsKey(str);
    }

    public boolean c(String str) {
        return (str == null || this.f4175a.remove(str) == null) ? false : true;
    }

    public Marker d(String str) {
        return new BasicMarker(str);
    }
}
