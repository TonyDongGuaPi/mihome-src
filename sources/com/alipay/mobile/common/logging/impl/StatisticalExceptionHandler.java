package com.alipay.mobile.common.logging.impl;

import android.content.Context;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.UncaughtExceptionCallback;
import com.alipay.mobile.common.logging.api.monitor.ExceptionID;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.render.ExceptionRender;
import com.alipay.mobile.common.nativecrash.CrashCombineUtils;
import com.alipay.mobile.common.nativecrash.NativeCrashHandler;
import com.alipay.mobile.common.nativecrash.NativeCrashHandlerApi;
import java.lang.Thread;

public class StatisticalExceptionHandler implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f963a = "StatisticalExceptionHandler";
    private static StatisticalExceptionHandler b;
    private Thread.UncaughtExceptionHandler c;
    private UncaughtExceptionCallback d;
    private Context e;
    private Runnable f;
    private boolean g;

    private StatisticalExceptionHandler(final Context context) {
        this.e = context;
        this.f = new Runnable() {
            public void run() {
                NativeCrashHandler.initialize(context);
            }
        };
    }

    public static synchronized void createInstance(Context context) {
        synchronized (StatisticalExceptionHandler.class) {
            if (b == null) {
                b = new StatisticalExceptionHandler(context);
            }
        }
    }

    public static synchronized StatisticalExceptionHandler getInstance() {
        StatisticalExceptionHandler statisticalExceptionHandler;
        synchronized (StatisticalExceptionHandler.class) {
            statisticalExceptionHandler = b;
        }
        return statisticalExceptionHandler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setup() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.g     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 1
            r2.g = r0     // Catch:{ all -> 0x0037 }
            java.lang.Thread$UncaughtExceptionHandler r0 = java.lang.Thread.getDefaultUncaughtExceptionHandler()     // Catch:{ all -> 0x0037 }
            r2.c = r0     // Catch:{ all -> 0x0037 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r2)     // Catch:{ all -> 0x0037 }
            android.os.Looper r0 = android.os.Looper.myLooper()     // Catch:{ all -> 0x0037 }
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0037 }
            if (r0 != r1) goto L_0x0023
            android.content.Context r0 = r2.e     // Catch:{ all -> 0x0037 }
            com.alipay.mobile.common.nativecrash.NativeCrashHandler.initialize(r0)     // Catch:{ all -> 0x0037 }
            goto L_0x0035
        L_0x0023:
            java.lang.Runnable r0 = r2.f     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0035
            android.os.Handler r0 = new android.os.Handler     // Catch:{ all -> 0x0037 }
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0037 }
            r0.<init>(r1)     // Catch:{ all -> 0x0037 }
            java.lang.Runnable r1 = r2.f     // Catch:{ all -> 0x0037 }
            r0.post(r1)     // Catch:{ all -> 0x0037 }
        L_0x0035:
            monitor-exit(r2)
            return
        L_0x0037:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.impl.StatisticalExceptionHandler.setup():void");
    }

    public synchronized void takedown() {
        this.g = false;
        Thread.setDefaultUncaughtExceptionHandler(this.c);
    }

    public void setUncaughtExceptionCallback(UncaughtExceptionCallback uncaughtExceptionCallback) {
        this.d = uncaughtExceptionCallback;
    }

    public UncaughtExceptionCallback getUncaughtExceptionCallback() {
        return this.d;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Throwable th2;
        boolean z;
        if ("NegligibleThrowable".equals(th.getMessage())) {
            z = true;
            th2 = th.getCause();
        } else {
            z = false;
            th2 = th;
        }
        if (LoggerFactory.getProcessInfo().isMainProcess()) {
            if (th2 != null && !z) {
                LoggerFactory.getMonitorLogger().crash(th2, (String) null);
            }
        } else if (!LoggerFactory.getProcessInfo().isPushProcess() && !LoggerFactory.getProcessInfo().isToolsProcess()) {
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            String str = f963a;
            traceLogger.error(str, "uncaughtException: unknown process " + LoggerFactory.getProcessInfo().getProcessAlias(), th2);
        }
        if (this.c != null) {
            try {
                this.c.uncaughtException(thread, th);
            } catch (Throwable unused) {
            }
        }
    }

    public void handleNativeException(String str, String str2) {
        boolean equalsIgnoreCase = "main".equalsIgnoreCase(Thread.currentThread().getName());
        if (LoggerFactory.getProcessInfo().isMainProcess()) {
            if (equalsIgnoreCase) {
                LoggerFactory.getLogContext().traceNativeCrash(str, str2, false);
            } else {
                a(a(str, str2));
            }
        } else if (LoggerFactory.getProcessInfo().isPushProcess()) {
            a(a(str, str2));
        } else if (LoggerFactory.getProcessInfo().isToolsProcess()) {
            a(str, str2);
        } else {
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            String str3 = f963a;
            traceLogger.error(str3, "handleNativeException, error: unknown process " + LoggerFactory.getProcessInfo().getProcessAlias());
        }
        LoggerFactory.getTraceLogger().error(f963a, "handleNativeException" + ", filePath: " + str + ", callStack: " + str2 + ", process: " + LoggerFactory.getProcessInfo().getProcessAlias() + ", thread: " + Thread.currentThread().getName());
        if (equalsIgnoreCase) {
            LoggerFactory.getLogContext().flush(true);
        }
    }

    private String a(String str, String str2) {
        String UserTrackReport = CrashCombineUtils.UserTrackReport(str, str2);
        CrashCombineUtils.deleteFileByPath(str);
        LogContext logContext = LoggerFactory.getLogContext();
        logContext.syncAppendLogEvent(new LogEvent("crash", (String) null, LogEvent.Level.ERROR, new ExceptionRender(logContext).render(ExceptionID.MONITORPOINT_CLIENTSERR, UserTrackReport, (String) null, false, LoggerFactory.getProcessInfo().getProcessAlias(), Thread.currentThread().getName(), true)));
        return UserTrackReport;
    }

    private void a(String str) {
        NativeCrashHandlerApi.OnNativeCrashUploadListener onNativeCrashUploadListener = NativeCrashHandlerApi.getOnNativeCrashUploadListener();
        if (onNativeCrashUploadListener != null) {
            Log.w(f963a, "OnNativeCrashUploadListener is not null");
            try {
                onNativeCrashUploadListener.onUpload(str);
            } catch (Throwable th) {
                Log.e(f963a, "OnNativeCrashUploadListener", th);
            }
        } else {
            Log.w(f963a, "OnNativeCrashUploadListener is null");
        }
    }
}
