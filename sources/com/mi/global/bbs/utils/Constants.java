package com.mi.global.bbs.utils;

import android.content.Context;
import android.text.TextUtils;
import com.mi.account.sdk.util.Constants;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.model.PrizeShareInfo;
import com.mi.global.bbs.utils.Utils;

public class Constants {
    public static final String USER_TOP_BG_URL = "http://u01.appmifile.com/images/2017/07/24/385b0b1a-5b43-40d1-abcb-60db4015dbf3.jpg";

    public static final class App {
        public static final String ACTIVITY_GUIDE_IS_FIRST = "version_guide_is_first";
        public static final String CHANNEL_GOOGLEPLAY = "GOOGLEPLAY";
        public static final String CHANNEL_MSITE = "MSITE";
        public static final String CHANNEL_OTA = "OTA";
        public static final String FOR_YOU_IS_FIRST = "for_you_is_first";
        public static final String ISAPP = "ISAPP";
        public static final String PREF_CHANNEL = "pref_channel";
        public static final String PREF_IS_FIRST = "pref_is_first";
        public static final String PREF_LANG = "pref_lang";
        public static final String PREF_LOCALE = "pref_locale";
        public static final String PREF_LOCALE_HAS_SELECTED = "pref_locale_has_selected";
        public static final String PREF_LOCALE_NAME = "pref_locale_name";
        public static final String PREF_LOCALE_ORIGIN = "pref_locale_origin";
        public static final String PREF_VERSION = "pref_version";
        public static final String VERSION_26_IS_FIRST = "version_26_is_first";
    }

    public static class AppLink {
        public static final String PREF_APPLINK_HASLINK = "pref_applink_haslink";
        public static final String PREF_APPLINK_URL = "pref_applink_url";
    }

    public static final class ClickEvent {
        public static final String CLICK_COLUMN_MORE = "click_more";
        public static final String CLICK_COLUMN_MYSUB = "click_mysub";
        public static final String CLICK_COLUMN_SUBSCRIBE = "click_column_subscribe";
        public static final String CLICK_COMMENT = "click_comment";
        public static final String CLICK_DONE = "following_done";
        public static final String CLICK_FEED = "click_feed";
        public static final String CLICK_FEED_NO = "click_feed_no";
        public static final String CLICK_FOLLOW = "click_follow";
        public static final String CLICK_FOLLOW_MORE = "click_more";
        public static final String CLICK_FOLLOW_NO = "click_feed_no";
        public static final String CLICK_FRIEND = "click_friend";
        public static final String CLICK_HOME_FOR_YOU = "click_home_for_you";
        public static final String CLICK_HOME_FOR_YOU_NO = "click_home_for_you_no";
        public static final String CLICK_HOME_POPULAR = "click_home_popular";
        public static final String CLICK_HOME_POPULAR_NO = "click_home_popular_no";
        public static final String CLICK_LIKE = "click_like";
        public static final String CLICK_MI_HOME_BBS_TAB = "click_mi_home_bbs_tab";
        public static final String CLICK_NAG_POST = "click_post_nag";
        public static final String CLICK_OFFLINE_ACTIVITY = "click_offline_thread";
        public static final String CLICK_OFFLINE_ACTIVITY_LEAVE = "click_offline_no";
        public static final String CLICK_ONLINE_ACTIVITY = "click_online_thread";
        public static final String CLICK_ONLINE_ACTIVITY_LEAVE = "click_online_no";
        public static final String CLICK_PERSONALIZE = "click_personalize";
        public static final String CLICK_POST = "click_post";
        public static final String CLICK_REPLY = "click_reply";
        public static final String CLICK_SEARCH = "click_search";
        public static final String CLICK_SHARE = "click_share";
        public static final String CLICK_THREAD = "click_thread";
        public static final String CLICK_TOP_POST = "click_post_top";
        public static final String CLICK_USER = "click_user";
        public static final String QA_CLICK_ANSWER = "click_answer";
        public static final String QA_CLICK_NEW_NO = "click_new_no";
        public static final String QA_CLICK_POST = "click_post_question";
        public static final String QA_CLICK_THREAD = "click_thread";
        public static final String QA_CLICK_THREAD_NO = "click_trending_no";
        public static final String TO_HOME_FROM_STORE = "to_home_from_store";
    }

