package com.mi.global.shop.util;

import android.os.Handler;

public class DownloadPluginTask {
    public static final int b = 1;

    /* renamed from: a  reason: collision with root package name */
    public volatile boolean f7092a = false;
    private Handler c;

    public DownloadPluginTask(Handler handler) {
        this.c = handler;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [java.net.URLConnection] */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r9 = a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e8, code lost:
        if (r9 == null) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ee, code lost:
        if (r9.equals(r2) == false) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f0, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f3, code lost:
        if (r7 == null) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f5, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f8, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fb, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r6.delete();
        r4.disconnect();
        r2 = (java.net.HttpURLConnection) r3.openConnection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r3 = new android.os.Message();
        r3.what = 1;
        r3.arg1 = 0;
        r1.c.sendMessage(r3);
        com.mi.util.MiToast.a((android.content.Context) com.mi.global.shop.ShopApp.g(), (java.lang.CharSequence) com.mi.global.shop.ShopApp.g().getResources().getString(com.mi.global.shop.R.string.shop_download_plugin_again), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x012b, code lost:
        if (r7 == null) goto L_0x0130;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012d, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0130, code lost:
        r8.close();
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0135, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0136, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x013d A[SYNTHETIC, Splitter:B:55:0x013d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r18, java.lang.String r19, java.lang.String r20) throws java.io.IOException, java.lang.NullPointerException {
        /*
            r17 = this;
            r1 = r17
            r0 = r19
            r2 = r20
            java.net.URL r3 = new java.net.URL
            r4 = r18
            r3.<init>(r4)
            java.net.URLConnection r4 = r3.openConnection()
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
            int r5 = r4.getContentLength()
            java.io.File r6 = new java.io.File
            r6.<init>(r0)
            boolean r7 = r6.exists()
            r8 = 0
            r10 = -1
            r11 = 1
            if (r7 == 0) goto L_0x0056
            long r12 = r6.length()
            int r7 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r7 <= 0) goto L_0x0056
            if (r5 == r10) goto L_0x0039
            long r12 = (long) r5
            long r14 = r6.length()
            int r7 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r7 != 0) goto L_0x0056
        L_0x0039:
            java.lang.String r7 = r1.a((java.lang.String) r0)
            if (r7 == 0) goto L_0x0056
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x0056
            android.os.Message r0 = new android.os.Message
            r0.<init>()
            r0.what = r11
            r2 = 100
            r0.arg1 = r2
            android.os.Handler r2 = r1.c
            r2.sendMessage(r0)
            return r11
        L_0x0056:
            boolean r7 = r6.exists()
            r12 = 10240(0x2800, float:1.4349E-41)
            r13 = 0
            if (r7 == 0) goto L_0x0154
            long r14 = r6.length()
            int r7 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r7 <= 0) goto L_0x0154
            long r7 = r6.length()
            long r14 = (long) r5
            int r9 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r9 >= 0) goto L_0x0154
            r4.disconnect()
            java.net.URLConnection r4 = r3.openConnection()
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
            java.lang.String r7 = "RANGE"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "bytes="
            r8.append(r9)
            long r14 = r6.length()
            r8.append(r14)
            java.lang.String r9 = "-"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r4.setRequestProperty(r7, r8)
            int r7 = r4.getResponseCode()
            r8 = 206(0xce, float:2.89E-43)
            if (r7 != r8) goto L_0x014a
            java.io.InputStream r7 = r4.getInputStream()
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile
            java.lang.String r9 = "rw"
            r8.<init>(r6, r9)
            long r14 = r6.length()
            r8.seek(r14)
            long r14 = r6.length()     // Catch:{ IOException -> 0x013a }
            int r9 = (int) r14     // Catch:{ IOException -> 0x013a }
            byte[] r14 = new byte[r12]     // Catch:{ IOException -> 0x013a }
        L_0x00b9:
            int r15 = r7.read(r14)     // Catch:{ IOException -> 0x013a }
            if (r15 == r10) goto L_0x00e4
            int r9 = r9 + r15
            r8.write(r14, r13, r15)     // Catch:{ IOException -> 0x013a }
            android.os.Message r15 = new android.os.Message     // Catch:{ IOException -> 0x013a }
            r15.<init>()     // Catch:{ IOException -> 0x013a }
            r15.what = r11     // Catch:{ IOException -> 0x013a }
            int r16 = r9 * 100
            int r10 = r16 / r5
            r15.arg1 = r10     // Catch:{ IOException -> 0x013a }
            android.os.Handler r10 = r1.c     // Catch:{ IOException -> 0x013a }
            r10.sendMessage(r15)     // Catch:{ IOException -> 0x013a }
            boolean r10 = r1.f7092a     // Catch:{ IOException -> 0x013a }
            if (r10 == 0) goto L_0x00e2
            if (r7 == 0) goto L_0x00de
            r7.close()
        L_0x00de:
            r8.close()
            return r13
        L_0x00e2:
            r10 = -1
            goto L_0x00b9
        L_0x00e4:
            java.lang.String r9 = r1.a((java.lang.String) r0)     // Catch:{ IOException -> 0x013a }
            if (r9 == 0) goto L_0x00fc
            boolean r2 = r9.equals(r2)     // Catch:{ IOException -> 0x013a }
            if (r2 == 0) goto L_0x00fc
            r4.disconnect()     // Catch:{ IOException -> 0x013a }
            if (r7 == 0) goto L_0x00f8
            r7.close()
        L_0x00f8:
            r8.close()
            return r11
        L_0x00fc:
            r6.delete()     // Catch:{ IOException -> 0x013a }
            r4.disconnect()     // Catch:{ IOException -> 0x013a }
            java.net.URLConnection r2 = r3.openConnection()     // Catch:{ IOException -> 0x013a }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x013a }
            android.os.Message r3 = new android.os.Message     // Catch:{ IOException -> 0x0135 }
            r3.<init>()     // Catch:{ IOException -> 0x0135 }
            r3.what = r11     // Catch:{ IOException -> 0x0135 }
            r3.arg1 = r13     // Catch:{ IOException -> 0x0135 }
            android.os.Handler r4 = r1.c     // Catch:{ IOException -> 0x0135 }
            r4.sendMessage(r3)     // Catch:{ IOException -> 0x0135 }
            android.app.Application r3 = com.mi.global.shop.ShopApp.g()     // Catch:{ IOException -> 0x0135 }
            android.app.Application r4 = com.mi.global.shop.ShopApp.g()     // Catch:{ IOException -> 0x0135 }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ IOException -> 0x0135 }
            int r6 = com.mi.global.shop.R.string.shop_download_plugin_again     // Catch:{ IOException -> 0x0135 }
            java.lang.String r4 = r4.getString(r6)     // Catch:{ IOException -> 0x0135 }
            com.mi.util.MiToast.a((android.content.Context) r3, (java.lang.CharSequence) r4, (int) r13)     // Catch:{ IOException -> 0x0135 }
            if (r7 == 0) goto L_0x0130
            r7.close()
        L_0x0130:
            r8.close()
            r4 = r2
            goto L_0x0154
        L_0x0135:
            r0 = move-exception
            r4 = r2
            goto L_0x013b
        L_0x0138:
            r0 = move-exception
            goto L_0x0141
        L_0x013a:
            r0 = move-exception
        L_0x013b:
            if (r4 == 0) goto L_0x0140
            r4.disconnect()     // Catch:{ all -> 0x0138 }
        L_0x0140:
            throw r0     // Catch:{ all -> 0x0138 }
        L_0x0141:
            if (r7 == 0) goto L_0x0146
            r7.close()
        L_0x0146:
            r8.close()
            throw r0
        L_0x014a:
            r4.disconnect()
            java.net.URLConnection r2 = r3.openConnection()
            r4 = r2
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4
        L_0x0154:
            java.io.InputStream r2 = r4.getInputStream()
            java.io.FileOutputStream r3 = new java.io.FileOutputStream
            r3.<init>(r0)
            byte[] r0 = new byte[r12]     // Catch:{ all -> 0x019c }
            r6 = 0
        L_0x0160:
            int r7 = r2.read(r0)     // Catch:{ all -> 0x019c }
            r8 = -1
            if (r7 == r8) goto L_0x018e
            int r6 = r6 + r7
            r3.write(r0, r13, r7)     // Catch:{ all -> 0x019c }
            android.os.Message r7 = new android.os.Message     // Catch:{ all -> 0x019c }
            r7.<init>()     // Catch:{ all -> 0x019c }
            r7.what = r11     // Catch:{ all -> 0x019c }
            int r9 = r6 * 100
            int r9 = r9 / r5
            r7.arg1 = r9     // Catch:{ all -> 0x019c }
            android.os.Handler r9 = r1.c     // Catch:{ all -> 0x019c }
            r9.sendMessage(r7)     // Catch:{ all -> 0x019c }
            boolean r7 = r1.f7092a     // Catch:{ all -> 0x019c }
            if (r7 == 0) goto L_0x0160
            if (r2 == 0) goto L_0x0185
            r2.close()
        L_0x0185:
            r3.close()
            if (r4 == 0) goto L_0x018d
            r4.disconnect()
        L_0x018d:
            return r13
        L_0x018e:
            if (r2 == 0) goto L_0x0193
            r2.close()
        L_0x0193:
            r3.close()
            if (r4 == 0) goto L_0x019b
            r4.disconnect()
        L_0x019b:
            return r11
        L_0x019c:
            r0 = move-exception
            if (r2 == 0) goto L_0x01a2
            r2.close()
        L_0x01a2:
            r3.close()
            if (r4 == 0) goto L_0x01aa
            r4.disconnect()
        L_0x01aa:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.DownloadPluginTask.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0031 A[SYNTHETIC, Splitter:B:20:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0038 A[SYNTHETIC, Splitter:B:28:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0035, all -> 0x002d }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0035, all -> 0x002d }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x0036, all -> 0x002b }
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch:{ Exception -> 0x0036, all -> 0x002b }
            r3 = 0
            r4 = 0
        L_0x0012:
            r5 = -1
            if (r4 == r5) goto L_0x001f
            int r4 = r1.read(r7)     // Catch:{ Exception -> 0x0036, all -> 0x002b }
            if (r4 <= 0) goto L_0x0012
            r2.update(r7, r3, r4)     // Catch:{ Exception -> 0x0036, all -> 0x002b }
            goto L_0x0012
        L_0x001f:
            byte[] r7 = r2.digest()     // Catch:{ Exception -> 0x0036, all -> 0x002b }
            java.lang.String r7 = a((byte[]) r7)     // Catch:{ Exception -> 0x0036, all -> 0x002b }
            r1.close()     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            return r7
        L_0x002b:
            r7 = move-exception
            goto L_0x002f
        L_0x002d:
            r7 = move-exception
            r1 = r0
        L_0x002f:
            if (r1 == 0) goto L_0x0034
            r1.close()     // Catch:{ Exception -> 0x0034 }
        L_0x0034:
            throw r7
        L_0x0035:
            r1 = r0
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.DownloadPluginTask.a(java.lang.String):java.lang.String");
    }

    public static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = (bArr[i] >>> 4) & 15;
            int i2 = 0;
            while (true) {
                if (b2 < 0 || b2 > 9) {
                    stringBuffer.append((char) ((b2 - 10) + 97));
                } else {
                    stringBuffer.append((char) (b2 + 48));
                }
                b2 = bArr[i] & 15;
                int i3 = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i3;
            }
        }
        return stringBuffer.toString();
    }

    public void a() {
        this.f7092a = true;
    }
}
