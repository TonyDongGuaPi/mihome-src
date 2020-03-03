package com.alipay.mobile.common.logging.appender;

import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.EventCategory;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AppenderManager {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Appender> f955a = new HashMap();
    private LogContext b;

    public AppenderManager(LogContext logContext) {
        this.b = logContext;
        LogContext logContext2 = logContext;
        this.f955a.put(LogCategory.CATEGORY_APPLOG, new ExternalFileAppender(logContext2, LogCategory.CATEGORY_APPLOG, TimeUnit.HOURS.toMillis(1), TimeUnit.DAYS.toMillis(3)));
        this.f955a.put(LogCategory.CATEGORY_TRAFFICLOG, new ExternalFileAppender(logContext2, LogCategory.CATEGORY_TRAFFICLOG, TimeUnit.DAYS.toMillis(1), TimeUnit.DAYS.toMillis(15)));
        this.f955a.put(LogCategory.CATEGORY_LOGCAT, new ExternalFileAppender(logContext2, LogCategory.CATEGORY_LOGCAT, TimeUnit.HOURS.toMillis(1), TimeUnit.DAYS.toMillis(3)));
        this.f955a.put(LogCategory.CATEGORY_USERBEHAVOR, new MdapFileAppender(logContext, LogCategory.CATEGORY_USERBEHAVOR));
        this.f955a.put(LogCategory.CATEGORY_AUTOUSERBEHAVOR, new MdapFileAppender(logContext, LogCategory.CATEGORY_AUTOUSERBEHAVOR));
        this.f955a.put(LogCategory.CATEGORY_EXCEPTION, new MdapFileAppender(logContext, LogCategory.CATEGORY_EXCEPTION));
        this.f955a.put(LogCategory.CATEGORY_SDKMONITOR, new MdapFileAppender(logContext, LogCategory.CATEGORY_SDKMONITOR));
        this.f955a.put(LogCategory.CATEGORY_PERFORMANCE, new MdapFileAppender(logContext, LogCategory.CATEGORY_PERFORMANCE));
        this.f955a.put(LogCategory.CATEGORY_ROMESYNC, new MdapFileAppender(logContext, LogCategory.CATEGORY_ROMESYNC));
        this.f955a.put(LogCategory.CATEGORY_NETWORK, new MdapFileAppender(logContext, LogCategory.CATEGORY_NETWORK));
        this.f955a.put(LogCategory.CATEGORY_WEBAPP, new MdapFileAppender(logContext, LogCategory.CATEGORY_WEBAPP));
        this.f955a.put(LogCategory.CATEGORY_FOOTPRINT, new MdapFileAppender(logContext, LogCategory.CATEGORY_FOOTPRINT));
        this.f955a.put(LogCategory.CATEGORY_KEYBIZTRACE, new MdapFileAppender(logContext, LogCategory.CATEGORY_KEYBIZTRACE));
        this.f955a.put("crash", new MdapFileAppender(logContext, "crash"));
        this.f955a.put(LogCategory.CATEGORY_APM, new MdapFileAppender(logContext, LogCategory.CATEGORY_APM));
        this.f955a.put(LogCategory.CATEGORY_DATAFLOW, new MdapFileAppender(logContext, LogCategory.CATEGORY_DATAFLOW));
        this.f955a.put(LogCategory.CATEGORY_ALIVEREPORT, new MdapFileAppender(logContext, LogCategory.CATEGORY_ALIVEREPORT));
    }

    public void backupCurrentFile(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            LoggerFactory.getTraceLogger().error("AppenderMgr", "backupCurrentFile: no category");
            return;
        }
        Appender appender = this.f955a.get(str);
        if (appender == null) {
            LoggerFactory.getTraceLogger().error("AppenderMgr", "backupCurrentFile: no appender");
            return;
        }
        try {
            appender.backupCurrentFile(z);
        } catch (Throwable th) {
            LoggerFactory.getTraceLogger().error("AppenderMgr", "backupCurrentFile", th);
        }
    }

    public synchronized void appendLogEvent(LogEvent logEvent) {
        if (logEvent != null) {
            if (!logEvent.isIllegal()) {
                if (LogStrategyManager.getInstance().isLogWrite(logEvent.getCategory())) {
                    Appender appender = this.f955a.get(logEvent.getCategory());
                    if (appender != null) {
                        appender.appendLogEvent(logEvent);
                        return;
                    } else if (EventCategory.CATEGORY_FLUSH.equals(logEvent.getCategory())) {
                        String message = logEvent.getMessage();
                        for (Appender next : this.f955a.values()) {
                            if (message == null || message.equals(next.getLogCategory())) {
                                next.flush();
                            }
                        }
                    } else if (EventCategory.CATEGORY_UPLOAD.equals(logEvent.getCategory())) {
                        String message2 = logEvent.getMessage();
                        for (Appender next2 : this.f955a.values()) {
                            if (next2 instanceof MdapFileAppender) {
                                MdapFileAppender mdapFileAppender = (MdapFileAppender) next2;
                                if (LogStrategyManager.getInstance().isLogUpload(next2.getLogCategory(), message2)) {
                                    mdapFileAppender.upload();
                                }
                            }
                        }
                    } else if (EventCategory.CATEGORY_REFRESH_SESSION.equals(logEvent.getCategory())) {
                        try {
                            this.b.refreshSessionId();
                        } catch (Throwable th) {
                            LoggerFactory.getTraceLogger().error("AppenderMgr", th);
                        }
                    } else if ("gotoBackground".equals(logEvent.getCategory())) {
                        try {
                            LogStrategyManager.getInstance().updateBackgroundTime(Long.parseLong(logEvent.getMessage()));
                        } catch (Throwable th2) {
                            LoggerFactory.getTraceLogger().error("AppenderMgr", th2);
                        }
                    }
                } else {
                    return;
                }
            }
        }
        Log.e("AppenderMgr", "appendLogEvent: illegal logEvent");
        return;
        return;
    }
}
