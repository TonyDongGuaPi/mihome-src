package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtRequest;
import com.xiaomi.smarthome.core.server.bluetooth.IClassicBtResponse;
import com.xiaomi.smarthome.core.server.bluetooth.IProfileProxyPrepareCallback;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.plugin.rn.RNEventReceiver;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;

public class ClassicBluetoothModule extends MIOTBaseJavaModule {
    public static final String ACTION_CLASSIC_BLUETOOTH = "miot.classic.bluetooth.action.method_notify";
    private Context mContext;
    private IClassicBtRequest mRequest = null;

    public String getName() {
        return "ClassicBluetooth";
    }

    public ClassicBluetoothModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    /* access modifiers changed from: package-private */
    public boolean checkInit() {
        if (this.mRequest == null) {
            this.mRequest = CoreApi.a().aa();
            CoreApi.a().a((IClassicBtResponse) new ClassicBtResponseImpl(this.mContext));
        }
        return this.mRequest != null;
    }

    @ReactMethod
    public void create(Callback callback) {
        if (checkInit()) {
            try {
                this.mRequest.createClassicBTService();
                callbackString(callback, true, "create success...");
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "create func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void connectSocket(String str, String str2, Callback callback) {
        if (checkInit()) {
            try {
                boolean connectClassicBTSocket = this.mRequest.connectClassicBTSocket(str, str2);
                callbackString(callback, connectClassicBTSocket, "connect socket is " + connectClassicBTSocket);
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "connectSocket func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void disconnectSocket(Callback callback) {
        if (checkInit()) {
            try {
                this.mRequest.disconnectClassicBtSocket();
                callbackString(callback, true, "disconnect socket...");
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "disconnectSocket func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void write(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, "data is null");
        } else if (checkInit()) {
            try {
                boolean write = this.mRequest.write(MIOTUtils.a(str));
                callbackString(callback, write, "write is " + write);
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "write func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void prepareBluetoothProfile(int i, final Callback callback) {
        if (checkInit()) {
            try {
                this.mRequest.prepareBluetoothProfile(i, new IProfileProxyPrepareCallback.Stub() {
                    public void onServiceConnected(int i) throws RemoteException {
                        if (callback != null) {
                            callback.invoke(true, Integer.valueOf(i));
                        }
                    }

                    public void onServiceDisconnected(int i) throws RemoteException {
                        if (callback != null) {
                            callback.invoke(false, Integer.valueOf(i));
                        }
                    }
                });
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "prepareBluetoothProfile func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void connectBluetoothProfile(String str, int i, Callback callback) {
        if (checkInit()) {
            try {
                boolean connectBluetoothProfile = this.mRequest.connectBluetoothProfile(str, i);
                callbackString(callback, connectBluetoothProfile, "connect bluetooth profile is " + connectBluetoothProfile);
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "connectBluetoothProfile func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void disconnectBluetoothProfile(String str, int i, Callback callback) {
        if (checkInit()) {
            try {
                boolean disconnectBluetoothProfile = this.mRequest.disconnectBluetoothProfile(str, i);
                callbackString(callback, disconnectBluetoothProfile, "disconnect bluetooth profile is " + disconnectBluetoothProfile);
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "disconnectBluetoothProfile func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void getBluetoothProfileState(String str, int i, Callback callback) {
        if (checkInit()) {
            try {
                int bluetoothProfileState = this.mRequest.getBluetoothProfileState(str, i);
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("state", bluetoothProfileState);
                callbackMap(callback, true, createMap);
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "getBluetoothProfileState func exec, request init error, request is null...");
        }
    }

    @ReactMethod
    public void destroy(Callback callback) {
        if (checkInit()) {
            try {
                this.mRequest.destroy();
                callbackString(callback, true, "destroy...");
            } catch (RemoteException e) {
                callbackString(callback, false, e.toString());
            }
        } else {
            callbackString(callback, false, "destroy func exec, request init error, request is null...");
        }
    }

    private void callbackString(Callback callback, boolean z, String str) {
        if (callback != null) {
            callback.invoke(Boolean.valueOf(z), str);
        }
    }

    private void callbackMap(Callback callback, boolean z, WritableMap writableMap) {
        if (callback != null) {
            callback.invoke(Boolean.valueOf(z), writableMap);
        }
    }

    static class ClassicBtResponseImpl extends IClassicBtResponse.Stub {
        private Context mContext;

        ClassicBtResponseImpl(Context context) {
            this.mContext = context;
        }

        public void onBondStateChange(String str, int i) throws RemoteException {
            RnPluginLog.a("exec onBondStateChange...");
            WritableMap createMap = Arguments.createMap();
            createMap.putString("macAddress", str);
            createMap.putInt("state", i);
            sendEventToJs(RNEventReceiver.CLASSIC_BLUE_BOND_STATE_CHANGE, createMap);
        }

        public void onConnectionStateChanged(String str, int i) throws RemoteException {
            RnPluginLog.a("exec onConnectionStateChanged...");
            WritableMap createMap = Arguments.createMap();
            createMap.putString("macAddress", str);
            createMap.putInt("state", i);
            sendEventToJs(RNEventReceiver.CLASSIC_BLUE_CONNECTION_STATE_CHANGE, createMap);
        }

        public void onReceiveData(String str, byte[] bArr) throws RemoteException {
            RnPluginLog.a("exec onReceiveData...");
            if (bArr == null || bArr.length == 0) {
                RnPluginLog.a("ClassicBluetoothModule onReceiveData,  data is empty...");
                return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("macAddress", str);
            createMap.putString("data", MIOTUtils.a(bArr));
            sendEventToJs(RNEventReceiver.CLASSIC_BLUE_RECEIVE_DATA, createMap);
        }

        private void sendLocalBroadcast(Bundle bundle) {
            Intent intent = new Intent(ClassicBluetoothModule.ACTION_CLASSIC_BLUETOOTH);
            intent.putExtras(bundle);
            LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(intent);
        }

        private void sendEventToJs(String str, WritableMap writableMap) {
            ReactContext currentReactContext;
            ReactInstanceManager l = RNRuntime.a().l();
            if (l == null || (currentReactContext = l.getCurrentReactContext()) == null) {
                RnPluginLog.b("ReactInstanceManager is null, can not send event, eventName: " + str);
            } else if (RNRuntime.a().n()) {
                if (writableMap == null) {
                    writableMap = Arguments.createMap();
                }
                RnPluginLog.a("will send event, eventName: " + str);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
            } else {
                RnPluginLog.b("can not send event, eventName: " + str);
            }
        }
    }
}
