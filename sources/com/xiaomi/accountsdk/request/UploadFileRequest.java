package com.xiaomi.accountsdk.request;

public class UploadFileRequest {
    private static final String CRLF = "\r\n";

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0156 A[SYNTHETIC, Splitter:B:43:0x0156] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0160 A[SYNTHETIC, Splitter:B:48:0x0160] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String execute(java.lang.String r6, java.io.InputStream r7, java.lang.String r8, java.lang.String r9) throws com.xiaomi.accountsdk.request.InvalidResponseException, java.io.IOException {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x0151 }
            r1.<init>(r6)     // Catch:{ all -> 0x0151 }
            java.net.URLConnection r6 = r1.openConnection()     // Catch:{ all -> 0x0151 }
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch:{ all -> 0x0151 }
            java.util.Random r1 = new java.util.Random     // Catch:{ all -> 0x014e }
            r1.<init>()     // Catch:{ all -> 0x014e }
            r2 = 16
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x014e }
            r1.nextBytes(r2)     // Catch:{ all -> 0x014e }
            r1 = 2
            java.lang.String r1 = android.util.Base64.encodeToString(r2, r1)     // Catch:{ all -> 0x014e }
            r2 = 1
            r6.setDoOutput(r2)     // Catch:{ all -> 0x014e }
            r6.setDoInput(r2)     // Catch:{ all -> 0x014e }
            r2 = 0
            r6.setUseCaches(r2)     // Catch:{ all -> 0x014e }
            java.lang.String r3 = "POST"
            r6.setRequestMethod(r3)     // Catch:{ all -> 0x014e }
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r6.setRequestProperty(r3, r4)     // Catch:{ all -> 0x014e }
            java.lang.String r3 = "Content-Type"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x014e }
            r4.<init>()     // Catch:{ all -> 0x014e }
            java.lang.String r5 = "multipart/form-data;boundary="
            r4.append(r5)     // Catch:{ all -> 0x014e }
            r4.append(r1)     // Catch:{ all -> 0x014e }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x014e }
            r6.setRequestProperty(r3, r4)     // Catch:{ all -> 0x014e }
            java.lang.String r3 = "Cache-Control"
            java.lang.String r4 = "no-cache"
            r6.setRequestProperty(r3, r4)     // Catch:{ all -> 0x014e }
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch:{ all -> 0x014e }
            java.io.OutputStream r4 = r6.getOutputStream()     // Catch:{ all -> 0x014e }
            r3.<init>(r4)     // Catch:{ all -> 0x014e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r0.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r4 = "--"
            r0.append(r4)     // Catch:{ all -> 0x014c }
            r0.append(r1)     // Catch:{ all -> 0x014c }
            java.lang.String r4 = "\r\n"
            r0.append(r4)     // Catch:{ all -> 0x014c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x014c }
            r3.writeBytes(r0)     // Catch:{ all -> 0x014c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r0.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r4 = "Content-Disposition: form-data; name=\""
            r0.append(r4)     // Catch:{ all -> 0x014c }
            r0.append(r8)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = "\"; filename=\""
            r0.append(r8)     // Catch:{ all -> 0x014c }
            r0.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = "\""
            r0.append(r8)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = "\r\n"
            r0.append(r8)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x014c }
            r3.writeBytes(r8)     // Catch:{ all -> 0x014c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r8.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r0 = "Content-Type: "
            r8.append(r0)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = getMIMEType(r9)     // Catch:{ all -> 0x014c }
            r8.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = "\r\n"
            r8.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x014c }
            r3.writeBytes(r8)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = "\r\n"
            r3.writeBytes(r8)     // Catch:{ all -> 0x014c }
            r8 = 8192(0x2000, float:1.14794E-41)
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x014c }
        L_0x00bf:
            int r9 = r7.read(r8)     // Catch:{ all -> 0x014c }
            r0 = -1
            if (r9 == r0) goto L_0x00ca
            r3.write(r8, r2, r9)     // Catch:{ all -> 0x014c }
            goto L_0x00bf
        L_0x00ca:
            r3.flush()     // Catch:{ all -> 0x014c }
            java.lang.String r8 = "\r\n"
            r3.writeBytes(r8)     // Catch:{ all -> 0x014c }
            r3.flush()     // Catch:{ all -> 0x014c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r8.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r9 = "--"
            r8.append(r9)     // Catch:{ all -> 0x014c }
            r8.append(r1)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = "--"
            r8.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = "\r\n"
            r8.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x014c }
            r3.writeBytes(r8)     // Catch:{ all -> 0x014c }
            r3.flush()     // Catch:{ all -> 0x014c }
            r7.close()     // Catch:{ all -> 0x014c }
            int r8 = r6.getResponseCode()     // Catch:{ all -> 0x014c }
            r9 = 200(0xc8, float:2.8E-43)
            if (r8 != r9) goto L_0x0144
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r8.<init>()     // Catch:{ all -> 0x014c }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ all -> 0x014c }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ all -> 0x014c }
            java.io.InputStream r1 = r6.getInputStream()     // Catch:{ all -> 0x014c }
            r0.<init>(r1)     // Catch:{ all -> 0x014c }
            r1 = 1024(0x400, float:1.435E-42)
            r9.<init>(r0, r1)     // Catch:{ all -> 0x014c }
        L_0x0116:
            java.lang.String r0 = r9.readLine()     // Catch:{ all -> 0x013f }
            if (r0 == 0) goto L_0x0120
            r8.append(r0)     // Catch:{ all -> 0x013f }
            goto L_0x0116
        L_0x0120:
            com.xiaomi.accountsdk.utils.IOUtils.closeQuietly((java.io.Reader) r9)     // Catch:{ all -> 0x014c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x014c }
            r3.close()     // Catch:{ IOException -> 0x012b }
            goto L_0x012f
        L_0x012b:
            r9 = move-exception
            r9.printStackTrace()
        L_0x012f:
            if (r7 == 0) goto L_0x0139
            r7.close()     // Catch:{ IOException -> 0x0135 }
            goto L_0x0139
        L_0x0135:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0139:
            if (r6 == 0) goto L_0x013e
            r6.disconnect()
        L_0x013e:
            return r8
        L_0x013f:
            r8 = move-exception
            com.xiaomi.accountsdk.utils.IOUtils.closeQuietly((java.io.Reader) r9)     // Catch:{ all -> 0x014c }
            throw r8     // Catch:{ all -> 0x014c }
        L_0x0144:
            com.xiaomi.accountsdk.request.InvalidResponseException r9 = new com.xiaomi.accountsdk.request.InvalidResponseException     // Catch:{ all -> 0x014c }
            java.lang.String r0 = "failed to upload file"
            r9.<init>((int) r8, (java.lang.String) r0)     // Catch:{ all -> 0x014c }
            throw r9     // Catch:{ all -> 0x014c }
        L_0x014c:
            r8 = move-exception
            goto L_0x0154
        L_0x014e:
            r8 = move-exception
            r3 = r0
            goto L_0x0154
        L_0x0151:
            r8 = move-exception
            r6 = r0
            r3 = r6
        L_0x0154:
            if (r3 == 0) goto L_0x015e
            r3.close()     // Catch:{ IOException -> 0x015a }
            goto L_0x015e
        L_0x015a:
            r9 = move-exception
            r9.printStackTrace()
        L_0x015e:
            if (r7 == 0) goto L_0x0168
            r7.close()     // Catch:{ IOException -> 0x0164 }
            goto L_0x0168
        L_0x0164:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0168:
            if (r6 == 0) goto L_0x016d
            r6.disconnect()
        L_0x016d:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accountsdk.request.UploadFileRequest.execute(java.lang.String, java.io.InputStream, java.lang.String, java.lang.String):java.lang.String");
    }

    private static String getMIMEType(String str) {
        return (str.endsWith("png") || str.endsWith("PNG")) ? "image/png" : "image/jpg";
    }
}
