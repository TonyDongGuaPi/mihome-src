package com.xiaomi.miot.store.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.taobao.weex.bridge.WXBridgeManager;
import com.xiaomi.miot.store.api.IMiotStoreActivityDelegate;
import com.xiaomi.miot.store.api.IMiotStoreApi;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.ui.MiotStoreReactActivityDelegate;
import com.xiaomi.miot.store.ui.ReactNativeFragment;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomi.youpin.youpin_common.login.IYouPinAccountManager;
import java.util.Map;
import org.cybergarage.upnp.RootDescription;

public class MiotStoreApiImpl implements IMiotStoreApi {
    public void initial(RNStoreApiProvider rNStoreApiProvider) {
        RNAppStoreApiManager.a().a((IMiotStoreApi) this, rNStoreApiProvider);
    }

    public void updateJSBundler() {
        RNAppStoreApiManager.a().n();
    }

    public IMiotStoreActivityDelegate newMiotStoreActivityDelegate(Activity activity) {
        return new MiotStoreReactActivityDelegate(activity);
    }

    public Fragment newMiotStoreFragment(String str, boolean z) {
        ReactNativeFragment reactNativeFragment = new ReactNativeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uri", str);
        if (z) {
            bundle.putBoolean(RootDescription.ROOT_ELEMENT, true);
            bundle.putBoolean("useFragment", true);
        }
        bundle.putString(WXBridgeManager.MODULE, "MiotStore");
        reactNativeFragment.setArguments(bundle);
        return reactNativeFragment;
    }

    public boolean sendJsEvent(String str, Map<String, Object> map) {
        return RNAppStoreApiManager.a().a(str, map);
    }

    public void openPage(String str) {
        RNAppStoreApiManager.a().b(str);
    }

    public void openPage(Activity activity, String str, int i) {
        RNAppStoreApiManager.a().a(activity, str, i);
    }

    public Activity getActivity() {
        return RNAppStoreApiManager.a().i();
    }

    public String getUserAgent() {
        return UserAgent.d();
    }

    public void updateAccount() {
        RNAppStoreApiManager.a().l();
    }

    public void clearAccount() {
        RNAppStoreApiManager.a().m();
    }

    public RNStoreApiProvider getAppStoreApiProvider() {
        return RNAppStoreApiManager.a().j();
    }

    public void setYouPinAccountManager(IYouPinAccountManager iYouPinAccountManager) {
        RNAppStoreApiManager.a().a(iYouPinAccountManager);
    }
}
