package com.xiaomi.smarthome.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.VirtualDevice;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.core.server.internal.homeroom.CoreSharedHomeManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceListApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14825a = "DeviceListApi";
    /* access modifiers changed from: private */
    public final AllDeviceData b = new AllDeviceData();
    /* access modifiers changed from: private */
    public NetHandle c;
    private Context d;

    public DeviceListApi(Context context) {
        this.d = context;
    }

    public static class AllDeviceData {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<Device> f14832a;
        public ArrayList<Device> b;
        public ArrayList<Device> c;
        public ArrayList<Device> d;
        /* access modifiers changed from: private */
        public NetError e;

        public ArrayList<Device> a() {
            return this.f14832a;
        }

        public ArrayList<Device> b() {
            return this.b;
        }

        public ArrayList<Device> c() {
            return this.c;
        }

        public ArrayList<Device> d() {
            return this.d;
        }

        public void e() {
            this.f14832a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }
    }

    public NetHandle a(JSONObject jSONObject, final NetCallback<AllDeviceData, NetError> netCallback) {
        this.b.e();
        a();
        AnonymousClass1 r0 = new NetCallback<AllDeviceData, NetError>() {
            private boolean c;

            /* renamed from: a */
            public void b(AllDeviceData allDeviceData) {
            }

            /* renamed from: b */
            public void a(AllDeviceData allDeviceData) {
                if (netCallback != null && allDeviceData.f14832a != null && allDeviceData.b != null && allDeviceData.c != null && allDeviceData.d != null) {
                    netCallback.a(allDeviceData);
                    NetHandle unused = DeviceListApi.this.c = null;
                }
            }

            public void a(NetError netError) {
                if (netCallback != null && DeviceListApi.this.b.e != null) {
                    synchronized (this) {
                        if (!this.c) {
                            this.c = true;
                            netCallback.a(DeviceListApi.this.b.e);
                            DeviceListApi.this.a();
                        }
                    }
                }
            }
        };
        final NetHandle b2 = b(jSONObject, r0);
        final NetHandle a2 = a((NetCallback<AllDeviceData, NetError>) r0);
        final NetHandle b3 = b((NetCallback<AllDeviceData, NetError>) r0);
        final NetHandle c2 = c(r0);
        this.c = new NetHandle((Call) null) {
            public void a() {
                b2.a();
                a2.a();
                b3.a();
                c2.a();
            }
        };
        return this.c;
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.c != null) {
            synchronized (this) {
                if (this.c != null) {
                    this.c.a();
                    this.c = null;
                }
            }
        }
    }

    public NetHandle a(final NetCallback<AllDeviceData, NetError> netCallback) {
        LogUtil.c(f14825a, "updateLocalDeviceList core /v2/home/local_device_list");
        return SmartHomeRc4Api.a().a(a(new JSONObject(), "/v2/home/local_device_list"), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netResult == null || netResult.c == null) {
                    LogUtil.c(DeviceListApi.f14825a, "core /v2/home/local_device_list result.mResponse null");
                    if (netCallback != null) {
                        NetError unused = DeviceListApi.this.b.e = new NetError(-1, "result.mResponse null");
                        netCallback.a(DeviceListApi.this.b.e);
                        return;
                    }
                    return;
                }
                try {
                    ArrayList<Device> arrayList = new ArrayList<>();
                    JSONObject optJSONObject = new JSONObject(netResult.c).optJSONObject("result");
                    if (optJSONObject != null) {
                        JSONArray optJSONArray = optJSONObject.optJSONArray("local_list");
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("third_list");
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                if (optJSONObject2 != null) {
                                    arrayList.add(DeviceFactory.a(optJSONObject2));
                                }
                            }
                        }
                        if (optJSONArray2 != null) {
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                                if (optJSONObject3 != null) {
                                    arrayList.add(DeviceFactory.a(optJSONObject3));
                                }
                            }
                        }
                        LogUtil.c(DeviceListApi.f14825a, "core /v2/home/local_device_list onSuccess size:" + arrayList.size());
                    } else {
                        LogUtil.c(DeviceListApi.f14825a, "core /v2/home/local_device_list onSuccess result:" + netResult.c);
                    }
                    if (netCallback != null) {
                        DeviceListApi.this.b.b = arrayList;
                        netCallback.a(DeviceListApi.this.b);
                    }
                } catch (Exception unused2) {
                    LogUtil.c(DeviceListApi.f14825a, "core /v2/home/local_device_list Exception result:" + netResult.c);
                    if (netCallback != null) {
                        NetError unused3 = DeviceListApi.this.b.e = new NetError(-1, "parse Exception");
                        netCallback.a(DeviceListApi.this.b.e);
                    }
                }
            }

            public void a(NetError netError) {
                LogUtil.c(DeviceListApi.f14825a, "core /v2/home/local_device_list onFailure error:" + netError);
                if (netCallback != null) {
                    DeviceListApi.this.b.b = new ArrayList<>();
                    netCallback.a(netError);
                }
            }
        });
    }

    public NetHandle b(final NetCallback<AllDeviceData, NetError> netCallback) {
        LogUtil.c(f14825a, "updateTrialDeviceList core /v2/home/trial_device_info");
        return SmartHomeRc4Api.a().a(a(new JSONObject(), "/v2/home/trial_device_info"), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netResult == null || netResult.c == null) {
                    LogUtil.c(DeviceListApi.f14825a, "core /v2/home/trial_device_info result.mResponse null");
                    if (netCallback != null) {
                        NetError unused = DeviceListApi.this.b.e = new NetError(-1, "result.mResponse null");
                        netCallback.a(DeviceListApi.this.b.e);
                        return;
                    }
                    return;
                }
                try {
                    JSONArray optJSONArray = new JSONObject(netResult.c).optJSONArray("result");
                    if (optJSONArray != null) {
                        ArrayList<Device> arrayList = new ArrayList<>();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                arrayList.add(VirtualDevice.b(optJSONObject));
                            }
                        }
                        DeviceListApi.this.b.c = arrayList;
                        LogUtil.c(DeviceListApi.f14825a, "core /v2/home/trial_device_info onSuccess size:" + arrayList.size());
                    } else {
                        LogUtil.c(DeviceListApi.f14825a, "core /v2/home/trial_device_info onSuccess result:" + netResult.c);
                    }
                    if (netCallback != null) {
                        netCallback.a(DeviceListApi.this.b);
                    }
                } catch (Exception unused2) {
                    LogUtil.c(DeviceListApi.f14825a, "core /v2/home/trial_device_info Exception result:" + netResult.c);
                    if (netCallback != null) {
                        NetError unused3 = DeviceListApi.this.b.e = new NetError(-1, "parse Exception");
                        netCallback.a(DeviceListApi.this.b.e);
                    }
                }
            }

            public void a(NetError netError) {
                LogUtil.c(DeviceListApi.f14825a, "core /v2/home/trial_device_info onFailure error:" + netError);
                if (netCallback != null) {
                    DeviceListApi.this.b.c = new ArrayList<>();
                    netCallback.a(netError);
                }
            }
        });
    }

    public NetHandle c(final NetCallback<AllDeviceData, NetError> netCallback) {
        LogUtil.a(f14825a, "updateSharedHomeDeviceList");
        return CoreSharedHomeManager.a().a((NetCallback<ArrayList<Device>, NetError>) new NetCallback<ArrayList<Device>, NetError>() {
            /* renamed from: a */
            public void b(ArrayList<Device> arrayList) {
            }

            /* renamed from: b */
            public void a(ArrayList<Device> arrayList) {
                LogUtil.a(DeviceListApi.f14825a, "updateSharedHomeDeviceList onSuccess");
                if (netCallback != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    DeviceListApi.this.b.d = arrayList;
                    netCallback.a(DeviceListApi.this.b);
                }
            }

            public void a(NetError netError) {
                String str;
                StringBuilder sb = new StringBuilder();
                sb.append("updateSharedHomeDeviceList onFailure ");
                if (netError == null) {
                    str = null;
                } else {
                    str = netError.a() + ":" + netError.b();
                }
                sb.append(str);
                LogUtil.a(DeviceListApi.f14825a, sb.toString());
                if (netCallback != null) {
                    NetError unused = DeviceListApi.this.b.e = netError;
                    netCallback.a(DeviceListApi.this.b.e);
                }
            }
        });
    }

    public NetHandle b(final JSONObject jSONObject, final NetCallback<AllDeviceData, NetError> netCallback) {
        LogUtil.c(f14825a, "updateBindDeviceList core /v2/home/device_list_page");
        final NetHandle.NetHandleWrap netHandleWrap = new NetHandle.NetHandleWrap();
        netHandleWrap.a(SmartHomeRc4Api.a(this.d).a(a(jSONObject, "/v2/home/device_list_page"), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            private ArrayList<Device> e = new ArrayList<>();

            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netHandleWrap.b()) {
                    LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page isCancel times:" + this.e.size());
                    if (netCallback != null) {
                        NetError unused = DeviceListApi.this.b.e = new NetError(-1, "NetHandleWrap canceled");
                        netCallback.a(DeviceListApi.this.b.e);
                    }
                } else if (netResult == null || TextUtils.isEmpty(netResult.c)) {
                    LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page no netResult times:" + this.e.size());
                    if (netCallback != null) {
                        DeviceListApi.this.b.f14832a = this.e;
                        netCallback.a(DeviceListApi.this.b);
                    }
                } else {
                    try {
                        JSONObject optJSONObject = new JSONObject(netResult.c).optJSONObject("result");
                        if (optJSONObject != null) {
                            JSONArray optJSONArray = optJSONObject.optJSONArray("list");
                            if (optJSONArray != null) {
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                    if (optJSONObject2 != null) {
                                        this.e.add(DeviceFactory.a(optJSONObject2));
                                    }
                                }
                            }
                            if (optJSONObject.optBoolean("has_more")) {
                                String optString = optJSONObject.optString("next_start_did");
                                LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page request next page with did:" + optString);
                                jSONObject.put("start_did", optString);
                                netHandleWrap.a(SmartHomeRc4Api.a().a(DeviceListApi.a(jSONObject, "/v2/home/device_list_page"), (NetCallback<NetResult, NetError>) this));
                                return;
                            }
                            LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page success times:" + this.e.size());
                            if (netCallback != null) {
                                DeviceListApi.this.b.f14832a = this.e;
                                netCallback.a(DeviceListApi.this.b);
                                return;
                            }
                            return;
                        }
                        LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page resultObject null times:" + this.e.size());
                        if (netCallback != null) {
                            DeviceListApi.this.b.f14832a = this.e;
                            netCallback.a(DeviceListApi.this.b);
                        }
                    } catch (JSONException e2) {
                        LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page JSONException times:" + this.e.size());
                        if (netCallback != null) {
                            NetError unused2 = DeviceListApi.this.b.e = new NetError(-1, Log.getStackTraceString(e2));
                            netCallback.a(DeviceListApi.this.b.e);
                        }
                    }
                }
            }

            public void a(NetError netError) {
                LogUtil.c(DeviceListApi.f14825a, "core /v2/home/device_list_page onFailure times:" + this.e.size());
                if (netCallback != null) {
                    NetError unused = DeviceListApi.this.b.e = netError == null ? new NetError(-1, "onFailure null error") : netError;
                    netCallback.a(netError);
                }
            }
        }));
        return netHandleWrap;
    }

    public static NetRequest a(JSONObject jSONObject, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return new NetRequest.Builder().a("POST").b(str).b((List<KeyValuePair>) arrayList).a();
    }
}
