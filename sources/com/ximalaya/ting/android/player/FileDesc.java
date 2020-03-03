package com.ximalaya.ting.android.player;

import com.taobao.weex.el.parse.Operators;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

public class FileDesc {
    private static final String f = "dl_mp3";

    /* renamed from: a  reason: collision with root package name */
    public volatile boolean f2271a = false;
    public volatile int b;
    public volatile BitSet c;
    public volatile ArrayList<Integer> d;
    public volatile ArrayList<Integer> e;
    private String g;
    private long h;
    private int i;
    private int j;
    private String k;
    private String l;

    private boolean h() {
        File file = new File(XMediaPlayerConstants.o);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        Logger.a(f, (Object) "目录不存在，创建失败！" + XMediaPlayerConstants.o);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileDesc(java.lang.String r11, java.lang.String r12) throws java.io.IOException {
        /*
            r10 = this;
            r10.<init>()
            r0 = 0
            r10.f2271a = r0
            java.lang.String r1 = "dl_mp3"
            java.lang.String r2 = "======================FileDesc Constructor()"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)
            r10.l = r11
            r10.g = r12
            r10.h()
            java.lang.String r1 = com.ximalaya.ting.android.player.MD5.b(r12)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            java.lang.String r3 = "/"
            r2.append(r3)
            r2.append(r1)
            java.lang.String r3 = ".index"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r11)
            java.lang.String r5 = "/"
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = ".chunk"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.io.File r4 = new java.io.File
            r4.<init>(r1)
            boolean r1 = r3.exists()
            boolean r5 = r4.exists()
            if (r1 == r5) goto L_0x0064
            r3.delete()
            r4.delete()
        L_0x0064:
            boolean r1 = r3.exists()
            if (r1 != 0) goto L_0x006f
            r10.a((java.lang.String) r11, (java.lang.String) r12)
            goto L_0x012a
        L_0x006f:
            r1 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0116, all -> 0x0112 }
            r5.<init>(r2)     // Catch:{ Exception -> 0x0116, all -> 0x0112 }
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Exception -> 0x010f, all -> 0x010c }
            r2.<init>(r5)     // Catch:{ Exception -> 0x010f, all -> 0x010c }
            java.lang.String r1 = r2.readUTF()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.g = r1     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r1 = r10.g     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            if (r1 == 0) goto L_0x00fa
            java.lang.String r1 = r10.g     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r1 = com.ximalaya.ting.android.player.MD5.b(r1)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r6 = com.ximalaya.ting.android.player.MD5.b(r12)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            boolean r1 = r1.equals(r6)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            if (r1 != 0) goto L_0x0095
            goto L_0x00fa
        L_0x0095:
            long r6 = r2.readLong()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.h = r6     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r1 = r2.readUTF()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.k = r1     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            long r6 = r10.h     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            float r1 = (float) r6     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r6 = 1199570944(0x47800000, float:65536.0)
            float r1 = r1 / r6
            double r6 = (double) r1     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            double r6 = java.lang.Math.ceil(r6)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            int r1 = (int) r6     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r6 = "dl_mp3"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r7.<init>()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r8 = "calc002==comChunkNum==:"
            r7.append(r8)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            long r8 = r10.h     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r7.append(r8)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r8 = ", "
            r7.append(r8)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r7.append(r1)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.i = r1     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            int r6 = r2.readInt()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.j = r6     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r6.<init>()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
        L_0x00da:
            int r7 = r10.j     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            if (r0 >= r7) goto L_0x00ec
            int r7 = r2.readInt()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r6.add(r7)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            int r0 = r0 + 1
            goto L_0x00da
        L_0x00ec:
            int r0 = r10.i     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            if (r0 <= 0) goto L_0x00f6
            r0 = 1
            r10.f2271a = r0     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.a((java.util.ArrayList<java.lang.Integer>) r6, (int) r1)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
        L_0x00f6:
            r5.close()
            goto L_0x0127
        L_0x00fa:
            r3.delete()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r4.delete()     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r10.a((java.lang.String) r11, (java.lang.String) r12)     // Catch:{ Exception -> 0x0110, all -> 0x010a }
            r5.close()
            r2.close()
            return
        L_0x010a:
            r11 = move-exception
            goto L_0x012d
        L_0x010c:
            r11 = move-exception
            r2 = r1
            goto L_0x012d
        L_0x010f:
            r2 = r1
        L_0x0110:
            r1 = r5
            goto L_0x0117
        L_0x0112:
            r11 = move-exception
            r2 = r1
            r5 = r2
            goto L_0x012d
        L_0x0116:
            r2 = r1
        L_0x0117:
            r3.delete()     // Catch:{ all -> 0x012b }
            r4.delete()     // Catch:{ all -> 0x012b }
            r10.a((java.lang.String) r11, (java.lang.String) r12)     // Catch:{ all -> 0x012b }
            if (r1 == 0) goto L_0x0125
            r1.close()
        L_0x0125:
            if (r2 == 0) goto L_0x012a
        L_0x0127:
            r2.close()
        L_0x012a:
            return
        L_0x012b:
            r11 = move-exception
            r5 = r1
        L_0x012d:
            if (r5 == 0) goto L_0x0132
            r5.close()
        L_0x0132:
            if (r2 == 0) goto L_0x0137
            r2.close()
        L_0x0137:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.FileDesc.<init>(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0193, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0195, code lost:
        r3 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0193 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:10:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r12, java.lang.String r13) throws java.io.IOException {
        /*
            r11 = this;
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            r1 = 3
        L_0x0009:
            int r2 = r1 + -1
            r3 = 1
            r4 = 0
            if (r1 <= 0) goto L_0x020d
            com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay r1 = new com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay
            r1.<init>()
            r5 = 0
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ Exception -> 0x019b }
            r6[r4] = r13     // Catch:{ Exception -> 0x019b }
            boolean r7 = android.text.TextUtils.isEmpty(r13)     // Catch:{ Exception -> 0x019b }
            if (r7 == 0) goto L_0x0022
            java.lang.String r7 = ""
            goto L_0x002a
        L_0x0022:
            android.net.Uri r7 = android.net.Uri.parse(r13)     // Catch:{ Exception -> 0x019b }
            java.lang.String r7 = r7.getHost()     // Catch:{ Exception -> 0x019b }
        L_0x002a:
            java.lang.String r8 = "HEAD"
            java.net.HttpURLConnection r8 = com.ximalaya.ting.android.player.PlayerUtil.a(r6, r5, r3, r4, r8)     // Catch:{ Exception -> 0x019b }
            r5 = r6[r4]     // Catch:{ Exception -> 0x0195, all -> 0x0193 }
            if (r8 != 0) goto L_0x0064
            if (r8 == 0) goto L_0x0039
            r8.disconnect()
        L_0x0039:
            java.lang.String r13 = "cdn_head_info"
            java.lang.String r3 = r1.toString()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r13, (java.lang.String) r3)
            boolean r13 = r11.f2271a
            if (r13 != 0) goto L_0x0061
            java.lang.String r13 = r1.m()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0061
            java.lang.String r13 = r1.l()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x0061
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r13 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r1, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r13)
        L_0x0061:
            r1 = r2
            r13 = r5
            goto L_0x0009
        L_0x0064:
            int r13 = r8.getResponseCode()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r11.b = r13     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = "play_head_fail"
            r1.f(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.net.URL r13 = r8.getURL()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.g(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.h(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = "via"
            java.lang.String r13 = r8.getHeaderField(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.m(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r13.<init>()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            int r6 = r11.b     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r13.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r6 = ""
            r13.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.l(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.n(r7)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.o(r0)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            int r13 = r11.b     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r6 = 400(0x190, float:5.6E-43)
            if (r13 >= r6) goto L_0x0152
            r11.f2271a = r3     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            int r13 = r8.getContentLength()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            long r6 = (long) r13     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r11.h = r6     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            long r6 = r11.h     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r1.a((long) r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = "dl_mp3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r6.<init>()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r7 = "conn.getContentLength():"
            r6.append(r7)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            long r9 = r11.h     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r6.append(r9)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r13, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            long r6 = r11.h     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r9 = 0
            int r13 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r13 > 0) goto L_0x00e6
            java.lang.String r13 = "cdn_unknown_exception"
            r1.k(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r13 = "head request comFileLen<=0"
            r1.j(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r11.f2271a = r4     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
        L_0x00e6:
            java.lang.String r13 = "ETag"
            java.lang.String r13 = r8.getHeaderField(r13)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r11.k = r13     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            boolean r13 = r11.f2271a     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            if (r13 == 0) goto L_0x0122
            if (r8 == 0) goto L_0x00f7
            r8.disconnect()
        L_0x00f7:
            java.lang.String r13 = "cdn_head_info"
            java.lang.String r0 = r1.toString()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r13, (java.lang.String) r0)
            boolean r13 = r11.f2271a
            if (r13 != 0) goto L_0x011f
            java.lang.String r13 = r1.m()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x011f
            java.lang.String r13 = r1.l()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x011f
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r13 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r1, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r13)
        L_0x011f:
            r13 = r5
            goto L_0x020d
        L_0x0122:
            if (r8 == 0) goto L_0x0127
            r8.disconnect()
        L_0x0127:
            java.lang.String r13 = "cdn_head_info"
            java.lang.String r3 = r1.toString()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r13, (java.lang.String) r3)
            boolean r13 = r11.f2271a
            if (r13 != 0) goto L_0x014f
            java.lang.String r13 = r1.m()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x014f
            java.lang.String r13 = r1.l()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x014f
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r13 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r1, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r13)
        L_0x014f:
            r13 = r5
            goto L_0x01dc
        L_0x0152:
            java.lang.String r13 = "dl_mp3"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r3.<init>()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r6 = "Error response code for get head for url: "
            r3.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r3.append(r5)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r6 = ",status code is: "
            r3.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            int r6 = r11.b     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r3.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r6 = " in handler thread"
            r3.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            com.ximalaya.ting.android.player.Logger.d(r13, r3)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.io.IOException r13 = new java.io.IOException     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r3.<init>()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r6 = "http response status code is: "
            r3.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            int r6 = r11.b     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r3.append(r6)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            r13.<init>(r3)     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
            throw r13     // Catch:{ Exception -> 0x0190, all -> 0x0193 }
        L_0x0190:
            r3 = move-exception
            r13 = r5
            goto L_0x0196
        L_0x0193:
            r12 = move-exception
            goto L_0x01df
        L_0x0195:
            r3 = move-exception
        L_0x0196:
            r5 = r8
            goto L_0x019c
        L_0x0198:
            r12 = move-exception
            r8 = r5
            goto L_0x01df
        L_0x019b:
            r3 = move-exception
        L_0x019c:
            r11.f2271a = r4     // Catch:{ all -> 0x0198 }
            java.lang.String r4 = "cdn_io_exception"
            r1.k(r4)     // Catch:{ all -> 0x0198 }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0198 }
            r1.j(r3)     // Catch:{ all -> 0x0198 }
            java.lang.String r3 = "failed"
            r1.c(r3)     // Catch:{ all -> 0x0198 }
            if (r5 == 0) goto L_0x01b4
            r5.disconnect()
        L_0x01b4:
            java.lang.String r3 = "cdn_head_info"
            java.lang.String r4 = r1.toString()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r3, (java.lang.String) r4)
            boolean r3 = r11.f2271a
            if (r3 != 0) goto L_0x01dc
            java.lang.String r3 = r1.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x01dc
            java.lang.String r3 = r1.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x01dc
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r1, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r3)
        L_0x01dc:
            r1 = r2
            goto L_0x0009
        L_0x01df:
            if (r8 == 0) goto L_0x01e4
            r8.disconnect()
        L_0x01e4:
            java.lang.String r13 = "cdn_head_info"
            java.lang.String r0 = r1.toString()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r13, (java.lang.String) r0)
            boolean r13 = r11.f2271a
            if (r13 != 0) goto L_0x020c
            java.lang.String r13 = r1.m()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x020c
            java.lang.String r13 = r1.l()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x020c
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r13 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r1, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r13)
        L_0x020c:
            throw r12
        L_0x020d:
            boolean r0 = r11.f2271a
            if (r0 != 0) goto L_0x023e
            java.lang.String r12 = "dl_mp3"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r0 = "valid0:"
            r13.append(r0)
            boolean r0 = r11.f2271a
            r13.append(r0)
            java.lang.String r0 = "comFileLen:"
            r13.append(r0)
            long r0 = r11.h
            r13.append(r0)
            java.lang.String r0 = "statusCode:"
            r13.append(r0)
            int r0 = r11.b
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r12, (java.lang.Object) r13)
            return
        L_0x023e:
            java.lang.String r0 = "dl_mp3"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "valid1:"
            r1.append(r2)
            boolean r2 = r11.f2271a
            r1.append(r2)
            java.lang.String r2 = "comFileLen:"
            r1.append(r2)
            long r5 = r11.h
            r1.append(r5)
            java.lang.String r2 = "statusCode:"
            r1.append(r2)
            int r2 = r11.b
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            long r0 = r11.h
            float r0 = (float) r0
            r1 = 1199570944(0x47800000, float:65536.0)
            float r0 = r0 / r1
            double r0 = (double) r0
            double r0 = java.lang.Math.ceil(r0)
            int r0 = (int) r0
            r11.i = r0
            java.lang.String r0 = "dl_mp3"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "calc001==comFileLen==:"
            r1.append(r2)
            long r5 = r11.h
            r1.append(r5)
            java.lang.String r2 = ",comChunkNum:"
            r1.append(r2)
            int r2 = r11.i
            r1.append(r2)
            java.lang.String r2 = "statusCode:"
            r1.append(r2)
            int r2 = r11.b
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            r11.j = r4
            java.util.BitSet r0 = new java.util.BitSet
            int r1 = r11.i
            r0.<init>(r1)
            r11.c = r0
            int r0 = r11.i
            if (r0 <= 0) goto L_0x02de
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r11.i
            r0.<init>(r1)
            r11.d = r0
        L_0x02bc:
            int r0 = r11.i
            if (r4 >= r0) goto L_0x02cd
            java.util.ArrayList<java.lang.Integer> r0 = r11.d
            r1 = -1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.add(r4, r1)
            int r4 = r4 + 1
            goto L_0x02bc
        L_0x02cd:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r11.e = r0
            java.lang.String r13 = com.ximalaya.ting.android.player.MD5.b(r13)
            r11.b(r12, r13)
            r11.f2271a = r3
            goto L_0x02e4
        L_0x02de:
            r11.f2271a = r4
            r12 = 408(0x198, float:5.72E-43)
            r11.b = r12
        L_0x02e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.FileDesc.a(java.lang.String, java.lang.String):void");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FileDesc)) {
            return false;
        }
        FileDesc fileDesc = (FileDesc) obj;
        String e2 = e();
        if (e2 == null) {
            return false;
        }
        return e2.equals(fileDesc.e());
    }

    public int a() {
        return this.i;
    }

    public int b() {
        return (int) this.h;
    }

    public int c() {
        return this.b;
    }

    public String d() {
        return this.k;
    }

    public String e() {
        return this.g;
    }

    public int hashCode() {
        if (this.g == null) {
            return 0;
        }
        return this.g.hashCode();
    }

    private void a(ArrayList<Integer> arrayList, int i2) {
        this.c = new BitSet(i2);
        this.d = new ArrayList<>(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            this.d.add(i3, -1);
        }
        this.e = arrayList;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            this.c.set(arrayList.get(i4).intValue());
            this.d.set(arrayList.get(i4).intValue(), Integer.valueOf(i4));
        }
    }

    public synchronized boolean a(int i2) {
        boolean z;
        z = false;
        if (this.c != null && i2 >= 0 && i2 < this.c.length()) {
            z = this.c.get(i2);
        }
        return z;
    }

    public boolean b(int i2) {
        return this.c != null && i2 >= 0 && i2 < this.c.length();
    }

    public boolean f() {
        return this.f2271a && this.i > 0;
    }

    public synchronized void b(String str, String str2) {
        String str3;
        Logger.a(f, (Object) "saveDescHeadToDisk(" + str + ", " + str2 + ", " + this.h + Operators.BRACKET_END_STR);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str + "/" + str2 + ".index", false);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(this.g);
            dataOutputStream.writeLong(this.h);
            if (this.k == null) {
                str3 = "";
            } else {
                str3 = this.k;
            }
            dataOutputStream.writeUTF(str3);
            dataOutputStream.writeInt(0);
            dataOutputStream.flush();
            dataOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e2) {
            Logger.a(f, (Object) "exception: saveDescHeadToDisk" + e2.getMessage());
        }
        return;
    }

    public synchronized boolean c(String str, String str2) {
        String str3;
        Logger.a(f, (Object) "======================saveDescToDisk(" + str + ", " + str2 + Operators.BRACKET_END_STR);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str + "/" + str2 + ".index", false);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(this.g);
            dataOutputStream.writeLong(this.h);
            if (this.k == null) {
                str3 = "\"\"";
            } else {
                str3 = "\"" + this.k + "\"";
            }
            dataOutputStream.writeUTF(str3);
            dataOutputStream.writeInt(this.j);
            Iterator<Integer> it = this.e.iterator();
            while (it.hasNext()) {
                dataOutputStream.writeInt(it.next().intValue());
            }
            dataOutputStream.flush();
            dataOutputStream.close();
            fileOutputStream.close();
        } catch (Exception unused) {
            return false;
        }
        return true;
    }

    public synchronized void c(int i2) {
        if (this.c != null) {
            int size = this.e.size();
            this.c.set(i2);
            this.d.set(i2, Integer.valueOf(size));
            this.e.add(Integer.valueOf(i2));
            this.j++;
            c(this.l, MD5.b(this.g));
        }
    }

    public synchronized int g() {
        if (this.e == null) {
            return 0;
        }
        return this.e.size();
    }
}
