package com.alipay.zoloz.toyger.widget;

import android.view.View;

class e implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GenenalDialog f1210a;

    e(GenenalDialog genenalDialog) {
        this.f1210a = genenalDialog;
    }

    public void onClick(View view) {
        this.f1210a._self.dismiss();
        if (this.f1210a.negativeListener != null) {
            this.f1210a.negativeListener.onClick(this.f1210a._self, -2);
        }
    }
}
