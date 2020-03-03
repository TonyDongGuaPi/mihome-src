package com.xiaomi.smarthome.core.server;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.gms.measurement.AppMeasurement;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalErrorCode;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.Error;
import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.account.OAuthAccount;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.Location;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.entity.globaldynamicsetting.CTAInfo;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.entity.plugin.DeletePluginResult;
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
import com.xiaomi.smarthome.core.server.ICoreApi;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtRequest;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.debug.NetRequestWarningActivity;
import com.xiaomi.smarthome.core.server.internal.Const;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.api.MiRechargeApi;
import com.xiaomi.smarthome.core.server.internal.api.MiShopApi;
import com.xiaomi.smarthome.core.server.internal.api.RouterApi;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeAesApi;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeHttpsApi;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeOAuthHttpsApi;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeOpenApi;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothService;
import com.xiaomi.smarthome.core.server.internal.bluetooth.ClassicBtService;
import com.xiaomi.smarthome.core.server.internal.bluetooth.MiuiSDKHelper;
import com.xiaomi.smarthome.core.server.internal.bluetooth.mesh.MeshDfuManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.BleSecureConnectManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions;
import com.xiaomi.smarthome.core.server.internal.cta.CTAManager;
import com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam;
import com.xiaomi.smarthome.core.server.internal.device.DeviceHandle;
import com.xiaomi.smarthome.core.server.internal.device.DeviceManager;
import com.xiaomi.smarthome.core.server.internal.device.LocalDeviceApiInternal;
import com.xiaomi.smarthome.core.server.internal.device.api.AsyncResponseCallback;
import com.xiaomi.smarthome.core.server.internal.device.api.BatchRpcApi;
import com.xiaomi.smarthome.core.server.internal.device.api.DeviceApiInternal;
import com.xiaomi.smarthome.core.server.internal.device.api.RpcErrorDescription;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.homeroom.CoreSharedHomeManager;
import com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.scene.SceneManager;
import com.xiaomi.smarthome.core.server.internal.statistic.StatManager;
import com.xiaomi.smarthome.core.server.internal.upnp.UPnPService;
import com.xiaomi.smarthome.core.server.internal.util.AccountUtil;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.util.ProcUtil;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.core.server.internal.whitelist.WhiteListManager;
import com.xiaomi.smarthome.core.server.internal.wifiscanservice.WifiScanServices;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.MyLogger;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.report.StatLogSender;
import com.xiaomi.smarthome.stat.report.StatSession;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class CoreApiStub extends ICoreApi.Stub {
    private static final String TAG = "CoreApiStub";
    boolean mIsPromoteSuccess = false;
    private Map<String, Boolean> statusMap = new ConcurrentHashMap();

    public interface BooleanKey {

        /* renamed from: a  reason: collision with root package name */
        public static final String f14038a = "KEY.IS_DEVELOPER";
        public static final String b = "KEY.IS_SCAN";
        public static final String c = "KEY.IS_UPGRADING_FIRMWARE";
    }

    public OAuthAccount getOAuthAccount() throws RemoteException {
        return null;
    }

    public IBleChannelWriter registerChannelReader(String str, IBleChannelReader iBleChannelReader) throws RemoteException {
        return null;
    }

    public void registerClientApi(final IClientApi iClientApi) {
        try {
            final int callingPid = getCallingPid();
            final int callingUid = getCallingUid();
            if (GlobalSetting.u) {
                Log.d("login", "CoreApiStub registerClientApi in");
            }
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    if (GlobalSetting.u) {
                        Log.d("login", "ClientApiStub registerClientApi CoreAsyncTask in");
                    }
                    CoreManager.a().a(iClientApi, callingPid, callingUid);
                    CoreApi a2 = CoreApi.a();
                    if (a2.h()) {
                        try {
                            iClientApi.onAccountReady(a2.q(), a2.s());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, Log.getStackTraceString(e));
                        }
                    }
                    if (a2.i()) {
                        try {
                            iClientApi.onGlobalDynamicSettingReady();
                        } catch (RemoteException e2) {
                            Log.e(CoreApiStub.TAG, Log.getStackTraceString(e2));
                        }
                    }
                    if (a2.j()) {
                        try {
                            iClientApi.onStatisticReady();
                        } catch (RemoteException e3) {
                            Log.e(CoreApiStub.TAG, Log.getStackTraceString(e3));
                        }
                    }
                    if (a2.k()) {
                        try {
                            iClientApi.onPluginReady();
                        } catch (RemoteException e4) {
                            Log.e(CoreApiStub.TAG, Log.getStackTraceString(e4));
                        }
                    }
                    if (a2.l()) {
                        try {
                            iClientApi.onCoreReady();
                        } catch (RemoteException e5) {
                            Log.e(CoreApiStub.TAG, Log.getStackTraceString(e5));
                        }
                    }
                }
            }.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gc() throws RemoteException {
        if (HostSetting.g || HostSetting.i) {
            CommonApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    System.gc();
                }
            });
        }
    }

    public boolean isBooleanValue(String str) throws RemoteException {
        try {
            int callingPid = getCallingPid();
            if (!CoreManager.a().b(callingPid)) {
                return false;
            }
            if (BooleanKey.f14038a.equals(str)) {
                return WhiteListManager.a().d();
            }
            if (!BooleanKey.b.equals(str)) {
                return false;
            }
            if (!WhiteListManager.a().b(callingPid, getCallingUid())) {
                return false;
            }
            return WifiScanServices.a();
        } catch (Exception unused) {
            return false;
        }
    }

    public void updateBooleanValue(String str, boolean z) throws RemoteException {
        int callingPid = getCallingPid();
        if (!TextUtils.isEmpty(str)) {
            Log.d(TAG, "update boolean value key=" + str + ",value =" + z);
            if (CoreManager.a().b(callingPid)) {
                this.statusMap.put(str, Boolean.valueOf(z));
                Log.d(TAG, "update2 boolean value key=" + str + ",value =" + z);
            }
        }
    }

    public boolean isLoggedIn() throws RemoteException {
        int callingPid = getCallingPid();
        getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            return false;
        }
        return AccountManager.a().k();
    }

    public AccountType getAccountType() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return AccountManager.a().c();
        }
        return null;
    }

    public String getHomeId() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return AccountManager.a().d();
        }
        return "";
    }

    public boolean isMiLoggedIn() throws RemoteException {
        int callingPid = getCallingPid();
        getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            return false;
        }
        return AccountManager.a().l();
    }

    public String getMiId() {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return AccountManager.a().m();
        }
        return "0";
    }

    public IServerHandle setMiAccount(final LoginMiAccount loginMiAccount, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass3 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    AccountManager.a().i();
                    AccountManager.a().a(loginMiAccount);
                    AccountManager.a().j();
                    SmartHomeRc4Api.a().a(false);
                    SmartHomeOAuthHttpsApi.a().a(false);
                    RouterApi.a().a(false);
                    MiRechargeApi.a().a(false);
                    MiShopApi.a().a(false);
                    UserAgentUtil.a();
                    BluetoothCache.a();
                    DeviceManager.a().f();
                    CoreSharedHomeManager.a().b();
                    WhiteListManager.a().b();
                    if (iClientCallback != null) {
                        try {
                            iClientCallback.onSuccess(new Bundle());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle setMiServiceTokenTmp(final MiServiceTokenInfo miServiceTokenInfo, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass4 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    if (AccountUtil.a(miServiceTokenInfo)) {
                        AccountManager.a().a(miServiceTokenInfo.f23514a, miServiceTokenInfo.c, miServiceTokenInfo.d, miServiceTokenInfo.f, miServiceTokenInfo.e);
                        AccountManager.a().j();
                        if (miServiceTokenInfo.f23514a.equalsIgnoreCase("xiaomiio")) {
                            SmartHomeRc4Api.a().a(false);
                            SmartHomeOAuthHttpsApi.a().a(false);
                            UserAgentUtil.a();
                        }
                        if (iClientCallback != null) {
                            try {
                                iClientCallback.onSuccess(new Bundle());
                            } catch (RemoteException e) {
                                Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                            }
                        }
                    } else if (iClientCallback != null) {
                        try {
                            iClientCallback.onFailure(new Bundle());
                        } catch (RemoteException e2) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e2);
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle setOAuthAccount(final OAuthAccount oAuthAccount, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass5 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    AccountManager.a().i();
                    AccountManager.a().a(oAuthAccount);
                    AccountManager.a().j();
                    SmartHomeRc4Api.a().a(false);
                    SmartHomeOAuthHttpsApi.a().a(false);
                    RouterApi.a().a(false);
                    MiRechargeApi.a().a(false);
                    MiShopApi.a().a(false);
                    UserAgentUtil.a();
                    if (iClientCallback != null) {
                        try {
                            iClientCallback.onSuccess(new Bundle());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public LoginMiAccount getMiAccount() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return AccountManager.a().e();
        }
        return null;
    }

    public IServerHandle clearAccount(final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass6 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    if (AccountManager.a().n()) {
                        AccountManager.a().p();
                    }
                    AccountManager.a().i();
                    SmartHomeRc4Api.a().a(false);
                    SmartHomeOAuthHttpsApi.a().a(false);
                    RouterApi.a().a(false);
                    MiRechargeApi.a().a(false);
                    MiShopApi.a().a(false);
                    UserAgentUtil.a();
                    BluetoothCache.a();
                    if (iClientCallback != null) {
                        try {
                            iClientCallback.onSuccess(new Bundle());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public void clearAllMiServiceTokenInSystem() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            AccountManager.a().p();
        }
    }

    public void clearMiServiceTokenInSystem(String str) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            AccountManager.a().b(str);
        }
    }

    public IServerHandle sendSmartHomeRequest(final NetRequest netRequest, final Crypto crypto, final IClientCallback iClientCallback) throws RemoteException {
        ClientRecord b;
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if ((HostSetting.g || HostSetting.i) && ProcUtil.a(CoreService.getAppContext(), callingPid) && !ProcUtil.a(CoreService.getAppContext()) && (b = CoreManager.a().b("com.xiaomi.smarthome")) != null) {
            long currentTimeMillis = System.currentTimeMillis() - b.c();
            if (0 < currentTimeMillis && currentTimeMillis <= 200) {
                String str = "time:" + currentTimeMillis + "\nrequest:" + netRequest.b() + " " + netRequest.e();
                Intent intent = new Intent(CoreService.getAppContext(), NetRequestWarningActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra(NetRequestWarningActivity.KEY_ITEM, str);
                CoreService.getAppContext().startActivity(intent);
            }
        }
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass7 r0 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    if (crypto == Crypto.RC4) {
                        AccountType c = AccountManager.a().c();
                        if (c == AccountType.MI) {
                            this.mNetHandle = SmartHomeRc4Api.a().a(netRequest, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                                /* renamed from: a */
                                public void b(NetResult netResult) {
                                    if (iClientCallback != null) {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("result", netResult);
                                        try {
                                            iClientCallback.onSuccess(bundle);
                                        } catch (RemoteException e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }

                                /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
                                    return;
                                 */
                                /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0043 */
                                /* renamed from: b */
                                /* Code decompiled incorrectly, please refer to instructions dump. */
                                public void a(com.xiaomi.smarthome.core.entity.net.NetResult r5) {
                                    /*
                                        r4 = this;
                                        com.xiaomi.smarthome.core.server.CoreApiStub$7 r0 = com.xiaomi.smarthome.core.server.CoreApiStub.AnonymousClass7.this
                                        com.xiaomi.smarthome.core.client.IClientCallback r0 = r10
                                        if (r0 == 0) goto L_0x0074
                                        android.os.Bundle r0 = new android.os.Bundle     // Catch:{ TransactionTooLargeException -> 0x0043, Exception -> 0x0018 }
                                        r0.<init>()     // Catch:{ TransactionTooLargeException -> 0x0043, Exception -> 0x0018 }
                                        java.lang.String r1 = "result"
                                        r0.putParcelable(r1, r5)     // Catch:{ TransactionTooLargeException -> 0x0043, Exception -> 0x0018 }
                                        com.xiaomi.smarthome.core.server.CoreApiStub$7 r1 = com.xiaomi.smarthome.core.server.CoreApiStub.AnonymousClass7.this     // Catch:{ TransactionTooLargeException -> 0x0043, Exception -> 0x0018 }
                                        com.xiaomi.smarthome.core.client.IClientCallback r1 = r10     // Catch:{ TransactionTooLargeException -> 0x0043, Exception -> 0x0018 }
                                        r1.onSuccess(r0)     // Catch:{ TransactionTooLargeException -> 0x0043, Exception -> 0x0018 }
                                        goto L_0x0074
                                    L_0x0018:
                                        r5 = move-exception
                                        java.lang.String r0 = "CoreApiStub"
                                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                                        r1.<init>()
                                        java.lang.String r2 = "fatal"
                                        r1.append(r2)
                                        com.xiaomi.smarthome.core.server.CoreApiStub$7 r2 = com.xiaomi.smarthome.core.server.CoreApiStub.AnonymousClass7.this
                                        com.xiaomi.smarthome.core.entity.net.NetRequest r2 = r8
                                        java.lang.String r2 = r2.b()
                                        r1.append(r2)
                                        com.xiaomi.smarthome.core.server.CoreApiStub$7 r2 = com.xiaomi.smarthome.core.server.CoreApiStub.AnonymousClass7.this
                                        com.xiaomi.smarthome.core.entity.net.NetRequest r2 = r8
                                        java.util.List r2 = r2.e()
                                        r1.append(r2)
                                        java.lang.String r1 = r1.toString()
                                        android.util.Log.e(r0, r1, r5)
                                        goto L_0x0074
                                    L_0x0043:
                                        android.os.Bundle r0 = new android.os.Bundle     // Catch:{ Exception -> 0x006c }
                                        r0.<init>()     // Catch:{ Exception -> 0x006c }
                                        com.xiaomi.smarthome.core.server.MiHomeMemoryFile r1 = new com.xiaomi.smarthome.core.server.MiHomeMemoryFile     // Catch:{ Exception -> 0x006c }
                                        r1.<init>()     // Catch:{ Exception -> 0x006c }
                                        android.os.Parcel r2 = android.os.Parcel.obtain()     // Catch:{ Exception -> 0x006c }
                                        r3 = 0
                                        r2.writeParcelable(r5, r3)     // Catch:{ Exception -> 0x006c }
                                        byte[] r5 = r2.marshall()     // Catch:{ Exception -> 0x006c }
                                        r1.a(r5)     // Catch:{ Exception -> 0x006c }
                                        r2.recycle()     // Catch:{ Exception -> 0x006c }
                                        java.lang.String r5 = "result_file"
                                        r0.putParcelable(r5, r1)     // Catch:{ Exception -> 0x006c }
                                        com.xiaomi.smarthome.core.server.CoreApiStub$7 r5 = com.xiaomi.smarthome.core.server.CoreApiStub.AnonymousClass7.this     // Catch:{ Exception -> 0x006c }
                                        com.xiaomi.smarthome.core.client.IClientCallback r5 = r10     // Catch:{ Exception -> 0x006c }
                                        r5.onSuccess(r0)     // Catch:{ Exception -> 0x006c }
                                        goto L_0x0074
                                    L_0x006c:
                                        r5 = move-exception
                                        java.lang.String r0 = "CoreApiStub"
                                        java.lang.String r1 = "fatal"
                                        android.util.Log.e(r0, r1, r5)
                                    L_0x0074:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.CoreApiStub.AnonymousClass7.AnonymousClass1.a(com.xiaomi.smarthome.core.entity.net.NetResult):void");
                                }

                                public void a(NetError netError) {
                                    if (iClientCallback != null) {
                                        try {
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable("error", netError);
                                            iClientCallback.onFailure(bundle);
                                        } catch (RemoteException e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }
                            });
                        } else if (c == AccountType.WX) {
                            this.mNetHandle = SmartHomeOAuthHttpsApi.a().a(netRequest, new NetCallback<NetResult, NetError>() {
                                /* renamed from: a */
                                public void b(NetResult netResult) {
                                }

                                /* renamed from: b */
                                public void a(NetResult netResult) {
                                    if (iClientCallback != null) {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("result", netResult);
                                        try {
                                            iClientCallback.onSuccess(bundle);
                                        } catch (RemoteException e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }

                                public void a(NetError netError) {
                                    if (iClientCallback != null) {
                                        try {
                                            Bundle bundle = new Bundle();
                                            bundle.putParcelable("error", netError);
                                            iClientCallback.onFailure(bundle);
                                        } catch (RemoteException e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }
                            });
                        } else if (iClientCallback != null) {
                            try {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("error", new NetError(-9999, "unknown account type"));
                                iClientCallback.onFailure(bundle);
                            } catch (RemoteException e) {
                                Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                            }
                        }
                    } else if (crypto == Crypto.NONE) {
                        this.mNetHandle = SmartHomeOpenApi.a().a(netRequest, new NetCallback<NetResult, NetError>() {
                            /* renamed from: a */
                            public void b(NetResult netResult) {
                            }

                            /* renamed from: b */
                            public void a(NetResult netResult) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("result", netResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(NetError netError) {
                                if (iClientCallback != null) {
                                    try {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", netError);
                                        iClientCallback.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    } else if (crypto == Crypto.AES) {
                        this.mNetHandle = SmartHomeAesApi.a().a(netRequest, new NetCallback<NetResult, NetError>() {
                            /* renamed from: a */
                            public void b(NetResult netResult) {
                            }

                            /* renamed from: b */
                            public void a(NetResult netResult) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("result", netResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(NetError netError) {
                                if (iClientCallback != null) {
                                    try {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", netError);
                                        iClientCallback.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    } else if (crypto == Crypto.HTTPS) {
                        this.mNetHandle = SmartHomeHttpsApi.a().a(netRequest, new NetCallback<NetResult, NetError>() {
                            /* renamed from: a */
                            public void b(NetResult netResult) {
                            }

                            /* renamed from: b */
                            public void a(NetResult netResult) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("result", netResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(NetError netError) {
                                if (iClientCallback != null) {
                                    try {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", netError);
                                        iClientCallback.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    } else if (iClientCallback != null) {
                        try {
                            Bundle bundle2 = new Bundle();
                            bundle2.putParcelable("error", new NetError(-9999, "crypto not support"));
                            iClientCallback.onFailure(bundle2);
                        } catch (RemoteException e2) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e2);
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle sendRouterRequest(NetRequest netRequest, String str, boolean z, IClientCallback iClientCallback) throws RemoteException {
        if (ServerCompact.g(CoreService.getAppContext())) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-3, "core service : not support router request in Europe"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        }
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle3);
                } catch (RemoteException e3) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e3);
                }
            }
            return null;
        } else {
            final NetRequest netRequest2 = netRequest;
            final String str2 = str;
            final boolean z2 = z;
            final IClientCallback iClientCallback2 = iClientCallback;
            AnonymousClass8 r3 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    this.mNetHandle = RouterApi.a().a(netRequest2, str2, z2, new NetCallback<NetResult, NetError>() {
                        /* renamed from: a */
                        public void b(NetResult netResult) {
                        }

                        /* renamed from: b */
                        public void a(NetResult netResult) {
                            if (iClientCallback2 != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("result", netResult);
                                try {
                                    iClientCallback2.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a(NetError netError) {
                            if (iClientCallback2 != null) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", netError);
                                    iClientCallback2.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            };
            r3.execute();
            return r3;
        }
    }

    public IServerHandle sendMiRechargeRequest(final NetRequest netRequest, final IClientCallback iClientCallback) throws RemoteException {
        if (ServerCompact.g(CoreService.getAppContext())) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-3, "core service : not support mi recharge request in Europe"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        }
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle3);
                } catch (RemoteException e3) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e3);
                }
            }
            return null;
        } else {
            AnonymousClass9 r0 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    this.mNetHandle = MiRechargeApi.a().a(netRequest, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                        /* renamed from: a */
                        public void b(NetResult netResult) {
                        }

                        /* renamed from: b */
                        public void a(NetResult netResult) {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("result", netResult);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a(NetError netError) {
                            if (iClientCallback != null) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", netError);
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle sendMiShopRequest(final NetRequest netRequest, final IClientCallback iClientCallback) throws RemoteException {
        if (ServerCompact.g(CoreService.getAppContext())) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-3, "core service : not support mishop request in Europe"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        }
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle3);
                } catch (RemoteException e3) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e3);
                }
            }
            return null;
        } else {
            AnonymousClass10 r0 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    this.mNetHandle = MiShopApi.a().a(netRequest, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                        /* renamed from: a */
                        public void b(NetResult netResult) {
                        }

                        /* renamed from: b */
                        public void a(NetResult netResult) {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("result", netResult);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a(NetError netError) {
                            if (iClientCallback != null) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", netError);
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle scanDeviceList(final ScanType scanType, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass11 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    DeviceManager.a().a(scanType, iClientCallback);
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle getDeviceList(final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                    Log.e(AppMeasurement.Param.FATAL, "getDeviceList", e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                    Log.e(AppMeasurement.Param.FATAL, "getDeviceList", e2);
                }
            }
            return null;
        } else {
            AnonymousClass12 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    ArrayList<Device> c = DeviceManager.a().c();
                    if (iClientCallback != null) {
                        Bundle bundle = new Bundle();
                        if (c.size() > 200) {
                            MiHomeMemoryFile miHomeMemoryFile = new MiHomeMemoryFile();
                            Parcel obtain = Parcel.obtain();
                            obtain.writeList(c);
                            miHomeMemoryFile.a(obtain.marshall());
                            obtain.recycle();
                            bundle.putParcelable(Const.f14057a, miHomeMemoryFile);
                        } else {
                            bundle.putParcelableArrayList("result", c);
                        }
                        try {
                            iClientCallback.onSuccess(bundle);
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle batchRpcAsync(final List<BatchRpcParam> list, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass13 r0 = new CoreAsyncTask() {
                DeviceHandle mDeviceHandle;

                public void onCancel() {
                    if (this.mDeviceHandle != null) {
                        this.mDeviceHandle.a();
                    }
                }

                public void run() {
                    this.mDeviceHandle = new BatchRpcApi().a((List<BatchRpcParam>) list, (AsyncResponseCallback<String>) new AsyncResponseCallback<String>() {
                        public void a(String str) {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("result", str);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a(int i) {
                            if (iClientCallback != null) {
                                try {
                                    Error error = new Error(i, "unknown error");
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", error);
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a(int i, Object obj) {
                            if (iClientCallback != null) {
                                try {
                                    Error error = new Error(i, "unknown error");
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", error);
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            };
            r0.execute();
            return r0;
        }
    }

    /* access modifiers changed from: private */
    public boolean isUpgradingFirmware(String str) {
        if (MeshDfuManager.a().b()) {
            return true;
        }
        Map<String, Boolean> map = this.statusMap;
        Boolean bool = map.get(str + "#" + BooleanKey.c);
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Bundle buildRpcErrorBundle(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("error", new NetError(i, str));
        return bundle;
    }

    public IServerHandle rpcAsync(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException {
        try {
            if (!RpcErrorDescription.a(str, str2, str3)) {
                if (iClientCallback != null) {
                    try {
                        iClientCallback.onFailure(buildRpcErrorBundle(-97, "core service : two same rpc requsts can not be send in 1 second!"));
                    } catch (RemoteException e) {
                        Log.e(TAG, AppMeasurement.Param.FATAL, e);
                    }
                }
                return null;
            }
            int callingPid = getCallingPid();
            int callingUid = getCallingUid();
            if (!CoreManager.a().b(callingPid)) {
                if (iClientCallback != null) {
                    try {
                        iClientCallback.onFailure(buildRpcErrorBundle(-2, "core service : process not register"));
                    } catch (RemoteException e2) {
                        Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                    }
                }
                return null;
            } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
                if (iClientCallback != null) {
                    try {
                        iClientCallback.onFailure(buildRpcErrorBundle(-3, "core service : permission deny"));
                    } catch (RemoteException e3) {
                        Log.e(TAG, AppMeasurement.Param.FATAL, e3);
                    }
                }
                return null;
            } else {
                final String str4 = str;
                final IClientCallback iClientCallback2 = iClientCallback;
                final String str5 = str3;
                final String str6 = str2;
                AnonymousClass14 r1 = new CoreAsyncTask() {
                    DeviceHandle mDeviceHandle;

                    public void onCancel() {
                        if (this.mDeviceHandle != null) {
                            this.mDeviceHandle.a();
                        }
                    }

                    public void run() {
                        Device a2 = DeviceManager.a().a(str4);
                        if (a2 != null) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                            final Device device = a2;
                            final AtomicBoolean atomicBoolean2 = atomicBoolean;
                            AnonymousClass1 r2 = new AsyncResponseCallback<String>() {
                                public void a(String str) {
                                    if (iClientCallback2 != null) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("result", str);
                                        try {
                                            int c2 = RpcErrorDescription.c(str);
                                            ErrorCode valueof = ErrorCode.valueof(c2);
                                            String str2 = "";
                                            if (valueof != ErrorCode.SUCCESS) {
                                                str2 = str5;
                                            }
                                            String str3 = str2;
                                            iClientCallback2.onSuccess(bundle);
                                            if (valueof != ErrorCode.SUCCESS) {
                                                if (device != null && (!device.o() || RpcErrorDescription.f(device.k()))) {
                                                    MyLogger.a().a("RPC: onSuccess,device is offline ,but has error,not report");
                                                    return;
                                                } else if (CoreApiStub.this.isUpgradingFirmware(str4)) {
                                                    MyLogger.a().a("RPC: onSuccess, is upgrading firmware,not report");
                                                    return;
                                                } else if (c2 == -3 && device != null) {
                                                    RpcErrorDescription.d(device.k());
                                                }
                                            }
                                            if (!(c2 == -3 || device == null)) {
                                                RpcErrorDescription.e(device.k());
                                            }
                                            MyLogger.a().a("RPC: error code is success");
                                            if (atomicBoolean2.get()) {
                                                STAT.i.a(System.currentTimeMillis() - currentTimeMillis, device.l(), c2, str4, str, str3);
                                            } else {
                                                STAT.i.b(System.currentTimeMillis() - currentTimeMillis, device.l(), c2, str4, str, str3);
                                            }
                                        } catch (Exception e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }

                                public void a(int i) {
                                    LogUtil.b("RPC", "error: " + i + "   errorInfo: unknown error");
                                    if (iClientCallback2 != null) {
                                        try {
                                            iClientCallback2.onFailure(CoreApiStub.this.buildRpcErrorBundle(i, "unknown error"));
                                            if (device != null && !device.o()) {
                                                MyLogger.a().a("RPC: on failure ,device is offline but has error,not report");
                                            } else if (CoreApiStub.this.isUpgradingFirmware(str4)) {
                                                MyLogger.a().a("RPC: onFailure, is upgrading firmware,not report");
                                            } else if (atomicBoolean2.get()) {
                                                STAT.i.a(System.currentTimeMillis() - currentTimeMillis, device.l(), i, str4, (String) null, str5);
                                            } else {
                                                STAT.i.b(System.currentTimeMillis() - currentTimeMillis, device.l(), i, str4, (String) null, str5);
                                            }
                                        } catch (Exception e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }

                                public void a(int i, Object obj) {
                                    String obj2 = obj == null ? "unknown error" : obj.toString();
                                    LogUtil.b("RPC", "error: " + i + "   errorInfo: " + obj2);
                                    if (iClientCallback2 != null) {
                                        try {
                                            iClientCallback2.onFailure(CoreApiStub.this.buildRpcErrorBundle(i, obj2));
                                            Device a2 = DeviceManager.a().a(str4);
                                            if (a2 != null && !a2.o()) {
                                                return;
                                            }
                                            if (CoreApiStub.this.isUpgradingFirmware(str4)) {
                                                MyLogger.a().a("RPC: onFailure2, is upgrading firmware,not report");
                                                return;
                                            }
                                            int b2 = RpcErrorDescription.b(obj2);
                                            LogUtil.b("RPC", "rpc specific errorcode = " + b2);
                                            if (atomicBoolean2.get()) {
                                                STAT.i.a(System.currentTimeMillis() - currentTimeMillis, device.l(), b2, str4, obj2, str5);
                                            } else {
                                                STAT.i.b(System.currentTimeMillis() - currentTimeMillis, device.l(), b2, str4, obj2, str5);
                                            }
                                        } catch (RemoteException e) {
                                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        }
                                    }
                                }
                            };
                            if (str4.startsWith("mitv.")) {
                                this.mDeviceHandle = DeviceApiInternal.a().b(URLEncoder.encode(str4), str6, str5, r2);
                            } else if (WifiUtil.a(CoreService.getAppContext()) == null || !a2.Y()) {
                                LogUtil.c("RPC", "rpc is from cloud...");
                                this.mDeviceHandle = DeviceApiInternal.a().a(str4, str6, str5, r2);
                            } else {
                                atomicBoolean.set(false);
                                LogUtil.c("RPC", "rpc is from native...");
                                LocalDeviceApiInternal.a().b(a2.t(), str4, str6, str5, r2);
                            }
                        } else if (iClientCallback2 != null) {
                            try {
                                iClientCallback2.onFailure(CoreApiStub.this.buildRpcErrorBundle(-9999, "Device does not exist!"));
                            } catch (Exception e) {
                                Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                            }
                        }
                    }
                };
                r1.execute();
                return r1;
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public IServerHandle rpcAsyncToLocal(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            final String str4 = str;
            final IClientCallback iClientCallback2 = iClientCallback;
            final String str5 = str3;
            final String str6 = str2;
            AnonymousClass15 r4 = new CoreAsyncTask() {
                DeviceHandle mDeviceHandle;

                public void onCancel() {
                    if (this.mDeviceHandle != null) {
                        this.mDeviceHandle.a();
                    }
                }

                public void run() {
                    final Device a2 = DeviceManager.a().a(str4);
                    if (a2 != null) {
                        final long currentTimeMillis = System.currentTimeMillis();
                        AnonymousClass1 r9 = new AsyncResponseCallback<String>() {
                            public void a(String str) {
                                String str2;
                                if (iClientCallback2 != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("result", str);
                                    try {
                                        iClientCallback2.onSuccess(bundle);
                                        int c2 = RpcErrorDescription.c(str);
                                        if (ErrorCode.valueof(c2) != ErrorCode.SUCCESS) {
                                            str2 = str5;
                                        } else {
                                            str2 = "";
                                        }
                                        STAT.i.b(System.currentTimeMillis() - currentTimeMillis, a2.l(), c2, str4, str, str2);
                                    } catch (Exception e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(int i) {
                                LogUtil.b("RPC", "error: " + i + "   errorInfo: unknown error");
                                if (iClientCallback2 != null) {
                                    try {
                                        Error error = new Error(i, "unknown error");
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", error);
                                        iClientCallback2.onFailure(bundle);
                                        STAT.i.b(System.currentTimeMillis() - currentTimeMillis, a2.l(), i, str4, (String) null, str5);
                                    } catch (Exception e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(int i, Object obj) {
                                String obj2 = obj == null ? "unknown error" : obj.toString();
                                LogUtil.b("RPC", "error: " + i + "   errorInfo: " + obj2);
                                if (iClientCallback2 != null) {
                                    try {
                                        Error error = new Error(i, obj2);
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", error);
                                        iClientCallback2.onFailure(bundle);
                                        int b2 = RpcErrorDescription.b(obj2);
                                        LogUtil.b("RPC", "rpc specific errorcode = " + b2);
                                        STAT.i.b(System.currentTimeMillis() - currentTimeMillis, a2.l(), b2, str4, obj2, str5);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        };
                        if (str4.startsWith("mitv.")) {
                            this.mDeviceHandle = DeviceApiInternal.a().b(URLEncoder.encode(str4), str6, str5, r9);
                        } else if (WifiUtil.a(CoreService.getAppContext()) == null || !a2.Y()) {
                            r9.a(-9999, "current phone's wifi cannot use or current device is not local device,  isLocal: " + a2.Y());
                        } else {
                            LogUtil.c("RPC", "rpc is from native...");
                            LocalDeviceApiInternal.a().b(a2.t(), str4, str6, str5, r9);
                        }
                    } else if (iClientCallback2 != null) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("error", new NetError(-9999, "Device does not exist!"));
                        try {
                            iClientCallback2.onFailure(bundle);
                        } catch (Exception e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            };
            r4.execute();
            return r4;
        }
    }

    public IServerHandle rpcAsyncToCloud(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException {
        if (!RpcErrorDescription.a(str, str2, str3)) {
            if (iClientCallback != null) {
                try {
                    iClientCallback.onFailure(buildRpcErrorBundle(-97, "core service : two same rpc requsts can not be send in 1 second!"));
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        }
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e3) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e3);
                }
            }
            return null;
        } else {
            final String str4 = str;
            final IClientCallback iClientCallback2 = iClientCallback;
            final String str5 = str2;
            final String str6 = str3;
            AnonymousClass16 r2 = new CoreAsyncTask() {
                DeviceHandle mDeviceHandle;

                public void onCancel() {
                    if (this.mDeviceHandle != null) {
                        this.mDeviceHandle.a();
                    }
                }

                public void run() {
                    if (DeviceManager.a().a(str4) != null) {
                        AnonymousClass1 r0 = new AsyncResponseCallback<String>() {
                            public void a(String str) {
                                if (iClientCallback2 != null) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("result", str);
                                    try {
                                        iClientCallback2.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(int i) {
                                if (iClientCallback2 != null) {
                                    try {
                                        Error error = new Error(i, "unknown error");
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", error);
                                        iClientCallback2.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(int i, Object obj) {
                                if (iClientCallback2 != null) {
                                    try {
                                        Error error = new Error(i, "unknown error");
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", error);
                                        iClientCallback2.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        };
                        if (str4.startsWith("mitv.")) {
                            this.mDeviceHandle = DeviceApiInternal.a().b(URLEncoder.encode(str4), str5, str6, r0);
                            return;
                        }
                        this.mDeviceHandle = DeviceApiInternal.a().a(str4, str5, str6, r0);
                    } else if (iClientCallback2 != null) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("error", new NetError(-9999, "Device does not exist!"));
                        try {
                            iClientCallback2.onFailure(bundle);
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            };
            r2.execute();
            return r2;
        }
    }

    public void localPing(final String str, final IClientCallback iClientCallback) {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().b(callingPid, callingUid)) {
            new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    final Device a2 = DeviceManager.a().a(str);
                    if (a2 != null) {
                        MiioLocalAPI.a(a2.t(), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                            public void onResponse(String str) {
                                try {
                                    int optInt = new JSONObject(str).optInt("code");
                                    if (optInt == MiioLocalErrorCode.SUCCESS.getCode() || optInt == MiioLocalErrorCode.PERMISSION_DENIED.getCode()) {
                                        a2.a(Location.LOCAL);
                                        if (iClientCallback != null) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("result", "ping local sucess");
                                            try {
                                                iClientCallback.onSuccess(bundle);
                                                return;
                                            } catch (RemoteException e) {
                                                Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                if (iClientCallback != null) {
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putParcelable("error", new NetError(-9999, "ping time out"));
                                    try {
                                        iClientCallback.onFailure(bundle2);
                                    } catch (RemoteException e3) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e3);
                                    }
                                }
                            }
                        }, 1);
                    } else if (iClientCallback != null) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("error", new NetError(-9999, "Device does not exist!"));
                        try {
                            iClientCallback.onFailure(bundle);
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                }
            }.execute();
        } else if (iClientCallback != null) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                iClientCallback.onFailure(bundle2);
            } catch (RemoteException e2) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e2);
            }
        }
    }

    public IServerHandle renameDevice(final String str, final String str2, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass18 r0 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    this.mNetHandle = DeviceManager.a().a(str, str2, iClientCallback);
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle delDeviceBatch(final List<String> list, final IClientCallback iClientCallback) {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass19 r0 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    this.mNetHandle = DeviceManager.a().a((List<String>) list, iClientCallback);
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle updateDeviceBatch(final List<String> list, final IClientCallback iClientCallback) {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().b(callingPid, callingUid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new NetError(-3, "core service : permission deny"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass20 r0 = new CoreAsyncTask() {
                NetHandle mNetHandle;

                public void onCancel() {
                    if (this.mNetHandle != null) {
                        this.mNetHandle.a();
                    }
                }

                public void run() {
                    this.mNetHandle = DeviceManager.a().b((List<String>) list, iClientCallback);
                }
            };
            r0.execute();
            return r0;
        }
    }

    public boolean isServicePromoteSuccess() {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return this.mIsPromoteSuccess;
        }
        return false;
    }

    public void setPromoteStatus(boolean z) {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            this.mIsPromoteSuccess = z;
        }
    }

    public CTAInfo getGlobalSettingCTA() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid) || !WhiteListManager.a().b(callingPid, callingUid)) {
            return null;
        }
        boolean b = GlobalDynamicSettingManager.a().b();
        boolean c = GlobalDynamicSettingManager.a().c();
        CTAInfo cTAInfo = new CTAInfo();
        cTAInfo.a(b);
        cTAInfo.b(c);
        return cTAInfo;
    }

    public IServerHandle setGlobalSettingCTA(final boolean z, final boolean z2, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new Error(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass21 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    boolean b = GlobalDynamicSettingManager.a().b();
                    boolean c = GlobalDynamicSettingManager.a().c();
                    boolean b2 = CTAManager.a().b();
                    GlobalDynamicSettingManager.a().a(z, z2);
                    if (z2) {
                        CTAManager.a().a(true);
                    }
                    if (iClientCallback != null) {
                        try {
                            iClientCallback.onSuccess(new Bundle());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                    if (!c || (b && !b2)) {
                        LocalBroadcastManager.getInstance(CoreService.getAppContext()).sendBroadcast(new Intent(CTAManager.f14494a));
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public ServerBean getGlobalSettingServer() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return GlobalDynamicSettingManager.a().d();
        }
        return null;
    }

    public IServerHandle setGlobalSettingServer(final ServerBean serverBean, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new Error(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass22 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    ServerBean d = GlobalDynamicSettingManager.a().d();
                    ServerBean serverBean = serverBean;
                    GlobalDynamicSettingManager.a().a(serverBean);
                    if (iClientCallback != null) {
                        try {
                            iClientCallback.onSuccess(new Bundle());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                    if (serverBean != null || d != null) {
                        if (d == null || serverBean == null || !d.b.equals(serverBean.b)) {
                            PluginManager.a().a(d, serverBean);
                            DeviceManager.a().e();
                            SmartHomeRc4Api.a().a(false);
                            SmartHomeOAuthHttpsApi.a().a(false);
                            SmartHomeAesApi.a().a(false);
                            SmartHomeHttpsApi.a().a(false);
                            SmartHomeOpenApi.a().a(false);
                            RouterApi.a().a(false);
                            MiRechargeApi.a().a(false);
                            MiShopApi.a().a(false);
                            UserAgentUtil.a();
                            for (IClientApi onServerChanged : CoreManager.a().d()) {
                                try {
                                    onServerChanged.onServerChanged(d, serverBean);
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public String getGlobalSettingServerEnv() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            return GlobalDynamicSettingManager.a().f();
        }
        return "";
    }

    public IServerHandle setGlobalSettingServerEnv(final String str, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new Error(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass23 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    String f = GlobalDynamicSettingManager.a().f();
                    String str = str;
                    GlobalDynamicSettingManager.a().a(str);
                    SmartHomeRc4Api.a().a(false);
                    SmartHomeHttpsApi.a().a(false);
                    SmartHomeAesApi.a().a(false);
                    SmartHomeOpenApi.a().a(false);
                    if (iClientCallback != null) {
                        try {
                            iClientCallback.onSuccess(new Bundle());
                        } catch (RemoteException e) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                        }
                    }
                    if (TextUtils.isEmpty(f) && !TextUtils.isEmpty(str)) {
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public Bundle getGlobalSettingLocale() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid) || !WhiteListManager.a().b(callingPid, callingUid)) {
            return null;
        }
        Locale g = GlobalDynamicSettingManager.a().g();
        Bundle bundle = new Bundle();
        if (g != null) {
            bundle.putSerializable("result", g);
        }
        return bundle;
    }

    public IServerHandle setGlobalSettingLocale(final Bundle bundle, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new Error(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                    LogUtil.a("LanguageUtil", "setGlobalSettingLocale Exception");
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable("error", new Error(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle3);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                    LogUtil.a("LanguageUtil", "setGlobalSettingLocale Exception");
                }
            }
            return null;
        } else {
            AnonymousClass24 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    if (bundle != null) {
                        Locale g = GlobalDynamicSettingManager.a().g();
                        Locale locale = (Locale) bundle.getSerializable("result");
                        if (locale == null) {
                            GlobalDynamicSettingManager.a().h();
                        } else {
                            GlobalDynamicSettingManager.a().a(locale);
                        }
                        SmartHomeRc4Api.a().a(false);
                        SmartHomeOAuthHttpsApi.a().a(false);
                        SmartHomeAesApi.a().a(false);
                        SmartHomeHttpsApi.a().a(false);
                        SmartHomeOpenApi.a().a(false);
                        RouterApi.a().a(false);
                        MiRechargeApi.a().a(false);
                        MiShopApi.a().a(false);
                        UserAgentUtil.a();
                        PluginManager.a().a((PluginManager.ClearConfigCallback) null);
                        if (iClientCallback != null) {
                            try {
                                iClientCallback.onSuccess(new Bundle());
                            } catch (RemoteException e) {
                                Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                LogUtil.a("LanguageUtil", "2690 Exception");
                            }
                        }
                        if (!LocaleUtil.a(g, locale)) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("result", g);
                            Bundle bundle2 = new Bundle();
                            bundle2.putSerializable("result", locale);
                            for (IClientApi onLocaleChanged : CoreManager.a().d()) {
                                try {
                                    onLocaleChanged.onLocaleChanged(bundle, bundle2);
                                } catch (Exception e2) {
                                    LogUtil.a("LanguageUtil", e2.toString());
                                }
                            }
                        }
                    } else if (iClientCallback != null) {
                        try {
                            Bundle bundle3 = new Bundle();
                            bundle3.putParcelable("error", new Error(-999, "core service : bundle is null"));
                            iClientCallback.onFailure(bundle3);
                        } catch (RemoteException e3) {
                            Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e3);
                            LogUtil.a("LanguageUtil", "2722setGlobalSettingLocale Exception");
                        }
                    }
                }
            };
            r0.execute();
            return r0;
        }
    }

    public void updateWhiteList(final boolean z) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    if (WhiteListManager.a().c()) {
                        WhiteListManager.a().a(z);
                    }
                }
            }.execute();
        }
    }

    public void clearWhiteList() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    if (WhiteListManager.a().c()) {
                        WhiteListManager.a().e();
                    }
                }
            }.execute();
        }
    }

    public void setScanTimePeriod(int i) {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            WifiScanServices.a(i);
        }
    }

    public void startScanWithCallback(IClientCallback iClientCallback) {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            WifiScanServices.a(iClientCallback);
        }
    }

    public void startScan() {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            WifiScanServices.c();
        }
    }

    public void stopScan() {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().b(callingPid, callingUid)) {
            WifiScanServices.b();
        }
    }

    public void updatePluginConfig(final boolean z, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    PluginManager.a().a(z, (PluginManager.UpdateConfigCallback) new PluginManager.UpdateConfigCallback() {
                        public void a(boolean z, boolean z2) {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                UpdatePluginConfigResult updatePluginConfigResult = new UpdatePluginConfigResult();
                                updatePluginConfigResult.f13994a = z;
                                updatePluginConfigResult.b = z2;
                                bundle.putParcelable("result", updatePluginConfigResult);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a(CoreError coreError) {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("error", new PluginError(coreError.a(), coreError.b()));
                                try {
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            }.execute();
        } else if (iClientCallback != null) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new PluginError(-1, "core service permission deny"));
                iClientCallback.onFailure(bundle2);
            } catch (RemoteException e2) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e2);
            }
        }
    }

    public void clearPluginConfig(final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    PluginManager.a().a((PluginManager.ClearConfigCallback) new PluginManager.ClearConfigCallback() {
                        public void a() {
                            Bundle bundle = new Bundle();
                            if (iClientCallback != null) {
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void b() {
                            if (iClientCallback != null) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", new PluginError(-1, "plugin manager return failure"));
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            }.execute();
        } else if (iClientCallback != null) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new PluginError(-1, "core service permission deny"));
                iClientCallback.onFailure(bundle2);
            } catch (RemoteException e2) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e2);
            }
        }
    }

    public boolean isPluginDevice(String str) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().b(str);
        }
        return false;
    }

    public PluginRecord getPluginRecord(String str) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().c(str);
        }
        return null;
    }

    public List<PluginRecord> getPluginRecordList() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().j();
        }
        return null;
    }

    public String getModelByProductId(int i) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().a(i);
        }
        return "";
    }

    public int getProductIdByModel(String str) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().d(str);
        }
        return 0;
    }

    public String getModelBySSID(String str) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().e(str);
        }
        return "";
    }

    public boolean isPluginForceUpdating(String str) throws RemoteException {
        PluginRecord c;
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid) && (c = PluginManager.a().c(str)) != null) {
            return PluginManager.a().a(c);
        }
        return false;
    }

    public void downloadPlugin(String str, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new NetError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            final PluginRecord c = PluginManager.a().c(str);
            if (c == null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new PluginError(-1, "PluginRecord is null"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            } else {
                new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        PluginManager.a().a(c, (PluginManager.PluginDownloadCallback) new PluginManager.PluginDownloadCallback() {
                            public void a(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 1;
                                    downloadPluginResult.i = pluginRecord;
                                    downloadPluginResult.j = pluginDownloadTask;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 7;
                                    downloadPluginResult.i = pluginRecord;
                                    downloadPluginResult.j = pluginDownloadTask;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void c(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 2;
                                    downloadPluginResult.i = pluginRecord;
                                    downloadPluginResult.j = pluginDownloadTask;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(PluginRecord pluginRecord, float f) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 3;
                                    downloadPluginResult.i = pluginRecord;
                                    downloadPluginResult.k = f;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 4;
                                    downloadPluginResult.i = pluginRecord;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 5;
                                    downloadPluginResult.i = pluginRecord;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void c(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginResult downloadPluginResult = new DownloadPluginResult();
                                    downloadPluginResult.h = 6;
                                    downloadPluginResult.i = pluginRecord;
                                    bundle.putParcelable("result", downloadPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    }
                }.execute();
            }
        } else if (iClientCallback != null) {
            try {
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable("error", new PluginError(-1, "core service permission deny"));
                iClientCallback.onFailure(bundle3);
            } catch (RemoteException e3) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e3);
            }
        }
    }

    public void deletePlugin(String str, final IClientCallback iClientCallback) throws RemoteException {
        if (CoreManager.a().b(getCallingPid())) {
            final PluginRecord c = PluginManager.a().c(str);
            if (c == null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-1, "PluginRecord is null"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            } else {
                new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        PluginManager.a().a(c, (PluginManager.PluginDeleteCallback) new PluginManager.PluginDeleteCallback() {
                            public void a(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DeletePluginResult deletePluginResult = new DeletePluginResult();
                                    deletePluginResult.d = 1;
                                    deletePluginResult.e = pluginRecord;
                                    bundle.putParcelable("result", deletePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        e.printStackTrace();
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DeletePluginResult deletePluginResult = new DeletePluginResult();
                                    deletePluginResult.d = 2;
                                    deletePluginResult.e = pluginRecord;
                                    bundle.putParcelable("result", deletePluginResult);
                                    try {
                                        iClientCallback.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        e.printStackTrace();
                                    }
                                }
                            }

                            public void c(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DeletePluginResult deletePluginResult = new DeletePluginResult();
                                    deletePluginResult.d = 3;
                                    deletePluginResult.e = pluginRecord;
                                    bundle.putParcelable("result", deletePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }.execute();
            }
        } else if (iClientCallback != null) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new NetError(-2, "core service : process not register"));
                iClientCallback.onFailure(bundle2);
            } catch (RemoteException e2) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e2);
            }
        }
    }

    public void installPlugin(String str, final boolean z, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            final PluginRecord c = PluginManager.a().c(str);
            if (c == null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new PluginError(-1, "PluginRecord is null"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            } else {
                new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        PluginManager.a().a(c, z, (PluginManager.PluginInstallCallback) new PluginManager.PluginInstallCallback() {
                            public void a(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    InstallPluginResult installPluginResult = new InstallPluginResult();
                                    installPluginResult.e = 1;
                                    installPluginResult.f = pluginRecord;
                                    bundle.putParcelable("result", installPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    InstallPluginResult installPluginResult = new InstallPluginResult();
                                    installPluginResult.e = 2;
                                    installPluginResult.f = pluginRecord;
                                    bundle.putParcelable("result", installPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                        Log.e(AppMeasurement.Param.FATAL, "onSuccess", e);
                                    }
                                }
                            }

                            public void c(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    InstallPluginResult installPluginResult = new InstallPluginResult();
                                    installPluginResult.e = 3;
                                    installPluginResult.f = pluginRecord;
                                    bundle.putParcelable("result", installPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void d(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    InstallPluginResult installPluginResult = new InstallPluginResult();
                                    installPluginResult.e = 4;
                                    installPluginResult.f = pluginRecord;
                                    bundle.putParcelable("result", installPluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    }
                }.execute();
            }
        } else if (iClientCallback != null) {
            try {
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable("error", new PluginError(-1, "core service permission deny"));
                iClientCallback.onFailure(bundle3);
            } catch (RemoteException e3) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e3);
            }
        }
    }

    public void updateAllPlugin(final boolean z, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    PluginManager.a().a(z, (PluginManager.PluginUpdateAllCallback) new PluginManager.PluginUpdateAllCallback() {
                        public void a() {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                UpdateAllPluginResult updateAllPluginResult = new UpdateAllPluginResult();
                                updateAllPluginResult.c = 1;
                                bundle.putParcelable("result", updateAllPluginResult);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void b() {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                UpdateAllPluginResult updateAllPluginResult = new UpdateAllPluginResult();
                                updateAllPluginResult.c = 2;
                                bundle.putParcelable("result", updateAllPluginResult);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            }.execute();
        } else if (iClientCallback != null) {
            try {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new PluginError(-1, "core service permission deny"));
                iClientCallback.onFailure(bundle2);
            } catch (RemoteException e2) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e2);
            }
        }
    }

    public void updatePlugin(String str, final boolean z, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            final PluginRecord c = PluginManager.a().c(str);
            if (c != null) {
                new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        PluginManager.a().a(c, z, (PluginManager.PluginUpdateCallback) new PluginManager.PluginUpdateCallback() {
                            public void a(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 1;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 2;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(PluginRecord pluginRecord, HttpAsyncHandle httpAsyncHandle) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 3;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord, HttpAsyncHandle httpAsyncHandle) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 4;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(PluginRecord pluginRecord, float f) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 5;
                                    updatePluginResult.l = pluginRecord;
                                    updatePluginResult.m = f;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void c(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 6;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void d(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 7;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void e(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 8;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(PluginRecord pluginRecord, PluginUpdateInfo pluginUpdateInfo) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 9;
                                    updatePluginResult.l = pluginRecord;
                                    updatePluginResult.n = pluginUpdateInfo;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void f(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    UpdatePluginResult updatePluginResult = new UpdatePluginResult();
                                    updatePluginResult.k = 10;
                                    updatePluginResult.l = pluginRecord;
                                    bundle.putParcelable("result", updatePluginResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    }
                }.execute();
            } else if (iClientCallback != null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new PluginError(-1, "PluginRecord is null"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
        } else if (iClientCallback != null) {
            try {
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable("error", new PluginError(-1, "core service permission deny"));
                iClientCallback.onFailure(bundle3);
            } catch (RemoteException e3) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e3);
            }
        }
    }

    public void cancelPluginDownload(String str, final PluginDownloadTask pluginDownloadTask, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            final PluginRecord c = PluginManager.a().c(str);
            if (c == null) {
                try {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new PluginError(-1, "PluginRecord is null"));
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            } else {
                new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        PluginManager.a().a(c, pluginDownloadTask, (PluginManager.PluginCancelDownloadCallback) new PluginManager.PluginCancelDownloadCallback() {
                            public void a(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    try {
                                        iClientCallback.onSuccess(new Bundle());
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(PluginRecord pluginRecord) {
                                if (iClientCallback != null) {
                                    try {
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("error", new PluginError(-999, ""));
                                        iClientCallback.onFailure(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    }
                }.execute();
            }
        } else if (iClientCallback != null) {
            Bundle bundle3 = new Bundle();
            bundle3.putParcelable("error", new PluginError(-3, "core service : permission deny"));
            try {
                iClientCallback.onFailure(bundle3);
            } catch (RemoteException e3) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e3);
            }
        }
    }

    public IServerHandle getPluginAutoUpdate(final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new PluginError(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass35 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    PluginManager.a().a((PluginManager.GetAutoUpdateCallback) new PluginManager.GetAutoUpdateCallback() {
                        public void a(boolean z) {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putBoolean("result", z);
                                try {
                                    iClientCallback.onSuccess(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a() {
                            if (iClientCallback != null) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", new PluginError(-999, ""));
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            };
            r0.execute();
            return r0;
        }
    }

    public IServerHandle setPluginAutoUpdate(final boolean z, final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
            return null;
        } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
            if (iClientCallback != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("error", new PluginError(-3, "core service : permission deny"));
                try {
                    iClientCallback.onFailure(bundle2);
                } catch (RemoteException e2) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                }
            }
            return null;
        } else {
            AnonymousClass36 r0 = new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    PluginManager.a().a(z, (PluginManager.SetAutoUpdateCallback) new PluginManager.SetAutoUpdateCallback() {
                        public void a(boolean z) {
                            if (iClientCallback != null) {
                                try {
                                    iClientCallback.onSuccess(new Bundle());
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void a() {
                            if (iClientCallback != null) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("error", new PluginError(-999, ""));
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            };
            r0.execute();
            return r0;
        }
    }

    public List<PluginPackageInfo> getPluginInstalledPackageInfoList() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().k();
        }
        return null;
    }

    public PluginPackageInfo getPluginInstalledPackageInfo(long j) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().d(j);
        }
        return null;
    }

    public List<PluginPackageInfo> getPluginDownloadedPackageInfoList() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            return PluginManager.a().l();
        }
        return null;
    }

    public IServerHandle debugPluginPackage(final IClientCallback iClientCallback) throws RemoteException {
        if (HostSetting.i || HostSetting.g) {
            int callingPid = getCallingPid();
            int callingUid = getCallingUid();
            if (!CoreManager.a().b(callingPid)) {
                if (iClientCallback != null) {
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                        iClientCallback.onFailure(bundle);
                    } catch (RemoteException e) {
                        Log.e(TAG, AppMeasurement.Param.FATAL, e);
                    }
                }
                return null;
            } else if (!WhiteListManager.a().a(callingPid, callingUid)) {
                if (iClientCallback != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable("error", new PluginError(-3, "core service : permission deny"));
                    try {
                        iClientCallback.onFailure(bundle2);
                    } catch (RemoteException e2) {
                        Log.e(TAG, AppMeasurement.Param.FATAL, e2);
                    }
                }
                return null;
            } else {
                AnonymousClass37 r0 = new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        PluginManager.a().a((PluginManager.DebugPackageCallback) new PluginManager.DebugPackageCallback() {
                            public void a() {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginDebugPackageResult downloadPluginDebugPackageResult = new DownloadPluginDebugPackageResult();
                                    downloadPluginDebugPackageResult.e = 1;
                                    bundle.putParcelable("result", downloadPluginDebugPackageResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void a(String str) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginDebugPackageResult downloadPluginDebugPackageResult = new DownloadPluginDebugPackageResult();
                                    downloadPluginDebugPackageResult.e = 2;
                                    downloadPluginDebugPackageResult.f = str;
                                    bundle.putParcelable("result", downloadPluginDebugPackageResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b() {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginDebugPackageResult downloadPluginDebugPackageResult = new DownloadPluginDebugPackageResult();
                                    downloadPluginDebugPackageResult.e = 3;
                                    bundle.putParcelable("result", downloadPluginDebugPackageResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }

                            public void b(String str) {
                                if (iClientCallback != null) {
                                    Bundle bundle = new Bundle();
                                    DownloadPluginDebugPackageResult downloadPluginDebugPackageResult = new DownloadPluginDebugPackageResult();
                                    downloadPluginDebugPackageResult.e = 4;
                                    downloadPluginDebugPackageResult.f = str;
                                    bundle.putParcelable("result", downloadPluginDebugPackageResult);
                                    try {
                                        iClientCallback.onSuccess(bundle);
                                    } catch (RemoteException e) {
                                        Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                    }
                                }
                            }
                        });
                    }
                };
                r0.execute();
                return r0;
            }
        } else {
            if (iClientCallback != null) {
                try {
                    Bundle bundle3 = new Bundle();
                    bundle3.putParcelable("error", new PluginError(-999, "core service : only for debug or sdk"));
                    iClientCallback.onFailure(bundle3);
                } catch (RemoteException e3) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e3);
                }
            }
            return null;
        }
    }

    public void dumpPlugin(final IClientCallback iClientCallback) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid)) {
            if (iClientCallback != null) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("error", new PluginError(-2, "core service : process not register"));
                    iClientCallback.onFailure(bundle);
                } catch (RemoteException e) {
                    Log.e(TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        } else if (WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    PluginManager.a().a((PluginManager.DumpPluginCallback) new PluginManager.DumpPluginCallback() {
                        public void a() {
                            if (iClientCallback != null) {
                                try {
                                    iClientCallback.onSuccess(new Bundle());
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }

                        public void b() {
                            if (iClientCallback != null) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("error", new PluginError(-999, "core service : plugin manager failure "));
                                try {
                                    iClientCallback.onFailure(bundle);
                                } catch (RemoteException e) {
                                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                                }
                            }
                        }
                    });
                }
            }.execute();
        } else if (iClientCallback != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("error", new PluginError(-3, "core service : permission deny"));
            try {
                iClientCallback.onFailure(bundle2);
            } catch (RemoteException e2) {
                Log.e(TAG, AppMeasurement.Param.FATAL, e2);
            }
        }
    }

    public void loadLocalPluginConfig() throws RemoteException {
        PluginManager.a().f();
    }

    public void addStatRecord(StatType statType, String str, String str2, String str3, String str4, boolean z) throws RemoteException {
        if (!ServerCompact.g(CoreService.getAppContext())) {
            int callingPid = getCallingPid();
            int callingUid = getCallingUid();
            if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
                final StatType statType2 = statType;
                final String str5 = str2;
                final String str6 = str;
                final String str7 = str3;
                final String str8 = str4;
                final boolean z2 = z;
                new CoreAsyncTask() {
                    public void onCancel() {
                    }

                    public void run() {
                        if (statType2 != null && !TextUtils.isEmpty(str5)) {
                            StatManager.c().a(statType2.getValue(), str6, str5, str7, str8, z2);
                        }
                    }
                }.execute();
            }
        }
    }

    public boolean postStatRecord(final String str, final boolean z) throws RemoteException {
        if (!z && TextUtils.isEmpty(str)) {
            return false;
        }
        if (!StatManager.a()) {
            if (StatLogSender.c.equals(str)) {
                return StatManager.b();
            }
            return false;
        } else if (StatLogSender.c.equals(str)) {
            return true;
        } else {
            int callingPid = getCallingPid();
            int callingUid = getCallingUid();
            if (!CoreManager.a().b(callingPid) || !WhiteListManager.a().a(callingPid, callingUid)) {
                return false;
            }
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    StatManager.c().a(str, z);
                }
            }.execute();
            return true;
        }
    }

    public String takeStatSession(long j, long j2) throws RemoteException {
        return StatSession.a(j, j2);
    }

    public void uploadStat() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    StatManager.c().d();
                }
            }.execute();
        }
    }

    public void searchBluetoothDevice(SearchRequest searchRequest, SearchResponse searchResponse) throws RemoteException {
        BluetoothService.a().a(searchRequest, searchResponse);
    }

    public void stopSearchBluetoothDevice() throws RemoteException {
        BluetoothService.a().b();
    }

    public void callBluetoothApi(String str, int i, Bundle bundle, IBleResponse iBleResponse) throws RemoteException {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        BluetoothService.a().a(str, i, bundle, iBleResponse);
    }

    public ISecureConnectHandler secureConnect(String str, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.a(str, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler securityChipConnect(String str, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.b(str, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler securityChipPincodeConnect(String str, String str2, String str3, int i, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.a(str, str2, str3, i, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler securityChipSharedDeviceConnect(String str, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.c(str, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler bleMeshBind(String str, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.d(str, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler bleMeshConnect(final String str, final String str2, String str3, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.a(str, str2, str3, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                if (i == 0) {
                    DeviceApi.b(str, str2, (AsyncCallback<Void, com.xiaomi.smarthome.frame.Error>) null);
                }
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler bleStandardAuthBind(String str, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.e(str, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public ISecureConnectHandler bleStandardAuthConnect(String str, SecureConnectOptions secureConnectOptions, final ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
        return BleSecureConnectManager.f(str, secureConnectOptions, new IBleSecureConnectResponse() {
            public void a(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onConnectResponse(i, bundle);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            public void b(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onAuthResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void c(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onBindResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }

            public void d(int i, Bundle bundle) {
                try {
                    iSecureConnectResponse.onLastResponse(i, bundle);
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        });
    }

    public void startBleMeshUpgrade(String str, String str2, String str3, String str4, IBleMeshUpgradeResponse iBleMeshUpgradeResponse) throws RemoteException {
        BluetoothService.a().a(str, str2, str3, str4, iBleMeshUpgradeResponse);
    }

    public void cancelBleMeshUpgrade(String str) throws RemoteException {
        BluetoothService.a().a(str);
    }

    public void getBluetoothCache(String str, int i, Bundle bundle) throws RemoteException {
        BluetoothService.a().a(str, i, bundle);
    }

    public void setBluetoothCache(String str, int i, Bundle bundle) throws RemoteException {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        BluetoothService.a().b(str, i, bundle);
    }

    public void searchMiioBluetoothDevice(SearchRequest searchRequest, IBleResponse iBleResponse) throws RemoteException {
        if (searchRequest != null && iBleResponse != null) {
            BluetoothService.a().a(searchRequest, iBleResponse);
        }
    }

    public boolean setAlertConfigs(String str, int i, boolean z) throws RemoteException {
        return MiuiSDKHelper.a().a(str, i, z);
    }

    public void callUPnPApi(UPnPRequest uPnPRequest, IClientCallback iClientCallback) throws RemoteException {
        UPnPService.INSTANCE.callUPnPApi(uPnPRequest, iClientCallback);
    }

    public String getRootNodeValue(String str, String str2) throws RemoteException {
        return UPnPService.INSTANCE.getRootNodeValue(str, str2);
    }

    public void forceUpdateScene() throws RemoteException {
        SceneManager.a().b();
    }

    public void resetCore() throws RemoteException {
        try {
            SceneManager.a().d();
        } catch (Exception unused) {
        }
    }

    public void onActivityResume(final String str) throws RemoteException {
        final int callingPid = getCallingPid();
        final int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    try {
                        CoreManager.a().a("com.xiaomi.smarthome").onActivityResume(callingPid, callingUid, str);
                    } catch (Exception unused) {
                    }
                }
            }.execute();
        }
    }

    public MiHomeMemoryFile getPluginRecordMemoryFile() {
        LogUtil.a(TAG, "getPluginRecordMemoryFile");
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (!CoreManager.a().b(callingPid) || !WhiteListManager.a().a(callingPid, callingUid)) {
            return null;
        }
        MiHomeMemoryFile miHomeMemoryFile = new MiHomeMemoryFile();
        miHomeMemoryFile.a(PluginManager.a().i());
        return miHomeMemoryFile;
    }

    public void installDebugRNPluginWithoutPackage(String str, String str2) throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            PluginManager.a().a(str, str2);
        }
    }

    public void applicationEnterForground() throws RemoteException {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    DeviceManager.a().g();
                }
            }.execute();
        }
    }

    public void applicationEnterBackground() {
        int callingPid = getCallingPid();
        int callingUid = getCallingUid();
        if (CoreManager.a().b(callingPid) && WhiteListManager.a().a(callingPid, callingUid)) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    DeviceManager.a().h();
                }
            }.execute();
        }
    }

    public void log(int i, String str, String str2) throws RemoteException {
        MyLogger.a().a(i, str, str2);
    }

    public void uploadLogFile(String str, String str2, String[] strArr, boolean z, final IClientCallback iClientCallback) throws RemoteException {
        MyLogger.a().a(str, str2, strArr, z, new AsyncCallback<Void, com.xiaomi.smarthome.frame.Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                try {
                    iClientCallback.onSuccess((Bundle) null);
                } catch (RemoteException e) {
                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                }
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                try {
                    iClientCallback.onFailure((Bundle) null);
                } catch (RemoteException e) {
                    Log.e(CoreApiStub.TAG, AppMeasurement.Param.FATAL, e);
                }
            }
        });
    }

    public IClassicBtRequest getClassicBtRequestImpl() throws RemoteException {
        return ClassicBtService.getInstance();
    }

    public void addClassicBtResponse(IClassicBtResponse iClassicBtResponse) throws RemoteException {
        ClassicBtService.getInstance().setClassicBtResponse(iClassicBtResponse);
    }

    public HomeDeviceInfo getSharedHomeDeviceInfo(String str) throws RemoteException {
        return CoreSharedHomeManager.a().b(str);
    }

    public void setCurrentHome(HomeDeviceInfo homeDeviceInfo) throws RemoteException {
        CoreSharedHomeManager.a().a(homeDeviceInfo == null ? null : homeDeviceInfo.c());
    }

    public void loadHomeDeviceList(long j, long j2, final IClientCallback iClientCallback) throws RemoteException {
        CoreSharedHomeManager.a().a(j, j2, (NetCallback<ArrayList<Device>, NetError>) new NetCallback<ArrayList<Device>, NetError>() {
            /* renamed from: a */
            public void b(ArrayList<Device> arrayList) {
            }

            /* renamed from: b */
            public void a(ArrayList<Device> arrayList) {
                if (iClientCallback != null) {
                    try {
                        iClientCallback.onSuccess((Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void a(NetError netError) {
                if (iClientCallback != null) {
                    try {
                        iClientCallback.onFailure((Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public byte[] miotBleEncryptSync(String str, byte[] bArr) throws RemoteException {
        return BluetoothService.a().a(str, bArr);
    }

    public byte[] miotBleDecryptSync(String str, byte[] bArr) throws RemoteException {
        return BluetoothService.a().b(str, bArr);
    }
}
