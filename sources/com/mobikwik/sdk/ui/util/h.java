package com.mobikwik.sdk.ui.util;

import android.text.Html;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f8425a;

    h(g gVar) {
        this.f8425a = gVar;
    }

    public void run() {
        this.f8425a.f8424a.incrementProgressBy(1);
        this.f8425a.b.d.setMessage(Html.fromHtml(this.f8425a.b.a(this.f8425a.f8424a.getMax() - this.f8425a.f8424a.getProgress())));
        if (this.f8425a.f8424a.getProgress() >= d.f8420a) {
            this.f8425a.b.f();
            this.f8425a.b.b();
            this.f8425a.b.e();
        }
    }
}
