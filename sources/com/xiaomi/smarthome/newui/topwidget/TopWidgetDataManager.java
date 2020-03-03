package com.xiaomi.smarthome.newui.topwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.UserConfigKeyManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.OnStateChangedListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopWidgetDataManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20711a = "TopWidgetDataManager";
    public static final String b = "action_top_widget_data_changed";
    public static final String c = "source";
    public static final String d = "server_source";
    public static final String e = "setting_source";
    public static final String f = "family_source";
    public static final String g = "default_weather_did";
    public static final String h = "default_weather_model";
    public static final String i = "empty_home_id";
    public static final String j = "temp";
    public static final String k = "aqi";
    public static final String l = "tds";
    public static final String m = "humidity";
    private static final String n = "default_weather_did_temp";
    private static final String o = "default_weather_did_aqi";
    private static final String p = "default_weather_did_tds";
    private static final String q = "default_weather_did_humidity";
    private static volatile TopWidgetDataManager v;
    /* access modifiers changed from: private */
    public Map<String, List<TopWidgetData>> A = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public boolean B = false;
    private final List<TopWidgetDataChangerListener> C = Collections.synchronizedList(new ArrayList());
    private final OnStateChangedListener D = new OnStateChangedListener() {
        public final void onStateChanged(String str, String str2, Object obj) {
            TopWidgetDataManager.this.a(str, str2, obj);
        }
    };
    private BroadcastReceiver E = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                char c = 65535;
                switch (action.hashCode()) {
                    case -1323026591:
                        if (action.equals(ControlCardInfoManager.d)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -56322889:
                        if (action.equals(HomeManager.t)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 456399064:
                        if (action.equals(HomeManager.u)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1668330438:
                        if (action.equals(ControlCardInfoManager.f)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1744408299:
                        if (action.equals(AreaInfoManager.f11897a)) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                    case 1:
                        LogUtil.a(TopWidgetDataManager.f20711a, "device card changed: " + intent.getAction());
                        TopWidgetDataManager.this.f();
                        TopWidgetDataManager.this.e();
                        TopWidgetDataManager.this.f(TopWidgetDataManager.d);
                        return;
                    case 2:
                        LogUtil.a(TopWidgetDataManager.f20711a, "area info changed: " + intent.getAction());
                        TopWidgetDataManager.this.c();
                        TopWidgetDataManager.this.f(TopWidgetDataManager.d);
                        return;
                    case 3:
                    case 4:
                        LogUtil.a(TopWidgetDataManager.f20711a, "home changed: " + intent.getAction());
                        TopWidgetDataManager.this.c();
                        TopWidgetDataManager.this.f();
                        TopWidgetDataManager.this.e();
                        TopWidgetDataManager.this.f(TopWidgetDataManager.d);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private BroadcastReceiver F = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"action_on_login_success".equals(action) && "action_on_logout".equals(action)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(TopWidgetDataManager.n);
                arrayList.add(TopWidgetDataManager.o);
                arrayList.add(TopWidgetDataManager.p);
                arrayList.add(TopWidgetDataManager.q);
                TopWidgetDataManager.this.y.clear();
                TopWidgetDataManager.this.y.put(TopWidgetDataManager.i, arrayList);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(TopWidgetDataManager.this.r);
                arrayList2.add(TopWidgetDataManager.this.s);
                arrayList2.add(TopWidgetDataManager.this.t);
                arrayList2.add(TopWidgetDataManager.this.u);
                TopWidgetDataManager.this.A.clear();
                TopWidgetDataManager.this.A.put(TopWidgetDataManager.i, arrayList2);
                TopWidgetDataManager.this.z.clear();
                TopWidgetDataManager.this.z.put(TopWidgetDataManager.i, arrayList2);
                boolean unused = TopWidgetDataManager.this.B = false;
            }
        }
    };
    /* access modifiers changed from: private */
    public TopWidgetData r;
    /* access modifiers changed from: private */
    public TopWidgetData s;
    /* access modifiers changed from: private */
    public TopWidgetData t;
    /* access modifiers changed from: private */
    public TopWidgetData u;
    private Context w;
    private final Object x = new Object();
    /* access modifiers changed from: private */
    public Map<String, List<String>> y = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, List<TopWidgetData>> z = new ConcurrentHashMap();

    public interface TopWidgetDataChangerListener {
        void a(String str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, Object obj) {
        LogUtil.a(f20711a, "device card onStateChanged");
        f();
        e();
        f(d);
    }

    private TopWidgetDataManager(Context context) {
        this.w = context.getApplicationContext();
        b();
        f();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ControlCardInfoManager.d);
        intentFilter.addAction(ControlCardInfoManager.f);
        intentFilter.addAction(AreaInfoManager.f11897a);
        intentFilter.addAction(HomeManager.t);
        intentFilter.addAction(HomeManager.u);
        LocalBroadcastManager.getInstance(this.w).registerReceiver(this.E, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("action_on_login_success");
        intentFilter2.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(this.w).registerReceiver(this.F, intentFilter2);
        ControlCardInfoManager.f().a(this.D);
        MiotSpecCardManager.f().a(this.D);
    }

    public static TopWidgetDataManager a() {
        if (v == null) {
            synchronized (TopWidgetDataManager.class) {
                if (v == null) {
                    v = new TopWidgetDataManager(SHApplication.getApplication());
                }
            }
        }
        return v;
    }

    private void b() {
        AreaInfoManager a2 = AreaInfoManager.a();
        String l2 = a2.l();
        this.r = new TopWidgetData(h, g, "", l2, "temp", a2.w(), a2.A(), "℃", true);
        this.r.j = g(a2.A());
        this.s = new TopWidgetData(h, g, "", l2, "aqi", this.w.getString(R.string.air_quality_outside), a2.r(), "AQI", true);
        this.s.j = h(a2.r());
        this.t = new TopWidgetData(h, g, "", l2, l, this.w.getString(R.string.water_quality_purified_outside), a2.t(), "TDS", true);
        this.t.j = i(a2.t());
        this.u = new TopWidgetData(h, g, "", l2, m, this.w.getString(R.string.top_widget_humidity), g(), Operators.MOD, true);
        this.u.j = j(a2.C());
        ArrayList arrayList = new ArrayList();
        arrayList.add(n);
        arrayList.add(o);
        arrayList.add(p);
        arrayList.add(q);
        this.y.put(i, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.r);
        arrayList2.add(this.s);
        arrayList2.add(this.t);
        arrayList2.add(this.u);
        this.A.put(i, arrayList2);
        e();
    }

    /* access modifiers changed from: private */
    public void c() {
        AreaInfoManager a2 = AreaInfoManager.a();
        String l2 = AreaInfoManager.a().l();
        this.r.e = l2;
        this.r.g = a2.w();
        this.r.h = a2.A();
        this.r.j = g(a2.A());
        this.s.e = l2;
        this.s.g = this.w.getString(R.string.air_quality_outside);
        this.s.h = a2.r();
        this.s.j = h(a2.r());
        this.t.e = l2;
        this.t.g = this.w.getString(R.string.water_quality_purified_outside);
        this.t.h = a2.t();
        this.t.j = i(a2.t());
        this.u.e = l2;
        this.u.g = this.w.getString(R.string.top_widget_humidity);
        this.u.h = g();
        this.u.j = j(a2.C());
    }

    public void a(boolean z2) {
        LogUtil.a(f20711a, "update needNotify = " + z2);
        c();
        d();
        if (z2) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    LogUtil.a(TopWidgetDataManager.f20711a, "update");
                    TopWidgetDataManager.this.f(TopWidgetDataManager.d);
                }
            }, 3000);
        }
    }

    private void d() {
        LogUtil.a(f20711a, "getPropSortFromServer");
        if (new ArrayList(HomeManager.a().f()).size() == 0) {
            a((AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(String str) {
                    TopWidgetDataManager.this.y.put(TopWidgetDataManager.i, TopWidgetDataManager.this.d(str));
                    TopWidgetDataManager.this.e();
                    TopWidgetDataManager.this.f(TopWidgetDataManager.d);
                }
            });
        } else {
            b((AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(String str) {
                    boolean unused = TopWidgetDataManager.this.B = true;
                    if (TextUtils.isEmpty(str)) {
                        TopWidgetDataManager.this.a((AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                            public void onFailure(Error error) {
                            }

                            /* renamed from: a */
                            public void onSuccess(String str) {
                                TopWidgetDataManager.this.c(str);
                            }
                        });
                        return;
                    }
                    Map d = TopWidgetDataManager.this.e(str);
                    ArrayList arrayList = new ArrayList(HomeManager.a().f());
                    if (arrayList.size() > 0) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            Home home = (Home) arrayList.get(i);
                            if (home != null) {
                                List list = (List) d.get(home.j());
                                if (list != null) {
                                    TopWidgetDataManager.this.y.put(home.j(), list);
                                } else {
                                    ArrayList arrayList2 = new ArrayList();
                                    arrayList2.add(TopWidgetDataManager.n);
                                    arrayList2.add(TopWidgetDataManager.o);
                                    arrayList2.add(TopWidgetDataManager.p);
                                    arrayList2.add(TopWidgetDataManager.q);
                                    TopWidgetDataManager.this.y.put(home.j(), arrayList2);
                                }
                            }
                        }
                    }
                    TopWidgetDataManager.this.e();
                    TopWidgetDataManager.this.f(TopWidgetDataManager.d);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(final AsyncCallback<String, Error> asyncCallback) {
        LogUtil.a(f20711a, "getOldPropSort");
        UserConfigKeyManager.a().a(7, 3001, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(str);
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    private void b(final AsyncCallback<String, Error> asyncCallback) {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                LogUtil.a(TopWidgetDataManager.f20711a, "getMultiHomePropSort");
                UserConfigKeyManager.a().a(7, 4001, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess(str);
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        LogUtil.a(f20711a, "upgradeOldPropSort data = " + str);
        List<String> d2 = d(str);
        ArrayList arrayList = new ArrayList(HomeManager.a().f());
        if (arrayList.size() > 0) {
            String l2 = HomeManager.a().l();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Home home = (Home) arrayList.get(i2);
                if (TextUtils.equals(home.j(), l2)) {
                    this.y.put(l2, d2);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(n);
                    arrayList2.add(o);
                    arrayList2.add(p);
                    arrayList2.add(q);
                    this.y.put(home.j(), arrayList2);
                }
            }
            e();
            d((AsyncCallback<String, Error>) null);
        } else {
            this.y.put(i, d2);
            e();
        }
        f(d);
    }

    /* access modifiers changed from: private */
    public List<String> d(String str) {
        String str2;
        LogUtil.a(f20711a, "parseOldPropSort data = " + str);
        ArrayList arrayList = new ArrayList();
        try {
            String optString = new JSONObject(str).optString("device_props");
            if (!TextUtils.isEmpty(optString)) {
                JSONArray jSONArray = new JSONArray(optString);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = new JSONObject(jSONArray.getString(i2));
                    String optString2 = jSONObject.optString("did");
                    String optString3 = jSONObject.optString(XmBluetoothRecord.TYPE_PROP);
                    if (TextUtils.equals(jSONObject.optString("type"), "default")) {
                        str2 = "default_weather_did_" + optString3;
                    } else {
                        str2 = optString2 + JSMethod.NOT_SET + optString3;
                    }
                    arrayList.add(str2);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (!arrayList.contains(n)) {
            arrayList.add(n);
        }
        if (!arrayList.contains(o)) {
            arrayList.add(o);
        }
        if (!arrayList.contains(p)) {
            arrayList.add(p);
        }
        if (!arrayList.contains(q)) {
            arrayList.add(q);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public Map<String, List<String>> e(String str) {
        String str2;
        LogUtil.a(f20711a, "parseMultiHomePropSort data = " + str);
        HashMap hashMap = new HashMap();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    String optString = optJSONObject.optString("home_id");
                    JSONArray optJSONArray = optJSONObject.optJSONArray("device_props");
                    if (!TextUtils.isEmpty(optString) && optJSONArray != null && optJSONArray.length() > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                            if (optJSONObject2 != null) {
                                String optString2 = optJSONObject2.optString("did");
                                String optString3 = optJSONObject2.optString(XmBluetoothRecord.TYPE_PROP);
                                if (TextUtils.equals(optJSONObject2.optString("type"), "default")) {
                                    str2 = "default_weather_did_" + optString3;
                                } else {
                                    str2 = optString2 + JSMethod.NOT_SET + optString3;
                                }
                                arrayList.add(str2);
                            }
                        }
                        if (!arrayList.contains(n)) {
                            arrayList.add(n);
                        }
                        if (!arrayList.contains(o)) {
                            arrayList.add(o);
                        }
                        if (!arrayList.contains(p)) {
                            arrayList.add(p);
                        }
                        if (!arrayList.contains(q)) {
                            arrayList.add(q);
                        }
                        hashMap.put(optString, arrayList);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.TopWidgetDataChangerListener r4) {
        /*
            r3 = this;
            java.util.List<com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener> r0 = r3.C
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0023
            java.util.List<com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener> r1 = r3.C     // Catch:{ all -> 0x0021 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0021 }
        L_0x000b:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0021 }
            if (r2 == 0) goto L_0x001b
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0021 }
            com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener r2 = (com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.TopWidgetDataChangerListener) r2     // Catch:{ all -> 0x0021 }
            if (r2 != r4) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return
        L_0x001b:
            java.util.List<com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener> r1 = r3.C     // Catch:{ all -> 0x0021 }
            r1.add(r4)     // Catch:{ all -> 0x0021 }
            goto L_0x0023
        L_0x0021:
            r4 = move-exception
            goto L_0x0025
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.a(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.TopWidgetDataChangerListener r4) {
        /*
            r3 = this;
            java.util.List<com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener> r0 = r3.C
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0020
            java.util.List<com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener> r1 = r3.C     // Catch:{ all -> 0x001e }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x001e }
        L_0x000b:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x001e }
            if (r2 == 0) goto L_0x0020
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x001e }
            com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener r2 = (com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.TopWidgetDataChangerListener) r2     // Catch:{ all -> 0x001e }
            if (r2 != r4) goto L_0x000b
            r1.remove()     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x001e:
            r4 = move-exception
            goto L_0x0022
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.b(com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager$TopWidgetDataChangerListener):void");
    }

    /* access modifiers changed from: private */
    public void f(final String str) {
        synchronized (this.C) {
            for (final TopWidgetDataChangerListener next : this.C) {
                SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        if (next != null) {
                            next.a(str);
                        }
                    }
                });
            }
        }
        Intent intent = new Intent(b);
        intent.putExtra("source", str);
        LocalBroadcastManager.getInstance(this.w).sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void e() {
        LogUtil.a(f20711a, "resetChosenDataList");
        synchronized (this.x) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.y.entrySet()) {
                String str = (String) next.getKey();
                List<String> list = (List) next.getValue();
                List<TopWidgetData> list2 = this.A.get(str);
                ArrayList arrayList = new ArrayList();
                if (list2 != null && list2.size() > 0) {
                    for (TopWidgetData topWidgetData : list2) {
                        topWidgetData.l = false;
                    }
                    if (list != null && list.size() > 0) {
                        for (String str2 : list) {
                            for (TopWidgetData topWidgetData2 : list2) {
                                if (TextUtils.equals(str2, topWidgetData2.f20710a)) {
                                    topWidgetData2.l = true;
                                    arrayList.add(topWidgetData2);
                                }
                            }
                        }
                    }
                }
                hashMap.put(str, arrayList);
            }
            this.z = hashMap;
        }
    }

    public void a(String str, AsyncCallback<String, Error> asyncCallback) {
        LogUtil.a(f20711a, "savePropOrderList source = " + str);
        this.y.clear();
        for (Map.Entry next : this.z.entrySet()) {
            ArrayList arrayList = new ArrayList();
            String str2 = (String) next.getKey();
            for (TopWidgetData topWidgetData : (List) next.getValue()) {
                arrayList.add(topWidgetData.f20710a);
            }
            this.y.put(str2, arrayList);
        }
        if (new ArrayList(HomeManager.a().f()).size() > 0) {
            d(asyncCallback);
        } else {
            c(asyncCallback);
        }
        LogUtil.a(f20711a, "savePropOrderList source = " + str);
        f(str);
    }

    private void c(final AsyncCallback<String, Error> asyncCallback) {
        LogUtil.a(f20711a, "saveOldPropOrderList");
        if (SHApplication.getStateNotifier().a() == 4) {
            List<TopWidgetData> list = this.z.get(i);
            if (list != null && list.size() != 0) {
                JSONArray jSONArray = new JSONArray();
                for (TopWidgetData topWidgetData : list) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(XmBluetoothRecord.TYPE_PROP, topWidgetData.f);
                        if (TextUtils.equals(topWidgetData.c, g)) {
                            jSONObject.put("did", "");
                            jSONObject.put("type", "default");
                        } else {
                            jSONObject.put("did", topWidgetData.c);
                            jSONObject.put("type", "");
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("device_props", jSONArray);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                JSONArray jSONArray2 = new JSONArray();
                try {
                    UserApi.a(jSONObject2, 3001, 1000, 2048, jSONArray2, "7");
                    UserConfigApi.a().a(SHApplication.getAppContext(), jSONArray2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (asyncCallback != null) {
                                asyncCallback.onSuccess("");
                            }
                        }

                        public void onFailure(Error error) {
                            Log.e(TopWidgetDataManager.f20711a, "saveOldPropOrderList failed:" + error.a() + "," + error.b());
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(error);
                            }
                        }
                    });
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            } else if (asyncCallback != null) {
                asyncCallback.onSuccess("");
            }
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), "not logged in"));
        }
    }

    private void d(final AsyncCallback<String, Error> asyncCallback) {
        LogUtil.a(f20711a, "saveMultiHomePropOrderList");
        if (SHApplication.getStateNotifier().a() != 4) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), "not logged in"));
            }
        } else if (this.z.size() != 0 && (this.z.size() != 1 || this.z.get(i) == null)) {
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry next : this.z.entrySet()) {
                String str = (String) next.getKey();
                List<TopWidgetData> list = (List) next.getValue();
                if (!TextUtils.equals(str, i)) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (TopWidgetData topWidgetData : list) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put(XmBluetoothRecord.TYPE_PROP, topWidgetData.f);
                            if (TextUtils.equals(topWidgetData.c, g)) {
                                jSONObject.put("did", "");
                                jSONObject.put("type", "default");
                            } else {
                                jSONObject.put("did", topWidgetData.c);
                                jSONObject.put("type", "");
                            }
                            jSONArray2.put(jSONObject);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("home_id", str);
                        jSONObject2.put("device_props", jSONArray2);
                        jSONArray.put(jSONObject2);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            JSONArray jSONArray3 = new JSONArray();
            try {
                UserApi.a(jSONArray.toString(), 4001, 1000, 2048, jSONArray3, "7");
                UserConfigApi.a().a(SHApplication.getAppContext(), jSONArray3, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (asyncCallback != null) {
                            asyncCallback.onSuccess("");
                        }
                    }

                    public void onFailure(Error error) {
                        Log.e(TopWidgetDataManager.f20711a, "saveMultiHomePropOrderList failed:" + error.a() + "," + error.b());
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess("");
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        Map<String, List<TopWidgetData>> map;
        try {
            map = h();
        } catch (Exception e2) {
            e2.printStackTrace();
            map = null;
        }
        if (map != null) {
            LogUtil.a(f20711a, "updatePropsDataSource dataMap = " + map);
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList(HomeManager.a().f());
            if (arrayList.size() > 0) {
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    Home home = (Home) arrayList.get(i2);
                    List<TopWidgetData> list = this.A.get(home.j());
                    List<TopWidgetData> list2 = map.get(home.j());
                    if (list2 == null) {
                        list2 = new ArrayList<>();
                    } else if (list != null && list.size() > 0) {
                        for (TopWidgetData topWidgetData : list2) {
                            for (TopWidgetData topWidgetData2 : list) {
                                if (TextUtils.equals(topWidgetData.f20710a, topWidgetData2.f20710a)) {
                                    topWidgetData.l = topWidgetData2.l;
                                }
                            }
                        }
                    }
                    list2.add(this.r);
                    list2.add(this.s);
                    list2.add(this.t);
                    list2.add(this.u);
                    hashMap.put(home.j(), list2);
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(this.r);
                arrayList2.add(this.s);
                arrayList2.add(this.t);
                arrayList2.add(this.u);
                hashMap.put(i, arrayList2);
            } else {
                List<TopWidgetData> list3 = this.A.get(i);
                List<TopWidgetData> list4 = map.get(i);
                if (list4 == null) {
                    list4 = new ArrayList<>();
                } else if (list3 != null && list3.size() > 0) {
                    for (TopWidgetData topWidgetData3 : list4) {
                        for (TopWidgetData topWidgetData4 : list3) {
                            if (TextUtils.equals(topWidgetData3.f20710a, topWidgetData4.f20710a)) {
                                topWidgetData3.l = topWidgetData4.l;
                            }
                        }
                    }
                }
                list4.add(this.r);
                list4.add(this.s);
                list4.add(this.t);
                list4.add(this.u);
                hashMap.put(i, list4);
            }
            this.A = hashMap;
        }
    }

    public List<TopWidgetData> a(String str) {
        LogUtil.a(f20711a, "getChosenTopWidgets homeId = " + str);
        if (TextUtils.isEmpty(str)) {
            str = !TextUtils.isEmpty(HomeManager.a().l()) ? HomeManager.a().l() : i;
        }
        List<TopWidgetData> list = this.z.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.r);
        arrayList.add(this.s);
        arrayList.add(this.t);
        arrayList.add(this.u);
        return arrayList;
    }

    public List<TopWidgetData> b(String str) {
        LogUtil.a(f20711a, "getAllTopWidgets homeId = " + str);
        if (TextUtils.isEmpty(str)) {
            str = !TextUtils.isEmpty(HomeManager.a().l()) ? HomeManager.a().l() : i;
        }
        List<TopWidgetData> list = this.A.get(str);
        return list == null ? new ArrayList() : list;
    }

    private TopWidgetData a(String str, String str2) {
        List<TopWidgetData> list = this.A.get(str);
        if (list != null) {
            for (TopWidgetData topWidgetData : list) {
                if (TextUtils.equals(topWidgetData.f20710a, str2)) {
                    return topWidgetData;
                }
            }
        }
        return null;
    }

    public void a(String str, List<TopWidgetData> list) {
        LogUtil.a(f20711a, "syncChosenTopWidgets1 homeId = " + str);
        if (!TextUtils.isEmpty(str) && list != null && list.size() != 0) {
            synchronized (this.x) {
                ArrayList arrayList = new ArrayList();
                List list2 = this.z.get(str);
                if (list2 != null) {
                    for (TopWidgetData next : list) {
                        Iterator it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            TopWidgetData topWidgetData = (TopWidgetData) it.next();
                            if (TextUtils.equals(next.f20710a, topWidgetData.f20710a)) {
                                arrayList.add(topWidgetData);
                                it.remove();
                                break;
                            }
                        }
                    }
                    arrayList.addAll(list2);
                }
                this.z.put(str, arrayList);
            }
        }
    }

    public void a(String str, Map<String, Boolean> map) {
        LogUtil.a(f20711a, "syncChosenTopWidgets2 homeId = " + str);
        if (!TextUtils.isEmpty(str) && map != null && map.size() != 0) {
            synchronized (this.x) {
                List list = this.z.get(str);
                if (list != null) {
                    for (Map.Entry next : map.entrySet()) {
                        String str2 = (String) next.getKey();
                        Boolean bool = (Boolean) next.getValue();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= list.size()) {
                                i2 = -1;
                                break;
                            } else if (TextUtils.equals(str2, ((TopWidgetData) list.get(i2)).f20710a)) {
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (i2 != -1) {
                            list.remove(i2);
                        }
                        TopWidgetData a2 = a().a(str, str2);
                        if (a2 != null) {
                            if (bool.booleanValue()) {
                                a2.l = true;
                                list.add(a2);
                            } else {
                                a2.l = false;
                            }
                        }
                    }
                }
            }
        }
    }

    private String g(String str) {
        try {
            float floatValue = Float.valueOf(str).floatValue();
            if (floatValue < 0.0f) {
                return this.w.getString(R.string.temp_negative_20_0);
            }
            double d2 = (double) floatValue;
            if (d2 < 19.99d) {
                return this.w.getString(R.string.temp_0_20);
            }
            if (d2 < 22.99d) {
                return this.w.getString(R.string.temp_20_23);
            }
            if (d2 < 26.54d) {
                return this.w.getString(R.string.temp_23_26);
            }
            if (d2 < 27.99d) {
                return this.w.getString(R.string.temp_26_28);
            }
            return this.w.getString(R.string.temp_28_45);
        } catch (Exception unused) {
            return "";
        }
    }

    private String h(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt <= 50) {
                return this.w.getString(R.string.air_quality_50);
            }
            if (parseInt <= 100) {
                return this.w.getString(R.string.air_quality_100);
            }
            if (parseInt <= 150) {
                return this.w.getString(R.string.air_150);
            }
            if (parseInt <= 200) {
                return this.w.getString(R.string.air_200);
            }
            if (parseInt <= 300) {
                return this.w.getString(R.string.air_300);
            }
            return this.w.getString(R.string.air_x);
        } catch (Exception unused) {
            return "";
        }
    }

    private String i(String str) {
        try {
            if (Integer.valueOf(str).intValue() <= 50) {
                return this.w.getString(R.string.tds_50);
            }
            return this.w.getString(R.string.tds_1000);
        } catch (Exception unused) {
            return "";
        }
    }

    private String g() {
        float f2;
        try {
            f2 = Float.valueOf(AreaInfoManager.a().C()).floatValue();
        } catch (Exception unused) {
            f2 = 0.0f;
        }
        return String.format(MainPageOpManager.f13390a, new Object[]{Float.valueOf(f2)});
    }

    private String j(String str) {
        try {
            float floatValue = Float.valueOf(str).floatValue();
            if (floatValue < 20.0f) {
                return this.w.getString(R.string.humidity_20);
            }
            if (floatValue < 40.0f) {
                return this.w.getString(R.string.humidity_40);
            }
            if (floatValue < 60.0f) {
                return this.w.getString(R.string.humidity_60);
            }
            if (floatValue < 86.0f) {
                return this.w.getString(R.string.humidity_86);
            }
            return this.w.getString(R.string.humidity_100);
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0044, code lost:
        r15 = r14.model;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.newui.topwidget.TopWidgetData>> h() {
        /*
            r25 = this;
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            com.xiaomi.smarthome.newui.card.ControlCardInfoManager r0 = com.xiaomi.smarthome.newui.card.ControlCardInfoManager.f()
            java.util.Map r0 = r0.g()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r3 = r0.iterator()
        L_0x001a:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x02b1
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r4 = r0.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r0 = r0.getValue()
            java.util.Map r0 = (java.util.Map) r0
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r5 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            com.xiaomi.smarthome.device.Device r14 = r5.b((java.lang.String) r4)
            if (r14 == 0) goto L_0x02a7
            if (r0 == 0) goto L_0x02a7
            int r5 = r0.size()
            if (r5 <= 0) goto L_0x02a7
            java.lang.String r15 = r14.model
            com.xiaomi.smarthome.newui.card.ControlCardInfoManager r5 = com.xiaomi.smarthome.newui.card.ControlCardInfoManager.f()
            com.xiaomi.smarthome.newui.card.profile.ProfileCard r13 = r5.e((java.lang.String) r15)
            if (r13 == 0) goto L_0x02a7
            r2.clear()
            java.util.List<T> r5 = r13.c
            java.util.Iterator r5 = r5.iterator()
        L_0x0059:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x006d
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.newui.card.profile.ProfileCardType r6 = (com.xiaomi.smarthome.newui.card.profile.ProfileCardType) r6
            java.lang.String r7 = r6.c
            com.xiaomi.smarthome.newui.card.profile.PropItem r6 = r6.e
            r2.put(r7, r6)
            goto L_0x0059
        L_0x006d:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r16 = r0.iterator()
        L_0x0075:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L_0x02a7
            java.lang.Object r0 = r16.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r5 = r0.getKey()
            r12 = r5
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r0 = r0.getValue()
            java.lang.Object r5 = r2.get(r12)
            r11 = r5
            com.xiaomi.smarthome.newui.card.profile.PropItem r11 = (com.xiaomi.smarthome.newui.card.profile.PropItem) r11
            if (r11 == 0) goto L_0x0293
            java.util.List<java.lang.Integer> r5 = r11.h
            if (r5 == 0) goto L_0x0293
            java.util.List<java.lang.Integer> r5 = r11.h
            r6 = 2
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x0293
            java.lang.String r5 = "event.pure_water_record"
            boolean r5 = android.text.TextUtils.equals(r12, r5)
            if (r5 == 0) goto L_0x00bf
            if (r0 == 0) goto L_0x00d7
            java.lang.String r5 = r0.toString()
            java.lang.String r6 = "null"
            boolean r5 = android.text.TextUtils.equals(r5, r6)
            if (r5 == 0) goto L_0x00d7
            java.lang.String r0 = ""
            goto L_0x00d7
        L_0x00bf:
            com.xiaomi.smarthome.newui.card.Param r5 = r11.k
            if (r5 == 0) goto L_0x00d7
            com.xiaomi.smarthome.newui.card.Param r5 = r11.k
            java.lang.Object r0 = r5.c(r0)
            if (r0 == 0) goto L_0x00d7
            boolean r5 = r14 instanceof com.xiaomi.smarthome.device.BleDevice
            if (r5 == 0) goto L_0x00d7
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = com.xiaomi.smarthome.newui.card.CardItem.a((java.lang.String) r0)
        L_0x00d7:
            r17 = r0
            java.lang.String r0 = ""
            if (r17 == 0) goto L_0x00e1
            java.lang.String r0 = r17.toString()
        L_0x00e1:
            r5 = r0
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = "null"
            boolean r0 = android.text.TextUtils.equals(r5, r0)
            if (r0 != 0) goto L_0x014e
            boolean r0 = r11.a()
            if (r0 == 0) goto L_0x00fb
            java.lang.String r5 = com.xiaomi.smarthome.newui.card.CardItem.b((java.lang.Object) r5)
            goto L_0x014e
        L_0x00fb:
            boolean r0 = r11.b()
            if (r0 == 0) goto L_0x012c
            double r8 = java.lang.Double.parseDouble(r5)     // Catch:{ Exception -> 0x0127 }
            double r6 = r11.e     // Catch:{ Exception -> 0x0127 }
            double r8 = r8 * r6
            java.lang.String r0 = r11.f     // Catch:{ Exception -> 0x0127 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0127 }
            if (r0 != 0) goto L_0x0122
            java.lang.String r0 = r11.f     // Catch:{ Exception -> 0x0127 }
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0127 }
            java.lang.Double r7 = java.lang.Double.valueOf(r8)     // Catch:{ Exception -> 0x0127 }
            r8 = 0
            r6[r8] = r7     // Catch:{ Exception -> 0x0127 }
            java.lang.String r0 = java.lang.String.format(r0, r6)     // Catch:{ Exception -> 0x0127 }
            goto L_0x0148
        L_0x0122:
            java.lang.String r0 = java.lang.Double.toString(r8)     // Catch:{ Exception -> 0x0127 }
            goto L_0x0148
        L_0x0127:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x014e
        L_0x012c:
            java.lang.String r0 = r11.f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x014e
            java.lang.String r0 = r11.f     // Catch:{ Exception -> 0x014a }
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x014a }
            double r7 = java.lang.Double.parseDouble(r5)     // Catch:{ Exception -> 0x014a }
            java.lang.Double r7 = java.lang.Double.valueOf(r7)     // Catch:{ Exception -> 0x014a }
            r8 = 0
            r6[r8] = r7     // Catch:{ Exception -> 0x014a }
            java.lang.String r0 = java.lang.String.format(r0, r6)     // Catch:{ Exception -> 0x014a }
        L_0x0148:
            r5 = r0
            goto L_0x014e
        L_0x014a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x014e:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x015f
            java.lang.String r0 = "null"
            boolean r0 = android.text.TextUtils.equals(r5, r0)
            if (r0 == 0) goto L_0x015d
            goto L_0x015f
        L_0x015d:
            r0 = r5
            goto L_0x0161
        L_0x015f:
            java.lang.String r0 = "-"
        L_0x0161:
            com.xiaomi.smarthome.homeroom.HomeManager r5 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.lang.String r9 = r5.r(r4)
            com.xiaomi.smarthome.newui.topwidget.TopWidgetData r10 = new com.xiaomi.smarthome.newui.topwidget.TopWidgetData
            java.lang.String r8 = r14.name
            java.lang.String r7 = r11.f20557a
            java.util.Map<java.lang.String, java.lang.String> r5 = r11.c
            java.lang.String r18 = com.xiaomi.smarthome.newui.card.CardItem.a((java.util.Map<java.lang.String, java.lang.String>) r5)
            java.lang.String r6 = r11.g
            r5 = r10
            r19 = r6
            r6 = r15
            r20 = r7
            r7 = r4
            r21 = r2
            r2 = r10
            r10 = r20
            r22 = r3
            r3 = r11
            r11 = r18
            r23 = r14
            r14 = r12
            r12 = r0
            r24 = r15
            r15 = r13
            r13 = r19
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)
            java.util.List<com.xiaomi.smarthome.newui.card.profile.PropItem$PropExtraItem> r0 = r3.i
            if (r17 == 0) goto L_0x0208
            java.lang.String r5 = r17.toString()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x0208
            java.lang.String r5 = r17.toString()
            java.lang.String r6 = "null"
            boolean r5 = android.text.TextUtils.equals(r5, r6)
            if (r5 != 0) goto L_0x0208
            if (r0 == 0) goto L_0x0208
            int r5 = r0.size()
            if (r5 <= 0) goto L_0x0208
            java.lang.String r5 = r17.toString()     // Catch:{ Exception -> 0x0208 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Exception -> 0x0208 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0208 }
        L_0x01c2:
            boolean r6 = r0.hasNext()     // Catch:{ Exception -> 0x0208 }
            if (r6 == 0) goto L_0x0208
            java.lang.Object r6 = r0.next()     // Catch:{ Exception -> 0x0208 }
            com.xiaomi.smarthome.newui.card.profile.PropItem$PropExtraItem r6 = (com.xiaomi.smarthome.newui.card.profile.PropItem.PropExtraItem) r6     // Catch:{ Exception -> 0x0208 }
            java.lang.Object r7 = r6.f20558a     // Catch:{ Exception -> 0x0208 }
            if (r7 != 0) goto L_0x01ec
            double r7 = r6.c     // Catch:{ Exception -> 0x0208 }
            double r9 = (double) r5     // Catch:{ Exception -> 0x0208 }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 > 0) goto L_0x01c2
            double r7 = r6.d     // Catch:{ Exception -> 0x0208 }
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 > 0) goto L_0x01c2
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.b     // Catch:{ Exception -> 0x0208 }
            if (r0 == 0) goto L_0x0208
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.b     // Catch:{ Exception -> 0x0208 }
            java.lang.String r0 = com.xiaomi.smarthome.newui.card.CardItem.a((java.util.Map<java.lang.String, java.lang.String>) r0)     // Catch:{ Exception -> 0x0208 }
            r2.j = r0     // Catch:{ Exception -> 0x0208 }
            goto L_0x0208
        L_0x01ec:
            java.lang.String r7 = r17.toString()     // Catch:{ Exception -> 0x0208 }
            java.lang.Object r8 = r6.f20558a     // Catch:{ Exception -> 0x0208 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0208 }
            boolean r7 = android.text.TextUtils.equals(r7, r8)     // Catch:{ Exception -> 0x0208 }
            if (r7 == 0) goto L_0x01c2
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.b     // Catch:{ Exception -> 0x0208 }
            if (r0 == 0) goto L_0x0208
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.b     // Catch:{ Exception -> 0x0208 }
            java.lang.String r0 = com.xiaomi.smarthome.newui.card.CardItem.a((java.util.Map<java.lang.String, java.lang.String>) r0)     // Catch:{ Exception -> 0x0208 }
            r2.j = r0     // Catch:{ Exception -> 0x0208 }
        L_0x0208:
            r0 = -1
            java.util.List<T> r5 = r15.c
            if (r5 == 0) goto L_0x0229
            java.util.List<T> r5 = r15.c
            java.util.Iterator r5 = r5.iterator()
        L_0x0213:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0229
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.newui.card.profile.ProfileCardType r6 = (com.xiaomi.smarthome.newui.card.profile.ProfileCardType) r6
            java.lang.String r7 = r6.c
            boolean r7 = android.text.TextUtils.equals(r7, r14)
            if (r7 == 0) goto L_0x0213
            int r0 = r6.b
        L_0x0229:
            r5 = 8
            if (r0 != r5) goto L_0x023e
            java.lang.String r0 = r2.j
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x023e
            java.lang.String r0 = r2.j
            r2.h = r0
            java.lang.String r0 = ""
            r2.j = r0
            goto L_0x0262
        L_0x023e:
            boolean r0 = r3.a()
            if (r0 == 0) goto L_0x0262
            if (r17 == 0) goto L_0x0262
            java.lang.String r0 = r17.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0262
            java.lang.String r0 = r17.toString()
            java.lang.String r5 = "null"
            boolean r0 = android.text.TextUtils.equals(r0, r5)
            if (r0 != 0) goto L_0x0262
            java.lang.String r0 = a((java.lang.Object) r17)
            r2.j = r0
        L_0x0262:
            java.util.Map<java.lang.String, java.lang.String> r0 = r3.l
            if (r0 == 0) goto L_0x026e
            java.util.Map<java.lang.String, java.lang.String> r0 = r3.l
            java.lang.String r0 = com.xiaomi.smarthome.newui.card.CardItem.a((java.util.Map<java.lang.String, java.lang.String>) r0)
            r2.k = r0
        L_0x026e:
            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            com.xiaomi.smarthome.homeroom.model.Home r0 = r0.q(r4)
            if (r0 == 0) goto L_0x027d
            java.lang.String r0 = r0.j()
            goto L_0x027f
        L_0x027d:
            java.lang.String r0 = "empty_home_id"
        L_0x027f:
            java.lang.Object r3 = r1.get(r0)
            java.util.List r3 = (java.util.List) r3
            if (r3 != 0) goto L_0x028f
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1.put(r0, r3)
        L_0x028f:
            r3.add(r2)
            goto L_0x029c
        L_0x0293:
            r21 = r2
            r22 = r3
            r23 = r14
            r24 = r15
            r15 = r13
        L_0x029c:
            r13 = r15
            r2 = r21
            r3 = r22
            r14 = r23
            r15 = r24
            goto L_0x0075
        L_0x02a7:
            r21 = r2
            r22 = r3
            r2 = r21
            r3 = r22
            goto L_0x001a
        L_0x02b1:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManager.h():java.util.Map");
    }

    private static String a(Object obj) {
        if (obj == null || TextUtils.isEmpty(String.valueOf(obj))) {
            return "";
        }
        try {
            return new SimpleDateFormat("MM/dd", Locale.getDefault()).format(new Date(Long.parseLong(obj.toString()) * 1000));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
