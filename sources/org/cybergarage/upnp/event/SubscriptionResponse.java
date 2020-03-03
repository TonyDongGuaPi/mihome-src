package org.cybergarage.upnp.event;

import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPResponse;
import org.cybergarage.upnp.UPnP;

public class SubscriptionResponse extends HTTPResponse {
    public SubscriptionResponse() {
        setServer(UPnP.getServerName());
    }

    public SubscriptionResponse(HTTPResponse hTTPResponse) {
        super(hTTPResponse);
    }

    public void setResponse(int i) {
        setStatusCode(i);
        setContentLength(0);
    }

    public void setErrorResponse(int i) {
        setStatusCode(i);
        setContentLength(0);
    }

    public void setSID(String str) {
        setHeader(HTTP.SID, Subscription.toSIDHeaderString(str));
    }

    public String getSID() {
        return Subscription.getSID(getHeaderValue(HTTP.SID));
    }

    public void setTimeout(long j) {
        setHeader("TIMEOUT", Subscription.toTimeoutHeaderString(j));
    }

    public long getTimeout() {
        return Subscription.getTimeout(getHeaderValue("TIMEOUT"));
    }
}
