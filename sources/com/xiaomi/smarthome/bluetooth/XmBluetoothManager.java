package com.xiaomi.smarthome.bluetooth;

import android.content.Context;
import android.os.Bundle;
import com.xiaomi.smarthome.bluetooth.Response;
import java.util.UUID;

public abstract class XmBluetoothManager {
    public static final String ACTION_CHARACTER_CHANGED = "com.xiaomi.smarthome.bluetooth.character_changed";
    public static final String ACTION_CHARACTER_WRITE = "com.xiaomi.smarthome.bluetooth.character_write";
    public static final String ACTION_CONNECT_STATUS_CHANGED = "com.xiaomi.smarthome.bluetooth.connect_status_changed";
    public static final String ACTION_ONLINE_STATUS_CHANGED = "action.online.status.changed";
    public static final String ACTION_RENAME_NOTIFY = "action.more.rename.notify";
    public static final int ALERT_ALARM_ENABLED = 3;
    public static final int ALERT_INCALL_IN_CONTACTS_ENABLED = 1;
    public static final int ALERT_INCALL_NO_CONTACTS_ENABLED = 2;
    public static final int ALERT_SMS_IN_CONTACTS_ENABLED = 4;
    public static final int ALERT_SMS_NO_CONTACTS_ENABLED = 5;
    public static final int CODE_REFRESH_CACHE = 3;
    public static final int CODE_REFRESH_DEVICES = 2;
    public static final int CODE_REFRESH_DEVICE_STATUS = 1;
    public static final int CODE_REMOVE_TOKEN = 1;
    public static final String EXTRA_GATT_PROFILE = "key_gatt_profile";
    public static final String EXTRA_MAC = "extra_mac";
    public static final String EXTRA_NAME = "extra.name";
    public static final String EXTRA_ONLINE_STATUS = "extra_online_status";
    public static final String EXTRA_RESULT = "extra.result";
    public static final String EXTRA_SCANRECORD = "extra.scanRecord";
    public static final String EXTRA_TOKEN = "token";
    public static final String EXTRA_UPGRADE_PROCESS = "extra_upgrade_progress";
    public static final String KEY_CHARACTER_UUID = "key_character_uuid";
    public static final String KEY_CHARACTER_VALUE = "key_character_value";
    public static final String KEY_CHARACTER_WRITE_STATUS = "key_character_write_status";
    public static final String KEY_CONNECT_STATUS = "key_connect_status";
    public static final String KEY_DEVICES = "devices";
    public static final String KEY_DEVICE_ADDRESS = "key_device_address";
    public static final String KEY_FIRMWARE_CLICK = "key_firmware_click";
    public static final String KEY_MISERVICE_CHARACTERS = "key_miservice_characters";
    public static final String KEY_SERVICE_UUID = "key_service_uuid";
    public static final int PAGE_CURRENT_DEPRECATED = 2;
    public static final int PAGE_CURRENT_LATEST = 1;
    public static final int PAGE_LOADING = 0;
    public static final int PAGE_UPGRADE_FAILED = 5;
    public static final int PAGE_UPGRADE_SUCCESS = 4;
    public static final int PAGE_UPGRADING = 3;
    public static final int REQUEST_CODE_OPEN_BLUETOOTH = 16;
    public static final int RESULT_FAILED = 0;
    public static final int RESULT_SUCCESS = 1;
    public static final int SCAN_BLE = 1;
    public static final int SCAN_CLASSIC = 0;
    public static final int SECURITY_CHIP_BOLT_OPERATOR = 2;
    private static byte[] SECURITY_CHIP_BOLT_STATE = {2};
    public static final int SECURITY_CHIP_LOCK_OPERATOR = 1;
    private static byte[] SECURITY_CHIP_LOCK_STATE = {1};
    public static final int SECURITY_CHIP_UNLOCK_OPERATOR = 0;
    private static byte[] SECURITY_CHIP_UNLOCK_STATE = {0};
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
    public static final int STATE_UNKNOWN = -1;
    public static final int STATUS_CONNECTED = 16;
    public static final int STATUS_DISCONNECTED = 32;
    public static final int STATUS_LOGGED_IN = 80;
    public static final int STATUS_OFFLINE = 64;
    public static final int STATUS_ONLINE = 48;
    public static final int STATUS_UNKNOWN = 5;
    protected static XmBluetoothManager instance;

