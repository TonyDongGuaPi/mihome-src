package com.alipay.sdk.auth;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

class a implements DownloadListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AuthActivity f1087a;

    a(AuthActivity authActivity) {
        this.f1087a = authActivity;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            this.f1087a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Throwable unused) {
        }
    }
}
