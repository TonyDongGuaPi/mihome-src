package org.cybergarage.upnp.ssdp;

import org.cybergarage.http.HTTP;
import org.cybergarage.upnp.UPnP;

public class SSDPSearchResponse extends SSDPResponse {
    public SSDPSearchResponse() {
        setStatusCode(200);
        setCacheControl(1800);
        setHeader("Server", UPnP.getServerName());
        setHeader(HTTP.EXT, "");
    }
}
