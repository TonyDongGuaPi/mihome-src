package com.mi.global.bbs.manager;

import android.app.Application;
import android.content.Intent;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.observer.LocalManager;
import com.mi.global.bbs.ui.HomeFragment;
import com.mi.global.bbs.ui.MineActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.Utils;
import com.mi.log.LogUtil;
import com.mi.util.Utils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import io.reactivex.functions.Consumer;

public class MiCommunitySdkManager {
    private static final String TAG = "MiCommunitySdkManager";
    private static MiCommunitySdkManager instance;
    private SdkListener listener;
    private boolean useSdkLogin = false;

    private MiCommunitySdkManager() {
    }

    public static MiCommunitySdkManager getInstance() {
        if (instance == null) {
            synchronized (MiCommunitySdkManager.class) {
                if (instance == null) {
                    instance = new MiCommunitySdkManager();
                }
            }
        }
        return instance;
    }

    public void setUseSdkLogin(boolean z) {
        this.useSdkLogin = z;
    }

    public boolean isUseSdkLogin() {
        return this.useSdkLogin;
    }

    public void setLoginData(boolean z, String str, ExtendedAuthToken extendedAuthToken) {
        Application instance2 = BBSApplication.getInstance();
        Utils.Preference.setStringPref(instance2, "pref_uid", str);
        Utils.Preference.setStringPref(instance2, "pref_extended_token", extendedAuthToken.toPlain());
        if (z) {
            Utils.Preference.setStringPref(instance2, "pref_system_extended_token", extendedAuthToken.toPlain());
        }
        if (LoginManager.getInstance().hasLogin()) {
            LoginManager.getInstance().loginCallback();
        }
        miHomeNewUser();
        if (MineActivity.isCurrentShown) {
            instance2.sendBroadcast(new Intent(MineActivity.REFRESH_BROADCAST_ACTION));
        }
    }

    private void miHomeNewUser() {
        ApiClient.miHomeNewUser().subscribe(new Consumer<BaseResult>() {
            public void accept(BaseResult baseResult) {
                if (baseResult == null || baseResult.getErrno() != 0) {
                    LogUtil.b(MiCommunitySdkManager.TAG, "miHomeNewUser fail");
                } else {
                    LogUtil.b(MiCommunitySdkManager.TAG, "miHomeNewUser success");
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                if (th != null) {
                    LogUtil.a(MiCommunitySdkManager.TAG, th.getLocalizedMessage());
                }
            }
        });
    }

    public void invalidateLoginData() {
        Application instance2 = BBSApplication.getInstance();
        Utils.Preference.setStringPref(instance2, "pref_uid", (String) null);
        Utils.Preference.setStringPref(instance2, "pref_extended_token", (String) null);
        Utils.Preference.setStringPref(instance2, "pref_system_extended_token", (String) null);
    }

    public void changeRegion(String str) {
        if (LoginManager.getInstance().hasLogin()) {
            LoginManager.getInstance().logout();
        }
        clearForyouPrefer();
        LocalManager.init().localChange(true);
        if ("in".equals(str)) {
            LocaleHelper.switchLocale(0);
        } else if (Region.RU.equals(str)) {
            LocaleHelper.switchLocale(2);
        }
    }

    public void onBBSFragmentChecked() {
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_MI_HOME, Constants.ClickEvent.CLICK_MI_HOME_BBS_TAB, Constants.ClickEvent.CLICK_MI_HOME_BBS_TAB);
    }

    private void clearForyouPrefer() {
        HomeFragment.foryouRefreshed = false;
        Utils.Preference.removePref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_LIST);
        Utils.Preference.removePref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_FOLLOW);
        Utils.Preference.removePref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_FORYOU_RECOMMED);
    }

    public void setListener(SdkListener sdkListener) {
        if (sdkListener != null) {
            this.listener = sdkListener;
        }
    }

    public SdkListener getListener() {
        return this.listener;
    }
}
