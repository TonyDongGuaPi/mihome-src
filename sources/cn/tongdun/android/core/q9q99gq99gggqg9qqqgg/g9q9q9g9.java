package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.content.Context;
import android.content.pm.PackageInfo;
import cn.tongdun.android.shell.common.HelperJNI;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g9q9q9g9 {
    private static final String q9gqqq99999qq = gqg9qq9gqq9q9q("2d5c611f2d113846724969556440", 82);
    private static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("2a757f69667b687c", 120);
    private static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("2a366234693560", 59);
    private static final g9q9q9g9 qqq9gg9gqq9qgg99q = new g9q9q9g9();
    private Context g9q9q9g9;
    private String[] gqg9qq9gqq9q9q = {gqg9qq9gqq9q9q("2d7b61382d363861726e69726467", 117), gqg9qq9gqq9q9q("2d736130233e24757a64697764776d7e", 125), gqg9qq9gqq9q9q("2d4661052a18311e381e3d16321b34507e416a4c67446a", 72), gqg9qq9gqq9q9q("2d49610a230928147f4c60576656725b", 71), gqg9qq9gqq9q9q("2d7461373e2f3f283f697c657a6b70", 122)};

    public static g9q9q9g9 gqg9qq9gqq9q9q() {
        return qqq9gg9gqq9qgg99q;
    }

    public String gqg9qq9gqq9q9q(Context context) {
        this.g9q9q9g9 = context.getApplicationContext();
        try {
            JSONObject qgg9qgg9999g9g2 = qgg9qgg9999g9g();
            JSONObject qgg9qgg9999g9g3 = qgg9qgg9999g9g(this.g9q9q9g9);
            String shooted = HelperJNI.shooted();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(gqg9qq9gqq9q9q("2d5f6e596676576c73467b1d", 86), shooted);
            if (qgg9qgg9999g9g2 == null && qgg9qgg9999g9g3 == null && shooted == null && shooted == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            if (qgg9qgg9999g9g2 != null) {
                jSONArray.put(qgg9qgg9999g9g2);
            }
            if (qgg9qgg9999g9g3 != null) {
                jSONArray.put(qgg9qgg9999g9g3);
            }
            if (shooted != null) {
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private g9q9q9g9() {
    }

    private JSONObject qgg9qgg9999g9g(Context context) {
        if (context == null) {
            return null;
        }
        try {
            context.getPackageName();
            boolean z = false;
            String str = null;
            boolean z2 = false;
            for (PackageInfo next : context.getPackageManager().getInstalledPackages(0)) {
                if (next.packageName.equals(q9gqqq99999qq)) {
                    z2 = true;
                }
                if (next.packageName.startsWith(qgg9qgg9999g9g) || next.packageName.startsWith(q9qq99qg9qqgqg9gqgg9)) {
                    str = next.packageName;
                    z = true;
                }
            }
            if (z && !z2) {
                JSONObject jSONObject = new JSONObject();
                if (str != null) {
                    jSONObject.put(gqg9qq9gqq9q9q("2d206e266609571373397b", 41), str);
                } else {
                    jSONObject.put(gqg9qq9gqq9q9q("2d276e21660e5714733e7b", 46), gqg9qq9gqq9q9q("2a177e13741b4d20417a12610b61", 4));
                }
                return jSONObject;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static JSONObject qgg9qgg9999g9g() {
        String gqg9qq9gqq9q9q2;
        String[] split;
        JSONObject jSONObject = new JSONObject();
        String q9qq99qg9qqgqg9gqgg92 = q9qq99qg9qqgqg9gqgg9();
        if (q9qq99qg9qqgqg9gqgg92 == null || q9qq99qg9qqgqg9gqgg92.length() == 0 || (gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("3e58", 89))) == null || gqg9qq9gqq9q9q2.isEmpty() || (split = gqg9qq9gqq9q9q2.split(gqg9qq9gqq9q9q("44", 46))) == null || split.length <= 0) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            if (split[i2].contains(q9qq99qg9qqgqg9gqgg92)) {
                int lastIndexOf = split[i2].lastIndexOf(gqg9qq9gqq9q9q("6e", 80));
                File file = new File(String.format(gqg9qq9gqq9q9q("61572a423f0c7409611c2f1679", 30), new Object[]{split[i2].substring(lastIndexOf <= 0 ? 0 : lastIndexOf + 1, split[i2].length()), Locale.CHINA}));
                if (file.exists()) {
                    try {
                        jSONObject.put(gqg9qq9gqq9q9q("2d786e7e66405a4d1e", 113) + i2, file.getAbsolutePath());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }
        if (i > 1) {
            return jSONObject;
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063 A[SYNTHETIC, Splitter:B:32:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006d A[SYNTHETIC, Splitter:B:37:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0080 A[SYNTHETIC, Splitter:B:49:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x008a A[SYNTHETIC, Splitter:B:54:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String gqg9qq9gqq9q9q(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x007b, all -> 0x005e }
            java.lang.String r2 = "3d24"
            r3 = 61
            java.lang.String r2 = gqg9qq9gqq9q9q(r2, r3)     // Catch:{ Exception -> 0x007b, all -> 0x005e }
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ Exception -> 0x007b, all -> 0x005e }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            java.io.InputStream r4 = r1.getInputStream()     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            r2.write(r5)     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            r5 = 10
            r2.write(r5)     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            r2.flush()     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            r2.close()     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            r1.waitFor()     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            java.lang.String r5 = gqg9qq9gqq9q9q((java.io.BufferedInputStream) r3)     // Catch:{ Exception -> 0x007e, all -> 0x0052 }
            r2.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0044:
            r3.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004c:
            if (r1 == 0) goto L_0x0051
            r1.destroy()
        L_0x0051:
            return r5
        L_0x0052:
            r5 = move-exception
            r0 = r3
            goto L_0x0061
        L_0x0055:
            r5 = move-exception
            goto L_0x0061
        L_0x0057:
            r3 = r0
            goto L_0x007e
        L_0x0059:
            r5 = move-exception
            r2 = r0
            goto L_0x0061
        L_0x005c:
            r2 = r0
            goto L_0x007d
        L_0x005e:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L_0x0061:
            if (r2 == 0) goto L_0x006b
            r2.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r2 = move-exception
            r2.printStackTrace()
        L_0x006b:
            if (r0 == 0) goto L_0x0075
            r0.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0075:
            if (r1 == 0) goto L_0x007a
            r1.destroy()
        L_0x007a:
            throw r5
        L_0x007b:
            r1 = r0
            r2 = r1
        L_0x007d:
            r3 = r2
        L_0x007e:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0088:
            if (r3 == 0) goto L_0x0092
            r3.close()     // Catch:{ IOException -> 0x008e }
            goto L_0x0092
        L_0x008e:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0092:
            if (r1 == 0) goto L_0x0097
            r1.destroy()
        L_0x0097:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.g9q9q9g9.gqg9qq9gqq9q9q(java.lang.String):java.lang.String");
    }

    private static String gqg9qq9gqq9q9q(BufferedInputStream bufferedInputStream) {
        int read;
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[512];
        StringBuilder sb = new StringBuilder();
        do {
            try {
                read = bufferedInputStream.read(bArr);
                if (read > 0) {
                    sb.append(new String(bArr, 0, read));
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (read >= 512);
        return sb.toString();
    }

    private static String q9qq99qg9qqgqg9gqgg9() {
        String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("2d4c761879477b5a77162b00220a6b466f53724977", 76));
        if (gqg9qq9gqq9q9q2 == null || gqg9qq9gqq9q9q2.length() == 0) {
            return null;
        }
        int lastIndexOf = gqg9qq9gqq9q9q2.lastIndexOf(gqg9qq9gqq9q9q("3b4d78", 83));
        int lastIndexOf2 = gqg9qq9gqq9q9q2.lastIndexOf(gqg9qq9gqq9q9q("6143364e", 30));
        if (lastIndexOf < 0) {
            return null;
        }
        if (lastIndexOf2 <= 0) {
            lastIndexOf2 = gqg9qq9gqq9q9q2.length();
        }
        try {
            String replaceAll = gqg9qq9gqq9q9q2.substring(lastIndexOf + 4, lastIndexOf2).replaceAll(gqg9qq9gqq9q9q("44", 61), "");
            if (!qgg9qgg9999g9g(replaceAll)) {
                return null;
            }
            int intValue = Integer.valueOf(replaceAll).intValue();
            return String.format(gqg9qq9gqq9q9q("3b3c1a025e43", 123), new Object[]{Integer.valueOf(intValue - 10000)});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean qgg9qgg9999g9g(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 2);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 78);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
