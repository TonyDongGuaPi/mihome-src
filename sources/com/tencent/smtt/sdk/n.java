package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;

final class n implements TbsListener {
    n() {
    }

    public void onDownloadFinish(int i) {
        if (TbsDownloader.needDownloadDecoupleCore()) {
            TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is true", true);
            TbsDownloader.f9092a = true;
            return;
        }
        TbsLog.i("QbSdk", "onDownloadFinish needDownloadDecoupleCore is false", true);
        TbsDownloader.f9092a = false;
        if (QbSdk.B != null) {
            QbSdk.B.onDownloadFinish(i);
        }
        if (QbSdk.C != null) {
            QbSdk.C.onDownloadFinish(i);
        }
    }

    public void onDownloadProgress(int i) {
        if (QbSdk.C != null) {
            QbSdk.C.onDownloadProgress(i);
        }
        if (QbSdk.B != null) {
            QbSdk.B.onDownloadProgress(i);
        }
    }

    public void onInstallFinish(int i) {
        if (i != 200) {
        }
        boolean z = false;
        QbSdk.setTBSInstallingStatus(false);
        TbsDownloader.f9092a = false;
        if (TbsDownloader.startDecoupleCoreIfNeeded()) {
            z = true;
        }
        TbsDownloader.f9092a = z;
        if (QbSdk.B != null) {
            QbSdk.B.onInstallFinish(i);
        }
        if (QbSdk.C != null) {
            QbSdk.C.onInstallFinish(i);
        }
    }
}
