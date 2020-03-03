package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchResult;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchTask;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.CoreAsyncTask;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.core.server.bluetooth.SearchResponse;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.ChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.channel.IPCChannelManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizeResult;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizer;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.IBluetoothRecognizeResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.DeviceApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipOperate;
import com.xiaomi.smarthome.core.server.internal.device.DeviceManager;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.statistic.StatManager;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.bluetooth.security.BLECipher;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.SmartHomeApiParser;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattService;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadRssiResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchHelper;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchRequest;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BluetoothApi {

    /* renamed from: a  reason: collision with root package name */
    private static final int f14105a = 1;
    private static final int b = 2;
    private static final long c = 10000;
    private static final int d = 10000;
    /* access modifiers changed from: private */
    public static List<DisconnectionHolder> e = new ArrayList();
    /* access modifiers changed from: private */
    public static Handler f = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    BluetoothApi.j();
                    BluetoothApi.f.sendEmptyMessageDelayed(1, 10000);
                    return;
                case 2:
                    Bundle data = message.getData();
                    DisconnectionHolder disconnectionHolder = new DisconnectionHolder();
                    disconnectionHolder.b = data.getString(IBluetoothService.Y, "");
                    disconnectionHolder.f14144a = data.getLong(IBluetoothService.ab, 10000) + System.currentTimeMillis();
                    BluetoothApi.e.add(disconnectionHolder);
                    Collections.sort(BluetoothApi.e);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public static IBleChannelReader g = new ChannelManager.BleChannelReader() {
        public void onRead(String str, byte[] bArr, int i) throws RemoteException {
            if (i == 0) {
                byte[] a2 = BLECipher.a(BluetoothCache.p(str), bArr);
                IBleChannelWriter e = IPCChannelManager.e().e(str);
                if (e != null) {
                    BleMessageParser.a(str, a2, e);
                }
            }
        }
    };

    public static boolean a(int i) {
        return 5 >= i;
    }

    /* access modifiers changed from: private */
    public static void j() {
        while (!ListUtils.a(e)) {
            DisconnectionHolder disconnectionHolder = e.get(0);
            if (System.currentTimeMillis() < disconnectionHolder.f14144a) {
                break;
            }
            e.remove(0);
            f(disconnectionHolder.b);
        }
        if (ListUtils.a(e)) {
            f.removeMessages(1);
        }
    }

    static void a(SearchRequest searchRequest, final SearchResponse searchResponse) {
        if (searchRequest == null) {
            BluetoothMyLogger.c("searchBluetoothDevice request is null");
            if (searchResponse != null) {
                try {
                    searchResponse.onSearchCanceled();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            BluetoothSearchRequest.Builder builder = new BluetoothSearchRequest.Builder();
            for (SearchTask next : searchRequest.b()) {
                if (next.a() == 2) {
                    builder.a(next.b(), next.c());
                } else if (next.a() == 1) {
                    builder.b(next.b());
                }
            }
            BluetoothSearchHelper.b().a(builder.b(), (BluetoothSearchResponse) new BluetoothSearchResponse() {
                public void a() {
                    try {
                        searchResponse.onSearchStarted();
                    } catch (Exception e) {
                        BluetoothLog.a((Throwable) e);
                    }
                }

                public void a(BluetoothSearchResult bluetoothSearchResult) {
                    try {
                        searchResponse.onDeviceFounded(new SearchResult(bluetoothSearchResult));
                    } catch (Exception e) {
                        BluetoothLog.a((Throwable) e);
                    }
                }

                public void b() {
                    try {
                        searchResponse.onSearchStopped();
                    } catch (Exception e) {
                        BluetoothLog.a((Throwable) e);
                    }
                }

                public void c() {
                    try {
                        searchResponse.onSearchCanceled();
                    } catch (Exception e) {
                        BluetoothLog.a((Throwable) e);
                    }
                }
            });
        }
    }

    static void a(final String str, BleConnectOptions bleConnectOptions, final IBleResponse iBleResponse) {
        a(str, bleConnectOptions, (BleConnectResponse) new BleConnectResponse() {
            public void a(int i, Bundle bundle) {
                if (i == 0) {
                    Iterator<Device> it = DeviceManager.a().c().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Device next = it.next();
                        if (TextUtils.equals(str, next.n())) {
                            if (next.f() == 16) {
                                DeviceApi.b(str, next.k(), (AsyncCallback<Void, Error>) null);
                            }
                        }
                    }
                }
                if (iBleResponse != null) {
                    try {
                        iBleResponse.onResponse(i, bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void a(final String str, BleConnectOptions bleConnectOptions, final BleConnectResponse bleConnectResponse) {
        BluetoothMyLogger.e("connect mac = " + BluetoothMyLogger.a(str));
        BleConnectManager.a().a(str, bleConnectOptions, (BleConnectResponse) new BleConnectResponse() {
            public void a(int i, Bundle bundle) {
                BluetoothMyLogger.e("connect mac = " + BluetoothMyLogger.a(str) + ", code = " + i);
                try {
                    BleGattProfile bleGattProfile = (BleGattProfile) bundle.getParcelable("key_gatt_profile");
                    if (bleGattProfile != null) {
                        BluetoothCache.a(str, bleGattProfile);
                    }
                    BluetoothApi.b(str, bleGattProfile);
                    if (bleConnectResponse != null) {
                        bleConnectResponse.a(i, bundle);
                    }
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(final String str, BleGattProfile bleGattProfile) {
        BleGattService a2;
        BluetoothLog.c(String.format("registerChannelReaderIfSupported mac = %s", new Object[]{str}));
        if (bleGattProfile != null && (a2 = bleGattProfile.a(BluetoothConstants.i)) != null && a2.a(BluetoothConstants.R)) {
            BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.R, (BleNotifyResponse) new BleNotifyResponse() {
                public void a(int i, Void voidR) {
                    if (i == 0) {
                        IPCChannelManager.e().b(str, BluetoothApi.g);
                    }
                }
            });
        }
    }

    private static void f(String str) {
        BluetoothMyLogger.e("disconnect mac = " + BluetoothMyLogger.a(str));
        BleConnectManager.a().a(str);
    }

    public static void a(String str, long j) {
        if (j < 0) {
            j = 0;
        }
        if (j == 0) {
            f(str);
            return;
        }
        Message obtainMessage = f.obtainMessage(2);
        Bundle bundle = new Bundle();
        bundle.putString(IBluetoothService.Y, str);
        bundle.putLong(IBluetoothService.ab, j);
        obtainMessage.setData(bundle);
        f.sendMessage(obtainMessage);
        if (!f.hasMessages(1)) {
            f.sendEmptyMessage(1);
        }
    }

    static void a() {
        BluetoothMyLogger.f("BluetoothApi.disconnectAll");
        BleConnectManager.a().b();
    }

    public static void a(String str, UUID uuid, UUID uuid2, final IBleResponse iBleResponse) {
        BleConnectManager.a().a(str, uuid, uuid2, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                Bundle bundle = new Bundle();
                bundle.putByteArray(IBluetoothService.P, bArr);
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void a(final String str, byte[] bArr, final IBleResponse iBleResponse) {
        BluetoothMyLogger.e("securityChipEncrypt mac = " + BluetoothMyLogger.a(str));
        BleSecurityChipEncrypt.a(str, bArr, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                BluetoothMyLogger.e("securityChipEncrypt mac = " + BluetoothMyLogger.a(str) + ", code = " + i);
                Bundle bundle = new Bundle();
                bundle.putByteArray(IBluetoothService.P, bArr);
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void b(final String str, byte[] bArr, final IBleResponse iBleResponse) {
        BluetoothMyLogger.e("securityChipDecrypt mac = " + BluetoothMyLogger.a(str));
        BleSecurityChipEncrypt.b(str, bArr, new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                BluetoothMyLogger.e("securityChipDecrypt mac = " + BluetoothMyLogger.a(str) + ", code = " + i);
                Bundle bundle = new Bundle();
                bundle.putByteArray(IBluetoothService.P, bArr);
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void a(final String str, int i, final IBleResponse iBleResponse) {
        BluetoothMyLogger.e("securityChipOperate mac = " + BluetoothMyLogger.a(str) + ", operator = " + i);
        BleSecurityChipOperate.a(str, i, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                BluetoothMyLogger.e("securityChipOperate mac = " + BluetoothMyLogger.a(str) + ", code = " + i);
                Bundle bundle = new Bundle();
                bundle.putByteArray(IBluetoothService.P, bArr);
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static void a(final String str, final IBleResponse iBleResponse) {
        if (iBleResponse != null) {
            new CoreAsyncTask() {
                public void onCancel() {
                }

                public void run() {
                    String str = "";
                    ArrayList<Device> c = DeviceManager.a().c();
                    ArrayList arrayList = new ArrayList();
                    for (Device next : c) {
                        if (TextUtils.equals(str, next.n())) {
                            str = next.k();
                        }
                        PluginRecord c2 = PluginManager.a().c(next.l());
                        if (next.S() && c2 != null && c2.c() != null && c2.c().J() == 1) {
                            arrayList.add(next.k());
                        }
                    }
                    if (TextUtils.isEmpty(str) || arrayList.size() == 0) {
                        try {
                            iBleResponse.onResponse(-1, (Bundle) null);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } else {
                        BluetoothApi.a(str, (List<String>) arrayList, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Boolean bool) {
                                if (bool.booleanValue()) {
                                    try {
                                        iBleResponse.onResponse(0, (Bundle) null);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        iBleResponse.onResponse(-1, (Bundle) null);
                                    } catch (RemoteException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }

                            public void onFailure(Error error) {
                                try {
                                    iBleResponse.onResponse(-1, (Bundle) null);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }.execute();
        }
    }

    static void a(final String str, final List<String> list, final AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dids", new JSONArray(list));
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("getBleDeviceByGateway: [%s]", new Object[]{jSONObject}));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/device/get_bledevice_by_gateway").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass12 r1 = new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%s]", new Object[]{jSONObject}));
                if (jSONObject != null) {
                    for (String optString : list) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(jSONObject.optString(optString));
                            if (jSONObject2.length() > 0) {
                                Iterator<String> keys = jSONObject2.keys();
                                while (keys.hasNext()) {
                                    if (TextUtils.equals(str, keys.next())) {
                                        return true;
                                    }
                                }
                                continue;
                            } else {
                                continue;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r1, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    static void a(String str, UUID uuid, UUID uuid2, byte[] bArr, final IBleResponse iBleResponse) {
        BleConnectManager.a().a(str, uuid, uuid2, bArr, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                try {
                    iBleResponse.onResponse(i, (Bundle) null);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void b(String str, UUID uuid, UUID uuid2, byte[] bArr, final IBleResponse iBleResponse) {
        BluetoothCache.p(str);
        BleConnectManager.a().b(str, uuid, uuid2, bArr, new BleWriteResponse() {
            public void a(int i, Void voidR) {
                try {
                    iBleResponse.onResponse(i, (Bundle) null);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void c(String str, byte[] bArr, IBleResponse iBleResponse) {
        IPCChannelManager.e().a(str, bArr, iBleResponse);
    }

    static void b(String str, IBleResponse iBleResponse) {
        BleMessageParser.a(str, iBleResponse);
    }

    static void a(String str) {
        BleMessageParser.a(str);
    }

    static void b(String str, UUID uuid, UUID uuid2, final IBleResponse iBleResponse) {
        BleConnectManager.a().a(str, uuid, uuid2, (BleNotifyResponse) new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                try {
                    iBleResponse.onResponse(i, (Bundle) null);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void a(String str, UUID uuid, UUID uuid2) {
        BleConnectManager.a().a(str, uuid, uuid2);
    }

    static void c(String str, UUID uuid, UUID uuid2, final IBleResponse iBleResponse) {
        BleConnectManager.a().b(str, uuid, uuid2, new BleNotifyResponse() {
            public void a(int i, Void voidR) {
                try {
                    iBleResponse.onResponse(i, (Bundle) null);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void b(String str, UUID uuid, UUID uuid2) {
        BleConnectManager.a().b(str, uuid, uuid2);
    }

    static void b(String str) {
        BleConnectManager.a().b(str);
    }

    static void c(String str, final IBleResponse iBleResponse) {
        BleConnectManager.a().a(str, (BleReadRssiResponse) new BleReadRssiResponse() {
            public void a(int i, Integer num) {
                Bundle bundle = new Bundle();
                bundle.putInt(IBluetoothService.R, num.intValue());
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void b(String str, int i, final IBleResponse iBleResponse) {
        BleConnectManager.a().a(str, i, (BleRequestMtuResponse) new BleRequestMtuResponse() {
            public void a(int i, Integer num) {
                Bundle bundle = new Bundle();
                bundle.putInt(IBluetoothService.S, num.intValue());
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void d(String str, byte[] bArr, IBleResponse iBleResponse) {
        BluetoothMyLogger.e("miotBleEncrypt mac = " + BluetoothMyLogger.a(str));
        byte[] p = BluetoothCache.p(str);
        if (p == null || p.length == 0) {
            try {
                iBleResponse.onResponse(-1, new Bundle());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            BluetoothMyLogger.e("miotBleEncrypt failed, token is empty");
            return;
        }
        byte[] a2 = BLECipher.a(p, bArr);
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, a2);
        try {
            iBleResponse.onResponse(0, bundle);
        } catch (Exception e3) {
            BluetoothLog.a((Throwable) e3);
        }
    }

    static byte[] a(String str, byte[] bArr) {
        BluetoothMyLogger.e("miotBleEncryptSync mac = " + BluetoothMyLogger.a(str));
        if (TextUtils.isEmpty(str) || bArr == null) {
            return null;
        }
        byte[] p = BluetoothCache.p(str);
        if (p != null && p.length != 0) {
            return BLECipher.a(p, bArr);
        }
        BluetoothMyLogger.e("miotBleEncryptSync failed, token is empty");
        return null;
    }

    static void e(String str, byte[] bArr, IBleResponse iBleResponse) {
        BluetoothMyLogger.e("miotBleDecrypt mac = " + BluetoothMyLogger.a(str));
        byte[] p = BluetoothCache.p(str);
        if (p == null || p.length == 0) {
            try {
                iBleResponse.onResponse(-1, new Bundle());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            BluetoothMyLogger.e("miotBleDecrypt failed, token is empty");
            return;
        }
        byte[] a2 = BLECipher.a(p, bArr);
        Bundle bundle = new Bundle();
        bundle.putByteArray(IBluetoothService.P, a2);
        try {
            iBleResponse.onResponse(0, bundle);
        } catch (Exception e3) {
            BluetoothLog.a((Throwable) e3);
        }
    }

    static byte[] b(String str, byte[] bArr) {
        BluetoothMyLogger.e("miotBleDecryptSync mac = " + BluetoothMyLogger.a(str));
        if (TextUtils.isEmpty(str) || bArr == null) {
            return null;
        }
        byte[] p = BluetoothCache.p(str);
        if (p != null && p.length != 0) {
            return BLECipher.a(p, bArr);
        }
        BluetoothMyLogger.e("miotBleDecryptSync failed, token is empty");
        return null;
    }

    static void f(final String str, byte[] bArr, final IBleResponse iBleResponse) {
        BluetoothMyLogger.e("standardAuthEncrypt mac = " + BluetoothMyLogger.a(str));
        BleSecurityChipEncrypt.a(str, bArr, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                BluetoothMyLogger.e("standardAuthEncrypt mac = " + BluetoothMyLogger.a(str) + ", code = " + i);
                Bundle bundle = new Bundle();
                bundle.putByteArray(IBluetoothService.P, bArr);
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void g(final String str, byte[] bArr, final IBleResponse iBleResponse) {
        BluetoothMyLogger.e("standardAuthDecrypt mac = " + BluetoothMyLogger.a(str));
        BleSecurityChipEncrypt.b(str, bArr, new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                BluetoothMyLogger.e("standardAuthDecrypt mac = " + BluetoothMyLogger.a(str) + ", code = " + i);
                Bundle bundle = new Bundle();
                bundle.putByteArray(IBluetoothService.P, bArr);
                try {
                    iBleResponse.onResponse(i, bundle);
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        });
    }

    static void d(String str, UUID uuid, UUID uuid2, IBleResponse iBleResponse) {
        BleCharacterChangedManager.a(str, uuid, uuid2, iBleResponse);
    }

    static void c(String str, UUID uuid, UUID uuid2) {
        BleCharacterChangedManager.a(str, uuid, uuid2);
    }

    static void a(final String str, long j, final IBleResponse iBleResponse) {
        final Bundle bundle = new Bundle();
        for (BluetoothSearchResult g2 : BluetoothUtils.h()) {
            if (str.equalsIgnoreCase(g2.g())) {
                bundle.putInt(IBluetoothService.W, 16);
                b(bundle, iBleResponse);
                return;
            }
        }
        final BluetoothSearchRequest b2 = new BluetoothSearchRequest.Builder().a(1000, (int) (Math.min(Math.max(j, 5000), 15000) / 1000)).b();
        BluetoothSearchHelper.b().a(b2, (BluetoothSearchResponse) new BluetoothSearchResponse() {
            public void a() {
            }

            public void a(BluetoothSearchResult bluetoothSearchResult) {
                if (bluetoothSearchResult.g().equalsIgnoreCase(str)) {
                    BluetoothRecognizer.a().a(bluetoothSearchResult);
                    bundle.putInt(IBluetoothService.W, 48);
                    BluetoothApi.b(bundle, iBleResponse);
                    BluetoothSearchHelper.b().a(b2);
                }
            }

            public void b() {
                if (!bundle.containsKey(IBluetoothService.W)) {
                    bundle.putInt(IBluetoothService.W, 64);
                    BluetoothApi.b(bundle, iBleResponse);
                }
            }

            public void c() {
                if (!bundle.containsKey(IBluetoothService.W)) {
                    bundle.putInt(IBluetoothService.W, 64);
                    BluetoothApi.b(bundle, iBleResponse);
                }
            }
        });
    }

    static void a(String str, int i, int i2, long j, IBleResponse iBleResponse) {
        BluetoothMyLogger.e("getOneTimePassword mac = " + BluetoothMyLogger.a(str));
        if (i < 1 || i > 60) {
            BluetoothMyLogger.e("getOneTimePassword interval illegal");
            b(-1, "interval illegal", (int[]) null, iBleResponse);
        } else if (i2 < 6 || i2 > 8) {
            BluetoothMyLogger.e("getOneTimePassword digits illegal");
            b(-1, "digits illegal", (int[]) null, iBleResponse);
        } else {
            String f2 = BluetoothCache.f(str);
            if (TextUtils.isEmpty(f2)) {
                BluetoothMyLogger.e("getOneTimePassword can't find this device did");
                b(-1, "can't find this device did", (int[]) null, iBleResponse);
                return;
            }
            byte[] r = BluetoothCache.r(str);
            if (ByteUtils.e(r)) {
                final String str2 = str;
                final int i3 = i;
                final int i4 = i2;
                final long j2 = j;
                final IBleResponse iBleResponse2 = iBleResponse;
                DeviceApi.a(f2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        String str = "";
                        if (jSONObject != null) {
                            str = jSONObject.optString("key");
                        }
                        if (!TextUtils.isEmpty(str)) {
                            BluetoothCache.j(str2, str);
                            int optInt = jSONObject.optInt("encrypt_type", 0);
                            if (optInt == 0) {
                                BluetoothApi.b(ByteUtils.a(str), i3, i4, j2, iBleResponse2);
                                return;
                            }
                            String s = BluetoothCache.s(str2);
                            if (TextUtils.isEmpty(s)) {
                                BluetoothMyLogger.e("getOneTimePassword pincode is empty");
                                BluetoothApi.b(-38, "pincode is empty", (int[]) null, iBleResponse2);
                                return;
                            }
                            BluetoothApi.b(ByteUtils.a(LtmkEncryptUtil.b(s, str, optInt)), i3, i4, j2, iBleResponse2);
                            return;
                        }
                        BluetoothMyLogger.e("getOneTimePassword get ltmk failed");
                        BluetoothApi.b(-1, "get ltmk failed", (int[]) null, iBleResponse2);
                    }

                    public void onFailure(Error error) {
                        BluetoothMyLogger.d(String.format("getOneTimePassword %s error: getOwnLtmk failed(%s)", new Object[]{BluetoothMyLogger.a(str2), error.b()}));
                        BluetoothApi.b(-1, "get ltmk failed", (int[]) null, iBleResponse2);
                    }
                });
                return;
            }
            int t = BluetoothCache.t(str);
            if (t == 0) {
                b(r, i, i2, j, iBleResponse);
                return;
            }
            String s = BluetoothCache.s(str);
            if (TextUtils.isEmpty(s)) {
                BluetoothMyLogger.e("getOneTimePassword pincode is empty");
                b(-38, "pincode is empty", (int[]) null, iBleResponse);
                return;
            }
            b(ByteUtils.a(LtmkEncryptUtil.b(s, ByteUtils.d(r), t)), i, i2, j, iBleResponse);
        }
    }

    /* access modifiers changed from: private */
    public static void b(final byte[] bArr, final int i, final int i2, long j, final IBleResponse iBleResponse) {
        if (j > 0) {
            b(i, i2, bArr, j, iBleResponse);
        } else {
            DeviceApi.a(new AsyncCallback<Long, Error>() {
                /* renamed from: a */
                public void onSuccess(Long l) {
                    if (l == null || l.longValue() == -1) {
                        BluetoothApi.b(-1, "get utc time failed(1)", (int[]) null, iBleResponse);
                    } else {
                        BluetoothApi.b(i, i2, bArr, l.longValue(), iBleResponse);
                    }
                }

                public void onFailure(Error error) {
                    if (error != null) {
                        BluetoothApi.b(-1, error.b(), (int[]) null, iBleResponse);
                    } else {
                        BluetoothApi.b(-1, "get utc time failed", (int[]) null, iBleResponse);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(int r8, int r9, byte[] r10, long r11, com.xiaomi.smarthome.core.server.bluetooth.IBleResponse r13) {
        /*
            java.lang.String r0 = "mi-lock-otp-salt"
            java.lang.String r1 = "mi-lock-otp-info"
            r2 = 32
            r3 = 0
            java.lang.String r4 = "HmacSHA256"
            com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf r4 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.Hkdf.a((java.lang.String) r4)     // Catch:{ NoSuchAlgorithmException -> 0x001d }
            byte[] r0 = r0.getBytes()     // Catch:{ NoSuchAlgorithmException -> 0x001d }
            r4.a((byte[]) r10, (byte[]) r0)     // Catch:{ NoSuchAlgorithmException -> 0x001d }
            byte[] r10 = r1.getBytes()     // Catch:{ NoSuchAlgorithmException -> 0x001d }
            byte[] r10 = r4.a((byte[]) r10, (int) r2)     // Catch:{ NoSuchAlgorithmException -> 0x001d }
            goto L_0x0022
        L_0x001d:
            r10 = move-exception
            r10.printStackTrace()
            r10 = r3
        L_0x0022:
            r0 = 1
            r1 = 0
            if (r10 == 0) goto L_0x0068
            java.lang.String r4 = "HmacSHA256"
            javax.crypto.Mac r4 = javax.crypto.Mac.getInstance(r4)     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            javax.crypto.spec.SecretKeySpec r5 = new javax.crypto.spec.SecretKeySpec     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            java.lang.String r6 = "HmacSHA256"
            r5.<init>(r10, r6)     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r4.init(r5)     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            int r8 = r8 * 60
            long r5 = (long) r8     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            long r11 = r11 / r5
            int r8 = (int) r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r10 = 4
            byte[] r10 = new byte[r10]     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            int r11 = r8 >> 24
            r11 = r11 & 255(0xff, float:3.57E-43)
            byte r11 = (byte) r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r12 = 3
            r10[r12] = r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            int r11 = r8 >> 16
            r11 = r11 & 255(0xff, float:3.57E-43)
            byte r11 = (byte) r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r12 = 2
            r10[r12] = r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            int r11 = r8 >> 8
            r11 = r11 & 255(0xff, float:3.57E-43)
            byte r11 = (byte) r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r10[r0] = r11     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r8 = r8 & 255(0xff, float:3.57E-43)
            byte r8 = (byte) r8     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            r10[r1] = r8     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            byte[] r8 = r4.doFinal(r10)     // Catch:{ NoSuchAlgorithmException -> 0x0064, InvalidKeyException -> 0x005f }
            goto L_0x0069
        L_0x005f:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x0068
        L_0x0064:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0068:
            r8 = r3
        L_0x0069:
            if (r8 == 0) goto L_0x00ad
            int r10 = r8.length
            if (r10 != r2) goto L_0x00ad
            r10 = 8
            int[] r11 = new int[r10]
            r12 = 0
        L_0x0073:
            if (r12 >= r9) goto L_0x007a
            int r0 = r0 * 10
            int r12 = r12 + 1
            goto L_0x0073
        L_0x007a:
            r9 = 0
        L_0x007b:
            int r12 = r8.length
            if (r9 >= r12) goto L_0x00ae
            byte r12 = r8[r9]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r2 = r9 + 1
            byte r2 = r8[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r10
            r12 = r12 | r2
            int r2 = r9 + 2
            byte r2 = r8[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 16
            r12 = r12 | r2
            int r2 = r9 + 3
            byte r2 = r8[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 24
            r12 = r12 | r2
            long r4 = (long) r12
            r6 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r4 = r4 & r6
            long r6 = (long) r0
            long r4 = r4 % r6
            int r12 = r9 / 4
            int r2 = (int) r4
            r11[r12] = r2
            int r9 = r9 + 4
            goto L_0x007b
        L_0x00ad:
            r11 = r3
        L_0x00ae:
            if (r11 != 0) goto L_0x00bc
            java.lang.String r8 = "generateOneTimePassword generate failed"
            com.xiaomi.smarthome.frame.log.BluetoothMyLogger.e(r8)
            r8 = -1
            java.lang.String r9 = "generate failed"
            b((int) r8, (java.lang.String) r9, (int[]) r3, (com.xiaomi.smarthome.core.server.bluetooth.IBleResponse) r13)
            goto L_0x00c1
        L_0x00bc:
            java.lang.String r8 = ""
            b((int) r1, (java.lang.String) r8, (int[]) r11, (com.xiaomi.smarthome.core.server.bluetooth.IBleResponse) r13)
        L_0x00c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi.b(int, int, byte[], long, com.xiaomi.smarthome.core.server.bluetooth.IBleResponse):void");
    }

    static void d(String str, final IBleResponse iBleResponse) {
        a(str, (BleResponse<String>) new BleResponse<String>() {
            public void a(int i, String str) {
                if (iBleResponse != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(IBluetoothService.V, str);
                    try {
                        iBleResponse.onResponse(i, bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    static void e(String str, final IBleResponse iBleResponse) {
        a(str, (BleResponse<String>) new BleResponse<String>() {
            public void a(int i, String str) {
                if (iBleResponse != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(IBluetoothService.V, str);
                    try {
                        iBleResponse.onResponse(i, bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void a(String str, final BleResponse<String> bleResponse) {
        BluetoothMyLogger.e("getBleMeshFirmwareVersion mac = " + BluetoothMyLogger.a(str));
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.J, (BleReadResponse) new BleReadResponse() {
            public void a(int i, byte[] bArr) {
                if (i != 0 || ByteUtils.e(bArr)) {
                    bleResponse.a(-1, "");
                    return;
                }
                int i2 = 0;
                int i3 = 0;
                while (i2 < bArr.length && bArr[i2] != 0) {
                    i3++;
                    i2++;
                }
                if (i3 == 0) {
                    bleResponse.a(-1, "");
                    return;
                }
                byte[] bArr2 = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    bArr2[i4] = bArr[i4];
                }
                bleResponse.a(0, new String(bArr2));
            }
        });
    }

    static void e(String str, UUID uuid, UUID uuid2, final IBleResponse iBleResponse) {
        BleConnectManager.a().a(str, uuid, uuid2, (BleResponse<Void>) new BleResponse<Void>() {
            public void a(int i, Void voidR) {
                if (iBleResponse != null) {
                    try {
                        iBleResponse.onResponse(i, new Bundle());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void b(int i, String str, int[] iArr, IBleResponse iBleResponse) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString(IBluetoothService.ah, str);
        }
        if (iArr != null) {
            bundle.putIntArray(IBluetoothService.Q, iArr);
        }
        try {
            iBleResponse.onResponse(i, bundle);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void b(Bundle bundle, IBleResponse iBleResponse) {
        try {
            iBleResponse.onResponse(0, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0019
            byte[] r0 = com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.p(r2)
            boolean r1 = com.xiaomi.smarthome.library.common.util.ByteUtils.e(r0)
            if (r1 != 0) goto L_0x0019
            com.xiaomi.smarthome.core.server.internal.bluetooth.MiuiSDKHelper r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.MiuiSDKHelper.a()
            boolean r0 = r1.a(r2, r0)
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = "key.miui.bind"
            r1 = 1
            com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.a((java.lang.String) r2, (java.lang.String) r0, (boolean) r1)
        L_0x0022:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi.c(java.lang.String):void");
    }

    public static void d(String str) {
        if (!TextUtils.isEmpty(str) ? MiuiSDKHelper.a().a(str) : false) {
            BluetoothCache.a(str, BtConstants.c, false);
        }
    }

    public static void f(final String str, final IBleResponse iBleResponse) {
        CoreLogUtilGrey.a("BluetoothApi", String.format("BluetoothApi.findComboMac subMac = %s", new Object[]{str}));
        if (str == null || str.length() != 4) {
            CoreLogUtilGrey.a("BluetoothApi", "findComboMac error subMac must 4 char but get " + str);
            return;
        }
        String a2 = ComboCollector.a(str);
        final Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(a2)) {
            BluetoothLog.e("findComboMac by BluetoothDeviceSearch");
            bundle.putString(IBluetoothService.Y, a2);
            try {
                iBleResponse.onResponse(0, bundle);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            final BluetoothSearchRequest b2 = new BluetoothSearchRequest.Builder().a(3000, 5).b();
            BluetoothSearchHelper.b().a(b2, (BluetoothSearchResponse) new BluetoothSearchResponse() {
                public void a() {
                }

                public void a(final BluetoothSearchResult bluetoothSearchResult) {
                    BluetoothRecognizer.a().a(bluetoothSearchResult, (IBluetoothRecognizeResponse) new IBluetoothRecognizeResponse() {
                        public void a(BluetoothRecognizeResult bluetoothRecognizeResult) {
                            if (!TextUtils.isEmpty(ComboCollector.a(str))) {
                                BluetoothSearchHelper.b().a(b2);
                                bundle.putString(IBluetoothService.Y, bluetoothSearchResult.g());
                                try {
                                    iBleResponse.onResponse(0, bundle);
                                } catch (Exception e) {
                                    CoreLogUtilGrey.a("BluetoothApi", "findComboMac onRecognized" + Log.getStackTraceString(e));
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }

                public void b() {
                    if (!bundle.containsKey(IBluetoothService.Y)) {
                        try {
                            iBleResponse.onResponse(-1, bundle);
                        } catch (Exception e) {
                            CoreLogUtilGrey.a("BluetoothApi", "findComboMac onSearchStopped" + Log.getStackTraceString(e));
                            e.printStackTrace();
                        }
                    }
                }

                public void c() {
                    if (!bundle.containsKey(IBluetoothService.Y)) {
                        try {
                            iBleResponse.onResponse(-1, bundle);
                        } catch (Exception e) {
                            CoreLogUtilGrey.a("BluetoothApi", "findComboMac onSearchCanceled" + Log.getStackTraceString(e));
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public static void a(String str, String str2) {
        StatManager.c().a(StatType.EVENT.getValue(), "mihome", str, str2, (String) null, false);
    }

    public static ArrayList<BtDevice> b() {
        return BluetoothCache.b();
    }

    public static ArrayList<BtDevice> c() {
        return BluetoothCache.c();
    }

    public static ArrayList<BtDevice> d() {
        return BluetoothCache.d();
    }

    public static void e(String str) {
        BluetoothLog.c(String.format("clearDisconnection %s", new Object[]{str}));
        e.remove(new DisconnectionHolder(str));
        if (ListUtils.a(e)) {
            f.removeMessages(1);
        }
    }

    public static void e() {
        BluetoothLog.c(String.format("clearAllDisconnection", new Object[0]));
        e.clear();
        f.removeMessages(1);
    }
}
