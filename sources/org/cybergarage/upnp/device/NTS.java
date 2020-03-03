package org.cybergarage.upnp.device;

public class NTS {
    public static final String ALIVE = "ssdp:alive";
    public static final String BYEBYE = "ssdp:byebye";
    public static final String PROPCHANGE = "upnp:propchange";

    public static final boolean isAlive(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith(ALIVE);
    }

    public static final boolean isByeBye(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith(BYEBYE);
    }
}
