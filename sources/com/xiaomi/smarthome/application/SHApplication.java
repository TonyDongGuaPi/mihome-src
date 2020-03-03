package com.xiaomi.smarthome.application;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.internal.ThemeEnforcement;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.j256.ormlite.logger.LoggerFactory;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.account.openid.OauthAccountManager;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.jr.appbase.accounts.MiFiAccountNotifierImpl;
import com.xiaomi.loan.sdk.MiFiSdk;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.miot.store.wxpay.WxpayProvider;
import com.xiaomi.miot.support.monitor.MiotMonitorClient;
import com.xiaomi.miot.support.monitor.aop.trace.TraceActivity;
import com.xiaomi.mishopsdk.Listener.IShopAccountManager;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.youpin.YouPinManager;
import com.xiaomi.payment.channel.WXPayUtils;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.BuildConfig;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.acp.ACPUtil;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.auth.OAuthXiaomiAccount;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.core.server.CoreManager;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.device.PluginUPnPHostApiImp;
import com.xiaomi.smarthome.device.bluetooth.BluetoothManager;
import com.xiaomi.smarthome.device.bluetooth.search.BluetoothSearchManager;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.HostDependency;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.SDKSetting;
import com.xiaomi.smarthome.frame.baseui.PageHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.core.CoreHostApi;
import com.xiaomi.smarthome.frame.crash.FrameCrashHandler;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.LoginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginActivityHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginBluetoothManagerHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginBluetoothSearchManagerHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginCommonHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginUPnPHostApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.CoreBridgeImpl;
import com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager;
import com.xiaomi.smarthome.framework.commonapi.CommonApiV2;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.framework.crash.MainCrashHandler;
import com.xiaomi.smarthome.framework.crash.PluginCrashHandler;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.login.LoginHostApiImpl;
import com.xiaomi.smarthome.framework.page.PageHostApiImpl;
import com.xiaomi.smarthome.framework.page.verify.DevicePinVerifyEnterActivity;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginActivityHostApiImpl;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginCommonHostApiImpl;
import com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl;
import com.xiaomi.smarthome.framework.plugin.specific.RouterStartHandler;
import com.xiaomi.smarthome.framework.push.FamilyPushManager;
import com.xiaomi.smarthome.framework.push.PushManager;
import com.xiaomi.smarthome.framework.redpoint.ServerTimerManager;
import com.xiaomi.smarthome.framework.webview.Api64WebViewCompat;
import com.xiaomi.smarthome.globalnavbutton.GlobalNavButtonManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.util.WebViewCookieManager;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.ir.IRV2ControllerMiActivity;
import com.xiaomi.smarthome.monitor.MonitorConfigWare;
import com.xiaomi.smarthome.monitor.MonitorControllerImpl;
import com.xiaomi.smarthome.preload.PreloadInflateCache;
import com.xiaomi.smarthome.share.ShareManager;
import com.xiaomi.smarthome.shop.CurrentPage;
import com.xiaomi.smarthome.shop.RNStoreApiProviderImp;
import com.xiaomi.smarthome.shop.StoreApiProviderImp;
import com.xiaomi.smarthome.shop.WXStoreApiProviderImp;
import com.xiaomi.smarthome.shop.WebViewReceivedLoginRequest;
import com.xiaomi.smarthome.shop.mishop.ProductIdMapDataManager;
import com.xiaomi.smarthome.shop.mishop.YPAccountManager;
import com.xiaomi.smarthome.shop.mishop.YouPinProxy;
import com.xiaomi.smarthome.shop.utils.NetworkManager;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.stat.MiStat;
import com.xiaomi.youpin.AppInitialManagerImp;
import com.xiaomi.youpin.MJUrlSDKDispatcher;
import com.xiaomi.youpin.MiCSHelper;
import com.xiaomi.youpin.XmpluginHostApiImp;
import com.xiaomi.youpin.YouPinSplashManager;
import com.xiaomi.youpin.app_sdk.url_dispatch.SDKDispatcher;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.common.util.ProcessUtils;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.login.LoginDependencyApi;
import com.xiaomi.youpin.login.api.LoginConfig;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.stat.LoginStatInterface;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.setting.LoginConstant;
import com.xiaomi.youpin.mipay.MiFiAccountManagerImpl;
import com.xiaomi.youpin.share.config.ShareConfig;
import com.xiaomi.youpin.share.config.ShareDependency;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.login.IServiceTokenCallback;
import com.xiaomi.youpin.youpin_common.login.IYouPinAccountManager;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import com.xiaomiyoupin.toast.YPDToast;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.common.WXStoreApiProvider;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.cybergarage.util.CommonLog;

