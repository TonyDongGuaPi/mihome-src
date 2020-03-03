package com.mi.mistatistic.sdk;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mi.blockcanary.BlockAppContext;
import com.mi.blockcanary.BlockCanary;
import com.mi.mistatistic.sdk.controller.ApplicationContextHolder;
import com.mi.mistatistic.sdk.controller.AsyncJobDispatcher;
import com.mi.mistatistic.sdk.controller.DeviceIDHolder;
import com.mi.mistatistic.sdk.controller.EventDAO;
import com.mi.mistatistic.sdk.controller.LocalEventRecorder;
import com.mi.mistatistic.sdk.controller.Logger;
import com.mi.mistatistic.sdk.controller.MiStatOptions;
import com.mi.mistatistic.sdk.controller.SessionManager;
import com.mi.mistatistic.sdk.controller.UploadPolicyEngine;
import com.mi.mistatistic.sdk.controller.asyncjobs.BasicInfoRecordJob;
import com.mi.mistatistic.sdk.controller.asyncjobs.CorrectingServerTimeJob;
import com.mi.mistatistic.sdk.data.EventData;
import com.mi.mistatistic.sdk.data.NewUserEvent;
import com.mi.mistatistic.sdk.data.RNDownloadEvent;
import com.mi.mistatistic.sdk.data.RNLoadActivityEvent;
import com.mi.mistatistic.sdk.data.RNLoadBundleEvent;
import com.mi.mistatistic.sdk.data.StatClickEvent;
import com.mi.mistatistic.sdk.data.ViewClickEvent;
import com.mi.multimonitor.CrashReport;
import java.util.ArrayList;
import java.util.Map;