    public static final class Cookie {
        public static final String MAIN_SITE_1 = "mi.com";
        public static final String MAIN_SITE_2 = "xiaomi.com";
        public static final String MOBILE_SITE = "m.buy.mi.com";
    }

    public static final class Intent {
        public static final String ACTION_SHOW_PUSH_SITE = "com.mi.global.bbs.push";
    }

    public static final class PageFragment {
        public static final String PAGE_ACTIVITIES = "activities";
        public static final String PAGE_ACTIVITY = "activity";
        public static final String PAGE_COLUMN_DETAIL = "column_detail";
        public static final String PAGE_COLUMN_INDEX = "column_index";
        public static final String PAGE_COLUMN_LIST = "column_list";
        public static final String PAGE_COLUMN_SUBSCRIPTION = "column_subscription";
        public static final String PAGE_COMMENT = "comment_details";
        public static final String PAGE_DETAIL = "thread_details";
        public static final String PAGE_DISCOVER = "following_discover";
        public static final String PAGE_FOLLOWING = "following";
        public static final String PAGE_FOLLOWING_FEED = "following_feed";
        public static final String PAGE_FOLLOWING_NONE = "following_none";
        public static final String PAGE_FORU = "foru";
        public static final String PAGE_HOME = "home";
        public static final String PAGE_HOME_FROM_STORE = "from_mi_store_to_home";
        public static final String PAGE_HOT = "hot";
        public static final String PAGE_LATEST = "latest";
        public static final String PAGE_MI_HOME = "page_mi_home";
        public static final String PAGE_POST_DETAIL = "post_detail";
        public static final String PAGE_QA = "Q&A";
        public static final String PAGE_fORUM = "forum_details";
    }

    public static final class Prefence extends Constants.Prefence {
        public static final String PREF_KEY_ACTIVITY_CONFIG = "pref_key_activity_config";
        public static final String PREF_KEY_BLOCK_HOLD = "pref_key_block_hold";
        public static final String PREF_KEY_CACHE_CONFIG = "pref_key_cache_config";
        public static final String PREF_KEY_CART_NUM = "pref_key_shoppingcart_number";
        public static final String PREF_KEY_COLUMN_UPDATE = "pref_key_column_update";
        public static final String PREF_KEY_CUSTOM_COOKIES = "pref_key_custom_cookies";
        public static final String PREF_KEY_DYNAMIC_COOKIE_DOMAIN = "pref_key_dynamic_cookie_domain";
        public static final String PREF_KEY_DYNAMIC_COOKIE_DOMAIN_PATH = "pref_key_dynamic_cookie_domain_path";
        public static final String PREF_KEY_DYNAMIC_DOMAIN = "pref_key_dynamic_domain";
        public static final String PREF_KEY_DYNAMIC_DOMAIN_FEED = "pref_key_dynamic_domain_feed";
        public static final String PREF_KEY_DYNAMIC_DOMAIN_FEED_TEST = "pref_key_dynamic_domain_feed_test";
        public static final String PREF_KEY_DYNAMIC_DOMAIN_HD = "pref_key_dynamic_domain_hd";
        public static final String PREF_KEY_DYNAMIC_DOMAIN_HD_TEST = "pref_key_dynamic_domain_hd_test";
        public static final String PREF_KEY_DYNAMIC_DOMAIN_INFO = "pref_key_dynamic_domain_info";
        public static final String PREF_KEY_DYNAMIC_DOMAIN_TEST = "pref_key_dynamic_domain_test";
        public static final String PREF_KEY_FORUMS = "pref_key_forums";
        public static final String PREF_KEY_FORYOU_FOLLOW = "pref_key_foryou_follow";
        public static final String PREF_KEY_FORYOU_LIST = "pref_key_fouyou_list";
        public static final String PREF_KEY_FORYOU_RECOMMED = "pref_key_foryou_recommend";
        public static final String PREF_KEY_GAME_INFO = "pref_key_game_info";
        public static final String PREF_KEY_GROUP_ICON = "pref_key_group_icon";
        public static final String PREF_KEY_HOME_DATA = "pref_key_home_first_data";
        public static final String PREF_KEY_HOME_NEW_MESSAGE = "pref_key_home_new_mess_count";
        public static final String PREF_KEY_HOME_THREADS_MAX_ID = "pref_key_home_threads_max_id";
        public static final String PREF_KEY_LANAGER = "pref_key_language";
        public static final String PREF_KEY_NAVIGATION = "pref_key_navigation";
        public static final String PREF_KEY_OPEN_COUNTRY = "pref_key_open_country";
        public static final String PREF_KEY_PROFILE = "pref_key_profile";
        public static final String PREF_KEY_PUSH_SERVER = "pref_key_push_server";
        public static final String PREF_KEY_PWD_CHANGE = "pref_key_pwd_change";
        public static final String PREF_KEY_QA_FID_LIST = "pref_key_qa_fid_list";
        public static final String PREF_KEY_RECOMMEND = "pref_key_recommend";
        public static final String PREF_KEY_REGIONS = "pref_key_regions";
        public static final String PREF_KEY_SHARE_IMG = "pref_key_share_img";
        public static final String PREF_KEY_SHARE_PRIZE = "pref_key_share_prize";
        public static final String PREF_KEY_SHOP_TOKEN = "pref_key_shop_token";
        public static final String PREF_KEY_STORE_INFO = "pref_key_store_info";
        public static final String PREF_KEY_SYNC_DATA = "pref_key_sync_data";
        public static final String PREF_KEY_URL_POLICY = "pref_key_url_policy";
    }

