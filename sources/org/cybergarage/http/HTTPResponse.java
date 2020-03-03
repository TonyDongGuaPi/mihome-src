package org.cybergarage.http;

import java.io.InputStream;

public class HTTPResponse extends HTTPPacket {
    private int statusCode;

    public HTTPResponse() {
        this.statusCode = 0;
        setVersion("1.1");
        setContentType(HTML.CONTENT_TYPE);
        setServer(HTTPServer.getName());
        setContent("");
    }

    public HTTPResponse(HTTPResponse hTTPResponse) {
        this.statusCode = 0;
        set((HTTPPacket) hTTPResponse);
    }

    public HTTPResponse(InputStream inputStream) {
        super(inputStream);
        this.statusCode = 0;
    }

    public HTTPResponse(HTTPSocket hTTPSocket) {
        this(hTTPSocket.getInputStream());
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public int getStatusCode() {
        if (this.statusCode != 0) {
            return this.statusCode;
        }
        return new HTTPStatus(getFirstLine()).getStatusCode();
    }

    public boolean isSuccessful() {
        return HTTPStatus.isSuccessful(getStatusCode());
    }

    public String getStatusLineString() {
        return "HTTP/" + getVersion() + " " + getStatusCode() + " " + HTTPStatus.code2String(this.statusCode) + "\r\n";
    }

    public String getHeader() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getStatusLineString());
        stringBuffer.append(getHeaderString());
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getStatusLineString());
        stringBuffer.append(getHeaderString());
        stringBuffer.append("\r\n");
        stringBuffer.append(getContentString());
        return stringBuffer.toString();
    }

    public void print() {
        System.out.println(toString());
    }
}
