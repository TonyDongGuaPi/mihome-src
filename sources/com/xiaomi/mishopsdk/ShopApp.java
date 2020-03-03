package com.xiaomi.mishopsdk;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.mishopsdk.Listener.IShopAccountManager;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.fragment.BaseFragment;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.plugin.Model.PluginRuntimeEnv;
import com.xiaomi.mishopsdk.plugin.PluginSyncManager;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.AESUtil;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Coder;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.PicUtil;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import com.xiaomi.mishopsdk.util.ScreenInfo;
import com.xiaomi.mishopsdk.util.ThreadPool;
import com.xiaomi.mishopsdk.utils.SoDefender;
import com.xiaomi.mishopsdk.utils.StringUtils;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.util.Device;
import com.xiaomi.shop2.util.DeviceUtil;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShopApp {
    private static final String APP_ACCOUNT_MANAGER_CLASS = "com.xiaomi.mishopsdk.account.adapter.AppAccountManager";
    private static final String SYSTEM_ACCOUNT_MANAGER_CLASS = "com.xiaomi.mishopsdk.account.adapter.SystemAccountManager";
    private static final String TAG = "ShopApp";
    @Deprecated
    public static String channelId = "";
    public static Application instance = null;
    public static long invalidate_event_time = 0;
    public static boolean isMijiaMode = false;
    public static volatile Handler sApplicationHandler = null;
    private static boolean sIsCookiesInited = false;
    private static ShopApp sShopApp = null;
    public static int sShoppingCount = -1;
    private boolean hasDeviceInit = false;
    private boolean mHasInitPermission;
    /* access modifiers changed from: private */
    public AtomicBoolean mInitAsyncFinished = new AtomicBoolean(false);
    private boolean mInitIsAsync = false;
    private boolean mNetworkPermit;
    private HashMap<String, String> mSdkSettings = new HashMap<>();
    private IShopAccountManager mShopAccountManager;
    private HashMap<String, PluginRuntimeEnv> m_vPluginRuntimes = new HashMap<>();
    private IWXAPI wxAPI = null;

    public interface IAccountManagerFactory {
        IShopAccountManager createAccountManager();
    }

    public static final synchronized ShopApp getInstance() {
        ShopApp shopApp;
        synchronized (ShopApp.class) {
            if (sShopApp == null) {
                sShopApp = new ShopApp();
            }
            shopApp = sShopApp;
        }
        return shopApp;
    }

    protected ShopApp() {
    }

    public final void onInit(Application application, boolean z, IShopAccountManager iShopAccountManager) {
        SoDefender.defendLoadBaseLibrary(application);
        instance = application;
        Constants.onInit(instance);
        sApplicationHandler = new Handler(instance.getMainLooper());
        DeviceUtil.getInstance().preInit(instance);
        ScreenInfo.getInstance().initialScreenInfo(application);
        PicUtil.getInstance().init(application);
        RequestQueueManager.getInstance().init(application);
        this.mShopAccountManager = iShopAccountManager;
        this.mNetworkPermit = z;
        PluginSyncManager.getInstance().initManager();
        initStatService(instance);
        if (!isSystemApp()) {
            onPermissionInit();
        }
    }

    public boolean isSystemApp() {
        return TextUtils.equals(BuildConfig.FLAVOR, "systemApp");
    }

    public boolean hasInitCta() {
        return PreferenceUtil.getBooleanPref(instance, Constants.Preference.PREF_PRIVACY_AGREE, false);
    }

    public void onPermissionInit() {
        if (!this.hasDeviceInit) {
            this.hasDeviceInit = true;
            LoginManager.getInstance();
            if (this.mShopAccountManager == null) {
                try {
                    this.mShopAccountManager = (IShopAccountManager) Class.forName(APP_ACCOUNT_MANAGER_CLASS).getConstructor(new Class[]{Application.class}).newInstance(new Object[]{instance});
                } catch (Exception e) {
                    Log.e(TAG, "can not create %s.", APP_ACCOUNT_MANAGER_CLASS, e);
                    HashMap hashMap = new HashMap();
                    hashMap.put("new ShopAccountManager is null", "ShopAccountManager");
                    StatService.onError(instance, e, hashMap);
                }
            }
            LoginManager.getInstance().setIShopAccountManager(this.mShopAccountManager);
            Device.init(instance);
            DeviceUtil.getInstance().init();
            autoLogin(this.mShopAccountManager);
            if (this.mNetworkPermit) {
                PluginSyncManager.getInstance().checkTimerScheduled();
            }
        }
    }

    public final void onInitAsync(final Application application, final boolean z, final IShopAccountManager iShopAccountManager) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            instance = application;
            this.mShopAccountManager = iShopAccountManager;
            LoginManager.getInstance().setIShopAccountManager(iShopAccountManager);
            this.mInitIsAsync = true;
            AndroidUtil.sAliveRequestQueue.postRunnable(new Runnable() {
                public void run() {
                    synchronized (ShopApp.this.mInitAsyncFinished) {
                        try {
                            ShopApp.this.onInit(application, z, iShopAccountManager);
                        } catch (Exception e) {
                            Log.e(ShopApp.TAG, "onInitAsync with iShopAccountManager failed.", (Object) e);
                        }
                        ShopApp.this.mInitAsyncFinished.set(true);
                    }
                }
            });
            return;
        }
        throw new RuntimeException("onInitAsync must run in main thread.");
    }

    public final void onInitAsync(final Application application, final boolean z, final IAccountManagerFactory iAccountManagerFactory) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            instance = application;
            this.mInitIsAsync = true;
            AndroidUtil.sAliveRequestQueue.postRunnable(new Runnable() {
                public void run() {
                    synchronized (ShopApp.this.mInitAsyncFinished) {
                        try {
                            ShopApp.this.onInit(application, z, iAccountManagerFactory.createAccountManager());
                        } catch (Exception e) {
                            Log.e(ShopApp.TAG, "onInitAsync failed.", (Object) e);
                        }
                        ShopApp.this.mInitAsyncFinished.set(true);
                    }
                }
            });
            return;
        }
        throw new RuntimeException("onInitAsync must run in main thread.");
    }

    @Deprecated
    public final void waitForInitAsync() {
        if (this.mInitIsAsync) {
            do {
            } while (!this.mInitAsyncFinished.get());
        }
    }

    public final void setSdkProperty(String str, String str2) {
        this.mSdkSettings.put(str, str2);
    }

    public final void setMijiaMode(boolean z) {
        isMijiaMode = z;
        SdkUtils.updateMijiaMode();
    }

    public final String getSdkProperty(String str, String str2) {
        return this.mSdkSettings.containsKey(str) ? this.mSdkSettings.get(str) : str2;
    }

    private void autoLogin(IShopAccountManager iShopAccountManager) {
        switch (iShopAccountManager.getLoginType()) {
            case 1:
                boolean z = true;
                if (LoginManager.getInstance().hasLogin()) {
                    String accountId = LoginManager.getInstance().getAccountId();
                    String prefUserId = LoginManager.getInstance().getPrefUserId();
                    if (TextUtils.isEmpty(accountId) || (!TextUtils.isEmpty(accountId) && !TextUtils.isEmpty(prefUserId) && !TextUtils.equals(accountId, prefUserId))) {
                        LoginManager.getInstance().logout();
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    AndroidUtil.sStageQueue.postRunnable(new Runnable() {
                        public void run() {
                            String accountAuthToken = LoginManager.getInstance().getAccountAuthToken("eshopmobile");
                            if (!TextUtils.isEmpty(accountAuthToken)) {
                                LoginManager.getInstance().loginSystem(accountAuthToken);
                                LoginManager.getInstance().setSystemLogin(true);
                            }
                        }
                    });
                    return;
                }
                return;
            case 2:
            case 3:
                if (iShopAccountManager.hostAccountHasLogin() && !LoginManager.getInstance().hasLogin()) {
                    LoginManager.getInstance().login();
                    return;
                }
                return;
            default:
                Log.e(TAG, "fatal error: autoLogin get an unknown login type.");
                return;
        }
    }

    public final void setNetworkPermit(boolean z) {
        this.mNetworkPermit = z;
    }

    public final void onActivityResumed() {
        if (this.mNetworkPermit) {
            PluginSyncManager.getInstance().checkTimerScheduled();
        }
    }

    public final void addPluginRuntime(PluginRuntimeEnv pluginRuntimeEnv) {
        if (pluginRuntimeEnv != null && pluginRuntimeEnv.pluginInfo != null) {
            String md5 = StringUtils.md5(pluginRuntimeEnv.pluginInfo.localPath);
            if (this.m_vPluginRuntimes.containsKey(md5)) {
                this.m_vPluginRuntimes.remove(md5);
            }
            this.m_vPluginRuntimes.put(md5, pluginRuntimeEnv);
        }
    }

    public final PluginRuntimeEnv getPluginRuntimeEnv(PluginInfo pluginInfo) {
        if (pluginInfo == null || StringUtils.isEmpty(pluginInfo.localPath)) {
            return null;
        }
        return this.m_vPluginRuntimes.get(StringUtils.md5(pluginInfo.localPath));
    }

    /* access modifiers changed from: protected */
    public final void initStatService(Application application) {
        StatService.initService(application);
        StatService.setExceptionOn(application, false);
    }

    public final void postClientStateInfo() {
        String encodeBase64;
        String prefUserId = LoginManager.getInstance().getPrefUserId();
        if (prefUserId == null) {
            encodeBase64 = "";
        } else {
            try {
                encodeBase64 = Coder.encodeBase64(AESUtil.encrypt(prefUserId.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        prefUserId = encodeBase64;
        StatService.onPostClientInfo(instance, true, prefUserId, new HashMap(), Device.IMEI);
    }

    public static final boolean isActivityVisible() {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) instance.getSystemService("activity")).getRunningTasks(1);
        ActivityManager.RunningTaskInfo runningTaskInfo = (runningTasks == null || runningTasks.isEmpty()) ? null : runningTasks.get(0);
        if (runningTaskInfo != null) {
            if (Constants.REAL_PACKAGE_NAME.equals(runningTaskInfo.topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static void initIfHaveNot() {
        if (!sIsCookiesInited) {
            sIsCookiesInited = true;
            initCookies();
        }
    }

    private static void initCookies() {
        ThreadPool.execute(new Runnable() {
            public void run() {
                BaseFragment.initSettingCookies(ShopApp.instance);
                BaseFragment.setLoginCookies(ShopApp.instance);
            }
        });
    }

    public final IWXAPI getInstanceWXAPI() {
        if (this.wxAPI == null && !TextUtils.isEmpty(Constants.WX_APP_ID)) {
            this.wxAPI = WXAPIFactory.createWXAPI(instance, Constants.WX_APP_ID, true);
            this.wxAPI.registerApp(Constants.WX_APP_ID);
        }
        return this.wxAPI;
    }

    public final void updateShopingCount() {
        LocalBroadcastManager.getInstance(instance).sendBroadcast(new Intent("Event.ShopCounterUpdateEvent"));
    }

    public final void updateShopingCount(String[] strArr) {
        Intent intent = new Intent("Event.ShopCounterUpdateEvent");
        intent.putExtra("type", strArr);
        LocalBroadcastManager.getInstance(instance).sendBroadcast(intent);
    }
}
