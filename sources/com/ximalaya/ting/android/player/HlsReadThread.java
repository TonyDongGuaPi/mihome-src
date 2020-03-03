package com.ximalaya.ting.android.player;

import com.ximalaya.ting.android.player.XMediaplayerJNI;
import java.util.concurrent.LinkedBlockingQueue;

public class HlsReadThread extends Thread {
    private static final String n = "dl_hls";

    /* renamed from: a  reason: collision with root package name */
    public Object f2274a = new Object();
    public Object b = new Object();
    public volatile boolean c = false;
    private HlsAudioFile d;
    private boolean e = false;
    private volatile int f;
    private int g;
    private volatile int h;
    private String i;
    private XMediaplayerJNI j;
    private volatile Object k = new Object();
    private volatile boolean l;
    private volatile LinkedBlockingQueue<BufferItem> m;

    public HlsReadThread(HlsAudioFile hlsAudioFile, XMediaplayerJNI xMediaplayerJNI, String str, LinkedBlockingQueue<BufferItem> linkedBlockingQueue) {
        super("HlsReadThreadForPlayer");
        this.d = hlsAudioFile;
        this.j = xMediaplayerJNI;
        this.i = str;
        this.m = linkedBlockingQueue;
        this.l = true;
    }

    public void a(LinkedBlockingQueue<BufferItem> linkedBlockingQueue) {
        synchronized (this.k) {
            Logger.a(n, (Object) "resetIndex bq.size()0:" + linkedBlockingQueue.size());
            this.l = true;
            this.h = this.d.e();
            if (this.m != null) {
                this.m.clear();
            }
            this.m = linkedBlockingQueue;
            Logger.a(n, (Object) "resetIndex bq.size()1:" + linkedBlockingQueue.size());
        }
    }

    public void a() {
        if (this.c) {
            synchronized (this.b) {
                this.c = false;
                this.b.notify();
            }
        }
    }

    public void run() {
        this.h = this.d.e();
        this.l = true;
        while (true) {
            if (this.e || !MD5.b(this.j.getPlayUrl()).equals(MD5.b(this.i))) {
                break;
            }
            synchronized (this.k) {
                if (this.l) {
                    Logger.a(n, (Object) "resetIndex isResetIndex buffItemQueue.size():" + this.m.size());
                    this.l = false;
                    this.f = this.h;
                    this.g = this.h;
                }
            }
            if (this.f >= this.d.d() && !this.j.getAudioType().equals(XMediaplayerJNI.AudioType.HLS_FILE) && !this.l) {
                break;
            }
            if (this.g < this.f) {
                this.g = this.f;
            }
            int i2 = XMediaPlayerConstants.l - 3;
            while (this.g - this.f < i2 && this.m.size() >= 3 && !this.e && this.g < this.d.d() && !this.l) {
                String a2 = this.d.a(this.g);
                if (new HlsDownloadThread(a2, (BufferItem) null).a() > 0) {
                    Logger.a(n, (Object) "url:" + a2 + " downloadIndex:" + this.g + "下载并且缓存成功1");
                    this.g = this.g + 1;
                    this.j.onBufferingUpdateInner(this.d.a());
                } else {
                    Logger.a(n, (Object) "url:" + a2 + " downloadIndex:" + this.g + "下载失败error1");
                }
                Logger.a(n, (Object) "getCachePercent percent mDownloadIndex:" + this.g);
            }
            if (!this.e) {
                if (!this.l) {
                    String a3 = this.d.a(this.f);
                    Logger.a(n, (Object) "HlsReadThread downUrl0:" + a3 + "    cacheIndex:" + this.f + "getPlayUrlsLength:" + this.d.d());
                    if (a3 == null) {
                        if (!this.j.getAudioType().equals(XMediaplayerJNI.AudioType.HLS_FILE)) {
                            break;
                        }
                        synchronized (this.b) {
                            this.c = true;
                            try {
                                this.b.wait();
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else {
                        BufferItem bufferItem = new BufferItem();
                        bufferItem.a(this.f);
                        if (new HlsDownloadThread(a3, bufferItem).a() <= 0) {
                            Logger.a(n, (Object) "url:" + a3 + " curIndex:" + this.f + "下载并且缓存失败2");
                            break;
                        }
                        a(bufferItem);
                        Logger.a(n, (Object) "url:" + a3 + " curIndex:" + this.f + "下载并且缓存成功2");
                        this.f = this.f + 1;
                        this.j.onBufferingUpdateInner(this.d.a());
                    }
                }
            } else {
                break;
            }
        }
        this.e = true;
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "HlsReadThread isStop:" + this.e + "cacheIndex:" + this.f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[SYNTHETIC, Splitter:B:16:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0030 A[SYNTHETIC, Splitter:B:24:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(java.io.File r5, com.ximalaya.ting.android.player.BufferItem r6) {
        /*
            r4 = this;
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x002d, all -> 0x0025 }
            java.lang.String r2 = "rw"
            r1.<init>(r5, r2)     // Catch:{ IOException -> 0x002d, all -> 0x0025 }
            long r2 = r1.length()     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            int r5 = (int) r2     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            r0 = 0
        L_0x0010:
            int r2 = r5.length     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            int r2 = r2 - r0
            int r2 = r1.read(r5, r0, r2)     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            int r0 = r0 + r2
            if (r2 > 0) goto L_0x0010
            r6.a((byte[]) r5)     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            r1.close()     // Catch:{ IOException -> 0x002e, all -> 0x0023 }
            r1.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0022:
            return r0
        L_0x0023:
            r5 = move-exception
            goto L_0x0027
        L_0x0025:
            r5 = move-exception
            r1 = r0
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            throw r5
        L_0x002d:
            r1 = r0
        L_0x002e:
            if (r1 == 0) goto L_0x0033
            r1.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.HlsReadThread.a(java.io.File, com.ximalaya.ting.android.player.BufferItem):int");
    }

    private void a(BufferItem bufferItem) {
        if (!this.l) {
            Logger.a(n, (Object) "putItem url:" + this.d.a(bufferItem.e()) + " item Index:" + bufferItem.e());
            StringBuilder sb = new StringBuilder();
            sb.append("putItem buffItemQueue.size()0:");
            sb.append(this.m.size());
            Logger.a(n, (Object) sb.toString());
            try {
                this.m.put(bufferItem);
            } catch (InterruptedException unused) {
            }
            Logger.a(n, (Object) "putItem buffItemQueue.size()1:" + this.m.size());
            return;
        }
        Logger.a(n, (Object) "putItem buffItemQueue.size()2:" + this.m.size());
    }

    public int b() {
        if (this.g == 0) {
            return this.d.e();
        }
        return this.g;
    }

    public void c() {
        this.e = true;
        if (this.m != null) {
            this.m.clear();
        }
        Logger.a(XMediaplayerJNI.Tag, (Object) "HlsReadThread hls readData close");
    }

    public boolean d() {
        return this.e;
    }
}
