package com.xiaomi.smarthome.printer;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceImageApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21154a = "https://static.home.mi.com/app/image/get/file/";
    private static final String b = "DeviceImageApi";
    private static final String c = "get_product_config_cache_json";

    public static void a(final String str, final AsyncCallback<DeviceImageEntity, Error> asyncCallback) {
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                final SharedPreferences sharedPreferences = CommonApplication.getAppContext().getSharedPreferences(DeviceImageApi.c, 0);
                String string = sharedPreferences.getString(str, (String) null);
                if (TextUtils.isEmpty(string)) {
                    ArrayList arrayList = new ArrayList();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("models", str);
                        jSONObject.put("device", Build.DEVICE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                    CoreApi.a().a(CommonApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/public/get_product_config").b((List<KeyValuePair>) arrayList).a(), new JsonParser<DeviceImageEntity>() {
                        /* renamed from: a */
                        public DeviceImageEntity parse(JSONObject jSONObject) throws JSONException {
                            JSONArray optJSONArray = jSONObject.optJSONArray("configs");
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i).optJSONObject("neg_screen");
                                if (optJSONObject != null) {
                                    String jSONObject2 = optJSONObject.toString();
                                    LogUtil.c(DeviceImageApi.b, "request model:" + str + " url:" + jSONObject2);
                                    sharedPreferences.edit().putString(str, jSONObject2).apply();
                                    return DeviceImageEntity.a(optJSONObject);
                                }
                            }
                            DeviceImageEntity deviceImageEntity = new DeviceImageEntity();
                            PluginRecord d = CoreApi.a().d(str);
                            if (d != null) {
                                deviceImageEntity.f21157a = d.t();
                            }
                            return deviceImageEntity;
                        }
                    }, Crypto.NONE, asyncCallback);
                    return;
                }
                LogUtil.c(DeviceImageApi.b, "get cache model:" + str + " url:" + string);
                try {
                    asyncCallback.sendSuccessMessage(DeviceImageEntity.a(new JSONObject(string)));
                } catch (JSONException e2) {
                    asyncCallback.sendFailureMessage(new Error(-1, Log.getStackTraceString(e2)));
                }
            }
        });
    }

    public static class DeviceImageEntity {

        /* renamed from: a  reason: collision with root package name */
        public String f21157a;
        public String b;

        public static DeviceImageEntity a(JSONObject jSONObject) {
            DeviceImageEntity deviceImageEntity = new DeviceImageEntity();
            String optString = jSONObject.optString("short_480");
            String optString2 = jSONObject.optString("short_video");
            String optString3 = jSONObject.optString("neg_480");
            String optString4 = jSONObject.optString("neg_video");
            if (TextUtils.isEmpty(optString3)) {
                optString3 = "https://static.home.mi.com/app/image/get/file/" + optString;
            }
            deviceImageEntity.f21157a = optString3;
            if (TextUtils.isEmpty(optString4)) {
                optString4 = "https://static.home.mi.com/app/image/get/file/" + optString2;
            }
            deviceImageEntity.b = optString4;
            return deviceImageEntity;
        }
    }
}
