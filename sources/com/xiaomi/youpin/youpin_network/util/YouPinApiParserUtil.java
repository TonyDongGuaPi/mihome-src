package com.xiaomi.youpin.youpin_network.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.youpin.network.bean.NetError;
import com.xiaomi.youpin.network.bean.NetResult;
import com.xiaomi.youpin.youpin_network.bean.PipeData;
import com.xiaomi.youpin.youpin_network.bean.PipeMultiData;
import com.xiaomi.youpin.youpin_network.bean.PipeRequest;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import com.xiaomi.youpin.youpin_network.callback.RequestAsyncCallback;
import com.xiaomi.youpin.youpin_network.callback.YouPinJsonParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class YouPinApiParserUtil {
    public static <T> void a(NetResult netResult, PipeRequest<T> pipeRequest) {
        RequestAsyncCallback<T, NetError> requestAsyncCallback;
        PipeData<T> pipeData;
        if (pipeRequest != null && (requestAsyncCallback = pipeRequest.callback) != null) {
            if (netResult == null) {
                a(false, requestAsyncCallback, -1, "netResult is null");
                return;
            }
            boolean z = netResult.b;
            String str = netResult.d;
            if (TextUtils.isEmpty(str)) {
                a(z, requestAsyncCallback, -1, "response is empty");
                return;
            }
            RequestParams requestParams = pipeRequest.requestParams;
            YouPinJsonParser<T> youPinJsonParser = pipeRequest.parser;
            if (requestParams == null || TextUtils.isEmpty(requestParams.key)) {
                pipeData = a("", str, youPinJsonParser);
            } else {
                pipeData = a(requestParams.key, str, youPinJsonParser);
            }
            if (pipeData == null) {
                a(z, requestAsyncCallback, -1, "json handleResponse error");
                return;
            }
            int i = pipeData.code;
            if (i != 0) {
                a(z, requestAsyncCallback, i, pipeData.message);
                return;
            }
            T t = pipeData.bean;
            if (netResult.b) {
                requestAsyncCallback.b(t);
            } else {
                requestAsyncCallback.b(t, netResult.c);
            }
        }
    }

    public static <T> Pair<T, NetError> b(NetResult netResult, PipeRequest<T> pipeRequest) {
        PipeData<T> pipeData;
        if (netResult == null) {
            return a(-1, "netResult is null");
        }
        String str = netResult.d;
        if (TextUtils.isEmpty(str)) {
            return a(-1, "response is empty");
        }
        RequestParams requestParams = pipeRequest.requestParams;
        YouPinJsonParser<T> youPinJsonParser = pipeRequest.parser;
        if (requestParams == null || TextUtils.isEmpty(requestParams.key)) {
            pipeData = a("", str, youPinJsonParser);
        } else {
            pipeData = a(requestParams.key, str, youPinJsonParser);
        }
        if (pipeData == null) {
            return a(-1, "json handleResponse error");
        }
        int i = pipeData.code;
        if (i != 0) {
            return a(i, pipeData.message);
        }
        return new Pair<>(pipeData.bean, (Object) null);
    }

    public static void a(NetResult netResult, List<PipeRequest> list) {
        if (netResult != null && list != null && !list.isEmpty()) {
            boolean z = netResult.b;
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            for (PipeRequest next : list) {
                arrayList.add(next.callback);
                hashMap.put(next.requestParams.key, next);
            }
            String str = netResult.d;
            if (TextUtils.isEmpty(str)) {
                a(z, (List<RequestAsyncCallback>) arrayList, -1, "response is empty");
                return;
            }
            PipeMultiData a2 = a(str, (HashMap<String, PipeRequest>) hashMap);
            if (a2 == null) {
                a(z, (List<RequestAsyncCallback>) arrayList, -1, "json handleResponse error");
                return;
            }
            int i = a2.code;
            if (i != 0) {
                a(z, (List<RequestAsyncCallback>) arrayList, i, a2.message);
                return;
            }
            HashMap<String, Object> hashMap2 = a2.dataMap;
            if (netResult.b) {
                for (PipeRequest next2 : list) {
                    try {
                        next2.callback.b(hashMap2.get(next2.requestParams.key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
            for (PipeRequest next3 : list) {
                try {
                    next3.callback.b(hashMap2.get(next3.requestParams.key), netResult.c);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static PipeMultiData a(String str, HashMap<String, PipeRequest> hashMap) {
        PipeMultiData pipeMultiData;
        JsonElement jsonElement;
        T t;
        try {
            pipeMultiData = (PipeMultiData) new Gson().fromJson(str, new TypeToken<PipeMultiData>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            pipeMultiData = null;
        }
        if (pipeMultiData == null) {
            return null;
        }
        if (pipeMultiData.data != null) {
            jsonElement = pipeMultiData.data;
        } else {
            jsonElement = pipeMultiData.result != null ? pipeMultiData.result : null;
        }
        if (jsonElement != null && jsonElement.isJsonObject()) {
            for (Map.Entry next : jsonElement.getAsJsonObject().entrySet()) {
                if (!hashMap.containsKey(next.getKey())) {
                    pipeMultiData.dataMap.put(next.getKey(), (Object) null);
                } else {
                    YouPinJsonParser<T> youPinJsonParser = hashMap.get(next.getKey()).parser;
                    JsonElement jsonElement2 = (JsonElement) next.getValue();
                    if (youPinJsonParser == null) {
                        pipeMultiData.dataMap.put(next.getKey(), (Object) null);
                    } else {
                        try {
                            t = youPinJsonParser.b(jsonElement2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            t = null;
                        }
                        pipeMultiData.dataMap.put(next.getKey(), t);
                    }
                }
            }
        }
        return pipeMultiData;
    }

    private static <T> PipeData<T> a(@Nullable String str, @NonNull String str2, @Nullable YouPinJsonParser<T> youPinJsonParser) {
        PipeData<T> pipeData;
        JsonElement jsonElement;
        T t;
        T t2;
        T t3;
        try {
            pipeData = (PipeData) new Gson().fromJson(str2, new TypeToken<PipeData<T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            pipeData = null;
        }
        if (pipeData == null) {
            return null;
        }
        if (pipeData.data != null) {
            jsonElement = pipeData.data;
        } else {
            jsonElement = pipeData.result != null ? pipeData.result : null;
        }
        if (youPinJsonParser == null) {
            return pipeData;
        }
        if (jsonElement == null) {
            try {
                t3 = youPinJsonParser.b(new JsonObject());
            } catch (Exception e2) {
                e2.printStackTrace();
                t3 = null;
            }
            pipeData.bean = t3;
            return pipeData;
        } else if (TextUtils.isEmpty(str)) {
            try {
                t2 = youPinJsonParser.b(jsonElement);
            } catch (Exception e3) {
                e3.printStackTrace();
                t2 = null;
            }
            pipeData.bean = t2;
            return pipeData;
        } else {
            if (jsonElement.isJsonObject()) {
                Iterator<Map.Entry<String, JsonElement>> it = jsonElement.getAsJsonObject().entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry next = it.next();
                    if (str.equals(next.getKey())) {
                        try {
                            t = youPinJsonParser.b((JsonElement) next.getValue());
                        } catch (Exception e4) {
                            e4.printStackTrace();
                            t = null;
                        }
                        pipeData.bean = t;
                        break;
                    }
                }
            }
            return pipeData;
        }
    }

    private static void a(boolean z, List<RequestAsyncCallback> list, int i, String str) {
        for (RequestAsyncCallback a2 : list) {
            a(z, a2, i, str);
        }
    }

    private static <R> void a(boolean z, RequestAsyncCallback<R, NetError> requestAsyncCallback, int i, String str) {
        if (!z) {
            requestAsyncCallback.b(new NetError(i, str));
        }
    }

    private static <R> Pair<R, NetError> a(int i, String str) {
        return new Pair<>((Object) null, new NetError(i, str));
    }
}
