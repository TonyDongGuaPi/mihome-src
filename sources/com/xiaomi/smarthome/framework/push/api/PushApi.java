package com.xiaomi.smarthome.framework.push.api;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PushApi {

    /* renamed from: a  reason: collision with root package name */
    private static PushApi f17670a;
    private static final Object b = new Object();

    private PushApi() {
    }

    public static PushApi a() {
        if (f17670a == null) {
            synchronized (b) {
                if (f17670a == null) {
                    f17670a = new PushApi();
                }
            }
        }
        return f17670a;
    }

    public AsyncHandle a(Context context, String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pushid", str);
            jSONObject.put("deviceid", str2);
            jSONObject.put("region", str3);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/reg").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle a(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pushid", str);
            jSONObject.put("deviceid", str2);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/unreg").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), ""));
            }
            return new AsyncHandle(null);
        }
    }
}
