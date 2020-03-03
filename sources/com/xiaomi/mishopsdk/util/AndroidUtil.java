package com.xiaomi.mishopsdk.util;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import com.xiaomi.mishopsdk.ShopApp;

public class AndroidUtil {
    private static final String TAG = "AndroidUtil";
    public static volatile DispatchQueue sAliveRequestQueue = new DispatchQueue("aliverequest");
    @Deprecated
    public static volatile DispatchQueue sHighestQueue = new DispatchQueue("highestQueue");
    public static volatile DispatchQueue sLogStatViewQueue = new DispatchQueue("LogStatViewQueue");
    public static volatile DispatchQueue sPluginQueue = new DispatchQueue("pluginQueue");
    public static volatile DispatchQueue sStageQueue = new DispatchQueue("stageQueue");
    public static volatile DispatchQueue sUIHandleQueue = new DispatchQueue("uiHandleQueue");

    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long j) {
        if (j == 0) {
            ShopApp.sApplicationHandler.post(runnable);
        } else {
            ShopApp.sApplicationHandler.postDelayed(runnable, j);
        }
    }

    public static boolean isIntentAvailable(Intent intent) {
        return ShopApp.instance.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private static Bundle getMetaDataBundle() {
        try {
            ApplicationInfo applicationInfo = ShopApp.instance.getPackageManager().getApplicationInfo(ShopApp.instance.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "get meta-data failed.", (Object) e);
            return null;
        }
    }

    public static String getMetaDataString(String str) {
        Bundle metaDataBundle = getMetaDataBundle();
        if (metaDataBundle == null) {
            return "";
        }
        return metaDataBundle.getString(str);
    }

    public static int getMetaDataInt(String str) {
        Bundle metaDataBundle = getMetaDataBundle();
        if (metaDataBundle == null) {
            return 0;
        }
        return metaDataBundle.getInt(str);
    }

    public static boolean permissionHasBeenGranted(String str) {
        if (Constants.needRequestPermision() && ContextCompat.checkSelfPermission(ShopApp.instance, str) != 0) {
            return false;
        }
        return true;
    }

    @Deprecated
    public static String getAppName() {
        try {
            PackageManager packageManager = ShopApp.instance.getPackageManager();
            return packageManager.getPackageInfo(ShopApp.instance.getPackageName(), 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
