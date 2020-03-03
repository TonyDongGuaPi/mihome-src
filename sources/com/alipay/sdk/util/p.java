package com.alipay.sdk.util;

import android.app.Activity;

final class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f1140a;

    p(Activity activity) {
        this.f1140a = activity;
    }

    public void run() {
        this.f1140a.finish();
    }
}
