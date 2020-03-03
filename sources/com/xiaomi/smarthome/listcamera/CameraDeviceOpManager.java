package com.xiaomi.smarthome.listcamera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.operation.DeviceControl;
import com.xiaomi.smarthome.listcamera.operation.MultiButtonDeviceControl;
import com.xiaomi.smarthome.listcamera.operation.Param;
import com.xiaomi.smarthome.miio.camera.CameraDeviceDataManager;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.youpin.login.api.LoginErrorCode;
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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraDeviceOpManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19211a = "CameraDeviceOpManager";
    private static final int b = 1;
    private static CameraDeviceOpManager c = null;
    private static final String e = "camera_op_config";
    private static final String f = "device_prop_config";
    private DevicePropSubscriber d;
    private HashMap<String, List<DeviceControl>> g = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<String, List<DeviceControl>> h = new HashMap<>();
    private Map<String, Map<String, Object>> i = new ConcurrentHashMap();
    private Map<String, Map<String, Object>> j = new ConcurrentHashMap();
    private Map<String, DeviceDesc> k = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, DeviceDesc> l = new ConcurrentHashMap();
    private Map<String, ColorGradient> m = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, ColorGradient> n = new ConcurrentHashMap();
    private Map<String, Long> o = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, Long> p = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public SharedPreferences q;
    /* access modifiers changed from: private */
    public Handler r = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            DataPackage dataPackage;
            if (message.what == 1 && (dataPackage = (DataPackage) message.obj) != null) {
                CameraDeviceOpManager.this.h.clear();
                CameraDeviceOpManager.this.h.putAll(dataPackage.f19221a);
                CameraDeviceOpManager.this.p.clear();
                CameraDeviceOpManager.this.p.putAll(dataPackage.c);
                CameraDeviceOpManager.this.n.clear();
                CameraDeviceOpManager.this.n.putAll(dataPackage.b);
                CameraDeviceOpManager.this.l.clear();
                CameraDeviceOpManager.this.l.putAll(dataPackage.d);
            }
        }
    };
    private Map<String, WeakReference<Runnable>> s = new ConcurrentHashMap();

    public static class ColorGradient {

        /* renamed from: a  reason: collision with root package name */
        public String f19220a;
        public String b;
    }

    public void g() {
    }

    public static CameraDeviceOpManager a() {
        if (c == null) {
            c = new CameraDeviceOpManager();
        }
        return c;
    }

    private CameraDeviceOpManager() {
        Context appContext = SHApplication.getAppContext();
        this.q = appContext.getSharedPreferences("camera_op_" + CoreApi.a().s(), 0);
    }

    private static class DataPackage {

        /* renamed from: a  reason: collision with root package name */
        HashMap<String, List<DeviceControl>> f19221a;
        Map<String, ColorGradient> b;
        Map<String, Long> c;
        Map<String, DeviceDesc> d;

        private DataPackage() {
            this.f19221a = new HashMap<>();
            this.b = new ConcurrentHashMap();
            this.c = new ConcurrentHashMap();
            this.d = new HashMap();
        }
    }

    private static class DeviceDesc {

        /* renamed from: a  reason: collision with root package name */
        String f19222a;
        Map<Object, Map<String, String>> b;

        private DeviceDesc() {
            this.b = new HashMap();
        }
    }

    public Map<String, Object> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.i.get(str);
    }

    public ColorGradient b(String str) {
        return this.m.get(str);
    }

    public List<DeviceControl> c(String str) {
        return this.g.get(str);
    }

    public void b() {
        this.g.clear();
        this.g.putAll(this.h);
        this.i.clear();
        this.i.putAll(this.j);
        this.o.clear();
        this.o.putAll(this.p);
        this.m.clear();
        this.m.putAll(this.n);
        this.k.clear();
        this.k.putAll(this.l);
    }

    /* access modifiers changed from: package-private */
    public String c() {
        if (!CoreApi.a().q()) {
            return e;
        }
        return CoreApi.a().s() + JSMethod.NOT_SET + e;
    }

    /* access modifiers changed from: package-private */
    public String d() {
        if (!CoreApi.a().q()) {
            return f;
        }
        return CoreApi.a().s() + JSMethod.NOT_SET + f;
    }

    public void e() {
        DataPackage dataPackage;
        String string = this.q.getString(c(), "");
        if (string.length() > 0) {
            try {
                dataPackage = a(new JSONObject(string));
            } catch (JSONException e2) {
                e2.printStackTrace();
                dataPackage = null;
            }
            this.r.sendMessage(this.r.obtainMessage(1, dataPackage));
        }
        j();
    }

    public Map<String, Long> f() {
        return this.o;
    }

    public boolean a(Device device) {
        return this.g.containsKey(device.model);
    }

    public void a(final AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "zh_CN");
            StringBuilder sb = new StringBuilder();
            sb.append("camera_device_list_config");
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

                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: org.json.JSONObject} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void processResponse(okhttp3.Response r4) {
                    /*
                        r3 = this;
                        okhttp3.ResponseBody r4 = r4.body()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        java.lang.String r4 = r4.string()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r0.<init>(r4)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        java.lang.String r4 = "result"
                        boolean r4 = r0.isNull(r4)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        if (r4 == 0) goto L_0x0016
                        return
                    L_0x0016:
                        java.lang.String r4 = "result"
                        org.json.JSONObject r4 = r0.optJSONObject(r4)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        java.lang.String r0 = "content"
                        boolean r0 = r4.isNull(r0)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        if (r0 == 0) goto L_0x0025
                        return
                    L_0x0025:
                        r0 = 0
                        java.lang.String r1 = "content"
                        java.lang.Object r4 = r4.get(r1)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        boolean r1 = r4 instanceof org.json.JSONObject     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        if (r1 == 0) goto L_0x0034
                        r0 = r4
                        org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        goto L_0x003f
                    L_0x0034:
                        boolean r1 = r4 instanceof java.lang.String     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        if (r1 == 0) goto L_0x003f
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r0.<init>(r4)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                    L_0x003f:
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r4 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.this     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        android.content.SharedPreferences r4 = r4.q     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r1 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.this     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        java.lang.String r1 = r1.c()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        java.lang.String r2 = r0.toString()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r4.putString(r1, r2)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r4.commit()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r4 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.this     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager$DataPackage r4 = r4.a((org.json.JSONObject) r0)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r0 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.this     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        android.os.Handler r0 = r0.r     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r1 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.this     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        android.os.Handler r1 = r1.r     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r2 = 1
                        android.os.Message r4 = r1.obtainMessage(r2, r4)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r0.sendMessage(r4)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager r4 = com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.this     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        android.os.Handler r4 = r4.r     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        com.xiaomi.smarthome.listcamera.CameraDeviceOpManager$2$1 r0 = new com.xiaomi.smarthome.listcamera.CameraDeviceOpManager$2$1     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r0.<init>()     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        r4.post(r0)     // Catch:{ IOException -> 0x0087, JSONException -> 0x0082 }
                        goto L_0x008b
                    L_0x0082:
                        r4 = move-exception
                        r4.printStackTrace()
                        goto L_0x008b
                    L_0x0087:
                        r4 = move-exception
                        r4.printStackTrace()
                    L_0x008b:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.listcamera.CameraDeviceOpManager.AnonymousClass2.processResponse(okhttp3.Response):void");
                }

                public void processFailure(Call call, IOException iOException) {
                    CameraDeviceOpManager.this.r.post(new Runnable() {
                        public void run() {
                            asyncCallback.onFailure(null);
                        }
                    });
                }

                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                    CameraDeviceOpManager.this.r.post(new Runnable() {
                        public void run() {
                            asyncCallback.onFailure(null);
                        }
                    });
                }
            });
        }
    }

    @NonNull
    private String b(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public boolean a(MiioDeviceV2 miioDeviceV2, String str, Object obj) {
        List<DeviceControl> list = this.g.get(miioDeviceV2.model);
        if (list == null) {
            return false;
        }
        for (DeviceControl deviceControl : list) {
            if (deviceControl.a().equals(str) && this.i.get(miioDeviceV2.did) != null && deviceControl.a(this.i.get(miioDeviceV2.did).get(str), obj)) {
                return true;
            }
        }
        return false;
    }

    private JSONObject a(Device device, List<DeviceControl> list) {
        JSONObject jSONObject = new JSONObject();
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < list.size(); i2++) {
            DeviceControl deviceControl = list.get(i2);
            if (device.model.startsWith("lumi.sensor_magnet.")) {
                hashSet.add("event.open");
                hashSet.add("event.close");
            } else if (!TextUtils.isEmpty(deviceControl.a())) {
                hashSet.add(deviceControl.a());
            }
        }
        if (hashSet.size() > 0) {
            try {
                jSONObject.put("did", device.did);
                JSONArray jSONArray = new JSONArray();
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (device.model.startsWith("rockrobo.vacuum.v1")) {
                        jSONArray.put(Device.EVENT_PREFIX + str);
                    } else {
                        jSONArray.put("prop." + str);
                    }
                }
                jSONObject.put("props", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    public void b(final AsyncCallback<Void, Error> asyncCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            Locale I = CoreApi.a().I();
            if (I != null) {
                jSONObject.put("region", I.getCountry());
            } else {
                jSONObject.put("region", Locale.getDefault().getCountry());
            }
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            List<CameraGroupManager.GroupInfo> c2 = CameraGroupManager.a().c();
            final HashMap hashMap = new HashMap();
            for (CameraGroupManager.GroupInfo next : c2) {
                if (next != null && !TextUtils.isEmpty(next.f19240a)) {
                    hashMap.put(next.f19240a, next);
                    String str = SmartHomeDeviceManager.a().b(next.f19240a).model;
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("model", str);
                    jSONObject3.put("did", next.f19240a);
                    jSONArray.put(jSONObject3);
                }
            }
            if (jSONArray.length() > 0) {
                jSONObject2.put("modelPairs", jSONArray);
                jSONObject.put("modelPairs", jSONObject2);
                CameraDeviceDataManager.getInstance().loadAllCameraCloudStorageStatus(jSONObject.toString(), new CameraDeviceDataManager.ICameraDeviceDataCallback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject, Object obj) {
                        JSONObject optJSONObject;
                        JSONArray optJSONArray;
                        if (jSONObject != null) {
                            if (jSONObject.optInt("code", -1) == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null && (optJSONArray = optJSONObject.optJSONArray("didSupportInfos")) != null) {
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                    CameraGroupManager.GroupInfo groupInfo = (CameraGroupManager.GroupInfo) hashMap.get(optJSONObject2.optString("did"));
                                    if (groupInfo != null) {
                                        boolean optBoolean = optJSONObject2.optBoolean("cloudSupport");
                                        groupInfo.b = optJSONObject2.optBoolean("isVip");
                                        groupInfo.c = optBoolean;
                                    }
                                }
                            }
                        } else if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(LoginErrorCode.aI, "result is null"));
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (asyncCallback != null) {
                            LogUtil.b(CameraDeviceOpManager.f19211a, "loadAllCameraCloudStorageStatus onFailure:" + i + " errorInfo:" + str);
                            asyncCallback.onFailure(new Error(i, str));
                        }
                    }
                });
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-9005, "did or model array is empty"));
            }
        } catch (Exception e2) {
            LogUtil.b(f19211a, "Exception:" + e2.getLocalizedMessage());
            e2.printStackTrace();
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-9002, "exception:" + e2.getLocalizedMessage()));
            }
        }
    }

    public void c(AsyncCallback<Void, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        List<CameraGroupManager.GroupInfo> c2 = CameraGroupManager.a().c();
        for (int i2 = 0; i2 < c2.size(); i2++) {
            CameraGroupManager.GroupInfo groupInfo = c2.get(i2);
            if (groupInfo != null) {
                for (CameraGroupManager.GroupInfo.DeviceControlInfo deviceControlInfo : groupInfo.e) {
                    Device n2 = SmartHomeDeviceManager.a().n(deviceControlInfo.f19241a);
                    if (n2 != null) {
                        arrayList.add(n2);
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            a((List<Device>) arrayList, asyncCallback);
            return;
        }
        Log.e("AllCamera", "Empty");
        asyncCallback.onSuccess(null);
    }

    public String b(Device device) {
        Param param;
        Map<String, Object> a2;
        if (!this.k.containsKey(device.model)) {
            return "";
        }
        DeviceDesc deviceDesc = this.k.get(device.model);
        DeviceControl deviceControl = null;
        Iterator it = this.g.get(device.model).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceControl deviceControl2 = (DeviceControl) it.next();
            if (deviceControl2.a().equals(deviceDesc.f19222a)) {
                deviceControl = deviceControl2;
                break;
            }
        }
        if (deviceControl == null || !(deviceControl instanceof MultiButtonDeviceControl) || (param = ((MultiButtonDeviceControl) deviceControl).f19318a) == null || (a2 = a().a(device.did)) == null || !a2.containsKey(deviceDesc.f19222a)) {
            return "";
        }
        param.a(a2.get(deviceDesc.f19222a));
        for (Object next : deviceDesc.b.keySet()) {
            if (param.c(next)) {
                return DeviceControl.a(deviceDesc.b.get(next));
            }
        }
        return "";
    }

    public void a(final List<Device> list, final AsyncCallback<Void, Error> asyncCallback) {
        List list2;
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Device device = list.get(i2);
            if (!(device == null || (list2 = this.g.get(device.model)) == null)) {
                jSONArray.put(a(device, (List<DeviceControl>) list2));
            }
        }
        if (jSONArray.length() > 0) {
            DevicelibApi.batchGetDeviceProps(SHApplication.getAppContext(), jSONArray, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    ArrayList arrayList = new ArrayList();
                    for (Device device : list) {
                        arrayList.add(device.did);
                    }
                    CameraDeviceOpManager.this.a(str, (List<String>) arrayList, false);
                    asyncCallback.onSuccess(null);
                }

                public void onFailure(Error error) {
                    asyncCallback.onSuccess(null);
                }
            }, CameraDeviceOpManager.class.getSimpleName());
            return;
        }
        asyncCallback.onSuccess(null);
    }

    private void i() {
        Set<String> keySet = this.i.keySet();
        JSONArray jSONArray = new JSONArray();
        for (String next : keySet) {
            JSONObject jSONObject = new JSONObject();
            try {
                Map map = this.i.get(next);
                if (map != null) {
                    Set<String> keySet2 = map.keySet();
                    JSONObject jSONObject2 = new JSONObject();
                    for (String str : keySet2) {
                        jSONObject2.put(str, map.get(str));
                    }
                    jSONObject.put(next, jSONObject2);
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        SharedPreferences.Editor edit = this.q.edit();
        edit.putString(d(), jSONArray.toString());
        edit.commit();
    }

    private void j() {
        String string = this.q.getString(d(), "");
        this.j.clear();
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    Iterator<String> keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject(next);
                        HashMap hashMap = new HashMap();
                        Iterator<String> keys2 = optJSONObject2.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            hashMap.put(next2, optJSONObject2.opt(next2));
                        }
                        this.j.put(next, hashMap);
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, List<String> list, boolean z) {
        Device n2;
        Map<String, Map<String, Object>> concurrentHashMap = new ConcurrentHashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            if (list != null && list.size() > 0) {
                concurrentHashMap = this.i;
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
                        } else if (next2.startsWith(Device.EVENT_PREFIX)) {
                            next2 = next2.substring(Device.EVENT_PREFIX.length());
                        }
                        if (obj != null) {
                            if (!obj.toString().equals("null")) {
                                hashMap.put(next2, "" + obj);
                            }
                        }
                    }
                    Log.e("device_rpc", "get props, did - " + next + ", " + hashMap.toString());
                    concurrentHashMap.put(next, hashMap);
                }
            }
            this.i = concurrentHashMap;
            HashSet hashSet = new HashSet();
            HashMap hashMap2 = new HashMap();
            List<CameraGroupManager.GroupInfo> c2 = CameraGroupManager.a().c();
            if (c2 != null) {
                for (CameraGroupManager.GroupInfo groupInfo : c2) {
                    for (CameraGroupManager.GroupInfo.DeviceControlInfo next3 : groupInfo.e) {
                        if (!hashMap2.containsKey(next3.f19241a) && (n2 = SmartHomeDeviceManager.a().n(next3.f19241a)) != null) {
                            hashMap2.put(next3.f19241a, n2);
                        }
                    }
                }
                hashSet.addAll(hashMap2.values());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(hashSet);
                hashSet.clear();
                if (this.d == null) {
                    this.d = new DevicePropSubscriber();
                }
                this.d.a((List<Device>) arrayList, (DevicePropSubscriber.DeviceSubscriberInterface) new DevicePropSubscriber.DeviceSubscriberInterface() {
                    public List<String> a(String str) {
                        Map<String, Object> a2 = CameraDeviceOpManager.this.a(str);
                        if (a2 == null || a2.size() == 0) {
                            return null;
                        }
                        return new ArrayList(a2.keySet());
                    }

                    public void a(String str, JSONArray jSONArray) {
                        CameraDeviceOpManager.this.a(str, jSONArray);
                    }

                    public JSONArray b(String str) {
                        JSONArray jSONArray = new JSONArray();
                        Map<String, Object> a2 = CameraDeviceOpManager.this.a(str);
                        if (a2 != null) {
                            for (Map.Entry next : a2.entrySet()) {
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
                if (!z) {
                    i();
                }
            }
        } catch (JSONException unused) {
        }
    }

    public void a(String str, String str2, Object obj) {
        Map map = this.i.get(str);
        if (map != null) {
            map.put(str2, obj);
        }
    }

    public void a(String str, JSONArray jSONArray) {
        Log.e("device_rpc", str + " prop changed, " + jSONArray.toString());
        Map map = this.i.get(str);
        if (map == null) {
            map = new HashMap();
            this.i.put(str, map);
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null && !optJSONObject.isNull("key") && !optJSONObject.isNull("value")) {
                String optString = optJSONObject.optString("key");
                if (!TextUtils.isEmpty(optString) && optString.startsWith("prop.")) {
                    String substring = optString.substring("prop.".length());
                    Object opt = optJSONObject.opt("value");
                    if ((opt instanceof JSONArray) && ((JSONArray) opt).length() > 0) {
                        try {
                            opt = ((JSONArray) opt).get(0);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                    map.put(substring, opt);
                }
            }
        }
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(AllCameraPage.c));
    }

    /* access modifiers changed from: package-private */
    public DataPackage a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        AnonymousClass1 r2 = null;
        DataPackage dataPackage = new DataPackage();
        dataPackage.f19221a = new HashMap<>();
        dataPackage.b = new HashMap();
        dataPackage.c = new ConcurrentHashMap();
        dataPackage.d = new HashMap();
        try {
            if (!jSONObject2.optBoolean("enabled")) {
                return null;
            }
            JSONArray optJSONArray = jSONObject2.optJSONArray("support_camera_model_list");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                dataPackage.c.put(optJSONObject.optString("model"), Long.valueOf(optJSONObject.optLong("min_support_version_code")));
            }
            JSONArray optJSONArray2 = jSONObject2.optJSONArray("data");
            int i3 = 0;
            while (i3 < optJSONArray2.length()) {
                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i3);
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList arrayList2 = new ArrayList();
                JSONArray optJSONArray3 = optJSONObject2.optJSONArray("model");
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    arrayList.add(optJSONArray3.optString(i4));
                }
                if (optJSONObject2.has("desc")) {
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("desc");
                    DeviceDesc deviceDesc = new DeviceDesc();
                    deviceDesc.f19222a = optJSONObject3.optString("show_op_name");
                    JSONArray optJSONArray4 = optJSONObject3.optJSONArray("show_values");
                    for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                        JSONObject optJSONObject4 = optJSONArray4.optJSONObject(i5);
                        Object opt = optJSONObject4.opt("value");
                        JSONObject optJSONObject5 = optJSONObject4.optJSONObject("name");
                        HashMap hashMap = new HashMap();
                        Iterator<String> keys = optJSONObject5.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            hashMap.put(next, optJSONObject5.optString(next));
                        }
                        deviceDesc.b.put(opt, hashMap);
                    }
                    for (String put : arrayList) {
                        dataPackage.d.put(put, deviceDesc);
                    }
                }
                ColorGradient colorGradient = new ColorGradient();
                colorGradient.f19220a = optJSONObject2.optString("from_color");
                colorGradient.b = optJSONObject2.optString("to_color");
                for (String put2 : arrayList) {
                    dataPackage.b.put(put2, colorGradient);
                }
                JSONArray optJSONArray5 = optJSONObject2.optJSONArray("operations");
                for (int i6 = 0; i6 < optJSONArray5.length(); i6++) {
                    DeviceControl a2 = DeviceControl.a(optJSONArray5.optJSONObject(i6));
                    if (a2 != null) {
                        arrayList2.add(a2);
                    }
                }
                for (String put3 : arrayList) {
                    dataPackage.f19221a.put(put3, arrayList2);
                }
                i3++;
                r2 = null;
            }
            return dataPackage;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void h() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }
}
