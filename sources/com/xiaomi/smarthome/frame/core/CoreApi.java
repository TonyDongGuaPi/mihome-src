package com.xiaomi.smarthome.frame.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.sina.weibo.sdk.statistic.LogBuilder;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.account.OAuthAccount;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchResult;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.DeviceListResult;
import com.xiaomi.smarthome.core.entity.device.ScanState;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.entity.globaldynamicsetting.CTAInfo;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.entity.plugin.DownloadPluginDebugPackageResult;
import com.xiaomi.smarthome.core.entity.plugin.DownloadPluginResult;
import com.xiaomi.smarthome.core.entity.plugin.InstallPluginResult;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginUpdateInfo;
import com.xiaomi.smarthome.core.entity.plugin.UpdateAllPluginResult;
import com.xiaomi.smarthome.core.entity.plugin.UpdatePluginConfigResult;
import com.xiaomi.smarthome.core.entity.plugin.UpdatePluginResult;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.entity.upnp.UPnPRequest;
import com.xiaomi.smarthome.core.server.CoreApiStub;
import com.xiaomi.smarthome.core.server.ICoreApi;
import com.xiaomi.smarthome.core.server.IServerHandle;
import com.xiaomi.smarthome.core.server.MiHomeMemoryFile;
import com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtRequest;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.internal.Const;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BleSearchRunnable;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BtConstants;
import com.xiaomi.smarthome.core.server.internal.bluetooth.LocalSearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions;
import com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.MiRechargeApiParser;
import com.xiaomi.smarthome.frame.RouterApiParser;
import com.xiaomi.smarthome.frame.SyncCallback;
import com.xiaomi.smarthome.frame.log.MyLogHelper;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.common.util.MijiaWrapper;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import com.xiaomi.smarthome.stat.MiscStat;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreApi {

    /* renamed from: a  reason: collision with root package name */
    static final String f1526a = "CoreApi.onAccountReadyInternal";
    static final String b = "CoreApi.onGlobalDynamicSettingReadyInternal";
    static final String c = "CoreApi.onStatisticReadyInternal";
    static final String d = "CoreApi.onPluginReadyInternal";
    static final String e = "CoreApi.onCoreReadyInternal";
    static final String f = "CoreApi.onPluginCacheReadyInternal";
    private static CoreApi o;
    /* access modifiers changed from: private */
    public static Object p = new Object();
    private static volatile boolean v;
    private String A = "";
    private Object B = new Object();
    private ServerBean C = null;
    private Object D = new Object();
    private Locale E = null;
    private volatile boolean F = false;
    private List<PluginRecord> G = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public List<IsCoreReadyCallback> H = new ArrayList();
    MessageHandlerThread g = new MessageHandlerThread("CoreApiWorker");
    Handler h;
    Handler i;
    List<SmartHomeRequestRecord> j = new CopyOnWriteArrayList();
    List<RouterRequestRecord> k = new CopyOnWriteArrayList();
    Random l = new Random();
    public volatile boolean m = true;
    public Map<String, PluginRecord> n = new ConcurrentHashMap();
    private volatile ICoreApi q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean w;
    private Object x = new Object();
    private Boolean y = null;
    private Object z = new Object();

    public interface CancelPluginDownloadCallback {
        void a();

        void a(PluginError pluginError);
    }

    public static abstract class DebugPackageCallback {
        public abstract void a();

        public abstract void a(String str);

        public abstract void b();

        public abstract void b(String str);

        public abstract void c(String str);
    }

    public interface DeletePluginCallback {
        void a(PluginError pluginError);

        void a(String str);
    }

    public interface DownloadPluginCallback {
        void onCancel();

        void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask);

        void onFailure(PluginError pluginError);

        void onProgress(PluginRecord pluginRecord, float f);

        void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask);

        void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask);

        void onSuccess(PluginRecord pluginRecord);
    }

    public interface InstallPluginCallback {
        void onCancel();

        void onFailure(PluginError pluginError);

        void onStart(PluginRecord pluginRecord);

        void onSuccess(PluginRecord pluginRecord);
    }

    public interface IsAccountReadyCallback {
        void a(boolean z, String str);
    }

    public interface IsCoreReadyCallback {
        void onCoreReady();
    }

    public interface IsGlobalDynamicSettingReadyCallback {
        void a();
    }

    public interface IsPluginCacheReadyCallback {
        void onPluginCacheReady();
    }

    public interface IsPluginReadyCallback {
        void a();
    }

    public interface IsStatisticReadyCallback {
        void a();
    }

    public interface UpdateConfigCallback {
        void a(PluginError pluginError);

        void a(boolean z, boolean z2);
    }

    public interface UpdatePluginAllCallback {
        void a();

        void a(PluginError pluginError);
    }

    public interface UpdatePluginCallback {
        void a(PluginError pluginError);

        void a(PluginRecord pluginRecord);

        void a(PluginRecord pluginRecord, float f);

        void a(PluginRecord pluginRecord, PluginUpdateInfo pluginUpdateInfo);

        void b(PluginRecord pluginRecord);

        void c(PluginRecord pluginRecord);

        void d(PluginRecord pluginRecord);

        void e(PluginRecord pluginRecord);

        void f(PluginRecord pluginRecord);

        void g(PluginRecord pluginRecord);
    }

    public interface WifiScanCallback {
        void a(String str);
    }

    static abstract class ClientCallback extends IClientCallback.Stub {
        AsyncCallback mCallback;

        ClientCallback(AsyncCallback asyncCallback) {
            this.mCallback = asyncCallback;
        }
    }

    private CoreApi() {
        this.g.start();
        this.h = new Handler(this.g.getLooper());
        this.i = new Handler(Looper.getMainLooper());
        this.m = ProcessUtil.b(CommonApplication.getAppContext());
    }

    public static CoreApi a() {
        if (o == null) {
            synchronized (p) {
                if (o == null) {
                    o = new CoreApi();
                }
            }
        }
        return o;
    }

    private ICoreApi ad() throws CoreNotReadyException {
        ICoreApi iCoreApi = this.q;
        boolean z2 = v;
        if (iCoreApi == null) {
            throw new CoreNotReadyException("apiProxy null" + ProcessUtil.g(FrameManager.b().c()));
        } else if (z2) {
            return iCoreApi;
        } else {
            throw new CoreNotReadyException("isCoreReady false");
        }
    }

    public static ICoreApi a(IBinder iBinder) {
        ICoreApi asInterface = ICoreApi.Stub.asInterface(iBinder);
        if (!GlobalSetting.H) {
            return asInterface;
        }
        return (ICoreApi) Proxy.newProxyInstance(ICoreApi.class.getClassLoader(), new Class[]{ICoreApi.class}, new CoreApiStubProxy(asInterface));
    }

    public static class CoreApiStubProxy implements InvocationHandler {

        /* renamed from: a  reason: collision with root package name */
        private ICoreApi f16111a;

        public CoreApiStubProxy(ICoreApi iCoreApi) {
            this.f16111a = iCoreApi;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (GlobalSetting.H && method.getName().equals("getPluginRecordList")) {
                LogUtil.a("CoreApi", "CoreApiStubProxy intercepted " + method.getName());
                return this.f16111a.getPluginRecordList();
            } else if (!GlobalSetting.H || !method.getName().equals("getPluginRecord")) {
                return method.invoke(this.f16111a, objArr);
            } else {
                LogUtil.a("CoreApi", "CoreApiStubProxy intercepted " + method.getName());
                return this.f16111a.getPluginRecord(objArr[0]);
            }
        }
    }

    public void a(ICoreApi iCoreApi) {
        if (GlobalSetting.u) {
            LogUtilGrey.a("CoreApi", "setCoreApiProxy isMain=" + GlobalSetting.H);
        }
        synchronized (p) {
            this.q = iCoreApi;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2, String str) {
        if (GlobalSetting.u) {
            LogUtilGrey.a("login", "CoreApi onAccountReady isMiLoggedIn=" + z2 + "," + str);
        }
        synchronized (this.x) {
            this.y = Boolean.valueOf(z2);
        }
        synchronized (this.z) {
            this.A = str;
        }
        synchronized (p) {
            this.r = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        synchronized (p) {
            this.s = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        synchronized (p) {
            this.t = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        synchronized (p) {
            this.u = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (GlobalSetting.u) {
            LogUtilGrey.a("CoreApi", "onCoreReady isMain=" + GlobalSetting.H);
        }
        synchronized (p) {
            v = true;
            ae();
        }
    }

    private void ae() {
        if (!this.H.isEmpty()) {
            final long currentTimeMillis = System.currentTimeMillis();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
                    if (com.xiaomi.smarthome.globalsetting.GlobalSetting.H == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
                    if (com.xiaomi.smarthome.globalsetting.GlobalSetting.u == false) goto L_?;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:26:0x0069, code lost:
                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a("CoreApi", "notifyCoreReadyCallback handler run end main");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                    return;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
                    return;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r8 = this;
                        java.lang.Object r0 = com.xiaomi.smarthome.frame.core.CoreApi.p
                        monitor-enter(r0)
                        boolean r1 = com.xiaomi.smarthome.globalsetting.GlobalSetting.H     // Catch:{ all -> 0x0071 }
                        if (r1 == 0) goto L_0x002b
                        boolean r1 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ all -> 0x0071 }
                        if (r1 == 0) goto L_0x002b
                        java.lang.String r1 = "CoreApi"
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
                        r2.<init>()     // Catch:{ all -> 0x0071 }
                        java.lang.String r3 = "notifyCoreReadyCallback handler in main "
                        r2.append(r3)     // Catch:{ all -> 0x0071 }
                        long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0071 }
                        long r5 = r0     // Catch:{ all -> 0x0071 }
                        r7 = 0
                        long r3 = r3 - r5
                        r2.append(r3)     // Catch:{ all -> 0x0071 }
                        java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0071 }
                        com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r1, r2)     // Catch:{ all -> 0x0071 }
                    L_0x002b:
                        com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.this     // Catch:{ all -> 0x0071 }
                        boolean r1 = r1.l()     // Catch:{ all -> 0x0071 }
                        if (r1 != 0) goto L_0x0035
                        monitor-exit(r0)     // Catch:{ all -> 0x0071 }
                        return
                    L_0x0035:
                        r1 = 0
                    L_0x0036:
                        com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.this     // Catch:{ all -> 0x0071 }
                        java.util.List r2 = r2.H     // Catch:{ all -> 0x0071 }
                        int r2 = r2.size()     // Catch:{ all -> 0x0071 }
                        if (r1 >= r2) goto L_0x0057
                        com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.this     // Catch:{ all -> 0x0071 }
                        java.util.List r2 = r2.H     // Catch:{ all -> 0x0071 }
                        java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0071 }
                        com.xiaomi.smarthome.frame.core.CoreApi$IsCoreReadyCallback r2 = (com.xiaomi.smarthome.frame.core.CoreApi.IsCoreReadyCallback) r2     // Catch:{ all -> 0x0071 }
                        if (r2 != 0) goto L_0x0051
                        goto L_0x0054
                    L_0x0051:
                        r2.onCoreReady()     // Catch:{ all -> 0x0071 }
                    L_0x0054:
                        int r1 = r1 + 1
                        goto L_0x0036
                    L_0x0057:
                        com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.this     // Catch:{ all -> 0x0071 }
                        java.util.List r1 = r1.H     // Catch:{ all -> 0x0071 }
                        r1.clear()     // Catch:{ all -> 0x0071 }
                        monitor-exit(r0)     // Catch:{ all -> 0x0071 }
                        boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.H
                        if (r0 == 0) goto L_0x0070
                        boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u
                        if (r0 == 0) goto L_0x0070
                        java.lang.String r0 = "CoreApi"
                        java.lang.String r1 = "notifyCoreReadyCallback handler run end main"
                        com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)
                    L_0x0070:
                        return
                    L_0x0071:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x0071 }
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass1.run():void");
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        synchronized (p) {
            this.w = true;
        }
    }

    public void g() {
        if (GlobalSetting.u) {
            LogUtilGrey.a("CoreApi", "reset isMain=" + GlobalSetting.H);
        }
        synchronized (p) {
            this.q = null;
            this.r = false;
            this.s = false;
            this.t = false;
            this.u = false;
            v = false;
            this.C = null;
            this.w = false;
            this.H.clear();
        }
        if (GlobalSetting.u) {
            LogUtilGrey.a("CoreApi", "reset:" + Log.getStackTraceString(new Exception()));
        }
    }

    public boolean h() {
        boolean z2;
        synchronized (p) {
            if (GlobalSetting.H) {
                z2 = this.r;
            } else {
                z2 = this.q != null && this.r;
            }
        }
        return z2;
    }

    public void a(Context context, final IsAccountReadyCallback isAccountReadyCallback) {
        LogUtilGrey.a("login", "CoreApi isAccountReady " + context);
        if (context != null) {
            if (h()) {
                if (GlobalSetting.u) {
                    LogUtilGrey.a("login", "CoreApi isAccountReady true, will callback " + q());
                }
                if (isAccountReadyCallback == null) {
                    return;
                }
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    isAccountReadyCallback.a(q(), s());
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            isAccountReadyCallback.a(CoreApi.this.q(), CoreApi.this.s());
                        }
                    });
                }
            } else {
                if (GlobalSetting.u) {
                    LogUtilGrey.a("login", "CoreApi isAccountReady false, will reg receiver");
                }
                final WeakReference weakReference = new WeakReference(context);
                IntentFilter intentFilter = new IntentFilter(f1526a);
                final MijiaWrapper mijiaWrapper = new MijiaWrapper(isAccountReadyCallback);
                LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        Context context2 = (Context) weakReference.get();
                        if (context2 != null) {
                            LocalBroadcastManager.getInstance(context2.getApplicationContext()).unregisterReceiver(this);
                        }
                        boolean booleanExtra = intent.getBooleanExtra("isMiLoggedIn", false);
                        String stringExtra = intent.getStringExtra("miId");
                        IsAccountReadyCallback isAccountReadyCallback = (IsAccountReadyCallback) mijiaWrapper.f18690a;
                        mijiaWrapper.f18690a = null;
                        if (isAccountReadyCallback != null) {
                            isAccountReadyCallback.a(booleanExtra, stringExtra);
                        }
                    }
                }, intentFilter);
            }
        }
    }

    public boolean i() {
        boolean z2;
        synchronized (p) {
            z2 = this.q != null && this.s;
        }
        return z2;
    }

    public void a(final Context context, final IsGlobalDynamicSettingReadyCallback isGlobalDynamicSettingReadyCallback) {
        if (context != null) {
            if (!i()) {
                IntentFilter intentFilter = new IntentFilter(b);
                LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(this);
                        if (isGlobalDynamicSettingReadyCallback != null) {
                            isGlobalDynamicSettingReadyCallback.a();
                        }
                    }
                }, intentFilter);
            } else if (isGlobalDynamicSettingReadyCallback == null) {
            } else {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    isGlobalDynamicSettingReadyCallback.a();
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            isGlobalDynamicSettingReadyCallback.a();
                        }
                    });
                }
            }
        }
    }

    public boolean j() {
        boolean z2;
        synchronized (p) {
            z2 = this.q != null && this.t;
        }
        return z2;
    }

    public void a(final Context context, final IsStatisticReadyCallback isStatisticReadyCallback) {
        if (context != null) {
            if (!j()) {
                IntentFilter intentFilter = new IntentFilter(c);
                LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(this);
                        if (isStatisticReadyCallback != null) {
                            isStatisticReadyCallback.a();
                        }
                    }
                }, intentFilter);
            } else if (isStatisticReadyCallback == null) {
            } else {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    isStatisticReadyCallback.a();
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            isStatisticReadyCallback.a();
                        }
                    });
                }
            }
        }
    }

    public boolean k() {
        boolean z2;
        synchronized (p) {
            z2 = this.q != null && this.u;
        }
        return z2;
    }

    public void a(final Context context, final IsPluginReadyCallback isPluginReadyCallback) {
        if (context != null) {
            if (!k()) {
                IntentFilter intentFilter = new IntentFilter(d);
                LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(this);
                        if (isPluginReadyCallback != null) {
                            isPluginReadyCallback.a();
                        }
                    }
                }, intentFilter);
            } else if (isPluginReadyCallback == null) {
            } else {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    isPluginReadyCallback.a();
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            isPluginReadyCallback.a();
                        }
                    });
                }
            }
        }
    }

    public boolean l() {
        boolean z2;
        synchronized (p) {
            z2 = this.q != null && v;
        }
        return z2;
    }

    public void a(List<IsCoreReadyCallback> list) {
        synchronized (p) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                this.H.remove(list.get(i2));
            }
        }
    }

    public void a(Context context, final IsCoreReadyCallback isCoreReadyCallback) {
        if (context == null) {
            isCoreReadyCallback.onCoreReady();
        } else if (!l()) {
            synchronized (p) {
                if (!l()) {
                    this.H.add(isCoreReadyCallback);
                } else if (isCoreReadyCallback != null) {
                    if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                        isCoreReadyCallback.onCoreReady();
                    } else {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                isCoreReadyCallback.onCoreReady();
                            }
                        });
                    }
                }
            }
            IntentFilter intentFilter = new IntentFilter(e);
            final WeakReference weakReference = new WeakReference(context);
            LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    Context context2 = (Context) weakReference.get();
                    if (context2 != null) {
                        LocalBroadcastManager.getInstance(context2.getApplicationContext()).unregisterReceiver(this);
                    }
                }
            }, intentFilter);
        } else if (isCoreReadyCallback == null) {
        } else {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                isCoreReadyCallback.onCoreReady();
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        isCoreReadyCallback.onCoreReady();
                    }
                });
            }
        }
    }

    public boolean m() {
        boolean z2;
        synchronized (p) {
            z2 = this.q != null && this.w;
        }
        return z2;
    }

    public void a(final Context context, final IsPluginCacheReadyCallback isPluginCacheReadyCallback) {
        if (context != null) {
            if (!m()) {
                IntentFilter intentFilter = new IntentFilter(f);
                LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(this);
                        if (isPluginCacheReadyCallback != null) {
                            isPluginCacheReadyCallback.onPluginCacheReady();
                        }
                    }
                }, intentFilter);
            } else if (isPluginCacheReadyCallback == null) {
            } else {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    isPluginCacheReadyCallback.onPluginCacheReady();
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            isPluginCacheReadyCallback.onPluginCacheReady();
                        }
                    });
                }
            }
        }
    }

    public void n() {
        if (HostSetting.g || HostSetting.i) {
            try {
                ad().gc();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (CoreNotReadyException e3) {
                e3.printStackTrace();
            }
        }
    }

    public AccountType o() {
        try {
            return ad().getAccountType();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public boolean p() {
        try {
            return ad().isBooleanValue(CoreApiStub.BooleanKey.f14038a);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public void a(String str, boolean z2) {
        try {
            ICoreApi ad = ad();
            ad.updateBooleanValue(str + "#" + CoreApiStub.BooleanKey.c, z2);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002d  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean q() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.x
            monitor-enter(r0)
            java.lang.Boolean r1 = r4.y     // Catch:{ all -> 0x0037 }
            if (r1 != 0) goto L_0x002a
            com.xiaomi.smarthome.core.server.ICoreApi r2 = r4.ad()     // Catch:{ RemoteException -> 0x0026, CoreNotReadyException -> 0x0021 }
            boolean r2 = r2.isMiLoggedIn()     // Catch:{ RemoteException -> 0x0026, CoreNotReadyException -> 0x0021 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ RemoteException -> 0x0026, CoreNotReadyException -> 0x0021 }
            r4.y = r2     // Catch:{ RemoteException -> 0x001c, CoreNotReadyException -> 0x0017 }
            r1 = r2
            goto L_0x002a
        L_0x0017:
            r1 = move-exception
            r3 = r2
            r2 = r1
            r1 = r3
            goto L_0x0022
        L_0x001c:
            r1 = move-exception
            r3 = r2
            r2 = r1
            r1 = r3
            goto L_0x0027
        L_0x0021:
            r2 = move-exception
        L_0x0022:
            r2.printStackTrace()     // Catch:{ all -> 0x0037 }
            goto L_0x002a
        L_0x0026:
            r2 = move-exception
        L_0x0027:
            r2.printStackTrace()     // Catch:{ all -> 0x0037 }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            if (r1 != 0) goto L_0x0032
            r0 = 0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
        L_0x0032:
            boolean r0 = r1.booleanValue()
            return r0
        L_0x0037:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.q():boolean");
    }

    /* access modifiers changed from: package-private */
    public void r() {
        synchronized (this.x) {
            this.y = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0039 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String s() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.z
            monitor-enter(r0)
            java.lang.String r1 = r4.A     // Catch:{ all -> 0x003c }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x003c }
            if (r2 != 0) goto L_0x0013
            java.lang.String r2 = "0"
            boolean r2 = android.text.TextUtils.equals(r1, r2)     // Catch:{ all -> 0x003c }
            if (r2 == 0) goto L_0x0032
        L_0x0013:
            com.xiaomi.smarthome.core.server.ICoreApi r2 = r4.ad()     // Catch:{ RemoteException -> 0x002e, CoreNotReadyException -> 0x0029 }
            java.lang.String r2 = r2.getMiId()     // Catch:{ RemoteException -> 0x002e, CoreNotReadyException -> 0x0029 }
            r4.A = r2     // Catch:{ RemoteException -> 0x0024, CoreNotReadyException -> 0x001f }
            r1 = r2
            goto L_0x0032
        L_0x001f:
            r1 = move-exception
            r3 = r2
            r2 = r1
            r1 = r3
            goto L_0x002a
        L_0x0024:
            r1 = move-exception
            r3 = r2
            r2 = r1
            r1 = r3
            goto L_0x002f
        L_0x0029:
            r2 = move-exception
        L_0x002a:
            r2.printStackTrace()     // Catch:{ all -> 0x003c }
            goto L_0x0032
        L_0x002e:
            r2 = move-exception
        L_0x002f:
            r2.printStackTrace()     // Catch:{ all -> 0x003c }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r1 = "0"
        L_0x003b:
            return r1
        L_0x003c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.s():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public void t() {
        synchronized (this.z) {
            this.A = "";
        }
    }

    public String u() {
        String str = "0";
        try {
            LoginMiAccount miAccount = ad().getMiAccount();
            if (!(miAccount == null || miAccount.a("passportapi") == null)) {
                str = miAccount.a("passportapi").b;
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
        return TextUtils.isEmpty(str) ? "0" : str;
    }

    public boolean v() {
        try {
            LoginMiAccount miAccount = ad().getMiAccount();
            if (miAccount != null) {
                return miAccount.b();
            }
            return false;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public String w() {
        try {
            LoginMiAccount miAccount = ad().getMiAccount();
            if (miAccount != null) {
                return miAccount.c();
            }
            return "";
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return "";
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public MiServiceTokenInfo a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            LoginMiAccount miAccount = ad().getMiAccount();
            if (miAccount != null) {
                return miAccount.a(str);
            }
            return null;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public List<MiServiceTokenInfo> x() {
        ArrayList arrayList = new ArrayList();
        try {
            LoginMiAccount miAccount = ad().getMiAccount();
            if (miAccount != null) {
                return miAccount.d();
            }
            return arrayList;
        } catch (Throwable th) {
            th.printStackTrace();
            return arrayList;
        }
    }

    public AsyncHandle a(LoginMiAccount loginMiAccount, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().setMiAccount(loginMiAccount, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                    CoreApi.this.r();
                    CoreApi.this.t();
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(-1, ""));
                    }
                    CoreApi.this.r();
                    CoreApi.this.t();
                }
            });
        } catch (Throwable th) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-2, th.toString()));
            }
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle a(MiServiceTokenInfo miServiceTokenInfo, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().setMiServiceTokenTmp(miServiceTokenInfo, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
                    }
                }
            });
        } catch (Throwable unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle a(OAuthAccount oAuthAccount, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().setOAuthAccount(oAuthAccount, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                    CoreApi.this.r();
                    CoreApi.this.t();
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
                    }
                    CoreApi.this.r();
                    CoreApi.this.t();
                }
            });
        } catch (Throwable th) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-9999, th.getMessage()));
            }
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public LoginMiAccount y() {
        try {
            return ad().getMiAccount();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public boolean z() {
        try {
            return ad().isServicePromoteSuccess();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public void A() {
        try {
            ad().clearAllMiServiceTokenInSystem();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public void b(String str) {
        try {
            ad().clearMiServiceTokenInSystem(str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public AsyncHandle a(final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        FrameManager.b().g().a("---start clearAccount---");
        FrameManager.b().g().a(Log.getStackTraceString(new Exception()));
        FrameManager.b().g().a("---end clearAccount---");
        try {
            iServerHandle = ad().clearAccount(new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    CoreApi.this.r();
                    CoreApi.this.t();
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    CoreApi.this.r();
                    CoreApi.this.t();
                    Error error = new Error(ErrorCode.INVALID.getCode(), "");
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error);
                    }
                }
            });
        } catch (Throwable th) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-9999, th.getMessage()));
            }
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public <R> AsyncHandle a(Context context, NetRequest netRequest, JsonParser<R> jsonParser, Crypto crypto, AsyncCallback<R, Error> asyncCallback) {
        return a(context, netRequest, jsonParser, crypto, asyncCallback, false);
    }

    public <R> AsyncHandle a(Context context, NetRequest netRequest, JsonParser<R> jsonParser, Crypto crypto, AsyncCallback<R, Error> asyncCallback, boolean z2) {
        IServerHandle iServerHandle;
        NetRequest netRequest2 = netRequest;
        Crypto crypto2 = crypto;
        AsyncCallback<R, Error> asyncCallback2 = asyncCallback;
        int hashCode = context != null ? context.hashCode() : 0;
        long B2 = B();
        final JsonParser<R> jsonParser2 = jsonParser;
        final boolean z3 = z2;
        final Context context2 = context;
        final NetRequest netRequest3 = netRequest;
        final long j2 = B2;
        AnonymousClass19 r1 = new ClientCallback(asyncCallback) {
            /* JADX WARNING: type inference failed for: r5v21, types: [android.os.Parcelable] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onSuccess(android.os.Bundle r5) throws android.os.RemoteException {
                /*
                    r4 = this;
                    java.lang.Class<com.xiaomi.smarthome.core.entity.net.NetResult> r0 = com.xiaomi.smarthome.core.entity.net.NetResult.class
                    java.lang.ClassLoader r0 = r0.getClassLoader()
                    r5.setClassLoader(r0)
                    java.lang.String r0 = "result"
                    android.os.Parcelable r0 = r5.getParcelable(r0)
                    com.xiaomi.smarthome.core.entity.net.NetResult r0 = (com.xiaomi.smarthome.core.entity.net.NetResult) r0
                    if (r0 != 0) goto L_0x002e
                    java.lang.String r1 = "result_file"
                    android.os.Parcelable r5 = r5.getParcelable(r1)
                    com.xiaomi.smarthome.core.server.MiHomeMemoryFile r5 = (com.xiaomi.smarthome.core.server.MiHomeMemoryFile) r5
                    if (r5 == 0) goto L_0x002e
                    android.os.Parcel r5 = r5.b()
                    java.lang.Class<com.xiaomi.smarthome.core.entity.net.NetResult> r0 = com.xiaomi.smarthome.core.entity.net.NetResult.class
                    java.lang.ClassLoader r0 = r0.getClassLoader()
                    android.os.Parcelable r5 = r5.readParcelable(r0)
                    r0 = r5
                    com.xiaomi.smarthome.core.entity.net.NetResult r0 = (com.xiaomi.smarthome.core.entity.net.NetResult) r0
                L_0x002e:
                    com.xiaomi.smarthome.frame.SmartHomeApiParser r5 = com.xiaomi.smarthome.frame.SmartHomeApiParser.a()
                    com.xiaomi.smarthome.frame.JsonParser r1 = r4
                    com.xiaomi.smarthome.frame.AsyncCallback r2 = r4.mCallback
                    boolean r3 = r5
                    r5.a(r0, r1, r2, r3)
                    boolean r5 = com.xiaomi.smarthome.frame.HostSetting.g     // Catch:{ Exception -> 0x008f }
                    if (r5 != 0) goto L_0x0043
                    boolean r5 = com.xiaomi.smarthome.frame.HostSetting.i     // Catch:{ Exception -> 0x008f }
                    if (r5 == 0) goto L_0x00ad
                L_0x0043:
                    if (r0 == 0) goto L_0x00ad
                    java.lang.String r5 = r0.c     // Catch:{ Exception -> 0x008f }
                    boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x008f }
                    if (r5 != 0) goto L_0x00ad
                    org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x008f }
                    java.lang.String r1 = r0.c     // Catch:{ Exception -> 0x008f }
                    r5.<init>(r1)     // Catch:{ Exception -> 0x008f }
                    java.lang.String r1 = "code"
                    boolean r1 = r5.isNull(r1)     // Catch:{ Exception -> 0x008f }
                    if (r1 != 0) goto L_0x00ad
                    java.lang.String r1 = "code"
                    int r1 = r5.optInt(r1)     // Catch:{ Exception -> 0x008f }
                    r2 = -1
                    if (r1 != r2) goto L_0x00ad
                    java.lang.String r1 = "message"
                    boolean r1 = r5.isNull(r1)     // Catch:{ Exception -> 0x008f }
                    if (r1 != 0) goto L_0x00ad
                    java.lang.String r1 = "message"
                    java.lang.String r5 = r5.optString(r1)     // Catch:{ Exception -> 0x008f }
                    java.lang.String r1 = "no permit"
                    boolean r5 = android.text.TextUtils.equals(r5, r1)     // Catch:{ Exception -> 0x008f }
                    if (r5 == 0) goto L_0x00ad
                    android.os.Looper r5 = android.os.Looper.getMainLooper()     // Catch:{ Exception -> 0x008f }
                    if (r5 == 0) goto L_0x00ad
                    android.os.Handler r1 = new android.os.Handler     // Catch:{ Exception -> 0x008f }
                    r1.<init>(r5)     // Catch:{ Exception -> 0x008f }
                    com.xiaomi.smarthome.frame.core.CoreApi$19$1 r5 = new com.xiaomi.smarthome.frame.core.CoreApi$19$1     // Catch:{ Exception -> 0x008f }
                    r5.<init>()     // Catch:{ Exception -> 0x008f }
                    r1.post(r5)     // Catch:{ Exception -> 0x008f }
                    goto L_0x00ad
                L_0x008f:
                    r5 = move-exception
                    java.lang.String r1 = "ExceptionTAG"
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "onSuccess: "
                    r2.append(r3)
                    java.lang.String r3 = r5.toString()
                    r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r1, r2)
                    r5.printStackTrace()
                L_0x00ad:
                    boolean r5 = r0.b
                    if (r5 == 0) goto L_0x00b2
                    return
                L_0x00b2:
                    com.xiaomi.smarthome.frame.AsyncCallback r5 = r4.mCallback
                    if (r5 == 0) goto L_0x00bd
                    com.xiaomi.smarthome.frame.AsyncCallback r5 = r4.mCallback
                    boolean r5 = r5 instanceof com.xiaomi.smarthome.frame.SyncCallback
                    if (r5 == 0) goto L_0x00bd
                    goto L_0x00c4
                L_0x00bd:
                    com.xiaomi.smarthome.frame.core.CoreApi r5 = com.xiaomi.smarthome.frame.core.CoreApi.this
                    long r0 = r8
                    r5.b((long) r0)
                L_0x00c4:
                    r5 = 0
                    r4.mCallback = r5
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass19.onSuccess(android.os.Bundle):void");
            }

            public void onFailure(Bundle bundle) throws RemoteException {
                Error error;
                bundle.setClassLoader(Error.class.getClassLoader());
                NetError netError = (NetError) bundle.getParcelable("error");
                if (!TextUtils.isEmpty(netError.c())) {
                    error = new Error(netError.a(), netError.b(), netError.c());
                } else {
                    error = new Error(netError.a(), netError.b());
                }
                if (this.mCallback != null) {
                    this.mCallback.sendFailureMessage(error);
                }
                if (this.mCallback == null || !(this.mCallback instanceof SyncCallback)) {
                    CoreApi.this.b(j2);
                }
                this.mCallback = null;
                MyLogHelper.a(netRequest3, error);
            }
        };
        boolean z4 = asyncCallback2 instanceof SyncCallback;
        if (z4) {
            try {
                ((SyncCallback) asyncCallback2).setUseSyncLockMode();
                Object syncLock = ((SyncCallback) asyncCallback2).getSyncLock();
                synchronized (syncLock) {
                    iServerHandle = ad().sendSmartHomeRequest(netRequest2, crypto2, r1);
                    syncLock.wait();
                }
                SyncCallback syncCallback = (SyncCallback) asyncCallback2;
                if (syncCallback.isSuccess()) {
                    asyncCallback2.onSuccess(syncCallback.getResult());
                } else {
                    asyncCallback2.onFailure(syncCallback.getError());
                }
            } catch (Throwable th) {
                if (asyncCallback2 != null) {
                    asyncCallback2.sendFailureMessage(new Error(-9999, th.getMessage()));
                }
                return new AsyncHandle(null);
            }
        } else {
            try {
                iServerHandle = ad().sendSmartHomeRequest(netRequest2, crypto2, r1);
            } catch (Throwable th2) {
                if (asyncCallback2 != null) {
                    asyncCallback2.sendFailureMessage(new Error(-9999, th2.getMessage()));
                }
                return new AsyncHandle(null);
            }
        }
        AsyncHandle asyncHandle = new AsyncHandle(iServerHandle);
        if (!z4) {
            a(B2, hashCode, asyncHandle);
        }
        return asyncHandle;
    }

    private void a(long j2, int i2, AsyncHandle asyncHandle) {
        final long j3 = j2;
        final AsyncHandle asyncHandle2 = asyncHandle;
        final int i3 = i2;
        this.h.post(new Runnable() {
            public void run() {
                SmartHomeRequestRecord smartHomeRequestRecord = new SmartHomeRequestRecord();
                smartHomeRequestRecord.f16113a = j3;
                smartHomeRequestRecord.c = new WeakReference<>(asyncHandle2);
                smartHomeRequestRecord.b = i3;
                CoreApi.this.j.add(smartHomeRequestRecord);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final long j2) {
        this.h.post(new Runnable() {
            public void run() {
                int i = 0;
                while (i < CoreApi.this.j.size()) {
                    if (CoreApi.this.j.get(i).f16113a == j2) {
                        CoreApi.this.j.remove(i);
                        i--;
                    }
                    i++;
                }
            }
        });
    }

    private void c(final int i2) {
        this.h.post(new Runnable() {
            public void run() {
                for (int i = 0; i < CoreApi.this.j.size(); i++) {
                    SmartHomeRequestRecord smartHomeRequestRecord = CoreApi.this.j.get(i);
                    if (!(smartHomeRequestRecord.b != i2 || smartHomeRequestRecord.c == null || smartHomeRequestRecord.c.get() == null)) {
                        ((AsyncHandle) smartHomeRequestRecord.c.get()).cancel();
                    }
                }
            }
        });
    }

    private void b(long j2, int i2, AsyncHandle asyncHandle) {
        final long j3 = j2;
        final AsyncHandle asyncHandle2 = asyncHandle;
        final int i3 = i2;
        this.h.post(new Runnable() {
            public void run() {
                RouterRequestRecord routerRequestRecord = new RouterRequestRecord();
                routerRequestRecord.f16112a = j3;
                routerRequestRecord.c = new WeakReference<>(asyncHandle2);
                routerRequestRecord.b = i3;
                CoreApi.this.k.add(routerRequestRecord);
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(final long j2) {
        this.h.post(new Runnable() {
            public void run() {
                int i = 0;
                while (i < CoreApi.this.k.size()) {
                    if (CoreApi.this.k.get(i).f16112a == j2) {
                        CoreApi.this.k.remove(i);
                        i--;
                    }
                    i++;
                }
            }
        });
    }

    private void d(final int i2) {
        this.h.post(new Runnable() {
            public void run() {
                for (int i = 0; i < CoreApi.this.k.size(); i++) {
                    RouterRequestRecord routerRequestRecord = CoreApi.this.k.get(i);
                    if (!(routerRequestRecord.b != i2 || routerRequestRecord.c == null || routerRequestRecord.c.get() == null)) {
                        ((AsyncHandle) routerRequestRecord.c.get()).cancel();
                    }
                }
            }
        });
    }

    public void a(Context context) {
        if (context != null) {
            int hashCode = context.hashCode();
            c(hashCode);
            d(hashCode);
        }
    }

    public <R> AsyncHandle a(Context context, final NetRequest netRequest, final JsonParser<R> jsonParser, final AsyncCallback<R, Error> asyncCallback) {
        try {
            return new AsyncHandle(ad().sendMiRechargeRequest(netRequest, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(NetResult.class.getClassLoader());
                    MiRechargeApiParser.a().a((NetResult) bundle.getParcelable("result"), jsonParser, asyncCallback);
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    NetError netError = (NetError) bundle.getParcelable("error");
                    Error error = new Error(netError.a(), netError.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error);
                    }
                    MyLogHelper.a(netRequest, error);
                }
            }));
        } catch (Throwable th) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-9999, th.getMessage()));
            }
            return new AsyncHandle(null);
        }
    }

    @Deprecated
    public <R> AsyncHandle b(Context context, final NetRequest netRequest, final JsonParser<R> jsonParser, final AsyncCallback<R, Error> asyncCallback) {
        try {
            return new AsyncHandle(ad().sendMiShopRequest(netRequest, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(NetResult.class.getClassLoader());
                    MiRechargeApiParser.a().a((NetResult) bundle.getParcelable("result"), jsonParser, asyncCallback);
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    NetError netError = (NetError) bundle.getParcelable("error");
                    Error error = new Error(netError.a(), netError.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error);
                    }
                    MyLogHelper.a(netRequest, error);
                }
            }));
        } catch (Throwable th) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-9999, th.getMessage()));
            }
            return new AsyncHandle(null);
        }
    }

    public <R> AsyncHandle a(Context context, NetRequest netRequest, String str, boolean z2, JsonParser<R> jsonParser, AsyncCallback<R, Error> asyncCallback) {
        IServerHandle iServerHandle;
        NetRequest netRequest2 = netRequest;
        String str2 = str;
        boolean z3 = z2;
        AsyncCallback<R, Error> asyncCallback2 = asyncCallback;
        int hashCode = context != null ? context.hashCode() : 0;
        long B2 = B();
        final JsonParser<R> jsonParser2 = jsonParser;
        final AsyncCallback<R, Error> asyncCallback3 = asyncCallback;
        final long j2 = B2;
        final NetRequest netRequest3 = netRequest;
        AnonymousClass28 r1 = new IClientCallback.Stub() {
            public void onSuccess(Bundle bundle) throws RemoteException {
                bundle.setClassLoader(NetResult.class.getClassLoader());
                RouterApiParser.a().a((NetResult) bundle.getParcelable("result"), jsonParser2, asyncCallback3);
                if (!(asyncCallback3 instanceof SyncCallback)) {
                    CoreApi.this.c(j2);
                }
            }

            public void onFailure(Bundle bundle) throws RemoteException {
                bundle.setClassLoader(Error.class.getClassLoader());
                NetError netError = (NetError) bundle.getParcelable("error");
                Error error = new Error(netError.a(), netError.b());
                if (asyncCallback3 != null) {
                    asyncCallback3.sendFailureMessage(error);
                }
                if (!(asyncCallback3 instanceof SyncCallback)) {
                    CoreApi.this.c(j2);
                }
                MyLogHelper.a(netRequest3, error);
            }
        };
        boolean z4 = asyncCallback2 instanceof SyncCallback;
        if (z4) {
            try {
                ((SyncCallback) asyncCallback2).setUseSyncLockMode();
                Object syncLock = ((SyncCallback) asyncCallback2).getSyncLock();
                synchronized (syncLock) {
                    iServerHandle = ad().sendRouterRequest(netRequest2, str2, z3, r1);
                    syncLock.wait();
                }
                SyncCallback syncCallback = (SyncCallback) asyncCallback2;
                if (syncCallback.isSuccess()) {
                    asyncCallback2.onSuccess(syncCallback.getResult());
                } else {
                    asyncCallback2.onFailure(syncCallback.getError());
                }
            } catch (Throwable th) {
                if (asyncCallback2 != null) {
                    asyncCallback2.sendFailureMessage(new Error(-9999, th.getMessage()));
                }
                return new AsyncHandle(null);
            }
        } else {
            try {
                iServerHandle = ad().sendRouterRequest(netRequest2, str2, z3, r1);
            } catch (Throwable th2) {
                if (asyncCallback2 != null) {
                    asyncCallback2.sendFailureMessage(new Error(-9999, th2.getMessage()));
                }
                return new AsyncHandle(null);
            }
        }
        AsyncHandle asyncHandle = new AsyncHandle(iServerHandle);
        if (!z4) {
            b(B2, hashCode, asyncHandle);
        }
        return asyncHandle;
    }

    public AsyncHandle a(ScanType scanType, final AsyncCallback<Integer, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().scanDeviceList(scanType, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(ScanState.class.getClassLoader());
                    int i = bundle.getInt("result");
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(Integer.valueOf(i));
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("scanDeviceList fail", error2);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new AsyncHandle(iServerHandle);
        if (asyncCallback != null) {
            asyncCallback.sendFailureMessage(new Error(-9999, th.getMessage()));
        }
        iServerHandle = null;
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle<List<Device>> b(final AsyncCallback<List<Device>, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().getDeviceList(new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    MiHomeMemoryFile miHomeMemoryFile;
                    bundle.setClassLoader(DeviceListResult.class.getClassLoader());
                    ArrayList parcelableArrayList = bundle.getParcelableArrayList("result");
                    if (parcelableArrayList == null && (miHomeMemoryFile = (MiHomeMemoryFile) bundle.getParcelable(Const.f14057a)) != null) {
                        parcelableArrayList = new ArrayList(1000);
                        try {
                            miHomeMemoryFile.b().readList(parcelableArrayList, getClass().getClassLoader());
                            miHomeMemoryFile.c();
                        } catch (Exception e) {
                            Log.e("getDeviceList", AppMeasurement.Param.FATAL, e);
                        }
                        parcelableArrayList.trimToSize();
                    }
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(parcelableArrayList);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("getDeviceList fail", error2);
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle<>(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle<>(iServerHandle);
        }
        return new AsyncHandle<>(iServerHandle);
    }

    public AsyncHandle a(final List<BatchRpcParam> list, final AsyncCallback<JSONObject, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().batchRpcAsync(list, new IClientCallback.Stub() {
                /* JADX WARNING: Removed duplicated region for block: B:24:0x005e  */
                /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(android.os.Bundle r6) throws android.os.RemoteException {
                    /*
                        r5 = this;
                        java.lang.Class<com.xiaomi.smarthome.core.entity.device.DeviceListResult> r0 = com.xiaomi.smarthome.core.entity.device.DeviceListResult.class
                        java.lang.ClassLoader r0 = r0.getClassLoader()
                        r6.setClassLoader(r0)
                        java.lang.String r0 = "result"
                        java.lang.String r6 = r6.getString(r0)
                        org.json.JSONObject r0 = new org.json.JSONObject
                        r0.<init>()
                        r1 = -999(0xfffffffffffffc19, float:NaN)
                        if (r6 == 0) goto L_0x006f
                        boolean r2 = android.text.TextUtils.isEmpty(r6)
                        if (r2 != 0) goto L_0x006f
                        org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0059 }
                        r2.<init>(r6)     // Catch:{ JSONException -> 0x0059 }
                        java.lang.String r6 = "code"
                        int r6 = r2.optInt(r6)     // Catch:{ JSONException -> 0x005a }
                        com.xiaomi.smarthome.frame.ErrorCode r0 = com.xiaomi.smarthome.frame.ErrorCode.valueof(r6)     // Catch:{ JSONException -> 0x005a }
                        com.xiaomi.smarthome.frame.ErrorCode r3 = com.xiaomi.smarthome.frame.ErrorCode.SUCCESS     // Catch:{ JSONException -> 0x005a }
                        if (r0 != r3) goto L_0x0044
                        java.lang.String r6 = "result"
                        org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ JSONException -> 0x005a }
                        if (r6 != 0) goto L_0x003a
                        r6 = r2
                    L_0x003a:
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3     // Catch:{ JSONException -> 0x005a }
                        if (r0 == 0) goto L_0x0083
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3     // Catch:{ JSONException -> 0x005a }
                        r0.sendSuccessMessage(r6)     // Catch:{ JSONException -> 0x005a }
                        goto L_0x0083
                    L_0x0044:
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3     // Catch:{ JSONException -> 0x005a }
                        if (r0 == 0) goto L_0x0083
                        java.lang.String r0 = "message"
                        java.lang.String r0 = r2.optString(r0)     // Catch:{ JSONException -> 0x005a }
                        com.xiaomi.smarthome.frame.AsyncCallback r3 = r3     // Catch:{ JSONException -> 0x005a }
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error     // Catch:{ JSONException -> 0x005a }
                        r4.<init>(r6, r0)     // Catch:{ JSONException -> 0x005a }
                        r3.sendFailureMessage(r4)     // Catch:{ JSONException -> 0x005a }
                        goto L_0x0083
                    L_0x0059:
                        r2 = r0
                    L_0x005a:
                        com.xiaomi.smarthome.frame.AsyncCallback r6 = r3
                        if (r6 == 0) goto L_0x0083
                        java.lang.String r6 = "message"
                        java.lang.String r6 = r2.optString(r6)
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3
                        com.xiaomi.smarthome.frame.Error r2 = new com.xiaomi.smarthome.frame.Error
                        r2.<init>(r1, r6)
                        r0.sendFailureMessage(r2)
                        goto L_0x0083
                    L_0x006f:
                        com.xiaomi.smarthome.frame.AsyncCallback r6 = r3
                        if (r6 == 0) goto L_0x0083
                        java.lang.String r6 = "message"
                        java.lang.String r6 = r0.optString(r6)
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3
                        com.xiaomi.smarthome.frame.Error r2 = new com.xiaomi.smarthome.frame.Error
                        r2.<init>(r1, r6)
                        r0.sendFailureMessage(r2)
                    L_0x0083:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass31.onSuccess(android.os.Bundle):void");
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("batchRpcAsync fail " + Arrays.toString(list.toArray()), error2);
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle a(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        return a(str, str2, str3, asyncCallback, false);
    }

    public AsyncHandle a(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback, boolean z2) {
        IServerHandle iServerHandle;
        final boolean z3 = z2;
        final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
        final String str4 = str;
        final String str5 = str3;
        try {
            iServerHandle = ad().rpcAsyncToCloud(str, str2, str3, new IClientCallback.Stub() {
                /* JADX WARNING: Removed duplicated region for block: B:26:0x0062  */
                /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(android.os.Bundle r6) throws android.os.RemoteException {
                    /*
                        r5 = this;
                        java.lang.Class<com.xiaomi.smarthome.core.entity.device.DeviceListResult> r0 = com.xiaomi.smarthome.core.entity.device.DeviceListResult.class
                        java.lang.ClassLoader r0 = r0.getClassLoader()
                        r6.setClassLoader(r0)
                        java.lang.String r0 = "result"
                        java.lang.String r6 = r6.getString(r0)
                        org.json.JSONObject r0 = new org.json.JSONObject
                        r0.<init>()
                        r1 = -999(0xfffffffffffffc19, float:NaN)
                        if (r6 == 0) goto L_0x0073
                        boolean r2 = android.text.TextUtils.isEmpty(r6)
                        if (r2 != 0) goto L_0x0073
                        org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x005d }
                        r2.<init>(r6)     // Catch:{ JSONException -> 0x005d }
                        java.lang.String r6 = "code"
                        int r6 = r2.optInt(r6)     // Catch:{ JSONException -> 0x005e }
                        com.xiaomi.smarthome.frame.ErrorCode r0 = com.xiaomi.smarthome.frame.ErrorCode.valueof(r6)     // Catch:{ JSONException -> 0x005e }
                        com.xiaomi.smarthome.frame.ErrorCode r3 = com.xiaomi.smarthome.frame.ErrorCode.SUCCESS     // Catch:{ JSONException -> 0x005e }
                        if (r0 != r3) goto L_0x0048
                        java.lang.String r6 = "result"
                        org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch:{ JSONException -> 0x005e }
                        if (r6 == 0) goto L_0x003d
                        boolean r0 = r2     // Catch:{ JSONException -> 0x005e }
                        if (r0 == 0) goto L_0x003e
                    L_0x003d:
                        r6 = r2
                    L_0x003e:
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3     // Catch:{ JSONException -> 0x005e }
                        if (r0 == 0) goto L_0x0087
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3     // Catch:{ JSONException -> 0x005e }
                        r0.sendSuccessMessage(r6)     // Catch:{ JSONException -> 0x005e }
                        goto L_0x0087
                    L_0x0048:
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3     // Catch:{ JSONException -> 0x005e }
                        if (r0 == 0) goto L_0x0087
                        java.lang.String r0 = "message"
                        java.lang.String r0 = r2.optString(r0)     // Catch:{ JSONException -> 0x005e }
                        com.xiaomi.smarthome.frame.AsyncCallback r3 = r3     // Catch:{ JSONException -> 0x005e }
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error     // Catch:{ JSONException -> 0x005e }
                        r4.<init>(r6, r0)     // Catch:{ JSONException -> 0x005e }
                        r3.sendFailureMessage(r4)     // Catch:{ JSONException -> 0x005e }
                        goto L_0x0087
                    L_0x005d:
                        r2 = r0
                    L_0x005e:
                        com.xiaomi.smarthome.frame.AsyncCallback r6 = r3
                        if (r6 == 0) goto L_0x0087
                        java.lang.String r6 = "message"
                        java.lang.String r6 = r2.optString(r6)
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3
                        com.xiaomi.smarthome.frame.Error r2 = new com.xiaomi.smarthome.frame.Error
                        r2.<init>(r1, r6)
                        r0.sendFailureMessage(r2)
                        goto L_0x0087
                    L_0x0073:
                        com.xiaomi.smarthome.frame.AsyncCallback r6 = r3
                        if (r6 == 0) goto L_0x0087
                        java.lang.String r6 = "message"
                        java.lang.String r6 = r0.optString(r6)
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r3
                        com.xiaomi.smarthome.frame.Error r2 = new com.xiaomi.smarthome.frame.Error
                        r2.<init>(r1, r6)
                        r0.sendFailureMessage(r2)
                    L_0x0087:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass32.onSuccess(android.os.Bundle):void");
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback2 != null) {
                        asyncCallback2.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("rpcAsyncToCloud fail " + str4 + ":" + str5, error2);
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle b(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        return b(str, str2, str3, asyncCallback, false);
    }

    public AsyncHandle b(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback, boolean z2) {
        IServerHandle iServerHandle;
        final long currentTimeMillis = System.currentTimeMillis();
        final boolean z3 = z2;
        final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
        final String str4 = str3;
        final String str5 = str;
        try {
            iServerHandle = ad().rpcAsync(str, str2, str3, new IClientCallback.Stub() {
                /* JADX WARNING: Removed duplicated region for block: B:26:0x006d  */
                /* JADX WARNING: Removed duplicated region for block: B:32:0x0098  */
                /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(android.os.Bundle r8) throws android.os.RemoteException {
                    /*
                        r7 = this;
                        long r0 = java.lang.System.currentTimeMillis()
                        long r2 = r2
                        long r0 = r0 - r2
                        long r0 = java.lang.Math.abs(r0)
                        java.lang.Class<com.xiaomi.smarthome.core.entity.device.DeviceListResult> r2 = com.xiaomi.smarthome.core.entity.device.DeviceListResult.class
                        java.lang.ClassLoader r2 = r2.getClassLoader()
                        r8.setClassLoader(r2)
                        java.lang.String r2 = "result"
                        java.lang.String r8 = r8.getString(r2)
                        org.json.JSONObject r2 = new org.json.JSONObject
                        r2.<init>()
                        r3 = -999(0xfffffffffffffc19, float:NaN)
                        if (r8 == 0) goto L_0x007e
                        boolean r4 = android.text.TextUtils.isEmpty(r8)
                        if (r4 != 0) goto L_0x007e
                        org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0068 }
                        r4.<init>(r8)     // Catch:{ JSONException -> 0x0068 }
                        java.lang.String r8 = "code"
                        int r8 = r4.optInt(r8)     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.valueof(r8)     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.ErrorCode r5 = com.xiaomi.smarthome.frame.ErrorCode.SUCCESS     // Catch:{ JSONException -> 0x0069 }
                        if (r2 != r5) goto L_0x0053
                        java.lang.String r8 = "result"
                        org.json.JSONObject r8 = r4.optJSONObject(r8)     // Catch:{ JSONException -> 0x0069 }
                        if (r8 == 0) goto L_0x0048
                        boolean r2 = r4     // Catch:{ JSONException -> 0x0069 }
                        if (r2 == 0) goto L_0x0049
                    L_0x0048:
                        r8 = r4
                    L_0x0049:
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5     // Catch:{ JSONException -> 0x0069 }
                        if (r2 == 0) goto L_0x0092
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5     // Catch:{ JSONException -> 0x0069 }
                        r2.sendSuccessMessage(r8)     // Catch:{ JSONException -> 0x0069 }
                        goto L_0x0092
                    L_0x0053:
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5     // Catch:{ JSONException -> 0x0069 }
                        if (r2 == 0) goto L_0x0092
                        java.lang.String r2 = "message"
                        java.lang.String r2 = r4.optString(r2)     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.AsyncCallback r5 = r5     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.Error r6 = new com.xiaomi.smarthome.frame.Error     // Catch:{ JSONException -> 0x0069 }
                        r6.<init>(r8, r2)     // Catch:{ JSONException -> 0x0069 }
                        r5.sendFailureMessage(r6)     // Catch:{ JSONException -> 0x0069 }
                        goto L_0x0092
                    L_0x0068:
                        r4 = r2
                    L_0x0069:
                        com.xiaomi.smarthome.frame.AsyncCallback r8 = r5
                        if (r8 == 0) goto L_0x0092
                        java.lang.String r8 = "message"
                        java.lang.String r8 = r4.optString(r8)
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        r4.<init>(r3, r8)
                        r2.sendFailureMessage(r4)
                        goto L_0x0092
                    L_0x007e:
                        com.xiaomi.smarthome.frame.AsyncCallback r8 = r5
                        if (r8 == 0) goto L_0x0092
                        java.lang.String r8 = "message"
                        java.lang.String r8 = r2.optString(r8)
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        r4.<init>(r3, r8)
                        r2.sendFailureMessage(r4)
                    L_0x0092:
                        r2 = 3000(0xbb8, double:1.482E-320)
                        int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                        if (r8 <= 0) goto L_0x00a0
                        java.lang.String r8 = r6
                        java.lang.String r2 = r7
                        r3 = 1
                        com.xiaomi.smarthome.stat.MiscStat.a(r0, r8, r2, r3)
                    L_0x00a0:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass33.onSuccess(android.os.Bundle):void");
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    long abs = Math.abs(System.currentTimeMillis() - currentTimeMillis);
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback2 != null) {
                        asyncCallback2.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("rpcAsync fail " + str5 + ":" + str4, error2);
                    if (abs > 3000) {
                        MiscStat.a(abs, str4, str5, false);
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle c(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback, boolean z2) {
        IServerHandle iServerHandle;
        final long currentTimeMillis = System.currentTimeMillis();
        final boolean z3 = z2;
        final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
        final String str4 = str3;
        final String str5 = str;
        try {
            iServerHandle = ad().rpcAsyncToLocal(str, str2, str3, new IClientCallback.Stub() {
                /* JADX WARNING: Removed duplicated region for block: B:26:0x006d  */
                /* JADX WARNING: Removed duplicated region for block: B:32:0x0098  */
                /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(android.os.Bundle r8) throws android.os.RemoteException {
                    /*
                        r7 = this;
                        long r0 = java.lang.System.currentTimeMillis()
                        long r2 = r2
                        long r0 = r0 - r2
                        long r0 = java.lang.Math.abs(r0)
                        java.lang.Class<com.xiaomi.smarthome.core.entity.device.DeviceListResult> r2 = com.xiaomi.smarthome.core.entity.device.DeviceListResult.class
                        java.lang.ClassLoader r2 = r2.getClassLoader()
                        r8.setClassLoader(r2)
                        java.lang.String r2 = "result"
                        java.lang.String r8 = r8.getString(r2)
                        org.json.JSONObject r2 = new org.json.JSONObject
                        r2.<init>()
                        r3 = -999(0xfffffffffffffc19, float:NaN)
                        if (r8 == 0) goto L_0x007e
                        boolean r4 = android.text.TextUtils.isEmpty(r8)
                        if (r4 != 0) goto L_0x007e
                        org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0068 }
                        r4.<init>(r8)     // Catch:{ JSONException -> 0x0068 }
                        java.lang.String r8 = "code"
                        int r8 = r4.optInt(r8)     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.ErrorCode r2 = com.xiaomi.smarthome.frame.ErrorCode.valueof(r8)     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.ErrorCode r5 = com.xiaomi.smarthome.frame.ErrorCode.SUCCESS     // Catch:{ JSONException -> 0x0069 }
                        if (r2 != r5) goto L_0x0053
                        java.lang.String r8 = "result"
                        org.json.JSONObject r8 = r4.optJSONObject(r8)     // Catch:{ JSONException -> 0x0069 }
                        if (r8 == 0) goto L_0x0048
                        boolean r2 = r4     // Catch:{ JSONException -> 0x0069 }
                        if (r2 == 0) goto L_0x0049
                    L_0x0048:
                        r8 = r4
                    L_0x0049:
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5     // Catch:{ JSONException -> 0x0069 }
                        if (r2 == 0) goto L_0x0092
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5     // Catch:{ JSONException -> 0x0069 }
                        r2.sendSuccessMessage(r8)     // Catch:{ JSONException -> 0x0069 }
                        goto L_0x0092
                    L_0x0053:
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5     // Catch:{ JSONException -> 0x0069 }
                        if (r2 == 0) goto L_0x0092
                        java.lang.String r2 = "message"
                        java.lang.String r2 = r4.optString(r2)     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.AsyncCallback r5 = r5     // Catch:{ JSONException -> 0x0069 }
                        com.xiaomi.smarthome.frame.Error r6 = new com.xiaomi.smarthome.frame.Error     // Catch:{ JSONException -> 0x0069 }
                        r6.<init>(r8, r2)     // Catch:{ JSONException -> 0x0069 }
                        r5.sendFailureMessage(r6)     // Catch:{ JSONException -> 0x0069 }
                        goto L_0x0092
                    L_0x0068:
                        r4 = r2
                    L_0x0069:
                        com.xiaomi.smarthome.frame.AsyncCallback r8 = r5
                        if (r8 == 0) goto L_0x0092
                        java.lang.String r8 = "message"
                        java.lang.String r8 = r4.optString(r8)
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        r4.<init>(r3, r8)
                        r2.sendFailureMessage(r4)
                        goto L_0x0092
                    L_0x007e:
                        com.xiaomi.smarthome.frame.AsyncCallback r8 = r5
                        if (r8 == 0) goto L_0x0092
                        java.lang.String r8 = "message"
                        java.lang.String r8 = r2.optString(r8)
                        com.xiaomi.smarthome.frame.AsyncCallback r2 = r5
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        r4.<init>(r3, r8)
                        r2.sendFailureMessage(r4)
                    L_0x0092:
                        r2 = 3000(0xbb8, double:1.482E-320)
                        int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                        if (r8 <= 0) goto L_0x00a0
                        java.lang.String r8 = r6
                        java.lang.String r2 = r7
                        r3 = 1
                        com.xiaomi.smarthome.stat.MiscStat.a(r0, r8, r2, r3)
                    L_0x00a0:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass34.onSuccess(android.os.Bundle):void");
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    long abs = Math.abs(System.currentTimeMillis() - currentTimeMillis);
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback2 != null) {
                        asyncCallback2.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("rpcAsync fail " + str5 + ":" + str4, error2);
                    if (abs > 3000) {
                        MiscStat.a(abs, str4, str5, false);
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle a(String str, String str2, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().renameDevice(str, str2, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("renameDevice fail", error2);
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle b(List<String> list, final AsyncCallback<String, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().delDeviceBatch(list, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        bundle.setClassLoader(DeviceListResult.class.getClassLoader());
                        asyncCallback.sendSuccessMessage(bundle.getString("result"));
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("delDeviceBatch request", error2);
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public AsyncHandle c(List<String> list, final AsyncCallback<List<Device>, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().updateDeviceBatch(list, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        bundle.setClassLoader(DeviceListResult.class.getClassLoader());
                        asyncCallback.sendSuccessMessage(bundle.getParcelableArrayList("result"));
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = new Error(error.a(), error.b());
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(error2);
                    }
                    MyLogHelper.a("updateDeviceBatch fail", error2);
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            iServerHandle = null;
            return new AsyncHandle(iServerHandle);
        }
        return new AsyncHandle(iServerHandle);
    }

    public void a(String str, final AsyncCallback<Void, Error> asyncCallback) {
        try {
            ad().localPing(str, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(error.a(), error.b()));
                    }
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public long B() {
        return this.l.nextLong();
    }

    public CTAInfo C() {
        try {
            return ad().getGlobalSettingCTA();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public AsyncHandle a(boolean z2, boolean z3, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().setGlobalSettingCTA(z2, z3, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(error.a(), error.b()));
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public boolean D() {
        return ServerCompact.b(F());
    }

    public boolean E() {
        String H2 = H();
        return !TextUtils.isEmpty(H2) && H2.equalsIgnoreCase("preview");
    }

    @Nullable
    public ServerBean F() {
        ServerBean serverBean;
        ServerBean globalSettingServer;
        synchronized (this.B) {
            serverBean = this.C;
            if (serverBean == null) {
                try {
                    if (this.m) {
                        globalSettingServer = GlobalDynamicSettingManager.a().d();
                    } else {
                        globalSettingServer = ad().getGlobalSettingServer();
                    }
                    serverBean = globalSettingServer;
                    this.C = serverBean;
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                } catch (CoreNotReadyException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return serverBean;
    }

    public AsyncHandle a(ServerBean serverBean, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().setGlobalSettingServer(serverBean, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                    CoreApi.this.G();
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(error.a(), error.b()));
                    }
                    CoreApi.this.G();
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public void G() {
        synchronized (this.B) {
            this.C = null;
        }
    }

    public String H() {
        try {
            return ad().getGlobalSettingServerEnv();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public AsyncHandle b(String str, final AsyncCallback<Void, Error> asyncCallback) {
        IServerHandle iServerHandle;
        try {
            iServerHandle = ad().setGlobalSettingServerEnv(str, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(error.a(), error.b()));
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            iServerHandle = null;
        }
        return new AsyncHandle(iServerHandle);
    }

    public Locale I() {
        Locale locale = this.E;
        if (locale == null && !this.F) {
            synchronized (this.D) {
                locale = this.E;
                if (locale == null) {
                    try {
                        Bundle globalSettingLocale = ad().getGlobalSettingLocale();
                        if (globalSettingLocale != null) {
                            locale = (Locale) globalSettingLocale.getSerializable("result");
                        }
                        this.E = locale;
                        this.F = true;
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return locale;
    }

    public AsyncHandle a(Locale locale, final AsyncCallback<Void, Error> asyncCallback) {
        AnonymousClass42 r0 = new IClientCallback.Stub() {
            public void onSuccess(Bundle bundle) throws RemoteException {
                if (asyncCallback != null) {
                    asyncCallback.sendSuccessMessage(null);
                }
                CoreApi.this.J();
            }

            public void onFailure(Bundle bundle) throws RemoteException {
                bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(error.a(), error.b()));
                }
                CoreApi.this.J();
            }
        };
        try {
            Bundle bundle = new Bundle();
            if (locale != null) {
                bundle.putSerializable("result", locale);
            }
            ad().setGlobalSettingLocale(bundle, r0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new AsyncHandle(null);
    }

    public void J() {
        synchronized (this.D) {
            this.F = false;
            this.E = null;
        }
    }

    public void a(boolean z2) {
        try {
            ad().updateWhiteList(z2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void K() {
        try {
            ad().clearWhiteList();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(int i2) {
        try {
            ad().setScanTimePeriod(i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(final WifiScanCallback wifiScanCallback) {
        try {
            ad().startScanWithCallback(new IClientCallback.Stub() {
                public void onFailure(Bundle bundle) throws RemoteException {
                }

                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (bundle != null && bundle.containsKey("result")) {
                        wifiScanCallback.a(bundle.getString("result"));
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void L() {
        try {
            ad().stopScan();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean M() {
        try {
            return ad().isBooleanValue(CoreApiStub.BooleanKey.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void a(boolean z2, final UpdateConfigCallback updateConfigCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            ad().updatePluginConfig(z2, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(UpdatePluginConfigResult.class.getClassLoader());
                    final UpdatePluginConfigResult updatePluginConfigResult = (UpdatePluginConfigResult) bundle.getParcelable("result");
                    if (updateConfigCallback != null) {
                        handler.post(new Runnable() {
                            public void run() {
                                updateConfigCallback.a(updatePluginConfigResult.f13994a, updatePluginConfigResult.b);
                            }
                        });
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    if (updateConfigCallback != null) {
                        handler.post(new Runnable() {
                            public void run() {
                                updateConfigCallback.a(pluginError);
                            }
                        });
                    }
                    MyLogHelper.a("updatePluginConfig fail", new Error(pluginError.a(), pluginError.b()));
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void N() {
        try {
            ad().clearPluginConfig((IClientCallback) null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(IClientCallback iClientCallback) {
        try {
            ad().clearPluginConfig(iClientCallback);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals(RnPluginDebugDeviceMock.f22091a)) {
            return true;
        }
        if (!this.m) {
            P();
            return this.n.containsKey(str);
        } else if (l()) {
            boolean b2 = PluginManager.a().b(str);
            if (!b2) {
                LogUtilGrey.a("CoreApi", "isPluginDevice: PluginManager does not contain the model:" + str);
            }
            return b2;
        } else {
            LogUtilGrey.a("CoreApi", "isPluginDevice: core not ready!");
            return false;
        }
    }

    public PluginRecord d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equals(RnPluginDebugDeviceMock.f22091a)) {
            return RnPluginDebugDeviceMock.a();
        }
        if (!this.m) {
            P();
            return this.n.get(str);
        } else if (l()) {
            return PluginManager.a().c(str);
        } else {
            return null;
        }
    }

    public List<PluginRecord> O() {
        if (!this.m) {
            P();
            return this.G;
        } else if (l()) {
            return PluginManager.a().j();
        } else {
            return this.G;
        }
    }

    public void P() {
        if (this.G != null && this.G.size() <= 0) {
            ServiceApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    CoreApi.this.a(true, true, (String) null);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a5 A[SYNTHETIC, Splitter:B:27:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b5 A[SYNTHETIC, Splitter:B:33:0x00b5] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x014f A[LOOP:1: B:51:0x0149->B:53:0x014f, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r5, boolean r6, java.lang.String r7) {
        /*
            r4 = this;
            boolean r6 = r4.m
            if (r6 == 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.String r6 = "CoreApi"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onPluginChanged deviceListChanged="
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ",isCoreReady="
            r0.append(r1)
            boolean r1 = r4.l()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r6, r0)
            boolean r6 = r4.l()
            if (r6 != 0) goto L_0x002e
            return
        L_0x002e:
            r6 = 0
            if (r5 == 0) goto L_0x016a
            r5 = 0
            com.xiaomi.smarthome.core.server.ICoreApi r7 = r4.ad()     // Catch:{ Throwable -> 0x006b }
            com.xiaomi.smarthome.core.server.MiHomeMemoryFile r7 = r7.getPluginRecordMemoryFile()     // Catch:{ Throwable -> 0x006b }
            android.os.Parcel r0 = r7.b()     // Catch:{ Throwable -> 0x006b }
            int r1 = r0.readInt()     // Catch:{ Throwable -> 0x006b }
            if (r1 == 0) goto L_0x0061
            int r1 = r0.readInt()     // Catch:{ Throwable -> 0x006b }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x006b }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x006b }
        L_0x004d:
            if (r6 >= r1) goto L_0x0062
            java.lang.Class<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r3 = com.xiaomi.smarthome.core.entity.plugin.PluginRecord.class
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ Throwable -> 0x0069 }
            android.os.Parcelable r3 = r0.readParcelable(r3)     // Catch:{ Throwable -> 0x0069 }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r3 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r3     // Catch:{ Throwable -> 0x0069 }
            r2.add(r3)     // Catch:{ Throwable -> 0x0069 }
            int r6 = r6 + 1
            goto L_0x004d
        L_0x0061:
            r2 = r5
        L_0x0062:
            r0.recycle()     // Catch:{ Throwable -> 0x0069 }
            r7.c()     // Catch:{ Throwable -> 0x0069 }
            goto L_0x00a3
        L_0x0069:
            r6 = move-exception
            goto L_0x006d
        L_0x006b:
            r6 = move-exception
            r2 = r5
        L_0x006d:
            com.xiaomi.smarthome.frame.FrameManager r7 = com.xiaomi.smarthome.frame.FrameManager.b()     // Catch:{ Exception -> 0x009f }
            com.xiaomi.smarthome.frame.login.LoginHostApi r7 = r7.g()     // Catch:{ Exception -> 0x009f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f }
            r0.<init>()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = "click_device_list onPluginChanged getCoreApiProxy ex "
            r0.append(r1)     // Catch:{ Exception -> 0x009f }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x009f }
            r0.append(r6)     // Catch:{ Exception -> 0x009f }
            java.lang.String r6 = " "
            r0.append(r6)     // Catch:{ Exception -> 0x009f }
            java.lang.Throwable r6 = new java.lang.Throwable     // Catch:{ Exception -> 0x009f }
            r6.<init>()     // Catch:{ Exception -> 0x009f }
            java.lang.String r6 = android.util.Log.getStackTraceString(r6)     // Catch:{ Exception -> 0x009f }
            r0.append(r6)     // Catch:{ Exception -> 0x009f }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x009f }
            r7.a((java.lang.String) r6)     // Catch:{ Exception -> 0x009f }
            goto L_0x00a3
        L_0x009f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00a3:
            if (r2 != 0) goto L_0x00b2
            com.xiaomi.smarthome.core.server.ICoreApi r6 = r4.ad()     // Catch:{ Throwable -> 0x00ae }
            java.util.List r6 = r6.getPluginRecordList()     // Catch:{ Throwable -> 0x00ae }
            goto L_0x00b3
        L_0x00ae:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00b2:
            r6 = r2
        L_0x00b3:
            if (r6 != 0) goto L_0x00df
            com.xiaomi.smarthome.frame.FrameManager r7 = com.xiaomi.smarthome.frame.FrameManager.b()     // Catch:{ Exception -> 0x00db }
            com.xiaomi.smarthome.frame.login.LoginHostApi r7 = r7.g()     // Catch:{ Exception -> 0x00db }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00db }
            r0.<init>()     // Catch:{ Exception -> 0x00db }
            java.lang.String r1 = "click_device_list onPluginChanged recordList null "
            r0.append(r1)     // Catch:{ Exception -> 0x00db }
            java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ Exception -> 0x00db }
            r1.<init>()     // Catch:{ Exception -> 0x00db }
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)     // Catch:{ Exception -> 0x00db }
            r0.append(r1)     // Catch:{ Exception -> 0x00db }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00db }
            r7.a((java.lang.String) r0)     // Catch:{ Exception -> 0x00db }
            goto L_0x00df
        L_0x00db:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00df:
            if (r6 == 0) goto L_0x0111
            int r7 = r6.size()
            if (r7 > 0) goto L_0x0111
            com.xiaomi.smarthome.frame.FrameManager r7 = com.xiaomi.smarthome.frame.FrameManager.b()     // Catch:{ Exception -> 0x010d }
            com.xiaomi.smarthome.frame.login.LoginHostApi r7 = r7.g()     // Catch:{ Exception -> 0x010d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010d }
            r0.<init>()     // Catch:{ Exception -> 0x010d }
            java.lang.String r1 = "click_device_list onPluginChanged recordList size 0 "
            r0.append(r1)     // Catch:{ Exception -> 0x010d }
            java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ Exception -> 0x010d }
            r1.<init>()     // Catch:{ Exception -> 0x010d }
            java.lang.String r1 = android.util.Log.getStackTraceString(r1)     // Catch:{ Exception -> 0x010d }
            r0.append(r1)     // Catch:{ Exception -> 0x010d }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x010d }
            r7.a((java.lang.String) r0)     // Catch:{ Exception -> 0x010d }
            goto L_0x0111
        L_0x010d:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0111:
            java.lang.String r7 = "CoreApi"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onPluginChanged recordList="
            r0.append(r1)
            if (r6 != 0) goto L_0x0120
            goto L_0x0128
        L_0x0120:
            int r5 = r6.size()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
        L_0x0128:
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r7, r5)
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r5 = r4.G
            r5.clear()
            if (r6 == 0) goto L_0x013e
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r5 = r4.G
            r5.addAll(r6)
        L_0x013e:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r5 = r4.n
            r5.clear()
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r5 = r4.G
            java.util.Iterator r5 = r5.iterator()
        L_0x0149:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x015f
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r6 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r6
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r7 = r4.n
            java.lang.String r0 = r6.o()
            r7.put(r0, r6)
            goto L_0x0149
        L_0x015f:
            r4.d()
            java.lang.String r5 = "CoreApi"
            java.lang.String r6 = "onPluginReady set"
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r5, r6)
            goto L_0x01b3
        L_0x016a:
            boolean r5 = android.text.TextUtils.isEmpty(r7)
            if (r5 != 0) goto L_0x01b3
            com.xiaomi.smarthome.core.server.ICoreApi r5 = r4.ad()     // Catch:{ Throwable -> 0x01af }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r5 = r5.getPluginRecord(r7)     // Catch:{ Throwable -> 0x01af }
        L_0x0178:
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r0 = r4.G     // Catch:{ Throwable -> 0x01af }
            int r0 = r0.size()     // Catch:{ Throwable -> 0x01af }
            if (r6 >= r0) goto L_0x019d
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r0 = r4.G     // Catch:{ Throwable -> 0x01af }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ Throwable -> 0x01af }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = (com.xiaomi.smarthome.core.entity.plugin.PluginRecord) r0     // Catch:{ Throwable -> 0x01af }
            java.lang.String r0 = r0.o()     // Catch:{ Throwable -> 0x01af }
            boolean r0 = r0.equalsIgnoreCase(r7)     // Catch:{ Throwable -> 0x01af }
            if (r0 == 0) goto L_0x019a
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r0 = r4.G     // Catch:{ Throwable -> 0x01af }
            int r1 = r6 + -1
            r0.remove(r6)     // Catch:{ Throwable -> 0x01af }
            r6 = r1
        L_0x019a:
            int r6 = r6 + 1
            goto L_0x0178
        L_0x019d:
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r6 = r4.n     // Catch:{ Throwable -> 0x01af }
            r6.remove(r7)     // Catch:{ Throwable -> 0x01af }
            if (r5 == 0) goto L_0x01b3
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r6 = r4.G     // Catch:{ Throwable -> 0x01af }
            r6.add(r5)     // Catch:{ Throwable -> 0x01af }
            java.util.Map<java.lang.String, com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r6 = r4.n     // Catch:{ Throwable -> 0x01af }
            r6.put(r7, r5)     // Catch:{ Throwable -> 0x01af }
            goto L_0x01b3
        L_0x01af:
            r5 = move-exception
            r5.printStackTrace()
        L_0x01b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.a(boolean, boolean, java.lang.String):void");
    }

    public String b(int i2) {
        try {
            return ad().getModelByProductId(i2);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public int e(String str) {
        try {
            return ad().getProductIdByModel(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public String f(String str) {
        try {
            return ad().getModelBySSID(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public boolean g(String str) {
        if (TextUtils.isEmpty(str) || RnPluginDebugDeviceMock.f22091a.equals(str)) {
            return false;
        }
        try {
            return ad().isPluginForceUpdating(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void a(final String str, final DeletePluginCallback deletePluginCallback) {
        PluginRecord d2;
        final Handler handler = new Handler(Looper.getMainLooper());
        if (!TextUtils.isEmpty(str) && (d2 = d(str)) != null && d2.k()) {
            try {
                ad().deletePlugin(str, new IClientCallback.Stub() {
                    public void onSuccess(Bundle bundle) throws RemoteException {
                        if (deletePluginCallback != null) {
                            handler.post(new Runnable() {
                                public void run() {
                                    deletePluginCallback.a(str);
                                }
                            });
                        }
                    }

                    public void onFailure(Bundle bundle) throws RemoteException {
                        if (deletePluginCallback != null) {
                            bundle.setClassLoader(Error.class.getClassLoader());
                            final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                            handler.post(new Runnable() {
                                public void run() {
                                    deletePluginCallback.a(pluginError);
                                }
                            });
                            MyLogHelper.a("deletePlugin fail", new Error(pluginError.a(), pluginError.b()));
                        }
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(String str, final DownloadPluginCallback downloadPluginCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        if (!TextUtils.isEmpty(str)) {
            final MijiaWrapper mijiaWrapper = new MijiaWrapper(downloadPluginCallback);
            try {
                ad().downloadPlugin(str, new IClientCallback.Stub() {
                    public void onSuccess(Bundle bundle) throws RemoteException {
                        if (mijiaWrapper.f18690a != null) {
                            bundle.setClassLoader(DownloadPluginResult.class.getClassLoader());
                            final DownloadPluginResult downloadPluginResult = (DownloadPluginResult) bundle.getParcelable("result");
                            if (downloadPluginResult.h == 1) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onStart(downloadPluginResult.i, downloadPluginResult.j);
                                    }
                                });
                            } else if (downloadPluginResult.h == 7) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onDownInfoSuccess(downloadPluginResult.i, downloadPluginResult.j);
                                    }
                                });
                            } else if (downloadPluginResult.h == 2) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onStartAlready(downloadPluginResult.i, downloadPluginResult.j);
                                    }
                                });
                            } else if (downloadPluginResult.h == 3) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onProgress(downloadPluginResult.i, downloadPluginResult.k);
                                    }
                                });
                            } else if (downloadPluginResult.h == 4) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        mijiaWrapper.f18690a = null;
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onSuccess(downloadPluginResult.i);
                                    }
                                });
                            } else if (downloadPluginResult.h == 5) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        if (downloadPluginResult.i == null || downloadPluginResult.i.j() == null || !TextUtils.isEmpty(downloadPluginResult.i.j().g())) {
                                            PluginError pluginError = new PluginError(-1, "");
                                            mijiaWrapper.f18690a = null;
                                            ((DownloadPluginCallback) mijiaWrapper.f18690a).onFailure(pluginError);
                                            MyLogHelper.a("downloadPlugin fail2", new Error(pluginError.a(), pluginError.b()));
                                            return;
                                        }
                                        PluginError pluginError2 = new PluginError(com.xiaomi.smarthome.core.entity.Error.f, "should upgrade mihome apk...");
                                        mijiaWrapper.f18690a = null;
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onFailure(pluginError2);
                                        MyLogHelper.a("downloadPlugin fail2", new Error(pluginError2.a(), pluginError2.b()));
                                    }
                                });
                            } else if (downloadPluginResult.h == 6) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        mijiaWrapper.f18690a = null;
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onCancel();
                                    }
                                });
                            } else {
                                handler.post(new Runnable() {
                                    public void run() {
                                        PluginError pluginError = new PluginError(-1, "unknown error");
                                        mijiaWrapper.f18690a = null;
                                        ((DownloadPluginCallback) mijiaWrapper.f18690a).onFailure(pluginError);
                                        MyLogHelper.a("downloadPlugin fail3", new Error(pluginError.a(), pluginError.b()));
                                    }
                                });
                            }
                        }
                    }

                    public void onFailure(Bundle bundle) throws RemoteException {
                        bundle.setClassLoader(Error.class.getClassLoader());
                        final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                        if (mijiaWrapper.f18690a != null) {
                            handler.post(new Runnable() {
                                public void run() {
                                    mijiaWrapper.f18690a = null;
                                    ((DownloadPluginCallback) mijiaWrapper.f18690a).onFailure(pluginError);
                                }
                            });
                        }
                        MyLogHelper.a("downloadPlugin fail4", new Error(pluginError.a(), pluginError.b()));
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if (downloadPluginCallback != null) {
            handler.post(new Runnable() {
                public void run() {
                    PluginError pluginError = new PluginError(-1, "model is null");
                    downloadPluginCallback.onFailure(pluginError);
                    MyLogHelper.a("downloadPlugin fail1", new Error(pluginError.a(), pluginError.b()));
                }
            });
        }
    }

    public void a(String str, boolean z2, final InstallPluginCallback installPluginCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        if (!TextUtils.isEmpty(str)) {
            try {
                ad().installPlugin(str, z2, new IClientCallback.Stub() {
                    public void onSuccess(Bundle bundle) throws RemoteException {
                        if (installPluginCallback != null) {
                            bundle.setClassLoader(DownloadPluginResult.class.getClassLoader());
                            final InstallPluginResult installPluginResult = (InstallPluginResult) bundle.getParcelable("result");
                            if (installPluginResult.e == 1) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        installPluginCallback.onStart(installPluginResult.f);
                                    }
                                });
                            } else if (installPluginResult.e == 2) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        installPluginCallback.onSuccess(installPluginResult.f);
                                    }
                                });
                            } else if (installPluginResult.e == 3) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        PluginError pluginError = new PluginError(-1, "");
                                        installPluginCallback.onFailure(pluginError);
                                        MyLogHelper.a("installPlugin fail2", new Error(pluginError.a(), pluginError.b()));
                                    }
                                });
                            } else if (installPluginResult.e == 4) {
                                handler.post(new Runnable() {
                                    public void run() {
                                        PluginError pluginError = new PluginError(-1, "");
                                        installPluginCallback.onFailure(pluginError);
                                        MyLogHelper.a("installPlugin fail3", new Error(pluginError.a(), pluginError.b()));
                                    }
                                });
                            } else {
                                handler.post(new Runnable() {
                                    public void run() {
                                        PluginError pluginError = new PluginError(-1, "unknown error");
                                        installPluginCallback.onFailure(pluginError);
                                        MyLogHelper.a("installPlugin fail4", new Error(pluginError.a(), pluginError.b()));
                                    }
                                });
                            }
                        }
                    }

                    public void onFailure(Bundle bundle) throws RemoteException {
                        bundle.setClassLoader(Error.class.getClassLoader());
                        final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                        if (installPluginCallback != null) {
                            handler.post(new Runnable() {
                                public void run() {
                                    installPluginCallback.onFailure(pluginError);
                                    MyLogHelper.a("installPlugin fail5", new Error(pluginError.a(), pluginError.b()));
                                }
                            });
                        }
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else if (installPluginCallback != null) {
            handler.post(new Runnable() {
                public void run() {
                    PluginError pluginError = new PluginError(-1, "model is null");
                    installPluginCallback.onFailure(pluginError);
                    MyLogHelper.a("installPlugin fail1", new Error(pluginError.a(), pluginError.b()));
                }
            });
        }
    }

    public void a(boolean z2, final UpdatePluginAllCallback updatePluginAllCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            ad().updateAllPlugin(z2, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (updatePluginAllCallback != null) {
                        bundle.setClassLoader(UpdateAllPluginResult.class.getClassLoader());
                        UpdateAllPluginResult updateAllPluginResult = (UpdateAllPluginResult) bundle.getParcelable("result");
                        if (updateAllPluginResult.c == 1) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginAllCallback.a();
                                }
                            });
                        } else if (updateAllPluginResult.c == 2) {
                            final PluginError pluginError = new PluginError(-1, "");
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginAllCallback.a(pluginError);
                                }
                            });
                            MyLogHelper.a("updateAllPlugin fail1", new Error(pluginError.a(), pluginError.b()));
                        } else {
                            final PluginError pluginError2 = new PluginError(-1, "unknown error");
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginAllCallback.a(pluginError2);
                                    MyLogHelper.a("updateAllPlugin fail2", new Error(pluginError2.a(), pluginError2.b()));
                                }
                            });
                        }
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    if (updatePluginAllCallback != null) {
                        handler.post(new Runnable() {
                            public void run() {
                                updatePluginAllCallback.a(pluginError);
                            }
                        });
                    }
                    MyLogHelper.a("updateAllPlugin fail3", new Error(pluginError.a(), pluginError.b()));
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, boolean z2, final UpdatePluginCallback updatePluginCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            ad().updatePlugin(str, z2, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (updatePluginCallback != null) {
                        bundle.setClassLoader(UpdatePluginResult.class.getClassLoader());
                        final UpdatePluginResult updatePluginResult = (UpdatePluginResult) bundle.getParcelable("result");
                        if (updatePluginResult.k == 1) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.a(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 2) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.b(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 3) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.c(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 4) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.d(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 5) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.a(updatePluginResult.l, updatePluginResult.m);
                                }
                            });
                        } else if (updatePluginResult.k == 6) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.e(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 7) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.f(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 8) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.g(updatePluginResult.l);
                                }
                            });
                        } else if (updatePluginResult.k == 9) {
                            handler.post(new Runnable() {
                                public void run() {
                                    updatePluginCallback.a(updatePluginResult.l, updatePluginResult.n);
                                }
                            });
                        } else if (updatePluginResult.k == 10) {
                            handler.post(new Runnable() {
                                public void run() {
                                    PluginError pluginError = new PluginError(-1, "error");
                                    updatePluginCallback.a(pluginError);
                                    MyLogHelper.a("updatePlugin fail1", new Error(pluginError.a(), pluginError.b()));
                                }
                            });
                        } else {
                            handler.post(new Runnable() {
                                public void run() {
                                    PluginError pluginError = new PluginError(-1, "unknown error");
                                    updatePluginCallback.a(pluginError);
                                    MyLogHelper.a("updatePlugin fail2", new Error(pluginError.a(), pluginError.b()));
                                }
                            });
                        }
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    if (updatePluginCallback != null) {
                        handler.post(new Runnable() {
                            public void run() {
                                updatePluginCallback.a(pluginError);
                                MyLogHelper.a("updatePlugin fail3", new Error(pluginError.a(), pluginError.b()));
                            }
                        });
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, PluginDownloadTask pluginDownloadTask, final CancelPluginDownloadCallback cancelPluginDownloadCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            ad().cancelPluginDownload(str, pluginDownloadTask, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (cancelPluginDownloadCallback != null) {
                        handler.post(new Runnable() {
                            public void run() {
                                cancelPluginDownloadCallback.a();
                            }
                        });
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Error.class.getClassLoader());
                    final PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    if (cancelPluginDownloadCallback != null) {
                        handler.post(new Runnable() {
                            public void run() {
                                cancelPluginDownloadCallback.a(pluginError);
                            }
                        });
                    }
                    MyLogHelper.a("cancelPluginDownload fail", new Error(pluginError.a(), pluginError.b()));
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void c(final AsyncCallback<Boolean, Error> asyncCallback) {
        try {
            ad().getPluginAutoUpdate(new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Const.class.getClassLoader());
                    boolean z = bundle.getBoolean("result");
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(Boolean.valueOf(z));
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                    PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(pluginError.a(), pluginError.b()));
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(boolean z2, AsyncCallback<Void, Error> asyncCallback) {
        final MijiaWrapper mijiaWrapper = new MijiaWrapper(asyncCallback);
        try {
            ad().setPluginAutoUpdate(z2, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    AsyncCallback asyncCallback = (AsyncCallback) mijiaWrapper.f18690a;
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                    mijiaWrapper.f18690a = null;
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                    PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    AsyncCallback asyncCallback = (AsyncCallback) mijiaWrapper.f18690a;
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(pluginError.a(), pluginError.b()));
                    }
                    mijiaWrapper.f18690a = null;
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<PluginPackageInfo> Q() {
        try {
            return ad().getPluginInstalledPackageInfoList();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public PluginPackageInfo a(long j2) {
        try {
            return ad().getPluginInstalledPackageInfo(j2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public List<PluginPackageInfo> R() {
        try {
            return ad().getPluginDownloadedPackageInfoList();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(final DebugPackageCallback debugPackageCallback) {
        final Handler handler = new Handler(Looper.getMainLooper());
        try {
            ad().debugPluginPackage(new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (debugPackageCallback != null) {
                        bundle.setClassLoader(DownloadPluginDebugPackageResult.class.getClassLoader());
                        final DownloadPluginDebugPackageResult downloadPluginDebugPackageResult = (DownloadPluginDebugPackageResult) bundle.getParcelable("result");
                        if (downloadPluginDebugPackageResult.e == 1) {
                            handler.post(new Runnable() {
                                public void run() {
                                    debugPackageCallback.a();
                                }
                            });
                        } else if (downloadPluginDebugPackageResult.e == 2) {
                            handler.post(new Runnable() {
                                public void run() {
                                    debugPackageCallback.a(downloadPluginDebugPackageResult.f);
                                }
                            });
                        } else if (downloadPluginDebugPackageResult.e == 3) {
                            handler.post(new Runnable() {
                                public void run() {
                                    debugPackageCallback.b();
                                }
                            });
                        } else if (downloadPluginDebugPackageResult.e == 4) {
                            handler.post(new Runnable() {
                                public void run() {
                                    debugPackageCallback.b(downloadPluginDebugPackageResult.f);
                                }
                            });
                        }
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(com.xiaomi.smarthome.core.entity.Error.class.getClassLoader());
                    PluginError pluginError = (PluginError) bundle.getParcelable("error");
                    if (debugPackageCallback != null) {
                        debugPackageCallback.c(pluginError.b());
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void d(final AsyncCallback<Void, Error> asyncCallback) {
        try {
            ad().dumpPlugin(new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(-1, ""));
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void S() {
        try {
            ad().loadLocalPluginConfig();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            jSONObject.put(Downloads.COLUMN_REFERER, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        a(StatType.EVENT, "page_start", jSONObject.toString(), (String) null, false);
        a(StatType.EVENT, "PageStart", jSONObject.toString(), (String) null, false);
    }

    public void a(String str, String str2, int i2) {
        JSONObject jSONObject = new JSONObject();
        String str3 = str;
        try {
            jSONObject.put("name", str);
            jSONObject.put(Downloads.COLUMN_REFERER, str2);
            jSONObject.put(LogBuilder.i, i2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        a(StatType.EVENT, "page_end", jSONObject.toString(), (String) null, false);
        a(StatType.EVENT, "PageEnd", jSONObject.toString(), (String) null, false);
    }

    public void a(StatType statType, String str, String str2, String str3, boolean z2) {
        a(statType, "mihome", str, str2, str3, z2);
    }

    public boolean b(String str, boolean z2) {
        try {
            return ad().postStatRecord(str, z2);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public String a(long j2, long j3) {
        try {
            return ad().takeStatSession(j2, j3);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(StatType statType, String str, String str2, String str3, String str4, boolean z2) {
        try {
            ad().addStatRecord(statType, str, str2, str3, str4, z2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void T() {
        try {
            ad().uploadStat();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void U() {
        try {
            ad().forceUpdateScene();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void V() {
        try {
            ad().resetCore();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(SearchRequest searchRequest, final SearchResponse searchResponse) {
        try {
            ad().searchBluetoothDevice(searchRequest, new SearchResponse.Stub() {
                public void onSearchStarted() throws RemoteException {
                    CoreApi.this.i.post(new Runnable() {
                        public void run() {
                            try {
                                if (searchResponse != null) {
                                    searchResponse.onSearchStarted();
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }

                public void onDeviceFounded(final SearchResult searchResult) throws RemoteException {
                    CoreApi.this.i.post(new Runnable() {
                        public void run() {
                            try {
                                if (searchResponse != null) {
                                    searchResponse.onDeviceFounded(searchResult);
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }

                public void onSearchStopped() throws RemoteException {
                    CoreApi.this.i.post(new Runnable() {
                        public void run() {
                            try {
                                if (searchResponse != null) {
                                    searchResponse.onSearchStopped();
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }

                public void onSearchCanceled() throws RemoteException {
                    CoreApi.this.i.post(new Runnable() {
                        public void run() {
                            try {
                                if (searchResponse != null) {
                                    searchResponse.onSearchCanceled();
                                }
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(SearchRequest searchRequest, final LocalSearchResponse localSearchResponse) {
        if (searchRequest == null || localSearchResponse == null) {
            BluetoothLog.f("searchMiioBluetoothDevice: request or response null");
            return;
        }
        try {
            ad().searchMiioBluetoothDevice(searchRequest, new IBleResponse.Stub() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    CoreApi.this.i.post(new BleSearchRunnable(i, bundle) {
                        public void run() {
                            if (this.f14104a == BtConstants.t) {
                                localSearchResponse.a();
                            } else if (this.f14104a == BtConstants.v) {
                                localSearchResponse.b();
                            } else if (this.f14104a == BtConstants.u) {
                                localSearchResponse.c();
                            } else if (this.f14104a == BtConstants.w && this.b != null) {
                                this.b.setClassLoader(getClass().getClassLoader());
                                BtDevice btDevice = null;
                                try {
                                    btDevice = (BtDevice) this.b.getParcelable("extra.device");
                                } catch (Exception e) {
                                    BluetoothLog.a((Throwable) e);
                                }
                                if (btDevice != null) {
                                    localSearchResponse.a(btDevice);
                                }
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
    }

    public void W() {
        try {
            ad().stopSearchBluetoothDevice();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, int i2, Bundle bundle, final IBleResponse iBleResponse) {
        try {
            ad().callBluetoothApi(str, i2, bundle, new BluetoothResponse() {
                public void onResponse(final int i, final Bundle bundle) throws RemoteException {
                    CoreApi.this.i.post(new Runnable() {
                        public void run() {
                            if (iBleResponse != null) {
                                try {
                                    if (bundle != null) {
                                        bundle.setClassLoader(getClass().getClassLoader());
                                    }
                                    iBleResponse.onResponse(i, bundle);
                                } catch (Throwable th) {
                                    BluetoothLog.a(th);
                                }
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, String str2, String str3, String str4, final Response.BleUpgradeResponse bleUpgradeResponse) {
        try {
            ad().startBleMeshUpgrade(str, str2, str3, str4, new IBleMeshUpgradeResponse.Stub() {
                public void onProgress(final int i) throws RemoteException {
                    if (bleUpgradeResponse != null) {
                        BluetoothContextManager.c(new Runnable() {
                            public void run() {
                                bleUpgradeResponse.onProgress(i);
                            }
                        });
                    }
                }

                public void onResponse(final int i, final String str) throws RemoteException {
                    if (bleUpgradeResponse != null) {
                        BluetoothContextManager.c(new Runnable() {
                            public void run() {
                                bleUpgradeResponse.onResponse(i, str);
                            }
                        });
                    }
                }

                public boolean isMeshDevice() throws RemoteException {
                    if (bleUpgradeResponse != null) {
                        return bleUpgradeResponse.isMeshDevice();
                    }
                    return false;
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public void h(String str) {
        try {
            ad().cancelBleMeshUpgrade(str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public byte[] a(String str, byte[] bArr) {
        try {
            return ad().miotBleEncryptSync(str, bArr);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return null;
        }
    }

    public byte[] b(String str, byte[] bArr) {
        try {
            return ad().miotBleDecryptSync(str, bArr);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return null;
        }
    }

    public ISecureConnectHandler a(String str, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().secureConnect(str, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler b(String str, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().securityChipConnect(str, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler a(String str, String str2, String str3, int i2, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().securityChipPincodeConnect(str, str2, str3, i2, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler c(String str, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().securityChipSharedDeviceConnect(str, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler d(String str, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().bleMeshBind(str, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler a(String str, String str2, String str3, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().bleMeshConnect(str, str2, str3, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler e(String str, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().bleStandardAuthBind(str, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ISecureConnectHandler f(String str, SecureConnectOptions secureConnectOptions, final IBleSecureConnectResponse iBleSecureConnectResponse) {
        try {
            return ad().bleStandardAuthConnect(str, secureConnectOptions, new ISecureConnectResponse.Stub() {
                public void onConnectResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.a(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onAuthResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.b(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onBindResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.c(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }

                public void onLastResponse(final int i, final Bundle bundle) throws RemoteException {
                    if (bundle != null) {
                        bundle.setClassLoader(getClass().getClassLoader());
                    }
                    BluetoothContextManager.c(new Runnable() {
                        public void run() {
                            try {
                                iBleSecureConnectResponse.d(i, bundle);
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                        }
                    });
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void a(String str, int i2, Bundle bundle) {
        try {
            ad().getBluetoothCache(str, i2, bundle);
            if (bundle != null) {
                bundle.setClassLoader(getClass().getClassLoader());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b(String str, int i2, Bundle bundle) {
        try {
            ad().setBluetoothCache(str, i2, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String b(String str, String str2) {
        try {
            return ad().getRootNodeValue(str, str2);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return "";
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public void a(final UPnPRequest uPnPRequest, final AsyncCallback<String, Error> asyncCallback) {
        try {
            ad().callUPnPApi(uPnPRequest, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        bundle.setClassLoader(Const.class.getClassLoader());
                        asyncCallback.sendSuccessMessage(bundle.getString("result"));
                    }
                }

                public void onFailure(Bundle bundle) throws RemoteException {
                    bundle.setClassLoader(Const.class.getClassLoader());
                    com.xiaomi.smarthome.core.entity.Error error = (com.xiaomi.smarthome.core.entity.Error) bundle.getParcelable("error");
                    Error error2 = null;
                    if (error != null) {
                        asyncCallback.sendFailureMessage(new Error(error.a(), error.b()));
                    } else {
                        asyncCallback.sendFailureMessage(new Error(-1, (String) null));
                    }
                    if (error != null) {
                        error2 = new Error(error.a(), error.b());
                    }
                    MyLogHelper.a("callUPnPApi fail " + uPnPRequest.toString(), error2);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void i(String str) {
        try {
            ad().onActivityResume(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void X() {
        this.G.clear();
        this.n.clear();
        if (this.h != null) {
            this.h.post(new Runnable() {
                public void run() {
                    CoreApi.this.j.clear();
                }
            });
        } else {
            this.j.clear();
        }
        this.k.clear();
        this.H.clear();
    }

    public void a(int i2, String str, String str2) {
        try {
            ServerBean F2 = a().F();
            if (!a().D() || !ServerCompact.d(F2)) {
                ad().log(i2, str, str2);
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public void a(String str, String str2, String[] strArr, boolean z2, IClientCallback iClientCallback) {
        try {
            ad().uploadLogFile(str, str2, strArr, z2, iClientCallback);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public static class CoreNotReadyException extends Exception {
        public CoreNotReadyException(String str) {
            super(str);
        }
    }

    private class SmartHomeRequestRecord {

        /* renamed from: a  reason: collision with root package name */
        long f16113a;
        int b;
        WeakReference<AsyncHandle> c;

        private SmartHomeRequestRecord() {
        }
    }

    private class RouterRequestRecord {

        /* renamed from: a  reason: collision with root package name */
        long f16112a;
        int b;
        WeakReference<AsyncHandle> c;

        private RouterRequestRecord() {
        }
    }

    public boolean a(String str, int i2, boolean z2) {
        try {
            return ad().setAlertConfigs(str, i2, z2);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return false;
        }
    }

    public void Y() {
        try {
            ad().applicationEnterBackground();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void c(String str, String str2) {
        try {
            ad().installDebugRNPluginWithoutPackage(str2, str);
        } catch (Throwable unused) {
        }
    }

    public void Z() {
        try {
            ad().applicationEnterForground();
        } catch (Throwable unused) {
        }
    }

    public IClassicBtRequest aa() {
        try {
            return ad().getClassicBtRequestImpl();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void a(IClassicBtResponse iClassicBtResponse) {
        try {
            ad().addClassicBtResponse(iClassicBtResponse);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public void a(HomeDeviceInfo homeDeviceInfo) {
        try {
            ad().setCurrentHome(homeDeviceInfo);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public HomeDeviceInfo j(String str) {
        try {
            return ad().getSharedHomeDeviceInfo(str);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void a(long j2, long j3, final AsyncCallback<Object, Error> asyncCallback) {
        try {
            ad().loadHomeDeviceList(j2, j3, new IClientCallback.Stub() {
                public void onSuccess(Bundle bundle) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(bundle);
                    }
                }

                /* JADX WARNING: type inference failed for: r4v7, types: [android.os.Parcelable] */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onFailure(android.os.Bundle r4) throws android.os.RemoteException {
                    /*
                        r3 = this;
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r11
                        if (r0 == 0) goto L_0x0053
                        r0 = 0
                        if (r4 == 0) goto L_0x0019
                        java.lang.Class<com.xiaomi.smarthome.frame.Error> r0 = com.xiaomi.smarthome.frame.Error.class
                        java.lang.ClassLoader r0 = r0.getClassLoader()
                        r4.setClassLoader(r0)
                        java.lang.String r0 = "error"
                        android.os.Parcelable r4 = r4.getParcelable(r0)
                        r0 = r4
                        com.xiaomi.smarthome.core.entity.net.NetError r0 = (com.xiaomi.smarthome.core.entity.net.NetError) r0
                    L_0x0019:
                        if (r0 != 0) goto L_0x0025
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        r0 = -9999(0xffffffffffffd8f1, float:NaN)
                        java.lang.String r1 = "errorBundle is null"
                        r4.<init>(r0, r1)
                        goto L_0x004e
                    L_0x0025:
                        java.lang.String r4 = r0.c()
                        boolean r4 = android.text.TextUtils.isEmpty(r4)
                        if (r4 != 0) goto L_0x0041
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        int r1 = r0.a()
                        java.lang.String r2 = r0.b()
                        java.lang.String r0 = r0.c()
                        r4.<init>(r1, r2, r0)
                        goto L_0x004e
                    L_0x0041:
                        com.xiaomi.smarthome.frame.Error r4 = new com.xiaomi.smarthome.frame.Error
                        int r1 = r0.a()
                        java.lang.String r0 = r0.b()
                        r4.<init>(r1, r0)
                    L_0x004e:
                        com.xiaomi.smarthome.frame.AsyncCallback r0 = r11
                        r0.onFailure(r4)
                    L_0x0053:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.core.CoreApi.AnonymousClass72.onFailure(android.os.Bundle):void");
                }
            });
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (CoreNotReadyException e3) {
            e3.printStackTrace();
        }
    }

    public static boolean ab() {
        return v;
    }
}
