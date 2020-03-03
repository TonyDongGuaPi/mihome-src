package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GatewaySupportManger {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f20503a = "gateway_config_content";
    /* access modifiers changed from: private */
    public static String b = "gateway_config_url";
    private static final String c = "GatewaySupportManger";
    private static final String d = "gateway_control_config_";
    private static final int e = 100;
    private static final int f = 200;
    private static final int g = 300;
    private static final int h = 400;
    private static final int i = 500;
    private static final int j = 600;
    private static final int k = 700;
    private SharedPreferences l;
    private HandlerThread m;
    /* access modifiers changed from: private */
    public WorkerHandler n;
    /* access modifiers changed from: private */
    public final Object o;
    /* access modifiers changed from: private */
    public final List<GatewaySupportListOfCompany> p;
    /* access modifiers changed from: private */
    public final Map<String, List<String>> q;
    /* access modifiers changed from: private */
    public volatile boolean r;
    /* access modifiers changed from: private */
    public List<OnGatewaySupportListener> s;

    public interface OnGatewaySupportListener {
        void a();

        void b();
    }

    public void a() {
        if (!this.r) {
            LogUtilGrey.a(c, "init loadConfigFromServer");
            g();
        }
    }

    public void b() {
        g();
    }

    public boolean c() {
        boolean z;
        synchronized (this.o) {
            z = this.r;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.xiaomi.smarthome.device.Device r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.o
            monitor-enter(r0)
            boolean r1 = r4.r     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0054
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r1 = r4.q     // Catch:{ all -> 0x005c }
            java.lang.String r2 = r5.model     // Catch:{ all -> 0x005c }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x005c }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x005c }
            r2 = 1
            if (r1 == 0) goto L_0x0022
            int r3 = r1.size()     // Catch:{ all -> 0x005c }
            if (r3 <= 0) goto L_0x0022
            boolean r1 = r1.contains(r6)     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            return r2
        L_0x0022:
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x005c }
            java.lang.String r5 = r5.model     // Catch:{ all -> 0x005c }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r5 = r1.d((java.lang.String) r5)     // Catch:{ all -> 0x005c }
            com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x005c }
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r6 = r1.d((java.lang.String) r6)     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0051
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r5 = r5.c()     // Catch:{ all -> 0x005c }
            r1 = 8
            boolean r5 = r5.t(r1)     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0051
            if (r6 == 0) goto L_0x0051
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r5 = r6.c()     // Catch:{ all -> 0x005c }
            int r5 = r5.e()     // Catch:{ all -> 0x005c }
            r6 = 14
            if (r5 != r6) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r2 = 0
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            return r2
        L_0x0054:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x005c }
            java.lang.String r6 = "please wait data ready"
            r5.<init>(r6)     // Catch:{ all -> 0x005c }
            throw r5     // Catch:{ all -> 0x005c }
        L_0x005c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.GatewaySupportManger.a(com.xiaomi.smarthome.device.Device, java.lang.String):boolean");
    }

    public List<String> a(String str) {
        return this.q.get(str);
    }

    private GatewaySupportManger() {
        this.m = new MessageHandlerThread(c);
        this.o = new Object();
        this.p = new ArrayList();
        this.q = new HashMap();
        this.r = false;
        this.s = new ArrayList();
        this.m.start();
        this.n = new WorkerHandler(this.m.getLooper());
        if (CoreApi.a().l()) {
            Context appContext = SHApplication.getAppContext();
            this.l = appContext.getSharedPreferences(d + CoreApi.a().s(), 0);
        }
        a();
    }

    /* access modifiers changed from: private */
    public void g() {
        Request request;
        final String string = i().getString(b, "");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "en");
            StringBuilder sb = new StringBuilder();
            sb.append("android_gateway_for_device_dict");
            sb.append(GlobalSetting.E ? "_preview" : "");
            jSONObject.put("name", sb.toString());
            jSONObject.put("result_level", "1");
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        try {
            request = new Request.Builder().a("GET").b(b(jSONObject)).a();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            request = null;
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
                    try {
                        String optString = new JSONObject(response.body().string()).optJSONObject("result").optString("file_url");
                        if (TextUtils.isEmpty(string)) {
                            GatewaySupportManger.this.b(optString);
                            LogUtilGrey.a(GatewaySupportManger.c, "load server urlnull " + response.request().url());
                        } else if (!string.equals(optString) || GatewaySupportManger.this.p.size() <= 0 || GatewaySupportManger.this.q.size() <= 0) {
                            GatewaySupportManger.this.b(optString);
                            LogUtilGrey.a(GatewaySupportManger.c, "load server urlnotmatch " + response.request().url());
                        } else {
                            GatewaySupportManger.this.h();
                            LogUtilGrey.a(GatewaySupportManger.c, "load cache " + response.request().url());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        LogUtilGrey.a(c, "loadConfigFromLocal start");
        String string = i().getString(f20503a, "");
        try {
            if (string.length() > 0) {
                a(new JSONObject(string));
                synchronized (this.o) {
                    if (this.p.size() > 0 && this.q.size() > 0) {
                        LogUtilGrey.a(c, "loadConfigFromLocal success");
                        this.r = true;
                        this.n.sendEmptyMessage(400);
                        this.n.sendEmptyMessage(500);
                        return;
                    }
                }
            }
        } catch (Exception e2) {
            try {
                LogUtilGrey.a(c, "loadConfigFromLocal exception:" + Log.getStackTraceString(e2));
            } catch (Throwable th) {
                this.n.sendEmptyMessage(500);
                throw th;
            }
        }
        this.n.sendEmptyMessage(500);
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        Request request;
        LogUtilGrey.a(c, "loadConfigContentFromServer start");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, "en");
            StringBuilder sb = new StringBuilder();
            sb.append("android_gateway_for_device_dict");
            sb.append(GlobalSetting.E ? "_preview" : "");
            jSONObject.put("name", sb.toString());
            jSONObject.put("version", "1");
        } catch (Exception unused) {
        }
        try {
            request = new Request.Builder().a("GET").b(b(jSONObject)).a();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            request = null;
        }
        if (request != null) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onFailure(Error error, Exception exc, Response response) {
                }

                public void onSuccess(Object obj, Response response) {
                }

                public void processResponse(Response response) {
                    try {
                        JSONObject b2 = GatewaySupportManger.this.c(response.body().string());
                        if (b2 != null) {
                            GatewaySupportManger.this.a(b2);
                            synchronized (GatewaySupportManger.this.o) {
                                if (GatewaySupportManger.this.p.size() <= 0 || GatewaySupportManger.this.q.size() <= 0) {
                                    LogUtilGrey.a(GatewaySupportManger.c, "loadConfigContentFromServer success nodata");
                                } else {
                                    LogUtilGrey.a(GatewaySupportManger.c, "loadConfigContentFromServer success");
                                    boolean unused = GatewaySupportManger.this.r = true;
                                    SharedPreferences.Editor edit = GatewaySupportManger.this.i().edit();
                                    edit.putString(GatewaySupportManger.f20503a, b2.toString());
                                    edit.putString(GatewaySupportManger.b, str);
                                    edit.apply();
                                    GatewaySupportManger.this.n.sendEmptyMessage(600);
                                    GatewaySupportManger.this.n.sendEmptyMessage(700);
                                    return;
                                }
                            }
                        }
                    } catch (Exception e) {
                        try {
                            LogUtilGrey.a(GatewaySupportManger.c, "loadConfigContentFromServer exception:" + Log.getStackTraceString(e));
                        } catch (Throwable th) {
                            GatewaySupportManger.this.n.sendEmptyMessage(700);
                            throw th;
                        }
                    }
                    GatewaySupportManger.this.n.sendEmptyMessage(700);
                }

                public void processFailure(Call call, IOException iOException) {
                    LogUtilGrey.a(GatewaySupportManger.c, "loadConfigContentFromServer processFailure:" + Log.getStackTraceString(iOException));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public JSONObject c(String str) {
        Object opt;
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("result");
            if (optJSONObject == null || (opt = optJSONObject.opt("content")) == null) {
                return null;
            }
            if (opt instanceof JSONObject) {
                return (JSONObject) opt;
            }
            return new JSONObject((String) opt);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("companyDevices");
            if (jSONArray != null && jSONArray.length() > 0) {
                ArrayList<GatewaySupportListOfCompany> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    GatewaySupportListOfCompany c2 = c((JSONObject) jSONArray.get(i2));
                    if (c2 != null) {
                        arrayList.add(c2);
                    }
                }
                synchronized (this.o) {
                    this.p.clear();
                    this.p.addAll(arrayList);
                    for (GatewaySupportListOfCompany gatewaySupportListOfCompany : arrayList) {
                        Map a2 = gatewaySupportListOfCompany.d;
                        if (a2 != null) {
                            Map b2 = gatewaySupportListOfCompany.b;
                            if (b2 != null) {
                                for (String str : b2.keySet()) {
                                    List list = (List) b2.get(str);
                                    List<String> list2 = (List) a2.get(str);
                                    if (!(list2 == null || list == null)) {
                                        for (String put : list2) {
                                            this.q.put(put, list);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @NonNull
    private String b(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    private GatewaySupportListOfCompany c(JSONObject jSONObject) {
        Map map;
        try {
            String optString = jSONObject.optString(MibiConstants.fg);
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            JSONObject optJSONObject = jSONObject.optJSONObject("deviceGateway");
            if (optJSONObject != null) {
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArray = optJSONObject.getJSONArray(next);
                    if (jSONArray != null && jSONArray.length() > 0) {
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            String string = jSONArray.getString(i2);
                            if (!TextUtils.isEmpty(string)) {
                                arrayList2.add(string);
                            }
                        }
                        hashMap2.put(next, arrayList2);
                    }
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("miHomeSupport");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    String string2 = optJSONArray.getString(i3);
                    if (!TextUtils.isEmpty(string2)) {
                        arrayList.add(string2);
                    }
                }
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("group");
            if (optJSONObject2 != null) {
                Iterator<String> keys2 = optJSONObject2.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    ArrayList arrayList3 = new ArrayList();
                    JSONArray jSONArray2 = optJSONObject2.getJSONArray(next2);
                    if (jSONArray2 != null && jSONArray2.length() > 0) {
                        for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                            arrayList3.add(jSONArray2.getString(i4));
                        }
                    }
                    hashMap.put(next2, arrayList3);
                }
            }
            JSONObject optJSONObject3 = jSONObject.optJSONObject("ikeaVersion");
            if (optJSONObject3 != null) {
                Map arrayMap = Build.VERSION.SDK_INT >= 19 ? new ArrayMap() : new HashMap();
                Iterator<String> keys3 = optJSONObject3.keys();
                while (keys3.hasNext()) {
                    String next3 = keys3.next();
                    arrayMap.put(next3, optJSONObject3.optString(next3));
                }
                map = arrayMap;
            } else {
                map = null;
            }
            return new GatewaySupportListOfCompany(optString, hashMap2, arrayList, hashMap, map);
        } catch (JSONException e2) {
            Log.e(c, AppMeasurement.Param.FATAL, e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public SharedPreferences i() {
        if (this.l == null) {
            Context appContext = SHApplication.getAppContext();
            this.l = appContext.getSharedPreferences(d + CoreApi.a().s(), 0);
        }
        return this.l;
    }

    public boolean a(String str, Device device) {
        String str2;
        if (device == null) {
            return false;
        }
        for (GatewaySupportListOfCompany next : this.p) {
            if (!(next == null || next.c == null || !next.c.contains(str))) {
                try {
                    String optString = new JSONObject(device.extra).optString("fw_version");
                    if (next.e == null || (str2 = (String) next.e.get(device.model)) == null || DeviceUtils.a(optString, str2) >= 0) {
                        return true;
                    }
                    return false;
                } catch (Exception unused) {
                    Log.e(c, "nextActivity check version parse fail:" + device.extra);
                }
            }
        }
        return true;
    }

    private class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 100) {
                GatewaySupportManger.this.g();
            } else if (i == 200) {
                GatewaySupportManger.this.h();
            } else if (i == 300) {
                GatewaySupportManger.this.g();
            } else if (i == 400) {
                for (int size = GatewaySupportManger.this.s.size() - 1; size >= 0; size--) {
                    ((OnGatewaySupportListener) GatewaySupportManger.this.s.get(size)).a();
                }
            } else if (i == 600) {
                for (int size2 = GatewaySupportManger.this.s.size() - 1; size2 >= 0; size2--) {
                    ((OnGatewaySupportListener) GatewaySupportManger.this.s.get(size2)).b();
                }
            }
        }
    }

    public boolean a(OnGatewaySupportListener onGatewaySupportListener) {
        if (onGatewaySupportListener != null) {
            return this.s.add(onGatewaySupportListener);
        }
        return false;
    }

    public boolean b(OnGatewaySupportListener onGatewaySupportListener) {
        return this.s.remove(onGatewaySupportListener);
    }

    public static GatewaySupportManger d() {
        return InstanceHolder.f20507a;
    }

    private static class InstanceHolder {

        /* renamed from: a  reason: collision with root package name */
        static GatewaySupportManger f20507a = new GatewaySupportManger();

        private InstanceHolder() {
        }
    }

    public static final class GatewaySupportListOfCompany {

        /* renamed from: a  reason: collision with root package name */
        private final String f20506a;
        /* access modifiers changed from: private */
        public final Map<String, List<String>> b;
        /* access modifiers changed from: private */
        public final List<String> c;
        /* access modifiers changed from: private */
        public final Map<String, List<String>> d;
        /* access modifiers changed from: private */
        public final Map<String, String> e;

        public GatewaySupportListOfCompany(String str, Map<String, List<String>> map, List<String> list, Map<String, List<String>> map2, Map<String, String> map3) {
            this.f20506a = str;
            this.b = map;
            this.c = list;
            this.d = map2;
            this.e = map3;
        }
    }
}
