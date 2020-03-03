package com.mi.util;

import android.text.TextUtils;
import android.util.Log;
import com.google.code.microlog4android.format.PatternFormatter;
import java.io.File;

public class SignUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f7421a = Constants.f1348a;
    private static final String b = (f7421a ? "SignUtils" : SignUtils.class.getSimpleName());

    private static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        char[] cArr2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i + 1;
            cArr[i] = cArr2[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0038 A[SYNTHETIC, Splitter:B:22:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0045 A[SYNTHETIC, Splitter:B:29:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.File r5) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0031, all -> 0x002e }
            java.lang.String r5 = "MD5"
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r5)     // Catch:{ Exception -> 0x002c }
            r2 = 8192(0x2000, float:1.14794E-41)
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x002c }
        L_0x0010:
            int r3 = r1.read(r2)     // Catch:{ Exception -> 0x002c }
            if (r3 <= 0) goto L_0x001b
            r4 = 0
            r5.update(r2, r4, r3)     // Catch:{ Exception -> 0x002c }
            goto L_0x0010
        L_0x001b:
            byte[] r5 = r5.digest()     // Catch:{ Exception -> 0x002c }
            java.lang.String r5 = a((byte[]) r5)     // Catch:{ Exception -> 0x002c }
            r1.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0041
        L_0x0027:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0041
        L_0x002c:
            r5 = move-exception
            goto L_0x0033
        L_0x002e:
            r5 = move-exception
            r1 = r0
            goto L_0x0043
        L_0x0031:
            r5 = move-exception
            r1 = r0
        L_0x0033:
            r5.printStackTrace()     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0040:
            r5 = r0
        L_0x0041:
            return r5
        L_0x0042:
            r5 = move-exception
        L_0x0043:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ IOException -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.util.SignUtils.a(java.io.File):java.lang.String");
    }

    public static boolean a(File file, String str) {
        if (!TextUtils.isEmpty(str)) {
            String a2 = a(file);
            if (f7421a) {
                Log.d(b, String.format("file's md5=%s, real md5=%s", new Object[]{a2, str}));
            }
            return str.equals(a2);
        }
        throw new RuntimeException("md5 cannot be empty");
    }

    public static boolean a(String str, String str2) {
        return a(new File(str), str2);
    }
}
