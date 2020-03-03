package com.liulishuo.filedownloader;

public abstract class FileDownloadLargeFileListener extends FileDownloadListener {
    /* access modifiers changed from: protected */
    public abstract void a(BaseDownloadTask baseDownloadTask, long j, long j2);

    /* access modifiers changed from: protected */
    public void a(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public void a(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j) {
    }

    /* access modifiers changed from: protected */
    public abstract void b(BaseDownloadTask baseDownloadTask, long j, long j2);

    /* access modifiers changed from: protected */
    public abstract void c(BaseDownloadTask baseDownloadTask, long j, long j2);

    /* access modifiers changed from: protected */
    public void connected(BaseDownloadTask baseDownloadTask, String str, boolean z, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
    }

    public FileDownloadLargeFileListener() {
    }

    public FileDownloadLargeFileListener(int i) {
        super(i);
    }
}
