package org.cybergarage.upnp.event;

import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.upnp.Service;
import org.cybergarage.upnp.device.NT;

public class SubscriptionRequest extends HTTPRequest {
    private static final String CALLBACK_END_WITH = ">";
    private static final String CALLBACK_START_WITH = "<";

    public SubscriptionRequest() {
        setContentLength(0);
    }

    public SubscriptionRequest(HTTPRequest hTTPRequest) {
        this();
        set(hTTPRequest);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r4 = r4.getRootDevice();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setService(org.cybergarage.upnp.Service r4) {
        /*
            r3 = this;
            java.lang.String r0 = r4.getEventSubURL()
            r1 = 1
            r3.setURI(r0, r1)
            java.lang.String r1 = ""
            org.cybergarage.upnp.Device r2 = r4.getDevice()
            if (r2 == 0) goto L_0x0014
            java.lang.String r1 = r2.getURLBase()
        L_0x0014:
            if (r1 == 0) goto L_0x001c
            int r2 = r1.length()
            if (r2 > 0) goto L_0x0026
        L_0x001c:
            org.cybergarage.upnp.Device r2 = r4.getRootDevice()
            if (r2 == 0) goto L_0x0026
            java.lang.String r1 = r2.getURLBase()
        L_0x0026:
            if (r1 == 0) goto L_0x002e
            int r2 = r1.length()
            if (r2 > 0) goto L_0x0039
        L_0x002e:
            org.cybergarage.upnp.Device r4 = r4.getRootDevice()
            if (r4 == 0) goto L_0x0039
            java.lang.String r4 = r4.getLocation()
            goto L_0x003a
        L_0x0039:
            r4 = r1
        L_0x003a:
            if (r4 == 0) goto L_0x0042
            int r1 = r4.length()
            if (r1 > 0) goto L_0x0049
        L_0x0042:
            boolean r1 = org.cybergarage.http.HTTP.isAbsoluteURL(r0)
            if (r1 == 0) goto L_0x0049
            r4 = r0
        L_0x0049:
            java.lang.String r0 = org.cybergarage.http.HTTP.getHost(r4)
            int r4 = org.cybergarage.http.HTTP.getPort(r4)
            r3.setHost(r0, r4)
            r3.setRequestHost(r0)
            r3.setRequestPort(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.upnp.event.SubscriptionRequest.setService(org.cybergarage.upnp.Service):void");
    }

    public void setSubscribeRequest(Service service, String str, long j) {
        setMethod("SUBSCRIBE");
        setService(service);
        setCallback(str);
        setNT(NT.EVENT);
        setTimeout(j);
    }

    public void setRenewRequest(Service service, String str, long j) {
        setMethod("SUBSCRIBE");
        setService(service);
        setSID(str);
        setTimeout(j);
    }

    public void setUnsubscribeRequest(Service service) {
        setMethod("UNSUBSCRIBE");
        setService(service);
        setSID(service.getSID());
    }

    public void setNT(String str) {
        setHeader(HTTP.NT, str);
    }

    public String getNT() {
        return getHeaderValue(HTTP.NT);
    }

    public boolean hasNT() {
        String nt = getNT();
        return nt != null && nt.length() > 0;
    }

    public void setCallback(String str) {
        setStringHeader(HTTP.CALLBACK, str, "<", ">");
    }

    public String getCallback() {
        return getStringHeaderValue(HTTP.CALLBACK, "<", ">");
    }

    public boolean hasCallback() {
        String callback = getCallback();
        return callback != null && callback.length() > 0;
    }

    public void setSID(String str) {
        setHeader(HTTP.SID, Subscription.toSIDHeaderString(str));
    }

    public String getSID() {
        String sid = Subscription.getSID(getHeaderValue(HTTP.SID));
        return sid == null ? "" : sid;
    }

    public boolean hasSID() {
        String sid = getSID();
        return sid != null && sid.length() > 0;
    }

    public final void setTimeout(long j) {
        setHeader("TIMEOUT", Subscription.toTimeoutHeaderString(j));
    }

    public long getTimeout() {
        return Subscription.getTimeout(getHeaderValue("TIMEOUT"));
    }

    public void post(SubscriptionResponse subscriptionResponse) {
        super.post(subscriptionResponse);
    }

    public SubscriptionResponse post() {
        return new SubscriptionResponse(post(getRequestHost(), getRequestPort()));
    }
}
