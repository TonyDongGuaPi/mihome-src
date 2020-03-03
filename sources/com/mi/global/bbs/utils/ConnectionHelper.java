package com.mi.global.bbs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.model.SyncModel;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ConnectionHelper {
    public static final String DEFAULT_DOMAIN_IN = "in.c.mi.com";
    public static final String DEFAULT_DOMIN_FEED_ONLINE = "feed-c.mi.com";
    public static final String DEFAULT_DOMIN_FEED_TEST = "feed-c.test.mi.com";
    public static final String DEFAULT_DOMIN_HD_ONLINE = "hd.c.mi.com";
    public static final String DEFAULT_DOMIN_HD_TEST = "hd.c.test.mi.com";
    public static final String DEFAULT_DOMIN_ONLINE = "c.mi.com";
    public static final String DEFAULT_DOMIN_TEST = "c.test.mi.com";
    public static final String DEFAULT_WEB_COOKIE = "mi.com";
    public static final String DEFAULT_WEB_COOKIE_DOMAIN_WITH_PATH = ("mi.com/" + LocaleHelper.getLocalCookie());
    private static final String EVENT_CONFIRM_URL = "/duck/confirm?from=bbs";
    private static final String EVENT_PRIZE_LIST_URL = "/duck/list?from=bbs";
    private static final String EVENT_PRIZE_URL = "/duck/prize?from=bbs";
    private static final String EVENT_SHARE_URL = "/duck/share?from=bbs";
    private static final String FACEBOOK_INDIA = "https://www.facebook.com/XiaomiIndia";
    public static final String HTTPS_PREFIX = "https://";
    public static final String HTTP_PREFIX = "http://";
    private static final String MEVENT_URL_REGIEX = "^https*://m*event.c.mi.com.*";
    private static final String PLUGIN_URL = "app.php?mod=plugin";
    private static final String PROFILE_ALARM_URL = "home.php?mod=space&do=pm";
    private static final String SYNC_URL = "app.php?mod=sync";
    public static final String WEB_COOKIE_PATH = ("/" + LocaleHelper.getLocalCookie());

    public static String getFacebook() {
        return "https://www.facebook.com/XiaomiIndia";
    }

    public static String getWebCookieDomain() {
        return BBSApplication.isUserTest() ? DEFAULT_DOMIN_TEST : DEFAULT_DOMIN_ONLINE;
    }

    public static String getWebCookie() {
        return Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_COOKIE_DOMAIN, "mi.com");
    }

    public static String getWebCookieWithPath() {
        return Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_COOKIE_DOMAIN_PATH, DEFAULT_WEB_COOKIE_DOMAIN_WITH_PATH);
    }

    public static String getUpdateUrl() {
        return getAppIndexUrl() + "app.php?mod=sync";
    }

    public static String getAppIndexUrl() {
        StringBuilder sb;
        String domainOnline;
        if (BBSApplication.isUserTest()) {
            sb = new StringBuilder();
            sb.append(HTTP_PREFIX);
            domainOnline = getDomainTest();
        } else {
            sb = new StringBuilder();
            sb.append(HTTP_PREFIX);
            domainOnline = getDomainOnline();
        }
        sb.append(domainOnline);
        sb.append("/");
        return sb.toString();
    }

    public static String getDomainTest() {
        return Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_TEST, DEFAULT_DOMIN_TEST);
    }

    public static String getDomainOnline() {
        return Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN, DEFAULT_DOMIN_ONLINE);
    }

    public static String getFeedIndexUrl() {
        String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_FEED, DEFAULT_DOMIN_FEED_ONLINE);
        String stringPref2 = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_FEED_TEST, DEFAULT_DOMIN_FEED_TEST);
        if (BBSApplication.isUserTest()) {
            return HTTP_PREFIX + stringPref2 + "/";
        }
        return HTTP_PREFIX + stringPref + "/";
    }

    public static String getEventIndexUrl() {
        String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_HD, DEFAULT_DOMIN_HD_ONLINE);
        String stringPref2 = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_HD_TEST, DEFAULT_DOMIN_HD_TEST);
        if (BBSApplication.isUserTest()) {
            return HTTP_PREFIX + stringPref2 + "/";
        }
        return HTTP_PREFIX + stringPref + "/";
    }

    public static String getProfileAlarmUrl() {
        return getAppIndexUrl() + "home.php?mod=space&do=pm";
    }

    public static String getPluginUrl() {
        return getAppIndexUrl() + PLUGIN_URL;
    }

    public static String getCardEventGetUrl() {
        return getEventIndexUrl() + LocaleHelper.getLocalCookie() + EVENT_PRIZE_URL;
    }

    public static String getCardEventShareUrl() {
        return getEventIndexUrl() + LocaleHelper.getLocalCookie() + EVENT_SHARE_URL;
    }

    public static String getCardConfirmUrl() {
        return getEventIndexUrl() + LocaleHelper.getLocalCookie() + EVENT_CONFIRM_URL;
    }

    public static String getCardEventlistUrl() {
        return getEventIndexUrl() + LocaleHelper.getLocalCookie() + EVENT_PRIZE_LIST_URL;
    }

    public static boolean needOpenInBrowser(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (SyncModel.inAppUrls != null) {
            for (String contains : SyncModel.inAppUrls) {
                if (str.contains(contains)) {
                    return false;
                }
            }
        }
        if (SyncModel.inBrowserUrls != null) {
            for (String contains2 : SyncModel.inBrowserUrls) {
                if (str.contains(contains2)) {
                    return true;
                }
            }
        }
        if (str.matches("^https*://m*event.c.mi.com.*")) {
            return false;
        }
        if ((str.contains("facebook.com") && !str.contains("oauth")) || str.contains("twitter.com") || str.contains("youtube.com")) {
            return true;
        }
        if (str.startsWith("http://c.test.mi.com/") || str.startsWith("http://c.mi.com/") || str.startsWith("http://in.c.mi.com/") || str.startsWith("https://in.c.mi.com/") || str.startsWith("https://c.test.mi.com/") || str.startsWith("https://c.mi.com/") || str.contains(Constants.WebViewURL.GO_STORE_RN) || str.contains(Constants.WebViewURL.GO_STORE_RN_SECOND) || str.contains(Constants.WebViewURL.GO_STORE_RN_TEST) || str.contains(Constants.WebViewURL.GO_STORE_RN_SECOND_TEST)) {
            return false;
        }
        return true;
    }

    public static String getAppUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.matches("^https*://m*event.c.mi.com.*")) {
            try {
                String trim = str.replace(".event.c.mi.com", ".mevent.c.mi.com").trim();
                try {
                    String[] split = trim.split("/");
                    if (split.length > 4 && !"app".equals(split[4])) {
                        String str2 = split[0] + "/" + split[1] + "/" + split[2] + "/" + split[3] + "/app/";
                        for (int i = 4; i < split.length; i++) {
                            str2 = str2 + split[i] + "/";
                        }
                        return str2.substring(0, str2.length() - 1);
                    }
                } catch (Exception unused) {
                }
                str = trim;
            } catch (Exception unused2) {
            }
        }
        return str.trim();
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("URLEncoder.encode() failed for " + str);
        }
    }

    public static boolean isOpenNetwork(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
}