    public static final class Push {
        public static final String SERVER_CN = "cn";
        public static final String SERVER_GLOBAL = "global";
        public static final String URL = "url";

        public static final class WaterMarkTypes {
            public static final String RECOMMEND_CAMPAIGN = "1";
        }
    }

    public static final class RequestCode {
        public static final int CODE_LOCALE_SWITCH_LANGUAGE_RESULT = 2;
        public static final int CODE_LOCALE_SWITCH_REGION_RESULT = 1;
        public static final int REQUEST_GO_POST = 55555;
    }

    public static final class Schema {
        public static final String CALL_MAIL_SCHEMA = "mailto:";
        public static final String CALL_TEL_SCHEMA = "tel:";
    }

    public static final class TitleMenu {
        public static final String MENU_ALARM = "alarm";
        public static final String MENU_DOWNLOAD = "download";
        public static final String MENU_FAVORITE = "favorite";
        public static final String MENU_FOLLOW = "isfollow";
        public static final String MENU_SEARCH = "search";
        public static final String MENU_SETTING_PUSH = "push_set";
        public static final String MENU_SHARE = "share";
        public static final String MENU_SHARE_EVENT = "share_event";
        public static final String MENU_TASK = "task";
        public static final String MENU_WRITE = "write";
        public static final String TYPE_FAVORITE = "thread";
        public static final String TYPE_FOLLOW = "forum";
    }

    public static final class WebView {
        public static final String CLICK_AD_BANNER = "click_ad_banner";
        public static final String CLICK_FAVORITE = "click_favorite";
        public static final String CLICK_ICON_RECOMMEND = "click_forum";
        public static final String CLICK_MENU = "click_menu";
        public static final String CLICK_NOTIFY = "click_notify";
        public static final String CLICK_POST = "click_post";
        public static final String CLICK_POST_SUBMIT = "click_post_submit";
        public static final String CLICK_QUESTION_SUBMIT = "click_question_submit";
        public static final String CLICK_SETTING = "click_setting";
        public static final String CLICK_SUGGEST = "click_suggest";
        public static final String CLICK_TASK = "click_task";
        public static final String CLICK_TOP_BANNER = "click_top_banner";
        public static final String CLICK_VIDEO = "click_video";
        public static final String PAGE_DETAIL = "page_detail";
        public static final String PAGE_FORUM = "page_forum";
        public static final String PAGE_INDEX = "page_index";
        public static final String PAGE_LIST = "page_list";
        public static final String PAGE_OTHER = "page_other";
        public static final String PAGE_PROFILE = "page_profile";
        public static final String POST_SUCCEED = "post_succeed";
        public static final String SHOP_LOGIN_URL = "account.xiaomi.com/pass/serviceLogin";
        public static final String WEB_LOGIN_URL = "action=login";
    }

