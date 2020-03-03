package com.unionpay.mobile.android.widgets;

import android.view.ViewGroup;
import android.widget.PopupWindow;
import com.unionpay.mobile.android.global.a;

final class bc implements PopupWindow.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bb f9788a;

    bc(bb bbVar) {
        this.f9788a = bbVar;
    }

    public final void onDismiss() {
        if (this.f9788a.g != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f9788a.g.getLayoutParams();
            marginLayoutParams.bottomMargin = a.b;
            marginLayoutParams.height = this.f9788a.h;
            this.f9788a.g.setLayoutParams(marginLayoutParams);
        }
    }
}
