package com.mi.util;

public class MD5Util {
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        return null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r3) {
        /*
            java.lang.String r0 = "MD5"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r1 = 0
            int r2 = r3.length     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r0.update(r3, r1, r2)     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            byte[] r3 = r0.digest()     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            java.lang.String r3 = a((byte[]) r3)     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            return r3
        L_0x0018:
            r3 = move-exception
            throw r3
        L_0x001a:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.util.MD5Util.a(java.lang.String):java.lang.String");
    }

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            byte b = (bArr[i] >>> 4) & 15;
            int i2 = 0;
            while (true) {
                if (b < 0 || b > 9) {
                    stringBuffer.append((char) ((b - 10) + 97));
                } else {
                    stringBuffer.append((char) (b + 48));
                }
                b = bArr[i] & 15;
                int i3 = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i3;
            }
        }
        return stringBuffer.toString();
    }
}
