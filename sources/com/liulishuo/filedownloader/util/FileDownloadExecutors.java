package com.liulishuo.filedownloader.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FileDownloadExecutors {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6462a = 5;

    public static ThreadPoolExecutor a(int i, String str) {
        return a(i, new LinkedBlockingQueue(), str);
    }

    public static ThreadPoolExecutor a(int i, LinkedBlockingQueue<Runnable> linkedBlockingQueue, String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 5, TimeUnit.SECONDS, linkedBlockingQueue, new FileDownloadThreadFactory(str));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    static class FileDownloadThreadFactory implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private static final AtomicInteger f6463a = new AtomicInteger(1);
        private final String b;
        private final ThreadGroup c = Thread.currentThread().getThreadGroup();
        private final AtomicInteger d = new AtomicInteger(1);

        FileDownloadThreadFactory(String str) {
            this.b = FileDownloadUtils.k(str);
        }

        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.c;
            Thread thread = new Thread(threadGroup, runnable, this.b + this.d.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }
}
