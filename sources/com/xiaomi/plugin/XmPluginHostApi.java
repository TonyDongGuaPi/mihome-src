package com.xiaomi.plugin;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.google.gson.JsonObject;
import com.xiaomi.miot.store.api.IPayProvider;
import com.xiaomi.plugin.RedpointManager;
import com.xiaomi.plugin.account.MiServiceTokenInfo;
import com.xiaomi.plugin.location.ILocationListener;
import com.xiaomi.plugin.update.AppUpdateManager;
import com.xiaomi.youpin.youpin_network.util.YouPinParamsUtil;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class XmPluginHostApi {
    public static final String ACTION_ON_LOGIN = "com.xiaomi.youpin.action.on_login";
    public static final String ACTION_ON_LOGOUT = "com.xiaomi.youpin.action.on_logout";
    public static final String ANIM_FADE_IN_LEFT = "fade_in_left";
    public static final String ANIM_FADE_IN_RIGHT = "fade_in_right";
    public static final String ANIM_FADE_OUT_LEFT = "fade_out_left";
    public static final String ANIM_FADE_OUT_RIGHT = "fade_out_right";
    public static final String ANIM_SLIDE_IN_BOTTOM = "slide_in_bottom";
    public static final String ANIM_SLIDE_IN_LEFT = "slide_in_left";
    public static final String ANIM_SLIDE_IN_RIGHT = "slide_in_right";
    public static final String ANIM_SLIDE_IN_TOP = "slide_in_top";
    public static final String ANIM_SLIDE_OUT_BOTTOM = "slide_out_bottom";
    public static final String ANIM_SLIDE_OUT_LEFT = "slide_out_left";
    public static final String ANIM_SLIDE_OUT_RIGHT = "slide_out_right";
    public static final String ANIM_SLIDE_OUT_TOP = "slide_out_top";
    public static final String DATA_TYPE_LIMITED_TIME_PURCHASE = "data_type_limited_time_purchase";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final int PAGE_STATE_ISBACK = 1;
    public static final int PAGE_STATE_NORMAL = 2;
    protected static XmPluginHostApi sXmPluginHostApi;

    public interface SharedValueListener {
        void onValueChanged(String str, String str2, Object obj);
    }

    public abstract void addBadgeUpdateListener(RedpointManager.BadgeUpdateListener badgeUpdateListener);

    public abstract void addPayFailedRecord(String str, String str2, String str3);

    public abstract void addPaySuccessRecord(String str, String str2, String str3);

    public abstract void addRecord(String str, String str2, String str3, Object obj, Map<String, Object> map, String str4);

    public abstract void addRecord(String str, String str2, String str3, Object obj, Map<String, Object> map, boolean z);

    public abstract void addTouchRecord(String str, String str2, String str3);

    public abstract void addTouchRecord1(String str, String str2, String str3);

    public abstract void addTouchRecord1(String str, String str2, String str3, String str4);

    public abstract void addTouchRecord2(String str, String str2, String str3, String str4);

    public abstract void addTouchRecord2(String str, String str2, String str3, String str4, String str5);

    public abstract void addViewEndRecord();

    public abstract void addViewRecord(String str, String str2, String str3, int i);

    public abstract void addVisibleRecord(String str, String str2, String str3);

    public abstract void addVisibleRecord(String str, String str2, String str3, String str4);

    public abstract void addVisibleRecord(String str, String str2, String str3, String str4, String str5);

    public abstract String appId();

    public abstract Application application();

    public abstract void bindWx(AsyncCallback<Void, Error> asyncCallback);

    public abstract void clearBadge();

    public abstract Context context();

    public abstract String createSpm(String str, String str2, String str3);

    public abstract void downloadFile(String str, String str2, ProgressCallback<Void> progressCallback);

    public abstract void enableBlackTranslucentStatus(Window window);

    public abstract void enableWhiteTranslucentStatus(Window window);

    public abstract String getAccountId();

    public abstract void getAccountInfo(Callback<AccountInfo> callback);

    public abstract void getAccountInfo(boolean z, Callback<AccountInfo> callback);

    public abstract Intent getActivityIntent(XmPluginPackage xmPluginPackage, String str);

    public abstract AppUpdateManager getAppUpdateManager();

    public abstract int getAppVersionCode();

    public abstract String getAppVersionName();

    public abstract int getBadgeCount();

    public abstract int getBadgeCount(String str);

    public abstract int getBadgeCount(String str, String str2);

    public abstract String getChannel();

    public abstract String getCurrentPage();

    public abstract String getCurrentPageUrl();

    public abstract String getEncryptedAccountId();

    public abstract Fragment getFragmentByUrl(Activity activity, String str, boolean z);

    public abstract String getIMEI();

    public abstract void getLocationInfo(ILocationListener iLocationListener);

    public abstract MiServiceTokenInfo getMiServiceToken(String str);

    public abstract int getPageModel();

    public abstract String getPassToken();

    public abstract IPayProvider getPay(String str);

    public abstract Object getPreferenceValue(String str, Object obj);

    public abstract void getPwdCaptchaImage(String str, AsyncCallback<Pair<Bitmap, String>, Error> asyncCallback);

    public abstract Fragment getRNFragmentByUrl(Activity activity, String str);

    public abstract RedpointManager getRedpointManager();

    public abstract long getServerTime();

    public abstract Object getSharedValue(String str, String str2);

    public abstract void getSharedValue(String str, Callback<String> callback);

    public abstract int getStatusHeight(Context context);

    public abstract String getUserAgent();

    public abstract void getWxTouristAccountInfo(Callback<WxTouristAccount> callback);

    public abstract boolean hasSwitch(String str);

    public abstract void httpRequest(String str, String str2, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, Callback<byte[]> callback);

    public abstract boolean isAccountLogined();

    public abstract boolean isCurrentInShop();

    public abstract boolean isDevMode();

    public abstract boolean isReactNatvieDebug();

    public abstract boolean isSetPassword();

    public abstract boolean isTranslucentStatusEnabled();

    public abstract boolean isTranslucentStatusbarEnable();

    public abstract void isWXBind(AsyncCallback<Boolean, Error> asyncCallback);

    public abstract boolean isWxTouristLogin();

    public abstract boolean isYoupinHost(String str);

    public abstract void login(Context context);

    public abstract void login(Context context, String str);

    public abstract void logout(int i, Callback<Void> callback);

    public abstract void logout(Callback<Void> callback);

    public abstract void onActivityCreate(Activity activity);

    public abstract void onActivityDestroy(Activity activity);

    public abstract void openChangePasswordPage(Activity activity, int i);

    public abstract void openH5UserHome();

    public abstract void openUrl(Activity activity, String str, int i);

    public abstract void openUrl(String str);

    public abstract void openUrl(String str, SPM spm);

    public abstract void openUrlFromMainTab(String str);

    public abstract void openWeexDevTool(String str);

    public abstract void overridePendingTransition(Activity activity, String str, String str2);

    public abstract String registerAppKey();

    public abstract void registerSharedValueChangeListener(String str, SharedValueListener sharedValueListener);

    public abstract void removeBadgeUpdateListener(RedpointManager.BadgeUpdateListener badgeUpdateListener);

    public abstract void removeValue(String str, String str2);

    public abstract void routeToPhoneBind(AsyncCallback<Void, Error> asyncCallback);

    public abstract void scanBanner(String str, Callback<String> callback);

    public abstract boolean sendJsEvent(String str, Map<String, Object> map);

    public abstract <T> void sendMijiaShopRequest(String str, JsonObject jsonObject, Callback<T> callback, Parser<T> parser, boolean z);

    public abstract void sendMijiaShopRequest(String str, List<Request> list, boolean z);

    public abstract void sendNetLog(Context context);

    public abstract <T> void sendRawMijiaShopRequest(String str, String str2, String str3, Callback<T> callback, Parser<T> parser, boolean z);

    public abstract <T> void sendRawMijiaShopRequest(String str, String str2, String str3, String str4, File file, Callback<T> callback, Parser<T> parser, boolean z);

    public abstract void sendRequestWithHeader(String str, String str2, HashMap<String, String> hashMap, String str3, boolean z, boolean z2, AsyncCallback<String, Error> asyncCallback);

    public abstract void sendYouPinNewRequest(String str, String str2, String str3, boolean z, boolean z2, AsyncCallback<String, Error> asyncCallback);

    public abstract void setBadge(String str, String str2, int i);

    public abstract void setPageModel(int i);

    public abstract void setPreferenceValue(String str, Object obj);

    public abstract void setReactNativeDebug(boolean z);

    public abstract void setSharedValue(String str, String str2, Callback<Void> callback);

    public abstract void setSharedValue(String str, String str2, Object obj);

    public abstract void setTextViewFont(int i, TextView textView);

    public abstract void setTitleBarPadding(View view);

    public abstract void share(Context context, String str);

    public abstract void sharePicOrVideo(Context context, String str, String str2, String str3);

    public abstract void showNotification(String str, String str2, int i, PendingIntent pendingIntent);

    public abstract void startActivity(Context context, XmPluginPackage xmPluginPackage, Class<? extends XmPluginBaseActivity> cls, Intent intent);

    public abstract void startActivityForResult(Context context, XmPluginPackage xmPluginPackage, Class<? extends XmPluginBaseActivity> cls, Intent intent, int i);

    public abstract int startChat(Context context, Map<String, String> map);

    public abstract void startScreenshotDetecting(Context context);

    public abstract String statGetSpmref(String str);

    public abstract void stopLocation();

    public abstract void stopScreenshotDetecting();

    public abstract List<String> supportPayList();

    public abstract void syncServerTime();

    public abstract void unbindWX(AsyncCallback<Void, Error> asyncCallback);

    public abstract void unregisterSharedValueChangeListener(String str, SharedValueListener sharedValueListener);

    public abstract void updatePortrait(String str, AsyncCallback<String, Error> asyncCallback);

    public abstract void updateProductIdMap();

    public abstract void updateSetPasswordInfo(Callback<Boolean> callback);

    public abstract void updateUserNickName(String str, AsyncCallback<Void, Error> asyncCallback);

    public abstract void uploadStatic(boolean z);

    public static XmPluginHostApi instance() {
        return sXmPluginHostApi;
    }

    public void sendMijiaShopRequest(String str, Request request, boolean z) {
        sendMijiaShopRequest(str, (List<Request>) Arrays.asList(new Request[]{request}), z);
    }

    public <T> void sendMijiaShopRequest1(String str, String str2, Callback<T> callback, Parser<T> parser, boolean z) {
        sendMijiaShopRequest(YouPinParamsUtil.b, new Request(new RequestParams(str, str2), callback, parser), z);
    }

    public <T> void sendMijiaShopRequest2(String str, String str2, Callback<T> callback, Parser<T> parser, boolean z) {
        sendMijiaShopRequest(YouPinParamsUtil.f23868a, new Request(new RequestParams(str, str2), callback, parser), z);
    }

    public <T> void sendMijiaShopRequest1(RequestParams requestParams, Callback<T> callback, Parser<T> parser, boolean z) {
        sendMijiaShopRequest(YouPinParamsUtil.b, new Request(requestParams, callback, parser), z);
    }

    public <T> void sendMijiaShopRequest2(RequestParams requestParams, Callback<T> callback, Parser<T> parser, boolean z) {
        sendMijiaShopRequest(YouPinParamsUtil.f23868a, new Request(requestParams, callback, parser), z);
    }

    public void sendMijiaShopRequest2(List<Request> list, boolean z) {
        sendMijiaShopRequest(YouPinParamsUtil.f23868a, list, z);
    }

    public void addTouchRecord(String str, String str2) {
        addTouchRecord(str, str2, "");
    }

    public void addTouchRecord(String str, String str2, String str3, String str4) {
        addTouchRecord(str3, str4, "");
    }
}
