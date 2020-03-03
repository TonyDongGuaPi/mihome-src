package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;

final class m implements TbsDownloader.TbsDownloaderCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9184a;
    final /* synthetic */ QbSdk.PreInitCallback b;

    m(Context context, QbSdk.PreInitCallback preInitCallback) {
        this.f9184a = context;
        this.b = preInitCallback;
    }

    public void onNeedDownloadFinish(boolean z, int i) {
        if (TbsShareManager.findCoreForThirdPartyApp(this.f9184a) != 0 || TbsShareManager.getCoreDisabled()) {
            if (QbSdk.i && TbsShareManager.isThirdPartyApp(this.f9184a)) {
                TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(this.f9184a);
            }
            QbSdk.preInit(this.f9184a, this.b);
            return;
        }
        TbsShareManager.forceToLoadX5ForThirdApp(this.f9184a, false);
        if (QbSdk.i && TbsShareManager.isThirdPartyApp(this.f9184a)) {
            TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(this.f9184a);
        }
    }
}
