package com.mi.global.shop.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.google.android.exoplayer2.C;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.MainTabActivity;
import com.mi.global.shop.activity.OrderListAcitvity;
import com.mi.global.shop.activity.OrderViewActivity;
import com.mi.global.shop.activity.ReviewListAcitvity;
import com.mi.global.shop.activity.TrackAcitvity;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.user.CouponActivity;
import com.mi.global.shop.xmsf.account.LoginManager;

public class PushRouteUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7104a = "/user";
    public static final String b = "/user/orderview";
    public static final String c = "/user/coupon";
    public static final String d = "/user/orderlist";
    public static final String e = "#track";
    public static final String f = "/index";
    public static final String g = "/category";
    public static final String h = "/discover";
    public static final String i = "/comment/myreview";
    public static final String j = "order_id";
    public static final String k = "package_id";
    public static final String l = "type";
    public static final String m = "RNType";

    public static boolean a(Context context, String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                return false;
            }
            String path = parse.getPath();
            if (LocaleHelper.g()) {
                if (path.contains("/user/orderview")) {
                    if (a(context)) {
                        return true;
                    }
                    String a2 = a(path, "order_id");
                    Intent intent = new Intent(context, OrderViewActivity.class);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("orderview_orderid", a2);
                    context.startActivity(intent);
                    return true;
                } else if (path.contains("/user/coupon")) {
                    if (a(context)) {
                        return true;
                    }
                    Intent intent2 = new Intent(context, CouponActivity.class);
                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent2.putExtra("com.mi.global.shop.extra_user_coupon_type", "coupon_manage");
                    context.startActivity(intent2);
                    return true;
                } else if (path.contains(d)) {
                    if (a(context)) {
                        return true;
                    }
                    try {
                        i2 = Integer.parseInt(a(path, "type"));
                    } catch (Exception unused) {
                        i2 = -1;
                    }
                    Intent intent3 = new Intent(context, OrderListAcitvity.class);
                    if (i2 != -1) {
                        intent3.addFlags(C.ENCODING_PCM_MU_LAW);
                        intent3.putExtra("type", i2);
                        context.startActivity(intent3);
                        return true;
                    }
                } else if (path.contains(i)) {
                    if (a(context)) {
                        return true;
                    }
                    Intent intent4 = new Intent(context, ReviewListAcitvity.class);
                    intent4.addFlags(C.ENCODING_PCM_MU_LAW);
                    context.startActivity(intent4);
                    return true;
                } else if (path.contains(e)) {
                    if (a(context)) {
                        return true;
                    }
                    String a3 = a(path, k);
                    Intent intent5 = new Intent(context, TrackAcitvity.class);
                    intent5.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent5.putExtra("orderview_orderid", a3);
                    context.startActivity(intent5);
                    return true;
                } else if (path.contains(h)) {
                    Intent intent6 = new Intent(context, MainTabActivity.class);
                    intent6.putExtra("change_tab", 2);
                    intent6.addFlags(C.ENCODING_PCM_MU_LAW);
                    context.startActivity(intent6);
                    return true;
                }
            }
            if (path.contains(f)) {
                Intent intent7 = new Intent(context, MainTabActivity.class);
                intent7.putExtra("change_tab", 0);
                intent7.addFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(intent7);
                return true;
            } else if (path.contains(g)) {
                Intent intent8 = new Intent(context, MainTabActivity.class);
                intent8.putExtra("change_tab", 1);
                intent8.addFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(intent8);
                return true;
            } else if (!path.endsWith("/user")) {
                return false;
            } else {
                Intent intent9 = new Intent(context, MainTabActivity.class);
                intent9.putExtra("change_tab", 3);
                intent9.addFlags(C.ENCODING_PCM_MU_LAW);
                context.startActivity(intent9);
                return true;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public static String a(String str, String str2) {
        if (str.indexOf(str2) != -1) {
            try {
                return str.substring(str.indexOf(str2)).split(a.b)[0].split("=")[1];
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static boolean a(Context context) {
        if (LoginManager.u().x()) {
            return false;
        }
        try {
            if (ShopApp.h.f.equals("community_sdk")) {
                ((BaseActivity) context).gotoAccount();
                return true;
            }
            ShopApp.d();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
