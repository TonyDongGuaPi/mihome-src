package com.alipay.sdk.widget;

import android.view.View;
import com.alipay.sdk.widget.WebViewWindow;

class q implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewWindow f1156a;

    q(WebViewWindow webViewWindow) {
        this.f1156a = webViewWindow;
    }

    public void onClick(View view) {
        WebViewWindow.c a2 = this.f1156a.i;
        if (a2 != null) {
            view.setEnabled(false);
            WebViewWindow.f.postDelayed(new r(this, view), 256);
            if (view == this.f1156a.f1141a) {
                a2.a(this.f1156a);
            } else if (view == this.f1156a.c) {
                a2.b(this.f1156a);
            }
        }
    }
}
