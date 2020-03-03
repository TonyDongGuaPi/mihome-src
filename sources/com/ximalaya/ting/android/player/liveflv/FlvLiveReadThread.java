package com.ximalaya.ting.android.player.liveflv;

import com.ximalaya.ting.android.player.BufferItem;
import com.ximalaya.ting.android.player.Logger;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import java.util.concurrent.LinkedBlockingQueue;

public class FlvLiveReadThread extends Thread {
    private static final String g = "dl_hls";

    /* renamed from: a  reason: collision with root package name */
    long f2303a;
    long b;
    private volatile boolean c = false;
    private XMediaplayerJNI d;
    private String e;
    private volatile LinkedBlockingQueue<BufferItem> f;
    private boolean h = false;

    public FlvLiveReadThread(XMediaplayerJNI xMediaplayerJNI, String str, LinkedBlockingQueue<BufferItem> linkedBlockingQueue) {
        super("FlvLiveReadThreadForPlayer");
        this.d = xMediaplayerJNI;
        this.e = str;
        this.f = linkedBlockingQueue;
        this.c = false;
        setPriority(10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:111:0x034c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x034d, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x036a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x036b, code lost:
        r3 = r0;
        r0 = null;
        r4 = 0;
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0370, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0371, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ea, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x034c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:42:0x010b] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x036a A[ExcHandler: all (r0v48 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r9 
      PHI: (r9v28 long) = (r9v27 long), (r9v29 long), (r9v29 long) binds: [B:32:0x00b7, B:38:0x00ed, B:113:0x0350] A[DONT_GENERATE, DONT_INLINE], Splitter:B:32:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x03bb A[Catch:{ all -> 0x0476 }] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x041f A[Catch:{ all -> 0x0476 }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0432 A[Catch:{ all -> 0x0473 }] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0451 A[Catch:{ all -> 0x0473 }] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0461 A[Catch:{ all -> 0x0473 }] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0497  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x04bc A[SYNTHETIC, Splitter:B:180:0x04bc] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x04c7  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x04fd  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x050b  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0530  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x063f A[SYNTHETIC, Splitter:B:230:0x063f] */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x064a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a7 A[ExcHandler: all (th java.lang.Throwable), PHI: r9 
      PHI: (r9v34 long) = (r9v27 long), (r9v31 long), (r9v37 long), (r9v37 long) binds: [B:35:0x00bd, B:36:?, B:22:0x0081, B:23:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:22:0x0081] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ea A[ExcHandler: IOException (e java.io.IOException), PHI: r3 r9 
      PHI: (r3v23 java.io.InputStream) = (r3v21 java.io.InputStream), (r3v21 java.io.InputStream), (r3v0 java.io.InputStream), (r3v0 java.io.InputStream) binds: [B:42:0x010b, B:43:?, B:35:0x00bd, B:36:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r9v30 long) = (r9v29 long), (r9v29 long), (r9v27 long), (r9v31 long) binds: [B:42:0x010b, B:43:?, B:35:0x00bd, B:36:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:35:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0320 A[SYNTHETIC, Splitter:B:97:0x0320] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r21 = this;
            r1 = r21
            boolean r0 = r1.c
            if (r0 != 0) goto L_0x066b
            com.ximalaya.ting.android.player.XMediaplayerJNI r0 = r1.d
            java.lang.String r0 = r0.getPlayUrl()
            java.lang.String r0 = com.ximalaya.ting.android.player.MD5.b(r0)
            java.lang.String r2 = r1.e
            java.lang.String r2 = com.ximalaya.ting.android.player.MD5.b(r2)
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x066b
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r2 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            r3 = 0
            r4 = 0
            r5 = 1
            if (r2 != 0) goto L_0x0028
            r6 = r3
            r7 = 1
            goto L_0x003f
        L_0x0028:
            com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay r0 = new com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay
            r0.<init>()
            java.lang.String r6 = "play_flv_live"
            r0.f(r6)
            java.util.UUID r6 = java.util.UUID.randomUUID()
            java.lang.String r6 = r6.toString()
            r0.o(r6)
            r6 = r0
            r7 = 0
        L_0x003f:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.String r8 = "flv start run"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r8)
            java.lang.String r0 = r1.e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0051
            java.lang.String r0 = ""
            goto L_0x005b
        L_0x0051:
            java.lang.String r0 = r1.e
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.String r0 = r0.getHost()
        L_0x005b:
            java.lang.String[] r8 = new java.lang.String[r5]
            java.lang.String r9 = r1.e
            r8[r4] = r9
            r9 = 3
            java.lang.String r10 = "GET"
            java.net.HttpURLConnection r8 = com.ximalaya.ting.android.player.PlayerUtil.a(r8, r3, r9, r4, r10)
            r12 = 0
            boolean r15 = r1.c     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            if (r15 != 0) goto L_0x037b
            java.lang.String r15 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            java.lang.String r11 = "flv getConnectionUseDnsCache return httpUrlConnection"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r15, (java.lang.Object) r11)     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            if (r8 == 0) goto L_0x0373
            if (r6 == 0) goto L_0x00b6
            long r15 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x00b2, all -> 0x00ac }
            r11 = 0
            long r9 = r15 - r12
            float r11 = (float) r9
            float r11 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r11, (boolean) r4)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            r6.a((float) r11)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            java.net.URL r11 = r8.getURL()     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            r6.g(r11)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            r6.n(r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            java.lang.String r0 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r11)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            r6.h(r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            java.lang.String r0 = "via"
            java.lang.String r0 = r8.getHeaderField(r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            r6.m(r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00a7 }
            goto L_0x00b7
        L_0x00a7:
            r0 = move-exception
            goto L_0x00ae
        L_0x00a9:
            r0 = move-exception
            goto L_0x0391
        L_0x00ac:
            r0 = move-exception
            r9 = r12
        L_0x00ae:
            r5 = 0
            r14 = 0
            goto L_0x04ea
        L_0x00b2:
            r0 = move-exception
            r9 = r12
            goto L_0x0391
        L_0x00b6:
            r9 = r12
        L_0x00b7:
            int r11 = r8.getResponseCode()     // Catch:{ IOException -> 0x0370, all -> 0x036a }
            if (r6 == 0) goto L_0x00ed
            long r15 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            r0 = 0
            long r9 = r15 - r12
            float r0 = (float) r9     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            float r0 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r0, (boolean) r4)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            r6.a((float) r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            java.lang.String r0 = "via"
            java.lang.String r0 = r8.getHeaderField(r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            r6.m(r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            r0.<init>()     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            r0.append(r11)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            java.lang.String r15 = ""
            r0.append(r15)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            r6.l(r0)     // Catch:{ IOException -> 0x00ea, all -> 0x00a7 }
            goto L_0x00ed
        L_0x00ea:
            r0 = move-exception
            goto L_0x0392
        L_0x00ed:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            r15.<init>()     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            java.lang.String r3 = "flv responseCode:"
            r15.append(r3)     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            r15.append(r11)     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            java.lang.String r3 = r15.toString()     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            r0 = 200(0xc8, float:2.8E-43)
            if (r11 != r0) goto L_0x0350
            java.io.InputStream r3 = r8.getInputStream()     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            com.ximalaya.ting.android.player.liveflv.FlvAacParser r0 = new com.ximalaya.ting.android.player.liveflv.FlvAacParser     // Catch:{ IOException -> 0x00ea, all -> 0x034c }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00ea, all -> 0x034c }
            r15 = 4096(0x1000, float:5.74E-42)
            byte[] r15 = new byte[r15]     // Catch:{ IOException -> 0x00ea, all -> 0x034c }
            r12 = 0
            r14 = 0
        L_0x0116:
            boolean r13 = r1.c     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            if (r13 != 0) goto L_0x0148
            int r14 = r0.a((byte[]) r15, (int) r14)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            int r12 = r12 + r14
            byte[] r13 = new byte[r14]     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            java.lang.System.arraycopy(r15, r4, r13, r4, r14)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            com.ximalaya.ting.android.player.BufferItem r4 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r4.<init>()     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r4.a((byte[]) r13)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r1.a(r4)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r13.<init>()     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            java.lang.String r5 = "flv read buf len:"
            r13.append(r5)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r13.append(r14)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            java.lang.String r5 = r13.toString()     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r4 = 0
            r5 = 1
            goto L_0x0116
        L_0x0148:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            java.lang.String r4 = "flv read BufferItem last"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r4)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            com.ximalaya.ting.android.player.BufferItem r0 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r0.<init>()     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r0.d()     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r1.a(r0)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r4 = 1
            r1.c = r4     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            if (r6 == 0) goto L_0x01cc
            if (r12 <= 0) goto L_0x0167
            java.lang.String r0 = "success"
            r6.c(r0)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            goto L_0x016c
        L_0x0167:
            java.lang.String r0 = "failed"
            r6.c(r0)     // Catch:{ IOException -> 0x034a, all -> 0x034c }
        L_0x016c:
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x034a, all -> 0x034c }
            r0 = 0
            r13 = 0
            long r4 = r4 - r13
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0186
            float r0 = (float) r12
            r13 = 0
            float r0 = r0 + r13
            r14 = 1149239296(0x44800000, float:1024.0)
            float r0 = r0 / r14
            float r14 = (float) r4
            float r14 = r14 + r13
            r13 = 1148846080(0x447a0000, float:1000.0)
            float r14 = r14 / r13
            float r14 = r0 / r14
            goto L_0x0187
        L_0x0186:
            r14 = 0
        L_0x0187:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c9 }
            r0.<init>()     // Catch:{ IOException -> 0x01c9 }
            r0.append(r12)     // Catch:{ IOException -> 0x01c9 }
            java.lang.String r13 = ""
            r0.append(r13)     // Catch:{ IOException -> 0x01c9 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x01c9 }
            r6.a((java.lang.String) r0)     // Catch:{ IOException -> 0x01c9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c9 }
            r0.<init>()     // Catch:{ IOException -> 0x01c9 }
            r0.append(r4)     // Catch:{ IOException -> 0x01c9 }
            java.lang.String r4 = ""
            r0.append(r4)     // Catch:{ IOException -> 0x01c9 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x01c9 }
            r6.b((java.lang.String) r0)     // Catch:{ IOException -> 0x01c9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c9 }
            r0.<init>()     // Catch:{ IOException -> 0x01c9 }
            r4 = 1
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r14, (boolean) r4)     // Catch:{ IOException -> 0x01c9 }
            r0.append(r5)     // Catch:{ IOException -> 0x01c9 }
            java.lang.String r4 = ""
            r0.append(r4)     // Catch:{ IOException -> 0x01c9 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x01c9 }
            r6.i(r0)     // Catch:{ IOException -> 0x01c9 }
            goto L_0x01cd
        L_0x01c9:
            r0 = move-exception
            goto L_0x0394
        L_0x01cc:
            r14 = 0
        L_0x01cd:
            if (r7 != 0) goto L_0x031c
            if (r6 == 0) goto L_0x031c
            java.lang.String r0 = r6.o()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01df
            r4 = 0
            r6.m(r4)
        L_0x01df:
            java.lang.String r0 = r6.n()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01ee
            java.lang.String r0 = ""
            r6.l(r0)
        L_0x01ee:
            long r4 = java.lang.System.currentTimeMillis()
            r6.b((long) r4)
            java.lang.String r0 = r6.c()
            if (r0 == 0) goto L_0x0207
            java.lang.String r0 = r6.c()
            java.lang.String r4 = "success"
            boolean r0 = r0.contains(r4)
            if (r0 != 0) goto L_0x020c
        L_0x0207:
            java.lang.String r0 = "failed"
            r6.c(r0)
        L_0x020c:
            int r0 = r2.f()
            r4 = -1
            if (r0 != r4) goto L_0x0216
            r7 = 1
            goto L_0x031c
        L_0x0216:
            if (r0 != 0) goto L_0x0297
            int r0 = r2.h()
            long r4 = (long) r0
            r1.f2303a = r4
            int r0 = r2.j()
            long r4 = (long) r0
            r1.b = r4
            long r4 = r1.f2303a
            r11 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r11
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0262
            java.lang.String r0 = "cdn_connected_too_slow"
            r6.k(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "connected_time="
            r0.append(r4)
            float r4 = (float) r9
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)
            r0.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r0.append(r4)
            long r4 = r1.f2303a
            r0.append(r4)
            java.lang.String r4 = "s"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r6.j(r0)
        L_0x025e:
            r19 = 1
            goto L_0x031e
        L_0x0262:
            long r4 = r1.b
            float r0 = (float) r4
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x031c
            java.lang.String r0 = "cdn_download_too_slow"
            r6.k(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "download_speed="
            r0.append(r4)
            r4 = 1
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r14, (boolean) r4)
            r0.append(r5)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r0.append(r4)
            long r4 = r1.b
            r0.append(r4)
            java.lang.String r4 = "KB/s"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r6.j(r0)
            goto L_0x025e
        L_0x0297:
            r4 = 1
            if (r0 != r4) goto L_0x031c
            int r0 = r2.g()
            long r4 = (long) r0
            r1.f2303a = r4
            int r0 = r2.i()
            long r4 = (long) r0
            r1.b = r4
            r4 = 0
            r1.f2303a = r4
            long r4 = r1.f2303a
            r11 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r11
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x02e6
            java.lang.String r0 = "cdn_connected_too_slow"
            r6.k(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "connected_time="
            r0.append(r4)
            float r4 = (float) r9
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)
            r0.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r0.append(r4)
            long r4 = r1.f2303a
            r0.append(r4)
            java.lang.String r4 = "s"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r6.j(r0)
            goto L_0x025e
        L_0x02e6:
            long r4 = r1.b
            float r0 = (float) r4
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x031c
            java.lang.String r0 = "cdn_download_too_slow"
            r6.k(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "download_speed="
            r0.append(r4)
            r4 = 1
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r14, (boolean) r4)
            r0.append(r5)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r0.append(r4)
            long r4 = r1.b
            r0.append(r4)
            java.lang.String r4 = "KB/s"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r6.j(r0)
            goto L_0x025e
        L_0x031c:
            r19 = 0
        L_0x031e:
            if (r3 == 0) goto L_0x0329
            r3.close()     // Catch:{ IOException -> 0x0324 }
            goto L_0x0329
        L_0x0324:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x0329:
            if (r8 == 0) goto L_0x032e
            r8.disconnect()
        L_0x032e:
            if (r6 == 0) goto L_0x066b
            if (r19 == 0) goto L_0x066b
            if (r7 != 0) goto L_0x066b
            java.lang.String r0 = r6.m()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x066b
            java.lang.String r0 = r6.l()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x066b
            goto L_0x04e2
        L_0x034a:
            r0 = move-exception
            goto L_0x0393
        L_0x034c:
            r0 = move-exception
            r4 = 0
            goto L_0x00ae
        L_0x0350:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            r3.<init>()     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            java.lang.String r4 = "httpCode"
            r3.append(r4)     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            r3.append(r11)     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0367, all -> 0x036a }
            throw r0     // Catch:{ IOException -> 0x0367, all -> 0x036a }
        L_0x0367:
            r0 = move-exception
            r3 = 0
            goto L_0x0392
        L_0x036a:
            r0 = move-exception
            r3 = r0
            r0 = 0
            r4 = 0
            r5 = 0
            goto L_0x038a
        L_0x0370:
            r0 = move-exception
            r3 = 0
            goto L_0x0391
        L_0x0373:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            java.lang.String r3 = "httpUrlConnection is null"
            r0.<init>(r3)     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            throw r0     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
        L_0x037b:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            java.lang.String r3 = "Connection timeout flv has stop"
            r0.<init>(r3)     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
            throw r0     // Catch:{ IOException -> 0x038d, all -> 0x0383 }
        L_0x0383:
            r0 = move-exception
            r3 = r0
            r0 = 0
            r4 = 0
            r5 = 0
            r9 = 0
        L_0x038a:
            r14 = 0
            goto L_0x04ef
        L_0x038d:
            r0 = move-exception
            r3 = 0
            r9 = 0
        L_0x0391:
            r11 = 0
        L_0x0392:
            r12 = 0
        L_0x0393:
            r14 = 0
        L_0x0394:
            r0.printStackTrace()     // Catch:{ all -> 0x04e7 }
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x04e7 }
            java.lang.String r5 = "flv read BufferItem last exception"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x04e7 }
            com.ximalaya.ting.android.player.BufferItem r4 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ all -> 0x04e7 }
            r4.<init>()     // Catch:{ all -> 0x04e7 }
            r5 = 1
            r4.b = r5     // Catch:{ all -> 0x04e7 }
            r4.c = r11     // Catch:{ all -> 0x04e7 }
            r1.a(r4)     // Catch:{ all -> 0x04e7 }
            r1.c = r5     // Catch:{ all -> 0x04e7 }
            if (r7 != 0) goto L_0x047b
            if (r6 == 0) goto L_0x047b
            java.lang.String r4 = r6.k()     // Catch:{ all -> 0x0476 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0476 }
            if (r4 == 0) goto L_0x0416
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0476 }
            r11 = 0
            r17 = 0
            long r4 = r4 - r17
            int r11 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
            if (r11 == 0) goto L_0x03d5
            float r11 = (float) r12     // Catch:{ all -> 0x0476 }
            r13 = 0
            float r11 = r11 + r13
            r14 = 1149239296(0x44800000, float:1024.0)
            float r11 = r11 / r14
            float r14 = (float) r4     // Catch:{ all -> 0x0476 }
            float r14 = r14 + r13
            r13 = 1148846080(0x447a0000, float:1000.0)
            float r14 = r14 / r13
            float r11 = r11 / r14
            r14 = r11
        L_0x03d5:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0476 }
            r11.<init>()     // Catch:{ all -> 0x0476 }
            r13 = 1
            float r15 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r14, (boolean) r13)     // Catch:{ all -> 0x0476 }
            r11.append(r15)     // Catch:{ all -> 0x0476 }
            java.lang.String r13 = ""
            r11.append(r13)     // Catch:{ all -> 0x0476 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0476 }
            r6.i(r11)     // Catch:{ all -> 0x0476 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0476 }
            r11.<init>()     // Catch:{ all -> 0x0476 }
            r11.append(r12)     // Catch:{ all -> 0x0476 }
            java.lang.String r12 = ""
            r11.append(r12)     // Catch:{ all -> 0x0476 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0476 }
            r6.a((java.lang.String) r11)     // Catch:{ all -> 0x0476 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0476 }
            r11.<init>()     // Catch:{ all -> 0x0476 }
            r11.append(r4)     // Catch:{ all -> 0x0476 }
            java.lang.String r4 = ""
            r11.append(r4)     // Catch:{ all -> 0x0476 }
            java.lang.String r4 = r11.toString()     // Catch:{ all -> 0x0476 }
            r6.b((java.lang.String) r4)     // Catch:{ all -> 0x0476 }
        L_0x0416:
            float r4 = r6.p()     // Catch:{ all -> 0x0476 }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0432
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0476 }
            r9 = 0
            r9 = 0
            long r12 = r4 - r9
            float r4 = (float) r12
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)     // Catch:{ all -> 0x0473 }
            r6.a((float) r4)     // Catch:{ all -> 0x0473 }
            goto L_0x0433
        L_0x0432:
            r12 = r9
        L_0x0433:
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x0473 }
            if (r4 == 0) goto L_0x0461
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x0473 }
            java.lang.String r5 = "ENOSPC"
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0473 }
            if (r4 != 0) goto L_0x0451
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x0473 }
            java.lang.String r5 = "EACCES"
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0473 }
            if (r4 == 0) goto L_0x0461
        L_0x0451:
            java.lang.String r4 = "0"
            r6.a((java.lang.String) r4)     // Catch:{ all -> 0x0473 }
            java.lang.String r4 = "0"
            r6.b((java.lang.String) r4)     // Catch:{ all -> 0x0473 }
            java.lang.String r4 = "system_exception"
            r6.k(r4)     // Catch:{ all -> 0x0473 }
            goto L_0x0466
        L_0x0461:
            java.lang.String r4 = "cdn_io_exception"
            r6.k(r4)     // Catch:{ all -> 0x0473 }
        L_0x0466:
            java.lang.String r0 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r0)     // Catch:{ all -> 0x0473 }
            r6.j(r0)     // Catch:{ all -> 0x0473 }
            java.lang.String r0 = "failed"
            r6.c(r0)     // Catch:{ all -> 0x0473 }
            goto L_0x047b
        L_0x0473:
            r0 = move-exception
            r9 = r12
            goto L_0x0477
        L_0x0476:
            r0 = move-exception
        L_0x0477:
            r4 = 1
            r5 = 1
            goto L_0x04ea
        L_0x047b:
            if (r7 != 0) goto L_0x04ba
            if (r6 == 0) goto L_0x04ba
            java.lang.String r0 = r6.o()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x048d
            r4 = 0
            r6.m(r4)
        L_0x048d:
            java.lang.String r0 = r6.n()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x049c
            java.lang.String r0 = ""
            r6.l(r0)
        L_0x049c:
            long r4 = java.lang.System.currentTimeMillis()
            r6.b((long) r4)
            java.lang.String r0 = r6.c()
            if (r0 == 0) goto L_0x04b5
            java.lang.String r0 = r6.c()
            java.lang.String r4 = "success"
            boolean r0 = r0.contains(r4)
            if (r0 != 0) goto L_0x04ba
        L_0x04b5:
            java.lang.String r0 = "failed"
            r6.c(r0)
        L_0x04ba:
            if (r3 == 0) goto L_0x04c5
            r3.close()     // Catch:{ IOException -> 0x04c0 }
            goto L_0x04c5
        L_0x04c0:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        L_0x04c5:
            if (r8 == 0) goto L_0x04ca
            r8.disconnect()
        L_0x04ca:
            if (r6 == 0) goto L_0x066b
            if (r7 != 0) goto L_0x066b
            java.lang.String r0 = r6.m()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x066b
            java.lang.String r0 = r6.l()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x066b
        L_0x04e2:
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r6, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
            goto L_0x066b
        L_0x04e7:
            r0 = move-exception
            r4 = 0
            r5 = 0
        L_0x04ea:
            r20 = r3
            r3 = r0
            r0 = r20
        L_0x04ef:
            if (r7 != 0) goto L_0x063d
            if (r6 == 0) goto L_0x063d
            java.lang.String r11 = r6.o()
            boolean r11 = android.text.TextUtils.isEmpty(r11)
            if (r11 == 0) goto L_0x0501
            r11 = 0
            r6.m(r11)
        L_0x0501:
            java.lang.String r11 = r6.n()
            boolean r11 = android.text.TextUtils.isEmpty(r11)
            if (r11 == 0) goto L_0x0510
            java.lang.String r11 = ""
            r6.l(r11)
        L_0x0510:
            long r11 = java.lang.System.currentTimeMillis()
            r6.b((long) r11)
            java.lang.String r11 = r6.c()
            if (r11 == 0) goto L_0x0529
            java.lang.String r11 = r6.c()
            java.lang.String r12 = "success"
            boolean r11 = r11.contains(r12)
            if (r11 != 0) goto L_0x052e
        L_0x0529:
            java.lang.String r11 = "failed"
            r6.c(r11)
        L_0x052e:
            if (r4 != 0) goto L_0x063d
            int r4 = r2.f()
            r11 = -1
            if (r4 != r11) goto L_0x053a
            r7 = 1
            goto L_0x063d
        L_0x053a:
            if (r4 != 0) goto L_0x05ba
            int r4 = r2.h()
            long r11 = (long) r4
            r1.f2303a = r11
            int r4 = r2.j()
            long r11 = (long) r4
            r1.b = r11
            long r11 = r1.f2303a
            r15 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 * r15
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r4 <= 0) goto L_0x0585
            java.lang.String r4 = "cdn_connected_too_slow"
            r6.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "connected_time="
            r4.append(r5)
            float r5 = (float) r9
            r9 = 0
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r5, (boolean) r9)
            r4.append(r5)
            java.lang.String r5 = "s, connected_time_threshold="
            r4.append(r5)
            long r9 = r1.f2303a
            r4.append(r9)
            java.lang.String r5 = "s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r6.j(r4)
        L_0x0582:
            r5 = 1
            goto L_0x063d
        L_0x0585:
            long r9 = r1.b
            float r4 = (float) r9
            int r4 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x063d
            java.lang.String r4 = "cdn_download_too_slow"
            r6.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "download_speed="
            r4.append(r5)
            r5 = 1
            float r9 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r14, (boolean) r5)
            r4.append(r9)
            java.lang.String r5 = "KB/s, download_speed_threshold="
            r4.append(r5)
            long r9 = r1.b
            r4.append(r9)
            java.lang.String r5 = "KB/s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r6.j(r4)
            goto L_0x0582
        L_0x05ba:
            r11 = 1
            if (r4 != r11) goto L_0x063d
            int r4 = r2.g()
            long r11 = (long) r4
            r1.f2303a = r11
            int r4 = r2.i()
            long r11 = (long) r4
            r1.b = r11
            r11 = 0
            r1.f2303a = r11
            long r11 = r1.f2303a
            r15 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 * r15
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r4 <= 0) goto L_0x0609
            java.lang.String r4 = "cdn_connected_too_slow"
            r6.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "connected_time="
            r4.append(r5)
            float r5 = (float) r9
            r9 = 0
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r5, (boolean) r9)
            r4.append(r5)
            java.lang.String r5 = "s, connected_time_threshold="
            r4.append(r5)
            long r9 = r1.f2303a
            r4.append(r9)
            java.lang.String r5 = "s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r6.j(r4)
            goto L_0x0582
        L_0x0609:
            long r9 = r1.b
            float r4 = (float) r9
            int r4 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r4 <= 0) goto L_0x063d
            java.lang.String r4 = "cdn_download_too_slow"
            r6.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "download_speed="
            r4.append(r5)
            r5 = 1
            float r9 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r14, (boolean) r5)
            r4.append(r9)
            java.lang.String r9 = "KB/s, download_speed_threshold="
            r4.append(r9)
            long r9 = r1.b
            r4.append(r9)
            java.lang.String r9 = "KB/s"
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r6.j(r4)
        L_0x063d:
            if (r0 == 0) goto L_0x0648
            r0.close()     // Catch:{ IOException -> 0x0643 }
            goto L_0x0648
        L_0x0643:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()
        L_0x0648:
            if (r8 == 0) goto L_0x064d
            r8.disconnect()
        L_0x064d:
            if (r6 == 0) goto L_0x066a
            if (r5 == 0) goto L_0x066a
            if (r7 != 0) goto L_0x066a
            java.lang.String r0 = r6.m()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x066a
            java.lang.String r0 = r6.l()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x066a
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r6, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x066a:
            throw r3
        L_0x066b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.liveflv.FlvLiveReadThread.run():void");
    }

    public boolean a() {
        return this.h;
    }

    private void a(BufferItem bufferItem) {
        Logger.a(g, (Object) "putItem buffItemQueue.size()0:" + this.f.size());
        if (this.f.remainingCapacity() < 5) {
            this.h = true;
        } else {
            this.h = false;
        }
        try {
            this.f.put(bufferItem);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        Logger.a(g, (Object) "putItem buffItemQueue.size()1:" + this.f.size());
    }

    public void b() {
        this.c = true;
        if (this.f != null) {
            this.f.clear();
        }
        interrupt();
        Logger.a(XMediaplayerJNI.Tag, (Object) "HlsReadThread hls readData close");
    }

    public boolean c() {
        return this.c;
    }
}
