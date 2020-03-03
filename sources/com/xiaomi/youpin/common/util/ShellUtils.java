package com.xiaomi.youpin.common.util;

import java.util.List;

public final class ShellUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23269a = System.getProperty("line.separator");

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static CommandResult a(String str, boolean z) {
        return a(new String[]{str}, z, true);
    }

    public static CommandResult a(List<String> list, boolean z) {
        return a(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static CommandResult a(String[] strArr, boolean z) {
        return a(strArr, z, true);
    }

    public static CommandResult a(String str, boolean z, boolean z2) {
        return a(new String[]{str}, z, z2);
    }

    public static CommandResult a(List<String> list, boolean z, boolean z2) {
        return a(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: java.lang.StringBuilder} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0139, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x013a, code lost:
        r9.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x013f, code lost:
        r10.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0161, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0162, code lost:
        r11.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x016b, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x016c, code lost:
        r11.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0175, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0176, code lost:
        r11.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x017b, code lost:
        r10.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c7, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c8, code lost:
        r4 = null;
        r5 = r1;
        r1 = r9;
        r9 = r3;
        r3 = null;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cf, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d0, code lost:
        r3 = null;
        r4 = null;
        r5 = null;
        r8 = r1;
        r1 = r9;
        r9 = r8;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d8, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d9, code lost:
        r1 = r9;
        r9 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0102, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0103, code lost:
        r4 = null;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0125, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0126, code lost:
        r9.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x012f, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0130, code lost:
        r9.printStackTrace();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0135 A[SYNTHETIC, Splitter:B:100:0x0135] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x015d A[SYNTHETIC, Splitter:B:118:0x015d] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0167 A[SYNTHETIC, Splitter:B:123:0x0167] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0171 A[SYNTHETIC, Splitter:B:128:0x0171] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ea A[SYNTHETIC, Splitter:B:61:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f4 A[SYNTHETIC, Splitter:B:66:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0102 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0121 A[SYNTHETIC, Splitter:B:90:0x0121] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x012b A[SYNTHETIC, Splitter:B:95:0x012b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.youpin.common.util.ShellUtils.CommandResult a(java.lang.String[] r9, boolean r10, boolean r11) {
        /*
            r0 = 0
            r1 = -1
            if (r9 == 0) goto L_0x017f
            int r2 = r9.length
            if (r2 != 0) goto L_0x0009
            goto L_0x017f
        L_0x0009:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0115, all -> 0x0110 }
            if (r10 == 0) goto L_0x0012
            java.lang.String r10 = "su"
            goto L_0x0014
        L_0x0012:
            java.lang.String r10 = "sh"
        L_0x0014:
            java.lang.Process r10 = r2.exec(r10)     // Catch:{ Exception -> 0x0115, all -> 0x0110 }
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x010d, all -> 0x010a }
            java.io.OutputStream r3 = r10.getOutputStream()     // Catch:{ Exception -> 0x010d, all -> 0x010a }
            r2.<init>(r3)     // Catch:{ Exception -> 0x010d, all -> 0x010a }
            int r3 = r9.length     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r4 = 0
        L_0x0023:
            if (r4 >= r3) goto L_0x003c
            r5 = r9[r4]     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            if (r5 != 0) goto L_0x002a
            goto L_0x0039
        L_0x002a:
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r2.write(r5)     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            java.lang.String r5 = f23269a     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r2.writeBytes(r5)     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r2.flush()     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
        L_0x0039:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x003c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r9.<init>()     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            java.lang.String r3 = "exit"
            r9.append(r3)     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            java.lang.String r3 = f23269a     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r9.append(r3)     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r2.writeBytes(r9)     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            r2.flush()     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            int r9 = r10.waitFor()     // Catch:{ Exception -> 0x0106, all -> 0x0102 }
            if (r11 == 0) goto L_0x00dc
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d8, all -> 0x0102 }
            r11.<init>()     // Catch:{ Exception -> 0x00d8, all -> 0x0102 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cf, all -> 0x0102 }
            r1.<init>()     // Catch:{ Exception -> 0x00cf, all -> 0x0102 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00c7, all -> 0x0102 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00c7, all -> 0x0102 }
            java.io.InputStream r5 = r10.getInputStream()     // Catch:{ Exception -> 0x00c7, all -> 0x0102 }
            java.lang.String r6 = "UTF-8"
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x00c7, all -> 0x0102 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00c7, all -> 0x0102 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.io.InputStream r6 = r10.getErrorStream()     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.lang.String r7 = "UTF-8"
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.lang.String r5 = r3.readLine()     // Catch:{ Exception -> 0x00b5 }
            if (r5 == 0) goto L_0x009d
            r11.append(r5)     // Catch:{ Exception -> 0x00b5 }
        L_0x008e:
            java.lang.String r5 = r3.readLine()     // Catch:{ Exception -> 0x00b5 }
            if (r5 == 0) goto L_0x009d
            java.lang.String r6 = f23269a     // Catch:{ Exception -> 0x00b5 }
            r11.append(r6)     // Catch:{ Exception -> 0x00b5 }
            r11.append(r5)     // Catch:{ Exception -> 0x00b5 }
            goto L_0x008e
        L_0x009d:
            java.lang.String r5 = r4.readLine()     // Catch:{ Exception -> 0x00b5 }
            if (r5 == 0) goto L_0x00e0
            r1.append(r5)     // Catch:{ Exception -> 0x00b5 }
        L_0x00a6:
            java.lang.String r5 = r4.readLine()     // Catch:{ Exception -> 0x00b5 }
            if (r5 == 0) goto L_0x00e0
            java.lang.String r6 = f23269a     // Catch:{ Exception -> 0x00b5 }
            r1.append(r6)     // Catch:{ Exception -> 0x00b5 }
            r1.append(r5)     // Catch:{ Exception -> 0x00b5 }
            goto L_0x00a6
        L_0x00b5:
            r5 = move-exception
            r8 = r1
            r1 = r9
            r9 = r5
            r5 = r8
            goto L_0x011c
        L_0x00bc:
            r9 = move-exception
            r4 = r0
            goto L_0x015a
        L_0x00c0:
            r4 = move-exception
            r5 = r1
            r1 = r9
            r9 = r4
            r4 = r0
            goto L_0x011c
        L_0x00c7:
            r3 = move-exception
            r4 = r0
            r5 = r1
            r1 = r9
            r9 = r3
            r3 = r4
            goto L_0x011c
        L_0x00cf:
            r1 = move-exception
            r3 = r0
            r4 = r3
            r5 = r4
            r8 = r1
            r1 = r9
            r9 = r8
            goto L_0x011c
        L_0x00d8:
            r11 = move-exception
            r1 = r9
            r9 = r11
            goto L_0x0107
        L_0x00dc:
            r11 = r0
            r1 = r11
            r3 = r1
            r4 = r3
        L_0x00e0:
            r2.close()     // Catch:{ IOException -> 0x00e4 }
            goto L_0x00e8
        L_0x00e4:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00e8:
            if (r3 == 0) goto L_0x00f2
            r3.close()     // Catch:{ IOException -> 0x00ee }
            goto L_0x00f2
        L_0x00ee:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00f2:
            if (r4 == 0) goto L_0x00fc
            r4.close()     // Catch:{ IOException -> 0x00f8 }
            goto L_0x00fc
        L_0x00f8:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00fc:
            if (r10 == 0) goto L_0x0144
            r10.destroy()
            goto L_0x0144
        L_0x0102:
            r9 = move-exception
            r4 = r0
            goto L_0x015b
        L_0x0106:
            r9 = move-exception
        L_0x0107:
            r11 = r0
            r3 = r11
            goto L_0x011a
        L_0x010a:
            r9 = move-exception
            r2 = r0
            goto L_0x0113
        L_0x010d:
            r9 = move-exception
            r11 = r0
            goto L_0x0118
        L_0x0110:
            r9 = move-exception
            r10 = r0
            r2 = r10
        L_0x0113:
            r4 = r2
            goto L_0x015b
        L_0x0115:
            r9 = move-exception
            r10 = r0
            r11 = r10
        L_0x0118:
            r2 = r11
            r3 = r2
        L_0x011a:
            r4 = r3
            r5 = r4
        L_0x011c:
            r9.printStackTrace()     // Catch:{ all -> 0x0159 }
            if (r2 == 0) goto L_0x0129
            r2.close()     // Catch:{ IOException -> 0x0125 }
            goto L_0x0129
        L_0x0125:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0129:
            if (r3 == 0) goto L_0x0133
            r3.close()     // Catch:{ IOException -> 0x012f }
            goto L_0x0133
        L_0x012f:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0133:
            if (r4 == 0) goto L_0x013d
            r4.close()     // Catch:{ IOException -> 0x0139 }
            goto L_0x013d
        L_0x0139:
            r9 = move-exception
            r9.printStackTrace()
        L_0x013d:
            if (r10 == 0) goto L_0x0142
            r10.destroy()
        L_0x0142:
            r9 = r1
            r1 = r5
        L_0x0144:
            com.xiaomi.youpin.common.util.ShellUtils$CommandResult r10 = new com.xiaomi.youpin.common.util.ShellUtils$CommandResult
            if (r11 != 0) goto L_0x014a
            r11 = r0
            goto L_0x014e
        L_0x014a:
            java.lang.String r11 = r11.toString()
        L_0x014e:
            if (r1 != 0) goto L_0x0151
            goto L_0x0155
        L_0x0151:
            java.lang.String r0 = r1.toString()
        L_0x0155:
            r10.<init>(r9, r11, r0)
            return r10
        L_0x0159:
            r9 = move-exception
        L_0x015a:
            r0 = r3
        L_0x015b:
            if (r2 == 0) goto L_0x0165
            r2.close()     // Catch:{ IOException -> 0x0161 }
            goto L_0x0165
        L_0x0161:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0165:
            if (r0 == 0) goto L_0x016f
            r0.close()     // Catch:{ IOException -> 0x016b }
            goto L_0x016f
        L_0x016b:
            r11 = move-exception
            r11.printStackTrace()
        L_0x016f:
            if (r4 == 0) goto L_0x0179
            r4.close()     // Catch:{ IOException -> 0x0175 }
            goto L_0x0179
        L_0x0175:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0179:
            if (r10 == 0) goto L_0x017e
            r10.destroy()
        L_0x017e:
            throw r9
        L_0x017f:
            com.xiaomi.youpin.common.util.ShellUtils$CommandResult r9 = new com.xiaomi.youpin.common.util.ShellUtils$CommandResult
            r9.<init>(r1, r0, r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ShellUtils.a(java.lang.String[], boolean, boolean):com.xiaomi.youpin.common.util.ShellUtils$CommandResult");
    }

    public static class CommandResult {

        /* renamed from: a  reason: collision with root package name */
        public int f23270a;
        public String b;
        public String c;

        public CommandResult(int i, String str, String str2) {
            this.f23270a = i;
            this.b = str;
            this.c = str2;
        }
    }
}
