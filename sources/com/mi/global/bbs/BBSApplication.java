package com.mi.global.bbs;

import android.app.Application;
import android.content.Context;
import android.webkit.WebView;
import com.mi.MiApplicationContext;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.db.DbCore;
import com.mi.global.bbs.manager.InitOptions;
import com.mi.global.bbs.utils.ChannelUtil;
import com.mi.global.bbs.utils.CrashInterception;
import com.mi.global.bbs.utils.LocaleHelper;
import com.mi.global.bbs.utils.Setting;
import com.mi.global.bbs.utils.UIAdapter;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.mi.mistatistic.sdk.controller.MiStatOptions;
import com.mi.util.Constants;
import com.mi.util.Device;
import com.mi.util.LeakCanaryUtil;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ScreenInfo;
import com.mi.util.permission.PermissionUtil;

public class BBSApplication {
    public static boolean DEBUG = false;
    private static boolean TEST = false;
    private static InitOptions initOptions = null;
    private static volatile boolean initialize = false;
    private static Application instance;

    public static void bbsInit(Application application, boolean z, InitOptions initOptions2) {
        instance = application;
        initOptions = initOptions2;
        TEST = z;
        Constants.b = z;
        MiApplicationContext.f1260a = application;
        Device.a(application, PermissionUtil.a((Context) application, "android.permission.READ_PHONE_STATE"));
        LogUtil.a(z);
        ScreenInfo.a().a(application);
        RequestQueueUtil.a(application);
        LeakCanaryUtil.a(application);
        LocaleHelper.initLocaleAndLanguage();
        if (!initialize) {
            initialize = true;
            init();
        }
    }

    public static void webViewClear() {
        try {
            new WebView(instance).destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Application getApplication() {
        return instance;
    }

    private static void init() {
        UIAdapter.getInstance().initAdapter();
        ChannelUtil.init();
        LoginManager.init(instance);
        Setting.initSettingDataSaverEnabled();
        DbCore.init(instance);
        if (initOptions.isUseCrashReporter()) {
            CrashInterception.initCatchGMSException();
        }
        if (initOptions.isUseStatistics()) {
            MiStatInterface.a(instance, new MiStatOptions.Builder().a(instance.getResources().getString(R.string.statistic_crash_id)).b(Device.B).a(TEST).d(LoginManager.getInstance().getUserId()).g(false).d(true).a());
        }
    }

    public static Application getInstance() {
        return instance;
    }

    public static boolean isUserDebug() {
        return DEBUG;
    }

    public static boolean isUserTest() {
        return TEST;
    }
}
