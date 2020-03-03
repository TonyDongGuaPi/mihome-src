package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PayApi {

    /* renamed from: a  reason: collision with root package name */
    private static PayApi f16376a;
    private static final Object b = new Object();

    private PayApi() {
    }

    public static PayApi a() {
        if (f16376a == null) {
            synchronized (b) {
                if (f16376a == null) {
                    f16376a = new PayApi();
                }
            }
        }
        return f16376a;
    }

    public AsyncHandle a(Context context, String str, final String str2, AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("order_id", str);
            jSONObject.put("bank", str2);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/pay/bankgo").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                Miio.h("shop", "response: " + jSONObject);
                if (TextUtils.equals(str2, "alipaysecurity_v11")) {
                    return jSONObject.getString("data");
                }
                if (TextUtils.equals(str2, "unionpaynative")) {
                    return jSONObject.getJSONObject("data").getString(BaseInfo.KEY_THREAD_NAME);
                }
                if (TextUtils.equals(str2, "micash_app")) {
                    return jSONObject.getJSONObject("data").toString();
                }
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
