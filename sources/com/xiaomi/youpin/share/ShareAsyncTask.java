package com.xiaomi.youpin.share;

import android.os.AsyncTask;

public class ShareAsyncTask extends AsyncTask<String, Void, ShareObject> {

    /* renamed from: a  reason: collision with root package name */
    private String f23665a = getClass().getSimpleName();

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01ec A[Catch:{ Exception -> 0x0236 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.youpin.share.ShareObject doInBackground(java.lang.String... r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x023e
            int r1 = r9.length
            r2 = 1
            if (r1 == r2) goto L_0x0009
            goto L_0x023e
        L_0x0009:
            com.xiaomi.youpin.share.ShareObject r1 = new com.xiaomi.youpin.share.ShareObject
            r1.<init>()
            r3 = 0
            r9 = r9[r3]
            boolean r4 = android.text.TextUtils.isEmpty(r9)
            if (r4 == 0) goto L_0x0018
            return r0
        L_0x0018:
            android.net.Uri r4 = android.net.Uri.parse(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = r8.f23665a     // Catch:{ Exception -> 0x0236 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0236 }
            r6.<init>()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r7 = "query: "
            r6.append(r7)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r7 = r4.getQuery()     // Catch:{ Exception -> 0x0236 }
            r6.append(r7)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0236 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "title"
            java.lang.String r5 = r4.getQueryParameter(r5)     // Catch:{ Exception -> 0x0236 }
            r1.f(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "content"
            java.lang.String r5 = r4.getQueryParameter(r5)     // Catch:{ Exception -> 0x0236 }
            r1.g(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "wbContent"
            java.lang.String r5 = r4.getQueryParameter(r5)     // Catch:{ Exception -> 0x0236 }
            r1.h(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "pics"
            java.lang.String r5 = r4.getQueryParameter(r5)     // Catch:{ Exception -> 0x0236 }
            r1.j(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "url"
            java.lang.String r5 = r4.getQueryParameter(r5)     // Catch:{ Exception -> 0x0236 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0236 }
            if (r6 != 0) goto L_0x006a
            r1.i(r5)     // Catch:{ Exception -> 0x0236 }
            goto L_0x006d
        L_0x006a:
            r1.i(r9)     // Catch:{ Exception -> 0x0236 }
        L_0x006d:
            java.lang.String r9 = "ml"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.l(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "wx"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.k(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "pyq"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.m(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "wb"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.n(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "pyqTitle"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.b((java.lang.String) r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "pyqContent"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.c(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "wbTitle"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.d(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "wbContent"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            r1.e(r9)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = "miniProgramPath"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            boolean r5 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x0236 }
            if (r5 != 0) goto L_0x00c4
            r1.a((java.lang.String) r9)     // Catch:{ Exception -> 0x0236 }
        L_0x00c4:
            java.lang.String r9 = "liteId"
            java.lang.String r9 = r4.getQueryParameter(r9)     // Catch:{ Exception -> 0x0236 }
            boolean r5 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x0236 }
            if (r5 != 0) goto L_0x00d3
            r1.p(r9)     // Catch:{ Exception -> 0x0236 }
        L_0x00d3:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0236 }
            r9.<init>()     // Catch:{ Exception -> 0x0236 }
            com.xiaomi.youpin.share.config.ShareConfig r5 = com.xiaomi.youpin.share.config.YouPinShareApi.a()     // Catch:{ Exception -> 0x0236 }
            android.content.Context r5 = r5.e()     // Catch:{ Exception -> 0x0236 }
            java.io.File r5 = r5.getCacheDir()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = r5.getPath()     // Catch:{ Exception -> 0x0236 }
            r9.append(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = java.io.File.separator     // Catch:{ Exception -> 0x0236 }
            r9.append(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "share"
            r9.append(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = "pic"
            java.lang.String r5 = r4.getQueryParameter(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r6 = "pics"
            java.lang.String r4 = r4.getQueryParameter(r6)     // Catch:{ Exception -> 0x0236 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0236 }
            if (r6 != 0) goto L_0x0148
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0236 }
            r2.<init>(r9)     // Catch:{ Exception -> 0x0236 }
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x0236 }
            if (r3 != 0) goto L_0x0119
            r2.mkdir()     // Catch:{ Exception -> 0x0236 }
        L_0x0119:
            r1.j(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r2 = com.xiaomi.youpin.common.util.crypto.MD5Utils.d(r5)     // Catch:{ Exception -> 0x0236 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0236 }
            r3.<init>(r9, r2)     // Catch:{ Exception -> 0x0236 }
            boolean r9 = r3.exists()     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x012e
            r3.delete()     // Catch:{ Exception -> 0x0236 }
        L_0x012e:
            java.lang.String r9 = r1.j()     // Catch:{ Exception -> 0x0236 }
            boolean r9 = com.xiaomi.youpin.youpin_network.http.HttpApi.a((java.lang.String) r9, (java.io.File) r3)     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x022c
            android.net.Uri r9 = android.net.Uri.fromFile(r3)     // Catch:{ Exception -> 0x0236 }
            r1.a((android.net.Uri) r9)     // Catch:{ Exception -> 0x0236 }
            android.net.Uri r9 = android.net.Uri.fromFile(r3)     // Catch:{ Exception -> 0x0236 }
            r1.b((android.net.Uri) r9)     // Catch:{ Exception -> 0x0236 }
            goto L_0x022c
        L_0x0148:
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x0236 }
            if (r5 != 0) goto L_0x022c
            r1.j(r4)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r4 = com.xiaomi.youpin.common.util.crypto.MD5Utils.d(r4)     // Catch:{ Exception -> 0x0236 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0236 }
            r5.<init>()     // Catch:{ Exception -> 0x0236 }
            r5.append(r4)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r6 = "dir"
            r5.append(r6)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0236 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0236 }
            r6.<init>(r9, r5)     // Catch:{ Exception -> 0x0236 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0236 }
            r5.<init>(r9, r4)     // Catch:{ Exception -> 0x0236 }
            boolean r9 = r6.exists()     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x01a7
            boolean r9 = r6.isDirectory()     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x01a7
            java.io.File[] r9 = r6.listFiles()     // Catch:{ Exception -> 0x0236 }
            int r9 = r9.length     // Catch:{ Exception -> 0x0236 }
            r4 = 2
            if (r9 >= r4) goto L_0x01a7
            java.lang.String r9 = r8.f23665a     // Catch:{ Exception -> 0x0236 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0236 }
            r4.<init>()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r7 = "file dir destroied: "
            r4.append(r7)     // Catch:{ Exception -> 0x0236 }
            r4.append(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0236 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r9, (java.lang.String) r4)     // Catch:{ Exception -> 0x0236 }
            r6.delete()     // Catch:{ Exception -> 0x0236 }
            boolean r9 = r5.exists()     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x01a8
            r5.delete()     // Catch:{ Exception -> 0x0236 }
            goto L_0x01a8
        L_0x01a7:
            r2 = 0
        L_0x01a8:
            if (r2 != 0) goto L_0x01c8
            boolean r9 = r6.exists()     // Catch:{ Exception -> 0x0236 }
            if (r9 != 0) goto L_0x01b1
            goto L_0x01c8
        L_0x01b1:
            java.lang.String r9 = r8.f23665a     // Catch:{ Exception -> 0x0236 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0236 }
            r2.<init>()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r4 = "file exists: "
            r2.append(r4)     // Catch:{ Exception -> 0x0236 }
            r2.append(r5)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0236 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r9, (java.lang.String) r2)     // Catch:{ Exception -> 0x0236 }
            goto L_0x01d8
        L_0x01c8:
            r6.mkdirs()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r9 = r1.j()     // Catch:{ Exception -> 0x0236 }
            boolean r9 = com.xiaomi.youpin.youpin_network.http.HttpApi.a((java.lang.String) r9, (java.io.File) r5)     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x01d8
            com.xiaomi.youpin.common.util.ZipUtils.b((java.io.File) r5, (java.io.File) r6)     // Catch:{ Exception -> 0x0236 }
        L_0x01d8:
            boolean r9 = r6.exists()     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x022c
            boolean r9 = r6.isDirectory()     // Catch:{ Exception -> 0x0236 }
            if (r9 == 0) goto L_0x022c
            java.io.File[] r9 = r6.listFiles()     // Catch:{ Exception -> 0x0236 }
            int r2 = r9.length     // Catch:{ Exception -> 0x0236 }
            r4 = 0
        L_0x01ea:
            if (r4 >= r2) goto L_0x022c
            r5 = r9[r4]     // Catch:{ Exception -> 0x0236 }
            java.lang.String r6 = r5.getName()     // Catch:{ Exception -> 0x0236 }
            java.lang.String r7 = "."
            int r7 = r6.lastIndexOf(r7)     // Catch:{ Exception -> 0x0236 }
            if (r7 <= 0) goto L_0x0229
            java.lang.String r6 = r6.substring(r3, r7)     // Catch:{ Exception -> 0x0236 }
            java.lang.String r7 = "thumb"
            boolean r7 = android.text.TextUtils.equals(r6, r7)     // Catch:{ Exception -> 0x0236 }
            if (r7 == 0) goto L_0x020e
            android.net.Uri r5 = android.net.Uri.fromFile(r5)     // Catch:{ Exception -> 0x0236 }
            r1.a((android.net.Uri) r5)     // Catch:{ Exception -> 0x0236 }
            goto L_0x0229
        L_0x020e:
            java.lang.String r7 = "pic"
            boolean r6 = android.text.TextUtils.equals(r6, r7)     // Catch:{ Exception -> 0x0236 }
            if (r6 == 0) goto L_0x021e
            android.net.Uri r5 = android.net.Uri.fromFile(r5)     // Catch:{ Exception -> 0x0236 }
            r1.b((android.net.Uri) r5)     // Catch:{ Exception -> 0x0236 }
            goto L_0x0229
        L_0x021e:
            java.util.ArrayList r6 = r1.m()     // Catch:{ Exception -> 0x0236 }
            android.net.Uri r5 = android.net.Uri.fromFile(r5)     // Catch:{ Exception -> 0x0236 }
            r6.add(r5)     // Catch:{ Exception -> 0x0236 }
        L_0x0229:
            int r4 = r4 + 1
            goto L_0x01ea
        L_0x022c:
            java.lang.String r9 = r8.f23665a     // Catch:{ Exception -> 0x0236 }
            java.lang.String r2 = r1.toString()     // Catch:{ Exception -> 0x0236 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r9, (java.lang.String) r2)     // Catch:{ Exception -> 0x0236 }
            return r1
        L_0x0236:
            java.lang.String r9 = r8.f23665a
            java.lang.String r1 = "Dealing share object failed!"
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r9, (java.lang.String) r1)
            return r0
        L_0x023e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.share.ShareAsyncTask.doInBackground(java.lang.String[]):com.xiaomi.youpin.share.ShareObject");
    }
}
