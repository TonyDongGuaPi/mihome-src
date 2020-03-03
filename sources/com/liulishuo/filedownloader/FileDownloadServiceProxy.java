package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler;
import com.liulishuo.filedownloader.util.FileDownloadProperties;

public class FileDownloadServiceProxy implements IFileDownloadServiceProxy {

    /* renamed from: a  reason: collision with root package name */
    private final IFileDownloadServiceProxy f6398a;

    private static final class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloadServiceProxy f6399a = new FileDownloadServiceProxy();

        private HolderClass() {
        }
    }

    public static FileDownloadServiceProxy a() {
        return HolderClass.f6399a;
    }

    public static FDServiceSharedHandler.FileDownloadServiceSharedConnection b() {
        if (a().f6398a instanceof FileDownloadServiceSharedTransmit) {
            return (FDServiceSharedHandler.FileDownloadServiceSharedConnection) a().f6398a;
        }
        return null;
    }

    private FileDownloadServiceProxy() {
        this.f6398a = FileDownloadProperties.a().d ? new FileDownloadServiceSharedTransmit() : new FileDownloadServiceUIGuard();
    }

    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        return this.f6398a.a(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean a(int i) {
        return this.f6398a.a(i);
    }

    public boolean a(String str, String str2) {
        return this.f6398a.a(str, str2);
    }

    public long b(int i) {
        return this.f6398a.b(i);
    }

    public long c(int i) {
        return this.f6398a.c(i);
    }

    public byte d(int i) {
        return this.f6398a.d(i);
    }

    public void c() {
        this.f6398a.c();
    }

    public boolean d() {
        return this.f6398a.d();
    }

    public boolean e() {
        return this.f6398a.e();
    }

    public void a(Context context) {
        this.f6398a.a(context);
    }

    public void a(Context context, Runnable runnable) {
        this.f6398a.a(context, runnable);
    }

    public void b(Context context) {
        this.f6398a.b(context);
    }

    public void a(int i, Notification notification) {
        this.f6398a.a(i, notification);
    }

    public void a(boolean z) {
        this.f6398a.a(z);
    }

    public boolean e(int i) {
        return this.f6398a.e(i);
    }

    public boolean f(int i) {
        return this.f6398a.f(i);
    }

    public void f() {
        this.f6398a.f();
    }
}
