package com.xiaomi.smarthome.frame.plugin.runtime.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.mi.global.shop.util.PushRouteUtil;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.bluetooth.BleUpgrader;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.FaceManagerCallback;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.debug.PluginErrorInfoActivity;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.frame.plugin.runtime.util.OfflineViewDelegate;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.sdk.R;
import com.xiaomi.smarthome.stat.PluginStatReporter;
import com.xiaomi.youpin.login.other.common.TitleBarUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginHostActivity extends BaseActivity implements IXmPluginHostActivity {
    public static final int ACTIVITY_REQUEST_VERIFY_PINCODE = 9999;
    public static final String EXTRA_DEVICE = "extra_device";
    public static final String EXTRA_DEVICE_DID = "extra_device_did";
    public static final String EXTRA_DEVICE_MODEL = "extra_device_model";
    public static final String EXTRA_RECYCLE_PLUGIN = "extra_recycle_plugin";
    private static final String FORCE_FSG_NAV_BAR = "force_fsg_nav_bar";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final String FRAGMENTS_TAGAPP = "android:fragments";
    public static final String JUMP_FORM_PLUGIN_HOST = "jump_form_plugin_host";
    protected static final int MSG_EXIT_PROCESS = 1;
    private static final int PROCESS_KILL_DELAY = 30000;
    public static final String TAG = "PluginHostActivity";
    private static ArrayList<WeakReference<PluginHostActivity>> mPluginHostActivityRefStack = new ArrayList<>();
    private static Handler sHandle = null;
    public boolean backPressEnable = true;
    private ActivityInfo mActivityInfo;
    private String mClass;
    DeviceStat mDevice;
    boolean mEnableVerifyPincode = false;
    boolean mIsReady = true;
    boolean mIsSupportAd = false;
    boolean mIsVerifyed = false;
    private boolean mLastNavigationBarStatus = false;
    private XmPluginPackage mLoadedInfo;
    String mModel;
    private OfflineViewDelegate mOfflineViewDelegate;
    List<Runnable> mOnReadyRunableList = new ArrayList();
    private long mOnResumeTimestamp;
    private String mPackageName;
    private String mPageName;
    Resources.Theme mTheme;
    private XmPluginBaseActivity mXmPluginActivity;

    public final FragmentActivity activity() {
        return this;
    }

    protected static Handler getHandler() {
        if (sHandle == null) {
            sHandle = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message message) {
                    if (message.what == 1) {
                        System.exit(0);
                    }
                }
            };
        }
        return sHandle;
    }

    private static synchronized void pushPluginHostActivity(PluginHostActivity pluginHostActivity) {
        synchronized (PluginHostActivity.class) {
            if (pluginHostActivity != null) {
                getHandler().removeMessages(1);
                mPluginHostActivityRefStack.add(new WeakReference(pluginHostActivity));
            }
        }
    }

    public static synchronized PluginHostActivity getTopPluginHostActivity() {
        synchronized (PluginHostActivity.class) {
            if (mPluginHostActivityRefStack.size() <= 0) {
                return null;
            }
            PluginHostActivity pluginHostActivity = (PluginHostActivity) mPluginHostActivityRefStack.get(mPluginHostActivityRefStack.size() - 1).get();
            return pluginHostActivity;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void popPluginHostActivity(com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity r5) {
        /*
            java.lang.Class<com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity> r0 = com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity.class
            monitor-enter(r0)
            if (r5 != 0) goto L_0x0007
            monitor-exit(r0)
            return
        L_0x0007:
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity>> r1 = mPluginHostActivityRefStack     // Catch:{ all -> 0x0049 }
            int r1 = r1.size()     // Catch:{ all -> 0x0049 }
            r2 = 1
            int r1 = r1 - r2
        L_0x000f:
            if (r1 < 0) goto L_0x0028
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity>> r3 = mPluginHostActivityRefStack     // Catch:{ all -> 0x0049 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x0049 }
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3     // Catch:{ all -> 0x0049 }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x0049 }
            if (r3 != r5) goto L_0x0025
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity>> r5 = mPluginHostActivityRefStack     // Catch:{ all -> 0x0049 }
            r5.remove(r1)     // Catch:{ all -> 0x0049 }
            goto L_0x0028
        L_0x0025:
            int r1 = r1 + -1
            goto L_0x000f
        L_0x0028:
            java.util.ArrayList<java.lang.ref.WeakReference<com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity>> r5 = mPluginHostActivityRefStack     // Catch:{ all -> 0x0049 }
            int r5 = r5.size()     // Catch:{ all -> 0x0049 }
            if (r5 != 0) goto L_0x0047
            com.xiaomi.smarthome.frame.FrameManager r5 = com.xiaomi.smarthome.frame.FrameManager.b()     // Catch:{ all -> 0x0049 }
            android.content.Context r5 = r5.c()     // Catch:{ all -> 0x0049 }
            boolean r5 = com.xiaomi.smarthome.frame.process.ProcessUtil.b(r5)     // Catch:{ all -> 0x0049 }
            if (r5 != 0) goto L_0x0047
            android.os.Handler r5 = getHandler()     // Catch:{ all -> 0x0049 }
            r3 = 30000(0x7530, double:1.4822E-319)
            r5.sendEmptyMessageDelayed(r2, r3)     // Catch:{ all -> 0x0049 }
        L_0x0047:
            monitor-exit(r0)
            return
        L_0x0049:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity.popPluginHostActivity(com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity):void");
    }

    public static synchronized void resetPluginHostActivity() {
        synchronized (PluginHostActivity.class) {
            mPluginHostActivityRefStack.clear();
        }
    }

    public XmPluginPackage getXmPluginPackage() {
        return this.mLoadedInfo;
    }

    public Resources getResources() {
        if (this.mLoadedInfo == null) {
            return super.getResources();
        }
        return this.mLoadedInfo.resources;
    }

    public AssetManager getAssets() {
        if (this.mLoadedInfo == null) {
            return super.getAssets();
        }
        return this.mLoadedInfo.assetManager;
    }

    public Resources.Theme getTheme() {
        if (this.mTheme == null) {
            return super.getTheme();
        }
        return this.mTheme;
    }

    public ClassLoader getClassLoader() {
        if (this.mLoadedInfo == null) {
            return super.getClassLoader();
        }
        return this.mLoadedInfo.classLoader;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable final Bundle bundle) {
        Log.d("PluginHostActivity", "onCreate");
        if (bundle != null) {
            bundle.remove(FRAGMENTS_TAG);
            bundle.remove(FRAGMENTS_TAGAPP);
        }
        super.onCreate(bundle);
        DarkModeCompat.a((Activity) this);
        TitleBarUtil.a(getWindow());
        if (!CoreApi.a().l()) {
            Log.e("PluginHostActivity", "core process not ready,initial core process");
            this.mIsReady = false;
            this.mOnReadyRunableList.clear();
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doCreate(bundle);
                }
            });
            CoreApi.a().a(getApplicationContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    CoreApi.a().a(PluginHostActivity.this.getApplicationContext(), (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                        public void onPluginCacheReady() {
                            if (CoreApi.a().l()) {
                                PluginHostActivity.this.mIsReady = true;
                                for (int i = 0; i < PluginHostActivity.this.mOnReadyRunableList.size(); i++) {
                                    PluginHostActivity.this.mOnReadyRunableList.get(i).run();
                                }
                                PluginHostActivity.this.mOnReadyRunableList.clear();
                                return;
                            }
                            Log.e("PluginHostActivity", "core process not ready");
                            PluginHostActivity.this.mOnReadyRunableList.clear();
                            PluginHostActivity.this.finish();
                        }
                    });
                }
            });
            return;
        }
        this.mIsReady = true;
        doCreate(bundle);
    }

    /* access modifiers changed from: package-private */
    public void doCreate(Bundle bundle) {
        Intent intent = getIntent();
        String str = "";
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            for (String next : categories) {
                if (next.startsWith("did:")) {
                    str = next.substring("did:".length());
                } else if (next.startsWith("model:")) {
                    this.mModel = next.substring("model:".length());
                }
            }
        }
        if (bundle != null) {
            if (bundle.getBoolean("extra_recycle_plugin", false)) {
                finish();
                return;
            }
            Log.d("PluginHostActivity", "has savedInstanceState");
            if (bundle.containsKey("extra_device_did")) {
                str = bundle.getString("extra_device_did");
            }
            Log.d("PluginHostActivity", "savedInstanceState did:" + str);
            if (bundle.containsKey("extra_device")) {
                this.mDevice = (DeviceStat) bundle.getParcelable("extra_device");
            }
            Log.d("PluginHostActivity", "savedInstanceState device:" + this.mDevice);
            if (bundle.containsKey("extra_device_model")) {
                this.mModel = bundle.getString("extra_device_model");
            }
            Log.d("PluginHostActivity", "savedInstanceState model:" + this.mModel);
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(this.mModel)) {
            str = intent.getStringExtra("extra_device_did");
        }
        if (!TextUtils.isEmpty(str) && this.mDevice == null) {
            this.mDevice = PluginBridgeService.getCachedDeviceStat(str);
            if (this.mDevice == null) {
                this.mDevice = XmPluginHostApi.instance().getDeviceByDid(str);
            }
        }
        if (this.mDevice == null) {
            Log.e("PluginHostActivity", "device is null");
        }
        if (this.mDevice != null) {
            this.mModel = this.mDevice.model;
        }
        if (TextUtils.isEmpty(this.mModel)) {
            try {
                this.mModel = getIntent().getStringExtra("extra_device_model");
            } catch (Throwable th) {
                PluginErrorInfoActivity.showErrorInfo(this, (XmPluginPackage) null, th);
                finish();
                return;
            }
        }
        if (TextUtils.isEmpty(this.mModel)) {
            Log.e("PluginHostActivity", "model is null");
            finish();
            return;
        }
        this.mLoadedInfo = PluginRuntimeManager.getInstance().getXmPluginPackage(this.mModel);
        if (this.mLoadedInfo == null) {
            PluginRecord d = CoreApi.a().d(this.mModel);
            if (d == null || d.h() == null) {
                Log.e("PluginHostActivity", "not found PluginRecord");
                finish();
                return;
            }
            this.mLoadedInfo = PluginRuntimeManager.getInstance().loadApk(d.h());
        }
        if (this.mLoadedInfo == null) {
            Log.e("PluginHostActivity", "not found loadedinfo");
            setResult(0);
            finish();
            return;
        }
        intent.setExtrasClassLoader(this.mLoadedInfo.classLoader);
        this.mPackageName = intent.getStringExtra("extra_package");
        this.mClass = intent.getStringExtra("extra_class");
        getActivityInfo();
        handleActivityInfo();
        launchActivity(intent);
        pushPluginHostActivity(this);
        if (intent != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Log.d("DebugTime", "LaunchPluginActivityCreateTime:" + (currentTimeMillis - intent.getLongExtra("__StartTime__", currentTimeMillis)));
        }
        this.mOfflineViewDelegate = new OfflineViewDelegate(this, this.mDevice);
        this.mOfflineViewDelegate.onCreate();
        if (!intent.getBooleanExtra(JUMP_FORM_PLUGIN_HOST, false)) {
            this.mOfflineViewDelegate.showOfflineIfNeeded();
            Window window = getWindow();
            if (window != null && PluginBridgeService.msgType == 1) {
                View findViewById = window.getDecorView().findViewById(16908290);
                if (findViewById instanceof FrameLayout) {
                    this.mOfflineViewDelegate.showWeakRssiIfNeed((FrameLayout) findViewById);
                }
            }
        }
        if (this.mDevice != null && this.mEnableVerifyPincode) {
            if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
                if (TextUtils.isEmpty(LockSecurePinUtil.getPropLtmk(this.mDevice.mac)) || TextUtils.isEmpty(LockSecurePinUtil.getPropPincode(this.mDevice.mac)) || LockSecurePinUtil.getPropShowPincode(this.mDevice.mac)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("extra_device_did", this.mDevice.did);
                    FrameManager.b().k().openVerfyPincode(this, bundle2, 9999);
                    if (LockSecurePinUtil.isNetworkAvailable(this)) {
                        LockSecurePinUtil.checkSecurePinChanged(this, this.mDevice.did, this.mDevice.mac);
                        LockSecurePinUtil.checkLtmkChanged(this, this.mDevice.did, this.mDevice.mac, true);
                    }
                } else if (LockSecurePinUtil.isNetworkAvailable(this)) {
                    LockSecurePinUtil.checkSecurePinChanged(this, this.mDevice.did, this.mDevice.mac);
                    LockSecurePinUtil.checkLtmkChanged(this, this.mDevice.did, this.mDevice.mac, false);
                }
            } else if (this.mDevice.isSetPinCode != 0) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("extra_device_did", this.mDevice.did);
                FrameManager.b().k().openVerfyPincode(this, bundle3, 9999);
            }
        }
        if (this.mIsSupportAd && this.mDevice != null) {
            FrameManager.b().k().sendPluginAdRequest(this, this.mModel, this.mDevice.version);
        }
        Log.e("PluginLocale", getResources().getConfiguration().locale.toString());
    }

    private int getNavigationBarHeight(Context context) {
        if (!hasNavBar(context)) {
            return 0;
        }
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    public static boolean hasNavBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (identifier == 0) {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
        boolean z = resources.getBoolean(identifier);
        String navBarOverride = getNavBarOverride();
        if ("1".equals(navBarOverride)) {
            return false;
        }
        if ("0".equals(navBarOverride)) {
            return true;
        }
        return z;
    }

    private boolean hasNavigationBar() {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(activity().getContentResolver(), FORCE_FSG_NAV_BAR, 0) != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y != point.y) {
                return true;
            }
            return false;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    private static String getNavBarOverride() {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke((Object) null, new Object[]{"qemu.hw.mainkeys"});
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private void showBtmBar(final View view) {
        getHandler().postDelayed(new Runnable() {
            public void run() {
                view.setVisibility(0);
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                int bottom = viewGroup.getBottom() - viewGroup.getTop();
                float f = PluginHostActivity.this.getResources().getDisplayMetrics().density;
                float f2 = (float) bottom;
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{f2, f2 - (f * 70.0f)});
                ofFloat.setInterpolator(new TimeInterpolator() {
                    public float getInterpolation(float f) {
                        if (f <= 0.8f) {
                            double d = (double) f;
                            Double.isNaN(d);
                            return (float) (d * 1.5d);
                        }
                        double d2 = (double) f;
                        Double.isNaN(d2);
                        return (float) (2.0d - d2);
                    }
                });
                ofFloat.setDuration(600);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat);
                animatorSet.start();
            }
        }, 1000);
    }

    private void disappearBtmBar(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        float f = getResources().getDisplayMetrics().density;
        int bottom = viewGroup.getBottom() - viewGroup.getTop();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.Y, new float[]{((float) bottom) - (f * 70.0f), (float) viewGroup.getHeight()});
        ofFloat.setInterpolator(new TimeInterpolator() {
            public float getInterpolation(float f) {
                if (f <= 0.2f) {
                    return -f;
                }
                double d = (double) f;
                Double.isNaN(d);
                return (float) ((d * 1.5d) - 0.5d);
            }
        });
        ofFloat.setDuration(600);
        ofFloat.start();
    }

    private void getActivityInfo() {
        if (this.mLoadedInfo != null) {
            PackageInfo packageInfo = this.mLoadedInfo.packageInfo;
            if (packageInfo.activities != null && packageInfo.activities.length > 0) {
                if (this.mClass == null) {
                    this.mClass = packageInfo.activities[0].name;
                }
                for (ActivityInfo activityInfo : packageInfo.activities) {
                    if (activityInfo.name.equals(this.mClass)) {
                        this.mActivityInfo = activityInfo;
                    }
                }
            }
        }
    }

    private void handleActivityInfo() {
        Resources.Theme theme = super.getTheme();
        this.mTheme = this.mLoadedInfo.resources.newTheme();
        this.mTheme.setTo(theme);
        try {
            this.mTheme.applyStyle(Build.VERSION.SDK_INT >= 14 ? 16974120 : 16973829, true);
        } catch (Exception unused) {
        }
    }

    private void launchActivity(Intent intent) {
        if (this.mLoadedInfo != null) {
            try {
                this.mXmPluginActivity = (XmPluginBaseActivity) this.mLoadedInfo.classLoader.loadClass(this.mClass).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.mXmPluginActivity.attach(this, this.mLoadedInfo, this.mDevice);
                this.mXmPluginActivity.setIntent(intent);
                this.mXmPluginActivity.onCreate(intent.getExtras());
            } catch (Throwable th) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, th);
                finish();
            }
        } else {
            try {
                this.mXmPluginActivity = (XmPluginBaseActivity) getClassLoader().loadClass(this.mClass).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.mXmPluginActivity.attach(this, this.mLoadedInfo, this.mDevice);
                this.mXmPluginActivity.setIntent(intent);
                this.mXmPluginActivity.onCreate(intent.getExtras());
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void recreate() {
        super.recreate();
        if (this.mIsReady) {
            doRecreate();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doRecreate();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doRecreate() {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.recreate();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        if (this.mIsReady) {
            doPostCreate(bundle);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doPostCreate(bundle);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doPostCreate(Bundle bundle) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onPostCreate(bundle);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.mIsReady) {
            doStart();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doStart();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doStart() {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onStart();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public String getPageName() {
        if (this.mXmPluginActivity == null) {
            return "";
        }
        return this.mModel + ":" + this.mXmPluginActivity.getClass().getName();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mIsReady) {
            doResume();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doResume();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doResume() {
        this.mOnResumeTimestamp = System.currentTimeMillis();
        this.mPageName = getPageName();
        if (TextUtils.isEmpty(this.mPageName)) {
            this.mPageName = getClass().getName();
        }
        MiStatInterface.a((Activity) this, this.mPageName);
        if (this.mLoadedInfo != null) {
            String str = "plugin." + this.mLoadedInfo.getPluginId() + "." + this.mLoadedInfo.getPackageId();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", this.mPageName);
                jSONObject.put("timestamp", this.mOnResumeTimestamp / 1000);
                jSONObject.put("plugin_id", this.mLoadedInfo.getPluginId());
                jSONObject.put(PushRouteUtil.k, this.mLoadedInfo.getPackageId());
            } catch (JSONException unused) {
            }
            if (this.mDevice != null) {
                PluginStatReporter.a(PluginStatReporter.a(this.mLoadedInfo.getPluginId(), this.mLoadedInfo.getPackageId()), this.mDevice.model, this.mDevice.did, (Object) this, this.mPageName);
            }
            CoreApi.a().a(StatType.EVENT, str, "page_start", jSONObject.toString(), (String) null, false);
            CoreApi.a().T();
        }
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onResume();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return;
            }
        }
        if (this.mOfflineViewDelegate != null) {
            this.mOfflineViewDelegate.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        try {
            super.onPostResume();
            if (this.mIsReady) {
                doPostResume();
            } else {
                this.mOnReadyRunableList.add(new Runnable() {
                    public void run() {
                        PluginHostActivity.this.doPostResume();
                    }
                });
            }
        } catch (Exception e) {
            PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public void doPostResume() {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onPostResume();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MiStatInterface.c();
        if (this.mLoadedInfo != null) {
            String str = "plugin." + this.mLoadedInfo.getPluginId() + "." + this.mLoadedInfo.getPackageId();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", this.mPageName);
                jSONObject.put("timestamp", this.mOnResumeTimestamp);
                jSONObject.put("plugin_id", this.mLoadedInfo.getPluginId());
                jSONObject.put(PushRouteUtil.k, this.mLoadedInfo.getPackageId());
            } catch (JSONException unused) {
            }
            if (this.mDevice != null) {
                PluginStatReporter.a(PluginStatReporter.a(this.mLoadedInfo.getPluginId(), this.mLoadedInfo.getPackageId()), this.mDevice.model, this.mDevice.did, (Object) this, this.mOnResumeTimestamp, this.mPageName);
            }
            CoreApi.a().a(StatType.EVENT, str, "page_end", jSONObject.toString(), (String) null, false);
            CoreApi.a().T();
        }
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onPause();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return;
            }
        }
        if (this.mOfflineViewDelegate != null) {
            this.mOfflineViewDelegate.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onStop();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.d("PluginHostActivity", com.xiaomi.miot.support.monitor.core.activity.ActivityInfo.TYPE_STR_ONDESTROY);
        if (this.mIsSupportAd) {
            FrameManager.b().k().stopPluginAd(this.mModel);
        }
        popPluginHostActivity(this);
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onDestroy();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return;
            }
        }
        if (this.mOfflineViewDelegate != null) {
            this.mOfflineViewDelegate.onDestroy();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("PluginHostActivity", "onSaveInstanceState   outState");
        if (this.mDevice != null) {
            bundle.putString("extra_device_did", this.mDevice.did);
            bundle.putParcelable("extra_device", this.mDevice);
        }
        if (!TextUtils.isEmpty(this.mModel)) {
            bundle.putString("extra_device_model", this.mModel);
        }
        bundle.putBoolean("extra_recycle_plugin", true);
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onSaveInstanceState(bundle);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onSaveInstanceState(bundle, persistableBundle);
        Log.d("PluginHostActivity", "onSaveInstanceState");
        if (this.mDevice != null) {
            bundle.putString("extra_device_did", this.mDevice.did);
            bundle.putParcelable("extra_device", this.mDevice);
        }
        if (!TextUtils.isEmpty(this.mModel)) {
            bundle.putString("extra_device_model", this.mModel);
        }
        bundle.putBoolean("extra_recycle_plugin", true);
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onSaveInstanceState(bundle);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(final Bundle bundle) {
        try {
            if (this.mLoadedInfo != null || !bundle.getBoolean("extra_recycle_plugin", false)) {
                super.onRestoreInstanceState(bundle);
                if (this.mIsReady) {
                    doRestoreInstanceState(bundle);
                } else {
                    this.mOnReadyRunableList.add(new Runnable() {
                        public void run() {
                            PluginHostActivity.this.doRestoreInstanceState(bundle);
                        }
                    });
                }
            }
        } catch (Exception e) {
            PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public void doRestoreInstanceState(Bundle bundle) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onRestoreInstanceState(bundle);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(final int i, final int i2, final Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.d("PluginHostActivity", "onActivityResult");
        if (this.mIsReady) {
            doActivityResult(i, i2, intent);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doActivityResult(i, i2, intent);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doActivityResult(int i, int i2, Intent intent) {
        if (9999 == i) {
            if (i2 == 0) {
                finish();
            }
        } else if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onActivityResult(i, i2, intent);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onRequestPermissionsResult(i, strArr, iArr);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.d("PluginHostActivity", "onNewIntent");
        if (this.mIsReady) {
            doNewIntent(intent);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginHostActivity.this.doNewIntent(intent);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doNewIntent(Intent intent) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onNewIntent(intent);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onBackPressed() {
        if (this.backPressEnable && this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onBackPressed();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onTouchEvent(motionEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mXmPluginActivity == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        try {
            if (this.mXmPluginActivity.dispatchTouchEvent(motionEvent)) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
            PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
            finish();
            return false;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onKeyDown(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onKeyUp(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onKeyLongPress(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onKeyLongPress(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onKeyMultiple(i, i2, keyEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onKeyShortcut(i, keyEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onKeyShortcut(i, keyEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onWindowAttributesChanged(layoutParams);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return;
            }
        }
        super.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onWindowFocusChanged(z);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return;
            }
        }
        super.onWindowFocusChanged(z);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onCreateOptionsMenu(menu);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onOptionsItemSelected(menuItem);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onContentChanged() {
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onContentChanged();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onAttachedToWindow();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onDetachedFromWindow();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onLowMemory();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onTrimMemory(i);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.mXmPluginActivity != null) {
            try {
                if (this.mXmPluginActivity.onTrackballEvent(motionEvent)) {
                    return true;
                }
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
                return false;
            }
        }
        return super.onTrackballEvent(motionEvent);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onUserInteraction();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onUserLeaveHint();
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mXmPluginActivity != null) {
            try {
                this.mXmPluginActivity.onConfigurationChanged(configuration);
            } catch (Exception e) {
                PluginErrorInfoActivity.showErrorInfo(this, this.mLoadedInfo, e);
                finish();
            }
        }
    }

    public void overridePendingTransition(String str, String str2) {
        overridePendingTransition(getAnimRes(str), getAnimRes(str2));
    }

    private int getAnimRes(String str) {
        if ("slide_in_left".equals(str)) {
            return R.anim.activity_slide_in_left;
        }
        if ("slide_in_right".equals(str)) {
            return R.anim.activity_slide_in_right;
        }
        if ("slide_in_top".equals(str)) {
            return R.anim.activity_slide_in_top;
        }
        if ("slide_in_bottom".equals(str)) {
            return R.anim.activity_slide_in_bottom;
        }
        if ("slide_out_left".equals(str)) {
            return R.anim.activity_slide_out_left;
        }
        if ("slide_out_right".equals(str)) {
            return R.anim.activity_slide_out_right;
        }
        if ("slide_out_top".equals(str)) {
            return R.anim.activity_slide_out_top;
        }
        if ("slide_out_bottom".equals(str)) {
            return R.anim.activity_slide_out_bottom;
        }
        if ("fade_in_left".equals(str)) {
            return R.anim.activity_fade_in_left;
        }
        if ("fade_in_right".equals(str)) {
            return R.anim.activity_fade_in_right;
        }
        if ("fade_out_left".equals(str)) {
            return R.anim.activity_fade_out_left;
        }
        if ("fade_out_right".equals(str)) {
            return R.anim.activity_fade_out_right;
        }
        return 0;
    }

    public final void startActivityForResult(Intent intent, String str, String str2, int i) {
        Class pluginActivityClass = PluginRuntimeManager.getInstance().getPluginActivityClass(((PluginHostApi) XmPluginHostApi.instance()).pluginRunningProcess());
        if (pluginActivityClass != null) {
            Intent intent2 = new Intent(this, pluginActivityClass);
            intent2.putExtra(JUMP_FORM_PLUGIN_HOST, true);
            if (this.mDevice != null) {
                intent2.addCategory("did:" + this.mDevice.did);
                intent2.addCategory("model:" + this.mDevice.model);
                intent2.putExtra("extra_device_did", this.mDevice.did);
            } else {
                intent2.addCategory("model:" + this.mModel);
                intent2.putExtra("extra_device_model", this.mModel);
            }
            if (intent != null) {
                intent2.putExtras(intent);
            }
            intent2.putExtra("extra_package", str);
            intent2.putExtra("extra_class", str2);
            if (i < 0) {
                i = 0;
            }
            startActivityForResult(intent2, i);
        }
    }

    public final void openShareActivity() {
        View decorView;
        Bitmap bitmap;
        if (this.mDevice != null && (decorView = getWindow().getDecorView()) != null) {
            if (decorView.getWidth() <= 0 || decorView.getHeight() <= 0) {
                bitmap = null;
            } else {
                bitmap = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(), Bitmap.Config.RGB_565);
                decorView.draw(new Canvas(bitmap));
            }
            if (bitmap != null) {
                File externalCacheDir = getExternalCacheDir();
                boolean z = false;
                if (externalCacheDir == null) {
                    Toast.makeText(this, R.string.share_pic_not_extern_storage, 0).show();
                    return;
                }
                File file = new File(externalCacheDir, "share.jpg");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                    fileOutputStream.close();
                    try {
                        bitmap.recycle();
                    } catch (Exception unused) {
                    }
                    z = true;
                } catch (Exception unused2) {
                }
                if (z) {
                    openShareMediaActivity("米家", this.mDevice.name.toString(), file.getAbsolutePath());
                }
                XmPluginHostApi.instance().addRecord(this.mLoadedInfo, "SnapShare", (Object) 1, (JSONObject) null);
                StatHelper.a(this.mDevice);
            }
        }
    }

    public final void setTitleBarPadding(View view) {
        TitleBarUtil.a(TitleBarUtil.a((Context) activity()), view);
    }

    public final Intent getActivityIntent(String str, String str2) {
        RunningProcess pluginRunningProcess = ((PluginHostApi) XmPluginHostApi.instance()).pluginRunningProcess();
        if (pluginRunningProcess == null) {
            pluginRunningProcess = RunningProcess.PLUGIN0;
        }
        Class pluginActivityClass = PluginRuntimeManager.getInstance().getPluginActivityClass(pluginRunningProcess);
        if (pluginActivityClass == null) {
            return null;
        }
        Intent intent = new Intent(this, pluginActivityClass);
        if (this.mDevice != null) {
            intent.addCategory("did:" + this.mDevice.did);
            intent.putExtra("extra_device_did", this.mDevice.did);
        } else {
            intent.putExtra("extra_device_model", this.mModel);
        }
        intent.putExtra("extra_package", str);
        intent.putExtra("extra_class", str2);
        return intent;
    }

    public final void enableWhiteTranslucentStatus() {
        TitleBarUtil.b(getWindow());
    }

    public final boolean isTranslucentStatusbarEnable() {
        return TitleBarUtil.f23574a;
    }

    public final void enableBlackTranslucentStatus() {
        TitleBarUtil.a(getWindow());
    }

    public final void openDevice(String str, Intent intent) {
        XmPluginHostApi.instance().sendMessage(str, 1, intent, (DeviceStat) null, (MessageCallback) null);
    }

    public void openMoreMenu(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i) {
        FrameManager.b().k().openMoreMenu(this, this.mDevice, arrayList, z, i);
        StatHelper.b(this.mDevice);
    }

    public void openMoreMenu(ArrayList<String> arrayList, ArrayList<Intent> arrayList2, boolean z, int i) {
        FrameManager.b().k().openMoreMenu((Activity) this, this.mDevice, arrayList, arrayList2, z, i);
        StatHelper.b(this.mDevice);
    }

    public void openMoreMenu(String str, ArrayList<String> arrayList, ArrayList<Intent> arrayList2, boolean z, int i) {
        FrameManager.b().k().openMoreMenu((Activity) this, str, arrayList, arrayList2, z, i);
        StatHelper.b(this.mDevice);
    }

    public void goUpdateActivity() {
        FrameManager.b().k().goUpdateActivity((Activity) this, this.mDevice);
    }

    public void startLoadScene() {
        FrameManager.b().k().startLoadScene(this.mXmPluginActivity);
    }

    public void startCreateSceneByDid(String str) {
        FrameManager.b().k().startCreateSceneByDid(this, str);
    }

    public void startEditScene(int i) {
        FrameManager.b().k().startEditScene((Activity) this, i);
    }

    public void startEditScene(String str) {
        FrameManager.b().k().startEditScene((Activity) this, str);
    }

    public void startSetTimerList(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        FrameManager.b().k().startSetTimerList(this, str, str2, str3, str4, str5, str6, str7);
    }

    public void openFeedbackActivity() {
        FrameManager.b().k().openFeedbackActivity(this, this.mDevice);
        StatHelper.c(this.mDevice);
    }

    public void addToLauncher() {
        FrameManager.b().k().addToLauncher(this.mDevice);
    }

    public void loadWebView(String str, String str2) {
        FrameManager.b().k().loadWebView(str, str2);
    }

    public void openShopActivity(String str) {
        FrameManager.b().k().openShopActivity(str);
    }

    public void share(String str, String str2, String str3, String str4, String str5, Bitmap bitmap) {
        FrameManager.b().k().share(this, str, str2, str3, str4, str5, bitmap);
    }

    public void startSearchNewDevice(String str, String str2, IXmPluginHostActivity.DeviceFindCallback deviceFindCallback) {
        FrameManager.b().k().startSearchNewDevice(str, str2, deviceFindCallback);
    }

    public void openSceneActivity(String str) {
        FrameManager.b().k().openSceneActivity(this, this.mDevice, str);
        StatHelper.e(this.mDevice);
    }

    public void getDeviceRecommendScenes(String str, IXmPluginHostActivity.AsyncCallback<List<RecommendSceneItem>> asyncCallback) {
        FrameManager.b().k().getDeviceRecommendScenes(str, asyncCallback);
    }

    public void startEditRecommendScenes(RecommendSceneItem recommendSceneItem, String str, String str2) {
        FrameManager.b().k().startEditRecommendScenes(this, recommendSceneItem, str, str2);
    }

    public List<SceneInfo> getSceneByDid(String str) {
        return FrameManager.b().k().getSceneByDid(str);
    }

    public void setSceneEnabled(SceneInfo sceneInfo, boolean z, IXmPluginHostActivity.AsyncCallback<Void> asyncCallback) {
        FrameManager.b().k().setSceneEnabled(sceneInfo, z, asyncCallback);
    }

    public void modifySceneName(SceneInfo sceneInfo, IXmPluginHostActivity.AsyncCallback<Void> asyncCallback) {
        FrameManager.b().k().modifySceneName(sceneInfo, asyncCallback);
    }

    public void startSetTimerList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        FrameManager.b().k().startSetTimerList(this, str, str2, str3, str4, str5, str6, str7, str8);
    }

    public void openShareMediaActivity(String str, String str2, String str3, Bitmap bitmap, String str4, Bitmap bitmap2) {
        FrameManager.b().k().openShareMediaActivity(this, str, str2, str3, bitmap, str4, bitmap2, this.mDevice);
    }

    public void openShareMediaActivity(String str, String str2, String str3) {
        FrameManager.b().k().openShareMediaActivity(this, str, str2, str3, this.mDevice);
    }

    public void openShareMediaActivity(String str, String str2, String str3, Bitmap bitmap) {
        FrameManager.b().k().openShareMediaActivity(this, str, str2, str3, bitmap, this.mDevice);
    }

    public void openSharePictureActivity(String str, String str2, String str3) {
        FrameManager.b().k().openSharePictureActivity(this, str, str2, str3);
    }

    public void startEditCustomScene() {
        FrameManager.b().k().startEditCustomScene(this);
    }

    public void goUpdateActivity(String str) {
        FrameManager.b().k().goUpdateActivity((Activity) this, str);
    }

    public void startLoadScene(IXmPluginHostActivity.AsyncCallback asyncCallback) {
        FrameManager.b().k().startLoadScene(asyncCallback);
    }

    public void openHelpActivity() {
        FrameManager.b().k().openHelpActivity(this, this.mDevice);
        StatHelper.d(this.mDevice);
    }

    public void goBleUpdateActivity(Intent intent, BleUpgrader bleUpgrader) {
        Intent intent2 = new Intent();
        if (intent != null) {
            intent2.putExtras(intent);
        }
        if (bleUpgrader != null && Build.VERSION.SDK_INT >= 18) {
            Bundle bundle = new Bundle();
            bundle.putBinder(IXmPluginHostActivity.BleMenuItem.EXTRA_UPGRADE_CONTROLLER, bleUpgrader);
            intent2.putExtras(bundle);
        }
        FrameManager.b().k().goUpdateActivity(this, this.mDevice, intent2);
    }

    public void openRechargePage(int i, double d, double d2) {
        String str;
        String str2 = "";
        if (i > 0 && Math.abs(d * d2) > 0.0d) {
            try {
                List<Address> fromLocation = new Geocoder(XmPluginHostApi.instance().context(), Locale.CHINA).getFromLocation(d, d2, 1);
                if (fromLocation != null && fromLocation.size() > 0) {
                    Address address = fromLocation.get(0);
                    if (TextUtils.isEmpty(str2)) {
                        str2 = address.getLocality();
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = address.getAdminArea();
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = address.getSubAdminArea();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str = "https://web.recharge.pay.xiaomi.com/web/utility/v2?refs=10";
        } else {
            str = String.format("https://web.recharge.pay.xiaomi.com/web/utility/utilityCode/%d?refs=10&cityName=%s", new Object[]{Integer.valueOf(i), str2});
        }
        Log.d("PluginHostActivity", "loadWebView" + str);
        loadWebView(str, "");
        CoreApi.a().a(StatType.EVENT, "plugin_go_mi_recharge", Integer.toString(1), (String) null, false);
    }

    public void openScanBarcodePage(Bundle bundle, int i) {
        FrameManager.b().k().openScanBarcodePage(this, bundle, i);
    }

    public void openMoreMenu(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        FrameManager.b().k().openMoreMenu((Activity) this, this.mDevice, arrayList, z, i, intent);
        StatHelper.b(this.mDevice);
    }

    public void openMoreMenu(Activity activity, DeviceStat deviceStat, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, String str, int i, Intent intent) {
        FrameManager.b().k().openMoreMenu(this, this.mDevice, arrayList, z, i, intent, str);
        StatHelper.b(this.mDevice);
    }

    public void openMoreMenu2(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        FrameManager.b().k().openMoreMenu2(this, this.mDevice, arrayList, z, i, intent);
        StatHelper.b(this.mDevice);
    }

    public void openAddIRController(DeviceStat deviceStat, int i, String str) {
        openAddIRController(deviceStat, i, TextUtils.isEmpty(str) ? null : new String[]{str}, (Bundle) null);
    }

    public void openAddIRController(DeviceStat deviceStat, int i, String[] strArr, Bundle bundle) {
        FrameManager.b().k().openAddIRController(this, deviceStat, i, strArr, bundle);
    }

    public void openAddIRController(DeviceStat deviceStat) {
        openAddIRController(deviceStat, 0, (String[]) null, (Bundle) null);
    }

    public void openGatewaySubDeviceList(String str) {
        FrameManager.b().k().openGatewaySubDeviceList(this, str);
    }

    public void enableVerifyPincode() {
        this.mEnableVerifyPincode = true;
    }

    public void enableAd() {
        this.mIsSupportAd = true;
    }

    public void openShareVideoActivity(String str, String str2, String str3) {
        FrameManager.b().k().openSharePictureActivity(this, str, str2, str3);
    }

    public void showBannerAd(ViewGroup viewGroup, String str) {
        FrameManager.b().k().showBannerAd(activity(), viewGroup, this.mModel, str);
    }

    public void showNoticeAd(ViewGroup viewGroup, String str) {
        FrameManager.b().k().showNoticeAd(activity(), viewGroup, this.mModel, str);
    }

    public void clickHotSpotAd(String str) {
        FrameManager.b().k().clickHotSpotAd(activity(), this.mModel, str);
    }

    public void openOpHistoryActivity() {
        FrameManager.b().k().openOpHistoryActivity(this, this.mDevice.did);
    }

    public void reportHotSpotAdShow(String str) {
        FrameManager.b().k().reportHotSpotAdShow(this.mDevice.model, str);
    }

    public void openShareDeviceActivity() {
        FrameManager.b().k().openShareDeviceActivity(this, this.mDevice.did);
    }

    public void showUserLicenseDialog(@Deprecated String str, String str2, String str3, View.OnClickListener onClickListener) {
        FrameManager.b().k().showUserLicenseDialog(this, str, str2, str3, onClickListener);
    }

    public void showUserLicenseDialog(@Deprecated String str, String str2, Spanned spanned, String str3, Spanned spanned2, View.OnClickListener onClickListener) {
        FrameManager.b().k().showUserLicenseDialog(this, str, str2, spanned, str3, spanned2, onClickListener, this.mDevice.did, (Intent) null);
    }

    public void showUserLicenseDialog(@Deprecated String str, String str2, Spanned spanned, String str3, Spanned spanned2, View.OnClickListener onClickListener, Intent intent) {
        FrameManager.b().k().showUserLicenseDialog(this, str, str2, spanned, str3, spanned2, onClickListener, this.mDevice.did, intent);
    }

    public void showUserLicenseHtmlDialog(@Deprecated String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener) {
        FrameManager.b().k().showUserLicenseHtmlDialog(this, str, str2, str3, str4, str5, onClickListener, this.mDevice.did);
    }

    public void showUserLicenseUriDialog(@Deprecated String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener) {
        FrameManager.b().k().showUserLicenseUriDialog(this, str, str2, str3, str4, str5, onClickListener, this.mDevice.did);
    }

    public void openMoreMenu2(ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, Intent intent2) {
        FrameManager.b().k().openMoreMenu2(this, this.mDevice, arrayList, z, i, intent, intent2);
        StatHelper.b(this.mDevice);
    }

    public void showUseDefaultLicenseDialog(@Deprecated String str, View.OnClickListener onClickListener) {
        FrameManager.b().k().showUserLicenseDialog(this, str, onClickListener, this.mDevice.did);
    }

    public void startSetTimerListV2(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, String str11) {
        FrameManager.b().k().startSetTimerListV2(this, str, str2, str3, str4, str5, str6, str7, str8, z, str9, str10, str11);
    }

    public void startSetTimerListV3(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        FrameManager.b().k().startSetTimerListV3(this, str, str2, str3, str4, str5, str6, str7, str8);
    }

    public void startSetTimerListV3(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, String str11) {
        FrameManager.b().k().startSetTimerListV3(this, str, str2, str3, str4, str5, str6, str7, str8, z, str9, str10, str11);
    }

    public void startAddRoom() {
        FrameManager.b().k().startAddRoom(this);
    }

    public void startSetTimerCountDown(String str, String str2, String str3, String str4, String str5, boolean z) {
        FrameManager.b().k().startSetTimerCountDown(this, str, str2, str3, str4, str5, z);
    }

    public void openCloudVideoListActivity(String str, String str2) {
        FrameManager.b().k().openCloudVideoListActivity(this, str, str2);
    }

    public void openCloudVideoPlayerActivity(String str, String str2, String str3) {
        FrameManager.b().k().openCloudVideoPlayerActivity(this, str, str2, str3);
    }

    public void openCloudVideoWebActivity(String str, String str2, String str3) {
        FrameManager.b().k().openCloudVideoWebActivity(this, str, str2, str3);
    }

    public void onDeviceReady(String str, String str2) {
        FrameManager.b().k().onDeviceReady(this, str, str2, new IXmPluginHostActivity.AsyncCallback<Void>() {
            public void onFailure(int i, Object obj) {
            }

            public void onSuccess(Void voidR) {
            }
        });
    }

    public void openPowerSwitchNameActivity(String str, String str2) {
        FrameManager.b().k().openPowerSwitchNameActivity(this, str, str2);
    }

    public void openCloudVideoExoPlayerActivity(String str, String str2, String str3) {
        FrameManager.b().k().openCloudVideoExoPlayerActivity(this, str, str2, str3);
    }

    public void openWxBindActivity(int i) {
        FrameManager.b().k().openWxBindActivity(this, i);
    }

    public void openBtGatewayActivity(String str) {
        FrameManager.b().k().openBtGatewayActivity(this, str);
    }

    public void openOneTimePasswordActivity(String str, int i, int i2) {
        FrameManager.b().k().openOneTimePasswordActivity(this, str, i, i2);
    }

    public void openFaceManagerActivity(String str) {
        FrameManager.b().k().openFaceManagerActivity(this, str);
    }

    public void openFaceManagerActivity(int i, String str, String str2) {
        FrameManager.b().k().openFaceManagerActivity(i, this, str, str2);
    }

    public void openMarkFaceDialog(String str, String str2, FaceManagerCallback faceManagerCallback) {
        FrameManager.b().k().openMarkFaceDialog(this, str, str2, faceManagerCallback);
    }

    public void openReplaceFaceDialog(String str, String str2, String str3, String str4, FaceManagerCallback faceManagerCallback) {
        FrameManager.b().k().openReplaceFaceDialog(this, str, str2, str3, str4, faceManagerCallback);
    }

    public void openFaceEmptyActivity(String str) {
        FrameManager.b().k().openFaceEmptyActivity(this, str);
    }

    public void startRecommendSceneDetailActivityBy(String str, int i) {
        FrameManager.b().k().startRecommendSceneDetailActivityBy(this, str, i);
    }

    public void openVirtualGroupInitActivity(String str, int i) {
        FrameManager.b().k().openVirtualGroupInitActivity(this, this.mDevice.did, i);
    }

    public void openNetworkInfoActivity(String str) {
        FrameManager.b().k().openNetworkInfoActivity(this, this.mDevice.did);
    }

    public void openScreenDeviceLinkageSettingActivity(String str, boolean z) {
        FrameManager.b().k().openScreenDeviceLinkageSettingActivity(this, str, z);
    }

    public void openScreenDeviceLinkageSettingActivity(String str, boolean z, int i, String str2) {
        FrameManager.b().k().openScreenDeviceLinkageSettingActivity(this, str, z, i, str2);
    }
}
