package com.alipay.zoloz.android.phone.mrpc.core;

import java.util.ArrayList;
import org.apache.http.HeaderElement;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public final class Headers {
    public static final String ACCEPT_RANGES = "accept-ranges";
    public static final String CACHE_CONTROL = "cache-control";
    public static final int CONN_CLOSE = 1;
    public static final String CONN_DIRECTIVE = "connection";
    public static final int CONN_KEEP_ALIVE = 2;
    public static final String CONTENT_DISPOSITION = "content-disposition";
    public static final String CONTENT_ENCODING = "content-encoding";
    public static final String CONTENT_LEN = "content-length";
    public static final String CONTENT_TYPE = "content-type";
    public static final String ETAG = "etag";
    public static final String EXPIRES = "expires";
    public static final String LAST_MODIFIED = "last-modified";
    public static final String LOCATION = "location";
    public static final int NO_CONN_TYPE = 0;
    public static final long NO_CONTENT_LENGTH = -1;
    public static final long NO_TRANSFER_ENCODING = 0;
    public static final String PRAGMA = "pragma";
    public static final String PROXY_AUTHENTICATE = "proxy-authenticate";
    public static final String PROXY_CONNECTION = "proxy-connection";
    public static final String REFRESH = "refresh";
    public static final String SET_COOKIE = "set-cookie";
    public static final String TRANSFER_ENCODING = "transfer-encoding";
    public static final String WWW_AUTHENTICATE = "www-authenticate";
    public static final String X_PERMITTED_CROSS_DOMAIN_POLICIES = "x-permitted-cross-domain-policies";
    private static final String[] f = {"transfer-encoding", "content-length", "content-type", "content-encoding", "connection", "location", "proxy-connection", "www-authenticate", "proxy-authenticate", "content-disposition", "accept-ranges", "expires", "cache-control", "last-modified", "etag", "set-cookie", "pragma", "refresh", "x-permitted-cross-domain-policies"};

    /* renamed from: a  reason: collision with root package name */
    private long f1182a = 0;
    private long b = -1;
    private int c = 0;
    private ArrayList<String> d = new ArrayList<>(2);
    private String[] e = new String[19];
    private ArrayList<String> g = new ArrayList<>(4);
    private ArrayList<String> h = new ArrayList<>(4);

    public interface HeaderCallback {
        void header(String str, String str2);
    }

    public void parseHeader(CharArrayBuffer charArrayBuffer) {
        int lowercaseIndexOf = CharArrayBuffers.setLowercaseIndexOf(charArrayBuffer, 58);
        if (lowercaseIndexOf != -1) {
            String substringTrimmed = charArrayBuffer.substringTrimmed(0, lowercaseIndexOf);
            if (substringTrimmed.length() != 0) {
                int i = lowercaseIndexOf + 1;
                String substringTrimmed2 = charArrayBuffer.substringTrimmed(i, charArrayBuffer.length());
                switch (substringTrimmed.hashCode()) {
                    case -1345594014:
                        if (substringTrimmed.equals("x-permitted-cross-domain-policies")) {
                            this.e[18] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -1309235404:
                        if (substringTrimmed.equals("expires")) {
                            this.e[11] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -1267267485:
                        if (substringTrimmed.equals("content-disposition")) {
                            this.e[9] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -1132779846:
                        if (substringTrimmed.equals("content-length")) {
                            this.e[1] = substringTrimmed2;
                            try {
                                this.b = Long.parseLong(substringTrimmed2);
                                return;
                            } catch (NumberFormatException unused) {
                                return;
                            }
                        } else {
                            return;
                        }
                    case -980228804:
                        if (substringTrimmed.equals("pragma")) {
                            this.e[16] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -775651618:
                        if (substringTrimmed.equals("connection")) {
                            this.e[4] = substringTrimmed2;
                            a(charArrayBuffer, i);
                            return;
                        }
                        return;
                    case -301767724:
                        if (substringTrimmed.equals("proxy-authenticate")) {
                            this.e[8] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -243037365:
                        if (substringTrimmed.equals("www-authenticate")) {
                            this.e[7] = substringTrimmed2;
                            return;
                        }
                        return;
                    case -208775662:
                        if (!substringTrimmed.equals("cache-control")) {
                            return;
                        }
                        if (this.e[12] == null || this.e[12].length() <= 0) {
                            this.e[12] = substringTrimmed2;
                            return;
                        }
                        StringBuilder sb = new StringBuilder();
                        String[] strArr = this.e;
                        sb.append(strArr[12]);
                        sb.append(',');
                        sb.append(substringTrimmed2);
                        strArr[12] = sb.toString();
                        return;
                    case 3123477:
                        if (substringTrimmed.equals("etag")) {
                            this.e[14] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 150043680:
                        if (substringTrimmed.equals("last-modified")) {
                            this.e[13] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 285929373:
                        if (substringTrimmed.equals("proxy-connection")) {
                            this.e[6] = substringTrimmed2;
                            a(charArrayBuffer, i);
                            return;
                        }
                        return;
                    case 785670158:
                        if (substringTrimmed.equals("content-type")) {
                            this.e[2] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 1085444827:
                        if (substringTrimmed.equals("refresh")) {
                            this.e[17] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 1237214767:
                        if (substringTrimmed.equals("set-cookie")) {
                            this.e[15] = substringTrimmed2;
                            this.d.add(substringTrimmed2);
                            return;
                        }
                        return;
                    case 1274458357:
                        if (substringTrimmed.equals("transfer-encoding")) {
                            this.e[0] = substringTrimmed2;
                            HeaderElement[] parseElements = BasicHeaderValueParser.DEFAULT.parseElements(charArrayBuffer, new ParserCursor(i, charArrayBuffer.length()));
                            int length = parseElements.length;
                            if ("identity".equalsIgnoreCase(substringTrimmed2)) {
                                this.f1182a = -1;
                                return;
                            } else if (length <= 0 || !"chunked".equalsIgnoreCase(parseElements[length - 1].getName())) {
                                this.f1182a = -1;
                                return;
                            } else {
                                this.f1182a = -2;
                                return;
                            }
                        } else {
                            return;
                        }
                    case 1397189435:
                        if (substringTrimmed.equals("accept-ranges")) {
                            this.e[10] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 1901043637:
                        if (substringTrimmed.equals("location")) {
                            this.e[5] = substringTrimmed2;
                            return;
                        }
                        return;
                    case 2095084583:
                        if (substringTrimmed.equals("content-encoding")) {
                            this.e[3] = substringTrimmed2;
                            return;
                        }
                        return;
                    default:
                        this.g.add(substringTrimmed);
                        this.h.add(substringTrimmed2);
                        return;
                }
            }
        }
    }

    public long getTransferEncoding() {
        return this.f1182a;
    }

    public long getContentLength() {
        return this.b;
    }

    public int getConnectionType() {
        return this.c;
    }

    public String getContentType() {
        return this.e[2];
    }

    public String getContentEncoding() {
        return this.e[3];
    }

    public String getLocation() {
        return this.e[5];
    }

    public String getWwwAuthenticate() {
        return this.e[7];
    }

    public String getProxyAuthenticate() {
        return this.e[8];
    }

    public String getContentDisposition() {
        return this.e[9];
    }

    public String getAcceptRanges() {
        return this.e[10];
    }

    public String getExpires() {
        return this.e[11];
    }

    public String getCacheControl() {
        return this.e[12];
    }

    public String getLastModified() {
        return this.e[13];
    }

    public String getEtag() {
        return this.e[14];
    }

    public ArrayList<String> getSetCookie() {
        return this.d;
    }

    public String getPragma() {
        return this.e[16];
    }

    public String getRefresh() {
        return this.e[17];
    }

    public String getXPermittedCrossDomainPolicies() {
        return this.e[18];
    }

    public void setContentLength(long j) {
        this.b = j;
    }

    public void setContentType(String str) {
        this.e[2] = str;
    }

    public void setContentEncoding(String str) {
        this.e[3] = str;
    }

    public void setLocation(String str) {
        this.e[5] = str;
    }

    public void setWwwAuthenticate(String str) {
        this.e[7] = str;
    }

    public void setProxyAuthenticate(String str) {
        this.e[8] = str;
    }

    public void setContentDisposition(String str) {
        this.e[9] = str;
    }

    public void setAcceptRanges(String str) {
        this.e[10] = str;
    }

    public void setExpires(String str) {
        this.e[11] = str;
    }

    public void setCacheControl(String str) {
        this.e[12] = str;
    }

    public void setLastModified(String str) {
        this.e[13] = str;
    }

    public void setEtag(String str) {
        this.e[14] = str;
    }

    public void setXPermittedCrossDomainPolicies(String str) {
        this.e[18] = str;
    }

    public void getHeaders(HeaderCallback headerCallback) {
        for (int i = 0; i < 19; i++) {
            String str = this.e[i];
            if (str != null) {
                headerCallback.header(f[i], str);
            }
        }
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            headerCallback.header(this.g.get(i2), this.h.get(i2));
        }
    }

    private void a(CharArrayBuffer charArrayBuffer, int i) {
        if (CharArrayBuffers.containsIgnoreCaseTrimmed(charArrayBuffer, i, "Close")) {
            this.c = 1;
        } else if (CharArrayBuffers.containsIgnoreCaseTrimmed(charArrayBuffer, i, "Keep-Alive")) {
            this.c = 2;
        }
    }
}
