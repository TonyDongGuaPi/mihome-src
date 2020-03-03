package com.xiaomi.smarthome.newui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.newui.topwidget.EnvDevice;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManagerNew;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeEnvInfoViewModel extends ViewModel {

    /* renamed from: a  reason: collision with root package name */
    public static String f20291a = "HomeEnvInfoViewModel";
    public static int b = 43200;
    public static final int c = 0;
    public static final int d = 1;
    public static List<String> e = Arrays.asList(SHApplication.getAppContext().getResources().getStringArray(R.array.home_env_type));
    private static final String f = "home_env_info";
    private static final String g = "env_data";
    private static final String h = "top_data";
    private static final String i = "env_device";
    private SharedPreferences j;
    /* access modifiers changed from: private */
    public BroadcastReceiver k;
    /* access modifiers changed from: private */
    public AtomicBoolean l = new AtomicBoolean(false);
    private AtomicBoolean m = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public HashMap<String, List<GroupBean>> n = new HashMap<>();
    private HashMap<String, List<GroupBean>> o = new HashMap<>();
    /* access modifiers changed from: private */
    public MutableLiveData<Map<String, TopWidgetDataNew>> p = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public MutableLiveData<Map<String, TopWidgetInfo>> q = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public MutableLiveData<Map<String, List<EnvDevice>>> r = new MutableLiveData<>();

    public class GroupBean {

        /* renamed from: a  reason: collision with root package name */
        String f20296a;
        String b;
        List<TopWidgetDataNew.Detail> c = new ArrayList();
        int d = 0;

        public GroupBean(String str, String str2, List<TopWidgetDataNew.Detail> list) {
            this.f20296a = str;
            this.b = str2;
            this.c.clear();
            this.c.addAll(list);
        }
    }

    public MutableLiveData<Map<String, TopWidgetDataNew>> a() {
        if (this.p.getValue() == null) {
            a(HomeManager.a().l());
        }
        return this.p;
    }

    public MutableLiveData<Map<String, TopWidgetInfo>> b() {
        if (this.q.getValue() == null) {
            a(HomeManager.a().l());
        }
        return this.q;
    }

    public MutableLiveData<Map<String, List<EnvDevice>>> c() {
        if (this.r.getValue() == null) {
            a(HomeManager.a().l());
        }
        return this.r;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            String str2 = f20291a;
            LogUtil.a(str2, "home id is null!" + System.currentTimeMillis());
            if (this.k == null) {
                this.k = new HomeIDReadyReceiver();
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.k, new IntentFilter(HomeManager.t));
                return;
            }
            return;
        }
        if (this.k != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.k);
        }
        h(str);
        g(str);
        f(str);
    }

    public void a(final String str, final List<EnvDevice> list, final AsyncCallback<JSONObject, Error> asyncCallback) {
        if (SHApplication.getStateNotifier().a() == 4) {
            TopWidgetDataManagerNew.b().a(str, list, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null && jSONObject.optInt("code", -1) == 0) {
                        Map map = (Map) HomeEnvInfoViewModel.this.r.getValue();
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(str, list);
                        HomeEnvInfoViewModel.this.r.setValue(map);
                    }
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(jSONObject);
                    }
                    String str = HomeEnvInfoViewModel.f20291a;
                    LogUtil.a(str, "setDefaultEnvDevicesToServer onSuccess: " + jSONObject.toString());
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                    String str = HomeEnvInfoViewModel.f20291a;
                    LogUtil.a(str, "setDefaultEnvDevicesToServer onFailure: " + error.b());
                }
            });
        }
    }

    private void f(final String str) {
        if (SHApplication.getStateNotifier().a() == 4) {
            TopWidgetDataManagerNew.b().a(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null && !jSONObject.isNull("result")) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("result");
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            EnvDevice a2 = EnvDevice.a(optJSONArray.optJSONObject(i));
                            if (!TextUtils.isEmpty(a2.f20709a) && !TextUtils.isEmpty(a2.b)) {
                                arrayList.add(a2);
                            }
                        }
                        Map map = (Map) HomeEnvInfoViewModel.this.r.getValue();
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(str, arrayList);
                        HomeEnvInfoViewModel.this.r.setValue(map);
                        HomeEnvInfoViewModel.this.a(str, jSONObject);
                    }
                    String str = HomeEnvInfoViewModel.f20291a;
                    LogUtil.a(str, "loadDefaultEnvDeviceFromServer succ: " + jSONObject);
                }

                public void onFailure(Error error) {
                    String str = HomeEnvInfoViewModel.f20291a;
                    LogUtil.a(str, "loadDefaultEnvDeviceFromServer err " + error.b());
                }
            });
        }
    }

    private void g(final String str) {
        if (SHApplication.getStateNotifier().a() == 4) {
            TopWidgetDataManagerNew.b().a(str, ((int) (System.currentTimeMillis() / 1000)) - b, e, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        Map map = (Map) HomeEnvInfoViewModel.this.q.getValue();
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(str, TopWidgetInfo.a(jSONObject));
                        HomeEnvInfoViewModel.this.q.setValue(map);
                        HomeEnvInfoViewModel.this.k(str);
                        String str = HomeEnvInfoViewModel.f20291a;
                        LogUtil.a(str, "loadTopWidgetInfoFromServer succ: " + jSONObject);
                    }
                }

                public void onFailure(Error error) {
                    Map map = (Map) HomeEnvInfoViewModel.this.q.getValue();
                    if (map == null) {
                        map = new HashMap();
                    }
                    map.put(str, new TopWidgetInfo());
                    HomeEnvInfoViewModel.this.q.setValue(map);
                    String str = HomeEnvInfoViewModel.f20291a;
                    LogUtil.a(str, "loadTopWidgetInfoFromServer err " + error.b());
                }
            });
        }
    }

    private void h(final String str) {
        if (SHApplication.getStateNotifier().a() == 4 && !this.l.getAndSet(true)) {
            TopWidgetDataManagerNew.b().a(str, ((int) (System.currentTimeMillis() / 1000)) - b, 1, e, new AsyncCallback<TopWidgetDataNew, Error>() {
                /* renamed from: a */
                public void onSuccess(TopWidgetDataNew topWidgetDataNew) {
                    if (topWidgetDataNew != null) {
                        Map map = (Map) HomeEnvInfoViewModel.this.p.getValue();
                        topWidgetDataNew.a();
                        HomeEnvInfoViewModel.this.a(str, topWidgetDataNew);
                        HomeEnvInfoViewModel.this.b(str, topWidgetDataNew);
                        if (map == null) {
                            map = new HashMap();
                        }
                        map.put(str, topWidgetDataNew);
                        HomeEnvInfoViewModel.this.p.setValue(map);
                        HomeEnvInfoViewModel.this.j(str);
                        String str = HomeEnvInfoViewModel.f20291a;
                        LogUtil.a(str, "getDeviceDescription onSuccess " + topWidgetDataNew.f20739a.size() + " " + HomeEnvInfoViewModel.this.n.size());
                    }
                    HomeEnvInfoViewModel.this.l.set(false);
                }

                public void onFailure(Error error) {
                    HomeEnvInfoViewModel.this.l.set(false);
                    String str = HomeEnvInfoViewModel.f20291a;
                    LogUtil.a(str, "getDeviceDescription err " + error.b());
                }
            });
        }
    }

    public void b(String str) {
        Map value = this.p.getValue();
        if ((value == null || value.get(str) == null || ((TopWidgetDataNew) value.get(str)).f20739a == null || ((TopWidgetDataNew) value.get(str)).f20739a.isEmpty()) && !this.m.get()) {
            i(str);
            l(str);
            this.m.set(true);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, TopWidgetDataNew topWidgetDataNew) {
        ArrayList arrayList = new ArrayList();
        Home j2 = HomeManager.a().j(str);
        if (j2 != null) {
            if (topWidgetDataNew == null || topWidgetDataNew.f20739a == null || topWidgetDataNew.f20739a.isEmpty()) {
                this.n.put(str, arrayList);
                return;
            }
            HashMap hashMap = new HashMap();
            List<TopWidgetDataNew.Detail> list = topWidgetDataNew.f20739a;
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i2 >= list.size()) {
                    break;
                }
                TopWidgetDataNew.Detail detail = list.get(i2);
                if (SmartHomeDeviceManager.a().b(detail.b) != null) {
                    if (TextUtils.isEmpty(detail.h) || TextUtils.equals(detail.h, HomeManager.d)) {
                        detail.h = HomeManager.d;
                    } else {
                        Room i3 = HomeManager.a().i(detail.h);
                        if (i3 == null || !TextUtils.equals(i3.f(), str)) {
                            z = false;
                        }
                    }
                    if (z) {
                        List list2 = (List) hashMap.get(detail.h);
                        if (list2 == null) {
                            list2 = new ArrayList();
                            hashMap.put(detail.h, list2);
                        }
                        list2.add(detail);
                    }
                }
                i2++;
            }
            if (hashMap.isEmpty()) {
                this.n.put(str, arrayList);
                return;
            }
            for (List sort : hashMap.values()) {
                Collections.sort(sort, $$Lambda$HomeEnvInfoViewModel$OAQveEygQbOubMn340QZRkxik4Y.INSTANCE);
            }
            List<Room> a2 = HomeManager.a().a(str);
            if (a2 != null && !a2.isEmpty()) {
                for (int i4 = 0; i4 < a2.size(); i4++) {
                    Room room = a2.get(i4);
                    List list3 = (List) hashMap.get(room.d());
                    if (list3 != null && !list3.isEmpty()) {
                        arrayList.add(new GroupBean(room.e(), room.d(), list3));
                    }
                }
            }
            List list4 = (List) hashMap.get(HomeManager.d);
            if (list4 != null && !list4.isEmpty()) {
                arrayList.add(new GroupBean(SHApplication.getAppContext().getResources().getString(R.string.default_room), HomeManager.d, list4));
            }
            if (!arrayList.isEmpty()) {
                if (j2.p()) {
                    GroupBean groupBean = new GroupBean("", "", new ArrayList());
                    groupBean.d = 1;
                    arrayList.add(groupBean);
                }
                this.n.put(str, arrayList);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(TopWidgetDataNew.Detail detail, TopWidgetDataNew.Detail detail2) {
        return e.indexOf(detail.g) - e.indexOf(detail2.g);
    }

    /* access modifiers changed from: private */
    public void b(String str, TopWidgetDataNew topWidgetDataNew) {
        ArrayList arrayList = new ArrayList();
        if (HomeManager.a().j(str) != null) {
            if (topWidgetDataNew == null || topWidgetDataNew.f20739a == null || topWidgetDataNew.f20739a.isEmpty()) {
                this.o.put(str, arrayList);
                return;
            }
            HashMap hashMap = new HashMap();
            List<TopWidgetDataNew.Detail> list = topWidgetDataNew.f20739a;
            for (int i2 = 0; i2 < list.size(); i2++) {
                TopWidgetDataNew.Detail detail = list.get(i2);
                if (SmartHomeDeviceManager.a().b(detail.b) != null) {
                    boolean z = true;
                    if (TextUtils.isEmpty(detail.h) || TextUtils.equals(detail.h, HomeManager.d)) {
                        detail.h = HomeManager.d;
                    } else {
                        Room i3 = HomeManager.a().i(detail.h);
                        if (i3 == null || !TextUtils.equals(i3.f(), str)) {
                            z = false;
                        }
                    }
                    if (z) {
                        List list2 = (List) hashMap.get(detail.g);
                        if (list2 == null) {
                            list2 = new ArrayList();
                            hashMap.put(detail.g, list2);
                        }
                        list2.add(detail);
                    }
                }
            }
            if (!hashMap.isEmpty()) {
                ArrayList<String> arrayList2 = new ArrayList<>(hashMap.keySet());
                Collections.sort(arrayList2, $$Lambda$HomeEnvInfoViewModel$e50ESHZ955lCSbTx20P3U0ATXYQ.INSTANCE);
                List<Room> a2 = HomeManager.a().a(str);
                if (a2 != null) {
                    List asList = Arrays.asList(SHApplication.getAppContext().getResources().getStringArray(R.array.home_env_type_desc));
                    for (String str2 : arrayList2) {
                        List<TopWidgetDataNew.Detail> list3 = (List) hashMap.get(str2);
                        Collections.sort(list3, new Comparator(a2) {
                            private final /* synthetic */ List f$0;

                            {
                                this.f$0 = r1;
                            }

                            public final int compare(Object obj, Object obj2) {
                                return HomeEnvInfoViewModel.a(this.f$0, (TopWidgetDataNew.Detail) obj, (TopWidgetDataNew.Detail) obj2);
                            }
                        });
                        if (list3 != null && !list3.isEmpty()) {
                            ArrayList arrayList3 = new ArrayList();
                            for (TopWidgetDataNew.Detail detail2 : list3) {
                                if (!SmartHomeDeviceManager.a().b(detail2.b).isOnline) {
                                    arrayList3.add(detail2);
                                }
                            }
                            if (!arrayList3.isEmpty()) {
                                list3.removeAll(arrayList3);
                                list3.addAll(arrayList3);
                            }
                            int indexOf = e.indexOf(str2);
                            arrayList.add(new GroupBean((indexOf < 0 || indexOf >= asList.size()) ? NotificationCompat.CATEGORY_ERROR : (String) asList.get(indexOf), str2, list3));
                        }
                    }
                }
                this.o.put(str, arrayList);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(String str, String str2) {
        return e.indexOf(str) - e.indexOf(str2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(List list, TopWidgetDataNew.Detail detail, TopWidgetDataNew.Detail detail2) {
        Room i2 = HomeManager.a().i(detail.h);
        Room i3 = HomeManager.a().i(detail2.h);
        return (i2 != null ? list.indexOf(i2) : list.size()) - (i3 != null ? list.indexOf(i3) : list.size());
    }

    public List<GroupBean> c(String str) {
        return this.n.get(str);
    }

    public List<GroupBean> d(String str) {
        return this.o.get(str);
    }

    private void i(String str) {
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        try {
            SharedPreferences sharedPreferences = this.j;
            String string = sharedPreferences.getString(str + g, "");
            if (string != null) {
                if (!string.isEmpty()) {
                    TopWidgetDataNew a2 = TopWidgetDataNew.a(new JSONObject(string));
                    if (a2 != null) {
                        a2.a();
                        a(str, a2);
                        b(str, a2);
                        Map value = this.p.getValue();
                        if (value == null) {
                            value = new HashMap();
                        }
                        value.put(str, a2);
                        this.p.setValue(value);
                        String str2 = f20291a;
                        LogUtil.a(str2, "readEnvInfoCache onSuccess " + a2.f20739a.size() + " " + this.n.size());
                        return;
                    }
                    return;
                }
            }
            LogUtil.a(f20291a, "readEnvInfoCache: empty!");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void j(String str) {
        TopWidgetDataNew topWidgetDataNew;
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        Map value = this.p.getValue();
        if (value != null && (topWidgetDataNew = (TopWidgetDataNew) value.get(str)) != null) {
            SharedPreferences.Editor edit = this.j.edit();
            edit.putString(str + g, topWidgetDataNew.b().toString()).apply();
        }
    }

    public void e(String str) {
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        try {
            SharedPreferences sharedPreferences = this.j;
            String string = sharedPreferences.getString(str + h, "");
            if (TextUtils.isEmpty(string)) {
                LogUtil.a(f20291a, "readTopInfoCache: empty!");
                return;
            }
            TopWidgetInfo a2 = TopWidgetInfo.a(new JSONObject(string));
            Map value = this.q.getValue();
            if (value == null) {
                value = new HashMap();
            }
            value.put(str, a2);
            this.q.postValue(value);
            String str2 = f20291a;
            LogUtil.a(str2, "readTopInfoCache : " + string);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void k(String str) {
        TopWidgetInfo topWidgetInfo;
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        Map value = this.q.getValue();
        if (value != null && (topWidgetInfo = (TopWidgetInfo) value.get(str)) != null) {
            SharedPreferences.Editor edit = this.j.edit();
            edit.putString(str + h, topWidgetInfo.a().toString()).apply();
        }
    }

    private void l(String str) {
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        try {
            SharedPreferences sharedPreferences = this.j;
            String string = sharedPreferences.getString(str + i, "");
            if (TextUtils.isEmpty(string)) {
                LogUtil.a(f20291a, "readDefaultEnvDeviceCache: empty!");
                return;
            }
            JSONObject jSONObject = new JSONObject(string);
            if (!jSONObject.isNull("result")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    EnvDevice a2 = EnvDevice.a(optJSONArray.optJSONObject(i2));
                    if (!TextUtils.isEmpty(a2.f20709a) && !TextUtils.isEmpty(a2.b)) {
                        arrayList.add(a2);
                    }
                }
                Map value = this.r.getValue();
                if (value == null) {
                    value = new HashMap();
                }
                value.put(str, arrayList);
                this.r.setValue(value);
            }
            String str2 = f20291a;
            LogUtil.a(str2, "readDefaultEnvDeviceCache : " + string);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, JSONObject jSONObject) {
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        if (jSONObject != null) {
            SharedPreferences.Editor edit = this.j.edit();
            edit.putString(str + i, jSONObject.toString()).apply();
        }
    }

    class HomeIDReadyReceiver extends BroadcastReceiver {
        HomeIDReadyReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(HomeManager.v);
            int intExtra = intent.getIntExtra("result_code", -1);
            if (TextUtils.equals(action, HomeManager.t) && TextUtils.equals(HomeManager.x, stringExtra) && intExtra == ErrorCode.SUCCESS.getCode() && !TextUtils.isEmpty(HomeManager.a().l())) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(HomeEnvInfoViewModel.this.k);
                BroadcastReceiver unused = HomeEnvInfoViewModel.this.k = null;
                String str = HomeEnvInfoViewModel.f20291a;
                LogUtil.a(str, "home id is ready!" + System.currentTimeMillis());
                HomeEnvInfoViewModel.this.a(HomeManager.a().l());
            }
        }
    }

    public void d() {
        LogUtil.a(f20291a, "clear");
        this.n.clear();
        this.o.clear();
        this.m.set(false);
        Map value = this.p.getValue();
        if (value != null) {
            value.clear();
        }
        Map value2 = this.q.getValue();
        if (value2 != null) {
            value2.clear();
        }
        if (this.j == null) {
            this.j = SHApplication.getAppContext().getSharedPreferences(f, 0);
        }
        this.j.edit().clear().commit();
    }
}
