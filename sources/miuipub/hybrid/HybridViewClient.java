package miuipub.hybrid;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.http.SslError;
import com.miuipub.internal.hybrid.HybridManager;
import com.miuipub.internal.webkit.WebViewClientDelegate;
import java.io.IOException;
import java.util.UUID;

public class HybridViewClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2947a = "hybrid/";
    private static final String b = "android_asset/hybrid/";
    private HybridManager c;
    private WebViewClientDelegate d = new WebViewClientDelegate();

    public void a(HybridManager hybridManager) {
        this.c = hybridManager;
    }

    public void a(HybridView hybridView, String str, Bitmap bitmap) {
        PageContext pageContext = new PageContext();
        pageContext.a(UUID.randomUUID().toString());
        pageContext.b(str);
        this.c.a(pageContext);
        this.c.c();
        hybridView.setWebProvider(str);
        hybridView.setLoadingError(false);
        this.d.a(hybridView.getWebView(), str, bitmap);
    }

    public void b(HybridView hybridView, String str) {
        if (this.c.b().getActionBar() != null) {
            this.c.b().getActionBar().setTitle(hybridView.getTitle());
        }
        this.d.b(hybridView.getWebView(), str);
    }

    public HybridResourceResponse c(HybridView hybridView, String str) {
        int indexOf;
        if (str == null || !str.startsWith("http") || (indexOf = str.indexOf(b)) < 0 || b.length() + indexOf >= str.length()) {
            return null;
        }
        String substring = str.substring(indexOf + b.length());
        try {
            AssetManager assets = this.c.b().getAssets();
            return new HybridResourceResponse((String) null, (String) null, assets.open(f2947a + substring));
        } catch (IOException unused) {
            return null;
        }
    }

    public boolean a(HybridView hybridView, String str) {
        return this.d.a(hybridView.getWebView(), str);
    }

    public void a(HybridView hybridView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.b();
    }

    public void a(HybridView hybridView, int i, String str, String str2) {
        hybridView.setLoadingError(true);
        hybridView.showReloadView();
    }

    public void a(HybridView hybridView, String str, String str2, String str3) {
        this.d.a(hybridView.getWebView(), str, str2, str3);
    }
}
