package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.coloros.mcssdk.mode.Message;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.DeviceUpdateInfo;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.ProductInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.plugin.rn.constants.RnApiErrorInfo;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.plugin.Error;
import com.xiaomi.smarthome.plugin.devicesubscribe.PluginSubscribeCallback;
import com.xiaomi.smarthome.plugin.devicesubscribe.PluginUnSubscribeCallback;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.youpin.utils.LogUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MIOTDeviceModule extends MIOTBaseJavaModule {
    public static final String DEVICESTATUSCHANGED = "devicestatuschanged";
    public static final String EMIT_DATA = "emit_data";
    public static final String EMIT_DID = "emit_did";
    public static final String EMIT_SUBID = "emit_subid";
    private static final Parser<String> PARSERFORRAWRESULT = new PluginHostApi.ParserForRawResult<String>() {
        /* renamed from: a */
        public String parse(String str) throws JSONException {
            return str;
        }
    };
    private static int PERMISSION_NONE_MASK = 30;
    private static int PERMISSION_OWNER = 16;
    private static List<Integer> mErrorcodesShowForDebug = new ArrayList();

    /* access modifiers changed from: private */
    public int convertRssiToSignal(int i) {
        if (i >= -50) {
            return 4;
        }
        if (i >= -70) {
            return 3;
        }
        if (i >= -90) {
            return 2;
        }
        return i >= -100 ? 1 : 0;
    }

    /* access modifiers changed from: private */
    public void showErrorInfoForDebug(int i, String str) {
    }

    public String getName() {
        return "MIOTDevice";
    }

    static {
        mErrorcodesShowForDebug.add(-9999);
        mErrorcodesShowForDebug.add(-12);
        mErrorcodesShowForDebug.add(-97);
        mErrorcodesShowForDebug.add(-4);
        mErrorcodesShowForDebug.add(-3);
        mErrorcodesShowForDebug.add(-1);
    }

    public MIOTDeviceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        WritableMap _getCurrentDeviceInfo = _getCurrentDeviceInfo();
        HashMap hashMap = new HashMap();
        hashMap.put("currentDevice", _getCurrentDeviceInfo);
        return hashMap;
    }

    private WritableMap _getCurrentDeviceInfo() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        device2Map(writableNativeMap, getDevice());
        PluginRecord pluginRecord = getPluginRecord();
        if (pluginRecord != null) {
            boolean z = true;
            if (pluginRecord.c() == null || pluginRecord.c().i != 1) {
                z = false;
            }
            writableNativeMap.putBoolean("isVoiceDevice", z);
        }
        return writableNativeMap;
    }

    @ReactMethod
    public void loadSubDevices(String str, final Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, "did is empty...");
        } else if (getDevice() == null) {
            callback.invoke(false, "current device is null...");
        } else {
            XmPluginHostApi.instance().getSubDevice(getDevice().model, new String[]{str}, new com.xiaomi.smarthome.device.api.Callback<List<DeviceStat>>() {
                /* renamed from: a */
                public void onSuccess(List<DeviceStat> list) {
                    WritableNativeArray writableNativeArray = new WritableNativeArray();
                    for (DeviceStat deviceStat : list) {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        MIOTDeviceModule.device2Map(writableNativeMap, XmPluginHostApi.instance().getDeviceByDid(deviceStat.did));
                        writableNativeArray.pushMap(writableNativeMap);
                    }
                    callback.invoke(true, writableNativeArray);
                }

                public void onFailure(int i, String str) {
                    callback.invoke(false, str);
                }
            });
        }
    }

    @ReactMethod
    public void getVersion(boolean z, final Callback callback) {
        DeviceStat device = getDevice();
        if (device == null) {
            callback.invoke(false, "has no device");
            return;
        }
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(device.did);
        if (deviceByDid == null || TextUtils.isEmpty(deviceByDid.version)) {
            XmPluginHostApi.instance().getUpdateInfo(device.model, device.did, device.pid, new com.xiaomi.smarthome.device.api.Callback<DeviceUpdateInfo>() {
                /* renamed from: a */
                public void onSuccess(DeviceUpdateInfo deviceUpdateInfo) {
                    callback.invoke(true, deviceUpdateInfo.mCurVersion);
                }

                public void onFailure(int i, String str) {
                    Callback callback = callback;
                    callback.invoke(false, i + str);
                }
            });
            return;
        }
        callback.invoke(true, deviceByDid.version);
    }

    @ReactMethod
    public void callMethod(String str, String str2, String str3, String str4, final Callback callback) {
        JSONObject jSONObject;
        if (str4 == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Throwable th) {
                if (callback != null) {
                    callback.invoke(false, Log.getStackTraceString(th));
                    return;
                }
                return;
            }
        } else {
            jSONObject = new JSONObject(str4);
        }
        jSONObject.put("id", ((PluginHostApi) XmPluginHostApi.instance()).generateNonce());
        jSONObject.put("method", str2);
        if (str3 == null) {
            str3 = "";
        }
        jSONObject.put("params", new JSONTokener(str3).nextValue());
        AnonymousClass3 r4 = null;
        if (callback != null) {
            r4 = new com.xiaomi.smarthome.device.api.Callback<String>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    try {
                        WritableMap createMap = Arguments.createMap();
                        MIOTUtils.a(new JSONObject(str), createMap);
                        callback.invoke(true, createMap);
                    } catch (JSONException unused) {
                        callback.invoke(true, str);
                    }
                }

                public void onFailure(int i, String str) {
                    MIOTDeviceModule.this.showErrorInfoForDebug(i, str);
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putInt("error", i);
                    writableNativeMap.putString("message", str);
                    callback.invoke(false, writableNativeMap);
                }
            };
        }
        XmPluginHostApi.instance().callMethod(str, jSONObject.toString(), r4, MIOTServiceModule.PARSERFORRAWRESULT);
    }

    @ReactMethod
    public void callMethodFromLocal(String str, String str2, String str3, String str4, Callback callback) {
        if (callback != null) {
            try {
                DeviceStat device = getDevice();
                if (!device.did.equals(str)) {
                    device = XmPluginHostApi.instance().getDeviceByDid(str);
                }
                if (device.location != 1) {
                    final String str5 = str;
                    final String str6 = str2;
                    final String str7 = str3;
                    final String str8 = str4;
                    final Callback callback2 = callback;
                    XmPluginHostApi.instance().localPing(str, new com.xiaomi.smarthome.device.api.Callback<Void>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            RnPluginLog.a("localPing is success...");
                            if (MIOTDeviceModule.this.getDevice().did.equals(str5)) {
                                MIOTDeviceModule.this.getDevice().location = 1;
                            }
                            MIOTDeviceModule.this.callMethodFromLocalWithoutLocalPint(str5, str6, str7, str8, callback2);
                        }

                        public void onFailure(int i, String str) {
                            if (MIOTDeviceModule.this.getDevice().did.equals(str5)) {
                                MIOTDeviceModule.this.getDevice().location = 0;
                            }
                            callback2.invoke(false, RnCallbackMapUtil.a(i, str));
                        }
                    });
                    return;
                }
                callMethodFromLocalWithoutLocalPint(str, str2, str3, str4, callback);
            } catch (Exception e) {
                callback.invoke(false, RnCallbackMapUtil.a(e.toString()));
            }
        }
    }

    /* access modifiers changed from: private */
    public void callMethodFromLocalWithoutLocalPint(String str, String str2, String str3, String str4, final Callback callback) {
        JSONObject jSONObject;
        AnonymousClass5 r0 = callback != null ? new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    WritableMap createMap = Arguments.createMap();
                    MIOTUtils.a(new JSONObject(str), createMap);
                    callback.invoke(true, createMap);
                } catch (JSONException unused) {
                    callback.invoke(true, str);
                }
            }

            public void onFailure(int i, String str) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("error", i);
                writableNativeMap.putString("message", str);
                callback.invoke(false, writableNativeMap);
            }
        } : null;
        if (str4 == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Throwable th) {
                callback.invoke(false, RnCallbackMapUtil.a(th.toString()));
                return;
            }
        } else {
            jSONObject = new JSONObject(str4);
        }
        jSONObject.put("id", ((PluginHostApi) XmPluginHostApi.instance()).generateNonce());
        jSONObject.put("method", str2);
        if (str3 == null) {
            str3 = "";
        }
        jSONObject.put("params", new JSONTokener(str3).nextValue());
        XmPluginHostApi.instance().callMethodFromLocal(str, jSONObject.toString(), r0, MIOTServiceModule.PARSERFORRAWRESULT);
    }

    @ReactMethod
    public void localPingWithCallback(String str, final Callback callback) {
        try {
            XmPluginHostApi.instance().localPing(str, new com.xiaomi.smarthome.device.api.Callback<Void>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    callback.invoke(true);
                }

                public void onFailure(int i, String str) {
                    Callback callback = callback;
                    callback.invoke(false, i + str);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @ReactMethod
    public void callMethodFromCloud(String str, String str2, String str3, String str4, final Callback callback) {
        JSONObject jSONObject;
        if (str4 == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Throwable th) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("error", -1);
                writableNativeMap.putString("message", Log.getStackTraceString(th));
                callback.invoke(false, writableNativeMap);
                return;
            }
        } else {
            jSONObject = new JSONObject(str4);
        }
        jSONObject.put("id", ((PluginHostApi) XmPluginHostApi.instance()).generateNonce());
        jSONObject.put("method", str2);
        jSONObject.put("params", new JSONTokener(str3).nextValue());
        XmPluginHostApi.instance().callMethodFromCloud(str, jSONObject.toString(), new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    WritableMap createMap = Arguments.createMap();
                    MIOTUtils.a(new JSONObject(str), createMap);
                    callback.invoke(true, createMap);
                } catch (JSONException unused) {
                    callback.invoke(true, str);
                }
            }

            public void onFailure(int i, String str) {
                MIOTDeviceModule.this.showErrorInfoForDebug(i, str);
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putInt("error", i);
                writableNativeMap.putString("message", str);
                callback.invoke(false, writableNativeMap);
            }
        }, MIOTServiceModule.PARSERFORRAWRESULT);
    }

    @ReactMethod
    public void subscribeMessages(String str, ReadableArray readableArray, final Callback callback) {
        DeviceStat device = getDevice();
        if (getPluginRecord() == null || device == null) {
            callback.invoke(false, "no device pluginRecord ");
            return;
        }
        String str2 = str;
        XmPluginHostApi.instance().subscribeDeviceV2(str2, device.pid, Arguments.toList(readableArray), 3, new PluginSubscribeCallback() {
            private String c;

            public void onReceive(String str, String str2, JSONArray jSONArray) {
                ReactApplicationContext access$200 = MIOTDeviceModule.this.getReactApplicationContext();
                if (access$200 != null && jSONArray != null) {
                    LocalBroadcastManager.getInstance(access$200).sendBroadcast(new Intent().setAction(MIOTDeviceModule.DEVICESTATUSCHANGED).putExtra(MIOTDeviceModule.EMIT_DATA, jSONArray.toString()).putExtra(MIOTDeviceModule.EMIT_SUBID, this.c).putExtra(MIOTDeviceModule.EMIT_DID, str));
                }
            }

            public void onSuccess(String str) {
                this.c = str;
                RnPluginLog.a("   subscribeEvent : onSuccess" + str);
                if (callback != null) {
                    callback.invoke(true, str);
                }
            }

            public void onFailure(Error error) {
                RnPluginLog.b("   subscribeEvent : onFailure   " + error);
                if (callback != null) {
                    Callback callback = callback;
                    callback.invoke(false, "" + error);
                }
            }
        });
    }

    @ReactMethod
    public void unsubscribeMessages(String str, ReadableArray readableArray, String str2, final Callback callback) {
        DeviceStat device = getDevice();
        if (getPluginRecord() == null || device == null) {
            callback.invoke(false, "no device pluginRecord ");
            return;
        }
        String str3 = str;
        XmPluginHostApi.instance().unsubscribeDeviceV2(str3, device.pid, Arguments.toList(readableArray), str2, new PluginUnSubscribeCallback() {
            public void onSuccess() {
                if (callback != null) {
                    callback.invoke(true);
                }
                LogUtils.c(ReactConstants.TAG, "   unsubscribeEvent : onSuccess");
            }

            public void onFailure(Error error) {
                if (callback != null) {
                    callback.invoke(false);
                }
                LogUtils.c(ReactConstants.TAG, "   unsubscribeEvent : onFailure   " + error);
            }
        });
    }

    @ReactMethod
    public void readDeviceNetWorkInfo(String str, Callback callback) {
        if (callback != null) {
            DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
            if (deviceByDid != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("bssid", deviceByDid.bssid);
                writableNativeMap.putString(DeviceTagInterface.e, deviceByDid.ssid);
                writableNativeMap.putInt("rssi", deviceByDid.rssi);
                callback.invoke(true, writableNativeMap);
                return;
            }
            callback.invoke(false, "device info is null, please  insure your did is right!");
        }
    }

    @ReactMethod
    public void requestAuthorizedDeviceListData(String str, Callback callback) {
        List<DeviceStat> filterBluetoothDeviceList = XmPluginHostApi.instance().getFilterBluetoothDeviceList(str);
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (DeviceStat next : filterBluetoothDeviceList) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            device2Map(writableNativeMap, next);
            ProductInfo productInfo = XmPluginHostApi.instance().getProductInfo(next.model);
            writableNativeMap.putInt("product_id", productInfo.productId);
            writableNativeMap.putString("product_name", productInfo.name);
            writableNativeArray.pushMap(writableNativeMap);
        }
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putArray("result", writableNativeArray);
        callback.invoke(true, writableNativeMap2);
    }

    @ReactMethod
    public void reportDeviceGPSInfo(final Callback callback) {
        if (callback != null) {
            DeviceStat device = getDevice();
            if (device == null) {
                callback.invoke(false, "device is null...");
                return;
            }
            XmPluginHostApi.instance().reportDeviceGPSInfo(device.did, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    WritableMap createMap = Arguments.createMap();
                    if (jSONObject != null) {
                        createMap.putString("longitude", jSONObject.optString("longitude"));
                        createMap.putString("latitude", jSONObject.optString("latitude"));
                        createMap.putString("adminArea", jSONObject.optString("adminArea"));
                        createMap.putString(Constant.KEY_COUNTRY_CODE, jSONObject.optString(Constant.KEY_COUNTRY_CODE));
                        createMap.putString("locality", jSONObject.optString("locality"));
                        createMap.putString("thoroughfare", jSONObject.optString("thoroughfare"));
                        createMap.putString("subLocality", jSONObject.optString("subLocality"));
                        try {
                            DeviceStat device = MIOTDeviceModule.this.getDevice();
                            device.latitude = Double.parseDouble(jSONObject.optString("latitude"));
                            device.longitude = Double.parseDouble(jSONObject.optString("longitude"));
                        } catch (NumberFormatException e) {
                            RnPluginLog.b(e.toString());
                        }
                    }
                    callback.invoke(true, createMap);
                }

                public void onFailure(int i, String str) {
                    callback.invoke(false, str);
                }
            });
        }
    }

    @ReactMethod
    public void getLinkedBTDevices(String str, final Callback callback) {
        if (callback != null) {
            DeviceStat device = getDevice();
            if (device == null) {
                callback.invoke(false, "current device is null... ");
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (TextUtils.isEmpty(str) || (!str.equals(device.did) && !str.equals(device.parentId))) {
                callback.invoke(false, "did is empty...");
                return;
            }
            arrayList.add(str);
            XmPluginHostApi.instance().getBleGatewaySubDevices(arrayList, new com.xiaomi.smarthome.device.api.Callback<List<DeviceStat>>() {
                /* renamed from: a */
                public void onSuccess(List<DeviceStat> list) {
                    WritableArray createArray = Arguments.createArray();
                    WritableArray createArray2 = Arguments.createArray();
                    if (list != null && list.size() > 0) {
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            String string = list.get(i).property == null ? "" : list.get(i).property.getString("deviceGatewayType");
                            WritableMap createMap = Arguments.createMap();
                            list.get(i).rssi = MIOTDeviceModule.this.convertRssiToSignal(list.get(i).rssi);
                            MIOTDeviceModule.device2Map(createMap, list.get(i));
                            if ("mesh".equalsIgnoreCase(string)) {
                                createArray.pushMap(createMap);
                            } else {
                                createArray2.pushMap(createMap);
                            }
                        }
                    }
                    callback.invoke(true, MIOTDeviceModule.this.combineBleGatewayDevice(createArray, createArray2));
                }

                public void onFailure(int i, String str) {
                    callback.invoke(false, str);
                }
            });
        }
    }

    @ReactMethod
    public void getDeviceTimeZone(final String str, final Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke(false, "current activity is null...");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject.put("did", str);
            jSONArray.put("timezone");
            jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi((String) null, "/v2/device/get_extra_data", jSONObject.toString(), new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (TextUtils.isEmpty(str)) {
                    callback.invoke(true, Arguments.createMap());
                    return;
                }
                try {
                    String str2 = (String) new JSONObject(str).getJSONObject("result").getJSONObject("data").get("timezone");
                    if (TextUtils.isEmpty(str2)) {
                        callback.invoke(true, Arguments.createMap());
                        return;
                    }
                    String displayName = TimeZone.getTimeZone(str2).getDisplayName(false, 0);
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("timeZone", displayName);
                    createMap.putString("timeZoneId", str2);
                    createMap.putString("did", str);
                    callback.invoke(true, createMap);
                } catch (JSONException e) {
                    RnPluginLog.b(e.toString());
                    callback.invoke(false, e.toString());
                }
            }

            public void onFailure(int i, String str) {
                Callback callback = callback;
                callback.invoke(false, "error: " + i + ",  " + str);
            }
        }, PARSERFORRAWRESULT);
    }

    @ReactMethod
    public void changeDeviceName(final String str, String str2, final Callback callback) {
        DeviceStat device = getDevice();
        if (TextUtils.isEmpty(str2)) {
            if (device == null) {
                callback.invoke(false, "this did and current device is empty, change device name failed...");
                return;
            }
            str2 = device.did;
        } else if (device == null) {
            callback.invoke(false, "current device is empty, change device name failed...");
            return;
        } else {
            DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str2);
            if (deviceByDid == null || !device.did.equals(deviceByDid.parentId)) {
                callback.invoke(false, "this device is not current child device, change device name failed...");
                return;
            }
        }
        XmPluginHostApi.instance().modDeviceName(str2, str, new com.xiaomi.smarthome.device.api.Callback<Void>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                callback.invoke(true, str);
            }

            public void onFailure(int i, String str) {
                Callback callback = callback;
                callback.invoke(false, "errorCode: " + i + ",  errorInfo: " + str);
            }
        });
    }

    @ReactMethod
    public void getRecommendScenes(String str, String str2, final Callback callback) {
        if (callback != null) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                callback.invoke(false, "model or did is empty...");
                return;
            }
            XmPluginHostApi.instance().getRecommendScenes(str, str2, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    callback.invoke(true, jSONObject.toString());
                }

                public void onFailure(int i, String str) {
                    Callback callback = callback;
                    callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                }
            });
        }
    }

    @ReactMethod
    public void getCurrentDeviceValue(Callback callback) {
        DeviceStat device = getDevice();
        if (device == null) {
            callback.invoke(false, "device is null...");
            return;
        }
        JSONArray deviceProp = XmPluginHostApi.instance().getDeviceProp(device.did);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (deviceProp != null) {
            for (int i = 0; i < deviceProp.length(); i++) {
                try {
                    JSONObject jSONObject = deviceProp.getJSONObject(i);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (next != null) {
                            if (next.startsWith("prop.") || next.startsWith(Device.EVENT_PREFIX)) {
                                writableNativeMap.putString(next, jSONObject.optString(next));
                            } else {
                                writableNativeMap.putString("prop." + next, jSONObject.optString(next));
                            }
                        }
                    }
                } catch (JSONException unused) {
                }
            }
            callback.invoke(true, writableNativeMap);
            return;
        }
        callback.invoke(false, "deviceProp is null... ");
    }

    @ReactMethod
    public void sendKeyFramePayLoad(String str, int i, String str2, final Callback callback) {
        if (callback != null) {
            DeviceStat device = getDevice();
            if (device == null) {
                callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.DEVICE_ERROR));
            } else if (TextUtils.isEmpty(str)) {
                callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.PARAMS_ERROR, "params payload is empty..."));
            } else {
                LocalSceneBuilder.a().a(str, device, i, str2, new MessageCallback() {
                    public void onSuccess(Intent intent) {
                        if (intent == null) {
                            callback.invoke(true, "");
                            return;
                        }
                        String stringExtra = intent.getStringExtra("result");
                        try {
                            WritableMap createMap = Arguments.createMap();
                            MIOTUtils.a(new JSONObject(stringExtra), createMap);
                            callback.invoke(true, createMap);
                        } catch (Exception unused) {
                            callback.invoke(true, stringExtra);
                        }
                    }

                    public void onFailure(int i, String str) {
                        callback.invoke(false, RnCallbackMapUtil.a(i, str));
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public WritableMap combineBleGatewayDevice(WritableArray writableArray, WritableArray writableArray2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putArray("mesh", writableArray);
        createMap.putArray("normal", writableArray2);
        return createMap;
    }

    private static final String NVL(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String obj2 = obj.toString();
        return obj2 == null ? "" : obj2;
    }

    public static void device2Map(WritableMap writableMap, DeviceStat deviceStat) {
        convertDeviceStat2Map(writableMap, deviceStat);
        if (deviceStat != null && !TextUtils.isEmpty(deviceStat.parentId)) {
            writableMap.putMap("parentDevice", parentDvice2Map(deviceStat.parentId));
        }
    }

    private static WritableMap parentDvice2Map(String str) {
        WritableMap createMap = Arguments.createMap();
        if (TextUtils.isEmpty(str)) {
            return createMap;
        }
        convertDeviceStat2Map(createMap, XmPluginHostApi.instance().getDeviceByDid(str));
        return createMap;
    }

    private static void convertDeviceStat2Map(WritableMap writableMap, DeviceStat deviceStat) {
        if (deviceStat != null && writableMap != null) {
            writableMap.putString("model", NVL(deviceStat.model));
            writableMap.putString("did", NVL(deviceStat.did));
            writableMap.putString("mac", NVL(deviceStat.mac));
            writableMap.putString("name", NVL(deviceStat.name));
            writableMap.putInt("permitLevel", deviceStat.permitLevel);
            writableMap.putString("extrainfo", NVL(deviceStat.extrainfo));
            writableMap.putString("ownerName", NVL(deviceStat.ownerName));
            if (!TextUtils.isEmpty(deviceStat.ownerId) || !isOwner(deviceStat.permitLevel)) {
                writableMap.putString("ownerId", NVL(deviceStat.ownerId));
            } else {
                writableMap.putString("ownerId", NVL(XmPluginHostApi.instance().getAccountId()));
            }
            writableMap.putString("bssid", NVL(deviceStat.bssid));
            writableMap.putString("deviceIconReal", NVL(deviceStat.deviceIconReal));
            writableMap.putString("iconURL", NVL(deviceStat.deviceIconReal));
            writableMap.putString("event", NVL(deviceStat.event));
            writableMap.putString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, NVL(deviceStat.ip));
            writableMap.putBoolean("isOnline", deviceStat.isOnline);
            writableMap.putInt("isSetPinCode", deviceStat.isSetPinCode);
            writableMap.putString("parentId", NVL(deviceStat.parentId));
            writableMap.putString("parentModel", NVL(deviceStat.parentModel));
            writableMap.putString("lastModified", NVL(String.valueOf(deviceStat.lastModified)));
            writableMap.putString("session", NVL(MD5Util.a(NVL(deviceStat.token) + "4E60D36E")).toUpperCase());
            writableMap.putInt("location", deviceStat.location);
            writableMap.putString("latitude", Double.toString(deviceStat.latitude));
            writableMap.putString("longitude", Double.toString(deviceStat.longitude));
            writableMap.putString("pid", NVL(String.valueOf(deviceStat.pid)));
            writableMap.putString("rssi", NVL(String.valueOf(deviceStat.rssi)));
            writableMap.putInt("resetFlag", deviceStat.resetFlag);
            writableMap.putString(DeviceTagInterface.e, NVL(deviceStat.ssid));
            writableMap.putString("version", NVL(deviceStat.version));
            writableMap.putInt(Message.SHOW_MODE, deviceStat.showMode);
            writableMap.putString("propInfo", NVL(deviceStat.propInfo));
        }
    }

    private static boolean isOwner(int i) {
        return ((i & PERMISSION_NONE_MASK) & PERMISSION_OWNER) != 0;
    }
}
