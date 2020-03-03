package com.xiaomi.router.miio.miioplugin;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.ad.api.IAdCallback;
import com.xiaomi.smarthome.device.api.IBleCallback;
import com.xiaomi.smarthome.device.api.IRecommendSceneItemCallback;
import com.xiaomi.smarthome.device.api.ISceneCallback;
import com.xiaomi.smarthome.device.api.ISceneInfoCallback;
import com.xiaomi.smarthome.device.api.SceneInfo;
import java.util.List;

public interface IPluginRequest extends IInterface {
    void addDeviceToMain(String str, String str2, IPluginCallback iPluginCallback) throws RemoteException;

    void addRecord(String str, String str2) throws RemoteException;

    void addRoom(RoomStatus roomStatus, IPluginCallbackRoomStatus iPluginCallbackRoomStatus) throws RemoteException;

    void addTag(String str, String str2) throws RemoteException;

    void addToLauncher(String str, Intent intent) throws RemoteException;

    void bleMeshConnect(String str, String str2, IBleCallback iBleCallback) throws RemoteException;

    void bleStandardAuthConnect(String str, IBleCallback iBleCallback) throws RemoteException;

    void callBleApi(String str, int i, Bundle bundle, IBleCallback iBleCallback) throws RemoteException;

    void callRemoteAsync(String[] strArr, int i, String str, IPluginCallback iPluginCallback, IPluginCallback iPluginCallback2) throws RemoteException;

    boolean checkIfSupportVoiceCtrl(String str) throws RemoteException;

    boolean checkIfVoiceCtrlAuthorizedExpired(String str) throws RemoteException;

    void checkLocalRouterInfo(String str, IPluginCallback iPluginCallback) throws RemoteException;

    boolean checkVoiceCtrlAuthorized(String str) throws RemoteException;

    void closeCameraFloatingWindow(String str) throws RemoteException;

    void connectBand(String str, IPluginCallback iPluginCallback) throws RemoteException;

    void deInitBandManager() throws RemoteException;

    void delDeviceBatch(List<String> list, IPluginCallback iPluginCallback) throws RemoteException;

    void deleteCard(String str, IPluginCallback iPluginCallback) throws RemoteException;

    void deleteRooms(List<String> list, IPluginCallback2 iPluginCallback2) throws RemoteException;

    String getAccountId() throws RemoteException;

    void getAllCards(IPluginCallback iPluginCallback) throws RemoteException;

    int getApiLevel() throws RemoteException;

    List<DeviceStatus> getBleGatewayDeviceList() throws RemoteException;

