package com.mipay.common.data;

import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import java.io.File;

public class g {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8133a = 100;
    private static final String b = "PaymentImage";
    private static String c = "http://file.market.xiaomi.com/mfc/download/";
    private static String d = "http://file.market.xiaomi.com/mfc/thumbnail/";
    private static a h;
    private String e;
    private String f;
    private b g;

    private static class a extends LruCache<String, g> {
        public a(int i) {
            super(i);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
            return r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.mipay.common.data.g a(java.lang.String r3) {
            /*
                r2 = this;
                monitor-enter(r2)
                boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x001c }
                r1 = 0
                if (r0 == 0) goto L_0x000a
                monitor-exit(r2)     // Catch:{ all -> 0x001c }
                return r1
            L_0x000a:
                java.lang.Object r0 = r2.get(r3)     // Catch:{ all -> 0x001c }
                com.mipay.common.data.g r0 = (com.mipay.common.data.g) r0     // Catch:{ all -> 0x001c }
                if (r0 != 0) goto L_0x001a
                com.mipay.common.data.g r0 = new com.mipay.common.data.g     // Catch:{ all -> 0x001c }
                r0.<init>(r3)     // Catch:{ all -> 0x001c }
                r2.put(r3, r0)     // Catch:{ all -> 0x001c }
            L_0x001a:
                monitor-exit(r2)     // Catch:{ all -> 0x001c }
                return r0
            L_0x001c:
                r3 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x001c }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mipay.common.data.g.a.a(java.lang.String):com.mipay.common.data.g");
        }
    }

    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final int f8134a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 0;
        public static final int f = 1;
        public static final int g = 2;
        public static final int h = 80;
        private int i = 0;
        private int j = 0;
        private int k = 0;
        private int l;
        private int m;
        private int n = 80;

        public static b a(int i2, int i3) {
            b bVar = new b();
            bVar.k = i2;
            bVar.l = 0;
            bVar.m = i3;
            return bVar;
        }

        public static b a(int i2, int i3, int i4) {
            b bVar = new b();
            bVar.i = i2;
            bVar.j = i3;
            bVar.l = 3;
            bVar.m = i4;
            return bVar;
        }

        public static b b(int i2, int i3) {
            b bVar = new b();
            bVar.i = i2;
            bVar.l = 1;
            bVar.m = i3;
            return bVar;
        }

        public static b c(int i2, int i3) {
            b bVar = new b();
            bVar.i = i2;
            bVar.l = 2;
            bVar.m = i3;
            return bVar;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String a() {
            /*
                r4 = this;
                boolean r0 = r4.b()
                if (r0 != 0) goto L_0x0008
                r0 = 0
                return r0
            L_0x0008:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                int r1 = r4.m
                r2 = 2
                r3 = 1
                if (r1 != 0) goto L_0x0019
                java.lang.String r1 = "jpeg"
            L_0x0015:
                r0.append(r1)
                goto L_0x0028
            L_0x0019:
                int r1 = r4.m
                if (r1 != r3) goto L_0x0020
                java.lang.String r1 = "png"
                goto L_0x0015
            L_0x0020:
                int r1 = r4.m
                if (r1 != r2) goto L_0x0028
                java.lang.String r1 = "webp"
                goto L_0x0015
            L_0x0028:
                java.lang.String r1 = "/"
                r0.append(r1)
                int r1 = r4.l
                if (r1 != 0) goto L_0x0048
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "l"
                r1.append(r2)
                int r2 = r4.k
            L_0x003d:
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                goto L_0x008d
            L_0x0048:
                int r1 = r4.l
                if (r1 != r3) goto L_0x005a
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "w"
                r1.append(r2)
                int r2 = r4.i
                goto L_0x003d
            L_0x005a:
                int r1 = r4.l
                if (r1 != r2) goto L_0x006b
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
            L_0x0063:
                java.lang.String r2 = "h"
                r1.append(r2)
                int r2 = r4.j
                goto L_0x003d
            L_0x006b:
                int r1 = r4.l
                r2 = 3
                if (r1 != r2) goto L_0x008d
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "w"
                r1.append(r2)
                int r2 = r4.i
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                goto L_0x0063
            L_0x008d:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "q"
                r1.append(r2)
                int r2 = r4.n
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mipay.common.data.g.b.a():java.lang.String");
        }

        public void a(int i2) {
            if (i2 > 100 || i2 < 0) {
                this.n = 80;
            }
            this.n = i2;
        }

        public boolean b() {
            if (this.m != 0 && this.m != 1 && this.m != 2) {
                return false;
            }
            if (this.l == 0 && this.k > 0) {
                return true;
            }
            if (this.l == 1 && this.i > 0) {
                return true;
            }
            if (this.l != 2 || this.j <= 0) {
                return this.l == 3 && this.i > 0 && this.j > 0;
            }
            return true;
        }
    }

    private g(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
            this.f = c.b(this.e);
        }
    }

    public static g a(String str) {
        if (h == null) {
            a();
        }
        return h.a(str);
    }

    public static void a() {
        if (h == null) {
            h = new a(100);
        }
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r1 = r2;
        r4 = r0;
        r0 = r6;
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004e, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005b, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0066, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006a, code lost:
        android.util.Log.e(com.xiaomi.smarthome.framework.page.CommonShareActivity.SHARE_IMAGE, "saveJPEG error, remove the output file.");
        r7.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0075, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0039 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:5:0x0010] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004a A[SYNTHETIC, Splitter:B:30:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0062 A[SYNTHETIC, Splitter:B:38:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x006a A[Catch:{ Exception -> 0x0066 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.graphics.Bitmap r6, java.io.File r7) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.io.File r7 = r5.b(r7)     // Catch:{ Exception -> 0x0043, all -> 0x0040 }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x003e }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003e }
            r3.<init>(r7)     // Catch:{ Exception -> 0x003e }
            r2.<init>(r3)     // Catch:{ Exception -> 0x003e }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0039, all -> 0x0036 }
            r3 = 90
            boolean r6 = r6.compress(r1, r3, r2)     // Catch:{ Exception -> 0x0039, all -> 0x0036 }
            r2.flush()     // Catch:{ Exception -> 0x0039, all -> 0x0030 }
            r2.close()     // Catch:{ Exception -> 0x002b }
            if (r6 != 0) goto L_0x005f
            java.lang.String r0 = "Image"
            java.lang.String r1 = "saveJPEG error, remove the output file."
            android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x002b }
            r7.delete()     // Catch:{ Exception -> 0x002b }
            goto L_0x005f
        L_0x002b:
            r7 = move-exception
            r7.printStackTrace()
            goto L_0x005f
        L_0x0030:
            r0 = move-exception
            r1 = r2
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x0060
        L_0x0036:
            r6 = move-exception
            r1 = r2
            goto L_0x0060
        L_0x0039:
            r6 = move-exception
            r1 = r2
            goto L_0x0045
        L_0x003c:
            r6 = move-exception
            goto L_0x0060
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r7 = r1
            goto L_0x0060
        L_0x0043:
            r6 = move-exception
            r7 = r1
        L_0x0045:
            r6.printStackTrace()     // Catch:{ all -> 0x003c }
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ Exception -> 0x004e }
            goto L_0x0050
        L_0x004e:
            r6 = move-exception
            goto L_0x005b
        L_0x0050:
            java.lang.String r6 = "Image"
            java.lang.String r1 = "saveJPEG error, remove the output file."
            android.util.Log.e(r6, r1)     // Catch:{ Exception -> 0x004e }
            r7.delete()     // Catch:{ Exception -> 0x004e }
            goto L_0x005e
        L_0x005b:
            r6.printStackTrace()
        L_0x005e:
            r6 = 0
        L_0x005f:
            return r6
        L_0x0060:
            if (r1 == 0) goto L_0x0068
            r1.close()     // Catch:{ Exception -> 0x0066 }
            goto L_0x0068
        L_0x0066:
            r7 = move-exception
            goto L_0x0075
        L_0x0068:
            if (r0 != 0) goto L_0x0078
            java.lang.String r0 = "Image"
            java.lang.String r1 = "saveJPEG error, remove the output file."
            android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x0066 }
            r7.delete()     // Catch:{ Exception -> 0x0066 }
            goto L_0x0078
        L_0x0075:
            r7.printStackTrace()
        L_0x0078:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mipay.common.data.g.a(android.graphics.Bitmap, java.io.File):boolean");
    }

    public final boolean a(File file) {
        return !TextUtils.isEmpty(this.f) && new File(file, this.f).exists();
    }

    public final File b(File file) {
        if (!TextUtils.isEmpty(this.f)) {
            return new File(file, this.f);
        }
        Log.e(b, "Image has no cache name");
        return null;
    }

    public final String b() {
        String a2;
        if (this.g != null) {
            String a3 = this.g.a();
            if (!TextUtils.isEmpty(a3)) {
                a2 = e.a(d, a3);
                return e.a(a2, this.e);
            }
        }
        a2 = c;
        return e.a(a2, this.e);
    }

    public String c() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof g) || this.e == null) {
            return false;
        }
        return this.e.equals(((g) obj).e);
    }

    public int hashCode() {
        if (this.e == null) {
            return 0;
        }
        return this.e.hashCode();
    }
}
