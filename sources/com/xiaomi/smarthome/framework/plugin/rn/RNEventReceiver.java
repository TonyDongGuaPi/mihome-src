package com.xiaomi.smarthome.framework.plugin.rn;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.taobao.weex.WXGlobalEventReceiver;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTBluetoothModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTDeviceModule;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.youpin.hawkeye.entity.StatType;

public class RNEventReceiver extends BroadcastReceiver {
    public static final String ACTION_NET_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String AUDIO_PLAYER_DID_FINISH_PLAYING = "audioPlayerDidFinishPlaying_36621";
    public static final String AUDIO_PLAYER_DID_START_PLAYING = "audioPlayerDidStartPlaying_36621";
    public static final String AUDIO_UPDATE_AUDIOPLAYER_TIME = "updateAudioPlayerTime_36621";
    public static final String BAND4_NFC_UI_SHOW = "miband_nfc_confirm_ui_show";
    public static final String BLUETOOTHCHARACTERISTICCHANGED = "bluetoothCharacteristicValueChanged_36621";
    public static final String BLUETOOTHCHARACTERISTICDISCOVERED = "bluetoothCharacteristicDiscovered_36621";
    public static final String BLUETOOTHCONNECTIONSTATUSCHANGED = "bluetoothConnectionStatusChanged_36621";
    public static final String BLUETOOTHDEVICEDISCOVERED = "bluetoothDeviceDiscovered_36621";
    public static final String BLUETOOTHSEVICEDISCOVERED = "bluetoothSeviceDiscovered_36621";
    public static final String BLUETOOTHSTATUSCHANGED = "bluetoothStatusChanged_36621";
    public static final String CAMERA_COMMAND_RECEIVE_EVENT = "commandReceiveCallBack_36621";
    public static final String CAMERA_CONNECTION_CHANGE_EVENT = "connectionCallBack_36621";
    public static final String CANCELAUTHOR = "packageAuthorizationCancel_36621";
    public static final String CLASSIC_BLUE_BOND_STATE_CHANGE = "classicBlueBondStateChanged_36621";
    public static final String CLASSIC_BLUE_CONNECTION_STATE_CHANGE = "classicBlueConnectionStateChanged_36621";
    public static final String CLASSIC_BLUE_RECEIVE_DATA = "classicBlueReceivedData_36621";
    public static final String DEVICENAMECHANGED = "deviceNameChanged_36621";
    public static final String DEVICESTATUSCHANGED = "deviceRecievedMessages_36621";
    public static final String DEVICE_TIMEZONE_CHANGED = "deviceTimeZoneChanged_36621";
    public static final String EVENT_PUSH = "deviceStatusUpdatedEventName_36621";
    public static final String EXTRA_RECYCLE_PLUGIN = "extra_recycle_plugin";
    public static final String FILEISDOWNLOADINGEVENTNAME = "fileDownloadProgress_36621";
    public static final String PACKAGEDIDRESUME = "packageDidResume_36621";
    public static final String PACKAGEWILLEXIT = "packageWillExit_36621";
    public static final String PACKAGEWILLPAUSE = "packageWillPause_36621";
    public static final String SCENEPUSH = "packageReceivedInformation_36621";
    public static final String VIEWWILLAPPEAR = "packageViewWillAppear_36621";
    private static final String c = "cellPhoneNetworkStateChanged_36621";

    /* renamed from: a  reason: collision with root package name */
    private int f17247a = -1;
    private long b = -1;

