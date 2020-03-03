package com.alipay.mobile.common.logging.appender;

import android.content.Context;
import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;

public abstract class Appender {

    /* renamed from: a  reason: collision with root package name */
    private final String f954a;
    protected final LogContext context;

    /* access modifiers changed from: protected */
    public abstract boolean onAppend(String str);

    /* access modifiers changed from: protected */
    public abstract boolean onAppend(byte[] bArr);

    public Appender(LogContext logContext, String str) {
        this.context = logContext;
        this.f954a = str;
    }

    public String getLogCategory() {
        return this.f954a;
    }

    public Context getContext() {
        return this.context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void appendLogEvent(LogEvent logEvent) {
        if (logEvent != null) {
            onAppend(logEvent.toString());
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void flush() {
    }

    /* access modifiers changed from: protected */
    public synchronized void backupCurrentFile(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void destroy() {
        Log.w("Appender", getClass().getSimpleName() + " appender destroy: " + getLogCategory());
    }
}
