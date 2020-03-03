package com.tencent.smtt.sdk;

class ae implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ad f9135a;

    ae(ad adVar) {
        this.f9135a = adVar;
    }

    public void run() {
        if (!TbsShareManager.forceLoadX5FromTBSDemo(this.f9135a.b.getContext()) && TbsDownloader.needDownload(this.f9135a.b.getContext(), false)) {
            TbsDownloader.startDownload(this.f9135a.b.getContext());
        }
    }
}
