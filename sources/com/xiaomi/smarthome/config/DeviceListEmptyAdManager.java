package com.xiaomi.smarthome.config;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.Parser;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.scene.GoLeaveHomeGroupConditionActivity;
import com.xiaomi.youpin.XmpluginHostApiImp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceListEmptyAdManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13933a = "device_list_empty_ad_updated";
    static final String b = "DeviceListEmptyAdManager";
    static final String c = "/shopv3/adv?model=device";
    static final String d = "https://api.io.mi.com/app/guest/intro";
    private static final String g = "device_list_empty_ad";
    private static final String h = "content";
    private static final String i = "mijia_intro_content";
    private static final String j = "timestamp";
    private static DeviceListEmptyAdManager k;
    DeviceListEmptyAdItem e = null;
    boolean f = false;
    /* access modifiers changed from: private */
    public volatile DeviceListEmptyAdData l;
    private long m;
    private int n = 60000;
    /* access modifiers changed from: private */
    public AtomicBoolean o = new AtomicBoolean(false);
    private boolean p = false;

    private DeviceListEmptyAdManager() {
        g();
    }

    public static DeviceListEmptyAdManager a() {
        if (k == null) {
            synchronized (DeviceListEmptyAdManager.class) {
                if (k == null) {
                    k = new DeviceListEmptyAdManager();
                }
            }
        }
        return k;
    }

    public DeviceListEmptyAdData b() {
        return this.l;
    }

    public void a(boolean z) {
        HomeManager.a();
        if (HomeManager.A() || this.o.getAndSet(true)) {
            return;
        }
        if (this.l == null) {
            f();
            e();
        } else if (z || Math.abs(System.currentTimeMillis() - this.m) >= ((long) this.n)) {
            f();
            e();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(g, 0).edit();
        edit.putString(i, str);
        edit.commit();
    }

    /* access modifiers changed from: private */
    public void a(DeviceListEmptyAdItem deviceListEmptyAdItem) {
        synchronized (DeviceListEmptyAdManager.class) {
            this.e = deviceListEmptyAdItem;
            if (this.l != null) {
                this.l.f13937a = deviceListEmptyAdItem;
            }
            if (!this.o.get()) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(f13933a));
            }
            this.f = true;
        }
    }

    private void e() {
        this.f = false;
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", UserAgentUtil.a(SHApplication.getAppContext()));
        HttpApi.a(new Request.Builder().a("GET").a((Map<String, String>) hashMap).b(d).a(), (AsyncHandler) new AsyncHandler() {
            public void processResponse(Response response) {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    if (!jSONObject.isNull("code")) {
                        if (!jSONObject.isNull("result")) {
                            if (jSONObject.optInt("code") != 0) {
                                DeviceListEmptyAdManager.this.a((DeviceListEmptyAdItem) null);
                                return;
                            }
                            JSONObject optJSONObject = jSONObject.optJSONObject("result");
                            if (optJSONObject == null) {
                                DeviceListEmptyAdManager.this.a("");
                                DeviceListEmptyAdManager.this.e = null;
                                if (DeviceListEmptyAdManager.this.l != null) {
                                    DeviceListEmptyAdManager.this.l.f13937a = null;
                                }
                                DeviceListEmptyAdManager.this.a((DeviceListEmptyAdItem) null);
                                return;
                            }
                            DeviceListEmptyAdManager.this.a(DeviceListEmptyAdItem.a(optJSONObject));
                            DeviceListEmptyAdManager.this.a(optJSONObject.toString());
                            DeviceListEmptyAdManager.this.f = true;
                            return;
                        }
                    }
                    DeviceListEmptyAdManager.this.a((DeviceListEmptyAdItem) null);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }

            public void processFailure(Call call, IOException iOException) {
                DeviceListEmptyAdManager.this.f = true;
                if (!DeviceListEmptyAdManager.this.o.get()) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceListEmptyAdManager.f13933a));
                }
            }

            public void onSuccess(Object obj, Response response) {
                DeviceListEmptyAdManager.this.f = true;
                if (!DeviceListEmptyAdManager.this.o.get()) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceListEmptyAdManager.f13933a));
                }
            }

            public void onFailure(Error error, Exception exc, Response response) {
                DeviceListEmptyAdManager.this.f = true;
                if (!DeviceListEmptyAdManager.this.o.get()) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceListEmptyAdManager.f13933a));
                }
            }
        });
    }

    private void f() {
        XmpluginHostApiImp.instance().sendMijiaShopRequest(c, new JsonObject(), new Callback<Object>() {
            public void onCache(Object obj) {
            }

            public void onFailure(int i, String str) {
            }

            public void onSuccess(Object obj, boolean z) {
                if (obj != null) {
                    DeviceListEmptyAdData b = DeviceListEmptyAdManager.this.b(obj.toString());
                    if (b == null) {
                        b = new DeviceListEmptyAdData();
                    }
                    DeviceListEmptyAdData unused = DeviceListEmptyAdManager.this.l = b;
                    DeviceListEmptyAdManager.this.a(b);
                    synchronized (DeviceListEmptyAdManager.class) {
                        if (DeviceListEmptyAdManager.this.l != null) {
                            DeviceListEmptyAdManager.this.l.f13937a = DeviceListEmptyAdManager.this.e;
                        }
                        if (DeviceListEmptyAdManager.this.f) {
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceListEmptyAdManager.f13933a));
                        }
                    }
                    DeviceListEmptyAdManager.this.o.set(false);
                }
            }
        }, new Parser<Object>() {
            public Object parse(JsonElement jsonElement) {
                Log.d("hzd1", jsonElement.toString());
                return jsonElement;
            }
        }, false);
    }

    private void g() {
        SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(g, 0);
        String string = sharedPreferences.getString("content", "");
        String string2 = sharedPreferences.getString(i, "");
        if (!TextUtils.isEmpty(string) || !TextUtils.isEmpty(string2)) {
            try {
                if (!TextUtils.isEmpty(string)) {
                    this.l = DeviceListEmptyAdData.a(new JSONArray(string));
                    this.m = sharedPreferences.getLong("timestamp", 0);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            try {
                if (!TextUtils.isEmpty(string2) && this.l == null) {
                    this.l = new DeviceListEmptyAdData();
                    this.l.f13937a = DeviceListEmptyAdItem.a(new JSONObject(string2));
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(f13933a));
        }
    }

    /* access modifiers changed from: private */
    public void a(DeviceListEmptyAdData deviceListEmptyAdData) {
        if (deviceListEmptyAdData != null) {
            String a2 = deviceListEmptyAdData.a();
            SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(g, 0).edit();
            edit.putString("content", a2);
            edit.putLong("timestamp", System.currentTimeMillis());
            edit.apply();
        }
    }

    /* access modifiers changed from: private */
    public DeviceListEmptyAdData b(String str) {
        try {
            return DeviceListEmptyAdData.a(new JSONArray(str));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void c() {
        this.p = true;
    }

    public boolean d() {
        if (!this.p || CoreApi.a().D() || this.l == null || (this.l.f13937a == null && (this.l.b == null || this.l.b.size() == 0))) {
            return false;
        }
        if (CoreApi.a().q()) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(SmartHomeDeviceManager.a().d());
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Device device = (Device) arrayList.get(i2);
                if (device != null && !device.model.startsWith("xiaomi.tv") && !device.model.startsWith(GoLeaveHomeGroupConditionActivity.MI_ROUTER_PREFIX) && !device.model.startsWith("xiaomi.phone_ir.t1") && !device.model.startsWith("xiaomi.phone_ir.v1")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class DeviceListEmptyAdItem {

        /* renamed from: a  reason: collision with root package name */
        public String f13938a;
        public String b;
        public String c;
        public long d;
        public long e;

        public static DeviceListEmptyAdItem a(JSONObject jSONObject) {
            if (jSONObject.isNull("img") || jSONObject.isNull("url")) {
                return null;
            }
            String optString = jSONObject.optString("img");
            String optString2 = jSONObject.optString("url");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return null;
            }
            DeviceListEmptyAdItem deviceListEmptyAdItem = new DeviceListEmptyAdItem();
            deviceListEmptyAdItem.f13938a = jSONObject.optString("img");
            deviceListEmptyAdItem.b = jSONObject.optString("url");
            deviceListEmptyAdItem.c = jSONObject.optString("title");
            deviceListEmptyAdItem.d = jSONObject.optLong("start");
            deviceListEmptyAdItem.e = jSONObject.optLong("end");
            return deviceListEmptyAdItem;
        }

        public int hashCode() {
            int hashCode = !TextUtils.isEmpty(this.f13938a) ? this.f13938a.hashCode() : 0;
            return !TextUtils.isEmpty(this.b) ? (hashCode * 37) + this.b.hashCode() : hashCode;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DeviceListEmptyAdItem) || obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            DeviceListEmptyAdItem deviceListEmptyAdItem = (DeviceListEmptyAdItem) obj;
            if (!TextUtils.equals(deviceListEmptyAdItem.f13938a, this.f13938a) || !TextUtils.equals(deviceListEmptyAdItem.b, this.b)) {
                return false;
            }
            return true;
        }
    }

    public static class DeviceListEmptyAdData {

        /* renamed from: a  reason: collision with root package name */
        public DeviceListEmptyAdItem f13937a;
        public List<DeviceListEmptyAdItem> b;
        public JSONArray c;

        public static DeviceListEmptyAdData a(JSONArray jSONArray) {
            DeviceListEmptyAdData deviceListEmptyAdData = new DeviceListEmptyAdData();
            deviceListEmptyAdData.c = jSONArray;
            deviceListEmptyAdData.b = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                if (DeviceListEmptyAdItem.a(jSONArray.optJSONObject(i)) != null) {
                    deviceListEmptyAdData.b.add(DeviceListEmptyAdItem.a(jSONArray.optJSONObject(i)));
                }
            }
            return deviceListEmptyAdData;
        }

        public String a() {
            if (this.c == null) {
                return "";
            }
            return this.c.toString();
        }

        public int hashCode() {
            int hashCode = this.f13937a != null ? this.f13937a.hashCode() : 0;
            if (this.b != null && this.b.size() > 0) {
                for (int i = 0; i < this.b.size(); i++) {
                    DeviceListEmptyAdItem deviceListEmptyAdItem = this.b.get(i);
                    if (deviceListEmptyAdItem != null) {
                        hashCode = (hashCode * 37) + deviceListEmptyAdItem.hashCode();
                    }
                }
            }
            return hashCode;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof DeviceListEmptyAdData)) {
                return false;
            }
            DeviceListEmptyAdData deviceListEmptyAdData = (DeviceListEmptyAdData) obj;
            if ((deviceListEmptyAdData.f13937a == null && this.f13937a != null) || (deviceListEmptyAdData.f13937a != null && this.f13937a == null)) {
                return false;
            }
            if (this.f13937a != null && !this.f13937a.equals(deviceListEmptyAdData.f13937a)) {
                return false;
            }
            if ((deviceListEmptyAdData.b == null && this.b != null) || (deviceListEmptyAdData.b != null && this.b == null)) {
                return false;
            }
            if (deviceListEmptyAdData.b == null && this.b == null) {
                return true;
            }
            if (deviceListEmptyAdData.b.size() != this.b.size()) {
                return false;
            }
            for (DeviceListEmptyAdItem contains : this.b) {
                if (!deviceListEmptyAdData.b.contains(contains)) {
                    return false;
                }
            }
            return true;
        }
    }
}
