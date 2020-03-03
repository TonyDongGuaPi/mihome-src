package com.alipay.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;
import com.google.android.exoplayer2.C;

final class o implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f1139a;

    o(Context context) {
        this.f1139a = context;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            this.f1139a.startActivity(intent);
        } catch (Throwable unused) {
        }
    }
}
