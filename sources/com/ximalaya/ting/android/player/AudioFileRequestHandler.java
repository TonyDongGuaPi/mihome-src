package com.ximalaya.ting.android.player;

import com.ximalaya.ting.android.player.model.JNIDataModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioFileRequestHandler {

    /* renamed from: a  reason: collision with root package name */
    public static int f2267a = -2;
    public static int b = -1;
    private ReadThread c;
    private XMediaplayerJNI d;
    private LinkedBlockingQueue<BufferItem> e;
    private String f = null;
    private volatile boolean g = false;
    private int h = 1000;
    private volatile int i = 0;

    public AudioFileRequestHandler(XMediaplayerJNI xMediaplayerJNI) {
        this.d = xMediaplayerJNI;
        this.i = 0;
    }

    public AudioFileRequestHandler(XMediaplayerJNI xMediaplayerJNI, String str) {
        this.d = xMediaplayerJNI;
        this.i = 0;
        this.f = str;
    }

    public void a(String str) {
        this.f = str;
        if (this.c != null && !this.c.e()) {
            this.c.a(this.f);
        }
    }

    public int a() {
        if (PlayerUtil.a(this.d.getPlayUrl())) {
            return 100;
        }
        if (this.c == null) {
            return 0;
        }
        return this.c.c();
    }

    private boolean a(int i2, JNIDataModel jNIDataModel) {
        Logger.a(XMediaplayerJNI.Tag, (Object) "dataStreamInputFuncCallBackT resetLoadDataPosition");
        this.e = new LinkedBlockingQueue<>(3);
        if (this.c != null && !this.c.e()) {
            Logger.a(XMediaplayerJNI.Tag, (Object) "dataStreamInputFuncCallBackT resetLoadDataPosition1");
            long b2 = (long) this.c.a().d().b();
            jNIDataModel.fileSize = b2;
            this.d.setCurFileSize(b2);
            if (this.c.a(i2 / 65536, this.e)) {
                return true;
            }
        }
        Logger.a(XMediaplayerJNI.Tag, (Object) "dataStreamInputFuncCallBackT resetLoadDataPosition0");
        try {
            AudioFile a2 = AudioFile.a(XMediaPlayerConstants.o, this.d.getPlayUrl());
            if (a2 == null || !a2.d().f()) {
                Logger.a(XMediaplayerJNI.Tag, (Object) "mFile.getFileInfo().inValid()");
                return false;
            }
            if (this.c != null) {
                this.c.d();
                String str = XMediaplayerJNI.Tag;
                Logger.a(str, (Object) "dataStreamInputFuncCallBackT mReadThread.close" + this.c.toString());
            }
            if (!a2.d().f()) {
                return false;
            }
            long b3 = (long) a2.d().b();
            jNIDataModel.fileSize = b3;
            this.d.setCurFileSize(b3);
            int i3 = i2 / 65536;
            if (this.f == null) {
                this.c = new ReadThread(a2, i3, this.e, this.d);
            } else {
                this.c = new ReadThread(a2, i3, this.e, this.d, this.f);
            }
            Logger.a(XMediaplayerJNI.Tag, (Object) "ReadThread.start()");
            this.c.start();
            return true;
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return false;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0091=Splitter:B:31:0x0091, B:38:0x00a3=Splitter:B:38:0x00a3} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int a(java.lang.String r8, int r9, byte[] r10) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ all -> 0x00ba }
            java.lang.String r1 = "r"
            r0.<init>(r8, r1)     // Catch:{ all -> 0x00ba }
            long r1 = r0.length()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = ".xm"
            boolean r3 = r8.endsWith(r3)     // Catch:{ all -> 0x00ba }
            r4 = 0
            if (r3 == 0) goto L_0x0039
            r8 = 12
            long r5 = (long) r8     // Catch:{ all -> 0x00ba }
            r0.seek(r5)     // Catch:{ all -> 0x00ba }
            int r8 = r0.readInt()     // Catch:{ all -> 0x00ba }
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r7.d     // Catch:{ all -> 0x00ba }
            long r5 = (long) r8     // Catch:{ all -> 0x00ba }
            long r1 = r1 - r5
            r3.setCurFileSize(r1)     // Catch:{ all -> 0x00ba }
            int r9 = r9 + r8
            long r8 = (long) r9     // Catch:{ all -> 0x00ba }
            r0.seek(r8)     // Catch:{ all -> 0x00ba }
        L_0x002b:
            int r8 = r10.length     // Catch:{ all -> 0x00ba }
            int r8 = r8 - r4
            int r8 = r0.read(r10, r4, r8)     // Catch:{ all -> 0x00ba }
            int r4 = r4 + r8
            if (r8 > 0) goto L_0x002b
            r0.close()     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r4
        L_0x0039:
            java.lang.String r3 = ".x2m"
            boolean r8 = r8.endsWith(r3)     // Catch:{ all -> 0x00ba }
            if (r8 == 0) goto L_0x00a3
            int r8 = com.ximalaya.ting.android.player.MediadataCrytoUtil.f2276a     // Catch:{ all -> 0x00ba }
            if (r9 >= r8) goto L_0x00a3
            int r8 = com.ximalaya.ting.android.player.MediadataCrytoUtil.f2276a     // Catch:{ all -> 0x00ba }
            long r5 = (long) r8     // Catch:{ all -> 0x00ba }
            int r8 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r8 < 0) goto L_0x00a3
            com.ximalaya.ting.android.player.XMediaplayerJNI r8 = r7.d     // Catch:{ all -> 0x00ba }
            r8.setCurFileSize(r1)     // Catch:{ all -> 0x00ba }
            r1 = 0
            r0.seek(r1)     // Catch:{ all -> 0x00ba }
            int r8 = com.ximalaya.ting.android.player.MediadataCrytoUtil.f2276a     // Catch:{ all -> 0x00ba }
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x00ba }
            r1 = 0
        L_0x005b:
            int r2 = r8.length     // Catch:{ all -> 0x00ba }
            int r2 = r2 - r1
            int r2 = r0.read(r8, r1, r2)     // Catch:{ all -> 0x00ba }
            int r1 = r1 + r2
            if (r2 > 0) goto L_0x005b
            com.ximalaya.ting.android.player.MediadataCrytoUtil r1 = com.ximalaya.ting.android.player.MediadataCrytoUtil.a()     // Catch:{ all -> 0x00ba }
            byte[] r8 = r1.b(r8)     // Catch:{ all -> 0x00ba }
            int r1 = com.ximalaya.ting.android.player.MediadataCrytoUtil.f2276a     // Catch:{ all -> 0x00ba }
            int r1 = r1 - r9
            int r2 = r10.length     // Catch:{ all -> 0x00ba }
            if (r2 <= r1) goto L_0x0091
        L_0x0072:
            if (r4 >= r1) goto L_0x007d
            int r2 = r9 + r4
            byte r2 = r8[r2]     // Catch:{ all -> 0x00ba }
            r10[r4] = r2     // Catch:{ all -> 0x00ba }
            int r4 = r4 + 1
            goto L_0x0072
        L_0x007d:
            int r8 = com.ximalaya.ting.android.player.MediadataCrytoUtil.f2276a     // Catch:{ all -> 0x00ba }
            long r8 = (long) r8     // Catch:{ all -> 0x00ba }
            r0.seek(r8)     // Catch:{ all -> 0x00ba }
        L_0x0083:
            int r8 = r10.length     // Catch:{ all -> 0x00ba }
            int r8 = r8 - r1
            int r8 = r0.read(r10, r1, r8)     // Catch:{ all -> 0x00ba }
            int r1 = r1 + r8
            if (r8 > 0) goto L_0x0083
            r0.close()     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r1
        L_0x0091:
            int r1 = r10.length     // Catch:{ all -> 0x00ba }
            if (r4 >= r1) goto L_0x009d
            int r1 = r9 + r4
            byte r1 = r8[r1]     // Catch:{ all -> 0x00ba }
            r10[r4] = r1     // Catch:{ all -> 0x00ba }
            int r4 = r4 + 1
            goto L_0x0091
        L_0x009d:
            r0.close()     // Catch:{ all -> 0x00ba }
            int r8 = r10.length     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00a3:
            com.ximalaya.ting.android.player.XMediaplayerJNI r8 = r7.d     // Catch:{ all -> 0x00ba }
            r8.setCurFileSize(r1)     // Catch:{ all -> 0x00ba }
            long r8 = (long) r9     // Catch:{ all -> 0x00ba }
            r0.seek(r8)     // Catch:{ all -> 0x00ba }
        L_0x00ac:
            int r8 = r10.length     // Catch:{ all -> 0x00ba }
            int r8 = r8 - r4
            int r8 = r0.read(r10, r4, r8)     // Catch:{ all -> 0x00ba }
            int r4 = r4 + r8
            if (r8 > 0) goto L_0x00ac
            r0.close()     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r4
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioFileRequestHandler.a(java.lang.String, int, byte[]):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x0378  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0380  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(com.ximalaya.ting.android.player.model.JNIDataModel r17, boolean r18, int r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r19
            boolean r4 = r16.b()
            if (r4 == 0) goto L_0x000f
            int r0 = r1.i
            return r0
        L_0x000f:
            java.lang.String r4 = r2.filePath
            boolean r4 = com.ximalaya.ting.android.player.PlayerUtil.a((java.lang.String) r4)
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r4 == 0) goto L_0x0042
            int r0 = r2.bufSize
            if (r0 <= r5) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            int r5 = r2.bufSize
        L_0x0020:
            byte[] r0 = new byte[r5]
            r2.buf = r0
            java.lang.String r0 = r2.filePath     // Catch:{ IOException -> 0x0040 }
            byte[] r4 = r2.buf     // Catch:{ IOException -> 0x0040 }
            r1.a((java.lang.String) r0, (int) r3, (byte[]) r4)     // Catch:{ IOException -> 0x0040 }
            com.ximalaya.ting.android.player.XMediaplayerJNI r0 = r1.d
            long r3 = r0.getCurFileSize()
            r2.fileSize = r3
            com.ximalaya.ting.android.player.XMediaplayerJNI r0 = r1.d
            int r3 = r16.a()
            r0.onBufferingUpdateInner(r3)
            byte[] r0 = r2.buf
            int r0 = r0.length
            return r0
        L_0x0040:
            r0 = -1
            return r0
        L_0x0042:
            if (r18 != 0) goto L_0x004a
            boolean r4 = r16.d()
            if (r4 == 0) goto L_0x0058
        L_0x004a:
            boolean r4 = r1.a(r3, r2)
            if (r4 != 0) goto L_0x0058
            int r0 = r1.i
            r1.a((int) r0)
            int r0 = r1.i
            return r0
        L_0x0058:
            com.ximalaya.ting.android.player.XMediaplayerJNI r4 = r1.d
            long r6 = r4.getCurFileSize()
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "dataStreamInputFuncCallBackT 0 tCurFileSize:"
            r8.append(r9)
            r8.append(r6)
            java.lang.String r8 = r8.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r8)
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 > 0) goto L_0x0082
            int r0 = r1.i
            r1.a((int) r0)
            int r0 = r1.i
            return r0
        L_0x0082:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.String r10 = "dataStreamInputFuncCallBackT 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r10)
            r4 = 0
            if (r18 != 0) goto L_0x00a8
            com.ximalaya.ting.android.player.XMediaplayerJNI r10 = r1.d     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r10 = r10.tmepBuf     // Catch:{ InterruptedException -> 0x00a5 }
            if (r10 == 0) goto L_0x00a8
            com.ximalaya.ting.android.player.XMediaplayerJNI r0 = r1.d     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r0 = r0.tmepBuf     // Catch:{ InterruptedException -> 0x00a5 }
            r2.buf = r0     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.XMediaplayerJNI r0 = r1.d     // Catch:{ InterruptedException -> 0x00a5 }
            r0.tmepBuf = r4     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = "dataStreamInputFuncCallBackT read temp buf"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            goto L_0x034f
        L_0x00a5:
            r0 = move-exception
            goto L_0x035a
        L_0x00a8:
            java.lang.String r10 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r11 = "dataStreamInputFuncCallBackT 2"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r10, (java.lang.Object) r11)     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.XMediaplayerJNI r10 = r1.d     // Catch:{ InterruptedException -> 0x00a5 }
            r10.tmepBuf = r4     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r10 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r11.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = "dataStreamInputFuncCallBackT 2 buffItemQueue.size():"
            r11.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r12 = r1.e     // Catch:{ InterruptedException -> 0x00a5 }
            int r12 = r12.size()     // Catch:{ InterruptedException -> 0x00a5 }
            r11.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r11 = r11.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r10, (java.lang.Object) r11)     // Catch:{ InterruptedException -> 0x00a5 }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r10 = r1.e     // Catch:{ InterruptedException -> 0x00a5 }
            int r10 = r10.size()     // Catch:{ InterruptedException -> 0x00a5 }
            r11 = 0
            if (r10 > 0) goto L_0x00e2
            com.ximalaya.ting.android.player.XMediaplayerJNI r10 = r1.d     // Catch:{ InterruptedException -> 0x00a5 }
            android.content.Context r10 = r10.mContext     // Catch:{ InterruptedException -> 0x00a5 }
            boolean r10 = com.ximalaya.ting.android.player.PlayerUtil.a((android.content.Context) r10)     // Catch:{ InterruptedException -> 0x00a5 }
            if (r10 == 0) goto L_0x0148
        L_0x00e2:
            boolean r4 = r16.b()     // Catch:{ InterruptedException -> 0x00a5 }
            if (r4 == 0) goto L_0x00eb
            int r0 = r1.i     // Catch:{ InterruptedException -> 0x00a5 }
            return r0
        L_0x00eb:
            r4 = 1
            r1.g = r4     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r10.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = "dataStreamInputFuncCallBackT 2 buffItemQueue.size():"
            r10.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r12 = r1.e     // Catch:{ InterruptedException -> 0x00a5 }
            int r12 = r12.size()     // Catch:{ InterruptedException -> 0x00a5 }
            r10.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = " isPollData0:"
            r10.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            boolean r12 = r1.g     // Catch:{ InterruptedException -> 0x00a5 }
            r10.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r10 = r10.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r4 = r1.e     // Catch:{ InterruptedException -> 0x00a5 }
            r12 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.Object r4 = r4.poll(r12, r10)     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.BufferItem r4 = (com.ximalaya.ting.android.player.BufferItem) r4     // Catch:{ InterruptedException -> 0x00a5 }
            r1.g = r11     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r10 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r12.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r13 = "dataStreamInputFuncCallBackT 2 buffItemQueue.size():"
            r12.append(r13)     // Catch:{ InterruptedException -> 0x00a5 }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r13 = r1.e     // Catch:{ InterruptedException -> 0x00a5 }
            int r13 = r13.size()     // Catch:{ InterruptedException -> 0x00a5 }
            r12.append(r13)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r13 = " isPollData1:"
            r12.append(r13)     // Catch:{ InterruptedException -> 0x00a5 }
            boolean r13 = r1.g     // Catch:{ InterruptedException -> 0x00a5 }
            r12.append(r13)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = r12.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r10, (java.lang.Object) r12)     // Catch:{ InterruptedException -> 0x00a5 }
        L_0x0148:
            java.lang.String r10 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = "dataStreamInputFuncCallBackT 3"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r10, (java.lang.Object) r12)     // Catch:{ InterruptedException -> 0x00a5 }
            if (r4 != 0) goto L_0x0160
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = "dataStreamInputFuncCallBackT timeout item null"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = r1.i     // Catch:{ InterruptedException -> 0x00a5 }
            r1.a((int) r0)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = r1.i     // Catch:{ InterruptedException -> 0x00a5 }
            return r0
        L_0x0160:
            boolean r10 = r4.b     // Catch:{ InterruptedException -> 0x00a5 }
            if (r10 == 0) goto L_0x0197
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r3.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = "dataStreamInputFuncCallBackT timeout item null index:"
            r3.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            int r5 = r4.e()     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = " bItem.errorCode:"
            r3.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            int r4 = r4.c     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = r1.i     // Catch:{ InterruptedException -> 0x00a5 }
            r1.a((int) r0)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = "dataStreamInputFuncCallBackT timeout item null return -1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = r1.i     // Catch:{ InterruptedException -> 0x00a5 }
            return r0
        L_0x0197:
            int r10 = r4.e()     // Catch:{ InterruptedException -> 0x00a5 }
            r12 = 65536(0x10000, double:3.2379E-319)
            long r14 = r6 / r12
            int r14 = (int) r14     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r15 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r11.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = "dataStreamInputFuncCallBackT 4 index:"
            r11.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            r11.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = "total index:"
            r11.append(r12)     // Catch:{ InterruptedException -> 0x00a5 }
            r11.append(r14)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r11 = r11.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r15, (java.lang.Object) r11)     // Catch:{ InterruptedException -> 0x00a5 }
            if (r10 < 0) goto L_0x0352
            boolean r11 = r4.b()     // Catch:{ InterruptedException -> 0x00a5 }
            if (r11 != 0) goto L_0x0352
            if (r10 <= r14) goto L_0x01cb
            goto L_0x0352
        L_0x01cb:
            java.lang.String r11 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r12.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r13 = "dataStreamInputFuncCallBackT seekParaTimeStampMs index:"
            r12.append(r13)     // Catch:{ InterruptedException -> 0x00a5 }
            r12.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r13 = "total index:"
            r12.append(r13)     // Catch:{ InterruptedException -> 0x00a5 }
            r12.append(r14)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r12 = r12.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r11, (java.lang.Object) r12)     // Catch:{ InterruptedException -> 0x00a5 }
            if (r18 == 0) goto L_0x02d9
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r11 = "dataStreamInputFuncCallBackT seekParaTimeStampMs 1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r11)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = r3 % r5
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r5.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r11 = "dataStreamInputFuncCallBackT offset:"
            r5.append(r11)     // Catch:{ InterruptedException -> 0x00a5 }
            r5.append(r0)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = r5.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r5)     // Catch:{ InterruptedException -> 0x00a5 }
            java.nio.ByteBuffer r3 = r4.a()     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r3 = r3.array()     // Catch:{ InterruptedException -> 0x00a5 }
            int r3 = r3.length     // Catch:{ InterruptedException -> 0x00a5 }
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x0253
            if (r10 != r14) goto L_0x0253
            r8 = 65536(0x10000, double:3.2379E-319)
            long r8 = r6 % r8
            int r3 = (int) r8     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r8.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r9 = "dataStreamInputFuncCallBackT lastChunkLength:"
            r8.append(r9)     // Catch:{ InterruptedException -> 0x00a5 }
            r8.append(r3)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r9 = " index:"
            r8.append(r9)     // Catch:{ InterruptedException -> 0x00a5 }
            r8.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r8 = r8.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r8)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r8.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r9 = "dataStreamInputFuncCallBackT seekParaTimeStampMs 2 index:"
            r8.append(r9)     // Catch:{ InterruptedException -> 0x00a5 }
            r8.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r8 = r8.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r8)     // Catch:{ InterruptedException -> 0x00a5 }
        L_0x0253:
            java.lang.String r5 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r8.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r9 = "dataStreamInputFuncCallBackT offset:"
            r8.append(r9)     // Catch:{ InterruptedException -> 0x00a5 }
            r8.append(r0)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r9 = "length:"
            r8.append(r9)     // Catch:{ InterruptedException -> 0x00a5 }
            r8.append(r3)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r9 = " index:"
            r8.append(r9)     // Catch:{ InterruptedException -> 0x00a5 }
            r8.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r8 = r8.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r8)     // Catch:{ InterruptedException -> 0x00a5 }
            java.nio.ByteBuffer r4 = r4.a()     // Catch:{ InterruptedException -> 0x00a5 }
            java.nio.Buffer r0 = r4.position(r0)     // Catch:{ InterruptedException -> 0x00a5 }
            r0.limit(r3)     // Catch:{ InterruptedException -> 0x00a5 }
            java.nio.ByteBuffer r0 = r4.slice()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r4.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = "dataStreamInputFuncCallBackT slice remaining:"
            r4.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            int r5 = r0.remaining()     // Catch:{ InterruptedException -> 0x00a5 }
            r4.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = " index:"
            r4.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            r4.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r4 = r4.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ InterruptedException -> 0x00a5 }
            int r3 = r0.remaining()     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r3 = new byte[r3]     // Catch:{ InterruptedException -> 0x00a5 }
            r2.buf = r3     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r3 = r2.buf     // Catch:{ InterruptedException -> 0x00a5 }
            r0.get(r3)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r3.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r4 = "dataStreamInputFuncCallBackT slice buf size:"
            r3.append(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r4 = r2.buf     // Catch:{ InterruptedException -> 0x00a5 }
            int r4 = r4.length     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r4 = " index:"
            r3.append(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            goto L_0x034f
        L_0x02d9:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r3.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r5 = "dataStreamInputFuncCallBackT seekParaTimeStampMs 3 index:"
            r3.append(r5)     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x032f
            if (r10 != r14) goto L_0x032f
            r8 = 65536(0x10000, double:3.2379E-319)
            long r8 = r6 % r8
            int r0 = (int) r8     // Catch:{ InterruptedException -> 0x00a5 }
            java.nio.ByteBuffer r3 = r4.a()     // Catch:{ InterruptedException -> 0x00a5 }
            r4 = 0
            java.nio.Buffer r4 = r3.position(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            r4.limit(r0)     // Catch:{ InterruptedException -> 0x00a5 }
            java.nio.ByteBuffer r0 = r3.slice()     // Catch:{ InterruptedException -> 0x00a5 }
            int r3 = r0.remaining()     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r3 = new byte[r3]     // Catch:{ InterruptedException -> 0x00a5 }
            r2.buf = r3     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r3 = r2.buf     // Catch:{ InterruptedException -> 0x00a5 }
            r0.get(r3)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r3.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r4 = "dataStreamInputFuncCallBackT seekParaTimeStampMs 4 index:"
            r3.append(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
            goto L_0x034f
        L_0x032f:
            java.nio.ByteBuffer r0 = r4.a()     // Catch:{ InterruptedException -> 0x00a5 }
            byte[] r0 = r0.array()     // Catch:{ InterruptedException -> 0x00a5 }
            r2.buf = r0     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00a5 }
            r3.<init>()     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r4 = "dataStreamInputFuncCallBackT seekParaTimeStampMs 5 index:"
            r3.append(r4)     // Catch:{ InterruptedException -> 0x00a5 }
            r3.append(r10)     // Catch:{ InterruptedException -> 0x00a5 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x00a5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r3)     // Catch:{ InterruptedException -> 0x00a5 }
        L_0x034f:
            r2.fileSize = r6     // Catch:{ InterruptedException -> 0x00a5 }
            goto L_0x0374
        L_0x0352:
            int r0 = b     // Catch:{ InterruptedException -> 0x00a5 }
            r1.a((int) r0)     // Catch:{ InterruptedException -> 0x00a5 }
            int r0 = b     // Catch:{ InterruptedException -> 0x00a5 }
            return r0
        L_0x035a:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "dataStreamInputFuncCallBackT 19"
            r4.append(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r0)
        L_0x0374:
            byte[] r0 = r2.buf
            if (r0 != 0) goto L_0x0380
            int r0 = b
            r1.a((int) r0)
            int r0 = b
            return r0
        L_0x0380:
            byte[] r0 = r2.buf
            int r0 = r0.length
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioFileRequestHandler.a(com.ximalaya.ting.android.player.model.JNIDataModel, boolean, int):int");
    }

    private boolean d() {
        return this.e == null || (this.c != null && this.c.e() && this.e.size() == 0);
    }

    public boolean b() {
        return this.i < 0;
    }

    public void c() {
        this.i = 0;
    }

    public void a(int i2) {
        if (i2 == 0) {
            i2 = b;
        }
        this.i = i2;
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "AudioFileRequestHandler release mReadThread.close" + toString());
        if (this.c != null) {
            this.c.d();
            String str2 = XMediaplayerJNI.Tag;
            Logger.a(str2, (Object) "AudioFileRequestHandler release mReadThread.close" + this.c.toString());
        }
        if (this.e != null) {
            String str3 = XMediaplayerJNI.Tag;
            Logger.a(str3, (Object) "AudioFileRequestHandler release buffItemQueue.size():" + this.e.size() + " isPollData:" + this.g);
            if (this.e.size() != 0 || !this.g) {
                this.e.clear();
                Logger.a(XMediaplayerJNI.Tag, (Object) "AudioFileRequestHandler release buffItemQueue.clear");
                return;
            }
            BufferItem bufferItem = new BufferItem();
            bufferItem.b = true;
            bufferItem.c = this.h;
            this.e.add(bufferItem);
            String str4 = XMediaplayerJNI.Tag;
            Logger.a(str4, (Object) "AudioFileRequestHandler release normalReleaseCode:" + this.h);
        }
    }
}
