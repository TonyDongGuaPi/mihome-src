package com.alipay.mobile.common.logging.api;

import android.content.Context;

public interface LogContext {
    public static final String ACTION_DYNAMIC_RELEASE = ".monitor.action.DYNAMIC_RELEASE";
    public static final String ACTION_MONITOR_COMMAND = ".monitor.command";
    public static final String ACTION_TRACE_NATIVECRASH = ".monitor.action.TRACE_NATIVE_CRASH";
    public static final String ACTION_UPDATE_LOG_CONTEXT = ".monitor.action.UPDATE_LOG_CONTEXT";
    public static final String ACTION_UPDATE_LOG_STRATEGY = ".monitor.action.UPDATE_LOG_STRATEGY";
    public static final String ACTION_UPLOAD_MDAPLOG = ".monitor.action.upload.mdaplog";
    public static final String CLIENT_ENVENT_CLIENTLAUNCH = "clientEventLaunch";
    public static final String CLIENT_ENVENT_CLIENTQUIT = "clientEventQuit";
    public static final String CLIENT_ENVENT_GOTOBACKGROUND = "clientEventBackground";
    public static final String CLIENT_ENVENT_GOTOFOREGROUND = "clientEventForeground";
    public static final String ENVENT_BUGREPORT = "bugReport";
    @Deprecated
    public static final String ENVENT_CLIENTLAUNCH = "clientLaunch";
    @Deprecated
    public static final String ENVENT_CLIENTQUIT = "clientQuit";
    public static final String ENVENT_DUMPLOGTOSD = "dumpLogToSD";
    @Deprecated
    public static final String ENVENT_GOTOBACKGROUND = "gotoBackground";
    @Deprecated
    public static final String ENVENT_GOTOFOREGROUND = "gotoForeground";
    public static final String ENVENT_SUBAPPRESUME = "subappResume";
    public static final String ENVENT_SUBAPPSTART = "subappStart";
    public static final String ENVENT_USERLOGIN = "userLogin";
    public static final String ENVENT_VIEWSWITCH = "viewSwitch";
    public static final String LOCAL_STORAGE_ACTIONDESC = "actionDesc";
    public static final String LOCAL_STORAGE_ACTIONID = "actionID";
    public static final String LOCAL_STORAGE_ACTIONTIMESTAMP = "actionTimestamp";
    public static final String LOCAL_STORAGE_ACTIONTOKEN = "actionToken";
    public static final int PERFORMANCE_SCORE_ENDURE = 2013;
    public static final String PUSH_RECEIVER_CLASS_NAME = "com.alipay.mobile.logmonitor.ClientMonitorWakeupReceiver";
    public static final String PUSH_SERVICE_CLASS_NAME = "com.alipay.mobile.logmonitor.ClientMonitorService";
    public static final String RELEASETYPE_DEV = "dev";
    public static final String RELEASETYPE_RC = "rc";
    public static final String RELEASETYPE_RELEASE = "release";
    public static final String RELEASETYPE_TEST = "test";
    public static final String RELEASETYPE_TESTPRE = "testpre";
    public static final String TOOLS_RECEIVER_CLASS_NAME = "com.alipay.mobile.common.logging.process.LogReceiverInToolsProcess";
    public static final String TOOLS_SERVICE_CLASS_NAME = "com.alipay.mobile.common.logging.process.LogServiceInToolsProcess";

    void adjustRequestSpanByNetNotMatch();

    void adjustRequestSpanByReceived();

    void adjustRequestSpanByUploadFail();

    void adjustRequestSpanByZipFail();

    void appendLogEvent(LogEvent logEvent);

    void backupCurrentFile(String str, boolean z);

    void flush(String str, boolean z);

    void flush(boolean z);

    String getApkUniqueId();

    Context getApplicationContext();

    String getBirdNestVersion();

    String getBundleVersion();

    String getChannelId();

    String getClientId();

    String getClientStatus(boolean z);

    String getContextParam(String str);

    String getDeviceId();

    int getDevicePerformanceScore();

    String getHotpatchVersion();

    String getLanguage();

    String getLocalParam(String str);

    String getLogHost();

    String getPackageId();

    String getProductId();

    String getProductVersion();

    String getReleaseCode();

    String getReleaseType();

    String getSessionId();

    String getSourceId();

    String getStorageParam(String str);

    String getUserId();

    boolean isDebuggable();

    boolean isDisableToolsProcess();

    boolean isEnableTrafficLimit();

    boolean isPositiveDiagnose();

    boolean isZipAndSevenZip();

    void notifyClientEvent(String str, Object obj);

    void putContextParam(String str, String str2);

    void putLocalParam(String str, String str2);

    void refreshSessionId();

    void removeContextParam(String str);

    void removeLocalParam(String str);

    void revertRequestSpanToNormal();

    void setApkUniqueId(String str);

    void setBirdNestVersion(String str);

    void setBundleVersion(String str);

    void setChannelId(String str);

    void setClientId(String str);

    void setDeviceId(String str);

    void setHotpatchVersion(String str);

    void setLanguage(String str);

    void setPackageId(String str);

    void setProductId(String str);

    void setProductVersion(String str);

    void setReleaseCode(String str);

    void setReleaseType(String str);

    void setSourceId(String str);

    void setUserId(String str);

    void setupExceptionHandler(UncaughtExceptionCallback uncaughtExceptionCallback, int i);

    void syncAppendLogEvent(LogEvent logEvent);

    @Deprecated
    void takedownExceptionHandler();

    void traceNativeCrash(String str, String str2, boolean z);

    void updateLogStrategyCfg(String str);

    void upload(String str);
}
