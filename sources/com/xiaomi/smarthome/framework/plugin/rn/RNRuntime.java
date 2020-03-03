package com.xiaomi.smarthome.framework.plugin.rn;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.airbnb.android.react.lottie.LottiePackage;
import com.babisoft.ReactNativeLocalization.ReactNativeLocalizationPackage;
import com.brentvatne.react.ReactVideoPackage;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DevLoadingViewController;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.measurement.AppMeasurement;
import com.horcrux.svg.SvgPackage;
import com.imagepicker.ImagePickerPackage;
import com.mi.global.shop.util.PushRouteUtil;
import com.projectseptember.RNGL.RNGLPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.geolocation.GeolocationPackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.reactnativecommunity.viewpager.RNCViewPagerPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.rt2zz.reactnativecontacts.ReactNativeContacts;
import com.xiaomi.infrared.activity.IRMatchingDeviceTypeActivity;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.router.miio.miioplugin.IPluginCallback;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.aitraining.AiTrainingWebActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.ModifyGroupActivity;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.feedback.FeedbackCommonProblemActivity;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.debug.PluginErrorInfoActivity;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity;
import com.xiaomi.smarthome.framework.page.TimezoneActivity;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugConstant;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugFileUtil;
import com.xiaomi.smarthome.framework.page.verify.SecuritySettingActivity;
import com.xiaomi.smarthome.framework.plugin.rn.fixbug.FixBugMainReactPackage;
import com.xiaomi.smarthome.framework.plugin.rn.jsc.JSCManager;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPackageModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.react_native_orientation.OrientationPackage;
import com.xiaomi.smarthome.framework.plugin.rn.ota.nodrc.dfu.RNNordicDfuPackage;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.blurview.BlurViewPackage;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera.MiotCameraViewPackage;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.lineargradient.LinearGradientPackage;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper.MiotMapSweeperViewPackage;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.DeviceRoomAddActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.activity.BleGatewayActivity;
import com.xiaomi.smarthome.miio.activity.BleGatewayListActivity;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import java.io.File;
import java.lang.Thread;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.reactnative.camera.RNCameraPackage;

