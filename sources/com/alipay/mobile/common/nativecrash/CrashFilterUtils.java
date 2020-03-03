package com.alipay.mobile.common.nativecrash;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import java.util.regex.Pattern;

public class CrashFilterUtils {
    public static final String FILTER_PROCESS = ">>> %s <<<";
    public static final String FILTER_SIGNAL = "signal 6";

    public static boolean isFilterCrash(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (Pattern.compile(FILTER_SIGNAL).matcher(str).find()) {
                return true;
            }
            if (!Pattern.compile(String.format(FILTER_PROCESS, new Object[]{context.getPackageName()})).matcher(str).find()) {
                return true;
            }
            return false;
        } catch (Throwable unused) {
        }
    }

    public static boolean isTargetProcess(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            boolean find = Pattern.compile(String.format(FILTER_PROCESS, new Object[]{str2})).matcher(str).find();
            TraceLogger traceLogger = LoggerFactory.getTraceLogger();
            traceLogger.info("CrashFilter", str2 + " call 'isTargetProcess' spend " + (SystemClock.elapsedRealtime() - elapsedRealtime));
            return find;
        } catch (Throwable unused) {
            return false;
        }
    }
}
