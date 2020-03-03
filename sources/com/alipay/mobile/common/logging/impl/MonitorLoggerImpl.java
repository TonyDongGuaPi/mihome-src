package com.alipay.mobile.common.logging.impl;

import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.UncaughtExceptionCallback;
import com.alipay.mobile.common.logging.api.monitor.ExceptionID;
import com.alipay.mobile.common.logging.api.monitor.MonitorLogger;
import com.alipay.mobile.common.logging.api.monitor.Performance;
import com.alipay.mobile.common.logging.api.monitor.PerformanceID;
import com.alipay.mobile.common.logging.render.DataflowRender;
import com.alipay.mobile.common.logging.render.DiagnoseRender;
import com.alipay.mobile.common.logging.render.ExceptionRender;
import com.alipay.mobile.common.logging.render.PerformanceRender;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import java.util.Map;

public class MonitorLoggerImpl implements MonitorLogger {

    /* renamed from: a  reason: collision with root package name */
    private LogContext f962a;
    private ExceptionRender b;
    private PerformanceRender c;
    private DiagnoseRender d;
    private DataflowRender e;

    public MonitorLoggerImpl(LogContext logContext) {
        this.f962a = logContext;
        this.b = new ExceptionRender(logContext);
        this.c = new PerformanceRender(logContext);
        this.d = new DiagnoseRender(logContext);
        this.e = new DataflowRender(logContext);
    }

    public void crash(Throwable th, String str) {
        try {
            UncaughtExceptionCallback uncaughtExceptionCallback = StatisticalExceptionHandler.getInstance().getUncaughtExceptionCallback();
            if (uncaughtExceptionCallback != null) {
                str = uncaughtExceptionCallback.getExternalExceptionInfo(Thread.currentThread(), th);
            }
        } catch (Throwable unused) {
        }
        this.f962a.syncAppendLogEvent(new LogEvent("crash", (String) null, LogEvent.Level.ERROR, this.b.render(ExceptionID.MONITORPOINT_CRASH, th, str)));
        String str2 = "crash: " + LoggingUtil.throwableToString(th);
        LoggerFactory.getTraceLogger().info("MonitorLogger", str2);
        LoggerFactory.getLogContext().flush(true);
        LoggerFactory.getLogContext().backupCurrentFile(LogCategory.CATEGORY_APPLOG, false);
        LoggingUtil.reflectErrorLog("automationcrash", "Force Start parse for automation", false);
        LoggingUtil.reflectErrorLog(str2);
        LoggingUtil.reflectErrorLog("automationcrash", "Force End parse for automation", false);
    }

    public void exception(ExceptionID exceptionID, Throwable th) {
        if (th != null) {
            if ("Native_Crash_In_Child_Thread:".equals(th.getMessage())) {
                this.f962a.syncAppendLogEvent(new LogEvent("crash", (String) null, LogEvent.Level.ERROR, this.b.render(exceptionID, th, (String) null)));
            } else {
                this.f962a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_EXCEPTION, (String) null, LogEvent.Level.ERROR, this.b.render(exceptionID, th, (String) null)));
            }
        }
    }

    public void performance(PerformanceID performanceID, Performance performance) {
        String str = LogCategory.CATEGORY_PERFORMANCE;
        if (performanceID == PerformanceID.MONITORPOINT_NETWORK) {
            str = LogCategory.CATEGORY_NETWORK;
        } else if (performanceID == PerformanceID.MONITORPOINT_WEBAPP) {
            str = LogCategory.CATEGORY_WEBAPP;
        } else if (performanceID == PerformanceID.MONITORPOINT_SDKMONITOR) {
            str = LogCategory.CATEGORY_SDKMONITOR;
        } else if (performanceID == PerformanceID.MONITORPOINT_SYNCLINK || performanceID == PerformanceID.MONITORPOINT_SYNCPROTO) {
            str = LogCategory.CATEGORY_ROMESYNC;
        }
        this.f962a.appendLogEvent(new LogEvent(str, (String) null, LogEvent.Level.INFO, this.c.render(performanceID.getDes(), performance)));
    }

    public void footprint(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        LogContext logContext = this.f962a;
        LogEvent.Level level = LogEvent.Level.INFO;
        PerformanceRender performanceRender = this.c;
        String des = PerformanceID.MONITORPOINT_FOOTPRINT.getDes();
        logContext.appendLogEvent(new LogEvent(LogCategory.CATEGORY_FOOTPRINT, (String) null, level, performanceRender.render(des, str, str2, str3, str4 + str5, map)));
    }

    public void apm(String str, String str2, Throwable th, Map<String, String> map) {
        this.f962a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_APM, (String) null, LogEvent.Level.INFO, this.d.render(str, str2, th, map)));
    }

    public void dataflow(String str, String str2, long j, String str3, Map<String, String> map, long j2, long j3, long j4) {
        this.f962a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_DATAFLOW, (String) null, LogEvent.Level.INFO, this.e.render(str, str2, j, str3, map, j2, j3, j4)));
    }

    public void mtBizReport(String str, String str2, String str3, Map<String, String> map) {
        this.f962a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_KEYBIZTRACE, (String) null, LogEvent.Level.INFO, this.c.render(PerformanceID.MONITORPOINT_KEYBIZTRACE.getDes(), "BizCanNotUse", str, str2, str3, map)));
    }

    public void keyBizTrace(String str, String str2, String str3, Map<String, String> map) {
        this.f962a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_KEYBIZTRACE, (String) null, LogEvent.Level.INFO, this.c.render(PerformanceID.MONITORPOINT_KEYBIZTRACE.getDes(), "KeyBiz", str, str2, str3, map)));
    }
}
