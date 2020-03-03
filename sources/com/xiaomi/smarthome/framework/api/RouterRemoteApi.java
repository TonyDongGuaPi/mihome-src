package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.RemoteRouterMitvApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterRemoteApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16413a = "RouterRemoteApi";
    public static final QuantityUnit[] b = {new QuantityUnit(1073741824, "GB/s"), new QuantityUnit(1048576, "MB/s"), new QuantityUnit(1024, "KB/s")};
    private static RouterRemoteApi c;
    private static final Object d = new Object();

    public static class DetailInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f16422a = "0";
        public String b = "KB/s";
    }

    public static class MiRouterPwdInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f16423a;
        public String b;
        public String c;
        public String d;
    }

    private RouterRemoteApi() {
    }

    public static RouterRemoteApi a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new RouterRemoteApi();
                }
            }
        }
        return c;
    }

    public AsyncHandle a(Context context, String str, AsyncCallback<String, Error> asyncCallback) {
        return CoreApi.a().a(context, new NetRequest.Builder().a("GET").b("/api/xqsystem/renew_token").b((List<KeyValuePair>) new ArrayList()).a(), str, false, new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.getString("token");
            }
        }, asyncCallback);
    }

    public AsyncHandle a(Context context, RemoteRouterMitvApi.MiRouterInfo miRouterInfo, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("serialNumber", miRouterInfo.f16404a));
        arrayList.add(new KeyValuePair("deviceID", miRouterInfo.b));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/s/device/isDeviceBound").b((List<KeyValuePair>) arrayList).a(), miRouterInfo.b, true, new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                return Boolean.valueOf(jSONObject.getBoolean("result"));
            }
        }, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("d", str3));
        arrayList.add(new KeyValuePair("deviceName", str2));
        arrayList.add(new KeyValuePair("deviceID", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/s/register").b((List<KeyValuePair>) arrayList).a(), str, true, (JsonParser) null, asyncCallback);
    }

    public AsyncHandle b(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("deviceID", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/s/admin/dismiss").b((List<KeyValuePair>) arrayList).a(), str, true, (JsonParser) null, asyncCallback);
    }

    public AsyncHandle c(Context context, String str, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("deviceID", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/s/admin/demoteSelf").b((List<KeyValuePair>) arrayList).a(), str, true, (JsonParser) null, asyncCallback);
    }

    public AsyncHandle d(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("deviceID", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("GET").b("/api/xqsystem/init_info").b((List<KeyValuePair>) arrayList).a(), str, false, new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, asyncCallback);
    }

    public AsyncHandle e(Context context, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("deviceId", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("GET").b("/s/admin/deviceList").b((List<KeyValuePair>) arrayList).a(), str, true, new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, asyncCallback);
    }

    public AsyncHandle a(Context context, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONArray.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/service/miot/get_device_info").b((List<KeyValuePair>) arrayList).a(), "", true, new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, asyncCallback);
    }

    public AsyncHandle f(Context context, String str, AsyncCallback<WifiDetail, Error> asyncCallback) {
        return CoreApi.a().a(context, new NetRequest.Builder().a("GET").b("/api/xqnetwork/wifi_detail_all").b((List<KeyValuePair>) new ArrayList()).a(), str, false, new JsonParser<WifiDetail>() {
            /* renamed from: a */
            public WifiDetail parse(JSONObject jSONObject) throws JSONException {
                return WifiDetail.a(jSONObject);
            }
        }, asyncCallback);
    }

    public <T> AsyncHandle a(Context context, String str, String str2, String str3, List<KeyValuePair> list, boolean z, AsyncCallback<String, Error> asyncCallback) {
        return CoreApi.a().a(context, new NetRequest.Builder().a(str3).b(str2).b(list).a(), str, z, new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.toString();
            }
        }, asyncCallback);
    }

    public void a(AsyncCallback<JSONObject, Error> asyncCallback, String str) {
        if (str.startsWith("miwifi.")) {
            str = str.substring("miwifi.".length());
        }
        String str2 = str;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("routerID", str2));
        arrayList.add(new KeyValuePair("locale", Locale.getDefault().toString()));
        CoreApi.a().a(CommonApplication.getAppContext(), new NetRequest.Builder().a("GET").b("/s/api/device_list").b((List<KeyValuePair>) arrayList).a(), str2, true, new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, asyncCallback);
    }

    private static final class QuantityUnit {

        /* renamed from: a  reason: collision with root package name */
        int f16424a;
        String b;

        public QuantityUnit(int i, String str) {
            this.f16424a = i;
            this.b = str;
        }
    }

    public static class WifiInfo implements Parcelable {
        public static final Parcelable.Creator<WifiInfo> CREATOR = new Parcelable.Creator<WifiInfo>() {
            /* renamed from: a */
            public WifiInfo createFromParcel(Parcel parcel) {
                return new WifiInfo(parcel);
            }

            /* renamed from: a */
            public WifiInfo[] newArray(int i) {
                return new WifiInfo[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public int f16426a;
        public boolean b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;

        public int describeContents() {
            return 0;
        }

        public WifiInfo() {
        }

        protected WifiInfo(Parcel parcel) {
            this.f16426a = parcel.readInt();
            this.b = parcel.readByte() != 0;
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
            this.g = parcel.readString();
            this.h = parcel.readString();
            this.i = parcel.readString();
            this.j = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f16426a);
            parcel.writeByte(this.b ? (byte) 1 : 0);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
            parcel.writeString(this.g);
            parcel.writeString(this.h);
            parcel.writeString(this.i);
            parcel.writeString(this.j);
        }

        public static WifiInfo a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            WifiInfo wifiInfo = new WifiInfo();
            wifiInfo.f16426a = jSONObject.optInt("channel");
            if (wifiInfo.f16426a == 0 && jSONObject.has("channelInfo")) {
                wifiInfo.f16426a = jSONObject.optJSONObject("channelInfo").optInt("channel");
            }
            boolean z = false;
            if (jSONObject.optInt("status", 0) == 1) {
                z = true;
            }
            wifiInfo.b = z;
            wifiInfo.c = jSONObject.optString(DeviceTagInterface.e);
            wifiInfo.d = jSONObject.optString("bssid");
            wifiInfo.e = jSONObject.optString("password");
            wifiInfo.f = jSONObject.optString("ifname");
            wifiInfo.g = jSONObject.optString("encryption");
            wifiInfo.h = jSONObject.optString("mode");
            wifiInfo.i = jSONObject.optString("txpwr");
            wifiInfo.j = jSONObject.optString("signal");
            return wifiInfo;
        }
    }

    public static class WifiDetail {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<WifiInfo> f16425a = new ArrayList<>();

        public static WifiDetail a(JSONObject jSONObject) {
            WifiDetail wifiDetail = new WifiDetail();
            JSONArray optJSONArray = jSONObject.optJSONArray("info");
            if (optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    WifiInfo a2 = WifiInfo.a(optJSONArray.optJSONObject(i));
                    if (a2 != null) {
                        wifiDetail.f16425a.add(a2);
                    }
                }
            }
            return wifiDetail;
        }
    }
}
