package com.alipay.sdk.widget;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.google.android.exoplayer2.C;

class i implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ h f1149a;

    i(h hVar) {
        this.f1149a = hVar;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            this.f1149a.f1148a.startActivity(intent);
        } catch (Throwable unused) {
        }
    }
}