    void getBleGatewaySubDevices(List<String> list, IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException;

    void getDefaultCardAndActivateInfo(IPluginCallback iPluginCallback) throws RemoteException;

    DeviceStatus getDevice(String str) throws RemoteException;

    List<DeviceStatus> getDeviceList() throws RemoteException;

    List<DeviceStatus> getDeviceListV2(List<String> list) throws RemoteException;

    String getDevicePincode(String str) throws RemoteException;

    String getDevicePropByDid(String str) throws RemoteException;

    void getDeviceRealIconByModel(String str, IPluginCallback3 iPluginCallback3) throws RemoteException;

    void getDeviceStatus(String str, DeviceStatus deviceStatus) throws RemoteException;

    DeviceTagInfo getDeviceTagInfo(String str) throws RemoteException;

    List<DeviceStatus> getFilterBluetoothDeviceList(String str) throws RemoteException;

    Location getLastLocation() throws RemoteException;

    String getLightDeviceGroupModel(String str) throws RemoteException;

    int getMainProcessId() throws RemoteException;

    String getMediaButtonModel() throws RemoteException;

    String getPluginProcessName(int i, String str) throws RemoteException;

    List<String> getRecommendTags(String str) throws RemoteException;

    List<RoomStatus> getRoomAll() throws RemoteException;

    String getRouterFileDownloadUrl(String str) throws RemoteException;

    List<SceneInfo> getSceneByDid(String str) throws RemoteException;

    String getSpecInstanceStr(String str) throws RemoteException;

    String getSpecProptyValueFromSpecCard(String str) throws RemoteException;

    List<DeviceStatus> getSubDeviceByParentDid(String str) throws RemoteException;

    int getUsePreviewConfig() throws RemoteException;

    void getUserInfo(String str, IPluginCallbackUserInfo iPluginCallbackUserInfo) throws RemoteException;

    String getVirtualGroupStatus(String str) throws RemoteException;

    void getWatchControllableDevices(String str, IPluginCallback iPluginCallback) throws RemoteException;

    void gotoPage(Uri uri) throws RemoteException;

    boolean hasSceneOnline(String str, String str2) throws RemoteException;

    void initBandManager(String str, String str2, IPluginCallback iPluginCallback) throws RemoteException;

    void isBleGatewayExistInDeviceList(IBleCallback iBleCallback) throws RemoteException;

    boolean isGPSLocationEnable() throws RemoteException;

    boolean isLocalMiRouter() throws RemoteException;

    boolean isNetworkLocationEnabled() throws RemoteException;

    boolean isNotificationBarOpBtnEnabled(String str, String str2) throws RemoteException;

    boolean isUsrExpPlanEnabled(String str) throws RemoteException;

    void issueDoorCard(IPluginCallback iPluginCallback) throws RemoteException;

    void loadBitmap(String str, IPluginCallback3 iPluginCallback3) throws RemoteException;

    void loadRecommendScenes(String str, IRecommendSceneItemCallback iRecommendSceneItemCallback) throws RemoteException;

    void loadUrl(String str, String str2) throws RemoteException;

    void loadWebView(String str, String str2) throws RemoteException;

    void log(String str, String str2) throws RemoteException;

    void logByModel(String str, String str2) throws RemoteException;

    void modDeviceName(String str, String str2, IPluginCallback iPluginCallback) throws RemoteException;

    void notifyBluetoothBinded(String str, String str2) throws RemoteException;

    void openCameraFloatingWindow(String str) throws RemoteException;

    void queryAd(String str, String str2, IAdCallback iAdCallback) throws RemoteException;

    void recordCalculateEvent(String str, String str2, long j) throws RemoteException;

    void recordCountEvent(String str, String str2) throws RemoteException;

    void recordNumericPropertyEvent(String str, String str2, long j) throws RemoteException;

    void recordStringPropertyEvent(String str, String str2, String str3) throws RemoteException;

    void refreshDeviceListUi() throws RemoteException;

    void refreshScene(String str, ISceneCallback iSceneCallback) throws RemoteException;

    void registerMediaButtonReceiver(String str) throws RemoteException;

    void removeTag(String str) throws RemoteException;

    void renameBluetoothDevice(String str, String str2) throws RemoteException;

    void reportAdClick() throws RemoteException;

    void reportAdClose(Advertisement advertisement) throws RemoteException;

    void reportAdShow(Advertisement advertisement) throws RemoteException;

    void reportHotSpotShow() throws RemoteException;

    void requestLocation(ILocationCallback iLocationCallback) throws RemoteException;

    void requestPermission(String[] strArr, IPluginCallback iPluginCallback) throws RemoteException;

    void reverseGeo(double d, double d2, IPluginCallback2 iPluginCallback2) throws RemoteException;

    void roomRename(String str, String str2, IPluginCallback2 iPluginCallback2) throws RemoteException;

    void secureConnect(String str, IBleCallback iBleCallback) throws RemoteException;

    void securityChipConnect(String str, IBleCallback iBleCallback) throws RemoteException;

    void securityChipSharedDeviceConnect(String str, IBleCallback iBleCallback) throws RemoteException;

    void sendMessage(String str, int i, Intent intent, IPluginCallback2 iPluginCallback2) throws RemoteException;

    void sendPluginAdRequest(String str, String str2) throws RemoteException;

    void setBleDeviceSubtitle(String str, String str2) throws RemoteException;

    void setDefaultCard(String str, IPluginCallback iPluginCallback) throws RemoteException;

    void setSubDeviceShownMode(String str, boolean z, IPluginCallback iPluginCallback) throws RemoteException;

    void setUsrExpPlanEnabled(String str, boolean z) throws RemoteException;

    void startSearchNewDevice(String str, IPluginCallback2 iPluginCallback2) throws RemoteException;

    void stopPluginAd(String str) throws RemoteException;

    void subscribeDevice(String str, int i, List<String> list, int i2, IPluginCallback iPluginCallback) throws RemoteException;

    void subscribeDeviceV2(String str, int i, List<String> list, int i2, IPluginCallback2 iPluginCallback2) throws RemoteException;

    void unBindDevice(String str, int i, IPluginCallback iPluginCallback) throws RemoteException;

    void unRegisterMediaButtonReceiver(String str) throws RemoteException;

    void unsubscribeDevice(String str, int i, List<String> list, IPluginCallback iPluginCallback) throws RemoteException;

    void unsubscribeDeviceV2(String str, int i, List<String> list, String str2, IPluginCallback2 iPluginCallback2) throws RemoteException;

    void updateCard(String str, IPluginCallback iPluginCallback) throws RemoteException;

    void updateDevice(List<String> list, IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException;

    void updateDeviceList(IPluginCallback iPluginCallback) throws RemoteException;

    void updateDeviceProperties(String str, String str2) throws RemoteException;

    void updateDeviceStatus(DeviceStatus deviceStatus, IPluginCallback iPluginCallback) throws RemoteException;

    void updateScene(String str, IPluginCallback iPluginCallback) throws RemoteException;

    void updateSceneItem(SceneInfo sceneInfo, ISceneInfoCallback iSceneInfoCallback) throws RemoteException;

    void updateSubDevice(String[] strArr, IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException;

    void visualSecureBind(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IPluginRequest {
        private static final String DESCRIPTOR = "com.xiaomi.router.miio.miioplugin.IPluginRequest";
        static final int TRANSACTION_addDeviceToMain = 92;
        static final int TRANSACTION_addRecord = 26;
        static final int TRANSACTION_addRoom = 9;
        static final int TRANSACTION_addTag = 53;
        static final int TRANSACTION_addToLauncher = 32;
        static final int TRANSACTION_bleMeshConnect = 97;
        static final int TRANSACTION_bleStandardAuthConnect = 112;
        static final int TRANSACTION_callBleApi = 65;
        static final int TRANSACTION_callRemoteAsync = 40;
        static final int TRANSACTION_checkIfSupportVoiceCtrl = 85;
        static final int TRANSACTION_checkIfVoiceCtrlAuthorizedExpired = 70;
        static final int TRANSACTION_checkLocalRouterInfo = 34;
        static final int TRANSACTION_checkVoiceCtrlAuthorized = 69;
        static final int TRANSACTION_closeCameraFloatingWindow = 82;
        static final int TRANSACTION_connectBand = 104;
        static final int TRANSACTION_deInitBandManager = 103;
        static final int TRANSACTION_delDeviceBatch = 80;
        static final int TRANSACTION_deleteCard = 107;
        static final int TRANSACTION_deleteRooms = 7;
        static final int TRANSACTION_getAccountId = 17;
        static final int TRANSACTION_getAllCards = 105;
        static final int TRANSACTION_getApiLevel = 1;
        static final int TRANSACTION_getBleGatewayDeviceList = 96;
        static final int TRANSACTION_getBleGatewaySubDevices = 101;
        static final int TRANSACTION_getDefaultCardAndActivateInfo = 110;
        static final int TRANSACTION_getDevice = 50;
        static final int TRANSACTION_getDeviceList = 4;
        static final int TRANSACTION_getDeviceListV2 = 5;
        static final int TRANSACTION_getDevicePincode = 68;
        static final int TRANSACTION_getDevicePropByDid = 83;
        static final int TRANSACTION_getDeviceRealIconByModel = 79;
        static final int TRANSACTION_getDeviceStatus = 3;
        static final int TRANSACTION_getDeviceTagInfo = 51;
        static final int TRANSACTION_getFilterBluetoothDeviceList = 95;
        static final int TRANSACTION_getLastLocation = 18;
        static final int TRANSACTION_getLightDeviceGroupModel = 52;
        static final int TRANSACTION_getMainProcessId = 57;
        static final int TRANSACTION_getMediaButtonModel = 62;
        static final int TRANSACTION_getPluginProcessName = 67;
        static final int TRANSACTION_getRecommendTags = 55;
        static final int TRANSACTION_getRoomAll = 6;
        static final int TRANSACTION_getRouterFileDownloadUrl = 33;
        static final int TRANSACTION_getSceneByDid = 11;
        static final int TRANSACTION_getSpecInstanceStr = 99;
        static final int TRANSACTION_getSpecProptyValueFromSpecCard = 100;
        static final int TRANSACTION_getSubDeviceByParentDid = 10;
        static final int TRANSACTION_getUsePreviewConfig = 111;
        static final int TRANSACTION_getUserInfo = 38;
        static final int TRANSACTION_getVirtualGroupStatus = 98;
        static final int TRANSACTION_getWatchControllableDevices = 84;
        static final int TRANSACTION_gotoPage = 36;
        static final int TRANSACTION_hasSceneOnline = 48;
        static final int TRANSACTION_initBandManager = 102;
        static final int TRANSACTION_isBleGatewayExistInDeviceList = 90;
        static final int TRANSACTION_isGPSLocationEnable = 20;
        static final int TRANSACTION_isLocalMiRouter = 35;
        static final int TRANSACTION_isNetworkLocationEnabled = 19;
        static final int TRANSACTION_isNotificationBarOpBtnEnabled = 56;
        static final int TRANSACTION_isUsrExpPlanEnabled = 93;
        static final int TRANSACTION_issueDoorCard = 106;
        static final int TRANSACTION_loadBitmap = 71;
        static final int TRANSACTION_loadRecommendScenes = 45;
        static final int TRANSACTION_loadUrl = 44;
        static final int TRANSACTION_loadWebView = 43;
        static final int TRANSACTION_log = 31;
        static final int TRANSACTION_logByModel = 88;
        static final int TRANSACTION_modDeviceName = 27;
        static final int TRANSACTION_notifyBluetoothBinded = 42;
        static final int TRANSACTION_openCameraFloatingWindow = 81;
        static final int TRANSACTION_queryAd = 74;
        static final int TRANSACTION_recordCalculateEvent = 23;
        static final int TRANSACTION_recordCountEvent = 22;
        static final int TRANSACTION_recordNumericPropertyEvent = 25;
        static final int TRANSACTION_recordStringPropertyEvent = 24;
        static final int TRANSACTION_refreshDeviceListUi = 113;
        static final int TRANSACTION_refreshScene = 12;
        static final int TRANSACTION_registerMediaButtonReceiver = 60;
        static final int TRANSACTION_removeTag = 54;
        static final int TRANSACTION_renameBluetoothDevice = 58;
        static final int TRANSACTION_reportAdClick = 76;
        static final int TRANSACTION_reportAdClose = 77;
        static final int TRANSACTION_reportAdShow = 75;
        static final int TRANSACTION_reportHotSpotShow = 78;
        static final int TRANSACTION_requestLocation = 21;
        static final int TRANSACTION_requestPermission = 91;
        static final int TRANSACTION_reverseGeo = 89;
        static final int TRANSACTION_roomRename = 8;
        static final int TRANSACTION_secureConnect = 64;
        static final int TRANSACTION_securityChipConnect = 86;
        static final int TRANSACTION_securityChipSharedDeviceConnect = 87;
        static final int TRANSACTION_sendMessage = 37;
        static final int TRANSACTION_sendPluginAdRequest = 72;
        static final int TRANSACTION_setBleDeviceSubtitle = 59;
        static final int TRANSACTION_setDefaultCard = 108;
        static final int TRANSACTION_setSubDeviceShownMode = 49;
        static final int TRANSACTION_setUsrExpPlanEnabled = 94;
        static final int TRANSACTION_startSearchNewDevice = 41;
        static final int TRANSACTION_stopPluginAd = 73;
        static final int TRANSACTION_subscribeDevice = 13;
        static final int TRANSACTION_subscribeDeviceV2 = 15;
        static final int TRANSACTION_unBindDevice = 28;
        static final int TRANSACTION_unRegisterMediaButtonReceiver = 61;
        static final int TRANSACTION_unsubscribeDevice = 14;
        static final int TRANSACTION_unsubscribeDeviceV2 = 16;
        static final int TRANSACTION_updateCard = 109;
        static final int TRANSACTION_updateDevice = 66;
        static final int TRANSACTION_updateDeviceList = 29;
        static final int TRANSACTION_updateDeviceProperties = 30;
        static final int TRANSACTION_updateDeviceStatus = 2;
        static final int TRANSACTION_updateScene = 47;
        static final int TRANSACTION_updateSceneItem = 46;
        static final int TRANSACTION_updateSubDevice = 39;
        static final int TRANSACTION_visualSecureBind = 63;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPluginRequest asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPluginRequest)) {
                return new Proxy(iBinder);
            }
            return (IPluginRequest) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: com.xiaomi.router.miio.miioplugin.DeviceStatus} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: com.xiaomi.router.miio.miioplugin.RoomStatus} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v24, resolved type: com.xiaomi.smarthome.device.api.SceneInfo} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: com.xiaomi.smarthome.ad.api.Advertisement} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v34, resolved type: com.xiaomi.smarthome.ad.api.Advertisement} */
        /* JADX WARNING: type inference failed for: r4v0 */
        /* JADX WARNING: type inference failed for: r4v13, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r4v19, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r4v38 */
        /* JADX WARNING: type inference failed for: r4v39 */
        /* JADX WARNING: type inference failed for: r4v40 */
        /* JADX WARNING: type inference failed for: r4v41 */
        /* JADX WARNING: type inference failed for: r4v42 */
        /* JADX WARNING: type inference failed for: r4v43 */
        /* JADX WARNING: type inference failed for: r4v44 */
        /* JADX WARNING: type inference failed for: r4v45 */
        /* JADX WARNING: type inference failed for: r4v46 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
            /*
                r8 = this;
                java.lang.String r2 = "com.xiaomi.router.miio.miioplugin.IPluginRequest"
                r3 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r6 = 1
                if (r9 == r3) goto L_0x096e
                r3 = 0
                r4 = 0
                switch(r9) {
                    case 1: goto L_0x0960;
                    case 2: goto L_0x093f;
                    case 3: goto L_0x0926;
                    case 4: goto L_0x0918;
                    case 5: goto L_0x0906;
                    case 6: goto L_0x08f8;
                    case 7: goto L_0x08e2;
                    case 8: goto L_0x08c8;
                    case 9: goto L_0x08a7;
                    case 10: goto L_0x0895;
                    case 11: goto L_0x0883;
                    case 12: goto L_0x086d;
                    case 13: goto L_0x0845;
                    case 14: goto L_0x0827;
                    case 15: goto L_0x07ff;
                    case 16: goto L_0x07d7;
                    case 17: goto L_0x07c9;
                    case 18: goto L_0x07b2;
                    case 19: goto L_0x07a4;
                    case 20: goto L_0x0796;
                    case 21: goto L_0x0784;
                    case 22: goto L_0x0772;
                    case 23: goto L_0x075c;
                    case 24: goto L_0x0746;
                    case 25: goto L_0x0730;
                    case 26: goto L_0x071e;
                    case 27: goto L_0x0704;
                    case 28: goto L_0x06ea;
                    case 29: goto L_0x06d8;
                    case 30: goto L_0x06c6;
                    case 31: goto L_0x06b4;
                    case 32: goto L_0x0697;
                    case 33: goto L_0x0685;
                    case 34: goto L_0x066f;
                    case 35: goto L_0x0661;
                    case 36: goto L_0x0648;
                    case 37: goto L_0x061f;
                    case 38: goto L_0x0609;
                    case 39: goto L_0x05f3;
                    case 40: goto L_0x05c7;
                    case 41: goto L_0x05b1;
                    case 42: goto L_0x059f;
                    case 43: goto L_0x058d;
                    case 44: goto L_0x057b;
                    case 45: goto L_0x0565;
                    case 46: goto L_0x0544;
                    case 47: goto L_0x052e;
                    case 48: goto L_0x0518;
                    case 49: goto L_0x04fb;
                    case 50: goto L_0x04e0;
                    case 51: goto L_0x04c5;
                    case 52: goto L_0x04b3;
                    case 53: goto L_0x04a1;
                    case 54: goto L_0x0493;
                    case 55: goto L_0x0481;
                    case 56: goto L_0x046b;
                    case 57: goto L_0x045d;
                    case 58: goto L_0x044b;
                    case 59: goto L_0x0439;
                    case 60: goto L_0x042b;
                    case 61: goto L_0x041d;
                    case 62: goto L_0x040f;
                    case 63: goto L_0x0401;
                    case 64: goto L_0x03eb;
                    case 65: goto L_0x03b7;
                    case 66: goto L_0x03a1;
                    case 67: goto L_0x038b;
                    case 68: goto L_0x0379;
                    case 69: goto L_0x0367;
                    case 70: goto L_0x0355;
                    case 71: goto L_0x033f;
                    case 72: goto L_0x032d;
                    case 73: goto L_0x031f;
                    case 74: goto L_0x0305;
                    case 75: goto L_0x02ec;
                    case 76: goto L_0x02e2;
                    case 77: goto L_0x02c9;
                    case 78: goto L_0x02bf;
                    case 79: goto L_0x02a9;
                    case 80: goto L_0x0293;
                    case 81: goto L_0x0285;
                    case 82: goto L_0x0277;
                    case 83: goto L_0x0265;
                    case 84: goto L_0x024f;
                    case 85: goto L_0x023d;
                    case 86: goto L_0x0227;
                    case 87: goto L_0x0211;
                    case 88: goto L_0x01ff;
                    case 89: goto L_0x01e1;
                    case 90: goto L_0x01cf;
                    case 91: goto L_0x01b9;
                    case 92: goto L_0x019f;
                    case 93: goto L_0x018d;
                    case 94: goto L_0x0178;
                    case 95: goto L_0x0166;
                    case 96: goto L_0x0158;
                    case 97: goto L_0x013e;
                    case 98: goto L_0x012c;
                    case 99: goto L_0x011a;
                    case 100: goto L_0x0108;
                    case 101: goto L_0x00f2;
                    case 102: goto L_0x00d8;
                    case 103: goto L_0x00ce;
                    case 104: goto L_0x00b8;
                    case 105: goto L_0x00a6;
                    case 106: goto L_0x0094;
                    case 107: goto L_0x007e;
                    case 108: goto L_0x0068;
                    case 109: goto L_0x0052;
                    case 110: goto L_0x0040;
                    case 111: goto L_0x0032;
                    case 112: goto L_0x001c;
                    case 113: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r0 = super.onTransact(r9, r10, r11, r12)
                return r0
            L_0x0012:
                r10.enforceInterface(r2)
                r8.refreshDeviceListUi()
                r11.writeNoException()
                return r6
            L_0x001c:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r1 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r1)
                r8.bleStandardAuthConnect(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0032:
                r10.enforceInterface(r2)
                int r0 = r8.getUsePreviewConfig()
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x0040:
                r10.enforceInterface(r2)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r0 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r8.getDefaultCardAndActivateInfo(r0)
                r11.writeNoException()
                return r6
            L_0x0052:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.updateCard(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0068:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.setDefaultCard(r0, r1)
                r11.writeNoException()
                return r6
            L_0x007e:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.deleteCard(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0094:
                r10.enforceInterface(r2)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r0 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r8.issueDoorCard(r0)
                r11.writeNoException()
                return r6
            L_0x00a6:
                r10.enforceInterface(r2)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r0 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r8.getAllCards(r0)
                r11.writeNoException()
                return r6
            L_0x00b8:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.connectBand(r0, r1)
                r11.writeNoException()
                return r6
            L_0x00ce:
                r10.enforceInterface(r2)
                r8.deInitBandManager()
                r11.writeNoException()
                return r6
            L_0x00d8:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.initBandManager(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x00f2:
                r10.enforceInterface(r2)
                java.util.ArrayList r0 = r10.createStringArrayList()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList r1 = com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList.Stub.asInterface(r1)
                r8.getBleGatewaySubDevices(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0108:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getSpecProptyValueFromSpecCard(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x011a:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getSpecInstanceStr(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x012c:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getVirtualGroupStatus(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x013e:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r1 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r1)
                r8.bleMeshConnect(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x0158:
                r10.enforceInterface(r2)
                java.util.List r0 = r8.getBleGatewayDeviceList()
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x0166:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.util.List r0 = r8.getFilterBluetoothDeviceList(r0)
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x0178:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x0186
                r3 = 1
            L_0x0186:
                r8.setUsrExpPlanEnabled(r0, r3)
                r11.writeNoException()
                return r6
            L_0x018d:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                boolean r0 = r8.isUsrExpPlanEnabled(r0)
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x019f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.addDeviceToMain(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x01b9:
                r10.enforceInterface(r2)
                java.lang.String[] r0 = r10.createStringArray()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.requestPermission(r0, r1)
                r11.writeNoException()
                return r6
            L_0x01cf:
                r10.enforceInterface(r2)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r0 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r0)
                r8.isBleGatewayExistInDeviceList(r0)
                r11.writeNoException()
                return r6
            L_0x01e1:
                r10.enforceInterface(r2)
                double r2 = r10.readDouble()
                double r4 = r10.readDouble()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r7 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r0)
                r0 = r8
                r1 = r2
                r3 = r4
                r5 = r7
                r0.reverseGeo(r1, r3, r5)
                r11.writeNoException()
                return r6
            L_0x01ff:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.logByModel(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0211:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r1 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r1)
                r8.securityChipSharedDeviceConnect(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0227:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r1 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r1)
                r8.securityChipConnect(r0, r1)
                r11.writeNoException()
                return r6
            L_0x023d:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                boolean r0 = r8.checkIfSupportVoiceCtrl(r0)
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x024f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.getWatchControllableDevices(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0265:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getDevicePropByDid(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x0277:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.closeCameraFloatingWindow(r0)
                r11.writeNoException()
                return r6
            L_0x0285:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.openCameraFloatingWindow(r0)
                r11.writeNoException()
                return r6
            L_0x0293:
                r10.enforceInterface(r2)
                java.util.ArrayList r0 = r10.createStringArrayList()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.delDeviceBatch(r0, r1)
                r11.writeNoException()
                return r6
            L_0x02a9:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback3 r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback3.Stub.asInterface(r1)
                r8.getDeviceRealIconByModel(r0, r1)
                r11.writeNoException()
                return r6
            L_0x02bf:
                r10.enforceInterface(r2)
                r8.reportHotSpotShow()
                r11.writeNoException()
                return r6
            L_0x02c9:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x02db
                android.os.Parcelable$Creator<com.xiaomi.smarthome.ad.api.Advertisement> r0 = com.xiaomi.smarthome.ad.api.Advertisement.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                r4 = r0
                com.xiaomi.smarthome.ad.api.Advertisement r4 = (com.xiaomi.smarthome.ad.api.Advertisement) r4
            L_0x02db:
                r8.reportAdClose(r4)
                r11.writeNoException()
                return r6
            L_0x02e2:
                r10.enforceInterface(r2)
                r8.reportAdClick()
                r11.writeNoException()
                return r6
            L_0x02ec:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x02fe
                android.os.Parcelable$Creator<com.xiaomi.smarthome.ad.api.Advertisement> r0 = com.xiaomi.smarthome.ad.api.Advertisement.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                r4 = r0
                com.xiaomi.smarthome.ad.api.Advertisement r4 = (com.xiaomi.smarthome.ad.api.Advertisement) r4
            L_0x02fe:
                r8.reportAdShow(r4)
                r11.writeNoException()
                return r6
            L_0x0305:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.ad.api.IAdCallback r1 = com.xiaomi.smarthome.ad.api.IAdCallback.Stub.asInterface(r1)
                r8.queryAd(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x031f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.stopPluginAd(r0)
                r11.writeNoException()
                return r6
            L_0x032d:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.sendPluginAdRequest(r0, r1)
                r11.writeNoException()
                return r6
            L_0x033f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback3 r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback3.Stub.asInterface(r1)
                r8.loadBitmap(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0355:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                boolean r0 = r8.checkIfVoiceCtrlAuthorizedExpired(r0)
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x0367:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                boolean r0 = r8.checkVoiceCtrlAuthorized(r0)
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x0379:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getDevicePincode(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x038b:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                java.lang.String r1 = r10.readString()
                java.lang.String r0 = r8.getPluginProcessName(r0, r1)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x03a1:
                r10.enforceInterface(r2)
                java.util.ArrayList r0 = r10.createStringArrayList()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList r1 = com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList.Stub.asInterface(r1)
                r8.updateDevice(r0, r1)
                r11.writeNoException()
                return r6
            L_0x03b7:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r2 = r10.readInt()
                int r5 = r10.readInt()
                if (r5 == 0) goto L_0x03d0
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r10)
                android.os.Bundle r4 = (android.os.Bundle) r4
            L_0x03d0:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r1 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r1)
                r8.callBleApi(r0, r2, r4, r1)
                r11.writeNoException()
                if (r4 == 0) goto L_0x03e7
                r11.writeInt(r6)
                r4.writeToParcel(r11, r6)
                goto L_0x03ea
            L_0x03e7:
                r11.writeInt(r3)
            L_0x03ea:
                return r6
            L_0x03eb:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IBleCallback r1 = com.xiaomi.smarthome.device.api.IBleCallback.Stub.asInterface(r1)
                r8.secureConnect(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0401:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.visualSecureBind(r0)
                r11.writeNoException()
                return r6
            L_0x040f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r8.getMediaButtonModel()
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x041d:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.unRegisterMediaButtonReceiver(r0)
                r11.writeNoException()
                return r6
            L_0x042b:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.registerMediaButtonReceiver(r0)
                r11.writeNoException()
                return r6
            L_0x0439:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.setBleDeviceSubtitle(r0, r1)
                r11.writeNoException()
                return r6
            L_0x044b:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.renameBluetoothDevice(r0, r1)
                r11.writeNoException()
                return r6
            L_0x045d:
                r10.enforceInterface(r2)
                int r0 = r8.getMainProcessId()
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x046b:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                boolean r0 = r8.isNotificationBarOpBtnEnabled(r0, r1)
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x0481:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.util.List r0 = r8.getRecommendTags(r0)
                r11.writeNoException()
                r11.writeStringList(r0)
                return r6
            L_0x0493:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                r8.removeTag(r0)
                r11.writeNoException()
                return r6
            L_0x04a1:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.addTag(r0, r1)
                r11.writeNoException()
                return r6
            L_0x04b3:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getLightDeviceGroupModel(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x04c5:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                com.xiaomi.router.miio.miioplugin.DeviceTagInfo r0 = r8.getDeviceTagInfo(r0)
                r11.writeNoException()
                if (r0 == 0) goto L_0x04dc
                r11.writeInt(r6)
                r0.writeToParcel(r11, r6)
                goto L_0x04df
            L_0x04dc:
                r11.writeInt(r3)
            L_0x04df:
                return r6
            L_0x04e0:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                com.xiaomi.router.miio.miioplugin.DeviceStatus r0 = r8.getDevice(r0)
                r11.writeNoException()
                if (r0 == 0) goto L_0x04f7
                r11.writeInt(r6)
                r0.writeToParcel(r11, r6)
                goto L_0x04fa
            L_0x04f7:
                r11.writeInt(r3)
            L_0x04fa:
                return r6
            L_0x04fb:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0509
                r3 = 1
            L_0x0509:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.setSubDeviceShownMode(r0, r3, r1)
                r11.writeNoException()
                return r6
            L_0x0518:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                boolean r0 = r8.hasSceneOnline(r0, r1)
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x052e:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.updateScene(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0544:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0556
                android.os.Parcelable$Creator<com.xiaomi.smarthome.device.api.SceneInfo> r0 = com.xiaomi.smarthome.device.api.SceneInfo.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                r4 = r0
                com.xiaomi.smarthome.device.api.SceneInfo r4 = (com.xiaomi.smarthome.device.api.SceneInfo) r4
            L_0x0556:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.ISceneInfoCallback r0 = com.xiaomi.smarthome.device.api.ISceneInfoCallback.Stub.asInterface(r0)
                r8.updateSceneItem(r4, r0)
                r11.writeNoException()
                return r6
            L_0x0565:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.IRecommendSceneItemCallback r1 = com.xiaomi.smarthome.device.api.IRecommendSceneItemCallback.Stub.asInterface(r1)
                r8.loadRecommendScenes(r0, r1)
                r11.writeNoException()
                return r6
            L_0x057b:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.loadUrl(r0, r1)
                r11.writeNoException()
                return r6
            L_0x058d:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.loadWebView(r0, r1)
                r11.writeNoException()
                return r6
            L_0x059f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.notifyBluetoothBinded(r0, r1)
                r11.writeNoException()
                return r6
            L_0x05b1:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r1)
                r8.startSearchNewDevice(r0, r1)
                r11.writeNoException()
                return r6
            L_0x05c7:
                r10.enforceInterface(r2)
                java.lang.String[] r2 = r10.createStringArray()
                int r3 = r10.readInt()
                java.lang.String r4 = r10.readString()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r5 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r7 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r0 = r8
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r7
                r0.callRemoteAsync(r1, r2, r3, r4, r5)
                r11.writeNoException()
                return r6
            L_0x05f3:
                r10.enforceInterface(r2)
                java.lang.String[] r0 = r10.createStringArray()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList r1 = com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList.Stub.asInterface(r1)
                r8.updateSubDevice(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0609:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallbackUserInfo r1 = com.xiaomi.router.miio.miioplugin.IPluginCallbackUserInfo.Stub.asInterface(r1)
                r8.getUserInfo(r0, r1)
                r11.writeNoException()
                return r6
            L_0x061f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r2 = r10.readInt()
                int r3 = r10.readInt()
                if (r3 == 0) goto L_0x0639
                android.os.Parcelable$Creator r3 = android.content.Intent.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r10)
                r4 = r3
                android.content.Intent r4 = (android.content.Intent) r4
            L_0x0639:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r1)
                r8.sendMessage(r0, r2, r4, r1)
                r11.writeNoException()
                return r6
            L_0x0648:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x065a
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                r4 = r0
                android.net.Uri r4 = (android.net.Uri) r4
            L_0x065a:
                r8.gotoPage(r4)
                r11.writeNoException()
                return r6
            L_0x0661:
                r10.enforceInterface(r2)
                boolean r0 = r8.isLocalMiRouter()
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x066f:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.checkLocalRouterInfo(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0685:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r0 = r8.getRouterFileDownloadUrl(r0)
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x0697:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x06ad
                android.os.Parcelable$Creator r2 = android.content.Intent.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r10)
                r4 = r1
                android.content.Intent r4 = (android.content.Intent) r4
            L_0x06ad:
                r8.addToLauncher(r0, r4)
                r11.writeNoException()
                return r6
            L_0x06b4:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.log(r0, r1)
                r11.writeNoException()
                return r6
            L_0x06c6:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.updateDeviceProperties(r0, r1)
                r11.writeNoException()
                return r6
            L_0x06d8:
                r10.enforceInterface(r2)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r0 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r8.updateDeviceList(r0)
                r11.writeNoException()
                return r6
            L_0x06ea:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r2 = r10.readInt()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.unBindDevice(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x0704:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.modDeviceName(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x071e:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.addRecord(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0730:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                long r3 = r10.readLong()
                r8.recordNumericPropertyEvent(r0, r2, r3)
                r11.writeNoException()
                return r6
            L_0x0746:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.recordStringPropertyEvent(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x075c:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                long r3 = r10.readLong()
                r8.recordCalculateEvent(r0, r2, r3)
                r11.writeNoException()
                return r6
            L_0x0772:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r1 = r10.readString()
                r8.recordCountEvent(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0784:
                r10.enforceInterface(r2)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.ILocationCallback r0 = com.xiaomi.router.miio.miioplugin.ILocationCallback.Stub.asInterface(r0)
                r8.requestLocation(r0)
                r11.writeNoException()
                return r6
            L_0x0796:
                r10.enforceInterface(r2)
                boolean r0 = r8.isGPSLocationEnable()
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x07a4:
                r10.enforceInterface(r2)
                boolean r0 = r8.isNetworkLocationEnabled()
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x07b2:
                r10.enforceInterface(r2)
                android.location.Location r0 = r8.getLastLocation()
                r11.writeNoException()
                if (r0 == 0) goto L_0x07c5
                r11.writeInt(r6)
                r0.writeToParcel(r11, r6)
                goto L_0x07c8
            L_0x07c5:
                r11.writeInt(r3)
            L_0x07c8:
                return r6
            L_0x07c9:
                r10.enforceInterface(r2)
                java.lang.String r0 = r8.getAccountId()
                r11.writeNoException()
                r11.writeString(r0)
                return r6
            L_0x07d7:
                r10.enforceInterface(r2)
                java.lang.String r2 = r10.readString()
                int r3 = r10.readInt()
                java.util.ArrayList r4 = r10.createStringArrayList()
                java.lang.String r5 = r10.readString()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r7 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r0)
                r0 = r8
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r7
                r0.unsubscribeDeviceV2(r1, r2, r3, r4, r5)
                r11.writeNoException()
                return r6
            L_0x07ff:
                r10.enforceInterface(r2)
                java.lang.String r2 = r10.readString()
                int r3 = r10.readInt()
                java.util.ArrayList r4 = r10.createStringArrayList()
                int r5 = r10.readInt()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r7 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r0)
                r0 = r8
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r7
                r0.subscribeDeviceV2(r1, r2, r3, r4, r5)
                r11.writeNoException()
                return r6
            L_0x0827:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                int r2 = r10.readInt()
                java.util.ArrayList r3 = r10.createStringArrayList()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r1)
                r8.unsubscribeDevice(r0, r2, r3, r1)
                r11.writeNoException()
                return r6
            L_0x0845:
                r10.enforceInterface(r2)
                java.lang.String r2 = r10.readString()
                int r3 = r10.readInt()
                java.util.ArrayList r4 = r10.createStringArrayList()
                int r5 = r10.readInt()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r7 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r0 = r8
                r1 = r2
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r7
                r0.subscribeDevice(r1, r2, r3, r4, r5)
                r11.writeNoException()
                return r6
            L_0x086d:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.smarthome.device.api.ISceneCallback r1 = com.xiaomi.smarthome.device.api.ISceneCallback.Stub.asInterface(r1)
                r8.refreshScene(r0, r1)
                r11.writeNoException()
                return r6
            L_0x0883:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.util.List r0 = r8.getSceneByDid(r0)
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x0895:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.util.List r0 = r8.getSubDeviceByParentDid(r0)
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x08a7:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x08b9
                android.os.Parcelable$Creator<com.xiaomi.router.miio.miioplugin.RoomStatus> r0 = com.xiaomi.router.miio.miioplugin.RoomStatus.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                r4 = r0
                com.xiaomi.router.miio.miioplugin.RoomStatus r4 = (com.xiaomi.router.miio.miioplugin.RoomStatus) r4
            L_0x08b9:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallbackRoomStatus r0 = com.xiaomi.router.miio.miioplugin.IPluginCallbackRoomStatus.Stub.asInterface(r0)
                r8.addRoom(r4, r0)
                r11.writeNoException()
                return r6
            L_0x08c8:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                java.lang.String r2 = r10.readString()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r1)
                r8.roomRename(r0, r2, r1)
                r11.writeNoException()
                return r6
            L_0x08e2:
                r10.enforceInterface(r2)
                java.util.ArrayList r0 = r10.createStringArrayList()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback2 r1 = com.xiaomi.router.miio.miioplugin.IPluginCallback2.Stub.asInterface(r1)
                r8.deleteRooms(r0, r1)
                r11.writeNoException()
                return r6
            L_0x08f8:
                r10.enforceInterface(r2)
                java.util.List r0 = r8.getRoomAll()
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x0906:
                r10.enforceInterface(r2)
                java.util.ArrayList r0 = r10.createStringArrayList()
                java.util.List r0 = r8.getDeviceListV2(r0)
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x0918:
                r10.enforceInterface(r2)
                java.util.List r0 = r8.getDeviceList()
                r11.writeNoException()
                r11.writeTypedList(r0)
                return r6
            L_0x0926:
                r10.enforceInterface(r2)
                java.lang.String r0 = r10.readString()
                com.xiaomi.router.miio.miioplugin.DeviceStatus r1 = new com.xiaomi.router.miio.miioplugin.DeviceStatus
                r1.<init>()
                r8.getDeviceStatus(r0, r1)
                r11.writeNoException()
                r11.writeInt(r6)
                r1.writeToParcel(r11, r6)
                return r6
            L_0x093f:
                r10.enforceInterface(r2)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0951
                android.os.Parcelable$Creator<com.xiaomi.router.miio.miioplugin.DeviceStatus> r0 = com.xiaomi.router.miio.miioplugin.DeviceStatus.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                r4 = r0
                com.xiaomi.router.miio.miioplugin.DeviceStatus r4 = (com.xiaomi.router.miio.miioplugin.DeviceStatus) r4
            L_0x0951:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IPluginCallback r0 = com.xiaomi.router.miio.miioplugin.IPluginCallback.Stub.asInterface(r0)
                r8.updateDeviceStatus(r4, r0)
                r11.writeNoException()
                return r6
            L_0x0960:
                r10.enforceInterface(r2)
                int r0 = r8.getApiLevel()
                r11.writeNoException()
                r11.writeInt(r0)
                return r6
            L_0x096e:
                r11.writeString(r2)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.router.miio.miioplugin.IPluginRequest.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IPluginRequest {
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

            public int getApiLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateDeviceStatus(DeviceStatus deviceStatus, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (deviceStatus != null) {
                        obtain.writeInt(1);
                        deviceStatus.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getDeviceStatus(String str, DeviceStatus deviceStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        deviceStatus.a(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<DeviceStatus> getDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceStatus.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<DeviceStatus> getDeviceListV2(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceStatus.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<RoomStatus> getRoomAll() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(RoomStatus.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteRooms(List<String> list, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void roomRename(String str, String str2, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addRoom(RoomStatus roomStatus, IPluginCallbackRoomStatus iPluginCallbackRoomStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (roomStatus != null) {
                        obtain.writeInt(1);
                        roomStatus.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPluginCallbackRoomStatus != null ? iPluginCallbackRoomStatus.asBinder() : null);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<DeviceStatus> getSubDeviceByParentDid(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceStatus.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<SceneInfo> getSceneByDid(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(SceneInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void refreshScene(String str, ISceneCallback iSceneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iSceneCallback != null ? iSceneCallback.asBinder() : null);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void subscribeDevice(String str, int i, List<String> list, int i2, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unsubscribeDevice(String str, int i, List<String> list, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void subscribeDeviceV2(String str, int i, List<String> list, int i2, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unsubscribeDeviceV2(String str, int i, List<String> list, String str2, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getAccountId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Location getLastLocation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isNetworkLocationEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(19, obtain, obtain2, 0);
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

            public boolean isGPSLocationEnable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(20, obtain, obtain2, 0);
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

            public void requestLocation(ILocationCallback iLocationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLocationCallback != null ? iLocationCallback.asBinder() : null);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void recordCountEvent(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void recordCalculateEvent(String str, String str2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void recordStringPropertyEvent(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void recordNumericPropertyEvent(String str, String str2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addRecord(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void modDeviceName(String str, String str2, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unBindDevice(String str, int i, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateDeviceList(IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateDeviceProperties(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void log(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addToLauncher(String str, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getRouterFileDownloadUrl(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void checkLocalRouterInfo(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isLocalMiRouter() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(35, obtain, obtain2, 0);
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

            public void gotoPage(Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendMessage(String str, int i, Intent intent, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getUserInfo(String str, IPluginCallbackUserInfo iPluginCallbackUserInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallbackUserInfo != null ? iPluginCallbackUserInfo.asBinder() : null);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateSubDevice(String[] strArr, IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    obtain.writeStrongBinder(iPluginCallbackDeviceList != null ? iPluginCallbackDeviceList.asBinder() : null);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void callRemoteAsync(String[] strArr, int i, String str, IPluginCallback iPluginCallback, IPluginCallback iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    if (iPluginCallback2 != null) {
                        iBinder = iPluginCallback2.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startSearchNewDevice(String str, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void notifyBluetoothBinded(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void loadWebView(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void loadUrl(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void loadRecommendScenes(String str, IRecommendSceneItemCallback iRecommendSceneItemCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iRecommendSceneItemCallback != null ? iRecommendSceneItemCallback.asBinder() : null);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateSceneItem(SceneInfo sceneInfo, ISceneInfoCallback iSceneInfoCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (sceneInfo != null) {
                        obtain.writeInt(1);
                        sceneInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSceneInfoCallback != null ? iSceneInfoCallback.asBinder() : null);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateScene(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hasSceneOnline(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.mRemote.transact(48, obtain, obtain2, 0);
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

            public void setSubDeviceShownMode(String str, boolean z, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public DeviceStatus getDevice(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DeviceStatus.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public DeviceTagInfo getDeviceTagInfo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DeviceTagInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getLightDeviceGroupModel(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addTag(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeTag(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<String> getRecommendTags(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isNotificationBarOpBtnEnabled(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.mRemote.transact(56, obtain, obtain2, 0);
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

            public int getMainProcessId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void renameBluetoothDevice(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setBleDeviceSubtitle(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(59, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerMediaButtonReceiver(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unRegisterMediaButtonReceiver(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getMediaButtonModel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(62, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void visualSecureBind(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(63, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void secureConnect(String str, IBleCallback iBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(64, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void callBleApi(String str, int i, Bundle bundle, IBleCallback iBleCallback) throws RemoteException {
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
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(65, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateDevice(List<String> list, IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iPluginCallbackDeviceList != null ? iPluginCallbackDeviceList.asBinder() : null);
                    this.mRemote.transact(66, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getPluginProcessName(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(67, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getDevicePincode(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(68, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean checkVoiceCtrlAuthorized(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(69, obtain, obtain2, 0);
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

            public boolean checkIfVoiceCtrlAuthorizedExpired(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(70, obtain, obtain2, 0);
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

            public void loadBitmap(String str, IPluginCallback3 iPluginCallback3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback3 != null ? iPluginCallback3.asBinder() : null);
                    this.mRemote.transact(71, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendPluginAdRequest(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(72, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopPluginAd(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(73, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void queryAd(String str, String str2, IAdCallback iAdCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iAdCallback != null ? iAdCallback.asBinder() : null);
                    this.mRemote.transact(74, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void reportAdShow(Advertisement advertisement) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (advertisement != null) {
                        obtain.writeInt(1);
                        advertisement.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(75, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void reportAdClick() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(76, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void reportAdClose(Advertisement advertisement) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (advertisement != null) {
                        obtain.writeInt(1);
                        advertisement.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(77, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void reportHotSpotShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(78, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getDeviceRealIconByModel(String str, IPluginCallback3 iPluginCallback3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback3 != null ? iPluginCallback3.asBinder() : null);
                    this.mRemote.transact(79, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void delDeviceBatch(List<String> list, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(80, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void openCameraFloatingWindow(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(81, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void closeCameraFloatingWindow(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getDevicePropByDid(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(83, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getWatchControllableDevices(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(84, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean checkIfSupportVoiceCtrl(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(85, obtain, obtain2, 0);
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

            public void securityChipConnect(String str, IBleCallback iBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(86, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void securityChipSharedDeviceConnect(String str, IBleCallback iBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(87, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void logByModel(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(88, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void reverseGeo(double d, double d2, IPluginCallback2 iPluginCallback2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeStrongBinder(iPluginCallback2 != null ? iPluginCallback2.asBinder() : null);
                    this.mRemote.transact(89, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void isBleGatewayExistInDeviceList(IBleCallback iBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(90, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestPermission(String[] strArr, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(91, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addDeviceToMain(String str, String str2, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(92, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isUsrExpPlanEnabled(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(93, obtain, obtain2, 0);
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

            public void setUsrExpPlanEnabled(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(94, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<DeviceStatus> getFilterBluetoothDeviceList(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(95, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceStatus.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<DeviceStatus> getBleGatewayDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(96, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceStatus.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void bleMeshConnect(String str, String str2, IBleCallback iBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(97, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getVirtualGroupStatus(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(98, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getSpecInstanceStr(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(99, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getSpecProptyValueFromSpecCard(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(100, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getBleGatewaySubDevices(List<String> list, IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iPluginCallbackDeviceList != null ? iPluginCallbackDeviceList.asBinder() : null);
                    this.mRemote.transact(101, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void initBandManager(String str, String str2, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(102, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deInitBandManager() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(103, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void connectBand(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(104, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAllCards(IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(105, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void issueDoorCard(IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(106, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteCard(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(107, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDefaultCard(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(108, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateCard(String str, IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(109, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getDefaultCardAndActivateInfo(IPluginCallback iPluginCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPluginCallback != null ? iPluginCallback.asBinder() : null);
                    this.mRemote.transact(110, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getUsePreviewConfig() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(111, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void bleStandardAuthConnect(String str, IBleCallback iBleCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBleCallback != null ? iBleCallback.asBinder() : null);
                    this.mRemote.transact(112, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void refreshDeviceListUi() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(113, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
