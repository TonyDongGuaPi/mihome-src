package com.tencent.smtt.utils;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.utils.d;

class e implements d.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView f9204a;
    final /* synthetic */ Context b;
    final /* synthetic */ RelativeLayout c;
    final /* synthetic */ String d;
    final /* synthetic */ TextView e;
    final /* synthetic */ d f;

    e(d dVar, WebView webView, Context context, RelativeLayout relativeLayout, String str, TextView textView) {
        this.f = dVar;
        this.f9204a = webView;
        this.b = context;
        this.c = relativeLayout;
        this.d = str;
        this.e = textView;
    }

    public void a() {
        this.f9204a.post(new f(this));
    }

    public void a(int i) {
        this.f9204a.post(new g(this, i));
    }

    public void a(Throwable th) {
        this.f9204a.post(new h(this));
    }
}
