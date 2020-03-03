package com.alipay.zoloz.toyger.widget;

import android.view.View;

class f implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GenenalDialog f1211a;

    f(GenenalDialog genenalDialog) {
        this.f1211a = genenalDialog;
    }

    public void onClick(View view) {
        this.f1211a._self.dismiss();
        if (this.f1211a.negativeListener != null) {
            this.f1211a.negativeListener.onClick(this.f1211a._self, -2);
        }
    }
}
