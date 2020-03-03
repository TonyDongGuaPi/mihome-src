package com.xiaomi.smarthome.scene.api;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.location.model.SceneConditionWifiManager;
import com.xiaomi.smarthome.scenenew.debug.MyDebugLogger;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.smarthome.scenenew.model.SceneLogInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteSceneApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21504a = "RemoteMiioApi";
    private static RemoteSceneApi b;
    private static final Object c = new Object();

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject b(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    private RemoteSceneApi() {
    }

    public static RemoteSceneApi a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new RemoteSceneApi();
                }
            }
        }
        return b;
    }

    public AsyncHandle a(Context context, String str, AsyncCallback<List<RecommendSceneItem>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject.put("did", str);
            } catch (JSONException unused) {
            }
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/recom").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<RecommendSceneItem>>() {
            /* renamed from: a */
            public List<RecommendSceneItem> parse(JSONObject jSONObject) throws JSONException {
                JSONArray jSONArray;
                SceneLogUtil.a("miioGetRecommendScene--------------" + jSONObject.toString());
                if (jSONObject.has("result")) {
                    jSONArray = jSONObject.optJSONArray("result");
                } else {
                    jSONArray = jSONObject.optJSONArray("value");
                }
                return RemoteSceneApi.a(jSONArray);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static List<RecommendSceneItem> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            RecommendSceneItem recommendSceneItem = new RecommendSceneItem();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            recommendSceneItem.mRecommId = optJSONObject.optInt("sr_id");
            recommendSceneItem.mStatus = optJSONObject.optInt("status");
            if (optJSONObject.has("info")) {
                optJSONObject = optJSONObject.optJSONObject("info");
            }
            recommendSceneItem.mName = optJSONObject.optString("name");
            recommendSceneItem.mRecommLevel = optJSONObject.optDouble("level");
            recommendSceneItem.mEnablePush = optJSONObject.optInt("enable_push") == 1;
            recommendSceneItem.mShowInMainPage = optJSONObject.optInt("show_mode") == 1;
            recommendSceneItem.mIcon = optJSONObject.optString("icon");
            JSONArray optJSONArray = optJSONObject.optJSONArray("launch");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                recommendSceneItem.mRecommendConditionList = new RecommendSceneItem.RemommendSceneCondition[optJSONArray.length()];
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                    RecommendSceneItem.RemommendSceneCondition remommendSceneCondition = new RecommendSceneItem.RemommendSceneCondition();
                    remommendSceneCondition.mConditionName = optJSONObject2.optString("name");
                    RecommendSceneItem.Key key = new RecommendSceneItem.Key();
                    key.mKey = optJSONObject2.optString("key");
                    key.mValues = optJSONObject2.opt("value");
                    key.mName = remommendSceneCondition.mConditionName;
                    if ((key.mValues instanceof JSONObject) || (key.mValues instanceof JSONArray)) {
                        key.mValues = key.mValues.toString();
                    }
                    if (optJSONObject2.has("key_ex")) {
                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray("key_ex");
                        remommendSceneCondition.mKeys = new RecommendSceneItem.Key[(optJSONArray2.length() + 1)];
                        remommendSceneCondition.mKeys[0] = key;
                        int i3 = 0;
                        while (i3 < optJSONArray2.length()) {
                            JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i3);
                            RecommendSceneItem.Key key2 = new RecommendSceneItem.Key();
                            key2.mKey = optJSONObject3.optString("key");
                            key2.mValues = optJSONObject3.opt("value");
                            key2.mName = remommendSceneCondition.mConditionName;
                            if ((key2.mValues instanceof JSONObject) || (key2.mValues instanceof JSONArray)) {
                                key2.mValues = key2.mValues.toString();
                            }
                            i3++;
                            remommendSceneCondition.mKeys[i3] = key2;
                        }
                    } else {
                        remommendSceneCondition.mKeys = new RecommendSceneItem.Key[1];
                        remommendSceneCondition.mKeys[0] = key;
                    }
                    if (optJSONObject2.has("model_list")) {
                        JSONArray optJSONArray3 = optJSONObject2.optJSONArray("model_list");
                        remommendSceneCondition.mDeviceModels = new String[optJSONArray3.length()];
                        for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                            remommendSceneCondition.mDeviceModels[i4] = optJSONArray3.optString(i4);
                        }
                    } else {
                        remommendSceneCondition.mDeviceModels = new String[1];
                        remommendSceneCondition.mDeviceModels[0] = optJSONObject2.optString("model");
                    }
                    remommendSceneCondition.mAddAllDevice = Boolean.valueOf(optJSONObject2.optBoolean("pd_ex"));
                    remommendSceneCondition.mProductId = optJSONObject2.optString(ApiConst.j);
                    remommendSceneCondition.mSrc = optJSONObject2.optString("src");
                    recommendSceneItem.mRecommendConditionList[i2] = remommendSceneCondition;
                }
            }
            JSONArray optJSONArray4 = optJSONObject.optJSONArray("action_list");
            recommendSceneItem.mRecommendActionList = new RecommendSceneItem.RemommendSceneAction[optJSONArray4.length()];
            for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                JSONObject optJSONObject4 = optJSONArray4.optJSONObject(i5);
                RecommendSceneItem.RemommendSceneAction remommendSceneAction = new RecommendSceneItem.RemommendSceneAction();
                remommendSceneAction.mActionName = optJSONObject4.optString("name");
                JSONObject optJSONObject5 = optJSONObject4.optJSONObject(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
                RecommendSceneItem.Key key3 = new RecommendSceneItem.Key();
                key3.mKey = optJSONObject5.optString(CommandMessage.COMMAND);
                key3.mValues = optJSONObject5.opt("value");
                if ((key3.mValues instanceof JSONObject) || (key3.mValues instanceof JSONArray)) {
                    key3.mValues = key3.mValues.toString();
                }
                key3.mName = remommendSceneAction.mActionName;
                if (optJSONObject5.has("command_ex")) {
                    JSONArray optJSONArray5 = optJSONObject5.optJSONArray("command_ex");
                    remommendSceneAction.mKeys = new RecommendSceneItem.Key[(optJSONArray5.length() + 1)];
                    remommendSceneAction.mKeys[0] = key3;
                    for (int i6 = 0; i6 < optJSONArray5.length(); i6++) {
                        JSONObject optJSONObject6 = optJSONArray5.optJSONObject(i6);
                        RecommendSceneItem.Key key4 = new RecommendSceneItem.Key();
                        key4.mKey = optJSONObject6.optString(CommandMessage.COMMAND);
                        key4.mValues = optJSONObject6.opt("value");
                        if ((key4.mValues instanceof JSONObject) || (key4.mValues instanceof JSONArray)) {
                            key4.mValues = key4.mValues.toString();
                        }
                        key4.mName = remommendSceneAction.mActionName;
                        remommendSceneAction.mKeys[i + 1] = key4;
                    }
                } else {
                    remommendSceneAction.mKeys = new RecommendSceneItem.Key[1];
                    remommendSceneAction.mKeys[0] = key3;
                }
                if (optJSONObject4.has("model_list")) {
                    JSONArray optJSONArray6 = optJSONObject4.optJSONArray("model_list");
                    remommendSceneAction.mDeviceModels = new String[optJSONArray6.length()];
                    for (int i7 = 0; i7 < optJSONArray6.length(); i7++) {
                        remommendSceneAction.mDeviceModels[i7] = optJSONArray6.optString(i7);
                    }
                } else {
                    remommendSceneAction.mDeviceModels = new String[1];
                    remommendSceneAction.mDeviceModels[0] = optJSONObject4.optString("model");
                }
                remommendSceneAction.mProductId = optJSONObject4.optString(ApiConst.j);
                recommendSceneItem.mRecommendActionList[i5] = remommendSceneAction;
            }
            arrayList.add(recommendSceneItem);
        }
        return arrayList;
    }

    public AsyncHandle a(Context context, final SceneApi.SmartHomeScene smartHomeScene, final AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            if (CreateSceneManager.a().c(smartHomeScene.l)) {
                smartHomeScene.t = true;
            } else {
                smartHomeScene.t = false;
            }
            JSONObject a2 = smartHomeScene.a();
            if (a2 != null) {
                jSONObject = a2;
            }
        } catch (JSONException unused) {
        }
        SceneLogUtil.a("miioSceneSettingNew------------------------cmdObj.toString()---------" + jSONObject.toString());
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/editv2").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(jSONObject);
                }
                SceneManager.x().a(smartHomeScene);
                SceneConditionWifiManager.a().a(smartHomeScene);
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public AsyncHandle b(Context context, SceneApi.SmartHomeScene smartHomeScene, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("us_id", smartHomeScene.f);
            jSONObject.put("type", 1);
            jSONObject.put("status", 0);
            jSONObject.put("st_id", 15);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/setuserscene").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, final int i, final AsyncCallback<List<SceneApi.SmartHomeScene>, Error> asyncCallback) {
        return a(context, 4, i, (String) null, (String) null, false, (String) null, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (jSONObject.has(String.valueOf(i))) {
                    try {
                        SceneApi.SmartHomeScene a2 = SceneApi.SmartHomeScene.a(jSONObject.optJSONObject(String.valueOf(i)), i == 30);
                        if (a2 != null) {
                            arrayList.add(a2);
                        }
                        i++;
                    } catch (JSONException e) {
                        asyncCallback.onFailure(new Error(-1, Log.getStackTraceString(e)));
                    }
                }
                if (i == 15) {
                    SceneManager.x().b(jSONObject.toString());
                } else if (i == 50) {
                    SceneManager.x().a(jSONObject.toString());
                }
                asyncCallback.onSuccess(arrayList);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    public AsyncHandle a(Context context, int i, int i2, String str, String str2, boolean z, String str3, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        if (i > 0) {
            try {
                jSONObject.put("api_version", i);
            } catch (JSONException unused) {
            }
        }
        jSONObject.put("st_id", i2);
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("did", str);
        }
        if (!TextUtils.isEmpty(str3)) {
            jSONObject.put("name", str3);
        }
        if (z) {
            jSONObject.put("identify", str2);
        }
        if (i2 == 8) {
            jSONObject.put("support_second", true);
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/list").b((List<KeyValuePair>) arrayList).a(), $$Lambda$RemoteSceneApi$IYiUTzkPopUgwwvNcnN3BV7YGIk.INSTANCE, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle c(final Context context, final SceneApi.SmartHomeScene smartHomeScene, final AsyncCallback<Void, Error> asyncCallback) {
        return a(context, smartHomeScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                RemoteSceneApi.this.a(smartHomeScene, jSONObject, (AsyncCallback<Void, Error>) asyncCallback, context);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(final SceneApi.SmartHomeScene smartHomeScene, JSONObject jSONObject, final AsyncCallback<Void, Error> asyncCallback, final Context context) {
        String optString = jSONObject.optString("us_id");
        boolean optBoolean = jSONObject.optBoolean("local");
        smartHomeScene.f = optString;
        if (!optBoolean) {
            asyncCallback.onSuccess(null);
            return;
        }
        String optString2 = jSONObject.optString("local_dev");
        if (TextUtils.isEmpty(optString2)) {
            asyncCallback.onFailure(null);
            return;
        }
        Device n = SmartHomeDeviceManager.a().n(optString2);
        if (n == null) {
            asyncCallback.onFailure(null);
            return;
        }
        LocalSceneBuilder.a().a(XmPluginHostApi.instance().getDeviceByDid(n.did), jSONObject.optJSONObject("data").toString(), (MessageCallback) new MessageCallback() {
            public void onSuccess(Intent intent) {
                RemoteSceneApi.a().b(context, smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(null);
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(null);
                        }
                    }
                });
            }

            public void onFailure(int i, String str) {
                LogUtil.b("lumiscene", str);
                if (asyncCallback != null) {
                    asyncCallback.onFailure(null);
                }
            }
        });
    }

    @Deprecated
    public AsyncHandle b(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("us_id", str);
            jSONObject.put("key", "");
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/start").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", str2);
            jSONObject.put("us_id", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/start").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, List<String> list, final AsyncCallback<Void, Error> asyncCallback) {
        if (list == null || list.size() == 0) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "us_id is illegal"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (String next : list) {
                if (!TextUtils.isEmpty(next)) {
                    if (!TextUtils.equals("0", next)) {
                        jSONArray.put(next);
                    }
                }
            }
            if (jSONArray.length() == 0) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "us_id is illegal"));
                }
                return null;
            }
            jSONObject.put("us_id", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/scene/delete").b((List<KeyValuePair>) arrayList).a();
            final ArrayList arrayList2 = new ArrayList();
            for (String e : list) {
                arrayList2.add(SceneManager.x().e(e));
            }
            return CoreApi.a().a(context, a2, (JsonParser) null, Crypto.RC4, new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(voidR);
                    }
                    SceneManager.x().a((List<SceneApi.SmartHomeScene>) arrayList2);
                    SceneConditionWifiManager.a().a((List<SceneApi.SmartHomeScene>) arrayList2);
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                }
            });
        } catch (JSONException unused) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "us_id is illegal"));
            }
            return null;
        }
    }

    public AsyncHandle a(String str, long j, Context context, AsyncCallback<List<SceneLogInfo>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("did", str);
            }
            jSONObject.put(CommandMessage.COMMAND, "history");
            jSONObject.put("limit", 100);
            if (j > 0) {
                jSONObject.put("timestamp", j);
            } else {
                jSONObject.put("timestamp", System.currentTimeMillis() / 1000);
            }
        } catch (JSONException unused) {
        }
        AnonymousClass8 r5 = new JsonParser<List<SceneLogInfo>>() {
            /* renamed from: a */
            public List<SceneLogInfo> parse(JSONObject jSONObject) throws JSONException {
                MyDebugLogger a2 = MyDebugLogger.a();
                a2.a(System.currentTimeMillis() + "===scene log history: " + jSONObject.toString());
                JSONArray optJSONArray = jSONObject.optJSONArray("history");
                if (optJSONArray == null) {
                    return null;
                }
                return SceneLogInfo.a(optJSONArray);
            }
        };
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/history").b((List<KeyValuePair>) arrayList).a(), r5, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, Context context, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("did", str);
            }
            jSONObject.put(CommandMessage.COMMAND, "cleanHistory");
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/history").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, int i, String str, String str2, String str3, String str4, JSONObject jSONObject, JSONArray jSONArray, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("us_id", str);
            jSONObject2.put("identify", str3);
            if (str4 != null) {
                jSONObject2.put("name", str4);
            }
            jSONObject2.put("st_id", i);
            jSONObject2.put(a.j, jSONObject);
            jSONObject2.put("authed", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/scene/edit").b((List<KeyValuePair>) arrayList).a();
            return CoreApi.a().a(context, a2, new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e.getMessage()));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle a(Context context, int i, String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback, boolean z) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("st_id", i);
            jSONObject.put("did", str);
            jSONObject.put("inc_grp", 1);
            if (z) {
                jSONObject.put("identify", str2);
            }
            jSONObject.put("support_second", true);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/grouplist").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e.getMessage()));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle b(Context context, int i, String str, String str2, String str3, AsyncCallback<JSONObject, Error> asyncCallback, boolean z) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("st_id", i);
            jSONObject.put("did", str);
            if (z) {
                jSONObject.put("identify", str2);
            }
            jSONObject.put("support_second", true);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e.getMessage()));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle c(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.equals("0", str)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "us_id is illegal"));
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("us_id", str);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/delete").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                /* renamed from: a */
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException e) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e.getMessage()));
            }
            return new AsyncHandle(null);
        }
    }

    public AsyncHandle a(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/scene/userrecomv2").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, long j, double d, double d2, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CommandMessage.COMMAND, "setUserLocationInfo");
            JSONObject jSONObject2 = new JSONObject();
            if (j > 0) {
                jSONObject2.put("po_id", j);
            }
            if (d > 0.0d && d2 > 0.0d) {
                jSONObject2.put("latitude", d);
                jSONObject2.put("longtitude", d2);
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put("intro", str);
            }
            jSONObject.put("param", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/location").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, int i, long j, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "event");
            if (i == 1) {
                jSONObject.put("key", SceneApi.ConditionIZatGeoFencing.l);
            } else if (i == 2) {
                jSONObject.put("key", SceneApi.ConditionIZatGeoFencing.m);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (j > 0) {
                jSONObject2.put("po_id", j);
            }
            jSONObject.put("value", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/event").b((List<KeyValuePair>) arrayList).a(), $$Lambda$RemoteSceneApi$KwjP9yGtDpbGQjYHRvF1t4YKnA.INSTANCE, Crypto.RC4, asyncCallback);
    }
}
