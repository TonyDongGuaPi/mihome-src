package com.mi.global.bbs.view.webview;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.i;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.request.HostManager;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.LanguageHelper;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.TimeUtils;
import com.mi.global.bbs.utils.Utils;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.util.Device;
import com.mi.util.UserEncryptionUtil;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewCookieManager {
    private static final String TAG = "WebViewCookieManager";

    public static void addAppCookie() {
        try {
            CookieSyncManager.createInstance(BBSApplication.getInstance());
            CookieManager instance = CookieManager.getInstance();
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "ISAPP=1; domain=" + ConnectionHelper.getWebCookieDomain());
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), " ISBBS=1;domain=" + ConnectionHelper.getWebCookieDomain());
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), " ISBBSSDK=1;domain=" + ConnectionHelper.getWebCookieDomain());
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "APPVERSION=" + Device.r + "; domain=" + ConnectionHelper.getWebCookieDomain());
            StringBuilder sb = new StringBuilder();
            sb.append("DEVICEID=");
            sb.append(TextUtils.isEmpty(MiStatInterface.a((Context) BBSApplication.getInstance())) ? Device.C : MiStatInterface.a((Context) BBSApplication.getInstance()));
            sb.append("; domain=");
            sb.append(ConnectionHelper.getWebCookieDomain());
            sb.toString();
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "");
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "APP_LOCAL=" + LocaleHelper.getLocalCookie() + "; domain=" + ConnectionHelper.getWebCookieDomain());
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "TIME_ZONE=" + TimeUtils.getTimeZoneName() + "; domain=" + ConnectionHelper.getWebCookieDomain());
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "APP_LANGUAGE=" + LanguageHelper.getLanguageCookie() + "; domain=" + ConnectionHelper.getWebCookieDomain());
            instance.setCookie(ConnectionHelper.getWebCookieDomain(), "TIME_ZONE_ID=" + TimeUtils.getTimeZoneId() + "; domain=" + ConnectionHelper.getWebCookieDomain());
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAllCookie(String str) {
        try {
            CookieSyncManager.createInstance(BBSApplication.getInstance());
            String cookie = CookieManager.getInstance().getCookie(str);
            if (cookie == null) {
                LogUtil.b(TAG, "cookie is null");
                return;
            }
            LogUtil.b(TAG, "all cookie:" + cookie);
        } catch (Exception unused) {
        }
    }

    public static String getCookie(String str, String str2) {
        try {
            CookieSyncManager.createInstance(BBSApplication.getInstance());
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
            return "";
        } catch (Exception unused) {
        }
    }

    public static void removeLoginCookie(Context context) {
        removeCookie(context, "userId");
        removeCookie(context, HostManager.Parameters.Keys.SERVICE_BBS_TOKEN);
        removeCookie(context, "serviceToken");
        removeCookie(context, "xm_user_in_num");
    }

    public static void removeCookie(Context context, String str) {
        removeCookie(context, str, ConnectionHelper.getWebCookie(), "/");
        removeCookie(context, str, ConnectionHelper.getWebCookie(), ConnectionHelper.WEB_COOKIE_PATH);
    }

    private static String getCookieString(String str, String str2, String str3, String str4, String str5) {
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

    public static void setCookie(Context context, String str, String str2, String str3, String str4) {
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        if (instance != null) {
            String cookieString = getCookieString(str, str2, str3, str4, (String) null);
            instance.setCookie(str3, cookieString);
            LogUtil.b(TAG, "set Cookie: " + cookieString);
            CookieSyncManager.getInstance().sync();
        }
    }

    public static void removeAllCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    private static void removeCookie(Context context, String str, String str2, String str3) {
        LogUtil.b(TAG, "remove Cookie: " + str2 + ": " + str + "; path is : " + str3);
        CookieManager instance = CookieManager.getInstance();
        CookieSyncManager.createInstance(context);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str3);
        String cookie = instance.getCookie(sb.toString());
        LogUtil.b(TAG, "Get from Domain:" + str2 + str3 + " result is:" + cookie);
        if (cookie == null) {
            LogUtil.b(TAG, "no cookie in domain " + str2 + str3);
            return;
        }
        for (String split : cookie.split(i.b)) {
            String[] split2 = split.split("=");
            if (split2.length >= 2 && TextUtils.equals(split2[0].trim(), str)) {
                instance.setCookie(str2, getCookieString(str, "", str2, str3, new Date(1).toGMTString()));
                LogUtil.b(TAG, "remove succeed");
                instance.removeExpiredCookie();
                CookieSyncManager.getInstance().sync();
                return;
            }
        }
        LogUtil.b(TAG, "cookie name not found");
    }

    public static void clearCustomCookies(Context context) {
        String stringPref = Utils.Preference.getStringPref(context, "pref_key_custom_cookies", (String) null);
        if (stringPref != null && !stringPref.equals("")) {
            Utils.Preference.removePref(context, "pref_key_custom_cookies");
            clearCustomCookies(context, stringPref);
        }
    }

    public static void updateCustomCookies(Context context) {
        String stringPref = Utils.Preference.getStringPref(context, "pref_key_custom_cookies", (String) null);
        if (stringPref != null && !stringPref.equals("")) {
            addCustomCookies(context, stringPref);
        }
    }

    public static void updateCustomCookies(Context context, String str) {
        if (str != null && !str.equals("")) {
            addCustomCookies(context, str);
        }
    }

    public static void addCustomCookies(Context context, String str) {
        if (context != null) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("name");
                    String optString2 = optJSONObject.optString("value");
                    String optString3 = optJSONObject.optString("domain");
                    String optString4 = optJSONObject.optString("path");
                    if (!(optString == null || optString2 == null || optString3 == null || optString4 == null)) {
                        setCookie(context, optString, optString2, optString3, optString4);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void clearCustomCookies(Context context, String str) {
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
                        removeCookie(context, optString, optString3, optString4);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setShopLoginCookies(Context context) {
        removeCookie(context, "serviceToken");
        String stringPref = Utils.Preference.getStringPref(context, Constants.Prefence.PREF_KEY_SHOP_TOKEN, "");
        setCookie(context, "serviceToken", stringPref, ConnectionHelper.getWebCookie(), "/");
        String webCookie = ConnectionHelper.getWebCookie();
        setCookie(context, "serviceToken", stringPref, webCookie, "/" + LocaleHelper.getLocalCookie());
    }

    public static void setLoginCookies(Context context) {
        ExtendedAuthToken extendedAuthToken;
        String str;
        LoginManager instance = LoginManager.getInstance();
        if (instance.hasLogin()) {
            String userId = instance.getUserId();
            try {
                extendedAuthToken = instance.getExtendedAuthToken(Constants.Account.getInstance().getServiceID());
            } catch (Exception e) {
                e.printStackTrace();
                extendedAuthToken = null;
            }
            if (extendedAuthToken == null) {
                str = null;
            } else {
                str = extendedAuthToken.authToken;
            }
            setLoginCookies(context, userId, str, (String) null);
        }
    }

    public static void setLoginCookies(Context context, String str, String str2, String str3) {
        LoginManager instance = LoginManager.getInstance();
        if (instance.hasLogin() && !TextUtils.isEmpty(str)) {
            String b = UserEncryptionUtil.b(str);
            setCookie(context, "cUserId", b, ConnectionHelper.getWebCookie(), "/");
            String webCookie = ConnectionHelper.getWebCookie();
            setCookie(context, "cUserId", b, webCookie, "/" + LocaleHelper.getLocalCookie());
            String a2 = UserEncryptionUtil.a(str);
            setCookie(context, "mUserId", a2, ConnectionHelper.getWebCookie(), "/");
            String webCookie2 = ConnectionHelper.getWebCookie();
            setCookie(context, "mUserId", a2, webCookie2, "/" + LocaleHelper.getLocalCookie());
            setCookie(context, HostManager.Parameters.Keys.SERVICE_BBS_TOKEN, str2, ConnectionHelper.getWebCookie(), "/");
            String format = String.format("XM_%1$s_UN", new Object[]{instance.getUserId()});
            String userName = instance.getUserName();
            if (!TextUtils.isEmpty(userName)) {
                setCookie(context, format, userName, ConnectionHelper.getWebCookie(), "/");
            }
        }
    }
}
