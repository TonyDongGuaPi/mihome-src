package com.mi.global.bbs.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.android.volley.VolleyLog;
import com.mi.account.sdk.util.Constants;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.LaunchInfo;
import com.mi.global.bbs.model.SyncModel;
import com.mi.global.bbs.ui.MainActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import com.mi.log.LogUtil;
import com.taobao.weex.annotation.JSMethod;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class SplashUtil {
    private static final String FILENAME = "splash.jpg";
    protected static final int HTTP_REQUEST_TIMEOUT_MS = 10000;
    /* access modifiers changed from: private */
    public static final String IMAGE_PATH = (Environment.getExternalStorageDirectory() + "/com.mi.global.bbs/");
    private static final String JSON_TAG_END_TIME = "endTime";
    private static final String JSON_TAG_GIF_URL = "gif";
    private static final String JSON_TAG_IMG_URL = "img";
    private static final String JSON_TAG_START_TIME = "startTime";
    private static final String TAG = "SplashUtil";

    public static File getSplashFile() {
        if (!isSplashValid()) {
            return null;
        }
        return new File(IMAGE_PATH + LocaleHelper.APP_LOCALE + JSMethod.NOT_SET + FILENAME);
    }

    public static LaunchInfo getLaunchInfo() {
        Application instance = BBSApplication.getInstance();
        String stringPref = Utils.Preference.getStringPref(instance, "pref_key_splash_info_" + LocaleHelper.APP_LOCALE, "");
        if (TextUtils.isEmpty(stringPref)) {
            return null;
        }
        try {
            return LaunchInfo.parse(new JSONObject(stringPref));
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isSplashValid() {
        Application instance = BBSApplication.getInstance();
        String stringPref = Utils.Preference.getStringPref(instance, "pref_key_splash_info_" + LocaleHelper.APP_LOCALE, "");
        if (TextUtils.isEmpty(stringPref)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(stringPref);
            String string = jSONObject.getString("startTime");
            String string2 = jSONObject.getString("endTime");
            long parseLong = Long.parseLong(string);
            long parseLong2 = Long.parseLong(string2);
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            LogUtil.b(TAG, "start:" + parseLong + ", end:" + parseLong2 + ", now:" + currentTimeMillis);
            if (currentTimeMillis < parseLong || currentTimeMillis > parseLong2) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static void checkActivityConfig(Context context, JSONObject jSONObject) {
        if (jSONObject != null && context != null) {
            try {
                String optString = jSONObject.optString("url");
                long optLong = jSONObject.optLong("startTime");
                long optLong2 = jSONObject.optLong("endTime");
                int optInt = jSONObject.optInt("duration");
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                LogUtil.b(TAG, "url:" + optString + ",startTime:" + optLong + ",endTime:" + optLong2 + ",duration:" + optInt);
                if (!TextUtils.isEmpty(optString) && currentTimeMillis >= optLong && currentTimeMillis < optLong2) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("url", optString);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.b(TAG, "ActivityConfig parse error" + e.toString());
            }
        }
    }

    public static void loadInfo(String str) {
        try {
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_SYNC_DATA, str);
            JSONObject jSONObject = new JSONObject(str);
            SyncModel.response = jSONObject;
            JSONArray optJSONArray = jSONObject.optJSONObject("data").optJSONArray("inBrowserUrls");
            if (optJSONArray != null) {
                String[] strArr = new String[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    strArr[i] = optJSONArray.getString(i);
                }
                SyncModel.inBrowserUrls = strArr;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONObject("data").optJSONArray("inAppUrls");
            if (optJSONArray2 != null) {
                String[] strArr2 = new String[optJSONArray2.length()];
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    strArr2[i2] = optJSONArray2.getString(i2);
                }
                SyncModel.inAppUrls = strArr2;
            }
            JSONArray optJSONArray3 = jSONObject.optJSONObject("data").optJSONArray("inHardAccelerUrls");
            if (optJSONArray3 != null) {
                String[] strArr3 = new String[optJSONArray3.length()];
                for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                    strArr3[i3] = optJSONArray3.getString(i3);
                }
                SyncModel.inHardAccelerUrls = strArr3;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject != null) {
                Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_key_cache_config", optJSONObject.optString("cacheConfig"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_PROFILE, optJSONObject.optString("profile"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_RECOMMEND, optJSONObject.optString("recommend"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_NAVIGATION, optJSONObject.optString("navigation"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_INFO, optJSONObject.optString("dynamicDomain"));
                getDomainInfo();
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_SHARE_PRIZE, optJSONObject.optString("prizeShareConfig"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_SHARE_IMG, optJSONObject.optString("shareImage"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORUMS, optJSONObject.optString("fidlist"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_QA_FID_LIST, optJSONObject.optString("qafidlist"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_key_activity_config", optJSONObject.optString("activityConfig"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_GAME_INFO, optJSONObject.optString("gameinfo"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_STORE_INFO, optJSONObject.optString("storeinfo"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_REGIONS, optJSONObject.optString("regions"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_URL_POLICY, optJSONObject.optString("policyUrl"));
                Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_PWD_CHANGE, optJSONObject.optInt("pwdChange"));
                Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_BLOCK_HOLD, optJSONObject.optInt("blockHold"));
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_GROUP_ICON, optJSONObject.optString("groupIcon"));
            }
        } catch (Exception e) {
            LogUtil.b(TAG, " SyncModel.response Exception " + e.toString());
            VolleyLog.d(TAG, "JSON parse error" + e.toString());
        }
    }

    private static void getDomainInfo() {
        try {
            JSONObject jSONObject = new JSONObject(Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_INFO, ""));
            String optString = jSONObject.optString("dynamicDomainTest");
            String optString2 = jSONObject.optString("dynamicHDDomainTest");
            String optString3 = jSONObject.optString("dynamicFeedDomainTest");
            String optString4 = jSONObject.optString("dynamicHDDomain");
            String optString5 = jSONObject.optString("dynamicFeedDomain");
            String optString6 = jSONObject.optString("dynamicDomain");
            String optString7 = jSONObject.optString("dynamicCookieDomain");
            String optString8 = jSONObject.optString("dynamicSid");
            String optString9 = jSONObject.optString("dynamicStartTime");
            if (TextUtils.isEmpty(optString6)) {
                optString6 = ConnectionHelper.DEFAULT_DOMIN_ONLINE;
            }
            if (TextUtils.isEmpty(optString)) {
                optString = ConnectionHelper.DEFAULT_DOMIN_TEST;
            }
            if (TextUtils.isEmpty(optString4)) {
                optString4 = ConnectionHelper.DEFAULT_DOMIN_HD_ONLINE;
            }
            if (TextUtils.isEmpty(optString2)) {
                optString2 = ConnectionHelper.DEFAULT_DOMIN_HD_TEST;
            }
            if (TextUtils.isEmpty(optString5)) {
                optString5 = ConnectionHelper.DEFAULT_DOMIN_FEED_ONLINE;
            }
            if (TextUtils.isEmpty(optString3)) {
                optString3 = ConnectionHelper.DEFAULT_DOMIN_FEED_TEST;
            }
            if (TextUtils.isEmpty(optString7)) {
                optString7 = "mi.com";
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!TextUtils.isEmpty(optString9) && currentTimeMillis >= Long.parseLong(optString9)) {
                ApiClient.setApiServiceNull();
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN, optString6);
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_FEED, optString5);
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_HD, optString4);
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_TEST, optString);
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_FEED_TEST, optString3);
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_DOMAIN_HD_TEST, optString2);
                Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_DYNAMIC_COOKIE_DOMAIN, optString7);
                Application instance = BBSApplication.getInstance();
                Utils.Preference.setStringPref(instance, Constants.Prefence.PREF_KEY_DYNAMIC_COOKIE_DOMAIN_PATH, optString7 + "/" + LocaleHelper.getLocalCookie());
            }
            Constants.Account.accountSid = optString8;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class LoadImageThread extends Thread {
        private String mImageUrl;
        private JSONObject mJson;

        public LoadImageThread(JSONObject jSONObject) {
            this.mJson = jSONObject;
            String optString = jSONObject.optString(SplashUtil.JSON_TAG_GIF_URL);
            if (!TextUtils.isEmpty(optString)) {
                this.mImageUrl = optString;
            } else {
                this.mImageUrl = jSONObject.optString("img");
            }
        }

        private boolean saveFile(byte[] bArr, String str, String str2) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdir();
            }
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str + str2)));
                bufferedOutputStream.write(bArr);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                LogUtil.b(SplashUtil.TAG, "save splash.jpg success");
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        public void run() {
            Process.setThreadPriority(10);
            byte[] imageFromNetByUrl = SplashUtil.getImageFromNetByUrl(this.mImageUrl);
            if (imageFromNetByUrl != null && Environment.getExternalStorageState().equals("mounted")) {
                SplashUtil.clearSplash();
                String access$000 = SplashUtil.IMAGE_PATH;
                if (saveFile(imageFromNetByUrl, access$000, LocaleHelper.APP_LOCALE + JSMethod.NOT_SET + SplashUtil.FILENAME)) {
                    Application instance = BBSApplication.getInstance();
                    Utils.Preference.setStringPref(instance, "pref_key_splash_info_" + LocaleHelper.APP_LOCALE, this.mJson.toString());
                }
            }
        }
    }

    public static byte[] getImageFromNetByUrl(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(10000);
            return readInputStream(httpURLConnection.getInputStream());
        } catch (Exception unused) {
            LogUtil.b(TAG, "failed to get splash.jpg");
            return null;
        }
    }

    public static byte[] readInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void clearSplash() {
        Application instance = BBSApplication.getInstance();
        Utils.Preference.removePref(instance, "pref_key_splash_info_" + LocaleHelper.APP_LOCALE);
        try {
            File file = new File(IMAGE_PATH + LocaleHelper.APP_LOCALE + JSMethod.NOT_SET + FILENAME);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }
}
