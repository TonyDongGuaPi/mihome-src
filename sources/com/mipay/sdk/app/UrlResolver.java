package com.mipay.sdk.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.tsmclient.util.Constants;
import com.xiaomi.jr.appbase.utils.AppConstants;
import java.util.List;
import java.util.regex.Pattern;

public class UrlResolver {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8165a = "http";
    private static final String b = "https";
    private static final String[] c = {Constants.PACKAGE_NAME_WALLET, "com.xiaomi.payment", "com.miui.tsmclient", AppConstants.I, "com.xiaomi.*"};

    static ResolveInfo a(Context context, Intent intent) {
        if (!(context == null || intent == null)) {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities == null) {
                return null;
            }
            for (int i = 0; i < queryIntentActivities.size(); i++) {
                ResolveInfo resolveInfo = queryIntentActivities.get(i);
                if (b(resolveInfo.activityInfo.packageName)) {
                    return resolveInfo;
                }
            }
        }
        return null;
    }

    static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        return !"http".equals(parse.getScheme()) && !"https".equals(parse.getScheme());
    }

    private static boolean b(String str) {
        for (String matches : c) {
            if (Pattern.matches(matches, str)) {
                return true;
            }
        }
        return false;
    }
}
