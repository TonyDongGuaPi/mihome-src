package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.j;

class l extends j.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewWindow f1151a;
    final /* synthetic */ j b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    l(j jVar, WebViewWindow webViewWindow) {
        super(jVar, (k) null);
        this.b = jVar;
        this.f1151a = webViewWindow;
    }

    public void onAnimationEnd(Animation animation) {
        this.f1151a.a();
        boolean unused = this.b.v = false;
    }
}
