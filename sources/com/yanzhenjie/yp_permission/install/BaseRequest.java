package com.yanzhenjie.yp_permission.install;

import android.content.Context;
import android.content.Intent;
import com.google.android.exoplayer2.C;
import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.AndPermission;
import com.yanzhenjie.yp_permission.Rationale;
import com.yanzhenjie.yp_permission.RequestExecutor;
import com.yanzhenjie.yp_permission.source.Source;
import java.io.File;

abstract class BaseRequest implements InstallRequest {

    /* renamed from: a  reason: collision with root package name */
    private Source f2451a;
    private File b;
    private Rationale<File> c = new Rationale<File>() {
        public void a(Context context, File file, RequestExecutor requestExecutor) {
            requestExecutor.b();
        }
    };
    private Action<File> d;
    private Action<File> e;

    BaseRequest(Source source) {
        this.f2451a = source;
    }

    public final InstallRequest a(File file) {
        this.b = file;
        return this;
    }

    public final InstallRequest a(Rationale<File> rationale) {
        this.c = rationale;
        return this;
    }

    public final InstallRequest a(Action<File> action) {
        this.d = action;
        return this;
    }

    public final InstallRequest b(Action<File> action) {
        this.e = action;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void a(RequestExecutor requestExecutor) {
        this.c.a(this.f2451a.a(), null, requestExecutor);
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.addFlags(1);
        intent.setDataAndType(AndPermission.a(this.f2451a.a(), this.b), "application/vnd.android.package-archive");
        this.f2451a.a(intent);
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        if (this.d != null) {
            this.d.a(this.b);
        }
    }

    /* access modifiers changed from: package-private */
    public final void f() {
        if (this.e != null) {
            this.e.a(this.b);
        }
    }
}
