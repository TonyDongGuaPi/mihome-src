package com.tencent.smtt.utils;

import com.tencent.smtt.utils.d;

final class i extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9208a;
    final /* synthetic */ d.a b;

    i(String str, d.a aVar) {
        this.f9208a = str;
        this.b = aVar;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:38:0x0081=Splitter:B:38:0x0081, B:16:0x004f=Splitter:B:16:0x004f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r9 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            java.lang.String r2 = "http://soft.tbs.imtt.qq.com/17421/tbs_res_imtt_tbs_DebugPlugin_DebugPlugin.tbs"
            r1.<init>(r2)     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            int r2 = r1.getContentLength()     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r3 = 5000(0x1388, float:7.006E-42)
            r1.setConnectTimeout(r3)     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r1.connect()     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            java.lang.String r4 = r9.f9208a     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            java.io.FileOutputStream r3 = com.tencent.smtt.utils.j.d((java.io.File) r3)     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r4 = 0
            r5 = 0
        L_0x002f:
            int r6 = r1.read(r0)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            if (r6 <= 0) goto L_0x0042
            int r5 = r5 + r6
            r3.write(r0, r4, r6)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            int r6 = r5 * 100
            int r6 = r6 / r2
            com.tencent.smtt.utils.d$a r7 = r9.b     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r7.a((int) r6)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            goto L_0x002f
        L_0x0042:
            com.tencent.smtt.utils.d$a r0 = r9.b     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r0.a()     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r1.close()     // Catch:{ Exception -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004f:
            r3.close()     // Catch:{ Exception -> 0x0053 }
            goto L_0x0084
        L_0x0053:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0084
        L_0x0058:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0086
        L_0x005d:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0071
        L_0x0062:
            r2 = move-exception
            r3 = r0
            r0 = r1
            r1 = r2
            goto L_0x0086
        L_0x0067:
            r2 = move-exception
            r3 = r0
            r0 = r1
            r1 = r2
            goto L_0x0071
        L_0x006c:
            r1 = move-exception
            r3 = r0
            goto L_0x0086
        L_0x006f:
            r1 = move-exception
            r3 = r0
        L_0x0071:
            r1.printStackTrace()     // Catch:{ all -> 0x0085 }
            com.tencent.smtt.utils.d$a r2 = r9.b     // Catch:{ all -> 0x0085 }
            r2.a((java.lang.Throwable) r1)     // Catch:{ all -> 0x0085 }
            r0.close()     // Catch:{ Exception -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0081:
            r3.close()     // Catch:{ Exception -> 0x0053 }
        L_0x0084:
            return
        L_0x0085:
            r1 = move-exception
        L_0x0086:
            r0.close()     // Catch:{ Exception -> 0x008a }
            goto L_0x008e
        L_0x008a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x008e:
            r3.close()     // Catch:{ Exception -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0096:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.i.run():void");
    }
}
