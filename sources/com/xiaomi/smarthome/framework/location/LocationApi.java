package com.xiaomi.smarthome.framework.location;

import android.content.Context;
import android.location.Location;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.AreaPropAirWaterInfo;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.miio.areainfo.LocationData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationApi {

    /* renamed from: a  reason: collision with root package name */
    private static LocationApi f16500a;
    private static final Object b = new Object();

    private LocationApi() {
    }

    public static LocationApi a() {
        if (f16500a == null) {
            synchronized (b) {
                if (f16500a == null) {
                    f16500a = new LocationApi();
                }
            }
        }
        return f16500a;
    }

    public AsyncHandle a(Context context, String str, JsonParser<Location> jsonParser, AsyncCallback<Location, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/location/get").b((List<KeyValuePair>) arrayList).a(), jsonParser, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, final String str, Location location, AsyncCallback<AreaPropInfo, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("area_id", str);
            if (location != null) {
                jSONObject.put("latitude", "" + location.getLatitude());
                jSONObject.put("longitude", "" + location.getLongitude());
            }
            if (CoreApi.a().D()) {
                jSONObject.put("data_version", 3);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/location/area_prop_info_v2").b((List<KeyValuePair>) arrayList).a(), new JsonParser<AreaPropInfo>() {
            /* renamed from: a */
            public AreaPropInfo parse(JSONObject jSONObject) throws JSONException {
                JSONObject optJSONObject;
                if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
                    return null;
                }
                AreaPropInfo areaPropInfo = new AreaPropInfo();
                areaPropInfo.q = LocationApi.this.a(optJSONObject.optString(AreaPropInfo.f16447a));
                areaPropInfo.r = LocationApi.this.a(optJSONObject.optString("prop.aqi"));
                areaPropInfo.s = LocationApi.this.a(optJSONObject.optString(AreaPropInfo.c));
                areaPropInfo.t = optJSONObject.optString("prop.aqi.cnt");
                areaPropInfo.u = LocationApi.this.a(optJSONObject.optString("prop.tds_in"));
                areaPropInfo.v = LocationApi.this.a(optJSONObject.optString("prop.tds_out"));
                areaPropInfo.w = optJSONObject.optString("prop.tds_in.cnt");
                areaPropInfo.x = optJSONObject.optString(AreaPropInfo.h);
                areaPropInfo.y = optJSONObject.optString("prop.humidity");
                areaPropInfo.B = optJSONObject.optString(AreaPropInfo.l);
                areaPropInfo.y = AreaPropInfo.b(optJSONObject.optString("prop.humidity"));
                areaPropInfo.z = AreaPropInfo.b(optJSONObject.optString(AreaPropInfo.j));
                areaPropInfo.A = optJSONObject.optString("prop.humidity.cnt", "-");
                LocationApi.this.a(areaPropInfo);
                if (!optJSONObject.isNull(AreaPropInfo.n)) {
                    areaPropInfo.E = optJSONObject.optString(AreaPropInfo.n);
                } else {
                    areaPropInfo.E = areaPropInfo.C;
                }
                areaPropInfo.b(optJSONObject.optJSONObject("showItems"));
                areaPropInfo.G = AreaPropInfo.Forecast.a(optJSONObject.optJSONObject("forecast"));
                areaPropInfo.H = AreaPropInfo.WeatherIndex.a(optJSONObject.optJSONArray("index"));
                areaPropInfo.I = AreaPropInfo.WeatherRealTime.a(optJSONObject.optJSONObject("realtime"));
                return areaPropInfo;
            }
        }, Crypto.AES, asyncCallback);
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        if (StringUtil.c(str)) {
            return "-";
        }
        Double valueOf = Double.valueOf(str);
        if (valueOf == null) {
            return str;
        }
        return "" + valueOf.shortValue();
    }

    /* access modifiers changed from: private */
    public void a(AreaPropInfo areaPropInfo) {
        int indexOf;
        if (!StringUtil.c(areaPropInfo.B) && (indexOf = areaPropInfo.B.indexOf(8451)) > 0 && indexOf < areaPropInfo.B.length()) {
            areaPropInfo.C = areaPropInfo.B.substring(0, indexOf);
            areaPropInfo.D = areaPropInfo.B.substring(indexOf + 2, areaPropInfo.B.length() - 1);
        }
    }

    public AsyncHandle a(Context context, final List<String> list, AsyncCallback<List<AreaPropAirWaterInfo>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (String put : list) {
                jSONArray.put(put);
            }
            jSONObject.put("area_id", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/location/area_prop_info_v2").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<AreaPropAirWaterInfo>>() {
            /* renamed from: a */
            public List<AreaPropAirWaterInfo> parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (String str : list) {
                    JSONObject optJSONObject = jSONObject.optJSONObject(str);
                    if (optJSONObject != null) {
                        AreaPropAirWaterInfo a2 = AreaPropAirWaterInfo.a(optJSONObject);
                        a2.r = str;
                        arrayList.add(a2);
                    }
                }
                return arrayList;
            }
        }, Crypto.AES, asyncCallback);
    }

    private List<com.xiaomi.smarthome.library.http.KeyValuePair> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("appKey", "miot201608"));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("sign", "liMNdWw39EbdTt5r"));
        arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("isGlobal", "true"));
        if (LocaleUtil.c()) {
            arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("locale", "zh_CN"));
        } else {
            Locale a2 = LocaleUtil.a();
            if (a2 != null) {
                arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("locale", a2.getLanguage()));
            } else {
                arrayList.add(new com.xiaomi.smarthome.library.http.KeyValuePair("locale", "zh_CN"));
            }
        }
        return arrayList;
    }

    public HttpAsyncHandle a(LocationData locationData, Location location, AsyncHandler asyncHandler) {
        List<com.xiaomi.smarthome.library.http.KeyValuePair> b2 = b();
        b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("latitude", "" + location.getLatitude()));
        b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("longitude", "" + location.getLongitude()));
        if (locationData != null) {
            b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair(Constant.KEY_COUNTRY_CODE, locationData.h));
            b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("adminArea", locationData.c));
            b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("locality", locationData.d));
            b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("subLocality", locationData.e));
            b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("thoroughfare", locationData.g));
        }
        return HttpApi.a(new Request.Builder().b("https://weatherapi.market.xiaomi.com/wtr-v3/location/city/geo").a("GET").a(b2).a(), asyncHandler);
    }

    public HttpAsyncHandle a(String str, AsyncHandler asyncHandler) {
        List<com.xiaomi.smarthome.library.http.KeyValuePair> b2 = b();
        b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("locationKey", str));
        b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("days", "5"));
        return HttpApi.a(new Request.Builder().b("https://weatherapi.market.xiaomi.com/wtr-v3/weather/forecast/daily").a("GET").a(b2).a(), asyncHandler);
    }

    public HttpAsyncHandle a(Location location, AsyncHandler asyncHandler) {
        List<com.xiaomi.smarthome.library.http.KeyValuePair> b2 = b();
        b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("latitude", "" + location.getLatitude()));
        b2.add(new com.xiaomi.smarthome.library.http.KeyValuePair("longitude", "" + location.getLongitude()));
        return HttpApi.a(new Request.Builder().b("https://weatherapi.market.xiaomi.com/wtr-v3/weather/aqi/current/geo").a("GET").a(b2).a(), asyncHandler);
    }
}
