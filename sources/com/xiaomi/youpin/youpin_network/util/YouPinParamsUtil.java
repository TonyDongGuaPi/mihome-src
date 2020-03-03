package com.xiaomi.youpin.youpin_network.util;

import com.google.gson.JsonObject;
import com.xiaomi.youpin.network.bean.KeyValuePair;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.youpin_network.NetworkConfigManager;
import com.xiaomi.youpin.youpin_network.bean.RequestParams;
import java.util.ArrayList;
import java.util.List;

public class YouPinParamsUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23868a = "/shopv3/pipe";
    public static final String b = "/shop/pipe";

    public static NetRequest a(int i, String str, RequestParams requestParams) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", requestParams.toJsonObject().toString()));
        return b(i, str, arrayList);
    }

    public static NetRequest a(int i, String str, RequestParams... requestParamsArr) {
        JsonObject jsonObject = new JsonObject();
        for (RequestParams requestParams : requestParamsArr) {
            jsonObject.add(requestParams.key, requestParams.toJsonObjectForMulti());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jsonObject.toString()));
        return b(i, str, arrayList);
    }

    public static NetRequest a(int i, String str, List<RequestParams> list) {
        JsonObject jsonObject = new JsonObject();
        for (RequestParams next : list) {
            jsonObject.add(next.key, next.toJsonObjectForMulti());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jsonObject.toString()));
        return b(i, str, arrayList);
    }

    private static NetRequest b(int i, String str, List<KeyValuePair> list) {
        NetRequest.Builder builder = new NetRequest.Builder();
        if (b.equals(str)) {
            String e = NetworkConfigManager.a().b().a().e();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("userid", e));
            builder = builder.a((List<KeyValuePair>) arrayList);
        }
        return builder.a(str).a(i).b(list).a(true).a();
    }
}
