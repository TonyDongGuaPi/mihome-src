package com.xiaomi.ai.utils;

import org.cybergarage.http.HTTP;

public class a {
    private static String a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(HTTP.TAB);
        }
        return stringBuffer.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r3 != '}') goto L_0x004b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r6) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            r2 = 0
        L_0x0007:
            int r3 = r6.length()
            if (r1 >= r3) goto L_0x007d
            char r3 = r6.charAt(r1)
            if (r2 <= 0) goto L_0x0028
            r4 = 10
            int r5 = r0.length()
            int r5 = r5 + -1
            char r5 = r0.charAt(r5)
            if (r4 != r5) goto L_0x0028
            java.lang.String r4 = a((int) r2)
            r0.append(r4)
        L_0x0028:
            r4 = 44
            if (r3 == r4) goto L_0x0066
            r4 = 91
            if (r3 == r4) goto L_0x004f
            r4 = 93
            if (r3 == r4) goto L_0x003d
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L_0x004f
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x003d
            goto L_0x004b
        L_0x003d:
            java.lang.String r4 = "\n"
            r0.append(r4)
            int r2 = r2 + -1
            java.lang.String r4 = a((int) r2)
            r0.append(r4)
        L_0x004b:
            r0.append(r3)
            goto L_0x007a
        L_0x004f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = "\n"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r0.append(r3)
            int r2 = r2 + 1
            goto L_0x007a
        L_0x0066:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = "\n"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r0.append(r3)
        L_0x007a:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x007d:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.a.a(java.lang.String):java.lang.String");
    }
}
