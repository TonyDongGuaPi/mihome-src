package com.alipay.mobile.common.logging.api;

import android.content.Context;
import android.util.Log;
import com.alipay.mobile.common.logging.api.behavor.Behavor;
import com.alipay.mobile.common.logging.api.behavor.BehavorLogger;
import com.alipay.mobile.common.logging.api.monitor.ExceptionID;
import com.alipay.mobile.common.logging.api.monitor.MonitorLogger;
import com.alipay.mobile.common.logging.api.monitor.Performance;
import com.alipay.mobile.common.logging.api.monitor.PerformanceID;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoggerFactory {

    /* renamed from: a  reason: collision with root package name */
    private static ProcessInfo f947a = new NullProcessInfo();
    private static LogContext b = null;
    private static TraceLogger c = null;
    private static MonitorLogger d = null;
    private static BehavorLogger e = null;
    private static AtomicBoolean f = new AtomicBoolean(false);
    private static final String g = LoggerFactory.class.getSimpleName();

    public static synchronized BehavorLogger getBehavorLogger() {
        synchronized (LoggerFactory.class) {
            if (f != null) {
                if (f.get()) {
                    BehavorLogger behavorLogger = e;
                    return behavorLogger;
                }
            }
            NullBehavorLogger nullBehavorLogger = new NullBehavorLogger();
            return nullBehavorLogger;
        }
    }

    public static synchronized TraceLogger getTraceLogger() {
        synchronized (LoggerFactory.class) {
            if (f != null) {
                if (f.get()) {
                    TraceLogger traceLogger = c;
                    return traceLogger;
                }
            }
            NullTraceLogger nullTraceLogger = new NullTraceLogger();
            return nullTraceLogger;
        }
    }

    public static synchronized MonitorLogger getMonitorLogger() {
        synchronized (LoggerFactory.class) {
            if (f != null) {
                if (f.get()) {
                    MonitorLogger monitorLogger = d;
                    return monitorLogger;
                }
            }
            NullMonitorLogger nullMonitorLogger = new NullMonitorLogger();
            return nullMonitorLogger;
        }
    }

    public static ProcessInfo getProcessInfo() {
        return f947a;
    }

    public static void attachProcessInfo(ProcessInfo processInfo) {
        f947a = processInfo;
    }

    public static synchronized LogContext getLogContext() {
        synchronized (LoggerFactory.class) {
            if (f != null) {
                if (f.get()) {
                    LogContext logContext = b;
                    return logContext;
                }
            }
            NullLogContext nullLogContext = new NullLogContext();
            return nullLogContext;
        }
    }

    public static synchronized void attachLogContext(LogContext logContext) {
        synchronized (LoggerFactory.class) {
            b = logContext;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void bind(com.alipay.mobile.common.logging.api.trace.TraceLogger r3, com.alipay.mobile.common.logging.api.behavor.BehavorLogger r4, com.alipay.mobile.common.logging.api.monitor.MonitorLogger r5) {
        /*
            java.lang.Class<com.alipay.mobile.common.logging.api.LoggerFactory> r0 = com.alipay.mobile.common.logging.api.LoggerFactory.class
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicBoolean r1 = f     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x001e
            java.util.concurrent.atomic.AtomicBoolean r1 = f     // Catch:{ all -> 0x0020 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0010
            goto L_0x001e
        L_0x0010:
            java.util.concurrent.atomic.AtomicBoolean r1 = f     // Catch:{ all -> 0x0020 }
            r2 = 1
            r1.set(r2)     // Catch:{ all -> 0x0020 }
            e = r4     // Catch:{ all -> 0x0020 }
            d = r5     // Catch:{ all -> 0x0020 }
            c = r3     // Catch:{ all -> 0x0020 }
            monitor-exit(r0)
            return
        L_0x001e:
            monitor-exit(r0)
            return
        L_0x0020:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.api.LoggerFactory.bind(com.alipay.mobile.common.logging.api.trace.TraceLogger, com.alipay.mobile.common.logging.api.behavor.BehavorLogger, com.alipay.mobile.common.logging.api.monitor.MonitorLogger):void");
    }

    public static synchronized void init(Context context) {
        synchronized (LoggerFactory.class) {
            try {
                context.getClassLoader().loadClass("com.alipay.mobile.common.logging.LoggerFactoryBinder").getDeclaredMethod(LoginBindBaseWebActivity.SOURCE_BIND, new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            } catch (Throwable th) {
                Log.e(g, "init", th);
            }
        }
        return;
    }

    /* access modifiers changed from: private */
    public static void a() {
        Log.e(g, "need invoke bind before use");
    }

    private static class NullBehavorLogger implements BehavorLogger {
        private NullBehavorLogger() {
        }

        public void click(Behavor behavor) {
            LoggerFactory.a();
        }

        public void openPage(Behavor behavor) {
            LoggerFactory.a();
        }

        public void longClick(Behavor behavor) {
            LoggerFactory.a();
        }

        public void submit(Behavor behavor) {
            LoggerFactory.a();
        }

        public void slide(Behavor behavor) {
            LoggerFactory.a();
        }

        public void autoOpenPage(Behavor behavor) {
            LoggerFactory.a();
        }

        public void autoClick(Behavor behavor) {
            LoggerFactory.a();
        }

        public void event(String str, Behavor behavor) {
            LoggerFactory.a();
        }
    }

    private static class NullMonitorLogger implements MonitorLogger {
        private NullMonitorLogger() {
        }

        public void exception(ExceptionID exceptionID, Throwable th) {
            LoggerFactory.a();
        }

        public void performance(PerformanceID performanceID, Performance performance) {
            LoggerFactory.a();
        }

        public void footprint(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
            LoggerFactory.a();
        }

        public void crash(Throwable th, String str) {
            LoggerFactory.a();
        }

        public void apm(String str, String str2, Throwable th, Map<String, String> map) {
            LoggerFactory.a();
        }

        public void dataflow(String str, String str2, long j, String str3, Map<String, String> map, long j2, long j3, long j4) {
            LoggerFactory.a();
        }

        public void mtBizReport(String str, String str2, String str3, Map<String, String> map) {
            LoggerFactory.a();
        }

        public void keyBizTrace(String str, String str2, String str3, Map<String, String> map) {
            LoggerFactory.a();
        }
    }

    private static class NullTraceLogger implements TraceLogger {
        private NullTraceLogger() {
        }

        public void info(String str, String str2) {
            LoggerFactory.a();
        }

        public void verbose(String str, String str2) {
            LoggerFactory.a();
        }

        public void debug(String str, String str2) {
            LoggerFactory.a();
        }

        public void warn(String str, String str2) {
            LoggerFactory.a();
        }

        public void warn(String str, Throwable th) {
            LoggerFactory.a();
        }

        public void error(String str, String str2) {
            LoggerFactory.a();
        }

        public void error(String str, Throwable th) {
            LoggerFactory.a();
        }

        public void error(String str, String str2, Throwable th) {
            LoggerFactory.a();
        }

        public void print(String str, String str2) {
            LoggerFactory.a();
        }

        public void print(String str, Throwable th) {
            LoggerFactory.a();
        }
    }

    private static class NullLogContext implements LogContext {
        public boolean isDebuggable() {
            return false;
        }

        private NullLogContext() {
        }

        public String getStorageParam(String str) {
            LoggerFactory.a();
            return null;
        }

        public void putContextParam(String str, String str2) {
            LoggerFactory.a();
        }

        public void removeContextParam(String str) {
            LoggerFactory.a();
        }

        public String getContextParam(String str) {
            LoggerFactory.a();
            return null;
        }

        public void putLocalParam(String str, String str2) {
            LoggerFactory.a();
        }

        public void removeLocalParam(String str) {
            LoggerFactory.a();
        }

        public String getLocalParam(String str) {
            LoggerFactory.a();
            return null;
        }

        public void flush(boolean z) {
            LoggerFactory.a();
        }

        public void flush(String str, boolean z) {
            LoggerFactory.a();
        }

        public void backupCurrentFile(String str, boolean z) {
            LoggerFactory.a();
        }

        public void syncAppendLogEvent(LogEvent logEvent) {
            LoggerFactory.a();
        }

        public void appendLogEvent(LogEvent logEvent) {
            LoggerFactory.a();
        }

        public void upload(String str) {
            LoggerFactory.a();
        }

        public void updateLogStrategyCfg(String str) {
            LoggerFactory.a();
        }

        @Deprecated
        public void takedownExceptionHandler() {
            LoggerFactory.a();
        }

        public void setupExceptionHandler(UncaughtExceptionCallback uncaughtExceptionCallback, int i) {
            LoggerFactory.a();
        }

        public Context getApplicationContext() {
            LoggerFactory.a();
            return null;
        }

        public void notifyClientEvent(String str, Object obj) {
            LoggerFactory.a();
        }

        public boolean isPositiveDiagnose() {
            LoggerFactory.a();
            return false;
        }

        public boolean isZipAndSevenZip() {
            LoggerFactory.a();
            return false;
        }

        public boolean isDisableToolsProcess() {
            LoggerFactory.a();
            return false;
        }

        public boolean isEnableTrafficLimit() {
            LoggerFactory.a();
            return false;
        }

        public String getChannelId() {
            LoggerFactory.a();
            return null;
        }

        public String getReleaseType() {
            LoggerFactory.a();
            return null;
        }

        public String getReleaseCode() {
            LoggerFactory.a();
            return null;
        }

        public String getProductId() {
            LoggerFactory.a();
            return null;
        }

        public String getProductVersion() {
            LoggerFactory.a();
            return null;
        }

        public String getUserId() {
            LoggerFactory.a();
            return null;
        }

        public String getClientId() {
            LoggerFactory.a();
            return null;
        }

        public String getDeviceId() {
            LoggerFactory.a();
            return null;
        }

        public String getLanguage() {
            LoggerFactory.a();
            return null;
        }

        public String getSessionId() {
            LoggerFactory.a();
            return null;
        }

        public String getSourceId() {
            LoggerFactory.a();
            return null;
        }

        public String getHotpatchVersion() {
            LoggerFactory.a();
            return null;
        }

        public String getPackageId() {
            LoggerFactory.a();
            return null;
        }

        public String getApkUniqueId() {
            LoggerFactory.a();
            return null;
        }

        public void setChannelId(String str) {
            LoggerFactory.a();
        }

        public void setReleaseType(String str) {
            LoggerFactory.a();
        }

        public void setReleaseCode(String str) {
            LoggerFactory.a();
        }

        public void setProductId(String str) {
            LoggerFactory.a();
        }

        public void setProductVersion(String str) {
            LoggerFactory.a();
        }

        public void setUserId(String str) {
            LoggerFactory.a();
        }

        public void setClientId(String str) {
            LoggerFactory.a();
        }

        public void setDeviceId(String str) {
            LoggerFactory.a();
        }

        public void setLanguage(String str) {
            LoggerFactory.a();
        }

        public void refreshSessionId() {
            LoggerFactory.a();
        }

        public void setSourceId(String str) {
            LoggerFactory.a();
        }

        public void setHotpatchVersion(String str) {
            LoggerFactory.a();
        }

        public void setPackageId(String str) {
            LoggerFactory.a();
        }

        public void setApkUniqueId(String str) {
            LoggerFactory.a();
        }

        public void traceNativeCrash(String str, String str2, boolean z) {
            LoggerFactory.a();
        }

        public String getLogHost() {
            LoggerFactory.a();
            return null;
        }

        public String getClientStatus(boolean z) {
            LoggerFactory.a();
            return null;
        }

        public void adjustRequestSpanByReceived() {
            LoggerFactory.a();
        }

        public void adjustRequestSpanByNetNotMatch() {
            LoggerFactory.a();
        }

        public void adjustRequestSpanByZipFail() {
            LoggerFactory.a();
        }

        public void adjustRequestSpanByUploadFail() {
            LoggerFactory.a();
        }

        public void revertRequestSpanToNormal() {
            LoggerFactory.a();
        }

        public int getDevicePerformanceScore() {
            LoggerFactory.a();
            return Integer.MAX_VALUE;
        }

        public String getBundleVersion() {
            LoggerFactory.a();
            return null;
        }

        public String getBirdNestVersion() {
            LoggerFactory.a();
            return null;
        }

        public void setBundleVersion(String str) {
            LoggerFactory.a();
        }

        public void setBirdNestVersion(String str) {
            LoggerFactory.a();
        }
    }

    private static class NullProcessInfo implements ProcessInfo {
        private NullProcessInfo() {
        }

        public String getProcessName() {
            LoggerFactory.a();
            return "";
        }

        public String getProcessAlias() {
            LoggerFactory.a();
            return "";
        }

        public int getProcessId() {
            LoggerFactory.a();
            return -1;
        }

        public boolean isMainProcess() {
            LoggerFactory.a();
            return false;
        }

        public boolean isPushProcess() {
            LoggerFactory.a();
            return false;
        }

        public boolean isToolsProcess() {
            LoggerFactory.a();
            return false;
        }

        public String getMainProcessName() {
            LoggerFactory.a();
            return "";
        }

        public String getPushProcessName() {
            LoggerFactory.a();
            return "";
        }

        public String getToolsProcessName() {
            LoggerFactory.a();
            return "";
        }
    }
}
