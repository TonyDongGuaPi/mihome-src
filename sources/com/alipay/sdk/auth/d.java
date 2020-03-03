package com.alipay.sdk.auth;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.auth.AuthActivity;
import com.alipay.sdk.widget.e;
import com.xiaomi.infrared.InifraredContants;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SslErrorHandler f1090a;
    final /* synthetic */ AuthActivity.c b;

    d(AuthActivity.c cVar, SslErrorHandler sslErrorHandler) {
        this.b = cVar;
        this.f1090a = sslErrorHandler;
    }

    public void run() {
        e.a(AuthActivity.this, "安全警告", "由于您的设备缺少根证书，将无法校验该访问站点的安全性，可能存在风险，请选择是否继续？", "继续", new e(this), InifraredContants.B, new f(this));
    }
}
