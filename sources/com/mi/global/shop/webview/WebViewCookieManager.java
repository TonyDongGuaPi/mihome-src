package com.mi.global.shop.webview;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.i;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.Device;
import com.mi.util.UserEncryptionUtil;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewCookieManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7141a = "WebViewCookieManager";

    public static void a() {
        String str;
        try {
            CookieManager instance = CookieManager.getInstance();
            instance.setCookie(ConnectionHelper.E, "ISAPP=1; domain=" + ConnectionHelper.E);
            instance.setCookie(ConnectionHelper.E, "APPVERSION=" + Device.r + "; domain=" + ConnectionHelper.E);
            if (TextUtils.isEmpty(MiShopStatInterface.a((Context) ShopApp.g()))) {
                str = "DEVICEID=" + Device.C + "; domain=" + ConnectionHelper.E;
            } else {
                str = "DEVICEID=" + MiShopStatInterface.a((Context) ShopApp.g()) + "; domain=" + ConnectionHelper.E;
            }
            instance.setCookie(ConnectionHelper.E, str);
            String str2 = "request_from=community_sdk; domain=" + ConnectionHelper.E;
            if (ShopApp.h != null) {
                if (ShopApp.h.f.equals("community_sdk")) {
                    str2 = "request_from=community_sdk; domain=" + ConnectionHelper.E;
                } else if (ShopApp.h.f.equals("mihome_sdk")) {
                    str2 = "request_from=mihome_sdk; domain=" + ConnectionHelper.E;
                }
            }
            instance.setCookie(ConnectionHelper.E, str2);
            instance.setCookie(ConnectionHelper.E, "sdk_version=30502; domain=" + ConnectionHelper.E);
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int b() {
        String a2 = a(ConnectionHelper.G, "xm_user_in_num");
        if (TextUtils.isEmpty(a2)) {
            return 0;
        }
        return Integer.parseInt(a2);
    }

    public static void a(String str) {
        String cookie = CookieManager.getInstance().getCookie(str);
        if (cookie == null) {
            LogUtil.b(f7141a, "cookie is null");
            return;
        }
        LogUtil.b(f7141a, "all cookie:" + cookie);
    }

    public static String b(String str) {
        String str2;
        String cookie = CookieManager.getInstance().getCookie(str);
        LoginManager u = LoginManager.u();
        if (!u.x()) {
            return cookie;
        }
        u.c();
        if (u.a(Constants.Account.e().c()) == null) {
            str2 = null;
        } else {
            str2 = u.a(Constants.Account.e().c()).authToken;
        }
        return a("serviceToken", str2, Constants.Cookie.f7061a, "/" + ConnectionHelper.J, (String) null) + cookie;
    }

    public static String a(String str, String str2) {
        if (Build.VERSION.SDK_INT < 21 && ShopApp.h() != null) {
            CookieSyncManager.createInstance(ShopApp.g());
        }
        LogUtil.b(f7141a, "get Cookie key:" + str2 + " from:" + str);
        String cookie = CookieManager.getInstance().getCookie(str);
        if (cookie == null) {
            return "";
        }
        for (String split : cookie.split(i.b)) {
            String[] split2 = split.split("=");
            if (split2.length == 2 && split2[0].trim().equals(str2)) {
                return split2[1];
            }
        }
        LogUtil.b(f7141a, "get Cookie val:" + "" + " from:" + str);
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        }
        return "";
    }

    public static void a(Context context) {
        String stringPref = Utils.Preference.getStringPref(context, "pref_key_zip_code", "");
        String stringPref2 = Utils.Preference.getStringPref(context, "pref_key_city_name", "");
        String stringPref3 = Utils.Preference.getStringPref(context, "pref_key_state_id", "");
        String stringPref4 = Utils.Preference.getStringPref(context, "pref_key_warehouse_id", "");
        if (!TextUtils.isEmpty(stringPref2) && !TextUtils.isEmpty(stringPref3) && !TextUtils.isEmpty(stringPref4) && !TextUtils.isEmpty(stringPref)) {
            String str = Constants.Cookie.f7061a;
            a(context, "XM_pincode_in", stringPref, str, "/" + ConnectionHelper.J);
            String str2 = Constants.Cookie.f7061a;
            a(context, "WH_cityName", stringPref2, str2, "/" + ConnectionHelper.J);
            String str3 = Constants.Cookie.f7061a;
            a(context, "WH_stateId", stringPref3, str3, "/" + ConnectionHelper.J);
            String str4 = Constants.Cookie.f7061a;
            a(context, "WH_warehouse", stringPref4, str4, "/" + ConnectionHelper.J);
        }
    }

    public static void a(Context context, int i) {
        if (i != -1) {
            String valueOf = String.valueOf(i);
            String str = Constants.Cookie.f7061a;
            a(context, "xm_user_in_num", valueOf, str, "/" + ConnectionHelper.J);
        }
    }

    public static void b(Context context) {
        LogUtil.b(f7141a, "remove login cookie in:" + context.toString());
        a(context, "userId");
        a(context, "serviceToken");
        a(context, "xm_user_in_num");
        a(context, "cUserId");
        a(context, "mUserId");
    }

    public static void a(Context context, String str, String str2) {
        String str3 = Constants.Cookie.f7061a;
        a(context, str, str2, str3, "/" + ConnectionHelper.J);
    }

    public static void a(Context context, String str) {
        b(context, str, Constants.Cookie.f7061a, "/");
        b(context, str, Constants.Cookie.f7061a, ConnectionHelper.H);
    }

    private static String a(String str, String str2, String str3, String str4, String str5) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("=");
        sb.append(str2);
        sb.append(";domain=");
        sb.append(str3);
        sb.append(";path=");
        sb.append(str4);
        if (str5 != null) {
            sb.append(";expires=");
            sb.append(str5);
        } else {
            sb.append(i.b);
        }
        return sb.toString();
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        if (instance != null) {
            String a2 = a(str, str2, str3, str4, (String) null);
            instance.setCookie(str3, a2);
            LogUtil.b(f7141a, "set Cookie: " + a2);
            CookieSyncManager.getInstance().sync();
        }
    }

    private static void f(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    private static void b(Context context, String str, String str2, String str3) {
        LogUtil.b(f7141a, "remove Cookie: " + str2 + ": " + str + "; path is : " + str3);
        CookieManager instance = CookieManager.getInstance();
        CookieSyncManager.createInstance(context);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str3);
        String cookie = instance.getCookie(sb.toString());
        LogUtil.b(f7141a, "Get from Domain:" + str2 + str3 + " result is:" + cookie);
        if (cookie == null) {
            LogUtil.b(f7141a, "no cookie in domain " + str2 + str3);
            return;
        }
        for (String split : cookie.split(i.b)) {
            String[] split2 = split.split("=");
            if (split2.length >= 2 && TextUtils.equals(split2[0].trim(), str)) {
                instance.setCookie(str2, a(str, "", str2, str3, new Date(1).toGMTString()));
                LogUtil.b(f7141a, "remove succeed");
                instance.removeExpiredCookie();
                CookieSyncManager.getInstance().sync();
                return;
            }
        }
        LogUtil.b(f7141a, "cookie name not found");
    }

    public static void c(Context context) {
        String stringPref = Utils.Preference.getStringPref(context, "pref_key_custom_cookies", (String) null);
        if (stringPref != null && !stringPref.equals("")) {
            Utils.Preference.removePref(context, "pref_key_custom_cookies");
            d(context, stringPref);
        }
    }

    public static void d(Context context) {
        String stringPref = Utils.Preference.getStringPref(context, "pref_key_custom_cookies", (String) null);
        if (stringPref != null && !stringPref.equals("")) {
            c(context, stringPref);
        }
    }

    public static void b(Context context, String str) {
        if (str != null && !str.equals("")) {
            c(context, str);
        }
    }

    public static void c(Context context, String str) {
        if (context != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("key");
                    String optString2 = optJSONObject.optString("value");
                    String optString3 = optJSONObject.optString("domain");
                    String optString4 = optJSONObject.optString("path");
                    if (!(optString == null || optString2 == null || optString3 == null || optString4 == null)) {
                        a(context, optString, optString2, optString3, optString4);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void d(Context context, String str) {
        if (context != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("key");
                    String optString2 = optJSONObject.optString("value");
                    String optString3 = optJSONObject.optString("domain");
                    String optString4 = optJSONObject.optString("path");
                    if (!(optString == null || optString2 == null || optString3 == null || optString4 == null)) {
                        b(context, optString, optString3, optString4);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void e(Context context) {
        String str;
        LoginManager u = LoginManager.u();
        if (u.x()) {
            String c = u.c();
            ExtendedAuthToken a2 = u.a(Constants.Account.e().c());
            if (a2 == null) {
                str = null;
            } else {
                str = a2.authToken;
            }
            a(context, c, str, (String) null);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        LoginManager u = LoginManager.u();
        if (u.x()) {
            LogUtil.b(f7141a, "set login cookie>>>");
            a(context, "mUserId", UserEncryptionUtil.a(str), Constants.Cookie.f7061a, "/");
            String a2 = UserEncryptionUtil.a(str);
            String str4 = Constants.Cookie.f7061a;
            a(context, "mUserId", a2, str4, "/" + ConnectionHelper.J);
            a(context, "cUserId", UserEncryptionUtil.b(str), Constants.Cookie.f7061a, "/");
            String b = UserEncryptionUtil.b(str);
            String str5 = Constants.Cookie.f7061a;
            a(context, "cUserId", b, str5, "/" + ConnectionHelper.J);
            LogUtil.b(f7141a, "set uid:" + u.c());
            if (!TextUtils.isEmpty(str2)) {
                String str6 = Constants.Cookie.f7061a;
                a(context, "serviceToken", str2, str6, "/" + ConnectionHelper.J);
                LogUtil.b(f7141a, "set serviceToken:" + str2);
            }
            String L = LoginManager.u().L();
            if (!TextUtils.isEmpty(L)) {
                a(context, "serviceToken", L, "sg.support.kefu.mi.com", "/");
            }
            String B = LoginManager.u().B();
            if (!TextUtils.isEmpty(B)) {
                a(context, "serviceToken", B, ConnectionHelper.cT, "/");
            }
            String C = LoginManager.u().C();
            if (!TextUtils.isEmpty(C)) {
                a(context, "cashpay_wap_id_slh", C, ConnectionHelper.cT, "/");
            }
            String D = LoginManager.u().D();
            if (!TextUtils.isEmpty(D)) {
                a(context, "cashpay_wap_id_ph", D, ConnectionHelper.cT, "/");
            }
            String E = LoginManager.u().E();
            if (!TextUtils.isEmpty(E)) {
                a(context, "cUserId", E, ConnectionHelper.cT, "/");
            }
            String format = String.format("XM_%1$s_UN", new Object[]{UserEncryptionUtil.b(str)});
            String str7 = null;
            try {
                String G = u.G();
                if (G == null) {
                    G = "";
                }
                str7 = URLEncoder.encode(G, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LogUtil.a(e.getMessage());
            }
            if (!TextUtils.isEmpty(str7)) {
                a(context, format, str7, Constants.Cookie.f7061a, "/");
                LogUtil.b(f7141a, "set username:" + str7);
            }
            LogUtil.b(f7141a, "set login cookie<<<");
        }
    }
}
