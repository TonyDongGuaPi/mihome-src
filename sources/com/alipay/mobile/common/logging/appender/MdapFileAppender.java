package com.alipay.mobile.common.logging.appender;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.EventCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.alipay.mobile.common.logging.util.FileUtil;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.taobao.weex.annotation.JSMethod;
import java.io.File;

public class MdapFileAppender extends FileAppender {

    /* renamed from: a  reason: collision with root package name */
    private static final String f958a = "MdapFileAppender";
    private static final String f = ("mdap" + File.separatorChar + EventCategory.CATEGORY_UPLOAD);
    private int b;
    private boolean c = true;
    private int d;
    private StringBuffer e = new StringBuffer();

    public MdapFileAppender(LogContext logContext, String str) {
        super(logContext, str);
    }

    /* access modifiers changed from: protected */
    public void appendLogEvent(LogEvent logEvent) {
        if (this.c) {
            this.c = false;
            try {
                String readFile = FileUtil.readFile(getFile());
                if (!TextUtils.isEmpty(readFile)) {
                    this.b = readFile.split("\\$\\$").length;
                }
            } catch (Throwable th) {
                Log.e(f958a, getLogCategory() + ": " + th.getMessage());
            }
        }
        this.e.append(logEvent);
        this.d++;
        if (!LoggerFactory.getProcessInfo().isMainProcess() || this.d >= 10 || LoggingUtil.isOfflineMode() || LogStrategyManager.getInstance().isLogUpload(getLogCategory(), this.d)) {
            onAppend(this.e.toString());
            this.b += this.d;
            this.e.setLength(0);
            this.d = 0;
        }
        if (LogStrategyManager.getInstance().isLogUpload(getLogCategory(), this.b)) {
            Log.w(f958a, "upload: " + getLogCategory());
            upload();
            this.b = 0;
        }
    }

    /* access modifiers changed from: protected */
    public File getFile() {
        if (this.storageFile == null && LoggingUtil.isOfflineMode()) {
            File file = null;
            try {
                file = getContext().getExternalFilesDir("mdap");
            } catch (Throwable unused) {
            }
            if (file != null) {
                if (!file.exists()) {
                    file.mkdirs();
                }
                String logCategory = getLogCategory();
                this.storageFile = new File(file, this.processName + JSMethod.NOT_SET + logCategory);
                return this.storageFile;
            }
        }
        return super.getFile();
    }

    /* access modifiers changed from: protected */
    public File getUploadFile() {
        File file = new File(getContext().getFilesDir(), f);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, System.currentTimeMillis() + JSMethod.NOT_SET + getFile().getName());
    }

    /* access modifiers changed from: protected */
    public File getBackupFile() {
        File file = new File(getContext().getExternalFilesDir("mdap"), EventCategory.CATEGORY_UPLOAD);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, System.currentTimeMillis() + JSMethod.NOT_SET + getFile().getName());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:2|3|(2:5|6)|7|8|9|11|12|17) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void upload() {
        /*
            r4 = this;
            int r0 = r4.b
            if (r0 <= 0) goto L_0x003b
            boolean r0 = com.alipay.mobile.common.logging.util.LoggingUtil.isOfflineMode()     // Catch:{ Throwable -> 0x002d }
            if (r0 == 0) goto L_0x0015
            java.io.File r0 = r4.getFile()     // Catch:{ Throwable -> 0x0015 }
            java.io.File r1 = r4.getBackupFile()     // Catch:{ Throwable -> 0x0015 }
            com.alipay.mobile.common.logging.util.FileUtil.copyFile(r0, r1)     // Catch:{ Throwable -> 0x0015 }
        L_0x0015:
            java.io.File r0 = r4.getFile()     // Catch:{ Throwable -> 0x0020 }
            java.io.File r1 = r4.getUploadFile()     // Catch:{ Throwable -> 0x0020 }
            com.alipay.mobile.common.logging.util.FileUtil.moveFile(r0, r1)     // Catch:{ Throwable -> 0x0020 }
        L_0x0020:
            r0 = 0
            r4.b = r0     // Catch:{ Throwable -> 0x002d }
            com.alipay.mobile.common.logging.api.LogContext r0 = r4.context     // Catch:{ Throwable -> 0x002d }
            java.lang.String r1 = r4.getLogCategory()     // Catch:{ Throwable -> 0x002d }
            r0.upload(r1)     // Catch:{ Throwable -> 0x002d }
            goto L_0x003b
        L_0x002d:
            r0 = move-exception
            com.alipay.mobile.common.logging.api.trace.TraceLogger r1 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()
            java.lang.String r2 = f958a
            java.lang.String r3 = r4.getLogCategory()
            r1.error(r2, r3, r0)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.appender.MdapFileAppender.upload():void");
    }

    /* access modifiers changed from: protected */
    public synchronized void flush() {
        super.flush();
        if (this.d > 0) {
            LoggerFactory.getTraceLogger().info(f958a, getLogCategory() + " appender flush: " + this.d);
        }
        if (this.e.length() > 0) {
            onAppend(this.e.toString());
            this.e.setLength(0);
            this.b += this.d;
            this.d = 0;
        }
    }
}
