package com.loopj.android.http;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

public class LogHandler implements LogInterface {
    boolean mLoggingEnabled = true;
    int mLoggingLevel = 2;

    public boolean isLoggingEnabled() {
        return this.mLoggingEnabled;
    }

    public void setLoggingEnabled(boolean z) {
        this.mLoggingEnabled = z;
    }

    public int getLoggingLevel() {
        return this.mLoggingLevel;
    }

    public void setLoggingLevel(int i) {
        this.mLoggingLevel = i;
    }

    public boolean shouldLog(int i) {
        return i >= this.mLoggingLevel;
    }

    public void log(int i, String str, String str2) {
        logWithThrowable(i, str, str2, (Throwable) null);
    }

    public void logWithThrowable(int i, String str, String str2, Throwable th) {
        if (isLoggingEnabled() && shouldLog(i)) {
            switch (i) {
                case 2:
                    Log.v(str, str2, th);
                    return;
                case 3:
                    Log.d(str, str2, th);
                    return;
                case 4:
                    Log.i(str, str2, th);
                    return;
                case 5:
                    Log.w(str, str2, th);
                    return;
                case 6:
                    Log.e(str, str2, th);
                    return;
                case 8:
                    if (Integer.valueOf(Build.VERSION.SDK).intValue() > 8) {
                        checkedWtf(str, str2, th);
                        return;
                    } else {
                        Log.e(str, str2, th);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    @TargetApi(8)
    private void checkedWtf(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    public void v(String str, String str2) {
        log(2, str, str2);
    }

    public void v(String str, String str2, Throwable th) {
        logWithThrowable(2, str, str2, th);
    }

    public void d(String str, String str2) {
        log(2, str, str2);
    }

    public void d(String str, String str2, Throwable th) {
        logWithThrowable(3, str, str2, th);
    }

    public void i(String str, String str2) {
        log(4, str, str2);
    }

    public void i(String str, String str2, Throwable th) {
        logWithThrowable(4, str, str2, th);
    }

    public void w(String str, String str2) {
        log(5, str, str2);
    }

    public void w(String str, String str2, Throwable th) {
        logWithThrowable(5, str, str2, th);
    }

    public void e(String str, String str2) {
        log(6, str, str2);
    }

    public void e(String str, String str2, Throwable th) {
        logWithThrowable(6, str, str2, th);
    }

    public void wtf(String str, String str2) {
        log(8, str, str2);
    }

    public void wtf(String str, String str2, Throwable th) {
        logWithThrowable(8, str, str2, th);
    }
}
