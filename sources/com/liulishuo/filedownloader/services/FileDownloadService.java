package com.liulishuo.filedownloader.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.lang.ref.WeakReference;

@SuppressLint({"Registered"})
public class FileDownloadService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private IFileDownloadServiceHandler f6455a;

    public static class SeparateProcessService extends FileDownloadService {
    }

    public static class SharedMainProcessService extends FileDownloadService {
    }

    public void onCreate() {
        super.onCreate();
        FileDownloadHelper.a(this);
        try {
            FileDownloadUtils.a(FileDownloadProperties.a().f6466a);
            FileDownloadUtils.a(FileDownloadProperties.a().b);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FileDownloadManager fileDownloadManager = new FileDownloadManager();
        if (FileDownloadProperties.a().d) {
            this.f6455a = new FDServiceSharedHandler(new WeakReference(this), fileDownloadManager);
        } else {
            this.f6455a = new FDServiceSeparateHandler(new WeakReference(this), fileDownloadManager);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.f6455a.onStartCommand(intent, i, i2);
        return 1;
    }

    public void onDestroy() {
        this.f6455a.onDestroy();
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return this.f6455a.onBind(intent);
    }
}
