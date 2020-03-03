package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.j;

class m extends j.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewWindow f1152a;
    final /* synthetic */ String b;
    final /* synthetic */ j c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    m(j jVar, WebViewWindow webViewWindow, String str) {
        super(jVar, (k) null);
        this.c = jVar;
        this.f1152a = webViewWindow;
        this.b = str;
    }

    public void onAnimationEnd(Animation animation) {
        this.c.removeView(this.f1152a);
        this.c.x.a(this.b);
        boolean unused = this.c.v = false;
    }
}