    public static final class WebViewURL {
        public static final String GO_STORE_RN = "buy.mi.com";
        public static final String GO_STORE_RN_SECOND = "mobile.mi.com";
        public static final String GO_STORE_RN_SECOND_TEST = "mobile.test.mi.com";
        public static final String GO_STORE_RN_TEST = "buy.test.mi.com";
        public static final String PAGE_ACTIVITY = "open=activity_index";
        public static final String PAGE_COLUMN = "open=column_index";
        public static final String PAGE_COLUMN_DETAIL = "open=column_detail";
        public static final String PAGE_FEED = "open=feed";
        /* access modifiers changed from: private */
        public static final String PAGE_FORUM = (ConnectionHelper.getAppIndexUrl() + "forum.php");
        public static final String PAGE_HOME = "Home";
        /* access modifiers changed from: private */
        public static final String PAGE_INDEX = ConnectionHelper.getAppIndexUrl();
        /* access modifiers changed from: private */
        public static final String PAGE_LIST = (ConnectionHelper.getAppIndexUrl() + "forum-");
        public static final String PAGE_MIUI = "open=MIUI_index";
        /* access modifiers changed from: private */
        public static final String PAGE_PROFILE = (ConnectionHelper.getAppIndexUrl() + "space-uid");
        public static final String PAGE_QA = "open=activity_question";
        public static final String PAGE_SHORT_CONTENT_DETAIL = "open=shortMessage_detail";
        public static final String PAGE_SIGN_DETAIL = "open=sign_detail";
        public static final String PAGE_SUBFORUM = "open=subforum";
    }

    public static class YOUTUBE {
        public static final String DEVELOPER_KEY = "AIzaSyBwLn3YLa5rHgme07cUDyNhK376wtF-0yA";
        public static final String KEY_VIDEO_DEMO_ID = "6uZiMoquGWk";
        public static final String KEY_VIDEO_MAIN_ID = "vKXZUGDlD2A";
    }

    public static final class Image {
        public static String getDirectoryRoot(Context context) {
            return context.getFilesDir().getAbsolutePath() + "/bbs_image";
        }
    }

    public static class WebViewRes {
        public static final String ASSET_DIRECTORY_ROOT = "webview";
        public static final long DEFAULT_WEB_RES_VERSION = 0;
        public static final String PREF_WEB_RES_VERSION = "pref_web_res_version";
        public static final String WEBVIEW_CURRENT_URL = "webview_current_url";

        public static String getDirectoryRoot(Context context) {
            return context.getFilesDir().getAbsolutePath() + "/webview";
        }

        public static String getTempDirectoryRoot(Context context) {
            return context.getFilesDir().getAbsolutePath() + "/webview_temp";
        }

        public static String getTempPackageFilePath(Context context) {
            return getTempDirectoryRoot(context) + "/shopPackage";
        }

        public static String getPackageFilePath(Context context) {
            return getDirectoryRoot(context) + "/shopPackage";
        }

        public static String getVersionFilePath(Context context) {
            return getDirectoryRoot(context) + "/" + LocaleHelper.getLocalCookie() + "_version.txt";
        }
    }

    public static String getPageTypeByURL(String str) {
        if (TextUtils.isEmpty(str)) {
            return "other";
        }
        if (str.equals(WebViewURL.PAGE_INDEX)) {
            return WebView.PAGE_INDEX;
        }
        if (str.equals(WebViewURL.PAGE_FORUM)) {
            return WebView.PAGE_FORUM;
        }
        if (str.startsWith(WebViewURL.PAGE_PROFILE)) {
            return WebView.PAGE_PROFILE;
        }
        if (str.startsWith(WebViewURL.PAGE_LIST)) {
            return WebView.PAGE_LIST;
        }
        return str.startsWith(WebViewURL.PAGE_HOME) ? WebViewURL.PAGE_HOME : WebView.PAGE_OTHER;
    }

