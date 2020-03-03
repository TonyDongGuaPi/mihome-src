package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.miui.tsmclient.util.LogUtils;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.plugin.rn.RNEventReceiver;
import com.xiaomi.smarthome.mitsmsdk.NfcChannelManager;

public class MHMiBandCardModule extends ReactContextBaseJavaModule {
    private static final String TAG = "MHMiBandCardModule";

    public String getName() {
        return TAG;
    }

    public MHMiBandCardModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public final void initBandManager(String str, String str2, final Callback callback) {
        LogUtils.d(" RN initBandManager");
        XmPluginHostApi.instance().initBandManager(str, str2, new com.xiaomi.smarthome.device.api.Callback<Boolean>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                callback.invoke(0, "");
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void deInitBandManager() {
        XmPluginHostApi.instance().deInitBandManager();
    }

    @ReactMethod
    public final void connectBand(String str, final Callback callback) {
        LogUtils.d(" RN connectBracelet");
        XmPluginHostApi.instance().connectBand(str, new com.xiaomi.smarthome.device.api.Callback<Integer>() {
            /* renamed from: a */
            public void onSuccess(Integer num) {
                LogUtils.d(" RN connectBand result = " + num);
                if (num.intValue() == 3) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) MHMiBandCardModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.BAND4_NFC_UI_SHOW, 3);
                } else if (num.intValue() == 2) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) MHMiBandCardModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.BAND4_NFC_UI_SHOW, 2);
                } else {
                    callback.invoke(num, "");
                }
            }

            public void onFailure(int i, String str) {
                LogUtils.d(" RN connectBand fail error = " + i);
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void getAllCards(final Callback callback) {
        XmPluginHostApi.instance().getAllCards(new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                WritableArray c = NfcChannelManager.c(str);
                callback.invoke(0, c);
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void issueDoorCard(final Callback callback) {
        XmPluginHostApi.instance().issueDoorCard(new com.xiaomi.smarthome.device.api.Callback<Boolean>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                callback.invoke(0, "");
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void deleteCard(String str, final Callback callback) {
        XmPluginHostApi.instance().deleteCard(str, new com.xiaomi.smarthome.device.api.Callback<Boolean>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                callback.invoke(0, "");
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void setDefaultCard(String str, final Callback callback) {
        XmPluginHostApi.instance().setDefaultCard(str, new com.xiaomi.smarthome.device.api.Callback<Boolean>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                callback.invoke(0, "");
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void updateCard(ReadableMap readableMap, final Callback callback) {
        XmPluginHostApi.instance().updateCard(readableMap.toString(), new com.xiaomi.smarthome.device.api.Callback<Boolean>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                callback.invoke(0, "");
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public final void getDefaultCardAndActivateInfo(final Callback callback) {
        XmPluginHostApi.instance().getDefaultCardAndActivateInfo(new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                WritableArray e = NfcChannelManager.e(str);
                callback.invoke(0, e);
            }

            public void onFailure(int i, String str) {
                callback.invoke(Integer.valueOf(i), str);
            }
        });
    }
}
