package com.alipay.sdk.widget;

import android.webkit.SslErrorHandler;
import com.xiaomi.infrared.InifraredContants;

class n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SslErrorHandler f1153a;
    final /* synthetic */ j b;

    n(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.f1153a = sslErrorHandler;
    }

    public void run() {
        e.a(this.b.f1148a, "安全警告", "安全連接證書校驗無效，將無法保證訪問資料的安全性，可能存在風險，請選擇是否繼續？", "繼續", new o(this), InifraredContants.B, new p(this));
    }
}
