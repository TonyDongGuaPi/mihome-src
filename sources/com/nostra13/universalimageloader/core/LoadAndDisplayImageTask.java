package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

final class LoadAndDisplayImageTask implements IoUtils.CopyListener, Runnable {
    private static final String f = "ImageLoader is paused. Waiting...  [%s]";
    private static final String g = ".. Resume loading [%s]";
    private static final String h = "Delay %d ms before loading...  [%s]";
    private static final String i = "Start display image task [%s]";
    private static final String j = "Image already is loading. Waiting... [%s]";
    private static final String k = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String l = "Load image from network [%s]";
    private static final String m = "Load image from disk cache [%s]";
    private static final String n = "Resize image in disk cache [%s]";
    private static final String o = "PreProcess image before caching in memory [%s]";
    private static final String p = "PostProcess image before displaying [%s]";
    private static final String q = "Cache image in memory [%s]";
    private static final String r = "Cache image on disk [%s]";
    private static final String s = "Process image before cache on disk [%s]";
    private static final String t = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String u = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String v = "Task was interrupted [%s]";
    private static final String w = "No stream for image [%s]";
    private static final String x = "Pre-processor returned null [%s]";
    private static final String y = "Post-processor returned null [%s]";
    private static final String z = "Bitmap processor for disk cache returned null [%s]";
    private final ImageLoaderEngine A;
    private final ImageLoadingInfo B;
    private final Handler C;
    /* access modifiers changed from: private */
    public final ImageLoaderConfiguration D;
    private final ImageDownloader E;
    private final ImageDownloader F;
    private final ImageDownloader G;
    private final ImageDecoder H;
    private final String I;
    private final ImageSize J;
    private final boolean K;
    private LoadedFrom L = LoadedFrom.NETWORK;

