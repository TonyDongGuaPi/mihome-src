package com.xiaomi.smarthome.device.api;

import android.content.Context;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.util.KeyValuePairUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraApi {
    private static CameraApi sInstance;
    private static final Object sLock = new Object();

    private CameraApi() {
    }

    public static CameraApi getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new CameraApi();
                }
            }
        }
        return sInstance;
    }

    public AsyncHandle bindCameraWsDevice(Context context, String str, int i, String str2, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            jSONObject.put("verify_code", str2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/binddevice").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return Integer.valueOf(jSONObject.getInt("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getBindKey(Context context, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/get_bindkey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.optString("bindkey");
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getBindKeyAndExpireTime(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/get_bindkey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle checkBindKey(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bindkey", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/check_bindkey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle dotriggleCameraAudioToken(Device device, AsyncCallback<Void, Error> asyncCallback) {
        return httpRequestAsync(CommonApplication.getAppContext(), String.format("http://%s/cgi-bin/start_bind_xm.cgi", new Object[]{device.ip}), (List<com.xiaomi.smarthome.library.http.KeyValuePair>) null, asyncCallback);
    }

    public AsyncHandle doBindCameraSuscess(Device device, AsyncCallback<Void, Error> asyncCallback) {
        return httpRequestAsync(CommonApplication.getAppContext(), String.format("http://%s/cgi-bin/bind_success_xm.cgi", new Object[]{device.ip}), (List<com.xiaomi.smarthome.library.http.KeyValuePair>) null, asyncCallback);
    }

    private AsyncHandle httpRequestAsync(Context context, String str, List<com.xiaomi.smarthome.library.http.KeyValuePair> list, final AsyncCallback<Void, Error> asyncCallback) {
        Call newCall = ClientUtil.a().newCall(new Request.Builder().url(KeyValuePairUtil.a(str, list)).tag(context).build());
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                String str;
                if (asyncCallback != null) {
                    AsyncCallback asyncCallback = asyncCallback;
                    int code = ErrorCode.INVALID.getCode();
                    if (iOException == null) {
                        str = "request failure";
                    } else {
                        str = iOException.getMessage();
                    }
                    asyncCallback.sendFailureMessage(new Error(code, str));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response == null) {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), "response is null"));
                    }
                } else if (response.isSuccessful()) {
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(response.code(), "request failure"));
                }
            }
        });
        return new AsyncHandle(newCall);
    }
}
