package com.xiaomi.smarthome.scene.timer;

import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimerCommonConfigManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile TimerCommonConfigManager f21690a;
    /* access modifiers changed from: private */
    public Set<String> b = new HashSet();

    private TimerCommonConfigManager() {
    }

    public static TimerCommonConfigManager a() {
        if (f21690a == null) {
            synchronized (TimerCommonConfigManager.class) {
                if (f21690a == null) {
                    f21690a = new TimerCommonConfigManager();
                }
            }
        }
        return f21690a;
    }

    public void b() {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
            StringBuilder sb = new StringBuilder();
            sb.append("local_timer_config");
            sb.append(GlobalSetting.E ? "_preview" : "");
            jSONObject.put("name", sb.toString());
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Request request = null;
        try {
            request = new Request.Builder().a("GET").b(a(jSONObject)).a();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (request != null) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processFailure(Call call, IOException iOException) {
                }

                public void processResponse(Response response) {
                    JSONObject optJSONObject;
                    try {
                        JSONObject jSONObject = new JSONObject(response.body().string());
                        if (!jSONObject.isNull("result") && (optJSONObject = jSONObject.optJSONObject("result")) != null && optJSONObject.has("content")) {
                            JSONArray jSONArray = new JSONArray(optJSONObject.optString("content"));
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                String optString = jSONArray.optString(i);
                                if (!TextUtils.isEmpty(optString)) {
                                    arrayList.add(optString);
                                }
                            }
                            if (arrayList.size() > 0) {
                                TimerCommonConfigManager.this.b.clear();
                                TimerCommonConfigManager.this.b.addAll(arrayList);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.b.contains(str);
    }

    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }
}
