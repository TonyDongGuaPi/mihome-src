package com.xiaomi.smarthome.device.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.facebook.internal.ServerProtocol;
import com.mi.global.bbs.http.ParamKey;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.VirtualDeviceInfo;
import com.xiaomi.smarthome.device.authorization.BaseAuthData;
import com.xiaomi.smarthome.device.authorization.DeviceAuthData;
import com.xiaomi.smarthome.device.authorization.HomeRoomAuthData;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthFragment;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.light.group.LightGroupMemberUpdateActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceApi {
    private static final int MAXREQUEST = 300;
    private static final String TAG = "DeviceApi";
    private static DeviceApi sInstance;
    private static final Object sLock = new Object();

    static /* synthetic */ JSONObject lambda$getNewDevice$0(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    static /* synthetic */ JSONObject lambda$getRouterLocalUrl$1(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    private DeviceApi() {
    }

    public static DeviceApi getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new DeviceApi();
                }
            }
        }
        return sInstance;
    }

    public AsyncHandle getModelGroupInfoJson(Context context, AsyncCallback<String, Error> asyncCallback) {
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/virtualdevicedef").a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return null;
                }
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle createModelGroup(Context context, String[] strArr, String str, AsyncCallback<Device, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "create");
            jSONObject.put("name", str);
            JSONArray jSONArray = new JSONArray();
            for (String put : strArr) {
                jSONArray.put(put);
            }
            jSONObject.put("memberDids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/virtualdevicectr").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Device>() {
            public Device parse(JSONObject jSONObject) throws JSONException {
                Device b = DeviceFactory.b(jSONObject);
                b.setOwner(true);
                return b;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle deleteModelGroup(Context context, String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "del");
            jSONObject.put("masterDid", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/virtualdevicectr").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getVirtualDeviceInfoById(Context context, String str, AsyncCallback<VirtualDeviceInfo, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "get");
            jSONObject.put("masterDid", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/virtualdevicectr").b((List<KeyValuePair>) arrayList).a(), new JsonParser<VirtualDeviceInfo>() {
            public VirtualDeviceInfo parse(JSONObject jSONObject) throws JSONException {
                VirtualDeviceInfo virtualDeviceInfo = new VirtualDeviceInfo();
                virtualDeviceInfo.f15004a = jSONObject.optString("did");
                virtualDeviceInfo.d = jSONObject.optInt("pid");
                virtualDeviceInfo.c = jSONObject.optString("model");
                virtualDeviceInfo.b = jSONObject.optString("name");
                JSONArray optJSONArray = jSONObject.optJSONArray(LightGroupMemberUpdateActivity.KEY_MEMBER);
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    virtualDeviceInfo.e = null;
                } else {
                    virtualDeviceInfo.e = new String[optJSONArray.length()];
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        virtualDeviceInfo.e[i] = optJSONArray.getJSONObject(i).toString();
                    }
                }
                return virtualDeviceInfo;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle modifyModelGroup(Context context, String[] strArr, String str, String str2, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", ParamKey.mod);
            jSONObject.put("name", str2);
            jSONObject.put("masterDid", str);
            JSONArray jSONArray = new JSONArray();
            for (String put : strArr) {
                jSONArray.put(put);
            }
            jSONObject.put("memberDids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/virtualdevicectr").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    return jSONObject.toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle createVirtalDevice(Context context, String str, AsyncCallback<Device, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "create");
            if (context != null) {
                jSONObject.put("name", context.getResources().getString(R.string.yunyi_virtual_camera));
            }
            jSONObject.put("model", UrlResolver.b);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/virtualdevicectr").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Device>() {
            public Device parse(JSONObject jSONObject) throws JSONException {
                Device b = DeviceFactory.b(jSONObject);
                b.setOwner(true);
                return b;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle bindThirdDevice(Context context, String str, String str2, String str3, long j, String str4, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("token", str2);
            jSONObject.put("sn", str3);
            jSONObject.put("hashstamp", j);
            jSONObject.put("applicationId", str4);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/appbinddevice").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return Integer.valueOf(jSONObject.getInt("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle batchSetDeviceProps(Context context, JSONArray jSONArray, AsyncCallback<String, Error> asyncCallback) {
        if (jSONArray != null && jSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONArray.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/device/batch_set_props").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
                public String parse(JSONObject jSONObject) throws JSONException {
                    if (jSONObject == null) {
                        return null;
                    }
                    return "" + jSONObject.toString();
                }
            }, Crypto.RC4, asyncCallback);
        } else if (asyncCallback == null) {
            return null;
        } else {
            asyncCallback.onFailure(new Error(-1, "param is null"));
            return null;
        }
    }

    public AsyncHandle batchGetDevicePropsNew(Context context, JSONArray jSONArray, AsyncCallback<String, Error> asyncCallback, String str) {
        if (jSONArray != null && jSONArray.length() > 0) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("from_flag", str);
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONArray.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/device/batchgetdatas").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
                public String parse(JSONObject jSONObject) throws JSONException {
                    if (jSONObject == null) {
                        return null;
                    }
                    return "" + jSONObject.toString();
                }
            }, Crypto.RC4, asyncCallback);
        } else if (asyncCallback == null) {
            return null;
        } else {
            asyncCallback.onFailure(new Error(-1, "param is null"));
            return null;
        }
    }

    public AsyncHandle netChange(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ssid_before", str);
            jSONObject.put("ssid_current", str2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/stat/net_change").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getDeveloperInfo(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/plugin/developer_info").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                new ArrayList();
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    @Deprecated
    public AsyncHandle getSmartHomeConfigList(Context context, AsyncCallback<List<String>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        AnonymousClass11 r5 = new JsonParser<List<String>>() {
            public List<String> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.optString(i));
                }
                return arrayList;
            }
        };
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/suggest_new_devices").b((List<KeyValuePair>) arrayList).a(), r5, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getDeviceDetail(Context context, String[] strArr, final AsyncCallback<List<Device>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        if (strArr != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (String put : strArr) {
                    jSONArray.put(put);
                }
                jSONObject.put("dids", jSONArray);
            } catch (JSONException unused) {
            }
        }
        String c = WifiUtil.c(context);
        String b = WifiUtil.b(context);
        if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(b) && !TextUtils.equals(b, "02:00:00:00:00:00")) {
            jSONObject.put(DeviceTagInterface.e, c);
            jSONObject.put("bssid", b.toUpperCase());
        }
        return updateDeviceList(context, jSONObject, new AsyncCallback<ArrayList<Device>, Error>() {
            public void onSuccess(ArrayList<Device> arrayList) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(arrayList);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public AsyncHandle updateDeviceDesc(Context context, final List<Device> list, final AsyncCallback<List<Device>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        if (list != null) {
            AnonymousClass13 r8 = new JsonParser<List<Device>>() {
                public List<Device> parse(JSONObject jSONObject) throws JSONException {
                    for (Device device : list) {
                        Object opt = jSONObject.opt(device.did);
                        if (opt instanceof JSONObject) {
                            JSONObject jSONObject2 = (JSONObject) opt;
                            device.desc = jSONObject2.optString("desc");
                            device.descNew = jSONObject2.optString("descNew");
                        } else if (opt instanceof String) {
                            device.desc = (String) opt;
                        }
                    }
                    return list;
                }
            };
            final List<List<Device>> a2 = ListUtils.a(list, 300);
            AnonymousClass14 r9 = new AsyncCallback<List<Device>, Error>() {
                Error mError;
                AtomicInteger times = new AtomicInteger(a2.size());

                public void onSuccess(List<Device> list) {
                    onFailure((Error) null);
                }

                public void onFailure(Error error) {
                    if (error != null) {
                        this.mError = error;
                    }
                    if (this.times.decrementAndGet() != 0) {
                        return;
                    }
                    if (this.mError == null) {
                        asyncCallback.onSuccess(list);
                    } else {
                        asyncCallback.onFailure(this.mError);
                    }
                }
            };
            final ArrayList arrayList = new ArrayList();
            for (List<Device> next : a2) {
                try {
                    JSONArray jSONArray = new JSONArray();
                    for (Device device : next) {
                        if (!TextUtils.isEmpty(device.did)) {
                            jSONArray.put(device.did);
                        }
                    }
                    jSONObject.put("dids", jSONArray);
                } catch (JSONException unused) {
                }
                arrayList.add(CoreApi.a().a(context, getNetRequest("/v2/device/get_device_desc", jSONObject), r8, Crypto.RC4, r9));
            }
            return new AsyncHandle<List<Device>>((List) null) {
                public void cancel() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((AsyncHandle) it.next()).cancel();
                    }
                }
            };
        }
        asyncCallback.onFailure(new Error(-1, "new rquest devices"));
        return null;
    }

    public AsyncHandle updateDeviceList(Context context, JSONObject jSONObject, AsyncCallback<ArrayList<Device>, Error> asyncCallback) {
        final AnonymousClass16 r4 = new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        };
        final Context context2 = context;
        final JSONObject jSONObject2 = jSONObject;
        final AsyncCallback<ArrayList<Device>, Error> asyncCallback2 = asyncCallback;
        return new AsyncHandle(new AsyncHandle.Handle() {
            /* access modifiers changed from: private */
            public boolean mCancel = false;
            /* access modifiers changed from: private */
            public AsyncHandle mCurrentHandle = CoreApi.a().a(context2, DeviceApi.this.getNetRequest("/v2/home/device_list_page", jSONObject2), r4, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                private ArrayList<Device> result = new ArrayList<>();

                public void onSuccess(JSONObject jSONObject) {
                    if (!AnonymousClass17.this.mCancel) {
                        if (jSONObject != null) {
                            try {
                                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                                if (optJSONArray != null) {
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                        if (optJSONObject != null) {
                                            this.result.add(DeviceFactory.b(optJSONObject));
                                        }
                                    }
                                }
                                if (jSONObject.optBoolean("has_more")) {
                                    String optString = jSONObject.optString("next_start_did");
                                    LogUtil.c(DeviceApi.TAG, "/home/device_list_new request next page with did:" + optString);
                                    jSONObject2.put("start_did", optString);
                                    AsyncHandle unused = AnonymousClass17.this.mCurrentHandle = CoreApi.a().a(context2, DeviceApi.this.getNetRequest("/v2/home/device_list_page", jSONObject2), r4, Crypto.RC4, this);
                                    return;
                                }
                                LogUtil.c(DeviceApi.TAG, "/home/device_list_response success times:" + this.result.size());
                                if (asyncCallback2 != null) {
                                    asyncCallback2.onSuccess(this.result);
                                }
                            } catch (JSONException e) {
                                LogUtil.c(DeviceApi.TAG, "/home/device_list_response JSONException times:" + this.result.size());
                                if (asyncCallback2 != null) {
                                    asyncCallback2.onFailure(new Error(-1, Log.getStackTraceString(e)));
                                }
                            }
                        } else {
                            LogUtil.c(DeviceApi.TAG, "/home/device_list_response resultObject null times:" + this.result.size());
                            if (asyncCallback2 != null) {
                                asyncCallback2.onSuccess(this.result);
                            }
                        }
                    }
                }

                public void onFailure(Error error) {
                    LogUtil.c(DeviceApi.TAG, "/home/device_list_response onFailure times:" + this.result.size());
                    if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(error);
                    }
                }
            });
            final String url = "/v2/home/device_list_page";

            public void cancel() {
                this.mCancel = true;
                if (this.mCurrentHandle != null) {
                    this.mCurrentHandle.cancel();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public NetRequest getNetRequest(String str, JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return new NetRequest.Builder().a("POST").b(str).b((List<KeyValuePair>) arrayList).a();
    }

    public AsyncHandle getSubDevice(Context context, String[] strArr, AsyncCallback<List<Device>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        if (strArr != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (String put : strArr) {
                    jSONArray.put(put);
                }
                jSONObject.put("dids", jSONArray);
            } catch (JSONException unused) {
            }
        }
        String b = WifiUtil.b(context);
        if (!TextUtils.isEmpty(b)) {
            jSONObject.put("uid", b.toUpperCase());
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/sub_device_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<Device>>() {
            public List<Device> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                if (optJSONArray == null) {
                    return arrayList;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Device b = DeviceFactory.b(optJSONArray.getJSONObject(i));
                    if (b != null) {
                        arrayList.add(b);
                    }
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void getNewDevice(final Context context, String str, boolean z, String str2, String str3, String str4, String str5, AsyncCallback<List<Device>, Error> asyncCallback) {
        Context context2 = context;
        String str6 = str;
        final long currentTimeMillis = System.currentTimeMillis();
        final int k = SystemApi.a().k(context);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        final AtomicInteger atomicInteger2 = atomicInteger;
        final AsyncCallback<List<Device>, Error> asyncCallback2 = asyncCallback;
        final AnonymousClass19 r0 = new AsyncCallback<List<Device>, Error>() {
            public void onSuccess(List<Device> list) {
                LogUtilGrey.a("getNewDevice", "onSuccess:" + list);
                int decrementAndGet = atomicInteger2.decrementAndGet();
                long currentTimeMillis = System.currentTimeMillis();
                if (decrementAndGet == 0) {
                    asyncCallback2.onSuccess(list);
                    CoreApi.a().a(StatType.TIME, "get_new_device_list", Long.toString(currentTimeMillis - currentTimeMillis), Integer.toString(k), false);
                } else if (decrementAndGet > 0 && list != null && list.size() > 0) {
                    atomicInteger2.set(0);
                    asyncCallback2.onSuccess(list);
                    CoreApi.a().a(StatType.TIME, "get_new_device_list", Long.toString(currentTimeMillis - currentTimeMillis), Integer.toString(k), false);
                }
            }

            public void onFailure(Error error) {
                if (atomicInteger2.decrementAndGet() == 0) {
                    asyncCallback2.onFailure(error);
                }
            }
        };
        AnonymousClass20 r6 = new JsonParser<List<Device>>() {
            public List<Device> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                if (optJSONArray == null) {
                    return arrayList;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Device b = DeviceFactory.b(optJSONArray.getJSONObject(i));
                    if (b != null) {
                        arrayList.add(b);
                    }
                }
                return arrayList;
            }
        };
        if (z && !TextUtils.isEmpty(str)) {
            atomicInteger.incrementAndGet();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("dids", jSONArray);
            } catch (JSONException unused) {
            }
            LogUtilGrey.a("getNewDevice", "device_list" + jSONObject);
            CoreApi.a().a(context, getNetRequest("/v2/home/device_list_page", jSONObject), r6, Crypto.RC4, r0);
        }
        if (!TextUtils.isEmpty(str2)) {
            atomicInteger.incrementAndGet();
            JSONObject jSONObject2 = new JSONObject();
            try {
                String c = TextUtils.isEmpty(str3) ? WifiUtil.c(context) : str3;
                String b = TextUtils.isEmpty(str4) ? WifiUtil.b(context) : str4;
                if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(b)) {
                    jSONObject2.put(DeviceTagInterface.e, c);
                    jSONObject2.put("bssid", b.toUpperCase());
                }
                if (!TextUtils.isEmpty(str)) {
                    jSONObject2.put("did", str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject2.put("NewDeviceMac", str2.toUpperCase());
                }
            } catch (JSONException unused2) {
            }
            LogUtilGrey.a("getNewDevice", "device_new" + jSONObject2);
            CoreApi.a().a(context, getNetRequest("/home/device_new", jSONObject2), r6, Crypto.RC4, r0);
        }
        if (!TextUtils.isEmpty(str5)) {
            atomicInteger.incrementAndGet();
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("bindkey", str5);
            } catch (JSONException unused3) {
            }
            LogUtilGrey.a("getNewDevice", "check_bindkey" + jSONObject3);
            final AsyncCallback<List<Device>, Error> asyncCallback3 = asyncCallback;
            CoreApi.a().a(context, getNetRequest("/user/check_bindkey", jSONObject3), $$Lambda$DeviceApi$72YKwYPWOnNVDBevVSJusihSprc.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    LogUtilGrey.a("getNewDevice", "check_bindkey onSuccess:" + jSONObject);
                    int optInt = jSONObject.optInt("ret");
                    String optString = jSONObject.optString("bind_did");
                    if (optInt != 1 || TextUtils.isEmpty(optString)) {
                        onFailure(new Error(optInt, jSONObject.toString()));
                    } else {
                        DeviceApi.this.getNewDevice(context, optString, true, (String) null, (String) null, (String) null, (String) null, asyncCallback3);
                    }
                }

                public void onFailure(Error error) {
                    r0.onFailure(error);
                }
            });
        }
        if (atomicInteger.get() == 0) {
            r0.onFailure(new Error(-1, "no request params"));
        }
    }

    public AsyncHandle rpcAsyncRemote(Context context, String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str3));
        NetRequest.Builder a2 = new NetRequest.Builder().a("POST");
        return CoreApi.a().a(context, a2.b("/home/rpc/" + str).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle rpcAsyncRemote(Context context, String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str2));
        NetRequest.Builder a2 = new NetRequest.Builder().a("POST");
        NetRequest a3 = a2.b("/home/rpc/" + str).b((List<KeyValuePair>) arrayList).a();
        return CoreApi.a().a(context, a3, new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getDeviceSN(Context context, String str, String str2, String str3, String str4, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", str2);
            jSONObject.put("model", str3);
            jSONObject.put("token", str4);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("did", str);
            }
        } catch (JSONException unused) {
        }
        BluetoothLog.c(String.format("Apply did: [%s]", new Object[]{jSONObject}));
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/bltapplydid").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%s]", new Object[]{jSONObject}));
                if (jSONObject != null) {
                    return jSONObject.optString("did");
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle bindDeviceSN(Context context, String str, String str2, String str3, String str4, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("token", str3);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", XmBluetoothRecord.TYPE_PROP);
            jSONObject2.put("key", "bind_key");
            jSONObject2.put("value", str2);
            jSONArray.put(0, jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", XmBluetoothRecord.TYPE_PROP);
            jSONObject3.put("key", "smac");
            jSONObject3.put("value", str4);
            jSONArray.put(1, jSONObject3);
            jSONObject.put("props", jSONArray);
        } catch (JSONException unused) {
        }
        BluetoothLog.c(String.format("Bind did: [%s]", new Object[]{jSONObject}));
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/bltbind").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                if (jSONObject != null) {
                    return Boolean.valueOf(jSONObject.optBoolean("result"));
                }
                return false;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle verifyQrcode(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        return verifyQrcode(context, str, (String) null, asyncCallback);
    }

    public AsyncHandle verifyQrcodeNew(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bind_key", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/bind_with_bindkey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle verifyQrcode(Context context, String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("qrcode", str);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("model", str2);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/verify_qrcode").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle generateDid(Context context, String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", str);
            jSONObject.put("token", str2);
            jSONObject.put("model", str3);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/lapapplydid").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle lapBindDevice(Context context, String str, String str2, String str3, String str4, String str5, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac", str);
            jSONObject.put("token", str2);
            jSONObject.put("model", str3);
            jSONObject.put("did", str4);
            jSONObject.put("passwd", str5);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/lapbind").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getDetectIps(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceTagInterface.m, 2);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/getips").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle reportIpDetectResult(Context context, JSONArray jSONArray, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ipcost", jSONArray);
            jSONObject.put("net", "wifi");
            jSONObject.put(LogStrategyManager.SP_STRATEGY_KEY_TRIGGER, str2);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("sign", str);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/reportips").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle updateDeviceAuthData(Context context, String str, List<BaseAuthData.VoiceContrlData> list, DeviceAuthData deviceAuthData, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            Device b = SmartHomeDeviceManager.a().b(str);
            jSONObject.put("voice_did", str);
            jSONObject.put("voice_model", b.model);
            jSONObject.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, DeviceAuthFragment.f15018a);
            jSONObject.put("auth_all_device", deviceAuthData.f.get());
            if (!deviceAuthData.f.get()) {
                jSONObject.put("auto_auth_switch", deviceAuthData.g);
                JSONArray jSONArray = new JSONArray();
                for (BaseAuthData.VoiceContrlData next : list) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("ctrlable", Integer.parseInt(next.b));
                    jSONObject2.put("did", next.f15014a);
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("ctrl_devices", jSONArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/voicectrl/update_voice_device_auth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle updateHomeRoomAuthData(Context context, String str, HomeRoomAuthData homeRoomAuthData, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            Device b = SmartHomeDeviceManager.a().b(str);
            jSONObject.put("voice_did", str);
            jSONObject.put("voice_model", b.model);
            jSONObject.put(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, DeviceAuthFragment.b);
            jSONObject.put("auth_all_room", homeRoomAuthData.c());
            jSONObject.put("ctrl_share_device", homeRoomAuthData.d());
            jSONObject.put("ctrl_othercloud_device", homeRoomAuthData.e());
            jSONObject.put("home", homeRoomAuthData.f());
            jSONObject.put("rooms", new JSONArray(homeRoomAuthData.g()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/voicectrl/update_voice_device_auth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle updateSceneAuthData(Context context, String str, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("voicedevid", str);
            Device b = SmartHomeDeviceManager.a().b(str);
            if (b != null) {
                jSONObject2.put("voice_device_model", b.model);
            }
            if (list != null && list.size() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    try {
                        jSONArray2.put(Long.parseLong(list.get(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                jSONObject2.put("deny_scenes", jSONArray2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        jSONArray.put(jSONObject2);
        try {
            jSONObject.put("authdata", jSONArray);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/voicectrl/update_auth_scene").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getAllDeviceAuthData(Context context, List<String> list, AsyncCallback<BaseAuthData, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (String next : list) {
                Device b = SmartHomeDeviceManager.a().b(next);
                if (b != null) {
                    sb.append(next);
                    sb.append(",");
                    sb2.append(b.model);
                    sb2.append(",");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            if (sb.length() > 0) {
                sb2.deleteCharAt(sb2.length() - 1);
            }
            try {
                jSONObject.put("voice_did", sb.toString());
                jSONObject.put("voice_model", sb2.toString());
                jSONObject.put("ctrl_device_detail", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/voicectrl/get_voice_device_auth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<BaseAuthData>() {
            public BaseAuthData parse(JSONObject jSONObject) throws JSONException {
                String optString = jSONObject.optString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, "");
                if (TextUtils.equals(optString, DeviceAuthFragment.f15018a)) {
                    DeviceAuthData a2 = DeviceAuthData.a(jSONObject);
                    a2.b = optString;
                    a2.a();
                    return a2;
                } else if (!TextUtils.equals(optString, DeviceAuthFragment.b)) {
                    return null;
                } else {
                    HomeRoomAuthData a3 = HomeRoomAuthData.a(jSONObject);
                    a3.b = optString;
                    a3.a();
                    return a3;
                }
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getAllSceneAuthData(Context context, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        if (list != null && list.size() > 0) {
            for (String next : list) {
                Device b = SmartHomeDeviceManager.a().b(next);
                if (b != null) {
                    jSONArray.put(next);
                    jSONArray2.put(b.model);
                }
            }
            try {
                jSONObject.put("voicedevids", jSONArray);
                jSONObject.put("voice_device_models", jSONArray2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/voicectrl/get_auth_scene").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getNewDevice2(Context context, String[] strArr, String str, String str2, String str3, AsyncCallback<List<Device>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                jSONObject.put("sid", str2);
                jSONObject.put("did", str3);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/pseudo_device_new").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<Device>>() {
            public List<Device> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                BluetoothLog.c(String.format("/app/home/pseudo_device_new: %s", new Object[]{jSONObject}));
                if (optJSONArray == null) {
                    return arrayList;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    Device b = DeviceFactory.b(optJSONArray.getJSONObject(i));
                    if (b != null) {
                        arrayList.add(b);
                    }
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle setUserLicenseConfig(Context context, String str, String str2, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                jSONObject.put("did", str);
                jSONObject.put("type", str2);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/set_privacy_confirmation").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return null;
                }
                return "" + jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle reportNewBind(Context context, String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("regist_by", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AnonymousClass38 r5 = new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        };
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/home/report_new_bind").b((List<KeyValuePair>) arrayList).a(), r5, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle createLightGroup(Context context, List<String> list, String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jSONArray.put(list.get(i));
            }
            jSONObject.put("member_dids", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/group/create").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    return jSONObject.toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle createLightGroupV2(Context context, List<String> list, String str, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jSONArray.put(list.get(i));
            }
            jSONObject.put("member_dids", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/groupv2/create").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    return jSONObject.toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle modLightGroupOld(Context context, String str, List<String> list, List<String> list2, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("group_did", str);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jSONArray.put(list.get(i));
            }
            jSONObject.put("add_dids", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < list2.size(); i2++) {
                jSONArray2.put(list2.get(i2));
            }
            jSONObject.put("remove_dids", jSONArray2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/group/mod_member").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    return jSONObject.toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle modLightGroup(Context context, String str, List<String> list, List<String> list2, AsyncCallback<String, Error> asyncCallback, boolean... zArr) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("group_did", str);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jSONArray.put(list.get(i));
            }
            jSONObject.put("add_dids", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < list2.size(); i2++) {
                jSONArray2.put(list2.get(i2));
            }
            jSONObject.put("remove_dids", jSONArray2);
            if (zArr.length != 0) {
                jSONObject.put("keep_model", zArr[0]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/groupv2/mod_member").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    return jSONObject.toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle queryLightGroup(Context context, List<String> list, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                jSONArray.put(list.get(i));
            }
            jSONObject.put("group_did", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/groupv2/query_status").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject != null) {
                    return jSONObject.toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle getEncryptLtmk(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "own");
            jSONObject.put("did", str);
            jSONObject.put("keyid", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("getEncryptLtmk: [%s]", new Object[]{jSONObject}));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/share/askbluetoothkey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle setSecurePinSwitch(String str, boolean z, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "empty did"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(LtmkEncryptUtil.f14732a, z ? "1" : "0");
            jSONObject.put("extra_data", jSONObject2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/device/set_extra_data").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception unused) {
            return null;
        }
    }

    public AsyncHandle getRouterLocalUrl(String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_pub", str2);
            jSONObject.put("bssid", str3);
            jSONObject.put("router_id", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/router/apply_local_pwd").b((List<KeyValuePair>) arrayList).a(), $$Lambda$DeviceApi$4PQVpYVKULNn3g7Evn_6ez6ikR0.INSTANCE, Crypto.RC4, asyncCallback);
        } catch (Exception unused) {
            return null;
        }
    }

    public AsyncHandle bindNbIotDevice(Context context, String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("imei", str);
            jSONObject.put("bindKey", str2);
            jSONObject.put("sign", str3);
        } catch (JSONException unused) {
        }
        String jSONObject2 = jSONObject.toString();
        LogUtilGrey.a("NbiotStep", "request bindNbIotDevice:" + jSONObject2);
        arrayList.add(new KeyValuePair("data", jSONObject2));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/device/nb_iot_bind").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getHMBandSignature(Context context, String str, String str2, int i, int i2, final AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("publickeyhash", str2);
            jSONObject.put("device_type", i);
            jSONObject.put("appid", i2);
            jSONObject.put("random", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/huami/get_brand_sign").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                asyncCallback.onSuccess(jSONObject);
                return new JSONObject();
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle bindPushApDevice(Context context, String str, String str2, String str3, String str4, String str5, String str6, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", str);
            jSONObject.put("router_did", str2);
            jSONObject.put(DeviceTagInterface.e, str3);
            jSONObject.put("bssid", str4);
        } catch (JSONException unused) {
        }
        String jSONObject2 = jSONObject.toString();
        LogUtilGrey.a("NbiotStep", "request bindNbIotDevice:" + jSONObject2);
        arrayList.add(new KeyValuePair("data", jSONObject2));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/aiot/bind_confirm").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle requestBleWifiSpeakerState(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("dids", jSONArray);
        } catch (JSONException unused) {
        }
        String jSONObject2 = jSONObject.toString();
        LogUtilGrey.a(TAG, "request bleWifispeaker:" + jSONObject2);
        arrayList.add(new KeyValuePair("data", jSONObject2));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/blemesh/check_enable_switch").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
