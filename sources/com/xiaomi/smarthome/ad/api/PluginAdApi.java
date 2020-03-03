package com.xiaomi.smarthome.ad.api;

import android.content.Context;
import com.xiaomi.smarthome.ad.api.AdEvent;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginAdApi {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f13639a = new Object();
    private static volatile PluginAdApi b;

    private PluginAdApi() {
    }

    public static PluginAdApi a() {
        if (b == null) {
            synchronized (f13639a) {
                if (b == null) {
                    b = new PluginAdApi();
                }
            }
        }
        return b;
    }

    public AsyncHandle a(Context context, long j, final AsyncCallback<List<AdPosition>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("plugin_id", 122);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("GET").b("/service/getadconfig").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, Crypto.RC4, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONArray optJSONArray = new JSONObject(str).optJSONArray("result");
                    if (optJSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            arrayList.add(AdPosition.a(optJSONArray.optJSONObject(i)));
                        }
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(arrayList);
                        }
                    }
                } catch (Exception unused) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(-1, "json parse error"));
                    }
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(error.a(), error.b()));
                }
            }
        });
        return null;
    }

    public void a(String str) {
        a("PageStart", str, AdEvent.f13634a);
    }

    public void b(String str) {
        a(StatHelper.f13997a, str, AdEvent.b);
    }

    public void c(String str) {
        a(str);
    }

    public void d(String str) {
        a(StatHelper.f13997a, str, AdEvent.c);
    }

    private void a(String str, String str2, @AdEvent.Type String str3) {
        JSONObject jSONObject = new JSONObject();
        PluginRecord d = CoreApi.a().d(str);
        if (d != null && d.c() != null) {
            try {
                jSONObject.put("model", str);
                jSONObject.put("title", d.c().k());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CoreApi.a().a(StatType.EVENT, str2, str3, jSONObject.toString(), false);
        }
    }
}