    public interface BluetoothSearchResponse {
        void onDeviceFounded(XmBluetoothDevice xmBluetoothDevice);

        void onSearchCanceled();

        void onSearchStarted();

        void onSearchStopped();
    }

    public abstract void bindDevice(String str);

    public abstract void bleMeshConnect(String str, String str2, Response.BleConnectResponse bleConnectResponse);

    public abstract void bleStandardAuthConnect(String str, Response.BleConnectResponse bleConnectResponse);

    public abstract void bleStandardAuthDecrypt(String str, byte[] bArr, Response.BleReadResponse bleReadResponse);

    public abstract void bleStandardAuthEncrypt(String str, byte[] bArr, Response.BleReadResponse bleReadResponse);

    public abstract void call(String str, int i, Bundle bundle, Response.BleCallResponse bleCallResponse);

    public abstract void cancelBleMeshUpgrade(String str);

    public abstract void connect(String str, Response.BleConnectResponse bleConnectResponse);

    @Deprecated
    public abstract void deviceRename(String str, String str2);

    public abstract void disconnect(String str);

    public abstract void disconnect(String str, long j);

    public abstract void getBleMeshFirmwareVersion(String str, Response.BleReadFirmwareVersionResponse bleReadFirmwareVersionResponse);

    public abstract void getBleStandardAuthFirmwareVersion(String str, Response.BleReadFirmwareVersionResponse bleReadFirmwareVersionResponse);

    public abstract void getBluetoothFirmwareVersion(String str, Response.BleReadFirmwareVersionResponse bleReadFirmwareVersionResponse);

    public abstract int getConnectStatus(String str);

    public abstract void getOneTimePassword(String str, int i, int i2, Response.BleResponseV2<int[]> bleResponseV2);

    public abstract void getOneTimePasswordWithDelayTime(String str, int i, int i2, long j, Response.BleResponseV2<int[]> bleResponseV2);

    public abstract String getTokenMd5(String str);

    public abstract void indication(String str, UUID uuid, UUID uuid2, Response.BleNotifyResponse bleNotifyResponse);

    @Deprecated
    public abstract boolean isAutoReconnect(String str);

    public abstract void isBleCharacterExist(String str, UUID uuid, UUID uuid2, Response.BleResponse<Void> bleResponse);

    public abstract void isBleGatewayConnected(String str, Response.BleResponse<Void> bleResponse);

    public abstract void isBleGatewayExistInDeviceList(Response.BleResponse<Bundle> bleResponse);

    public abstract boolean isBluetoothOpen();

    public abstract boolean isSecurityChipSharedKeyValid(String str);

    public abstract void miotBleDecrypt(String str, byte[] bArr, Response.BleReadResponse bleReadResponse);

    public abstract byte[] miotBleDecryptSync(String str, byte[] bArr);

    public abstract void miotBleEncrypt(String str, byte[] bArr, Response.BleReadResponse bleReadResponse);

    public abstract byte[] miotBleEncryptSync(String str, byte[] bArr);

    public abstract void notify(String str, UUID uuid, UUID uuid2, Response.BleNotifyResponse bleNotifyResponse);

    public abstract void openBluetooth(Context context);

    public abstract void openBluetoothSilently();

    public abstract void read(String str, UUID uuid, UUID uuid2, Response.BleReadResponse bleReadResponse);

    public abstract void readRemoteRssi(String str, Response.BleReadRssiResponse bleReadRssiResponse);

