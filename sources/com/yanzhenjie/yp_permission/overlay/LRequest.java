package com.yanzhenjie.yp_permission.overlay;

import com.yanzhenjie.yp_permission.PermissionActivity;
import com.yanzhenjie.yp_permission.RequestExecutor;
import com.yanzhenjie.yp_permission.source.Source;
import com.yanzhenjie.yp_permission.util.MainExecutor;

class LRequest extends BaseRequest implements PermissionActivity.RequestListener, RequestExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final MainExecutor f2457a = new MainExecutor();
    private Source b;

    LRequest(Source source) {
        super(source);
        this.b = source;
    }

    public void f() {
        if (a(this.b.a())) {
            d();
        } else {
            a((RequestExecutor) this);
        }
    }

    public void b() {
        PermissionActivity.requestAlertWindow(this.b.a(), this);
    }

    public void c() {
        e();
    }

    public void a() {
        f2457a.a(new Runnable() {
            public void run() {
                LRequest.this.g();
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
