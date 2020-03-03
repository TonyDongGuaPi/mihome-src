package com.yanzhenjie.yp_permission.overlay;

import com.yanzhenjie.yp_permission.PermissionActivity;
import com.yanzhenjie.yp_permission.RequestExecutor;
import com.yanzhenjie.yp_permission.source.Source;
import com.yanzhenjie.yp_permission.util.MainExecutor;

class MRequest extends BaseRequest implements PermissionActivity.RequestListener, RequestExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final MainExecutor f2459a = new MainExecutor();
    private Source b;

    MRequest(Source source) {
        super(source);
        this.b = source;
    }

    public void f() {
        if (this.b.c()) {
            g();
        } else {
            a((RequestExecutor) this);
        }
    }

    public void b() {
        PermissionActivity.requestOverlay(this.b.a(), this);
    }

    public void c() {
        e();
    }

    public void a() {
        f2459a.a(new Runnable() {
            public void run() {
                MRequest.this.g();
            }
        }, 100);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (!this.b.c() || !a(this.b.a())) {
            e();
        } else {
            d();
        }
    }
}
