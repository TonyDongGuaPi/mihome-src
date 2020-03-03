package com.mi.global.bbs.service;

import android.content.Context;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.notification.FileDownloadNotificationHelper;
import com.mi.global.bbs.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

public class MiDownloader {
    static MiDownloader mDownloader;
    List<String> mDownloadQueue = new ArrayList();
    NotificationListener mListener = new NotificationListener(new FileDownloadNotificationHelper(), this.mDownloadQueue);

    public static MiDownloader init(Context context) {
        if (mDownloader == null) {
            synchronized (MiDownloader.class) {
                mDownloader = new MiDownloader(context.getApplicationContext());
            }
        }
        return mDownloader;
    }

    public MiDownloader(Context context) {
        FileDownloader.a(context);
    }

    public void download(String str) {
        if (!this.mDownloadQueue.contains(str)) {
            this.mDownloadQueue.add(str);
            FileDownloader.a().a(str).a(FileUtils.getAttachFileDir(), true).a((FileDownloadListener) this.mListener).h();
        }
    }
}
