package com.facebook.internal.instrument.crashreport;

import android.os.Process;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final int MAX_CRASH_REPORT_NUM = 5;
    private static final String TAG = CrashHandler.class.getCanonicalName();
    @Nullable
    private static CrashHandler instance;
    private boolean mEndApplication = false;
    @Nullable
    private final Thread.UncaughtExceptionHandler mPreviousHandler;

    private CrashHandler(@Nullable Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.mPreviousHandler = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (InstrumentUtility.isSDKRelatedException(th)) {
            new CrashReportData(th).save();
        }
        if (this.mPreviousHandler != null) {
            this.mPreviousHandler.uncaughtException(thread, th);
        }
        if (this.mEndApplication) {
            killProcess();
        }
    }

    public static synchronized void enable() {
        synchronized (CrashHandler.class) {
            if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                sendCrashReports();
            }
            if (instance != null) {
                Log.w(TAG, "Already enabled!");
                return;
            }
            instance = new CrashHandler(Thread.getDefaultUncaughtExceptionHandler());
            Thread.setDefaultUncaughtExceptionHandler(instance);
        }
    }

    public void endApplication() {
        this.mEndApplication = true;
    }

    private static void killProcess() {
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable unused) {
        }
    }

    private static void sendCrashReports() {
        File[] listCrashReportFiles = InstrumentUtility.listCrashReportFiles();
        final ArrayList arrayList = new ArrayList();
        int i = 0;
        for (File crashReportData : listCrashReportFiles) {
            CrashReportData crashReportData2 = new CrashReportData(crashReportData);
            if (crashReportData2.isValid()) {
                arrayList.add(crashReportData2);
            }
        }
        Collections.sort(arrayList, new Comparator<CrashReportData>() {
            public int compare(CrashReportData crashReportData, CrashReportData crashReportData2) {
                return crashReportData.compareTo(crashReportData2);
            }
        });
        JSONArray jSONArray = new JSONArray();
        while (i < arrayList.size() && i < 5) {
            jSONArray.put(arrayList.get(i));
            i++;
        }
        InstrumentUtility.sendReports("crash_reports", jSONArray, new GraphRequest.Callback() {
            public void onCompleted(GraphResponse graphResponse) {
                try {
                    if (graphResponse.getError() == null && graphResponse.getJSONObject().getBoolean("success")) {
                        for (int i = 0; arrayList.size() > i; i++) {
                            ((CrashReportData) arrayList.get(i)).clear();
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        });
    }
}
