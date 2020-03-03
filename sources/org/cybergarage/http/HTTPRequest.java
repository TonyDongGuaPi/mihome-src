package org.cybergarage.http;

import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class HTTPRequest extends HTTPPacket {
    private HTTPSocket httpSocket;
    private String method;
    private Socket postSocket;
    private String requestHost;
    private int requestPort;
    private String uri;

    public HTTPRequest() {
        this.method = null;
        this.uri = null;
        this.requestHost = "";
        this.requestPort = -1;
        this.httpSocket = null;
        this.postSocket = null;
        setVersion("1.1");
    }

    public HTTPRequest(InputStream inputStream) {
        super(inputStream);
        this.method = null;
        this.uri = null;
        this.requestHost = "";
        this.requestPort = -1;
        this.httpSocket = null;
        this.postSocket = null;
    }

    public HTTPRequest(HTTPSocket hTTPSocket) {
        this(hTTPSocket.getInputStream());
        setSocket(hTTPSocket);
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public String getMethod() {
        if (this.method != null) {
            return this.method;
        }
        return getFirstLineToken(0);
    }

    public boolean isMethod(String str) {
        String method2 = getMethod();
        if (method2 == null) {
            return false;
        }
        return method2.equalsIgnoreCase(str);
    }

    public boolean isGetRequest() {
        return isMethod("GET");
    }

    public boolean isPostRequest() {
        return isMethod("POST");
    }

    public boolean isHeadRequest() {
        return isMethod("HEAD");
    }

    public boolean isSubscribeRequest() {
        return isMethod("SUBSCRIBE");
    }

    public boolean isUnsubscribeRequest() {
        return isMethod("UNSUBSCRIBE");
    }

    public boolean isNotifyRequest() {
        return isMethod(HTTP.NOTIFY);
    }

    public void setURI(String str, boolean z) {
        this.uri = str;
        if (z) {
            this.uri = HTTP.toRelativeURL(this.uri);
        }
    }

    public void setURI(String str) {
        setURI(str, false);
    }

    public String getURI() {
        if (this.uri != null) {
            return this.uri;
        }
        return getFirstLineToken(1);
    }

    public ParameterList getParameterList() {
        int indexOf;
        int i;
        ParameterList parameterList = new ParameterList();
        String uri2 = getURI();
        if (uri2 == null || (indexOf = uri2.indexOf(63)) < 0) {
            return parameterList;
        }
        while (indexOf > 0) {
            int i2 = indexOf + 1;
            int indexOf2 = uri2.indexOf(61, i2);
            String substring = uri2.substring(i2, indexOf2);
            int i3 = indexOf2 + 1;
            int indexOf3 = uri2.indexOf(38, i3);
            if (indexOf3 > 0) {
                i = indexOf3;
            } else {
                i = uri2.length();
            }
            parameterList.add(new Parameter(substring, uri2.substring(i3, i)));
            indexOf = indexOf3;
        }
        return parameterList;
    }

    public String getParameterValue(String str) {
        return getParameterList().getValue(str);
    }

    public boolean isSOAPAction() {
        return hasHeader(HTTP.SOAP_ACTION);
    }

    public void setRequestHost(String str) {
        this.requestHost = str;
    }

    public String getRequestHost() {
        return this.requestHost;
    }

    public void setRequestPort(int i) {
        this.requestPort = i;
    }

    public int getRequestPort() {
        return this.requestPort;
    }

    public void setSocket(HTTPSocket hTTPSocket) {
        this.httpSocket = hTTPSocket;
    }

    public HTTPSocket getSocket() {
        return this.httpSocket;
    }

    public String getLocalAddress() {
        return getSocket().getLocalAddress();
    }

    public int getLocalPort() {
        return getSocket().getLocalPort();
    }

    public boolean parseRequestLine(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        setMethod(stringTokenizer.nextToken());
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        setURI(stringTokenizer.nextToken());
        if (!stringTokenizer.hasMoreTokens()) {
            return false;
        }
        setVersion(stringTokenizer.nextToken());
        return true;
    }

    public String getHTTPVersion() {
        if (hasFirstLine()) {
            return getFirstLineToken(2);
        }
        return "HTTP/" + super.getVersion();
    }

    public String getFirstLineString() {
        return getMethod() + " " + getURI() + " " + getHTTPVersion() + "\r\n";
    }

    public String getHeader() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getFirstLineString());
        stringBuffer.append(getHeaderString());
        return stringBuffer.toString();
    }

    public boolean isKeepAlive() {
        if (isCloseConnection()) {
            return false;
        }
        if (isKeepAliveConnection()) {
            return true;
        }
        return !(getHTTPVersion().indexOf("1.0") > 0);
    }

    public boolean read() {
        return super.read(getSocket());
    }

    public boolean post(HTTPResponse hTTPResponse) {
        long j;
        HTTPSocket socket = getSocket();
        long contentLength = hTTPResponse.getContentLength();
        long j2 = 0;
        if (hasContentRange()) {
            long contentRangeFirstPosition = getContentRangeFirstPosition();
            long contentRangeLastPosition = getContentRangeLastPosition();
            long j3 = contentRangeLastPosition <= 0 ? contentLength - 1 : contentRangeLastPosition;
            if (contentRangeFirstPosition > contentLength || j3 > contentLength) {
                return returnResponse(416);
            }
            j2 = contentRangeFirstPosition;
            hTTPResponse.setContentRange(j2, j3, contentLength);
            hTTPResponse.setStatusCode(206);
            j = (j3 - contentRangeFirstPosition) + 1;
        } else {
            j = contentLength;
        }
        return socket.post(hTTPResponse, j2, j, isHeadRequest());
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ca A[SYNTHETIC, Splitter:B:55:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e3 A[SYNTHETIC, Splitter:B:70:0x00e3] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00f8 A[SYNTHETIC, Splitter:B:86:0x00f8] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x00c2=Splitter:B:52:0x00c2, B:67:0x00db=Splitter:B:67:0x00db} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.cybergarage.http.HTTPResponse post(java.lang.String r11, int r12, boolean r13) {
        /*
            r10 = this;
            org.cybergarage.http.HTTPResponse r0 = new org.cybergarage.http.HTTPResponse
            r0.<init>()
            r10.setHost(r11)
            r1 = 1
            if (r13 != r1) goto L_0x000e
            java.lang.String r2 = "Keep-Alive"
            goto L_0x0010
        L_0x000e:
            java.lang.String r2 = "close"
        L_0x0010:
            r10.setConnection(r2)
            boolean r2 = r10.isHeadRequest()
            r3 = 500(0x1f4, float:7.0E-43)
            r4 = 0
            java.net.Socket r5 = r10.postSocket     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            if (r5 != 0) goto L_0x0032
            java.net.Socket r5 = new java.net.Socket     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            r5.<init>()     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            r10.postSocket = r5     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            java.net.Socket r5 = r10.postSocket     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            java.net.InetSocketAddress r6 = new java.net.InetSocketAddress     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            r6.<init>(r11, r12)     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            r11 = 80000(0x13880, float:1.12104E-40)
            r5.connect(r6, r11)     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
        L_0x0032:
            java.net.Socket r11 = r10.postSocket     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            java.io.OutputStream r11 = r11.getOutputStream()     // Catch:{ SocketException -> 0x00d7, IOException -> 0x00be, all -> 0x00b9 }
            java.io.PrintStream r12 = new java.io.PrintStream     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            r12.<init>(r11)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.lang.String r5 = r10.getHeader()     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            r12.print(r5)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.lang.String r5 = "\r\n"
            r12.print(r5)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            boolean r5 = r10.isChunked()     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.lang.String r6 = r10.getContentString()     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            r7 = 0
            if (r6 == 0) goto L_0x0058
            int r7 = r6.length()     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
        L_0x0058:
            if (r7 <= 0) goto L_0x0073
            if (r5 != r1) goto L_0x0069
            long r7 = (long) r7     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.lang.String r7 = java.lang.Long.toHexString(r7)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            r12.print(r7)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.lang.String r7 = "\r\n"
            r12.print(r7)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
        L_0x0069:
            r12.print(r6)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            if (r5 != r1) goto L_0x0073
            java.lang.String r6 = "\r\n"
            r12.print(r6)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
        L_0x0073:
            if (r5 != r1) goto L_0x007f
            java.lang.String r1 = "0"
            r12.print(r1)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.lang.String r1 = "\r\n"
            r12.print(r1)     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
        L_0x007f:
            r12.flush()     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.net.Socket r12 = r10.postSocket     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            java.io.InputStream r12 = r12.getInputStream()     // Catch:{ SocketException -> 0x00b5, IOException -> 0x00b1, all -> 0x00ad }
            r0.set(r12, r2)     // Catch:{ SocketException -> 0x00a7, IOException -> 0x00a1, all -> 0x009b }
            if (r13 != 0) goto L_0x00f4
            r12.close()     // Catch:{ Exception -> 0x0090 }
        L_0x0090:
            if (r12 == 0) goto L_0x0095
            r11.close()     // Catch:{ Exception -> 0x0095 }
        L_0x0095:
            if (r11 == 0) goto L_0x00f2
            java.net.Socket r11 = r10.postSocket     // Catch:{ Exception -> 0x00f2 }
            goto L_0x00ef
        L_0x009b:
            r0 = move-exception
            r1 = r11
            r11 = r12
            r12 = r0
            goto L_0x00f6
        L_0x00a1:
            r1 = move-exception
            r9 = r1
            r1 = r11
            r11 = r12
            r12 = r9
            goto L_0x00c2
        L_0x00a7:
            r1 = move-exception
            r9 = r1
            r1 = r11
            r11 = r12
            r12 = r9
            goto L_0x00db
        L_0x00ad:
            r12 = move-exception
            r1 = r11
            r11 = r4
            goto L_0x00f6
        L_0x00b1:
            r12 = move-exception
            r1 = r11
            r11 = r4
            goto L_0x00c2
        L_0x00b5:
            r12 = move-exception
            r1 = r11
            r11 = r4
            goto L_0x00db
        L_0x00b9:
            r11 = move-exception
            r12 = r11
            r11 = r4
            r1 = r11
            goto L_0x00f6
        L_0x00be:
            r11 = move-exception
            r12 = r11
            r11 = r4
            r1 = r11
        L_0x00c2:
            r0.setStatusCode(r3)     // Catch:{ all -> 0x00f5 }
            org.cybergarage.util.Debug.warning((java.lang.Exception) r12)     // Catch:{ all -> 0x00f5 }
            if (r13 != 0) goto L_0x00f4
            r11.close()     // Catch:{ Exception -> 0x00cd }
        L_0x00cd:
            if (r11 == 0) goto L_0x00d2
            r1.close()     // Catch:{ Exception -> 0x00d2 }
        L_0x00d2:
            if (r1 == 0) goto L_0x00f2
            java.net.Socket r11 = r10.postSocket     // Catch:{ Exception -> 0x00f2 }
            goto L_0x00ef
        L_0x00d7:
            r11 = move-exception
            r12 = r11
            r11 = r4
            r1 = r11
        L_0x00db:
            r0.setStatusCode(r3)     // Catch:{ all -> 0x00f5 }
            org.cybergarage.util.Debug.warning((java.lang.Exception) r12)     // Catch:{ all -> 0x00f5 }
            if (r13 != 0) goto L_0x00f4
            r11.close()     // Catch:{ Exception -> 0x00e6 }
        L_0x00e6:
            if (r11 == 0) goto L_0x00eb
            r1.close()     // Catch:{ Exception -> 0x00eb }
        L_0x00eb:
            if (r1 == 0) goto L_0x00f2
            java.net.Socket r11 = r10.postSocket     // Catch:{ Exception -> 0x00f2 }
        L_0x00ef:
            r11.close()     // Catch:{ Exception -> 0x00f2 }
        L_0x00f2:
            r10.postSocket = r4
        L_0x00f4:
            return r0
        L_0x00f5:
            r12 = move-exception
        L_0x00f6:
            if (r13 != 0) goto L_0x0109
            r11.close()     // Catch:{ Exception -> 0x00fb }
        L_0x00fb:
            if (r11 == 0) goto L_0x0100
            r1.close()     // Catch:{ Exception -> 0x0100 }
        L_0x0100:
            if (r1 == 0) goto L_0x0107
            java.net.Socket r11 = r10.postSocket     // Catch:{ Exception -> 0x0107 }
            r11.close()     // Catch:{ Exception -> 0x0107 }
        L_0x0107:
            r10.postSocket = r4
        L_0x0109:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.http.HTTPRequest.post(java.lang.String, int, boolean):org.cybergarage.http.HTTPResponse");
    }

    public HTTPResponse post(String str, int i) {
        return post(str, i, false);
    }

    public void set(HTTPRequest hTTPRequest) {
        set((HTTPPacket) hTTPRequest);
        setSocket(hTTPRequest.getSocket());
    }

    public boolean returnResponse(int i) {
        HTTPResponse hTTPResponse = new HTTPResponse();
        hTTPResponse.setStatusCode(i);
        hTTPResponse.setContentLength(0);
        return post(hTTPResponse);
    }

    public boolean returnOK() {
        return returnResponse(200);
    }

    public boolean returnBadRequest() {
        return returnResponse(400);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getHeader());
        stringBuffer.append("\r\n");
        stringBuffer.append(getContentString());
        return stringBuffer.toString();
    }

    public void print() {
        System.out.println(toString());
    }
}
