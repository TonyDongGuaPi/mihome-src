package com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;

public class ThirdSchemeInterceptor extends IUrlInterceptorAdapter {

    /* renamed from: a  reason: collision with root package name */
    private final Context f21080a;
    private final ThirdSchemeDialogHelper b;
    private boolean c = true;

    public ThirdSchemeInterceptor(Context context) {
        this.f21080a = context;
        this.b = new ThirdSchemeDialogHelper(context);
    }

    public boolean a(WebView webView, String str) {
        String scheme = Uri.parse(str).getScheme();
        if (UrlDispatchManger.a().d(str)) {
            UrlDispatchManger.a().c(str);
            return true;
        } else if (scheme == null || scheme.equals("https") || scheme.equals("http")) {
            return false;
        } else {
            if (scheme.equals("tel") || scheme.equals("mailto")) {
                if (this.f21080a.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536).size() > 0) {
                    this.f21080a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return true;
            } else if (scheme.equals("mihome") || scheme.equals(PageUrl.e)) {
                return true;
            } else {
                if (this.b.b()) {
                    return false;
                }
                if (this.f21080a.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536).size() > 0) {
                    this.b.a(new Runnable(str) {
                        private final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ThirdSchemeInterceptor.this.a(this.f$1);
                        }
                    });
                }
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str) {
        this.f21080a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public void a() {
        if (!this.c) {
            this.b.a();
        }
        this.c = false;
    }

    public void b() {
        this.b.a();
    }
}
