package com.xiaomi.smarthome.device.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mi.global.shop.model.Tags;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.model.SyncUpCommandResult;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.miio.MiioUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DevicelibApi {
    public static AsyncHandle getDeviceLocationList(Context context, JSONArray jSONArray, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/location/list").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle getDeviceCategory(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        AnonymousClass2 r4 = new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        };
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/product/category_v2").b((List<KeyValuePair>) arrayList).a(), r4, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle getDeviceExtraInfo(Context context, ArrayList<String> arrayList, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList2 = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("dids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList2.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/extra_info").b((List<KeyValuePair>) arrayList2).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle getGeekDeviceDesc(Context context, Set<String> set, AsyncCallback<String, Error> asyncCallback) {
        if (set == null || set.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Object[] array = set.toArray();
            for (Object put : array) {
                jSONArray.put(put);
            }
            jSONObject.put("models", jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/getmodeluiconfig").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
                public String parse(JSONObject jSONObject) throws JSONException {
                    if (jSONObject == null) {
                        return null;
                    }
                    return "" + jSONObject.toString();
                }
            }, Crypto.RC4, asyncCallback);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static AsyncHandle batchGetDeviceProps(Context context, JSONArray jSONArray, AsyncCallback<String, Error> asyncCallback, String str) {
        if (jSONArray != null && jSONArray.length() > 0) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("from_flag", str);
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONArray.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/batchdevicedatas").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
                public String parse(JSONObject jSONObject) throws JSONException {
                    if (jSONObject == null) {
                        return null;
                    }
                    return "" + jSONObject.toString();
                }
            }, Crypto.RC4, asyncCallback);
        } else if (asyncCallback == null) {
            return null;
        } else {
            asyncCallback.onFailure(new Error(-1, "param is null"));
            return null;
        }
    }

    public static AsyncHandle reportGPSInfo(Context context, String str, double d, double d2, String str2, String str3, String str4, String str5, String str6, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("lng", d);
            jSONObject.put(Tags.Nearby.LAT, d2);
            jSONObject.put("adminArea", str2);
            jSONObject.put(Constant.KEY_COUNTRY_CODE, str3);
            jSONObject.put("locality", str4);
            jSONObject.put("thoroughfare", str5);
            jSONObject.put("language", "zh_CN");
            jSONObject.put("subLocality", str6);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/location/set").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle delDevice(Context context, JSONObject jSONObject, final AsyncResponseCallback<Void> asyncResponseCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("type", "delDevice");
            jSONObject2.put("param", jSONObject);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/mdata").b((List<KeyValuePair>) arrayList).a(), new JsonParser<SyncUpCommandResult>() {
            public SyncUpCommandResult parse(JSONObject jSONObject) throws JSONException {
                SyncUpCommandResult syncUpCommandResult = new SyncUpCommandResult();
                syncUpCommandResult.b = jSONObject.optInt("ret");
                syncUpCommandResult.c = jSONObject.optLong("lastModify");
                JSONObject optJSONObject = jSONObject.optJSONObject("init");
                if (optJSONObject != null) {
                    syncUpCommandResult.d = MiioUtil.a(optJSONObject);
                }
                return syncUpCommandResult;
            }
        }, Crypto.RC4, new AsyncCallback<SyncUpCommandResult, Error>() {
            public void onSuccess(SyncUpCommandResult syncUpCommandResult) {
                if (syncUpCommandResult.a()) {
                    asyncResponseCallback.a(null);
                } else {
                    asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                }
            }

            public void onFailure(Error error) {
                asyncResponseCallback.a(error.a());
            }
        });
    }

    public static AsyncHandle delSubDevice(Context context, JSONObject jSONObject, final AsyncResponseCallback<Void> asyncResponseCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("type", "delSubDevice");
            jSONObject2.put("param", jSONObject);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/mdata").b((List<KeyValuePair>) arrayList).a(), new JsonParser<SyncUpCommandResult>() {
            public SyncUpCommandResult parse(JSONObject jSONObject) throws JSONException {
                SyncUpCommandResult syncUpCommandResult = new SyncUpCommandResult();
                syncUpCommandResult.b = jSONObject.optInt("ret");
                syncUpCommandResult.c = jSONObject.optLong("lastModify");
                JSONObject optJSONObject = jSONObject.optJSONObject("init");
                if (optJSONObject != null) {
                    syncUpCommandResult.d = MiioUtil.a(optJSONObject);
                }
                return syncUpCommandResult;
            }
        }, Crypto.RC4, new AsyncCallback<SyncUpCommandResult, Error>() {
            public void onSuccess(SyncUpCommandResult syncUpCommandResult) {
                if (syncUpCommandResult.a()) {
                    asyncResponseCallback.a(null);
                } else {
                    asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                }
            }

            public void onFailure(Error error) {
                asyncResponseCallback.a(error.a());
            }
        });
    }

    public static AsyncHandle getLiveCameraList(Context context, double d, double d2, AsyncCallback<JSONArray, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("longitude", d);
            jSONObject.put("latitude", d2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/broadcastlist").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONArray>() {
            public JSONArray parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.optJSONArray("places");
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle bindDevice(Context context, String str, String str2, int i, AsyncCallback<Integer, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("token", str2);
            jSONObject.put("pid", i);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/binddevice").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Integer>() {
            public Integer parse(JSONObject jSONObject) throws JSONException {
                return Integer.valueOf(jSONObject.getInt("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle setShowMode(Context context, String str, int i, AsyncCallback<Boolean, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("show_mode", i);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/set_show_mode").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                int optInt = jSONObject.optInt("code", -1);
                boolean z = false;
                boolean optBoolean = jSONObject.optBoolean("result", false);
                if (optInt == 0 && optBoolean) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle setShowMode(Context context, Set<String> set, int i, AsyncCallback<Boolean, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (set != null && set.size() > 0) {
                for (String put : set) {
                    jSONArray.put(put);
                }
            }
            jSONObject.put("didlist", jSONArray);
            jSONObject.put("show_mode", i);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/set_multi_show_mode").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                int optInt = jSONObject.optInt("code", -1);
                boolean z = false;
                boolean optBoolean = jSONObject.optBoolean("result", false);
                if (optInt == 0 && optBoolean) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle defLightGroup(Context context, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        AnonymousClass14 r4 = new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        };
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/group/device_def").b((List<KeyValuePair>) arrayList).a(), r4, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle subscribeDevice(Context context, String str, int i, List<String> list, int i2, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < list.size(); i3++) {
                jSONArray.put(list.get(i3));
            }
            jSONObject.put("method", jSONArray);
            jSONObject.put("pushid", CommonApplication.getApplication().getPushId());
            jSONObject.put("expire", i2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/eventsub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.getBoolean("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle unsubscribeDevice(Context context, String str, int i, List<String> list, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < list.size(); i2++) {
                jSONArray.put(list.get(i2));
            }
            jSONObject.put("method", jSONArray);
            jSONObject.put("pushid", CommonApplication.getApplication().getPushId());
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/eventunsub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.getBoolean("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle subscribeDeviceV2(Context context, String str, int i, List<String> list, String str2, int i2, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            JSONArray jSONArray = new JSONArray();
            for (int i3 = 0; i3 < list.size(); i3++) {
                jSONArray.put(list.get(i3));
            }
            jSONObject.put("method", jSONArray);
            jSONObject.put("pushid", CommonApplication.getApplication().getPushId());
            jSONObject.put("expire", i2);
            jSONObject.put("subid", str2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/eventsub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.getBoolean("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle unsubscribeDeviceV2(Context context, String str, int i, List<String> list, String str2, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("pid", i);
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < list.size(); i2++) {
                jSONArray.put(list.get(i2));
            }
            jSONObject.put("method", jSONArray);
            jSONObject.put("pushid", CommonApplication.getApplication().getPushId());
            jSONObject.put("subid", str2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/eventunsub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.getBoolean("ret"));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle subscribeDeviceBatchV2(Context context, JSONObject jSONObject, String str, int i, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("didList", jSONObject);
            String pushId = CommonApplication.getApplication().getPushId();
            if (TextUtils.isEmpty(pushId)) {
                LogUtilGrey.a("DevicelibApi", "subscribeDeviceBatchV2 pushId is null");
            }
            jSONObject2.put("pushId", pushId);
            jSONObject2.put("expire", i);
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put("subId", str);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject2.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/eventsubbatch").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.getString("subId");
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle unsubscribeDeviceBatchV2(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("subId", str);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/mipush/eventunsubbatch").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public static AsyncHandle queryModelFunction(Context context, String[] strArr, Set<String> set, AsyncCallback<SparseArray<List<String>>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        int i = 0;
        while (i < strArr.length) {
            try {
                jSONArray2.put(strArr[i]);
                i++;
            } catch (JSONException unused) {
            }
        }
        for (String put : set) {
            jSONArray.put(put);
        }
        jSONObject.put("models", jSONArray);
        jSONObject.put(CommandMessage.TYPE_TAGS, jSONArray2);
        AnonymousClass20 r7 = new JsonParser<SparseArray<List<String>>>() {
            public SparseArray<List<String>> parse(JSONObject jSONObject) throws JSONException {
                SparseArray<List<String>> sparseArray = new SparseArray<>();
                JSONObject optJSONObject = jSONObject.optJSONObject("tagModels");
                for (int i = 0; i < LightGroupManager.i.length; i++) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray(LightGroupManager.i[i]);
                    HashSet hashSet = new HashSet();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        hashSet.add(optJSONArray.optString(i2));
                    }
                    sparseArray.put(i, new ArrayList(hashSet));
                }
                return sparseArray;
            }
        };
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/group/query_model_func").b((List<KeyValuePair>) arrayList).a(), r7, Crypto.RC4, asyncCallback);
    }
}