    @Deprecated
    public abstract void refreshDeviceStatus(String str, long j, Response.BleDeviceStatusResponse bleDeviceStatusResponse);

    public abstract void registerBlockListener(String str, Response.BleReadResponse bleReadResponse);

    public abstract void registerCharacterChanged(String str, UUID uuid, UUID uuid2, Response.BleWriteResponse bleWriteResponse);

    public abstract void registerMediaButtonReceiver(String str);

    public abstract void removeToken(String str);

    public abstract void secureConnect(String str, Response.BleConnectResponse bleConnectResponse);

    public abstract void securityChipConnect(String str, Response.BleConnectResponse bleConnectResponse);

    public abstract void securityChipDecrypt(String str, byte[] bArr, Response.BleReadResponse bleReadResponse);

    public abstract void securityChipEncrypt(String str, byte[] bArr, Response.BleReadResponse bleReadResponse);

    public abstract void securityChipOperate(String str, int i, Response.BleReadResponse bleReadResponse);

    public abstract void securityChipSharedDeviceConnect(String str, Response.BleConnectResponse bleConnectResponse);

    public abstract boolean setAlertConfigs(String str, int i, boolean z);

    @Deprecated
    public abstract boolean setAutoReconnect(String str, boolean z);

    public abstract void startBleMeshUpgrade(String str, String str2, String str3, String str4, Response.BleUpgradeResponse bleUpgradeResponse);

    public abstract void startLeScan(int i, UUID[] uuidArr, BluetoothSearchResponse bluetoothSearchResponse);

    public abstract void startScan(int i, int i2, BluetoothSearchResponse bluetoothSearchResponse);

    public abstract void stopScan();

    public abstract void unBindDevice(String str);

    public abstract void unRegisterMediaButtonReceiver(String str);

    public abstract void unindication(String str, UUID uuid, UUID uuid2);

    public abstract void unnotify(String str, UUID uuid, UUID uuid2);

    public abstract void unregisterBlockListener(String str);

    public abstract void unregisterCharacterChanged(String str, UUID uuid, UUID uuid2);

    public abstract void write(String str, UUID uuid, UUID uuid2, byte[] bArr, Response.BleWriteResponse bleWriteResponse);

    public abstract void writeBlock(String str, byte[] bArr, Response.BleWriteResponse bleWriteResponse);

    public abstract void writeNoRsp(String str, UUID uuid, UUID uuid2, byte[] bArr, Response.BleWriteResponse bleWriteResponse);

    public static XmBluetoothManager getInstance() {
        return instance;
    }

