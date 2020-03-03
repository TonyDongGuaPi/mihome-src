package cn.tongdun.android.core.q9q99gq99gggqg9qqqgg;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;

public class q9gqqq99999qq {
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        if (r6 != null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005d, code lost:
        if (r6 != null) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r6.waitFor();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        r6.destroy();
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[SYNTHETIC, Splitter:B:20:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String gqg9qq9gqq9q9q(java.lang.String r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            boolean r1 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgggqg999gg9qqggq.gqg9qq9gqq9q9q((java.lang.String) r6)
            if (r1 != 0) goto L_0x0066
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x005c, all -> 0x0050 }
            java.lang.Process r6 = r2.exec(r6)     // Catch:{ IOException -> 0x005c, all -> 0x0050 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            java.io.InputStream r3 = r6.getInputStream()     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            java.lang.String r4 = "1a46670d72"
            r5 = 92
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            r2.<init>(r3, r4)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            r1.<init>(r2)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
        L_0x002a:
            java.lang.String r2 = r1.readLine()     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            if (r2 == 0) goto L_0x004b
            boolean r3 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgggqg999gg9qqggq.gqg9qq9gqq9q9q((java.lang.String) r2)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            if (r3 != 0) goto L_0x002a
            int r3 = r0.length()     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            if (r3 <= 0) goto L_0x0047
            java.lang.String r3 = "43"
            r4 = 78
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            r0.append(r3)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
        L_0x0047:
            r0.append(r2)     // Catch:{ IOException -> 0x005d, all -> 0x004e }
            goto L_0x002a
        L_0x004b:
            if (r6 == 0) goto L_0x0066
            goto L_0x005f
        L_0x004e:
            r0 = move-exception
            goto L_0x0052
        L_0x0050:
            r0 = move-exception
            r6 = r1
        L_0x0052:
            if (r6 == 0) goto L_0x005b
            r6.waitFor()     // Catch:{ InterruptedException -> 0x0058 }
            goto L_0x005b
        L_0x0058:
            r6.destroy()
        L_0x005b:
            throw r0
        L_0x005c:
            r6 = r1
        L_0x005d:
            if (r6 == 0) goto L_0x0066
        L_0x005f:
            r6.waitFor()     // Catch:{ InterruptedException -> 0x0063 }
            goto L_0x0066
        L_0x0063:
            r6.destroy()
        L_0x0066:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.q9gqqq99999qq.gqg9qq9gqq9q9q(java.lang.String):java.lang.String");
    }

    public static PackageInfo gqg9qq9gqq9q9q(PackageManager packageManager, String str) {
        try {
            return packageManager.getPackageInfo(str, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    @TargetApi(23)
    public static String gqg9qq9gqq9q9q() {
        if (Build.VERSION.SDK_INT > 23) {
            try {
                Iterator<T> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                while (it.hasNext()) {
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (networkInterface.getName().equalsIgnoreCase(gqg9qq9gqq9q9q("18317a3e24", 49))) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "";
                        }
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format(gqg9qq9gqq9q9q("4a1e277445", 16), new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString().toLowerCase();
                    }
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static boolean gqg9qq9gqq9q9q(Context context) {
        return (context.getApplicationInfo() == null || (context.getApplicationInfo().flags & 2) == 0) ? false : true;
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
            byte b = (byte) (i ^ 27);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.ELECTRONIC_END_TIME);
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
