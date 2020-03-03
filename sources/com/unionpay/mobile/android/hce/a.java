package com.unionpay.mobile.android.hce;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public final class a {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0063 A[Catch:{ Exception -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006b A[Catch:{ Exception -> 0x006e }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r8, java.lang.String r9) {
        /*
            r0 = 0
            java.lang.String r1 = "UTF-8"
            byte[] r9 = r9.getBytes(r1)     // Catch:{ Exception -> 0x006e }
            r1 = 2
            if (r8 == 0) goto L_0x0044
            java.lang.String r2 = ""
            boolean r2 = r8.equals(r2)     // Catch:{ Exception -> 0x006e }
            if (r2 == 0) goto L_0x0013
            goto L_0x0044
        L_0x0013:
            java.lang.String r8 = r8.toUpperCase()     // Catch:{ Exception -> 0x006e }
            int r2 = r8.length()     // Catch:{ Exception -> 0x006e }
            int r2 = r2 / r1
            char[] r8 = r8.toCharArray()     // Catch:{ Exception -> 0x006e }
            byte[] r3 = new byte[r2]     // Catch:{ Exception -> 0x006e }
            r4 = 0
        L_0x0023:
            if (r4 >= r2) goto L_0x0045
            int r5 = r4 * 2
            char r6 = r8[r5]     // Catch:{ Exception -> 0x006e }
            java.lang.String r7 = "0123456789ABCDEF"
            int r6 = r7.indexOf(r6)     // Catch:{ Exception -> 0x006e }
            byte r6 = (byte) r6     // Catch:{ Exception -> 0x006e }
            int r6 = r6 << 4
            int r5 = r5 + 1
            char r5 = r8[r5]     // Catch:{ Exception -> 0x006e }
            java.lang.String r7 = "0123456789ABCDEF"
            int r5 = r7.indexOf(r5)     // Catch:{ Exception -> 0x006e }
            byte r5 = (byte) r5     // Catch:{ Exception -> 0x006e }
            r5 = r5 | r6
            byte r5 = (byte) r5     // Catch:{ Exception -> 0x006e }
            r3[r4] = r5     // Catch:{ Exception -> 0x006e }
            int r4 = r4 + 1
            goto L_0x0023
        L_0x0044:
            r3 = r0
        L_0x0045:
            javax.crypto.spec.DESedeKeySpec r8 = new javax.crypto.spec.DESedeKeySpec     // Catch:{ Exception -> 0x006e }
            r8.<init>(r9)     // Catch:{ Exception -> 0x006e }
            java.lang.String r9 = "desede"
            javax.crypto.SecretKeyFactory r9 = javax.crypto.SecretKeyFactory.getInstance(r9)     // Catch:{ Exception -> 0x006e }
            javax.crypto.SecretKey r8 = r9.generateSecret(r8)     // Catch:{ Exception -> 0x006e }
            java.lang.String r9 = "DESEDE/ECB/PKCS5Padding"
            javax.crypto.Cipher r9 = javax.crypto.Cipher.getInstance(r9)     // Catch:{ Exception -> 0x006e }
            r9.init(r1, r8)     // Catch:{ Exception -> 0x006e }
            byte[] r8 = r9.doFinal(r3)     // Catch:{ Exception -> 0x006e }
            if (r8 == 0) goto L_0x006b
            java.lang.String r9 = new java.lang.String     // Catch:{ Exception -> 0x006e }
            java.lang.String r1 = "UTF-8"
            r9.<init>(r8, r1)     // Catch:{ Exception -> 0x006e }
            goto L_0x0073
        L_0x006b:
            java.lang.String r8 = ""
            return r8
        L_0x006e:
            r8 = move-exception
            r8.printStackTrace()
            r9 = r0
        L_0x0073:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.hce.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String b(String str, String str2) {
        byte[] bArr;
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            byte[] bytes2 = str.getBytes();
            SecretKey generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bytes));
            Cipher instance = Cipher.getInstance("desede/ECB/PKCS5Padding");
            instance.init(1, generateSecret);
            bArr = instance.doFinal(bytes2);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        return a(bArr);
    }
}
