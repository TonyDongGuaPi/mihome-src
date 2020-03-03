package com.xiaomi.infrared.request;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.xiaomi.infrared.bean.IRBrandType;
import com.xiaomi.infrared.bean.IRFunctionType;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.infrared.bean.IRSTBData;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.bean.InfraredControllerInfo;
import com.xiaomi.infrared.bean.MJAreaID;
import com.xiaomi.infrared.bean.MJSetResultBean;
import com.xiaomi.infrared.bean.MatchInfraredButton;
import com.xiaomi.infrared.bean.MjSingleMatchNodesV2;
import com.xiaomi.infrared.bean.NameIdEntity;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InifraredRequestApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10240a = "InifraredRequestApi";
    private Context b;
    private ArrayList<AsyncHandle> c = new ArrayList<>();

    public void a(AsyncCallback<String, Error> asyncCallback) {
        if (this.b != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", ""));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/categories").b((List<KeyValuePair>) arrayList).a();
            CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
            a(CoreApi.a().a(this.b, a2, new JsonParser<String>() {
                /* renamed from: a */
                public String parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject == null ? "" : jSONObject.toString();
                }
            }, Crypto.RC4, cancelableCallback), cancelableCallback);
        }
    }

    public void a(String str, String str2, String str3, AsyncCallback<MJAreaID, Error> asyncCallback) {
        if (this.b != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("province", str);
                jSONObject.put("city", str2);
                jSONObject.put("area", str3);
            } catch (Throwable th) {
                Log.e(f10240a, "requestAreaid", th);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/area/area_id").b((List<KeyValuePair>) arrayList).a();
            CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
            a(CoreApi.a().a(this.b, a2, new JsonParser<MJAreaID>() {
                /* renamed from: a */
                public MJAreaID parse(JSONObject jSONObject) throws JSONException {
                    return MJAreaID.a(jSONObject);
                }
            }, Crypto.RC4, cancelableCallback), cancelableCallback);
        }
    }

    public void a(String str, AsyncCallback<ArrayList<IRSTBData>, Error> asyncCallback) {
        if (this.b != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("area_id", Integer.parseInt(str));
            } catch (Throwable th) {
                Log.e(f10240a, "requestLineups", th);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/area/lineups").b((List<KeyValuePair>) arrayList).a();
            CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
            a(CoreApi.a().a(this.b, a2, new JsonParser<ArrayList<IRSTBData>>() {
                /* renamed from: a */
                public ArrayList<IRSTBData> parse(JSONObject jSONObject) throws JSONException {
                    return IRSTBData.a(jSONObject.optJSONArray("lineups"));
                }
            }, Crypto.RC4, cancelableCallback), cancelableCallback);
        }
    }

    public void b(String str, AsyncCallback<ArrayList<NameIdEntity>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("province_id", Integer.parseInt(str));
        } catch (Throwable th) {
            Log.e(f10240a, "requestAllCity", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/area/province/cities").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<ArrayList<NameIdEntity>>() {
            /* renamed from: a */
            public ArrayList<NameIdEntity> parse(JSONObject jSONObject) throws JSONException {
                return NameIdEntity.a(jSONObject.optJSONArray("cities"));
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void b(AsyncCallback<ArrayList<NameIdEntity>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/area/provinces/china").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<ArrayList<NameIdEntity>>() {
            /* renamed from: a */
            public ArrayList<NameIdEntity> parse(JSONObject jSONObject) throws JSONException {
                return NameIdEntity.a(jSONObject.optJSONArray("provinces"));
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void c(String str, AsyncCallback<ArrayList<NameIdEntity>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("city_id", Integer.parseInt(str));
        } catch (Throwable th) {
            Log.e(f10240a, "requestAllArea", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/area/city/areas").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<ArrayList<NameIdEntity>>() {
            /* renamed from: a */
            public ArrayList<NameIdEntity> parse(JSONObject jSONObject) throws JSONException {
                return NameIdEntity.a(jSONObject.optJSONArray("areas"));
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void c(AsyncCallback<ArrayList<IRBrandType>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/iptv/brands").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<ArrayList<IRBrandType>>() {
            /* renamed from: a */
            public ArrayList<IRBrandType> parse(JSONObject jSONObject) throws JSONException {
                return IRBrandType.a(jSONObject.optJSONArray("brands"));
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void a(int i, AsyncCallback<ArrayList<IRBrandType>, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", i);
        } catch (Throwable th) {
            Log.e(f10240a, "requestStandardBrand", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/ircode/category/brands").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<ArrayList<IRBrandType>>() {
            /* renamed from: a */
            public ArrayList<IRBrandType> parse(JSONObject jSONObject) throws JSONException {
                return IRBrandType.a(jSONObject.optJSONArray("brands"));
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void a(DeviceStat deviceStat, Map<String, InfraredControllerInfo> map, AsyncCallback<MJSetResultBean, Error> asyncCallback) {
        String h;
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, InfraredControllerInfo> value : map.entrySet()) {
                InfraredControllerInfo infraredControllerInfo = (InfraredControllerInfo) value.getValue();
                IRFunctionType k = infraredControllerInfo.k();
                IRType f = infraredControllerInfo.f();
                if (k != IRFunctionType.STUDY) {
                    jSONObject.put("controller_id", Integer.parseInt(infraredControllerInfo.j()));
                }
                if (f == IRType.STB) {
                    jSONObject.put("lineup_id", infraredControllerInfo.a());
                }
                h = infraredControllerInfo.h();
                if (h != null) {
                    jSONObject.put("brand_id", Integer.parseInt(h));
                }
                jSONObject.put("parent_id", deviceStat.did);
                jSONObject.put("category", infraredControllerInfo.f().value());
                jSONObject.put("name", infraredControllerInfo.d());
            }
        } catch (NumberFormatException unused) {
            jSONObject.put("brand_id", h);
        } catch (Throwable th) {
            Log.e(f10240a, "requestCreateIR", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/irdevice/controller/add").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<MJSetResultBean>() {
            /* renamed from: a */
            public MJSetResultBean parse(JSONObject jSONObject) throws JSONException {
                return MJSetResultBean.a(jSONObject);
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void a(List<IRKeyValue> list, String str, AsyncCallback<String, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            for (IRKeyValue next : list) {
                JSONObject jSONObject2 = new JSONObject();
                if (!TextUtils.isEmpty(next.a())) {
                    if (!"0".equals(next.a())) {
                        jSONObject2.put("key_id", Integer.parseInt(next.a()));
                        jSONObject2.put("code", next.g());
                        jSONArray.put(jSONObject2);
                    }
                }
                jSONObject2.put("name", CommUtil.a(next));
                jSONObject2.put("code", next.g());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
        } catch (Throwable th) {
            Log.e(f10240a, "requestCache", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/irdevice/controller/keys/set").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject == null ? "" : jSONObject.toString();
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void a(MatchInfraredButton matchInfraredButton, AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (matchInfraredButton.d() == IRType.AC) {
                jSONObject.put("ac_key", matchInfraredButton.e());
            } else {
                jSONObject.put("key_id", Integer.parseInt(matchInfraredButton.e()));
            }
            jSONObject.put("did", matchInfraredButton.f());
            jSONObject.put("controller_id", Integer.parseInt(matchInfraredButton.g()));
        } catch (Throwable th) {
            Log.e(f10240a, "requestClick", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/irdevice/send_key").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject == null ? "" : jSONObject.toString();
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void d(String str, AsyncCallback<String, Error> asyncCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("models", str);
            jSONObject.put("device", Build.DEVICE);
        } catch (Throwable th) {
            Log.e(f10240a, "getDeviceInfo", th);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        NetRequest a2 = new NetRequest.Builder().a("POST").b("/v2/public/get_product_config").b((List<KeyValuePair>) arrayList).a();
        CancelableCallback cancelableCallback = new CancelableCallback(asyncCallback);
        a(CoreApi.a().a(this.b, a2, new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject == null ? "" : jSONObject.toString();
            }
        }, Crypto.RC4, cancelableCallback), cancelableCallback);
    }

    public void a(IRSTBData iRSTBData, final AsyncCallback<MjSingleMatchNodesV2, Error> asyncCallback) {
        String str;
        try {
            if (iRSTBData.e() != 1) {
                str = MessageFormat.format("https://cdn.cnbj1.fds.api.mi-img.com/irservice/match/v2/city/{0}/sp/{1}", new Object[]{iRSTBData.b(), iRSTBData.c()});
            } else {
                str = MessageFormat.format("https://cdn.cnbj1.fds.api.mi-img.com/irservice/match/v2/iptv/{0}", new Object[]{iRSTBData.c()});
            }
            HttpApi.a(new Request.Builder().a("GET").b(str).a(), (AsyncHandler) new AsyncHandler<MjSingleMatchNodesV2, com.xiaomi.smarthome.library.http.Error>() {
                public void processResponse(Response response) {
                    try {
                        ResponseBody body = response.body();
                        if (!response.isSuccessful() || body == null) {
                            sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(response.code(), "resbody null"), new Exception(), response);
                        } else {
                            sendSuccessMessage(MjSingleMatchNodesV2.a(new JSONObject(body.string())), response);
                        }
                    } catch (Exception e) {
                        sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(-1, e.getMessage()), e, response);
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(-1, iOException.getMessage()), iOException, (Response) null);
                }

                /* renamed from: a */
                public void onSuccess(MjSingleMatchNodesV2 mjSingleMatchNodesV2, Response response) {
                    InifraredRequestApi.this.a(response);
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(mjSingleMatchNodesV2);
                    }
                }

                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                    InifraredRequestApi.this.a(response);
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(error.a(), error.b()));
                    }
                }
            });
        } catch (Throwable th) {
            Log.e(f10240a, "requestSbtMatchList", th);
        }
    }

    public void a(int i, String str, final AsyncCallback<MjSingleMatchNodesV2, Error> asyncCallback) {
        try {
            HttpApi.a(new Request.Builder().a("GET").b(MessageFormat.format("https://cdn.cnbj1.fds.api.mi-img.com/irservice/match/v2/category/{0}/brand/{1}", new Object[]{String.valueOf(i), str})).a(), (AsyncHandler) new AsyncHandler<MjSingleMatchNodesV2, com.xiaomi.smarthome.library.http.Error>() {
                public void processResponse(Response response) {
                    try {
                        ResponseBody body = response.body();
                        if (!response.isSuccessful() || body == null) {
                            sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(response.code(), "resbody null"), new Exception(), response);
                        } else {
                            sendSuccessMessage(MjSingleMatchNodesV2.a(new JSONObject(body.string())), response);
                        }
                    } catch (Exception e) {
                        sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(-1, e.getMessage()), e, response);
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(-1, iOException.getMessage()), iOException, (Response) null);
                }

                /* renamed from: a */
                public void onSuccess(MjSingleMatchNodesV2 mjSingleMatchNodesV2, Response response) {
                    InifraredRequestApi.this.a(response);
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(mjSingleMatchNodesV2);
                    }
                }

                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                    InifraredRequestApi.this.a(response);
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(error.a(), error.b()));
                    }
                }
            });
        } catch (Throwable th) {
            Log.e(f10240a, "requestSbtMatchList", th);
        }
    }

    public void b(int i, final AsyncCallback<ArrayList<IRKeyValue>, Error> asyncCallback) {
        try {
            HttpApi.a(new Request.Builder().a("GET").b(MessageFormat.format("http://cdn.cnbj1.fds.api.mi-img.com/irservice/ircode/v1/keys/category/{0}", new Object[]{String.valueOf(i)})).a(), (AsyncHandler) new AsyncHandler<ArrayList<IRKeyValue>, com.xiaomi.smarthome.library.http.Error>() {
                public void processResponse(Response response) {
                    try {
                        ResponseBody body = response.body();
                        if (!response.isSuccessful() || body == null) {
                            sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(response.code(), "resbody null"), new Exception(), response);
                        } else {
                            sendSuccessMessage(IRKeyValue.a(new JSONObject(body.string()).optJSONArray(QuickTimeAtomTypes.h)), response);
                        }
                    } catch (Exception e) {
                        sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(-1, e.getMessage()), e, response);
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    sendFailureMessage(new com.xiaomi.smarthome.library.http.Error(-1, iOException.getMessage()), iOException, (Response) null);
                }

                /* renamed from: a */
                public void onSuccess(ArrayList<IRKeyValue> arrayList, Response response) {
                    InifraredRequestApi.this.a(response);
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(arrayList);
                    }
                }

                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                    InifraredRequestApi.this.a(response);
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(error.a(), error.b()));
                    }
                }
            });
        } catch (Throwable th) {
            Log.e(f10240a, "requestSbtMatchList", th);
        }
    }

    /* access modifiers changed from: private */
    public void a(Response response) {
        try {
            response.close();
        } catch (Throwable unused) {
        }
    }

    private void a(AsyncHandle asyncHandle, CancelableCallback cancelableCallback) {
        cancelableCallback.a(this.c, asyncHandle);
        this.c.add(asyncHandle);
    }

    public void a(Context context) {
        this.b = context;
    }

    public void a() {
        Iterator<AsyncHandle> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
        this.b = null;
    }

    private static class CancelableCallback<R, E extends Error> extends AsyncCallback<R, E> {

        /* renamed from: a  reason: collision with root package name */
        private final AsyncCallback<R, E> f10256a;
        private AsyncHandle b;
        private ArrayList<AsyncHandle> c;

        public CancelableCallback(AsyncCallback<R, E> asyncCallback) {
            this.f10256a = asyncCallback;
        }

        public void a(ArrayList<AsyncHandle> arrayList, AsyncHandle asyncHandle) {
            this.c = arrayList;
            this.b = asyncHandle;
        }

        public void onSuccess(R r) {
            this.c.remove(this.b);
            if (this.f10256a != null) {
                this.f10256a.onSuccess(r);
            }
        }

        public void onFailure(E e) {
            this.c.remove(this.b);
            if (this.f10256a != null) {
                this.f10256a.onFailure(e);
            }
        }
    }
}
