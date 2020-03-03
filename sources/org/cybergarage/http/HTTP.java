package org.cybergarage.http;

import com.mi.global.bbs.utils.ConnectionHelper;
import java.net.URL;

public class HTTP {
    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CALLBACK = "CALLBACK";
    public static final String CHARSET = "charset";
    public static final String CHUNKED = "Chunked";
    public static final String CLOSE = "close";
    public static final String CONNECTION = "Connection";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_RANGE_BYTES = "bytes";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final byte CR = 13;
    public static final String CRLF = "\r\n";
    public static final String DATE = "Date";
    public static final int DEFAULT_CHUNK_SIZE = 524288;
    public static final int DEFAULT_PORT = 80;
    public static final int DEFAULT_TIMEOUT = 30;
    public static final String EXT = "EXT";
    public static final String GET = "GET";
    public static final String HEAD = "HEAD";
    public static final String HEADER_LINE_DELIM = " :";
    public static final String HOST = "HOST";
    public static final String KEEP_ALIVE = "Keep-Alive";
    public static final byte LF = 10;
    public static final String LOCATION = "Location";
    public static final String MAN = "MAN";
    public static final String MAX_AGE = "max-age";
    public static final String MX = "MX";
    public static final String MYNAME = "MYNAME";
    public static final String M_SEARCH = "M-SEARCH";
    public static final String NOTIFY = "NOTIFY";
    public static final String NO_CACHE = "no-cache";
    public static final String NT = "NT";
    public static final String NTS = "NTS";
    public static final String POST = "POST";
    public static final String RANGE = "Range";
    public static final String REQEST_LINE_DELIM = " ";
    public static final String SEQ = "SEQ";
    public static final String SERVER = "Server";
    public static final String SID = "SID";
    public static final String SOAP_ACTION = "SOAPACTION";
    public static final String ST = "ST";
    public static final String STATUS_LINE_DELIM = " ";
    public static final String SUBSCRIBE = "SUBSCRIBE";
    public static final String TAB = "\t";
    public static final String TIMEOUT = "TIMEOUT";
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String UNSUBSCRIBE = "UNSUBSCRIBE";
    public static final String USN = "USN";
    public static final String VERSION = "1.1";
    public static final String VERSION_10 = "1.0";
    public static final String VERSION_11 = "1.1";
    private static int chunkSize = 524288;

    public static final boolean isAbsoluteURL(String str) {
        try {
            new URL(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final String getHost(String str) {
        try {
            return new URL(str).getHost();
        } catch (Exception unused) {
            return "";
        }
    }

    public static final int getPort(String str) {
        try {
            int port = new URL(str).getPort();
            if (port <= 0) {
                return 80;
            }
            return port;
        } catch (Exception unused) {
            return 80;
        }
    }

    public static final String getRequestHostURL(String str, int i) {
        return ConnectionHelper.HTTP_PREFIX + str + ":" + i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f A[Catch:{ Exception -> 0x0069 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String toRelativeURL(java.lang.String r4, boolean r5) {
        /*
            boolean r0 = isAbsoluteURL(r4)
            r1 = 0
            if (r0 != 0) goto L_0x0027
            int r5 = r4.length()
            if (r5 <= 0) goto L_0x006a
            char r5 = r4.charAt(r1)
            r0 = 47
            if (r5 == r0) goto L_0x006a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "/"
            r5.append(r0)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            goto L_0x006a
        L_0x0027:
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x006a }
            r0.<init>(r4)     // Catch:{ Exception -> 0x006a }
            java.lang.String r2 = r0.getPath()     // Catch:{ Exception -> 0x006a }
            r4 = 1
            if (r5 != r4) goto L_0x0056
            java.lang.String r5 = r0.getQuery()     // Catch:{ Exception -> 0x0054 }
            java.lang.String r0 = ""
            boolean r0 = r5.equals(r0)     // Catch:{ Exception -> 0x0054 }
            if (r0 != 0) goto L_0x0056
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0054 }
            r0.<init>()     // Catch:{ Exception -> 0x0054 }
            r0.append(r2)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r3 = "?"
            r0.append(r3)     // Catch:{ Exception -> 0x0054 }
            r0.append(r5)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r5 = r0.toString()     // Catch:{ Exception -> 0x0054 }
            goto L_0x0057
        L_0x0054:
            r4 = r2
            goto L_0x006a
        L_0x0056:
            r5 = r2
        L_0x0057:
            java.lang.String r0 = "/"
            boolean r0 = r5.endsWith(r0)     // Catch:{ Exception -> 0x0069 }
            if (r0 == 0) goto L_0x0069
            int r0 = r5.length()     // Catch:{ Exception -> 0x0069 }
            int r0 = r0 - r4
            java.lang.String r4 = r5.substring(r1, r0)     // Catch:{ Exception -> 0x0069 }
            goto L_0x006a
        L_0x0069:
            r4 = r5
        L_0x006a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.http.HTTP.toRelativeURL(java.lang.String, boolean):java.lang.String");
    }

    public static final String toRelativeURL(String str) {
        return toRelativeURL(str, true);
    }

    public static final String getAbsoluteURL(String str, String str2) {
        try {
            URL url = new URL(str);
            return url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + toRelativeURL(str2);
        } catch (Exception unused) {
            return "";
        }
    }

    public static final void setChunkSize(int i) {
        chunkSize = i;
    }

    public static final int getChunkSize() {
        return chunkSize;
    }
}