    public static class Code {
        public static final int BLE_NOT_SUPPORTED = -4;
        public static final int BLUETOOTH_DISABLED = -5;
        public static final int CONFIG_UNREADY = -12;
        public static final int CONNECTION_NOT_READY = -6;
        public static final int ILLEGAL_ARGUMENT = -3;
        public static final int REQUEST_BIND_DID_FAILED = -30;
        public static final int REQUEST_CANCELED = -2;
        public static final int REQUEST_DENIED = -14;
        public static final int REQUEST_EXCEPTION = -15;
        public static final int REQUEST_FAILED = -1;
        public static final int REQUEST_GET_DID_FAILED = -29;
        public static final int REQUEST_MESH_PROVISION_INFO_FAILED = -44;
        public static final int REQUEST_MESH_REG_DEVICE_VERIFY_CERT_FAILED = -41;
        public static final int REQUEST_MESH_REG_DEVICE_VERIFY_PUB_FAILED = -43;
        public static final int REQUEST_MESH_REG_DEVICE_VERIFY_SIGN_FAILED = -42;
        public static final int REQUEST_MESH_REG_SERVER_VERIFY_CERT_FAILED = -39;
        public static final int REQUEST_MESH_REG_SERVER_VERIFY_SIGN_FAILED = -40;
        public static final int REQUEST_MESH_SEND_SERVER_RESULT_FAILED = -45;
        public static final int REQUEST_NOTIFY_FAILED = -27;
        public static final int REQUEST_NOTIFY_TIMEDOUT = -55;
        public static final int REQUEST_NOT_CONNECTED = -8;
        public static final int REQUEST_NOT_REGISTERED = -16;
        public static final int REQUEST_ONGOING = -13;
        public static final int REQUEST_OVERFLOW = -11;
        public static final int REQUEST_PINCODE_IS_EMPTY = -38;
        public static final int REQUEST_REGISTERED = -17;
        public static final int REQUEST_SC_BIND_LTMK_FAILED = -26;
        public static final int REQUEST_SC_LOGIN_ENCRYPT_DATA_FAILED = -21;
        public static final int REQUEST_SC_LOGIN_FAILED = -22;
        public static final int REQUEST_SC_REGISTER_FAILED = -20;
        public static final int REQUEST_SC_REGISTER_GET_BIND_KEY_FAILED = -46;
        public static final int REQUEST_SC_REGISTER_GET_VERSION_FAILED = -36;
        public static final int REQUEST_SC_REGISTER_INPUT_PAIR_CODE = -33;
        public static final int REQUEST_SC_REGISTER_PAIR_CODE_EXPIRED = -35;
        public static final int REQUEST_SC_REGISTER_PAIR_CODE_FAILED = -34;
        public static final int REQUEST_SC_REGISTER_UNSUPPORT_VERSION = -37;
        public static final int REQUEST_SC_SHARED_KEY_FAILED = -19;
        public static final int REQUEST_SC_SHARED_LOGIN_ENCRYPT_DATA_FAILED = -23;
        public static final int REQUEST_SC_SHARED_LOGIN_FAILED = -24;
        public static final int REQUEST_SC_SHARED_LOGIN_KEY_ID_EMPTY = -25;
        public static final int REQUEST_SHARED_KEY_EXPIRED = -18;
        public static final int REQUEST_STANDARD_AUTH_ERR_RELOGIN = -53;
        public static final int REQUEST_STANDARD_AUTH_GET_APP_CONFIRM_FAILED = -48;
        public static final int REQUEST_STANDARD_AUTH_GET_DEVICE_INFO_FAILED = -47;
        public static final int REQUEST_STANDARD_AUTH_GET_QR_OOB_FAILED = -50;
        public static final int REQUEST_STANDARD_AUTH_LOGIN_FAILED = -52;
        public static final int REQUEST_STANDARD_AUTH_OOB_FAILED = -49;
        public static final int REQUEST_STANDARD_AUTH_REGISTER_FAILED = -51;
        public static final int REQUEST_STANDARD_AUTH_TOKEN_IS_EMPTY = -54;
        public static final int REQUEST_STATUS_DISCONNECTED = -32;
        public static final int REQUEST_SUCCESS = 0;
        public static final int REQUEST_TIMEDOUT = -7;
        public static final int REQUEST_TOKEN_VERIFY_FAILED = -31;
        public static final int REQUEST_WRITE_FAILED = -28;
        public static final int TOKEN_NOT_MATCHED = -10;

