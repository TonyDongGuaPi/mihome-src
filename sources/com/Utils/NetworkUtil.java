package com.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.exoplayer2.C;
import java.io.IOException;

public class NetworkUtil {
    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean d(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static void e(Context context) {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011b A[SYNTHETIC, Splitter:B:75:0x011b] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0125 A[SYNTHETIC, Splitter:B:80:0x0125] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0131 A[SYNTHETIC, Splitter:B:86:0x0131] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x013b A[SYNTHETIC, Splitter:B:91:0x013b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "ping -s 1016 -c 3 "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0112, all -> 0x010d }
            java.lang.String r2 = "sh"
            java.lang.Process r1 = r1.exec(r2)     // Catch:{ IOException -> 0x0112, all -> 0x010d }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0112, all -> 0x010d }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ IOException -> 0x0112, all -> 0x010d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0112, all -> 0x010d }
            byte[] r6 = r6.getBytes()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.write(r6)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r6 = "\n"
            r2.writeBytes(r6)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.flush()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.lang.String r6 = "exit\n"
            r2.writeBytes(r6)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r2.flush()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
            r6.<init>(r3)     // Catch:{ IOException -> 0x0108, all -> 0x0103 }
        L_0x004a:
            java.lang.String r0 = r6.readLine()     // Catch:{ IOException -> 0x0101 }
            if (r0 == 0) goto L_0x00f5
            java.lang.String r1 = "---"
            boolean r0 = r0.startsWith(r1)     // Catch:{ IOException -> 0x0101 }
            if (r0 == 0) goto L_0x004a
            java.lang.String r0 = r6.readLine()     // Catch:{ IOException -> 0x0101 }
            if (r0 != 0) goto L_0x006f
            r2.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0066:
            r6.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r6 = move-exception
            r6.printStackTrace()
        L_0x006e:
            return
        L_0x006f:
            java.lang.String r1 = "transmitted,"
            int r1 = r0.indexOf(r1)     // Catch:{ IOException -> 0x0101 }
            int r1 = r1 + 13
            java.lang.String r3 = "received"
            int r3 = r0.indexOf(r3)     // Catch:{ IOException -> 0x0101 }
            java.lang.String r0 = r0.substring(r1, r3)     // Catch:{ IOException -> 0x0101 }
            java.lang.String r0 = r0.trim()     // Catch:{ IOException -> 0x0101 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0101 }
            int r0 = r0.intValue()     // Catch:{ IOException -> 0x0101 }
            if (r0 != 0) goto L_0x00a1
            r2.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0098
        L_0x0094:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0098:
            r6.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00a0:
            return
        L_0x00a1:
            java.lang.String r0 = r6.readLine()     // Catch:{ IOException -> 0x0101 }
            if (r0 != 0) goto L_0x00b8
            r2.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x00af
        L_0x00ab:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00af:
            r6.close()     // Catch:{ IOException -> 0x00b3 }
            goto L_0x00b7
        L_0x00b3:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00b7:
            return
        L_0x00b8:
            java.lang.String r1 = "="
            int r1 = r0.indexOf(r1)     // Catch:{ IOException -> 0x0101 }
            int r1 = r1 + 2
            java.lang.String r3 = "ms"
            int r3 = r0.indexOf(r3)     // Catch:{ IOException -> 0x0101 }
            r4 = 1
            int r3 = r3 - r4
            java.lang.String r0 = r0.substring(r1, r3)     // Catch:{ IOException -> 0x0101 }
            java.lang.String r0 = r0.trim()     // Catch:{ IOException -> 0x0101 }
            java.lang.String r1 = "/"
            java.lang.String[] r0 = r0.split(r1)     // Catch:{ IOException -> 0x0101 }
            int r1 = r0.length     // Catch:{ IOException -> 0x0101 }
            if (r1 >= r4) goto L_0x00ea
            r2.close()     // Catch:{ IOException -> 0x00dd }
            goto L_0x00e1
        L_0x00dd:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00e1:
            r6.close()     // Catch:{ IOException -> 0x00e5 }
            goto L_0x00e9
        L_0x00e5:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00e9:
            return
        L_0x00ea:
            r0 = r0[r4]     // Catch:{ IOException -> 0x0101 }
            java.lang.Float r0 = java.lang.Float.valueOf(r0)     // Catch:{ IOException -> 0x0101 }
            r0.floatValue()     // Catch:{ IOException -> 0x0101 }
            goto L_0x004a
        L_0x00f5:
            r2.close()     // Catch:{ IOException -> 0x00f9 }
            goto L_0x00fd
        L_0x00f9:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00fd:
            r6.close()     // Catch:{ IOException -> 0x0129 }
            goto L_0x012d
        L_0x0101:
            r0 = move-exception
            goto L_0x0116
        L_0x0103:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x012f
        L_0x0108:
            r6 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x0116
        L_0x010d:
            r6 = move-exception
            r2 = r0
            r0 = r6
            r6 = r2
            goto L_0x012f
        L_0x0112:
            r6 = move-exception
            r2 = r0
            r0 = r6
            r6 = r2
        L_0x0116:
            r0.printStackTrace()     // Catch:{ all -> 0x012e }
            if (r2 == 0) goto L_0x0123
            r2.close()     // Catch:{ IOException -> 0x011f }
            goto L_0x0123
        L_0x011f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0123:
            if (r6 == 0) goto L_0x012d
            r6.close()     // Catch:{ IOException -> 0x0129 }
            goto L_0x012d
        L_0x0129:
            r6 = move-exception
            r6.printStackTrace()
        L_0x012d:
            return
        L_0x012e:
            r0 = move-exception
        L_0x012f:
            if (r2 == 0) goto L_0x0139
            r2.close()     // Catch:{ IOException -> 0x0135 }
            goto L_0x0139
        L_0x0135:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0139:
            if (r6 == 0) goto L_0x0143
            r6.close()     // Catch:{ IOException -> 0x013f }
            goto L_0x0143
        L_0x013f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0143:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Utils.NetworkUtil.a(java.lang.String):void");
    }

    public static boolean a() {
        try {
            if (Runtime.getRuntime().exec("ping -c 1 www.baidu.com").waitFor() == 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
