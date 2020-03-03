package com.tiqiaa.icontrol.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class ManifestMetaData {
    private static final String TAG = "ManifestMetaData";

    private static Object readKey(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo.metaData;
            LogUtil.d(TAG, "readKey..............keyName = " + str + ",metaData -> " + applicationInfo.metaData.keySet());
            return bundle.get(str);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "readKey..............keyName = " + str + ",NameNotFoundException -> " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static int getInt(Context context, String str) {
        return ((Integer) readKey(context, str)).intValue();
    }

    public static String getString(Context context, String str) {
        return (String) readKey(context, str);
    }

    public static Boolean getBoolean(Context context, String str) {
        return (Boolean) readKey(context, str);
    }

    public static Object get(Context context, String str) {
        return readKey(context, str);
    }
}