public class SHApplication extends ServiceApplication {
    public static final String ACTION_ON_APPLICATION_START = "action_on_application_start";
    /* access modifiers changed from: private */
    public static AtomicInteger activityCount = null;
    private static boolean isMainProcess = true;
    /* access modifiers changed from: private */
    public static long mAppEnterTime = 0;
    public static Map<Integer, WeakReference<Activity>> sActivityStack = new LinkedHashMap();
    public static volatile boolean sEnableLogPerf = true;
    private static IWXAPI sIWXAPI;
    private static Object sIWXAPILock = new Object();
    private static boolean sIsFacebookSdkInited = false;
    private static long sLastPrintTs = 0;
    public static volatile boolean sShopInitiled = false;
    public static SparseArray<View> viewCache = new SparseArray<>();

    public static void printTimestamp(String str) {
    }

    public SHApplication() {
        this.mResetRepeatedCrashRunnable = new Runnable() {
            public void run() {
                ACPUtil.a(SHApplication.getAppContext());
            }
        };
        FrameManager.a((Application) this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        TraceActivity.b().b(context);
        HawkEyeAspect.a().b(context);
        super.attachBaseContext(context);
    }

    @UiThread
    public static void initFacebookSdk() {
        if (!sIsFacebookSdkInited) {
            sIsFacebookSdkInited = true;
            FacebookSdk.sdkInitialize(getAppContext());
            AppEventsLogger.activateApp((Application) getApplication());
        }
    }

    public static void onShopInitial() {
        if (!sShopInitiled) {
            AppInfo.a((Application) sInstance.a());
            if (!HomeManager.A()) {
                RNAppStoreApiManager.a((Application) getApplication());
                initDelay();
            }
        }
    }

    private static void initDelay() {
        CommonApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    UrlDispatchManger.a().a((SDKDispatcher) new MJUrlSDKDispatcher());
                    XmpluginHostApiImp.a(ServiceApplication.getAppContext());
                    YouPinSplashManager.c().a();
                    AppInitialManagerImp.a(CommonApplication.sInstance.a());
                    SHApplication.initMiShopSDK();
                    StoreApiManager.a().a((StoreApiProvider) new StoreApiProviderImp());
                    MiotStoreApi.a(new RNStoreApiProviderImp());
                    WXAppStoreApiManager.b().a((WXStoreApiProvider) new WXStoreApiProviderImp());
                    SHApplication.setWXPayCallback();
                    XmPluginHostApi.instance().syncServerTime();
                    SHApplication.initMiFiSdk();
                    RNAppStoreApiManager.a().a((IYouPinAccountManager) new IYouPinAccountManager() {
                        public boolean a() {
                            return ServiceApplication.getStateNotifier().a() == 4;
                        }

                        public void a(String str, IServiceTokenCallback iServiceTokenCallback) {
                            MiServiceTokenInfo a2 = CoreApi.a().a(str);
                            if (a2 != null && iServiceTokenCallback != null) {
                                iServiceTokenCallback.a(a2.c);
                            }
                        }
                    });
                    MiCSHelper.b();
                    MiCSHelper.c();
                    SHApplication.initShare();
                    YPDToast.init(ServiceApplication.getAppContext());
                    NetworkManager.a().a((NetworkManager.NetworkListener) new NetworkManager.NetworkListener() {
                        public void a(int i, boolean z) {
                            if (i != 0) {
                                YouPinSplashManager.c().f();
                                ProductIdMapDataManager.a().b();
                            }
                        }
                    });
                    ProductIdMapDataManager.a().b();
                    YouPinSplashManager.c().f();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("rnbranch_changed");
                    LocalBroadcastManager.getInstance(CommonApplication.sInstance.a()).registerReceiver(new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            if (intent != null && intent.getAction() != null && "rnbranch_changed".equals(intent.getAction())) {
                                RNAppStoreApiManager.a().o();
                            }
                        }
                    }, intentFilter);
                    Api64WebViewCompat.a();
                    SHApplication.sShopInitiled = true;
                    LogUtils.d("jc", "onShopInitial time  : " + (System.currentTimeMillis() - currentTimeMillis));
                } catch (Throwable th) {
                    LogUtils.d("jc", "run: ex: " + Log.getStackTraceString(th));
                }
            }
        }, 2000);
    }

    /* access modifiers changed from: private */
    public static void initMiFiSdk() {
        MiFiSdk.Application.a(getAppContext(), getApplication());
        MiFiAccountManagerImpl miFiAccountManagerImpl = new MiFiAccountManagerImpl(getAppContext());
        MiFiSdk.a((MiFiAccountNotifierImpl) miFiAccountManagerImpl);
        MiFiSdk.a((IAccountProvider) miFiAccountManagerImpl);
        MiFiSdk.Application.a();
    }

    private static void ignoreThemeCheck() {
        try {
            if (!GlobalSetting.q) {
                Field declaredField = ThemeEnforcement.class.getDeclaredField("APPCOMPAT_CHECK_ATTRS");
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    declaredField.set((Object) null, new int[0]);
                }
                Field declaredField2 = ThemeEnforcement.class.getDeclaredField("MATERIAL_CHECK_ATTRS");
                if (declaredField2 != null) {
                    declaredField2.setAccessible(true);
                    declaredField2.set((Object) null, new int[0]);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void onAllStart() {
        System.setProperty("rx2.purge-enabled", "true");
        System.setProperty("rx2.purge-period-seconds", "120");
        ignoreThemeCheck();
        if (!HomeManager.A()) {
            onShopInitial();
        } else {
            getThreadExecutor().execute($$Lambda$SHApplication$TmNW3a0ttn7kZr6FG2QNYom38Iw.INSTANCE);
            if (BBSInitializer.a((Context) getApplication()) && BBSInitializer.a((Application) getApplication())) {
                LogUtil.b(BBSInitializer.b, "bbs init2");
            }
        }
        initAccount();
        initWeiboSDK();
        initOAuth();
        getGlobalWorkerHandler().postDelayed(new Runnable() {
            public void run() {
                PluginServiceManager.a().b();
                try {
                    SHApplication.initFacebookSdk();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }, 2000);
        if (!CoreApi.a().l()) {
            IntentFilter intentFilter = new IntentFilter(CoreHostApiImpl.e);
            LocalBroadcastManager.getInstance(getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    LocalBroadcastManager.getInstance(ServiceApplication.getAppContext()).unregisterReceiver(this);
                    SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (CoreApi.a().q()) {
                                new RouterStartHandler().a();
                            }
                        }
                    }, 1000);
                }
            }, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter(CoreHostApiImpl.f1534a);
            LocalBroadcastManager.getInstance(getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (GlobalSetting.u) {
                        Log.d("login", "Receiver ACTION_ON_ACCOUNT_READY");
                    }
                    LogUtilGrey.a("login", "application Receiver ACTION_ON_ACCOUNT_READY");
                    LocalBroadcastManager.getInstance(ServiceApplication.getAppContext()).unregisterReceiver(this);
                    if (CoreApi.a().q()) {
                        SHApplication.getStateNotifier().c();
                        FrameManager.b().g().a(1);
                    }
                }
            }, intentFilter2);
        } else if (CoreApi.a().q()) {
            FrameManager.b().g().a(1);
            new RouterStartHandler().a();
        }
        getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                LocalBroadcastManager.getInstance(ServiceApplication.getAppContext()).sendBroadcast(new Intent(SHApplication.ACTION_ON_APPLICATION_START));
            }
        }, 7200000);
        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityCreated(final Activity activity, Bundle bundle) {
                if (SHApplication.sActivityStack.size() == 0 && SHApplication.shouldEnableBugly()) {
                    CommonApplication.getThreadExecutor().submit(new Runnable() {
                        public void run() {
                            CrashReport.initCrashReport(SHApplication.getAppContext(), "b1d4bd688c", true);
                            CrashReport.putUserData(SHApplication.getAppContext(), "miui-version", SystemApi.b());
                        }
                    });
                }
                SHApplication.sActivityStack.put(Integer.valueOf(activity.hashCode()), new WeakReference(activity));
                if (GlobalNavButtonManager.a().c()) {
                    GlobalNavButtonManager a2 = GlobalNavButtonManager.a();
                    final View a3 = a2.a(activity, activity.hashCode() + "");
                    if (a3 != null) {
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                GlobalNavButtonManager.a(activity, a3);
                            }
                        }, 10);
                    } else {
                        return;
                    }
                }
                MainCrashHandler.a(activity.getClass().getSimpleName() + (activity.hashCode() & 255) + " created " + (System.currentTimeMillis() / 1000));
            }

            public void onActivityStarted(Activity activity) {
                if (SHApplication.activityCount == null) {
                    AtomicInteger unused = SHApplication.activityCount = new AtomicInteger(1);
                } else if (SHApplication.activityCount.getAndIncrement() == 0) {
                    long unused2 = SHApplication.mAppEnterTime = System.currentTimeMillis();
                    STAT.f22748a.a(activity);
                }
            }

            public void onActivityResumed(Activity activity) {
                StatManager.c();
                CommonApplication.activeActivityCount.incrementAndGet();
                MainCrashHandler.a(activity.getClass().getSimpleName() + (activity.hashCode() & 255) + " resumed " + (System.currentTimeMillis() / 1000));
            }

            public void onActivityPaused(Activity activity) {
                StatManager.b();
                CommonApplication.activeActivityCount.decrementAndGet();
                MainCrashHandler.a(activity.getClass().getSimpleName() + (activity.hashCode() & 255) + " paused " + (System.currentTimeMillis() / 1000));
            }

            public void onActivityStopped(Activity activity) {
                if (SHApplication.activityCount != null && SHApplication.activityCount.decrementAndGet() == 0) {
                    STAT.i.a(System.currentTimeMillis() - SHApplication.mAppEnterTime);
                    STAT.f22748a.b(activity);
                }
            }

            public void onActivityDestroyed(Activity activity) {
                SHApplication.sActivityStack.remove(Integer.valueOf(activity.hashCode()));
                if (SHApplication.sActivityStack.isEmpty()) {
                    SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (SHApplication.sActivityStack.isEmpty()) {
                                try {
                                    CrashReport.closeBugly();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                GlobalNavButtonManager.a().b();
                            }
                        }
                    }, 10);
                }
                MainCrashHandler.a(activity.getClass().getSimpleName() + (activity.hashCode() & 255) + " destroyed " + (System.currentTimeMillis() / 1000));
            }
        });
        UserConfigCompactManager.a().b();
    }

    static /* synthetic */ void lambda$onAllStart$0() {
        if (ShopGlobalHelper.a((Context) getApplication())) {
            ShopGlobalHelper.a(false);
        }
    }

    public static boolean shouldEnableBugly() {
        return !GlobalSetting.u ? !HomeManager.A() : !HomeManager.B();
    }

    public static int getForegroundActivityCount() {
        if (activityCount == null) {
            return 0;
        }
        return activityCount.get();
    }

    public void onApplicationLifeCycleStart() {
        if (getAppContext() == null || !isMainProcess) {
            return;
        }
        if (sApplicationStart) {
            if (!sShopInitiled) {
                onShopInitial();
            }
            getThreadExecutor().execute($$Lambda$SHApplication$_uFTDac43xYjMyuzJTOoolDhF0.INSTANCE);
            if (BBSInitializer.a((Context) getApplication()) && BBSInitializer.a((Application) getApplication())) {
                LogUtil.b(BBSInitializer.b, "bbs init");
                return;
            }
            return;
        }
        sApplicationStart = true;
        int size = LifeCycleManager.a().b().size();
        for (int i = 0; i < size; i++) {
            try {
                LifeCycleManager.a().b().get(i).b();
            } catch (Exception unused) {
            }
        }
        try {
            onAllStart();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static /* synthetic */ void lambda$onApplicationLifeCycleStart$1() {
        if (ShopGlobalHelper.a((Context) getApplication())) {
            ShopGlobalHelper.a(false);
        }
    }

    public static SHApplication getApplication() {
        return (SHApplication) sInstance.a();
    }

    public static PushManager getPushManager() {
        return PushManager.a();
    }

    public String getPushId() {
        return getPushManager().g();
    }

    public static CommonApiV2 getCommonApiV2() {
        return CommonApiV2.a();
    }

    public static Dialog showLoginDialog(final Activity activity, final boolean z) {
        return new MLAlertDialog.Builder(activity).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginApi.a().a((Context) activity, 1, (LoginApi.LoginCallback) null);
                dialogInterface.dismiss();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (z) {
                    activity.finish();
                }
            }
        }).a(true).b((int) R.string.loing_helper_title).d();
    }

    public static IWXAPI getIWXAPI() {
        if (sIWXAPI == null) {
            synchronized (sIWXAPILock) {
                if (sIWXAPI == null) {
                    sIWXAPI = WXAPIFactory.createWXAPI(getAppContext(), GlobalSetting.e, true);
                    sIWXAPI.registerApp(GlobalSetting.e);
                }
            }
        }
        return sIWXAPI;
    }

    public static com.tencent.mm.sdk.openapi.IWXAPI getIWXAPI2() {
        return com.tencent.mm.sdk.openapi.WXAPIFactory.createWXAPI(getAppContext(), GlobalSetting.e, true);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LanguageUtil.a(configuration);
    }

    public static void initAccount() {
        if (MiLoginApi.a() == null) {
            try {
                String a2 = UserAgentUtil.a((Context) sInstance.a());
                MiLoginApi.a((Application) sInstance.a(), new LoginConfig.Builder().a(getAppContext()).a(0).a(true).a("wxxmzh", getIWXAPI()).a("xiaomiio").a("miotstore", "xiaoqiang", "passportapi", "xiaomihome", "mi_eshopm_go", "i.ai.mi.com", "mi_huodong", "ypsupport2", LoginConstant.n).b("").b("passportapi").c("").d("690839864422812").b(true).a((LoginDependencyApi) new LoginDependencyApi() {
                    public String a(String str) {
                        return "";
                    }

                    public void a(WebView webView, String str, String str2, String str3, final LoginDependencyApi.OnReceivedLoginRequestCallback onReceivedLoginRequestCallback) {
                        WebViewReceivedLoginRequest.a(webView, str, str2, str3, new StoreApiProvider.OnReceivedLoginRequestCallback() {
                            public void a(Intent intent) {
                                if (onReceivedLoginRequestCallback != null) {
                                    onReceivedLoginRequestCallback.a(intent);
                                }
                            }
                        });
                    }

                    public boolean a() {
                        return CoreApi.a().q();
                    }

                    public boolean b() {
                        return CoreApi.a().D();
                    }

                    public Pair<Long, Boolean> c() {
                        if (ServerTimerManager.a(ServiceApplication.getAppContext()).b()) {
                            return new Pair<>(Long.valueOf(ServerTimerManager.a(ServiceApplication.getAppContext()).c()), true);
                        }
                        return ServerTimerManager.e();
                    }
                }).e(a2).a((LoginStatInterface) new LoginStatInterface() {
                    public void a(String str, @Nullable Map<String, String> map) {
                    }

                    public void b(String str, @Nullable Map<String, String> map) {
                    }
                }).c());
                LogUtilGrey.a("userAgent", "ua:" + a2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void initWeiboSDK() {
        if (!HomeManager.A()) {
            getGlobalWorkerHandler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        WbSdk.install(ServiceApplication.getAppContext(), new AuthInfo(ServiceApplication.getAppContext(), GlobalSetting.g, "https://api.weibo.com/oauth2/default.html", ""));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }, 3000);
        }
    }

    private static void initOAuth() {
        try {
            OauthAccountManager.setOauthAccount(new OAuthXiaomiAccount((Context) sInstance.a()));
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.a(LoginType.k, (Throwable) e);
        }
    }

    private void initMiStatV3() {
        String str;
        if (GlobalSetting.u) {
            Log.d("MiStat", "initMiStatV3");
        }
        MiStat.initialize(this, HostSetting.n, HostSetting.o, true);
        ServerBean a2 = ServerCompact.a((Context) this);
        if (a2 == null) {
            str = "";
        } else {
            str = a2.b;
        }
        MiStat.setInternationalRegion(ServerCompact.e((Context) this), str);
        if (StartupCheckList.a()) {
            MiStat.setNetworkAccessEnabled(true);
        } else {
            MiStat.setNetworkAccessEnabled(false);
        }
        if (HostSetting.g) {
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a("MiStat", "MiStatDeviceID = " + MiStat.getDeviceId());
        }
        if (ServerCompact.g((Context) this)) {
            MiStat.setStatisticEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public static void initShare() {
        YouPinShareApi.a(new ShareConfig.Builder(getAppContext()).a(getIWXAPI()).a(GlobalSetting.e).b(GlobalSetting.g).a((ShareDependency) new ShareDependency() {
            public String a() {
                return CurrentPage.a();
            }

            public void a(String str, String str2, String str3) {
                XmPluginHostApi.instance().addTouchRecord(str, str2, str3);
            }

            public void a(String str, String str2, String str3, int i) {
                XmPluginHostApi.instance().addViewRecord(str, str2, str3, i);
            }

            public void b() {
                XmPluginHostApi.instance().addViewEndRecord();
            }

            public void a(Context context) {
                XmPluginHostApi.instance().startScreenshotDetecting(context);
            }

            public void c() {
                XmPluginHostApi.instance().stopScreenshotDetecting();
            }

            public Dialog a(Context context, String str) {
                XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
                xQProgressDialog.setMessage(str);
                xQProgressDialog.show();
                return xQProgressDialog;
            }
        }).a());
    }

    /* access modifiers changed from: private */
    public static void initMiShopSDK() {
        ShopApp.getInstance().onInitAsync((Application) getApplication(), true, (IShopAccountManager) new YPAccountManager(getApplication()));
        ShopApp.getInstance().setSdkProperty(Constants.SdkSettings.KEY_ENABLE_TINT, Constants.SdkSettings.VALUE_TINT_ALL_ENABLE);
        ShopApp.getInstance().setSdkProperty(Constants.SdkSettings.KEY_HOME_BTN_VISIBLE, "false");
        YouPinManager.setYouPinProxy(new YouPinProxy());
        if (CoreApi.a().q()) {
            LoginManager.getInstance().login();
        }
        getGlobalWorkerHandler().postDelayed($$Lambda$SHApplication$GXTTPc92oEr1XgTHRlWO0aQ3C0.INSTANCE, 1000);
    }

    private void exportAppBuildConfig() {
        GlobalSetting.j = false;
        GlobalSetting.k = "release";
        GlobalSetting.l = BuildConfig.g;
        GlobalSetting.m = "GooglePlay";
        GlobalSetting.n = 63000;
        GlobalSetting.o = "5.6.60";
        GlobalSetting.p = "com.xiaomi.smarthome";
        GlobalSetting.q = false;
        GlobalSetting.r = true;
        GlobalSetting.s = false;
        GlobalSetting.v = "GooglePlay";
        GlobalSetting.w = false;
        GlobalSetting.y = false;
        GlobalSetting.z = SmartHomeMainActivity.class;
        GlobalSetting.A = DevicePinVerifyEnterActivity.class;
        GlobalSetting.B = IRV2ControllerMiActivity.class;
        GlobalSetting.u = false;
        GlobalSetting.H = ProcessUtil.h(this);
        GlobalSetting.I = Process.myPid();
        GlobalSetting.J = Process.myUid();
    }

    public void onCreate() {
        sOnCreateTime = System.currentTimeMillis();
        sLastPrintTs = sOnCreateTime;
        System.setProperty(LoggerFactory.LOG_TYPE_SYSTEM_PROPERTY, LoggerFactory.LogType.ANDROID.toString());
        System.setProperty("log.tag.ORMLite", "ERROR");
        boolean b = ProcessUtil.b(this);
        isMainProcess = b;
        exportAppBuildConfig();
        CommonLog.isDebug = GlobalSetting.q;
        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        }
        super.onCreate();
        initMonitorConfig();
        if (!b && ProcessUtil.e(this)) {
            Thread.setDefaultUncaughtExceptionHandler(new PluginCrashHandler(getAppContext()));
        } else if (b) {
            Thread.setDefaultUncaughtExceptionHandler(new MainCrashHandler(getAppContext()));
            getGlobalHandler().postDelayed(this.mResetRepeatedCrashRunnable, 10000);
            ACPUtil.c(this);
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new FrameCrashHandler(getAppContext()));
        }
        FrameManager.b().a(this, new SDKSetting.Builder().a(true).a(), new HostSetting.Builder().a(GlobalSetting.j).a(GlobalSetting.k).a(GlobalSetting.l).b(GlobalSetting.m).b(GlobalSetting.n).c(GlobalSetting.o).b(GlobalSetting.q).c(GlobalSetting.r).d(GlobalSetting.s).d(GlobalSetting.v).e(GlobalSetting.w).f(GlobalSetting.x).g(GlobalSetting.y).e(GlobalSetting.f1547a).f(GlobalSetting.b).g(GlobalSetting.d).h(GlobalSetting.e).a(), new HostDependency.Builder().a((CoreHostApi) CoreHostApiImpl.g()).a((PageHostApi) PageHostApiImpl.a()).a((LoginHostApi) new LoginHostApiImpl()).a((PluginHostApi) new PluginHostApiImpl(getAppContext())).a((PluginActivityHostApi) new PluginActivityHostApiImpl()).a((PluginCommonHostApi) new PluginCommonHostApiImpl()).a((PluginUPnPHostApi) new PluginUPnPHostApiImp()).a((PluginBluetoothSearchManagerHostApi) new BluetoothSearchManager()).a((PluginBluetoothManagerHostApi) new BluetoothManager()).a());
        BluetoothContextManager.a((Context) this, HostSetting.h);
        LanguageUtil.c();
        CoreBridgeImpl.f();
        initMiStatV3();
        if (b) {
            LogUtils.setEnableLog(false);
            FrescoInitial.a(false);
            WebViewCookieManager.a((Context) this);
            PushManager.a();
            ShareManager.a();
            FamilyPushManager.a();
            sNotifier = new AppStateNotifier();
            MonitorControllerImpl.d();
            if (StartupCheckList.a()) {
                getApplication().onApplicationLifeCycleStart();
            }
        }
    }

    public static void setWXPayCallback() {
        WXPayUtils.b = GlobalSetting.e;
        WXPayUtils.a(new WXPayUtils.WXPayCallback() {
            public void a(BaseResp baseResp) {
                if (baseResp instanceof PayResp) {
                    PayResp payResp = (PayResp) baseResp;
                    SHApplication.notifyPayResult(payResp.errCode, payResp.returnKey);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void notifyPayResult(int i, String str) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getAppContext());
        Intent intent = new Intent(WxpayProvider.f11439a);
        intent.putExtra("extra_error_code", i);
        intent.putExtra(WxpayProvider.c, str);
        instance.sendBroadcast(intent);
    }

    public void onTerminate() {
        WXPayUtils.a();
        MiFiSdk.Application.b();
        super.onTerminate();
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (ProcessUtils.d()) {
            if (i == 5 || i == 10 || i == 15) {
                FrescoInitial.b.a(MemoryTrimType.OnCloseToDalvikHeapLimit);
                if (FrescoInitial.f1497a) {
                    ImagePipelineFactory.getInstance().getImagePipeline().clearMemoryCaches();
                }
            } else if (i == 20) {
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("action.youpin.go.background"));
                FrescoInitial.b.a(MemoryTrimType.OnAppBackgrounded);
            } else if (i == 40 || i == 60 || i == 80) {
                FrescoInitial.b.a(MemoryTrimType.OnSystemLowMemoryWhileAppInForeground);
            }
        }
    }

    public Resources getResources() {
        if (getAppContext() == null) {
            return super.getResources();
        }
        return LanguageUtil.a(super.getResources(), ServerCompact.c((Context) this));
    }

    /* access modifiers changed from: package-private */
    public void startPreloadView() {
        getGlobalHandler().post(new Runnable() {
            public void run() {
                AnonymousClass1PreloadEmptyActivity r0 = new Activity() {
                    public void a(Context context) {
                        attachBaseContext(context);
                    }

                    public Object getSystemService(@NonNull String str) {
                        if (TextUtils.equals(str, "layout_inflater")) {
                            return super.getSystemService(str);
                        }
                        return ServiceApplication.getAppContext().getSystemService(str);
                    }
                };
                r0.a(SHApplication.getAppContext());
                PreloadInflateCache.a(LayoutInflater.from(r0));
            }
        });
    }

    public void initMonitorConfig() {
        MiotMonitorClient.a(getApplicationContext(), isMainProcess ? true : isMonitorProcess(), new MonitorConfigWare(isMainProcess).a());
    }

    private boolean isMonitorProcess() {
        String a2 = com.xiaomi.miot.support.monitor.utils.ProcessUtils.a();
        return TextUtils.equals(a2, "com.xiaomi.smarthome") || TextUtils.equals(a2, CoreManager.d) || TextUtils.equals(a2, CoreManager.g) || TextUtils.equals(a2, "com.xiaomi.smarthome:plugin0") || TextUtils.equals(a2, "com.xiaomi.smarthome:plugin1") || TextUtils.equals(a2, "com.xiaomi.smarthome:plugin2");
    }
}
