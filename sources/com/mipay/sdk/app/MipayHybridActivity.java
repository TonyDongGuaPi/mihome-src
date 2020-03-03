package com.mipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;
import com.a.a.a.b;
import com.a.a.a.c;

public class MipayHybridActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private b f8155a;

    /* access modifiers changed from: protected */
    public void a(WebView webView) {
        this.f8155a = new b(this, webView);
        webView.addJavascriptInterface(new c(this.f8155a), "MiuiJsBridge");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.f8155a.a(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f8155a.f();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f8155a.d();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f8155a.c();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.f8155a.b();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.f8155a.e();
    }
}