public abstract class MiStatInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7319a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final long g = 60000;
    public static final long h = 86400000;
    private static MiStatOptions i;

    public static final void a(Application application, MiStatOptions miStatOptions) {
        if (miStatOptions == null) {
            miStatOptions = new MiStatOptions.Builder().a();
        }
        i = miStatOptions;
        if (!i.isDisableStat()) {
            if (!TextUtils.isEmpty(miStatOptions.getAppId())) {
                Context applicationContext = application.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = application;
                }
                BuildSetting.a(miStatOptions.isTest());
                BuildSetting.d(miStatOptions.isServerCn());
                BuildSetting.b(miStatOptions.isServerIndia());
                BuildSetting.c(miStatOptions.isServerRussia());
                if (miStatOptions.getNeedUploadPackageNameList() != null && miStatOptions.getNeedUploadPackageNameList().size() > 0) {
                    ApplicationContextHolder.a(miStatOptions.getNeedUploadPackageNameList());
                }
                ApplicationContextHolder.a(application, miStatOptions.getAppId(), TextUtils.isEmpty(miStatOptions.getChannel()) ? "mistats_default" : miStatOptions.getChannel());
                UploadPolicyEngine.a().b();
                a(miStatOptions.getUploadPolicy(), (long) miStatOptions.getUploadInteval());
                ApplicationContextHolder.a(miStatOptions.getUserId());
                ApplicationContextHolder.b(miStatOptions.getGaId());
                AsyncJobDispatcher.a().a((AsyncJobDispatcher.AsyncJob) new CorrectingServerTimeJob());
                EventDAO.a();
                CrashReport.setSingletonInstance(new CrashReport.Builder(applicationContext).appId(miStatOptions.getAppId()).version(miStatOptions.getVersionSpan()).isForSdk(miStatOptions.isForSdk()).build());
                BasicInfoRecordJob.a(applicationContext);
                a(miStatOptions.isEnableLog());
                if (miStatOptions.isOpenBlockCanary()) {
                    BlockCanary.a(applicationContext, new BlockAppContext(applicationContext)).b();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("appID  is empty.");
        }
    }

    public static final String a(Context context) {
        return DeviceIDHolder.a(context);
    }

    public static MiStatOptions a() {
        if (i == null) {
            i = new MiStatOptions.Builder().a();
        }
        return i;
    }

    private static void a(boolean z) {
        if (z) {
            Logger.a();
        }
    }

    private static final String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.contains(",") ? str.replace(",", "") : str;
    }

    public static final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        NewUserEvent newUserEvent = new NewUserEvent();
        newUserEvent.a(System.currentTimeMillis() / 1000);
        newUserEvent.d("extraContextEvent");
        newUserEvent.a(new EventData("new_user_tag", str, EventData.b));
        LocalEventRecorder.a(newUserEvent);
    }

    public static final void a(Context context, String str) {
        if (context != null && SessionManager.a() != null && !a().isDisableStat()) {
            SessionManager.a().a(context, str);
        }
    }

    public static final void b() {
        if (SessionManager.a() != null && !a().isDisableStat()) {
            SessionManager.a().f();
        }
    }

    public static final void a(String str, String str2) {
        a(str, str2, (String) null, (String) null);
    }

    public static final void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, str4, (String) null);
    }

    public static final void a(String str, String str2, String str3, String str4, String str5) {
        StatClickEvent statClickEvent;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.w("MiStat", "eventId and pageId must not empty.");
            return;
        }
        if (!TextUtils.isEmpty(str3)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new EventData(str3, str4, EventData.b));
            statClickEvent = new StatClickEvent(str, str2, str5, arrayList);
        } else {
            statClickEvent = new StatClickEvent(str, str2, str5, (ArrayList<EventData>) null);
        }
        LocalEventRecorder.a(statClickEvent);
    }

    public static final void a(String str, String str2, String[] strArr, String[] strArr2, String str3) {
        StatClickEvent statClickEvent;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalStateException("eventId and pageId must not empty.");
        } else if (strArr.length == strArr2.length) {
            if (strArr == null || strArr.length == 0) {
                statClickEvent = new StatClickEvent(str, str2, str3, (ArrayList<EventData>) null);
            } else {
                ArrayList arrayList = new ArrayList();
                int length = strArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    arrayList.add(new EventData(strArr[i2], strArr2[i2], EventData.b));
                }
                statClickEvent = new StatClickEvent(str, str2, str3, arrayList);
            }
            LocalEventRecorder.a(statClickEvent);
        } else {
            throw new IllegalStateException("key[] and value[] lenght must be equal.");
        }
    }

    public static void a(String str, String str2, String str3, String str4, long j) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EventData(str4, String.valueOf(j), "number"));
        LocalEventRecorder.a(new StatClickEvent(str, str2, str3, arrayList));
    }

    public static void b(String str, String str2, String str3, String str4, String str5) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EventData(str4, str5, EventData.b));
        LocalEventRecorder.a(new StatClickEvent(str, str2, str3, arrayList));
    }

    public static void a(String str, String str2, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String next : map.keySet()) {
                arrayList.add(new EventData(next, map.get(next), EventData.b));
            }
        }
        LocalEventRecorder.a(new StatClickEvent(str, str2, (String) null, arrayList));
    }

    public static void b(String str, String str2) {
        a(str, str2, (String) null);
    }

    public static final void a(String str, String str2, String str3) {
        LocalEventRecorder.a(new ViewClickEvent(str, str2, str3));
    }

    public static final void c(String str, String str2) {
        b(str, str2, "");
    }

    public static final void b(String str, String str2, String str3) {
        if (SessionManager.a() != null) {
            SessionManager.a().a(str, str2, str3);
        }
    }

    public static final void b(String str) {
        LocalEventRecorder.a(new RNDownloadEvent(str));
    }

    public static final void c(String str) {
        LocalEventRecorder.a(new RNLoadBundleEvent(str));
    }

    public static final void d(String str) {
        LocalEventRecorder.a(new RNLoadActivityEvent(str));
    }

    public static final void a(int i2, long j) {
        if (i2 != 4 || (j >= 60000 && j <= 86400000)) {
            UploadPolicyEngine.a().a(i2, j);
            return;
        }
        throw new IllegalArgumentException("interval should be set between 1 minutes and 1 day");
    }

    public static final int c() {
        return UploadPolicyEngine.a().f();
    }

    private static boolean b(Context context) {
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
