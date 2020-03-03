package com.xiaomi.smarthome.core.server.internal.device.api;

import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.device.DeviceHandle;
import com.xiaomi.smarthome.core.server.internal.device.api.DeviceListApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceApiInternal {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f14552a = new Object();
    private static final String b = "DeviceApiInternal";
    private static DeviceApiInternal c;

    private DeviceApiInternal() {
    }

    public static DeviceApiInternal a() {
        if (c == null) {
            synchronized (f14552a) {
                if (c == null) {
                    c = new DeviceApiInternal();
                }
            }
        }
        return c;
    }

    public DeviceHandle a(String str, final AsyncResponseCallback<String> asyncResponseCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/home/batchrpc").b((List<KeyValuePair>) arrayList).a();
        if (AccountManager.a().c() == AccountType.MI) {
            NetHandle a3 = SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(netResult.c);
                    }
                }

                public void a(NetError netError) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(netError.a(), netError.b());
                    }
                }
            });
            if (a3 != null) {
                return new DeviceHandle(a3);
            }
            return null;
        }
        if (asyncResponseCallback != null) {
            asyncResponseCallback.a(-9999, "Account type not supported!");
        }
        return null;
    }

    public DeviceHandle a(String str, String str2, String str3, final AsyncResponseCallback<String> asyncResponseCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str3));
        NetRequest.Builder a2 = new NetRequest.Builder().a("POST");
        NetRequest a3 = a2.b("/home/rpc/" + str).b((List<KeyValuePair>) arrayList).a();
        if (AccountManager.a().c() == AccountType.MI) {
            NetHandle a4 = SmartHomeRc4Api.a().a(a3, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(netResult.c);
                    }
                }

                public void a(NetError netError) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(netError.a(), netError.b());
                    }
                }
            });
            if (a4 != null) {
                return new DeviceHandle(a4);
            }
            return null;
        }
        if (asyncResponseCallback != null) {
            asyncResponseCallback.a(-9999, "Account type not supported!");
        }
        return null;
    }

    public DeviceHandle b(String str, String str2, String str3, final AsyncResponseCallback<String> asyncResponseCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str3));
        NetRequest.Builder a2 = new NetRequest.Builder().a("POST");
        NetRequest a3 = a2.b("/home/rpcv2/" + str).b((List<KeyValuePair>) arrayList).a();
        if (AccountManager.a().c() == AccountType.MI) {
            NetHandle a4 = SmartHomeRc4Api.a().a(a3, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                /* renamed from: a */
                public void b(NetResult netResult) {
                }

                /* renamed from: b */
                public void a(NetResult netResult) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(netResult.c);
                    }
                }

                public void a(NetError netError) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(netError.a(), netError.b());
                    }
                }
            });
            if (a4 != null) {
                return new DeviceHandle(a4);
            }
            return null;
        }
        if (asyncResponseCallback != null) {
            asyncResponseCallback.a(-9999, "Account type not supported!");
        }
        return null;
    }

    public NetHandle a(String str, String str2, NetCallback<NetResult, NetError> netCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("newName", str2);
            jSONObject.put("did", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/device/rename").b((List<KeyValuePair>) arrayList).a();
        if (AccountManager.a().c() == AccountType.MI) {
            return SmartHomeRc4Api.a().a(a2, netCallback);
        }
        if (netCallback == null) {
            return null;
        }
        netCallback.a(new NetError(-9999, "account type not supported!"));
        return null;
    }

    public NetHandle a(JSONArray jSONArray, NetCallback<NetResult, NetError> netCallback) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            if (netCallback != null) {
                netCallback.a(new NetError(-9999, "list empty"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("devList", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/user/del_owner_device_batch").b((List<KeyValuePair>) arrayList).a();
        if (AccountManager.a().c() == AccountType.MI) {
            return SmartHomeRc4Api.a().a(a2, netCallback);
        }
        if (netCallback != null) {
            netCallback.a(new NetError(-9999, "account type not supported!"));
        }
        return null;
    }

    public NetHandle a(List<String> list, NetCallback<DeviceListApi.AllDeviceData, NetError> netCallback) {
        if (list == null || list.isEmpty()) {
            if (netCallback != null) {
                netCallback.a(new NetError(-9999, "list empty"));
            }
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dids", jSONArray);
            if (AccountManager.a().c() == AccountType.MI) {
                return new DeviceListApi(CoreService.getAppContext()).a(jSONObject, netCallback);
            }
            if (netCallback != null) {
                netCallback.a(new NetError(-9999, "account type not supported!"));
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            if (netCallback != null) {
                netCallback.a(new NetError(-9999, "param exception"));
            }
            return null;
        }
    }

    public NetHandle a(Device device, int i, NetCallback<NetResult, NetError> netCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("did", device.k());
            jSONObject2.put("pid", device.f());
            jSONArray.put(jSONObject2);
            jSONObject.put("devList", jSONArray);
            jSONObject.put("errorCode", i);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/home/batch_unbind").b((List<KeyValuePair>) arrayList).a();
        if (AccountManager.a().c() == AccountType.MI) {
            return SmartHomeRc4Api.a().a(a2, netCallback);
        }
        if (netCallback == null) {
            return null;
        }
        netCallback.a(new NetError(-9999, "account type not supported!"));
        return null;
    }
}
