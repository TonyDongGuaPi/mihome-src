package com.liulishuo.filedownloader.util;

public class FileDownloadProperties {
    private static final String h = "http.lenient";
    private static final String i = "process.non-separate";
    private static final String j = "download.min-progress-step";
    private static final String k = "download.min-progress-time";
    private static final String l = "download.max-network-thread-count";
    private static final String m = "file.non-pre-allocation";
    private static final String n = "broadcast.completed";
    private static final String o = "true";
    private static final String p = "false";

    /* renamed from: a  reason: collision with root package name */
    public final int f6466a;
    public final long b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final boolean g;

    public static class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloadProperties f6467a = new FileDownloadProperties();
    }

    public static FileDownloadProperties a() {
        return HolderClass.f6467a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x019d  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x023b A[SYNTHETIC, Splitter:B:119:0x023b] */
    /* JADX WARNING: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0088 A[Catch:{ all -> 0x0238 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0096 A[Catch:{ all -> 0x0238 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x009b A[SYNTHETIC, Splitter:B:63:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0161  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private FileDownloadProperties() {
        /*
            r15 = this;
            r15.<init>()
            android.content.Context r0 = com.liulishuo.filedownloader.util.FileDownloadHelper.a()
            if (r0 == 0) goto L_0x0244
            long r0 = java.lang.System.currentTimeMillis()
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            r4 = 0
            android.content.Context r5 = com.liulishuo.filedownloader.util.FileDownloadHelper.a()     // Catch:{ IOException -> 0x007c, all -> 0x0078 }
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch:{ IOException -> 0x007c, all -> 0x0078 }
            java.lang.String r6 = "filedownloader.properties"
            java.io.InputStream r5 = r5.open(r6)     // Catch:{ IOException -> 0x007c, all -> 0x0078 }
            if (r5 == 0) goto L_0x0067
            r2.load(r5)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r6 = "http.lenient"
            java.lang.String r6 = r2.getProperty(r6)     // Catch:{ IOException -> 0x0064 }
            java.lang.String r7 = "process.non-separate"
            java.lang.String r7 = r2.getProperty(r7)     // Catch:{ IOException -> 0x0061 }
            java.lang.String r8 = "download.min-progress-step"
            java.lang.String r8 = r2.getProperty(r8)     // Catch:{ IOException -> 0x005e }
            java.lang.String r9 = "download.min-progress-time"
            java.lang.String r9 = r2.getProperty(r9)     // Catch:{ IOException -> 0x005b }
            java.lang.String r10 = "download.max-network-thread-count"
            java.lang.String r10 = r2.getProperty(r10)     // Catch:{ IOException -> 0x0058 }
            java.lang.String r11 = "file.non-pre-allocation"
            java.lang.String r11 = r2.getProperty(r11)     // Catch:{ IOException -> 0x0055 }
            java.lang.String r12 = "broadcast.completed"
            java.lang.String r2 = r2.getProperty(r12)     // Catch:{ IOException -> 0x0053 }
            r3 = r6
            goto L_0x006d
        L_0x0053:
            r2 = move-exception
            goto L_0x0084
        L_0x0055:
            r2 = move-exception
            r11 = r3
            goto L_0x0084
        L_0x0058:
            r2 = move-exception
            r10 = r3
            goto L_0x0083
        L_0x005b:
            r2 = move-exception
            r9 = r3
            goto L_0x0082
        L_0x005e:
            r2 = move-exception
            r8 = r3
            goto L_0x0081
        L_0x0061:
            r2 = move-exception
            r7 = r3
            goto L_0x0080
        L_0x0064:
            r2 = move-exception
            r6 = r3
            goto L_0x007f
        L_0x0067:
            r2 = r3
            r7 = r2
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
        L_0x006d:
            if (r5 == 0) goto L_0x00a5
            r5.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x00a5
        L_0x0073:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x00a5
        L_0x0078:
            r0 = move-exception
            r5 = r3
            goto L_0x0239
        L_0x007c:
            r2 = move-exception
            r5 = r3
            r6 = r5
        L_0x007f:
            r7 = r6
        L_0x0080:
            r8 = r7
        L_0x0081:
            r9 = r8
        L_0x0082:
            r10 = r9
        L_0x0083:
            r11 = r10
        L_0x0084:
            boolean r12 = r2 instanceof java.io.FileNotFoundException     // Catch:{ all -> 0x0238 }
            if (r12 == 0) goto L_0x0096
            boolean r2 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x0238 }
            if (r2 == 0) goto L_0x0099
            java.lang.Class<com.liulishuo.filedownloader.util.FileDownloadProperties> r2 = com.liulishuo.filedownloader.util.FileDownloadProperties.class
            java.lang.String r12 = "not found filedownloader.properties"
            java.lang.Object[] r13 = new java.lang.Object[r4]     // Catch:{ all -> 0x0238 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r2, r12, r13)     // Catch:{ all -> 0x0238 }
            goto L_0x0099
        L_0x0096:
            r2.printStackTrace()     // Catch:{ all -> 0x0238 }
        L_0x0099:
            if (r5 == 0) goto L_0x00a3
            r5.close()     // Catch:{ IOException -> 0x009f }
            goto L_0x00a3
        L_0x009f:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00a3:
            r2 = r3
            r3 = r6
        L_0x00a5:
            r5 = 2
            r6 = 1
            r12 = 3
            if (r3 == 0) goto L_0x00e2
            java.lang.String r13 = "true"
            boolean r13 = r3.equals(r13)
            if (r13 != 0) goto L_0x00d8
            java.lang.String r13 = "false"
            boolean r13 = r3.equals(r13)
            if (r13 == 0) goto L_0x00bc
            goto L_0x00d8
        L_0x00bc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.Object[] r1 = new java.lang.Object[r12]
            java.lang.String r2 = "http.lenient"
            r1[r4] = r2
            java.lang.String r2 = "true"
            r1[r6] = r2
            java.lang.String r2 = "false"
            r1[r5] = r2
            java.lang.String r2 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r2, (java.lang.Object[]) r1)
            r0.<init>(r1)
            throw r0
        L_0x00d8:
            java.lang.String r13 = "true"
            boolean r3 = r3.equals(r13)
            r15.c = r3
            goto L_0x00e4
        L_0x00e2:
            r15.c = r4
        L_0x00e4:
            if (r7 == 0) goto L_0x011e
            java.lang.String r3 = "true"
            boolean r3 = r7.equals(r3)
            if (r3 != 0) goto L_0x0114
            java.lang.String r3 = "false"
            boolean r3 = r7.equals(r3)
            if (r3 == 0) goto L_0x00f8
            goto L_0x0114
        L_0x00f8:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.Object[] r1 = new java.lang.Object[r12]
            java.lang.String r2 = "process.non-separate"
            r1[r4] = r2
            java.lang.String r2 = "true"
            r1[r6] = r2
            java.lang.String r2 = "false"
            r1[r5] = r2
            java.lang.String r2 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r2, (java.lang.Object[]) r1)
            r0.<init>(r1)
            throw r0
        L_0x0114:
            java.lang.String r3 = "true"
            boolean r3 = r7.equals(r3)
            r15.d = r3
            goto L_0x0120
        L_0x011e:
            r15.d = r4
        L_0x0120:
            if (r8 == 0) goto L_0x0131
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            int r3 = r3.intValue()
            int r3 = java.lang.Math.max(r4, r3)
            r15.f6466a = r3
            goto L_0x0135
        L_0x0131:
            r3 = 65536(0x10000, float:9.18355E-41)
            r15.f6466a = r3
        L_0x0135:
            if (r9 == 0) goto L_0x0148
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            long r7 = r3.longValue()
            r13 = 0
            long r7 = java.lang.Math.max(r13, r7)
            r15.b = r7
            goto L_0x014c
        L_0x0148:
            r7 = 2000(0x7d0, double:9.88E-321)
            r15.b = r7
        L_0x014c:
            if (r10 == 0) goto L_0x015d
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)
            int r3 = r3.intValue()
            int r3 = a(r3)
            r15.e = r3
            goto L_0x015f
        L_0x015d:
            r15.e = r12
        L_0x015f:
            if (r11 == 0) goto L_0x0199
            java.lang.String r3 = "true"
            boolean r3 = r11.equals(r3)
            if (r3 != 0) goto L_0x018f
            java.lang.String r3 = "false"
            boolean r3 = r11.equals(r3)
            if (r3 == 0) goto L_0x0173
            goto L_0x018f
        L_0x0173:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.Object[] r1 = new java.lang.Object[r12]
            java.lang.String r2 = "file.non-pre-allocation"
            r1[r4] = r2
            java.lang.String r2 = "true"
            r1[r6] = r2
            java.lang.String r2 = "false"
            r1[r5] = r2
            java.lang.String r2 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r2, (java.lang.Object[]) r1)
            r0.<init>(r1)
            throw r0
        L_0x018f:
            java.lang.String r3 = "true"
            boolean r3 = r11.equals(r3)
            r15.f = r3
            goto L_0x019b
        L_0x0199:
            r15.f = r4
        L_0x019b:
            if (r2 == 0) goto L_0x01d5
            java.lang.String r3 = "true"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x01cb
            java.lang.String r3 = "false"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x01af
            goto L_0x01cb
        L_0x01af:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.Object[] r1 = new java.lang.Object[r12]
            java.lang.String r2 = "broadcast.completed"
            r1[r4] = r2
            java.lang.String r2 = "true"
            r1[r6] = r2
            java.lang.String r2 = "false"
            r1[r5] = r2
            java.lang.String r2 = "the value of '%s' must be '%s' or '%s'"
            java.lang.String r1 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r2, (java.lang.Object[]) r1)
            r0.<init>(r1)
            throw r0
        L_0x01cb:
            java.lang.String r3 = "true"
            boolean r2 = r2.equals(r3)
            r15.g = r2
            goto L_0x01d7
        L_0x01d5:
            r15.g = r4
        L_0x01d7:
            boolean r2 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a
            if (r2 == 0) goto L_0x0237
            java.lang.Class<com.liulishuo.filedownloader.util.FileDownloadProperties> r2 = com.liulishuo.filedownloader.util.FileDownloadProperties.class
            java.lang.String r3 = "init properties %d\n load properties: %s=%B; %s=%B; %s=%d; %s=%d; %s=%d"
            r7 = 11
            java.lang.Object[] r7 = new java.lang.Object[r7]
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r0
            java.lang.Long r0 = java.lang.Long.valueOf(r8)
            r7[r4] = r0
            java.lang.String r0 = "http.lenient"
            r7[r6] = r0
            boolean r0 = r15.c
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r7[r5] = r0
            java.lang.String r0 = "process.non-separate"
            r7[r12] = r0
            r0 = 4
            boolean r1 = r15.d
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r7[r0] = r1
            r0 = 5
            java.lang.String r1 = "download.min-progress-step"
            r7[r0] = r1
            r0 = 6
            int r1 = r15.f6466a
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7[r0] = r1
            r0 = 7
            java.lang.String r1 = "download.min-progress-time"
            r7[r0] = r1
            r0 = 8
            long r4 = r15.b
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            r7[r0] = r1
            r0 = 9
            java.lang.String r1 = "download.max-network-thread-count"
            r7[r0] = r1
            r0 = 10
            int r1 = r15.e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7[r0] = r1
            com.liulishuo.filedownloader.util.FileDownloadLog.b(r2, r3, r7)
        L_0x0237:
            return
        L_0x0238:
            r0 = move-exception
        L_0x0239:
            if (r5 == 0) goto L_0x0243
            r5.close()     // Catch:{ IOException -> 0x023f }
            goto L_0x0243
        L_0x023f:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0243:
            throw r0
        L_0x0244:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Please invoke the 'FileDownloader#setup' before using FileDownloader. If you want to register some components on FileDownloader please invoke the 'FileDownloader#setupOnApplicationOnCreate' on the 'Application#onCreate' first."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.util.FileDownloadProperties.<init>():void");
    }

    public static int a(int i2) {
        if (i2 > 12) {
            FileDownloadLog.d(FileDownloadProperties.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i2), 12, 12);
            return 12;
        } else if (i2 >= 1) {
            return i2;
        } else {
            FileDownloadLog.d(FileDownloadProperties.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i2), 1, 1);
            return 1;
        }
    }
}
