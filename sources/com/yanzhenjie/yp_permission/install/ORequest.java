package com.yanzhenjie.yp_permission.install;

import com.yanzhenjie.yp_permission.PermissionActivity;
import com.yanzhenjie.yp_permission.RequestExecutor;
import com.yanzhenjie.yp_permission.source.Source;
import com.yanzhenjie.yp_permission.util.MainExecutor;

class ORequest extends BaseRequest implements PermissionActivity.RequestListener, RequestExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final MainExecutor f2453a = new MainExecutor();
    private Source b;

    ORequest(Source source) {
        super(source);
        this.b = source;
    }

    public void g() {
        if (this.b.b()) {
            e();
            d();
            return;
        }
        a((RequestExecutor) this);
    }

    public void b() {
        PermissionActivity.requestInstall(this.b.a(), this);
    }

    public void c() {
        f();
    }

    public void a() {
        f2453a.a(new Runnable() {
            public void run() {
                ORequest.this.h();
            }
        }, 100);
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.b.b()) {
            e();
            d();
            return;
        }
        f();
    }
}
