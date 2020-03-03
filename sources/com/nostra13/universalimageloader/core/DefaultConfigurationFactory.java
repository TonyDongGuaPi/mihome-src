package com.nostra13.universalimageloader.core;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultConfigurationFactory {
    public static Executor a(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, queueProcessingType == QueueProcessingType.LIFO ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue(), a(i2, "uil-pool-"));
    }

    public static Executor a() {
        return Executors.newCachedThreadPool(a(5, "uil-pool-d-"));
    }

    public static FileNameGenerator b() {
        return new HashCodeFileNameGenerator();
    }

    public static DiskCache a(Context context, FileNameGenerator fileNameGenerator, long j, int i) {
        File b = b(context);
        if (j > 0 || i > 0) {
            try {
                return new LruDiskCache(StorageUtils.b(context), b, fileNameGenerator, j, i);
            } catch (IOException e) {
                L.a((Throwable) e);
            }
        }
        return new UnlimitedDiskCache(StorageUtils.a(context), b, fileNameGenerator);
    }

    private static File b(Context context) {
        File a2 = StorageUtils.a(context, false);
        File file = new File(a2, "uil-images");
        return (file.exists() || file.mkdir()) ? file : a2;
    }

    public static MemoryCache a(Context context, int i) {
        if (i == 0) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int memoryClass = activityManager.getMemoryClass();
            if (d() && c(context)) {
                memoryClass = a(activityManager);
            }
            i = (memoryClass * 1048576) / 8;
        }
        return new LruMemoryCache(i);
    }

    private static boolean d() {
        return Build.VERSION.SDK_INT >= 11;
    }

    @TargetApi(11)
    private static boolean c(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    @TargetApi(11)
    private static int a(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    public static ImageDownloader a(Context context) {
        return new BaseImageDownloader(context);
    }

    public static ImageDecoder a(boolean z) {
        return new BaseImageDecoder(z);
    }

    public static BitmapDisplayer c() {
        return new SimpleBitmapDisplayer();
    }

    private static ThreadFactory a(int i, String str) {
        return new DefaultThreadFactory(i, str);
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f8454a = new AtomicInteger(1);
        private final ThreadGroup b;
        private final AtomicInteger c = new AtomicInteger(1);
        private final String d;
        private final int e;

        DefaultThreadFactory(int i, String str) {
            this.e = i;
            this.b = Thread.currentThread().getThreadGroup();
            this.d = str + f8454a.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.b;
            Thread thread = new Thread(threadGroup, runnable, this.d + this.c.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.e);
            return thread;
        }
    }
}
