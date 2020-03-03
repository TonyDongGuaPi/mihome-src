package com.xiaomi.miot.store.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import java.util.HashMap;
import java.util.Map;

public interface RNStoreApiProvider {

    public interface GetUserInfoCallback {
        void onFail(int i, String str);

        void onSuccess(HashMap<String, String> hashMap, HashMap<String, String> hashMap2);
    }

    public interface OnReceivedLoginRequestCallback {
        void onNeedGrantCredentialsPermission(Intent intent);
    }

    void bindWeixin(Callback callback);

    void deleteChat(String str, String str2, Callback callback);

    boolean enableStore();

    void getBadgeCount(String str, String str2, Callback callback);

    void getChatList(Callback callback);

    void getMessageHistory(String str, String str2, int i, Callback callback);

    void getRecentContactList(Callback callback);

    String getServerLocalCode();

    void handleException(Throwable th);

    void handleHiddenException(String str, Throwable th);

    void iniVerify(Activity activity, ReadableMap readableMap);

    void insertEventToCalendar(Activity activity, ReadableMap readableMap, Callback callback);

    boolean isRNDebug();

    void logout();

    void multiupdateExtra(ReadableMap readableMap, String str, Callback callback);

    void navigateBack(Activity activity, String str);

    void onActivityCreate(Activity activity);

    void onActivityDestroy(Activity activity);

    void onActivityPause(Activity activity);

    void onActivityResume(Activity activity);

    void onInitial(IMiotStoreApi iMiotStoreApi);

    void onReactContextInitialed();

    void openChangePasswordPage(Activity activity, Callback callback);

    void openLoginPage(Context context);

    void openUrl(String str, String str2);

    void setBadge(String str, String str2, int i);

    void shareCustom(ReadableMap readableMap, Callback callback);

    String shouldOpenUrl(String str, String str2);

    void showAppstoreComment();

    int startCustomerServiceChat(Context context, Map<String, String> map);

    void startVerify(ReadableMap readableMap, Callback callback);

    boolean useFragment();
}
