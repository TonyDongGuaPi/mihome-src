package com.liulishuo.filedownloader.download;

import android.os.SystemClock;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;

public class FetchDataTask {

    /* renamed from: a  reason: collision with root package name */
    static final int f6428a = 4096;
    long b;
    private final ProcessCallback c;
    private final int d;
    private final int e;
    private final DownloadRunnable f;
    private final FileDownloadConnection g;
    private final boolean h;
    private final long i;
    private final long j;
    private final long k;
    private final String l;
    private FileDownloadOutputStream m;
    private volatile boolean n;
    private final FileDownloadDatabase o;
    private volatile long p;
    private volatile long q;

    public void a() {
        this.n = true;
    }

    private FetchDataTask(FileDownloadConnection fileDownloadConnection, ConnectionProfile connectionProfile, DownloadRunnable downloadRunnable, int i2, int i3, boolean z, ProcessCallback processCallback, String str) {
        this.p = 0;
        this.q = 0;
        this.c = processCallback;
        this.l = str;
        this.g = fileDownloadConnection;
        this.h = z;
        this.f = downloadRunnable;
        this.e = i3;
        this.d = i2;
        this.o = CustomComponentHolder.a().c();
        this.i = connectionProfile.f6419a;
        this.j = connectionProfile.c;
        this.b = connectionProfile.b;
        this.k = connectionProfile.d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:131:0x01fa A[SYNTHETIC, Splitter:B:131:0x01fa] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0205 A[SYNTHETIC, Splitter:B:136:0x0205] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0219 A[SYNTHETIC, Splitter:B:147:0x0219] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() throws java.io.IOException, java.lang.IllegalAccessException, java.lang.IllegalArgumentException, com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException {
        /*
            r16 = this;
            r1 = r16
            boolean r0 = r1.n
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            int r0 = r1.e
            com.liulishuo.filedownloader.connection.FileDownloadConnection r2 = r1.g
            long r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.b((int) r0, (com.liulishuo.filedownloader.connection.FileDownloadConnection) r2)
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r6 = 2
            r7 = 1
            r8 = 0
            if (r0 == 0) goto L_0x0223
            long r9 = r1.k
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            r9 = 5
            r10 = 4
            r11 = 3
            if (r0 <= 0) goto L_0x0084
            long r12 = r1.k
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0084
            long r12 = r1.j
            int r0 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x003e
            java.lang.Object[] r0 = new java.lang.Object[r7]
            long r4 = r1.b
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0[r8] = r4
            java.lang.String r4 = "range[%d-)"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r4, (java.lang.Object[]) r0)
            goto L_0x0056
        L_0x003e:
            java.lang.Object[] r0 = new java.lang.Object[r6]
            long r4 = r1.b
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0[r8] = r4
            long r4 = r1.j
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0[r7] = r4
            java.lang.String r4 = "range[%d-%d)"
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r4, (java.lang.Object[]) r0)
        L_0x0056:
            com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException r4 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException
            java.lang.Object[] r5 = new java.lang.Object[r9]
            r5[r8] = r0
            long r8 = r1.k
            java.lang.Long r0 = java.lang.Long.valueOf(r8)
            r5[r7] = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r2)
            r5[r6] = r0
            int r0 = r1.d
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r11] = r0
            int r0 = r1.e
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r10] = r0
            java.lang.String r0 = "require %s with contentLength(%d), but the backend response contentLength is %d on downloadId[%d]-connectionIndex[%d], please ask your backend dev to fix such problem."
            java.lang.String r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r0, (java.lang.Object[]) r5)
            r4.<init>(r0)
            throw r4
        L_0x0084:
            long r4 = r1.b
            r12 = 0
            com.liulishuo.filedownloader.download.CustomComponentHolder r0 = com.liulishuo.filedownloader.download.CustomComponentHolder.a()     // Catch:{ all -> 0x01f5 }
            boolean r0 = r0.e()     // Catch:{ all -> 0x01f5 }
            com.liulishuo.filedownloader.download.DownloadRunnable r13 = r1.f     // Catch:{ all -> 0x01f5 }
            if (r13 == 0) goto L_0x009e
            if (r0 == 0) goto L_0x0096
            goto L_0x009e
        L_0x0096:
            java.lang.IllegalAccessException r0 = new java.lang.IllegalAccessException     // Catch:{ all -> 0x01f5 }
            java.lang.String r2 = "can't using multi-download when the output stream can't support seek"
            r0.<init>(r2)     // Catch:{ all -> 0x01f5 }
            throw r0     // Catch:{ all -> 0x01f5 }
        L_0x009e:
            java.lang.String r13 = r1.l     // Catch:{ all -> 0x01f5 }
            com.liulishuo.filedownloader.stream.FileDownloadOutputStream r13 = com.liulishuo.filedownloader.util.FileDownloadUtils.n(r13)     // Catch:{ all -> 0x01f5 }
            r1.m = r13     // Catch:{ all -> 0x01f2 }
            if (r0 == 0) goto L_0x00ad
            long r14 = r1.b     // Catch:{ all -> 0x01f2 }
            r13.a(r14)     // Catch:{ all -> 0x01f2 }
        L_0x00ad:
            boolean r0 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ all -> 0x01f2 }
            if (r0 == 0) goto L_0x00d9
            java.lang.String r0 = "start fetch(%d): range [%d, %d), seek to[%d]"
            java.lang.Object[] r14 = new java.lang.Object[r10]     // Catch:{ all -> 0x01f2 }
            int r15 = r1.e     // Catch:{ all -> 0x01f2 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x01f2 }
            r14[r8] = r15     // Catch:{ all -> 0x01f2 }
            long r9 = r1.i     // Catch:{ all -> 0x01f2 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x01f2 }
            r14[r7] = r9     // Catch:{ all -> 0x01f2 }
            long r9 = r1.j     // Catch:{ all -> 0x01f2 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x01f2 }
            r14[r6] = r9     // Catch:{ all -> 0x01f2 }
            long r9 = r1.b     // Catch:{ all -> 0x01f2 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x01f2 }
            r14[r11] = r9     // Catch:{ all -> 0x01f2 }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r1, r0, r14)     // Catch:{ all -> 0x01f2 }
        L_0x00d9:
            com.liulishuo.filedownloader.connection.FileDownloadConnection r0 = r1.g     // Catch:{ all -> 0x01f2 }
            java.io.InputStream r9 = r0.a()     // Catch:{ all -> 0x01f2 }
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x01ee }
            boolean r10 = r1.n     // Catch:{ all -> 0x01ee }
            if (r10 == 0) goto L_0x0112
            if (r9 == 0) goto L_0x00f2
            r9.close()     // Catch:{ IOException -> 0x00ed }
            goto L_0x00f2
        L_0x00ed:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x00f2:
            if (r13 == 0) goto L_0x0106
            r16.d()     // Catch:{ all -> 0x00f8 }
            goto L_0x0106
        L_0x00f8:
            r0 = move-exception
            r2 = r0
            if (r13 == 0) goto L_0x0105
            r13.b()     // Catch:{ IOException -> 0x0100 }
            goto L_0x0105
        L_0x0100:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0105:
            throw r2
        L_0x0106:
            if (r13 == 0) goto L_0x0111
            r13.b()     // Catch:{ IOException -> 0x010c }
            goto L_0x0111
        L_0x010c:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0111:
            return
        L_0x0112:
            int r10 = r9.read(r0)     // Catch:{ all -> 0x01ee }
            r12 = -1
            if (r10 != r12) goto L_0x0198
            if (r9 == 0) goto L_0x0124
            r9.close()     // Catch:{ IOException -> 0x011f }
            goto L_0x0124
        L_0x011f:
            r0 = move-exception
            r9 = r0
            r9.printStackTrace()
        L_0x0124:
            if (r13 == 0) goto L_0x0138
            r16.d()     // Catch:{ all -> 0x012a }
            goto L_0x0138
        L_0x012a:
            r0 = move-exception
            r2 = r0
            if (r13 == 0) goto L_0x0137
            r13.b()     // Catch:{ IOException -> 0x0132 }
            goto L_0x0137
        L_0x0132:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0137:
            throw r2
        L_0x0138:
            if (r13 == 0) goto L_0x0143
            r13.b()     // Catch:{ IOException -> 0x013e }
            goto L_0x0143
        L_0x013e:
            r0 = move-exception
            r9 = r0
            r9.printStackTrace()
        L_0x0143:
            long r9 = r1.b
            long r9 = r9 - r4
            r12 = -1
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x018c
            int r0 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r0 != 0) goto L_0x0151
            goto L_0x018c
        L_0x0151:
            com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException r0 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException
            r12 = 6
            java.lang.Object[] r12 = new java.lang.Object[r12]
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            r12[r8] = r9
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12[r7] = r2
            long r2 = r1.i
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12[r6] = r2
            long r2 = r1.j
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12[r11] = r2
            long r2 = r1.b
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r14 = 4
            r12[r14] = r2
            java.lang.Long r2 = java.lang.Long.valueOf(r4)
            r15 = 5
            r12[r15] = r2
            java.lang.String r2 = "fetched length[%d] != content length[%d], range[%d, %d) offset[%d] fetch begin offset"
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r2, (java.lang.Object[]) r12)
            r0.<init>(r2)
            throw r0
        L_0x018c:
            com.liulishuo.filedownloader.download.ProcessCallback r3 = r1.c
            com.liulishuo.filedownloader.download.DownloadRunnable r4 = r1.f
            long r5 = r1.i
            long r7 = r1.j
            r3.a(r4, r5, r7)
            return
        L_0x0198:
            r14 = 4
            r15 = 5
            r13.a(r0, r8, r10)     // Catch:{ all -> 0x01ee }
            long r11 = r1.b     // Catch:{ all -> 0x01ee }
            long r14 = (long) r10     // Catch:{ all -> 0x01ee }
            long r11 = r11 + r14
            r1.b = r11     // Catch:{ all -> 0x01ee }
            com.liulishuo.filedownloader.download.ProcessCallback r10 = r1.c     // Catch:{ all -> 0x01ee }
            r10.a((long) r14)     // Catch:{ all -> 0x01ee }
            r16.c()     // Catch:{ all -> 0x01ee }
            boolean r10 = r1.n     // Catch:{ all -> 0x01ee }
            if (r10 == 0) goto L_0x01da
            if (r9 == 0) goto L_0x01ba
            r9.close()     // Catch:{ IOException -> 0x01b5 }
            goto L_0x01ba
        L_0x01b5:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x01ba:
            if (r13 == 0) goto L_0x01ce
            r16.d()     // Catch:{ all -> 0x01c0 }
            goto L_0x01ce
        L_0x01c0:
            r0 = move-exception
            r2 = r0
            if (r13 == 0) goto L_0x01cd
            r13.b()     // Catch:{ IOException -> 0x01c8 }
            goto L_0x01cd
        L_0x01c8:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x01cd:
            throw r2
        L_0x01ce:
            if (r13 == 0) goto L_0x01d9
            r13.b()     // Catch:{ IOException -> 0x01d4 }
            goto L_0x01d9
        L_0x01d4:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x01d9:
            return
        L_0x01da:
            boolean r10 = r1.h     // Catch:{ all -> 0x01ee }
            if (r10 == 0) goto L_0x01eb
            boolean r10 = com.liulishuo.filedownloader.util.FileDownloadUtils.e()     // Catch:{ all -> 0x01ee }
            if (r10 != 0) goto L_0x01e5
            goto L_0x01eb
        L_0x01e5:
            com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException r0 = new com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException     // Catch:{ all -> 0x01ee }
            r0.<init>()     // Catch:{ all -> 0x01ee }
            throw r0     // Catch:{ all -> 0x01ee }
        L_0x01eb:
            r11 = 3
            goto L_0x0112
        L_0x01ee:
            r0 = move-exception
            r2 = r0
            r12 = r9
            goto L_0x01f8
        L_0x01f2:
            r0 = move-exception
            r2 = r0
            goto L_0x01f8
        L_0x01f5:
            r0 = move-exception
            r2 = r0
            r13 = r12
        L_0x01f8:
            if (r12 == 0) goto L_0x0203
            r12.close()     // Catch:{ IOException -> 0x01fe }
            goto L_0x0203
        L_0x01fe:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0203:
            if (r13 == 0) goto L_0x0217
            r16.d()     // Catch:{ all -> 0x0209 }
            goto L_0x0217
        L_0x0209:
            r0 = move-exception
            r2 = r0
            if (r13 == 0) goto L_0x0216
            r13.b()     // Catch:{ IOException -> 0x0211 }
            goto L_0x0216
        L_0x0211:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0216:
            throw r2
        L_0x0217:
            if (r13 == 0) goto L_0x0222
            r13.b()     // Catch:{ IOException -> 0x021d }
            goto L_0x0222
        L_0x021d:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0222:
            throw r2
        L_0x0223:
            com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException r0 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException
            java.lang.Object[] r2 = new java.lang.Object[r6]
            int r3 = r1.d
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            int r3 = r1.e
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r7] = r3
            java.lang.String r3 = "there isn't any content need to download on %d-%d with the content-length is 0"
            java.lang.String r2 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r3, (java.lang.Object[]) r2)
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.FetchDataTask.b():void");
    }

    private void c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (FileDownloadUtils.a(this.b - this.p, elapsedRealtime - this.q)) {
            d();
            this.p = this.b;
            this.q = elapsedRealtime;
        }
    }

    private void d() {
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            this.m.a();
            z = true;
        } catch (IOException e2) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "Because of the system cannot guarantee that all the buffers have been synchronized with physical media, or write to file failed, we just not flushAndSync process to database too %s", e2);
            }
            z = false;
        }
        if (z) {
            if (this.f != null) {
                this.o.a(this.d, this.e, this.b);
            } else {
                this.c.c();
            }
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "require flushAndSync id[%d] index[%d] offset[%d], consume[%d]", Integer.valueOf(this.d), Integer.valueOf(this.e), Long.valueOf(this.b), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
            }
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        DownloadRunnable f6429a;
        FileDownloadConnection b;
        ConnectionProfile c;
        ProcessCallback d;
        String e;
        Boolean f;
        Integer g;
        Integer h;

        public Builder a(FileDownloadConnection fileDownloadConnection) {
            this.b = fileDownloadConnection;
            return this;
        }

        public Builder a(ConnectionProfile connectionProfile) {
            this.c = connectionProfile;
            return this;
        }

        public Builder a(ProcessCallback processCallback) {
            this.d = processCallback;
            return this;
        }

        public Builder a(String str) {
            this.e = str;
            return this;
        }

        public Builder a(boolean z) {
            this.f = Boolean.valueOf(z);
            return this;
        }

        public Builder a(DownloadRunnable downloadRunnable) {
            this.f6429a = downloadRunnable;
            return this;
        }

        public Builder a(int i) {
            this.g = Integer.valueOf(i);
            return this;
        }

        public Builder b(int i) {
            this.h = Integer.valueOf(i);
            return this;
        }

        public FetchDataTask a() throws IllegalArgumentException {
            if (this.f != null && this.b != null && this.c != null && this.d != null && this.e != null && this.h != null && this.g != null) {
                return new FetchDataTask(this.b, this.c, this.f6429a, this.h.intValue(), this.g.intValue(), this.f.booleanValue(), this.d, this.e);
            }
            throw new IllegalArgumentException();
        }
    }
}
