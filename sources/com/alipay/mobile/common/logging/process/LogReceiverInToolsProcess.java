package com.alipay.mobile.common.logging.process;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.tencent.smtt.sdk.TbsReaderView;

public class LogReceiverInToolsProcess extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (!TextUtils.isEmpty(action) && extras != null) {
                Log.i("LogReceiverInTools", "action: " + action);
                if (action.equals(context.getPackageName() + LogContext.ACTION_MONITOR_COMMAND)) {
                    String string = extras.getString("action");
                    if (TextUtils.isEmpty(string)) {
                        TraceLogger traceLogger = LoggerFactory.getTraceLogger();
                        traceLogger.error("LogReceiverInTools", "none extra action: " + action);
                        return;
                    }
                    String string2 = extras.getString(TbsReaderView.KEY_FILE_PATH);
                    String string3 = extras.getString("callStack");
                    boolean z = extras.getBoolean("isBoot");
                    Intent intent2 = new Intent();
                    intent2.setClassName(context, LogContext.TOOLS_SERVICE_CLASS_NAME);
                    intent2.setAction(string);
                    intent2.putExtra(TbsReaderView.KEY_FILE_PATH, string2);
                    intent2.putExtra("callStack", string3);
                    intent2.putExtra("isBoot", z);
                    try {
                        if (context.startService(intent2) == null) {
                            TraceLogger traceLogger2 = LoggerFactory.getTraceLogger();
                            traceLogger2.error("LogReceiverInTools", "fail to start LogServiceInToolsProcess: " + action);
                        }
                    } catch (Throwable th) {
                        LoggerFactory.getTraceLogger().error("LogReceiverInTools", th);
                    }
                } else {
                    TraceLogger traceLogger3 = LoggerFactory.getTraceLogger();
                    traceLogger3.error("LogReceiverInTools", "no such action: " + action);
                }
            }
        }
    }
}
