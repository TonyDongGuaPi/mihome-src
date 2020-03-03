package org.cybergarage.upnp.ssdp;

import java.io.InputStream;
import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPRequest;

public class SSDPRequest extends HTTPRequest {
    public SSDPRequest() {
        setVersion("1.1");
    }

    public SSDPRequest(InputStream inputStream) {
        super(inputStream);
    }

    public void setNT(String str) {
        setHeader(HTTP.NT, str);
    }

    public String getNT() {
        return getHeaderValue(HTTP.NT);
    }

    public void setNTS(String str) {
        setHeader(HTTP.NTS, str);
    }

    public String getNTS() {
        return getHeaderValue(HTTP.NTS);
    }

    public void setLocation(String str) {
        setHeader("Location", str);
    }

    public String getLocation() {
        return getHeaderValue("Location");
    }

    public void setUSN(String str) {
        setHeader(HTTP.USN, str);
    }

    public String getUSN() {
        return getHeaderValue(HTTP.USN);
    }

    public void setLeaseTime(int i) {
        setHeader("Cache-Control", "max-age=" + Integer.toString(i));
    }

    public int getLeaseTime() {
        return SSDP.getLeaseTime(getHeaderValue("Cache-Control"));
    }
}
