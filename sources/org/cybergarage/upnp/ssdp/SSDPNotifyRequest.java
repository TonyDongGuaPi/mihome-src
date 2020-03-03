package org.cybergarage.upnp.ssdp;

import org.cybergarage.http.HTTP;

public class SSDPNotifyRequest extends SSDPRequest {
    public SSDPNotifyRequest() {
        setMethod(HTTP.NOTIFY);
        setURI("*");
    }
}
