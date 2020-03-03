package com.xiaomi.mobilestats.data;

public class ReadFromFileThead extends Thread {
    private String cC;

    public ReadFromFileThead(String str) {
        this.cC = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0088 A[Catch:{ Exception -> 0x00bc }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r4 = this;
            super.run()
            java.lang.String r0 = r4.cC
            boolean r0 = com.xiaomi.mobilestats.common.StrUtil.isEmpty(r0)
            if (r0 != 0) goto L_0x00c0
            r0 = 0
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "client.json"
            boolean r1 = r1.endsWith(r2)     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x001f
            r0 = 9
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
        L_0x001a:
            com.xiaomi.mobilestats.object.Msg r0 = com.xiaomi.mobilestats.common.ProtoUtil.readProtoInfoFromFile(r0, r1)     // Catch:{ Exception -> 0x00bc }
            goto L_0x0068
        L_0x001f:
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "page.json"
            boolean r1 = r1.endsWith(r2)     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x002d
            r0 = 5
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            goto L_0x001a
        L_0x002d:
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "event.json"
            boolean r1 = r1.endsWith(r2)     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x003b
            r0 = 7
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            goto L_0x001a
        L_0x003b:
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "error.json"
            boolean r1 = r1.endsWith(r2)     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x004a
            r0 = 11
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            goto L_0x001a
        L_0x004a:
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "crash.json"
            boolean r1 = r1.endsWith(r2)     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x0059
            r0 = 13
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            goto L_0x001a
        L_0x0059:
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = "view.json"
            boolean r1 = r1.endsWith(r2)     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x0068
            r0 = 17
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            goto L_0x001a
        L_0x0068:
            java.lang.String r1 = "test"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
            r2.<init>()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r3 = r4.cC     // Catch:{ Exception -> 0x00bc }
            r2.append(r3)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r3 = " ReadFromFileThead"
            r2.append(r3)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00bc }
            android.util.Log.d(r1, r2)     // Catch:{ Exception -> 0x00bc }
            if (r0 == 0) goto L_0x00c0
            boolean r0 = r0.isFlag()     // Catch:{ Exception -> 0x00bc }
            if (r0 == 0) goto L_0x00c0
            java.lang.String r0 = "test"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
            r1.<init>()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = r4.cC     // Catch:{ Exception -> 0x00bc }
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r2 = " ReadFromFileThead　OK"
            r1.append(r2)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00bc }
            android.util.Log.d(r0, r1)     // Catch:{ Exception -> 0x00bc }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x00bc }
            java.lang.String r1 = r4.cC     // Catch:{ Exception -> 0x00bc }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00bc }
            boolean r1 = r0.exists()     // Catch:{ Exception -> 0x00bc }
            if (r1 == 0) goto L_0x00c0
            r0.delete()     // Catch:{ Exception -> 0x00bc }
            com.xiaomi.mobilestats.controller.FileLruCache r1 = com.xiaomi.mobilestats.controller.FileLruCache.getInstance()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x00bc }
            r1.removeFile(r0)     // Catch:{ Exception -> 0x00bc }
            goto L_0x00c0
        L_0x00bc:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.data.ReadFromFileThead.run():void");
    }
}
