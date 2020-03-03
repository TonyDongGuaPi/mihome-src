package com.xiaomi.smarthome.core.server;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.account.OAuthAccount;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.entity.globaldynamicsetting.CTAInfo;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.entity.upnp.UPnPRequest;
import com.xiaomi.smarthome.core.server.IServerHandle;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtRequest;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions;
import com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam;
import com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.util.List;

public interface ICoreApi extends IInterface {
    void addClassicBtResponse(IClassicBtResponse iClassicBtResponse) throws RemoteException;

    void addStatRecord(StatType statType, String str, String str2, String str3, String str4, boolean z) throws RemoteException;

    void applicationEnterBackground() throws RemoteException;

    void applicationEnterForground() throws RemoteException;

    IServerHandle batchRpcAsync(List<BatchRpcParam> list, IClientCallback iClientCallback) throws RemoteException;

    ISecureConnectHandler bleMeshBind(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    ISecureConnectHandler bleMeshConnect(String str, String str2, String str3, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    ISecureConnectHandler bleStandardAuthBind(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    ISecureConnectHandler bleStandardAuthConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    void callBluetoothApi(String str, int i, Bundle bundle, IBleResponse iBleResponse) throws RemoteException;

    void callUPnPApi(UPnPRequest uPnPRequest, IClientCallback iClientCallback) throws RemoteException;

    void cancelBleMeshUpgrade(String str) throws RemoteException;

    void cancelPluginDownload(String str, PluginDownloadTask pluginDownloadTask, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle clearAccount(IClientCallback iClientCallback) throws RemoteException;

    void clearAllMiServiceTokenInSystem() throws RemoteException;

    void clearMiServiceTokenInSystem(String str) throws RemoteException;

    void clearPluginConfig(IClientCallback iClientCallback) throws RemoteException;

    void clearWhiteList() throws RemoteException;

    IServerHandle debugPluginPackage(IClientCallback iClientCallback) throws RemoteException;

    IServerHandle delDeviceBatch(List<String> list, IClientCallback iClientCallback) throws RemoteException;

    void deletePlugin(String str, IClientCallback iClientCallback) throws RemoteException;

    void downloadPlugin(String str, IClientCallback iClientCallback) throws RemoteException;

    void dumpPlugin(IClientCallback iClientCallback) throws RemoteException;

    void forceUpdateScene() throws RemoteException;

    void gc() throws RemoteException;

    AccountType getAccountType() throws RemoteException;

    void getBluetoothCache(String str, int i, Bundle bundle) throws RemoteException;

    IClassicBtRequest getClassicBtRequestImpl() throws RemoteException;

    IServerHandle getDeviceList(IClientCallback iClientCallback) throws RemoteException;

    CTAInfo getGlobalSettingCTA() throws RemoteException;

    Bundle getGlobalSettingLocale() throws RemoteException;

    ServerBean getGlobalSettingServer() throws RemoteException;

    String getGlobalSettingServerEnv() throws RemoteException;

    String getHomeId() throws RemoteException;

    LoginMiAccount getMiAccount() throws RemoteException;

    String getMiId() throws RemoteException;

    String getModelByProductId(int i) throws RemoteException;

    String getModelBySSID(String str) throws RemoteException;

    OAuthAccount getOAuthAccount() throws RemoteException;

    IServerHandle getPluginAutoUpdate(IClientCallback iClientCallback) throws RemoteException;

    List<PluginPackageInfo> getPluginDownloadedPackageInfoList() throws RemoteException;

    PluginPackageInfo getPluginInstalledPackageInfo(long j) throws RemoteException;

    List<PluginPackageInfo> getPluginInstalledPackageInfoList() throws RemoteException;

    PluginRecord getPluginRecord(String str) throws RemoteException;

    List<PluginRecord> getPluginRecordList() throws RemoteException;

    MiHomeMemoryFile getPluginRecordMemoryFile() throws RemoteException;

    int getProductIdByModel(String str) throws RemoteException;

    String getRootNodeValue(String str, String str2) throws RemoteException;

    HomeDeviceInfo getSharedHomeDeviceInfo(String str) throws RemoteException;

    void installDebugRNPluginWithoutPackage(String str, String str2) throws RemoteException;

    void installPlugin(String str, boolean z, IClientCallback iClientCallback) throws RemoteException;

    boolean isBooleanValue(String str) throws RemoteException;

    boolean isLoggedIn() throws RemoteException;

    boolean isMiLoggedIn() throws RemoteException;

    boolean isPluginDevice(String str) throws RemoteException;

    boolean isPluginForceUpdating(String str) throws RemoteException;

    boolean isServicePromoteSuccess() throws RemoteException;

    void loadHomeDeviceList(long j, long j2, IClientCallback iClientCallback) throws RemoteException;

    void loadLocalPluginConfig() throws RemoteException;

    void localPing(String str, IClientCallback iClientCallback) throws RemoteException;

    void log(int i, String str, String str2) throws RemoteException;

    byte[] miotBleDecryptSync(String str, byte[] bArr) throws RemoteException;

    byte[] miotBleEncryptSync(String str, byte[] bArr) throws RemoteException;

    void onActivityResume(String str) throws RemoteException;

    boolean postStatRecord(String str, boolean z) throws RemoteException;

    IBleChannelWriter registerChannelReader(String str, IBleChannelReader iBleChannelReader) throws RemoteException;

    void registerClientApi(IClientApi iClientApi) throws RemoteException;

    IServerHandle renameDevice(String str, String str2, IClientCallback iClientCallback) throws RemoteException;

    void resetCore() throws RemoteException;

    IServerHandle rpcAsync(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle rpcAsyncToCloud(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle rpcAsyncToLocal(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle scanDeviceList(ScanType scanType, IClientCallback iClientCallback) throws RemoteException;

    void searchBluetoothDevice(SearchRequest searchRequest, SearchResponse searchResponse) throws RemoteException;

    void searchMiioBluetoothDevice(SearchRequest searchRequest, IBleResponse iBleResponse) throws RemoteException;

    ISecureConnectHandler secureConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    ISecureConnectHandler securityChipConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    ISecureConnectHandler securityChipPincodeConnect(String str, String str2, String str3, int i, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    ISecureConnectHandler securityChipSharedDeviceConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException;

    IServerHandle sendMiRechargeRequest(NetRequest netRequest, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle sendMiShopRequest(NetRequest netRequest, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle sendRouterRequest(NetRequest netRequest, String str, boolean z, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle sendSmartHomeRequest(NetRequest netRequest, Crypto crypto, IClientCallback iClientCallback) throws RemoteException;

    boolean setAlertConfigs(String str, int i, boolean z) throws RemoteException;

    void setBluetoothCache(String str, int i, Bundle bundle) throws RemoteException;

    void setCurrentHome(HomeDeviceInfo homeDeviceInfo) throws RemoteException;

    IServerHandle setGlobalSettingCTA(boolean z, boolean z2, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setGlobalSettingLocale(Bundle bundle, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setGlobalSettingServer(ServerBean serverBean, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setGlobalSettingServerEnv(String str, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setMiAccount(LoginMiAccount loginMiAccount, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setMiServiceTokenTmp(MiServiceTokenInfo miServiceTokenInfo, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setOAuthAccount(OAuthAccount oAuthAccount, IClientCallback iClientCallback) throws RemoteException;

    IServerHandle setPluginAutoUpdate(boolean z, IClientCallback iClientCallback) throws RemoteException;

    void setScanTimePeriod(int i) throws RemoteException;

    void startBleMeshUpgrade(String str, String str2, String str3, String str4, IBleMeshUpgradeResponse iBleMeshUpgradeResponse) throws RemoteException;

    void startScan() throws RemoteException;

    void startScanWithCallback(IClientCallback iClientCallback) throws RemoteException;

    void stopScan() throws RemoteException;

    void stopSearchBluetoothDevice() throws RemoteException;

    String takeStatSession(long j, long j2) throws RemoteException;

    void updateAllPlugin(boolean z, IClientCallback iClientCallback) throws RemoteException;

    void updateBooleanValue(String str, boolean z) throws RemoteException;

    IServerHandle updateDeviceBatch(List<String> list, IClientCallback iClientCallback) throws RemoteException;

    void updatePlugin(String str, boolean z, IClientCallback iClientCallback) throws RemoteException;

    void updatePluginConfig(boolean z, IClientCallback iClientCallback) throws RemoteException;

    void updateWhiteList(boolean z) throws RemoteException;

    void uploadLogFile(String str, String str2, String[] strArr, boolean z, IClientCallback iClientCallback) throws RemoteException;

    void uploadStat() throws RemoteException;

    public static abstract class Stub extends Binder implements ICoreApi {
        private static final String DESCRIPTOR = "com.xiaomi.smarthome.core.server.ICoreApi";
        static final int TRANSACTION_addClassicBtResponse = 106;
        static final int TRANSACTION_addStatRecord = 72;
        static final int TRANSACTION_applicationEnterBackground = 102;
        static final int TRANSACTION_applicationEnterForground = 101;
        static final int TRANSACTION_batchRpcAsync = 29;
        static final int TRANSACTION_bleMeshBind = 88;
        static final int TRANSACTION_bleMeshConnect = 89;
        static final int TRANSACTION_bleStandardAuthBind = 92;
        static final int TRANSACTION_bleStandardAuthConnect = 93;
        static final int TRANSACTION_callBluetoothApi = 78;
        static final int TRANSACTION_callUPnPApi = 96;
        static final int TRANSACTION_cancelBleMeshUpgrade = 91;
        static final int TRANSACTION_cancelPluginDownload = 61;
        static final int TRANSACTION_clearAccount = 15;
        static final int TRANSACTION_clearAllMiServiceTokenInSystem = 16;
        static final int TRANSACTION_clearMiServiceTokenInSystem = 17;
        static final int TRANSACTION_clearPluginConfig = 48;
        static final int TRANSACTION_clearWhiteList = 42;
        static final int TRANSACTION_debugPluginPackage = 67;
        static final int TRANSACTION_delDeviceBatch = 28;
        static final int TRANSACTION_deletePlugin = 57;
        static final int TRANSACTION_downloadPlugin = 56;
        static final int TRANSACTION_dumpPlugin = 68;
        static final int TRANSACTION_forceUpdateScene = 98;
        static final int TRANSACTION_gc = 2;
        static final int TRANSACTION_getAccountType = 6;
        static final int TRANSACTION_getBluetoothCache = 80;
        static final int TRANSACTION_getClassicBtRequestImpl = 105;
        static final int TRANSACTION_getDeviceList = 23;
        static final int TRANSACTION_getGlobalSettingCTA = 33;
        static final int TRANSACTION_getGlobalSettingLocale = 39;
        static final int TRANSACTION_getGlobalSettingServer = 35;
        static final int TRANSACTION_getGlobalSettingServerEnv = 37;
        static final int TRANSACTION_getHomeId = 7;
        static final int TRANSACTION_getMiAccount = 13;
        static final int TRANSACTION_getMiId = 9;
        static final int TRANSACTION_getModelByProductId = 52;
        static final int TRANSACTION_getModelBySSID = 54;
        static final int TRANSACTION_getOAuthAccount = 14;
        static final int TRANSACTION_getPluginAutoUpdate = 62;
        static final int TRANSACTION_getPluginDownloadedPackageInfoList = 66;
        static final int TRANSACTION_getPluginInstalledPackageInfo = 65;
        static final int TRANSACTION_getPluginInstalledPackageInfoList = 64;
        static final int TRANSACTION_getPluginRecord = 50;
        static final int TRANSACTION_getPluginRecordList = 51;
        static final int TRANSACTION_getPluginRecordMemoryFile = 70;
        static final int TRANSACTION_getProductIdByModel = 53;
        static final int TRANSACTION_getRootNodeValue = 97;
        static final int TRANSACTION_getSharedHomeDeviceInfo = 107;
        static final int TRANSACTION_installDebugRNPluginWithoutPackage = 71;
        static final int TRANSACTION_installPlugin = 58;
        static final int TRANSACTION_isBooleanValue = 3;
        static final int TRANSACTION_isLoggedIn = 5;
        static final int TRANSACTION_isMiLoggedIn = 8;
        static final int TRANSACTION_isPluginDevice = 49;
        static final int TRANSACTION_isPluginForceUpdating = 55;
        static final int TRANSACTION_isServicePromoteSuccess = 32;
        static final int TRANSACTION_loadHomeDeviceList = 109;
        static final int TRANSACTION_loadLocalPluginConfig = 69;
        static final int TRANSACTION_localPing = 31;
        static final int TRANSACTION_log = 103;
        static final int TRANSACTION_miotBleDecryptSync = 95;
        static final int TRANSACTION_miotBleEncryptSync = 94;
        static final int TRANSACTION_onActivityResume = 100;
        static final int TRANSACTION_postStatRecord = 73;
        static final int TRANSACTION_registerChannelReader = 84;
        static final int TRANSACTION_registerClientApi = 1;
        static final int TRANSACTION_renameDevice = 27;
        static final int TRANSACTION_resetCore = 99;
        static final int TRANSACTION_rpcAsync = 24;
        static final int TRANSACTION_rpcAsyncToCloud = 26;
        static final int TRANSACTION_rpcAsyncToLocal = 25;
        static final int TRANSACTION_scanDeviceList = 22;
        static final int TRANSACTION_searchBluetoothDevice = 76;
        static final int TRANSACTION_searchMiioBluetoothDevice = 82;
        static final int TRANSACTION_secureConnect = 79;
        static final int TRANSACTION_securityChipConnect = 85;
        static final int TRANSACTION_securityChipPincodeConnect = 86;
        static final int TRANSACTION_securityChipSharedDeviceConnect = 87;
        static final int TRANSACTION_sendMiRechargeRequest = 20;
        static final int TRANSACTION_sendMiShopRequest = 21;
        static final int TRANSACTION_sendRouterRequest = 19;
        static final int TRANSACTION_sendSmartHomeRequest = 18;
        static final int TRANSACTION_setAlertConfigs = 83;
        static final int TRANSACTION_setBluetoothCache = 81;
        static final int TRANSACTION_setCurrentHome = 108;
        static final int TRANSACTION_setGlobalSettingCTA = 34;
        static final int TRANSACTION_setGlobalSettingLocale = 40;
        static final int TRANSACTION_setGlobalSettingServer = 36;
        static final int TRANSACTION_setGlobalSettingServerEnv = 38;
        static final int TRANSACTION_setMiAccount = 10;
        static final int TRANSACTION_setMiServiceTokenTmp = 11;
        static final int TRANSACTION_setOAuthAccount = 12;
        static final int TRANSACTION_setPluginAutoUpdate = 63;
        static final int TRANSACTION_setScanTimePeriod = 43;
        static final int TRANSACTION_startBleMeshUpgrade = 90;
        static final int TRANSACTION_startScan = 45;
        static final int TRANSACTION_startScanWithCallback = 44;
        static final int TRANSACTION_stopScan = 46;
        static final int TRANSACTION_stopSearchBluetoothDevice = 77;
        static final int TRANSACTION_takeStatSession = 74;
        static final int TRANSACTION_updateAllPlugin = 59;
        static final int TRANSACTION_updateBooleanValue = 4;
        static final int TRANSACTION_updateDeviceBatch = 30;
        static final int TRANSACTION_updatePlugin = 60;
        static final int TRANSACTION_updatePluginConfig = 47;
        static final int TRANSACTION_updateWhiteList = 41;
        static final int TRANSACTION_uploadLogFile = 104;
        static final int TRANSACTION_uploadStat = 75;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICoreApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICoreApi)) {
                return new Proxy(iBinder);
            }
            return (ICoreApi) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v43, resolved type: com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v54, resolved type: com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v57, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v62, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v65, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v68, resolved type: com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v87, resolved type: com.xiaomi.smarthome.core.entity.upnp.UPnPRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v93, resolved type: com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo} */
        /* JADX WARNING: type inference failed for: r10v0 */
        /* JADX WARNING: type inference failed for: r10v1, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v3, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v5, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v7, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v9, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v11, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v13, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v15, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v17, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v19, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v21, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v23, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v25, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v27, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v29, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v31, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v33, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v35, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v37, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v39, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v41, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v46, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v48, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v50, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v52 */
        /* JADX WARNING: type inference failed for: r10v53 */
        /* JADX WARNING: type inference failed for: r10v60, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v71, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v73, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v75, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v77, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v79, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v81, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v83, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v85, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v91, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r10v96 */
        /* JADX WARNING: type inference failed for: r10v97 */
        /* JADX WARNING: type inference failed for: r10v98 */
        /* JADX WARNING: type inference failed for: r10v99 */
        /* JADX WARNING: type inference failed for: r10v100 */
        /* JADX WARNING: type inference failed for: r10v101 */
        /* JADX WARNING: type inference failed for: r10v102 */
        /* JADX WARNING: type inference failed for: r10v103 */
        /* JADX WARNING: type inference failed for: r10v104 */
        /* JADX WARNING: type inference failed for: r10v105 */
        /* JADX WARNING: type inference failed for: r10v106 */
        /* JADX WARNING: type inference failed for: r10v107 */
        /* JADX WARNING: type inference failed for: r10v108 */
        /* JADX WARNING: type inference failed for: r10v109 */
        /* JADX WARNING: type inference failed for: r10v110 */
        /* JADX WARNING: type inference failed for: r10v111 */
        /* JADX WARNING: type inference failed for: r10v112 */
        /* JADX WARNING: type inference failed for: r10v113 */
        /* JADX WARNING: type inference failed for: r10v114 */
        /* JADX WARNING: type inference failed for: r10v115 */
        /* JADX WARNING: type inference failed for: r10v116 */
        /* JADX WARNING: type inference failed for: r10v117 */
        /* JADX WARNING: type inference failed for: r10v118 */
        /* JADX WARNING: type inference failed for: r10v119 */
        /* JADX WARNING: type inference failed for: r10v120 */
        /* JADX WARNING: type inference failed for: r10v121 */
        /* JADX WARNING: type inference failed for: r10v122 */
        /* JADX WARNING: type inference failed for: r10v123 */
        /* JADX WARNING: type inference failed for: r10v124 */
        /* JADX WARNING: type inference failed for: r10v125 */
        /* JADX WARNING: type inference failed for: r10v126 */
        /* JADX WARNING: type inference failed for: r10v127 */
        /* JADX WARNING: type inference failed for: r10v128 */
        /* JADX WARNING: type inference failed for: r10v129 */
        /* JADX WARNING: type inference failed for: r10v130 */
        /* JADX WARNING: type inference failed for: r10v131 */
        /* JADX WARNING: type inference failed for: r10v132 */
        /* JADX WARNING: type inference failed for: r10v133 */
        /* JADX WARNING: type inference failed for: r10v134 */
        /* JADX WARNING: type inference failed for: r10v135 */
        /* JADX WARNING: type inference failed for: r10v136 */
        /* JADX WARNING: type inference failed for: r10v137 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r13, android.os.Parcel r14, android.os.Parcel r15, int r16) throws android.os.RemoteException {
            /*
                r12 = this;
                r7 = r12
                r0 = r13
                r1 = r14
                r8 = r15
                java.lang.String r2 = "com.xiaomi.smarthome.core.server.ICoreApi"
                r3 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r9 = 1
                if (r0 == r3) goto L_0x0bdc
                r3 = 0
                r10 = 0
                switch(r0) {
                    case 1: goto L_0x0bca;
                    case 2: goto L_0x0bc0;
                    case 3: goto L_0x0bae;
                    case 4: goto L_0x0b99;
                    case 5: goto L_0x0b8b;
                    case 6: goto L_0x0b74;
                    case 7: goto L_0x0b66;
                    case 8: goto L_0x0b58;
                    case 9: goto L_0x0b4a;
                    case 10: goto L_0x0b1e;
                    case 11: goto L_0x0af2;
                    case 12: goto L_0x0ac6;
                    case 13: goto L_0x0aaf;
                    case 14: goto L_0x0a98;
                    case 15: goto L_0x0a7c;
                    case 16: goto L_0x0a72;
                    case 17: goto L_0x0a64;
                    case 18: goto L_0x0a28;
                    case 19: goto L_0x09f1;
                    case 20: goto L_0x09c5;
                    case 21: goto L_0x0999;
                    case 22: goto L_0x096d;
                    case 23: goto L_0x0951;
                    case 24: goto L_0x0929;
                    case 25: goto L_0x0901;
                    case 26: goto L_0x08d9;
                    case 27: goto L_0x08b5;
                    case 28: goto L_0x0895;
                    case 29: goto L_0x0873;
                    case 30: goto L_0x0853;
                    case 31: goto L_0x083d;
                    case 32: goto L_0x082f;
                    case 33: goto L_0x0818;
                    case 34: goto L_0x07ec;
                    case 35: goto L_0x07d5;
                    case 36: goto L_0x07a9;
                    case 37: goto L_0x079b;
                    case 38: goto L_0x077b;
                    case 39: goto L_0x0764;
                    case 40: goto L_0x0738;
                    case 41: goto L_0x0727;
                    case 42: goto L_0x071d;
                    case 43: goto L_0x070f;
                    case 44: goto L_0x06fd;
                    case 45: goto L_0x06f3;
                    case 46: goto L_0x06e9;
                    case 47: goto L_0x06d0;
                    case 48: goto L_0x06be;
                    case 49: goto L_0x06ac;
                    case 50: goto L_0x0691;
                    case 51: goto L_0x0683;
                    case 52: goto L_0x0671;
                    case 53: goto L_0x065f;
                    case 54: goto L_0x064d;
                    case 55: goto L_0x063b;
                    case 56: goto L_0x0625;
                    case 57: goto L_0x060f;
                    case 58: goto L_0x05f2;
                    case 59: goto L_0x05d9;
                    case 60: goto L_0x05bc;
                    case 61: goto L_0x0597;
                    case 62: goto L_0x057b;
                    case 63: goto L_0x0558;
                    case 64: goto L_0x054a;
                    case 65: goto L_0x052f;
                    case 66: goto L_0x0521;
                    case 67: goto L_0x0505;
                    case 68: goto L_0x04f3;
                    case 69: goto L_0x04e9;
                    case 70: goto L_0x04d2;
                    case 71: goto L_0x04c0;
                    case 72: goto L_0x0488;
                    case 73: goto L_0x046f;
                    case 74: goto L_0x0459;
                    case 75: goto L_0x044f;
                    case 76: goto L_0x042e;
                    case 77: goto L_0x0424;
                    case 78: goto L_0x03ef;
                    case 79: goto L_0x03bf;
                    case 80: goto L_0x0392;
                    case 81: goto L_0x0371;
                    case 82: goto L_0x0350;
                    case 83: goto L_0x0333;
                    case 84: goto L_0x0313;
                    case 85: goto L_0x02e3;
                    case 86: goto L_0x029f;
                    case 87: goto L_0x026f;
                    case 88: goto L_0x023f;
                    case 89: goto L_0x0200;
                    case 90: goto L_0x01d8;
                    case 91: goto L_0x01ca;
                    case 92: goto L_0x019a;
                    case 93: goto L_0x016a;
                    case 94: goto L_0x0154;
                    case 95: goto L_0x013e;
                    case 96: goto L_0x011d;
                    case 97: goto L_0x0107;
                    case 98: goto L_0x00fd;
                    case 99: goto L_0x00f3;
                    case 100: goto L_0x00e5;
                    case 101: goto L_0x00db;
                    case 102: goto L_0x00d1;
                    case 103: goto L_0x00bb;
                    case 104: goto L_0x008e;
                    case 105: goto L_0x007a;
                    case 106: goto L_0x0068;
                    case 107: goto L_0x004d;
                    case 108: goto L_0x0034;
                    case 109: goto L_0x0016;
                    default: goto L_0x0011;
                }
            L_0x0011:
                boolean r0 = super.onTransact(r13, r14, r15, r16)
                return r0
            L_0x0016:
                r14.enforceInterface(r2)
                long r2 = r14.readLong()
                long r4 = r14.readLong()
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r6 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r0 = r12
                r1 = r2
                r3 = r4
                r5 = r6
                r0.loadHomeDeviceList(r1, r3, r5)
                r15.writeNoException()
                return r9
            L_0x0034:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0046
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo> r0 = com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r10 = r0
                com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo r10 = (com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo) r10
            L_0x0046:
                r12.setCurrentHome(r10)
                r15.writeNoException()
                return r9
            L_0x004d:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo r0 = r12.getSharedHomeDeviceInfo(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0064
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x0067
            L_0x0064:
                r15.writeInt(r3)
            L_0x0067:
                return r9
            L_0x0068:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse r0 = com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse.Stub.asInterface(r0)
                r12.addClassicBtResponse(r0)
                r15.writeNoException()
                return r9
            L_0x007a:
                r14.enforceInterface(r2)
                com.xiaomi.smarthome.core.server.bluetooth.IClassicBtRequest r0 = r12.getClassicBtRequestImpl()
                r15.writeNoException()
                if (r0 == 0) goto L_0x008a
                android.os.IBinder r10 = r0.asBinder()
            L_0x008a:
                r15.writeStrongBinder(r10)
                return r9
            L_0x008e:
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String[] r5 = r14.createStringArray()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x00a5
                r6 = 1
                goto L_0x00a6
            L_0x00a5:
                r6 = 0
            L_0x00a6:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r10 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r0 = r12
                r1 = r2
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r10
                r0.uploadLogFile(r1, r2, r3, r4, r5)
                r15.writeNoException()
                return r9
            L_0x00bb:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                java.lang.String r2 = r14.readString()
                java.lang.String r1 = r14.readString()
                r12.log(r0, r2, r1)
                r15.writeNoException()
                return r9
            L_0x00d1:
                r14.enforceInterface(r2)
                r12.applicationEnterBackground()
                r15.writeNoException()
                return r9
            L_0x00db:
                r14.enforceInterface(r2)
                r12.applicationEnterForground()
                r15.writeNoException()
                return r9
            L_0x00e5:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                r12.onActivityResume(r0)
                r15.writeNoException()
                return r9
            L_0x00f3:
                r14.enforceInterface(r2)
                r12.resetCore()
                r15.writeNoException()
                return r9
            L_0x00fd:
                r14.enforceInterface(r2)
                r12.forceUpdateScene()
                r15.writeNoException()
                return r9
            L_0x0107:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r1 = r14.readString()
                java.lang.String r0 = r12.getRootNodeValue(r0, r1)
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x011d:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x012f
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.upnp.UPnPRequest> r0 = com.xiaomi.smarthome.core.entity.upnp.UPnPRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r10 = r0
                com.xiaomi.smarthome.core.entity.upnp.UPnPRequest r10 = (com.xiaomi.smarthome.core.entity.upnp.UPnPRequest) r10
            L_0x012f:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r12.callUPnPApi(r10, r0)
                r15.writeNoException()
                return r9
            L_0x013e:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                byte[] r1 = r14.createByteArray()
                byte[] r0 = r12.miotBleDecryptSync(r0, r1)
                r15.writeNoException()
                r15.writeByteArray(r0)
                return r9
            L_0x0154:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                byte[] r1 = r14.createByteArray()
                byte[] r0 = r12.miotBleEncryptSync(r0, r1)
                r15.writeNoException()
                r15.writeByteArray(r0)
                return r9
            L_0x016a:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0180
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r2 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r2 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r2
                goto L_0x0181
            L_0x0180:
                r2 = r10
            L_0x0181:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r12.bleStandardAuthConnect(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0196
                android.os.IBinder r10 = r0.asBinder()
            L_0x0196:
                r15.writeStrongBinder(r10)
                return r9
            L_0x019a:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x01b0
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r2 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r2 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r2
                goto L_0x01b1
            L_0x01b0:
                r2 = r10
            L_0x01b1:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r12.bleStandardAuthBind(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x01c6
                android.os.IBinder r10 = r0.asBinder()
            L_0x01c6:
                r15.writeStrongBinder(r10)
                return r9
            L_0x01ca:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                r12.cancelBleMeshUpgrade(r0)
                r15.writeNoException()
                return r9
            L_0x01d8:
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse r6 = com.xiaomi.smarthome.core.server.bluetooth.IBleMeshUpgradeResponse.Stub.asInterface(r0)
                r0 = r12
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r0.startBleMeshUpgrade(r1, r2, r3, r4, r5)
                r15.writeNoException()
                return r9
            L_0x0200:
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x021f
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r0 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r0 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r0
                r5 = r0
                goto L_0x0220
            L_0x021f:
                r5 = r10
            L_0x0220:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r6 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r0)
                r0 = r12
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r0.bleMeshConnect(r1, r2, r3, r4, r5)
                r15.writeNoException()
                if (r0 == 0) goto L_0x023b
                android.os.IBinder r10 = r0.asBinder()
            L_0x023b:
                r15.writeStrongBinder(r10)
                return r9
            L_0x023f:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0255
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r2 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r2 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r2
                goto L_0x0256
            L_0x0255:
                r2 = r10
            L_0x0256:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r12.bleMeshBind(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x026b
                android.os.IBinder r10 = r0.asBinder()
            L_0x026b:
                r15.writeStrongBinder(r10)
                return r9
            L_0x026f:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0285
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r2 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r2 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r2
                goto L_0x0286
            L_0x0285:
                r2 = r10
            L_0x0286:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r12.securityChipSharedDeviceConnect(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x029b
                android.os.IBinder r10 = r0.asBinder()
            L_0x029b:
                r15.writeStrongBinder(r10)
                return r9
            L_0x029f:
                r14.enforceInterface(r2)
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                java.lang.String r4 = r14.readString()
                int r5 = r14.readInt()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x02c2
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r0 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r0 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r0
                r6 = r0
                goto L_0x02c3
            L_0x02c2:
                r6 = r10
            L_0x02c3:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r11 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r0)
                r0 = r12
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r6 = r11
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r0.securityChipPincodeConnect(r1, r2, r3, r4, r5, r6)
                r15.writeNoException()
                if (r0 == 0) goto L_0x02df
                android.os.IBinder r10 = r0.asBinder()
            L_0x02df:
                r15.writeStrongBinder(r10)
                return r9
            L_0x02e3:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x02f9
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r2 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r2 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r2
                goto L_0x02fa
            L_0x02f9:
                r2 = r10
            L_0x02fa:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r12.securityChipConnect(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x030f
                android.os.IBinder r10 = r0.asBinder()
            L_0x030f:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0313:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader r1 = com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter r0 = r12.registerChannelReader(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x032f
                android.os.IBinder r10 = r0.asBinder()
            L_0x032f:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0333:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                int r1 = r14.readInt()
                if (r1 == 0) goto L_0x0345
                r3 = 1
            L_0x0345:
                boolean r0 = r12.setAlertConfigs(r0, r2, r3)
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x0350:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0362
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest> r0 = com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r10 = r0
                com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest r10 = (com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest) r10
            L_0x0362:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.bluetooth.IBleResponse r0 = com.xiaomi.smarthome.core.server.bluetooth.IBleResponse.Stub.asInterface(r0)
                r12.searchMiioBluetoothDevice(r10, r0)
                r15.writeNoException()
                return r9
            L_0x0371:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                int r3 = r14.readInt()
                if (r3 == 0) goto L_0x038b
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r3.createFromParcel(r14)
                r10 = r1
                android.os.Bundle r10 = (android.os.Bundle) r10
            L_0x038b:
                r12.setBluetoothCache(r0, r2, r10)
                r15.writeNoException()
                return r9
            L_0x0392:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x03ac
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r4.createFromParcel(r14)
                r10 = r1
                android.os.Bundle r10 = (android.os.Bundle) r10
            L_0x03ac:
                r12.getBluetoothCache(r0, r2, r10)
                r15.writeNoException()
                if (r10 == 0) goto L_0x03bb
                r15.writeInt(r9)
                r10.writeToParcel(r15, r9)
                goto L_0x03be
            L_0x03bb:
                r15.writeInt(r3)
            L_0x03be:
                return r9
            L_0x03bf:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x03d5
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions> r2 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions r2 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.SecureConnectOptions) r2
                goto L_0x03d6
            L_0x03d5:
                r2 = r10
            L_0x03d6:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectResponse.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.internal.bluetooth.security.ISecureConnectHandler r0 = r12.secureConnect(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x03eb
                android.os.IBinder r10 = r0.asBinder()
            L_0x03eb:
                r15.writeStrongBinder(r10)
                return r9
            L_0x03ef:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0409
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r14)
                r10 = r4
                android.os.Bundle r10 = (android.os.Bundle) r10
            L_0x0409:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.bluetooth.IBleResponse r1 = com.xiaomi.smarthome.core.server.bluetooth.IBleResponse.Stub.asInterface(r1)
                r12.callBluetoothApi(r0, r2, r10, r1)
                r15.writeNoException()
                if (r10 == 0) goto L_0x0420
                r15.writeInt(r9)
                r10.writeToParcel(r15, r9)
                goto L_0x0423
            L_0x0420:
                r15.writeInt(r3)
            L_0x0423:
                return r9
            L_0x0424:
                r14.enforceInterface(r2)
                r12.stopSearchBluetoothDevice()
                r15.writeNoException()
                return r9
            L_0x042e:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0440
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest> r0 = com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                r10 = r0
                com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest r10 = (com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest) r10
            L_0x0440:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.server.bluetooth.SearchResponse r0 = com.xiaomi.smarthome.core.server.bluetooth.SearchResponse.Stub.asInterface(r0)
                r12.searchBluetoothDevice(r10, r0)
                r15.writeNoException()
                return r9
            L_0x044f:
                r14.enforceInterface(r2)
                r12.uploadStat()
                r15.writeNoException()
                return r9
            L_0x0459:
                r14.enforceInterface(r2)
                long r2 = r14.readLong()
                long r0 = r14.readLong()
                java.lang.String r0 = r12.takeStatSession(r2, r0)
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x046f:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r1 = r14.readInt()
                if (r1 == 0) goto L_0x047d
                r3 = 1
            L_0x047d:
                boolean r0 = r12.postStatRecord(r0, r3)
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x0488:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x049a
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.statistic.StatType> r0 = com.xiaomi.smarthome.core.entity.statistic.StatType.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.statistic.StatType r0 = (com.xiaomi.smarthome.core.entity.statistic.StatType) r0
                r10 = r0
            L_0x049a:
                java.lang.String r2 = r14.readString()
                java.lang.String r4 = r14.readString()
                java.lang.String r5 = r14.readString()
                java.lang.String r6 = r14.readString()
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x04b2
                r11 = 1
                goto L_0x04b3
            L_0x04b2:
                r11 = 0
            L_0x04b3:
                r0 = r12
                r1 = r10
                r3 = r4
                r4 = r5
                r5 = r6
                r6 = r11
                r0.addStatRecord(r1, r2, r3, r4, r5, r6)
                r15.writeNoException()
                return r9
            L_0x04c0:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r1 = r14.readString()
                r12.installDebugRNPluginWithoutPackage(r0, r1)
                r15.writeNoException()
                return r9
            L_0x04d2:
                r14.enforceInterface(r2)
                com.xiaomi.smarthome.core.server.MiHomeMemoryFile r0 = r12.getPluginRecordMemoryFile()
                r15.writeNoException()
                if (r0 == 0) goto L_0x04e5
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x04e8
            L_0x04e5:
                r15.writeInt(r3)
            L_0x04e8:
                return r9
            L_0x04e9:
                r14.enforceInterface(r2)
                r12.loadLocalPluginConfig()
                r15.writeNoException()
                return r9
            L_0x04f3:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r12.dumpPlugin(r0)
                r15.writeNoException()
                return r9
            L_0x0505:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.debugPluginPackage(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x051d
                android.os.IBinder r10 = r0.asBinder()
            L_0x051d:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0521:
                r14.enforceInterface(r2)
                java.util.List r0 = r12.getPluginDownloadedPackageInfoList()
                r15.writeNoException()
                r15.writeTypedList(r0)
                return r9
            L_0x052f:
                r14.enforceInterface(r2)
                long r0 = r14.readLong()
                com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r0 = r12.getPluginInstalledPackageInfo(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0546
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x0549
            L_0x0546:
                r15.writeInt(r3)
            L_0x0549:
                return r9
            L_0x054a:
                r14.enforceInterface(r2)
                java.util.List r0 = r12.getPluginInstalledPackageInfoList()
                r15.writeNoException()
                r15.writeTypedList(r0)
                return r9
            L_0x0558:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0562
                r3 = 1
            L_0x0562:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setPluginAutoUpdate(r3, r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0577
                android.os.IBinder r10 = r0.asBinder()
            L_0x0577:
                r15.writeStrongBinder(r10)
                return r9
            L_0x057b:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.getPluginAutoUpdate(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0593
                android.os.IBinder r10 = r0.asBinder()
            L_0x0593:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0597:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x05ad
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask> r2 = com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                r10 = r2
                com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask r10 = (com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask) r10
            L_0x05ad:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                r12.cancelPluginDownload(r0, r10, r1)
                r15.writeNoException()
                return r9
            L_0x05bc:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x05ca
                r3 = 1
            L_0x05ca:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                r12.updatePlugin(r0, r3, r1)
                r15.writeNoException()
                return r9
            L_0x05d9:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x05e3
                r3 = 1
            L_0x05e3:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r12.updateAllPlugin(r3, r0)
                r15.writeNoException()
                return r9
            L_0x05f2:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0600
                r3 = 1
            L_0x0600:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                r12.installPlugin(r0, r3, r1)
                r15.writeNoException()
                return r9
            L_0x060f:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                r12.deletePlugin(r0, r1)
                r15.writeNoException()
                return r9
            L_0x0625:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                r12.downloadPlugin(r0, r1)
                r15.writeNoException()
                return r9
            L_0x063b:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                boolean r0 = r12.isPluginForceUpdating(r0)
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x064d:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r0 = r12.getModelBySSID(r0)
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x065f:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r0 = r12.getProductIdByModel(r0)
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x0671:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                java.lang.String r0 = r12.getModelByProductId(r0)
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x0683:
                r14.enforceInterface(r2)
                java.util.List r0 = r12.getPluginRecordList()
                r15.writeNoException()
                r15.writeTypedList(r0)
                return r9
            L_0x0691:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                com.xiaomi.smarthome.core.entity.plugin.PluginRecord r0 = r12.getPluginRecord(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x06a8
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x06ab
            L_0x06a8:
                r15.writeInt(r3)
            L_0x06ab:
                return r9
            L_0x06ac:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                boolean r0 = r12.isPluginDevice(r0)
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x06be:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r12.clearPluginConfig(r0)
                r15.writeNoException()
                return r9
            L_0x06d0:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x06da
                r3 = 1
            L_0x06da:
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r12.updatePluginConfig(r3, r0)
                r15.writeNoException()
                return r9
            L_0x06e9:
                r14.enforceInterface(r2)
                r12.stopScan()
                r15.writeNoException()
                return r9
            L_0x06f3:
                r14.enforceInterface(r2)
                r12.startScan()
                r15.writeNoException()
                return r9
            L_0x06fd:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                r12.startScanWithCallback(r0)
                r15.writeNoException()
                return r9
            L_0x070f:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                r12.setScanTimePeriod(r0)
                r15.writeNoException()
                return r9
            L_0x071d:
                r14.enforceInterface(r2)
                r12.clearWhiteList()
                r15.writeNoException()
                return r9
            L_0x0727:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0731
                r3 = 1
            L_0x0731:
                r12.updateWhiteList(r3)
                r15.writeNoException()
                return r9
            L_0x0738:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x074a
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                android.os.Bundle r0 = (android.os.Bundle) r0
                goto L_0x074b
            L_0x074a:
                r0 = r10
            L_0x074b:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setGlobalSettingLocale(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0760
                android.os.IBinder r10 = r0.asBinder()
            L_0x0760:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0764:
                r14.enforceInterface(r2)
                android.os.Bundle r0 = r12.getGlobalSettingLocale()
                r15.writeNoException()
                if (r0 == 0) goto L_0x0777
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x077a
            L_0x0777:
                r15.writeInt(r3)
            L_0x077a:
                return r9
            L_0x077b:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setGlobalSettingServerEnv(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0797
                android.os.IBinder r10 = r0.asBinder()
            L_0x0797:
                r15.writeStrongBinder(r10)
                return r9
            L_0x079b:
                r14.enforceInterface(r2)
                java.lang.String r0 = r12.getGlobalSettingServerEnv()
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x07a9:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x07bb
                android.os.Parcelable$Creator<com.xiaomi.smarthome.frame.server_compact.ServerBean> r0 = com.xiaomi.smarthome.frame.server_compact.ServerBean.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r0
                goto L_0x07bc
            L_0x07bb:
                r0 = r10
            L_0x07bc:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setGlobalSettingServer(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x07d1
                android.os.IBinder r10 = r0.asBinder()
            L_0x07d1:
                r15.writeStrongBinder(r10)
                return r9
            L_0x07d5:
                r14.enforceInterface(r2)
                com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = r12.getGlobalSettingServer()
                r15.writeNoException()
                if (r0 == 0) goto L_0x07e8
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x07eb
            L_0x07e8:
                r15.writeInt(r3)
            L_0x07eb:
                return r9
            L_0x07ec:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x07f7
                r0 = 1
                goto L_0x07f8
            L_0x07f7:
                r0 = 0
            L_0x07f8:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x07ff
                r3 = 1
            L_0x07ff:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setGlobalSettingCTA(r0, r3, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0814
                android.os.IBinder r10 = r0.asBinder()
            L_0x0814:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0818:
                r14.enforceInterface(r2)
                com.xiaomi.smarthome.core.entity.globaldynamicsetting.CTAInfo r0 = r12.getGlobalSettingCTA()
                r15.writeNoException()
                if (r0 == 0) goto L_0x082b
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x082e
            L_0x082b:
                r15.writeInt(r3)
            L_0x082e:
                return r9
            L_0x082f:
                r14.enforceInterface(r2)
                boolean r0 = r12.isServicePromoteSuccess()
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x083d:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                r12.localPing(r0, r1)
                r15.writeNoException()
                return r9
            L_0x0853:
                r14.enforceInterface(r2)
                java.util.ArrayList r0 = r14.createStringArrayList()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.updateDeviceBatch(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x086f
                android.os.IBinder r10 = r0.asBinder()
            L_0x086f:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0873:
                r14.enforceInterface(r2)
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam> r0 = com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam.CREATOR
                java.util.ArrayList r0 = r14.createTypedArrayList(r0)
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.batchRpcAsync(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0891
                android.os.IBinder r10 = r0.asBinder()
            L_0x0891:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0895:
                r14.enforceInterface(r2)
                java.util.ArrayList r0 = r14.createStringArrayList()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.delDeviceBatch(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x08b1
                android.os.IBinder r10 = r0.asBinder()
            L_0x08b1:
                r15.writeStrongBinder(r10)
                return r9
            L_0x08b5:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.renameDevice(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x08d5
                android.os.IBinder r10 = r0.asBinder()
            L_0x08d5:
                r15.writeStrongBinder(r10)
                return r9
            L_0x08d9:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.rpcAsyncToCloud(r0, r2, r3, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x08fd
                android.os.IBinder r10 = r0.asBinder()
            L_0x08fd:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0901:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.rpcAsyncToLocal(r0, r2, r3, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0925
                android.os.IBinder r10 = r0.asBinder()
            L_0x0925:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0929:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                java.lang.String r2 = r14.readString()
                java.lang.String r3 = r14.readString()
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.rpcAsync(r0, r2, r3, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x094d
                android.os.IBinder r10 = r0.asBinder()
            L_0x094d:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0951:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.getDeviceList(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0969
                android.os.IBinder r10 = r0.asBinder()
            L_0x0969:
                r15.writeStrongBinder(r10)
                return r9
            L_0x096d:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x097f
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.device.ScanType> r0 = com.xiaomi.smarthome.core.entity.device.ScanType.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.device.ScanType r0 = (com.xiaomi.smarthome.core.entity.device.ScanType) r0
                goto L_0x0980
            L_0x097f:
                r0 = r10
            L_0x0980:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.scanDeviceList(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0995
                android.os.IBinder r10 = r0.asBinder()
            L_0x0995:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0999:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x09ab
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.net.NetRequest> r0 = com.xiaomi.smarthome.core.entity.net.NetRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.net.NetRequest r0 = (com.xiaomi.smarthome.core.entity.net.NetRequest) r0
                goto L_0x09ac
            L_0x09ab:
                r0 = r10
            L_0x09ac:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.sendMiShopRequest(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x09c1
                android.os.IBinder r10 = r0.asBinder()
            L_0x09c1:
                r15.writeStrongBinder(r10)
                return r9
            L_0x09c5:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x09d7
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.net.NetRequest> r0 = com.xiaomi.smarthome.core.entity.net.NetRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.net.NetRequest r0 = (com.xiaomi.smarthome.core.entity.net.NetRequest) r0
                goto L_0x09d8
            L_0x09d7:
                r0 = r10
            L_0x09d8:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.sendMiRechargeRequest(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x09ed
                android.os.IBinder r10 = r0.asBinder()
            L_0x09ed:
                r15.writeStrongBinder(r10)
                return r9
            L_0x09f1:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0a03
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.net.NetRequest> r0 = com.xiaomi.smarthome.core.entity.net.NetRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.net.NetRequest r0 = (com.xiaomi.smarthome.core.entity.net.NetRequest) r0
                goto L_0x0a04
            L_0x0a03:
                r0 = r10
            L_0x0a04:
                java.lang.String r2 = r14.readString()
                int r4 = r14.readInt()
                if (r4 == 0) goto L_0x0a0f
                r3 = 1
            L_0x0a0f:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.sendRouterRequest(r0, r2, r3, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0a24
                android.os.IBinder r10 = r0.asBinder()
            L_0x0a24:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0a28:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0a3a
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.net.NetRequest> r0 = com.xiaomi.smarthome.core.entity.net.NetRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.net.NetRequest r0 = (com.xiaomi.smarthome.core.entity.net.NetRequest) r0
                goto L_0x0a3b
            L_0x0a3a:
                r0 = r10
            L_0x0a3b:
                int r2 = r14.readInt()
                if (r2 == 0) goto L_0x0a4a
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.net.Crypto> r2 = com.xiaomi.smarthome.core.entity.net.Crypto.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.net.Crypto r2 = (com.xiaomi.smarthome.core.entity.net.Crypto) r2
                goto L_0x0a4b
            L_0x0a4a:
                r2 = r10
            L_0x0a4b:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.sendSmartHomeRequest(r0, r2, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0a60
                android.os.IBinder r10 = r0.asBinder()
            L_0x0a60:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0a64:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                r12.clearMiServiceTokenInSystem(r0)
                r15.writeNoException()
                return r9
            L_0x0a72:
                r14.enforceInterface(r2)
                r12.clearAllMiServiceTokenInSystem()
                r15.writeNoException()
                return r9
            L_0x0a7c:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r0 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r0)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.clearAccount(r0)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0a94
                android.os.IBinder r10 = r0.asBinder()
            L_0x0a94:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0a98:
                r14.enforceInterface(r2)
                com.xiaomi.smarthome.core.entity.account.OAuthAccount r0 = r12.getOAuthAccount()
                r15.writeNoException()
                if (r0 == 0) goto L_0x0aab
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x0aae
            L_0x0aab:
                r15.writeInt(r3)
            L_0x0aae:
                return r9
            L_0x0aaf:
                r14.enforceInterface(r2)
                com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = r12.getMiAccount()
                r15.writeNoException()
                if (r0 == 0) goto L_0x0ac2
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x0ac5
            L_0x0ac2:
                r15.writeInt(r3)
            L_0x0ac5:
                return r9
            L_0x0ac6:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0ad8
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.account.OAuthAccount> r0 = com.xiaomi.smarthome.core.entity.account.OAuthAccount.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.smarthome.core.entity.account.OAuthAccount r0 = (com.xiaomi.smarthome.core.entity.account.OAuthAccount) r0
                goto L_0x0ad9
            L_0x0ad8:
                r0 = r10
            L_0x0ad9:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setOAuthAccount(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0aee
                android.os.IBinder r10 = r0.asBinder()
            L_0x0aee:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0af2:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0b04
                android.os.Parcelable$Creator<com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo> r0 = com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = (com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo) r0
                goto L_0x0b05
            L_0x0b04:
                r0 = r10
            L_0x0b05:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setMiServiceTokenTmp(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0b1a
                android.os.IBinder r10 = r0.asBinder()
            L_0x0b1a:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0b1e:
                r14.enforceInterface(r2)
                int r0 = r14.readInt()
                if (r0 == 0) goto L_0x0b30
                android.os.Parcelable$Creator<com.xiaomi.youpin.login.entity.account.LoginMiAccount> r0 = com.xiaomi.youpin.login.entity.account.LoginMiAccount.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r14)
                com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = (com.xiaomi.youpin.login.entity.account.LoginMiAccount) r0
                goto L_0x0b31
            L_0x0b30:
                r0 = r10
            L_0x0b31:
                android.os.IBinder r1 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientCallback r1 = com.xiaomi.smarthome.core.client.IClientCallback.Stub.asInterface(r1)
                com.xiaomi.smarthome.core.server.IServerHandle r0 = r12.setMiAccount(r0, r1)
                r15.writeNoException()
                if (r0 == 0) goto L_0x0b46
                android.os.IBinder r10 = r0.asBinder()
            L_0x0b46:
                r15.writeStrongBinder(r10)
                return r9
            L_0x0b4a:
                r14.enforceInterface(r2)
                java.lang.String r0 = r12.getMiId()
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x0b58:
                r14.enforceInterface(r2)
                boolean r0 = r12.isMiLoggedIn()
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x0b66:
                r14.enforceInterface(r2)
                java.lang.String r0 = r12.getHomeId()
                r15.writeNoException()
                r15.writeString(r0)
                return r9
            L_0x0b74:
                r14.enforceInterface(r2)
                com.xiaomi.smarthome.core.entity.account.AccountType r0 = r12.getAccountType()
                r15.writeNoException()
                if (r0 == 0) goto L_0x0b87
                r15.writeInt(r9)
                r0.writeToParcel(r15, r9)
                goto L_0x0b8a
            L_0x0b87:
                r15.writeInt(r3)
            L_0x0b8a:
                return r9
            L_0x0b8b:
                r14.enforceInterface(r2)
                boolean r0 = r12.isLoggedIn()
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x0b99:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                int r1 = r14.readInt()
                if (r1 == 0) goto L_0x0ba7
                r3 = 1
            L_0x0ba7:
                r12.updateBooleanValue(r0, r3)
                r15.writeNoException()
                return r9
            L_0x0bae:
                r14.enforceInterface(r2)
                java.lang.String r0 = r14.readString()
                boolean r0 = r12.isBooleanValue(r0)
                r15.writeNoException()
                r15.writeInt(r0)
                return r9
            L_0x0bc0:
                r14.enforceInterface(r2)
                r12.gc()
                r15.writeNoException()
                return r9
            L_0x0bca:
                r14.enforceInterface(r2)
                android.os.IBinder r0 = r14.readStrongBinder()
                com.xiaomi.smarthome.core.client.IClientApi r0 = com.xiaomi.smarthome.core.client.IClientApi.Stub.asInterface(r0)
                r12.registerClientApi(r0)
                r15.writeNoException()
                return r9
            L_0x0bdc:
                r15.writeString(r2)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.ICoreApi.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements ICoreApi {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void registerClientApi(IClientApi iClientApi) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientApi != null ? iClientApi.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void gc() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isBooleanValue(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateBooleanValue(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isLoggedIn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public AccountType getAccountType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? AccountType.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getHomeId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isMiLoggedIn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getMiId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setMiAccount(LoginMiAccount loginMiAccount, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (loginMiAccount != null) {
                        obtain.writeInt(1);
                        loginMiAccount.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setMiServiceTokenTmp(MiServiceTokenInfo miServiceTokenInfo, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (miServiceTokenInfo != null) {
                        obtain.writeInt(1);
                        miServiceTokenInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setOAuthAccount(OAuthAccount oAuthAccount, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (oAuthAccount != null) {
                        obtain.writeInt(1);
                        oAuthAccount.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public LoginMiAccount getMiAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? LoginMiAccount.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public OAuthAccount getOAuthAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? OAuthAccount.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle clearAccount(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearAllMiServiceTokenInSystem() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearMiServiceTokenInSystem(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle sendSmartHomeRequest(NetRequest netRequest, Crypto crypto, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (netRequest != null) {
                        obtain.writeInt(1);
                        netRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (crypto != null) {
                        obtain.writeInt(1);
                        crypto.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle sendRouterRequest(NetRequest netRequest, String str, boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (netRequest != null) {
                        obtain.writeInt(1);
                        netRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle sendMiRechargeRequest(NetRequest netRequest, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (netRequest != null) {
                        obtain.writeInt(1);
                        netRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle sendMiShopRequest(NetRequest netRequest, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (netRequest != null) {
                        obtain.writeInt(1);
                        netRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle scanDeviceList(ScanType scanType, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (scanType != null) {
                        obtain.writeInt(1);
                        scanType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle getDeviceList(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle rpcAsync(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle rpcAsyncToLocal(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle rpcAsyncToCloud(String str, String str2, String str3, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle renameDevice(String str, String str2, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle delDeviceBatch(List<String> list, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle batchRpcAsync(List<BatchRpcParam> list, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle updateDeviceBatch(List<String> list, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void localPing(String str, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isServicePromoteSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CTAInfo getGlobalSettingCTA() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? CTAInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setGlobalSettingCTA(boolean z, boolean z2, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ServerBean getGlobalSettingServer() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ServerBean.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setGlobalSettingServer(ServerBean serverBean, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (serverBean != null) {
                        obtain.writeInt(1);
                        serverBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getGlobalSettingServerEnv() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setGlobalSettingServerEnv(String str, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getGlobalSettingLocale() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setGlobalSettingLocale(Bundle bundle, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateWhiteList(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearWhiteList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setScanTimePeriod(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startScanWithCallback(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startScan() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopScan() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updatePluginConfig(boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearPluginConfig(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isPluginDevice(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public PluginRecord getPluginRecord(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? PluginRecord.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<PluginRecord> getPluginRecordList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PluginRecord.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getModelByProductId(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getProductIdByModel(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getModelBySSID(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isPluginForceUpdating(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void downloadPlugin(String str, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deletePlugin(String str, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void installPlugin(String str, boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateAllPlugin(boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(59, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updatePlugin(String str, boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void cancelPluginDownload(String str, PluginDownloadTask pluginDownloadTask, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (pluginDownloadTask != null) {
                        obtain.writeInt(1);
                        pluginDownloadTask.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle getPluginAutoUpdate(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(62, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle setPluginAutoUpdate(boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(63, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<PluginPackageInfo> getPluginInstalledPackageInfoList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PluginPackageInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public PluginPackageInfo getPluginInstalledPackageInfo(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(65, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? PluginPackageInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<PluginPackageInfo> getPluginDownloadedPackageInfoList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(66, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PluginPackageInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IServerHandle debugPluginPackage(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(67, obtain, obtain2, 0);
                    obtain2.readException();
                    return IServerHandle.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void dumpPlugin(IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(68, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void loadLocalPluginConfig() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(69, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public MiHomeMemoryFile getPluginRecordMemoryFile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(70, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MiHomeMemoryFile.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void installDebugRNPluginWithoutPackage(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(71, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addStatRecord(StatType statType, String str, String str2, String str3, String str4, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (statType != null) {
                        obtain.writeInt(1);
                        statType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(72, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean postStatRecord(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    boolean z2 = false;
                    this.mRemote.transact(73, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z2 = true;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String takeStatSession(long j, long j2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    this.mRemote.transact(74, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void uploadStat() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(75, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void searchBluetoothDevice(SearchRequest searchRequest, SearchResponse searchResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (searchRequest != null) {
                        obtain.writeInt(1);
                        searchRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(searchResponse != null ? searchResponse.asBinder() : null);
                    this.mRemote.transact(76, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopSearchBluetoothDevice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(77, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void callBluetoothApi(String str, int i, Bundle bundle, IBleResponse iBleResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBleResponse != null ? iBleResponse.asBinder() : null);
                    this.mRemote.transact(78, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler secureConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(79, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getBluetoothCache(String str, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(80, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setBluetoothCache(String str, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(81, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void searchMiioBluetoothDevice(SearchRequest searchRequest, IBleResponse iBleResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (searchRequest != null) {
                        obtain.writeInt(1);
                        searchRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBleResponse != null ? iBleResponse.asBinder() : null);
                    this.mRemote.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setAlertConfigs(String str, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    boolean z2 = false;
                    this.mRemote.transact(83, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z2 = true;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBleChannelWriter registerChannelReader(String str, IBleChannelReader iBleChannelReader) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBleChannelReader != null ? iBleChannelReader.asBinder() : null);
                    this.mRemote.transact(84, obtain, obtain2, 0);
                    obtain2.readException();
                    return IBleChannelWriter.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler securityChipConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(85, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler securityChipPincodeConnect(String str, String str2, String str3, int i, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(86, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler securityChipSharedDeviceConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(87, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler bleMeshBind(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(88, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler bleMeshConnect(String str, String str2, String str3, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(89, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startBleMeshUpgrade(String str, String str2, String str3, String str4, IBleMeshUpgradeResponse iBleMeshUpgradeResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeStrongBinder(iBleMeshUpgradeResponse != null ? iBleMeshUpgradeResponse.asBinder() : null);
                    this.mRemote.transact(90, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void cancelBleMeshUpgrade(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(91, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler bleStandardAuthBind(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(92, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ISecureConnectHandler bleStandardAuthConnect(String str, SecureConnectOptions secureConnectOptions, ISecureConnectResponse iSecureConnectResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (secureConnectOptions != null) {
                        obtain.writeInt(1);
                        secureConnectOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSecureConnectResponse != null ? iSecureConnectResponse.asBinder() : null);
                    this.mRemote.transact(93, obtain, obtain2, 0);
                    obtain2.readException();
                    return ISecureConnectHandler.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] miotBleEncryptSync(String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(94, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] miotBleDecryptSync(String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(95, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void callUPnPApi(UPnPRequest uPnPRequest, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uPnPRequest != null) {
                        obtain.writeInt(1);
                        uPnPRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(96, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getRootNodeValue(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(97, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void forceUpdateScene() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(98, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resetCore() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(99, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onActivityResume(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(100, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void applicationEnterForground() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(101, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void applicationEnterBackground() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(102, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void log(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(103, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void uploadLogFile(String str, String str2, String[] strArr, boolean z, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(104, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IClassicBtRequest getClassicBtRequestImpl() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(105, obtain, obtain2, 0);
                    obtain2.readException();
                    return IClassicBtRequest.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addClassicBtResponse(IClassicBtResponse iClassicBtResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iClassicBtResponse != null ? iClassicBtResponse.asBinder() : null);
                    this.mRemote.transact(106, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public HomeDeviceInfo getSharedHomeDeviceInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(107, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? HomeDeviceInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setCurrentHome(HomeDeviceInfo homeDeviceInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (homeDeviceInfo != null) {
                        obtain.writeInt(1);
                        homeDeviceInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(108, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void loadHomeDeviceList(long j, long j2, IClientCallback iClientCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeStrongBinder(iClientCallback != null ? iClientCallback.asBinder() : null);
                    this.mRemote.transact(109, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
