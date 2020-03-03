package com.alipay.mobile.common.logging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.monitor.PerformanceID;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.http.HttpClient;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.alipay.mobile.common.logging.util.FileUtil;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class MdapLogUploadManager {

    /* renamed from: a  reason: collision with root package name */
    private Context f944a;
    private File b;
    private Comparator<File> c = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };

    public MdapLogUploadManager(Context context, ContextInfo contextInfo) {
        this.f944a = context;
        this.b = new File(context.getFilesDir().getAbsolutePath() + "/mdap/upload/");
        if (!this.b.exists()) {
            this.b.mkdirs();
        }
    }

    private void a(Context context, String str) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, str), 1, 1);
        } catch (Throwable th) {
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            traceLogger.error("MdapLogUploadMgr", "setComponentEnabled: " + th.getMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d A[SYNTHETIC, Splitter:B:22:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009c A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009e A[SYNTHETIC, Splitter:B:36:0x009e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void syncLog() {
        /*
            r11 = this;
            monitor-enter(r11)
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0175 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0175 }
            r2.<init>()     // Catch:{ all -> 0x0175 }
            android.content.Context r3 = r11.f944a     // Catch:{ all -> 0x0175 }
            java.io.File r3 = r3.getFilesDir()     // Catch:{ all -> 0x0175 }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x0175 }
            r2.append(r3)     // Catch:{ all -> 0x0175 }
            java.lang.String r3 = "/mdap/"
            r2.append(r3)     // Catch:{ all -> 0x0175 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0175 }
            r1.<init>(r2)     // Catch:{ all -> 0x0175 }
            boolean r2 = com.alipay.mobile.common.logging.util.LoggingUtil.isOfflineMode()     // Catch:{ all -> 0x0175 }
            if (r2 == 0) goto L_0x009a
            android.content.Context r2 = r11.f944a     // Catch:{ Throwable -> 0x0047 }
            java.lang.String r3 = "mdap"
            java.io.File r2 = r2.getExternalFilesDir(r3)     // Catch:{ Throwable -> 0x0047 }
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0042 }
            java.lang.String r3 = "upload"
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0042 }
            boolean r2 = r0.exists()     // Catch:{ Throwable -> 0x0047 }
            if (r2 != 0) goto L_0x0063
            r0.mkdirs()     // Catch:{ Throwable -> 0x0047 }
            goto L_0x0063
        L_0x0042:
            r0 = move-exception
            r10 = r2
            r2 = r0
            r0 = r10
            goto L_0x0048
        L_0x0047:
            r2 = move-exception
        L_0x0048:
            java.lang.String r3 = "MdapLogUploadMgr"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0175 }
            r4.<init>()     // Catch:{ all -> 0x0175 }
            java.lang.String r5 = "syncLog backupLogDir: "
            r4.append(r5)     // Catch:{ all -> 0x0175 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0175 }
            r4.append(r2)     // Catch:{ all -> 0x0175 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0175 }
            android.util.Log.w(r3, r2)     // Catch:{ all -> 0x0175 }
        L_0x0063:
            android.content.Context r2 = r11.f944a     // Catch:{ Throwable -> 0x007e }
            java.lang.String r3 = "mdap"
            java.io.File r2 = r2.getExternalFilesDir(r3)     // Catch:{ Throwable -> 0x007e }
            if (r2 == 0) goto L_0x007c
            boolean r1 = r2.exists()     // Catch:{ Throwable -> 0x0077 }
            if (r1 != 0) goto L_0x007c
            r2.mkdirs()     // Catch:{ Throwable -> 0x0077 }
            goto L_0x007c
        L_0x0077:
            r1 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x007f
        L_0x007c:
            r1 = r2
            goto L_0x009a
        L_0x007e:
            r2 = move-exception
        L_0x007f:
            java.lang.String r3 = "MdapLogUploadMgr"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0175 }
            r4.<init>()     // Catch:{ all -> 0x0175 }
            java.lang.String r5 = "syncLog logFileDir: "
            r4.append(r5)     // Catch:{ all -> 0x0175 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0175 }
            r4.append(r2)     // Catch:{ all -> 0x0175 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0175 }
            android.util.Log.w(r3, r2)     // Catch:{ all -> 0x0175 }
        L_0x009a:
            if (r1 != 0) goto L_0x009e
            monitor-exit(r11)
            return
        L_0x009e:
            java.io.File[] r1 = r1.listFiles()     // Catch:{ Throwable -> 0x0153 }
            if (r1 != 0) goto L_0x00a6
            monitor-exit(r11)
            return
        L_0x00a6:
            com.alipay.mobile.common.logging.api.trace.TraceLogger r2 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r3 = "MdapLogUploadMgr"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0153 }
            r4.<init>()     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r5 = "syncLog: "
            r4.append(r5)     // Catch:{ Throwable -> 0x0153 }
            int r5 = r1.length     // Catch:{ Throwable -> 0x0153 }
            r4.append(r5)     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0153 }
            r2.info(r3, r4)     // Catch:{ Throwable -> 0x0153 }
            int r2 = r1.length     // Catch:{ Throwable -> 0x0153 }
            r3 = 0
        L_0x00c4:
            if (r3 >= r2) goto L_0x0173
            r4 = r1[r3]     // Catch:{ Throwable -> 0x0153 }
            if (r4 == 0) goto L_0x014f
            boolean r5 = r4.exists()     // Catch:{ Throwable -> 0x0153 }
            if (r5 == 0) goto L_0x014f
            boolean r5 = r4.isFile()     // Catch:{ Throwable -> 0x0153 }
            if (r5 == 0) goto L_0x014f
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x0129 }
            java.io.File r6 = r11.b     // Catch:{ Throwable -> 0x0129 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0129 }
            r7.<init>()     // Catch:{ Throwable -> 0x0129 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0129 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0129 }
            java.lang.String r8 = "_"
            r7.append(r8)     // Catch:{ Throwable -> 0x0129 }
            java.lang.String r8 = r4.getName()     // Catch:{ Throwable -> 0x0129 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0129 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0129 }
            r5.<init>(r6, r7)     // Catch:{ Throwable -> 0x0129 }
            boolean r6 = com.alipay.mobile.common.logging.util.LoggingUtil.isOfflineMode()     // Catch:{ Throwable -> 0x0129 }
            if (r6 == 0) goto L_0x0125
            if (r0 == 0) goto L_0x0125
            java.io.File r6 = new java.io.File     // Catch:{ Throwable -> 0x0129 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0129 }
            r7.<init>()     // Catch:{ Throwable -> 0x0129 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0129 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0129 }
            java.lang.String r8 = "_"
            r7.append(r8)     // Catch:{ Throwable -> 0x0129 }
            java.lang.String r8 = r4.getName()     // Catch:{ Throwable -> 0x0129 }
            r7.append(r8)     // Catch:{ Throwable -> 0x0129 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0129 }
            r6.<init>(r0, r7)     // Catch:{ Throwable -> 0x0129 }
            com.alipay.mobile.common.logging.util.FileUtil.copyFile(r4, r6)     // Catch:{ Throwable -> 0x0129 }
        L_0x0125:
            com.alipay.mobile.common.logging.util.FileUtil.moveFile(r4, r5)     // Catch:{ Throwable -> 0x0129 }
            goto L_0x014f
        L_0x0129:
            r5 = move-exception
            com.alipay.mobile.common.logging.api.trace.TraceLogger r6 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r7 = "MdapLogUploadMgr"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0153 }
            r8.<init>()     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r4 = r4.getName()     // Catch:{ Throwable -> 0x0153 }
            r8.append(r4)     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r4 = ", syncLog: "
            r8.append(r4)     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r4 = r5.getMessage()     // Catch:{ Throwable -> 0x0153 }
            r8.append(r4)     // Catch:{ Throwable -> 0x0153 }
            java.lang.String r4 = r8.toString()     // Catch:{ Throwable -> 0x0153 }
            r6.error((java.lang.String) r7, (java.lang.String) r4)     // Catch:{ Throwable -> 0x0153 }
        L_0x014f:
            int r3 = r3 + 1
            goto L_0x00c4
        L_0x0153:
            r0 = move-exception
            com.alipay.mobile.common.logging.api.trace.TraceLogger r1 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ all -> 0x0175 }
            java.lang.String r2 = "MdapLogUploadMgr"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0175 }
            r3.<init>()     // Catch:{ all -> 0x0175 }
            java.lang.String r4 = "syncLog, move fail: "
            r3.append(r4)     // Catch:{ all -> 0x0175 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0175 }
            r3.append(r0)     // Catch:{ all -> 0x0175 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0175 }
            r1.error((java.lang.String) r2, (java.lang.String) r0)     // Catch:{ all -> 0x0175 }
        L_0x0173:
            monitor-exit(r11)
            return
        L_0x0175:
            r0 = move-exception
            monitor-exit(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.MdapLogUploadManager.syncLog():void");
    }

    public synchronized void uploadLog(String str) {
        String str2;
        int i;
        try {
            a();
        } catch (Throwable th) {
            Log.w("MdapLogUploadMgr", "cleanExpiresFile: " + th.getMessage());
        }
        long j = 0;
        File[] listFiles = this.b.listFiles();
        int i2 = 0;
        if (listFiles != null) {
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            traceLogger.info("MdapLogUploadMgr", "uploadLog: " + listFiles.length);
            str2 = "";
            int i3 = 0;
            long j2 = 0;
            int i4 = 0;
            while (i2 < listFiles.length) {
                File file = listFiles[i2];
                if (file != null && file.exists() && file.isFile()) {
                    try {
                        String isLogSend = LogStrategyManager.getInstance().isLogSend(file.getName(), str);
                        if (isLogSend != null) {
                            a(file, isLogSend);
                            TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
                            traceLogger2.info("MdapLogUploadMgr", "uploadLog: " + file.getName());
                            i3++;
                            j2 += file.length();
                        }
                    } catch (Throwable th2) {
                        LoggerFactory.getTraceLogger().error("MdapLogUploadMgr", th2.getMessage());
                        i4++;
                        str2 = th2.getMessage();
                    }
                }
                i2++;
            }
            i2 = i3;
            i = i4;
            j = j2;
        } else {
            str2 = "";
            i = 0;
        }
        if (i2 > 0) {
            if (str == null || !str.equals(PerformanceID.MONITORPOINT_FOOTPRINT.getDes())) {
                HashMap hashMap = new HashMap();
                hashMap.put("realCount", String.valueOf(i2));
                hashMap.put("realLength", String.valueOf(j));
                hashMap.put("errorCount", String.valueOf(i));
                hashMap.put("errorMessage", str2);
                LoggerFactory.getMonitorLogger().footprint("MdapLog", "Upload", str, "ProcessName", LoggerFactory.getProcessInfo().getProcessAlias(), hashMap);
            }
            TraceLogger traceLogger3 = LoggerFactory.getTraceLogger();
            traceLogger3.info("MdapLogUploadMgr", "uploadLog, realCount: " + i2);
        }
    }

    private void a() {
        if (FileUtil.getFolderSize(this.b) > 52428800) {
            File[] listFiles = this.b.listFiles();
            Arrays.sort(listFiles, this.c);
            int length = (listFiles.length + 1) / 2;
            for (int i = 0; i < length; i++) {
                try {
                    File file = listFiles[i];
                    if (file != null && file.exists() && file.isFile()) {
                        file.delete();
                        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
                        traceLogger.info("MdapLogUploadMgr", "cleanExpiresFile: " + file.getAbsolutePath() + " too large or too old, total " + length);
                    }
                } catch (Throwable th) {
                    Log.e("MdapLogUploadMgr", "cleanExpiresFile: " + th.getMessage());
                }
            }
        }
    }

    private void a(File file, String str) {
        if (file != null) {
            try {
                String readFile = FileUtil.readFile(file);
                if (!TextUtils.isEmpty(readFile)) {
                    String logHost = LoggerFactory.getLogContext().getLogHost();
                    if (!TextUtils.isEmpty(logHost)) {
                        if (LoggingUtil.isOfflineMode()) {
                            a(this.f944a, "com.alipay.mobile.logmonitor.ClientMonitorExtReceiver");
                            Intent intent = new Intent(this.f944a.getPackageName() + ".monitor.action.UPLOAD_MDAP_LOG");
                            intent.setPackage(this.f944a.getPackageName());
                            intent.putExtra("file", file.getName());
                            intent.putExtra("content", readFile);
                            try {
                                this.f944a.sendBroadcast(intent);
                            } catch (Throwable unused) {
                            }
                        }
                        HttpClient httpClient = new HttpClient(logHost + "/loggw/logUpload.do", this.f944a);
                        if (httpClient.synchronousRequestForLog(readFile, str) != null) {
                            int responseCode = httpClient.getResponseCode();
                            String responseString = httpClient.getResponseString();
                            httpClient.closeStreamForNextExecute();
                            if (responseCode != 200) {
                                if (str == null || !str.equals(PerformanceID.MONITORPOINT_FOOTPRINT.getDes())) {
                                    HashMap hashMap = new HashMap();
                                    hashMap.put(TbsReaderView.KEY_FILE_PATH, file.getAbsolutePath());
                                    hashMap.put("fileLength", String.valueOf(file.length()));
                                    LoggerFactory.getMonitorLogger().footprint("MdapLog", "Upload", str, "ResponseCode", String.valueOf(responseCode), hashMap);
                                }
                                TraceLogger traceLogger = LoggerFactory.getTraceLogger();
                                traceLogger.error("MdapLogUploadMgr", "uploadFile, ResponseCode: " + responseCode);
                                throw new IllegalStateException("mdapUpload: responseCode is " + responseCode);
                            }
                            file.delete();
                            if (responseString != null) {
                                String str2 = null;
                                try {
                                    int indexOf = responseString.indexOf("logSwitch=");
                                    if (indexOf > 0) {
                                        str2 = responseString.substring(indexOf + 10);
                                    }
                                } catch (Throwable unused2) {
                                }
                                Log.v("MdapLogUploadMgr", "mdapUpload: uploadFile: " + file.getName() + " success, logswitch=" + str2);
                                return;
                            }
                            return;
                        }
                        httpClient.closeStreamForNextExecute();
                        throw new IllegalStateException("mdapUpload: httpResponse is NULL");
                    }
                    throw new IllegalStateException("mdapUpload: logHost is empty");
                }
                file.delete();
                throw new IllegalStateException("mdapUpload: file content is empty");
            } catch (Throwable th) {
                throw new IllegalStateException(th);
            }
        } else {
            throw new IllegalStateException("mdapUpload: file object is NULL");
        }
    }
}
