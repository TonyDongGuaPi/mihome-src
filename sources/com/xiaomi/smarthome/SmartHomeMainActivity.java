package com.xiaomi.smarthome;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.android.exoplayer2.C;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mi.global.bbs.manager.MiCommunitySdkManager;
import com.mi.global.shop.ShopApp;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.support.monitor.MiotMonitorClient;
import com.xiaomi.miot.support.monitor.aop.trace.TraceActivity;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.pluginhost.AppInitialApi;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.acp.ACPUtil;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.bbs.SmarthomeBBSFragment;
import com.xiaomi.smarthome.config.AndroidMonitorConfigManager;
import com.xiaomi.smarthome.config.GreyUpgradeConfigManager;
import com.xiaomi.smarthome.config.SHBusinessManager;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.device.ApDeviceManager;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.ChooseConnectDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.device.LocalRouterDeviceInfo;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.MitvDeviceManager;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.VirtualDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleDispatcher;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.download.PluginAutoDownloadTask;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.LoginApiNew;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.bluetooth.BluetoothReceiver;
import com.xiaomi.smarthome.framework.crash.MainCrashHandler;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.log.MyLogHelper;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.login.logic.MiStoreCookieHelper;
import com.xiaomi.smarthome.framework.login.util.LoginUtil;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.framework.network.NetworkManager;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.BaseFragmentInterface;
import com.xiaomi.smarthome.framework.permission.PermissionBean;
import com.xiaomi.smarthome.framework.permission.PermissionRequestActivity;
import com.xiaomi.smarthome.framework.permission.SerialPermissionRequest;
import com.xiaomi.smarthome.framework.push.PushListener;
import com.xiaomi.smarthome.framework.push.PushManager;
import com.xiaomi.smarthome.framework.push.PushType;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import com.xiaomi.smarthome.framework.redpoint.ServerTimerManager;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.update.UpdateItemHelper;
import com.xiaomi.smarthome.framework.update.UpdateManager;
import com.xiaomi.smarthome.globalnavbutton.GlobalNavButtonManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.international.ServerHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MenuDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ImageDownloadManager;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.FragmentPagerAdapter;
import com.xiaomi.smarthome.library.common.widget.IRHintBgView;
import com.xiaomi.smarthome.library.common.widget.IconPagerAdapter;
import com.xiaomi.smarthome.library.common.widget.TabPageIndicator;
import com.xiaomi.smarthome.library.common.widget.TabPageIndicatorNew;
import com.xiaomi.smarthome.library.common.widget.ViewPager;
import com.xiaomi.smarthome.listcamera.AllCameraPage;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.CameraHorizontalActivity;
import com.xiaomi.smarthome.listcamera.CameraSortActivity;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.activity.BleGatewayActivity;
import com.xiaomi.smarthome.miio.activity.BleGatewayListActivity;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.miio.consumables.ConsumableDataManager;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.page.BaseClientAllPage;
import com.xiaomi.smarthome.miio.page.PagerListener;
import com.xiaomi.smarthome.miio.page.SettingMainPageV2;
import com.xiaomi.smarthome.miio.update.AppUpdateManger;
import com.xiaomi.smarthome.miui.DialogTipsActivity;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.monitor.MonitorControllerImpl;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.HomeEnvInfoFragment;
import com.xiaomi.smarthome.newui.HomeEnvInfoSettingFragment;
import com.xiaomi.smarthome.newui.MyScaleAnimation;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.OnStateChangedListener;
import com.xiaomi.smarthome.newui.widget.CommonBlurView;
import com.xiaomi.smarthome.newui.widget.LinearViewPager;
import com.xiaomi.smarthome.newui.widget.ReactiveWallpaper;
import com.xiaomi.smarthome.preload.PreloadLayoutInflater;
import com.xiaomi.smarthome.scene.location.model.SceneConditionWifiManager;
import com.xiaomi.smarthome.scenenew.SceneTabFragment;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.xiaomi.smarthome.service.tasks.LoginTask;
import com.xiaomi.smarthome.shop.fragment.ShopFragment;
import com.xiaomi.smarthome.shop.fragment.ShopNewPointManager;
import com.xiaomi.smarthome.shopglobal.ShopGlobalFragment;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.xiaomi.smarthome.smartconfig.DeviceBindStatis;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.smartconfig.NetworkDetector;
import com.xiaomi.smarthome.smartconfig.stage.ConfigStage;
import com.xiaomi.smarthome.splashads.MiuiSplashActivity;
import com.xiaomi.smarthome.splashads.MiuiSplashAdsManager;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.report.StatLogSender;
import com.xiaomi.smarthome.voice.VoiceManager;
import com.xiaomi.smarthome.wificonfig.WIFIScanHomelogReceiver;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiLogManager;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import com.xiaomi.smarthome.wificonfig.WifiSettingNormal;
import com.xiaomi.stat.MiStat;
import com.xiaomi.voiceassistant.mijava.MiBrainCloudSDKManager;
import com.xiaomi.youpin.MainActivityLifecycleForYP;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.callback.MiuiSystemLoginCallback;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.AccessAccountCallback;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONObject;

