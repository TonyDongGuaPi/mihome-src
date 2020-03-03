package com.xiaomi.smarthome.feedback;

import android.content.Context;
import android.text.TextUtils;
import com.coloros.mcssdk.mode.CommandMessage;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public enum FeedbackApi {
    INSTANCE;
    
    public static final String AUTO_SCENE = "automation";
    public static final String BLE_GATEWAY = "blegateway";
    public static final String CHOICENESS = "choiceness";
    public static final String COMMON_EXP = "exp";
    public static final String COMMON_OTHER = "other";
    public static final String COMMON_SHOP = "shop";

    public AsyncHandle getRedDotCount(Context context, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/stat/feedback_count").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            /* renamed from: a */
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return Integer.valueOf(jSONObject.optInt("count", 0));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getFeedbackList(Context context, int i, int i2, AsyncCallback<FeedbackList, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("page", i);
            jSONObject.put("pageSize", i2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/stat/feedback_list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<FeedbackList>() {
            /* renamed from: a */
            public FeedbackList parse(JSONObject jSONObject) throws JSONException {
                return FeedbackList.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle deleteFeedback(Context context, String[] strArr, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList(2);
        JSONObject jSONObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        int i = 0;
        boolean z = true;
        while (i < length) {
            String str = strArr[i];
            if (!z) {
                sb.append(",");
            }
            sb.append(str);
            i++;
            z = false;
        }
        try {
            jSONObject.put("id", sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/stat/feedback_delete").b((List<KeyValuePair>) arrayList).a();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, a2, new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.optBoolean("result"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle getFeedbackDetail(Context context, String str, AsyncCallback<FeedbackDetail, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList(2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/stat/feedback_detail").b((List<KeyValuePair>) arrayList).a(), new JsonParser<FeedbackDetail>() {
            /* renamed from: a */
            public FeedbackDetail parse(JSONObject jSONObject) throws JSONException {
                return FeedbackDetail.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle sendFeedBack2(Context context, Device device, String str, String str2, String str3, String str4, String str5, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.Update.e, SystemApi.a().e(context));
            jSONObject.put("version_name", SystemApi.a().f(context));
            if (device != null) {
                jSONObject.put("did", device.did);
                str = device.model;
            }
            if (str != null && !str.isEmpty()) {
                jSONObject.put("model", str);
                PluginRecord d = CoreApi.a().d(str);
                if (d != null && d.l()) {
                    jSONObject.put("plugin_package_id", d.e());
                    jSONObject.put("plugin_id", d.d());
                }
            }
            if (!TextUtils.isEmpty(str5)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("router_brand", str5);
                jSONObject.put(CommandMessage.TYPE_TAGS, jSONObject2);
            }
            jSONObject.put("logfile", str2);
            jSONObject.put("content", str4);
            jSONObject.put("contact", str3);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/stat/feedback").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Void>() {
            /* renamed from: a */
            public Void parse(JSONObject jSONObject) throws JSONException {
                return null;
            }
        }, Crypto.RC4, asyncCallback);
    }
}
