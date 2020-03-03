package com.xiaomi.jr.web;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.mi.global.shop.model.Tags;
import com.xiaomi.jr.hybrid.HybridContext;
import com.xiaomi.jr.hybrid.NativeInterface;
import com.xiaomi.jr.hybrid.Response;

public class WebHybridContext extends HybridContext {
    /* access modifiers changed from: private */
    public WebView f;

    public WebHybridContext(Context context, Fragment fragment, NativeInterface nativeInterface, WebView webView) {
        super(context, fragment, nativeInterface);
        this.f = webView;
    }

    public String f() {
        if (this.b instanceof WebFragment) {
            return ((WebFragment) this.b).h();
        }
        return super.f();
    }

    public void a(Response response, Object obj) {
        if (this.f != null) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                if (response != null) {
                    str = a(response, str);
                }
                this.e.post(new JsInvocation(str));
            }
        }
    }

    public void a(String str) {
        a((Response) null, (Object) String.format("console.log(\"[App] %s\")", new Object[]{str}));
    }

    private class JsInvocation implements Runnable {
        private String b;

        public JsInvocation(String str) {
            this.b = str;
        }

        public void run() {
            if (Build.VERSION.SDK_INT >= 19) {
                WebHybridContext.this.f.evaluateJavascript(this.b, (ValueCallback) null);
                return;
            }
            WebView a2 = WebHybridContext.this.f;
            a2.loadUrl("javascript:" + this.b);
        }
    }

    private String a(Response response, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str + "('" + response.toString().replace(Tags.MiHome.TEL_SEPARATOR4, "\\\\").replace("'", "\\'") + "');";
    }
}
