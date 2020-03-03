package com.mi.util;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.File;
import java.io.InputStream;

public class HttpDownloader {
    public static final String f = "UserCannelled";

    /* renamed from: a  reason: collision with root package name */
    public final int f7414a = 0;
    public final int b = 1;
    public int c = 0;
    public Object d = null;
    public Object e = null;
    private final int g = 20000;
    private final int h = 60000;
    private final String i = DefaultDiskStorage.FileType.TEMP;
    private String j = null;
    private String k = null;
    private String l = null;
    /* access modifiers changed from: private */
    public HttpDownloadListener m = null;
    /* access modifiers changed from: private */
    public long n = -1;
    /* access modifiers changed from: private */
    public long o = 0;
    /* access modifiers changed from: private */
    public Object p = null;
    private Thread q = null;
    private boolean r = false;
    private boolean s = true;
    /* access modifiers changed from: private */
    public boolean t = false;
    private String u = "GET";
    private Object[] v = null;

    public interface HttpDownloadListener {
        void a(Object obj);

        void a(Object obj, long j, long j2);

        void a(Object obj, Throwable th);

        void b(Object obj);
    }

    public InputStream h() {
        return null;
    }

    public HttpDownloader(String str) {
        this.k = str;
    }

    public void a(Object obj) {
        this.p = obj;
    }

    public void a(String str) {
        try {
            new File(str).getParentFile().mkdirs();
        } catch (Exception unused) {
        }
        this.l = str;
    }

    public void a(HttpDownloadListener httpDownloadListener) {
        this.m = httpDownloadListener;
    }

    public long a() {
        return this.o;
    }

    public void b() {
        this.r = true;
        try {
            this.q.join(1000);
        } catch (Exception unused) {
        }
    }

    private void l() {
        this.q = new Thread() {
            public void run() {
                HttpDownloader.this.m();
            }
        };
        this.q.start();
    }

    public void c() {
        this.o = 0;
        l();
    }

    public String d() {
        return this.j;
    }

    public String e() {
        return this.l;
    }

    public void f() {
        if (this.o == 0) {
            this.o = new File(this.l + DefaultDiskStorage.FileType.TEMP).length();
        }
        this.q = new Thread() {
            public void run() {
                HttpDownloader.this.m();
            }
        };
        this.q.start();
    }

    public long g() {
        return this.n;
    }

    public void i() {
        new File(this.l).delete();
    }

    public void j() {
        this.u = "POST";
    }

    public void a(Object[] objArr) {
        this.v = objArr;
    }

