package org.cybergarage.upnp.ssdp;

import org.cybergarage.http.HTTP;
import org.cybergarage.net.HostInterface;

public class SSDPSearchRequest extends SSDPRequest {
    public SSDPSearchRequest(String str, int i) {
        setMethod(HTTP.M_SEARCH);
        setURI("*");
        setHeader(HTTP.ST, str);
        setHeader(HTTP.MX, Integer.toString(i));
        setHeader(HTTP.MAN, "\"ssdp:discover\"");
    }

    public SSDPSearchRequest(String str) {
        this(str, 3);
    }

    public SSDPSearchRequest() {
        this("upnp:rootdevice");
    }

    public void setLocalAddress(String str) {
        String str2 = SSDP.ADDRESS;
        if (HostInterface.isIPv6Address(str)) {
            str2 = SSDP.getIPv6Address();
        }
        setHost(str2, 1900);
    }
}
