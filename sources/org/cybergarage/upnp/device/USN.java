package org.cybergarage.upnp.device;

public class USN {
    public static final String ROOTDEVICE = "upnp:rootdevice";

    public static final boolean isRootDevice(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith("upnp:rootdevice");
    }

    public static final String getUDN(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf("::");
        if (indexOf < 0) {
            return str.trim();
        }
        return new String(str.getBytes(), 0, indexOf).trim();
    }
}
