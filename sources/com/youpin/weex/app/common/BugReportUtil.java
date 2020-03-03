package com.youpin.weex.app.common;

import com.xiaomi.youpin.common.util.AppInfo;
import com.youpin.weex.app.util.OpenUtils;

public class BugReportUtil {
    public static void a(String str, String str2, String str3, String str4, String str5, String str6) {
        WXStoreApiProvider c = WXAppStoreApiManager.b().c();
        if (c != null) {
            if (str == null) {
                str = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            if (str3 == null) {
                str3 = "";
            }
            if (str4 == null) {
                str4 = "";
            }
            c.a((Throwable) new Exception("page:\n" + "MJYPWeexSDK version:" + OpenUtils.e + "\n" + "PageInfos:\n" + "AppName:" + AppInfo.c() + ",PageName:" + str + ",WeexUrl:" + str2 + ",HtmlUrl:" + str3 + ",WeexPage:" + str4 + 10 + "MJYPWeexSDK Error Info:\n" + str5 + ":" + str6 + 10));
        }
    }
}
