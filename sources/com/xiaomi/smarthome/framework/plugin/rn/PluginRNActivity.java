package com.xiaomi.smarthome.framework.plugin.rn;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.android.gms.measurement.AppMeasurement;
import com.imagepicker.permissions.OnImagePickerPermissionsCallback;
import com.taobao.weex.WXGlobalEventReceiver;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.LockSecurePinUtil;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.frame.plugin.runtime.util.OfflineViewDelegate;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.page.TimezoneActivity;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugConstant;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugFileUtil;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTBluetoothModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTDeviceModule;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.plugin.rn.view.PluginFrameLayout;
import com.xiaomi.smarthome.library.common.widget.nestscroll.internal.SimpleAnimatorListener;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONObject;

public class PluginRNActivity extends LoadingBaseActivity implements PermissionAwareActivity, OnImagePickerPermissionsCallback, IPluginRnActivity {
    public static final String TAG = "rn-plugin";

    /* renamed from: a  reason: collision with root package name */
    private static final int f17216a = 9999;
    private static final float b = 0.3f;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 2;
    private static boolean f = true;
    private static String g;
    private static long h;
    private int i;
    /* access modifiers changed from: private */
    public PermissionListener j;
    private Callback k;
    /* access modifiers changed from: private */
    public DefaultHardwareBackBtnHandler l = new DefaultHardwareBackBtnHandler() {
        public void invokeDefaultOnBackPressed() {
            RnPluginLog.a("rn activity... invoke on backpressed");
            PluginRNActivity.super.onBackPressed();
        }
    };
    private BroadcastReceiver m;
    boolean mIsReady = true;
    List<Runnable> mOnReadyRunableList = new ArrayList();
    private OfflineViewDelegate n;
    /* access modifiers changed from: private */
    public int o = 0;
    FrameLayout targetView = null;

