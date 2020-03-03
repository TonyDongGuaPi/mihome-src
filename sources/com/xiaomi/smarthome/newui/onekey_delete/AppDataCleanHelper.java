package com.xiaomi.smarthome.newui.onekey_delete;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.youpin.utils.FileUtils;
import java.io.File;

public class AppDataCleanHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20692a = "AppDataCleanHelper";

    private AppDataCleanHelper() {
    }

    public static boolean a(Context context) {
        try {
            b(context);
            c(context);
            return true;
        } catch (Exception e) {
            LogUtil.b(f20692a, "clearAppData: " + Log.getStackTraceString(e));
            return false;
        }
    }

    private static void b(Context context) {
        if (TextUtils.equals(Environment.getExternalStorageState(), "mounted")) {
            FileUtils.e(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName());
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            sb.append("/SmartHome/");
            FileUtils.e(sb.toString());
        }
    }

    @SuppressLint({"SdCardPath"})
    private static void c(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((ActivityManager) context.getSystemService("activity")).clearApplicationUserData();
            return;
        }
        FileUtils.e(new File("/data/data/" + context.getPackageName() + "/").getAbsolutePath());
    }
}
