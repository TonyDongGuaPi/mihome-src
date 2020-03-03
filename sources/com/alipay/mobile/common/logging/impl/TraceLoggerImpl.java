package com.alipay.mobile.common.logging.impl;

import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.trace.TraceLogger;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.taobao.weex.el.parse.Operators;

public class TraceLoggerImpl implements TraceLogger {

    /* renamed from: a  reason: collision with root package name */
    private LogContext f965a;
    private boolean b = false;
    private StringBuffer c = new StringBuffer();

    public TraceLoggerImpl(LogContext logContext) {
        this.f965a = logContext;
        this.b = logContext.isDebuggable();
    }

    public void info(String str, String str2) {
        if (this.b) {
            Log.i(str, a(str2));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.INFO, str2));
    }

    public void verbose(String str, String str2) {
        if (this.b) {
            Log.v(str, a(str2));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.VERBOSE, str2));
    }

    public void debug(String str, String str2) {
        if (this.b) {
            Log.d(str, a(str2));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.DEBUG, str2));
    }

    public void warn(String str, String str2) {
        if (this.b) {
            Log.w(str, a(str2));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.WARN, str2));
    }

    public void warn(String str, Throwable th) {
        String throwableToString = LoggingUtil.throwableToString(th);
        if (this.b) {
            Log.w(str, a(throwableToString));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.WARN, throwableToString));
    }

    public void error(String str, String str2) {
        if (this.b) {
            Log.e(str, a(str2));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.ERROR, str2));
    }

    public void error(String str, Throwable th) {
        String throwableToString = LoggingUtil.throwableToString(th);
        if (this.b) {
            Log.e(str, a(throwableToString));
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.ERROR, throwableToString));
    }

    public void error(String str, String str2, Throwable th) {
        String str3;
        String throwableToString = LoggingUtil.throwableToString(th);
        if (this.b) {
            if (str2 != null) {
                str3 = str2 + a(throwableToString);
            } else {
                str3 = a(throwableToString);
            }
            Log.e(str, str3);
        }
        this.f965a.appendLogEvent(new TraceLogEvent(str, LogEvent.Level.ERROR, str2 + throwableToString));
    }

    public void print(String str, String str2) {
        if (this.b) {
            Log.v(str, str2);
        }
    }

    public void print(String str, Throwable th) {
        if (this.b) {
            Log.v(str, "", th);
        }
    }

    private String a(String str) {
        this.c.setLength(0);
        StringBuffer stringBuffer = this.c;
        stringBuffer.append(Operators.ARRAY_START);
        stringBuffer.append(Thread.currentThread().getName());
        stringBuffer.append("] ");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
