package com.alipay.zoloz.toyger.widget;

import android.view.View;

class d implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GenenalDialog f1209a;

    d(GenenalDialog genenalDialog) {
        this.f1209a = genenalDialog;
    }

    public void onClick(View view) {
        this.f1209a._self.dismiss();
        if (this.f1209a.positiveListener != null) {
            this.f1209a.positiveListener.onClick(this.f1209a._self, -1);
        }
    }
}
