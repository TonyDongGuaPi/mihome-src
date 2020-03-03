package com.liulishuo.filedownloader;

import android.app.Notification;

public class FileDownloadLineAsync {
    public boolean a(final int i, final Notification notification) {
        if (FileDownloader.a().j()) {
            FileDownloader.a().a(i, notification);
            return true;
        }
        FileDownloader.a().a((Runnable) new Runnable() {
            public void run() {
                FileDownloader.a().a(i, notification);
            }
        });
        return false;
    }
}
