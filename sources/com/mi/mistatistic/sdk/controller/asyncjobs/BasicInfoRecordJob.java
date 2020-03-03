package com.mi.mistatistic.sdk.controller.asyncjobs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.mi.mistatistic.sdk.controller.LocalEventRecorder;
import com.mi.mistatistic.sdk.controller.Logger;
import com.mi.mistatistic.sdk.controller.NetworkUtils;
import com.mi.mistatistic.sdk.controller.PrefPersistUtils;
import com.mi.mistatistic.sdk.controller.TimeUtil;
import com.mi.mistatistic.sdk.data.StartUpEvent;
import java.util.Calendar;

public class BasicInfoRecordJob {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7353a = "last_day";

    public static void a(Context context) {
        StringBuilder sb;
        int i = Calendar.getInstance().get(6);
        if (i != PrefPersistUtils.a(context, f7353a, 0)) {
            PrefPersistUtils.b(context, f7353a, i);
            StartUpEvent startUpEvent = new StartUpEvent();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics != null) {
                int i2 = displayMetrics.widthPixels;
                int i3 = displayMetrics.heightPixels;
                if (i2 < i3) {
                    sb = new StringBuilder();
                    sb.append(i2);
                    sb.append("x");
                    sb.append(i3);
                } else {
                    sb = new StringBuilder();
                    sb.append(i3);
                    sb.append("x");
                    sb.append(i2);
                }
                startUpEvent.d(sb.toString());
            }
            startUpEvent.b(TimeUtil.a().b());
            startUpEvent.e(NetworkUtils.b(context.getApplicationContext()));
            LocalEventRecorder.a(startUpEvent);
        }
    }

    private static String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
        } catch (Exception e) {
            Logger.a("", (Throwable) e);
            return null;
        }
    }

    private static String b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "unknown";
            }
            if (!activeNetworkInfo.isConnected()) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0) {
                return "unknown";
            }
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    return "unknown";
            }
        } catch (Throwable th) {
            Logger.a("", th);
            return "unknown";
        }
    }

    private static String c(Context context) {
        try {
            String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String str = System.getenv("SECONDARY_STORAGE");
            if (TextUtils.isEmpty(str)) {
                return "0";
            }
            return (!a(context, str) || str.equals(absolutePath)) ? "2" : "1";
        } catch (Throwable th) {
            Logger.a("", th);
            return "0";
        }
    }

    @SuppressLint({"InlinedApi"})
    private static boolean a(Context context, String str) {
        try {
            StorageManager storageManager = (StorageManager) context.getSystemService("storage");
            if ("mounted".equals((String) storageManager.getClass().getMethod("getVolumeState", new Class[]{String.class}).invoke(storageManager, new Object[]{str}))) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            Logger.a("", th);
        }
    }
}
