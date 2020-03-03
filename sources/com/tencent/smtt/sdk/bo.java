package com.tencent.smtt.sdk;

import android.content.pm.ApplicationInfo;
import android.webkit.DownloadListener;
import com.tencent.smtt.sdk.a.d;
import java.util.HashMap;

class bo implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DownloadListener f9168a;
    final /* synthetic */ WebView b;

    bo(WebView webView, DownloadListener downloadListener) {
        this.b = webView;
        this.f9168a = downloadListener;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (this.f9168a == null) {
            ApplicationInfo applicationInfo = this.b.i == null ? null : this.b.i.getApplicationInfo();
            if (applicationInfo == null || !"com.tencent.mm".equals(applicationInfo.packageName)) {
                d.a(this.b.i, str, (HashMap<String, String>) null, (WebView) null);
                return;
            }
            return;
        }
        this.f9168a.onDownloadStart(str, str2, str3, str4, j);
    }
}
