package com.xiaomi.smarthome.framework.login.logic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.CookieManager;
import android.widget.Toast;
import com.xiaomi.miot.store.api.IMiotStoreApi;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.constants.LoginConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.device.MitvDeviceManager;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.config.SHSetting;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.login.util.LoginUtil;
import com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher;
import com.xiaomi.smarthome.framework.page.ActivityStack;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.international.ServerHelper;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.PermssionUtil;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.miio.page.SettingMainPageV2;
import com.xiaomi.smarthome.miio.page.smartlife.SmartLifeItem;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.voice.VoiceManager;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.util.ArrayList;
import java.util.List;

public class LoginManager implements LoginConstants {
    public static volatile long k;
    private static final Object l = new Object();
    private static LoginManager m;
    private List<LoginManagerCallback> n = new ArrayList();
    private Subject<Integer> o = BehaviorSubject.createDefault(3);

    public interface LoginManagerCallback {
        void a();

        void b();
    }

    public static LoginManager a() {
        if (m == null) {
            synchronized (l) {
                if (m == null) {
                    m = new LoginManager();
                }
            }
        }
        return m;
    }

    public void a(LoginManagerCallback loginManagerCallback) {
        if (!this.n.contains(loginManagerCallback)) {
            this.n.add(loginManagerCallback);
        }
    }

    public void b(LoginManagerCallback loginManagerCallback) {
        this.n.remove(loginManagerCallback);
    }

    public Observable<Integer> b() {
        return this.o;
    }

    private void c() {
        int i = 0;
        while (i < this.n.size()) {
            try {
                LoginManagerCallback loginManagerCallback = this.n.get(i);
                if (loginManagerCallback != null) {
                    loginManagerCallback.a();
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.o.onNext(4);
    }

    /* access modifiers changed from: private */
    public void d() {
        int i = 0;
        while (i < this.n.size()) {
            try {
                LoginManagerCallback loginManagerCallback = this.n.get(i);
                if (loginManagerCallback != null) {
                    loginManagerCallback.b();
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.o.onNext(3);
    }

    public static void a(final Context context, final ServerBean serverBean, final AsyncCallback<Void, Error> asyncCallback) {
        a().logout(new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing()) {
                        if (Build.VERSION.SDK_INT >= 17 && ((Activity) context).isDestroyed()) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(voidR);
                }
                LoginManager.c(context, serverBean, new AsyncCallback<Void, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        Uri uri;
                        ActivityStack.instance.doClearOnServerChanged();
                        final Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeLauncher.class);
                        if (LoginUtil.a()) {
                            uri = Uri.parse("https://home.mi.com/main/login_mi_system?account_name=" + LoginUtil.b());
                        } else {
                            uri = Uri.parse("https://home.mi.com/main/login");
                        }
                        intent.setData(uri);
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                context.startActivity(intent);
                            }
                        }, 1000);
                    }
                });
            }

