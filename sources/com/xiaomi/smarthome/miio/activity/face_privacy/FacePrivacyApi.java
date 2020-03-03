package com.xiaomi.smarthome.miio.activity.face_privacy;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.international.RxApi;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacePrivacyApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11876a = "FacePrivacyApi";
    private static final String b = "/v2/camera/model_list";
    private static final String c = "/v2/camera/get_switch";
    private static final String d = "/v2/camera/detect_switch";
    private static FacePrivacyApi e;

    private FacePrivacyApi() {
    }

    public static FacePrivacyApi a() {
        if (e == null) {
            synchronized (FacePrivacyApi.class) {
                if (e == null) {
                    e = new FacePrivacyApi();
                }
            }
        }
        return e;
    }

    public Observable<List<FaceStatus>> b() {
        Observable<R> cache = c().map($$Lambda$FacePrivacyApi$h3BUckpjMXJdUODeaA2qi2frYzA.INSTANCE).cache();
        return Observable.zip(cache, cache.flatMap(new Function() {
            public final Object apply(Object obj) {
                return FacePrivacyApi.this.a((Map) obj);
            }
        }), $$Lambda$FacePrivacyApi$BBhS7sNl4wqKJzOsr7b23aFEvgY.INSTANCE).subscribeOn(Schedulers.io());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map a(Set set) throws Exception {
        HashMap hashMap = new HashMap();
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if (next != null && !SmartHomeDeviceManager.e(next) && set.contains(next.model)) {
                hashMap.put(next.did, next);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource a(Map map) throws Exception {
        return a((List<String>) new ArrayList(map.keySet()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List a(Map map, Map map2) throws Exception {
        ArrayList arrayList = new ArrayList();
        for (String str : map2.keySet()) {
            Device device = (Device) map.get(str);
            if (device == null) {
                LogUtil.b(f11876a, "map did to device lost did: " + str);
            } else {
                arrayList.add(new FaceStatus(device, (Boolean) map2.get(str)));
            }
        }
        return arrayList;
    }

    private Observable<Map<String, Boolean>> a(List<String> list) {
        if (list.isEmpty()) {
            Log.d(f11876a, "getSwitchStatus: empty parma dids");
            return Observable.just(Collections.emptyMap());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("op_type", 1);
            jSONObject.put("dids", new JSONArray(list));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return RxApi.a(new NetRequest.Builder().a("GET").b(c).b((List<KeyValuePair>) Collections.singletonList(new KeyValuePair("data", jSONObject.toString()))).a(), new JsonParser<Map<String, Boolean>>() {
            /* renamed from: a */
            public Map<String, Boolean> parse(JSONObject jSONObject) throws JSONException {
                LogUtil.a(FacePrivacyApi.f11876a, "getSwitchStatus parse: " + jSONObject);
                JSONArray jSONArray = jSONObject.getJSONArray("result");
                HashMap hashMap = new HashMap(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    hashMap.put(jSONObject2.getString("did"), Boolean.valueOf(jSONObject2.getBoolean("open")));
                }
                return hashMap;
            }
        }).doOnError(new Consumer<Throwable>() {
            /* renamed from: a */
            public void accept(Throwable th) throws Exception {
                LogUtil.a(FacePrivacyApi.f11876a, "getSwitchStatus: error " + Log.getStackTraceString(th));
            }
        });
    }

    private Observable<Set<String>> c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("op_type", 1);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return RxApi.a(new NetRequest.Builder().a("GET").b(b).b((List<KeyValuePair>) Collections.singletonList(new KeyValuePair("data", jSONObject.toString()))).a(), new JsonParser<Set<String>>() {
            /* renamed from: a */
            public Set<String> parse(JSONObject jSONObject) throws JSONException {
                LogUtil.a(FacePrivacyApi.f11876a, "getSupportModel parse: " + jSONObject);
                HashSet hashSet = new HashSet();
                JSONArray jSONArray = jSONObject.getJSONArray("result");
                for (int i = 0; i < jSONArray.length(); i++) {
                    hashSet.add(jSONArray.getString(i));
                }
                return hashSet;
            }
        }).doOnError(new Consumer<Throwable>() {
            /* renamed from: a */
            public void accept(Throwable th) throws Exception {
                LogUtil.a(FacePrivacyApi.f11876a, "getSupportModel: error " + Log.getStackTraceString(th));
            }
        });
    }

    public Observable<Boolean> a(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("op_type", 1);
            jSONObject.put("did", str);
            jSONObject.put("open", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return RxApi.a(new NetRequest.Builder().a("GET").b(d).b((List<KeyValuePair>) Collections.singletonList(new KeyValuePair("data", jSONObject.toString()))).a(), new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                LogUtil.a(FacePrivacyApi.f11876a, "toggleSwitch parse: " + jSONObject);
                return Boolean.valueOf(TextUtils.equals(jSONObject.getString("message"), "ok"));
            }
        }).onErrorReturnItem(false);
    }

    public static class FaceStatus {

        /* renamed from: a  reason: collision with root package name */
        final Device f11882a;
        final Boolean b;

        public FaceStatus(Device device, Boolean bool) {
            this.f11882a = device;
            this.b = bool;
        }
    }
}