    /* renamed from: a  reason: collision with root package name */
    final String f8468a;
    final ImageAware b;
    final DisplayImageOptions c;
    final ImageLoadingListener d;
    final ImageLoadingProgressListener e;

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.A = imageLoaderEngine;
        this.B = imageLoadingInfo;
        this.C = handler;
        this.D = imageLoaderEngine.f8465a;
        this.E = this.D.p;
        this.F = this.D.s;
        this.G = this.D.t;
        this.H = this.D.q;
        this.f8468a = imageLoadingInfo.f8467a;
        this.I = imageLoadingInfo.b;
        this.b = imageLoadingInfo.c;
        this.J = imageLoadingInfo.d;
        this.c = imageLoadingInfo.e;
        this.d = imageLoadingInfo.f;
        this.e = imageLoadingInfo.g;
        this.K = this.c.s();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:41|42|43|44) */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fb, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        g();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0103, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0104, code lost:
        r0.unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0107, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00fd */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d2 A[Catch:{ TaskCancelledException -> 0x00fd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            boolean r0 = r7.b()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = r7.c()
            if (r0 == 0) goto L_0x000e
            return
        L_0x000e:
            com.nostra13.universalimageloader.core.ImageLoadingInfo r0 = r7.B
            java.util.concurrent.locks.ReentrantLock r0 = r0.h
            java.lang.String r1 = "Start display image task [%s]"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = r7.I
            r5 = 0
            r3[r5] = r4
            com.nostra13.universalimageloader.utils.L.a(r1, r3)
            boolean r1 = r0.isLocked()
            if (r1 == 0) goto L_0x0030
            java.lang.String r1 = "Image already is loading. Waiting... [%s]"
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = r7.I
            r3[r5] = r4
            com.nostra13.universalimageloader.utils.L.a(r1, r3)
        L_0x0030:
            r0.lock()
            r7.i()     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r1 = r7.D     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.cache.memory.MemoryCache r1 = r1.n     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r3 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            android.graphics.Bitmap r1 = r1.a(r3)     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r1 == 0) goto L_0x0059
            boolean r3 = r1.isRecycled()     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r3 == 0) goto L_0x0049
            goto L_0x0059
        L_0x0049:
            com.nostra13.universalimageloader.core.assist.LoadedFrom r3 = com.nostra13.universalimageloader.core.assist.LoadedFrom.MEMORY_CACHE     // Catch:{ TaskCancelledException -> 0x00fd }
            r7.L = r3     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r3 = "...Get cached bitmap from memory after waiting. [%s]"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r6 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r4[r5] = r6     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.utils.L.a(r3, r4)     // Catch:{ TaskCancelledException -> 0x00fd }
            goto L_0x00b1
        L_0x0059:
            android.graphics.Bitmap r1 = r7.d()     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r1 != 0) goto L_0x0063
            r0.unlock()
            return
        L_0x0063:
            r7.i()     // Catch:{ TaskCancelledException -> 0x00fd }
            r7.o()     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r7.c     // Catch:{ TaskCancelledException -> 0x00fd }
            boolean r3 = r3.d()     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r3 == 0) goto L_0x0093
            java.lang.String r3 = "PreProcess image before caching in memory [%s]"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r6 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r4[r5] = r6     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.utils.L.a(r3, r4)     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r7.c     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.process.BitmapProcessor r3 = r3.o()     // Catch:{ TaskCancelledException -> 0x00fd }
            android.graphics.Bitmap r1 = r3.a(r1)     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r1 != 0) goto L_0x0093
            java.lang.String r3 = "Pre-processor returned null [%s]"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r6 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r4[r5] = r6     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.utils.L.d(r3, r4)     // Catch:{ TaskCancelledException -> 0x00fd }
        L_0x0093:
            if (r1 == 0) goto L_0x00b1
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r7.c     // Catch:{ TaskCancelledException -> 0x00fd }
            boolean r3 = r3.h()     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r3 == 0) goto L_0x00b1
            java.lang.String r3 = "Cache image in memory [%s]"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r6 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r4[r5] = r6     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.utils.L.a(r3, r4)     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r3 = r7.D     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.cache.memory.MemoryCache r3 = r3.n     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r4 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r3.a(r4, r1)     // Catch:{ TaskCancelledException -> 0x00fd }
        L_0x00b1:
            if (r1 == 0) goto L_0x00dd
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r7.c     // Catch:{ TaskCancelledException -> 0x00fd }
            boolean r3 = r3.e()     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r3 == 0) goto L_0x00dd
            java.lang.String r3 = "PostProcess image before displaying [%s]"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r6 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r4[r5] = r6     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.utils.L.a(r3, r4)     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r7.c     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.core.process.BitmapProcessor r3 = r3.p()     // Catch:{ TaskCancelledException -> 0x00fd }
            android.graphics.Bitmap r1 = r3.a(r1)     // Catch:{ TaskCancelledException -> 0x00fd }
            if (r1 != 0) goto L_0x00dd
            java.lang.String r3 = "Post-processor returned null [%s]"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ TaskCancelledException -> 0x00fd }
            java.lang.String r4 = r7.I     // Catch:{ TaskCancelledException -> 0x00fd }
            r2[r5] = r4     // Catch:{ TaskCancelledException -> 0x00fd }
            com.nostra13.universalimageloader.utils.L.d(r3, r2)     // Catch:{ TaskCancelledException -> 0x00fd }
        L_0x00dd:
            r7.i()     // Catch:{ TaskCancelledException -> 0x00fd }
            r7.o()     // Catch:{ TaskCancelledException -> 0x00fd }
            r0.unlock()
            com.nostra13.universalimageloader.core.DisplayBitmapTask r0 = new com.nostra13.universalimageloader.core.DisplayBitmapTask
            com.nostra13.universalimageloader.core.ImageLoadingInfo r2 = r7.B
            com.nostra13.universalimageloader.core.ImageLoaderEngine r3 = r7.A
            com.nostra13.universalimageloader.core.assist.LoadedFrom r4 = r7.L
            r0.<init>(r1, r2, r3, r4)
            boolean r1 = r7.K
            android.os.Handler r2 = r7.C
            com.nostra13.universalimageloader.core.ImageLoaderEngine r3 = r7.A
            a(r0, r1, r2, r3)
            return
        L_0x00fb:
            r1 = move-exception
            goto L_0x0104
        L_0x00fd:
            r7.g()     // Catch:{ all -> 0x00fb }
            r0.unlock()
            return
        L_0x0104:
            r0.unlock()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.run():void");
    }

    private boolean b() {
        AtomicBoolean d2 = this.A.d();
        if (d2.get()) {
            synchronized (this.A.e()) {
                if (d2.get()) {
                    L.a(f, this.I);
                    try {
                        this.A.e().wait();
                        L.a(g, this.I);
                    } catch (InterruptedException unused) {
                        L.d(v, this.I);
                        return true;
                    }
                }
            }
        }
        return j();
    }

    private boolean c() {
        if (!this.c.f()) {
            return false;
        }
        L.a(h, Integer.valueOf(this.c.l()), this.I);
        try {
            Thread.sleep((long) this.c.l());
            return j();
        } catch (InterruptedException unused) {
            L.d(v, this.I);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a4, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a5, code lost:
        r0 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a8, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a9, code lost:
        r0 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ac, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ad, code lost:
        r0 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b0, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b9, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ba, code lost:
        r9 = r1;
        r1 = null;
        r0 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c7, code lost:
        r9 = r1;
        r1 = null;
        r0 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d4, code lost:
        r9 = r1;
        r1 = null;
        r0 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e1, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e2, code lost:
        r1 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e0 A[ExcHandler: TaskCancelledException (r0v1 'e' com.nostra13.universalimageloader.core.LoadAndDisplayImageTask$TaskCancelledException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap d() throws com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException {
        /*
            r10 = this;
            r0 = 0
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r1 = r10.D     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            com.nostra13.universalimageloader.cache.disc.DiskCache r1 = r1.o     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            java.lang.String r2 = r10.f8468a     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            java.io.File r1 = r1.a(r2)     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0040
            boolean r4 = r1.exists()     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            if (r4 == 0) goto L_0x0040
            long r4 = r1.length()     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0040
            java.lang.String r4 = "Load image from disk cache [%s]"
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            java.lang.String r6 = r10.I     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            r5[r2] = r6     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            com.nostra13.universalimageloader.utils.L.a(r4, r5)     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            com.nostra13.universalimageloader.core.assist.LoadedFrom r4 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            r10.L = r4     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            r10.i()     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme r4 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            java.lang.String r1 = r4.wrap(r1)     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            android.graphics.Bitmap r1 = r10.a((java.lang.String) r1)     // Catch:{ IllegalStateException -> 0x00e2, TaskCancelledException -> 0x00e0, IOException -> 0x00d3, OutOfMemoryError -> 0x00c6, Throwable -> 0x00b9 }
            goto L_0x0041
        L_0x0040:
            r1 = r0
        L_0x0041:
            if (r1 == 0) goto L_0x0059
            int r4 = r1.getWidth()     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            if (r4 <= 0) goto L_0x0059
            int r4 = r1.getHeight()     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            if (r4 > 0) goto L_0x00e8
            goto L_0x0059
        L_0x0050:
            r0 = move-exception
            goto L_0x00bd
        L_0x0053:
            r0 = move-exception
            goto L_0x00ca
        L_0x0056:
            r0 = move-exception
            goto L_0x00d7
        L_0x0059:
            java.lang.String r4 = "Load image from network [%s]"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            java.lang.String r5 = r10.I     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            r3[r2] = r5     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            com.nostra13.universalimageloader.utils.L.a(r4, r3)     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            com.nostra13.universalimageloader.core.assist.LoadedFrom r2 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            r10.L = r2     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            java.lang.String r2 = r10.f8468a     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            com.nostra13.universalimageloader.core.DisplayImageOptions r3 = r10.c     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            boolean r3 = r3.i()     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            if (r3 == 0) goto L_0x008e
            boolean r3 = r10.e()     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            if (r3 == 0) goto L_0x008e
            com.nostra13.universalimageloader.core.ImageLoaderConfiguration r3 = r10.D     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            com.nostra13.universalimageloader.cache.disc.DiskCache r3 = r3.o     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            java.lang.String r4 = r10.f8468a     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            java.io.File r3 = r3.a(r4)     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            if (r3 == 0) goto L_0x008e
            com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme r2 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            java.lang.String r2 = r2.wrap(r3)     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
        L_0x008e:
            r10.i()     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            android.graphics.Bitmap r2 = r10.a((java.lang.String) r2)     // Catch:{ IllegalStateException -> 0x00e3, TaskCancelledException -> 0x00e0, IOException -> 0x0056, OutOfMemoryError -> 0x0053, Throwable -> 0x0050 }
            if (r2 == 0) goto L_0x00b2
            int r1 = r2.getWidth()     // Catch:{ IllegalStateException -> 0x00b0, TaskCancelledException -> 0x00e0, IOException -> 0x00ac, OutOfMemoryError -> 0x00a8, Throwable -> 0x00a4 }
            if (r1 <= 0) goto L_0x00b2
            int r1 = r2.getHeight()     // Catch:{ IllegalStateException -> 0x00b0, TaskCancelledException -> 0x00e0, IOException -> 0x00ac, OutOfMemoryError -> 0x00a8, Throwable -> 0x00a4 }
            if (r1 > 0) goto L_0x00b7
            goto L_0x00b2
        L_0x00a4:
            r1 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x00bd
        L_0x00a8:
            r1 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x00ca
        L_0x00ac:
            r1 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x00d7
        L_0x00b0:
            r1 = r2
            goto L_0x00e3
        L_0x00b2:
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r1 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.DECODING_ERROR     // Catch:{ IllegalStateException -> 0x00b0, TaskCancelledException -> 0x00e0, IOException -> 0x00ac, OutOfMemoryError -> 0x00a8, Throwable -> 0x00a4 }
            r10.a((com.nostra13.universalimageloader.core.assist.FailReason.FailType) r1, (java.lang.Throwable) r0)     // Catch:{ IllegalStateException -> 0x00b0, TaskCancelledException -> 0x00e0, IOException -> 0x00ac, OutOfMemoryError -> 0x00a8, Throwable -> 0x00a4 }
        L_0x00b7:
            r1 = r2
            goto L_0x00e8
        L_0x00b9:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00bd:
            com.nostra13.universalimageloader.utils.L.a((java.lang.Throwable) r0)
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.UNKNOWN
            r10.a((com.nostra13.universalimageloader.core.assist.FailReason.FailType) r2, (java.lang.Throwable) r0)
            goto L_0x00e8
        L_0x00c6:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00ca:
            com.nostra13.universalimageloader.utils.L.a((java.lang.Throwable) r0)
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY
            r10.a((com.nostra13.universalimageloader.core.assist.FailReason.FailType) r2, (java.lang.Throwable) r0)
            goto L_0x00e8
        L_0x00d3:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
        L_0x00d7:
            com.nostra13.universalimageloader.utils.L.a((java.lang.Throwable) r0)
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR
            r10.a((com.nostra13.universalimageloader.core.assist.FailReason.FailType) r2, (java.lang.Throwable) r0)
            goto L_0x00e8
        L_0x00e0:
            r0 = move-exception
            throw r0
        L_0x00e2:
            r1 = r0
        L_0x00e3:
            com.nostra13.universalimageloader.core.assist.FailReason$FailType r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.NETWORK_DENIED
            r10.a((com.nostra13.universalimageloader.core.assist.FailReason.FailType) r2, (java.lang.Throwable) r0)
        L_0x00e8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.d():android.graphics.Bitmap");
    }

    private Bitmap a(String str) throws IOException {
        String str2 = str;
        return this.H.a(new ImageDecodingInfo(this.I, str2, this.f8468a, this.J, this.b.c(), h(), this.c));
    }

    private boolean e() throws TaskCancelledException {
        L.a(r, this.I);
        try {
            boolean f2 = f();
            if (!f2) {
                return f2;
            }
            int i2 = this.D.d;
            int i3 = this.D.e;
            if (i2 <= 0 && i3 <= 0) {
                return f2;
            }
            L.a(n, this.I);
            b(i2, i3);
            return f2;
        } catch (IOException e2) {
            L.a((Throwable) e2);
            return false;
        }
    }

    private boolean f() throws IOException {
        InputStream stream = h().getStream(this.f8468a, this.c.n());
        if (stream == null) {
            L.d(w, this.I);
            return false;
        }
        try {
            return this.D.o.a(this.f8468a, stream, this);
        } finally {
            IoUtils.a((Closeable) stream);
        }
    }

    private boolean b(int i2, int i3) throws IOException {
        File a2 = this.D.o.a(this.f8468a);
        if (a2 == null || !a2.exists()) {
            return false;
        }
        Bitmap a3 = this.H.a(new ImageDecodingInfo(this.I, ImageDownloader.Scheme.FILE.wrap(a2.getAbsolutePath()), this.f8468a, new ImageSize(i2, i3), ViewScaleType.FIT_INSIDE, h(), new DisplayImageOptions.Builder().a(this.c).a(ImageScaleType.IN_SAMPLE_INT).d()));
        if (!(a3 == null || this.D.f == null)) {
            L.a(s, this.I);
            a3 = this.D.f.a(a3);
            if (a3 == null) {
                L.d(z, this.I);
            }
        }
        if (a3 == null) {
            return false;
        }
        boolean a4 = this.D.o.a(this.f8468a, a3);
        a3.recycle();
        return a4;
    }

    public boolean a(int i2, int i3) {
        return this.K || c(i2, i3);
    }

    private boolean c(final int i2, final int i3) {
        if (p() || j()) {
            return false;
        }
        if (this.e == null) {
            return true;
        }
        a(new Runnable() {
            public void run() {
                LoadAndDisplayImageTask.this.e.a(LoadAndDisplayImageTask.this.f8468a, LoadAndDisplayImageTask.this.b.d(), i2, i3);
            }
        }, false, this.C, this.A);
        return true;
    }

    private void a(final FailReason.FailType failType, final Throwable th) {
        if (!this.K && !p() && !j()) {
            a(new Runnable() {
                public void run() {
                    if (LoadAndDisplayImageTask.this.c.c()) {
                        LoadAndDisplayImageTask.this.b.a(LoadAndDisplayImageTask.this.c.c(LoadAndDisplayImageTask.this.D.f8460a));
                    }
                    LoadAndDisplayImageTask.this.d.onLoadingFailed(LoadAndDisplayImageTask.this.f8468a, LoadAndDisplayImageTask.this.b.d(), new FailReason(failType, th));
                }
            }, false, this.C, this.A);
        }
    }

    private void g() {
        if (!this.K && !p()) {
            a(new Runnable() {
                public void run() {
                    LoadAndDisplayImageTask.this.d.onLoadingCancelled(LoadAndDisplayImageTask.this.f8468a, LoadAndDisplayImageTask.this.b.d());
                }
            }, false, this.C, this.A);
        }
    }

    private ImageDownloader h() {
        if (this.A.f()) {
            return this.F;
        }
        if (this.A.g()) {
            return this.G;
        }
        return this.E;
    }

    private void i() throws TaskCancelledException {
        k();
        m();
    }

    private boolean j() {
        return l() || n();
    }

    private void k() throws TaskCancelledException {
        if (l()) {
            throw new TaskCancelledException();
        }
    }

    private boolean l() {
        if (!this.b.e()) {
            return false;
        }
        L.a(u, this.I);
        return true;
    }

    private void m() throws TaskCancelledException {
        if (n()) {
            throw new TaskCancelledException();
        }
    }

    private boolean n() {
        if (!(!this.I.equals(this.A.a(this.b)))) {
            return false;
        }
        L.a(t, this.I);
        return true;
    }

    private void o() throws TaskCancelledException {
        if (p()) {
            throw new TaskCancelledException();
        }
    }

    private boolean p() {
        if (!Thread.interrupted()) {
            return false;
        }
        L.a(v, this.I);
        return true;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.f8468a;
    }

    static void a(Runnable runnable, boolean z2, Handler handler, ImageLoaderEngine imageLoaderEngine) {
        if (z2) {
            runnable.run();
        } else if (handler == null) {
            imageLoaderEngine.a(runnable);
        } else {
            handler.post(runnable);
        }
    }

    class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }
}
