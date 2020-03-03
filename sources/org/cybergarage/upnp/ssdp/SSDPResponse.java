package org.cybergarage.upnp.ssdp;

import java.io.InputStream;
import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPResponse;

public class SSDPResponse extends HTTPResponse {
    public SSDPResponse() {
        setVersion("1.1");
    }

    public SSDPResponse(InputStream inputStream) {
        super(inputStream);
    }

    public void setST(String str) {
        setHeader(HTTP.ST, str);
    }

    public String getST() {
        return getHeaderValue(HTTP.ST);
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

    public void setMYNAME(String str) {
        setHeader(HTTP.MYNAME, str);
    }

    public String getMYNAME() {
        return getHeaderValue(HTTP.MYNAME);
    }

    public void setLeaseTime(int i) {
        setHeader("Cache-Control", "max-age=" + Integer.toString(i));
    }

    public int getLeaseTime() {
        return SSDP.getLeaseTime(getHeaderValue("Cache-Control"));
    }

    public String getHeader() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getStatusLineString());
        stringBuffer.append(getHeaderString());
        stringBuffer.append("\r\n");
        return stringBuffer.toString();
    }
}
