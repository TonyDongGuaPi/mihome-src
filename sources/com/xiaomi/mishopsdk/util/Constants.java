package com.xiaomi.mishopsdk.util;

import android.app.Application;

public class Constants {
    public static final int CALLIGRAPHY_TAG_PRICE = 67108864;
    public static String REAL_PACKAGE_NAME = null;
    public static int TARGET_SDK_VERSION = 0;
    public static final int UNINITIALIZED_NUM = -1;
    public static final String WEBVIEW_UPDATE_SERVICE_NAME = "webviewupdate";
    public static String WX_APP_ID;

    public static class PermissionRequestCode {
        public static final int PERMISSION_REQUEST_CODE_BASE = 18;
        public static final int PERMISSION_REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 21;
    }

    public static class Plugin {
        public static String ACTION_CART = "com.xiaomi.mishopsdk.pluginCart";
        public static String ACTION_CHILD = "com.xiaomi.mishopsdk.pluginChild";
        public static final String ACTION_HOMEPAGE = "com.xiaomi.mishopsdk.mainActivity";
        public static String ACTION_ROOT = "com.xiaomi.mishopsdk.pluginRoot";
        public static String ACTION_SEARCH = "com.xiaomi.mishopsdk.search";
        public static String ACTION_WEBVIEW = "com.xiaomi.mishopsdk.webview";
        public static final String ARGUMENT_FORCEVRESION = "forceVersion";
        public static final String ARGUMENT_LOGCODE = "log_code";
        public static final String ARGUMENT_PAGEID = "pageId";
        public static final String ARGUMENT_PLUGINEXTRA = "extra";
        public static final String ARGUMENT_PLUGINID = "pluginId";
        public static final String ARGUMENT_PLUGININFO = "pluginInfo";
        public static final String ARGUMENT_PLUGINPREVIOUS = "pluginPrevious";
        public static final String PLUGINID_ADDRESS = "105";
        public static final String PLUGINID_ADJUMP = "10004";
        public static final String PLUGINID_ADVERTISE = "10003";
        public static final String PLUGINID_BARCODE = "110";
        public static final String PLUGINID_CART = "102";
        public static final String PLUGINID_CATEGORY = "10002";
        public static final String PLUGINID_COMMENTS = "104";
        public static final String PLUGINID_COUPON = "107";
        public static final String PLUGINID_FAV = "118";
        public static final String PLUGINID_GOODSDETAIL = "101";
        public static final String PLUGINID_HDCHANNEL = "15102";
        public static final String PLUGINID_HOMEPAGE = "100";
        public static final String PLUGINID_MIHOME = "108";
        public static final String PLUGINID_MISHOPPUSH = "140";
        public static final String PLUGINID_MISHOPSTAT = "163";
        public static final String PLUGINID_NOPLUGIN = "10005";
        public static final String PLUGINID_ORDER = "103";
        public static final String PLUGINID_PHOTOPICKER = "112";
        public static final String PLUGINID_PRODUCTLIST = "116";
        public static final String PLUGINID_REVIEWS = "113";
        public static final String PLUGINID_SEARCH = "109";
        public static final String PLUGINID_SETTING = "115";
        public static final String PLUGINID_USERCENTER = "106";
        public static final String PLUGINID_WEBVIEW = "111";
    }

    public static class Preference {
        public static final String PREFERNCE_KEY_CLIENTVERSION = "mishopsdk_clientVersionCode";
        public static final String PREF_KEY_HEADER_UUID = "mishopsdk_pref_key_header_uuid";
        public static final String PREF_KEY_LASTTRIM_TIME = "mishopsdk_pref_key_lasttrim_time";
        public static final String PREF_NOMORE_EULA = "mishopsdk_pref_nomore_eula";
        public static final String PREF_PRIVACY_AGREE = "pref_privacy_agree_first_open";
    }

    public static class SdkSettings {
        public static final String KEY_ENABLE_CUSTOMER_SERVICE = "key_enable_cs";
        public static final String KEY_ENABLE_TINT = "key_enable_tint";
        public static final String KEY_HOME_BTN_VISIBLE = "key_home_btn_visible";
        public static final String VALUE_FALSE = "false";
        public static final String VALUE_TINT_ALL_ENABLE = "tint_all_enable";
        public static final String VALUE_TINT_DISABLE = "tint_disable";
        public static final String VALUE_TINT_MIUI6_ENABLE = "tint_miui6_enable";
        public static final String VALUE_TRUE = "true";
    }

    public static class SdkVersions {
        public static final int SUPPORT_CS = 20180513;
        public static final int SUPPORT_HOME_CLEAR_TOP = 20161201;
        public static final int SUPPORT_NOTCH = 20180427;
        public static final int SUPPORT_WEBLOGIN = 20161018;
        public static final int SUPPORT_YOUPIN = 20180427;
    }

    public static class Split {
        public static final String CTRL_A = "\u0001";
        public static final String CTRL_B = "\u0002";
        public static final String CTRL_C = "\u0003";
        public static final String CTRL_D = "\u0004";
        public static final String CTRL_E = "\u0005";
    }

    public static void onInit(Application application) {
        REAL_PACKAGE_NAME = application.getPackageName();
        WX_APP_ID = AndroidUtil.getMetaDataString("WXPAY_APPID");
        TARGET_SDK_VERSION = application.getApplicationInfo().targetSdkVersion;
    }

    public static boolean needRequestPermision() {
        return TARGET_SDK_VERSION >= 23;
    }
}
