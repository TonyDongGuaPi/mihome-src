package com.ximalaya.ting.android.player;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;

public class ReadThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2281a = "dl_mp3";
    private AudioFile b;
    private volatile LinkedBlockingQueue<BufferItem> c;
    private volatile int d;
    private volatile int e = 0;
    private volatile boolean f;
    private DownloadThread g;
    private int h;
    private volatile Object i = new Object();
    private XMediaplayerJNI j;
    private AudioFile k;
    private String l = null;
    private volatile boolean m;

    public ReadThread(AudioFile audioFile, int i2, LinkedBlockingQueue<BufferItem> linkedBlockingQueue, XMediaplayerJNI xMediaplayerJNI) {
        super("ReadThreadForPlayer");
        setPriority(5);
        this.b = audioFile;
        this.d = i2;
        this.c = linkedBlockingQueue;
        this.f = false;
        this.m = true;
        this.j = xMediaplayerJNI;
    }

    public void a(String str) {
        this.l = str;
    }

    public ReadThread(AudioFile audioFile, int i2, LinkedBlockingQueue<BufferItem> linkedBlockingQueue, XMediaplayerJNI xMediaplayerJNI, String str) {
        super("ReadThreadForPlayer");
        setPriority(5);
        this.b = audioFile;
        this.d = i2;
        this.c = linkedBlockingQueue;
        this.f = false;
        this.m = true;
        this.j = xMediaplayerJNI;
        this.l = str;
    }

    public AudioFile a() {
        return this.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x009a, code lost:
        if (e() == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x009c, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x009d, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(int r6, java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r7) {
        /*
            r5 = this;
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r0 = r5.c
            if (r0 == 0) goto L_0x0042
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "resetIndex count0-0:"
            r1.append(r2)
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r2 = r5.c
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r0 = r5.c
            r0.clear()
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "resetIndex count0-1:"
            r1.append(r2)
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r2 = r5.c
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            goto L_0x0049
        L_0x0042:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.String r1 = "resetIndex count0-0 buffItemQueue == null"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
        L_0x0049:
            java.lang.Object r0 = r5.i
            monitor-enter(r0)
            boolean r1 = r5.e()     // Catch:{ all -> 0x009e }
            r2 = 0
            if (r1 == 0) goto L_0x0055
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            return r2
        L_0x0055:
            java.lang.String r1 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x009e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r3.<init>()     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "resetIndex count0:"
            r3.append(r4)     // Catch:{ all -> 0x009e }
            int r4 = r7.size()     // Catch:{ all -> 0x009e }
            r3.append(r4)     // Catch:{ all -> 0x009e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x009e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x009e }
            r1 = 1
            r5.m = r1     // Catch:{ all -> 0x009e }
            r5.d = r6     // Catch:{ all -> 0x009e }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r6 = r5.c     // Catch:{ all -> 0x009e }
            r6.clear()     // Catch:{ all -> 0x009e }
            r5.c = r7     // Catch:{ all -> 0x009e }
            java.lang.String r6 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x009e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r3.<init>()     // Catch:{ all -> 0x009e }
            java.lang.String r4 = "resetIndex count1:"
            r3.append(r4)     // Catch:{ all -> 0x009e }
            int r7 = r7.size()     // Catch:{ all -> 0x009e }
            r3.append(r7)     // Catch:{ all -> 0x009e }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x009e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x009e }
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            boolean r6 = r5.e()
            if (r6 == 0) goto L_0x009d
            return r2
        L_0x009d:
            return r1
        L_0x009e:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.ReadThread.a(int, java.util.concurrent.LinkedBlockingQueue):boolean");
    }

    public int b() {
        return this.h;
    }

    public int c() {
        int chargeDataRealLength = this.j.getChargeDataRealLength() / 65536;
        if (chargeDataRealLength == 0) {
            chargeDataRealLength = this.b.d().a();
        }
        int i2 = (int) ((((float) (this.h - 1)) / ((float) chargeDataRealLength)) * 100.0f);
        Logger.a(f2281a, (Object) "getCachePercent percent:" + i2 + " mDownloadIndex:" + this.h + "getComChunkNum:" + this.b.d().a());
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r10.c.put(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00a7, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x017f, code lost:
        r3 = new com.ximalaya.ting.android.player.BufferItem();
        r3.a(java.nio.ByteBuffer.allocate(65536));
        r3.a(r10.e);
        r3.d();
        com.ximalaya.ting.android.player.Logger.a(f2281a, (java.lang.Object) "putLastChunk stopFlag:" + r10.f + " curIndex:" + r10.e + " getComChunkNum" + r1.a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x01c7, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0074, code lost:
        r1 = new com.ximalaya.ting.android.player.BufferItem();
        r1.b = true;
        r1.c = r10.b.d().b;
        com.ximalaya.ting.android.player.Logger.a(f2281a, (java.lang.Object) "resetIndex count5-1 put:" + r10.c.size());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r10 = this;
            java.lang.String r0 = "dl_mp3"
            java.lang.String r1 = "======================ReadThread run() start"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
        L_0x0007:
            r0 = 1
            boolean r1 = r10.f     // Catch:{ Exception -> 0x064d }
            if (r1 != 0) goto L_0x0668
            com.ximalaya.ting.android.player.XMediaplayerJNI r1 = r10.j     // Catch:{ Exception -> 0x064d }
            java.lang.String r1 = r1.getPlayUrl()     // Catch:{ Exception -> 0x064d }
            java.lang.String r1 = com.ximalaya.ting.android.player.MD5.b(r1)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r2 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r2 = r2.d()     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.e()     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = com.ximalaya.ting.android.player.MD5.b(r2)     // Catch:{ Exception -> 0x064d }
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x064d }
            if (r1 == 0) goto L_0x0668
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "======================ReadThread start while("
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r3 = r3.d()     // Catch:{ Exception -> 0x064d }
            int r3 = r3.a()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = ") stopFlag:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            boolean r3 = r10.f     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r1 = r1.d()     // Catch:{ Exception -> 0x064d }
            boolean r2 = r1.f()     // Catch:{ Exception -> 0x064d }
            if (r2 != 0) goto L_0x00ed
            com.ximalaya.ting.android.player.BufferItem r1 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ Exception -> 0x064d }
            r1.<init>()     // Catch:{ Exception -> 0x064d }
            r1.b = r0     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r2 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r2 = r2.d()     // Catch:{ Exception -> 0x064d }
            int r2 = r2.b     // Catch:{ Exception -> 0x064d }
            r1.c = r2     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = "dl_mp3"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r3.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r4 = "resetIndex count5-1 put:"
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r4 = r10.c     // Catch:{ Exception -> 0x064d }
            int r4 = r4.size()     // Catch:{ Exception -> 0x064d }
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x064d }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r2 = r10.c     // Catch:{ InterruptedException -> 0x00a7 }
            r2.put(r1)     // Catch:{ InterruptedException -> 0x00a7 }
            goto L_0x00ab
        L_0x00a7:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ Exception -> 0x064d }
        L_0x00ab:
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "resetIndex count5-2 put:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r3 = r10.c     // Catch:{ Exception -> 0x064d }
            int r3 = r3.size()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "fileDesc not Valid stopFlag:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            boolean r3 = r10.f     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            r10.f = r0     // Catch:{ Exception -> 0x064d }
            goto L_0x0668
        L_0x00ed:
            java.lang.Object r2 = r10.i     // Catch:{ Exception -> 0x064d }
            monitor-enter(r2)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "dl_mp3"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x064a }
            r4.<init>()     // Catch:{ all -> 0x064a }
            java.lang.String r5 = "resetIndex count6:"
            r4.append(r5)     // Catch:{ all -> 0x064a }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r5 = r10.c     // Catch:{ all -> 0x064a }
            int r5 = r5.size()     // Catch:{ all -> 0x064a }
            r4.append(r5)     // Catch:{ all -> 0x064a }
            java.lang.String r5 = " stopFlag:"
            r4.append(r5)     // Catch:{ all -> 0x064a }
            boolean r5 = r10.f     // Catch:{ all -> 0x064a }
            r4.append(r5)     // Catch:{ all -> 0x064a }
            java.lang.String r5 = " curIndex:"
            r4.append(r5)     // Catch:{ all -> 0x064a }
            int r5 = r10.e     // Catch:{ all -> 0x064a }
            r4.append(r5)     // Catch:{ all -> 0x064a }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x064a }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x064a }
            boolean r3 = r10.m     // Catch:{ all -> 0x064a }
            r4 = 0
            if (r3 == 0) goto L_0x012f
            r10.m = r4     // Catch:{ all -> 0x064a }
            int r3 = r10.d     // Catch:{ all -> 0x064a }
            r10.e = r3     // Catch:{ all -> 0x064a }
            int r3 = r10.d     // Catch:{ all -> 0x064a }
            r10.h = r3     // Catch:{ all -> 0x064a }
        L_0x012f:
            java.lang.String r3 = "dl_mp3"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x064a }
            r5.<init>()     // Catch:{ all -> 0x064a }
            java.lang.String r6 = "resetIndex count7:"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r6 = r10.c     // Catch:{ all -> 0x064a }
            int r6 = r6.size()     // Catch:{ all -> 0x064a }
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.lang.String r6 = " stopFlag:"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            boolean r6 = r10.f     // Catch:{ all -> 0x064a }
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.lang.String r6 = " curIndex:"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            int r6 = r10.e     // Catch:{ all -> 0x064a }
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x064a }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r5)     // Catch:{ all -> 0x064a }
            java.lang.String r3 = "dl_mp3"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x064a }
            r5.<init>()     // Catch:{ all -> 0x064a }
            java.lang.String r6 = "before put last item isResetIndex:"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            boolean r6 = r10.m     // Catch:{ all -> 0x064a }
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x064a }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r5)     // Catch:{ all -> 0x064a }
            int r3 = r10.e     // Catch:{ all -> 0x064a }
            int r5 = r1.a()     // Catch:{ all -> 0x064a }
            if (r3 < r5) goto L_0x01d7
            com.ximalaya.ting.android.player.BufferItem r3 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ all -> 0x064a }
            r3.<init>()     // Catch:{ all -> 0x064a }
            r4 = 65536(0x10000, float:9.18355E-41)
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)     // Catch:{ all -> 0x064a }
            r3.a((java.nio.ByteBuffer) r4)     // Catch:{ all -> 0x064a }
            int r4 = r10.e     // Catch:{ all -> 0x064a }
            r3.a((int) r4)     // Catch:{ all -> 0x064a }
            r3.d()     // Catch:{ all -> 0x064a }
            java.lang.String r4 = "dl_mp3"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x064a }
            r5.<init>()     // Catch:{ all -> 0x064a }
            java.lang.String r6 = "putLastChunk stopFlag:"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            boolean r6 = r10.f     // Catch:{ all -> 0x064a }
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.lang.String r6 = " curIndex:"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            int r6 = r10.e     // Catch:{ all -> 0x064a }
            r5.append(r6)     // Catch:{ all -> 0x064a }
            java.lang.String r6 = " getComChunkNum"
            r5.append(r6)     // Catch:{ all -> 0x064a }
            int r1 = r1.a()     // Catch:{ all -> 0x064a }
            r5.append(r1)     // Catch:{ all -> 0x064a }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x064a }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r1)     // Catch:{ all -> 0x064a }
            r10.a((com.ximalaya.ting.android.player.BufferItem) r3)     // Catch:{ InterruptedException -> 0x01c7 }
            goto L_0x01cb
        L_0x01c7:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x064a }
        L_0x01cb:
            r10.f = r0     // Catch:{ all -> 0x064a }
            java.lang.String r1 = "dl_mp3"
            java.lang.String r3 = "put LastChunk and close read thread"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x064a }
            monitor-exit(r2)     // Catch:{ all -> 0x064a }
            goto L_0x0668
        L_0x01d7:
            monitor-exit(r2)     // Catch:{ all -> 0x064a }
            int r2 = r10.h     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            if (r2 >= r3) goto L_0x01e2
            int r2 = r10.e     // Catch:{ Exception -> 0x064d }
            r10.h = r2     // Catch:{ Exception -> 0x064d }
        L_0x01e2:
            int r2 = com.ximalaya.ting.android.player.XMediaPlayerConstants.l     // Catch:{ Exception -> 0x064d }
            r3 = 3
            int r2 = r2 - r3
        L_0x01e6:
            int r5 = r10.h     // Catch:{ Exception -> 0x064d }
            int r6 = r10.e     // Catch:{ Exception -> 0x064d }
            int r5 = r5 - r6
            r6 = 200(0xc8, float:2.8E-43)
            r7 = 0
            if (r5 >= r2) goto L_0x03a5
            java.util.concurrent.LinkedBlockingQueue<com.ximalaya.ting.android.player.BufferItem> r5 = r10.c     // Catch:{ Exception -> 0x064d }
            int r5 = r5.size()     // Catch:{ Exception -> 0x064d }
            if (r5 < r3) goto L_0x03a5
            boolean r5 = r10.f     // Catch:{ Exception -> 0x064d }
            if (r5 != 0) goto L_0x03a5
            int r5 = r10.h     // Catch:{ Exception -> 0x064d }
            int r8 = r1.a()     // Catch:{ Exception -> 0x064d }
            if (r5 >= r8) goto L_0x03a5
            boolean r5 = r10.m     // Catch:{ Exception -> 0x064d }
            if (r5 != 0) goto L_0x03a5
            java.lang.String r5 = r10.l     // Catch:{ Exception -> 0x064d }
            if (r5 == 0) goto L_0x02d4
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            if (r5 != 0) goto L_0x02d4
            java.lang.String r5 = r10.l     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r8 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r8 = r8.e()     // Catch:{ Exception -> 0x064d }
            boolean r5 = r5.equals(r8)     // Catch:{ Exception -> 0x064d }
            if (r5 != 0) goto L_0x02d4
            java.lang.String r5 = com.ximalaya.ting.android.player.XMediaPlayerConstants.o     // Catch:{ Exception -> 0x0229 }
            java.lang.String r8 = r10.l     // Catch:{ Exception -> 0x0229 }
            com.ximalaya.ting.android.player.AudioFile r5 = com.ximalaya.ting.android.player.AudioFile.a(r5, r8)     // Catch:{ Exception -> 0x0229 }
            r10.k = r5     // Catch:{ Exception -> 0x0229 }
            goto L_0x0235
        L_0x0229:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r5 = r5.d()     // Catch:{ Exception -> 0x064d }
            r5.f2271a = r4     // Catch:{ Exception -> 0x064d }
        L_0x0235:
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r5 = r5.d()     // Catch:{ Exception -> 0x064d }
            boolean r5 = r5.f()     // Catch:{ Exception -> 0x064d }
            if (r5 == 0) goto L_0x02d4
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r5 = r5.d()     // Catch:{ Exception -> 0x064d }
            boolean r5 = r5.a(r4)     // Catch:{ Exception -> 0x064d }
            if (r5 != 0) goto L_0x02d4
            com.ximalaya.ting.android.player.DownloadThread r5 = new com.ximalaya.ting.android.player.DownloadThread     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r6 = r10.k     // Catch:{ Exception -> 0x064d }
            r5.<init>(r6, r4)     // Catch:{ Exception -> 0x064d }
            r5.a()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r5 = r5.f()     // Catch:{ Exception -> 0x064d }
            if (r5 == 0) goto L_0x02a9
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r6 = r10.k     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r6 = r6.f()     // Catch:{ Exception -> 0x064d }
            byte[] r6 = r6.array()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r8 = r10.k     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r8 = r8.f()     // Catch:{ Exception -> 0x064d }
            byte[] r8 = r8.array()     // Catch:{ Exception -> 0x064d }
            int r8 = r8.length     // Catch:{ Exception -> 0x064d }
            r5.a((int) r4, (byte[]) r6, (int) r4, (int) r8)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r5 = r10.k     // Catch:{ Exception -> 0x064d }
            r5.a((java.nio.ByteBuffer) r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r5 = "dl_mp3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r6.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "预缓冲 url_0:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r7 = r10.k     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = r7.e()     // Catch:{ Exception -> 0x064d }
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = " downloadIndex:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            r6.append(r4)     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "下载并且缓存成功"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x064d }
            goto L_0x01e6
        L_0x02a9:
            java.lang.String r5 = "dl_mp3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r6.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "预缓冲 url_0:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r7 = r10.k     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = r7.e()     // Catch:{ Exception -> 0x064d }
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = " downloadIndex:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            r6.append(r4)     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "失败"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x064d }
            goto L_0x01e6
        L_0x02d4:
            com.ximalaya.ting.android.player.AudioFile r5 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r5 = r5.d()     // Catch:{ Exception -> 0x064d }
            int r8 = r10.h     // Catch:{ Exception -> 0x064d }
            boolean r5 = r5.a(r8)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.XMediaplayerJNI r8 = r10.j     // Catch:{ Exception -> 0x064d }
            int r9 = r10.c()     // Catch:{ Exception -> 0x064d }
            r8.onBufferingUpdateInner(r9)     // Catch:{ Exception -> 0x064d }
            if (r5 != 0) goto L_0x0386
            com.ximalaya.ting.android.player.DownloadThread r5 = new com.ximalaya.ting.android.player.DownloadThread     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r8 = r10.b     // Catch:{ Exception -> 0x064d }
            int r9 = r10.h     // Catch:{ Exception -> 0x064d }
            r5.<init>(r8, r9)     // Catch:{ Exception -> 0x064d }
            int r5 = r5.a()     // Catch:{ Exception -> 0x064d }
            if (r5 == r6) goto L_0x0380
            com.ximalaya.ting.android.player.AudioFile r5 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r5 = r5.f()     // Catch:{ Exception -> 0x064d }
            if (r5 == 0) goto L_0x0354
            com.ximalaya.ting.android.player.AudioFile r5 = r10.b     // Catch:{ Exception -> 0x064d }
            int r6 = r10.h     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r8 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r8 = r8.f()     // Catch:{ Exception -> 0x064d }
            byte[] r8 = r8.array()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r9 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r9 = r9.f()     // Catch:{ Exception -> 0x064d }
            byte[] r9 = r9.array()     // Catch:{ Exception -> 0x064d }
            int r9 = r9.length     // Catch:{ Exception -> 0x064d }
            r5.a((int) r6, (byte[]) r8, (int) r4, (int) r9)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r5 = r10.b     // Catch:{ Exception -> 0x064d }
            r5.a((java.nio.ByteBuffer) r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r5 = "dl_mp3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r6.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "url:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r7 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = r7.e()     // Catch:{ Exception -> 0x064d }
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = " downloadIndex:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            int r7 = r10.h     // Catch:{ Exception -> 0x064d }
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "下载并且缓存成功"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x064d }
            int r5 = r10.h     // Catch:{ Exception -> 0x064d }
            int r5 = r5 + r0
            r10.h = r5     // Catch:{ Exception -> 0x064d }
            goto L_0x038b
        L_0x0354:
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " downloadIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.h     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "下载失败error"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            goto L_0x03a5
        L_0x0380:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x064d }
            r1.<init>()     // Catch:{ Exception -> 0x064d }
            throw r1     // Catch:{ Exception -> 0x064d }
        L_0x0386:
            int r5 = r10.h     // Catch:{ Exception -> 0x064d }
            int r5 = r5 + r0
            r10.h = r5     // Catch:{ Exception -> 0x064d }
        L_0x038b:
            java.lang.String r5 = "dl_mp3"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r6.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r7 = "getCachePercent percent mDownloadIndex0:"
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            int r7 = r10.h     // Catch:{ Exception -> 0x064d }
            r6.append(r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x064d }
            goto L_0x01e6
        L_0x03a5:
            boolean r1 = r10.f     // Catch:{ Exception -> 0x064d }
            if (r1 == 0) goto L_0x03cd
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "fileDesc not Valid stopFlag:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            boolean r3 = r10.f     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            goto L_0x0668
        L_0x03cd:
            boolean r1 = r10.m     // Catch:{ Exception -> 0x064d }
            if (r1 == 0) goto L_0x03ff
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "fileDesc not Valid stopFlag:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            boolean r3 = r10.f     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " isResetIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            boolean r3 = r10.m     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            goto L_0x0007
        L_0x03ff:
            com.ximalaya.ting.android.player.XMediaplayerJNI r1 = r10.j     // Catch:{ Exception -> 0x064d }
            int r2 = r10.c()     // Catch:{ Exception -> 0x064d }
            r1.onBufferingUpdateInner(r2)     // Catch:{ Exception -> 0x064d }
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "开始获取分段数据：url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r1 = r1.d()     // Catch:{ Exception -> 0x064d }
            int r2 = r10.e     // Catch:{ Exception -> 0x064d }
            boolean r1 = r1.a(r2)     // Catch:{ Exception -> 0x064d }
            if (r1 != 0) goto L_0x051c
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "缓存命中失败"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.DownloadThread r1 = new com.ximalaya.ting.android.player.DownloadThread     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r2 = r10.b     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x064d }
            r10.g = r1     // Catch:{ Exception -> 0x064d }
            int r1 = r1.a()     // Catch:{ Exception -> 0x064d }
            if (r1 == r6) goto L_0x0516
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r1 = r1.f()     // Catch:{ Exception -> 0x064d }
            if (r1 == 0) goto L_0x04e9
            com.ximalaya.ting.android.player.BufferItem r1 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ Exception -> 0x064d }
            r1.<init>()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r2 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r2 = r2.f()     // Catch:{ Exception -> 0x064d }
            r1.a((java.nio.ByteBuffer) r2)     // Catch:{ Exception -> 0x064d }
            int r2 = r10.e     // Catch:{ Exception -> 0x064d }
            r1.a((int) r2)     // Catch:{ Exception -> 0x064d }
            r10.a((com.ximalaya.ting.android.player.BufferItem) r1)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            int r2 = r10.e     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r3 = r3.f()     // Catch:{ Exception -> 0x064d }
            byte[] r3 = r3.array()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r5 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r5 = r5.f()     // Catch:{ Exception -> 0x064d }
            byte[] r5 = r5.array()     // Catch:{ Exception -> 0x064d }
            int r5 = r5.length     // Catch:{ Exception -> 0x064d }
            r1.a((int) r2, (byte[]) r3, (int) r4, (int) r5)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            r1.a((java.nio.ByteBuffer) r7)     // Catch:{ Exception -> 0x064d }
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "下载并且缓存成功"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            int r1 = r10.e     // Catch:{ Exception -> 0x064d }
            int r1 = r1 + r0
            r10.e = r1     // Catch:{ Exception -> 0x064d }
            goto L_0x05e4
        L_0x04e9:
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "下载失败error"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            goto L_0x0668
        L_0x0516:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ Exception -> 0x064d }
            r1.<init>()     // Catch:{ Exception -> 0x064d }
            throw r1     // Catch:{ Exception -> 0x064d }
        L_0x051c:
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "缓存命中成功"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            int r1 = r10.e     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.BufferItem r1 = r10.a((int) r1)     // Catch:{ Exception -> 0x064d }
            if (r1 == 0) goto L_0x0583
            java.lang.String r2 = "dl_mp3"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r3.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r4 = "url:"
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r4 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r4 = r4.e()     // Catch:{ Exception -> 0x064d }
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            java.lang.String r4 = " curIndex:"
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            int r4 = r10.e     // Catch:{ Exception -> 0x064d }
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            java.lang.String r4 = "缓存获取成功"
            r3.append(r4)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x064d }
            r10.a((com.ximalaya.ting.android.player.BufferItem) r1)     // Catch:{ Exception -> 0x064d }
            int r1 = r10.e     // Catch:{ Exception -> 0x064d }
            int r1 = r1 + r0
            r10.e = r1     // Catch:{ Exception -> 0x064d }
            goto L_0x05e4
        L_0x0583:
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "缓存获取失败error"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.DownloadThread r1 = new com.ximalaya.ting.android.player.DownloadThread     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r2 = r10.b     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x064d }
            r10.g = r1     // Catch:{ Exception -> 0x064d }
            r1.a()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r1 = r1.f()     // Catch:{ Exception -> 0x064d }
            if (r1 == 0) goto L_0x05e4
            com.ximalaya.ting.android.player.BufferItem r1 = new com.ximalaya.ting.android.player.BufferItem     // Catch:{ Exception -> 0x064d }
            r1.<init>()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r2 = r10.b     // Catch:{ Exception -> 0x064d }
            java.nio.ByteBuffer r2 = r2.f()     // Catch:{ Exception -> 0x064d }
            r1.a((java.nio.ByteBuffer) r2)     // Catch:{ Exception -> 0x064d }
            int r2 = r10.e     // Catch:{ Exception -> 0x064d }
            r1.a((int) r2)     // Catch:{ Exception -> 0x064d }
            r10.a((com.ximalaya.ting.android.player.BufferItem) r1)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r1 = r10.b     // Catch:{ Exception -> 0x064d }
            r1.a((java.nio.ByteBuffer) r7)     // Catch:{ Exception -> 0x064d }
            int r1 = r10.e     // Catch:{ Exception -> 0x064d }
            int r1 = r1 + r0
            r10.e = r1     // Catch:{ Exception -> 0x064d }
        L_0x05e4:
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "结束获取分段数据：url:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = r3.e()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            java.lang.String r1 = "dl_mp3"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x064d }
            r2.<init>()     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = "======================ReadThread end while("
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = ":"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.AudioFile r3 = r10.b     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.FileDesc r3 = r3.d()     // Catch:{ Exception -> 0x064d }
            int r3 = r3.a()     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = ") stopFlag:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            boolean r3 = r10.f     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r3 = " curIndex:"
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            int r3 = r10.e     // Catch:{ Exception -> 0x064d }
            r2.append(r3)     // Catch:{ Exception -> 0x064d }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x064d }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x064d }
            goto L_0x0007
        L_0x064a:
            r1 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x064a }
            throw r1     // Catch:{ Exception -> 0x064d }
        L_0x064d:
            r1 = move-exception
            java.lang.String r2 = "dl_mp3"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "ReadThread Exception:"
            r3.append(r4)
            java.lang.String r1 = r1.getMessage()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r1)
        L_0x0668:
            r10.f = r0
            java.lang.String r0 = "dl_mp3"
            java.lang.String r1 = "======================ReadThread run() end"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.ReadThread.run():void");
    }

    private void a(BufferItem bufferItem) throws InterruptedException {
        if (!this.m) {
            Logger.a(f2281a, (Object) "putItem url:" + this.b.e() + " item Index:" + bufferItem.e());
            StringBuilder sb = new StringBuilder();
            sb.append("resetIndex count3 putItem:");
            sb.append(this.c.size());
            Logger.a(f2281a, (Object) sb.toString());
            this.c.put(bufferItem);
            Logger.a(f2281a, (Object) "resetIndex count4 putItem:" + this.c.size());
            return;
        }
        Logger.a(f2281a, (Object) "resetIndex count5:" + this.c.size());
    }

    private BufferItem a(int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(65536);
        try {
            if (this.b.a(i2, 65536, allocate.array(), 0) != 65536) {
                return null;
            }
            BufferItem bufferItem = new BufferItem();
            bufferItem.a(allocate);
            bufferItem.a(i2);
            return bufferItem;
        } catch (IOException unused) {
            return null;
        }
    }

    public void d() {
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "mReadThread close" + toString());
        this.f = true;
        this.c.clear();
        if (this.g != null) {
            this.g.b();
        }
        this.j = null;
    }

    public boolean e() {
        return this.f;
    }
}
