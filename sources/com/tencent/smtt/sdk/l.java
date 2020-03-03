package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;

final class l implements TbsListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9183a;
    final /* synthetic */ QbSdk.PreInitCallback b;

    l(Context context, QbSdk.PreInitCallback preInitCallback) {
        this.f9183a = context;
        this.b = preInitCallback;
    }

    public void onDownloadFinish(int i) {
    }

    public void onDownloadProgress(int i) {
    }

    public void onInstallFinish(int i) {
        QbSdk.preInit(this.f9183a, this.b);
    }
}
