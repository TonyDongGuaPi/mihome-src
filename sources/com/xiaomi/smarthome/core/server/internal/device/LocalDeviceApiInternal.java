package com.xiaomi.smarthome.core.server.internal.device;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.core.server.internal.device.api.AsyncResponseCallback;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.ErrorCode;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalDeviceApiInternal {

    /* renamed from: a  reason: collision with root package name */
    private static LocalDeviceApiInternal f14533a;
    private static Object b = new Object();

    private LocalDeviceApiInternal() {
    }

    public static LocalDeviceApiInternal a() {
        if (f14533a == null) {
            synchronized (b) {
                if (f14533a == null) {
                    f14533a = new LocalDeviceApiInternal();
                }
            }
        }
        return f14533a;
    }

    public void a(String str, String str2, String str3, String str4, final AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        if (!TextUtils.isEmpty(str3)) {
            MiioLocalAPI.a(str3, str4, Long.valueOf(str).longValue(), str2, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                public void onResponse(String str) {
                    LocalDeviceApiInternal.a(str, asyncResponseCallback);
                }
            });
        } else if (asyncResponseCallback != null) {
            asyncResponseCallback.a(-9999, "ip is empty");
        }
    }

    public void b(String str, String str2, String str3, String str4, final AsyncResponseCallback<String> asyncResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("RPC", "device ip is empty");
            if (asyncResponseCallback != null) {
                try {
                    asyncResponseCallback.a(-9999, "ip is empty");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            MiioLocalAPI.a(str, str4, Long.valueOf(str2).longValue(), str3, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                public void onResponse(String str) {
                    if (asyncResponseCallback != null) {
                        try {
                            asyncResponseCallback.a(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public static void a(String str, AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code");
            if (AnonymousClass4.f14538a[ErrorCode.valueof(optInt).ordinal()] == 1) {
                JSONObject optJSONObject = jSONObject.optJSONObject("result");
                if (optJSONObject == null) {
                    optJSONObject = jSONObject;
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(optJSONObject);
                }
            } else if (asyncResponseCallback != null) {
                asyncResponseCallback.a(optInt);
            }
        } catch (JSONException unused) {
            if (asyncResponseCallback != null) {
                asyncResponseCallback.a(-31);
            }
        }
    }

    /* renamed from: com.xiaomi.smarthome.core.server.internal.device.LocalDeviceApiInternal$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f14538a = new int[ErrorCode.values().length];

        static {
            try {
                f14538a[ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void b(String str, final AsyncResponseCallback<String> asyncResponseCallback) {
        MiioLocalAPI.a(str, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
                Log.d("scan2", str);
                LocalDeviceApiInternal.a(str, new AsyncResponseCallback<JSONObject>() {
                    public void a(JSONObject jSONObject) {
                        String optString = jSONObject.optString("token");
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(optString);
                        }
                    }

                    public void a(int i) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(i);
                        }
                    }

                    public void a(int i, Object obj) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(i, obj);
                        }
                    }
                });
            }
        }, 9);
    }
}
