package com.alipay.mobile.common.logging.strategy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.ContextInfo;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.http.HttpClient;
import com.alipay.mobile.common.logging.util.LoggingSPCache;
import com.alipay.mobile.common.logging.util.NetUtil;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class LogStrategyManager {
    public static final String ACTION_TYPE_BOOT = "boot";
    public static final String ACTION_TYPE_FEEDBACK = "feedback";
    public static final String ACTION_TYPE_LEAVEHINT = "leavehint";
    public static final String ACTION_TYPE_LOGIN = "login";
    public static final String ACTION_TYPE_MDAPUPLOAD = "mdapupload";
    public static final String ACTION_TYPE_TIMEOUT = "timeout";
    public static final long MINIMUM_REQUEST_TIME_SPAN = TimeUnit.MINUTES.toMillis(3);
    public static final String SP_STRATEGY_KEY_NETWORK = "Network";
    public static final String SP_STRATEGY_KEY_THRESHOLD = "Threshold";
    public static final String SP_STRATEGY_KEY_TRIGGER = "Trigger";

    /* renamed from: a  reason: collision with root package name */
    private static LogStrategyManager f966a;
    private static final long b = TimeUnit.SECONDS.toMillis(5);
    private static final long c = TimeUnit.MINUTES.toMillis(30);
    private static final long d = TimeUnit.HOURS.toMillis(1);
    private static long e = c;
    private Context f;
    private ContextInfo g;
    private Map<String, LogStrategyInfo> h = new ConcurrentHashMap();
    private long i;
    private int j;
    private int k;
    private int l;
    private int m;

    public void adjustRequestSpanByNetNotMatch() {
    }

    public void adjustRequestSpanByReceived() {
    }

    public void adjustRequestSpanByUploadFail() {
    }

    public void adjustRequestSpanByZipFail() {
    }

    public boolean isEnableTrafficLimit() {
        return true;
    }

    public static synchronized void createInstance(Context context, ContextInfo contextInfo) {
        synchronized (LogStrategyManager.class) {
            if (f966a == null) {
                f966a = new LogStrategyManager(context, contextInfo);
            }
        }
    }

    public static LogStrategyManager getInstance() {
        if (f966a != null) {
            return f966a;
        }
        throw new IllegalStateException("need createInstance before use");
    }

    private LogStrategyManager(Context context, ContextInfo contextInfo) {
        this.f = context;
        this.g = contextInfo;
        try {
            c(context.getSharedPreferences("LogStrategyConfig", 4).getString("StrategConfigContent", (String) null));
        } catch (Throwable th) {
            LoggerFactory.getTraceLogger().error("LogStrategyMgr", th);
        }
    }

    public boolean isLogWrite(String str) {
        LogStrategyInfo logStrategyInfo = this.h.get(str);
        if (logStrategyInfo != null) {
            return logStrategyInfo.isWrite;
        }
        if ("crash".equalsIgnoreCase(str)) {
            long currentTimeMillis = System.currentTimeMillis();
            SharedPreferences sharedPreferences = this.f.getSharedPreferences("CrashCountInfo", 4);
            long millis = currentTimeMillis / TimeUnit.HOURS.toMillis(1);
            if (millis != sharedPreferences.getLong("curCrashHour", 0)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong("curCrashHour", millis);
                edit.putInt("curCrashHourCount", 1);
                edit.commit();
            } else {
                int i2 = sharedPreferences.getInt("curCrashHourCount", 0) + 1;
                if (i2 > 50) {
                    TraceLogger traceLogger = LoggerFactory.getTraceLogger();
                    traceLogger.error("LogStrategyMgr", "crash count beyound hour limit:" + i2);
                    return false;
                }
                sharedPreferences.edit().putInt("curCrashHourCount", i2).commit();
            }
            long millis2 = currentTimeMillis / TimeUnit.MINUTES.toMillis(1);
            if (millis2 != sharedPreferences.getLong("curCrashMinute", 0)) {
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                edit2.putLong("curCrashMinute", millis2);
                edit2.putInt("curCrashMinuteCount", 1);
                edit2.commit();
            } else {
                int i3 = sharedPreferences.getInt("curCrashMinuteCount", 0) + 1;
                if (i3 > 2) {
                    TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
                    traceLogger2.error("LogStrategyMgr", "crash count beyound minute limit:" + i3);
                    return false;
                }
                sharedPreferences.edit().putInt("curCrashMinuteCount", i3).commit();
            }
            return true;
        } else if (!LogCategory.CATEGORY_KEYBIZTRACE.equalsIgnoreCase(str)) {
            return !LogCategory.CATEGORY_SDKMONITOR.equalsIgnoreCase(str) && !LogCategory.CATEGORY_ROMESYNC.equalsIgnoreCase(str);
        } else {
            long currentTimeMillis2 = System.currentTimeMillis();
            SharedPreferences sharedPreferences2 = this.f.getSharedPreferences("KeyBizInfo", 4);
            long millis3 = currentTimeMillis2 / TimeUnit.DAYS.toMillis(1);
            if (millis3 != sharedPreferences2.getLong("curKeyBizDay", 0)) {
                SharedPreferences.Editor edit3 = sharedPreferences2.edit();
                edit3.putLong("curKeyBizDay", millis3);
                edit3.putInt("curKeyBizDayCount", 1);
                edit3.commit();
            } else {
                int i4 = sharedPreferences2.getInt("curKeyBizDayCount", 0) + 1;
                if (i4 > 200) {
                    TraceLogger traceLogger3 = LoggerFactory.getTraceLogger();
                    traceLogger3.error("LogStrategyMgr", "key biz trace count beyound day limit:" + i4);
                    return false;
                }
                sharedPreferences2.edit().putInt("curKeyBizDayCount", i4).commit();
            }
            return true;
        }
    }

    public String isLogSend(String str, String str2) {
        LogStrategyInfo logStrategyInfo;
        String[] split = str.split(JSMethod.NOT_SET);
        if (split.length < 3) {
            return null;
        }
        String str3 = split[2];
        if (str2 != null && !str2.equals(str3)) {
            return null;
        }
        NetworkInfo activeNetworkInfo = NetUtil.getActiveNetworkInfo(this.f);
        if (!(activeNetworkInfo == null || (logStrategyInfo = this.h.get(str3)) == null)) {
            if (activeNetworkInfo.getType() == 0 && !logStrategyInfo.sendCondition.contains("mobile")) {
                return null;
            }
            if (activeNetworkInfo.getType() != 1 || logStrategyInfo.sendCondition.contains("wifi")) {
                return str3;
            }
            return null;
        }
        return str3;
    }

    public boolean isLogUpload(String str, String str2) {
        LogStrategyInfo logStrategyInfo = this.h.get(str);
        List list = logStrategyInfo != null ? logStrategyInfo.uploadEvents : null;
        if (list == null) {
            list = new ArrayList();
        }
        if (list.isEmpty()) {
            if (LogCategory.CATEGORY_ALIVEREPORT.equals(str) || LogCategory.CATEGORY_PERFORMANCE.equals(str) || LogCategory.CATEGORY_USERBEHAVOR.equals(str) || LogCategory.CATEGORY_AUTOUSERBEHAVOR.equals(str)) {
                list.add("gotoBackground");
            } else if ("crash".equals(str) || LogCategory.CATEGORY_APM.equals(str)) {
                list.add("gotoBackground");
                list.add(LogContext.ENVENT_CLIENTLAUNCH);
            }
        }
        return list.contains(str2);
    }

    public boolean isLogUpload(String str, int i2) {
        int i3;
        if (LogCategory.CATEGORY_USERBEHAVOR.equals(str) || LogCategory.CATEGORY_AUTOUSERBEHAVOR.equals(str) || LogCategory.CATEGORY_EXCEPTION.equals(str) || LogCategory.CATEGORY_NETWORK.equals(str)) {
            i3 = 50;
        } else {
            i3 = LogCategory.CATEGORY_ALIVEREPORT.equals(str) ? 10 : ("crash".equals(str) || LogCategory.CATEGORY_APM.equals(str) || LogCategory.CATEGORY_KEYBIZTRACE.equals(str)) ? 1 : 100;
        }
        LogStrategyInfo logStrategyInfo = this.h.get(str);
        if (logStrategyInfo != null && logStrategyInfo.threshold > 0) {
            i3 = logStrategyInfo.threshold;
        }
        if (i2 >= i3) {
            return true;
        }
        return false;
    }

    public void revertRequestSpanToNormal() {
        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
        traceLogger.info("LogStrategyMgr", "revertRequestSpanToNormal: " + e);
        this.f.getSharedPreferences("LogStrategyConfig", 4).edit().putLong("CurrentRequestTimeSpan", e).commit();
    }

    public void queryStrategy(String str, boolean z) {
        if (z) {
            a(str);
            return;
        }
        SharedPreferences sharedPreferences = this.f.getSharedPreferences("LogStrategyConfig", 4);
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = sharedPreferences.getLong("PreviousRequestTime", 0);
        if (Math.abs(currentTimeMillis - j2) < sharedPreferences.getLong("CurrentRequestTimeSpan", e)) {
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            traceLogger.info("LogStrategyMgr", "queryStrategy returned by span: " + str);
        } else if (Math.abs(currentTimeMillis - this.i) < b) {
            TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
            traceLogger2.error("LogStrategyMgr", "queryStrategy returned by twice: " + str);
        } else {
            this.i = currentTimeMillis;
            if (NetUtil.isNetworkConnected(this.f)) {
                a();
            }
            a(str);
        }
    }

    private void a(final String str) {
        new Thread(new Runnable() {
            public void run() {
                LogStrategyManager.this.b(str);
            }
        }, "LogStrategyMgr.request").start();
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
        traceLogger.warn("LogStrategyMgr", "syncRequestLogConfig: " + str);
        try {
            String logHost = LoggerFactory.getLogContext().getLogHost();
            if (TextUtils.isEmpty(logHost)) {
                LoggerFactory.getTraceLogger().error("LogStrategyMgr", "syncRequestLogConfig: host is none");
                return;
            }
            HttpClient httpClient = new HttpClient(logHost + "/loggw/config.do", this.f);
            HashMap hashMap = new HashMap();
            hashMap.put(TSMAuthContants.PARAM_ACTION_TYPE, str);
            hashMap.put("userId", this.g.getUserId());
            hashMap.put(MifareCardInfo.KEY_PRODUCT_ID, this.g.getProductId());
            hashMap.put(LoggingSPCache.STORAGE_PRODUCTVERSION, this.g.getProductVersion());
            hashMap.put("processName", LoggerFactory.getProcessInfo().getProcessAlias());
            HttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.synchronousRequestByGET(hashMap);
            } catch (Throwable unused) {
            }
            if (httpResponse == null) {
                httpClient.closeStreamForNextExecute();
                LoggerFactory.getTraceLogger().error("LogStrategyMgr", "syncRequestLogConfig: response is NULL, network error");
                return;
            }
            a();
            int responseCode = httpClient.getResponseCode();
            String responseString = httpClient.getResponseString();
            httpClient.closeStreamForNextExecute();
            if (responseCode == 200) {
                if (!TextUtils.isEmpty(responseString)) {
                    JSONObject jSONObject = new JSONObject(responseString);
                    int i2 = jSONObject.getInt("code");
                    if (i2 != 200) {
                        TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
                        traceLogger2.error("LogStrategyMgr", "syncRequestLogConfig: serverLogicCode is " + i2);
                        return;
                    }
                    if (jSONObject.has("diagnose")) {
                        LoggerFactory.getTraceLogger().info("LogStrategyMgr", "syncRequestLogConfig: has diagnose tasks");
                        JSONArray jSONArray = jSONObject.getJSONArray("diagnose");
                        if (jSONArray != null) {
                            Intent intent = new Intent();
                            intent.setClassName(this.f, LogContext.PUSH_SERVICE_CLASS_NAME);
                            intent.setAction(this.f.getPackageName() + ".push.action.MONITOR_RECEIVED");
                            intent.putExtra("config_msg_tasks", jSONArray.toString());
                            intent.putExtra("config_msg_userid", this.g.getUserId());
                            if (this.f.startService(intent) == null) {
                                LoggerFactory.getTraceLogger().error("LogStrategyMgr", "syncRequestLogConfig: start service for diagnose occured error");
                            }
                        }
                    }
                    try {
                        if (jSONObject.has("config")) {
                            LoggerFactory.getTraceLogger().info("LogStrategyMgr", "syncRequestLogConfig: has configs");
                            String string = jSONObject.getString("config");
                            updateLogStrategy(string);
                            if (LoggerFactory.getProcessInfo().isMainProcess()) {
                                a(LogContext.PUSH_SERVICE_CLASS_NAME, string);
                                if (!isDisableToolsProcess()) {
                                    a(LogContext.TOOLS_SERVICE_CLASS_NAME, string);
                                    return;
                                }
                                return;
                            } else if (LoggerFactory.getProcessInfo().isPushProcess()) {
                                if (!isDisableToolsProcess()) {
                                    a(LogContext.TOOLS_SERVICE_CLASS_NAME, string);
                                    return;
                                }
                                return;
                            } else if (!LoggerFactory.getProcessInfo().isToolsProcess()) {
                                TraceLogger traceLogger3 = LoggerFactory.getTraceLogger();
                                traceLogger3.error("LogStrategyMgr", "syncRequestLogConfig, error: unknown process " + LoggerFactory.getProcessInfo().getProcessAlias());
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        LoggerFactory.getTraceLogger().error("LogStrategyMgr", "syncRequestLogConfig", th);
                        return;
                    }
                }
            }
            TraceLogger traceLogger4 = LoggerFactory.getTraceLogger();
            traceLogger4.error("LogStrategyMgr", "syncRequestLogConfig: response is none, or responseCode is " + responseCode);
            LoggerFactory.getMonitorLogger().footprint("LogStrategy", "LogConfig", "ResponseCode", String.valueOf(responseCode), "or response is none", (Map<String, String>) null);
        } catch (Throwable th2) {
            LoggerFactory.getTraceLogger().error("LogStrategyMgr", "syncRequestLogConfig", th2);
        }
    }

    private void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Intent intent = new Intent();
            intent.setClassName(this.f, str);
            intent.setAction(this.f.getPackageName() + LogContext.ACTION_UPDATE_LOG_STRATEGY);
            intent.putExtra(Constants.Name.STRATEGY, str2);
            try {
                intent.setPackage(this.f.getPackageName());
            } catch (Throwable th) {
                Log.w("LogStrategyMgr", th);
            }
            try {
                if (this.f.startService(intent) == null) {
                    LoggerFactory.getTraceLogger().error("LogStrategyMgr", "notifyOtherProcessToUpdateLogStrategy: start service occured error");
                }
            } catch (Throwable th2) {
                LoggerFactory.getTraceLogger().error("LogStrategyMgr", "notifyOtherProcessToUpdateLogStrategy", th2);
            }
        }
    }

    public void updateBackgroundTime(long j2) {
        this.f.getSharedPreferences("CrashCountInfo", 4).edit().putLong("backgroundTimestamp", j2).commit();
    }

    public long getBackgroundTime() {
        return this.f.getSharedPreferences("CrashCountInfo", 4).getLong("backgroundTimestamp", 0);
    }

    public void updateLogStrategy(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f.getSharedPreferences("LogStrategyConfig", 4).edit().putString("StrategConfigContent", str).commit();
            try {
                c(str);
            } catch (Throwable th) {
                LoggerFactory.getTraceLogger().error("LogStrategyMgr", th);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:69|70|71|72|73|74|75|(4:78|(2:84|132)(1:133)|85|76)|131|(1:87)|88|90|91|(3:94|95|92)|134|96|98|99|100|101) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:100:0x0185 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(java.lang.String r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 == 0) goto L_0x000b
            return
        L_0x000b:
            com.alipay.mobile.common.logging.api.trace.TraceLogger r2 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()
            java.lang.String r3 = "LogStrategyMgr"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "parseLogStrategy: "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            r2.info(r3, r4)
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>(r0)
            java.util.Iterator r3 = r2.keys()
            r5 = 1
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x0034:
            boolean r0 = r3.hasNext()
            r11 = 2
            if (r0 == 0) goto L_0x019a
            java.lang.Object r0 = r3.next()     // Catch:{ Throwable -> 0x018b }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x018b }
            r12 = 0
            org.json.JSONObject r13 = r2.getJSONObject(r0)     // Catch:{ Throwable -> 0x0047 }
            r12 = r13
        L_0x0047:
            if (r12 != 0) goto L_0x004b
            goto L_0x0197
        L_0x004b:
            java.lang.String r13 = "positiveDiagnoseLog"
            boolean r13 = r13.equalsIgnoreCase(r0)     // Catch:{ Throwable -> 0x018b }
            if (r13 == 0) goto L_0x00ea
            java.lang.String r0 = "event"
            boolean r0 = r12.has(r0)     // Catch:{ Throwable -> 0x018b }
            if (r0 == 0) goto L_0x00e5
            r0 = 3
            r1.j = r0     // Catch:{ Throwable -> 0x018b }
            java.lang.String r0 = "send"
            boolean r0 = r12.has(r0)     // Catch:{ Throwable -> 0x0090 }
            if (r0 == 0) goto L_0x0096
            java.lang.String r0 = "send"
            org.json.JSONArray r0 = r12.getJSONArray(r0)     // Catch:{ Throwable -> 0x0090 }
            r13 = 0
        L_0x006d:
            int r14 = r0.length()     // Catch:{ Throwable -> 0x0090 }
            if (r13 >= r14) goto L_0x0096
            java.lang.String r14 = r0.getString(r13)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r15 = "none"
            boolean r15 = r15.equalsIgnoreCase(r14)     // Catch:{ Throwable -> 0x0090 }
            if (r15 == 0) goto L_0x0082
            r1.j = r5     // Catch:{ Throwable -> 0x0090 }
            goto L_0x0096
        L_0x0082:
            java.lang.String r15 = "wifi"
            boolean r14 = r15.equalsIgnoreCase(r14)     // Catch:{ Throwable -> 0x0090 }
            if (r14 == 0) goto L_0x008d
            r1.j = r11     // Catch:{ Throwable -> 0x0090 }
        L_0x008d:
            int r13 = r13 + 1
            goto L_0x006d
        L_0x0090:
            r0 = move-exception
            java.lang.String r11 = "LogStrategyMgr"
            android.util.Log.w(r11, r0)     // Catch:{ Throwable -> 0x018b }
        L_0x0096:
            int r0 = r1.j     // Catch:{ Throwable -> 0x00de }
            if (r0 == r5) goto L_0x00e7
            java.lang.String r0 = "upInterval"
            boolean r0 = r12.has(r0)     // Catch:{ Throwable -> 0x00de }
            if (r0 == 0) goto L_0x00e7
            java.lang.String r0 = "upInterval"
            long r11 = r12.getLong(r0)     // Catch:{ Throwable -> 0x00de }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MINUTES     // Catch:{ Throwable -> 0x00de }
            r13 = 1
            long r13 = r0.toMillis(r13)     // Catch:{ Throwable -> 0x00de }
            long r11 = r11 * r13
            e = r11     // Catch:{ Throwable -> 0x00de }
            long r11 = e     // Catch:{ Throwable -> 0x00de }
            r13 = 0
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 > 0) goto L_0x00c3
            long r11 = c     // Catch:{ Throwable -> 0x00de }
            e = r11     // Catch:{ Throwable -> 0x00de }
            goto L_0x00dc
        L_0x00c3:
            long r11 = e     // Catch:{ Throwable -> 0x00de }
            long r13 = MINIMUM_REQUEST_TIME_SPAN     // Catch:{ Throwable -> 0x00de }
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x00d0
            long r11 = MINIMUM_REQUEST_TIME_SPAN     // Catch:{ Throwable -> 0x00de }
            e = r11     // Catch:{ Throwable -> 0x00de }
            goto L_0x00dc
        L_0x00d0:
            long r11 = e     // Catch:{ Throwable -> 0x00de }
            long r13 = d     // Catch:{ Throwable -> 0x00de }
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 <= 0) goto L_0x00dc
            long r11 = d     // Catch:{ Throwable -> 0x00de }
            e = r11     // Catch:{ Throwable -> 0x00de }
        L_0x00dc:
            r7 = 1
            goto L_0x00e7
        L_0x00de:
            r0 = move-exception
            java.lang.String r11 = "LogStrategyMgr"
            android.util.Log.w(r11, r0)     // Catch:{ Throwable -> 0x018b }
            goto L_0x00e7
        L_0x00e5:
            r1.j = r5     // Catch:{ Throwable -> 0x018b }
        L_0x00e7:
            r6 = 1
            goto L_0x0034
        L_0x00ea:
            java.lang.String r13 = "zipAndSevenZip"
            boolean r13 = r13.equalsIgnoreCase(r0)     // Catch:{ Throwable -> 0x018b }
            if (r13 == 0) goto L_0x00f8
            r1.k = r11     // Catch:{ Throwable -> 0x018b }
            r8 = 1
            goto L_0x0034
        L_0x00f8:
            java.lang.String r13 = "disableToolsProcess"
            boolean r13 = r13.equalsIgnoreCase(r0)     // Catch:{ Throwable -> 0x018b }
            if (r13 == 0) goto L_0x0105
            r1.l = r11     // Catch:{ Throwable -> 0x018b }
            r9 = 1
            goto L_0x0034
        L_0x0105:
            java.lang.String r11 = "enableTrafficLimit"
            boolean r11 = r11.equalsIgnoreCase(r0)     // Catch:{ Throwable -> 0x018b }
            if (r11 == 0) goto L_0x0112
            r1.m = r5     // Catch:{ Throwable -> 0x018b }
            r10 = 1
            goto L_0x0034
        L_0x0112:
            com.alipay.mobile.common.logging.strategy.LogStrategyInfo r11 = new com.alipay.mobile.common.logging.strategy.LogStrategyInfo     // Catch:{ Throwable -> 0x018b }
            r11.<init>()     // Catch:{ Throwable -> 0x018b }
            java.lang.String r13 = "yes"
            java.lang.String r14 = "write"
            java.lang.String r14 = r12.getString(r14)     // Catch:{ Throwable -> 0x0197 }
            boolean r13 = r13.equalsIgnoreCase(r14)     // Catch:{ Throwable -> 0x0197 }
            r11.isWrite = r13     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r13 = "send"
            org.json.JSONArray r13 = r12.getJSONArray(r13)     // Catch:{ Throwable -> 0x0164 }
            r14 = 0
            r15 = 0
        L_0x012f:
            int r4 = r13.length()     // Catch:{ Throwable -> 0x0164 }
            if (r14 >= r4) goto L_0x015b
            java.lang.String r4 = r13.getString(r14)     // Catch:{ Throwable -> 0x0164 }
            java.util.List<java.lang.String> r5 = r11.sendCondition     // Catch:{ Throwable -> 0x0164 }
            r5.add(r4)     // Catch:{ Throwable -> 0x0164 }
            java.lang.String r5 = "2g"
            boolean r5 = r5.equalsIgnoreCase(r4)     // Catch:{ Throwable -> 0x0164 }
            if (r5 != 0) goto L_0x0156
            java.lang.String r5 = "3g"
            boolean r5 = r5.equalsIgnoreCase(r4)     // Catch:{ Throwable -> 0x0164 }
            if (r5 != 0) goto L_0x0156
            java.lang.String r5 = "4g"
            boolean r4 = r5.equalsIgnoreCase(r4)     // Catch:{ Throwable -> 0x0164 }
            if (r4 == 0) goto L_0x0157
        L_0x0156:
            r15 = 1
        L_0x0157:
            int r14 = r14 + 1
            r5 = 1
            goto L_0x012f
        L_0x015b:
            if (r15 == 0) goto L_0x0164
            java.util.List<java.lang.String> r4 = r11.sendCondition     // Catch:{ Throwable -> 0x0164 }
            java.lang.String r5 = "mobile"
            r4.add(r5)     // Catch:{ Throwable -> 0x0164 }
        L_0x0164:
            java.lang.String r4 = "event"
            org.json.JSONArray r4 = r12.getJSONArray(r4)     // Catch:{ Throwable -> 0x017d }
            r5 = 0
        L_0x016b:
            int r13 = r4.length()     // Catch:{ Throwable -> 0x017d }
            if (r5 >= r13) goto L_0x017d
            java.lang.String r13 = r4.getString(r5)     // Catch:{ Throwable -> 0x017d }
            java.util.List<java.lang.String> r14 = r11.uploadEvents     // Catch:{ Throwable -> 0x017d }
            r14.add(r13)     // Catch:{ Throwable -> 0x017d }
            int r5 = r5 + 1
            goto L_0x016b
        L_0x017d:
            java.lang.String r4 = "maxLogCount"
            int r4 = r12.getInt(r4)     // Catch:{ Throwable -> 0x0185 }
            r11.threshold = r4     // Catch:{ Throwable -> 0x0185 }
        L_0x0185:
            java.util.Map<java.lang.String, com.alipay.mobile.common.logging.strategy.LogStrategyInfo> r4 = r1.h     // Catch:{ Throwable -> 0x018b }
            r4.put(r0, r11)     // Catch:{ Throwable -> 0x018b }
            goto L_0x0197
        L_0x018b:
            r0 = move-exception
            com.alipay.mobile.common.logging.api.trace.TraceLogger r4 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()
            java.lang.String r5 = "LogStrategyMgr"
            java.lang.String r11 = "parseLogStrategy"
            r4.error(r5, r11, r0)
        L_0x0197:
            r5 = 1
            goto L_0x0034
        L_0x019a:
            if (r6 != 0) goto L_0x01a0
            r2 = 1
            r1.j = r2
            goto L_0x01a1
        L_0x01a0:
            r2 = 1
        L_0x01a1:
            int r0 = r1.j
            if (r0 == r2) goto L_0x01a7
            if (r7 != 0) goto L_0x01ab
        L_0x01a7:
            long r3 = c
            e = r3
        L_0x01ab:
            if (r8 != 0) goto L_0x01af
            r1.k = r2
        L_0x01af:
            if (r9 != 0) goto L_0x01b3
            r1.l = r2
        L_0x01b3:
            if (r10 != 0) goto L_0x01b7
            r1.m = r11
        L_0x01b7:
            android.content.Context r0 = r1.f
            java.lang.String r2 = "LogStrategyConfig"
            r3 = 4
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r2, r3)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            java.lang.String r2 = "PositiveDiagnose"
            int r3 = r1.j
            r0.putInt(r2, r3)
            java.lang.String r2 = "CurrentRequestTimeSpan"
            long r3 = e
            r0.putLong(r2, r3)
            java.lang.String r2 = "ZipAndSevenZip"
            int r3 = r1.k
            r0.putInt(r2, r3)
            java.lang.String r2 = "DisableToolsProcess"
            int r3 = r1.l
            r0.putInt(r2, r3)
            java.lang.String r2 = "EnableTrafficLimit"
            int r3 = r1.m
            r0.putInt(r2, r3)
            r0.commit()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "parseLogStrategy"
            r0.<init>(r2)
            java.lang.String r2 = ", positiveDiagnoseTag: "
            r0.append(r2)
            int r2 = r1.j
            r0.append(r2)
            java.lang.String r2 = ", CURRENT_REQUEST_TIME_SPAN: "
            r0.append(r2)
            long r2 = e
            r0.append(r2)
            java.lang.String r2 = ", zipAndSevenZipTag: "
            r0.append(r2)
            int r2 = r1.k
            r0.append(r2)
            java.lang.String r2 = ", disableToolsProcessTag: "
            r0.append(r2)
            int r2 = r1.l
            r0.append(r2)
            java.lang.String r2 = ", enableTrafficLimitTag: "
            r0.append(r2)
            int r2 = r1.m
            r0.append(r2)
            com.alipay.mobile.common.logging.api.trace.TraceLogger r2 = com.alipay.mobile.common.logging.api.LoggerFactory.getTraceLogger()
            java.lang.String r3 = "LogStrategyMgr"
            java.lang.String r0 = r0.toString()
            r2.info(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.strategy.LogStrategyManager.c(java.lang.String):void");
    }

    public boolean isPositiveDiagnose() {
        if (this.j == 0) {
            this.j = this.f.getSharedPreferences("LogStrategyConfig", 4).getInt("PositiveDiagnose", 1);
        }
        if (this.j == 2) {
            NetworkInfo activeNetworkInfo = NetUtil.getActiveNetworkInfo(this.f);
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return false;
            }
            return true;
        } else if (this.j == 3) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isZipAndSevenZip() {
        if (this.k == 0) {
            this.k = this.f.getSharedPreferences("LogStrategyConfig", 4).getInt("ZipAndSevenZip", 1);
        }
        if (this.k == 2) {
            return true;
        }
        return false;
    }

    public boolean isDisableToolsProcess() {
        if (this.l == 0) {
            this.l = this.f.getSharedPreferences("LogStrategyConfig", 4).getInt("DisableToolsProcess", 1);
        }
        if (this.l == 2) {
            return true;
        }
        return false;
    }

    private void a() {
        this.f.getSharedPreferences("LogStrategyConfig", 4).edit().putLong("PreviousRequestTime", System.currentTimeMillis()).commit();
        revertRequestSpanToNormal();
    }
}
