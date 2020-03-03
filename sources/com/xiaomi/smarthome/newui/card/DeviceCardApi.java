package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.spec.operation.ActionParam;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyParam;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.newui.card.DeviceCardApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceCardApi {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20495a = 5000;

    public static AsyncHandle a(Context context, JSONArray jSONArray, AsyncCallback<String, Error> asyncCallback, String str) {
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
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/v2/device/batchgetdatas").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
                /* renamed from: a */
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

    public static AsyncHandle a(Context context, List<PropertyParam> list, AsyncCallback<List<PropertyParam>, Error> asyncCallback) {
        return a(context, list, true, asyncCallback);
    }

    public static AsyncHandle b(Context context, List<PropertyParam> list, AsyncCallback<List<PropertyParam>, Error> asyncCallback) {
        return a(context, list, false, asyncCallback);
    }

    private static AsyncHandle a(Context context, List<PropertyParam> list, boolean z, AsyncCallback<List<PropertyParam>, Error> asyncCallback) {
        if (list == null || list.size() == 0) {
            asyncCallback.onFailure(new Error(-1, "param is null"));
            return null;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            for (PropertyParam next : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("did", next.getDid());
                jSONObject2.put("siid", next.getSiid());
                jSONObject2.put("piid", next.getPiid());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("params", jSONArray);
            if (z) {
                jSONObject.put("type", 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        $$Lambda$DeviceCardApi$OqipStUDrrdfu6DS2nCKtIlv7o r5 = $$Lambda$DeviceCardApi$OqipStUDrrdfu6DS2nCKtIlv7o.INSTANCE;
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/miotspec/prop/get").b((List<KeyValuePair>) arrayList).a(), r5, Crypto.RC4, asyncCallback);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List a(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            try {
                if (jSONObject.optInt("code", -1) == 0) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("result");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        PropertyParam propertyParam = new PropertyParam();
                        propertyParam.setDid(optJSONObject.optString("did"));
                        propertyParam.setSiid(optJSONObject.optInt("siid"));
                        propertyParam.setPiid(optJSONObject.optInt("piid"));
                        propertyParam.setValue(optJSONObject.opt("value"));
                        propertyParam.setTimestamp(optJSONObject.optLong("updateTime"));
                        propertyParam.setResultCode(optJSONObject.optInt("code", -1));
                        arrayList.add(propertyParam);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public enum SpecPropertyApi {
        instance;
        
        /* access modifiers changed from: private */
        public Map<String, RequestRecord> mCacheRequest;

        public void setDeviceSpecProp(Context context, PropertyParam propertyParam, AsyncCallback<PropertyParam, Error> asyncCallback) {
            PropertyParam propertyParam2 = propertyParam;
            AsyncCallback<PropertyParam, Error> asyncCallback2 = asyncCallback;
            if (propertyParam2 == null) {
                asyncCallback2.onFailure(new Error(-1, "param is null"));
                return;
            }
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("did", propertyParam.getDid());
                jSONObject2.put("siid", propertyParam.getSiid());
                jSONObject2.put("piid", propertyParam.getPiid());
                String jSONObject3 = jSONObject2.toString();
                RequestRecord requestRecord = this.mCacheRequest.get(jSONObject3);
                if (requestRecord != null) {
                    if (requestRecord.c != null) {
                        requestRecord.c.sendSuccessMessage(requestRecord.f20500a);
                        LogUtilGrey.a("mijia-card", "setDeviceSpecProp pending request callback success param:" + requestRecord.f20500a);
                    }
                    requestRecord.f20500a = propertyParam2;
                    requestRecord.b = context;
                    requestRecord.c = asyncCallback2;
                    return;
                }
                Context context2 = context;
                RequestRecord requestRecord2 = new RequestRecord(jSONObject3, asyncCallback2);
                this.mCacheRequest.put(jSONObject3, requestRecord2);
                jSONObject2.put("value", propertyParam.getValue());
                jSONArray.put(jSONObject2);
                jSONObject.put("params", jSONArray);
                arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                $$Lambda$DeviceCardApi$SpecPropertyApi$DalKxlzJSTGXsh0MVDYxD547kuw r13 = new JsonParser() {
                    public final Object parse(JSONObject jSONObject) {
                        return DeviceCardApi.SpecPropertyApi.lambda$setDeviceSpecProp$0(PropertyParam.this, jSONObject);
                    }
                };
                Context context3 = context;
                CoreApi.a().a(context3, new NetRequest.Builder().a("POST").b("/miotspec/prop/set").b((List<KeyValuePair>) arrayList).a(), r13, Crypto.RC4, requestRecord2.d);
            } catch (Exception e) {
                asyncCallback2.sendFailureMessage(new Error(-1, Log.getStackTraceString(e)));
            }
        }

        private static class RequestRecord {

            /* renamed from: a  reason: collision with root package name */
            PropertyParam f20500a;
            Context b;
            AsyncCallback<PropertyParam, Error> c;
            AsyncCallback<PropertyParam, Error> d;
            /* access modifiers changed from: private */
            public final String e;
            /* access modifiers changed from: private */
            public final AsyncCallback<PropertyParam, Error> f;

            public RequestRecord(String str, AsyncCallback<PropertyParam, Error> asyncCallback) {
                this.d = new AsyncTimeoutCallback<PropertyParam>(5000) {
                    /* renamed from: a */
                    public void onSuccess(PropertyParam propertyParam) {
                        SpecPropertyApi.instance.mCacheRequest.remove(RequestRecord.this.e);
                        if (RequestRecord.this.f != null) {
                            RequestRecord.this.f.onSuccess(propertyParam);
                        }
                        if (RequestRecord.this.f20500a != null) {
                            SpecPropertyApi.instance.setDeviceSpecProp(RequestRecord.this.b, RequestRecord.this.f20500a, RequestRecord.this.c);
                        }
                    }

                    public void onFailure(Error error) {
                        SpecPropertyApi.instance.mCacheRequest.remove(RequestRecord.this.e);
                        if (RequestRecord.this.f != null) {
                            RequestRecord.this.f.onFailure(error);
                        }
                    }
                };
                this.e = str;
                this.f = asyncCallback;
            }
        }
    }

    public enum SpecActionApi {
        instance;
        
        /* access modifiers changed from: private */
        public Map<String, RequestRecord> mCacheRequest;

        public void setDeviceSpecAction(Context context, ActionParam actionParam, AsyncCallback<ActionParam, Error> asyncCallback) {
            if (actionParam == null) {
                asyncCallback.onFailure(new Error(-1, "param is null"));
                return;
            }
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("did", actionParam.getDid());
                jSONObject2.put("siid", actionParam.getSiid());
                jSONObject2.put("aiid", actionParam.getAiid());
                String jSONObject3 = jSONObject2.toString();
                RequestRecord requestRecord = this.mCacheRequest.get(jSONObject3);
                if (requestRecord != null) {
                    if (requestRecord.c != null) {
                        requestRecord.c.sendSuccessMessage(requestRecord.f20498a);
                        LogUtilGrey.a("mijia-card", "setDeviceSpecAction pending request callback success param:" + requestRecord.f20498a);
                    }
                    requestRecord.f20498a = actionParam;
                    requestRecord.b = context;
                    requestRecord.c = asyncCallback;
                    return;
                }
                RequestRecord requestRecord2 = new RequestRecord(jSONObject3, asyncCallback);
                this.mCacheRequest.put(jSONObject3, requestRecord2);
                JSONArray jSONArray = new JSONArray();
                List<Object> in2 = actionParam.getIn();
                if (in2 != null && in2.size() > 0) {
                    for (Object put : in2) {
                        jSONArray.put(put);
                    }
                }
                jSONObject2.put("in", jSONArray);
                jSONObject.put("params", jSONObject2);
                arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                $$Lambda$DeviceCardApi$SpecActionApi$JlmayyK1r1ACHkGyAMqdSNMI0E r9 = $$Lambda$DeviceCardApi$SpecActionApi$JlmayyK1r1ACHkGyAMqdSNMI0E.INSTANCE;
                Context context2 = context;
                CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/miotspec/action").b((List<KeyValuePair>) arrayList).a(), r9, Crypto.RC4, requestRecord2.d);
            } catch (Exception e) {
                asyncCallback.sendFailureMessage(new Error(-1, Log.getStackTraceString(e)));
            }
        }

        private static class RequestRecord {

            /* renamed from: a  reason: collision with root package name */
            ActionParam f20498a;
            Context b;
            AsyncCallback<ActionParam, Error> c;
            AsyncCallback<ActionParam, Error> d;
            /* access modifiers changed from: private */
            public final String e;
            /* access modifiers changed from: private */
            public final AsyncCallback<ActionParam, Error> f;

            public RequestRecord(String str, AsyncCallback<ActionParam, Error> asyncCallback) {
                this.d = new AsyncTimeoutCallback<ActionParam>(5000) {
                    /* renamed from: a */
                    public void onSuccess(ActionParam actionParam) {
                        SpecActionApi.instance.mCacheRequest.remove(RequestRecord.this.e);
                        if (RequestRecord.this.f != null) {
                            RequestRecord.this.f.onSuccess(actionParam);
                        }
                        if (RequestRecord.this.f20498a != null) {
                            SpecActionApi.instance.setDeviceSpecAction(RequestRecord.this.b, RequestRecord.this.f20498a, RequestRecord.this.c);
                        }
                    }

                    public void onFailure(Error error) {
                        SpecActionApi.instance.mCacheRequest.remove(RequestRecord.this.e);
                        if (RequestRecord.this.f != null) {
                            RequestRecord.this.f.onFailure(error);
                        }
                    }
                };
                this.e = str;
                this.f = asyncCallback;
            }
        }
    }

    public enum ProfileRpcApi {
        instance;
        
        /* access modifiers changed from: private */
        public Map<String, RequestRecord> mCacheRequest;

        public void rpcAsync(String str, String str2, String str3, Param param, Object obj, AsyncCallback<JSONObject, Error> asyncCallback) {
            RequestRecord requestRecord = this.mCacheRequest.get(str3);
            if (requestRecord != null) {
                if (requestRecord.f != null) {
                    requestRecord.f.sendSuccessMessage(null);
                    LogUtilGrey.a("mijia-card", "rpcAsync pending request callback success param:" + requestRecord.e);
                }
                requestRecord.b = str;
                requestRecord.c = str2;
                requestRecord.d = str3;
                requestRecord.e = param;
                requestRecord.f20496a = obj;
                requestRecord.f = asyncCallback;
                return;
            }
            RequestRecord requestRecord2 = new RequestRecord(str3, asyncCallback);
            this.mCacheRequest.put(str3, requestRecord2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", ((PluginHostApi) XmPluginHostApi.instance()).generateNonce());
                jSONObject.put("method", str3);
                jSONObject.put("params", param.a(obj));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CoreApi.a().b(str, str2, jSONObject.toString(), requestRecord2.g);
        }

        private static class RequestRecord {

            /* renamed from: a  reason: collision with root package name */
            public Object f20496a;
            public String b;
            public String c;
            public String d;
            Param e;
            AsyncCallback<JSONObject, Error> f;
            AsyncCallback<JSONObject, Error> g;
            /* access modifiers changed from: private */
            public final String h;
            /* access modifiers changed from: private */
            public final AsyncCallback<JSONObject, Error> i;

            public RequestRecord(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
                this.g = new AsyncTimeoutCallback<JSONObject>(5000) {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        ProfileRpcApi.instance.mCacheRequest.remove(RequestRecord.this.h);
                        if (RequestRecord.this.i != null) {
                            RequestRecord.this.i.onSuccess(jSONObject);
                        }
                        if (RequestRecord.this.d != null) {
                            ProfileRpcApi.instance.rpcAsync(RequestRecord.this.b, RequestRecord.this.c, RequestRecord.this.d, RequestRecord.this.e, RequestRecord.this.f20496a, RequestRecord.this.f);
                        }
                    }

                    public void onFailure(Error error) {
                        ProfileRpcApi.instance.mCacheRequest.remove(RequestRecord.this.h);
                        if (RequestRecord.this.i != null) {
                            RequestRecord.this.i.onFailure(error);
                        }
                    }
                };
                this.h = str;
                this.i = asyncCallback;
            }
        }
    }
}
