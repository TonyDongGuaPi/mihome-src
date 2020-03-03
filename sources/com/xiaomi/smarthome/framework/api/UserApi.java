package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.text.TextUtils;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.mi.blockcanary.internal.BlockInfo;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
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
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.model.UploadUserData;
import com.xiaomi.smarthome.framework.api.model.UserGlobalInfo;
import com.xiaomi.smarthome.framework.page.TimezoneActivity;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.activity.HomeLogContants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserApi {

    /* renamed from: a  reason: collision with root package name */
    private static UserApi f16427a;
    private static final Object b = new Object();

    private UserApi() {
    }

    public static UserApi a() {
        if (f16427a == null) {
            synchronized (b) {
                if (f16427a == null) {
                    f16427a = new UserApi();
                }
            }
        }
        return f16427a;
    }

    public AsyncHandle a(Context context, String str, JSONArray jSONArray, AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", str);
            if (jSONArray != null && jSONArray.length() > 0) {
                jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/get_third_user_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Map<Integer, UserConfig.UserConfigData>>() {
            /* renamed from: a */
            public Map<Integer, UserConfig.UserConfigData> parse(JSONObject jSONObject) throws JSONException {
                HashMap hashMap = new HashMap();
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        UserConfig.UserConfigData a2 = UserConfig.UserConfigData.a(optJSONArray.getJSONObject(i));
                        if (a2 != null) {
                            hashMap.put(Integer.valueOf(a2.b), a2);
                        }
                    }
                }
                return hashMap;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, JSONArray jSONArray, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONArray.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/set_third_user_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, long j, AsyncCallback<Map<String, String>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("key", HomeLogContants.f11749a);
            jSONObject2.put(BlockInfo.l, j - 1);
            jSONObject2.put(BlockInfo.k, System.currentTimeMillis() / 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        AnonymousClass3 r6 = new JsonParser<Map<String, String>>() {
            /* renamed from: a */
            public Map<String, String> parse(JSONObject jSONObject) throws JSONException {
                HashMap hashMap = new HashMap();
                if (jSONObject.has(HomeLogContants.h)) {
                    hashMap.put(HomeLogContants.h, jSONObject.optString(HomeLogContants.h));
                }
                if (jSONObject.has(HomeLogContants.f11749a) && jSONObject.has(HomeLogContants.f11749a)) {
                    hashMap.put(HomeLogContants.f11749a, jSONObject.optString(HomeLogContants.f11749a));
                }
                return hashMap;
            }
        };
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/getpdata").b((List<KeyValuePair>) arrayList).a(), r6, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, final long j, String str2, AsyncCallback<Long, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", str);
            jSONObject.put("time", j);
            jSONObject.put("value", str2);
        } catch (JSONException unused) {
        }
        AnonymousClass4 r5 = new JsonParser<Long>() {
            /* renamed from: a */
            public Long parse(JSONObject jSONObject) throws JSONException {
                return Long.valueOf(j);
            }
        };
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/setpdata").b((List<KeyValuePair>) arrayList).a(), r5, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, String str, String str2, AsyncCallback<UploadUserData, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.Name.PREFIX, str);
            jSONObject.put(Constants.Name.SUFFIX, str2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/uploaduserfileurl").b((List<KeyValuePair>) arrayList).a(), new JsonParser<UploadUserData>() {
            /* renamed from: a */
            public UploadUserData parse(JSONObject jSONObject) throws JSONException {
                UploadUserData uploadUserData = new UploadUserData();
                uploadUserData.a(jSONObject);
                return uploadUserData;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, Device device, String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.Update.e, SystemApi.a().e(context));
            jSONObject.put("version_name", SystemApi.a().f(context));
            if (device != null) {
                jSONObject.put("did", device.did);
                jSONObject.put("model", device.model);
                PluginRecord d = CoreApi.a().d(device.model);
                if (d != null && d.l()) {
                    jSONObject.put("plugin_package_id", d.e());
                    jSONObject.put("plugin_id", d.d());
                }
            }
            jSONObject.put("logfile", str);
            jSONObject.put("content", str3);
            jSONObject.put("contact", str2);
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

    public AsyncHandle a(Context context, AsyncCallback<JSONArray, Error> asyncCallback) {
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/extra_config").a(), new JsonParser<JSONArray>() {
            /* renamed from: a */
            public JSONArray parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.optJSONArray("result");
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, Locale locale, ServerBean serverBean, AsyncCallback<Void, Error> asyncCallback) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (locale == null) {
            locale = CoreApi.a().I();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (locale.getLanguage().equalsIgnoreCase("zh")) {
                str = locale.getLanguage() + JSMethod.NOT_SET + locale.getCountry();
            } else {
                str = locale.getLanguage();
            }
            jSONObject.put("Language", str);
            jSONObject.put("Locale", "");
            jSONObject.put("Country", serverBean.b);
            jSONObject.put(TimezoneActivity.KEY_TIMEZONE, SystemApi.a().m());
            jSONObject.put("TimeZoneID", SystemApi.a().n());
        } catch (JSONException unused) {
        }
        String jSONObject2 = jSONObject.toString();
        try {
            jSONObject2 = jSONObject2.replace(Tags.MiHome.TEL_SEPARATOR4, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject2));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/setuserglobal").b((List<KeyValuePair>) arrayList).a(), (JsonParser) null, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle b(Context context, AsyncCallback<UserGlobalInfo, Error> asyncCallback) {
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/home/getuserglobal").a(), new JsonParser<UserGlobalInfo>() {
            /* renamed from: a */
            public UserGlobalInfo parse(JSONObject jSONObject) throws JSONException {
                UserGlobalInfo userGlobalInfo = new UserGlobalInfo();
                userGlobalInfo.f16461a = jSONObject.optString("language");
                userGlobalInfo.b = jSONObject.optString("Locale");
                userGlobalInfo.c = jSONObject.optString("time_zone");
                return userGlobalInfo;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public int[] a(JSONObject jSONObject, String str, int i, int i2, int i3, JSONArray jSONArray) throws JSONException {
        String jSONObject2 = jSONObject.toString();
        int length = jSONObject2.length();
        if (length <= i3) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("model", str);
            jSONObject3.put("key", "" + i);
            jSONObject3.put("data", jSONObject2);
            jSONArray.put(jSONObject3);
            return new int[]{i};
        }
        int i4 = length / i3;
        if (length % i3 != 0) {
            i4++;
        }
        int i5 = i4 + 1;
        if (i5 > i2) {
            return new int[0];
        }
        int[] iArr = new int[i5];
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("model", str);
        jSONObject4.put("key", "" + i);
        iArr[0] = i;
        int i6 = i + 1;
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("ts", i6);
        jSONObject5.put("tc", i4);
        jSONObject4.put("data", jSONObject5.toString());
        jSONArray.put(jSONObject4);
        int i7 = 0;
        int i8 = 1;
        for (int i9 = 0; i9 < i4; i9++) {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("model", str);
            jSONObject6.put("key", "" + i6);
            if (i9 == i4 - 1) {
                jSONObject6.put("data", jSONObject2.substring(i7));
            } else {
                jSONObject6.put("data", jSONObject2.substring(i7, i7 + i3));
            }
            jSONArray.put(jSONObject6);
            i7 += i3;
            iArr[i8] = i6;
            i6++;
            i8++;
        }
        return iArr;
    }

    public static void a(JSONObject jSONObject, int i, int i2, int i3, JSONArray jSONArray, String str) throws JSONException {
        a(jSONObject.toString(), i, i2, i3, jSONArray, str);
    }

    public static void a(String str, int i, int i2, int i3, JSONArray jSONArray, String str2) throws JSONException {
        int i4;
        int length = str.length();
        if (length > i3) {
            int i5 = length / i3;
            i4 = length % i3 != 0 ? i5 + 1 : i5;
        } else {
            i4 = 1;
        }
        if (i4 + 1 <= i2) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("component_id", str2);
            jSONObject.put("key", "" + i);
            int i6 = i + 1;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ts", i6);
            jSONObject2.put("tc", i4);
            jSONObject.put("data", jSONObject2.toString());
            jSONArray.put(jSONObject);
            int i7 = 0;
            for (int i8 = 0; i8 < i4; i8++) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("component_id", str2);
                jSONObject3.put("key", "" + i6);
                if (i8 == i4 - 1) {
                    jSONObject3.put("data", str.substring(i7));
                } else {
                    jSONObject3.put("data", str.substring(i7, i7 + i3));
                }
                jSONArray.put(jSONObject3);
                i7 += i3;
                i6++;
            }
        }
    }

    public static void b(JSONObject jSONObject, int i, int i2, int i3, JSONArray jSONArray, String str) throws JSONException {
        int i4;
        String jSONObject2 = jSONObject.toString();
        int length = jSONObject2.length();
        if (length > i3) {
            int i5 = length / i3;
            i4 = length % i3 != 0 ? i5 + 1 : i5;
        } else {
            i4 = 1;
        }
        if (i4 + 1 <= i2) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("component_id", str);
            jSONObject3.put("key", "" + i);
            int i6 = i + 1;
            jSONObject3.put("data", new JSONObject().toString());
            jSONArray.put(jSONObject3);
            int i7 = 0;
            for (int i8 = 0; i8 < i4; i8++) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("component_id", str);
                jSONObject4.put("key", "" + i6);
                if (i8 == i4 - 1) {
                    jSONObject4.put("data", jSONObject2.substring(i7));
                } else {
                    jSONObject4.put("data", jSONObject2.substring(i7, i7 + i3));
                }
                jSONArray.put(jSONObject4);
                i7 += i3;
                i6++;
            }
        }
    }

    public static String a(int i, int i2, Map<Integer, UserConfig.UserConfigData> map) {
        String str = "";
        int i3 = i2 + i;
        while (i < i3) {
            UserConfig.UserConfigData userConfigData = map.get(Integer.valueOf(i));
            if (userConfigData == null) {
                return null;
            }
            str = str + userConfigData.c;
            i++;
        }
        return str;
    }

    public static boolean a(UserConfig.UserConfigData userConfigData, int[] iArr) {
        if (iArr != null && iArr.length >= 2 && userConfigData != null && !TextUtils.isEmpty(userConfigData.c)) {
            try {
                JSONObject jSONObject = new JSONObject(userConfigData.c);
                iArr[0] = jSONObject.optInt("ts");
                iArr[1] = jSONObject.optInt("tc");
                if (jSONObject.isNull("ts") || jSONObject.isNull("tc")) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