    public void onReceive(Context context, Intent intent) {
        ReactContext currentReactContext;
        String str;
        String str2;
        String str3;
        String str4;
        WritableMap createMap = Arguments.createMap();
        ReactInstanceManager l = RNRuntime.a().l();
        String str5 = "";
        DeviceStat f = RNRuntime.a().f();
        if (l != null && (currentReactContext = l.getCurrentReactContext()) != null && intent != null) {
            if (XmBluetoothManager.ACTION_RENAME_NOTIFY.equals(intent.getAction())) {
                str5 = DEVICENAMECHANGED;
                createMap.putString("newName", intent.getStringExtra(XmBluetoothManager.EXTRA_NAME));
                createMap.putString("result", intent.getStringExtra("extra.result"));
                if (f == null) {
                    str4 = "";
                } else {
                    str4 = f.did;
                }
                createMap.putString("did", str4);
            } else {
                boolean z = true;
                if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                    str5 = BLUETOOTHSTATUSCHANGED;
                    if (f == null) {
                        str3 = "";
                    } else {
                        str3 = f.mac;
                    }
                    createMap.putString("mac", str3);
                    if (12 != intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
                        z = false;
                    }
                    long j = this.b;
                    long j2 = z ? 1 : 0;
                    if (j != j2) {
                        this.b = j2;
                        createMap.putBoolean("isEnabled", z);
                    } else {
                        return;
                    }
                } else if ("com.xiaomi.smarthome.bluetooth.connect_status_changed".equals(intent.getAction())) {
                    if (16 != intent.getIntExtra("key_connect_status", 0)) {
                        z = false;
                    }
                    str5 = BLUETOOTHCONNECTIONSTATUSCHANGED;
                    createMap.putString("mac", intent.getStringExtra("key_device_address"));
                    if (!isInvalidConnectStatusChanged(z)) {
                        createMap.putBoolean("isConnected", z);
                    } else {
                        return;
                    }
                } else if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction())) {
                    str5 = BLUETOOTHCONNECTIONSTATUSCHANGED;
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice == null) {
                        str2 = "";
                    } else {
                        str2 = bluetoothDevice.getAddress();
                    }
                    createMap.putString("mac", str2);
                    if (!isInvalidConnectStatusChanged(false)) {
                        createMap.putBoolean("isConnected", false);
                    } else {
                        return;
                    }
                } else if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                    if (intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1) != 12) {
                        z = false;
                    }
                    str5 = BLUETOOTHCONNECTIONSTATUSCHANGED;
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice2 == null) {
                        str = "";
                    } else {
                        str = bluetoothDevice2.getAddress();
                    }
                    createMap.putString("mac", str);
                    if (!isInvalidConnectStatusChanged(z)) {
                        createMap.putBoolean("isConnected", z);
                    } else {
                        return;
                    }
                } else if (MIOTBluetoothModule.STARTSCAN_CALLBACK.equals(intent.getAction())) {
                    str5 = BLUETOOTHDEVICEDISCOVERED;
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        createMap.merge(Arguments.fromBundle(extras));
                    }
                } else if (MIOTBluetoothModule.DISCOVERSERVICES_CALLBACK.equals(intent.getAction())) {
                    str5 = BLUETOOTHSEVICEDISCOVERED;
                    createMap.putString("mac", intent.getStringExtra(MIOTBluetoothModule.EMIT_MAC));
                    createMap.putArray("foundUUIDs", Arguments.fromArray(intent.getStringArrayExtra(MIOTBluetoothModule.EMIT_UUID)));
                } else if (MIOTBluetoothModule.DISCOVERCHARACTERISTICS_CALLBACK.equals(intent.getAction())) {
                    str5 = BLUETOOTHCHARACTERISTICDISCOVERED;
                    createMap.putString("mac", intent.getStringExtra(MIOTBluetoothModule.EMIT_MAC));
                    createMap.putString("serviceUUID", intent.getStringExtra(MIOTBluetoothModule.EMIT_SERVICEID));
                    createMap.putArray("foundUUIDs", Arguments.fromArray(intent.getStringArrayExtra(MIOTBluetoothModule.EMIT_UUID)));
                } else if ("com.xiaomi.smarthome.bluetooth.character_changed".equals(intent.getAction())) {
                    str5 = BLUETOOTHCHARACTERISTICCHANGED;
                    createMap.putString("mac", intent.getStringExtra("key_device_address"));
                    createMap.putString("serviceUUID", intent.getSerializableExtra("key_service_uuid").toString());
                    createMap.putString("characteristicUUID", intent.getSerializableExtra("key_character_uuid").toString());
                    createMap.putString("value", MIOTUtils.a(intent.getByteArrayExtra("key_character_value")));
                } else if (MIOTDeviceModule.DEVICESTATUSCHANGED.equals(intent.getAction())) {
                    str5 = DEVICESTATUSCHANGED;
                    createMap.putString("subcribeId", intent.getStringExtra(MIOTDeviceModule.EMIT_SUBID));
                    createMap.putString("did", intent.getStringExtra(MIOTDeviceModule.EMIT_DID));
                    createMap.putString("data", intent.getStringExtra(MIOTDeviceModule.EMIT_DATA));
                } else if (PluginBridgeService.ACTION_PLUGIN_PUSH.equals(intent.getAction())) {
                    str5 = SCENEPUSH;
                    if ("ScenePush".equals(intent.getStringExtra("type"))) {
                        createMap.putString("did", intent.getStringExtra("did"));
                        createMap.putString("event", intent.getStringExtra("event"));
                        createMap.putString("extra", intent.getStringExtra("extra"));
                        createMap.putBoolean("isNotified", intent.getBooleanExtra("isNotified", false));
                        createMap.putDouble("time", (double) intent.getLongExtra("time", 0));
                    }
                } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                    str5 = c;
                    NetworkInfo networkInfo = null;
                    if (intent.getExtras() != null) {
                        try {
                            networkInfo = (NetworkInfo) intent.getExtras().get(StatType.NETWORKINFO);
                        } catch (Exception unused) {
                        }
                    }
                    createMap.putInt("networkState", a(networkInfo));
                }
            }
            createMap.putString(WXGlobalEventReceiver.EVENT_NAME, str5);
            if (RNRuntime.a().n()) {
                RnPluginLog.a("eventName: " + str5 + "  action: " + intent.getAction() + " data: " + createMap.toString());
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str5, createMap);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isInvalidConnectStatusChanged(boolean z) {
        if (this.f17247a == z) {
            return true;
        }
        this.f17247a = z ? 1 : 0;
        return false;
    }

    private int a(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return -1;
        }
        if (networkInfo.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED) {
            return 0;
        }
        if (networkInfo.getDetailedState() == NetworkInfo.DetailedState.CONNECTED && "MOBILE".equals(networkInfo.getTypeName())) {
            return 1;
        }
        if (networkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED || !"WIFI".equals(networkInfo.getTypeName())) {
            return -1;
        }
        return 2;
    }
}