public class SmartHomeMainActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback, DefaultHardwareBackBtnHandler, PermissionAwareActivity, ShopNewPointManager.IShowNewPointListener {
    public static final String ACTION_CLOSE_DRAWER = "close_drawer_action";
    public static final String ACTION_OPEN_DRAWER = "open_drawer_action";
    public static final int DEVICE_PAGE_RESULT = 100;
    static boolean ENABLE_KUWAN = true;
    public static final int GET_ACCOUNT = 1;
    public static final int GET_DEVICE_TYPE = 3;
    public static final String INTENT_KEY_REQUEST_CODE = "com.xiaomi.smarthome.request_code";
    public static final String INTENT_KEY_REQUEST_CODE_NEW = "req_code";
    public static final String PARAM_NEXT_ACTION = "next_action_param";
    public static final String PREF_CLIENT_MODE = "pref_client_mode";
    public static final int REQUEST_CODE_GO_TO_ADD_DEVICE = 2;
    public static final int REQUEST_CODE_GO_TO_BLE_GATEWAY = 9;
    public static final int REQUEST_CODE_GO_TO_DEVICE_LIST_PAGE = 1;
    public static final int REQUEST_CODE_GO_TO_LOGIN_MAIN = 7;
    public static final int REQUEST_CODE_GO_TO_MESSAGE_CENTER = 5;
    public static final int REQUEST_CODE_GO_TO_MY_ORDER = 4;
    public static final int REQUEST_CODE_GO_TO_SCENE_LOG_FRAGMENT = 8;
    public static final int REQUEST_CODE_GO_TO_SCENE_MAIN = 3;
    public static final int REQUEST_CODE_GO_TO_SHOP_MAIN = 6;
    public static final int REQUEST_CODE_UNKNOWN_PUSH = 10;
    public static final int REQUEST_PIN_CODE = 4;
    public static final int SETTING_PAGE_RESULT = 102;
    public static final int SHOP_PAGE_RESULT = 101;
    public static final int SHOW_ADD_DEVICE_TIPS = 5;
    public static final int SHOW_CARD = 6;
    public static final int START_KUAILIAN = 2;
    public static final String TAG = "SmartHomeMainActivity";
    private static final JoinPoint.StaticPart U = null;
    public static boolean USE_LITE_CLIENTPAGE = false;
    public static boolean USE_LITE_CLIENTPAGE_NEW = true;
    private static final int b = 291;
    /* access modifiers changed from: private */
    public static Integer[] c;
    /* access modifiers changed from: private */
    public static Integer[] d;
    /* access modifiers changed from: private */
    public static String[] e;
    /* access modifiers changed from: private */
    public static String[] f;
    public static BleDevice mChooseBleDevice;
    public static boolean mIsActivityResumed = false;
    public static ArrayList<ScanResult> mScanResult;
    private int A = 0;
    private CommonBlurView B;
    /* access modifiers changed from: private */
    public Map<Runnable, Object> C = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Runnable, Object> D = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public SmartHomeMainActivityLifecycle E;
    private SerialPermissionRequest F = new SerialPermissionRequest();
    private PushListener G = new PushListener() {
        public boolean a(String str, String str2) {
            SmartHomeDeviceManager.a().p();
            return true;
        }

        public boolean b(String str, String str2) {
            SmartHomeDeviceManager.a().p();
            return true;
        }
    };
    private boolean H = false;
    private AndroidMonitorConfigManager.ICallBack I;
    private Runnable J = new Runnable() {
        public void run() {
            if (SmartHomeMainActivity.this.isValid()) {
                ImageDownloadManager.a().b();
                ACPUtil.d(SmartHomeMainActivity.this);
                XmPluginHostApi instance = XmPluginHostApi.instance();
                if (instance != null && !instance.isAccountLogined()) {
                    MiLoginApi.a(SHApplication.getAppContext());
                }
            }
        }
    };
    private Runnable K = new Runnable() {
        public void run() {
            if (SmartHomeMainActivity.this.isValid()) {
                StatLogSender.a();
            }
        }
    };
    private Runnable L = new Runnable() {
        public void run() {
            if (SmartHomeMainActivity.this.isValid()) {
                SHLocationManager.a().a((SHLocationManager.LocationCallback) new LocationCallback(SmartHomeMainActivity.this));
            }
        }
    };
    private BroadcastReceiver M = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.hashCode() == -1323026591) {
                boolean equals = action.equals(ControlCardInfoManager.d);
            }
        }
    };
    /* access modifiers changed from: private */
    public HomeRoomReceiver N;
    /* access modifiers changed from: private */
    public XQProgressDialog O;
    private View P;
    private View Q;
    private int R = -1;
    private boolean S = true;
    /* access modifiers changed from: private */
    public List<CoreApi.IsCoreReadyCallback> T = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Dialog f1499a = null;
    int currentOnResumeTime;
    private boolean g = false;
    private boolean h = false;
    private View i;
    private ReactiveWallpaper j;
    private Device k;
    private LayoutInflater l;
    int lastOnResumeTime;
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            SmartHomeDeviceManager.a().c(SmartHomeMainActivity.this.listener);
            SmartHomeMainActivity.this.openDevice(SmartHomeMainActivity.this.getIntent(), true);
        }

        public void b(int i) {
            SmartHomeDeviceManager.a().c(SmartHomeMainActivity.this.listener);
            SmartHomeMainActivity.this.openDevice(SmartHomeMainActivity.this.getIntent(), true);
        }
    };
    private OnStateChangedListener m = new OnStateChangedListener() {
        public void onStateChanged(String str, String str2, Object obj) {
            if (DevicePropSubscriber.f15537a.equals(str2)) {
                Device b = SmartHomeDeviceManager.a().b(str);
                if (b != null) {
                    b.isOnline = true;
                }
            } else if (DevicePropSubscriber.b.equals(str2)) {
                Device b2 = SmartHomeDeviceManager.a().b(str);
                if (b2 != null) {
                    b2.isOnline = false;
                }
            } else {
                LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view").putExtra("param_did", str));
            }
        }
    };
    public HashSet<String> mCachedScanResult = new HashSet<>();
    SharedPreferences mClientPageModePreference;
    public volatile boolean mIsProcessAccount = true;
    NetworkManager.NetworkListener mNetworkListener = new NetworkManager.NetworkListener() {
        public void b() {
        }

        public void a() {
            SmartHomeMainActivity.this.a();
        }
    };
    Intent mPendingIntent;
    boolean mShopRedShow = false;
    /* access modifiers changed from: private */
    public UpdateCallback n = new UpdateCallback();
    private String o;
    /* access modifiers changed from: private */
    public TabPageIndicatorNew p;
    /* access modifiers changed from: private */
    public Context q;
    private CardStatus r = CardStatus.CARD_HIDE;
    private View s;
    private View t;
    private View u;
    private View v;
    /* access modifiers changed from: private */
    public LinearViewPager w;
    private MainAdapter x;
    private ScanResult y;
    private boolean z = false;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            SmartHomeMainActivity.onCreate_aroundBody0((SmartHomeMainActivity) objArr2[0], (Bundle) objArr2[1], (JoinPoint) objArr2[2]);
            return null;
        }
    }

    private enum CardStatus {
        CARD_HIDE,
        CARD_ANIMATION,
        CARD_SHOWING
    }

    private static void J() {
        Factory factory = new Factory("SmartHomeMainActivity.java", SmartHomeMainActivity.class);
        U = factory.a("method-execution", (Signature) factory.a("1", "onCreate", "com.xiaomi.smarthome.SmartHomeMainActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 687);
    }

    private void b(Context context) {
    }

    static {
        J();
    }

    public LocalActivityManager getLocalActivityManager() {
        if (this.E == null) {
            return null;
        }
        return this.E.b();
    }

    private static class MyIsDeviceReadyCallback implements SmartHomeDeviceManager.IsDeviceReadyCallback {
        private MyIsDeviceReadyCallback() {
        }

        public void onDeviceReady(List<Device> list) {
            VirtualDeviceManager.a();
        }
    }

    public void invokeDefaultOnBackPressed() {
        onBackPressed();
    }

    public void onNewPoint(final boolean z2) {
        this.mHandler.post(new Runnable() {
            public void run() {
                SmartHomeMainActivity.this.enableShopNewPoint(z2);
            }
        });
    }

    static class UpdateCallback implements CoreApi.UpdateConfigCallback {
        public void a(PluginError pluginError) {
        }

        UpdateCallback() {
        }

        public void a(boolean z, boolean z2) {
            if (z) {
                SmartHomeDeviceManager.a().r();
            }
        }
    }

    public void changeListClientPageMode(boolean z2, boolean z3) {
        if (z3) {
            if (z2 && this.h) {
                return;
            }
            if (!z2 && !this.h) {
                return;
            }
        }
        this.h = z2;
        USE_LITE_CLIENTPAGE = z2;
        this.mClientPageModePreference.edit().putBoolean(PREF_CLIENT_MODE, z2).apply();
        SmartHomeDeviceHelper.a().a(z2);
        if (z3) {
            MiBrainManager.a().c();
            VoiceManager.a().d();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(SmartHomeMainActivity.this, SmartHomeMainActivity.class);
                    intent.putExtra("change_model", true);
                    intent.addFlags(335544320);
                    intent.putExtra("source", 12);
                    SmartHomeMainActivity.this.startActivity(intent);
                    SmartHomeMainActivity.this.overridePendingTransition(0, 0);
                }
            }, 100);
        }
    }

    /* access modifiers changed from: package-private */
    public void initMiuiTextTypeface() {
        try {
            Class<?> cls = Class.forName("miui.util.TypefaceUtils");
            Field declaredField = cls.getDeclaredField("mFontsWhiteList");
            declaredField.setAccessible(true);
            HashSet hashSet = (HashSet) declaredField.get(cls);
            if (hashSet != null) {
                hashSet.add("com.xiaomi.smarthome");
            }
        } catch (ClassNotFoundException | Exception | IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    public void newDeviceWithStartConnect(List<ScanResult> list) {
        if (list.size() == 1) {
            this.y = list.get(0);
            gotoWifiSettingPage();
            return;
        }
        gotoConnectionSelectPage();
    }

    /* access modifiers changed from: private */
    public void a() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment next : fragments) {
                if (next instanceof BaseClientAllPage) {
                    ((BaseClientAllPage) next).i();
                } else if (next instanceof DeviceMainPage) {
                    ((DeviceMainPage) next).n();
                }
            }
        }
    }

    public void updateTabviewShopDot(boolean z2) {
        if (this.p != null) {
            this.p.updateTabviewShopDot(z2);
        }
        if (z2) {
            if (!this.mShopRedShow && this.E != null) {
                this.E.a(z2);
            }
            CoreApi.a().a(StatType.YOUPIN, "Show_Red_Point_Success", "YouPin_MainPage_From_MiHome", (String) null, false);
        } else if (this.mShopRedShow) {
            XmPluginHostApi.instance().addTouchRecord("YOUPINREDPOINTDISAPPEAR", "");
        }
    }

    /* access modifiers changed from: package-private */
    public void enableShopNewPoint(boolean z2) {
        if (z2) {
            if (this.w != null) {
                int currentItem = this.w.getCurrentItem();
                if (currentItem >= 0) {
                    BaseFragmentInterface fragment = getFragment(currentItem);
                    if (fragment != null) {
                        if (fragment instanceof ShopFragment) {
                            ShopNewPointManager.a().e();
                            updateTabviewShopDot(false);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else {
                return;
            }
        }
        updateTabviewShopDot(z2);
    }

    private void b() {
        if (this.B == null) {
            this.B = (CommonBlurView) ((ViewStub) this.s.findViewById(R.id.blur_view_vs)).inflate();
        }
        this.B.setBlurView(this.s.findViewById(R.id.layout_layer1), true);
    }

    private void c() {
        if (this.B != null) {
            this.B.setBlurViewGone();
        }
    }

    private void d() {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && intent.hasCategory("android.intent.category.LAUNCHER") && action.equals("android.intent.action.MAIN")) {
            STAT.f22748a.a();
        }
    }

    private static class MainCheckListCallback implements StartupCheckList.CheckListCallback {
        public void a() {
        }

        public void b() {
        }

        public void c() {
        }

        public void d() {
        }

        private MainCheckListCallback() {
        }

        public void e() {
            Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeMainActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            SHApplication.getAppContext().startActivity(intent);
        }
    }

    static final void onCreate_aroundBody0(SmartHomeMainActivity smartHomeMainActivity, Bundle bundle, JoinPoint joinPoint) {
        super.onCreate((Bundle) null);
        smartHomeMainActivity.getWindow().addFlags(2048);
        if (SHApplication.getAppContext() == null) {
            smartHomeMainActivity.finish();
            return;
        }
        if (!SHApplication.isCurrentHotStart()) {
            smartHomeMainActivity.setDelayFrescoInit();
        }
        smartHomeMainActivity.d();
        try {
            FirebaseAnalytics.getInstance(smartHomeMainActivity.getApplicationContext()).setAnalyticsCollectionEnabled(false);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        smartHomeMainActivity.getPackageManager().setComponentEnabledSetting(new ComponentName("com.xiaomi.smarthome", "com.xiaomi.smarthome.service.DeviceObserveService"), 1, 1);
        smartHomeMainActivity.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (SmartHomeMainActivity.this.isValid()) {
                    try {
                        if (System.currentTimeMillis() - SHConfig.a().b("last_detect") > 39600000) {
                            SHConfig.a().a("last_detect", System.currentTimeMillis());
                            NetworkDetector.a().b();
                            NetworkDetector.a().a("time");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 5000);
        if (!SHApplication.isApplicationStart()) {
            if (!StartupCheckList.b()) {
                StartupCheckList.a((StartupCheckList.CheckListCallback) new MainCheckListCallback());
                smartHomeMainActivity.H = true;
                smartHomeMainActivity.finish();
                return;
            }
            SHApplication.getApplication().onApplicationLifeCycleStart();
        }
        SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new MyIsDeviceReadyCallback());
        smartHomeMainActivity.mHandler.postDelayed(new Runnable() {
            public void run() {
                MyLog.d("timediff " + ServerTimerManager.a(SmartHomeMainActivity.this.q.getApplicationContext()).c());
            }
        }, 5000);
        smartHomeMainActivity.k();
        CoreApi.a().a((Context) smartHomeMainActivity, (CoreApi.IsAccountReadyCallback) new CoreApi.IsAccountReadyCallback() {
            public void a(boolean z, final String str) {
                if (!z) {
                    SmartHomeMainActivity.this.l();
                } else {
                    AnonymousClass1 r3 = new Runnable() {
                        public void run() {
                            if (SmartHomeMainActivity.this.isValid()) {
                                SmartHomeMainActivity.this.C.remove(this);
                                if (!CoreApi.a().v()) {
                                    return;
                                }
                                if (Build.VERSION.SDK_INT >= 26) {
                                    AccountManagerUtil.a(SHApplication.getAppContext(), false, new AccessAccountCallback() {
                                        public void a(Account account) {
                                            String b = LoginUtil.b();
                                            if (TextUtils.isEmpty(b) || (!TextUtils.isEmpty(b) && !b.equalsIgnoreCase(str))) {
                                                ToastUtil.a((int) R.string.system_account_invalid, 1);
                                                LoginManager.a().logout((AsyncCallback<Void, Error>) null);
                                                return;
                                            }
                                            SmartHomeMainActivity.this.n();
                                        }

                                        public void a(int i, String str) {
                                            LogUtilGrey.a("login", "AccountManagerUtil.canAccessAccount on fail " + i + "," + str);
                                        }
                                    });
                                    return;
                                }
                                String b2 = LoginUtil.b();
                                if (TextUtils.isEmpty(b2) || (!TextUtils.isEmpty(b2) && !b2.equalsIgnoreCase(str))) {
                                    ToastUtil.a((int) R.string.system_account_invalid, 1);
                                    LoginManager.a().logout((AsyncCallback<Void, Error>) null);
                                    return;
                                }
                                SmartHomeMainActivity.this.n();
                            }
                        }
                    };
                    SmartHomeMainActivity.this.C.put(r3, r3);
                    SHApplication.getGlobalHandler().postDelayed(r3, 1000);
                    SmartHomeMainActivity.this.mIsProcessAccount = false;
                }
                if (!CoreApi.a().D()) {
                    SHApplication.getThreadExecutor().submit(new Runnable() {
                        public void run() {
                            MiLoginApi.a(SHApplication.getAppContext());
                        }
                    });
                }
            }
        });
        AnonymousClass10 r1 = new Runnable() {
            public void run() {
                if (SmartHomeMainActivity.this.isValid()) {
                    AnonymousClass1 r0 = new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            LogUtilGrey.a("SmartHomeMainActivity", "SHApplication.sOnCreateTime: " + SHApplication.sOnCreateTime);
                            if (SmartHomeMainActivity.this.isValid()) {
                                SHApplication.getThreadExecutor().submit(new Runnable() {
                                    public void run() {
                                        if (SmartHomeMainActivity.this.isValid() && !CoreApi.a().D() && MiotStoreApi.a() != null) {
                                            MiotStoreApi.a().updateJSBundler();
                                        }
                                    }
                                });
                            }
                        }
                    };
                    SmartHomeMainActivity.this.T.add(r0);
                    CoreApi.a().a((Context) SmartHomeMainActivity.this, (CoreApi.IsCoreReadyCallback) r0);
                    if (SmartHomeMainActivity.this.E != null) {
                        SmartHomeMainActivity.this.E.a(1000);
                    }
                }
            }
        };
        if (SHApplication.isCurrentHotStart()) {
            r1.run();
        } else {
            new Handler().postDelayed(r1, 1000);
        }
        smartHomeMainActivity.q = smartHomeMainActivity;
        mScanResult = WifiDeviceFinder.j;
        try {
            smartHomeMainActivity.setContentView(R.layout.smarthome_main_v2);
        } catch (Exception e3) {
            String message = e3.getMessage();
            if (TextUtils.isEmpty(message) || !message.contains("SimpleDraweeView")) {
                throw e3;
            }
            FrescoInitial.a(false);
            smartHomeMainActivity.setContentView(R.layout.smarthome_main_v2);
        }
        smartHomeMainActivity.getWindow().setBackgroundDrawable((Drawable) null);
        smartHomeMainActivity.initMiuiTextTypeface();
        smartHomeMainActivity.x = new MainAdapter(smartHomeMainActivity.getSupportFragmentManager());
        smartHomeMainActivity.w = (LinearViewPager) smartHomeMainActivity.findViewById(R.id.pager);
        if (smartHomeMainActivity.w != null) {
            smartHomeMainActivity.w.setAdapter(smartHomeMainActivity.x);
        }
        smartHomeMainActivity.s = smartHomeMainActivity.findViewById(R.id.drawer_layout);
        smartHomeMainActivity.p = (TabPageIndicatorNew) smartHomeMainActivity.findViewById(R.id.indicator);
        smartHomeMainActivity.j = (ReactiveWallpaper) smartHomeMainActivity.findViewById(R.id.react_wallpaper);
        if (smartHomeMainActivity.p != null) {
            smartHomeMainActivity.p.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                    if (i == 0) {
                    }
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    SmartHomeMainActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            List<Fragment> fragments = SmartHomeMainActivity.this.getSupportFragmentManager().getFragments();
                            if (fragments != null && SmartHomeMainActivity.this.w != null) {
                                int currentItem = SmartHomeMainActivity.this.w.getCurrentItem();
                                int lastTabIndex = SmartHomeMainActivity.this.p.getLastTabIndex();
                                SmartHomeMainActivity.this.lastOnResumeTime = SmartHomeMainActivity.this.currentOnResumeTime;
                                SmartHomeMainActivity.this.currentOnResumeTime = (int) (System.currentTimeMillis() / 1000);
                                SmartHomeMainActivity.this.onPageChanged(lastTabIndex, currentItem);
                                if (currentItem >= 0 && currentItem < fragments.size() && (fragments.get(currentItem) instanceof SmarthomeBBSFragment)) {
                                    MiCommunitySdkManager.getInstance().onBBSFragmentChecked();
                                }
                            }
                        }
                    });
                }
            });
            smartHomeMainActivity.p.setOnClickInterceptListener(new TabPageIndicatorNew.OnClickInterceptListener() {
                public final boolean onIntercept(TabPageIndicatorNew.TabView tabView) {
                    return SmartHomeMainActivity.this.a(tabView);
                }
            });
        }
        smartHomeMainActivity.mHandler.postDelayed(new Runnable() {
            public final void run() {
                SmartHomeMainActivity.this.I();
            }
        }, 1000);
        smartHomeMainActivity.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!HomeManager.A()) {
                    AnonymousClass1 r0 = new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            Log.e("AppStateNotifier", "" + CoreApi.a().q());
                            if (CoreApi.a().q()) {
                                if (CoreApi.a().a("miotstore") == null) {
                                    LoginApiNew.a().a("miotstore", (com.xiaomi.youpin.login.AsyncCallback<MiServiceTokenInfo, ExceptionError>) new com.xiaomi.youpin.login.AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                                        public void a(ExceptionError exceptionError) {
                                        }

                                        public void a(MiServiceTokenInfo miServiceTokenInfo) {
                                            try {
                                                MiStoreCookieHelper.a(CoreApi.a().s(), URLEncoder.encode(miServiceTokenInfo.c, "UTF-8"), "shopapi.io.mi.com");
                                            } catch (UnsupportedEncodingException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                                if (CoreApi.a().a("mi_eshopm_go") == null) {
                                    LoginApiNew.a().a("mi_eshopm_go", (com.xiaomi.youpin.login.AsyncCallback<MiServiceTokenInfo, ExceptionError>) new com.xiaomi.youpin.login.AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                                        public void a(ExceptionError exceptionError) {
                                        }

                                        public void a(MiServiceTokenInfo miServiceTokenInfo) {
                                            try {
                                                MiStoreCookieHelper.a(CoreApi.a().s(), URLEncoder.encode(miServiceTokenInfo.c, "UTF-8"), "m.mi.com");
                                            } catch (UnsupportedEncodingException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                                if (CoreApi.a().a("mi_huodong") == null) {
                                    LoginApiNew.a().a("mi_huodong", (com.xiaomi.youpin.login.AsyncCallback<MiServiceTokenInfo, ExceptionError>) new com.xiaomi.youpin.login.AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                                        public void a(ExceptionError exceptionError) {
                                        }

                                        public void a(MiServiceTokenInfo miServiceTokenInfo) {
                                            try {
                                                MiStoreCookieHelper.a(CoreApi.a().s(), URLEncoder.encode(miServiceTokenInfo.c, "UTF-8"), ".huodong.mi.com");
                                            } catch (UnsupportedEncodingException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    };
                    SmartHomeMainActivity.this.T.add(r0);
                    CoreApi.a().a(SmartHomeMainActivity.this.q, (CoreApi.IsCoreReadyCallback) r0);
                }
            }
        }, 2000);
        new IntentFilter(HomeManager.S).addAction(SHBusinessManager.f13942a);
        smartHomeMainActivity.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (SmartHomeMainActivity.this.isValid()) {
                    SceneConditionWifiManager.a();
                    SmartHomeMainActivity.this.a((Context) SmartHomeMainActivity.this);
                    AnonymousClass1 r0 = new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            CoreApi.a().P();
                            PluginRuntimeManager.getInstance().initOneProgress();
                            LocalBroadcastManager.getInstance(SmartHomeMainActivity.this.q).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                        }
                    };
                    SmartHomeMainActivity.this.T.add(r0);
                    CoreApi.a().a(SmartHomeMainActivity.this.q, (CoreApi.IsCoreReadyCallback) r0);
                    GreyUpgradeConfigManager.a().b();
                }
            }
        }, 3000);
        SHApplication.getGlobalWorkerHandler().postDelayed(smartHomeMainActivity.L, 10000);
        SHApplication.getGlobalWorkerHandler().postDelayed(smartHomeMainActivity.J, 3000);
        SHApplication.getGlobalWorkerHandler().postDelayed(smartHomeMainActivity.K, 0);
        AnonymousClass14 r9 = new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                CoreApi.a().a(false, (CoreApi.UpdateConfigCallback) SmartHomeMainActivity.this.n);
                SmartHomeMainActivity.this.a(SmartHomeMainActivity.this.getIntent());
                CoreApi.a().Z();
                SmartHomeMainActivity.this.f();
            }
        };
        smartHomeMainActivity.T.add(r9);
        CoreApi.a().a(smartHomeMainActivity.getApplicationContext(), (CoreApi.IsCoreReadyCallback) r9);
        smartHomeMainActivity.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (SmartHomeMainActivity.this.E != null) {
                    SmartHomeMainActivity.this.E.a(2000);
                }
            }
        }, 2000);
        AnonymousClass16 r92 = new Runnable() {
            public void run() {
                if (SmartHomeMainActivity.this.isValid()) {
                    SmartHomeMainActivity.this.C.remove(this);
                    SmartHomeMainActivity.this.i();
                }
            }
        };
        smartHomeMainActivity.C.put(r92, r92);
        SHApplication.getGlobalHandler().postDelayed(r92, 2000);
        smartHomeMainActivity.E = new SmartHomeMainActivityLifecycle();
        smartHomeMainActivity.E.a(new MainActivityLifecycleForYP());
        smartHomeMainActivity.getLifecycle().a(smartHomeMainActivity.E);
        if (smartHomeMainActivity.E != null) {
            smartHomeMainActivity.E.a(smartHomeMainActivity, bundle);
        }
        LogUtilGrey.a("SmartHomeMainActivity", "MainActivity 63000 application start time:" + SHApplication.getOnCreateTime());
        smartHomeMainActivity.h();
        smartHomeMainActivity.e();
    }

    public void onCreate(Bundle bundle) {
        JoinPoint a2 = Factory.a(U, (Object) this, (Object) this, (Object) bundle);
        TraceActivity.b().a(new AjcClosure1(new Object[]{this, bundle, a2}).linkClosureAndJoinPoint(69648));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(TabPageIndicatorNew.TabView tabView) {
        int index = tabView.getIndex();
        if (index >= 0 && index < f.length) {
            if (TextUtils.equals(ShopGlobalFragment.class.getSimpleName(), f[index])) {
                Context appContext = SHApplication.getAppContext();
                if (SharePrefsManager.b(appContext, GlobalDynamicSettingManager.b, "is_show_global_shop_disclaim_" + ServerCompact.a(SHApplication.getAppContext()).b, true)) {
                    ShopApp.a(false, ShopGlobalHelper.f1572a.get(ServerCompact.a((Context) this).b));
                    a(tabView, true);
                    return true;
                }
                ShopApp.a(true, ShopGlobalHelper.f1572a.get(ServerCompact.a((Context) this).b));
                if (!CommonUtils.e((Context) this, "android.permission.READ_PHONE_STATE")) {
                    ShopApp.j = true;
                }
            }
            if (TextUtils.equals(SmarthomeBBSFragment.class.getSimpleName(), f[index])) {
                Context appContext2 = SHApplication.getAppContext();
                if (SharePrefsManager.b(appContext2, GlobalDynamicSettingManager.b, "is_show_global_bbs_disclaim_" + ServerCompact.a(SHApplication.getAppContext()).b, true)) {
                    a(tabView, false);
                    return true;
                }
            }
            if (!TextUtils.equals(ShopFragment.class.getSimpleName(), f[index]) || (AppInitialApi.a() != null && AppInitialApi.a().c() && SHApplication.sShopInitiled)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void I() {
        PushManager.a().c();
        WifiLogManager.a().a(false);
        new IntentFilter(ACTION_CLOSE_DRAWER).addAction(ACTION_OPEN_DRAWER);
        NetworkManager.a().a(this.mNetworkListener);
    }

    private void e() {
        SHApplication.getStateNotifier().a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
            public void b() {
            }

            public void a() {
                SHApplication.getThreadExecutor().submit($$Lambda$SmartHomeMainActivity$17$MeUQn0veLickPX1vdDuZUD2cyt8.INSTANCE);
            }
        });
    }

    public Object getSystemService(String str) {
        if (!str.equals("layout_inflater")) {
            return super.getSystemService(str);
        }
        if (this.l == null) {
            this.l = new PreloadLayoutInflater(getContext());
        }
        return this.l;
    }

    private void a(TabPageIndicatorNew.TabView tabView, boolean z2) {
        String str;
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        if (z2) {
            str = getString(R.string.device_shop_global);
        } else {
            str = getString(R.string.bbs);
        }
        String string = getString(R.string.shop_global_privacy_msg, new Object[]{str});
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass18 r1 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Locale c = ServerCompact.c((Context) SmartHomeMainActivity.this);
                if (LanguageUtil.a(Locale.US, c) || LanguageUtil.a(LanguageUtil.G, c)) {
                    if (ServerCompact.j((Context) SmartHomeMainActivity.this)) {
                        c = new Locale("en", ServerCompact.c);
                    } else if (ServerCompact.k((Context) SmartHomeMainActivity.this)) {
                        c = LanguageUtil.G;
                    }
                }
                intent.setData(Uri.parse("https://privacy.mi.com/all/" + c));
                SmartHomeMainActivity.this.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#FF527ACC"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r1, indexOf, indexOf2, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        builder.a((int) R.string.dialog_privacy_title).a(spannableStringBuilder).a((int) R.string.license_positive_btn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(z2, tabView) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ TabPageIndicatorNew.TabView f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SmartHomeMainActivity.this.a(this.f$1, this.f$2, dialogInterface, i);
            }
        }).b((int) R.string.license_negative_btn, (DialogInterface.OnClickListener) $$Lambda$SmartHomeMainActivity$rNzLB0ZtEVBr6TagFi2Fh7PgyZY.INSTANCE);
        MLAlertDialog b2 = builder.b();
        b2.setCancelable(false);
        b2.show();
        if (z2) {
            STAT.e.g();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(boolean z2, TabPageIndicatorNew.TabView tabView, DialogInterface dialogInterface, int i2) {
        if (z2) {
            Context appContext = SHApplication.getAppContext();
            SharePrefsManager.a(appContext, GlobalDynamicSettingManager.b, "is_show_global_shop_disclaim_" + ServerCompact.a(SHApplication.getAppContext()).b, false);
            STAT.d.bi();
        } else {
            Context appContext2 = SHApplication.getAppContext();
            SharePrefsManager.a(appContext2, GlobalDynamicSettingManager.b, "is_show_global_bbs_disclaim_" + ServerCompact.a(SHApplication.getAppContext()).b, false);
        }
        if (!CommonUtils.e((Context) this, "android.permission.READ_PHONE_STATE")) {
            ShopApp.j = true;
        }
        tabView.performClick();
        dialogInterface.dismiss();
    }

    /* access modifiers changed from: private */
    public void f() {
        if (!this.H && !isFinishing()) {
            if (this.I == null) {
                this.I = new AndroidMonitorConfigManager.ICallBack() {
                    public void a() {
                        MonitorControllerImpl.d().c();
                    }
                };
            }
            AndroidMonitorConfigManager.a().a(this.I);
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList.add(Integer.valueOf(R.string.client_manage));
        arrayList2.add(Integer.valueOf(R.drawable.common_icon_device));
        arrayList3.add("red_point_device_page");
        arrayList4.add(DeviceMainPage.class.getSimpleName());
        if (ServerCompact.e(this.q)) {
            if (ShopGlobalHelper.a(this.q)) {
                arrayList.add(Integer.valueOf(R.string.device_shop_global));
                arrayList2.add(Integer.valueOf(R.drawable.common_icon_shop));
                arrayList3.add("red_point_shop_page");
                arrayList4.add(ShopGlobalFragment.class.getSimpleName());
            }
            if (BBSInitializer.a(this.q)) {
                arrayList.add(Integer.valueOf(R.string.bbs));
                arrayList2.add(Integer.valueOf(R.drawable.common_icon_community));
                arrayList3.add(RedPointManagerNew.c);
                arrayList4.add(SmarthomeBBSFragment.class.getSimpleName());
            }
        } else {
            arrayList.add(Integer.valueOf(R.string.device_shop));
            arrayList2.add(Integer.valueOf(R.drawable.common_icon_shop));
            arrayList3.add("red_point_shop_page");
            arrayList4.add(ShopFragment.class.getSimpleName());
        }
        if (ENABLE_KUWAN) {
            arrayList.add(Integer.valueOf(R.string.app_kuwan));
            arrayList2.add(Integer.valueOf(R.drawable.common_icon_kuwan));
            arrayList3.add("red_point_shop_page");
            arrayList4.add(SceneTabFragment.class.getSimpleName());
        }
        arrayList.add(Integer.valueOf(R.string.my_home));
        arrayList2.add(Integer.valueOf(R.drawable.common_icon_user));
        arrayList3.add("red_point_setting_page");
        arrayList4.add(SettingMainPageV2.class.getSimpleName());
        f = (String[]) arrayList4.toArray(new String[0]);
        c = (Integer[]) arrayList.toArray(new Integer[0]);
        d = (Integer[]) arrayList2.toArray(new Integer[0]);
        e = (String[]) arrayList3.toArray(new String[0]);
    }

    private void h() {
        try {
            ImagePipelineFactory.getInstance();
        } catch (NullPointerException unused) {
            try {
                FrescoInitial.a(true);
            } catch (Exception e2) {
                e2.printStackTrace();
                MainCrashHandler.a((Throwable) e2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (getIntent() != null) {
            if (getIntent().getBooleanExtra("change_model", false) && this.p != null) {
                ArrayList<TabPageIndicatorNew.TabView> tabViewList = this.p.getTabViewList();
                if (!RedPointManagerNew.a().b().containsKey("red_point_setting_page") && tabViewList != null) {
                    for (int i2 = 0; i2 < tabViewList.size(); i2++) {
                        LogUtil.a(OpenApi.e, "initTabViewTags" + tabViewList.get(i2).mViewTag);
                        RedPointManagerNew.a().a(tabViewList.get(i2).mViewTag, (RedPointManagerNew.RedPointAction) tabViewList.get(i2));
                    }
                }
            }
        }
    }

    private void j() {
        ControlCardInfoManager.f().i();
        MiotSpecCardManager.f().g();
        ControlCardInfoManager.f().a(this.m);
        MiotSpecCardManager.f().a(this.m);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ControlCardInfoManager.d);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.M, intentFilter);
    }

    private class HomeRoomReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public String f13525a;

        private HomeRoomReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (HomeManager.a().j(this.f13525a) == null) {
                ToastUtil.a((int) R.string.action_fail);
                if (SmartHomeMainActivity.this.O != null) {
                    SmartHomeMainActivity.this.O.dismiss();
                }
            } else {
                HomeManager.a().a(this.f13525a, (AsyncCallback) new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        if (SmartHomeMainActivity.this.O != null && SmartHomeMainActivity.this.isValid()) {
                            SmartHomeMainActivity.this.O.dismiss();
                        }
                    }

                    public void onFailure(Error error) {
                        if (SmartHomeMainActivity.this.O != null && SmartHomeMainActivity.this.isValid()) {
                            SmartHomeMainActivity.this.O.dismiss();
                        }
                        ToastUtil.a((int) R.string.action_fail);
                    }
                });
            }
            if (SmartHomeMainActivity.this.N != null) {
                LocalBroadcastManager.getInstance(SmartHomeMainActivity.this).unregisterReceiver(SmartHomeMainActivity.this.N);
                HomeRoomReceiver unused = SmartHomeMainActivity.this.N = null;
            }
        }
    }

    public boolean isProcessAccount() {
        return this.mIsProcessAccount;
    }

    public void showAddView(boolean z2, boolean z3) {
        if (CoreApi.a().D()) {
            start();
        } else if (z3) {
            showCameraAddOption(z2);
        } else {
            showNormalAddOption(z2);
        }
    }

    /* access modifiers changed from: package-private */
    public void showNormalAddOption(final boolean z2) {
        final LayoutInflater from = LayoutInflater.from(this);
        final MenuDialog menuDialog = new MenuDialog(this);
        this.f1499a = menuDialog;
        menuDialog.a((BaseAdapter) new BaseAdapter() {
            public int getCount() {
                return 2;
            }

            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                switch (i) {
                    case 0:
                        View inflate = from.inflate(R.layout.menu_dialog_item_with_red_point, (ViewGroup) null);
                        ((TextView) inflate.findViewById(R.id.text1)).setText(R.string.add_device);
                        if (z2) {
                            inflate.findViewById(R.id.new_message_tag).setVisibility(0);
                        }
                        inflate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                    return;
                                }
                                SmartHomeMainActivity.this.start();
                                menuDialog.dismiss();
                                StatHelper.ac();
                            }
                        });
                        return inflate;
                    case 1:
                        View inflate2 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate2.findViewById(R.id.text1)).setText(R.string.add_scene);
                        inflate2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                    return;
                                }
                                menuDialog.dismiss();
                                StatHelper.ad();
                            }
                        });
                        return inflate2;
                    default:
                        return view;
                }
            }
        });
        menuDialog.setCanceledOnTouchOutside(true);
        menuDialog.show();
        StatHelper.k();
        StatHelper.ab();
    }

    /* access modifiers changed from: package-private */
    public void showCameraAddOption(final boolean z2) {
        if (CoreApi.a().D()) {
            start();
            return;
        }
        final LayoutInflater from = LayoutInflater.from(this);
        final MenuDialog menuDialog = new MenuDialog(this);
        this.f1499a = menuDialog;
        menuDialog.a((BaseAdapter) new BaseAdapter() {
            public int getCount() {
                return 5;
            }

            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                switch (i) {
                    case 0:
                        View inflate = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate.findViewById(R.id.text1)).setText(R.string.camera_option_sort);
                        inflate.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                    return;
                                }
                                SmartHomeMainActivity.this.startActivity(new Intent(SmartHomeMainActivity.this.getContext(), CameraSortActivity.class));
                                menuDialog.dismiss();
                            }
                        });
                        return inflate;
                    case 1:
                        View inflate2 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate2.findViewById(R.id.text1)).setText(R.string.camera_option_change_show);
                        inflate2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                    return;
                                }
                                new MLAlertDialog.Builder(SmartHomeMainActivity.this.q).a((CharSequence[]) new String[]{SmartHomeMainActivity.this.getString(R.string.camera_option_large_show), SmartHomeMainActivity.this.getString(R.string.camera_option_smarll_show)}, CameraGroupManager.a().f(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialogInterface, int i) {
                                        CameraGroupManager.a().a(i, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(Void voidR) {
                                                LocalBroadcastManager.getInstance(SmartHomeMainActivity.this.q).sendBroadcast(new Intent(AllCameraPage.d));
                                                dialogInterface.dismiss();
                                            }

                                            public void onFailure(Error error) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        dialogInterface.dismiss();
                                    }
                                }).b().show();
                                menuDialog.dismiss();
                            }
                        });
                        return inflate2;
                    case 2:
                        View inflate3 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate3.findViewById(R.id.text1)).setText(R.string.camera_option_fullscreen);
                        inflate3.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                    return;
                                }
                                SmartHomeMainActivity.this.startActivity(new Intent(SmartHomeMainActivity.this.getContext(), CameraHorizontalActivity.class));
                                menuDialog.dismiss();
                            }
                        });
                        return inflate3;
                    case 3:
                        View inflate4 = from.inflate(R.layout.menu_dialog_item_with_red_point, (ViewGroup) null);
                        ((TextView) inflate4.findViewById(R.id.text1)).setText(R.string.add_device);
                        if (z2) {
                            inflate4.findViewById(R.id.new_message_tag).setVisibility(0);
                        }
                        inflate4.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                    return;
                                }
                                SmartHomeMainActivity.this.start();
                                menuDialog.dismiss();
                            }
                        });
                        return inflate4;
                    case 4:
                        View inflate5 = from.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                        ((TextView) inflate5.findViewById(R.id.text1)).setText(R.string.add_scene);
                        inflate5.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!CoreApi.a().q()) {
                                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                                    menuDialog.dismiss();
                                }
                            }
                        });
                        return inflate5;
                    default:
                        return view;
                }
            }
        });
        menuDialog.setCanceledOnTouchOutside(true);
        if (CoreApi.a().q()) {
            menuDialog.show();
            StatHelper.k();
        }
    }

    private void k() {
        int intExtra;
        Intent intent = getIntent();
        if (!intent.hasExtra(MiuiSplashActivity.KEY_JUMP_TO_FLAG) && !ApiConst.f16684a.equals(intent.getAction()) && (intExtra = intent.getIntExtra("source", 0)) != 12 && intExtra != 15) {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    if (MiuiSplashAdsManager.a().a(SHApplication.getAppContext()) == null) {
                        AnonymousClass1 r0 = new CoreApi.IsCoreReadyCallback() {
                            public void onCoreReady() {
                                if (SmartHomeMainActivity.this.isValid() && !CoreApi.a().D()) {
                                    MiuiSplashAdsManager.a().d();
                                }
                            }
                        };
                        SmartHomeMainActivity.this.T.add(r0);
                        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) r0);
                        return;
                    }
                    SmartHomeMainActivity.this.startActivity(new Intent(SmartHomeMainActivity.this, MiuiSplashActivity.class));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        try {
            if (!HomeManager.A()) {
                LoginApi.a().a(new com.xiaomi.youpin.login.AsyncCallback() {
                    public void a(Object obj) {
                        SmartHomeMainActivity.this.m();
                    }

                    public void a(com.xiaomi.youpin.login.entity.Error error) {
                        SmartHomeMainActivity.this.m();
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        Bundle extras;
        Object obj;
        try {
            String b2 = LoginUtil.b();
            String str = null;
            if (!(!ApiConst.f16684a.equals(getIntent().getAction()) || (extras = getIntent().getExtras()) == null || (obj = extras.get("user_id")) == null)) {
                str = obj.toString();
            }
            if (TextUtils.isEmpty(b2) || (!TextUtils.isEmpty(str) && !b2.equals(str))) {
                SHApplication.getStateNotifier().f();
                this.mIsProcessAccount = false;
                return;
            }
            SHApplication.getStateNotifier().d();
            MultiHomeDeviceManager.a().b();
            SmartHomeDeviceManager.a().v();
            this.g = true;
            LoginApi.a().a(getApplicationContext(), (Activity) this, (MiuiSystemLoginCallback) new MiuiSystemLoginCallback() {
                public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                    SmartHomeMainActivity.this.n();
                    MessageRecord.deleteAll();
                    SmartHomeMainActivity.this.o();
                    SmartHomeMainActivity.this.u();
                    SmartHomeMainActivity.this.v();
                    SmartHomeMainActivity.this.mIsProcessAccount = false;
                    SettingMainPageV2 settingMainPage = SmartHomeMainActivity.this.getSettingMainPage();
                    if (settingMainPage != null && !settingMainPage.isDetached()) {
                        settingMainPage.b();
                    }
                }

                public void onLoginFail(int i, String str, Map<String, String> map) {
                    SHApplication.getStateNotifier().g();
                    SmartHomeMainActivity.this.o();
                    SmartHomeMainActivity.this.mIsProcessAccount = false;
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void n() {
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

    /* access modifiers changed from: private */
    public void o() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                SmartHomeDeviceManager.a().p();
                return null;
            }
        }, new Void[0]);
    }

    public View getChooseDeviceTitleBar() {
        if (this.t == null) {
            this.t = ((ViewStub) findViewById(R.id.title_bar_choose_device_stub)).inflate();
            TitleBarUtil.a(TitleBarUtil.a(), this.t.findViewById(R.id.title_bar_choose_device));
        }
        return this.t;
    }

    public View getChooseDeviceMenuBar() {
        if (this.u == null) {
            this.u = ((ViewStub) findViewById(R.id.edit_action_bar_stub)).inflate();
        }
        return this.u;
    }

    public View getChooseDeviceMenuBar2() {
        if (this.v == null) {
            this.v = ((ViewStub) findViewById(R.id.edit_action_bar_stub_v2)).inflate();
        }
        return this.v;
    }

    public View getChooseSceneTitleBar() {
        if (this.P == null) {
            this.P = ((ViewStub) findViewById(R.id.title_bar_choose_scene_stub)).inflate();
            if (TitleBarUtil.f11582a) {
                TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_scene));
            }
        }
        return this.P;
    }

    private void a(View view, final Runnable runnable) {
        MyScaleAnimation myScaleAnimation = new MyScaleAnimation(0.8f, 1, 0.5f, 1, 0.5f);
        myScaleAnimation.setDuration(360);
        myScaleAnimation.setInterpolator(new LinearInterpolator());
        myScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                runnable.run();
            }
        });
        view.startAnimation(myScaleAnimation);
    }

    public boolean showIrHint(Rect rect) {
        if (this.w == null || this.w.getCurrentItem() != 0) {
            return false;
        }
        if (this.i != null && this.i.getVisibility() == 0) {
            return false;
        }
        if (this.i == null) {
            this.i = ((ViewStub) findViewById(R.id.ir_hint)).inflate();
        }
        Log.e("IR", "show IR hint view");
        this.i.setVisibility(0);
        IRHintBgView iRHintBgView = (IRHintBgView) this.i.findViewById(R.id.ir_hint_bg);
        LinearLayout linearLayout = (LinearLayout) this.i.findViewById(R.id.main_view_container);
        ImageView imageView = (ImageView) this.i.findViewById(R.id.arrow);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(-1, -2);
        }
        int i2 = getResources().getDisplayMetrics().heightPixels;
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.ir_container);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout2.getLayoutParams();
        if (i2 - rect.bottom < layoutParams2.height) {
            layoutParams2.height = (i2 - rect.bottom) - DisplayUtils.a(30.0f);
            linearLayout2.setLayoutParams(layoutParams2);
        }
        layoutParams.topMargin = rect.bottom + 5;
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        if (layoutParams3 == null) {
            layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        }
        layoutParams3.leftMargin = rect.centerX() - DisplayUtils.a(7.0f);
        imageView.setLayoutParams(layoutParams3);
        iRHintBgView.setTranspantRect(rect);
        this.i.setOnClickListener($$Lambda$SmartHomeMainActivity$4X4EZgr9ZWB3CMtAHbGoLvIHzQ.INSTANCE);
        this.i.findViewById(R.id.btn_air_condition).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartHomeMainActivity.this.d(view);
            }
        });
        this.i.findViewById(R.id.btn_tv).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartHomeMainActivity.this.c(view);
            }
        });
        this.i.findViewById(R.id.btn_tv_box).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartHomeMainActivity.this.b(view);
            }
        });
        this.i.findViewById(R.id.bind_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartHomeMainActivity.this.a(view);
            }
        });
        STAT.e.e();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        a(view, (Runnable) new Runnable() {
            public final void run() {
                SmartHomeMainActivity.this.H();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void H() {
        IRDeviceUtil.a((Activity) this, 3);
        this.i.setVisibility(8);
        STAT.d.am(getResources().getString(R.string.ir_device_type_air_conditioner_text));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        a(view, (Runnable) new Runnable() {
            public final void run() {
                SmartHomeMainActivity.this.G();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void G() {
        this.i.setVisibility(8);
        IRDeviceUtil.a((Activity) this, 1);
        STAT.d.am(getResources().getString(R.string.ir_device_type_tv_text));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        a(view, (Runnable) new Runnable() {
            public final void run() {
                SmartHomeMainActivity.this.F();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void F() {
        this.i.setVisibility(8);
        IRDeviceUtil.a((Activity) this, 2);
        STAT.d.am(getResources().getString(R.string.ir_device_type_set_top_box_text));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        Intent intent = new Intent();
        intent.putExtra("add_device", true);
        IRDeviceUtil.a((Context) this, intent);
        STAT.d.ar();
    }

    public View getChooseSceneMenuBar() {
        if (this.Q == null) {
            this.Q = ((ViewStub) findViewById(R.id.menu_choose_scene_stub)).inflate();
        }
        return this.Q;
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        if (!isValid()) {
            finish();
        } else if (this.S) {
            this.mPendingIntent = intent;
        } else if (intent != null) {
            if (!openDevice(intent, false)) {
                int intExtra = intent.getIntExtra("source", 0);
                if (intExtra == 2) {
                    Class cls = (Class) intent.getSerializableExtra("target_activity");
                    if (cls.getName().equalsIgnoreCase(SmartHomeMainActivity.class.getName()) && intent.getBundleExtra("target_args").getInt(INTENT_KEY_REQUEST_CODE) == 8) {
                        p();
                    } else if (cls.getName().equalsIgnoreCase(SmartHomeMainActivity.class.getName()) && intent.getBundleExtra("target_args").getInt(INTENT_KEY_REQUEST_CODE) == 3) {
                        q();
                    } else if (!OpenApi.a((Class<?>) cls)) {
                        LogUtilGrey.a("SmartHomeMainActivity", "processArgs invalid open api activity " + cls);
                    } else {
                        Bundle bundleExtra = intent.getBundleExtra("target_args");
                        int intExtra2 = intent.getIntExtra(ApiConst.K, 0);
                        Intent intent2 = new Intent(SHApplication.getAppContext(), cls);
                        intent2.addFlags(268566528);
                        intent2.addFlags(intExtra2);
                        if (bundleExtra != null) {
                            intent2.putExtras(bundleExtra);
                        }
                        startActivity(intent2);
                    }
                } else if (intExtra == 1) {
                    if (!CoreApi.a().q()) {
                        LoginApi.a().a(getApplicationContext(), 1, (LoginApi.LoginCallback) null);
                    }
                } else if (intExtra == 3) {
                    if (mScanResult != null) {
                        for (int i2 = 0; i2 < mScanResult.size(); i2++) {
                            this.mCachedScanResult.add(mScanResult.get(i2).BSSID);
                        }
                        newDeviceWithStartConnect(mScanResult);
                        return;
                    }
                    gotoConnectionSelectPage();
                } else if (intExtra == 5) {
                    Device b2 = SmartHomeDeviceManager.a().b(getIntent().getStringExtra("device_id"));
                    if (b2 == null) {
                        return;
                    }
                    if (b2 instanceof MiTVDevice) {
                        MitvDeviceManager.a(this, b2);
                    } else if ((b2 instanceof RouterDevice) && !b2.isBinded()) {
                        b2.bindDevice(this.q, (Device.IBindDeviceCallback) null);
                    }
                } else if (intExtra == 6) {
                    if (!CoreApi.a().q()) {
                        MultiHomeDeviceManager.a().b();
                        SmartHomeDeviceManager.a().v();
                        SceneManager.x().A();
                        SmartHomeDeviceManager.a().p();
                        sendBroadcast(new Intent(WifiScanHomelog.c));
                        LoginApi.a().a(this.q, 5, (LoginApi.LoginCallback) null);
                        return;
                    }
                    String stringExtra = getIntent().getStringExtra(ApiConst.j);
                    if (this.E != null) {
                        SmartHomeMainActivityLifecycle smartHomeMainActivityLifecycle = this.E;
                        smartHomeMainActivityLifecycle.a("https://home.mi.com/shop/detail?gid=" + stringExtra);
                    }
                } else if (intExtra == 7) {
                    Uri data = ((Intent) getIntent().getParcelableExtra(ApiConst.N)).getData();
                    PageUrl.UrlConfigInfo c2 = PageUrl.c(data);
                    if (c2 == null) {
                        return;
                    }
                    if (!c2.c()) {
                        UrlResolver.a(this, data, false);
                    } else if (CoreApi.a().q()) {
                        UrlResolver.a(this, data, false);
                    } else {
                        if (this.f1499a != null && this.f1499a.isShowing()) {
                            this.f1499a.dismiss();
                        }
                        this.f1499a = new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MultiHomeDeviceManager.a().b();
                                SmartHomeDeviceManager.a().v();
                                SceneManager.x().A();
                                SmartHomeDeviceManager.a().p();
                                SmartHomeMainActivity.this.sendBroadcast(new Intent(WifiScanHomelog.c));
                                LoginApi.a().a(SmartHomeMainActivity.this.q, 5, (LoginApi.LoginCallback) null);
                                dialogInterface.dismiss();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                            }
                        }).b((int) R.string.loing_helper_title).d();
                    }
                } else if (intExtra == 8) {
                    Uri data2 = ((Intent) getIntent().getParcelableExtra(ApiConst.O)).getData();
                    PageUrl.UrlConfigInfo c3 = PageUrl.c(data2);
                    if (c3 == null) {
                        return;
                    }
                    if (!c3.c()) {
                        UrlResolver.a(this, data2, false);
                    } else if (CoreApi.a().q()) {
                        UrlResolver.a(this, data2, false);
                    } else {
                        if (this.f1499a != null && this.f1499a.isShowing()) {
                            this.f1499a.dismiss();
                        }
                        this.f1499a = new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MultiHomeDeviceManager.a().b();
                                SmartHomeDeviceManager.a().v();
                                SceneManager.x().A();
                                SmartHomeDeviceManager.a().p();
                                SmartHomeMainActivity.this.sendBroadcast(new Intent(WifiScanHomelog.c));
                                LoginApi.a().a(SmartHomeMainActivity.this.q, 5, (LoginApi.LoginCallback) null);
                                dialogInterface.dismiss();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                            }
                        }).b((int) R.string.loing_helper_title).d();
                    }
                } else if (intExtra == 9) {
                    String stringExtra2 = intent.getStringExtra("page");
                    if (!TextUtils.isEmpty(stringExtra2) && this.w != null) {
                        if (stringExtra2.equalsIgnoreCase("device_list")) {
                            this.mHandler.post(new Runnable() {
                                public void run() {
                                    SmartHomeMainActivity.this.w.setCurrentItem(0);
                                    SmartHomeMainActivity.this.p.setCurrentItem(0);
                                }
                            });
                        } else if (stringExtra2.equalsIgnoreCase("shop")) {
                            if (!CoreApi.a().D()) {
                                this.mHandler.post(new Runnable() {
                                    public void run() {
                                        SmartHomeMainActivity.this.w.setCurrentItem(1);
                                        SmartHomeMainActivity.this.p.setCurrentItem(1);
                                    }
                                });
                            }
                        } else if (!stringExtra2.equalsIgnoreCase("personal_center")) {
                        } else {
                            if (!CoreApi.a().D()) {
                                this.mHandler.post(new Runnable() {
                                    public void run() {
                                        SmartHomeMainActivity.this.w.setCurrentItem(3);
                                        SmartHomeMainActivity.this.p.setCurrentItem(3);
                                    }
                                });
                            } else {
                                this.mHandler.post(new Runnable() {
                                    public void run() {
                                        if (SmartHomeMainActivity.f != null) {
                                            for (int i = 0; i < SmartHomeMainActivity.f.length; i++) {
                                                if (TextUtils.equals(SettingMainPageV2.class.getSimpleName(), SmartHomeMainActivity.f[i])) {
                                                    SmartHomeMainActivity.this.p.setCurrentItem(i);
                                                    SmartHomeMainActivity.this.w.setCurrentItem(i);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                } else if (intExtra == 11) {
                    StatHelper.as();
                    MobclickAgent.a((Context) this, "open_app", "open_app_from_miui_lockscreen");
                } else if (intExtra == 14) {
                    final String stringExtra3 = intent.getStringExtra("device_id");
                    if (!TextUtils.isEmpty(stringExtra3)) {
                        this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                SmartHomeMainActivity.this.getClientAllPage().a(stringExtra3);
                            }
                        }, 1000);
                    }
                } else if (intExtra != 16) {
                } else {
                    if (CoreApi.a().D()) {
                        a(false, getString(R.string.share_accept_home_fail_not_support), (String) null);
                        return;
                    }
                    String stringExtra4 = intent.getStringExtra(ApiConst.l);
                    if (TextUtils.isEmpty(stringExtra4)) {
                        a(false, getString(R.string.accept_wx_share_error), (String) null);
                    } else {
                        a(stringExtra4);
                    }
                }
            }
        }
    }

    private void a(String str) {
        if (this.N == null) {
            this.O = XQProgressDialog.a(this, "", getString(R.string.loading));
            HomeMemberManager.a().b(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (!SmartHomeMainActivity.this.isValid()) {
                        return;
                    }
                    if (jSONObject != null) {
                        SmartHomeMainActivity.this.O.dismiss();
                        SHApplication.getGlobalHandler().postDelayed($$Lambda$SmartHomeMainActivity$42$WCx6UrQB6qpS3jTMhzauQaFFuo4.INSTANCE, 3500);
                        SmartHomeMainActivity smartHomeMainActivity = SmartHomeMainActivity.this;
                        Object[] objArr = new Object[1];
                        objArr[0] = TextUtils.isEmpty(jSONObject.optString("home_name")) ? "" : jSONObject.optString("home_name");
                        SmartHomeMainActivity.this.a(true, smartHomeMainActivity.getString(R.string.home_share_response_success1, objArr), jSONObject.optString("home_id"));
                        return;
                    }
                    SmartHomeMainActivity.this.a(false, SmartHomeMainActivity.this.getString(R.string.accept_wx_share_error), (String) null);
                }

                public void onFailure(Error error) {
                    String b = error.b();
                    if (SmartHomeMainActivity.this.isValid()) {
                        SmartHomeMainActivity.this.O.dismiss();
                        SmartHomeMainActivity.this.a(false, b, (String) null);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, String str, String str2) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        if (z2) {
            if (!TextUtils.isEmpty(str2)) {
                builder.b((CharSequence) str);
                builder.a((int) R.string.view_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(str2) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        SmartHomeMainActivity.this.b(this.f$1, dialogInterface, i);
                    }
                });
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$SmartHomeMainActivity$CoOvDHFjeEnx8bfGsLqoRqEsnUM.INSTANCE);
                builder.a(false);
                builder.b().show();
                STAT.e.a(4);
                return;
            }
            ToastUtil.a((CharSequence) getString(R.string.home_share_response_success_exception));
        } else if (!TextUtils.isEmpty(str)) {
            ToastUtil.a((CharSequence) str);
        } else {
            ToastUtil.a((CharSequence) getString(R.string.accept_wx_share_error));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(String str, DialogInterface dialogInterface, int i2) {
        this.O = XQProgressDialog.a(this, "", getString(R.string.loading));
        STAT.d.a(4, 1);
        if (HomeManager.a().j(str) != null) {
            HomeManager.a().a(str, (AsyncCallback) new AsyncCallback() {
                public void onSuccess(Object obj) {
                    if (SmartHomeMainActivity.this.isValid()) {
                        SmartHomeMainActivity.this.O.dismiss();
                    }
                }

                public void onFailure(Error error) {
                    if (SmartHomeMainActivity.this.isValid()) {
                        SmartHomeMainActivity.this.O.dismiss();
                    }
                    ToastUtil.a((int) R.string.action_fail);
                }
            });
            return;
        }
        this.N = new HomeRoomReceiver();
        this.N.f13525a = str;
        LocalBroadcastManager.getInstance(this).registerReceiver(this.N, new IntentFilter(HomeManager.t));
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public final void run() {
                SmartHomeMainActivity.this.E();
            }
        }, 6000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void E() {
        if (this.O != null && isValid()) {
            this.O.dismiss();
        }
        if (this.N != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.N);
            ToastUtil.a((CharSequence) SHApplication.getAppContext().getString(R.string.home_share_response_success_exception));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        STAT.d.aK();
    }

    public void onClickCommonUseDevice(Device device, RectF rectF, String str) {
        STAT.d.b(false);
        if (device != null) {
            this.k = device;
            Intent intent = new Intent();
            intent.putExtra("did", device.did);
            intent.putExtra("room_name", str);
            intent.setClass(getContext(), MIUI10CardActivity.class);
            intent.putExtra("view_position", rectF);
            startActivityForResult(intent, 6);
            b();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x014b A[EDGE_INSN: B:117:0x014b->B:65:0x014b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x023b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean openDevice(android.content.Intent r18, boolean r19) {
        /*
            r17 = this;
            r6 = r17
            r0 = r18
            java.lang.String r1 = "com.xiaomi.smarthome.action.viewdevice"
            java.lang.String r2 = r18.getAction()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0269
            java.lang.String r1 = ""
            android.content.Intent r2 = r17.getIntent()
            android.os.Bundle r2 = r2.getExtras()
            if (r2 == 0) goto L_0x0033
            java.lang.String r3 = "user_id"
            java.lang.Object r2 = r2.get(r3)
            if (r2 == 0) goto L_0x0033
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "0"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0033
            java.lang.String r1 = ""
        L_0x0033:
            java.lang.String r2 = "device_mac"
            java.lang.String r2 = r0.getStringExtra(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0043
            java.lang.String r2 = r2.toLowerCase()
        L_0x0043:
            java.lang.String r3 = "device_id"
            java.lang.String r3 = r0.getStringExtra(r3)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            r7 = 1
            if (r4 == 0) goto L_0x0056
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x0268
        L_0x0056:
            com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r4 = r4.q()
            r5 = 0
            if (r4 != 0) goto L_0x0092
            com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager r0 = com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager.a()
            r0.b()
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            r0.v()
            com.xiaomi.router.api.SceneManager r0 = com.xiaomi.router.api.SceneManager.x()
            r0.A()
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            r0.p()
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "update_remote_wifi_log"
            r0.<init>(r2)
            r6.sendBroadcast(r0)
            com.xiaomi.smarthome.frame.login.LoginApi r0 = com.xiaomi.smarthome.frame.login.LoginApi.a()
            android.content.Context r2 = r6.q
            r0.a((android.content.Context) r2, (java.lang.String) r1, (com.xiaomi.smarthome.frame.login.LoginApi.LoginCallback) r5)
            return r7
        L_0x0092:
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x00b4
            com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r4 = r4.q()
            if (r4 == 0) goto L_0x00b4
            com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r4 = r4.s()
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x00b4
            r6.c((java.lang.String) r1)
            return r7
        L_0x00b4:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r1 = r1.d()
            if (r1 == 0) goto L_0x0116
            java.util.Iterator r1 = r1.iterator()
        L_0x00c2:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0116
            java.lang.Object r4 = r1.next()
            com.xiaomi.smarthome.device.Device r4 = (com.xiaomi.smarthome.device.Device) r4
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 != 0) goto L_0x00e4
            boolean r8 = r4 instanceof com.xiaomi.smarthome.device.MiTVDevice
            if (r8 == 0) goto L_0x00e4
            r8 = r4
            com.xiaomi.smarthome.device.MiTVDevice r8 = (com.xiaomi.smarthome.device.MiTVDevice) r8
            java.lang.String r8 = r8.u
            boolean r8 = r2.equals(r8)
            if (r8 == 0) goto L_0x00e4
            goto L_0x0117
        L_0x00e4:
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 != 0) goto L_0x00ff
            java.lang.String r8 = r4.mac
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x00ff
            java.lang.String r8 = r4.mac
            java.lang.String r8 = r8.toLowerCase()
            boolean r8 = r2.equals(r8)
            if (r8 == 0) goto L_0x00ff
            goto L_0x0117
        L_0x00ff:
            boolean r8 = android.text.TextUtils.isEmpty(r3)
            if (r8 != 0) goto L_0x00c2
            java.lang.String r8 = r4.did
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x00c2
            java.lang.String r8 = r4.did
            boolean r8 = r3.equals(r8)
            if (r8 == 0) goto L_0x00c2
            goto L_0x0117
        L_0x0116:
            r4 = r5
        L_0x0117:
            if (r4 != 0) goto L_0x014b
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r1 = r1.k()
            if (r1 == 0) goto L_0x014b
            java.util.Iterator r1 = r1.iterator()
        L_0x0127:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x014b
            java.lang.Object r2 = r1.next()
            com.xiaomi.smarthome.device.Device r2 = (com.xiaomi.smarthome.device.Device) r2
            boolean r8 = android.text.TextUtils.isEmpty(r3)
            if (r8 != 0) goto L_0x0127
            java.lang.String r8 = r2.did
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x0127
            java.lang.String r8 = r2.did
            boolean r8 = r3.equals(r8)
            if (r8 == 0) goto L_0x0127
            r1 = r2
            goto L_0x014c
        L_0x014b:
            r1 = r4
        L_0x014c:
            if (r1 == 0) goto L_0x0165
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "openDevice "
            r2.append(r3)
            java.lang.String r3 = r1.did
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.xiaomi.smarthome.miio.Miio.g(r2)
            goto L_0x016a
        L_0x0165:
            java.lang.String r2 = "openDevice null"
            com.xiaomi.smarthome.miio.Miio.g(r2)
        L_0x016a:
            if (r1 == 0) goto L_0x023b
            boolean r2 = r1.isBinded()
            if (r2 != 0) goto L_0x01ab
            boolean r2 = r1.canUseNotBind
            if (r2 != 0) goto L_0x01ab
            android.app.Dialog r0 = r6.f1499a
            if (r0 == 0) goto L_0x0187
            android.app.Dialog r0 = r6.f1499a
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L_0x0187
            android.app.Dialog r0 = r6.f1499a
            r0.dismiss()
        L_0x0187:
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
            r0.<init>(r6)
            r1 = 2131497440(0x7f0c11e0, float:1.8618473E38)
            java.lang.String r1 = r6.getString(r1)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.b((java.lang.CharSequence) r1)
            r1 = 2131497396(0x7f0c11b4, float:1.8618384E38)
            com.xiaomi.smarthome.SmartHomeMainActivity$44 r2 = new com.xiaomi.smarthome.SmartHomeMainActivity$44
            r2.<init>()
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((int) r1, (android.content.DialogInterface.OnClickListener) r2)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r0 = r0.d()
            r6.f1499a = r0
            goto L_0x0268
        L_0x01ab:
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r3 = r1.model
            boolean r2 = r2.c((java.lang.String) r3)
            if (r2 == 0) goto L_0x0220
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r3 = r1.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r10 = r2.d((java.lang.String) r3)
            android.content.Intent r12 = new android.content.Intent
            r12.<init>()
            if (r0 == 0) goto L_0x01cb
            r12.putExtras(r0)
        L_0x01cb:
            android.app.Dialog r0 = r6.f1499a
            if (r0 == 0) goto L_0x01dc
            android.app.Dialog r0 = r6.f1499a
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L_0x01dc
            android.app.Dialog r0 = r6.f1499a
            r0.dismiss()
        L_0x01dc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r2 = 2131497833(0x7f0c1369, float:1.861927E38)
            java.lang.String r2 = r6.getString(r2)
            r0.append(r2)
            java.lang.String r2 = r10.p()
            r0.append(r2)
            r2 = 2131497825(0x7f0c1361, float:1.8619254E38)
            java.lang.String r2 = r6.getString(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog r0 = com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog.b(r6, r0)
            r6.f1499a = r0
            com.xiaomi.smarthome.frame.plugin.PluginApi r8 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
            android.content.Context r9 = r17.getApplicationContext()
            r11 = 1
            com.xiaomi.smarthome.device.api.DeviceStat r13 = r1.newDeviceStat()
            r14 = 0
            r15 = 0
            com.xiaomi.smarthome.SmartHomeMainActivity$45 r1 = new com.xiaomi.smarthome.SmartHomeMainActivity$45
            r1.<init>(r0)
            r16 = r1
            r8.sendMessage(r9, r10, r11, r12, r13, r14, r15, r16)
            goto L_0x0268
        L_0x0220:
            if (r0 == 0) goto L_0x0228
            android.os.Bundle r0 = r18.getExtras()
            r3 = r0
            goto L_0x0229
        L_0x0228:
            r3 = r5
        L_0x0229:
            com.xiaomi.smarthome.device.renderer.DeviceRenderer r0 = com.xiaomi.smarthome.device.renderer.DeviceRenderer.a((com.xiaomi.smarthome.device.Device) r1)
            r4 = 0
            r5 = 0
            r2 = r17
            android.content.Intent r0 = r0.a((com.xiaomi.smarthome.device.Device) r1, (android.content.Context) r2, (android.os.Bundle) r3, (boolean) r4, (com.xiaomi.smarthome.device.renderer.DeviceRenderer.LoadingCallback) r5)
            if (r0 == 0) goto L_0x0268
            r6.startActivity(r0)
            goto L_0x0268
        L_0x023b:
            if (r19 != 0) goto L_0x024e
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            com.xiaomi.smarthome.device.SmartHomeDeviceManager$IClientDeviceListener r1 = r6.listener
            r0.a((com.xiaomi.smarthome.device.SmartHomeDeviceManager.IClientDeviceListener) r1)
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            r0.p()
            goto L_0x0268
        L_0x024e:
            if (r1 != 0) goto L_0x0257
            r0 = 2131497444(0x7f0c11e4, float:1.8618481E38)
            com.xiaomi.smarthome.library.common.util.ToastUtil.a((int) r0)
            goto L_0x0268
        L_0x0257:
            boolean r0 = r1.isOnline
            if (r0 != 0) goto L_0x0262
            r0 = 2131497443(0x7f0c11e3, float:1.861848E38)
            com.xiaomi.smarthome.library.common.util.ToastUtil.a((int) r0)
            goto L_0x0268
        L_0x0262:
            r0 = 2131497441(0x7f0c11e1, float:1.8618475E38)
            com.xiaomi.smarthome.library.common.util.ToastUtil.a((int) r0)
        L_0x0268:
            return r7
        L_0x0269:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.SmartHomeMainActivity.openDevice(android.content.Intent, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        DevicePushBindManager.instance.onPause();
        if (!this.H) {
            mIsActivityResumed = false;
            b((Activity) this);
            KuailianManager.a().b();
            AnonymousClass46 r0 = new Runnable() {
                public void run() {
                    if (SmartHomeMainActivity.this.isValid()) {
                        SmartHomeMainActivity.this.D.remove(this);
                        WifiUtil.g(SHApplication.getAppContext());
                    }
                }
            };
            this.D.put(r0, r0);
            SHApplication.getGlobalWorkerHandler().postDelayed(r0, 1000);
            SHApplication.getPushManager().b(PushType.DEVICE_CONNECTED, this.G);
            ImageDownloadManager.a().c();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LogUtil.a("SmartHomeMainActivity", "SmartHomeMainActivityonDestroy");
        super.onDestroy();
        if (this.I != null) {
            this.I = null;
        }
        MiotMonitorClient.h();
        sendBroadcast(new Intent(CoreLogUtilGrey.f16120a).putExtra(CoreLogUtilGrey.b, false));
        if (!this.H) {
            ShopNewPointManager.a().a((ShopNewPointManager.IShowNewPointListener) null);
            if (this.p != null) {
                this.p.setOnPageChangeListener((ViewPager.OnPageChangeListener) null);
                this.p.setOnTabReselectedListener((TabPageIndicator.OnTabReselectedListener) null);
                AppUpdateManger.a().m();
            }
            CoreApi.a().T();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        NetworkManager.a().b(SmartHomeMainActivity.this.mNetworkListener);
                        if (SmartHomeMainActivity.this.mNetworkListener != null) {
                            SmartHomeMainActivity.this.mNetworkListener = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1000);
            try {
                BluetoothReceiver.unregisterBluetoothReceiver();
                ControlCardInfoManager.f().b(this.m);
                MiotSpecCardManager.f().b(this.m);
                ControlCardInfoManager.f().k();
                MiotSpecCardManager.f().h();
                LocalBroadcastManager.getInstance(this).unregisterReceiver(this.M);
                if (this.N != null) {
                    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.N);
                }
                this.p = null;
                this.w = null;
                this.x = null;
                SceneConditionWifiManager.a().b();
                MiBrainManager.a().c();
                VoiceManager.a().d();
                ServerTimerManager.a((Context) this).d();
                RedPointManagerNew.a().c();
                MessageCenter.a().h();
                ConsumableDataManager.a().c();
                CameraFrameManager.a().a(this.q);
                PreferenceUtils.b(AppConstants.G, true);
                HomeManager.J();
                UpdateItemHelper.a().a(true);
                z();
                PluginRuntimeManager.getInstance().exitAllFrameProcess();
                RecommendSceneManager.a().n();
                AreaInfoManager.a().d();
                if (MiBrainCloudSDKManager.g() != null) {
                    MiBrainCloudSDKManager.g().m();
                }
                SceneManager.x().z();
                LiteSceneManager.j().l();
                ShowProvinceHelper.b();
                ApDeviceManager.a().e();
                SHApplication.getGlobalWorkerHandler().removeCallbacks(this.L);
                SHApplication.getGlobalWorkerHandler().removeCallbacks(this.J);
                SHApplication.getGlobalWorkerHandler().removeCallbacks(this.K);
                CoreApi.a().Y();
                C();
                UpdateManager.a().b();
                getLifecycle().b(this.E);
                if (this.f1499a != null && this.f1499a.isShowing()) {
                    this.f1499a.dismiss();
                }
                SmartHomeDeviceManager.a().c(this.listener);
                NetworkDetector.a().c();
                Set<Runnable> keySet = this.C.keySet();
                if (keySet != null && !keySet.isEmpty()) {
                    for (Runnable removeCallbacks : keySet) {
                        SHApplication.getGlobalHandler().removeCallbacks(removeCallbacks);
                    }
                    this.C.clear();
                }
                Set<Runnable> keySet2 = this.D.keySet();
                if (keySet2 != null && !keySet2.isEmpty()) {
                    for (Runnable removeCallbacks2 : keySet2) {
                        SHApplication.getGlobalHandler().removeCallbacks(removeCallbacks2);
                    }
                    this.D.clear();
                }
                SHApplication.getGlobalHandler().postDelayed(new GCTask(), 3000);
                SharedHomeDeviceManager.b().e();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.E == null || !this.E.a(i2, keyEvent)) {
            return super.onKeyUp(i2, keyEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        SettingMainPageV2 settingMainPage;
        super.onActivityResult(i2, i3, intent);
        LogUtil.a("SmartHomeMainActivity", "onActivityResult" + i2);
        if (i2 == 6) {
            a(this.k);
            c();
        } else if (i2 == 6050) {
            if (i3 == -1) {
                ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(PermissionRequestActivity.ARG_KEYS_PERMISSION);
                if (parcelableArrayListExtra == null || parcelableArrayListExtra.size() <= 0) {
                    b(getIntent());
                } else {
                    this.F.a((Activity) this, (ArrayList<PermissionBean>) parcelableArrayListExtra);
                }
            } else {
                b(getIntent());
            }
        } else if (i2 == 199) {
            b(getIntent());
        } else if (i2 != 2) {
            if (i2 == 1) {
                if (i3 == -1 && (settingMainPage = getSettingMainPage()) != null) {
                    settingMainPage.c();
                }
            } else if (i2 == 5) {
                gotoConnectionSelectPage();
            } else if (i2 == 3) {
                if (i3 == -1) {
                    Intent intent2 = null;
                    this.y = null;
                    if (intent != null && !intent.getBooleanExtra("is_ble_device", false)) {
                        this.y = (ScanResult) intent.getParcelableExtra("scanResult");
                        startWifiSetting(intent.getExtras().getString("result"));
                    }
                    if (intent != null && intent.getBooleanExtra("is_ble_device", false) && mChooseBleDevice != null) {
                        Intent intent3 = getIntent();
                        if (CoreApi.a().c(mChooseBleDevice.model)) {
                            BleDispatcher.a((Context) this, mChooseBleDevice, intent3, (ArrayList<String>) null);
                        } else if (DeviceUtils.a(mChooseBleDevice)) {
                            BleDispatcher.b(this, mChooseBleDevice, intent3, (ArrayList<String>) null);
                        } else {
                            Bundle extras = intent3 != null ? intent3.getExtras() : null;
                            if (mChooseBleDevice != null) {
                                intent2 = DeviceRenderer.a((Device) mChooseBleDevice).a((Device) mChooseBleDevice, (Context) this, extras, false, (DeviceRenderer.LoadingCallback) null);
                            }
                            if (intent2 != null) {
                                startActivity(intent2);
                            }
                        }
                    }
                }
            } else if (i2 == 100) {
                getClientAllPage().onActivityResult(i2, i3, intent);
            } else if (i2 == 101) {
                getShopPage().onActivityResult(i2, i3, intent);
            } else if (i2 == 102) {
                getSettingMainPage().onActivityResult(i2, i3, intent);
            } else if (i2 == 999) {
                if (getSceneTabPage() != null) {
                    getSceneTabPage().onActivityResult(i2, i3, intent);
                }
            } else if (this.E != null) {
                this.E.a(i2, i3, intent);
            }
        }
    }

    private void a(final Device device) {
        if (device != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(device.did);
            SmartHomeDeviceManager.a().a((List<String>) arrayList, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    SmartHomeDeviceManager.a().a(device);
                    for (GridViewData next : HomeManager.a().F()) {
                        if (next.f18311a == GridViewData.GridType.TYPE_NORMAL && next.b.did.equals(device.did)) {
                            Intent intent = new Intent();
                            intent.setAction("com.xiaomi.smarthome.refresh_device");
                            intent.putExtra(LoginTask.f22078a, CoreApi.a().s());
                            intent.putExtra(GetDeviceTask.c, GetDeviceTask.a(next));
                            SHApplication.getAppContext().sendBroadcast(intent);
                            return;
                        }
                    }
                }
            });
        }
    }

    public SceneTabFragment getSceneTabPage() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return null;
        }
        for (Fragment next : fragments) {
            if (next instanceof SceneTabFragment) {
                return (SceneTabFragment) next;
            }
        }
        return null;
    }

    @TargetApi(23)
    public void requestPermissions(String[] strArr, int i2, PermissionListener permissionListener) {
        if (this.E != null) {
            this.E.a(strArr, i2, permissionListener);
        }
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (this.E != null) {
            this.E.a(i2, strArr, iArr);
        }
        boolean a2 = this.F.a(i2, strArr, iArr);
        final Intent intent = getIntent();
        if (!a2) {
            this.F.a((DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartHomeMainActivity.this.b(intent);
                }
            });
        } else if (i2 == 6000) {
            b(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        b(intent);
        a(getIntent());
        if (this.E != null) {
            this.E.a(intent);
        }
        LogUtil.a("SmartHomeMainActivity", "SmartHomeMainActivityonNewIntent");
    }

    /* access modifiers changed from: private */
    public void b(Intent intent) {
        Bundle extras;
        Object obj;
        int intValue;
        int intExtra = intent.getIntExtra(INTENT_KEY_REQUEST_CODE, 0);
        if (!(intExtra != 0 || (extras = intent.getExtras()) == null || (obj = extras.get(INTENT_KEY_REQUEST_CODE_NEW)) == null)) {
            try {
                if (obj instanceof String) {
                    intValue = Integer.parseInt((String) obj);
                } else if (obj instanceof Integer) {
                    intValue = ((Integer) obj).intValue();
                }
                intExtra = intValue;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        switch (intExtra) {
            case 1:
                if (this.w != null) {
                    this.w.setCurrentItem(0);
                    this.p.setCurrentItem(0);
                    break;
                }
                break;
            case 2:
                r();
                break;
            case 3:
                q();
                break;
            case 4:
                s();
                break;
            case 5:
                t();
                break;
            case 6:
                if (!CoreApi.a().D() && this.w != null) {
                    this.mHandler.post(new Runnable() {
                        public void run() {
                            SmartHomeMainActivity.this.w.setCurrentItem(1);
                            SmartHomeMainActivity.this.p.setCurrentItem(1);
                        }
                    });
                    break;
                }
            case 7:
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        LoginApi.a().a(SmartHomeMainActivity.this.q, 5, (LoginApi.LoginCallback) null);
                    }
                }, 1000);
                break;
            case 8:
                p();
                break;
            case 9:
                a(intent.getStringExtra("did"), intent.getStringExtra("userid"), 0);
                break;
            case 10:
                b(intent.getStringExtra("service_key"));
                break;
        }
        StatHelper.a(intent);
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.equals(str, GreyUpgradeConfigManager.f13939a)) {
            GreyUpgradeConfigManager.a().a(str, (AsyncCallback<GreyUpgradeConfigManager.GreyUpgradeInfo, Error>) new AsyncCallback<GreyUpgradeConfigManager.GreyUpgradeInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(final GreyUpgradeConfigManager.GreyUpgradeInfo greyUpgradeInfo) {
                    if (greyUpgradeInfo != null && SmartHomeMainActivity.this.isValid() && GreyUpgradeConfigManager.a().a(greyUpgradeInfo)) {
                        SmartHomeMainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (SmartHomeMainActivity.this.f1499a != null && SmartHomeMainActivity.this.f1499a.isShowing()) {
                                    SmartHomeMainActivity.this.f1499a.dismiss();
                                }
                                Dialog unused = SmartHomeMainActivity.this.f1499a = new MLAlertDialog.Builder(SmartHomeMainActivity.this).b((CharSequence) SmartHomeMainActivity.this.getString(R.string.dialog_title_force_update)).a((int) R.string.dialog_right_update, (DialogInterface.OnClickListener) 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0059: INVOKE  
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0025: IGET  (r0v4 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0023: IGET  (r0v3 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog : 0x0055: INVOKE  (r1v4 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0051: INVOKE  (r1v3 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0049: INVOKE  (r1v2 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x003b: INVOKE  (r1v1 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x002d: CONSTRUCTOR  (r1v0 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x002b: IGET  (r2v1 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0029: IGET  (r2v0 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                     call: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.<init>(android.content.Context):void type: CONSTRUCTOR)
                                      (wrap: java.lang.String : 0x0037: INVOKE  (r2v4 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0032: IGET  (r2v3 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0030: IGET  (r2v2 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_title_force_update int)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.getString(int):java.lang.String type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(java.lang.CharSequence):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_right_update int)
                                      (wrap: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ : 0x0046: CONSTRUCTOR  (r4v0 com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                      (wrap: com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo : 0x0042: IGET  (r3v1 com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.a com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo)
                                     call: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ.<init>(com.xiaomi.smarthome.SmartHomeMainActivity$52$1, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo):void type: CONSTRUCTOR)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.a(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.cancel int)
                                      (null android.content.DialogInterface$OnClickListener)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.d():com.xiaomi.smarthome.library.common.dialog.MLAlertDialog type: VIRTUAL)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.access$2702(com.xiaomi.smarthome.SmartHomeMainActivity, android.app.Dialog):android.app.Dialog type: STATIC in method: com.xiaomi.smarthome.SmartHomeMainActivity.52.1.run():void, dex: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0000: IPUT  
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog : 0x0055: INVOKE  (r1v4 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0051: INVOKE  (r1v3 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0049: INVOKE  (r1v2 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x003b: INVOKE  (r1v1 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x002d: CONSTRUCTOR  (r1v0 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x002b: IGET  (r2v1 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0029: IGET  (r2v0 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                     call: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.<init>(android.content.Context):void type: CONSTRUCTOR)
                                      (wrap: java.lang.String : 0x0037: INVOKE  (r2v4 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0032: IGET  (r2v3 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0030: IGET  (r2v2 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_title_force_update int)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.getString(int):java.lang.String type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(java.lang.CharSequence):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_right_update int)
                                      (wrap: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ : 0x0046: CONSTRUCTOR  (r4v0 com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                      (wrap: com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo : 0x0042: IGET  (r3v1 com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.a com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo)
                                     call: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ.<init>(com.xiaomi.smarthome.SmartHomeMainActivity$52$1, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo):void type: CONSTRUCTOR)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.a(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.cancel int)
                                      (null android.content.DialogInterface$OnClickListener)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.d():com.xiaomi.smarthome.library.common.dialog.MLAlertDialog type: VIRTUAL)
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0025: IGET  (r0v4 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0023: IGET  (r0v3 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.a android.app.Dialog in method: com.xiaomi.smarthome.SmartHomeMainActivity.52.1.run():void, dex: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:924)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:684)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                                    	... 107 more
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0055: INVOKE  (r1v4 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0051: INVOKE  (r1v3 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0049: INVOKE  (r1v2 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x003b: INVOKE  (r1v1 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x002d: CONSTRUCTOR  (r1v0 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x002b: IGET  (r2v1 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0029: IGET  (r2v0 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                     call: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.<init>(android.content.Context):void type: CONSTRUCTOR)
                                      (wrap: java.lang.String : 0x0037: INVOKE  (r2v4 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0032: IGET  (r2v3 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0030: IGET  (r2v2 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_title_force_update int)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.getString(int):java.lang.String type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(java.lang.CharSequence):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_right_update int)
                                      (wrap: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ : 0x0046: CONSTRUCTOR  (r4v0 com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                      (wrap: com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo : 0x0042: IGET  (r3v1 com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.a com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo)
                                     call: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ.<init>(com.xiaomi.smarthome.SmartHomeMainActivity$52$1, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo):void type: CONSTRUCTOR)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.a(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.cancel int)
                                      (null android.content.DialogInterface$OnClickListener)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.d():com.xiaomi.smarthome.library.common.dialog.MLAlertDialog type: VIRTUAL in method: com.xiaomi.smarthome.SmartHomeMainActivity.52.1.run():void, dex: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:429)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 111 more
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0051: INVOKE  (r1v3 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x0049: INVOKE  (r1v2 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x003b: INVOKE  (r1v1 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x002d: CONSTRUCTOR  (r1v0 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x002b: IGET  (r2v1 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0029: IGET  (r2v0 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                     call: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.<init>(android.content.Context):void type: CONSTRUCTOR)
                                      (wrap: java.lang.String : 0x0037: INVOKE  (r2v4 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0032: IGET  (r2v3 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0030: IGET  (r2v2 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_title_force_update int)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.getString(int):java.lang.String type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(java.lang.CharSequence):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_right_update int)
                                      (wrap: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ : 0x0046: CONSTRUCTOR  (r4v0 com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                      (wrap: com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo : 0x0042: IGET  (r3v1 com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.a com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo)
                                     call: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ.<init>(com.xiaomi.smarthome.SmartHomeMainActivity$52$1, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo):void type: CONSTRUCTOR)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.a(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.cancel int)
                                      (null android.content.DialogInterface$OnClickListener)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL in method: com.xiaomi.smarthome.SmartHomeMainActivity.52.1.run():void, dex: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 115 more
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0049: INVOKE  (r1v2 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x003b: INVOKE  (r1v1 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder : 0x002d: CONSTRUCTOR  (r1v0 com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x002b: IGET  (r2v1 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0029: IGET  (r2v0 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                     call: com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.<init>(android.content.Context):void type: CONSTRUCTOR)
                                      (wrap: java.lang.String : 0x0037: INVOKE  (r2v4 java.lang.String) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity : 0x0032: IGET  (r2v3 com.xiaomi.smarthome.SmartHomeMainActivity) = 
                                      (wrap: com.xiaomi.smarthome.SmartHomeMainActivity$52 : 0x0030: IGET  (r2v2 com.xiaomi.smarthome.SmartHomeMainActivity$52) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.b com.xiaomi.smarthome.SmartHomeMainActivity$52)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.a com.xiaomi.smarthome.SmartHomeMainActivity)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_title_force_update int)
                                     com.xiaomi.smarthome.SmartHomeMainActivity.getString(int):java.lang.String type: VIRTUAL)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.b(java.lang.CharSequence):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL)
                                      (wrap: ? : ?: SGET   com.xiaomi.smarthome.R.string.dialog_right_update int)
                                      (wrap: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ : 0x0046: CONSTRUCTOR  (r4v0 com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                      (wrap: com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo : 0x0042: IGET  (r3v1 com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.a com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo)
                                     call: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ.<init>(com.xiaomi.smarthome.SmartHomeMainActivity$52$1, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo):void type: CONSTRUCTOR)
                                     com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.Builder.a(int, android.content.DialogInterface$OnClickListener):com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder type: VIRTUAL in method: com.xiaomi.smarthome.SmartHomeMainActivity.52.1.run():void, dex: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 121 more
                                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0046: CONSTRUCTOR  (r4v0 com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                      (wrap: com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo : 0x0042: IGET  (r3v1 com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo) = 
                                      (r5v0 'this' com.xiaomi.smarthome.SmartHomeMainActivity$52$1 A[THIS])
                                     com.xiaomi.smarthome.SmartHomeMainActivity.52.1.a com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo)
                                     call: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ.<init>(com.xiaomi.smarthome.SmartHomeMainActivity$52$1, com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.SmartHomeMainActivity.52.1.run():void, dex: classes7.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 127 more
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                                    	... 133 more
                                    */
                                /*
                                    this = this;
                                    com.xiaomi.smarthome.SmartHomeMainActivity$52 r0 = com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.this
                                    com.xiaomi.smarthome.SmartHomeMainActivity r0 = com.xiaomi.smarthome.SmartHomeMainActivity.this
                                    android.app.Dialog r0 = r0.f1499a
                                    if (r0 == 0) goto L_0x0023
                                    com.xiaomi.smarthome.SmartHomeMainActivity$52 r0 = com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.this
                                    com.xiaomi.smarthome.SmartHomeMainActivity r0 = com.xiaomi.smarthome.SmartHomeMainActivity.this
                                    android.app.Dialog r0 = r0.f1499a
                                    boolean r0 = r0.isShowing()
                                    if (r0 == 0) goto L_0x0023
                                    com.xiaomi.smarthome.SmartHomeMainActivity$52 r0 = com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.this
                                    com.xiaomi.smarthome.SmartHomeMainActivity r0 = com.xiaomi.smarthome.SmartHomeMainActivity.this
                                    android.app.Dialog r0 = r0.f1499a
                                    r0.dismiss()
                                L_0x0023:
                                    com.xiaomi.smarthome.SmartHomeMainActivity$52 r0 = com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.this
                                    com.xiaomi.smarthome.SmartHomeMainActivity r0 = com.xiaomi.smarthome.SmartHomeMainActivity.this
                                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r1 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
                                    com.xiaomi.smarthome.SmartHomeMainActivity$52 r2 = com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.this
                                    com.xiaomi.smarthome.SmartHomeMainActivity r2 = com.xiaomi.smarthome.SmartHomeMainActivity.this
                                    r1.<init>(r2)
                                    com.xiaomi.smarthome.SmartHomeMainActivity$52 r2 = com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.this
                                    com.xiaomi.smarthome.SmartHomeMainActivity r2 = com.xiaomi.smarthome.SmartHomeMainActivity.this
                                    r3 = 2131494775(0x7f0c0777, float:1.8613068E38)
                                    java.lang.String r2 = r2.getString(r3)
                                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r1 = r1.b((java.lang.CharSequence) r2)
                                    r2 = 2131494772(0x7f0c0774, float:1.8613062E38)
                                    com.xiaomi.smarthome.config.GreyUpgradeConfigManager$GreyUpgradeInfo r3 = r3
                                    com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ r4 = new com.xiaomi.smarthome.-$$Lambda$SmartHomeMainActivity$52$1$Oeroy3m6skK__VGgcH4ObEVr1eQ
                                    r4.<init>(r5, r3)
                                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r1 = r1.a((int) r2, (android.content.DialogInterface.OnClickListener) r4)
                                    r2 = 2131493823(0x7f0c03bf, float:1.8611137E38)
                                    r3 = 0
                                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r1 = r1.b((int) r2, (android.content.DialogInterface.OnClickListener) r3)
                                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r1 = r1.d()
                                    android.app.Dialog unused = r0.f1499a = r1
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.SmartHomeMainActivity.AnonymousClass52.AnonymousClass1.run():void");
                            }

                            /* access modifiers changed from: private */
                            public /* synthetic */ void a(GreyUpgradeConfigManager.GreyUpgradeInfo greyUpgradeInfo, DialogInterface dialogInterface, int i) {
                                try {
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.setData(Uri.parse(greyUpgradeInfo.e()));
                                    SmartHomeMainActivity.this.startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                public void onFailure(Error error) {
                    if (SmartHomeMainActivity.this.isValid()) {
                    }
                }
            });
        }
    }

    private void c(String str) {
        if (this.f1499a != null && this.f1499a.isShowing()) {
            this.f1499a.dismiss();
        }
        this.f1499a = new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.open_device_account_erro)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SmartHomeMainActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(final String str, DialogInterface dialogInterface, int i2) {
        LoginManager.a().logout(new AsyncCallback<Void, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Void voidR) {
                LoginApi.a().a(SmartHomeMainActivity.this.q, str, (LoginApi.LoginCallback) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2, final int i2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && SHApplication.getStateNotifier().a() != 3) {
            if (SHApplication.getStateNotifier().a() != 4) {
                if (i2 < 3) {
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            SmartHomeMainActivity.this.a(str, str2, i2 + 1);
                        }
                    }, 1000);
                }
            } else if (!TextUtils.equals(str2, CoreApi.a().s())) {
                c(str2);
            } else {
                final AnonymousClass55 r5 = new AnonymousClass1DialogRunnable() {
                    public void run() {
                        if (SmartHomeMainActivity.this.isValid()) {
                            if (SmartHomeDeviceManager.a().b(str) == null) {
                                ToastUtil.a((int) R.string.qr_cannot_find_device);
                                return;
                            }
                            Intent intent = new Intent(SmartHomeMainActivity.this, BleGatewayActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString(BleGatewayListActivity.KEY_GATEWAY_DID, str);
                            intent.putExtras(bundle);
                            SmartHomeMainActivity.this.startActivity(intent);
                            if (this.f13441a != null) {
                                this.f13441a.dismiss();
                            }
                        }
                    }
                };
                if (this.f1499a != null && this.f1499a.isShowing()) {
                    this.f1499a.dismiss();
                }
                if (!SmartHomeDeviceManager.a().u() || SmartHomeDeviceManager.a().d().size() <= 0) {
                    LogUtil.a("SmartHomeMainActivity", "ble gateway page:show loading");
                    this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (SmartHomeMainActivity.this.isValid()) {
                                XQProgressDialog a2 = XQProgressDialog.a(SmartHomeMainActivity.this, "", SmartHomeMainActivity.this.getString(R.string.loading), true, true, new DialogInterface.OnCancelListener() {
                                    public void onCancel(DialogInterface dialogInterface) {
                                        SmartHomeMainActivity.this.mHandler.removeCallbacks(r5);
                                    }
                                });
                                Dialog unused = SmartHomeMainActivity.this.f1499a = a2;
                                r5.f13441a = a2;
                                SmartHomeMainActivity.this.mHandler.postDelayed(r5, com.xiaomi.smarthome.download.Constants.x);
                            }
                        }
                    }, 500);
                    return;
                }
                LogUtil.a("SmartHomeMainActivity", "jump to ble gateway page directly");
                r5.run();
            }
        }
    }

    private void p() {
        this.mHandler.post(new Runnable() {
            public final void run() {
                SmartHomeMainActivity.this.D();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void D() {
        if (!CoreApi.a().q()) {
            LoginApi.a().a((Context) this, 1, (LoginApi.LoginCallback) null);
        } else if (this.w != null && this.p != null) {
            RecommendSceneManager.a().c(true);
            if (f != null) {
                for (int i2 = 0; i2 < f.length; i2++) {
                    if (TextUtils.equals(SceneTabFragment.class.getSimpleName(), f[i2])) {
                        this.p.setCurrentItem(i2);
                        this.w.setCurrentItem(i2);
                        return;
                    }
                }
            }
        }
    }

    private void q() {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (!CoreApi.a().q()) {
                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                } else if (SmartHomeMainActivity.this.w != null && SmartHomeMainActivity.this.p != null) {
                    RecommendSceneManager.a().b(true);
                    if (SmartHomeMainActivity.f != null) {
                        for (int i = 0; i < SmartHomeMainActivity.f.length; i++) {
                            if (TextUtils.equals(SceneTabFragment.class.getSimpleName(), SmartHomeMainActivity.f[i])) {
                                SmartHomeMainActivity.this.p.setCurrentItem(i);
                                SmartHomeMainActivity.this.w.setCurrentItem(i);
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    private void r() {
        CoreApi.a().a(StatType.EVENT, "force_touch_item_click", "page_add_device", (String) null, false);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!CoreApi.a().q()) {
                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                } else if (PreferenceUtils.a("find_device_tips", true)) {
                    PreferenceUtils.b("find_device_tips", false);
                    SmartHomeMainActivity.this.startActivityForResult(new Intent(SmartHomeMainActivity.this.getContext(), DialogTipsActivity.class), 5);
                } else {
                    SmartHomeMainActivity.this.startActivityForResult(new Intent(SmartHomeMainActivity.this, ChooseConnectDevice.class), 3);
                }
            }
        }, 1000);
    }

    private void s() {
        if (CoreApi.a().D()) {
            CoreApi.a().a(StatType.EVENT, "force_touch_item_click", "page_my_order_reject", (String) null, false);
            ToastUtil.a((int) R.string.not_support_my_order);
            return;
        }
        CoreApi.a().a(StatType.EVENT, "force_touch_item_click", "page_my_order", (String) null, false);
        final AnonymousClass59 r0 = new AnonymousClass2DialogRunnable() {
            public void run() {
                if (SmartHomeMainActivity.this.E != null) {
                    SmartHomeMainActivity.this.E.a("https://home.mi.com/shop/orderlist");
                }
                if (this.f13463a != null) {
                    this.f13463a.dismiss();
                }
            }
        };
        if (this.f1499a != null && this.f1499a.isShowing()) {
            this.f1499a.dismiss();
        }
        XQProgressDialog a2 = XQProgressDialog.a(this, "", getString(R.string.opening_my_order_page_tips), true, true, new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                SmartHomeMainActivity.this.mHandler.removeCallbacks(r0);
            }
        });
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!CoreApi.a().q()) {
                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                }
            }
        }, 100);
        this.f1499a = a2;
        r0.f13463a = a2;
        this.mHandler.postDelayed(r0, 5000);
    }

    private void t() {
        CoreApi.a().a(StatType.EVENT, "force_touch_item_click", "page_message_center", (String) null, false);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!CoreApi.a().q()) {
                    LoginApi.a().a((Context) SmartHomeMainActivity.this, 1, (LoginApi.LoginCallback) null);
                    return;
                }
                SmartHomeMainActivity.this.startActivity(new Intent(SmartHomeMainActivity.this, MessageCenterActivity.class));
            }
        }, 1000);
    }

    private void a(Activity activity) {
        if (this.w != null) {
            this.currentOnResumeTime = (int) (System.currentTimeMillis() / 1000);
            onPageChanged(-1, this.w.getCurrentItem());
        }
    }

    private void b(Activity activity) {
        ShopFragment shopPage;
        if (this.w != null) {
            int currentItem = this.w.getCurrentItem();
            if (this.p != null) {
                int lastTabIndex = this.p.getLastTabIndex();
                if (!CoreApi.a().D() && lastTabIndex == 1 && (shopPage = getShopPage()) != null) {
                    shopPage.c();
                }
            }
            this.lastOnResumeTime = this.currentOnResumeTime;
            onPageChanged(currentItem, -1);
        }
    }

    /* access modifiers changed from: package-private */
    public String getClientAllPageName() {
        return DeviceMainPage.class.getName();
    }

    /* access modifiers changed from: package-private */
    public String getFragmentName(int i2) {
        if (TextUtils.equals(DeviceMainPage.class.getSimpleName(), f[i2])) {
            return getClientAllPageName();
        }
        if (TextUtils.equals(ShopGlobalFragment.class.getSimpleName(), f[i2])) {
            return ShopGlobalFragment.class.getName();
        }
        if (TextUtils.equals(SmarthomeBBSFragment.class.getSimpleName(), f[i2])) {
            return SmarthomeBBSFragment.class.getName();
        }
        if (TextUtils.equals(ShopFragment.class.getSimpleName(), f[i2])) {
            return ShopFragment.class.getName();
        }
        if (TextUtils.equals(SceneTabFragment.class.getSimpleName(), f[i2])) {
            return SceneTabFragment.class.getName();
        }
        return TextUtils.equals(SettingMainPageV2.class.getSimpleName(), f[i2]) ? SettingMainPageV2.class.getName() : "";
    }

    /* access modifiers changed from: package-private */
    public BaseFragmentInterface getFragment(int i2) {
        if (TextUtils.equals(DeviceMainPage.class.getSimpleName(), f[i2])) {
            return getClientAllPage();
        }
        if (TextUtils.equals(ShopGlobalFragment.class.getSimpleName(), f[i2])) {
            return x();
        }
        if (TextUtils.equals(SmarthomeBBSFragment.class.getSimpleName(), f[i2])) {
            return y();
        }
        if (TextUtils.equals(ShopFragment.class.getSimpleName(), f[i2])) {
            return getShopPage();
        }
        if (TextUtils.equals(SceneTabFragment.class.getSimpleName(), f[i2])) {
            return getSceneTabPage();
        }
        if (TextUtils.equals(SettingMainPageV2.class.getSimpleName(), f[i2])) {
            return getSettingMainPage();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void onPageChanged(int i2, final int i3) {
        BaseFragmentInterface fragment;
        final int i4 = this.R;
        if (i4 >= 0) {
            try {
                BaseFragmentInterface fragment2 = getFragment(i4);
                if (fragment2 != null && (fragment2 instanceof PagerListener)) {
                    ((PagerListener) fragment2).a(false);
                    Log.d("SmartHomeMainActivity", "onPagePaused:" + fragment2.getClass().getName());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.R = i3;
        if (i3 >= 0 && (fragment = getFragment(i3)) != null) {
            if (fragment instanceof ShopFragment) {
                ShopNewPointManager.a().e();
            }
            if (fragment instanceof PagerListener) {
                ((PagerListener) fragment).a(true);
            }
            fragment.refreshTitleBar();
            Log.d("SmartHomeMainActivity", "onPageResume:" + fragment.getClass().getName());
        }
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                if (i4 >= 0) {
                    AnonymousClass1 r0 = new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            String fragmentName = SmartHomeMainActivity.this.getFragmentName(i4);
                            MiStatInterface.c();
                            MobclickAgent.b(fragmentName);
                            CoreApi.a().a(fragmentName, SmartHomeMainActivity.this.getSingleReferer(), SmartHomeMainActivity.this.lastOnResumeTime);
                            CoreApi.a().T();
                        }
                    };
                    SmartHomeMainActivity.this.T.add(r0);
                    CoreApi.a().a(SmartHomeMainActivity.this.getContext(), (CoreApi.IsCoreReadyCallback) r0);
                }
                if (i3 >= 0) {
                    AnonymousClass2 r02 = new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            String fragmentName = SmartHomeMainActivity.this.getFragmentName(i3);
                            MiStatInterface.a((Activity) SmartHomeMainActivity.this, fragmentName);
                            MobclickAgent.a(fragmentName);
                            CoreApi.a().a(fragmentName, SmartHomeMainActivity.this.getSingleReferer());
                            CoreApi.a().T();
                        }
                    };
                    SmartHomeMainActivity.this.T.add(r02);
                    CoreApi.a().a(SmartHomeMainActivity.this.getContext(), (CoreApi.IsCoreReadyCallback) r02);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void u() {
        AnonymousClass64 r0 = new Runnable() {
            public void run() {
                if (SmartHomeMainActivity.this.isValid()) {
                    try {
                        SmartHomeMainActivity.this.D.remove(this);
                        MessageCenter.a().a(SmartHomeMainActivity.this);
                        MessageCenter.a().b(SmartHomeMainActivity.this);
                        MessageCenter.a().g();
                        MessageCenter a2 = MessageCenter.a();
                        Context appContext = SHApplication.getAppContext();
                        a2.a(PreferenceUtils.b(appContext, ProfileRedPointManager.d + CoreApi.a().s(), System.currentTimeMillis()), 2);
                        MessageCenter a3 = MessageCenter.a();
                        Context appContext2 = SHApplication.getAppContext();
                        a3.a(PreferenceUtils.b(appContext2, ProfileRedPointManager.e + CoreApi.a().s(), System.currentTimeMillis()), 1);
                        ShopNewPointManager.a().f();
                        MessageCenter.a().i();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.D.put(r0, r0);
        SHApplication.getGlobalWorkerHandler().postDelayed(r0, 1000);
    }

    /* access modifiers changed from: private */
    public void v() {
        AnonymousClass65 r0 = new Runnable() {
            public void run() {
                try {
                    ConsumableDataManager.a().a((Context) SmartHomeMainActivity.this);
                } catch (Exception unused) {
                }
            }
        };
        this.D.put(r0, r0);
        SHApplication.getGlobalWorkerHandler().postDelayed(r0, 1000);
    }

    public void onResume() {
        super.onResume();
        DevicePushBindManager.instance.onResume();
        if (!this.H) {
            w();
            a((Activity) this);
            AnonymousClass66 r0 = new Runnable() {
                public void run() {
                    if (SmartHomeMainActivity.this.isValid()) {
                        SmartHomeMainActivity.this.D.remove(this);
                        WIFIScanHomelogReceiver.initWifiScanMonitors();
                        WifiUtil.f(SHApplication.getAppContext());
                        PluginAutoDownloadTask.a().b();
                    }
                }
            };
            this.D.put(r0, r0);
            SHApplication.getGlobalWorkerHandler().postDelayed(r0, 1000);
            mIsActivityResumed = true;
            this.z = false;
            SHApplication.getPushManager().a(PushType.DEVICE_CONNECTED, this.G);
            CoreApi.a().a((Context) this, (CoreApi.IsAccountReadyCallback) new CoreApi.IsAccountReadyCallback() {
                public void a(boolean z, String str) {
                    Miio.b("onAccountReady", "isMiLoggedIn" + z);
                    if (SHApplication.getStateNotifier().a() == 4) {
                        SmartHomeMainActivity.this.u();
                        SmartHomeMainActivity.this.v();
                        return;
                    }
                    AnonymousClass1 r3 = new Runnable() {
                        public void run() {
                            if (SmartHomeMainActivity.this.isValid()) {
                                SmartHomeMainActivity.this.C.remove(this);
                                MessageCenter.a().b(SmartHomeMainActivity.this);
                                DeviceBindStatis.a(SHApplication.getAppContext());
                            }
                        }
                    };
                    SmartHomeMainActivity.this.C.put(r3, r3);
                    SHApplication.getGlobalHandler().postDelayed(r3, 1000);
                }
            });
        }
    }

    public DeviceMainPage getClientAllPage() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return null;
        }
        for (Fragment next : fragments) {
            if (next instanceof DeviceMainPage) {
                return (DeviceMainPage) next;
            }
        }
        return null;
    }

    public SettingMainPageV2 getSettingMainPage() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return null;
        }
        for (Fragment next : fragments) {
            if (next instanceof SettingMainPageV2) {
                return (SettingMainPageV2) next;
            }
        }
        return null;
    }

    public ShopFragment getShopPage() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return null;
        }
        for (int size = fragments.size() - 1; size >= 0; size--) {
            Fragment fragment = fragments.get(size);
            if (fragment instanceof ShopFragment) {
                return (ShopFragment) fragment;
            }
        }
        return null;
    }

    public void start() {
        if (CoreApi.a().q() && !this.z) {
            this.z = true;
            gotoConnectionSelectPage();
        }
    }

    public void startWifiSetting(String str) {
        this.o = str;
        if (this.y != null) {
            gotoWifiSettingPage();
        } else {
            gotoWifiSettingFirstPage();
        }
    }

    public void startWifiSetting(ScanResult scanResult, String str) {
        this.o = str;
        this.y = scanResult;
        gotoWifiSettingPage();
    }

    public void gotoWifiSettingFirstPage() {
        Intent intent = new Intent();
        intent.putExtra("model", this.o);
        intent.setClass(this, WifiSettingNormal.class);
        startActivityForResult(intent, 2);
    }

    public void gotoConnectionSelectPage() {
        if (DeviceFinder.a().e()) {
            this.z = false;
            ToastUtil.a((int) R.string.smart_config_connecting);
            return;
        }
        startActivityForResult(new Intent(this, ChooseDeviceActivity.class), 3);
    }

    public void gotoShopPage() {
        this.w.setCurrentItem(1);
        this.p.setCurrentItem(1);
    }

    public void gotoDevicePage() {
        this.w.setCurrentItem(0);
        this.p.setCurrentItem(0);
    }

    /* access modifiers changed from: package-private */
    public void gotoWifiSettingPage() {
        Intent a2 = ConfigStage.a(this, this.y, DeviceFactory.a(this.y), (String) null, (String) null);
        if (a2 != null) {
            startActivityForResult(a2, 2);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (!this.H) {
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        LogUtil.a("SmartHomeMainActivity", "SmartHomeMainActivityonStop");
        if (!this.H) {
        }
    }

    public void finish() {
        super.finish();
        boolean booleanExtra = getIntent().getBooleanExtra(ApiConst.n, false);
        if (ApiConst.f16684a.equals(getIntent().getAction()) && !booleanExtra) {
            overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
        }
    }

    private void w() {
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("source", 0);
        if (intExtra == 4) {
            if (this.w != null) {
                this.w.setCurrentItem(1);
                this.p.setCurrentItem(1);
            }
            intent.putExtra("source", 0);
        } else if (intExtra == 10) {
            if (this.w != null && ENABLE_KUWAN && f != null) {
                int i2 = 0;
                while (true) {
                    if (i2 >= f.length) {
                        break;
                    } else if (TextUtils.equals(SceneTabFragment.class.getSimpleName(), f[i2])) {
                        this.p.setCurrentItem(i2);
                        this.w.setCurrentItem(i2);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            intent.putExtra("source", 0);
        }
    }

    public void onBackPressed() {
        if (this.p == null) {
            super.onBackPressed();
        } else if (this.r != CardStatus.CARD_ANIMATION) {
            try {
                Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(HomeEnvInfoFragment.class.getSimpleName());
                Fragment findFragmentByTag2 = getSupportFragmentManager().findFragmentByTag(HomeEnvInfoSettingFragment.class.getSimpleName());
                if (findFragmentByTag2 != null && !findFragmentByTag2.isDetached()) {
                    ((HomeEnvInfoSettingFragment) findFragmentByTag2).onBackPressed();
                } else if (findFragmentByTag == null || findFragmentByTag.isDetached()) {
                    int selectedTabIndex = this.p.getSelectedTabIndex();
                    if (selectedTabIndex == 0) {
                        DeviceMainPage clientAllPage = getClientAllPage();
                        if (clientAllPage != null) {
                            clientAllPage.onBackPressed();
                        }
                    } else if (this.x != null && this.x.a(selectedTabIndex) != null && (this.x.a(selectedTabIndex) instanceof SceneTabFragment)) {
                        SceneTabFragment sceneTabPage = getSceneTabPage();
                        if (sceneTabPage != null) {
                            sceneTabPage.onBackPressed();
                        }
                    } else if (this.x != null && this.x.a(selectedTabIndex) != null && (this.x.a(selectedTabIndex) instanceof SettingMainPageV2)) {
                        super.onBackPressed();
                    } else if (this.x == null || this.x.a(selectedTabIndex) == null || !(this.x.a(selectedTabIndex) instanceof SmarthomeBBSFragment)) {
                        super.onBackPressed();
                    } else {
                        SmarthomeBBSFragment y2 = y();
                        if (y2 != null) {
                            y2.onBackPressed();
                        }
                    }
                } else {
                    ((HomeEnvInfoFragment) findFragmentByTag).onBackPressed();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        if (CoreApi.a().D()) {
            c(context);
        } else {
            b(context);
        }
    }

    private void c(Context context) {
        if (CoreApi.a().D()) {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    if (SmartHomeMainActivity.this.isValid()) {
                        CommentInternationalHelper.a();
                        if (CommentInternationalHelper.b()) {
                            SmartHomeMainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (SmartHomeMainActivity.this.f1499a != null && SmartHomeMainActivity.this.f1499a.isShowing()) {
                                        SmartHomeMainActivity.this.f1499a.dismiss();
                                    }
                                    Dialog unused = SmartHomeMainActivity.this.f1499a = CommentInternationalHelper.a(SmartHomeMainActivity.this);
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d(Context context) {
        if (this.f1499a != null && this.f1499a.isShowing()) {
            this.f1499a.dismiss();
        }
        MLAlertDialog b2 = new MLAlertDialog.Builder(this).d(false).a((int) R.string.comment_for_mihome_title).b((int) R.string.do_it_later, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SmartHomeMainActivity.this.d("mihome_comment_refuse");
                dialogInterface.dismiss();
            }
        }).a((int) R.string.do_it_right_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean z = false;
                try {
                    SmartHomeMainActivity.this.startActivity(Intent.parseUri("market://comments?id=75542", 0));
                    z = true;
                } catch (Exception unused) {
                }
                if (z) {
                    SmartHomeMainActivity.this.d("mihome_comment_accept");
                }
                dialogInterface.dismiss();
            }
        }).b();
        this.f1499a = b2;
        TextView textView = new TextView(context);
        textView.setText(R.string.comment_for_mihome_detail);
        textView.setTextColor(context.getResources().getColor(R.color.class_Y));
        textView.setLineSpacing(0.0f, 1.0f);
        b2.setView(textView, DisplayUtils.a(20.0f), DisplayUtils.a(5.0f) * -1, DisplayUtils.a(20.0f), DisplayUtils.a(10.0f));
        d("mihome_comment_show");
        if (!isFinishing()) {
            b2.show();
        }
    }

    /* access modifiers changed from: private */
    public void d(final String str) {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                CoreApi.a().a(StatType.EVENT, str, Integer.toString(1), (String) null, false);
                MobclickAgent.a(SmartHomeMainActivity.this.getContext(), "mihome", str);
            }
        });
    }

    private BaseFragmentInterface x() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return null;
        }
        for (Fragment next : fragments) {
            if (next instanceof ShopGlobalFragment) {
                return (ShopGlobalFragment) next;
            }
        }
        return null;
    }

    private SmarthomeBBSFragment y() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return null;
        }
        for (Fragment next : fragments) {
            if (next instanceof SmarthomeBBSFragment) {
                return (SmarthomeBBSFragment) next;
            }
        }
        return null;
    }

    class MainAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        public MainAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            SmartHomeMainActivity.this.g();
        }

        public Fragment a(int i) {
            if (TextUtils.equals(DeviceMainPage.class.getSimpleName(), SmartHomeMainActivity.f[i % SmartHomeMainActivity.f.length])) {
                return new DeviceMainPage();
            }
            if (TextUtils.equals(ShopGlobalFragment.class.getSimpleName(), SmartHomeMainActivity.f[i % SmartHomeMainActivity.f.length])) {
                return new ShopGlobalFragment();
            }
            if (TextUtils.equals(SmarthomeBBSFragment.class.getSimpleName(), SmartHomeMainActivity.f[i % SmartHomeMainActivity.f.length])) {
                return new SmarthomeBBSFragment();
            }
            if (TextUtils.equals(ShopFragment.class.getSimpleName(), SmartHomeMainActivity.f[i % SmartHomeMainActivity.f.length])) {
                return new ShopFragment();
            }
            if (TextUtils.equals(SceneTabFragment.class.getSimpleName(), SmartHomeMainActivity.f[i % SmartHomeMainActivity.f.length])) {
                return new SceneTabFragment();
            }
            if (TextUtils.equals(SettingMainPageV2.class.getSimpleName(), SmartHomeMainActivity.f[i % SmartHomeMainActivity.f.length])) {
                return new SettingMainPageV2();
            }
            return null;
        }

        public CharSequence b(int i) {
            return SmartHomeMainActivity.this.getResources().getString(SmartHomeMainActivity.c[i % SmartHomeMainActivity.c.length].intValue());
        }

        public int a() {
            return SmartHomeMainActivity.c.length;
        }

        public int c(int i) {
            return SmartHomeMainActivity.d[i].intValue();
        }

        public String d(int i) {
            return SmartHomeMainActivity.e[i];
        }
    }

    /* access modifiers changed from: protected */
    public int getPageNavBarColor() {
        return super.getPageNavBarColor();
    }

    public void enterHomeEnvInfoFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(HomeEnvInfoFragment.class.getSimpleName());
        if (findFragmentByTag == null) {
            findFragmentByTag = new HomeEnvInfoFragment();
        }
        beginTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        beginTransaction.replace(this.w.getId(), findFragmentByTag, HomeEnvInfoFragment.class.getSimpleName());
        beginTransaction.addToBackStack((String) null);
        beginTransaction.commitAllowingStateLoss();
        if (this.p != null) {
            this.p.setVisibility(8);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
        layoutParams.bottomMargin = 0;
        this.w.setLayoutParams(layoutParams);
    }

    public void enterHomeEnvInfoSettingFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(HomeEnvInfoSettingFragment.class.getSimpleName());
        if (findFragmentByTag == null) {
            findFragmentByTag = new HomeEnvInfoSettingFragment();
        }
        beginTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        beginTransaction.replace(this.w.getId(), findFragmentByTag, HomeEnvInfoSettingFragment.class.getSimpleName());
        beginTransaction.addToBackStack((String) null);
        beginTransaction.commitAllowingStateLoss();
    }

    public void exitHomeEnvInfoFragment() {
        try {
            getSupportFragmentManager().popBackStackImmediate();
            if (this.p != null) {
                this.p.setVisibility(0);
            }
            DeviceMainPage clientAllPage = getClientAllPage();
            if (clientAllPage != null && clientAllPage.f()) {
                clientAllPage.e();
            }
            if (clientAllPage != null) {
                clientAllPage.g();
                this.mHandler.postDelayed(new Runnable(clientAllPage) {
                    private final /* synthetic */ DeviceMainPage f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        SmartHomeMainActivity.this.a(this.f$1);
                    }
                }, 100);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
            layoutParams.bottomMargin = DisplayUtils.a(55.0f);
            this.w.setLayoutParams(layoutParams);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DeviceMainPage deviceMainPage) {
        if (isValid()) {
            deviceMainPage.d();
        }
    }

    public void exitTopWidgetSettingFragment() {
        try {
            getSupportFragmentManager().popBackStackImmediate();
            if (this.p != null) {
                this.p.setVisibility(0);
            }
            DeviceMainPage clientAllPage = getClientAllPage();
            if (clientAllPage != null && clientAllPage.f()) {
                clientAllPage.e();
            }
            if (clientAllPage != null) {
                clientAllPage.g();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static class LocationCallback extends SHLocationManager.LocationCallback {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<SmartHomeMainActivity> f13527a;

        public LocationCallback(SmartHomeMainActivity smartHomeMainActivity) {
            this.f13527a = new WeakReference<>(smartHomeMainActivity);
        }

        public void onSucceed(String str, Location location) {
            Bundle extras;
            Address address;
            Context context;
            if (location != null && (extras = location.getExtras()) != null) {
                try {
                    address = (Address) extras.getParcelable("address");
                } catch (Exception unused) {
                    address = null;
                }
                if (address != null) {
                    ServerBean b = ServerCompact.b(SHApplication.getAppContext(), address.getCountryCode());
                    ServerBean F = CoreApi.a().F();
                    if (b != null && F != null && (context = (Context) this.f13527a.get()) != null && ServerCompact.c(F)) {
                        if ((ServerCompact.f(b) || ServerCompact.k(b) || ServerCompact.i(b) || ServerCompact.l(b)) && !ServerHelper.a()) {
                            ServerHelper.a(context, b, new ServerHelper.InternationalCallback() {
                                public void a() {
                                }

                                public void b() {
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private static class GCTask implements Runnable {
        private GCTask() {
        }

        public void run() {
            Runtime.getRuntime().gc();
        }
    }

    private void z() {
        if (Build.VERSION.SDK_INT >= 21) {
            A();
        }
    }

    private void A() {
        LongSparseArray longSparseArray;
        Method declaredMethod;
        LongSparseArray[] longSparseArrayArr;
        try {
            Class<?> cls = Class.forName("android.content.res.ResourcesImpl");
            if (cls != null) {
                Field declaredField = cls.getDeclaredField("sPreloadedDrawables");
                declaredField.setAccessible(true);
                if (declaredField.isAccessible() && (longSparseArrayArr = (LongSparseArray[]) declaredField.get((Object) null)) != null) {
                    for (LongSparseArray longSparseArray2 : longSparseArrayArr) {
                        Method declaredMethod2 = LongSparseArray.class.getDeclaredMethod("clear", new Class[0]);
                        if (declaredMethod2 != null) {
                            declaredMethod2.setAccessible(true);
                            declaredMethod2.invoke(longSparseArray2, new Object[0]);
                        }
                    }
                }
                Field declaredField2 = cls.getDeclaredField("sPreloadedColorDrawables");
                declaredField2.setAccessible(true);
                if (declaredField2.isAccessible() && (longSparseArray = (LongSparseArray) declaredField2.get((Object) null)) != null && (declaredMethod = LongSparseArray.class.getDeclaredMethod("clear", new Class[0])) != null) {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(longSparseArray, new Object[0]);
                }
            }
        } catch (Exception unused) {
        }
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.S) {
            this.S = false;
            runOnUiThread(new Runnable() {
                public void run() {
                    SmartHomeMainActivity.this.B();
                }
            });
            if (currentTimeMillis - SHApplication.sOnCreateTime > com.xiaomi.smarthome.download.Constants.x) {
                MyLogHelper.a(currentTimeMillis - SHApplication.sOnCreateTime);
            }
        }
    }

    /* access modifiers changed from: private */
    public void B() {
        if (isValid()) {
            ServerTimerManager.a((Context) this).a();
            DeviceFinder.a();
            ShopNewPointManager.a().a((ShopNewPointManager.IShowNewPointListener) this);
            if (this.p != null) {
                this.p.setVisibility(0);
                this.p.setViewPager(this.w);
                this.p.setOnTabReselectedListener(new TabPageIndicator.OnTabReselectedListener() {
                    public void a(int i) {
                        CoreApi.a().D();
                    }
                });
                onPageChanged(-1, 0);
            }
            GlobalNavButtonManager.a().b();
            AppUpdateManger.a().n();
            if (PermissionRequestActivity.Companion.b(this)) {
                b(getIntent());
            } else if (PreferenceUtils.a("check_permission", true)) {
                if (!PermissionRequestActivity.Companion.a((Activity) this)) {
                    b(getIntent());
                }
                PreferenceUtils.b("check_permission", false);
            } else {
                b(getIntent());
            }
            MiStat.setCustomPrivacyState(true);
            j();
            LocalRouterDeviceInfo.a().b();
            ServerTimerManager.a((Context) this).a();
            HomeKeyManager.a().a(false);
            ApDeviceManager.a().d();
            a(this.mPendingIntent);
            if (this.E != null) {
                this.E.a();
            }
        }
    }

    private void C() {
        try {
            CoreApi.a().a(this.T);
            this.T.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
