package com.xiaomi.smarthome.device.api;

import android.text.TextUtils;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.SyncCallback;
import com.xiaomi.smarthome.miio.Miio;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalDeviceApi {
    private static LocalDeviceApi sInstance;
    private static final Object sLock = new Object();

    private LocalDeviceApi() {
    }

    public static LocalDeviceApi getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new LocalDeviceApi();
                }
            }
        }
        return sInstance;
    }

    public static void parseRpcResponse(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code");
            if (AnonymousClass4.$SwitchMap$com$xiaomi$smarthome$frame$ErrorCode[ErrorCode.valueof(optInt).ordinal()] == 1) {
                JSONObject optJSONObject = jSONObject.optJSONObject("result");
                if (optJSONObject == null) {
                    optJSONObject = jSONObject;
                }
                if (asyncCallback != null) {
                    asyncCallback.sendSuccessMessage(optJSONObject);
                }
            } else if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(optInt, ""));
            }
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.ERROR_RESPONSE_JSON_FAIL.getCode(), ""));
            }
        }
    }

    /* renamed from: com.xiaomi.smarthome.device.api.LocalDeviceApi$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$smarthome$frame$ErrorCode = new int[ErrorCode.values().length];

        static {
            try {
                $SwitchMap$com$xiaomi$smarthome$frame$ErrorCode[ErrorCode.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void rpcAsync(String str, String str2, String str3, String str4, final AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str3)) {
            Miio.g("ip is empty");
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), "ip is empty"));
                return;
            }
            return;
        }
        MiioLocalAPI.a(str3, str4, Long.valueOf(str).longValue(), str2, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
                LocalDeviceApi.parseRpcResponse(str, asyncCallback);
            }
        });
    }

    public void rpcAsync(String str, String str2, final AsyncCallback<JSONObject, Error> asyncCallback) {
        Miio.g("localApi " + str2);
        String ipByDid = getIpByDid(str);
        if (TextUtils.isEmpty(ipByDid)) {
            Miio.g("ip is empty");
            asyncCallback.sendFailureMessage(new Error(ErrorCode.ERROR_NETWORK_ERROR.getCode(), "ip is empty"));
            return;
        }
        MiioLocalAPI.a(ipByDid, str2, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
                LocalDeviceApi.parseRpcResponse(str, asyncCallback);
            }
        });
    }

    private String getIpByDid(String str) {
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if (next.did != null && next.did.equalsIgnoreCase(str)) {
                return next.ip;
            }
        }
        return null;
    }

    public void fetchTokenByIp(String str, final AsyncCallback<String, Error> asyncCallback) {
        MiioLocalAPI.a(str, (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
                LocalDeviceApi.parseRpcResponse(str, new SyncCallback<JSONObject, Error>() {
                    public void onSuccess(JSONObject jSONObject) {
                        String optString = jSONObject.optString("token");
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(optString);
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(error);
                        }
                    }
                });
            }
        }, 9);
    }
}
