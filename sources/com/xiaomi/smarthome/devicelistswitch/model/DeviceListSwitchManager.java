package com.xiaomi.smarthome.devicelistswitch.model;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyParam;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.newui.card.DeviceCardApi;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.service.tasks.DeviceOnTask;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.xiaomi.smarthome.service.tasks.LoginTask;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceListSwitchManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15515a = "device_list_switch_subtitle";
    public static final String b = "device_plugin_opening";
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    private static final String i = "DeviceListSwitchManager";
    private static DeviceListSwitchManager j = null;
    private static final int k = 1800000;
    private static final String o = "sp_device_list_switch_config";
    private static final int p = 1;
    protected Map<String, Map<String, String>> c = new ConcurrentHashMap();
    public volatile boolean d = false;
    private long l = 0;
    /* access modifiers changed from: private */
    public DeviceListSwitchData m = new DeviceListSwitchData();
    /* access modifiers changed from: private */
    public Map<String, Boolean> n = new ConcurrentHashMap();
    private DevicePropSubscriber q;
    /* access modifiers changed from: private */
    public Handler r = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                DeviceListSwitchManager.this.k();
            }
        }
    };
    /* access modifiers changed from: private */
    public AtomicBoolean s = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean t = new AtomicBoolean(false);
    private DevicePropSubscriber u;
    /* access modifiers changed from: private */
    public Map<String, WeakReference<Runnable>> v = new ConcurrentHashMap();

    private DeviceListSwitchManager() {
    }

    public static DeviceListSwitchManager a() {
        if (j == null) {
            synchronized (DeviceListSwitchManager.class) {
                if (j == null) {
                    j = new DeviceListSwitchManager();
                }
            }
        }
        return j;
    }

    /* access modifiers changed from: private */
    public void k() {
        m();
        d();
    }

    public void b() {
        if (this.d) {
            l();
            return;
        }
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 != null && d2.size() != 0) {
            if (!this.s.getAndSet(true)) {
                this.r.sendEmptyMessage(1);
            } else {
                this.t.set(true);
            }
        }
    }

    private void l() {
        ArrayList<Device> arrayList = new ArrayList<>();
        arrayList.addAll(SmartHomeDeviceManager.a().d());
        arrayList.addAll(SmartHomeDeviceManager.a().k());
        ArrayList arrayList2 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (Device device : arrayList) {
            hashSet.add(device.did);
            if (!this.c.containsKey(device.did)) {
                arrayList2.add(device);
            }
        }
        if (!arrayList2.isEmpty()) {
            b(arrayList2, (AsyncCallback<Void, Error>) null);
        }
        for (String str : new ArrayList(this.c.keySet())) {
            if (!hashSet.contains(str)) {
                this.c.remove(str);
            }
        }
    }

    public Map<String, ModelOperations> c() {
        Map<String, ModelOperations> b2 = this.m.b();
        if (b2.isEmpty()) {
            d();
        }
        return b2;
    }

    public void d() {
        this.r.removeMessages(1);
        n();
        a((List<String>) null, (AsyncCallback<Void, Error>) null);
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(o, 0);
        sharedPreferences.edit().putString("content", jSONObject.toString()).commit();
        sharedPreferences.edit().putLong("timestamp", System.currentTimeMillis()).commit();
    }

    private void m() {
        String string = SHApplication.getAppContext().getSharedPreferences(o, 0).getString("content", "");
        if (!TextUtils.isEmpty(string)) {
            try {
                this.m = DeviceListSwitchData.a(new JSONObject(string));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public int a(Device device) {
        ModelOperations modelOperations;
        if (!this.d || !this.m.a() || !device.isBinded() || this.c.isEmpty() || !this.c.containsKey(device.did) || (modelOperations = this.m.b().get(device.model)) == null || modelOperations.c() == null || modelOperations.c().length == 0) {
            return 3;
        }
        Operation operation = modelOperations.c()[0];
        Map<String, Map<String, String>> map = this.c;
        if (map.isEmpty() || !map.containsKey(device.did)) {
            return 3;
        }
        Map map2 = map.get(device.did);
        if (map2.isEmpty() || !map2.containsKey(operation.b())) {
            return 3;
        }
        String str = (String) map2.get(operation.b());
        for (Operation operation2 : modelOperations.c()) {
            if (TextUtils.equals(operation2.b(), operation.b()) && TextUtils.equals(operation2.c(), str)) {
                return 1;
            }
        }
        return 3;
    }

    public boolean b(Device device) {
        ModelOperations modelOperations = this.m.b().get(device.model);
        if (modelOperations == null || modelOperations.c() == null || modelOperations.c().length == 0) {
            return false;
        }
        Operation operation = modelOperations.c()[0];
        Map<String, Map<String, String>> map = this.c;
        if (map.isEmpty() || !map.containsKey(device.did)) {
            return false;
        }
        Map map2 = map.get(device.did);
        if (map2.isEmpty() || !map2.containsKey(operation.b())) {
            return false;
        }
        String str = (String) map2.get(operation.b());
        for (Operation operation2 : modelOperations.c()) {
            if (TextUtils.equals(operation2.b(), operation.b()) && TextUtils.equals(operation2.c(), str)) {
                return TextUtils.equals("on", operation2.g());
            }
        }
        return false;
    }

    public boolean a(String str) {
        Boolean bool = this.n.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean c(Device device) {
        ModelOperations modelOperations;
        if (this.d && this.m.a() && device.isBinded() && device.isOnline && !this.c.isEmpty() && this.c.containsKey(device.did) && (modelOperations = this.m.b().get(device.model)) != null && modelOperations.c() != null && modelOperations.c().length != 0 && !CoreApi.a().D()) {
            return true;
        }
        return false;
    }

    public void e() {
        this.r.removeCallbacksAndMessages((Object) null);
        if (this.q != null) {
            this.q.a();
            this.q = null;
        }
        j = null;
    }

    public boolean f() {
        return this.d;
    }

    private Operation a(Operation[] operationArr, String str) {
        Operation operation;
        int i2 = 0;
        while (true) {
            if (i2 >= operationArr.length) {
                operation = null;
                break;
            } else if (TextUtils.equals(operationArr[i2].c(), str)) {
                operation = operationArr[i2];
                break;
            } else {
                i2++;
            }
        }
        if (operation == null) {
            return null;
        }
        String f2 = operation.f();
        for (int i3 = 0; i3 < operationArr.length; i3++) {
            if (TextUtils.equals(operationArr[i3].c(), f2)) {
                return operationArr[i3];
            }
        }
        return null;
    }

    public void a(Device device, boolean z, AsyncCallback<Void, Error> asyncCallback) {
        final Operation operation;
        if (!(device instanceof MiioDeviceV2)) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(0, "should be MiioDeviceV2 type"));
            }
        } else if (a().a(device) == 3) {
            a(device, asyncCallback);
        } else {
            final ModelOperations modelOperations = this.m.b().get(device.model);
            if (modelOperations != null && modelOperations.c() != null && modelOperations.c().length != 0) {
                Map<String, Map<String, String>> map = this.c;
                if (!map.isEmpty() && map.containsKey(device.did)) {
                    Map map2 = map.get(device.did);
                    Operation operation2 = modelOperations.c()[0];
                    String b2 = operation2.b();
                    if (!map2.isEmpty() && map2.containsKey(b2)) {
                        String str = (String) map2.get(b2);
                        if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
                            ToastUtil.a((Context) SHApplication.getApplication(), SHApplication.getApplication().getString(R.string.property_is_null_tips), 1);
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(new Error(0, "the prop:" + operation2.b() + " is null, should init by device plugin"));
                                return;
                            }
                            return;
                        }
                        Operation[] c2 = modelOperations.c();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= c2.length) {
                                operation = null;
                                break;
                            } else if (TextUtils.equals(c2[i2].c(), str)) {
                                operation = c2[i2];
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (operation != null) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
                                jSONObject.put("method", operation.d());
                                JSONArray jSONArray = new JSONArray();
                                if (operation.e() != null && operation.e().length > 0) {
                                    for (String put : operation.e()) {
                                        jSONArray.put(put);
                                    }
                                }
                                jSONObject.put("params", jSONArray);
                            } catch (JSONException unused) {
                            }
                            final long currentTimeMillis = System.currentTimeMillis();
                            final String d2 = operation.d();
                            final Device device2 = device;
                            final AsyncCallback<Void, Error> asyncCallback2 = asyncCallback;
                            ((MiioDeviceV2) device).a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    Operation a2;
                                    DeviceListSwitchManager.this.a(device2, operation);
                                    if (!(device2.property == null || (a2 = DeviceListSwitchManager.this.a(operation.f(), modelOperations)) == null)) {
                                        device2.property.putString(DeviceListSwitchManager.f15515a, DeviceListSwitchManager.this.a(a2));
                                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent());
                                    }
                                    DeviceListSwitchManager.this.d(((MiioDeviceV2) device2).n());
                                    if (asyncCallback2 != null) {
                                        asyncCallback2.onSuccess(null);
                                    }
                                    long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                                    if (currentTimeMillis > 0 && currentTimeMillis < 10000) {
                                        STAT.i.a(currentTimeMillis, 1, d2, device2.model);
                                    }
                                }

                                public void onFailure(Error error) {
                                    if (asyncCallback2 != null) {
                                        asyncCallback2.onFailure(error);
                                    }
                                    long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                                    if (currentTimeMillis > 0 && currentTimeMillis < 10000) {
                                        STAT.i.a(currentTimeMillis, 0, d2, device2.model);
                                    }
                                }
                            });
                            JSONObject jSONObject2 = new JSONObject();
                            if (device != null) {
                                try {
                                    jSONObject2.put("model", device.model);
                                    jSONObject2.put("did", device.did);
                                    jSONObject2.put("isOnline", device.isOnline);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            jSONObject2.put("rpc", jSONObject);
                            CoreApi.a().a(StatType.EVENT, "device_list_switch", "toggleDevice", jSONObject2.toString(), false);
                        } else if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(0, "not found related operation"));
                        }
                    } else if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(0, "mDevicePropMap not contain related prop:" + operation2.b()));
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(0, "not found related device by did:" + device.did));
                }
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(0, "model operation not init"));
            }
        }
    }

    private void a(final Device device, final AsyncCallback<Void, Error> asyncCallback) {
        Boolean bool = this.n.get(device.did);
        Pair<SpecService, SpecProperty> e2 = MiotSpecCardManager.f().e(device);
        if (bool == null || e2 == null || !((SpecProperty) e2.second).getPropertyDefinition().writable()) {
            asyncCallback.onFailure(new Error(0, "cannot find device state"));
            return;
        }
        DeviceCardApi.SpecPropertyApi.instance.setDeviceSpecProp(SHApplication.getAppContext(), new PropertyParam(device.did, ((SpecService) e2.first).getIid(), ((SpecProperty) e2.second).getIid(), Boolean.valueOf(!bool.booleanValue())), new AsyncCallback<PropertyParam, Error>() {
            /* renamed from: a */
            public void onSuccess(PropertyParam propertyParam) {
                if (propertyParam != null && propertyParam.getResultCode() == 0) {
                    DeviceListSwitchManager.this.n.put(device.did, (Boolean) propertyParam.getValue());
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(null);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(0, "set failed"));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String a(Operation operation) {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = SHApplication.getAppContext().getResources().getConfiguration().locale;
        }
        if (I == null) {
            return operation.a("en");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String languageTag = I.toLanguageTag();
            if (!TextUtils.isEmpty(languageTag)) {
                String a2 = operation.a(languageTag);
                if (!TextUtils.isEmpty(a2)) {
                    return a2;
                }
            }
        }
        String language = I.getLanguage();
        String country = I.getCountry();
        String a3 = operation.a(language + JSMethod.NOT_SET + country);
        if (!TextUtils.isEmpty(a3)) {
            return a3;
        }
        if (language.equalsIgnoreCase(Locale.SIMPLIFIED_CHINESE.getLanguage())) {
            return operation.a("zh_CN");
        }
        if (language.equalsIgnoreCase(Locale.TAIWAN.getLanguage())) {
            return operation.a("zh_TW");
        }
        if (language.equalsIgnoreCase(Locale.KOREA.getLanguage()) || language.equalsIgnoreCase(Locale.KOREAN.getLanguage())) {
            return operation.a("korea");
        }
        if (language.equalsIgnoreCase(Locale.US.getLanguage()) || language.equalsIgnoreCase(Locale.UK.getLanguage())) {
            return operation.a("en");
        }
        if (language.equalsIgnoreCase(new Locale("bo", "CN").getLanguage())) {
            return operation.a("tibet");
        }
        return operation.a("en");
    }

    /* access modifiers changed from: private */
    public Operation a(String str, ModelOperations modelOperations) {
        Operation[] c2 = modelOperations.c();
        if (c2 == null || c2.length == 0) {
            return null;
        }
        for (Operation operation : c2) {
            if (TextUtils.equals(operation.c(), str)) {
                return operation;
            }
        }
        return null;
    }

    public void g() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(GetDeviceTask.d));
    }

    public void h() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(GetDeviceTask.e));
    }

    public void a(Device device, Operation operation) {
        if (!TextUtils.isEmpty(operation.f()) && !"null".equalsIgnoreCase(operation.f())) {
            Map map = this.c.get(device.did);
            if (map == null) {
                map = new HashMap();
            }
            map.put(operation.b(), operation.f());
            this.c.put(device.did, map);
            SmartHomeDeviceManager.a().a(SmartHomeDeviceManager.a().b(device.did));
        }
    }

    public void a(final List<String> list, AsyncCallback<Void, Error> asyncCallback) {
        ModelOperations modelOperations;
        Operation[] c2;
        i();
        Map<String, ModelOperations> b2 = this.m.b();
        if (b2.isEmpty()) {
            this.s.set(false);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(SmartHomeDeviceManager.a().d());
        arrayList.addAll(SmartHomeDeviceManager.a().k());
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Device device = (Device) arrayList.get(i2);
            if (!(device == null || ((list != null && !list.contains(device.did)) || (modelOperations = b2.get(device.model)) == null || (c2 = modelOperations.c()) == null || c2.length == 0))) {
                jSONArray.put(a(device, c2));
            }
        }
        if (jSONArray.length() > 0) {
            AnonymousClass4 r9 = new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    DeviceListSwitchManager.this.a(str, (List<String>) list);
                    DeviceListSwitchManager.this.d = true;
                    DeviceListSwitchManager.this.r.post(new Runnable() {
                        public void run() {
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                            DeviceListSwitchManager.this.g();
                        }
                    });
                    DeviceListSwitchManager.this.s.set(false);
                    if (DeviceListSwitchManager.this.t.getAndSet(false)) {
                        DeviceListSwitchManager.this.a((List<String>) null, (AsyncCallback<Void, Error>) null);
                    }
                }

                public void onFailure(Error error) {
                    DeviceListSwitchManager.this.s.set(false);
                    DeviceListSwitchManager.this.t.set(false);
                    DeviceListSwitchManager.this.h();
                }
            };
            Context appContext = SHApplication.getAppContext();
            DevicelibApi.batchGetDeviceProps(appContext, jSONArray, r9, DeviceListSwitchManager.class.getSimpleName() + "-updateDeviceProps");
            return;
        }
        this.s.set(false);
        g();
    }

    public void i() {
        Pair<SpecService, SpecProperty> e2;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(SmartHomeDeviceManager.a().d());
        arrayList.addAll(SmartHomeDeviceManager.a().k());
        arrayList.addAll(SmartHomeDeviceManager.a().m());
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Device device = (Device) it.next();
            if (device.isOnline && (e2 = MiotSpecCardManager.f().e(device)) != null && !((SpecProperty) e2.second).getPropertyDefinition().readable()) {
                arrayList2.add(new PropertyParam(device.did, ((SpecService) e2.first).getIid(), ((SpecProperty) e2.second).getIid()));
                arrayList3.add(device);
            }
        }
        DeviceCardApi.b(SHApplication.getAppContext(), arrayList2, new AsyncCallback<List<PropertyParam>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(List<PropertyParam> list) {
                HashMap hashMap = new HashMap();
                for (PropertyParam next : list) {
                    if (next.getResultCode() == 0) {
                        if (next.getValue() instanceof Boolean) {
                            hashMap.put(next.getDid(), (Boolean) next.getValue());
                        } else if (next.getValue() instanceof Integer) {
                            hashMap.put(next.getDid(), Boolean.valueOf(((Integer) next.getValue()).intValue() != 0));
                        }
                    }
                }
                Map unused = DeviceListSwitchManager.this.n = hashMap;
                DeviceListSwitchManager.this.a((List<Device>) arrayList3, (List<PropertyParam>) arrayList2);
                DeviceListSwitchManager.this.r.post($$Lambda$DeviceListSwitchManager$5$i24fXIQBi26zARbY6tljNm30w.INSTANCE);
            }
        });
    }

    public void a(List<Device> list, final List<PropertyParam> list2) {
        if (this.u == null) {
            this.u = new DevicePropSubscriber();
        }
        this.u.a(list, (DevicePropSubscriber.DeviceSubscriberInterface) new DevicePropSubscriber.DeviceSubscriberInterface() {
            public List<String> a(String str) {
                ArrayList arrayList = new ArrayList();
                for (PropertyParam propertyParam : list2) {
                    if (propertyParam.getDid().equals(str)) {
                        arrayList.add("prop." + propertyParam.getSiid() + "." + propertyParam.getPiid());
                        return arrayList;
                    }
                }
                return null;
            }

            public void a(final String str, JSONArray jSONArray) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null && !optJSONObject.isNull("key")) {
                        Object opt = optJSONObject.opt("value");
                        if ((opt instanceof JSONArray) && ((JSONArray) opt).length() > 0) {
                            try {
                                opt = ((JSONArray) opt).get(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (opt instanceof Boolean) {
                            DeviceListSwitchManager.this.n.put(str, (Boolean) opt);
                        } else if (opt instanceof Integer) {
                            DeviceListSwitchManager.this.n.put(str, Boolean.valueOf(((Integer) opt).intValue() != 0));
                        }
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str);
                        SmartHomeDeviceManager.a().a((List<String>) arrayList, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                            public void onFailure(Error error) {
                            }

                            /* renamed from: a */
                            public void onSuccess(List<Device> list) {
                                Device b2 = SmartHomeDeviceManager.a().b(str);
                                SmartHomeDeviceManager.a().a(b2);
                                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                if (b2 != null) {
                                    for (GridViewData next : HomeManager.a().F()) {
                                        if (next.f18311a == GridViewData.GridType.TYPE_NORMAL && next.b.did.equals(b2.did)) {
                                            Intent intent = new Intent();
                                            intent.setAction("com.xiaomi.smarthome.refresh_device");
                                            intent.putExtra(LoginTask.f22078a, CoreApi.a().s());
                                            intent.putExtra(GetDeviceTask.c, GetDeviceTask.a(next));
                                            SHApplication.getAppContext().sendBroadcast(intent);
                                            return;
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }

            public JSONArray b(String str) {
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                Boolean bool = (Boolean) DeviceListSwitchManager.this.n.get(str);
                for (PropertyParam propertyParam : list2) {
                    if (propertyParam.getDid().equals(str)) {
                        try {
                            jSONObject.put(propertyParam.getSiid() + "." + propertyParam.getPiid(), bool);
                            jSONArray.put(jSONObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return jSONArray;
            }
        });
    }

    public void b(final List<Device> list, AsyncCallback<Void, Error> asyncCallback) {
        ModelOperations modelOperations;
        Operation[] c2;
        Map<String, ModelOperations> b2 = this.m.b();
        if (b2.isEmpty()) {
            this.s.set(false);
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Device device = list.get(i2);
            if (!(device == null || (modelOperations = b2.get(device.model)) == null || (c2 = modelOperations.c()) == null || c2.length == 0)) {
                jSONArray.put(a(device, c2));
            }
        }
        if (jSONArray.length() > 0) {
            AnonymousClass7 r8 = new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    ArrayList arrayList = new ArrayList();
                    for (Device device : list) {
                        arrayList.add(device.did);
                    }
                    DeviceListSwitchManager.this.a(str, (List<String>) arrayList);
                    DeviceListSwitchManager.this.d = true;
                    DeviceListSwitchManager.this.r.post(new Runnable() {
                        public void run() {
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                            DeviceListSwitchManager.this.g();
                        }
                    });
                    DeviceListSwitchManager.this.s.set(false);
                    if (DeviceListSwitchManager.this.t.getAndSet(false)) {
                        DeviceListSwitchManager.this.a((List<String>) null, (AsyncCallback<Void, Error>) null);
                    }
                }

                public void onFailure(Error error) {
                    DeviceListSwitchManager.this.s.set(false);
                    DeviceListSwitchManager.this.t.set(false);
                    DeviceListSwitchManager.this.h();
                }
            };
            Context appContext = SHApplication.getAppContext();
            DevicelibApi.batchGetDeviceProps(appContext, jSONArray, r8, DeviceListSwitchManager.class.getSimpleName() + "-updateDevicePropsByDevice");
            return;
        }
        this.s.set(false);
        g();
    }

    /* access modifiers changed from: protected */
    public void a(String str, final List<String> list) {
        Map<String, Map<String, String>> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            if (list != null && list.size() > 0) {
                concurrentHashMap = this.c;
            }
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                if (optJSONObject != null) {
                    HashMap hashMap = new HashMap();
                    Iterator<String> keys2 = optJSONObject.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        Object obj = optJSONObject.get(next2);
                        if (next2.startsWith("prop.")) {
                            next2 = next2.substring("prop.".length());
                        }
                        if (obj != null) {
                            hashMap.put(next2, "" + obj);
                        }
                    }
                    concurrentHashMap.put(next, hashMap);
                }
            }
            this.c = concurrentHashMap;
            if (list == null || list.size() <= 0) {
                this.r.post(new Runnable() {
                    public void run() {
                        SmartHomeDeviceManager.a().j();
                    }
                });
                HashSet hashSet = new HashSet();
                hashSet.addAll(SmartHomeDeviceManager.a().d());
                hashSet.addAll(SmartHomeDeviceManager.a().k());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(hashSet);
                hashSet.clear();
                if (this.q == null) {
                    this.q = new DevicePropSubscriber();
                }
                this.q.a((List<Device>) arrayList, (DevicePropSubscriber.DeviceSubscriberInterface) new DevicePropSubscriber.DeviceSubscriberInterface() {
                    public List<String> a(String str) {
                        Map<String, String> b = DeviceListSwitchManager.this.b(str);
                        if (b == null || b.size() == 0) {
                            return null;
                        }
                        return new ArrayList(b.keySet());
                    }

                    public void a(String str, JSONArray jSONArray) {
                        DeviceListSwitchManager.this.a(str, jSONArray);
                    }

                    public JSONArray b(String str) {
                        JSONArray jSONArray = new JSONArray();
                        Map<String, String> b = DeviceListSwitchManager.this.b(str);
                        if (b != null) {
                            for (Map.Entry next : b.entrySet()) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put((String) next.getKey(), next.getValue());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                jSONArray.put(jSONObject);
                            }
                        }
                        return jSONArray;
                    }
                });
                return;
            }
            this.r.post(new Runnable() {
                public void run() {
                    for (String b2 : list) {
                        SmartHomeDeviceManager.a().a(SmartHomeDeviceManager.a().b(b2));
                    }
                }
            });
        } catch (JSONException unused) {
        }
    }

    private JSONObject a(Device device, Operation[] operationArr) {
        JSONObject jSONObject = new JSONObject();
        HashSet hashSet = new HashSet();
        for (Operation operation : operationArr) {
            if (device.model.startsWith("lumi.sensor_magnet.")) {
                hashSet.add("event.open");
                hashSet.add("event.close");
            } else if (!TextUtils.isEmpty(operation.b())) {
                hashSet.add(operation.b());
            }
        }
        if (hashSet.size() > 0) {
            try {
                jSONObject.put("did", device.did);
                JSONArray jSONArray = new JSONArray();
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    jSONArray.put("prop." + ((String) it.next()));
                }
                jSONObject.put("props", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    private void n() {
        if (Math.abs(System.currentTimeMillis() - this.l) >= 1800000) {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
                StringBuilder sb = new StringBuilder();
                sb.append("shortcut_card_device_config");
                sb.append(GlobalSetting.E ? "_preview" : "");
                jSONObject.put("name", sb.toString());
                jSONObject.put("version", "1");
            } catch (Exception unused) {
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            Request request = null;
            try {
                request = new Request.Builder().a("GET").b(b(jSONObject)).a();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            if (request != null) {
                HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                    public void onSuccess(Object obj, Response response) {
                    }

                    public void processFailure(Call call, IOException iOException) {
                    }

                    public void processResponse(Response response) {
                        JSONObject optJSONObject;
                        JSONObject jSONObject;
                        try {
                            DeviceListSwitchManager.this.d = true;
                            String string = response.body().string();
                            if (!TextUtils.isEmpty(string)) {
                                JSONObject jSONObject2 = new JSONObject(string);
                                if (jSONObject2.isNull("result") || (optJSONObject = jSONObject2.optJSONObject("result")) == null) {
                                    return;
                                }
                                if (!optJSONObject.isNull("content")) {
                                    Object obj = optJSONObject.get("content");
                                    if (obj instanceof JSONObject) {
                                        jSONObject = (JSONObject) obj;
                                    } else {
                                        jSONObject = obj instanceof String ? new JSONObject((String) obj) : null;
                                    }
                                    DeviceListSwitchData unused = DeviceListSwitchManager.this.m = DeviceListSwitchData.a(jSONObject);
                                    if (!jSONObject.isNull("data")) {
                                        DeviceListSwitchManager.this.a(jSONObject);
                                        if (DeviceListSwitchManager.this.c.isEmpty()) {
                                            DeviceListSwitchManager.this.a((List<String>) null, (AsyncCallback<Void, Error>) null);
                                        }
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                        DeviceListSwitchManager.this.d = true;
                    }
                });
            }
        }
    }

    @NonNull
    private String b(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    /* access modifiers changed from: private */
    public void d(final String str) {
        Runnable runnable;
        WeakReference weakReference = this.v.get(str);
        if (!(weakReference == null || (runnable = (Runnable) weakReference.get()) == null)) {
            this.r.removeCallbacks(runnable);
        }
        AnonymousClass12 r0 = new Runnable() {
            public void run() {
                DeviceListSwitchManager.this.v.remove(str);
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                SmartHomeDeviceManager.a().a((List<String>) arrayList, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(List<Device> list) {
                        DeviceListSwitchManager.this.r.postDelayed(new Runnable() {
                            public void run() {
                                Device b = SmartHomeDeviceManager.a().b(str);
                                if (!(b == null || b.property == null)) {
                                    b.property.putString(DeviceListSwitchManager.f15515a, "");
                                }
                                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceOnTask.f22060a));
                            }
                        }, 1000);
                    }
                });
            }
        };
        this.v.put(str, new WeakReference(r0));
        this.r.postDelayed(r0, 3000);
    }

    public void j() {
        ArrayList<Device> arrayList = new ArrayList<>();
        List<Device> k2 = SmartHomeDeviceManager.a().k();
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (k2 != null) {
            arrayList.addAll(k2);
        }
        if (d2 != null) {
            arrayList.addAll(d2);
        }
        for (Device device : arrayList) {
            if (!(device == null || device.property == null)) {
                device.property.putString(f15515a, "");
            }
        }
    }

    public Map<String, String> b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.c.get(str);
    }

    public void a(final String str, JSONArray jSONArray) {
        if (!TextUtils.isEmpty(str) && jSONArray != null && jSONArray.length() != 0) {
            List<Device> d2 = SmartHomeDeviceManager.a().d();
            List<Device> k2 = SmartHomeDeviceManager.a().k();
            ArrayList arrayList = new ArrayList(d2);
            arrayList.addAll(k2);
            if (arrayList.size() != 0) {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    Device device = (Device) arrayList.get(i2);
                    if (device != null) {
                        boolean equals = TextUtils.equals(str, device.did);
                    }
                }
                Map map = this.c.get(str);
                if (map == null) {
                    map = new HashMap();
                    this.c.put(str, map);
                }
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                    if (optJSONObject != null && !optJSONObject.isNull("key") && !optJSONObject.isNull("value")) {
                        String optString = optJSONObject.optString("key");
                        if (!TextUtils.isEmpty(optString) && optString.startsWith("prop.")) {
                            String substring = optString.substring("prop.".length());
                            Object opt = optJSONObject.opt("value");
                            if ((opt instanceof JSONArray) && ((JSONArray) opt).length() > 0) {
                                try {
                                    opt = ((JSONArray) opt).getString(0);
                                } catch (JSONException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            String str2 = i;
                            Log.d(str2, "did=" + str + ",key=" + substring + ",value=" + opt.toString());
                            map.put(substring, opt.toString());
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(str);
                SmartHomeDeviceManager.a().a((List<String>) arrayList2, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(List<Device> list) {
                        Device b2 = SmartHomeDeviceManager.a().b(str);
                        SmartHomeDeviceManager.a().a(b2);
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                        if (b2 != null) {
                            for (GridViewData next : HomeManager.a().F()) {
                                if (next.f18311a == GridViewData.GridType.TYPE_NORMAL && next.b.did.equals(b2.did)) {
                                    Intent intent = new Intent();
                                    intent.setAction("com.xiaomi.smarthome.refresh_device");
                                    intent.putExtra("packagename", "com.miui.home");
                                    intent.putExtra(LoginTask.f22078a, CoreApi.a().s());
                                    intent.putExtra(GetDeviceTask.c, GetDeviceTask.a(next));
                                    SHApplication.getAppContext().sendBroadcast(intent);
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public int c(String str) {
        ModelOperations modelOperations;
        Map<String, ModelOperations> b2 = this.m.b();
        if (b2 == null || (modelOperations = b2.get(str)) == null) {
            return 0;
        }
        return modelOperations.d();
    }
}