public class RNRuntime {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17249a = "com.xiaomi.smarthome.framework.plugin.rn.RNRuntime";
    public static final String b = "@ExceptionFromReactNative";
    public static final String c = "@ExceptionFromReactNative";
    public static final byte d = 0;
    public static final byte e = 1;
    public static final byte f = 2;
    public static final byte g = -1;
    private static final String h = "sdk.bundle";
    private static final String i = "main.bundle";
    private static final String j = "sdk";
    private static final String k = "Error calling AppRegistry.runApplication";
    private static final String l = "has not been registered";
    private static final int m = 0;
    private static final int n = 1;
    private static final int o = 2;
    private static final int p = 65536;
    private static final int q = 131072;
    private static int s = 101;
    private static int t;
    private static RNRuntime u;
    private static final Object v = new Object();
    /* access modifiers changed from: private */
    public Activity A;
    /* access modifiers changed from: private */
    public PluginSetting.RnSdkInfo B;
    private String C;
    /* access modifiers changed from: private */
    public PluginRecord D;
    /* access modifiers changed from: private */
    public DeviceStat E;
    private Bundle F;
    /* access modifiers changed from: private */
    public boolean G = false;
    private boolean H = false;
    private ArrayList<RNStateListener> I;
    private HashMap<String, String> J = new HashMap<>();
    /* access modifiers changed from: private */
    public long K = -1;
    /* access modifiers changed from: private */
    public long L = -1;
    /* access modifiers changed from: private */
    public String M = null;
    /* access modifiers changed from: private */
    public byte N = 0;
    private Map<String, Integer> O = new HashMap();
    private volatile int P = 0;
    /* access modifiers changed from: private */
    public final BroadcastReceiver Q = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                char c = 65535;
                if (action.hashCode() == 1415192249 && action.equals(PluginManager.b)) {
                    c = 0;
                }
                if (c == 0) {
                    long longExtra = intent.getLongExtra(PluginManager.c, -1);
                    if (longExtra >= RNRuntime.this.K && RNRuntime.this.A == null) {
                        ReactInstanceManager unused = RNRuntime.this.z = null;
                        RNRuntime.this.d();
                    }
                    XmPluginHostApi instance = XmPluginHostApi.instance();
                    instance.log("RNRuntime.mReceiver", "apiLevel:" + longExtra + " currentLevel:" + RNRuntime.this.K + " activity:" + RNRuntime.this.A);
                }
            }
        }
    };
    private DefaultHardwareBackBtnHandler R = new DefaultHardwareBackBtnHandler() {
        public void invokeDefaultOnBackPressed() {
            RnPluginLog.a("rn runtime... invoke on backpressed");
            if (RNRuntime.this.A != null) {
                RNRuntime.this.A.finish();
            }
        }
    };
    private RuntimeInfo S = new RuntimeInfo();
    /* access modifiers changed from: private */
    public int r = 0;
    /* access modifiers changed from: private */
    public ReactRootView w;
    private MIOTReactNativeSDKPackage x;
    private String y;
    /* access modifiers changed from: private */
    public ReactInstanceManager z;

    public interface RNStateListener {
        void a(ReactRootView reactRootView, ReactInstanceManager reactInstanceManager, DeviceStat deviceStat, PluginRecord pluginRecord);
    }

    private RNRuntime() {
        SHApplication.getAppContext().registerReceiver(this.Q, new IntentFilter(PluginManager.b));
    }

    public static RNRuntime a() {
        if (u == null) {
            synchronized (v) {
                if (u == null) {
                    u = new RNRuntime();
                }
            }
        }
        return u;
    }

    /* access modifiers changed from: private */
    public void a(JSBundleLoaderDelegate jSBundleLoaderDelegate, PluginSetting.RnSdkInfo rnSdkInfo) {
        if (jSBundleLoaderDelegate != null && this.B != null && this.B == rnSdkInfo) {
            try {
                if (this.K < 0) {
                    this.K = this.B.f22090a;
                    this.L = this.B.b;
                    PluginSetting.a("runtime load sdk script");
                    PluginSetting.a("runtime load sdk ", Long.valueOf(this.K), this.B.c);
                    RnPluginLog.a("2、 start load rn sdk bundle, PluginStartTime  loadScript  " + System.currentTimeMillis() + "  " + this.B.d);
                    try {
                        if (this.B.e) {
                            JSBundleLoader.createAssetLoader(SHApplication.getAppContext(), this.B.c, false).loadScript(jSBundleLoaderDelegate);
                        } else {
                            JSBundleLoader.createFileLoader(this.B.c, this.B.d, false).loadScript(jSBundleLoaderDelegate);
                        }
                    } catch (Throwable th) {
                        RnPluginLog.b("load sdk error,message" + Log.getStackTraceString(th));
                        this.r = this.r | 2;
                    }
                }
                if (this.M == null && !TextUtils.isEmpty(this.C)) {
                    PluginSetting.a(PluginSetting.k);
                    File file = new File(this.C, "main.bundle");
                    if (file.exists()) {
                        this.M = this.C;
                        PluginSetting.a(PluginSetting.k, Long.valueOf(this.K), this.B.c, this.M);
                        LogUtil.c("PluginStartTime", "loadScript  " + System.currentTimeMillis() + "  " + file);
                        try {
                            JSBundleLoader.createFileLoader(file.getAbsolutePath(), this.M, false).loadScript(jSBundleLoaderDelegate);
                        } catch (Throwable th2) {
                            this.r |= 131072;
                            RnPluginLog.b("load plugin error,message:" + Log.getStackTraceString(th2));
                        }
                    } else {
                        this.r |= 65536;
                    }
                }
            } catch (Exception e2) {
                LogUtil.b(AppMeasurement.Param.FATAL, Log.getStackTraceString(e2));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x025a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.plugin.core.XmPluginPackage r14, java.lang.Throwable r15) {
        /*
            r13 = this;
            com.facebook.react.ReactInstanceManager r0 = r13.z
            if (r0 == 0) goto L_0x000b
            com.facebook.react.ReactInstanceManager r0 = r13.z
            com.facebook.react.bridge.ReactContext r0 = r0.getCurrentReactContext()
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            if (r0 != 0) goto L_0x0012
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
        L_0x0012:
            java.lang.String r1 = com.xiaomi.smarthome.frame.process.ProcessUtil.g(r0)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x002c
            java.lang.String r2 = "com.xiaomi.smarthome:"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x002c
            java.lang.String r2 = "com.xiaomi.smarthome:"
            java.lang.String r3 = ""
            java.lang.String r1 = r1.replace(r2, r3)
        L_0x002c:
            r7 = r1
            boolean r1 = r15 instanceof com.facebook.react.common.JavascriptException
            r2 = 0
            if (r1 != 0) goto L_0x0041
            boolean r1 = r15 instanceof com.facebook.react.bridge.JSApplicationCausedNativeException
            if (r1 == 0) goto L_0x0038
            goto L_0x0041
        L_0x0038:
            int r14 = s
            long r1 = (long) r14
            int r14 = t
            long r3 = (long) r14
        L_0x003e:
            r9 = r1
            r11 = r3
            goto L_0x005f
        L_0x0041:
            if (r14 == 0) goto L_0x004c
            long r1 = r14.getPluginId()
            long r3 = r14.getPackageId()
            goto L_0x003e
        L_0x004c:
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r14 = r13.D
            if (r14 == 0) goto L_0x005d
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r14 = r13.D
            long r1 = r14.f()
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r14 = r13.D
            long r3 = r14.g()
            goto L_0x003e
        L_0x005d:
            r9 = r2
            r11 = r9
        L_0x005f:
            java.io.StringWriter r14 = new java.io.StringWriter
            r14.<init>()
            java.io.PrintWriter r1 = new java.io.PrintWriter
            r1.<init>(r14)
            r15.printStackTrace(r1)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r1 = "===plugin crash===\npluginType=ReactNative\npluginId="
            r15.append(r1)
            r15.append(r9)
            java.lang.String r1 = "\npkgId="
            r15.append(r1)
            r15.append(r11)
            java.lang.String r1 = "\n"
            r15.append(r1)
            java.lang.String r15 = r15.toString()
            com.xiaomi.smarthome.setting.PluginSetting$RnSdkInfo r1 = r13.B
            if (r1 == 0) goto L_0x00d7
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            java.lang.String r15 = "rnSdkApiLevel="
            r1.append(r15)
            com.xiaomi.smarthome.setting.PluginSetting$RnSdkInfo r15 = r13.B
            long r2 = r15.f22090a
            r1.append(r2)
            java.lang.String r15 = "\nrnSdkBuildTime="
            r1.append(r15)
            com.xiaomi.smarthome.setting.PluginSetting$RnSdkInfo r15 = r13.B
            long r2 = r15.b
            r1.append(r2)
            java.lang.String r15 = "\nrnSdkIsInner="
            r1.append(r15)
            com.xiaomi.smarthome.setting.PluginSetting$RnSdkInfo r15 = r13.B
            boolean r15 = r15.e
            r1.append(r15)
            java.lang.String r15 = "\nrnSdkInnerApiLevel="
            r1.append(r15)
            r15 = 10034(0x2732, float:1.406E-41)
            r1.append(r15)
            java.lang.String r15 = "\nAppVersion="
            r1.append(r15)
            java.lang.String r15 = com.xiaomi.smarthome.globalsetting.GlobalSetting.o
            r1.append(r15)
            java.lang.String r15 = "\n"
            r1.append(r15)
            java.lang.String r15 = r1.toString()
        L_0x00d7:
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r13.E
            if (r1 == 0) goto L_0x0115
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            java.lang.String r15 = "model="
            r1.append(r15)
            com.xiaomi.smarthome.device.api.DeviceStat r15 = r13.E
            java.lang.String r15 = r15.model
            r1.append(r15)
            java.lang.String r15 = "\n"
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            java.lang.String r15 = "did="
            r1.append(r15)
            com.xiaomi.smarthome.device.api.DeviceStat r15 = r13.E
            java.lang.String r15 = r15.did
            r1.append(r15)
            java.lang.String r15 = "\n"
            r1.append(r15)
            java.lang.String r15 = r1.toString()
        L_0x0115:
            r1 = 1
            int r2 = r13.P     // Catch:{ Exception -> 0x01f1 }
            int r2 = r2 + r1
            r13.P = r2     // Catch:{ Exception -> 0x01f1 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f1 }
            r2.<init>()     // Catch:{ Exception -> 0x01f1 }
            r2.append(r15)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = "userId="
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            com.xiaomi.smarthome.frame.core.CoreApi r3 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = r3.s()     // Catch:{ Exception -> 0x01f1 }
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x01f1 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ef }
            r15.<init>()     // Catch:{ Exception -> 0x01ef }
            r15.append(r2)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r3 = "debug="
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            boolean r3 = com.xiaomi.smarthome.globalsetting.GlobalSetting.j     // Catch:{ Exception -> 0x01ef }
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r3 = "\n"
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x01ef }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f1 }
            r2.<init>()     // Catch:{ Exception -> 0x01f1 }
            r2.append(r15)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = "isGrayPublish="
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            boolean r3 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x01f1 }
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x01f1 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ef }
            r15.<init>()     // Catch:{ Exception -> 0x01ef }
            r15.append(r2)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r3 = "reportTime="
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x01ef }
            java.lang.String r4 = "yyyy-MM-dd HH:mm:ss"
            r3.<init>(r4)     // Catch:{ Exception -> 0x01ef }
            java.util.Date r4 = new java.util.Date     // Catch:{ Exception -> 0x01ef }
            r4.<init>()     // Catch:{ Exception -> 0x01ef }
            java.lang.String r3 = r3.format(r4)     // Catch:{ Exception -> 0x01ef }
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r3 = "\n"
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x01ef }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f1 }
            r2.<init>()     // Catch:{ Exception -> 0x01f1 }
            r2.append(r15)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = "reportIndex="
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            int r3 = r13.P     // Catch:{ Exception -> 0x01f1 }
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x01f1 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ef }
            r15.<init>()     // Catch:{ Exception -> 0x01ef }
            r15.append(r2)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r3 = "currentPluginProcessName="
            r15.append(r3)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r0 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((android.content.Context) r0)     // Catch:{ Exception -> 0x01ef }
            r15.append(r0)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r0 = "\n"
            r15.append(r0)     // Catch:{ Exception -> 0x01ef }
            java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x01ef }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f1 }
            r0.<init>()     // Catch:{ Exception -> 0x01f1 }
            r0.append(r15)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r2 = "launchErrorCode="
            r0.append(r2)     // Catch:{ Exception -> 0x01f1 }
            int r2 = r13.r     // Catch:{ Exception -> 0x01f1 }
            r0.append(r2)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ Exception -> 0x01f1 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01f1 }
            goto L_0x01f2
        L_0x01ef:
            r0 = r2
            goto L_0x01f2
        L_0x01f1:
            r0 = r15
        L_0x01f2:
            java.lang.String r14 = r14.toString()
            java.lang.String r15 = com.xiaomi.smarthome.library.crypto.MD5Util.a((java.lang.String) r14)
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r13.O
            boolean r2 = r2.containsKey(r15)
            if (r2 != 0) goto L_0x025a
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r13.O
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            r2.put(r15, r3)
            int r15 = r13.P
            if (r15 <= r1) goto L_0x022b
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "crashIndex:"
            r14.append(r15)
            int r15 = r13.P
            r14.append(r15)
            java.lang.String r15 = ",crashIndex >1,needn't report(only report the 1st crash)"
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r14)
            return
        L_0x022b:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r0)
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            int r15 = r14.length()
            r0 = 5000(0x1388, float:7.006E-42)
            if (r15 <= r0) goto L_0x0247
            r15 = 0
            java.lang.String r14 = r14.substring(r15, r0)
        L_0x0247:
            r6 = r14
            com.xiaomi.smarthome.frame.crash.FrameCrashApi r2 = com.xiaomi.smarthome.frame.crash.FrameCrashApi.a()
            android.content.Context r3 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r4 = "@ExceptionFromReactNative"
            java.lang.String r5 = "@ExceptionFromReactNative"
            java.lang.String r8 = com.xiaomi.smarthome.frame.HostSetting.j
            r2.a(r3, r4, r5, r6, r7, r8, r9, r11)
            return
        L_0x025a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.RNRuntime.a(com.xiaomi.plugin.core.XmPluginPackage, java.lang.Throwable):void");
    }

    public RuntimeInfo b() {
        return this.S;
    }

    public void a(final int i2) {
        final Activity activity = this.A;
        if (activity != null) {
            final ReactRootView reactRootView = this.w;
            if (reactRootView.getChildCount() == 0) {
                reactRootView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                    public void onChildViewRemoved(View view, View view2) {
                    }

                    public void onChildViewAdded(View view, View view2) {
                        reactRootView.setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
                        reactRootView.post(new Runnable() {
                            public void run() {
                                ((PluginRNActivity) activity).onPageResume(reactRootView, i2);
                            }
                        });
                    }
                });
            } else {
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        ((PluginRNActivity) activity).onPageResume(reactRootView, i2);
                    }
                });
            }
        }
    }

    public void b(final int i2) {
        final Activity activity = this.A;
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    ((PluginRNActivity) activity).onPagePause(RNRuntime.this.w, i2);
                }
            });
        }
    }

    public final class RuntimeInfo {
        private RuntimeInfo() {
        }

        public String a() {
            return RNRuntime.this.M;
        }

        public File b() {
            return MIOTPackageModule.getPluginDir(RNRuntime.this.l().getCurrentReactContext(), RNRuntime.this.e());
        }

        public File c() {
            return MIOTPackageModule.getPluginFilesPath(b());
        }

        public File d() {
            return MIOTPackageModule.getPluginDatabasePath(b());
        }

        public PluginSetting.RnSdkInfo e() {
            return RNRuntime.this.B;
        }
    }

    public byte c() {
        return this.N;
    }

    public void d() {
        if (this.z == null) {
            PluginSetting.a("runtime init");
            FrescoInitial.a(false);
            this.N = 1;
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    byte unused = RNRuntime.this.N = (byte) -1;
                    Log.e(thread.getName(), AppMeasurement.Param.FATAL, th);
                    ReactInstanceManager unused2 = RNRuntime.this.z = null;
                    long unused3 = RNRuntime.this.K = -1;
                    long unused4 = RNRuntime.this.L = -1;
                    DeviceStat unused5 = RNRuntime.this.E = null;
                    PluginRecord unused6 = RNRuntime.this.D = null;
                    String unused7 = RNRuntime.this.M = null;
                    XmPluginHostApi.instance().log("ReactInstanceManager init error", Log.getStackTraceString(th));
                    SHApplication.getAppContext().unregisterReceiver(RNRuntime.this.Q);
                    Thread.setDefaultUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
                }
            });
            this.K = -1;
            this.L = -1;
            this.E = null;
            this.D = null;
            this.M = null;
            final PluginSetting.RnSdkInfo a2 = PluginManager.a().a(SHApplication.getAppContext());
            this.B = a2;
            PluginSetting.a("runtime got sdk info at init", Long.valueOf(this.B.f22090a), this.B.c);
            this.C = null;
            JSCManager.a().b();
            this.G = DevelopSharePreManager.a().g();
            RnPluginLog.a(this.G);
            boolean t2 = t();
            String str = f17249a;
            Log.i(str, "init> isRnCanDebug:" + t2 + " rnSdkInfo:" + a2.f22090a);
            ReactInstanceManagerBuilder builder = ReactInstanceManager.builder();
            this.x = new MIOTReactNativeSDKPackage(t2);
            builder.setApplication(SHApplication.getApplication()).addPackage(new FixBugMainReactPackage()).addPackage(new ReactNativeLocalizationPackage()).addPackage(new SvgPackage()).addPackage(new OrientationPackage()).addPackage(new RNGLPackage()).addPackage(new ReactVideoPackage()).addPackage(new ReactNativeContacts()).addPackage(new ImagePickerPackage()).addPackage(new MIOTSQLitePackage()).addPackage(new LottiePackage()).addPackage(new RNNordicDfuPackage()).addPackage(new RNCameraPackage()).addPackage(new LinearGradientPackage()).addPackage(new BlurViewPackage()).addPackage(new MiotMapSweeperViewPackage()).addPackage(new MiotCameraViewPackage()).addPackage(this.x).addPackage(new RNCWebViewPackage()).addPackage(new AsyncStoragePackage()).addPackage(new NetInfoPackage()).addPackage(new RNCViewPagerPackage()).addPackage(new GeolocationPackage()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE).setDefaultHardwareBackBtnHandler(this.R).setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler() {
                public void handleException(Exception exc) {
                    Log.e("ReactNativeJS", "NativeModuleCallExceptionHandler,  process name is " + MIOTUtils.a((Context) SHApplication.getApplication()), exc);
                    if (RNRuntime.this.D != null) {
                        XmPluginPackage xmPluginPackage = PluginRuntimeManager.getInstance().getXmPluginPackage(RNRuntime.this.D.o());
                        if (RNRuntime.this.G || GlobalSetting.q || GlobalSetting.s) {
                            PluginErrorInfoActivity.showErrorInfo(SHApplication.getAppContext(), xmPluginPackage, exc);
                        } else {
                            if (RNRuntime.this.K <= 0) {
                                int unused = RNRuntime.this.r = RNRuntime.this.r | 1;
                            }
                            RNRuntime.this.a(xmPluginPackage, (Throwable) exc);
                        }
                    }
                    XmPluginHostApi instance = XmPluginHostApi.instance();
                    instance.log("RNRuntime.handleException", "sdkpath:" + RNRuntime.this.g() + "\npluginpath:" + RNRuntime.this.h());
                }
            }).setUseDeveloperSupport(t2);
            if (t2) {
                builder.setJSMainModulePath("projects/" + this.y + PushRouteUtil.f);
                this.z = builder.build();
            } else {
                builder.setJSBundleLoader(new JSBundleLoader() {
                    public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                        RnPluginLog.a("1、 will loadScript rn sdk bundle...");
                        RNRuntime.this.a(jSBundleLoaderDelegate, a2);
                        return "";
                    }
                });
                try {
                    this.z = builder.build();
                    if (this.w != null) {
                        this.w.unmountReactApplication();
                        this.w = null;
                    }
                    new ReactRootView(SHApplication.getAppContext()).startReactApplication(this.z, "sdk");
                    LogUtil.c("PluginStartTime", "initRnRuntime  " + System.currentTimeMillis() + "  " + this.B.d);
                } catch (Exception e2) {
                    Log.i(f17249a, "failed to build ReactInstanceManager", e2);
                    return;
                }
            }
            this.z.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                public void onReactContextInitialized(ReactContext reactContext) {
                    Thread.setDefaultUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
                    byte unused = RNRuntime.this.N = (byte) 2;
                    PluginSetting.a("runtime sdk ready");
                }
            });
        }
    }

    private boolean t() {
        return this.G && this.H;
    }

    public PluginRecord e() {
        return this.D;
    }

    public DeviceStat f() {
        return this.E;
    }

    public String g() {
        return this.B.d;
    }

    public String h() {
        return this.C;
    }

    public Bundle i() {
        return this.F;
    }

    public void a(DeviceStat deviceStat, PluginRecord pluginRecord, Bundle bundle) {
        this.E = deviceStat;
        this.D = pluginRecord;
        this.F = bundle;
    }

    public void j() {
        try {
            Class<?> cls = Class.forName(PluginRNActivity.class.getCanonicalName());
            String str = f17249a;
            LogUtil.a(str, "preload class:" + cls.getCanonicalName());
        } catch (ClassNotFoundException e2) {
            String str2 = f17249a;
            LogUtil.e(str2, "preloadRNActivityClass:" + Log.getStackTraceString(e2));
        }
        if (LoadingBaseActivity.preloadContentView()) {
            LogUtil.a(f17249a, "preload LoadingBaseActivity contentView finished");
        }
    }

    public void k() {
        b(this.E, this.D, this.F);
    }

    public void b(final DeviceStat deviceStat, final PluginRecord pluginRecord, Bundle bundle) {
        String str;
        PluginSetting.a(PluginSetting.k);
        this.F = bundle;
        if (this.G) {
            DevLoadingViewController.setDevLoadingEnabled(false);
            this.y = DevelopSharePreManager.a().j();
            this.H = false;
            if (RnPluginDebugDeviceMock.f22091a.equals(deviceStat.model)) {
                RnPluginLog.a("RNRuntime  mock device, 仅供UI调试...");
                this.H = true;
                str = DevelopSharePreManager.a().i();
            } else {
                JSONObject a2 = RnDebugFileUtil.a(deviceStat.model);
                if (a2 != null) {
                    this.H = a2.optBoolean(RnDebugConstant.c);
                    str = a2.optString(RnDebugConstant.f16953a);
                } else {
                    this.H = false;
                    str = pluginRecord.h().h();
                }
            }
            RnPluginLog.a("RNRuntime  调试的插件包： " + str + "   model: " + deviceStat.model);
            this.y = str;
            if (this.H) {
                a(deviceStat, pluginRecord);
            } else {
                b(deviceStat, pluginRecord);
                if (DevelopSharePreManager.a().h()) {
                    if (this.B != null) {
                        ToastUtil.a((CharSequence) "api_level=" + this.B.f22090a + "  is_inner_sdk=" + this.B.e + "  build_time=" + this.B.b, 1);
                    } else {
                        ToastUtil.a((CharSequence) "加载 rn sdk error");
                    }
                }
            }
            this.x.a(this.H);
        } else {
            b(deviceStat, pluginRecord);
        }
        if (this.w != null) {
            this.w.unmountReactApplication();
        }
        String str2 = f17249a;
        Log.i(str2, "start react app > " + this.y);
        this.w = new ReactRootView(SHApplication.getAppContext());
        this.w.startReactApplication(this.z, this.y);
        if (this.z.getCurrentReactContext() == null) {
            this.z.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                public void onReactContextInitialized(ReactContext reactContext) {
                    RNRuntime.this.c(deviceStat, pluginRecord);
                    PluginSetting.a("runtime plugin ready");
                }
            });
        } else {
            c(deviceStat, pluginRecord);
        }
        XmPluginHostApi instance = XmPluginHostApi.instance();
        String o2 = pluginRecord.o();
        instance.logByModel(o2, "RNRuntime.initRuntime:" + this.z.getCurrentReactContext() + "\npluginpath:" + h());
    }

    private void a(DeviceStat deviceStat, PluginRecord pluginRecord) {
        this.z = null;
        d();
        PluginPackageInfo h2 = pluginRecord.h();
        this.C = PluginManager.a(h2.f()) + File.separator;
        h2.b(this.y);
        this.E = deviceStat;
        this.D = pluginRecord;
        PluginPackageInfo i2 = pluginRecord.i();
        if (i2 != null) {
            i2.b(this.y);
        }
        RnPluginLog.a("RNRuntime  initRuntime debug>" + this.y + ">" + this.C);
    }

    private void b(DeviceStat deviceStat, PluginRecord pluginRecord) {
        PluginSetting.RnSdkInfo rnSdkInfo = this.B;
        boolean z2 = false;
        PluginSetting.a("runtime got sdk info at initRuntime", Long.valueOf(this.B.f22090a), this.B.c);
        PluginPackageInfo h2 = pluginRecord.h();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        String h3 = h2.h();
        boolean isEmpty = TextUtils.isEmpty(this.y);
        if (this.E == null || deviceStat == null || deviceStat.did.equals(this.E.did)) {
            z2 = isEmpty;
        }
        this.y = h3;
        if (rnSdkInfo.f22090a > this.B.f22090a || !z2) {
            this.z = null;
            d();
        }
        this.C = PluginManager.a(h2.f()) + File.separator;
        RnPluginLog.a("RnPluginInfo:  sdkInfo=" + this.B.toString() + "   PluginBundlePath=" + this.C);
        this.E = deviceStat;
        this.D = pluginRecord;
        ReactContext currentReactContext = this.z.getCurrentReactContext();
        if (currentReactContext != null) {
            if (this.K > 0) {
                RnPluginLog.a("RNRuntime --> will send onPluginConfigUpdate event...");
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onPluginConfigUpdate", this.x.b());
            } else {
                RnPluginLog.b("RNRuntime --> not send onPluginConfigUpdate event...");
            }
            CatalystInstance catalystInstance = currentReactContext.getCatalystInstance();
            if (catalystInstance != null) {
                a((JSBundleLoaderDelegate) (CatalystInstanceImpl) catalystInstance, rnSdkInfo);
            }
        }
    }

    public ReactInstanceManager l() {
        return this.z;
    }

    public void a(Activity activity) {
        this.A = activity;
        this.x.a();
        if (t()) {
            try {
                this.z.onHostResume(activity, this.R);
            } catch (Exception e2) {
                Log.i(f17249a, "failed to resume the activity on registry", e2);
            }
        }
    }

    public void m() {
        this.A = null;
    }

    public void a(RNStateListener rNStateListener) {
        if (rNStateListener == null) {
            return;
        }
        if (n()) {
            try {
                rNStateListener.a(this.w, this.z, this.E, this.D);
            } catch (Exception e2) {
                Log.i(f17249a, "failed to register listener", e2);
            }
        } else if (this.I == null) {
            this.I = new ArrayList<>();
            this.I.add(rNStateListener);
        } else if (!this.I.contains(rNStateListener)) {
            this.I.add(rNStateListener);
        }
    }

    public boolean n() {
        return (this.D == null || this.E == null || this.z == null || this.z.getCurrentReactContext() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void c(DeviceStat deviceStat, PluginRecord pluginRecord) {
        if (this.I != null) {
            for (int i2 = 0; i2 < this.I.size(); i2++) {
                RNStateListener rNStateListener = this.I.get(i2);
                if (rNStateListener != null) {
                    rNStateListener.a(this.w, this.z, deviceStat, pluginRecord);
                }
            }
            this.I = null;
        }
    }

    public void a(boolean z2) {
        try {
            if (this.A == null) {
                return;
            }
            if (z2) {
                this.A.getWindow().addFlags(128);
            } else {
                this.A.getWindow().clearFlags(128);
            }
        } catch (Throwable th) {
            LogUtil.b(PluginRNActivity.TAG, th.toString());
        }
    }

    public void o() {
        if (this.A != null) {
            this.A.finish();
        }
    }

    public void a(String str, String str2, ReadableArray readableArray, String str3) {
        if (this.A != null) {
            String a2 = MIOTUtils.a(readableArray);
            Intent intent = new Intent(this.A, CommonShareActivity.class);
            intent.putExtra(CommonShareActivity.START_ACTIVITY_FROM, "rn");
            intent.putExtra("ShareTitle", str);
            intent.putExtra("ShareContent", str2);
            if (a2 != null && a2.startsWith("file://")) {
                a2 = a2.substring(7);
            }
            if (!TextUtils.isEmpty(a2)) {
                if (a2.endsWith(".zip")) {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, a2);
                } else {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_URL_NO_ZIP, a2);
                }
            }
            DeviceStat f2 = f();
            if (f2 != null) {
                intent.putExtra(CommonShareActivity.SHARE_DEVICE_MODEL, f2.model);
            }
            intent.putExtra(CommonShareActivity.SHARE_URL, str3);
            this.A.startActivity(intent);
        }
    }

    public final void a(String str) {
        if (this.A != null) {
            ShareDeviceActivity.openShareDeviceActivity(this.A, str);
        }
    }

    public final void b(String str) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, DeviceRoomAddActivity.class);
            intent.putExtra("did", str);
            this.A.startActivity(intent);
        }
    }

    public void c(String str) {
        if (this.A != null) {
            FrameManager.b().k().openGatewaySubDeviceList(this.A, str);
        }
    }

    public final void d(String str) {
        if (this.A != null) {
            XmPluginHostApi.instance().gotoAuthManagerPage(this.A, str);
        }
    }

    public final void a(DeviceStat deviceStat) {
        if (this.A != null) {
            FrameManager.b().k().openSceneActivity(this.A, deviceStat, deviceStat.did);
        }
    }

    public final void p() {
        DeviceStat f2 = f();
        if (this.A != null && this.E != null) {
            FrameManager.b().k().openPowerSwitchNameActivity(this.A, f2.did, f2.mac);
        }
    }

    public void q() {
        DeviceStat f2 = f();
        if (this.A != null && f2 != null) {
            FrameManager.b().k().openBtGatewayActivity(this.A, f2.did);
        }
    }

    public void r() {
        if (this.A != null) {
            this.A.startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
        }
    }

    public final void a(String str, String str2) {
        if (this.A != null) {
            XmPluginHostApi.instance().gotoFeedback(this.A, str, str2, PluginRuntimeManager.getInstance().getXmPluginPackage(str));
        }
    }

    public final void e(String str) {
        if (this.A != null) {
            SecuritySettingActivity.startActivity(this.A, str, "");
        }
    }

    public final void b(String str, String str2) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, FeedbackCommonProblemActivity.class);
            intent.putExtra("did", str2);
            intent.putExtra("extra_model", str);
            this.A.startActivity(intent);
        }
    }

    public final void f(String str) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, BleGatewayActivity.class);
            intent.putExtra(BleGatewayListActivity.KEY_GATEWAY_DID, str);
            this.A.startActivity(intent);
        }
    }

    public final void a(int i2, String[] strArr, Bundle bundle) {
        if (this.A != null && this.E != null) {
            IRMatchingDeviceTypeActivity.showMatchingDeviceTypeActivity(this.A, this.E, i2, strArr, bundle);
        }
    }

    public final void a(String str, String str2, ReadableArray readableArray, String str3, ReadableArray readableArray2) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, LicenseAndPrivacyActivity.class);
            intent.putExtra(DeviceMoreActivity.ARGS_LICENSE_URI, MIOTUtils.a(readableArray));
            intent.putExtra(DeviceMoreActivity.ARGS_PRIVACY_URI, MIOTUtils.a(readableArray2));
            intent.putExtra(LicenseAndPrivacyActivity.ARGS_LICENSE_TITLE, str2);
            intent.putExtra(LicenseAndPrivacyActivity.ARGS_PRIVACY_TITLE, str3);
            intent.putExtra("did", str);
            this.A.startActivityForResult(intent, 100);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r14, java.lang.String r15, com.facebook.react.bridge.ReadableArray r16, java.lang.String r17, com.facebook.react.bridge.ReadableArray r18, com.facebook.react.bridge.Callback r19) {
        /*
            r13 = this;
            r0 = r13
            r1 = r19
            android.app.Activity r2 = r0.A
            if (r2 == 0) goto L_0x0074
            java.lang.String r2 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableArray) r16)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            java.lang.String r3 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableArray) r18)
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0060
            com.xiaomi.smarthome.frame.FrameManager r3 = com.xiaomi.smarthome.frame.FrameManager.b()
            com.xiaomi.smarthome.frame.plugin.host.PluginActivityHostApi r4 = r3.k()
            android.app.Activity r5 = r0.A
            r6 = 0
            if (r2 == 0) goto L_0x002a
            java.lang.String r2 = ""
        L_0x0028:
            r7 = r2
            goto L_0x003b
        L_0x002a:
            boolean r2 = android.text.TextUtils.isEmpty(r15)
            if (r2 == 0) goto L_0x003a
            android.app.Activity r2 = r0.A
            r3 = 2131494750(0x7f0c075e, float:1.8613017E38)
            java.lang.String r2 = r2.getString(r3)
            goto L_0x0028
        L_0x003a:
            r7 = r15
        L_0x003b:
            java.lang.String r8 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableArray) r16)
            boolean r2 = android.text.TextUtils.isEmpty(r17)
            if (r2 == 0) goto L_0x0050
            android.app.Activity r2 = r0.A
            r3 = 2131494771(0x7f0c0773, float:1.861306E38)
            java.lang.String r2 = r2.getString(r3)
            r9 = r2
            goto L_0x0052
        L_0x0050:
            r9 = r17
        L_0x0052:
            java.lang.String r10 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableArray) r18)
            com.xiaomi.smarthome.framework.plugin.rn.RNRuntime$11 r11 = new com.xiaomi.smarthome.framework.plugin.rn.RNRuntime$11
            r11.<init>(r1)
            r12 = r14
            r4.showUserLicenseUriDialog(r5, r6, r7, r8, r9, r10, r11, r12)
            goto L_0x0074
        L_0x0060:
            com.xiaomi.smarthome.frame.FrameManager r2 = com.xiaomi.smarthome.frame.FrameManager.b()
            com.xiaomi.smarthome.frame.plugin.host.PluginActivityHostApi r2 = r2.k()
            android.app.Activity r3 = r0.A
            r4 = 0
            com.xiaomi.smarthome.framework.plugin.rn.RNRuntime$12 r5 = new com.xiaomi.smarthome.framework.plugin.rn.RNRuntime$12
            r5.<init>(r1)
            r1 = r14
            r2.showUserLicenseDialog(r3, r4, r5, r14)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.RNRuntime.a(java.lang.String, java.lang.String, com.facebook.react.bridge.ReadableArray, java.lang.String, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.Callback):void");
    }

    public final void g(String str) {
        if (this.A != null) {
            FrameManager.b().k().goBleMeshDeviceUpdateActivity(this.A, str);
        }
    }

    public final void a(int i2, String str, String str2, String str3, String str4) {
        if (this.A != null) {
            FrameManager.b().k().goBleOtaDeviceUpdateActivity(this.A, i2, str, str2, str3, str4);
        }
    }

    public final void h(String str) {
        if (this.A != null) {
            FrameManager.b().k().goUpdateActivity(this.A, str);
        }
    }

    public final void i(String str) {
        if (this.A != null) {
            XmPluginHostApi.instance().createDeviceGroup(this.A, str);
        }
    }

    public final void a(ReadableArray readableArray) {
        ArrayList list;
        if (this.A != null && (list = Arguments.toList(readableArray)) != null) {
            Intent intent = new Intent(this.A, ModifyGroupActivity.class);
            intent.putExtra("group_device_ids", (String[]) list.toArray(new String[list.size()]));
            intent.putExtra("from", ModifyGroupActivity.PLUGIN_GROUP_DEVICE);
            this.A.startActivity(intent);
        }
    }

    public final void c(String str, String str2) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, ModifyGroupActivity.class);
            intent.putExtra("group_model", str);
            intent.putExtra("masterDid", str2);
            intent.putExtra("from", ModifyGroupActivity.PLUGIN_GROUP_DEVICE);
            this.A.startActivity(intent);
        }
    }

    public final void a(String str, String str2, String str3, String str4, String str5) {
        if (this.A != null) {
            FrameManager.b().k().startSetTimerList(this.A, str, str2, str3, str4, str5, "", "");
        }
    }

    public final void b(String str, String str2, String str3, String str4, String str5) {
        if (this.A != null) {
            FrameManager.b().k().startSetTimerList(this.A, str, str2, str3, str4, str5, "", "");
        }
    }

    public final void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.A != null) {
            FrameManager.b().k().startSetTimerList(this.A, str, str3, str4, str5, str6, str2, "", "");
        }
    }

    public final void a(String str, String str2, String str3, String str4, String str5, boolean z2) {
        if (this.A != null) {
            FrameManager.b().k().startSetTimerCountDown(this.A, str, str2, str3, str4, str5, z2);
        }
    }

    public final void a(String str, final String str2, final int i2) {
        if (this.A != null) {
            if (TextUtils.isEmpty(str)) {
                str = this.A.getString(R.string.smarthome_delete_tips);
            }
            new MLAlertDialog.Builder(this.A).b((CharSequence) str).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!TextUtils.equals(CoreApi.a().s(), "923522198") || !TextUtils.equals(str2, "658906")) {
                        final XQProgressDialog xQProgressDialog = new XQProgressDialog(RNRuntime.this.A);
                        xQProgressDialog.setMessage(RNRuntime.this.A.getString(R.string.smarthome_deleting));
                        xQProgressDialog.setCancelable(false);
                        xQProgressDialog.show();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str2);
                        if (PluginServiceManager.a().b() != null) {
                            try {
                                PluginServiceManager.a().b().delDeviceBatch(arrayList, new IPluginCallback.Stub() {
                                    public void onRequestSuccess(String str) {
                                        SHApplication.getGlobalHandler().post(new Runnable() {
                                            public void run() {
                                                Toast.makeText(RNRuntime.this.A, R.string.smarthome_device_delete_success, 0).show();
                                                xQProgressDialog.dismiss();
                                                Intent intent = new Intent();
                                                intent.putExtra("result_data", 1);
                                                RNRuntime.this.A.setResult(-1, intent);
                                                RNRuntime.this.A.finish();
                                                if (i2 == Device.PID_VIRTUAL_GROUP) {
                                                    SHApplication.getGlobalHandler().postDelayed($$Lambda$RNRuntime$14$1$1$bdEKG4KBX_FSwdK3NfrcXW64VzY.INSTANCE, 300);
                                                }
                                            }

                                            /* access modifiers changed from: private */
                                            public static /* synthetic */ void a() {
                                                try {
                                                    PluginServiceManager.a().b().updateDeviceList((IPluginCallback) null);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }

                                    public void onRequestFailed(int i, String str) {
                                        SHApplication.getGlobalHandler().post(new Runnable() {
                                            public void run() {
                                                Toast.makeText(RNRuntime.this.A, R.string.smarthome_device_delete_fail, 0).show();
                                                xQProgressDialog.dismiss();
                                            }
                                        });
                                    }
                                });
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        Toast.makeText(RNRuntime.this.A, R.string.smarthome_device_delete_fail, 0).show();
                    }
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                }
            }).d();
        }
    }

    public final void a(String str, String str2, int i2, String str3, Callback callback) {
        if (this.A != null) {
            final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(this.A).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
            final EditText editText = clientRemarkInputView.getEditText();
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
            MLAlertDialog d2 = new MLAlertDialog.Builder(this.A).a((int) R.string.smarthome_device_rename).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    clientRemarkInputView.onConfirm(dialogInterface);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                }
            }).d();
            final String str4 = str;
            final Callback callback2 = callback;
            final int i3 = i2;
            final String str5 = str2;
            final String str6 = str3;
            clientRemarkInputView.initialize(new ClientRemarkInputView.RenameInterface() {
                public void modifyBackName(final String str) {
                    final XQProgressDialog xQProgressDialog = new XQProgressDialog(RNRuntime.this.A);
                    xQProgressDialog.setMessage(RNRuntime.this.A.getString(R.string.changeing_back_name));
                    xQProgressDialog.setCancelable(false);
                    xQProgressDialog.show();
                    XmPluginHostApi.instance().modDeviceName(str4, str, new com.xiaomi.smarthome.device.api.Callback<Void>() {
                        private void a(String str, int i) {
                            Intent intent = new Intent(XmBluetoothManager.ACTION_RENAME_NOTIFY);
                            intent.putExtra("did", str4);
                            intent.putExtra(XmBluetoothManager.EXTRA_NAME, str);
                            intent.putExtra("extra.result", i);
                            RNRuntime.this.A.sendBroadcast(intent);
                        }

                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            a(str, 1);
                            xQProgressDialog.dismiss();
                            callback2.invoke(str);
                            if (i3 == Device.PID_BLUETOOTH) {
                                BleCacheUtils.a(str5, str);
                            }
                            Toast.makeText(RNRuntime.this.A, R.string.change_back_name_success, 0).show();
                        }

                        public void onFailure(int i, String str) {
                            a(str6, 0);
                            xQProgressDialog.dismiss();
                            Toast.makeText(RNRuntime.this.A, R.string.change_back_name_fail, 0).show();
                        }
                    });
                }
            }, d2, str3, str3, false);
            final Button button = d2.getButton(-1);
            button.setEnabled(false);
            button.setTextColor(this.A.getResources().getColor(R.color.std_list_subtitle));
            clientRemarkInputView.getEditText().addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    clientRemarkInputView.setAlertText("");
                    button.setEnabled(true);
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    String obj = editText.getText().toString();
                    if (obj.length() <= 0) {
                        button.setEnabled(false);
                        button.setTextColor(RNRuntime.this.A.getResources().getColor(R.color.std_list_subtitle));
                    } else if (StringUtil.u(obj)) {
                        clientRemarkInputView.setAlertText(RNRuntime.this.A.getString(R.string.tag_save_data_description));
                        button.setEnabled(false);
                        button.setTextColor(RNRuntime.this.A.getResources().getColor(R.color.std_list_subtitle));
                    } else if (!HomeManager.A(obj)) {
                        clientRemarkInputView.setAlertText(RNRuntime.this.A.getString(R.string.room_name_too_long));
                        button.setEnabled(false);
                        button.setTextColor(RNRuntime.this.A.getResources().getColor(R.color.std_list_subtitle));
                    } else {
                        clientRemarkInputView.setAlertText("");
                        button.setEnabled(true);
                        button.setTextColor(RNRuntime.this.A.getResources().getColor(R.color.std_dialog_button_green));
                    }
                }
            });
        }
    }

    public void a(String str, Dynamic dynamic) {
        if (this.A != null) {
            Intent intent = this.A.getIntent();
            try {
                ReadableType type = dynamic.getType();
                if (type == ReadableType.String) {
                    JSONObject optJSONObject = new JSONObject(dynamic.asString()).optJSONObject(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
                    String optString = optJSONObject.optString("value");
                    String optString2 = optJSONObject.optString("name");
                    RnPluginLog.a("自能场景 entrance: " + str + "  extra: " + dynamic.asString() + "  value:" + optString + "  keyName: " + optString2);
                    intent.putExtra("value", optString);
                    intent.putExtra("key_name", optString2);
                    intent.putExtra("isSaveEntrance", true);
                } else if (type == ReadableType.Map) {
                    ReadableMap f2 = MIOTUtils.f(dynamic.asMap(), MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
                    String a2 = MIOTUtils.a(f2, "value");
                    String a3 = MIOTUtils.a(f2, "name");
                    RnPluginLog.a("自能场景  entrance: " + str + "  value: " + a2 + "  keyName: " + a3);
                    intent.putExtra("value", a2);
                    intent.putExtra("key_name", a3);
                    intent.putExtra("isSaveEntrance", true);
                }
            } catch (Throwable th) {
                Log.e("ReactNativeJS", "MIOTPackage.setExitInfo", th);
            }
        }
    }

    public static Object a(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals(JSONObject.NULL)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                JSONArray jSONArray = new JSONArray();
                int length = Array.getLength(obj);
                for (int i2 = 0; i2 < length; i2++) {
                    jSONArray.put(a(Array.get(obj, i2)));
                }
                return jSONArray;
            } else if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            } else {
                if (!(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short)) {
                    if (!(obj instanceof String)) {
                        if (obj.getClass().getPackage().getName().startsWith("java.")) {
                            return obj.toString();
                        }
                        return null;
                    }
                }
                return obj;
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public WritableMap s() {
        WritableMap createMap = Arguments.createMap();
        if (this.F != null) {
            Bundle bundle = this.F;
            createMap.putInt("package_msgType", bundle.getInt("package_msgType", 1));
            createMap.putString("model", bundle.getString("model"));
            createMap.putString("did", bundle.getString("did"));
            createMap.putString(CommandMessage.COMMAND, bundle.getString("action"));
            createMap.putInt("id", bundle.getInt("actionId", -1));
            int i2 = bundle.getInt("scene_type", -1);
            if (i2 == 1) {
                String string = bundle.getString("last_value", "");
                if (TextUtils.isEmpty(string)) {
                    string = bundle.getString("value");
                }
                createMap.putString("value", string);
            } else {
                createMap.putString("value", bundle.getString("value"));
            }
            createMap.putString("plug_id", bundle.getString("plug_id"));
            createMap.putString("name", bundle.getString("name"));
            createMap.putString("tr_id", bundle.getString("tr_id"));
            createMap.putInt("scene_type", i2);
            createMap.putString("extra", bundle.getString("extra"));
        }
        return createMap;
    }

    @Deprecated
    public void j(String str) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, TimezoneActivity.class);
            intent.putExtra("extra_device_did", str);
            this.A.startActivityForResult(intent, 3);
        }
    }

    public void c(String str, String str2, String str3, String str4, String str5) {
        b(str, str2, str3, str4, str5, true);
    }

    public void b(String str, String str2, String str3, String str4, String str5, boolean z2) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, AiTrainingWebActivity.class);
            intent.putExtra("url", "https://i.ai.mi.com/mico#/skill/training");
            intent.putExtra(AiTrainingWebActivity.CLIENTID, str);
            intent.putExtra("did", str2);
            intent.putExtra(AiTrainingWebActivity.AICLIENTID, str4);
            intent.putExtra(AiTrainingWebActivity.AIMIOTCLIENTID, str3);
            intent.putExtra(AiTrainingWebActivity.AIVERSION, str5);
            intent.putExtra(AiTrainingWebActivity.SHOW_CLOSE_BTN, z2);
            this.A.startActivity(intent);
        }
    }

    public void d(String str, String str2) {
        if (this.A != null) {
            Intent intent = new Intent(this.A, InitDeviceRoomActivity.class);
            intent.putExtra("device_id", str2);
            this.A.startActivity(intent);
        }
    }

    public void k(String str) {
        if (this.A != null) {
            DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
            if (deviceByDid != null) {
                XmPluginHostApi.instance().createDeviceGroup(this.A, deviceByDid.model);
            } else {
                RnPluginLog.b("openCreateDeviceGroupPage-->deviceStat is null ");
            }
        }
    }

    public void l(String str) {
        if (this.A != null) {
            DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
            if (deviceByDid != null) {
                Intent intent = new Intent(this.A, ModifyGroupActivity.class);
                intent.putExtra("group_model", deviceByDid.model);
                intent.putExtra("masterDid", deviceByDid.did);
                intent.putExtra("from", ModifyGroupActivity.PLUGIN_GROUP_DEVICE);
                this.A.startActivity(intent);
                return;
            }
            RnPluginLog.b("openEditDeviceGroupPage-->deviceStat is null ");
        }
    }

    public void b(boolean z2) {
        if (this.A == null) {
            return;
        }
        if (z2) {
            this.A.getWindow().clearFlags(2048);
            this.A.getWindow().setFlags(1024, 1024);
            return;
        }
        this.A.getWindow().clearFlags(1024);
    }
}
