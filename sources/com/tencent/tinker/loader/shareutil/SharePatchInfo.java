package com.tencent.tinker.loader.shareutil;

public class SharePatchInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9259a = 2;
    public static final String b = "old";
    public static final String c = "new";
    public static final String d = "is_protected_app";
    public static final String e = "is_remove_new_version";
    public static final String f = "print";
    public static final String g = "dir";
    public static final String h = "odex";
    private static final String o = "Tinker.PatchInfo";
    public String i;
    public String j;
    public boolean k;
    public boolean l;
    public String m;
    public String n;

    public SharePatchInfo(String str, String str2, boolean z, boolean z2, String str3, String str4) {
        this.i = str;
        this.j = str2;
        this.k = z;
        this.l = z2;
        this.m = str3;
        this.n = str4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x003d A[SYNTHETIC, Splitter:B:26:0x003d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.tinker.loader.shareutil.SharePatchInfo a(java.io.File r3, java.io.File r4) {
        /*
            r0 = 0
            if (r3 == 0) goto L_0x004a
            if (r4 != 0) goto L_0x0006
            goto L_0x004a
        L_0x0006:
            java.io.File r1 = r4.getParentFile()
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x0013
            r1.mkdirs()
        L_0x0013:
            com.tencent.tinker.loader.shareutil.ShareFileLockHelper r4 = com.tencent.tinker.loader.shareutil.ShareFileLockHelper.a(r4)     // Catch:{ Exception -> 0x0032 }
            com.tencent.tinker.loader.shareutil.SharePatchInfo r3 = a(r3)     // Catch:{ Exception -> 0x002d, all -> 0x002a }
            if (r4 == 0) goto L_0x0029
            r4.close()     // Catch:{ IOException -> 0x0021 }
            goto L_0x0029
        L_0x0021:
            r4 = move-exception
            java.lang.String r0 = "Tinker.PatchInfo"
            java.lang.String r1 = "releaseInfoLock error"
            android.util.Log.w(r0, r1, r4)
        L_0x0029:
            return r3
        L_0x002a:
            r3 = move-exception
            r0 = r4
            goto L_0x003b
        L_0x002d:
            r3 = move-exception
            r0 = r4
            goto L_0x0033
        L_0x0030:
            r3 = move-exception
            goto L_0x003b
        L_0x0032:
            r3 = move-exception
        L_0x0033:
            com.tencent.tinker.loader.TinkerRuntimeException r4 = new com.tencent.tinker.loader.TinkerRuntimeException     // Catch:{ all -> 0x0030 }
            java.lang.String r1 = "readAndCheckPropertyWithLock fail"
            r4.<init>(r1, r3)     // Catch:{ all -> 0x0030 }
            throw r4     // Catch:{ all -> 0x0030 }
        L_0x003b:
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0049
        L_0x0041:
            r4 = move-exception
            java.lang.String r0 = "Tinker.PatchInfo"
            java.lang.String r1 = "releaseInfoLock error"
            android.util.Log.w(r0, r1, r4)
        L_0x0049:
            throw r3
        L_0x004a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.SharePatchInfo.a(java.io.File, java.io.File):com.tencent.tinker.loader.shareutil.SharePatchInfo");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x003f A[SYNTHETIC, Splitter:B:27:0x003f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r2, com.tencent.tinker.loader.shareutil.SharePatchInfo r3, java.io.File r4) {
        /*
            if (r2 == 0) goto L_0x004c
            if (r3 == 0) goto L_0x004c
            if (r4 != 0) goto L_0x0007
            goto L_0x004c
        L_0x0007:
            java.io.File r0 = r4.getParentFile()
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0014
            r0.mkdirs()
        L_0x0014:
            r0 = 0
            com.tencent.tinker.loader.shareutil.ShareFileLockHelper r4 = com.tencent.tinker.loader.shareutil.ShareFileLockHelper.a(r4)     // Catch:{ Exception -> 0x0034 }
            boolean r2 = a((java.io.File) r2, (com.tencent.tinker.loader.shareutil.SharePatchInfo) r3)     // Catch:{ Exception -> 0x002f, all -> 0x002c }
            if (r4 == 0) goto L_0x002b
            r4.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x002b
        L_0x0023:
            r3 = move-exception
            java.lang.String r4 = "Tinker.PatchInfo"
            java.lang.String r0 = "releaseInfoLock error"
            android.util.Log.i(r4, r0, r3)
        L_0x002b:
            return r2
        L_0x002c:
            r2 = move-exception
            r0 = r4
            goto L_0x003d
        L_0x002f:
            r2 = move-exception
            r0 = r4
            goto L_0x0035
        L_0x0032:
            r2 = move-exception
            goto L_0x003d
        L_0x0034:
            r2 = move-exception
        L_0x0035:
            com.tencent.tinker.loader.TinkerRuntimeException r3 = new com.tencent.tinker.loader.TinkerRuntimeException     // Catch:{ all -> 0x0032 }
            java.lang.String r4 = "rewritePatchInfoFileWithLock fail"
            r3.<init>(r4, r2)     // Catch:{ all -> 0x0032 }
            throw r3     // Catch:{ all -> 0x0032 }
        L_0x003d:
            if (r0 == 0) goto L_0x004b
            r0.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x004b
        L_0x0043:
            r3 = move-exception
            java.lang.String r4 = "Tinker.PatchInfo"
            java.lang.String r0 = "releaseInfoLock error"
            android.util.Log.i(r4, r0, r3)
        L_0x004b:
            throw r2
        L_0x004c:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.SharePatchInfo.a(java.io.File, com.tencent.tinker.loader.shareutil.SharePatchInfo, java.io.File):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0079, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007c, code lost:
        r12 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0016] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00d3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.tencent.tinker.loader.shareutil.SharePatchInfo a(java.io.File r16) {
        /*
            r2 = 0
            r3 = 0
            r6 = r2
            r7 = r6
            r10 = r7
            r11 = r10
            r0 = 0
            r4 = 0
            r8 = 0
            r9 = 0
        L_0x000a:
            r5 = 2
            if (r0 >= r5) goto L_0x00df
            if (r4 != 0) goto L_0x00df
            int r5 = r0 + 1
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.io.FileInputStream r12 = new java.io.FileInputStream     // Catch:{ IOException -> 0x007e, all -> 0x007b }
            r13 = r16
            r12.<init>(r13)     // Catch:{ IOException -> 0x0079, all -> 0x007b }
            r0.load(r12)     // Catch:{ IOException -> 0x0077 }
            java.lang.String r14 = "old"
            java.lang.String r14 = r0.getProperty(r14)     // Catch:{ IOException -> 0x0077 }
            java.lang.String r6 = "new"
            java.lang.String r6 = r0.getProperty(r6)     // Catch:{ IOException -> 0x0075 }
            java.lang.String r7 = "is_protected_app"
            java.lang.String r7 = r0.getProperty(r7)     // Catch:{ IOException -> 0x0073 }
            if (r7 == 0) goto L_0x0044
            boolean r15 = r7.isEmpty()     // Catch:{ IOException -> 0x0073 }
            if (r15 != 0) goto L_0x0044
            java.lang.String r15 = "0"
            boolean r7 = r15.equals(r7)     // Catch:{ IOException -> 0x0073 }
            if (r7 != 0) goto L_0x0044
            r8 = 1
            goto L_0x0045
        L_0x0044:
            r8 = 0
        L_0x0045:
            java.lang.String r7 = "is_remove_new_version"
            java.lang.String r7 = r0.getProperty(r7)     // Catch:{ IOException -> 0x0073 }
            if (r7 == 0) goto L_0x005d
            boolean r15 = r7.isEmpty()     // Catch:{ IOException -> 0x0073 }
            if (r15 != 0) goto L_0x005d
            java.lang.String r15 = "0"
            boolean r7 = r15.equals(r7)     // Catch:{ IOException -> 0x0073 }
            if (r7 != 0) goto L_0x005d
            r9 = 1
            goto L_0x005e
        L_0x005d:
            r9 = 0
        L_0x005e:
            java.lang.String r7 = "print"
            java.lang.String r7 = r0.getProperty(r7)     // Catch:{ IOException -> 0x0073 }
            java.lang.String r10 = "dir"
            java.lang.String r0 = r0.getProperty(r10)     // Catch:{ IOException -> 0x0070 }
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r12)
            r11 = r0
            r10 = r7
            goto L_0x009d
        L_0x0070:
            r0 = move-exception
            r10 = r7
            goto L_0x0084
        L_0x0073:
            r0 = move-exception
            goto L_0x0084
        L_0x0075:
            r0 = move-exception
            goto L_0x0083
        L_0x0077:
            r0 = move-exception
            goto L_0x0082
        L_0x0079:
            r0 = move-exception
            goto L_0x0081
        L_0x007b:
            r0 = move-exception
            r12 = r2
            goto L_0x00db
        L_0x007e:
            r0 = move-exception
            r13 = r16
        L_0x0081:
            r12 = r2
        L_0x0082:
            r14 = r6
        L_0x0083:
            r6 = r7
        L_0x0084:
            java.lang.String r7 = "Tinker.PatchInfo"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x00da }
            r15.<init>()     // Catch:{ all -> 0x00da }
            java.lang.String r1 = "read property failed, e:"
            r15.append(r1)     // Catch:{ all -> 0x00da }
            r15.append(r0)     // Catch:{ all -> 0x00da }
            java.lang.String r0 = r15.toString()     // Catch:{ all -> 0x00da }
            android.util.Log.w(r7, r0)     // Catch:{ all -> 0x00da }
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r12)
        L_0x009d:
            r7 = r6
            r6 = r14
            if (r6 == 0) goto L_0x00d7
            if (r7 != 0) goto L_0x00a4
            goto L_0x00d7
        L_0x00a4:
            java.lang.String r0 = ""
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x00b2
            boolean r0 = com.tencent.tinker.loader.shareutil.SharePatchFileUtil.e((java.lang.String) r6)
            if (r0 == 0) goto L_0x00b8
        L_0x00b2:
            boolean r0 = com.tencent.tinker.loader.shareutil.SharePatchFileUtil.e((java.lang.String) r7)
            if (r0 != 0) goto L_0x00d3
        L_0x00b8:
            java.lang.String r0 = "Tinker.PatchInfo"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r12 = "path info file  corrupted:"
            r1.append(r12)
            java.lang.String r12 = r16.getAbsolutePath()
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r0, r1)
            goto L_0x00d7
        L_0x00d3:
            r0 = r5
            r4 = 1
            goto L_0x000a
        L_0x00d7:
            r0 = r5
            goto L_0x000a
        L_0x00da:
            r0 = move-exception
        L_0x00db:
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r12)
            throw r0
        L_0x00df:
            if (r4 == 0) goto L_0x00e8
            com.tencent.tinker.loader.shareutil.SharePatchInfo r0 = new com.tencent.tinker.loader.shareutil.SharePatchInfo
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11)
            return r0
        L_0x00e8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.SharePatchInfo.a(java.io.File):com.tencent.tinker.loader.shareutil.SharePatchInfo");
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0084 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.io.File r8, com.tencent.tinker.loader.shareutil.SharePatchInfo r9) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x0141
            if (r9 != 0) goto L_0x0007
            goto L_0x0141
        L_0x0007:
            java.lang.String r1 = r9.m
            boolean r1 = com.tencent.tinker.loader.shareutil.ShareTinkerInternals.b((java.lang.String) r1)
            if (r1 == 0) goto L_0x0013
            java.lang.String r1 = android.os.Build.FINGERPRINT
            r9.m = r1
        L_0x0013:
            java.lang.String r1 = r9.n
            boolean r1 = com.tencent.tinker.loader.shareutil.ShareTinkerInternals.b((java.lang.String) r1)
            if (r1 == 0) goto L_0x001f
            java.lang.String r1 = "odex"
            r9.n = r1
        L_0x001f:
            java.lang.String r1 = "Tinker.PatchInfo"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "rewritePatchInfoFile file path:"
            r2.append(r3)
            java.lang.String r3 = r8.getAbsolutePath()
            r2.append(r3)
            java.lang.String r3 = " , oldVer:"
            r2.append(r3)
            java.lang.String r3 = r9.i
            r2.append(r3)
            java.lang.String r3 = ", newVer:"
            r2.append(r3)
            java.lang.String r3 = r9.j
            r2.append(r3)
            java.lang.String r3 = ", isProtectedApp:"
            r2.append(r3)
            boolean r3 = r9.k
            r2.append(r3)
            java.lang.String r3 = ", isRemoveNewVersion:"
            r2.append(r3)
            boolean r3 = r9.l
            r2.append(r3)
            java.lang.String r3 = ", fingerprint:"
            r2.append(r3)
            java.lang.String r3 = r9.m
            r2.append(r3)
            java.lang.String r3 = ", oatDir:"
            r2.append(r3)
            java.lang.String r3 = r9.n
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r1, r2)
            java.io.File r1 = r8.getParentFile()
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x0082
            r1.mkdirs()
        L_0x0082:
            r1 = 0
            r2 = 0
        L_0x0084:
            r3 = 2
            r4 = 1
            if (r1 >= r3) goto L_0x013d
            if (r2 != 0) goto L_0x013d
            int r1 = r1 + 1
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            java.lang.String r3 = "old"
            java.lang.String r5 = r9.i
            r2.put(r3, r5)
            java.lang.String r3 = "new"
            java.lang.String r5 = r9.j
            r2.put(r3, r5)
            java.lang.String r3 = "is_protected_app"
            boolean r5 = r9.k
            if (r5 == 0) goto L_0x00a8
            java.lang.String r5 = "1"
            goto L_0x00aa
        L_0x00a8:
            java.lang.String r5 = "0"
        L_0x00aa:
            r2.put(r3, r5)
            java.lang.String r3 = "is_remove_new_version"
            boolean r5 = r9.l
            if (r5 == 0) goto L_0x00b6
            java.lang.String r5 = "1"
            goto L_0x00b8
        L_0x00b6:
            java.lang.String r5 = "0"
        L_0x00b8:
            r2.put(r3, r5)
            java.lang.String r3 = "print"
            java.lang.String r5 = r9.m
            r2.put(r3, r5)
            java.lang.String r3 = "dir"
            java.lang.String r5 = r9.n
            r2.put(r3, r5)
            r3 = 0
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00fb }
            r5.<init>(r8, r0)     // Catch:{ Exception -> 0x00fb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            r3.<init>()     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            java.lang.String r6 = "from old version:"
            r3.append(r6)     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            java.lang.String r6 = r9.i     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            r3.append(r6)     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            java.lang.String r6 = " to new version:"
            r3.append(r6)     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            java.lang.String r6 = r9.j     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            r3.append(r6)     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            r2.store(r5, r3)     // Catch:{ Exception -> 0x00f5, all -> 0x00f3 }
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r5)
            goto L_0x0115
        L_0x00f3:
            r8 = move-exception
            goto L_0x0139
        L_0x00f5:
            r2 = move-exception
            r3 = r5
            goto L_0x00fc
        L_0x00f8:
            r8 = move-exception
            r5 = r3
            goto L_0x0139
        L_0x00fb:
            r2 = move-exception
        L_0x00fc:
            java.lang.String r5 = "Tinker.PatchInfo"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
            r6.<init>()     // Catch:{ all -> 0x00f8 }
            java.lang.String r7 = "write property failed, e:"
            r6.append(r7)     // Catch:{ all -> 0x00f8 }
            r6.append(r2)     // Catch:{ all -> 0x00f8 }
            java.lang.String r2 = r6.toString()     // Catch:{ all -> 0x00f8 }
            android.util.Log.w(r5, r2)     // Catch:{ all -> 0x00f8 }
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r3)
        L_0x0115:
            com.tencent.tinker.loader.shareutil.SharePatchInfo r2 = a(r8)
            if (r2 == 0) goto L_0x0131
            java.lang.String r3 = r2.i
            java.lang.String r5 = r9.i
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0131
            java.lang.String r2 = r2.j
            java.lang.String r3 = r9.j
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0131
            r2 = 1
            goto L_0x0132
        L_0x0131:
            r2 = 0
        L_0x0132:
            if (r2 != 0) goto L_0x0084
            r8.delete()
            goto L_0x0084
        L_0x0139:
            com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a((java.lang.Object) r5)
            throw r8
        L_0x013d:
            if (r2 == 0) goto L_0x0140
            return r4
        L_0x0140:
            return r0
        L_0x0141:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.SharePatchInfo.a(java.io.File, com.tencent.tinker.loader.shareutil.SharePatchInfo):boolean");
    }
}
