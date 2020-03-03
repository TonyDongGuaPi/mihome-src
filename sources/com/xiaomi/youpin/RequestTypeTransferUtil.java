package com.xiaomi.youpin;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.ProgressCallback;
import com.xiaomi.plugin.Request;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestTypeTransferUtil {
    public static List<RequestParams> a(JsonObject jsonObject) {
        return a(jsonObject.toString());
    }

    public static List<RequestParams> a(String str) {
        JsonObject asJsonObject;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || (asJsonObject = new JsonParser().parse(str).getAsJsonObject()) == null) {
            return arrayList;
        }
        for (Map.Entry next : asJsonObject.entrySet()) {
            String str2 = "";
            String str3 = "";
            JsonObject jsonObject = null;
            String str4 = (String) next.getKey();
            JsonElement jsonElement = (JsonElement) next.getValue();
            if (jsonElement.isJsonObject()) {
                JsonObject asJsonObject2 = jsonElement.getAsJsonObject();
                JsonElement jsonElement2 = asJsonObject2.get("model");
                if (jsonElement2.isJsonPrimitive()) {
                    str2 = jsonElement2.getAsString();
                }
                JsonElement jsonElement3 = asJsonObject2.get("action");
                if (jsonElement3.isJsonPrimitive()) {
                    str3 = jsonElement3.getAsString();
                }
                if (asJsonObject2.get("parameters").isJsonObject()) {
                    jsonObject = asJsonObject2.getAsJsonObject();
                }
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str4)) {
                    RequestParams requestParams = new RequestParams(str2, str3, jsonObject);
                    requestParams.key = str4;
                    arrayList.add(requestParams);
                }
            }
        }
        return arrayList;
    }

    public static RequestParams a(com.xiaomi.plugin.RequestParams requestParams) {
        RequestParams requestParams2 = new RequestParams(requestParams.model, requestParams.action, requestParams.parameters);
        requestParams2.key = requestParams.key;
        return requestParams2;
    }

    public static <T> YouPinJsonParser<T> a(final Parser<T> parser) {
        if (parser == null) {
            return null;
        }
        return new YouPinJsonParser<T>() {
            public T b(JsonElement jsonElement) {
                return parser.parse(jsonElement);
            }
        };
    }

    public static <T> RequestAsyncCallback<T, NetError> a(final Callback<T> callback) {
        if (callback == null) {
            return null;
        }
        return new RequestAsyncCallback<T, NetError>() {
            public void a(T t) {
                callback.onCache(t);
            }

            public void a(T t, boolean z) {
                callback.onSuccess(t, z);
            }

            public void a(NetError netError) {
                callback.onFailure(netError.a(), netError.b());
            }

            public void a(long j, long j2, boolean z) {
                if (callback instanceof ProgressCallback) {
                    ((ProgressCallback) callback).onProgress(j, j2);
                }
            }
        };
    }

    public static PipeRequest a(Request request) {
        return new PipeRequest(a(request.requestParams), a(request.parser), a(request.callback));
    }
}
