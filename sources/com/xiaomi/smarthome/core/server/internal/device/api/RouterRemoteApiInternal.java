package com.xiaomi.smarthome.core.server.internal.device.api;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.NetHandle;
import com.xiaomi.smarthome.core.server.internal.api.RouterApi;
import com.xiaomi.smarthome.core.server.internal.device.api.RemoteRouterMitvApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.RouterApiParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterRemoteApiInternal {

    /* renamed from: a  reason: collision with root package name */
    private static Object f14568a = new Object();
    private static RouterRemoteApiInternal b;

    public static RouterRemoteApiInternal a() {
        if (b == null) {
            synchronized (f14568a) {
                if (b == null) {
                    b = new RouterRemoteApiInternal();
                }
            }
        }
        return b;
    }

    public NetHandle a(Context context, RemoteRouterMitvApi.MiRouterInfo miRouterInfo, final AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("serialNumber", miRouterInfo.f14567a));
        arrayList.add(new KeyValuePair("deviceID", miRouterInfo.b));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/s/device/isDeviceBound").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass1 r0 = new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.getBoolean("result"));
            }
        };
        return RouterApi.a().a(a2, miRouterInfo.b, true, new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                RouterApiParser.a().a(netResult, r0, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public void a(final Device device, final NetCallback<NetResult, NetError> netCallback) {
        String k = device.k();
        if (k.startsWith("miwifi.")) {
            k = k.substring(7);
        }
        AnonymousClass3 r1 = new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (netCallback != null) {
                    try {
                        int optInt = new JSONObject(netResult.c).optInt("code");
                        if (optInt != 0) {
                            DeviceApiInternal.a().a(device, optInt, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                                /* renamed from: a */
                                public void b(NetResult netResult) {
                                }

                                /* renamed from: b */
                                public void a(NetResult netResult) {
                                    if (netCallback != null) {
                                        netCallback.a(netResult);
                                    }
                                }

                                public void a(NetError netError) {
                                    if (netCallback != null) {
                                        netCallback.a(netError);
                                    }
                                }
                            });
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    netCallback.a(netResult);
                }
            }

            public void a(NetError netError) {
                DeviceApiInternal.a().a(device, netError != null ? netError.a() : -1, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
                    /* renamed from: a */
                    public void b(NetResult netResult) {
                    }

                    /* renamed from: b */
                    public void a(NetResult netResult) {
                        if (netCallback != null) {
                            netCallback.a(netResult);
                        }
                    }

                    public void a(NetError netError) {
                        if (netCallback != null) {
                            netCallback.a(netError);
                        }
                    }
                });
            }
        };
        if (device.S()) {
            a().a(k, (NetCallback<NetResult, NetError>) r1);
        } else if (device.V() || device.W()) {
            a().b(k, r1);
        }
    }

    public NetHandle a(String str, NetCallback<NetResult, NetError> netCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("deviceID", str));
        return RouterApi.a().a(new NetRequest.Builder().a("POST").b("/s/admin/dismiss").b((List<KeyValuePair>) arrayList).a(), str, true, netCallback);
    }

    public NetHandle b(String str, NetCallback<NetResult, NetError> netCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("deviceId", str));
        return RouterApi.a().a(new NetRequest.Builder().a("POST").b("/s/admin/demoteSelf").b((List<KeyValuePair>) arrayList).a(), str, true, netCallback);
    }
}
