package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.util.Log;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteAsyncApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16378a = "RemoteAsyncApi";
    private static RemoteAsyncApi b;
    private static final Object c = new Object();

    private RemoteAsyncApi() {
    }

    public static RemoteAsyncApi a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new RemoteAsyncApi();
                }
            }
        }
        return b;
    }

    public AsyncHandle a(Context context, String[] strArr, int i, Object obj, AsyncCallback<RemoteAsyncApiHelper.RemoteAsyncResponse, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_id", i);
            jSONObject.put("params", obj);
            if (strArr != null && strArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String put : strArr) {
                    jSONArray.put(put);
                }
                jSONObject.put("dids", jSONArray);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/third/api").b((List<KeyValuePair>) arrayList).a(), new JsonParser<RemoteAsyncApiHelper.RemoteAsyncResponse>() {
            /* renamed from: a */
            public RemoteAsyncApiHelper.RemoteAsyncResponse parse(JSONObject jSONObject) throws JSONException {
                RemoteAsyncApiHelper.RemoteAsyncResponse remoteAsyncResponse = new RemoteAsyncApiHelper.RemoteAsyncResponse();
                remoteAsyncResponse.f16389a = jSONObject.optLong("sid", -1);
                remoteAsyncResponse.b = jSONObject.optInt(Constants.Name.INTERVAL, -1);
                remoteAsyncResponse.c = jSONObject.optInt("max_retry", -1);
                return remoteAsyncResponse;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, long j, int i, AsyncCallback<RemoteAsyncApiHelper.RemoteAsyncResult, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", j);
            jSONObject.put("retry_time", i);
        } catch (JSONException unused) {
        }
        String str = f16378a;
        Log.d(str, "getApiResult request data=" + jSONObject.toString());
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/third/api_result").b((List<KeyValuePair>) arrayList).a(), new JsonParser<RemoteAsyncApiHelper.RemoteAsyncResult>() {
            /* renamed from: a */
            public RemoteAsyncApiHelper.RemoteAsyncResult parse(JSONObject jSONObject) throws JSONException {
                RemoteAsyncApiHelper.RemoteAsyncResult remoteAsyncResult = new RemoteAsyncApiHelper.RemoteAsyncResult();
                remoteAsyncResult.f16390a = jSONObject;
                return remoteAsyncResult;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
