package com.tencent.smtt.sdk;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.tencent.smtt.export.external.interfaces.DownloadListener;
import com.tencent.smtt.sdk.a.d;
import java.util.HashMap;

class b implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    private DownloadListener f9154a;
    private WebView b;

    public b(WebView webView, DownloadListener downloadListener, boolean z) {
        this.f9154a = downloadListener;
        this.b = webView;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        onDownloadStart(str, (String) null, (byte[]) null, str2, str3, str4, j, (String) null, (String) null);
    }

    public void onDownloadStart(String str, String str2, byte[] bArr, String str3, String str4, String str5, long j, String str6, String str7) {
        String str8 = str;
        if (this.f9154a == null) {
            String str9 = str5;
            if (QbSdk.canOpenMimeFileType(this.b.getContext(), str5)) {
                Intent intent = new Intent("com.tencent.QQBrowser.action.sdk.document");
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_reader_sdk_url", str);
                intent.putExtra("key_reader_sdk_type", 1);
                intent.setData(Uri.parse(str));
                this.b.getContext().startActivity(intent);
                return;
            }
            ApplicationInfo applicationInfo = this.b.getContext().getApplicationInfo();
            if (applicationInfo == null || !"com.tencent.mm".equals(applicationInfo.packageName)) {
                d.a(this.b.getContext(), str, (HashMap<String, String>) null, (WebView) null);
                return;
            }
            return;
        }
        this.f9154a.onDownloadStart(str, str3, str4, str5, j);
    }

    public void onDownloadVideo(String str, long j, int i) {
    }
}
