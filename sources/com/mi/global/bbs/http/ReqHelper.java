package com.mi.global.bbs.http;

import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.request.HostManager;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.LanguageHelper;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.NetworkUtil;
import com.mi.global.bbs.utils.TimeUtils;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.utils.WebCookieUtil;
import com.mi.log.LogUtil;
import com.mi.util.Device;
import com.mi.util.UserEncryptionUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReqHelper {
    private static final String TAG = "ReqHelper";

    public static String getCookies() {
        String str;
        try {
            CookieSyncManager.createInstance(BBSApplication.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String userId = LoginManager.getInstance().getUserId();
        if (LoginManager.getInstance().getExtendedAuthToken(Constants.Account.getInstance().getServiceID()) == null) {
            str = null;
        } else {
            str = LoginManager.getInstance().getExtendedAuthToken(Constants.Account.getInstance().getServiceID()).authToken;
        }
        String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), "xm_in_sid", (String) null);
        String xmuuId = WebCookieUtil.getXmuuId();
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append("bbs_serviceToken=");
            sb.append(str);
        }
        if (!TextUtils.isEmpty(userId)) {
            String b = UserEncryptionUtil.b(userId);
            sb.append("; cUserId=");
            sb.append(b);
        }
        if (!TextUtils.isEmpty(userId)) {
            String a2 = UserEncryptionUtil.a(userId);
            sb.append("; mUserId=");
            sb.append(a2);
        }
        if (!TextUtils.isEmpty(stringPref)) {
            sb.append("; xm_in_sid=");
            sb.append(stringPref);
        }
        if (!TextUtils.isEmpty(xmuuId)) {
            sb.append("; xmuuid=");
            sb.append(xmuuId);
        }
        sb.append(";ISAPP=1");
        sb.append(";ISBBSSDK=1");
        sb.append(";IS_MI_HOME_BBS_SDK=1");
        sb.append(";jsontag=true");
        sb.append(";APPVERSION=" + Device.r);
        sb.append(";APP_LOCAL=" + LocaleHelper.getLocalCookie());
        sb.append(";APP_LANGUAGE=" + LanguageHelper.getLanguageCookie());
        sb.append(";TIME_ZONE=" + TimeUtils.getTimeZoneName());
        sb.append(";TIME_ZONE_ID=" + TimeUtils.getTimeZoneId());
        sb.append(i.b);
        sb.append("phone_model");
        sb.append("=");
        sb.append(Device.e);
        sb.append(i.b);
        sb.append(HostManager.Parameters.Keys.PHONE_WIDTH);
        sb.append("=");
        sb.append(Device.f1349a);
        sb.append(i.b);
        sb.append(HostManager.Parameters.Keys.PHONE_HEIGHT);
        sb.append("=");
        sb.append(Device.b);
        sb.append(i.b);
        sb.append("networkType");
        sb.append("=");
        sb.append(NetworkUtil.getNetworkType());
        String stringPref2 = Utils.Preference.getStringPref(BBSApplication.getInstance(), "pref_key_custom_cookies", (String) null);
        if (!TextUtils.isEmpty(stringPref2)) {
            try {
                JSONArray jSONArray = new JSONArray(stringPref2);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("name");
                    String optString2 = optJSONObject.optString("value");
                    String optString3 = optJSONObject.optString("domain");
                    String optString4 = optJSONObject.optString("path");
                    if (!(optString == null || optString2 == null || optString3 == null || optString4 == null)) {
                        sb.append(String.format("; %s=%s", new Object[]{optString, optString2}));
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getDeviceInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("version");
        sb.append("=");
        sb.append(Device.r);
        sb.append(a.b);
        sb.append("phone_model");
        sb.append("=");
        sb.append(Device.e);
        sb.append(a.b);
        sb.append("networkType");
        sb.append("=");
        sb.append(NetworkUtil.getNetworkType());
        LogUtil.b(TAG, "device:" + sb.toString());
        return sb.toString();
    }

    public static void onExpiredHandler(Runnable runnable, Runnable runnable2) {
        LoginManager instance = LoginManager.getInstance();
        instance.invalidateServiceToken();
        String refreshServiceToken = instance.refreshServiceToken();
        if (!TextUtils.isEmpty(refreshServiceToken)) {
            LogUtil.b(TAG, "new extended token plain:" + refreshServiceToken);
            Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_extended_token", refreshServiceToken);
            Utils.Preference.setLongPref(BBSApplication.getInstance(), "pref_last_refresh_serviceToken_time", Long.valueOf(System.currentTimeMillis()));
            runnable.run();
            return;
        }
        runnable2.run();
    }
}
