package org.cybergarage.xml;

import com.alipay.sdk.sys.a;

public class XML {
    public static final String CHARSET_UTF8 = "utf-8";
    public static final String CONTENT_TYPE = "text/xml; charset=\"utf-8\"";

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0048 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String escapeXMLChars(java.lang.String r9, boolean r10) {
        /*
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            int r2 = r9.length()
            char[] r3 = new char[r2]
            r4 = 0
            r9.getChars(r4, r2, r3, r4)
            r6 = r0
            r5 = 0
        L_0x0015:
            if (r4 >= r2) goto L_0x004b
            char r7 = r3[r4]
            r8 = 34
            if (r7 == r8) goto L_0x0037
            r8 = 60
            if (r7 == r8) goto L_0x0034
            r8 = 62
            if (r7 == r8) goto L_0x0031
            switch(r7) {
                case 38: goto L_0x002e;
                case 39: goto L_0x0029;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x003b
        L_0x0029:
            if (r10 == 0) goto L_0x0037
            java.lang.String r6 = "&apos;"
            goto L_0x003b
        L_0x002e:
            java.lang.String r6 = "&amp;"
            goto L_0x003b
        L_0x0031:
            java.lang.String r6 = "&gt;"
            goto L_0x003b
        L_0x0034:
            java.lang.String r6 = "&lt;"
            goto L_0x003b
        L_0x0037:
            if (r10 == 0) goto L_0x003b
            java.lang.String r6 = "&quot;"
        L_0x003b:
            if (r6 == 0) goto L_0x0048
            int r7 = r4 - r5
            r1.append(r3, r5, r7)
            r1.append(r6)
            int r5 = r4 + 1
            r6 = r0
        L_0x0048:
            int r4 = r4 + 1
            goto L_0x0015
        L_0x004b:
            if (r5 != 0) goto L_0x004e
            return r9
        L_0x004e:
            int r2 = r2 - r5
            r1.append(r3, r5, r2)
            java.lang.String r9 = r1.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.xml.XML.escapeXMLChars(java.lang.String, boolean):java.lang.String");
    }

    public static final String escapeXMLChars(String str) {
        return escapeXMLChars(str, true);
    }

    public static final String unescapeXMLChars(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("&amp;", a.b).replace("&lt;", "<").replace("&gt;", ">").replace("&apos;", "'").replace("&quot;", "\"");
    }
}
