package com.sina.weibo.sdk.share;

public final class ShareUtils {
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b7 A[SYNTHETIC, Splitter:B:32:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c1 A[Catch:{ Exception -> 0x0151, all -> 0x014e }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x011c A[Catch:{ Exception -> 0x0143, all -> 0x0140 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x012f A[Catch:{ Exception -> 0x013e }, LOOP:0: B:51:0x0128->B:53:0x012f, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x014a A[SYNTHETIC, Splitter:B:67:0x014a] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x015f A[SYNTHETIC, Splitter:B:78:0x015f] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0164 A[Catch:{ Exception -> 0x0167 }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x016c A[SYNTHETIC, Splitter:B:87:0x016c] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0171 A[Catch:{ Exception -> 0x0174 }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0133 A[EDGE_INSN: B:93:0x0133->B:54:0x0133 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String copyFileToWeiboTem(android.content.Context r11, android.net.Uri r12, int r13) {
        /*
            r0 = 0
            com.sina.weibo.sdk.auth.WbAppInfo r1 = com.sina.weibo.sdk.WeiboAppManager.b(r11)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            if (r2 == 0) goto L_0x0011
            java.lang.String r1 = "com.sina.weibo"
        L_0x0011:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r2.<init>()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r3 = "/Android/data/"
            r2.append(r3)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r2.append(r1)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r1 = "/files/.composerTem/"
            r2.append(r1)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r3.<init>()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.io.File r4 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r4 = r4.getAbsolutePath()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r3.append(r4)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r3.append(r1)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r2.mkdirs()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.util.Calendar r2 = java.util.Calendar.getInstance()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r3 = 0
            java.lang.String r4 = r12.getScheme()     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            java.lang.String r5 = "file"
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            if (r4 == 0) goto L_0x0071
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            r4.<init>()     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            long r5 = r2.getTimeInMillis()     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            r4.append(r5)     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            java.lang.String r2 = r12.getLastPathSegment()     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            r4.append(r2)     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            r4 = r2
            r2 = r0
            goto L_0x00a0
        L_0x0071:
            android.content.ContentResolver r4 = r11.getContentResolver()     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            java.lang.String r2 = "_display_name"
            r6[r3] = r2     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r12
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x00aa, all -> 0x00a6 }
            if (r2 == 0) goto L_0x009f
            boolean r4 = r2.moveToFirst()     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            if (r4 == 0) goto L_0x009f
            java.lang.String r4 = "_display_name"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            java.lang.String r4 = r2.getString(r4)     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            goto L_0x00a0
        L_0x0097:
            r11 = move-exception
            goto L_0x0148
        L_0x009a:
            r4 = move-exception
            r10 = r4
            r4 = r2
            r2 = r10
            goto L_0x00ac
        L_0x009f:
            r4 = r0
        L_0x00a0:
            if (r2 == 0) goto L_0x00bb
            r2.close()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            goto L_0x00bb
        L_0x00a6:
            r11 = move-exception
            r2 = r0
            goto L_0x0148
        L_0x00aa:
            r2 = move-exception
            r4 = r0
        L_0x00ac:
            java.lang.String r5 = "weibo sdk rename"
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0146 }
            android.util.Log.v(r5, r2)     // Catch:{ all -> 0x0146 }
            if (r4 == 0) goto L_0x00ba
            r4.close()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
        L_0x00ba:
            r4 = r0
        L_0x00bb:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            if (r2 == 0) goto L_0x00df
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r2.<init>()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.util.Calendar r4 = java.util.Calendar.getInstance()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            long r4 = r4.getTimeInMillis()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r2.append(r4)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            if (r13 != 0) goto L_0x00d6
            java.lang.String r13 = "_sdk_temp.mp4"
            goto L_0x00d8
        L_0x00d6:
            java.lang.String r13 = "_sdk_temp.jpg"
        L_0x00d8:
            r2.append(r13)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r4 = r2.toString()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
        L_0x00df:
            android.content.ContentResolver r11 = r11.getContentResolver()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.lang.String r13 = "r"
            android.os.ParcelFileDescriptor r11 = r11.openFileDescriptor(r12, r13)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.io.FileDescriptor r11 = r11.getFileDescriptor()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.io.BufferedInputStream r12 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.io.FileInputStream r13 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r13.<init>(r11)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            r12.<init>(r13)     // Catch:{ Exception -> 0x0151, all -> 0x014e }
            java.io.File r11 = new java.io.File     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r13.<init>()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r13.append(r2)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r13.append(r1)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r13.append(r4)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r11.<init>(r13)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            boolean r13 = r11.exists()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            if (r13 == 0) goto L_0x011f
            r11.delete()     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
        L_0x011f:
            java.io.FileOutputStream r13 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r13.<init>(r11)     // Catch:{ Exception -> 0x0143, all -> 0x0140 }
            r1 = 1444(0x5a4, float:2.023E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x013e }
        L_0x0128:
            int r2 = r12.read(r1)     // Catch:{ Exception -> 0x013e }
            r4 = -1
            if (r2 == r4) goto L_0x0133
            r13.write(r1, r3, r2)     // Catch:{ Exception -> 0x013e }
            goto L_0x0128
        L_0x0133:
            java.lang.String r11 = r11.getPath()     // Catch:{ Exception -> 0x013e }
            r12.close()     // Catch:{ Exception -> 0x013d }
            r13.close()     // Catch:{ Exception -> 0x013d }
        L_0x013d:
            return r11
        L_0x013e:
            r11 = move-exception
            goto L_0x0154
        L_0x0140:
            r11 = move-exception
            r13 = r0
            goto L_0x0169
        L_0x0143:
            r11 = move-exception
            r13 = r0
            goto L_0x0154
        L_0x0146:
            r11 = move-exception
            r2 = r4
        L_0x0148:
            if (r2 == 0) goto L_0x014d
            r2.close()     // Catch:{ Exception -> 0x0151, all -> 0x014e }
        L_0x014d:
            throw r11     // Catch:{ Exception -> 0x0151, all -> 0x014e }
        L_0x014e:
            r11 = move-exception
            r13 = r0
            goto L_0x016a
        L_0x0151:
            r11 = move-exception
            r12 = r0
            r13 = r12
        L_0x0154:
            java.lang.String r1 = "weibo sdk copy"
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0168 }
            com.sina.weibo.sdk.utils.LogUtil.c(r1, r11)     // Catch:{ all -> 0x0168 }
            if (r12 == 0) goto L_0x0162
            r12.close()     // Catch:{ Exception -> 0x0167 }
        L_0x0162:
            if (r13 == 0) goto L_0x0167
            r13.close()     // Catch:{ Exception -> 0x0167 }
        L_0x0167:
            return r0
        L_0x0168:
            r11 = move-exception
        L_0x0169:
            r0 = r12
        L_0x016a:
            if (r0 == 0) goto L_0x016f
            r0.close()     // Catch:{ Exception -> 0x0174 }
        L_0x016f:
            if (r13 == 0) goto L_0x0174
            r13.close()     // Catch:{ Exception -> 0x0174 }
        L_0x0174:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.share.ShareUtils.copyFileToWeiboTem(android.content.Context, android.net.Uri, int):java.lang.String");
    }
}
