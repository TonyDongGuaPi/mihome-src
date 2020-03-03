package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.m;

class ay implements m.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TbsLogReport f9152a;

    ay(TbsLogReport tbsLogReport) {
        this.f9152a = tbsLogReport;
    }

    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i);
        if (i < 300) {
            this.f9152a.h();
        }
    }
}
