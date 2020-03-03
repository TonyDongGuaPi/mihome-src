package com.alipay.mobile.common.logging.appender;

import android.util.Log;
import android.util.Pair;
import com.alibaba.android.arouter.utils.Consts;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.util.FileUtil;
import com.alipay.mobile.common.logging.util.HybridEncryption;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.taobao.weex.annotation.JSMethod;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class ExternalFileAppender extends FileAppender {

    /* renamed from: a  reason: collision with root package name */
    private File f956a;
    private File b;
    private File c;
    private long d;
    private long e;
    private boolean f;
    private boolean g;
    private StringBuilder h = new StringBuilder(9216);
    private Comparator<File> i = new Comparator<File>() {
        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };

    public ExternalFileAppender(LogContext logContext, String str, long j, long j2) {
        super(logContext, str);
        this.d = j;
        this.e = j2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:19|20|21|22|23|24|25) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00a8 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00b7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File getFile() {
        /*
            r6 = this;
            com.alipay.mobile.common.logging.api.ProcessInfo r0 = com.alipay.mobile.common.logging.api.LoggerFactory.getProcessInfo()
            java.lang.String r0 = r0.getProcessName()
            r1 = 58
            r2 = 45
            java.lang.String r0 = r0.replace(r1, r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = r6.d
            long r2 = r2 / r4
            long r4 = r6.d
            long r2 = r2 * r4
            r1.append(r2)
            r2 = 95
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "-V2"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.io.File r1 = r6.f956a
            if (r1 == 0) goto L_0x004c
            java.io.File r1 = r6.f956a
            boolean r1 = r1.exists()
            if (r1 == 0) goto L_0x004c
            java.io.File r1 = r6.f956a
            java.lang.String r1 = r1.getName()
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x00c5
        L_0x004c:
            java.lang.String r1 = "ExternalFile"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "checkAndRollFile:"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.i(r1, r2)
            boolean r1 = com.alipay.mobile.common.logging.util.LoggingUtil.isOfflineForExternalFile()
            if (r1 != 0) goto L_0x0083
            java.io.File r1 = r6.f956a
            if (r1 == 0) goto L_0x0083
            java.io.File r1 = r6.f956a
            boolean r1 = r1.exists()
            if (r1 == 0) goto L_0x0083
            java.io.File r1 = r6.f956a
            long r1 = r1.length()
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0083
            r6.a()
        L_0x0083:
            java.io.File r1 = r6.b()
            if (r1 != 0) goto L_0x00a5
            java.lang.String r0 = "ExternalFile"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "currentLogDir is NULl with "
            r1.append(r2)
            java.lang.String r2 = r6.getLogCategory()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r0, r1)
            r0 = 0
            return r0
        L_0x00a5:
            r6.a(r1)     // Catch:{ Throwable -> 0x00a8 }
        L_0x00a8:
            android.content.Context r2 = r6.getContext()     // Catch:{ Throwable -> 0x00b7 }
            java.lang.String r3 = r6.getLogCategory()     // Catch:{ Throwable -> 0x00b7 }
            java.io.File r2 = r2.getExternalFilesDir(r3)     // Catch:{ Throwable -> 0x00b7 }
            r6.a(r2)     // Catch:{ Throwable -> 0x00b7 }
        L_0x00b7:
            java.io.File r2 = r6.c()     // Catch:{ Throwable -> 0x00be }
            r6.a(r2)     // Catch:{ Throwable -> 0x00be }
        L_0x00be:
            java.io.File r2 = new java.io.File
            r2.<init>(r1, r0)
            r6.f956a = r2
        L_0x00c5:
            java.io.File r0 = r6.f956a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.appender.ExternalFileAppender.getFile():java.io.File");
    }

    /* access modifiers changed from: protected */
    public void appendLogEvent(LogEvent logEvent) {
        StringBuilder sb = this.h;
        sb.append(logEvent);
        sb.append(Consts.c);
        if (this.h.length() > 8192) {
            appendEncryptContent();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void flush() {
        super.flush();
        if (this.h.length() > 0) {
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            traceLogger.verbose("ExternalFile", getLogCategory() + " appender flush: " + this.h.length());
        }
        appendEncryptContent();
    }

    /* access modifiers changed from: protected */
    public void destroy() {
        super.destroy();
        appendEncryptContent();
    }

    /* access modifiers changed from: protected */
    public void appendEncryptContent() {
        byte[] bArr;
        if (this.h.length() != 0) {
            try {
                bArr = this.h.toString().getBytes("UTF-8");
            } catch (Throwable th) {
                if (!this.f) {
                    this.f = true;
                    Log.e("ExternalFile", "appendEncryptContent", th);
                }
                bArr = null;
            }
            Pair<String, String> encrypt = HybridEncryption.getInstance().encrypt(bArr);
            StringBuilder sb = new StringBuilder("\r\n");
            if (encrypt != null) {
                sb.append("[seed]");
                sb.append((String) encrypt.first);
                sb.append("[seed]");
                sb.append((String) encrypt.second);
                sb.append("\r\n");
            } else if (!this.g) {
                this.g = true;
                Log.e("ExternalFile", "HybridEncryption.encrypt occured error");
            }
            onAppend(sb.toString());
            this.h.setLength(0);
        }
    }

    private void a(File file) {
        if (file != null && file.isDirectory()) {
            File[] fileArr = null;
            try {
                fileArr = file.listFiles();
            } catch (Throwable unused) {
            }
            if (fileArr != null) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = currentTimeMillis - this.e;
                long j2 = currentTimeMillis + this.e;
                for (File file2 : fileArr) {
                    if (file2 != null && file2.exists() && file2.isFile()) {
                        try {
                            long parseLong = Long.parseLong(file2.getName().split(JSMethod.NOT_SET)[0]);
                            if (parseLong < j || parseLong > j2) {
                                file2.delete();
                                Log.e("ExternalFile", "cleanExpiresFile:" + file2.getName() + " too old");
                            }
                        } catch (Throwable th) {
                            Log.e("ExternalFile", file2.getName(), th);
                        }
                    }
                }
                if (FileUtil.getFolderSize(file) > 52428800) {
                    File[] listFiles = file.listFiles();
                    Arrays.sort(listFiles, this.i);
                    for (int i2 = 0; i2 < (listFiles.length + 1) / 2; i2++) {
                        File file3 = listFiles[i2];
                        if (file3 != null && file3.exists() && file3.isFile()) {
                            try {
                                file3.delete();
                                Log.e("ExternalFile", "cleanExpiresFile:" + file3.getName() + " too large");
                            } catch (Throwable th2) {
                                Log.e("ExternalFile", file3.getName(), th2);
                            }
                        }
                    }
                }
            }
        }
    }

    private void a() {
        if (this.f956a != null) {
            try {
                File file = new File(c(), this.f956a.getName());
                if (this.f956a.length() > 0 && this.f956a.length() != file.length()) {
                    FileUtil.copyFile(this.f956a, file);
                }
            } catch (Throwable th) {
                Log.e("ExternalFile", "backupCurrentFileCore", th);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005d A[SYNTHETIC, Splitter:B:22:0x005d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void backupCurrentFile(boolean r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            super.backupCurrentFile(r12)     // Catch:{ all -> 0x00c9 }
            boolean r0 = com.alipay.mobile.common.logging.util.LoggingUtil.isOfflineForExternalFile()     // Catch:{ all -> 0x00c9 }
            if (r0 == 0) goto L_0x0013
            java.lang.String r12 = "ExternalFile"
            java.lang.String r0 = "backupCurrentFile: need not backup in offline"
            android.util.Log.i(r12, r0)     // Catch:{ all -> 0x00c9 }
            monitor-exit(r11)
            return
        L_0x0013:
            com.alipay.mobile.common.logging.api.trace.TraceLogger r0 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ all -> 0x00c9 }
            java.lang.String r1 = "ExternalFile"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c9 }
            r2.<init>()     // Catch:{ all -> 0x00c9 }
            java.lang.String r3 = "backupCurrentFile: need to backup, isBackupAll="
            r2.append(r3)     // Catch:{ all -> 0x00c9 }
            r2.append(r12)     // Catch:{ all -> 0x00c9 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c9 }
            r0.info(r1, r2)     // Catch:{ all -> 0x00c9 }
            java.io.File r0 = r11.f956a     // Catch:{ all -> 0x00c9 }
            r1 = 0
            if (r0 == 0) goto L_0x0052
            java.io.File r0 = r11.f956a     // Catch:{ all -> 0x00c9 }
            boolean r0 = r0.exists()     // Catch:{ all -> 0x00c9 }
            if (r0 == 0) goto L_0x0052
            java.io.File r0 = r11.f956a     // Catch:{ all -> 0x00c9 }
            boolean r0 = r0.isFile()     // Catch:{ all -> 0x00c9 }
            if (r0 == 0) goto L_0x0052
            java.io.File r0 = r11.f956a     // Catch:{ all -> 0x00c9 }
            long r3 = r0.length()     // Catch:{ all -> 0x00c9 }
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x004e
            goto L_0x0052
        L_0x004e:
            r11.a()     // Catch:{ all -> 0x00c9 }
            goto L_0x0059
        L_0x0052:
            java.lang.String r0 = "ExternalFile"
            java.lang.String r3 = "backupCurrentFile: no target log file"
            android.util.Log.e(r0, r3)     // Catch:{ all -> 0x00c9 }
        L_0x0059:
            if (r12 != 0) goto L_0x005d
            monitor-exit(r11)
            return
        L_0x005d:
            java.io.File r12 = r11.b()     // Catch:{ Throwable -> 0x00bf }
            java.io.File[] r12 = r12.listFiles()     // Catch:{ Throwable -> 0x00bf }
            int r0 = r12.length     // Catch:{ Throwable -> 0x00bf }
            r3 = 0
        L_0x0067:
            if (r3 >= r0) goto L_0x00c7
            r4 = r12[r3]     // Catch:{ Throwable -> 0x00bf }
            if (r4 == 0) goto L_0x00bc
            boolean r5 = r4.exists()     // Catch:{ Throwable -> 0x00bf }
            if (r5 == 0) goto L_0x00bc
            boolean r5 = r4.isFile()     // Catch:{ Throwable -> 0x00bf }
            if (r5 == 0) goto L_0x00bc
            long r5 = r4.length()     // Catch:{ Throwable -> 0x00bf }
            int r7 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r7 == 0) goto L_0x00bc
            java.io.File r5 = r11.f956a     // Catch:{ Throwable -> 0x00bf }
            boolean r5 = r4.equals(r5)     // Catch:{ Throwable -> 0x00bf }
            if (r5 == 0) goto L_0x008a
            goto L_0x00bc
        L_0x008a:
            java.io.File r5 = new java.io.File     // Catch:{ Throwable -> 0x00bf }
            java.io.File r6 = r11.c()     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r7 = r4.getName()     // Catch:{ Throwable -> 0x00bf }
            r5.<init>(r6, r7)     // Catch:{ Throwable -> 0x00bf }
            boolean r6 = r5.exists()     // Catch:{ Throwable -> 0x00bf }
            if (r6 == 0) goto L_0x00b0
            boolean r6 = r5.isFile()     // Catch:{ Throwable -> 0x00bf }
            if (r6 == 0) goto L_0x00b0
            long r6 = r5.length()     // Catch:{ Throwable -> 0x00bf }
            long r8 = r4.length()     // Catch:{ Throwable -> 0x00bf }
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x00b0
            goto L_0x00bc
        L_0x00b0:
            com.alipay.mobile.common.logging.util.FileUtil.copyFile(r4, r5)     // Catch:{ Throwable -> 0x00b4 }
            goto L_0x00bc
        L_0x00b4:
            r4 = move-exception
            java.lang.String r5 = "ExternalFile"
            java.lang.String r6 = "backupCurrentFile"
            android.util.Log.e(r5, r6, r4)     // Catch:{ Throwable -> 0x00bf }
        L_0x00bc:
            int r3 = r3 + 1
            goto L_0x0067
        L_0x00bf:
            r12 = move-exception
            java.lang.String r0 = "ExternalFile"
            java.lang.String r1 = "backupCurrentFile"
            android.util.Log.e(r0, r1, r12)     // Catch:{ all -> 0x00c9 }
        L_0x00c7:
            monitor-exit(r11)
            return
        L_0x00c9:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.appender.ExternalFileAppender.backupCurrentFile(boolean):void");
    }

    private File b() {
        if (this.b == null) {
            try {
                this.b = LoggingUtil.getStorageFilesDir(getContext(), getLogCategory());
            } catch (Throwable th) {
                Log.e("ExternalFile", "getCurrentLogsDir", th);
            }
        }
        try {
            if (this.b != null && !this.b.exists()) {
                this.b.mkdirs();
            }
        } catch (Throwable th2) {
            Log.e("ExternalFile", "getCurrentLogsDir", th2);
        }
        return this.b;
    }

    private File c() {
        if (this.c == null) {
            try {
                File file = new File(LoggingUtil.getCommonExternalStorageDir(), getContext().getPackageName());
                this.c = new File(file, getLogCategory() + "_bak");
            } catch (Throwable th) {
                Log.e("ExternalFile", "getBackupLogsDir", th);
            }
        }
        try {
            if (this.c != null && !this.c.exists()) {
                this.c.mkdirs();
            }
        } catch (Throwable th2) {
            Log.e("ExternalFile", "getBackupLogsDir", th2);
        }
        return this.c;
    }
}
