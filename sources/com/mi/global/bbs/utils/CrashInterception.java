package com.mi.global.bbs.utils;

import com.mi.multimonitor.CrashReport;
import java.lang.Thread;

public class CrashInterception {
    /* access modifiers changed from: private */
    public static String communityPackageName = "com.mi.global.bbs";
    private static Thread.UncaughtExceptionHandler mCaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable th) {
            if (th != null) {
                try {
                    if (th.getStackTrace().length > 0) {
                        boolean z = false;
                        for (String str : CrashInterception.systemCrashPackageNameList) {
                            int i = 0;
                            while (i < th.getStackTrace().length) {
                                StackTraceElement stackTraceElement = th.getStackTrace()[i];
                                if (stackTraceElement.getClassName() == null || !stackTraceElement.getClassName().startsWith(str)) {
                                    i++;
                                } else {
                                    CrashReport.get();
                                    CrashReport.postCrash(thread, th);
                                    return;
                                }
                            }
                        }
                        int i2 = 0;
                        while (true) {
                            if (i2 >= th.getStackTrace().length) {
                                break;
                            }
                            StackTraceElement stackTraceElement2 = th.getStackTrace()[i2];
                            if (stackTraceElement2.getClassName() != null && stackTraceElement2.getClassName().startsWith(CrashInterception.communityPackageName)) {
                                z = true;
                                break;
                            }
                            i2++;
                        }
                        if (!z) {
                            Throwable th2 = new Throwable(th.getMessage() + " \n*************  Current  Activity Name ************ + " + Utils.getTopActivity(), th.getCause());
                            th2.setStackTrace(th.getStackTrace());
                            CrashInterception.mDefaultUEH.uncaughtException(thread, th2);
                            return;
                        }
                        CrashInterception.mDefaultUEH.uncaughtException(thread, th);
                    }
                } catch (Exception unused) {
                    CrashInterception.mDefaultUEH.uncaughtException(thread, th);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public static Thread.UncaughtExceptionHandler mDefaultUEH;
    /* access modifiers changed from: private */
    public static String[] systemCrashPackageNameList = {"com.google.android.gms", "android.webkit.WebViewFactory.getWebViewContextAndSetProvider", "org.chromium.base.ResourceExtractor$ExtractTask.extractResourceHelper"};

    public static void initCatchGMSException() {
        mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(mCaughtExceptionHandler);
    }
}
