package com.alipay.sdk.app;

import android.app.Activity;

final class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f1073a;

    f(Activity activity) {
        this.f1073a = activity;
    }

    public void run() {
        this.f1073a.finish();
    }
}
