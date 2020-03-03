package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BtConstants;
import com.xiaomi.smarthome.device.api.BleMeshFirmwareUpdateInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BluetoothManager;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattService;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.UUIDUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.stat.STAT;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MIOTBluetoothModule extends MIOTBaseJavaModule {
    public static final String DISCOVERCHARACTERISTICS_CALLBACK = "discovercharacteristics_callback";
    public static final String DISCOVERSERVICES_CALLBACK = "discoverservices_callback";
    public static final String EMIT_MAC = "emit_mac";
    public static final String EMIT_SERVICEID = "emit_serviceid";
    public static final String EMIT_UUID = "emit_uuid";
    private static final int MSG_TIMEOUT = 1001;
    private static final int PERMISSION_NONE_MASK = 30;
    private static final int PERMISSION_OWNER = 16;
    public static final String STARTSCAN_CALLBACK = "startscan_callback";
    private boolean connect;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private Handler mHandler;
    /* access modifiers changed from: private */
    public volatile boolean mIsTimeOut = false;
    private Map<String, UUID> mUUIDCacheMap = new HashMap();

    public String getName() {
        return "MIOTBluetooth";
    }

    public MIOTBluetoothModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private void initHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler() {
                public void handleMessage(Message message) {
                    if (message.what == 1001 && MIOTBluetoothModule.this.mCallback != null) {
                        boolean unused = MIOTBluetoothModule.this.mIsTimeOut = true;
                        MIOTBluetoothModule.this.mCallback.invoke(false, "timeout...");
                        Callback unused2 = MIOTBluetoothModule.this.mCallback = null;
                    }
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public void clearHandler() {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(1001);
        }
        this.mHandler = null;
        this.mCallback = null;
    }

    public boolean isConnect() {
        return this.connect;
    }

    @ReactMethod
    public void connect(final String str, final int i, ReadableMap readableMap, final Callback callback) {
        connectDevice(str, i, readableMap, new Callback() {
            public void invoke(Object... objArr) {
                if (callback != null) {
                    String a2 = BluetoothManager.a(str);
                    if (objArr[0].booleanValue()) {
                        STAT.i.a(a2, "login");
                    } else {
                        try {
                            WritableMap writableMap = objArr[1];
                            if (writableMap != null) {
                                STAT.i.a(a2, "login", writableMap.getInt("code"), "", String.valueOf(i));
                            }
                        } catch (Exception unused) {
                        }
                    }
                    callback.invoke(objArr);
                }
            }
        });
    }

    public void connectDevice(String str, int i, ReadableMap readableMap, Callback callback) {
        PluginDeviceInfo c;
        final String str2 = str;
        ReadableMap readableMap2 = readableMap;
        final Callback callback2 = callback;
        PluginRecord pluginRecord = getPluginRecord();
        if (pluginRecord == null) {
            callback2.invoke(false, "no pluginRecord");
            return;
        }
        int i2 = i;
        int i3 = i2 == -1 ? (pluginRecord == null || (c = pluginRecord.c()) == null || c.I() != 1) ? 0 : ((getDevice().permitLevel & 30) & 16) != 0 ? 1 : 2 : i2;
        double a2 = MIOTUtils.a(readableMap2, "timeout", 35000.0d);
        int a3 = MIOTUtils.a(readableMap2, "discoverRetry", 2);
        double max = (double) Math.max(a3, 1);
        Double.isNaN(max);
        double a4 = MIOTUtils.a(readableMap2, "discoverTimeout", a2 / max);
        int a5 = MIOTUtils.a(readableMap2, "connectRetry", 1);
        boolean b = MIOTUtils.b(readableMap2, "checkFirmwareVersion");
        BleConnectOptions a6 = new BleConnectOptions.Builder().a(a5).c((int) a2).b(a3).d((int) a4).a();
        if (i3 == 0) {
            final String str3 = str;
            final ReadableMap readableMap3 = readableMap;
            final Callback callback3 = callback;
            final int i4 = i;
            final boolean z = b;
            final int i5 = i3;
            BluetoothHelper.a(str2, a6, (Response.BleConnectResponse) new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    BluetoothLog.a("connect  " + i + "  " + bundle);
                    if (-10 == i) {
                        XmBluetoothManager.getInstance().removeToken(str3);
                        MIOTBluetoothModule.this.connect(str3, 0, readableMap3, callback3);
                    } else if (i4 != -1) {
                        MIOTBluetoothModule.this.callbackBundle(i, bundle, callback3);
                        if (i == 0 && z) {
                            MIOTBluetoothModule.checkFirmwareVersion(i5, str3);
                        }
                    } else if (i == 0) {
                        MIOTBluetoothModule.this.callbackBundle(i, bundle, callback3);
                        if (i == 0 && z) {
                            MIOTBluetoothModule.checkFirmwareVersion(i5, str3);
                        }
                    } else {
                        MIOTBluetoothModule.this.connect(str3, 3, readableMap3, callback3);
                    }
                }
            });
        } else if (i3 == 1) {
            final Callback callback4 = callback;
            final boolean z2 = b;
            final int i6 = i3;
            final String str4 = str;
            BluetoothHelper.c(str2, a6, new IBleSecureConnectResponse() {
                public void a(int i, Bundle bundle) {
                }

                public void b(int i, Bundle bundle) {
                }

                public void c(int i, Bundle bundle) {
                }

                public void d(int i, Bundle bundle) {
                    BluetoothLog.a("connect  " + i + "  " + bundle);
                    MIOTBluetoothModule.this.callbackBundle(i, bundle, callback4);
                    if (i == 0 && z2) {
                        MIOTBluetoothModule.checkFirmwareVersion(i6, str4);
                    }
                }
            });
        } else if (i3 == 2) {
            BluetoothHelper.b(str2, a6, new IBleSecureConnectResponse() {
                public void a(int i, Bundle bundle) {
                }

                public void b(int i, Bundle bundle) {
                }

                public void c(int i, Bundle bundle) {
                }

                public void d(int i, Bundle bundle) {
                    if (i == 0) {
                        Intent intent = new Intent("action.online.status.changed");
                        intent.putExtra("extra_mac", str2);
                        intent.putExtra("extra_online_status", 80);
                        BluetoothUtils.a(intent);
                    }
                    BluetoothLog.a("connect  " + i + "  " + bundle);
                    MIOTBluetoothModule.this.callbackBundle(i, bundle, callback2);
                }
            });
        } else if (i3 == 3) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(BtConstants.d, a6);
            CoreApi.a().a(str2, 1, bundle, (IBleResponse) new BluetoothResponse() {
                public void onResponse(int i, Bundle bundle) throws RemoteException {
                    BluetoothLog.a("connect  " + i + "  " + bundle);
                    MIOTBluetoothModule.this.callbackBundle(i, bundle, callback2);
                }
            });
        } else if (i3 == 4) {
            final Callback callback5 = callback;
            final boolean z3 = b;
            final int i7 = i3;
            final String str5 = str;
            BluetoothHelper.e(str2, a6, new IBleSecureConnectResponse() {
                public void a(int i, Bundle bundle) {
                }

                public void b(int i, Bundle bundle) {
                }

                public void c(int i, Bundle bundle) {
                }

                public void d(int i, Bundle bundle) {
                    MIOTBluetoothModule.this.callbackBundle(i, bundle, callback5);
                    if (i == 0 && z3) {
                        MIOTBluetoothModule.checkFirmwareVersion(i7, str5);
                    }
                }
            });
        } else if (i3 == 5) {
            String a7 = MIOTUtils.a(readableMap2, "did");
            BluetoothMyLogger.e("ble mesh did =" + a7);
            XmBluetoothManager.getInstance().bleMeshConnect(str2, a7, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    MIOTBluetoothModule.this.callbackBundle(i, bundle, callback2);
                }
            });
        }
    }

    @ReactMethod
    public void getVersion(final String str, boolean z, final Callback callback) {
        if (getPluginRecord() == null) {
            callback.invoke(false, "no device pluginRecord ");
            return;
        }
        XmBluetoothManager.getInstance().read(str, BluetoothConstants.w, BluetoothConstants.y, new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, final byte[] bArr) {
                if (i != 0 || ByteUtils.e(bArr)) {
                    MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
                } else {
                    XmBluetoothManager.getInstance().isBleCharacterExist(str, com.xiaomi.smarthome.library.bluetooth.BluetoothConstants.i, com.xiaomi.smarthome.library.bluetooth.BluetoothConstants.H, new Response.BleResponse<Void>() {
                        /* renamed from: a */
                        public void onResponse(int i, Void voidR) {
                            byte[] bArr = bArr;
                            if (i == 0) {
                                RnPluginLog.a("miio-bluetooth getVersion RC4 需要解密...");
                                bArr = BLECipher.a(BleCacheUtils.q(str), bArr);
                            } else {
                                RnPluginLog.a("miio-bluetooth getVersion 非RC4 不需要解密...");
                            }
                            byte[] b2 = ByteUtils.b(bArr, (byte) 0);
                            callback.invoke(true, new String(b2));
                        }
                    });
                }
            }
        });
    }

    public void callbackBundle(int i, Bundle bundle, Callback callback) {
        UUID a2;
        UUID uuid;
        if (callback != null) {
            WritableMap createMap = Arguments.createMap();
            if (bundle != null) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj == null) {
                        createMap.putNull(str);
                    } else if (obj instanceof byte[]) {
                        createMap.putString(str, MIOTUtils.a((byte[]) obj));
                    } else if (obj.getClass().isArray()) {
                        try {
                            createMap.putArray(str, Arguments.fromArray(obj));
                        } catch (Throwable unused) {
                        }
                    } else if (obj instanceof String) {
                        createMap.putString(str, (String) obj);
                    } else if (obj instanceof Number) {
                        if (obj instanceof Integer) {
                            createMap.putInt(str, ((Integer) obj).intValue());
                        } else {
                            createMap.putDouble(str, ((Number) obj).doubleValue());
                        }
                    } else if (obj instanceof Boolean) {
                        createMap.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof List) {
                        createMap.putArray(str, Arguments.fromList((List) obj));
                    } else if (obj instanceof BleGattProfile) {
                        WritableArray createArray = Arguments.createArray();
                        for (BleGattService next : ((BleGattProfile) obj).a()) {
                            if (!(next == null || (a2 = next.a()) == null)) {
                                WritableMap createMap2 = Arguments.createMap();
                                String lowerCase = a2.toString().toLowerCase();
                                this.mUUIDCacheMap.put(lowerCase, a2);
                                createMap2.putString("uuid", lowerCase);
                                List<ParcelUuid> b = next.b();
                                if (b != null) {
                                    WritableArray createArray2 = Arguments.createArray();
                                    for (ParcelUuid next2 : b) {
                                        if (!(next2 == null || (uuid = next2.getUuid()) == null)) {
                                            String lowerCase2 = uuid.toString().toLowerCase();
                                            this.mUUIDCacheMap.put(lowerCase2, uuid);
                                            createArray2.pushString(lowerCase2);
                                        }
                                    }
                                    createMap2.putArray("chars", createArray2);
                                    createArray.pushMap(createMap2);
                                }
                            }
                        }
                        createMap.putArray("services", createArray);
                    }
                }
            }
            createMap.putInt("code", i);
            if (i != -55) {
                switch (i) {
                    case -35:
                        createMap.putString("msg", "REQUEST_SC_REGISTER_PAIR_CODE_EXPIRED");
                        callback.invoke(false, createMap);
                        return;
                    case -34:
                        createMap.putString("msg", "REQUEST_SC_REGISTER_PAIR_CODE_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -33:
                        createMap.putString("msg", "REQUEST_SC_REGISTER_INPUT_PAIR_CODE");
                        callback.invoke(false, createMap);
                        return;
                    case -32:
                        createMap.putString("msg", "REQUEST_STATUS_DISCONNECTED");
                        callback.invoke(false, createMap);
                        return;
                    case -31:
                        createMap.putString("msg", "REQUEST_TOKEN_VERIFY_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -30:
                        createMap.putString("msg", "REQUEST_BIND_DID_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -29:
                        createMap.putString("msg", "REQUEST_GET_DID_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -28:
                        createMap.putString("msg", "REQUEST_WRITE_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -27:
                        createMap.putString("msg", "REQUEST_NOTIFY_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -26:
                        createMap.putString("msg", "REQUEST_SC_BIND_LTMK_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -25:
                        createMap.putString("msg", "REQUEST_SC_SHARED_LOGIN_KEY_ID_EMPTY");
                        callback.invoke(false, createMap);
                        return;
                    case -24:
                        createMap.putString("msg", "REQUEST_SC_SHARED_LOGIN_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -23:
                        createMap.putString("msg", "REQUEST_SC_SHARED_LOGIN_ENCRYPT_DATA_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -22:
                        createMap.putString("msg", "REQUEST_SC_LOGIN_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -21:
                        createMap.putString("msg", "REQUEST_SC_LOGIN_ENCRYPT_DATA_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -20:
                        createMap.putString("msg", "REQUEST_SC_REGISTER_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -19:
                        createMap.putString("msg", "REQUEST_SC_SHARED_KEY_FAILED");
                        callback.invoke(false, createMap);
                        return;
                    case -18:
                        createMap.putString("msg", "REQUEST_SHARED_KEY_EXPIRED");
                        callback.invoke(false, createMap);
                        return;
                    case -17:
                        createMap.putString("msg", "REQUEST_REGISTERED");
                        callback.invoke(false, createMap);
                        return;
                    case -16:
                        createMap.putString("msg", "REQUEST_NOT_REGISTERED");
                        callback.invoke(false, createMap);
                        return;
                    case -15:
                        createMap.putString("msg", "REQUEST_EXCEPTION");
                        callback.invoke(false, createMap);
                        return;
                    case -14:
                        createMap.putString("msg", "REQUEST_DENIED");
                        callback.invoke(false, createMap);
                        return;
                    case -13:
                        createMap.putString("msg", "REQUEST_ONGOING");
                        callback.invoke(false, createMap);
                        return;
                    case -12:
                        createMap.putString("msg", "CONFIG_UNREADY");
                        callback.invoke(false, createMap);
                        return;
                    case -11:
                        createMap.putString("msg", "REQUEST_OVERFLOW");
                        callback.invoke(false, createMap);
                        return;
                    case -10:
                        createMap.putString("msg", "TOKEN_NOT_MATCHED");
                        callback.invoke(false, createMap);
                        return;
                    default:
                        switch (i) {
                            case -7:
                                break;
                            case -6:
                                createMap.putString("msg", "CONNECTION_NOT_READY");
                                callback.invoke(false, createMap);
                                return;
                            case -5:
                                createMap.putString("msg", "BLUETOOTH_DISABLED");
                                callback.invoke(false, createMap);
                                return;
                            case -4:
                                createMap.putString("msg", "BLE_NOT_SUPPORTED");
                                callback.invoke(false, createMap);
                                return;
                            case -3:
                                createMap.putString("msg", "ILLEGAL_ARGUMENT");
                                callback.invoke(false, createMap);
                                return;
                            case -2:
                                createMap.putString("msg", "REQUEST_CANCELED");
                                callback.invoke(false, createMap);
                                return;
                            case -1:
                                createMap.putString("msg", "REQUEST_FAILED");
                                callback.invoke(false, createMap);
                                return;
                            case 0:
                                callback.invoke(true, createMap);
                                this.connect = true;
                                return;
                            default:
                                createMap.putString("msg", "unknow");
                                callback.invoke(false, createMap);
                                return;
                        }
                }
            }
            createMap.putString("msg", "REQUEST_TIMEDOUT");
            callback.invoke(false, createMap);
        }
    }

    @ReactMethod
    public void discoverServices(String str, ReadableArray readableArray) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null && readableArray != null) {
            String[] strArr = new String[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                strArr[i] = readableArray.getString(i);
            }
            LocalBroadcastManager.getInstance(reactApplicationContext).sendBroadcast(new Intent().setAction(DISCOVERSERVICES_CALLBACK).putExtra(EMIT_MAC, str).putExtra(EMIT_UUID, strArr));
        }
    }

    @ReactMethod
    public void discoverCharacteristics(String str, ReadableArray readableArray, String str2) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext != null && readableArray != null) {
            String[] strArr = new String[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                strArr[i] = readableArray.getString(i);
            }
            LocalBroadcastManager.getInstance(reactApplicationContext).sendBroadcast(new Intent().setAction(DISCOVERCHARACTERISTICS_CALLBACK).putExtra(EMIT_MAC, str).putExtra(EMIT_SERVICEID, str2).putExtra(EMIT_UUID, strArr));
        }
    }

    @ReactMethod
    public void bindDevice(String str) {
        XmBluetoothManager.getInstance().bindDevice(str);
    }

    @ReactMethod
    public void unBindDevice(String str) {
        XmBluetoothManager.getInstance().unBindDevice(str);
    }

    @ReactMethod
    public void call(String str, int i, ReadableMap readableMap, final Callback callback) {
        XmBluetoothManager.getInstance().call(str, i, Arguments.toBundle(readableMap), new Response.BleCallResponse() {
            /* renamed from: a */
            public void onResponse(int i, Bundle bundle) {
                if (callback == null) {
                    return;
                }
                if (i == 0) {
                    callback.invoke(true, Arguments.createMap());
                    return;
                }
                MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
            }
        });
    }

    @ReactMethod
    public void oneTimePassword(String str, int i, int i2, final Callback callback) {
        XmBluetoothManager.getInstance().getOneTimePassword(str, i, i2, new Response.BleResponseV2<int[]>() {
            /* renamed from: a */
            public void onResponse(int i, String str, int[] iArr) {
                if (callback == null) {
                    return;
                }
                if (i == 0) {
                    Callback callback = callback;
                    Object[] objArr = new Object[2];
                    objArr[0] = true;
                    Object obj = str;
                    if (iArr != null) {
                        obj = Arguments.fromArray(iArr);
                    }
                    objArr[1] = obj;
                    callback.invoke(objArr);
                    return;
                }
                MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
            }
        });
    }

    @ReactMethod
    public void getTokenMd5(String str, Callback callback) {
        callback.invoke(XmBluetoothManager.getInstance().getTokenMd5(str));
    }

    @ReactMethod
    public void removeToken(String str) {
        XmBluetoothManager.getInstance().removeToken(str);
    }

    @ReactMethod
    public void isAutoReconnect(String str, Callback callback) {
        callback.invoke(Boolean.valueOf(XmBluetoothManager.getInstance().isAutoReconnect(str)));
    }

    @ReactMethod
    public void isBluetoothOpen(Callback callback) {
        callback.invoke(Boolean.valueOf(XmBluetoothManager.getInstance().isBluetoothOpen()));
    }

    @ReactMethod
    public void openBluetooth(boolean z) {
        if (z) {
            XmBluetoothManager.getInstance().openBluetoothSilently();
        } else {
            XmBluetoothManager.getInstance().openBluetooth(getCurrentActivity());
        }
    }

    @ReactMethod
    public void isShareSecureKeyValid(String str, Callback callback) {
        try {
            callback.invoke(Integer.valueOf(XmBluetoothManager.getInstance().isSecurityChipSharedKeyValid(str) ? 1 : 0));
        } catch (Throwable th) {
            callback.invoke(0, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public void isBleGatewayConnected(String str, final Callback callback) {
        XmBluetoothManager.getInstance().isBleGatewayConnected(str, new Response.BleResponse<Void>() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (callback != null) {
                    callback.invoke(Integer.valueOf(i));
                }
            }
        });
    }

    @ReactMethod
    public void readHexStringWithCallback(String str, String str2, String str3, final Callback callback) {
        XmBluetoothManager.getInstance().read(str, getUUid(str3), getUUid(str2), new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                if (callback == null) {
                    return;
                }
                if (i == 0) {
                    callback.invoke(true, MIOTUtils.a(bArr));
                    return;
                }
                MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
            }
        });
    }

    @ReactMethod
    public void readRSSI(String str, final Callback callback) {
        XmBluetoothManager.getInstance().readRemoteRssi(str, new Response.BleReadRssiResponse() {
            /* renamed from: a */
            public void onResponse(int i, Integer num) {
                Callback callback = callback;
                Object[] objArr = new Object[2];
                objArr[0] = Boolean.valueOf(i == 0);
                objArr[1] = num;
                callback.invoke(objArr);
            }
        });
    }

    @ReactMethod
    public void writeHexStringWithCallback(String str, String str2, String str3, String str4, int i, Callback callback) {
        String str5 = str3;
        String str6 = str4;
        final Callback callback2 = callback;
        if (i == 1) {
            XmBluetoothManager.getInstance().writeNoRsp(str, getUUid(str6), getUUid(str5), MIOTUtils.a(str2), new Response.BleWriteResponse() {
                /* renamed from: a */
                public void onResponse(int i, Void voidR) {
                    if (callback2 != null) {
                        Callback callback = callback2;
                        Object[] objArr = new Object[2];
                        objArr[0] = Boolean.valueOf(i == 0);
                        objArr[1] = XmBluetoothManager.Code.toString(i);
                        callback.invoke(objArr);
                    }
                }
            });
            return;
        }
        XmBluetoothManager.getInstance().write(str, getUUid(str6), getUUid(str5), MIOTUtils.a(str2), new Response.BleWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (callback2 != null) {
                    Callback callback = callback2;
                    Object[] objArr = new Object[2];
                    objArr[0] = Boolean.valueOf(i == 0);
                    objArr[1] = XmBluetoothManager.Code.toString(i);
                    callback.invoke(objArr);
                }
            }
        });
    }

    @ReactMethod
    public void writeBlock(String str, String str2, final Callback callback) {
        XmBluetoothManager.getInstance().writeBlock(str, MIOTUtils.a(str2), new Response.BleWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (callback != null) {
                    callback.invoke(Integer.valueOf(i));
                }
            }
        });
    }

    @ReactMethod
    public void setNotifyWithCallback(String str, boolean z, String str2, String str3, final Callback callback) {
        BluetoothLog.c("miot-->setNotify...mac is " + str + "  flag is " + z + " serviceId is " + str3 + "  characterId is " + str2);
        if (z) {
            XmBluetoothManager.getInstance().notify(str, getUUid(str3), getUUid(str2), new Response.BleNotifyResponse() {
                /* renamed from: a */
                public void onResponse(int i, Void voidR) {
                    if (callback != null) {
                        Callback callback = callback;
                        Object[] objArr = new Object[2];
                        objArr[0] = Boolean.valueOf(i == 0);
                        objArr[1] = XmBluetoothManager.Code.toString(i);
                        callback.invoke(objArr);
                    }
                }
            });
            return;
        }
        XmBluetoothManager.getInstance().unnotify(str, getUUid(str3), getUUid(str2));
        callback.invoke(true, 0);
    }

    @ReactMethod
    public void setIndicationWithCallback(String str, boolean z, String str2, String str3, final Callback callback) {
        if (z) {
            XmBluetoothManager.getInstance().indication(str, getUUid(str3), getUUid(str2), new Response.BleNotifyResponse() {
                /* renamed from: a */
                public void onResponse(int i, Void voidR) {
                    if (callback != null) {
                        Callback callback = callback;
                        Object[] objArr = new Object[2];
                        objArr[0] = Boolean.valueOf(i == 0);
                        objArr[1] = XmBluetoothManager.Code.toString(i);
                        callback.invoke(objArr);
                    }
                }
            });
            return;
        }
        XmBluetoothManager.getInstance().unindication(str, getUUid(str3), getUUid(str2));
        callback.invoke(true, 0);
    }

    @ReactMethod
    public void registerBlockListener(String str, final Callback callback) {
        XmBluetoothManager.getInstance().registerBlockListener(str, new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                if (callback == null) {
                    return;
                }
                if (i == 0) {
                    callback.invoke(true, MIOTUtils.a(bArr));
                    return;
                }
                MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
            }
        });
    }

    @ReactMethod
    public void unregisterBlockListener(String str) {
        XmBluetoothManager.getInstance().unregisterBlockListener(str);
    }

    @ReactMethod
    public void unregisterCharacterChanged(String str, String str2, String str3) {
        XmBluetoothManager.getInstance().unregisterCharacterChanged(str, getUUid(str2), getUUid(str3));
    }

    @ReactMethod
    public void registerCharacterChanged(String str, String str2, String str3, final Callback callback) {
        XmBluetoothManager.getInstance().registerCharacterChanged(str, getUUid(str2), getUUid(str3), new Response.BleWriteResponse() {
            /* renamed from: a */
            public void onResponse(int i, Void voidR) {
                if (callback != null) {
                    callback.invoke(Integer.valueOf(i));
                }
            }
        });
    }

    @ReactMethod
    public void registerMediaButtonReceiver(String str) {
        XmBluetoothManager.getInstance().registerMediaButtonReceiver(str);
    }

    @ReactMethod
    public void unRegisterMediaButtonReceiver(String str) {
        XmBluetoothManager.getInstance().unRegisterMediaButtonReceiver(str);
    }

    @ReactMethod
    public void decryptMessageXiaoMiBLE(String str, String str2, final Callback callback) {
        XmBluetoothManager.getInstance().securityChipDecrypt(str, MIOTUtils.a(str2), new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                if (callback == null) {
                    return;
                }
                if (i == 0) {
                    callback.invoke(true, MIOTUtils.a(bArr));
                    return;
                }
                MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
            }
        });
    }

    @ReactMethod
    public void encryptMessageXiaoMiBLE(String str, String str2, final Callback callback) {
        XmBluetoothManager.getInstance().securityChipEncrypt(str, MIOTUtils.a(str2), new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                if (callback == null) {
                    return;
                }
                if (i == 0) {
                    callback.invoke(true, MIOTUtils.a(bArr));
                    return;
                }
                MIOTBluetoothModule.this.callbackBundle(i, new Bundle(), callback);
            }
        });
    }

    @ReactMethod
    public void toggleLockXiaoMiBLE(String str, int i, int i2, final Callback callback) {
        initHandler();
        final long currentTimeMillis = System.currentTimeMillis();
        this.mIsTimeOut = false;
        if (this.mHandler != null && i2 > 0) {
            this.mCallback = callback;
            this.mHandler.sendEmptyMessageDelayed(1001, (long) i2);
        }
        XmBluetoothManager.getInstance().securityChipOperate(str, i, new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                RnPluginLog.a("toggle cost time:  " + (System.currentTimeMillis() - currentTimeMillis));
                MIOTBluetoothModule.this.clearHandler();
                if (MIOTBluetoothModule.this.mIsTimeOut) {
                    RnPluginLog.b("toggle is timeout...");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putByteArray("result", bArr);
                MIOTBluetoothModule.this.callbackBundle(i, bundle, callback);
            }
        });
    }

    @ReactMethod
    public void setAlertConfigs(String str, int i, boolean z, Callback callback) {
        boolean alertConfigs = XmBluetoothManager.getInstance().setAlertConfigs(str, i, z);
        if (callback != null) {
            callback.invoke(Boolean.valueOf(alertConfigs));
        }
    }

    @ReactMethod
    public void setAutoReconnect(String str, boolean z, Callback callback) {
        boolean autoReconnect = XmBluetoothManager.getInstance().setAutoReconnect(str, z);
        if (callback != null) {
            callback.invoke(Boolean.valueOf(autoReconnect));
        }
    }

    @Deprecated
    @ReactMethod
    public void startScan(int i, int i2) {
        XmBluetoothManager.getInstance().startScan(i, i2, new XmBluetoothManager.BluetoothSearchResponse() {
            public void onSearchCanceled() {
            }

            public void onSearchStarted() {
            }

            public void onSearchStopped() {
            }

            public void onDeviceFounded(XmBluetoothDevice xmBluetoothDevice) {
                ReactApplicationContext access$300 = MIOTBluetoothModule.this.getReactApplicationContext();
                if (access$300 != null && xmBluetoothDevice != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", xmBluetoothDevice.name);
                    bundle.putInt("deviceType", xmBluetoothDevice.deviceType);
                    bundle.putInt("rssi", xmBluetoothDevice.rssi);
                    bundle.putString("scanRecord", MIOTUtils.a(xmBluetoothDevice.scanRecord));
                    bundle.putBoolean("isConnected", xmBluetoothDevice.isConnected);
                    String address = xmBluetoothDevice.getAddress();
                    bundle.putString("address", address);
                    bundle.putString("mac", address);
                    bundle.putString("uuid", address);
                    LocalBroadcastManager.getInstance(access$300).sendBroadcast(new Intent().setAction(MIOTBluetoothModule.STARTSCAN_CALLBACK).putExtras(bundle));
                }
            }
        });
    }

    @ReactMethod
    public void startLeScan(int i, ReadableArray readableArray) {
        final int size = readableArray == null ? 0 : readableArray.size();
        UUID[] uuidArr = null;
        if (size != 0) {
            uuidArr = new UUID[size];
            for (int i2 = 0; i2 < size; i2++) {
                uuidArr[i2] = getUUid(readableArray.getString(i2));
            }
        }
        XmBluetoothManager.getInstance().startLeScan(i, uuidArr, new XmBluetoothManager.BluetoothSearchResponse() {
            public void onSearchCanceled() {
            }

            public void onSearchStarted() {
            }

            public void onSearchStopped() {
            }

            public void onDeviceFounded(XmBluetoothDevice xmBluetoothDevice) {
                Log.i("ReactNativeModule", size + "> startLeScan:" + xmBluetoothDevice);
                ReactApplicationContext access$400 = MIOTBluetoothModule.this.getReactApplicationContext();
                if (access$400 != null && xmBluetoothDevice != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name", xmBluetoothDevice.name);
                    bundle.putInt("deviceType", xmBluetoothDevice.deviceType);
                    bundle.putInt("rssi", xmBluetoothDevice.rssi);
                    bundle.putString("scanRecord", MIOTUtils.a(xmBluetoothDevice.scanRecord));
                    bundle.putBoolean("isConnected", xmBluetoothDevice.isConnected);
                    String address = xmBluetoothDevice.getAddress();
                    bundle.putString("address", xmBluetoothDevice.getAddress());
                    bundle.putString("mac", address);
                    bundle.putString("uuid", address);
                    LocalBroadcastManager.getInstance(access$400).sendBroadcast(new Intent().setAction(MIOTBluetoothModule.STARTSCAN_CALLBACK).putExtras(bundle));
                }
            }
        });
    }

    @ReactMethod
    public void stopScan() {
        XmBluetoothManager.getInstance().stopScan();
    }

    @ReactMethod
    public void disconnectDeviceWithDelay(String str, double d) {
        this.connect = false;
        if (d > 0.0d) {
            XmBluetoothManager.getInstance().disconnect(str, (long) d);
        } else {
            XmBluetoothManager.getInstance().disconnect(str);
        }
    }

    @ReactMethod
    public void miotBleEncrypt(String str, String str2, final Callback callback) {
        XmBluetoothManager.getInstance().miotBleEncrypt(str, MIOTUtils.a(str2), new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("result", bArr);
                MIOTBluetoothModule.this.callbackBundle(i, bundle, callback);
            }
        });
    }

    @ReactMethod
    public void miotBleDecrypt(String str, String str2, final Callback callback) {
        XmBluetoothManager.getInstance().miotBleDecrypt(str, MIOTUtils.a(str2), new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, byte[] bArr) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("result", bArr);
                MIOTBluetoothModule.this.callbackBundle(i, bundle, callback);
            }
        });
    }

    private UUID getUUid(String str) {
        if (str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        UUID uuid = this.mUUIDCacheMap.get(lowerCase);
        if (uuid != null) {
            return uuid;
        }
        Map<String, UUID> map = this.mUUIDCacheMap;
        UUID fromString = lowerCase.contains("-") ? UUID.fromString(lowerCase) : UUIDUtils.a(lowerCase);
        map.put(lowerCase, fromString);
        return fromString;
    }

    public void onCatalystInstanceDestroy() {
        clearHandler();
        super.onCatalystInstanceDestroy();
    }

    public static void checkFirmwareVersion(final int i, final String str) {
        readFirmwareVersion(str, new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                MIOTBluetoothModule.requestFirmwareUpgradeInfo(str, i, BleCacheUtils.j(str), BleCacheUtils.e(str));
            }

            public void onFailure(int i, String str) {
                MIOTBluetoothModule.sendCheckUpgradeResultBroadcast(false, (String) null, (String) null, false, str);
            }
        });
    }

    private static void sendEventToJs(String str, WritableMap writableMap) {
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

    /* access modifiers changed from: private */
    public static void sendCheckUpgradeResultBroadcast(boolean z, String str, String str2, boolean z2, String str3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("request_success", z);
        if (!TextUtils.isEmpty(str)) {
            createMap.putString("current_version", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            createMap.putString("latest_version", str2);
        }
        createMap.putBoolean("support_common_ble_ota_upgrade", z2);
        if (!TextUtils.isEmpty(str3)) {
            createMap.putString("error_msg", str3);
        }
        sendEventToJs("action.com.miot.check.upgrade.result", createMap);
    }

    public static void requestFirmwareUpgradeInfo(final String str, final int i, String str2, String str3) {
        XmPluginHostApi.instance().getBleMeshFirmwareUpdateInfo(str2, str3, new com.xiaomi.smarthome.device.api.Callback<BleMeshFirmwareUpdateInfo>() {
            /* renamed from: a */
            public void onSuccess(BleMeshFirmwareUpdateInfo bleMeshFirmwareUpdateInfo) {
                MIOTBluetoothModule.sendCheckUpgradeResultBroadcast(true, str, bleMeshFirmwareUpdateInfo.version, BluetoothHelper.a(i, str), (String) null);
            }

            public void onFailure(int i, String str) {
                MIOTBluetoothModule.sendCheckUpgradeResultBroadcast(false, (String) null, (String) null, false, str);
            }
        });
    }

    public static void readFirmwareVersion(final String str, final com.xiaomi.smarthome.device.api.Callback<String> callback) {
        XmBluetoothManager.getInstance().read(str, BluetoothConstants.w, BluetoothConstants.y, new Response.BleReadResponse() {
            /* renamed from: a */
            public void onResponse(int i, final byte[] bArr) {
                if (i != 0 || ByteUtils.e(bArr)) {
                    callback.onFailure(-1, "read firmware version fail");
                } else {
                    XmBluetoothManager.getInstance().isBleCharacterExist(str, com.xiaomi.smarthome.library.bluetooth.BluetoothConstants.i, com.xiaomi.smarthome.library.bluetooth.BluetoothConstants.H, new Response.BleResponse<Void>() {
                        /* renamed from: a */
                        public void onResponse(int i, Void voidR) {
                            byte[] bArr = bArr;
                            if (i == 0) {
                                bArr = BLECipher.a(BleCacheUtils.q(str), bArr);
                            }
                            byte[] b2 = ByteUtils.b(bArr, (byte) 0);
                            if (callback != null) {
                                callback.onSuccess(new String(b2));
                            }
                        }
                    });
                }
            }
        });
    }
}
