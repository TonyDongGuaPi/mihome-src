package com.alipay.zoloz.toyger.workspace;

import com.alipay.biometrics.ui.widget.TitleBar;

class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f1233a;
    final /* synthetic */ m b;

    p(m mVar, int i) {
        this.b = mVar;
        this.f1233a = i;
    }

    public void run() {
        if (this.b.f1230a.mToygerCirclePattern != null && this.b.f1230a.mToygerCirclePattern.getTitleBar() != null && !"Cherry".equalsIgnoreCase("Cherry")) {
            TitleBar titleBar = this.b.f1230a.mToygerCirclePattern.getTitleBar();
            titleBar.setTimeOut((this.f1233a / 1000) + "S");
        }
    }
}
