package com.mi.global.shop.util;

import android.content.Context;
import android.text.TextUtils;
import com.mi.global.shop.activity.LaunchWebActivity;
import com.mi.global.shop.model.HomeThemeItem;
import com.mi.global.shop.model.HomeThemeTopicItem;

public class HomeThemeItemClick {

    /* renamed from: a  reason: collision with root package name */
    public static String f7098a = "native";
    public static String b = "inner_web";
    public static String c = "single_web";
    public static String d = "full_web";
    public static String e = "browser";
    public static String f = "land_web";
    private static final String g = "HomeThemeItemClick";

    private HomeThemeItemClick() {
    }

    public static void a(Context context, HomeThemeTopicItem homeThemeTopicItem) {
        Context context2 = context;
        a(context2, homeThemeTopicItem.mOpenType, homeThemeTopicItem.mLinkUrl, (String) null, (String) null, (String) null);
    }

    public static void a(Context context, HomeThemeItem homeThemeItem) {
        Context context2 = context;
        a(context2, homeThemeItem.mOpenType, homeThemeItem.mLinkUrl, homeThemeItem.mProductId, homeThemeItem.mProductName, homeThemeItem.mKeyword);
    }

    private static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        a(context, str, str2);
    }

    private static void a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            LaunchWebActivity.startActivityStandard(context, str2);
        }
    }
}
