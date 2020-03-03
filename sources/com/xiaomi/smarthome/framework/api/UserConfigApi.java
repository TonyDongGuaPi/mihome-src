package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserConfigApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16439a = "local_userconfig_pref";
    private static UserConfigApi d;
    private static final Object e = new Object();
    /* access modifiers changed from: private */
    public SharedPreferences b;
    /* access modifiers changed from: private */
    public String c;

    private UserConfigApi() {
    }

    public static UserConfigApi a() {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new UserConfigApi();
                }
            }
        }
        return d;
    }

    /* access modifiers changed from: private */
    public String b() {
        if (!CoreApi.a().q()) {
            return f16439a;
        }
        return CoreApi.a().s() + JSMethod.NOT_SET + f16439a;
    }

    public AsyncHandle a(Context context, JSONArray jSONArray, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONArray.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/set_user_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, UserConfig userConfig, AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        new JSONObject();
        try {
            arrayList.add(new KeyValuePair("data", UserConfig.a(userConfig).toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/set_user_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Void>() {
                /* renamed from: a */
                public Void parse(JSONObject jSONObject) throws JSONException {
                    return null;
                }
            }, Crypto.RC4, asyncCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            return new AsyncHandle(null);
        }
    }

    public void a(final UserConfig userConfig, final AsyncCallback<Void, Error> asyncCallback) {
        if (userConfig == null) {
            Log.e("UserApi", "setUserConfigLocal userConfig is null");
        } else {
            CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    if (UserConfigApi.this.b != null && !UserConfigApi.this.c.equals(UserConfigApi.this.b())) {
                        SharedPreferences unused = UserConfigApi.this.b = null;
                    }
                    if (UserConfigApi.this.b == null) {
                        String unused2 = UserConfigApi.this.c = UserConfigApi.this.b();
                        SharedPreferences unused3 = UserConfigApi.this.b = CommonApplication.getAppContext().getSharedPreferences(UserConfigApi.this.c, 0);
                    }
                    try {
                        JSONObject a2 = UserConfig.a(userConfig);
                        SharedPreferences.Editor edit = UserConfigApi.this.b.edit();
                        edit.putString(userConfig.B + userConfig.C, a2.toString()).apply();
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(null);
                        }
                    } catch (JSONException e) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(-1, e.getMessage()));
                        }
                    }
                }
            });
        }
    }

    public synchronized UserConfig a(int i, String str) {
        if (this.b != null && !this.c.equals(b())) {
            this.b = null;
        }
        if (this.b == null) {
            this.c = b();
            this.b = CommonApplication.getAppContext().getSharedPreferences(this.c, 0);
        }
        SharedPreferences sharedPreferences = this.b;
        String string = sharedPreferences.getString(i + str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return UserConfig.a(new JSONObject(string));
        } catch (JSONException unused) {
            return null;
        }
    }

    public void a(final int i, final String str, final AsyncCallback<UserConfig, Error> asyncCallback) {
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                UserConfig a2 = UserConfigApi.this.a(i, str);
                if (a2 != null) {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(a2);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "no local data"));
                }
            }
        });
    }

    public AsyncHandle a(Context context, int i, String[] strArr, AsyncCallback<ArrayList<UserConfig>, Error> asyncCallback) {
        if (UserConfigCompactManager.a().a(context, i, strArr, asyncCallback)) {
            return new AsyncHandle(null);
        }
        return b(context, i, strArr, asyncCallback);
    }

    private AsyncHandle b(Context context, int i, String[] strArr, AsyncCallback<ArrayList<UserConfig>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("component_id", i);
            if (strArr != null && strArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String put : strArr) {
                    jSONArray.put(put);
                }
                jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/get_user_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<ArrayList<UserConfig>>() {
            /* renamed from: a */
            public ArrayList<UserConfig> parse(JSONObject jSONObject) throws JSONException {
                ArrayList<UserConfig> arrayList = new ArrayList<>();
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        UserConfig a2 = UserConfig.a(optJSONArray.getJSONObject(i));
                        if (a2 != null) {
                            arrayList.add(a2);
                        }
                    }
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(Context context, int i, JSONArray jSONArray, AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> asyncCallback) {
        if (!UserConfigCompactManager.a().a(context, i, jSONArray, asyncCallback)) {
            return b(context, i, jSONArray, asyncCallback);
        }
        LogUtilGrey.a("UserApi", "testAccept intercepted");
        return new AsyncHandle(null);
    }

    private AsyncHandle b(Context context, int i, JSONArray jSONArray, AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("component_id", i);
            if (jSONArray != null && jSONArray.length() > 0) {
                jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
            }
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/get_user_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Map<Integer, UserConfig.UserConfigData>>() {
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
}
