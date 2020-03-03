package com.xiaomi.shop2.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.ToastUtil;

public class AppUtil {
    private static final String MIMARKET_PACKAGE_NAME = "com.xiaomi.market";
    public static final String MISHOP_PACKAGE_NAME = "com.xiaomi.shop";

    private static boolean startAppStoreIntent(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=com.xiaomi.shop"));
        return startIntentActivity(activity, intent);
    }

    private static boolean startBrowserIntent(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://app.xiaomi.com/details?id=com.xiaomi.shop"));
        return startIntentActivity(activity, intent);
    }

    private static boolean startIntentActivity(Activity activity, Intent intent) {
        if (!isIntentAvailable(intent)) {
            return false;
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.mishopsdk_right_enter, R.anim.mishopsdk_left_out);
        return true;
    }

    private static boolean isIntentAvailable(Intent intent) {
        return ShopApp.instance.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static void go2DownloadMiShopApp(Activity activity) {
        if (!startAppStoreIntent(activity) && !startBrowserIntent(activity)) {
            ToastUtil.show("请安装小米商城最新版本。");
        }
    }

    public static void go2Market(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
