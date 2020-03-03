package com.xiaomi.smarthome.config;

import android.support.annotation.NonNull;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.miot.support.monitor.config.MiotMonitorConstants;
import com.xiaomi.miot.support.monitor.utils.MiotMonitorXCache;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONObject;

public class AndroidMonitorConfigManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f13929a = "AndroidMonitorConfigManager";
    private static volatile AndroidMonitorConfigManager c = null;
    private static final int d = 5000;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public long f = 0;

    public interface ICallBack {
        void a();
    }

    private AndroidMonitorConfigManager() {
    }

    public static AndroidMonitorConfigManager a() {
        if (c == null) {
            synchronized (AndroidMonitorConfigManager.class) {
                if (c == null) {
                    c = new AndroidMonitorConfigManager();
                }
            }
        }
        return c;
    }

    public void a(final ICallBack iCallBack) {
        Request request;
        if (Math.abs(System.currentTimeMillis() - this.f) >= 5000 && !this.e.getAndSet(true)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
                StringBuilder sb = new StringBuilder();
                sb.append("android_monitor_config");
                sb.append(GlobalSetting.E ? "_preview" : "");
                jSONObject.put("name", sb.toString());
                jSONObject.put("version", "1");
            } catch (Exception unused) {
                this.e.set(false);
            }
            try {
                request = new Request.Builder().a("GET").b(a(jSONObject)).a();
            } catch (UnsupportedEncodingException e2) {
                this.e.set(false);
                e2.printStackTrace();
                request = null;
            }
            if (request == null) {
                this.e.set(false);
            } else {
                HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                    public void onFailure(Error error, Exception exc, Response response) {
                    }

                    public void onSuccess(Object obj, Response response) {
                    }

                    public void processResponse(Response response) {
                        String str = "";
                        if (response != null) {
                            try {
                                if (response.body() != null) {
                                    str = response.body().string();
                                }
                            } catch (Exception e) {
                                AndroidMonitorConfigManager.this.e.set(false);
                                e.printStackTrace();
                                return;
                            }
                        }
                        JSONObject optJSONObject = new JSONObject(str).optJSONObject("result");
                        if (optJSONObject != null) {
                            String b2 = AndroidMonitorConfigManager.f13929a;
                            Miio.b(b2, "getRemoteConfig  monitor result" + optJSONObject.toString());
                            String unused = AndroidMonitorConfigManager.this.b = optJSONObject.optString("content");
                        } else {
                            String unused2 = AndroidMonitorConfigManager.this.b = "";
                        }
                        MiotMonitorXCache.a(SHApplication.getAppContext()).a(MiotMonitorConstants.f11455a, AndroidMonitorConfigManager.this.b);
                        long unused3 = AndroidMonitorConfigManager.this.f = System.currentTimeMillis();
                        if (iCallBack != null) {
                            iCallBack.a();
                        }
                        AndroidMonitorConfigManager.this.e.set(false);
                    }

                    public void processFailure(Call call, IOException iOException) {
                        if (iCallBack != null) {
                            iCallBack.a();
                        }
                        long unused = AndroidMonitorConfigManager.this.f = System.currentTimeMillis();
                        AndroidMonitorConfigManager.this.e.set(false);
                    }
                });
            }
        }
    }

    @NonNull
    private String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }
}