    public static final class Account extends Constants.Account {
        public static final String ACCOUNT_TYPE = "com.xiaomi";
        public static final String ACCOUNT_URL_BASE = "https://account.xiaomi.com/pass";
        public static final String ACCOUNT_URL_BASE_NEW = "https://www.account.xiaomi.com/pass";
        public static final String ACCOUNT_USER_NAME = "acc_user_name";
        public static final String API_URL_BASE = "http://api.account.xiaomi.com/pass";
        public static final String PREF_CACHE_SERVICE_TOKEN_LIST = "pref_cache_service_token_list";
        public static final String PREF_CUSER_ID = "pref_c_user_id";
        public static final String PREF_C_UID = "pref_c_uid";
        public static final String PREF_DATA_SAVER_ENABLED = "pref_data_saver_mode";
        public static final String PREF_EXTENDED_TOKEN = "pref_extended_token";
        public static final String PREF_LAST_REFRESH_SERVICETOKEN_TIME = "pref_last_refresh_serviceToken_time";
        public static final String PREF_LOGIN_SYSTEM = "pref_login_system";
        public static final String PREF_MUSER_ID = "pref_m_user_id";
        public static final String PREF_PASS_TOKEN = "pref_pass_token";
        public static final String PREF_SYSTEM_ASKED = "pref_asked_system";
        public static final String PREF_SYSTEM_C_UID = "pref_system_c_uid";
        public static final String PREF_SYSTEM_EXTENDED_TOKEN = "pref_system_extended_token";
        public static final String PREF_SYSTEM_UID = "pref_system_uid";
        public static final String PREF_UID = "pref_uid";
        public static final String PREF_USER_CREDITS = "pref_user_credits";
        public static final String PREF_USER_ICON = "bbs_pref_user_icons";
        public static final String PREF_USER_NAMES = "bbs_pref_user_names";
        public static final String PREF_USER_REPLIES = "pref_user_replies";
        public static final String PREF_USER_THREADS = "pref_user_threads";
        public static final String PREF_XM_IN_SID = "xm_in_sid";
        public static final String URL_LOGIN = "https://www.account.xiaomi.com/pass/serviceLogin";
        public static final String URL_LOGIN_AUTH = "https://www.account.xiaomi.com/pass/serviceLoginAuth";
        public static String URL_PASSWORD_RECOVERY = null;
        public static final String URL_QUERY_PHONE = "http://api.account.xiaomi.com/pass/activate/dev/%s/activating";
        public static final String URL_QUERY_SMS_GW = "/pass/config";
        public static final String URL_REG = "http://api.account.xiaomi.com/pass/user/full";
        public static final String URL_RESEND_EMAIL = "http://api.account.xiaomi.com/pass/sendActivateMessage";
        public static final String URL_USER_EXISTS = "http://api.account.xiaomi.com/pass/user@id";
        public static final String USER_NAME_SEPARATOR = ",";
        private static Account instance;

        public static void init() {
            if (instance == null) {
                instance = new Account();
                URL_PASSWORD_RECOVERY = "https://www.account.xiaomi.com/pass/forgetPassword?sid=" + getInstance().getServiceID() + "&_locale=" + LocaleHelper.getLocaleLang();
            }
        }

        public static Account getInstance() {
            return instance;
        }
    }

    public static final class ShareContent {
        public static final String SHARE_DES = "I'm reading this thread on Mi Community. Check it out! ";
        public static final String SHARE_IMG_URL = "http://u01.appmifile.com/images/2017/07/07/81ec742b-1a47-4f23-99e0-33ec8abe7d21.jpg";

        public static PrizeShareInfo getEventShare() {
            return new PrizeShareInfo(Utils.Preference.getStringPref(BBSApplication.getInstance(), Prefence.PREF_KEY_SHARE_PRIZE, ""));
        }
    }
}
