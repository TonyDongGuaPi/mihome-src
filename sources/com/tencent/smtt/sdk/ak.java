package com.tencent.smtt.sdk;

import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.m;

final class ak implements m.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TbsDownloadConfig f9140a;
    final /* synthetic */ boolean b;

    ak(TbsDownloadConfig tbsDownloadConfig, boolean z) {
        this.f9140a = tbsDownloadConfig;
        this.b = z;
    }

    public void a(int i) {
        TbsDownloadConfig tbsDownloadConfig;
        int i2;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.sendRequest] httpResponseCode=" + i);
        if (TbsShareManager.isThirdPartyApp(TbsDownloader.c) && i == 200) {
            this.f9140a.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_REQUEST_SUCCESS, Long.valueOf(System.currentTimeMillis()));
            this.f9140a.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_REQUEST_FAIL, 0L);
            this.f9140a.f9091a.put(TbsDownloadConfig.TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, 0L);
            this.f9140a.commit();
        }
        if (i >= 300) {
            if (this.b) {
                tbsDownloadConfig = this.f9140a;
                i2 = -107;
            } else {
                tbsDownloadConfig = this.f9140a;
                i2 = -207;
            }
            tbsDownloadConfig.setDownloadInterruptCode(i2);
        }
    }
}