        public static String toString(int i) {
            switch (i) {
                case -55:
                    return "REQUEST_NOTIFY_TIMEDOUT";
                case -54:
                    return "REQUEST_STANDARD_AUTH_TOKEN_IS_EMPTY";
                case -53:
                    return "REQUEST_STANDARD_AUTH_ERR_RELOGIN";
                case -52:
                    return "REQUEST_STANDARD_AUTH_LOGIN_FAILED";
                case -51:
                    return "REQUEST_STANDARD_AUTH_REGISTER_FAILED";
                case -50:
                    return "REQUEST_STANDARD_AUTH_GET_QR_OOB_FAILED";
                case -49:
                    return "REQUEST_STANDARD_AUTH_OOB_FAILED";
                case -48:
                    return "REQUEST_STANDARD_AUTH_GET_APP_CONFIRM_FAILED";
                case -47:
                    return "REQUEST_STANDARD_AUTH_GET_DEVICE_INFO_FAILED";
                case -46:
                    return "REQUEST_SC_REGISTER_GET_BIND_KEY_FAILED";
                case -45:
                    return "REQUEST_MESH_SEND_SERVER_RESULT_FAILED";
                case -44:
                    return "REQUEST_MESH_PROVISION_INFO_FAILED";
                case -43:
                    return "REQUEST_MESH_REG_DEVICE_VERIFY_PUB_FAILED";
                case -42:
                    return "REQUEST_MESH_REG_DEVICE_VERIFY_SIGN_FAILED";
                case -41:
                    return "REQUEST_MESH_REG_DEVICE_VERIFY_CERT_FAILED";
                case -40:
                    return "REQUEST_MESH_REG_SERVER_VERIFY_SIGN_FAILED";
                case -39:
                    return "REQUEST_MESH_REG_SERVER_VERIFY_CERT_FAILED";
                case -38:
                    return "REQUEST_PINCODE_IS_EMPTY";
                case -37:
                    return "REQUEST_SC_REGISTER_UNSUPPORT_VERSION";
                case -36:
                    return "REQUEST_SC_REGISTER_GET_VERSION_FAILED";
                case -35:
                    return "REQUEST_SC_REGISTER_PAIR_CODE_EXPIRED";
                case -34:
                    return "REQUEST_SC_REGISTER_PAIR_CODE_FAILED";
                case -33:
                    return "REQUEST_SC_REGISTER_INPUT_PAIR_CODE";
                case -32:
                    return "REQUEST_STATUS_DISCONNECTED";
                case -31:
                    return "REQUEST_TOKEN_VERIFY_FAILED";
                case -30:
                    return "REQUEST_BIND_DID_FAILED";
                case -29:
                    return "REQUEST_GET_DID_FAILED";
                case -28:
                    return "REQUEST_WRITE_FAILED";
                case -27:
                    return "REQUEST_NOTIFY_FAILED";
                case -26:
                    return "REQUEST_SC_BIND_LTMK_FAILED";
                case -25:
                    return "REQUEST_SC_SHARED_LOGIN_KEY_ID_EMPTY";
                case -24:
                    return "REQUEST_SC_SHARED_LOGIN_FAILED";
                case -23:
                    return "REQUEST_SC_SHARED_LOGIN_ENCRYPT_DATA_FAILED";
                case -22:
                    return "REQUEST_SC_LOGIN_FAILED";
                case -21:
                    return "REQUEST_SC_LOGIN_ENCRYPT_DATA_FAILED";
                case -20:
                    return "REQUEST_SC_REGISTER_FAILED";
                case -19:
                    return "REQUEST_SC_SHARED_KEY_FAILED";
                case -18:
                    return "REQUEST_SHARED_KEY_EXPIRED";
                case -17:
                    return "REQUEST_REGISTERED";
                case -16:
                    return "REQUEST_NOT_REGISTERED";
                case -15:
                    return "REQUEST_EXCEPTION";
                case -14:
                    return "REQUEST_DENIED";
                case -13:
                    return "REQUEST_ONGOING";
                case -12:
                    return "CONFIG_UNREADY";
                case -11:
                    return "REQUEST_OVERFLOW";
                case -10:
                    return "TOKEN_NOT_MATCHED";
                case -8:
                    return "REQUEST_NOT_CONNECTED";
                case -7:
                    return "REQUEST_TIMEDOUT";
                case -6:
                    return "CONNECTION_NOT_READY";
                case -5:
                    return "BLUETOOTH_DISABLED";
                case -4:
                    return "BLE_NOT_SUPPORTED";
                case -3:
                    return "ILLEGAL_ARGUMENT";
                case -2:
                    return "REQUEST_CANCELED";
                case -1:
                    return "REQUEST_FAILED";
                case 0:
                    return "REQUEST_SUCCESS";
                default:
                    return "unknown code: " + i;
            }
        }
    }
}
