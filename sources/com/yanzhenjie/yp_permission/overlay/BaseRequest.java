package com.yanzhenjie.yp_permission.overlay;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import com.yanzhenjie.yp_permission.Action;
import com.yanzhenjie.yp_permission.R;
import com.yanzhenjie.yp_permission.Rationale;
import com.yanzhenjie.yp_permission.RequestExecutor;
import com.yanzhenjie.yp_permission.source.Source;

abstract class BaseRequest implements OverlayRequest {

    /* renamed from: a  reason: collision with root package name */
    private Source f2455a;
    private Rationale<Void> b = new Rationale<Void>() {
        public void a(Context context, Void voidR, RequestExecutor requestExecutor) {
            requestExecutor.b();
        }
    };
    private Action<Void> c;
    private Action<Void> d;

    BaseRequest(Source source) {
        this.f2455a = source;
    }

    public final OverlayRequest a(Rationale<Void> rationale) {
        this.b = rationale;
        return this;
    }

    public final OverlayRequest a(Action<Void> action) {
        this.c = action;
        return this;
    }

    public final OverlayRequest b(Action<Void> action) {
        this.d = action;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void a(RequestExecutor requestExecutor) {
        this.b.a(this.f2455a.a(), null, requestExecutor);
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        if (this.c != null) {
            this.c.a(null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        if (this.d != null) {
            this.d.a(null);
        }
    }

    static boolean a(Context context) {
        Dialog dialog = new Dialog(context, R.style.YPPermission_Theme_Dialog);
        dialog.getWindow().setType(Build.VERSION.SDK_INT >= 26 ? 2038 : 2003);
        try {
            dialog.show();
            if (!dialog.isShowing()) {
                return true;
            }
            dialog.dismiss();
            return true;
        } catch (Exception unused) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            return false;
        } catch (Throwable th) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            throw th;
        }
    }
}
