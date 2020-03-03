package com.xiaomi.miot.store.api;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.xiaomi.youpin.youpin_common.login.IYouPinAccountManager;
import java.util.Map;

public interface IMiotStoreApi {
    void clearAccount();

    Activity getActivity();

    RNStoreApiProvider getAppStoreApiProvider();

    String getUserAgent();

    void initial(RNStoreApiProvider rNStoreApiProvider);

    IMiotStoreActivityDelegate newMiotStoreActivityDelegate(Activity activity);

    Fragment newMiotStoreFragment(String str, boolean z);

    void openPage(Activity activity, String str, int i);

    void openPage(String str);

    boolean sendJsEvent(String str, Map<String, Object> map);

    void setYouPinAccountManager(IYouPinAccountManager iYouPinAccountManager);

    void updateAccount();

    void updateJSBundler();
}
