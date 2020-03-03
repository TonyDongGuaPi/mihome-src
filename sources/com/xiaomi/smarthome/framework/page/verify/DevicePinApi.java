package com.xiaomi.smarthome.framework.page.verify;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DevicePinApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f17039a = new Object();
    private static DevicePinApi b;

    private DevicePinApi() {
    }

    public static DevicePinApi a() {
        if (b == null) {
            synchronized (f17039a) {
                if (b == null) {
                    b = new DevicePinApi();
                }
            }
        }
        return b;
    }

    public AsyncHandle a(Context context, String str, String str2, AsyncCallback<Integer, Error> asyncCallback) {
        return b(context, str, str2, "", asyncCallback);
    }

    public AsyncHandle b(Context context, String str, String str2, AsyncCallback<Integer, Error> asyncCallback) {
        return b(context, str, "", str2, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, String str2, String str3, AsyncCallback<Integer, Error> asyncCallback) {
        return b(context, str, str2, str3, asyncCallback);
    }

    private AsyncHandle b(Context context, String str, String str2, String str3, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pincode", str2);
            jSONObject.put("oldpincode", str3);
            jSONObject.put("did", str);
        } catch (JSONException e) {
            e.printStackTrace();
            asyncCallback.onFailure(new Error(-35, e.getMessage()));
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/pincode/set").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            /* renamed from: a */
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return Integer.valueOf(jSONObject.optInt("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle c(Context context, String str, String str2, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pincode", str2);
            jSONObject.put("did", str);
        } catch (JSONException e) {
            e.printStackTrace();
            asyncCallback.onFailure(new Error(-1, e.getMessage()));
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/pincode/check").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            /* renamed from: a */
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return Integer.valueOf(jSONObject.optInt("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle d(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("ltmk_key", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/device/blelockupdatekey").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Void>() {
            /* renamed from: a */
            public Void parse(JSONObject jSONObject) throws JSONException {
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
