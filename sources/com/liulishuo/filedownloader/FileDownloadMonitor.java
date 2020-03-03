package com.liulishuo.filedownloader;

public class FileDownloadMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static IMonitor f6396a;

    public interface IMonitor {
        void a(int i, boolean z, FileDownloadListener fileDownloadListener);

        void a(BaseDownloadTask baseDownloadTask);

        void b(BaseDownloadTask baseDownloadTask);

        void c(BaseDownloadTask baseDownloadTask);

        void d(BaseDownloadTask baseDownloadTask);
    }

    public static void a(IMonitor iMonitor) {
        f6396a = iMonitor;
    }

    public static void a() {
        f6396a = null;
    }

    public static IMonitor b() {
        return f6396a;
    }

    public static boolean c() {
        return b() != null;
    }
}