    public boolean k() {
        return this.r;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0249 A[Catch:{ all -> 0x025d }] */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0254 A[SYNTHETIC, Splitter:B:149:0x0254] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0259 A[Catch:{ Exception -> 0x025c }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0262 A[SYNTHETIC, Splitter:B:157:0x0262] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0267 A[Catch:{ Exception -> 0x026a }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m() {
        /*
            r14 = this;
            r0 = 0
            r1 = 1
            java.net.URL r2 = new java.net.URL     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r3 = r14.k     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r3 = com.mi.util.PathUtils.a(r3)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r3 = r14.c     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            if (r3 != 0) goto L_0x002e
            java.lang.String r3 = r14.l     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            if (r3 == 0) goto L_0x002e
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r4.<init>()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r5 = r14.l     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r4.append(r5)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r5 = ".tmp"
            r4.append(r5)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            goto L_0x002f
        L_0x002e:
            r3 = r0
        L_0x002f:
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r4 = 20000(0x4e20, float:2.8026E-41)
            r2.setConnectTimeout(r4)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r4 = 60000(0xea60, float:8.4078E-41)
            r2.setReadTimeout(r4)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r4 = r14.u     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r2.setRequestMethod(r4)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            boolean r4 = r14.s     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r5 = 0
            if (r4 == 0) goto L_0x007d
            int r4 = r14.c     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            if (r4 != 0) goto L_0x007d
            if (r3 == 0) goto L_0x007d
            long r7 = r3.length()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x007d
            long r7 = r3.length()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r14.o = r7     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r4 = "Range"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r7.<init>()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r8 = "bytes="
            r7.append(r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            long r8 = r14.o     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r8 = "-"
            r7.append(r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r2.setRequestProperty(r4, r7)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            goto L_0x007f
        L_0x007d:
            r14.o = r5     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
        L_0x007f:
            java.lang.Object[] r4 = r14.v     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r7 = 0
            if (r4 == 0) goto L_0x00be
            r4 = 0
        L_0x0085:
            java.lang.Object[] r8 = r14.v     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r8 = r8.length     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            if (r4 >= r8) goto L_0x00be
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r8.<init>()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r9 = ""
            r8.append(r9)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.Object[] r9 = r14.v     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r10 = r4 + 0
            r9 = r9[r10]     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r8.append(r9)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r9.<init>()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r10 = ""
            r9.append(r10)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.Object[] r10 = r14.v     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r11 = r4 + 1
            r10 = r10[r11]     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r9.append(r10)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r2.setRequestProperty(r8, r9)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r4 = r4 + 2
            goto L_0x0085
        L_0x00be:
            java.lang.String r4 = "Cache-Control"
            java.lang.String r8 = "no-cache"
            r2.setRequestProperty(r4, r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r4 = "Pragma"
            java.lang.String r8 = "no-cache"
            r2.setRequestProperty(r4, r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r4 = "Accept-Encoding"
            java.lang.String r8 = "identity"
            r2.setRequestProperty(r4, r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r4 = r14.u     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r8 = "POST"
            boolean r4 = r4.equalsIgnoreCase(r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            if (r4 == 0) goto L_0x00e0
            r2.setDoOutput(r1)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
        L_0x00e0:
            r2.connect()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            long r8 = r14.o     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x00f3
            int r4 = r2.getResponseCode()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r8 = 206(0xce, float:2.89E-43)
            if (r4 == r8) goto L_0x00f3
            r14.o = r5     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
        L_0x00f3:
            com.mi.util.HttpDownloader$HttpDownloadListener r4 = r14.m     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            if (r4 == 0) goto L_0x00fe
            com.mi.util.HttpDownloader$HttpDownloadListener r4 = r14.m     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.Object r8 = r14.p     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r4.a(r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
        L_0x00fe:
            long r8 = r14.o     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            int r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x010c
            if (r3 == 0) goto L_0x010c
            r3.delete()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            r3.createNewFile()     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
        L_0x010c:
            if (r3 == 0) goto L_0x011b
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            java.lang.String r8 = "rw"
            r4.<init>(r3, r8)     // Catch:{ Throwable -> 0x0243, all -> 0x0240 }
            long r8 = r14.o     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            r4.seek(r8)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            goto L_0x011c
        L_0x011b:
            r4 = r0
        L_0x011c:
            boolean r3 = r14.r     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            if (r3 == 0) goto L_0x013a
            com.mi.util.HttpDownloader$HttpDownloadListener r2 = r14.m     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            if (r2 == 0) goto L_0x0132
            com.mi.util.HttpDownloader$HttpDownloadListener r2 = r14.m     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.Object r3 = r14.p     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            java.lang.String r6 = "UserCannelled"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            r2.a(r3, r5)     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
        L_0x0132:
            r14.t = r1
            if (r4 == 0) goto L_0x0139
            r4.close()     // Catch:{ Exception -> 0x0139 }
        L_0x0139:
            return
        L_0x013a:
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ Throwable -> 0x023c, all -> 0x0238 }
            int r8 = r2.getContentLength()     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r8 = (long) r8     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r10 = r14.o     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r12 = 0
            long r8 = r8 + r10
            r14.n = r8     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r8 = r14.n     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            int r10 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r10 >= 0) goto L_0x016e
            com.mi.util.HttpDownloader$HttpDownloadListener r5 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r5 == 0) goto L_0x016e
            com.mi.util.HttpDownloader$HttpDownloadListener r0 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Object r2 = r14.p     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.String r6 = "error"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r0.a(r2, r5)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r14.t = r1
            if (r4 == 0) goto L_0x0168
            r4.close()     // Catch:{ Exception -> 0x016d }
        L_0x0168:
            if (r3 == 0) goto L_0x016d
            r3.close()     // Catch:{ Exception -> 0x016d }
        L_0x016d:
            return
        L_0x016e:
            int r5 = r14.c     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r5 != r1) goto L_0x0178
            long r5 = r14.n     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            int r5 = (int) r5     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            goto L_0x017c
        L_0x0178:
            r5 = 16384(0x4000, float:2.2959E-41)
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
        L_0x017c:
            com.mi.util.HttpDownloader$HttpDownloadListener r6 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r6 == 0) goto L_0x018b
            com.mi.util.HttpDownloader$HttpDownloadListener r8 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Object r9 = r14.p     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r10 = r14.n     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r12 = r14.o     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r8.a(r9, r10, r12)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
        L_0x018b:
            r14.t = r7     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            com.mi.util.HttpDownloader$3 r6 = new com.mi.util.HttpDownloader$3     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r6.<init>()     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r6.start()     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r6 = 0
        L_0x0196:
            int r8 = r5.length     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            int r8 = r8 - r6
            int r8 = r3.read(r5, r6, r8)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r8 <= 0) goto L_0x01b9
            long r9 = r14.o     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r11 = r14.n     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 >= 0) goto L_0x01b9
            boolean r9 = r14.r     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r9 != 0) goto L_0x01b9
            if (r4 == 0) goto L_0x01b1
            r4.write(r5, r7, r8)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r6 = 0
            goto L_0x01b2
        L_0x01b1:
            int r6 = r6 + r8
        L_0x01b2:
            long r9 = r14.o     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r11 = (long) r8     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            long r9 = r9 + r11
            r14.o = r9     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            goto L_0x0196
        L_0x01b9:
            r14.t = r1     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            boolean r6 = r14.r     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r6 == 0) goto L_0x01de
            com.mi.util.HttpDownloader$HttpDownloadListener r0 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r0 == 0) goto L_0x01d1
            com.mi.util.HttpDownloader$HttpDownloadListener r0 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Object r2 = r14.p     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.String r6 = "UserCannelled"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r0.a(r2, r5)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
        L_0x01d1:
            r14.t = r1
            if (r4 == 0) goto L_0x01d8
            r4.close()     // Catch:{ Exception -> 0x01dd }
        L_0x01d8:
            if (r3 == 0) goto L_0x01dd
            r3.close()     // Catch:{ Exception -> 0x01dd }
        L_0x01dd:
            return
        L_0x01de:
            int r2 = r2.getResponseCode()     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r6 = 300(0x12c, float:4.2E-43)
            if (r2 < r6) goto L_0x0205
            com.mi.util.HttpDownloader$HttpDownloadListener r0 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r0 == 0) goto L_0x01f8
            com.mi.util.HttpDownloader$HttpDownloadListener r0 = r14.m     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Object r2 = r14.p     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            java.lang.String r6 = "error"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r0.a(r2, r5)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
        L_0x01f8:
            r14.t = r1
            if (r4 == 0) goto L_0x01ff
            r4.close()     // Catch:{ Exception -> 0x0204 }
        L_0x01ff:
            if (r3 == 0) goto L_0x0204
            r3.close()     // Catch:{ Exception -> 0x0204 }
        L_0x0204:
            return
        L_0x0205:
            int r2 = r14.c     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            if (r2 != r1) goto L_0x0210
            java.lang.String r2 = new java.lang.String     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r14.j = r2     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
        L_0x0210:
            if (r4 == 0) goto L_0x0219
            r4.close()     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            r14.n()     // Catch:{ Throwable -> 0x0236, all -> 0x0234 }
            goto L_0x021a
        L_0x0219:
            r0 = r4
        L_0x021a:
            com.mi.util.HttpDownloader$HttpDownloadListener r2 = r14.m     // Catch:{ Throwable -> 0x0232 }
            if (r2 == 0) goto L_0x0225
            com.mi.util.HttpDownloader$HttpDownloadListener r2 = r14.m     // Catch:{ Throwable -> 0x0232 }
            java.lang.Object r4 = r14.p     // Catch:{ Throwable -> 0x0232 }
            r2.b(r4)     // Catch:{ Throwable -> 0x0232 }
        L_0x0225:
            r14.t = r1
            if (r0 == 0) goto L_0x022c
            r0.close()     // Catch:{ Exception -> 0x0231 }
        L_0x022c:
            if (r3 == 0) goto L_0x0231
            r3.close()     // Catch:{ Exception -> 0x0231 }
        L_0x0231:
            return
        L_0x0232:
            r2 = move-exception
            goto L_0x0245
        L_0x0234:
            r2 = move-exception
            goto L_0x023a
        L_0x0236:
            r2 = move-exception
            goto L_0x023e
        L_0x0238:
            r2 = move-exception
            r3 = r0
        L_0x023a:
            r0 = r4
            goto L_0x025e
        L_0x023c:
            r2 = move-exception
            r3 = r0
        L_0x023e:
            r0 = r4
            goto L_0x0245
        L_0x0240:
            r2 = move-exception
            r3 = r0
            goto L_0x025e
        L_0x0243:
            r2 = move-exception
            r3 = r0
        L_0x0245:
            com.mi.util.HttpDownloader$HttpDownloadListener r4 = r14.m     // Catch:{ all -> 0x025d }
            if (r4 == 0) goto L_0x0250
            com.mi.util.HttpDownloader$HttpDownloadListener r4 = r14.m     // Catch:{ all -> 0x025d }
            java.lang.Object r5 = r14.p     // Catch:{ all -> 0x025d }
            r4.a(r5, r2)     // Catch:{ all -> 0x025d }
        L_0x0250:
            r14.t = r1
            if (r0 == 0) goto L_0x0257
            r0.close()     // Catch:{ Exception -> 0x025c }
        L_0x0257:
            if (r3 == 0) goto L_0x025c
            r3.close()     // Catch:{ Exception -> 0x025c }
        L_0x025c:
            return
        L_0x025d:
            r2 = move-exception
        L_0x025e:
            r14.t = r1
            if (r0 == 0) goto L_0x0265
            r0.close()     // Catch:{ Exception -> 0x026a }
        L_0x0265:
            if (r3 == 0) goto L_0x026a
            r3.close()     // Catch:{ Exception -> 0x026a }
        L_0x026a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.util.HttpDownloader.m():void");
    }

    private void n() {
        try {
            File file = new File(this.l);
            if (file.exists()) {
                file.delete();
            }
            new File(this.l + DefaultDiskStorage.FileType.TEMP).renameTo(file);
        } catch (Exception unused) {
        }
    }

    public void a(boolean z) {
        this.s = z;
    }
}
