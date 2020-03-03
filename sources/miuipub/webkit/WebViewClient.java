package miuipub.webkit;

import android.graphics.Bitmap;
import android.webkit.WebView;
import com.miuipub.internal.webkit.WebViewClientDelegate;
import com.miuipub.internal.webkit.WebViewWrapper;

public class WebViewClient extends android.webkit.WebViewClient {
    public static final int b = 1;
    public static final int c = 2;

    /* renamed from: a  reason: collision with root package name */
    private WebViewClientDelegate f3066a;

    public WebViewClient() {
        this.f3066a = new WebViewClientDelegate();
    }

    public WebViewClient(int i) {
        this.f3066a = new WebViewClientDelegate(i);
    }

    public WebViewClient(int i, int i2) {
        this.f3066a = new WebViewClientDelegate(i, i2);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return this.f3066a.a(new WebViewWrapper(webView), str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f3066a.a(new WebViewWrapper(webView), str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        this.f3066a.b(new WebViewWrapper(webView), str);
    }

    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        this.f3066a.a(new WebViewWrapper(webView), str, str2, str3);
    }
}
