package com.tencent.smtt.sdk;

class w implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ v f9193a;

    w(v vVar) {
        this.f9193a = vVar;
    }

    public void run() {
        if (!TbsShareManager.forceLoadX5FromTBSDemo(this.f9193a.b.getContext()) && TbsDownloader.needDownload(this.f9193a.b.getContext(), false)) {
            TbsDownloader.startDownload(this.f9193a.b.getContext());
        }
    }
}
