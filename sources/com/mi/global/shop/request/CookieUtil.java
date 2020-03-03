package com.mi.global.shop.request;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.util.WebCookieUtil;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.util.Constants;
import com.mi.util.Device;
import com.mi.util.UserEncryptionUtil;
import com.mi.util.Utils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;

public class CookieUtil {
    public static String a() {
        String str;
        LoginManager u = LoginManager.u();
        String c = u.c();
        ExtendedAuthToken a2 = u.a(Constants.Account.e().c());
        String str2 = a2 != null ? a2.authToken : null;
        String stringPref = Utils.Preference.getStringPref(ShopApp.g(), "xm_in_sid", (String) null);
        String a3 = WebCookieUtil.a();
        String stringPref2 = Utils.Preference.getStringPref(ShopApp.g(), "pref_key_zip_code", "");
        String stringPref3 = Utils.Preference.getStringPref(ShopApp.g(), "pref_key_city_name", "");
        String stringPref4 = Utils.Preference.getStringPref(ShopApp.g(), "pref_key_state_id", "");
        String stringPref5 = Utils.Preference.getStringPref(ShopApp.g(), "pref_key_warehouse_id", "");
        if (TextUtils.isEmpty(MiShopStatInterface.a((Context) ShopApp.g()))) {
            str = Device.C;
        } else {
            str = MiShopStatInterface.a((Context) ShopApp.g());
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            sb.append("serviceToken=");
            sb.append(str2);
        }
        a(sb, c);
        if (!TextUtils.isEmpty(stringPref)) {
            sb.append("; xm_in_sid=");
            sb.append(stringPref);
        }
        if (!TextUtils.isEmpty(a3)) {
            sb.append("; xmuuid=");
            sb.append(a3);
        }
        if (!TextUtils.isEmpty(stringPref2) && !TextUtils.isEmpty(stringPref3) && !TextUtils.isEmpty(stringPref4) && !TextUtils.isEmpty(stringPref5)) {
            sb.append("; XM_pincode_in=");
            sb.append(stringPref2);
            sb.append("; WH_cityName=");
            sb.append(stringPref3);
            sb.append("; WH_stateId=");
            sb.append(stringPref4);
            sb.append("; WH_warehouse=");
            sb.append(stringPref5);
        }
        if (ShopApp.h != null) {
            if (ShopApp.h.f.equals("community_sdk")) {
                sb.append(i.b);
                sb.append("request_from");
                sb.append("=");
                sb.append("community_sdk");
            } else if (ShopApp.h.f.equals("mihome_sdk")) {
                sb.append(i.b);
                sb.append("request_from");
                sb.append("=");
                sb.append("mihome_sdk");
            } else {
                sb.append(i.b);
                sb.append("request_from");
                sb.append("=");
                sb.append("community_sdk");
            }
        }
        sb.append("; sdk_version=");
        sb.append(ShopApp.m);
        if (ShopApp.n()) {
            sb.append(";ot=5");
            if (Build.VERSION.SDK_INT > 15) {
                sb.append(";rnversion=0.39.1");
                sb.append(";rn=");
                String[] split = Utils.Preference.getStringPref(ShopApp.g(), "rn_bundle_name_list", "product").split(i.b);
                for (int i = 0; i < split.length; i++) {
                    Application g = ShopApp.g();
                    int intPref = Utils.Preference.getIntPref(g, split[i] + "_current_version", 1);
                    if (i == 0) {
                        sb.append(split[i]);
                        sb.append(":");
                        sb.append(intPref);
                    } else {
                        sb.append(",");
                        sb.append(split[i]);
                        sb.append(":");
                        sb.append(intPref);
                    }
                }
            }
        }
        int intPref2 = Utils.Preference.getIntPref(ShopApp.g(), Constants.AppUpdate.n, 0);
        if (intPref2 > 0) {
            sb.append(";hotfixVersion=" + intPref2);
        }
        sb.append(";ISAPP=1");
        if (!TextUtils.isEmpty(str)) {
            sb.append("; DEVICEID=");
            sb.append(str);
        }
        return sb.toString();
    }

    public static String b() {
        return "version" + "=" + Device.r + a.b + "phone_model" + "=" + Device.e + a.b + "networkType" + "=" + NetworkUtil.a() + "android_sdk_version" + "=" + Device.m + a.b + "android_version" + "=" + Device.o + a.b;
    }

    public static void a(StringBuilder sb, String str) {
        if (!TextUtils.isEmpty(str)) {
            String a2 = UserEncryptionUtil.a(str);
            if (!TextUtils.isEmpty(a2)) {
                sb.append("; mUserId=");
                sb.append(a2);
            }
            String b = UserEncryptionUtil.b(str);
            if (!TextUtils.isEmpty(b)) {
                sb.append("; cUserId=");
                sb.append(b);
            }
        }
    }
}
