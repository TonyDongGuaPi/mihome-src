package com.xiaomi.smarthome.frame.plugin.runtime.bridge;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.google.android.exoplayer2.C;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.ExternalLoadManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.CoreBridge;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.IBridgeCallback;
import com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.frame.plugin.runtime.service.PluginHostService;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.stat.PluginStatReporter;
import com.xiaomi.smarthome.stat.report.StatLogSender;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PluginBridgeService extends Service {
    public static final String ACTION_PLUGIN_PUSH = "push_action_plugin";
    public static final String ACTION_PLUGIN_SCENE = "scene_action_plugin";
    public static final String EXTRA_CLICK_DEVICE_TIME = "extra_click_device_time";
    public static final String EXTRA_DEVICESTAT = "extra_devicestat";
    public static final String EXTRA_START_RNPLUGIN_ACTIVITY = "extra_start_rnplugin_activity";
    /* access modifiers changed from: private */
    public static AtomicInteger activityCount = null;
    static Map<String, DeviceStat> mCacheDeviceMap = new ConcurrentHashMap();
    public static volatile long mLoadTime = 0;
    static Map<String, StartServiceRecord> mStartServiceRecordMap = new ConcurrentHashMap();
    public static int msgType;
    public static RunningProcess process;
    Context mAppContext;
    /* access modifiers changed from: private */
    public volatile long mLauncherTime = 0;
    /* access modifiers changed from: private */
    public volatile long mOpenTime = 0;
    /* access modifiers changed from: private */
    public volatile boolean mProcessForeground;
    IBridgeServiceApi.Stub mStub = new IBridgeServiceApi.Stub() {
        public void sendMessage(String str, String str2, int i, Intent intent, boolean z, IBridgeCallback iBridgeCallback) throws RemoteException {
            final int i2 = i;
            PluginBridgeService.msgType = i2;
            LogUtil.c("PluginBridgeService", "sendMessage in  isCoreReady=" + CoreApi.a().l() + ",isPluginCacheReady=" + CoreApi.a().m() + " msgType:" + i);
            final String str3 = str;
            final IBridgeCallback iBridgeCallback2 = iBridgeCallback;
            final Intent intent2 = intent;
            final String str4 = str2;
            final boolean z2 = z;
            CoreApi.a().a(PluginBridgeService.this.mAppContext, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    LogUtil.c("PluginStartTime", "PluginBridgeService  " + System.currentTimeMillis());
                    CoreApi.a().a(PluginBridgeService.this.mAppContext, (CoreApi.IsPluginCacheReadyCallback) new CoreApi.IsPluginCacheReadyCallback() {
                        public void onPluginCacheReady() {
                            PluginBridgeService.this.mWorkerHandler.post(new Runnable() {
                                public void run() {
                                    Log.e("Device_Renderer", str3 + ", 2 - " + System.currentTimeMillis());
                                    final PluginRecord d = CoreApi.a().d(str3);
                                    if (d == null) {
                                        if (iBridgeCallback2 != null) {
                                            try {
                                                iBridgeCallback2.onFailure(new BridgeError(-1, "PluginRecord is null"));
                                            } catch (RemoteException unused) {
                                            }
                                        }
                                        LogUtilGrey.a("click_device_list", "PluginBridgerService.onPluginCacheReady record is null");
                                    } else if (!d.l()) {
                                        if (iBridgeCallback2 != null) {
                                            try {
                                                iBridgeCallback2.onFailure(new BridgeError(-1, "not installed"));
                                            } catch (RemoteException unused2) {
                                            }
                                        }
                                        LogUtilGrey.a("click_device_list", "PluginBridgerService.onPluginCacheReady record not installed");
                                    } else {
                                        if (i2 == 1) {
                                            PluginBridgeService.this.mUiHandler.post(new Runnable() {
                                                public void run() {
                                                    if (intent2 != null) {
                                                        intent2.setExtrasClassLoader(PluginBridgeService.this.getClassLoader());
                                                        long unused = PluginBridgeService.this.mOpenTime = intent2.getLongExtra("plugin_init_time", 0);
                                                    }
                                                }
                                            });
                                        }
                                        PluginBridgeService.mLoadTime = 0;
                                        LogUtil.c("install_package_path", d.w());
                                        if (d.h().q() || CoreBridge.a().a(str3)) {
                                            ((PluginHostApi) PluginHostApi.instance()).ensureService(new Callback() {
                                                public void onFailure(int i, String str) {
                                                }

                                                public void onSuccess(Object obj) {
                                                    PluginBridgeService.this.mUiHandler.post(new Runnable() {
                                                        public void run() {
                                                            if (!TextUtils.isEmpty(str4)) {
                                                                PluginBridgeService.this.loadRN(d, i2, XmPluginHostApi.instance().getDeviceByDid(str4), intent2, z2, iBridgeCallback2);
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        } else if (d.h().p()) {
                                            AnonymousClass1.this.loadApk(d, str3, i2, str4, intent2, z2, iBridgeCallback2);
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }

        public void loadApk(PluginRecord pluginRecord, String str, int i, String str2, Intent intent, boolean z, IBridgeCallback iBridgeCallback) {
            Intent intent2 = intent;
            IBridgeCallback iBridgeCallback2 = iBridgeCallback;
            final XmPluginPackage loadApk = PluginRuntimeManager.getInstance().loadApk(pluginRecord.h());
            if (loadApk == null || loadApk.xmPluginMessageReceiver == null) {
                if (iBridgeCallback2 != null) {
                    try {
                        iBridgeCallback2.onFailure(new BridgeError(-1, "xmPluginMessageReceiver is null"));
                    } catch (RemoteException unused) {
                    }
                }
                LogUtilGrey.a("click_device_list", "PluginBridgerService.onPluginCacheReady xmPluginMessageReceiver is null");
                return;
            }
            try {
                PluginRuntimeManager.clearViewBuffer();
                if (intent2 != null) {
                    intent2.setExtrasClassLoader(loadApk.getClassLoader());
                }
                final String str3 = str2;
                final IBridgeCallback iBridgeCallback3 = iBridgeCallback;
                final String str4 = str;
                final int i2 = i;
                final boolean z2 = z;
                final Intent intent3 = intent;
                ((PluginHostApi) PluginHostApi.instance()).ensureService(new Callback() {
                    public void onFailure(int i, String str) {
                    }

                    public void onSuccess(Object obj) {
                        PluginBridgeService.this.mUiHandler.post(new Runnable() {
                            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|(2:2|(4:4|(2:6|7)|8|10)(1:11))(1:12)|13|14|15|(1:17)|18|(1:20)(1:21)|22|(2:24|25)|26|27|(3:29|30|42)(1:41)) */
                            /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x00f5 */
                            /* JADX WARNING: Removed duplicated region for block: B:29:0x00fb A[SYNTHETIC, Splitter:B:29:0x00fb] */
                            /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                    r9 = this;
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this
                                    java.lang.String r0 = r3
                                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                                    r1 = -1
                                    if (r0 != 0) goto L_0x004a
                                    com.xiaomi.smarthome.device.api.XmPluginHostApi r0 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this
                                    java.lang.String r2 = r3
                                    com.xiaomi.smarthome.device.api.DeviceStat r0 = r0.getDeviceByDid(r2)
                                    com.xiaomi.smarthome.device.api.XmPluginHostApi r2 = com.xiaomi.smarthome.frame.plugin.host.PluginHostApi.instance()
                                    com.xiaomi.smarthome.frame.plugin.host.PluginHostApi r2 = (com.xiaomi.smarthome.frame.plugin.host.PluginHostApi) r2
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r3 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this
                                    java.lang.String r3 = r3
                                    r2.setCurrentDid(r3)
                                    if (r0 != 0) goto L_0x0042
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r0 = r4
                                    if (r0 == 0) goto L_0x003a
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError r0 = new com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError     // Catch:{ RemoteException -> 0x003a }
                                    java.lang.String r2 = "deviceStat is null"
                                    r0.<init>(r1, r2)     // Catch:{ RemoteException -> 0x003a }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r1 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ RemoteException -> 0x003a }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r1 = r4     // Catch:{ RemoteException -> 0x003a }
                                    r1.onFailure(r0)     // Catch:{ RemoteException -> 0x003a }
                                L_0x003a:
                                    java.lang.String r0 = "click_device_list"
                                    java.lang.String r1 = "PluginBridgerService.onPluginCacheReady deviceStat is null"
                                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)
                                    return
                                L_0x0042:
                                    java.util.Map<java.lang.String, com.xiaomi.smarthome.device.api.DeviceStat> r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.mCacheDeviceMap
                                    java.lang.String r3 = r0.did
                                    r2.put(r3, r0)
                                    goto L_0x004b
                                L_0x004a:
                                    r0 = 0
                                L_0x004b:
                                    r7 = r0
                                    java.lang.String r0 = "Device_Renderer"
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0103 }
                                    r2.<init>()     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r3 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    java.lang.String r3 = r5     // Catch:{ Throwable -> 0x0103 }
                                    r2.append(r3)     // Catch:{ Throwable -> 0x0103 }
                                    java.lang.String r3 = ", 3 - "
                                    r2.append(r3)     // Catch:{ Throwable -> 0x0103 }
                                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0103 }
                                    r2.append(r3)     // Catch:{ Throwable -> 0x0103 }
                                    java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0103 }
                                    android.util.Log.e(r0, r2)     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    int r0 = r6     // Catch:{ Throwable -> 0x0103 }
                                    r2 = 1
                                    if (r0 != r2) goto L_0x0081
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.this     // Catch:{ Throwable -> 0x0103 }
                                    long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0103 }
                                    long unused = r0.mLauncherTime = r2     // Catch:{ Throwable -> 0x0103 }
                                L_0x0081:
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    boolean r0 = r7     // Catch:{ Throwable -> 0x0103 }
                                    if (r0 == 0) goto L_0x00ab
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.plugin.core.XmPluginPackage r0 = r8     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.device.api.IXmPluginMessageReceiver r2 = r0.xmPluginMessageReceiver     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.this     // Catch:{ Throwable -> 0x0103 }
                                    android.content.Context r3 = r0.mAppContext     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.plugin.core.XmPluginPackage r4 = r8     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    int r5 = r6     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    android.content.Intent r6 = r9     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2$1$1 r8 = new com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2$1$1     // Catch:{ Throwable -> 0x0103 }
                                    r8.<init>()     // Catch:{ Throwable -> 0x0103 }
                                    boolean r0 = r2.handleMessage(r3, r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x0103 }
                                    goto L_0x00c9
                                L_0x00ab:
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.plugin.core.XmPluginPackage r0 = r8     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.device.api.IXmPluginMessageReceiver r2 = r0.xmPluginMessageReceiver     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.this     // Catch:{ Throwable -> 0x0103 }
                                    android.content.Context r3 = r0.mAppContext     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.plugin.core.XmPluginPackage r4 = r8     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    int r5 = r6     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r0 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    android.content.Intent r6 = r9     // Catch:{ Throwable -> 0x0103 }
                                    boolean r0 = r2.handleMessage(r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x0103 }
                                L_0x00c9:
                                    java.lang.String r2 = "PluginStartTime"
                                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0103 }
                                    r3.<init>()     // Catch:{ Throwable -> 0x0103 }
                                    java.lang.String r4 = "PluginMessageReceiver  "
                                    r3.append(r4)     // Catch:{ Throwable -> 0x0103 }
                                    long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0103 }
                                    r3.append(r4)     // Catch:{ Throwable -> 0x0103 }
                                    java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r2, r3)     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r2 = r4     // Catch:{ Throwable -> 0x0103 }
                                    if (r2 == 0) goto L_0x00f5
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ RemoteException -> 0x00f5 }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r2 = r4     // Catch:{ RemoteException -> 0x00f5 }
                                    android.os.Bundle r3 = new android.os.Bundle     // Catch:{ RemoteException -> 0x00f5 }
                                    r3.<init>()     // Catch:{ RemoteException -> 0x00f5 }
                                    r2.onSendSuccess(r3)     // Catch:{ RemoteException -> 0x00f5 }
                                L_0x00f5:
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ Throwable -> 0x0103 }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r2 = r4     // Catch:{ Throwable -> 0x0103 }
                                    if (r2 == 0) goto L_0x0143
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ RemoteException -> 0x0143 }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r2 = r4     // Catch:{ RemoteException -> 0x0143 }
                                    r2.onHandle(r0)     // Catch:{ RemoteException -> 0x0143 }
                                    goto L_0x0143
                                L_0x0103:
                                    r0 = move-exception
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ RemoteException -> 0x011a }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r2 = r4     // Catch:{ RemoteException -> 0x011a }
                                    if (r2 == 0) goto L_0x011a
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this     // Catch:{ RemoteException -> 0x011a }
                                    com.xiaomi.smarthome.frame.plugin.IBridgeCallback r2 = r4     // Catch:{ RemoteException -> 0x011a }
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError r3 = new com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError     // Catch:{ RemoteException -> 0x011a }
                                    java.lang.String r4 = r0.getMessage()     // Catch:{ RemoteException -> 0x011a }
                                    r3.<init>(r1, r4)     // Catch:{ RemoteException -> 0x011a }
                                    r2.onFailure(r3)     // Catch:{ RemoteException -> 0x011a }
                                L_0x011a:
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r1 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1 r1 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.this
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService r1 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.this
                                    android.content.Context r1 = r1.mAppContext
                                    com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService$1$2 r2 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.this
                                    com.xiaomi.plugin.core.XmPluginPackage r2 = r8
                                    com.xiaomi.smarthome.frame.plugin.debug.PluginErrorInfoActivity.showErrorInfo(r1, r2, r0)
                                    java.lang.String r1 = "click_device_list"
                                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                                    r2.<init>()
                                    java.lang.String r3 = "PluginBridgerService.onPluginCacheReady try-catch 222 "
                                    r2.append(r3)
                                    java.lang.String r0 = r0.getMessage()
                                    r2.append(r0)
                                    java.lang.String r0 = r2.toString()
                                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r1, r0)
                                L_0x0143:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass1.AnonymousClass2.AnonymousClass1.run():void");
                            }
                        });
                    }
                });
            } catch (Throwable th) {
                if (iBridgeCallback2 != null) {
                    try {
                        iBridgeCallback2.onFailure(new BridgeError(-1, th.getMessage()));
                    } catch (RemoteException unused2) {
                    }
                }
                LogUtilGrey.a("click_device_list", "PluginBridgerService.onPluginCacheReady try-catch 111 " + th.getMessage());
            }
        }

        public void startService(String str, long j, long j2, Intent intent, String str2) throws RemoteException {
            final long j3 = j2;
            final String str3 = str;
            final Intent intent2 = intent;
            final String str4 = str2;
            CoreApi.a().a(PluginBridgeService.this.mAppContext, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    if (CoreApi.a().a(j3) != null) {
                        Intent intent = new Intent();
                        intent.setClassName(PluginBridgeService.this.mAppContext.getPackageName(), str3);
                        if (intent2 != null) {
                            intent.putExtra(PluginHostService.PLUGIN_EXTRA_START_INTENT, intent2);
                            intent.addFlags(intent2.getFlags());
                        }
                        intent.putExtra(PluginHostService.PLUGIN_EXTRA_PACKAGE_ID, j3);
                        intent.putExtra(PluginHostService.PLUGIN_EXTRA_CLASS, str4);
                        boolean z = false;
                        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) PluginBridgeService.this.mAppContext.getSystemService("activity")).getRunningServices(200).iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().service.getClassName().equalsIgnoreCase(str3)) {
                                    z = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (!z && !PluginBridgeService.mStartServiceRecordMap.containsKey(str3)) {
                            StartServiceRecord startServiceRecord = new StartServiceRecord();
                            startServiceRecord.intent = intent;
                            PluginBridgeService.mStartServiceRecordMap.put(str3, startServiceRecord);
                            FrameManager.b().d().postDelayed(new Runnable() {
                                public void run() {
                                    PluginBridgeService.mStartServiceRecordMap.remove(str3);
                                }
                            }, 60000);
                        }
                        PluginBridgeService.this.mAppContext.startService(intent);
                    }
                }
            });
        }

        public void exitProcess() throws RemoteException {
            PluginBridgeService.this.mUiHandler.post(new Runnable() {
                public void run() {
                    System.exit(0);
                }
            });
        }

        public boolean isProcessForeground() {
            return PluginBridgeService.this.mProcessForeground;
        }
    };
    Handler mUiHandler;
    Handler mWorkerHandler;
    HandlerThread mWorkerThread;

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00b2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadRN(com.xiaomi.smarthome.core.entity.plugin.PluginRecord r7, int r8, com.xiaomi.smarthome.device.api.DeviceStat r9, android.content.Intent r10, boolean r11, com.xiaomi.smarthome.frame.plugin.IBridgeCallback r12) {
        /*
            r6 = this;
            r0 = 1
            if (r8 == r0) goto L_0x0090
            r1 = 3
            if (r8 != r1) goto L_0x0008
            goto L_0x0090
        L_0x0008:
            r1 = 2
            if (r8 != r1) goto L_0x005e
            if (r10 == 0) goto L_0x0045
            java.lang.String r1 = "extra_start_rnplugin_activity"
            boolean r1 = r10.getBooleanExtra(r1, r0)
            java.lang.String r2 = "type"
            java.lang.String r2 = r10.getStringExtra(r2)
            java.lang.String r3 = "PluginBridgeService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "loadRn-->msgType=PUSH_MESSAGE,  type"
            r4.append(r5)
            r4.append(r2)
            java.lang.String r5 = ", startRnPluginActivity:"
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.c(r3, r4)
            java.lang.String r3 = "ScenePush"
            boolean r2 = r3.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x0045
            if (r1 == 0) goto L_0x0045
            r6.openRnPluginActivity(r7, r8, r9, r10, r11, r12)
            return
        L_0x0045:
            android.content.Context r7 = r6.mAppContext
            android.support.v4.content.LocalBroadcastManager r7 = android.support.v4.content.LocalBroadcastManager.getInstance(r7)
            android.content.Intent r8 = new android.content.Intent
            r8.<init>()
            java.lang.String r11 = "push_action_plugin"
            android.content.Intent r8 = r8.setAction(r11)
            android.content.Intent r8 = r8.putExtras(r10)
            r7.sendBroadcast(r8)
            goto L_0x0093
        L_0x005e:
            r7 = 18
            if (r8 != r7) goto L_0x006a
            com.xiaomi.smarthome.core.server.CoreBridge r7 = com.xiaomi.smarthome.core.server.CoreBridge.a()
            r7.a((com.xiaomi.smarthome.device.api.DeviceStat) r9)
            goto L_0x0093
        L_0x006a:
            r7 = 19
            if (r8 != r7) goto L_0x0078
            if (r10 == 0) goto L_0x0093
            com.xiaomi.smarthome.core.server.CoreBridge r7 = com.xiaomi.smarthome.core.server.CoreBridge.a()
            r7.a(r9, r10)
            goto L_0x0093
        L_0x0078:
            r7 = 20
            if (r8 != r7) goto L_0x0084
            com.xiaomi.smarthome.core.server.CoreBridge r7 = com.xiaomi.smarthome.core.server.CoreBridge.a()
            r7.b(r9)
            goto L_0x0093
        L_0x0084:
            r7 = 21
            if (r8 != r7) goto L_0x0093
            com.xiaomi.smarthome.core.server.CoreBridge r7 = com.xiaomi.smarthome.core.server.CoreBridge.a()
            r7.c(r9)
            goto L_0x0093
        L_0x0090:
            r6.openRnPluginActivity(r7, r8, r9, r10, r11, r12)
        L_0x0093:
            if (r12 == 0) goto L_0x00c8
            if (r9 == 0) goto L_0x00bd
            java.lang.String r7 = r9.did
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x00bd
            com.xiaomi.smarthome.device.api.XmPluginHostApi r7 = com.xiaomi.smarthome.frame.plugin.host.PluginHostApi.instance()
            com.xiaomi.smarthome.frame.plugin.host.PluginHostApi r7 = (com.xiaomi.smarthome.frame.plugin.host.PluginHostApi) r7
            java.lang.String r8 = r9.did
            r7.setCurrentDid(r8)
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ RemoteException -> 0x00b2 }
            r7.<init>()     // Catch:{ RemoteException -> 0x00b2 }
            r12.onSendSuccess(r7)     // Catch:{ RemoteException -> 0x00b2 }
        L_0x00b2:
            r12.onHandle(r0)     // Catch:{ RemoteException -> 0x00b5 }
        L_0x00b5:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.device.api.DeviceStat> r7 = mCacheDeviceMap
            java.lang.String r8 = r9.did
            r7.put(r8, r9)
            goto L_0x00c8
        L_0x00bd:
            com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError r7 = new com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError     // Catch:{ RemoteException -> 0x00c8 }
            r8 = -1
            java.lang.String r9 = "invalid device stat"
            r7.<init>(r8, r9)     // Catch:{ RemoteException -> 0x00c8 }
            r12.onFailure(r7)     // Catch:{ RemoteException -> 0x00c8 }
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.loadRN(com.xiaomi.smarthome.core.entity.plugin.PluginRecord, int, com.xiaomi.smarthome.device.api.DeviceStat, android.content.Intent, boolean, com.xiaomi.smarthome.frame.plugin.IBridgeCallback):void");
    }

    private void openRnPluginActivity(PluginRecord pluginRecord, int i, DeviceStat deviceStat, Intent intent, boolean z, final IBridgeCallback iBridgeCallback) {
        byte c = CoreBridge.a().c();
        if (pluginRecord == null || deviceStat == null) {
            XmPluginHostApi instance = XmPluginHostApi.instance();
            instance.log("PluginBridgeService.loadRN", System.currentTimeMillis() + " msgType:" + i + " initStatus:" + c + " PluginRecord or DeviceStat is null");
        } else {
            XmPluginHostApi instance2 = XmPluginHostApi.instance();
            instance2.log("PluginBridgeService.loadRN", System.currentTimeMillis() + " msgType:" + i + " stat:" + deviceStat + " record:" + pluginRecord + " initStatus:" + c);
        }
        if (c < 0) {
            if (iBridgeCallback != null) {
                try {
                    iBridgeCallback.onFailure(new BridgeError(-1, "ReactInstanceManager init error"));
                } catch (RemoteException unused) {
                }
            }
        } else if (pluginRecord != null && pluginRecord.c() != null && deviceStat != null) {
            if (this.mLauncherTime < 10000) {
                this.mLauncherTime = System.currentTimeMillis();
            }
            Intent intent2 = new Intent();
            intent2.putExtra(EXTRA_CLICK_DEVICE_TIME, System.currentTimeMillis());
            intent2.setClassName(this, PluginRuntimeManager.getInstance().getPluginRNActivityClass(process));
            intent2.putExtra("model", pluginRecord.c().c());
            intent2.putExtra("did", deviceStat.did);
            intent2.putExtra("openTime", System.currentTimeMillis());
            intent2.addFlags(536870912);
            intent2.addFlags(4194304);
            intent2.addFlags(C.ENCODING_PCM_MU_LAW);
            intent2.putExtras(intent);
            intent2.putExtra("package_msgType", i);
            CoreBridge.a().a(deviceStat, pluginRecord, intent2.getExtras());
            startActivity(intent2);
            LogUtil.c("PluginStartTime", "startActivityReactNative  " + System.currentTimeMillis());
            if (z) {
                LocalBroadcastManager.getInstance(this.mAppContext).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if (iBridgeCallback != null) {
                            try {
                                LogUtil.c("miot-rn-plugin", "退出自动化场景...");
                                Bundle extras = intent.getExtras();
                                boolean z = false;
                                if (extras != null) {
                                    z = extras.getBoolean("isSaveEntrance", false);
                                }
                                if (z) {
                                    iBridgeCallback.onMessageSuccess(intent.getExtras());
                                } else {
                                    iBridgeCallback.onMessageFailure(new BridgeError(-1, "rn plugin entrance cancel"));
                                }
                            } catch (RemoteException unused) {
                            }
                        }
                        LocalBroadcastManager.getInstance(PluginBridgeService.this.mAppContext).unregisterReceiver(this);
                    }
                }, new IntentFilter(ACTION_PLUGIN_SCENE));
            }
        } else if (iBridgeCallback != null) {
            try {
                iBridgeCallback.onFailure(new BridgeError(-1, "RN device or plugin init error"));
            } catch (RemoteException unused2) {
            }
        }
    }

    public static StartServiceRecord getStartServiceRecord(String str) {
        StartServiceRecord startServiceRecord = mStartServiceRecordMap.get(str);
        if (startServiceRecord != null) {
            mStartServiceRecordMap.remove(str);
        }
        return startServiceRecord;
    }

    public static DeviceStat getCachedDeviceStat(String str) {
        return mCacheDeviceMap.get(str);
    }

    public void onCreate() {
        ExternalLoadManager.instance.loadExternal((com.xiaomi.smarthome.core.server.internal.plugin.util.Callback<Integer, Integer>) null);
        this.mAppContext = getApplicationContext();
        this.mWorkerThread = new MessageHandlerThread("PluginBridgeServiceWorker");
        this.mWorkerThread.start();
        this.mWorkerHandler = new Handler(this.mWorkerThread.getLooper());
        this.mUiHandler = new Handler(Looper.getMainLooper());
        process = PluginRuntimeManager.getProcessByName(getClass().getName());
        initWebViewDirectorySuffix(process);
        bindActivityListener();
        CoreBridge.a().b();
        CoreBridge.a().e();
    }

    private void bindActivityListener() {
        if (activityCount == null) {
            activityCount = new AtomicInteger(0);
            getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                private long lastTime = 0;
                private String plugin = null;

                public void onActivityPaused(Activity activity) {
                }

                public void onActivityResumed(Activity activity) {
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                public void onActivityCreated(Activity activity, Bundle bundle) {
                    boolean unused = PluginBridgeService.this.mProcessForeground = true;
                }

                /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
                    r6 = ((com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity) r6).getPluginRecord();
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                private java.lang.String getPluginString(android.app.Activity r6) {
                    /*
                        r5 = this;
                        boolean r0 = r6 instanceof com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity
                        if (r0 == 0) goto L_0x001a
                        r0 = r6
                        com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity r0 = (com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity) r0
                        com.xiaomi.plugin.core.XmPluginPackage r0 = r0.getXmPluginPackage()
                        if (r0 == 0) goto L_0x001a
                        long r1 = r0.getPluginId()
                        long r3 = r0.getPackageId()
                        java.lang.String r6 = com.xiaomi.smarthome.stat.PluginStatReporter.a((long) r1, (long) r3)
                        return r6
                    L_0x001a:
                        boolean r0 = r6 instanceof com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity
                        if (r0 == 0) goto L_0x0033
                        com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity r6 = (com.xiaomi.smarthome.frame.plugin.runtime.activity.IPluginRnActivity) r6
                        com.xiaomi.smarthome.core.entity.plugin.PluginRecord r6 = r6.getPluginRecord()
                        if (r6 == 0) goto L_0x0033
                        long r0 = r6.d()
                        long r2 = r6.e()
                        java.lang.String r6 = com.xiaomi.smarthome.stat.PluginStatReporter.a((long) r0, (long) r2)
                        return r6
                    L_0x0033:
                        java.lang.String r6 = ""
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService.AnonymousClass3.getPluginString(android.app.Activity):java.lang.String");
                }

                public void onActivityStarted(Activity activity) {
                    long j;
                    PluginPackageInfo h;
                    int i;
                    long j2;
                    Activity activity2 = activity;
                    if (PluginBridgeService.activityCount.getAndIncrement() == 0) {
                        if (PluginBridgeService.this.mLauncherTime > 0) {
                            String str = null;
                            int i2 = -1;
                            long j3 = -1;
                            if (activity2 instanceof PluginHostActivity) {
                                XmPluginPackage xmPluginPackage = ((PluginHostActivity) activity2).getXmPluginPackage();
                                if (xmPluginPackage != null) {
                                    str = xmPluginPackage.packageName;
                                    int i3 = xmPluginPackage.packageVersion;
                                    i = i3;
                                    j2 = xmPluginPackage.getPluginId();
                                    j3 = xmPluginPackage.getPackageId();
                                } else {
                                    j2 = -1;
                                    i = -1;
                                }
                                long j4 = j2;
                                i2 = i;
                                j = j3;
                                j3 = j4;
                            } else {
                                if (activity2 instanceof IPluginRnActivity) {
                                    IPluginRnActivity iPluginRnActivity = (IPluginRnActivity) activity2;
                                    String deviceModel = iPluginRnActivity.getDeviceModel();
                                    PluginRecord pluginRecord = iPluginRnActivity.getPluginRecord();
                                    if (pluginRecord == null || (h = pluginRecord.h()) == null) {
                                        str = deviceModel;
                                    } else {
                                        i2 = h.g();
                                        j3 = h.a();
                                        j = h.b();
                                        str = deviceModel;
                                    }
                                }
                                j = -1;
                            }
                            String a2 = PluginStatReporter.a(j3, j);
                            if (!TextUtils.isEmpty(a2)) {
                                long currentTimeMillis = System.currentTimeMillis() - PluginBridgeService.this.mLauncherTime;
                                long currentTimeMillis2 = System.currentTimeMillis() - PluginBridgeService.this.mOpenTime;
                                if (currentTimeMillis > 0 && currentTimeMillis < 10000) {
                                    PluginStatReporter.a(a2, currentTimeMillis, str, i2);
                                }
                                if (currentTimeMillis2 > 0 && currentTimeMillis2 < 10000) {
                                    PluginStatReporter.b(a2, currentTimeMillis2, str, i2);
                                    if (PluginBridgeService.mLoadTime > 0 && PluginBridgeService.mLoadTime < 10000) {
                                        PluginStatReporter.a(a2, currentTimeMillis2, PluginBridgeService.mLoadTime, currentTimeMillis, str, i2);
                                    }
                                }
                            }
                        }
                        this.plugin = getPluginString(activity);
                        StatLogSender.b().a(StatLogSender.f22763a, this.plugin, false);
                        if (this.plugin != null) {
                            this.lastTime = PluginStatReporter.a(this.plugin);
                        } else {
                            return;
                        }
                    }
                    long unused = PluginBridgeService.this.mLauncherTime = 0;
                    long unused2 = PluginBridgeService.this.mOpenTime = 0;
                    PluginBridgeService.mLoadTime = 0;
                }

                public void onActivityStopped(Activity activity) {
                    if (PluginBridgeService.activityCount.decrementAndGet() == 0) {
                        if (this.plugin == null) {
                            this.plugin = getPluginString(activity);
                        }
                        if (this.plugin != null) {
                            PluginStatReporter.a(this.plugin, this.lastTime);
                        }
                        this.lastTime = 0;
                        long unused = PluginBridgeService.this.mLauncherTime = 0;
                        long unused2 = PluginBridgeService.this.mOpenTime = 0;
                        StatLogSender.b().a(StatLogSender.b, this.plugin, false);
                    }
                }

                public void onActivityDestroyed(Activity activity) {
                    boolean unused = PluginBridgeService.this.mProcessForeground = false;
                }
            });
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return this.mStub;
    }

    public class StartServiceRecord {
        public Intent intent;

        public StartServiceRecord() {
        }
    }

    private void initWebViewDirectorySuffix(RunningProcess runningProcess) {
        if (Build.VERSION.SDK_INT >= 28) {
            switch (runningProcess) {
                case PLUGIN0:
                case PLUGIN1:
                case PLUGIN2:
                case PLUGIN3:
                    try {
                        LogUtil.c("WebView init", "WebView init,  processName=" + runningProcess.getValue());
                        WebView.setDataDirectorySuffix(runningProcess.getValue());
                        return;
                    } catch (Exception e) {
                        LogUtil.b("WebView init", e.toString());
                        return;
                    }
                default:
                    return;
            }
        } else {
            LogUtil.c("WebView init", "WebView init,  SDK_INT=" + Build.VERSION.SDK_INT);
        }
    }
}
