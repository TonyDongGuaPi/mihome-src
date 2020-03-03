package org.cybergarage.upnp.event;

import org.cybergarage.upnp.UPnP;

public class Subscription {
    public static final String INFINITE_STRING = "infinite";
    public static final int INFINITE_VALUE = -1;
    public static final String SUBSCRIBE_METHOD = "SUBSCRIBE";
    public static final String TIMEOUT_HEADER = "Second-";
    public static final String UNSUBSCRIBE_METHOD = "UNSUBSCRIBE";
    public static final String UUID = "uuid:";
    public static final String XMLNS = "urn:schemas-upnp-org:event-1-0";

    public static final String toTimeoutHeaderString(long j) {
        if (j == -1) {
            return "infinite";
        }
        return TIMEOUT_HEADER + Long.toString(j);
    }

    public static final long getTimeout(String str) {
        try {
            return Long.parseLong(str.substring(str.indexOf(45) + 1, str.length()));
        } catch (Exception unused) {
            return -1;
        }
    }

    public static final String createSID() {
        return UPnP.createUUID();
    }

    public static final String toSIDHeaderString(String str) {
        return UUID + str;
    }

    public static final String getSID(String str) {
        if (str == null) {
            return "";
        }
        if (!str.startsWith(UUID)) {
            return str;
        }
        return str.substring(UUID.length(), str.length());
    }
}
