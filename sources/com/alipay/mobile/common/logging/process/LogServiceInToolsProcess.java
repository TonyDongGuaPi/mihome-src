package com.alipay.mobile.common.logging.process;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.util.LoggingSPCache;
import com.taobao.weex.common.Constants;
import com.tencent.smtt.sdk.TbsReaderView;

public class LogServiceInToolsProcess extends IntentService {
    public LogServiceInToolsProcess() {
        super("LogServiceInTools");
    }

    public void onDestroy() {
        super.onDestroy();
        LoggerFactory.getLogContext().flush(false);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (!TextUtils.isEmpty(action) && extras != null) {
                Log.i("LogServiceInTools", "action: " + action);
                if (action.equals(getPackageName() + LogContext.ACTION_UPLOAD_MDAPLOG)) {
                    LoggerFactory.getLogContext().upload(extras.getString("logCategory"));
                    return;
                }
                if (action.equals(getPackageName() + LogContext.ACTION_UPDATE_LOG_STRATEGY)) {
                    LoggerFactory.getLogContext().updateLogStrategyCfg(extras.getString(Constants.Name.STRATEGY));
                    return;
                }
                if (action.equals(getPackageName() + LogContext.ACTION_UPDATE_LOG_CONTEXT)) {
                    String string = extras.getString("type");
                    String string2 = extras.getString("value");
                    if (LoggingSPCache.STORAGE_CHANNELID.equals(string)) {
                        LoggerFactory.getLogContext().setChannelId(string2);
                    } else if (LoggingSPCache.STORAGE_RELEASETYPE.equals(string)) {
                        LoggerFactory.getLogContext().setReleaseType(string2);
                    } else if (LoggingSPCache.STORAGE_RELEASECODE.equals(string)) {
                        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
                        traceLogger.warn("LogServiceInTools", "update ReleaseCode: " + string2);
                        LoggerFactory.getLogContext().setReleaseCode(string2);
                    } else if (LoggingSPCache.STORAGE_PRODUCTID.equals(string)) {
                        LoggerFactory.getLogContext().setProductId(string2);
                    } else if (LoggingSPCache.STORAGE_PRODUCTVERSION.equals(string)) {
                        LoggerFactory.getLogContext().setProductVersion(string2);
                    } else if (LoggingSPCache.STORAGE_USERID.equals(string)) {
                        LoggerFactory.getLogContext().setUserId(string2);
                    } else if (LoggingSPCache.STORAGE_CLIENTID.equals(string)) {
                        LoggerFactory.getLogContext().setClientId(string2);
                    } else if ("utdid".equals(string)) {
                        LoggerFactory.getLogContext().setDeviceId(string2);
                    } else if ("language".equals(string)) {
                        LoggerFactory.getLogContext().setLanguage(string2);
                    } else if (LoggingSPCache.STORAGE_HOTPATCHVERSION.equals(string)) {
                        LoggerFactory.getLogContext().setHotpatchVersion(string2);
                    } else if (LoggingSPCache.STORAGE_PACKAGEID.equals(string)) {
                        LoggerFactory.getLogContext().setPackageId(string2);
                    } else if (LoggingSPCache.STORAGE_BUNDLEVERSION.equals(string)) {
                        LoggerFactory.getLogContext().setBundleVersion(string2);
                    } else if (LoggingSPCache.STORAGE_BIRDNESTVERSION.equals(string)) {
                        LoggerFactory.getLogContext().setBirdNestVersion(string2);
                    } else {
                        TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
                        traceLogger2.error("LogServiceInTools", "not mapping, type: " + string + ", value: " + string2);
                    }
                } else {
                    if (action.equals(getPackageName() + LogContext.ACTION_TRACE_NATIVECRASH)) {
                        LoggerFactory.getLogContext().traceNativeCrash(extras.getString(TbsReaderView.KEY_FILE_PATH), extras.getString("callStack"), extras.getBoolean("isBoot"));
                        return;
                    }
                    TraceLogger traceLogger3 = LoggerFactory.getTraceLogger();
                    traceLogger3.error("LogServiceInTools", "no such action: " + action);
                }
            }
        }
    }
}
