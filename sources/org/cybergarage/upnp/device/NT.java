package org.cybergarage.upnp.device;

public class NT {
    public static final String EVENT = "upnp:event";
    public static final String ROOTDEVICE = "upnp:rootdevice";

    public static final boolean isRootDevice(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("upnp:rootdevice");
    }
}
