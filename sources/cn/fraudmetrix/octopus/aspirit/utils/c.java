package cn.fraudmetrix.octopus.aspirit.utils;

import java.security.MessageDigest;

public class c {
    public static String a(String str) {
        String str2 = "";
        try {
            String a2 = a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
            try {
                return a2.substring(8, 24);
            } catch (Exception e) {
                Exception exc = e;
                str2 = a2;
                e = exc;
                e.printStackTrace();
                return str2;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return str2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(byte[] r4) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
        L_0x0006:
            int r2 = r4.length
            if (r1 >= r2) goto L_0x0022
            byte r2 = r4[r1]
            if (r2 >= 0) goto L_0x000f
            int r2 = r2 + 256
        L_0x000f:
            r3 = 16
            if (r2 >= r3) goto L_0x0018
            java.lang.String r3 = "0"
            r0.append(r3)
        L_0x0018:
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r0.append(r2)
            int r1 = r1 + 1
            goto L_0x0006
        L_0x0022:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.toUpperCase()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.fraudmetrix.octopus.aspirit.utils.c.a(byte[]):java.lang.String");
    }
}
