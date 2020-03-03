package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.m;

class ax implements m.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TbsLogReport f9151a;

    ax(TbsLogReport tbsLogReport) {
        this.f9151a = tbsLogReport;
    }

    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportTbsLog] httpResponseCode=" + i);
    }
}
