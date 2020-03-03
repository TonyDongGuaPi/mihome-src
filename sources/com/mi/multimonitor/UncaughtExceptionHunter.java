package com.mi.multimonitor;

import java.lang.Thread;

public class UncaughtExceptionHunter implements Thread.UncaughtExceptionHandler {
    private static volatile boolean mCrashing = false;
    private ICrashDataSender mCrashSender;
    private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    UncaughtExceptionHunter(ICrashDataSender iCrashDataSender) {
        this.mCrashSender = iCrashDataSender;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (!mCrashing) {
            mCrashing = true;
            handleException(thread, th);
            if (this.mDefaultHandler != null) {
                this.mDefaultHandler.uncaughtException(thread, th);
            }
        }
    }

    private void handleException(Thread thread, Throwable th) {
        this.mCrashSender.postCrashData(thread, th);
    }
}
