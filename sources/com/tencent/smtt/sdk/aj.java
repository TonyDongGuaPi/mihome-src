package com.tencent.smtt.sdk;

import android.os.Handler;
import android.os.Looper;

final class aj extends Handler {
    aj(Looper looper) {
        super(looper);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0182  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r13) {
        /*
            r12 = this;
            int r0 = r13.what
            r1 = 108(0x6c, float:1.51E-43)
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x00f1
            switch(r0) {
                case 100: goto L_0x0096;
                case 101: goto L_0x00f1;
                case 102: goto L_0x0046;
                case 103: goto L_0x0021;
                case 104: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x01e3
        L_0x000d:
            java.lang.String r13 = "TbsDownload"
            java.lang.String r0 = "[TbsDownloader.handleMessage] MSG_UPLOAD_TBSLOG"
            com.tencent.smtt.utils.TbsLog.i(r13, r0)
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsLogReport r13 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r13)
            r13.c()
            goto L_0x01e3
        L_0x0021:
            java.lang.String r0 = "TbsDownload"
            java.lang.String r1 = "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            int r0 = r13.arg1
            if (r0 != 0) goto L_0x0039
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()
            java.lang.Object r13 = r13.obj
            android.content.Context r13 = (android.content.Context) r13
            r0.a((android.content.Context) r13, (boolean) r2)
            goto L_0x01e3
        L_0x0039:
            com.tencent.smtt.sdk.am r0 = com.tencent.smtt.sdk.am.a()
            java.lang.Object r13 = r13.obj
            android.content.Context r13 = (android.content.Context) r13
            r0.a((android.content.Context) r13, (boolean) r3)
            goto L_0x01e3
        L_0x0046:
            java.lang.String r13 = "TbsDownload"
            java.lang.String r0 = "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT"
            com.tencent.smtt.utils.TbsLog.i(r13, r0)
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            boolean r13 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r13)
            if (r13 == 0) goto L_0x0060
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            int r13 = com.tencent.smtt.sdk.TbsShareManager.a((android.content.Context) r13, (boolean) r3)
            goto L_0x006c
        L_0x0060:
            com.tencent.smtt.sdk.am r13 = com.tencent.smtt.sdk.am.a()
            android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
            int r13 = r13.m(r0)
        L_0x006c:
            java.lang.String r0 = "TbsDownload"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[TbsDownloader.handleMessage] localTbsVersion="
            r1.append(r2)
            r1.append(r13)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            com.tencent.smtt.sdk.ag r0 = com.tencent.smtt.sdk.TbsDownloader.g
            r0.a((int) r13)
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsLogReport r13 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r13)
            r13.b()
            goto L_0x01e3
        L_0x0096:
            int r0 = r13.arg1
            if (r0 != r2) goto L_0x009c
            r0 = 1
            goto L_0x009d
        L_0x009c:
            r0 = 0
        L_0x009d:
            boolean r1 = com.tencent.smtt.sdk.TbsDownloader.b(r2, r3)
            java.lang.Object r2 = r13.obj
            if (r2 == 0) goto L_0x00dc
            java.lang.Object r2 = r13.obj
            boolean r2 = r2 instanceof com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback
            if (r2 == 0) goto L_0x00dc
            java.lang.String r2 = "TbsDownload"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "needDownload-onNeedDownloadFinish needStartDownload="
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r4)
            if (r1 == 0) goto L_0x00c5
            if (r0 == 0) goto L_0x00dc
        L_0x00c5:
            java.lang.Object r13 = r13.obj
            com.tencent.smtt.sdk.TbsDownloader$TbsDownloaderCallback r13 = (com.tencent.smtt.sdk.TbsDownloader.TbsDownloaderCallback) r13
            android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r0)
            android.content.SharedPreferences r0 = r0.mPreferences
            java.lang.String r2 = "tbs_download_version"
            int r0 = r0.getInt(r2, r3)
            r13.onNeedDownloadFinish(r1, r0)
        L_0x00dc:
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            boolean r13 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r13)
            if (r13 == 0) goto L_0x01e3
            if (r1 == 0) goto L_0x01e3
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloader.startDownload(r13)
            goto L_0x01e3
        L_0x00f1:
            r0 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "tbs_download_lock_file"
            r4.append(r5)
            android.content.Context r5 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            android.content.SharedPreferences r5 = r5.mPreferences
            java.lang.String r6 = "tbs_download_version"
            int r5 = r5.getInt(r6, r3)
            r4.append(r5)
            java.lang.String r5 = ".txt"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.content.Context r5 = com.tencent.smtt.sdk.TbsDownloader.c
            java.io.FileOutputStream r4 = com.tencent.smtt.utils.j.b(r5, r3, r4)
            if (r4 == 0) goto L_0x0148
            android.content.Context r0 = com.tencent.smtt.sdk.TbsDownloader.c
            java.nio.channels.FileLock r0 = com.tencent.smtt.utils.j.a((android.content.Context) r0, (java.io.FileOutputStream) r4)
            if (r0 != 0) goto L_0x0164
            java.lang.String r13 = "TbsDownload"
            java.lang.String r0 = "file lock locked,wx or qq is downloading"
            com.tencent.smtt.utils.TbsLog.i(r13, r0)
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r13 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r0 = -203(0xffffffffffffff35, float:NaN)
            r13.setDownloadInterruptCode(r0)
            java.lang.String r13 = "TbsDownload"
            java.lang.String r0 = "MSG_START_DOWNLOAD_DECOUPLECORE return #1"
        L_0x0144:
            com.tencent.smtt.utils.TbsLog.i(r13, r0)
            return
        L_0x0148:
            android.content.Context r5 = com.tencent.smtt.sdk.TbsDownloader.c
            boolean r5 = com.tencent.smtt.utils.j.a((android.content.Context) r5)
            if (r5 == 0) goto L_0x0164
            android.content.Context r13 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r13 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)
            r0 = -204(0xffffffffffffff34, float:NaN)
            r13.setDownloadInterruptCode(r0)
            java.lang.String r13 = "TbsDownload"
            java.lang.String r0 = "MSG_START_DOWNLOAD_DECOUPLECORE return #2"
            goto L_0x0144
        L_0x0164:
            int r5 = r13.arg1
            if (r5 != r2) goto L_0x016a
            r5 = 1
            goto L_0x016b
        L_0x016a:
            r5 = 0
        L_0x016b:
            android.content.Context r6 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)
            int r7 = r13.what
            if (r1 != r7) goto L_0x0179
            r7 = 1
            goto L_0x017a
        L_0x0179:
            r7 = 0
        L_0x017a:
            boolean r7 = com.tencent.smtt.sdk.TbsDownloader.c(r3, r5, r7)
            r8 = 110(0x6e, float:1.54E-43)
            if (r7 == 0) goto L_0x01d4
            if (r5 == 0) goto L_0x01af
            com.tencent.smtt.sdk.am r7 = com.tencent.smtt.sdk.am.a()
            android.content.Context r9 = com.tencent.smtt.sdk.TbsDownloader.c
            android.content.Context r10 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)
            android.content.SharedPreferences r10 = r10.mPreferences
            java.lang.String r11 = "tbs_download_version"
            int r10 = r10.getInt(r11, r3)
            boolean r7 = r7.a((android.content.Context) r9, (int) r10)
            if (r7 == 0) goto L_0x01af
            com.tencent.smtt.sdk.TbsListener r13 = com.tencent.smtt.sdk.QbSdk.m
            r1 = 122(0x7a, float:1.71E-43)
            r13.onDownloadFinish(r1)
            r13 = -213(0xffffffffffffff2b, float:NaN)
            r6.setDownloadInterruptCode(r13)
            goto L_0x01d9
        L_0x01af:
            android.content.SharedPreferences r6 = r6.mPreferences
            java.lang.String r7 = "tbs_needdownload"
            boolean r6 = r6.getBoolean(r7, r3)
            if (r6 == 0) goto L_0x01d4
            android.content.Context r6 = com.tencent.smtt.sdk.TbsDownloader.c
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)
            r7 = -215(0xffffffffffffff29, float:NaN)
            r6.setDownloadInterruptCode(r7)
            com.tencent.smtt.sdk.ag r6 = com.tencent.smtt.sdk.TbsDownloader.g
            int r13 = r13.what
            if (r1 != r13) goto L_0x01cf
            goto L_0x01d0
        L_0x01cf:
            r2 = 0
        L_0x01d0:
            r6.b(r5, r2)
            goto L_0x01d9
        L_0x01d4:
            com.tencent.smtt.sdk.TbsListener r13 = com.tencent.smtt.sdk.QbSdk.m
            r13.onDownloadFinish(r8)
        L_0x01d9:
            java.lang.String r13 = "TbsDownload"
            java.lang.String r1 = "------freeFileLock called :"
            com.tencent.smtt.utils.TbsLog.i(r13, r1)
            com.tencent.smtt.utils.j.a((java.nio.channels.FileLock) r0, (java.io.FileOutputStream) r4)
        L_0x01e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.handleMessage(android.os.Message):void");
    }
}
