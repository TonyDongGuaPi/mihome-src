package com.alipay.mobile.common.logging;

import android.content.Context;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.impl.BehavorloggerImpl;
import com.alipay.mobile.common.logging.impl.MonitorLoggerImpl;
import com.alipay.mobile.common.logging.impl.TraceLoggerImpl;
import com.alipay.mobile.common.logging.util.LoggingUtil;

public class LoggerFactoryBinder {
    public static void bind(Context context) {
        TraceLogger traceLogger;
        ProcessInfoImpl processInfoImpl = new ProcessInfoImpl(context);
        LoggerFactory.attachProcessInfo(processInfoImpl);
        LogContextImpl logContextImpl = new LogContextImpl(context);
        LoggerFactory.attachLogContext(logContextImpl);
        if (LoggingUtil.isDebug(context)) {
            traceLogger = new TraceLoggerImpl(logContextImpl);
        } else {
            traceLogger = new TraceLogger() {
                public void debug(String str, String str2) {
                }

                public void error(String str, String str2) {
                }

                public void error(String str, String str2, Throwable th) {
                }

                public void error(String str, Throwable th) {
                }

                public void info(String str, String str2) {
                }

                public void print(String str, String str2) {
                }

                public void print(String str, Throwable th) {
                }

                public void verbose(String str, String str2) {
                }

                public void warn(String str, String str2) {
                }

                public void warn(String str, Throwable th) {
                }
            };
        }
        LoggerFactory.bind(traceLogger, new BehavorloggerImpl(logContextImpl), new MonitorLoggerImpl(logContextImpl));
        String concatArray = LoggingUtil.concatArray(',', Integer.valueOf(processInfoImpl.getProcessId()), processInfoImpl.getProcessAlias(), logContextImpl.getReleaseCode(), logContextImpl.getProductVersion(), logContextImpl.getUserId(), logContextImpl.getHotpatchVersion(), logContextImpl.getApkUniqueId(), logContextImpl.getBundleVersion(), logContextImpl.getBirdNestVersion());
        LoggerFactory.getTraceLogger().warn("Logging", "LoggerFactoryBinder.bind invoked");
        LoggerFactory.getTraceLogger().verbose("ContextInfo", concatArray);
    }
}