    public void setPermissionListener(@NonNull PermissionListener permissionListener) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable final Bundle bundle) {
        FrescoInitial.a(false);
        super.onCreate(bundle);
        RnPluginLog.a("PluginRnActivity   start onCreate...");
        long longExtra = getIntent().getLongExtra("openTime", System.currentTimeMillis());
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.c("statistic-time", "statistic time:  msg-->onCreate is cost " + (currentTimeMillis - longExtra));
        RNPluginReportTime.a().a(longExtra);
        RNPluginReportTime.a().b(currentTimeMillis);
        if (getPluginRecord() == null) {
            finish();
            return;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        RNRuntime.a().k();
        RnPluginLog.a("PluginRNActivity  initRuntime cost time " + (System.currentTimeMillis() - currentTimeMillis2));
        this.i = getIntent().getIntExtra(PluginApi.TYPE_LOADING, 0);
        if (this.i == 1) {
            overridePendingTransition(R.anim.activity_anim_no_in, R.anim.activity_anim_no_out);
            this.flRoot.setBackgroundColor(0);
            GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) this.mSdvIcon.getHierarchy();
            if (genericDraweeHierarchy != null) {
                genericDraweeHierarchy.setFadeDuration(0);
                genericDraweeHierarchy.setPlaceholderImage((Drawable) null);
            }
        } else {
            overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        }
        if (this.launchMsgType != 3) {
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
        if (!CoreApi.a().l()) {
            Log.e(TAG, "core process not ready,initial core process");
            this.mIsReady = false;
            this.mOnReadyRunableList.clear();
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.a(bundle);
                }
            });
            CoreApi.a().a(getApplicationContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    CoreApi.a().a(PluginRNActivity.this.getApplicationContext(), (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                        public void onPluginCacheReady() {
                            if (CoreApi.a().l()) {
                                PluginRNActivity.this.mIsReady = true;
                                for (int i = 0; i < PluginRNActivity.this.mOnReadyRunableList.size(); i++) {
                                    PluginRNActivity.this.mOnReadyRunableList.get(i).run();
                                }
                                PluginRNActivity.this.mOnReadyRunableList.clear();
                                return;
                            }
                            Log.e(PluginRNActivity.TAG, "core process not ready");
                            PluginRNActivity.this.mOnReadyRunableList.clear();
                            PluginRNActivity.this.finish();
                        }
                    });
                }
            });
            return;
        }
        this.mIsReady = true;
        a(bundle);
    }

    public String getDeviceModel() {
        return getIntent().getStringExtra("model");
    }

    public PluginRecord getPluginRecord() {
        return RNRuntime.a().e();
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        boolean z;
        RnPluginLog.a("PluginRNActivity-->doCreate()...");
        if (bundle == null || !bundle.getBoolean("extra_recycle_plugin", false)) {
            RNRuntime.a().a((Activity) this);
            String stringExtra = getIntent().getStringExtra("model");
            RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
                public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                    if (reactRootView.getParent() instanceof ViewGroup) {
                        ((ViewGroup) reactRootView.getParent()).removeView(reactRootView);
                    }
                    PluginRNActivity.this.flRoot.addView(reactRootView, 0);
                    LogUtil.c("PluginStartTime", "onAttachPlugin  " + System.currentTimeMillis());
                    reactRootView.setAlpha(PluginRNActivity.b);
                    final ReactRootView reactRootView2 = reactRootView;
                    final ReactInstanceManager reactInstanceManager2 = reactInstanceManager;
                    final DeviceStat deviceStat2 = deviceStat;
                    final PluginRecord pluginRecord2 = pluginRecord;
                    reactRootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        private SimpleAnimatorListener f = new SimpleAnimatorListener() {
                            public void onAnimationEnd(Animator animator) {
                                XmPluginHostApi instance = XmPluginHostApi.instance();
                                instance.log("PluginRNActivity.doCreate", System.currentTimeMillis() + " reactView:" + reactRootView2 + " instanceManager:" + reactInstanceManager2 + " deviceStat:" + deviceStat2 + " record:" + pluginRecord2);
                                PluginRNActivity.this.llLoading.setVisibility(8);
                                RnPluginLog.a("Broadcast: PluginRNActivity.doCreate-->sendBroadcast(new Intent(LoadingRNActivity.ACTION_LAUNCH_PLUGIN_FINISH))");
                                PluginRNActivity.this.sendBroadcast(new Intent(LoadingRNActivity.ACTION_LAUNCH_PLUGIN_FINISH));
                                reactRootView2.setAlpha(1.0f);
                                if (PluginRNActivity.this.mLoadingDrawable != null) {
                                    PluginRNActivity.this.mLoadingDrawable.stop();
                                }
                            }
                        };

                        public boolean onPreDraw() {
                            int childCount = reactRootView2.getChildCount();
                            if (childCount <= 0) {
                                return true;
                            }
                            LogUtil.c("PluginStartTime", "onPreDraw  " + childCount);
                            reactRootView2.getViewTreeObserver().removeOnPreDrawListener(this);
                            PluginRNActivity.this.flRoot.setBackgroundColor(-1);
                            PluginRNActivity.this.llLoading.setBackgroundColor(-1);
                            if (System.currentTimeMillis() - PluginRNActivity.this.getIntent().getLongExtra(PluginBridgeService.EXTRA_CLICK_DEVICE_TIME, 0) < 300) {
                                this.f.onAnimationEnd((Animator) null);
                                return true;
                            }
                            ValueAnimator duration = ValueAnimator.ofFloat(new float[]{1.0f, PluginRNActivity.b}).setDuration(350);
                            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                    PluginRNActivity.this.llLoading.setAlpha(floatValue);
                                    reactRootView2.setAlpha((1.0f - floatValue) + PluginRNActivity.b);
                                }
                            });
                            duration.addListener(this.f);
                            duration.start();
                            return true;
                        }
                    });
                }
            });
            JSONObject a2 = RnDebugFileUtil.a(stringExtra);
            if (!RnPluginDebugDeviceMock.f22091a.equals(stringExtra) && (a2 == null || !a2.optBoolean(RnDebugConstant.c))) {
                z = false;
            } else {
                z = true;
            }
            boolean z2 = DevelopSharePreManager.a().g() && z;
            RnPluginLog.a("PluginRNActivity  is_debug=" + z2 + "  isPluginDebugChecked=" + z + "  model=" + stringExtra);
            if (this.launchMsgType != 1 || z2) {
                RNPluginReportTime.a().a(false);
            } else {
                RNPluginReportTime.a().a(true);
            }
            if (z2) {
                View findViewById = findViewById(R.id.drag_button_dev_setting);
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ReactInstanceManager l = RNRuntime.a().l();
                        if (l != null) {
                            l.getDevSupportManager().handleReloadJS();
                        }
                    }
                });
            }
            this.m = new RNEventReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(XmBluetoothManager.ACTION_RENAME_NOTIFY);
            intentFilter.addAction(MIOTBluetoothModule.STARTSCAN_CALLBACK);
            intentFilter.addAction(MIOTBluetoothModule.DISCOVERSERVICES_CALLBACK);
            intentFilter.addAction(MIOTBluetoothModule.DISCOVERCHARACTERISTICS_CALLBACK);
            intentFilter.addAction(MIOTDeviceModule.DEVICESTATUSCHANGED);
            intentFilter.addAction(PluginBridgeService.ACTION_PLUGIN_PUSH);
            LocalBroadcastManager.getInstance(this).registerReceiver(this.m, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction(XmBluetoothManager.ACTION_RENAME_NOTIFY);
            intentFilter2.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter2.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
            intentFilter2.addAction("com.xiaomi.smarthome.bluetooth.character_changed");
            intentFilter2.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
            intentFilter2.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(this.m, intentFilter2);
            this.n = new OfflineViewDelegate(this, this.mDevice);
            this.n.onCreate();
            if (this.launchMsgType != 3) {
                this.n.showOfflineIfNeeded();
                return;
            }
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void a() {
        RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
            public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                if (reactInstanceManager != null && reactInstanceManager.getLifecycleState() != LifecycleState.RESUMED) {
                    RnPluginLog.a("PluginRNActivity doResume  onAttachPlugin...");
                    reactInstanceManager.onHostResume(PluginRNActivity.this, PluginRNActivity.this.l);
                    ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.PACKAGEDIDRESUME);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.PACKAGEDIDRESUME, createMap);
                }
            }
        });
        if (this.n != null) {
            this.n.onResume();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d(TAG, "onSaveInstanceState");
        bundle.putBoolean("extra_recycle_plugin", true);
    }

    /* access modifiers changed from: private */
    public void b() {
        RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
            public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                if (reactInstanceManager.getLifecycleState() == LifecycleState.RESUMED) {
                    try {
                        reactInstanceManager.onHostPause(PluginRNActivity.this);
                    } catch (Exception e) {
                        Log.e("doPause", AppMeasurement.Param.FATAL, e);
                    }
                    ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.PACKAGEWILLPAUSE);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.PACKAGEWILLPAUSE, createMap);
                }
            }
        });
        if (this.n != null) {
            this.n.onPause();
        }
    }

    /* access modifiers changed from: private */
    public void a(final Intent intent) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str : extras.keySet()) {
                writableNativeMap.putString(str, String.valueOf(extras.get(str)));
            }
        }
        RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
            public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                reactInstanceManager.onNewIntent(intent);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.EVENT_PUSH, writableNativeMap);
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Intent intent = new Intent("onConfigurationChanged");
        intent.putExtra("newConfig", configuration);
        sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void a(final int i2, final int i3, final Intent intent) {
        if (9999 != i2) {
            if (RNRuntime.a().l() != null) {
                RNRuntime.a().l().onActivityResult(this, i2, i3, intent);
            }
            RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
                public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                    WritableMap writableMap;
                    ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                    if (intent != null && i2 == 3 && i3 == 4) {
                        if (deviceStat != null) {
                            String displayName = ((TimeZone) intent.getSerializableExtra(TimezoneActivity.KEY_TIMEZONE)).getDisplayName(false, 0);
                            WritableMap createMap = Arguments.createMap();
                            createMap.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.DEVICE_TIMEZONE_CHANGED);
                            createMap.putString("timeZone", displayName);
                            createMap.putString("did", deviceStat.did);
                            LogUtil.c("deviceTimeZoneChangedEvent", "  offset: " + displayName + "  did: " + deviceStat.did);
                            ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.DEVICE_TIMEZONE_CHANGED, createMap);
                        }
                    } else if (intent != null && -1 == i3 && DeviceMoreActivity.ARGS_RESULT_REMOVE_LICENSE.equals(intent.getStringExtra("result_data"))) {
                        WritableMap createMap2 = Arguments.createMap();
                        createMap2.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.CANCELAUTHOR);
                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.CANCELAUTHOR, createMap2);
                    }
                    if (intent == null || intent.getExtras() == null) {
                        writableMap = Arguments.createMap();
                    } else {
                        try {
                            writableMap = Arguments.fromBundle(intent.getExtras());
                        } catch (IllegalArgumentException e) {
                            WritableMap createMap3 = Arguments.createMap();
                            LogUtil.e("PluginRNActivity", e.toString());
                            writableMap = createMap3;
                        }
                    }
                    writableMap.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.VIEWWILLAPPEAR);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.VIEWWILLAPPEAR, writableMap);
                }
            });
        } else if (i3 == 0) {
            finish();
        }
    }

    public void onBackPressed() {
        RnPluginLog.a("rn activity... onBackPressed...mIsReady is " + this.mIsReady);
        if (this.mIsReady) {
            ReactInstanceManager l2 = RNRuntime.a().l();
            if (l2 == null || !RNRuntime.a().n()) {
                super.onBackPressed();
            } else {
                l2.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
            public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                LocalBroadcastManager.getInstance(PluginRNActivity.this).sendBroadcast(PluginRNActivity.this.getIntent().setAction(PluginBridgeService.ACTION_PLUGIN_SCENE));
                ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                WritableMap createMap = Arguments.createMap();
                createMap.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.PACKAGEWILLEXIT);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.PACKAGEWILLEXIT, createMap);
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        RNRuntime.a().m();
        if (this.m != null) {
            unregisterReceiver(this.m);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.m);
            this.m = null;
        }
        RNRuntime.a().a((RNRuntime.RNStateListener) new RNRuntime.RNStateListener() {
            public void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord) {
                if (reactRootView.getParent() instanceof ViewGroup) {
                    ((ViewGroup) reactRootView.getParent()).removeView(reactRootView);
                }
                reactInstanceManager.onHostDestroy(PluginRNActivity.this);
            }
        });
        RnPluginLog.a("launchMsgType:  " + this.launchMsgType);
        int i2 = this.launchMsgType;
        if (this.n != null) {
            this.n.onDestroy();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mIsReady) {
            a();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.a();
                }
            });
        }
        if (this.k != null) {
            this.k.invoke(new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mIsReady) {
            d();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.d();
                }
            });
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
        sendBroadcast(new Intent(LoadingRNActivity.ACTION_LAUNCH_PLUGIN_FINISH));
        if (this.mIsReady) {
            c();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.c();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mIsReady) {
            b();
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.b();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        RnPluginLog.a("PluginRNActivity-->onNewIntent()...");
        if (this.mIsReady) {
            a(intent);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.a(intent);
                }
            });
        }
    }

    public void startActivityForResult(Intent intent, int i2, @Nullable Bundle bundle) {
        if (i2 == -1) {
            i2 = 0;
        }
        super.startActivityForResult(intent, i2, bundle);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(final int i2, final int i3, final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (this.mIsReady) {
            a(i2, i3, intent);
        } else {
            this.mOnReadyRunableList.add(new Runnable() {
                public void run() {
                    PluginRNActivity.this.a(i2, i3, intent);
                }
            });
        }
    }

    public void requestPermissions(String[] strArr, int i2, PermissionListener permissionListener) {
        RnPluginLog.a("permissions: " + strArr + "   requestCode: " + i2);
        this.j = permissionListener;
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(strArr, i2);
        }
    }

    public void onRequestPermissionsResult(final int i2, @NonNull final String[] strArr, @NonNull final int[] iArr) {
        RnPluginLog.a("permissions: " + strArr + "   requestCode: " + i2 + "  grantResults:" + iArr);
        this.k = new Callback() {
            public void invoke(Object... objArr) {
                if (PluginRNActivity.this.j != null && PluginRNActivity.this.j.onRequestPermissionsResult(i2, strArr, iArr)) {
                    PermissionListener unused = PluginRNActivity.this.j = null;
                }
            }
        };
    }

    public void onPagePause(ReactRootView reactRootView, int i2) {
        Log.i(TAG, "onPagePause " + i2);
        if (i2 == 0 && this.o == 2 && this.targetView != null) {
            this.targetView.setVisibility(8);
        }
    }

    public void onPageResume(ReactRootView reactRootView, int i2) {
        Log.i(TAG, "onPageResume " + i2);
        if (this.launchMsgType == 1) {
            if (i2 == 0) {
                if (this.o == 0) {
                    final View childAt = reactRootView.getChildAt(0);
                    if (childAt instanceof ReactViewGroup) {
                        ReactViewGroup reactViewGroup = (ReactViewGroup) childAt;
                        if (reactViewGroup.getChildCount() > 0) {
                            View childAt2 = reactViewGroup.getChildAt(0);
                            if (childAt2 instanceof ReactViewGroup) {
                                PluginFrameLayout pluginFrameLayout = new PluginFrameLayout(this);
                                ((ReactViewGroup) childAt2).addView(pluginFrameLayout, 0, 0);
                                this.targetView = pluginFrameLayout;
                                this.o = 1;
                            }
                            LogUtil.c(TAG, "onPageResume targetView is negivation child 2");
                        } else {
                            reactViewGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                                public void onChildViewRemoved(View view, View view2) {
                                }

                                public void onChildViewAdded(View view, View view2) {
                                    ((ReactViewGroup) childAt).setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
                                    childAt.post(new Runnable() {
                                        public void run() {
                                            if (PluginRNActivity.this.targetView == null) {
                                                PluginRNActivity.this.targetView = new PluginFrameLayout(PluginRNActivity.this);
                                            } else {
                                                ((ViewGroup) PluginRNActivity.this.targetView.getParent()).removeView(PluginRNActivity.this.targetView);
                                            }
                                            ((ReactViewGroup) ((ReactViewGroup) childAt).getChildAt(0)).addView(PluginRNActivity.this.targetView, 0, 0);
                                            int unused = PluginRNActivity.this.o = 1;
                                            LogUtil.c(PluginRNActivity.TAG, "onPageResume targetView is delay negivation child 2");
                                        }
                                    });
                                }
                            });
                        }
                    }
                    if (this.targetView == null) {
                        View findViewById = findViewById(16908290);
                        if (findViewById instanceof ViewGroup) {
                            PluginFrameLayout pluginFrameLayout2 = new PluginFrameLayout(this);
                            ((ViewGroup) findViewById).addView(pluginFrameLayout2, -1, -1);
                            this.targetView = pluginFrameLayout2;
                            this.o = 2;
                            LogUtil.c(TAG, "onPageResume targetView is content");
                        }
                    }
                    this.n.showWeakRssiIfNeed(this.targetView);
                } else if (this.o == 2 && this.targetView != null) {
                    this.targetView.setVisibility(0);
                }
            } else if (this.targetView != null && this.o == 1 && this.targetView.getParent() != null) {
                ViewParent parent = this.targetView.getParent().getParent();
                if ((parent instanceof ReactViewGroup) && ((ReactViewGroup) parent).getChildCount() != i2 + 1) {
                    LogUtil.c(TAG, "onPageResume targetView attach not negivation");
                    this.o = 2;
                    this.targetView.setVisibility(8);
                }
            }
        }
    }
}