            public void onFailure(Error error) {
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing()) {
                        if (Build.VERSION.SDK_INT < 17 || !((Activity) context).isDestroyed()) {
                            Toast.makeText(context, R.string.server_change_server_failure, 0).show();
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void c(Context context, final ServerBean serverBean, final AsyncCallback<Void, Error> asyncCallback) {
        if (serverBean != null) {
            if (CoreApi.a().l()) {
                CoreApi.a().a(serverBean, asyncCallback);
            } else {
                IntentFilter intentFilter = new IntentFilter(CoreHostApiImpl.e);
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                        CoreApi.a().a(serverBean, (AsyncCallback<Void, Error>) asyncCallback);
                    }
                }, intentFilter);
            }
            LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
            Intent intent = new Intent(ServerHelper.b);
            intent.putExtra("param_key", 1);
            instance.sendBroadcast(intent);
        }
    }

    public void a(int i) {
        k = System.currentTimeMillis();
        e();
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                SHApplication.getPushManager().e();
                SmartLifeItem.b();
            }
        }, 30000);
        MiStoreCookieHelper.a();
        SHApplication.getStateNotifier().c();
        SHSetting.a();
        SHApplication.getAppContext().sendBroadcast(new Intent(WifiScanHomelog.c));
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("action_on_login_success"));
        c();
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.xiaomi.youpin.action.on_login"));
        LoginMiAccount y = CoreApi.a().y();
        if (y != null) {
            SmartNotiApi.a(SHApplication.getAppContext()).a(y.e());
        }
        if (i != 1) {
            SmartHomeDeviceManager.a().s();
            SceneManager.x().A();
            SceneManager.x().a();
            SmartHomeDeviceHelper.a().e();
            CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                public void a(boolean z, boolean z2) {
                    if (z) {
                        SmartHomeDeviceManager.a().r();
                    }
                    SmartHomeDeviceHelper.a().f();
                }

                public void a(PluginError pluginError) {
                    SmartHomeDeviceHelper.a().g();
                }
            });
            CoreApi.a().a(true);
            VoiceManager.a().d();
        }
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                IMiotStoreApi a2;
                if (CommonUtils.r(SHApplication.getAppContext()) && (a2 = MiotStoreApi.a()) != null) {
                    if (CoreApi.a().D()) {
                        a2.clearAccount();
                    } else {
                        a2.updateAccount();
                    }
                }
                StatHelper.g(CoreApi.a().q());
            }
        }, 1000);
        try {
            ShopGlobalHelper.b();
            BBSInitializer.a();
            if (!PermssionUtil.a(SHApplication.getAppContext())) {
                PermssionUtil.a(SHApplication.getAppContext(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e() {
        Context appContext = SHApplication.getAppContext();
        if (PreferenceUtils.b(appContext, ProfileRedPointManager.e + CoreApi.a().s(), -1) == -1) {
            Context appContext2 = SHApplication.getAppContext();
            PreferenceUtils.a(appContext2, ProfileRedPointManager.e + CoreApi.a().s(), (System.currentTimeMillis() + ProfileRedPointManager.a().c()) / 1000);
        }
        Context appContext3 = SHApplication.getAppContext();
        if (PreferenceUtils.b(appContext3, ProfileRedPointManager.d + CoreApi.a().s(), -1) == -1) {
            Context appContext4 = SHApplication.getAppContext();
            PreferenceUtils.a(appContext4, ProfileRedPointManager.d + CoreApi.a().s(), (System.currentTimeMillis() + ProfileRedPointManager.a().c()) / 1000);
        }
    }

    public AsyncHandle a(final AsyncCallback<Void, Error> asyncCallback) {
        LogUtilGrey.a(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "logoutWitoutCleanPluginRecord start");
        return SHApplication.getPushManager().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                LoginApi.a().a(false, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        DevicePushBindManager.instance.clear();
                        MultiHomeDeviceManager.a().b();
                        SmartHomeDeviceManager.a().v();
                        SceneManager.x().y();
                        CoreApi.a().V();
                        SHApplication.getStateNotifier().h();
                        SmartHomeDeviceManager.a().p();
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(WifiScanHomelog.c));
                        CameraFrameManager.a().a(SHApplication.getAppContext());
                        CameraInfoRefreshManager.a().e();
                        SHApplication.getPushManager().f();
                        CameraInfoRefreshManager.a().e();
                        DeviceFinder.a().b();
                        SHSetting.a();
                        SmartNotiApi.a(SHApplication.getAppContext()).a();
                        ShopGlobalHelper.a();
                        BBSInitializer.b();
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("action_on_logout"));
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.xiaomi.youpin.action.on_logout"));
                        PreferenceUtils.b(SettingMainPageV2.b, true);
                        PreferenceUtils.b("my_home_red_dot_clicked", true);
                        CookieManager.getInstance().removeAllCookie();
                        if (MiotStoreApi.a() != null) {
                            MiotStoreApi.a().clearAccount();
                        }
                        MitvDeviceManager.b().e();
                        LiteSceneManager.j().k();
                        MiBrainManager.a().k();
                        RecommendSceneManager.a().l();
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(null);
                        }
                        try {
                            PermssionUtil.a(SHApplication.getAppContext(), false);
                            ThirdAccountBindManager.a().f();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(new Error(-9999, ""));
                        }
                    }
                });
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(-9999, ""));
                }
            }
        });
    }

    public AsyncHandle logout(final AsyncCallback<Void, Error> asyncCallback) {
        LogUtilGrey.a(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "logout start");
        return SHApplication.getPushManager().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                LoginApi.a().a(true, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        DevicePushBindManager.instance.clear();
                        MultiHomeDeviceManager.a().b();
                        SmartHomeDeviceManager.a().v();
                        SceneManager.x().y();
                        CoreApi.a().V();
                        SHApplication.getStateNotifier().h();
                        SmartHomeDeviceManager.a().p();
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(WifiScanHomelog.c));
                        CameraFrameManager.a().a(SHApplication.getAppContext());
                        CameraInfoRefreshManager.a().e();
                        SHApplication.getPushManager().f();
                        CameraInfoRefreshManager.a().e();
                        DeviceFinder.a().b();
                        SHSetting.a();
                        SmartNotiApi.a(SHApplication.getAppContext()).a();
                        ShopGlobalHelper.a();
                        BBSInitializer.b();
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("action_on_logout"));
                        LoginManager.this.d();
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.xiaomi.youpin.action.on_logout"));
                        PreferenceUtils.b(SettingMainPageV2.b, true);
                        PreferenceUtils.b("my_home_red_dot_clicked", true);
                        CookieManager.getInstance().removeAllCookie();
                        if (MiotStoreApi.a() != null) {
                            MiotStoreApi.a().clearAccount();
                        }
                        MitvDeviceManager.b().e();
                        LiteSceneManager.j().k();
                        MiBrainManager.a().k();
                        RecommendSceneManager.a().l();
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(null);
                        }
                        try {
                            PermssionUtil.a(SHApplication.getAppContext(), false);
                            ThirdAccountBindManager.a().f();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(new Error(-9999, ""));
                        }
                    }
                });
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(-9999, ""));
                }
            }
        });
    }

    public void a(String str, final AsyncCallback<MiServiceTokenInfo, Error> asyncCallback) {
        if (!CoreApi.a().q()) {
            asyncCallback.onFailure(new Error(-1, "not login"));
        } else if (CoreApi.a().v()) {
            MiLoginApi.a(str, CoreApi.a().a(str), false, (com.xiaomi.youpin.login.AsyncCallback<com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo, ExceptionError>) new com.xiaomi.youpin.login.AsyncCallback<com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo, ExceptionError>() {
                public void a(com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo miServiceTokenInfo) {
                    CoreApi.a().a(miServiceTokenInfo, (AsyncCallback<Void, Error>) null);
                    asyncCallback.onSuccess(new MiServiceTokenInfo(miServiceTokenInfo.f23514a, miServiceTokenInfo.b, miServiceTokenInfo.c, miServiceTokenInfo.d, miServiceTokenInfo.f, miServiceTokenInfo.e));
                }

                public void a(ExceptionError exceptionError) {
                    asyncCallback.onFailure(new Error(exceptionError.a(), exceptionError.b()));
                }
            });
        } else {
            MiLoginApi.a(str, CoreApi.a().s(), CoreApi.a().w(), false, new com.xiaomi.youpin.login.AsyncCallback<com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo, ExceptionError>() {
                public void a(com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo miServiceTokenInfo) {
                    CoreApi.a().a(miServiceTokenInfo, (AsyncCallback<Void, Error>) null);
                    asyncCallback.onSuccess(new MiServiceTokenInfo(miServiceTokenInfo.f23514a, miServiceTokenInfo.b, miServiceTokenInfo.c, miServiceTokenInfo.d, miServiceTokenInfo.f, miServiceTokenInfo.e));
                }

                public void a(ExceptionError exceptionError) {
                    asyncCallback.onFailure(new Error(exceptionError.a(), exceptionError.b()));
                }
            });
        }
    }
}
