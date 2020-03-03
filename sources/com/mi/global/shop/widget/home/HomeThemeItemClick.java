package com.mi.global.shop.widget.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.mi.global.shop.activity.MainTabActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.MiShopStatInterface;

public class HomeThemeItemClick {

    /* renamed from: a  reason: collision with root package name */
    public static String f7242a = "keyword";
    public static String b = "native";
    public static String c = "inner_web";
    public static String d = "full_web";
    public static String e = "open_web";
    public static String f = "recharge";
    public static String g = "plugin";
    private static final String h = "HomeThemeItemClick";

    private HomeThemeItemClick() {
    }

    public static void a(NewHomeBlockInfoItem newHomeBlockInfoItem) {
        if (newHomeBlockInfoItem != null && !TextUtils.isEmpty(newHomeBlockInfoItem.mViewId)) {
            MiShopStatInterface.c(newHomeBlockInfoItem.mViewId, MainTabActivity.class.getSimpleName());
        }
    }

    public static void a(Context context, NewHomeBlockInfoItem newHomeBlockInfoItem) {
        if (newHomeBlockInfoItem != null) {
            if (!TextUtils.isEmpty(newHomeBlockInfoItem.mViewId)) {
                MiShopStatInterface.b(newHomeBlockInfoItem.mViewId, MainTabActivity.class.getSimpleName());
            }
            if (!TextUtils.isEmpty(newHomeBlockInfoItem.mPath)) {
                a(context, newHomeBlockInfoItem.mPath);
            }
        }
    }

    private static void a(Context context, String str) {
        if (!str.equals("app://feedback")) {
            if (TextUtils.isEmpty(str) || !ConnectionHelper.b(str)) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", str);
                context.startActivity(intent);
                return;
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }
}
