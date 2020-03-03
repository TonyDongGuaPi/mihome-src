package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;

public interface IFileDownloadServiceProxy {
    void a(int i, Notification notification);

    void a(Context context);

    void a(Context context, Runnable runnable);

    void a(boolean z);

    boolean a(int i);

    boolean a(String str, String str2);

    boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3);

    long b(int i);

    void b(Context context);

    long c(int i);

    void c();

    byte d(int i);

    boolean d();

    boolean e();

    boolean e(int i);

    void f();

    boolean f(int i);
}
