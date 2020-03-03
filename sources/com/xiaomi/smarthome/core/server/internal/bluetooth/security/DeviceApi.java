package com.xiaomi.smarthome.core.server.internal.bluetooth.security;

import android.text.TextUtils;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetError;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.net.NetResult;
import com.xiaomi.smarthome.core.server.internal.NetCallback;
import com.xiaomi.smarthome.core.server.internal.api.SmartHomeRc4Api;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.SmartHomeApiParser;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceApi {
    public static void a(String str, String str2, String str3, String str4, final AsyncCallback<String, Error> asyncCallback) {
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
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/device/bltapplydid").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass1 r4 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%s]", new Object[]{jSONObject}));
                if (jSONObject != null) {
                    return jSONObject.optString("did");
                }
                return null;
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r4, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void b(String str, String str2, String str3, String str4, final AsyncCallback<Boolean, Error> asyncCallback) {
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
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/device/bltbind").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass3 r7 = new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                if (jSONObject != null) {
                    return Boolean.valueOf(jSONObject.optBoolean("result"));
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
                SmartHomeApiParser.a().a(netResult, r7, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, String str10, String str11, String str12, AsyncCallback<Boolean, Error> asyncCallback) {
        int i2 = i;
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        String str13 = str;
        try {
            jSONObject.put("mac", str);
            String str14 = str2;
            jSONObject.put("model", str2);
            String str15 = str3;
            jSONObject.put("token", str3);
            String str16 = str4;
            jSONObject.put(BluetoothConstants.ai, str4);
            String str17 = str5;
            jSONObject.put("name", str5);
            String str18 = str6;
            jSONObject.put("device_cert", str6);
            String str19 = str7;
            jSONObject.put("manu_cert", str7);
            String str20 = str10;
            jSONObject.put("app_signature", str10);
            jSONObject.put("sign_data", str11);
            if (!TextUtils.isEmpty(str8)) {
                String str21 = str8;
                jSONObject.put("beacon_key", str8);
            }
            if (!TextUtils.isEmpty(str9)) {
                String str22 = str9;
                jSONObject.put("cloud_key", str9);
            }
            if (i2 != 0) {
                jSONObject.put("encrypt_type", i);
            }
            if (!TextUtils.isEmpty(str12)) {
                jSONObject.put("bindkey", str12);
            }
            jSONObject.put("props", new JSONArray());
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/device/blelockbind").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass5 r1 = new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                boolean z = true;
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                if (jSONObject == null || !jSONObject.has("code")) {
                    return false;
                }
                if (jSONObject.getInt("code") != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        };
        final AsyncCallback<Boolean, Error> asyncCallback2 = asyncCallback;
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r1, asyncCallback2);
            }

            public void a(NetError netError) {
                if (asyncCallback2 != null) {
                    asyncCallback2.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        a("own", str, "0", asyncCallback);
    }

    public static void a(String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        a("share", str, str2, asyncCallback);
    }

    public static void a(String str, String str2, String str3, final AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("did", str2);
            long j = 0;
            try {
                j = Long.valueOf(str3).longValue();
            } catch (Exception unused) {
            }
            jSONObject.put("keyid", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("askSecurityShareKey: [%s]", new Object[]{jSONObject}));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/share/askbluetoothkey").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass7 r5 = new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject;
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r5, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void c(String str, String str2, String str3, String str4, final AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", str2);
            jSONObject2.put("key", str3);
            jSONObject2.put("value", str4);
            jSONArray.put(jSONObject2);
            jSONObject.put("datas", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("reportDeviceEvent: [%s]", new Object[]{jSONObject}));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/device/event").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess("");
                }
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(final AsyncCallback<Long, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/device/get_utc_time").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass10 r1 = new JsonParser<Long>() {
            /* renamed from: a */
            public Long parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                return Long.valueOf(jSONObject.optLong("result", -1));
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

    public static void a(int i, String str, String str2, String str3, String str4, String str5, final AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pdid", i);
            jSONObject.put("dev_mesh_pub", str);
            jSONObject.put("manu_cert_id", str2);
            jSONObject.put("dev_cert", str3);
            jSONObject.put("dev_info", str4);
            jSONObject.put("code", str5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("authBleMesh: [%s]", new Object[]{jSONObject}));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/blemesh/auth").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass12 r3 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) {
                BluetoothLog.c(String.format("authBleMesh Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject.toString();
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r3, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(long j, String str, String str2, String str3, String str4, final AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pdid", j);
            jSONObject.put("mac", str);
            jSONObject.put("sign", str2);
            jSONObject.put("did", str3);
            jSONObject.put("token", str4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("bindBleMesh: [%s]", new Object[]{jSONObject}));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/blemesh/bind").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass14 r3 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) {
                BluetoothLog.c(String.format("bindBleMesh Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject.toString();
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r3, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void b(final AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/blemesh/ctl_info").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass16 r1 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("queryBleMeshCtlInfo Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject.toString();
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

    public static void a(int i, final AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pdid", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/blemesh/query_model").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass18 r0 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                BluetoothLog.c(String.format("queryBleMeshModelInfo Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject.toString();
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r0, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(boolean z, String str, String str2, String str3, final AsyncCallback<Boolean, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", z ^ true ? 1 : 0);
            jSONObject.put("did", str);
            jSONObject.put("device_key", str2);
            jSONObject.put("auth", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("sendBleMeshProvisionResult: [%s]", new Object[]{jSONObject}));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/blemesh/provision_done").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass20 r4 = new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                boolean z = true;
                BluetoothLog.c(String.format("sendBleMeshProvisionResult Http Response: [%S]", new Object[]{jSONObject}));
                if (jSONObject.optInt("code", -1) != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r4, asyncCallback);
            }

            public void a(NetError netError) {
                BluetoothMyLogger.d("sendBleMeshProvisionResult failed = " + netError.b());
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, final AsyncCallback<Void, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("fw_ver", str2);
            jSONObject.put("hw_ver", str3);
            jSONObject.put("latitude", str4);
            jSONObject.put("longitude", str5);
            jSONObject.put("iternetip", str6);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("reportBleMeshDevInfo: [%s]", new Object[]{jSONObject}));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/v2/blemesh/dev_info").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                }
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void b(String str, final String str2, final AsyncCallback<Void, Error> asyncCallback) {
        BluetoothApi.a(str, (BleResponse<String>) new BleResponse<String>() {
            public void a(int i, String str) {
                if (i == 0 && !TextUtils.isEmpty(str)) {
                    DeviceApi.a(str2, str, "", "", "", "", (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (asyncCallback != null) {
                                asyncCallback.onSuccess(null);
                            }
                        }

                        public void onFailure(Error error) {
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(error);
                            }
                        }
                    });
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "read version failed"));
                }
            }
        });
    }

    public static void b(String str, final AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("authBleMesh: [%s]", new Object[]{jSONObject}));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/blemesh/query_dev").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass24 r0 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject.toString();
            }
        };
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                SmartHomeApiParser.a().a(netResult, r0, asyncCallback);
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void a(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(LtmkEncryptUtil.f14732a, z ? "1" : "0");
            jSONObject.put("extra_data", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/device/set_extra_data").b((List<KeyValuePair>) arrayList).a();
        BluetoothLog.c(String.format("setShowSecurePin: [%s]", new Object[]{jSONObject}));
        SmartHomeRc4Api.a().a(a2, (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            public void a(NetError netError) {
            }

            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                BluetoothLog.c(String.format("setShowSecurePin response: [%s]", new Object[]{netResult.c}));
            }
        });
    }

    public static void c(String str, String str2, final AsyncCallback<Void, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("fw_ver", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        BluetoothLog.c(String.format("reportCommonBleVersion: [%s]", new Object[]{jSONObject}));
        SmartHomeRc4Api.a().a(new NetRequest.Builder().a("POST").b("/v2/device/bledevice_info").b((List<KeyValuePair>) arrayList).a(), (NetCallback<NetResult, NetError>) new NetCallback<NetResult, NetError>() {
            /* renamed from: a */
            public void b(NetResult netResult) {
            }

            /* renamed from: b */
            public void a(NetResult netResult) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                }
            }

            public void a(NetError netError) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(netError.a(), netError.b()));
                }
            }
        });
    }

    public static void c(final AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/user/get_bindkey").b((List<KeyValuePair>) arrayList).a();
        final AnonymousClass28 r1 = new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) {
                BluetoothLog.c(String.format("Http Response: [%S]", new Object[]{jSONObject}));
                return jSONObject.optString("bindkey");
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
}
