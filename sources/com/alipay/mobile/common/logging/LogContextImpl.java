package com.alipay.mobile.common.logging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.DeviceHWInfo;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.UncaughtExceptionCallback;
import com.alipay.mobile.common.logging.api.behavor.Behavor;
import com.alipay.mobile.common.logging.api.monitor.ExceptionID;
import com.alipay.mobile.common.logging.api.monitor.Performance;
import com.alipay.mobile.common.logging.api.monitor.PerformanceID;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.appender.AppenderManager;
import com.alipay.mobile.common.logging.helper.YearClass;
import com.alipay.mobile.common.logging.http.HttpClient;
import com.alipay.mobile.common.logging.impl.StatisticalExceptionHandler;
import com.alipay.mobile.common.logging.impl.TraceLogEvent;
import com.alipay.mobile.common.logging.render.BehavorRender;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.alipay.mobile.common.logging.util.FileUtil;
import com.alipay.mobile.common.logging.util.HybridEncryption;
import com.alipay.mobile.common.logging.util.LoggingSPCache;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.nativecrash.CrashClientImpl;
import com.alipay.mobile.common.nativecrash.CrashCombineUtils;
import com.alipay.mobile.common.nativecrash.CrashFilterUtils;
import com.alipay.mobile.common.nativecrash.NativeCrashHandler;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.exoplayer2.offline.DownloadService;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class LogContextImpl implements LogContext {
    public static final String STORAGE_APPID = "appID";
    public static final String STORAGE_PAGESERIAL = "pageSerial";
    public static final String STORAGE_REFVIEWID = "refViewID";
    public static final String STORAGE_VIEWID = "viewID";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f943a = LogContext.class.getSimpleName();
    private static final long b = TimeUnit.MINUTES.toMillis(30);
    private Context c;
    private String d;
    private ThreadLocal<Integer> e = new ThreadLocal<>();
    private Map<String, String> f = new ConcurrentHashMap();
    private InheritableThreadLocal<Map<String, String>> g = new InheritableThreadLocal<>();
    /* access modifiers changed from: private */
    public BlockingQueue<LogEvent> h = new ArrayBlockingQueue(1024);
    private ContextInfo i;
    private AppendWorker j;
    /* access modifiers changed from: private */
    public AppenderManager k;
    private MdapLogUploadManager l;
    private long m = System.currentTimeMillis();
    private long n = 0;
    private long o = 0;
    private long p = 0;
    private Map<String, Long> q = new HashMap();

    private class AppendWorker extends Thread {
        private AppendWorker() {
        }

        public void run() {
            try {
                int priority = Looper.getMainLooper().getThread().getPriority() - 2;
                if (priority < 5) {
                    priority = 5;
                }
                setPriority(priority);
            } catch (Throwable th) {
                Log.e(LogContextImpl.f943a, "AppendWorker priority: " + th.getMessage());
            }
            while (true) {
                try {
                    LogContextImpl.this.syncAppendLogEvent((LogEvent) LogContextImpl.this.h.take());
                } catch (Throwable th2) {
                    Log.e(LogContextImpl.f943a, "AppendWorker finally: " + th2.getMessage());
                    return;
                }
            }
            throw th;
        }
    }

    public LogContextImpl(Context context) {
        CrashCombineUtils.class.getName() + CrashCombineUtils.FlatComparator.class + CrashFilterUtils.class.getName() + NativeCrashHandler.class.getName() + CrashClientImpl.class.getName() + LogEvent.class.getName() + Behavor.class.getName() + Behavor.Builder.class.getName() + PerformanceID.class.getName() + Performance.class.getName() + Performance.Builder.class.getName() + ExceptionID.class.getName() + LogCategory.class.getName() + LogEvent.Level.class.getName() + HttpClient.class.getName() + TraceLogEvent.class.getName() + EventCategory.class.getName() + UncaughtExceptionCallback.class.getName();
        this.c = context;
        LoggingSPCache.createInstance(context);
        StatisticalExceptionHandler.createInstance(context);
        HybridEncryption.createInstance(context);
        this.i = new ContextInfo(context);
        LogStrategyManager.createInstance(context, this.i);
        this.k = new AppenderManager(this);
        this.l = new MdapLogUploadManager(context, this.i);
    }

    public String getStorageParam(String str) {
        String localParam = getLocalParam(str);
        return localParam == null ? getContextParam(str) : localParam;
    }

    public void putContextParam(String str, String str2) {
        if (str != null && str2 != null) {
            this.f.put(str, str2);
        }
    }

    public String getContextParam(String str) {
        return this.f.get(str);
    }

    public void removeContextParam(String str) {
        if (str != null) {
            this.f.remove(str);
        }
    }

    public void cleanContextParam() {
        this.f.clear();
    }

    public Context getApplicationContext() {
        return this.c;
    }

    public boolean isDebuggable() {
        return LoggingUtil.isDebug(this.c);
    }

    public void putLocalParam(String str, String str2) {
        if (str != null && str2 != null) {
            Map map = (Map) this.g.get();
            if (a(a(1)) || map == null) {
                a((Map<String, String>) map).put(str, str2);
            } else {
                map.put(str, str2);
            }
        }
    }

    public String getLocalParam(String str) {
        Map<String, String> propertyMap = getPropertyMap();
        if (propertyMap == null || str == null) {
            return null;
        }
        return propertyMap.get(str);
    }

    public void removeLocalParam(String str) {
        Map map;
        if (str != null && (map = (Map) this.g.get()) != null) {
            if (a(a(1))) {
                a((Map<String, String>) map).remove(str);
            } else {
                map.remove(str);
            }
        }
    }

    public void cleanLocalParam() {
        this.e.set(1);
        this.g.remove();
    }

    public void flush(boolean z) {
        flush((String) null, z);
    }

    public void flush(String str, boolean z) {
        LogEvent logEvent = new LogEvent(EventCategory.CATEGORY_FLUSH, (String) null, LogEvent.Level.ERROR, str);
        if (z) {
            syncAppendLogEvent(logEvent);
        } else {
            appendLogEvent(logEvent);
        }
    }

    public void backupCurrentFile(String str, boolean z) {
        this.k.backupCurrentFile(str, z);
    }

    public void upload(String str) {
        if (LoggerFactory.getProcessInfo().isMainProcess()) {
            Intent intent = new Intent();
            if (LogStrategyManager.getInstance().isDisableToolsProcess()) {
                intent.setClassName(this.c, LogContext.PUSH_SERVICE_CLASS_NAME);
            } else {
                intent.setClassName(this.c, LogContext.TOOLS_SERVICE_CLASS_NAME);
            }
            a(intent, str);
        } else if (LoggerFactory.getProcessInfo().isPushProcess()) {
            if (LogStrategyManager.getInstance().isDisableToolsProcess()) {
                b(str);
            } else {
                Intent intent2 = new Intent();
                intent2.setClassName(this.c, LogContext.TOOLS_SERVICE_CLASS_NAME);
                a(intent2, str);
            }
        } else if (LoggerFactory.getProcessInfo().isToolsProcess()) {
            b(str);
        } else {
            b(str);
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            String str2 = f943a;
            traceLogger.error(str2, "upload, error: unknown process " + LoggerFactory.getProcessInfo().getProcessAlias());
        }
        a();
    }

    private void a(Intent intent, String str) {
        if (intent != null) {
            try {
                intent.setPackage(this.c.getPackageName());
            } catch (Throwable unused) {
            }
            intent.setAction(this.c.getPackageName() + LogContext.ACTION_UPLOAD_MDAPLOG);
            intent.putExtra("logCategory", str);
            boolean z = false;
            try {
                if (this.c.startService(intent) != null) {
                    z = true;
                }
            } catch (Throwable th) {
                LoggerFactory.getTraceLogger().error(f943a, "uploadCoreByStartService", th);
            }
            LoggerFactory.getTraceLogger().info(f943a, "uploadCoreByStartService: start upload service" + ", logCategory: " + str + ", success: " + z + ", process: " + LoggerFactory.getProcessInfo().getProcessAlias() + ", disableTools: " + LogStrategyManager.getInstance().isDisableToolsProcess());
            if (!z) {
                a(str);
            }
        }
    }

    private void a(final String str) {
        AnonymousClass1 r1 = new Runnable() {
            public void run() {
                LogContextImpl.this.b(str);
            }
        };
        new Thread(r1, f943a + ".upload").start();
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
        String str2 = f943a;
        traceLogger.info(str2, LoggerFactory.getProcessInfo().getProcessAlias() + " syncUploadCoreByCategoryDirectly: " + str);
        if (!LoggerFactory.getProcessInfo().isMainProcess() && str == null) {
            try {
                this.l.syncLog();
            } catch (Throwable th) {
                TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
                String str3 = f943a;
                traceLogger2.error(str3, "syncUploadCoreByCategoryDirectly, syncLog: " + th.getMessage());
            }
        }
        try {
            this.l.uploadLog(str);
        } catch (Throwable th2) {
            TraceLogger traceLogger3 = LoggerFactory.getTraceLogger();
            String str4 = f943a;
            traceLogger3.error(str4, "syncUploadCoreByCategoryDirectly, uploadLog: " + th2.getMessage());
        }
    }

    private void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.p >= LogStrategyManager.MINIMUM_REQUEST_TIME_SPAN) {
            this.p = currentTimeMillis;
            LogStrategyManager.getInstance().queryStrategy(LogStrategyManager.ACTION_TYPE_MDAPUPLOAD, false);
        }
    }

    public void updateLogStrategyCfg(String str) {
        LogStrategyManager.getInstance().updateLogStrategy(str);
    }

    public boolean isPositiveDiagnose() {
        return LogStrategyManager.getInstance().isPositiveDiagnose();
    }

    public boolean isZipAndSevenZip() {
        return LogStrategyManager.getInstance().isZipAndSevenZip();
    }

    public boolean isDisableToolsProcess() {
        return LogStrategyManager.getInstance().isDisableToolsProcess();
    }

    public boolean isEnableTrafficLimit() {
        return LogStrategyManager.getInstance().isEnableTrafficLimit();
    }

    private Map<String, String> a(Map<String, String> map) {
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap());
        if (map != null) {
            synchronized (map) {
                synchronizedMap.putAll(map);
            }
        }
        this.g.set(synchronizedMap);
        return synchronizedMap;
    }

    private Integer a(int i2) {
        Integer num = this.e.get();
        this.e.set(Integer.valueOf(i2));
        return num;
    }

    private boolean a(Integer num) {
        return num == null || num.intValue() == 2;
    }

    public Map<String, String> getPropertyMap() {
        this.e.set(2);
        return (Map) this.g.get();
    }

    public void syncAppendLogEvent(LogEvent logEvent) {
        this.k.appendLogEvent(logEvent);
    }

    public void appendLogEvent(LogEvent logEvent) {
        if (logEvent == null || logEvent.isIllegal()) {
            Log.e(f943a, "appendLogEvent: illegal logEvent");
            return;
        }
        if (getAppendWorker() == null) {
            synchronized (this) {
                if (getAppendWorker() == null) {
                    AppendWorker appendWorker = new AppendWorker();
                    appendWorker.setDaemon(true);
                    appendWorker.setName("LogAppendWorker");
                    appendWorker.start();
                    setAppendWorker(appendWorker);
                }
            }
        }
        try {
            if (!this.h.add(logEvent)) {
                throw new RuntimeException("add log event to queue fail");
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized AppendWorker getAppendWorker() {
        return this.j;
    }

    public synchronized void setAppendWorker(AppendWorker appendWorker) {
        this.j = appendWorker;
    }

    public String getChannelId() {
        return this.i.getChannelId();
    }

    public String getReleaseType() {
        return this.i.getReleaseType();
    }

    public String getReleaseCode() {
        return this.i.getReleaseCode();
    }

    public String getProductId() {
        return this.i.getProductId();
    }

    public String getProductVersion() {
        return this.i.getProductVersion();
    }

    public String getUserId() {
        return this.i.getUserId();
    }

    public String getClientId() {
        return this.i.getClientId();
    }

    public String getDeviceId() {
        return this.i.getDeviceId();
    }

    public String getLanguage() {
        return this.i.getLanguage();
    }

    public String getSessionId() {
        return this.i.getSessionId();
    }

    public String getSourceId() {
        return this.i.getSourceId();
    }

    public String getHotpatchVersion() {
        return this.i.getHotpatchVersion();
    }

    public String getBundleVersion() {
        return this.i.getBundleVersion();
    }

    public String getBirdNestVersion() {
        return this.i.getBirdNestVersion();
    }

    public String getPackageId() {
        return this.i.getPackageId();
    }

    public String getApkUniqueId() {
        return this.i.getApkUniqueId();
    }

    public void setChannelId(String str) {
        this.i.setChannelId(str);
    }

    public void setReleaseType(String str) {
        this.i.setReleaseType(str);
    }

    public void setReleaseCode(String str) {
        this.i.setReleaseCode(str);
    }

    public void setProductId(String str) {
        this.i.setProductId(str);
    }

    public void setProductVersion(String str) {
        this.i.setProductVersion(str);
    }

    public void setUserId(String str) {
        this.i.setUserId(str);
    }

    public void setClientId(String str) {
        this.i.setClientId(str);
    }

    public void setDeviceId(String str) {
        this.i.setDeviceId(str);
    }

    public void setLanguage(String str) {
        this.i.setLanguage(str);
    }

    public void refreshSessionId() {
        this.i.refreshSessionId();
    }

    public void setSourceId(String str) {
        this.i.setSourceId(str);
    }

    public void setHotpatchVersion(String str) {
        this.i.setHotpatchVersion(str);
    }

    public void setPackageId(String str) {
        this.i.setPackageId(str);
    }

    public void setApkUniqueId(String str) {
        this.i.setApkUniqueId(str);
    }

    public void setBundleVersion(String str) {
        this.i.setBundleVersion(str);
    }

    public void setBirdNestVersion(String str) {
        this.i.setBirdNestVersion(str);
    }

    @Deprecated
    public void takedownExceptionHandler() {
        LoggerFactory.getTraceLogger().error(f943a, (Throwable) new Exception("illegal to invoke 'takedownExceptionHandler' function"));
    }

    public void setupExceptionHandler(UncaughtExceptionCallback uncaughtExceptionCallback, int i2) {
        StatisticalExceptionHandler.getInstance().setup();
        StatisticalExceptionHandler.getInstance().setUncaughtExceptionCallback(uncaughtExceptionCallback);
    }

    public void notifyClientEvent(String str, Object obj) {
        if (LogContext.ENVENT_VIEWSWITCH.equals(str)) {
            String str2 = (String) obj;
            if (str2 != null && !str2.equals(getContextParam(STORAGE_VIEWID))) {
                putContextParam(STORAGE_REFVIEWID, getContextParam(STORAGE_VIEWID));
                putContextParam(STORAGE_VIEWID, str2);
                putContextParam(STORAGE_PAGESERIAL, this.i.getSessionId() + '_' + System.currentTimeMillis());
            }
        } else if (LogContext.ENVENT_SUBAPPSTART.equals(str)) {
            String str3 = (String) obj;
            putContextParam("appID", str3);
            Behavor behavor = new Behavor();
            behavor.setSeedID("startApp");
            behavor.setParam1(str3);
            appendLogEvent(new LogEvent(LogCategory.CATEGORY_ALIVEREPORT, (String) null, LogEvent.Level.ERROR, new BehavorRender(this).render("event", behavor)));
        } else if (LogContext.ENVENT_SUBAPPRESUME.equals(str)) {
            String str4 = (String) obj;
            if (!TextUtils.isEmpty(str4) && !str4.equals(getContextParam("appID"))) {
                putContextParam("appID", str4);
            }
        } else if (LogContext.ENVENT_GOTOFOREGROUND.equals(str)) {
            this.o = 0;
            appendLogEvent(new LogEvent("gotoBackground", (String) null, LogEvent.Level.ERROR, Long.toString(this.o)));
            appendLogEvent(new LogEvent(EventCategory.CATEGORY_REFRESH_SESSION, (String) null, LogEvent.Level.ERROR, (String) null));
            LogStrategyManager.getInstance().queryStrategy("timeout", false);
            Behavor behavor2 = new Behavor();
            behavor2.setSeedID("reportActive");
            try {
                behavor2.addExtParam("CpuName", DeviceHWInfo.getCpuName());
            } catch (Throwable unused) {
            }
            appendLogEvent(new LogEvent(LogCategory.CATEGORY_ALIVEREPORT, (String) null, LogEvent.Level.ERROR, new BehavorRender(this).render("event", behavor2)));
            d(str);
        } else if ("gotoBackground".equals(str)) {
            this.o = System.currentTimeMillis();
            appendLogEvent(new LogEvent("gotoBackground", (String) null, LogEvent.Level.ERROR, Long.toString(this.o)));
            LogStrategyManager.getInstance().queryStrategy(LogStrategyManager.ACTION_TYPE_LEAVEHINT, false);
            flush(false);
            if (LoggingUtil.isOfflineMode()) {
                upload((String) null);
            } else {
                d(str);
            }
        } else if (LogContext.CLIENT_ENVENT_CLIENTLAUNCH.equals(str)) {
            this.n = System.currentTimeMillis();
            appendLogEvent(new LogEvent(EventCategory.CATEGORY_REFRESH_SESSION, (String) null, LogEvent.Level.ERROR, (String) null));
            LogStrategyManager.getInstance().queryStrategy(LogStrategyManager.ACTION_TYPE_BOOT, false);
            Behavor behavor3 = new Behavor();
            behavor3.setSeedID("reportActive");
            appendLogEvent(new LogEvent(LogCategory.CATEGORY_ALIVEREPORT, (String) null, LogEvent.Level.ERROR, new BehavorRender(this).render("event", behavor3)));
            d(str);
        } else if (LogContext.ENVENT_CLIENTQUIT.equals(str)) {
            flush(false);
            d(str);
        } else if (LogContext.ENVENT_USERLOGIN.equals(str)) {
            String str5 = (String) obj;
            if (!TextUtils.isEmpty(str5)) {
                this.i.setUserId(str5);
                LogStrategyManager.getInstance().queryStrategy("login", !str5.equals(this.i.getUserId()));
                Behavor behavor4 = new Behavor();
                behavor4.setSeedID("login");
                behavor4.setParam1(str5);
                appendLogEvent(new LogEvent(LogCategory.CATEGORY_ALIVEREPORT, (String) null, LogEvent.Level.ERROR, new BehavorRender(this).render("event", behavor4)));
                d(str);
            }
        } else if (LogContext.ENVENT_BUGREPORT.equals(str)) {
            LogStrategyManager.getInstance().queryStrategy(LogStrategyManager.ACTION_TYPE_FEEDBACK, true);
            AnonymousClass2 r8 = new Runnable() {
                public void run() {
                    LogContextImpl.this.k.backupCurrentFile(LogCategory.CATEGORY_APPLOG, true);
                    LogContextImpl.this.k.backupCurrentFile(LogCategory.CATEGORY_TRAFFICLOG, true);
                }
            };
            new Thread(r8, f943a + ".BUGREPORT").start();
            upload((String) null);
        } else if (LogContext.ENVENT_DUMPLOGTOSD.equals(str)) {
            final String str6 = (String) obj;
            if (!TextUtils.isEmpty(str6)) {
                AnonymousClass3 r0 = new Runnable() {
                    public void run() {
                        String string = LoggingSPCache.getInstance().getString(LoggingSPCache.LOGGING_CACHE_KEY_LOG_DUMP_TAG, (String) null);
                        if (string == null || !string.equals(str6)) {
                            LoggingSPCache.getInstance().putString(LoggingSPCache.LOGGING_CACHE_KEY_LOG_DUMP_TAG, str6);
                            try {
                                LogContextImpl.this.c(LogCategory.CATEGORY_APPLOG);
                            } catch (Throwable th) {
                                LoggerFactory.getTraceLogger().error(LogContextImpl.f943a, th);
                            }
                            try {
                                LogContextImpl.this.c(LogCategory.CATEGORY_TRAFFICLOG);
                            } catch (Throwable th2) {
                                LoggerFactory.getTraceLogger().error(LogContextImpl.f943a, th2);
                            }
                        }
                    }
                };
                new Thread(r0, f943a + ".DUMPLOGTOSD").start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        File[] listFiles;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            LoggerFactory.getTraceLogger().info(f943a, "dumpLogToSD fail:" + str);
            return;
        }
        File file = new File(this.c.getFilesDir(), str);
        File file2 = new File(new File(LoggingUtil.getCommonExternalStorageDir(), this.c.getPackageName()), str + "_dump");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file3 : listFiles) {
                if (file3 != null) {
                    try {
                        FileUtil.copyFile(file3, new File(file2, file3.getName()));
                    } catch (Throwable th) {
                        LoggerFactory.getTraceLogger().error(f943a, th);
                    }
                }
            }
        }
    }

    private synchronized void d(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        Long l2 = this.q.get(str);
        if (l2 == null || Math.abs(currentTimeMillis - l2.longValue()) > b) {
            this.q.put(str, Long.valueOf(currentTimeMillis));
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            String str2 = f943a;
            traceLogger.info(str2, "notifyUpload:" + str);
            appendLogEvent(new LogEvent(EventCategory.CATEGORY_UPLOAD, (String) null, LogEvent.Level.ERROR, str));
        }
    }

    private void a(StringBuilder sb) {
        sb.append("[native crash on main thread but NONE returned, java stack traces are used instead]\n");
        try {
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement append : stackTrace) {
                    sb.append(9);
                    sb.append(append);
                    sb.append(10);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void traceNativeCrash(String str, String str2, boolean z) {
        String processAlias = LoggerFactory.getProcessInfo().getProcessAlias();
        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
        String str3 = f943a;
        traceLogger.error(str3, "traceNativeCrash, filePath:" + str + ", isBoot:" + z + ", process: " + processAlias);
        if (LoggerFactory.getProcessInfo().isMainProcess()) {
            String a2 = a(str, str2, z);
            Intent intent = new Intent();
            if (LogStrategyManager.getInstance().isDisableToolsProcess()) {
                intent.setClassName(this.c, LogContext.PUSH_RECEIVER_CLASS_NAME);
            } else {
                intent.setClassName(this.c, LogContext.TOOLS_RECEIVER_CLASS_NAME);
            }
            a(intent, str, a2, z);
        } else if (LoggerFactory.getProcessInfo().isPushProcess()) {
            if (LogStrategyManager.getInstance().isDisableToolsProcess()) {
                b(str, str2, z);
                return;
            }
            Intent intent2 = new Intent();
            intent2.setClassName(this.c, LogContext.TOOLS_RECEIVER_CLASS_NAME);
            a(intent2, str, str2, z);
        } else if (LoggerFactory.getProcessInfo().isToolsProcess()) {
            b(str, str2, z);
        } else {
            b(str, str2, z);
            TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
            String str4 = f943a;
            traceLogger2.error(str4, "traceNativeCrash, error: unknown process " + processAlias);
        }
    }

    private String a(String str, String str2, boolean z) {
        if (z) {
            return str2;
        }
        StringBuilder sb = null;
        try {
            if (TextUtils.isEmpty(str)) {
                sb = new StringBuilder();
                sb.append("file path is empty");
            } else {
                File file = new File(str);
                if (!file.exists() || !file.isFile()) {
                    sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" is not exist");
                }
            }
            if (sb == null) {
                return str2;
            }
            sb.append(", logType: ");
            sb.append(str2);
            sb.append(10);
            a(sb);
            return sb.toString();
        } catch (Throwable th) {
            LoggerFactory.getTraceLogger().error(f943a, th);
            return str2;
        }
    }

    private void a(Intent intent, String str, String str2, boolean z) {
        if (intent != null) {
            try {
                intent.setPackage(this.c.getPackageName());
            } catch (Throwable unused) {
            }
            intent.setAction(this.c.getPackageName() + LogContext.ACTION_MONITOR_COMMAND);
            intent.putExtra("action", this.c.getPackageName() + LogContext.ACTION_TRACE_NATIVECRASH);
            intent.putExtra(TbsReaderView.KEY_FILE_PATH, str);
            intent.putExtra("callStack", str2);
            intent.putExtra("isBoot", z);
            boolean z2 = false;
            try {
                this.c.sendBroadcast(intent);
                z2 = true;
            } catch (Throwable th) {
                LoggerFactory.getTraceLogger().error(f943a, "handleNativeCrashBySendBroadcast", th);
            }
            LoggerFactory.getTraceLogger().info(f943a, "handleNativeCrashBySendBroadcast: send native crash broadcast" + ", filePath: " + str + ", isBoot: " + z + ", success: " + z2 + ", process: " + LoggerFactory.getProcessInfo().getProcessAlias() + ", disableTools: " + LogStrategyManager.getInstance().isDisableToolsProcess());
            if (!z2) {
                b(str, str2, z);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047 A[ADDED_TO_REGION, Catch:{ Throwable -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0090 A[Catch:{ Throwable -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e0 A[Catch:{ Throwable -> 0x0100 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.lang.String r12, java.lang.String r13, boolean r14) {
        /*
            r11 = this;
            r0 = 0
            if (r14 == 0) goto L_0x000d
            android.content.Context r12 = r11.c     // Catch:{ Throwable -> 0x000a }
            java.lang.String r12 = com.alipay.mobile.common.nativecrash.CrashCombineUtils.getLatestTombAndDelOld(r12)     // Catch:{ Throwable -> 0x000a }
            goto L_0x0038
        L_0x000a:
            r12 = move-exception
            r13 = r0
            goto L_0x003b
        L_0x000d:
            com.alipay.mobile.common.logging.api.trace.TraceLogger r1 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ Throwable -> 0x000a }
            java.lang.String r2 = f943a     // Catch:{ Throwable -> 0x000a }
            java.lang.String r3 = "handleNativeCrashByAppendDirectly, !isBoot"
            r1.error((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ Throwable -> 0x000a }
            java.lang.String r13 = com.alipay.mobile.common.nativecrash.CrashCombineUtils.UserTrackReport(r12, r13)     // Catch:{ Throwable -> 0x000a }
            com.alipay.mobile.common.nativecrash.CrashCombineUtils.deleteFileByPath(r12)     // Catch:{ Throwable -> 0x003a }
            com.alipay.mobile.common.nativecrash.NativeCrashHandlerApi$OnNativeCrashUploadListener r12 = com.alipay.mobile.common.nativecrash.NativeCrashHandlerApi.getOnNativeCrashUploadListener()     // Catch:{ Throwable -> 0x003a }
            if (r12 == 0) goto L_0x0030
            java.lang.String r1 = f943a     // Catch:{ Throwable -> 0x003a }
            java.lang.String r2 = "OnNativeCrashUploadListener is not null"
            android.util.Log.w(r1, r2)     // Catch:{ Throwable -> 0x003a }
            r12.onUpload(r13)     // Catch:{ Throwable -> 0x0037 }
            goto L_0x0037
        L_0x0030:
            java.lang.String r12 = f943a     // Catch:{ Throwable -> 0x003a }
            java.lang.String r1 = "OnNativeCrashUploadListener is null"
            android.util.Log.w(r12, r1)     // Catch:{ Throwable -> 0x003a }
        L_0x0037:
            r12 = r13
        L_0x0038:
            r5 = r12
            goto L_0x0045
        L_0x003a:
            r12 = move-exception
        L_0x003b:
            com.alipay.mobile.common.logging.api.trace.TraceLogger r1 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r2 = f943a     // Catch:{ Throwable -> 0x0100 }
            r1.error((java.lang.String) r2, (java.lang.Throwable) r12)     // Catch:{ Throwable -> 0x0100 }
            r5 = r13
        L_0x0045:
            if (r14 == 0) goto L_0x004a
            if (r5 != 0) goto L_0x004a
            return
        L_0x004a:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0100 }
            r12.<init>()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r13 = "traceNativeCrash:"
            r12.append(r13)     // Catch:{ Throwable -> 0x0100 }
            r12.append(r5)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x0100 }
            com.alipay.mobile.common.logging.api.trace.TraceLogger r13 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r1 = f943a     // Catch:{ Throwable -> 0x0100 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0100 }
            r2.<init>()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r3 = "handleNativeCrashByAppendDirectly: "
            r2.append(r3)     // Catch:{ Throwable -> 0x0100 }
            r2.append(r12)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0100 }
            r13.error((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r13 = "automationcrash"
            java.lang.String r1 = "Force Start parse for automation"
            r2 = 0
            com.alipay.mobile.common.logging.util.LoggingUtil.reflectErrorLog(r13, r1, r2)     // Catch:{ Throwable -> 0x0100 }
            com.alipay.mobile.common.logging.util.LoggingUtil.reflectErrorLog(r12)     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r12 = "automationcrash"
            java.lang.String r13 = "Force End parse for automation"
            com.alipay.mobile.common.logging.util.LoggingUtil.reflectErrorLog(r12, r13, r2)     // Catch:{ Throwable -> 0x0100 }
            android.content.Context r12 = r11.c     // Catch:{ Throwable -> 0x0100 }
            boolean r12 = com.alipay.mobile.common.nativecrash.CrashFilterUtils.isFilterCrash(r5, r12)     // Catch:{ Throwable -> 0x0100 }
            if (r12 == 0) goto L_0x00e0
            java.lang.String r12 = "unknown"
            com.alipay.mobile.common.logging.api.ProcessInfo r13 = com.alipay.mobile.common.logging.api.LoggerFactory.getProcessInfo()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r13 = r13.getMainProcessName()     // Catch:{ Throwable -> 0x0100 }
            android.content.Context r1 = r11.c     // Catch:{ Throwable -> 0x0100 }
            boolean r13 = com.alipay.mobile.common.nativecrash.CrashFilterUtils.isTargetProcess(r1, r5, r13)     // Catch:{ Throwable -> 0x0100 }
            if (r13 == 0) goto L_0x00a7
            java.lang.String r12 = "main"
        L_0x00a5:
            r8 = r12
            goto L_0x00ce
        L_0x00a7:
            com.alipay.mobile.common.logging.api.ProcessInfo r13 = com.alipay.mobile.common.logging.api.LoggerFactory.getProcessInfo()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r13 = r13.getPushProcessName()     // Catch:{ Throwable -> 0x0100 }
            android.content.Context r1 = r11.c     // Catch:{ Throwable -> 0x0100 }
            boolean r13 = com.alipay.mobile.common.nativecrash.CrashFilterUtils.isTargetProcess(r1, r5, r13)     // Catch:{ Throwable -> 0x0100 }
            if (r13 == 0) goto L_0x00ba
            java.lang.String r12 = "push"
            goto L_0x00a5
        L_0x00ba:
            com.alipay.mobile.common.logging.api.ProcessInfo r13 = com.alipay.mobile.common.logging.api.LoggerFactory.getProcessInfo()     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r13 = r13.getToolsProcessName()     // Catch:{ Throwable -> 0x0100 }
            android.content.Context r1 = r11.c     // Catch:{ Throwable -> 0x0100 }
            boolean r13 = com.alipay.mobile.common.nativecrash.CrashFilterUtils.isTargetProcess(r1, r5, r13)     // Catch:{ Throwable -> 0x0100 }
            if (r13 == 0) goto L_0x00a5
            java.lang.String r12 = "tools"
            goto L_0x00a5
        L_0x00ce:
            com.alipay.mobile.common.logging.render.ExceptionRender r3 = new com.alipay.mobile.common.logging.render.ExceptionRender     // Catch:{ Throwable -> 0x0100 }
            r3.<init>(r11)     // Catch:{ Throwable -> 0x0100 }
            com.alipay.mobile.common.logging.api.monitor.ExceptionID r4 = com.alipay.mobile.common.logging.api.monitor.ExceptionID.MONITORPOINT_CLIENTSERR     // Catch:{ Throwable -> 0x0100 }
            r6 = 0
            java.lang.String r9 = "unknown"
            r10 = 1
            r7 = r14
            java.lang.String r12 = r3.render(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x0100 }
            goto L_0x00f3
        L_0x00e0:
            com.alipay.mobile.common.logging.render.ExceptionRender r3 = new com.alipay.mobile.common.logging.render.ExceptionRender     // Catch:{ Throwable -> 0x0100 }
            r3.<init>(r11)     // Catch:{ Throwable -> 0x0100 }
            com.alipay.mobile.common.logging.api.monitor.ExceptionID r4 = com.alipay.mobile.common.logging.api.monitor.ExceptionID.MONITORPOINT_CRASH     // Catch:{ Throwable -> 0x0100 }
            r6 = 0
            java.lang.String r8 = "main"
            java.lang.String r9 = "unknown"
            r10 = 1
            r7 = r14
            java.lang.String r12 = r3.render(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x0100 }
        L_0x00f3:
            com.alipay.mobile.common.logging.api.LogEvent r13 = new com.alipay.mobile.common.logging.api.LogEvent     // Catch:{ Throwable -> 0x0100 }
            java.lang.String r14 = "crash"
            com.alipay.mobile.common.logging.api.LogEvent$Level r1 = com.alipay.mobile.common.logging.api.LogEvent.Level.ERROR     // Catch:{ Throwable -> 0x0100 }
            r13.<init>(r14, r0, r1, r12)     // Catch:{ Throwable -> 0x0100 }
            r11.syncAppendLogEvent(r13)     // Catch:{ Throwable -> 0x0100 }
            goto L_0x010a
        L_0x0100:
            r12 = move-exception
            com.alipay.mobile.common.logging.api.trace.TraceLogger r13 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()
            java.lang.String r14 = f943a
            r13.error((java.lang.String) r14, (java.lang.Throwable) r12)
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.LogContextImpl.b(java.lang.String, java.lang.String, boolean):void");
    }

    public String getLogHost() {
        if (this.d != null) {
            return this.d;
        }
        String str = null;
        try {
            ApplicationInfo applicationInfo = this.c.getPackageManager().getApplicationInfo(this.c.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                str = applicationInfo.metaData.getString("logging.gateway");
            }
        } catch (Throwable th) {
            Log.w(f943a, th);
        }
        if (isDebuggable() && TextUtils.isEmpty(str)) {
            str = LoggingUtil.getZhizhiSetting(this.c, "content://com.alipay.setting/MdapLogUrlPrefix", str);
        }
        if (this.i != null && TextUtils.isEmpty(str)) {
            str = "dev".equals(this.i.getReleaseType()) ? "http://mdap-1-64.test.alipay.net" : "http://mdap.alipaylog.com";
        }
        this.d = str;
        return this.d;
    }

    public String getClientStatus(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            long backgroundTime = LogStrategyManager.getInstance().getBackgroundTime();
            long crashTime = CrashCombineUtils.getCrashTime();
            return (crashTime <= 0 || backgroundTime <= 0 || crashTime >= currentTimeMillis || crashTime <= backgroundTime + TimeUnit.MINUTES.toMillis(5)) ? "unkown" : AppStateModule.APP_STATE_BACKGROUND;
        } else if (this.o <= 0 || currentTimeMillis - this.o <= TimeUnit.MINUTES.toMillis(5)) {
            return (this.n > 0 || this.m <= 0 || currentTimeMillis - this.m <= TimeUnit.MINUTES.toMillis(1)) ? DownloadService.KEY_FOREGROUND : AppStateModule.APP_STATE_BACKGROUND;
        } else {
            return AppStateModule.APP_STATE_BACKGROUND;
        }
    }

    public void adjustRequestSpanByReceived() {
        LogStrategyManager.getInstance().adjustRequestSpanByNetNotMatch();
    }

    public void adjustRequestSpanByNetNotMatch() {
        LogStrategyManager.getInstance().adjustRequestSpanByNetNotMatch();
    }

    public void adjustRequestSpanByZipFail() {
        LogStrategyManager.getInstance().adjustRequestSpanByZipFail();
    }

    public void adjustRequestSpanByUploadFail() {
        LogStrategyManager.getInstance().adjustRequestSpanByUploadFail();
    }

    public void revertRequestSpanToNormal() {
        LogStrategyManager.getInstance().revertRequestSpanToNormal();
    }

    public int getDevicePerformanceScore() {
        try {
            return YearClass.get(this.c);
        } catch (Throwable th) {
            Log.w(f943a, th);
            return Integer.MAX_VALUE;
        }
    }
}
