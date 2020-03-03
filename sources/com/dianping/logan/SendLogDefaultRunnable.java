package com.dianping.logan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SendLogDefaultRunnable extends SendLogRunnable {
    private static final String c = "SendLogDefaultRunnable";
    private final Map<String, String> d = new HashMap();
    private String e;
    private SendLogCallback f;

    public void a(File file) {
        a(file, this.d, this.e);
        a();
        if (file.getName().contains(".copy")) {
            file.delete();
        }
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(Map<String, String> map) {
        this.d.clear();
        if (map != null) {
            this.d.putAll(map);
        }
    }

    public void a(SendLogCallback sendLogCallback) {
        this.f = sendLogCallback;
    }

    private void a(File file, Map<String, String> map, String str) {
        try {
            a(str, (InputStream) new FileInputStream(file), map);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0161, code lost:
        if (r0 == null) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0163, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x018d, code lost:
        if (r0 == null) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x01b5, code lost:
        if (r0 == null) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x01dd, code lost:
        if (r0 == null) goto L_0x01e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00f6, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00f7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00f8, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00fd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00fe, code lost:
        r6 = null;
        r8 = r0;
        r0 = r10;
        r10 = r12;
        r12 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0105, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0106, code lost:
        r6 = null;
        r8 = r0;
        r0 = r10;
        r10 = r12;
        r12 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x010d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x010e, code lost:
        r6 = null;
        r8 = r0;
        r0 = r10;
        r10 = r12;
        r12 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0115, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0116, code lost:
        r6 = null;
        r8 = r0;
        r0 = r10;
        r10 = r12;
        r12 = r8;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0145 A[SYNTHETIC, Splitter:B:103:0x0145] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x014f A[SYNTHETIC, Splitter:B:108:0x014f] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0159 A[SYNTHETIC, Splitter:B:113:0x0159] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0171 A[SYNTHETIC, Splitter:B:124:0x0171] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x017b A[SYNTHETIC, Splitter:B:129:0x017b] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0185 A[SYNTHETIC, Splitter:B:134:0x0185] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0199 A[SYNTHETIC, Splitter:B:144:0x0199] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01a3 A[SYNTHETIC, Splitter:B:149:0x01a3] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x01ad A[SYNTHETIC, Splitter:B:154:0x01ad] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x01c1 A[SYNTHETIC, Splitter:B:164:0x01c1] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x01cb A[SYNTHETIC, Splitter:B:169:0x01cb] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x01d5 A[SYNTHETIC, Splitter:B:174:0x01d5] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0205 A[SYNTHETIC, Splitter:B:185:0x0205] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x020f A[SYNTHETIC, Splitter:B:190:0x020f] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0219 A[SYNTHETIC, Splitter:B:195:0x0219] */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0223  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00f7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x005c] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:100:0x0140=Splitter:B:100:0x0140, B:141:0x0194=Splitter:B:141:0x0194, B:121:0x016c=Splitter:B:121:0x016c, B:161:0x01bc=Splitter:B:161:0x01bc} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r10, java.io.InputStream r11, java.util.Map<java.lang.String, java.lang.String> r12) {
        /*
            r9 = this;
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r1 = new byte[r0]
            r2 = -1
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ ProtocolException -> 0x01b8, MalformedURLException -> 0x0190, IOException -> 0x0168, Exception -> 0x013c, all -> 0x0137 }
            r4.<init>(r10)     // Catch:{ ProtocolException -> 0x01b8, MalformedURLException -> 0x0190, IOException -> 0x0168, Exception -> 0x013c, all -> 0x0137 }
            java.net.URLConnection r10 = r4.openConnection()     // Catch:{ ProtocolException -> 0x01b8, MalformedURLException -> 0x0190, IOException -> 0x0168, Exception -> 0x013c, all -> 0x0137 }
            java.net.HttpURLConnection r10 = (java.net.HttpURLConnection) r10     // Catch:{ ProtocolException -> 0x01b8, MalformedURLException -> 0x0190, IOException -> 0x0168, Exception -> 0x013c, all -> 0x0137 }
            boolean r4 = r10 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            if (r4 == 0) goto L_0x0020
            r4 = r10
            javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            com.dianping.logan.SendLogDefaultRunnable$1 r5 = new com.dianping.logan.SendLogDefaultRunnable$1     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            r5.<init>()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            r4.setHostnameVerifier(r5)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
        L_0x0020:
            java.util.Set r12 = r12.entrySet()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
        L_0x0028:
            boolean r4 = r12.hasNext()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            if (r4 == 0) goto L_0x0044
            java.lang.Object r4 = r12.next()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.lang.Object r5 = r4.getKey()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.lang.Object r4 = r4.getValue()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            r10.addRequestProperty(r5, r4)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            goto L_0x0028
        L_0x0044:
            r12 = 15000(0x3a98, float:2.102E-41)
            r10.setReadTimeout(r12)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            r10.setConnectTimeout(r12)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            r12 = 1
            r10.setDoInput(r12)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            r10.setDoOutput(r12)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.lang.String r12 = "POST"
            r10.setRequestMethod(r12)     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.io.OutputStream r12 = r10.getOutputStream()     // Catch:{ ProtocolException -> 0x0131, MalformedURLException -> 0x012b, IOException -> 0x0126, Exception -> 0x0121, all -> 0x011d }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
            r4.<init>(r0)     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
        L_0x0061:
            int r0 = r11.read(r1)     // Catch:{ all -> 0x00f2 }
            r5 = 0
            if (r0 == r2) goto L_0x006c
            r4.write(r1, r5, r0)     // Catch:{ all -> 0x00f2 }
            goto L_0x0061
        L_0x006c:
            byte[] r0 = r4.toByteArray()     // Catch:{ all -> 0x00f2 }
            r4.close()     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
            r12.write(r0)     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
            r12.flush()     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
            int r0 = r10.getResponseCode()     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
            int r4 = r0 / 100
            r6 = 2
            if (r4 != r6) goto L_0x00aa
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ ProtocolException -> 0x00ea, MalformedURLException -> 0x00e2, IOException -> 0x00da, Exception -> 0x00d2, all -> 0x00f7 }
            r4.<init>()     // Catch:{ ProtocolException -> 0x00ea, MalformedURLException -> 0x00e2, IOException -> 0x00da, Exception -> 0x00d2, all -> 0x00f7 }
            java.io.InputStream r6 = r10.getInputStream()     // Catch:{ ProtocolException -> 0x00ea, MalformedURLException -> 0x00e2, IOException -> 0x00da, Exception -> 0x00d2, all -> 0x00f7 }
        L_0x008b:
            int r7 = r6.read(r1)     // Catch:{ ProtocolException -> 0x00a7, MalformedURLException -> 0x00a4, IOException -> 0x00a1, Exception -> 0x009e, all -> 0x009b }
            if (r7 == r2) goto L_0x0095
            r4.write(r1, r5, r7)     // Catch:{ ProtocolException -> 0x00a7, MalformedURLException -> 0x00a4, IOException -> 0x00a1, Exception -> 0x009e, all -> 0x009b }
            goto L_0x008b
        L_0x0095:
            byte[] r1 = r4.toByteArray()     // Catch:{ ProtocolException -> 0x00a7, MalformedURLException -> 0x00a4, IOException -> 0x00a1, Exception -> 0x009e, all -> 0x009b }
            r3 = r6
            goto L_0x00ab
        L_0x009b:
            r0 = move-exception
            goto L_0x00f9
        L_0x009e:
            r1 = move-exception
            r2 = r0
            goto L_0x00d5
        L_0x00a1:
            r1 = move-exception
            r2 = r0
            goto L_0x00dd
        L_0x00a4:
            r1 = move-exception
            r2 = r0
            goto L_0x00e5
        L_0x00a7:
            r1 = move-exception
            r2 = r0
            goto L_0x00ed
        L_0x00aa:
            r1 = r3
        L_0x00ab:
            if (r12 == 0) goto L_0x00b5
            r12.close()     // Catch:{ IOException -> 0x00b1 }
            goto L_0x00b5
        L_0x00b1:
            r12 = move-exception
            r12.printStackTrace()
        L_0x00b5:
            if (r3 == 0) goto L_0x00bf
            r3.close()     // Catch:{ IOException -> 0x00bb }
            goto L_0x00bf
        L_0x00bb:
            r12 = move-exception
            r12.printStackTrace()
        L_0x00bf:
            if (r11 == 0) goto L_0x00c9
            r11.close()     // Catch:{ IOException -> 0x00c5 }
            goto L_0x00c9
        L_0x00c5:
            r11 = move-exception
            r11.printStackTrace()
        L_0x00c9:
            if (r10 == 0) goto L_0x00ce
            r10.disconnect()
        L_0x00ce:
            r2 = r0
            r3 = r1
            goto L_0x01e0
        L_0x00d2:
            r1 = move-exception
            r2 = r0
            r6 = r3
        L_0x00d5:
            r0 = r10
            r10 = r12
            r12 = r1
            goto L_0x0140
        L_0x00da:
            r1 = move-exception
            r2 = r0
            r6 = r3
        L_0x00dd:
            r0 = r10
            r10 = r12
            r12 = r1
            goto L_0x016c
        L_0x00e2:
            r1 = move-exception
            r2 = r0
            r6 = r3
        L_0x00e5:
            r0 = r10
            r10 = r12
            r12 = r1
            goto L_0x0194
        L_0x00ea:
            r1 = move-exception
            r2 = r0
            r6 = r3
        L_0x00ed:
            r0 = r10
            r10 = r12
            r12 = r1
            goto L_0x01bc
        L_0x00f2:
            r0 = move-exception
            r4.close()     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
            throw r0     // Catch:{ ProtocolException -> 0x0115, MalformedURLException -> 0x010d, IOException -> 0x0105, Exception -> 0x00fd, all -> 0x00f7 }
        L_0x00f7:
            r0 = move-exception
            r6 = r3
        L_0x00f9:
            r3 = r12
            r12 = r0
            goto L_0x0203
        L_0x00fd:
            r0 = move-exception
            r6 = r3
            r8 = r0
            r0 = r10
            r10 = r12
            r12 = r8
            goto L_0x0140
        L_0x0105:
            r0 = move-exception
            r6 = r3
            r8 = r0
            r0 = r10
            r10 = r12
            r12 = r8
            goto L_0x016c
        L_0x010d:
            r0 = move-exception
            r6 = r3
            r8 = r0
            r0 = r10
            r10 = r12
            r12 = r8
            goto L_0x0194
        L_0x0115:
            r0 = move-exception
            r6 = r3
            r8 = r0
            r0 = r10
            r10 = r12
            r12 = r8
            goto L_0x01bc
        L_0x011d:
            r12 = move-exception
            r6 = r3
            goto L_0x0203
        L_0x0121:
            r12 = move-exception
            r0 = r10
            r10 = r3
            r6 = r10
            goto L_0x0140
        L_0x0126:
            r12 = move-exception
            r0 = r10
            r10 = r3
            r6 = r10
            goto L_0x016c
        L_0x012b:
            r12 = move-exception
            r0 = r10
            r10 = r3
            r6 = r10
            goto L_0x0194
        L_0x0131:
            r12 = move-exception
            r0 = r10
            r10 = r3
            r6 = r10
            goto L_0x01bc
        L_0x0137:
            r12 = move-exception
            r10 = r3
            r6 = r10
            goto L_0x0203
        L_0x013c:
            r12 = move-exception
            r10 = r3
            r0 = r10
            r6 = r0
        L_0x0140:
            r12.printStackTrace()     // Catch:{ all -> 0x0200 }
            if (r10 == 0) goto L_0x014d
            r10.close()     // Catch:{ IOException -> 0x0149 }
            goto L_0x014d
        L_0x0149:
            r10 = move-exception
            r10.printStackTrace()
        L_0x014d:
            if (r6 == 0) goto L_0x0157
            r6.close()     // Catch:{ IOException -> 0x0153 }
            goto L_0x0157
        L_0x0153:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0157:
            if (r11 == 0) goto L_0x0161
            r11.close()     // Catch:{ IOException -> 0x015d }
            goto L_0x0161
        L_0x015d:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0161:
            if (r0 == 0) goto L_0x01e0
        L_0x0163:
            r0.disconnect()
            goto L_0x01e0
        L_0x0168:
            r12 = move-exception
            r10 = r3
            r0 = r10
            r6 = r0
        L_0x016c:
            r12.printStackTrace()     // Catch:{ all -> 0x0200 }
            if (r10 == 0) goto L_0x0179
            r10.close()     // Catch:{ IOException -> 0x0175 }
            goto L_0x0179
        L_0x0175:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0179:
            if (r6 == 0) goto L_0x0183
            r6.close()     // Catch:{ IOException -> 0x017f }
            goto L_0x0183
        L_0x017f:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0183:
            if (r11 == 0) goto L_0x018d
            r11.close()     // Catch:{ IOException -> 0x0189 }
            goto L_0x018d
        L_0x0189:
            r10 = move-exception
            r10.printStackTrace()
        L_0x018d:
            if (r0 == 0) goto L_0x01e0
            goto L_0x0163
        L_0x0190:
            r12 = move-exception
            r10 = r3
            r0 = r10
            r6 = r0
        L_0x0194:
            r12.printStackTrace()     // Catch:{ all -> 0x0200 }
            if (r10 == 0) goto L_0x01a1
            r10.close()     // Catch:{ IOException -> 0x019d }
            goto L_0x01a1
        L_0x019d:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01a1:
            if (r6 == 0) goto L_0x01ab
            r6.close()     // Catch:{ IOException -> 0x01a7 }
            goto L_0x01ab
        L_0x01a7:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01ab:
            if (r11 == 0) goto L_0x01b5
            r11.close()     // Catch:{ IOException -> 0x01b1 }
            goto L_0x01b5
        L_0x01b1:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01b5:
            if (r0 == 0) goto L_0x01e0
            goto L_0x0163
        L_0x01b8:
            r12 = move-exception
            r10 = r3
            r0 = r10
            r6 = r0
        L_0x01bc:
            r12.printStackTrace()     // Catch:{ all -> 0x0200 }
            if (r10 == 0) goto L_0x01c9
            r10.close()     // Catch:{ IOException -> 0x01c5 }
            goto L_0x01c9
        L_0x01c5:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01c9:
            if (r6 == 0) goto L_0x01d3
            r6.close()     // Catch:{ IOException -> 0x01cf }
            goto L_0x01d3
        L_0x01cf:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01d3:
            if (r11 == 0) goto L_0x01dd
            r11.close()     // Catch:{ IOException -> 0x01d9 }
            goto L_0x01dd
        L_0x01d9:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01dd:
            if (r0 == 0) goto L_0x01e0
            goto L_0x0163
        L_0x01e0:
            java.lang.String r10 = "SendLogDefaultRunnable"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "log send completed, http statusCode : "
            r11.append(r12)
            r11.append(r2)
            java.lang.String r11 = r11.toString()
            android.util.Log.d(r10, r11)
            com.dianping.logan.SendLogCallback r10 = r9.f
            if (r10 == 0) goto L_0x01ff
            com.dianping.logan.SendLogCallback r10 = r9.f
            r10.a(r2, r3)
        L_0x01ff:
            return
        L_0x0200:
            r12 = move-exception
            r3 = r10
            r10 = r0
        L_0x0203:
            if (r3 == 0) goto L_0x020d
            r3.close()     // Catch:{ IOException -> 0x0209 }
            goto L_0x020d
        L_0x0209:
            r0 = move-exception
            r0.printStackTrace()
        L_0x020d:
            if (r6 == 0) goto L_0x0217
            r6.close()     // Catch:{ IOException -> 0x0213 }
            goto L_0x0217
        L_0x0213:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0217:
            if (r11 == 0) goto L_0x0221
            r11.close()     // Catch:{ IOException -> 0x021d }
            goto L_0x0221
        L_0x021d:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0221:
            if (r10 == 0) goto L_0x0226
            r10.disconnect()
        L_0x0226:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dianping.logan.SendLogDefaultRunnable.a(java.lang.String, java.io.InputStream, java.util.Map):void");
    }
}
