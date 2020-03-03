package com.xiaomi.smarthome.newui.topwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.devicesubscribe.SubscribeCallback;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataNew;
import com.xiaomi.stat.a.j;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopWidgetDataManagerNew {
    private static final int b = 150;
    private static final int c = 0;
    private static final String d = "top_widget_data";
    private static volatile TopWidgetDataManagerNew f = null;
    private static final int m = 180;
    private static final int n = 1;

    /* renamed from: a  reason: collision with root package name */
    SmartHomeDeviceManager.IClientDeviceListener f20724a = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            TopWidgetDataManagerNew.this.f();
        }
    };
    private SharedPreferences e;
    /* access modifiers changed from: private */
    public HashMap<String, TopWidgetDataNew> g = new HashMap<>();
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public String j;
    private boolean k = false;
    /* access modifiers changed from: private */
    public boolean l = false;
    private BroadcastReceiver o = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && TextUtils.equals(action, "action_on_logout")) {
                TopWidgetDataManagerNew.this.g.clear();
            }
        }
    };
    private BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && TextUtils.equals(action, HomeManager.t) && TextUtils.equals(HomeManager.x, intent.getStringExtra(HomeManager.v)) && intent.getIntExtra("result_code", -1) == ErrorCode.SUCCESS.getCode() && !TextUtils.isEmpty(HomeManager.a().l())) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                LogUtil.a("TopWidgetDataManagerNew", "home id is ready!" + System.currentTimeMillis());
                TopWidgetDataManagerNew.this.b(HomeManager.a().l());
                TopWidgetDataManagerNew.this.c();
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler q = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                TopWidgetDataManagerNew.this.e();
            }
        }
    };
    /* access modifiers changed from: private */
    public AsyncCallback<TopWidgetDataNew, Error> r = new AsyncCallback<TopWidgetDataNew, Error>() {
        /* renamed from: a */
        public void onSuccess(TopWidgetDataNew topWidgetDataNew) {
            LogUtil.a("TopWidgetDataManagerNew", "request " + TopWidgetDataManagerNew.this.h + " page TOP_INFO_SUCCESS " + System.currentTimeMillis());
            if (TopWidgetDataManagerNew.this.h == TopWidgetDataManagerNew.this.i) {
                TopWidgetDataManagerNew.this.g.remove(TopWidgetDataManagerNew.this.j);
            }
            TopWidgetDataManagerNew.this.a(TopWidgetDataManagerNew.this.j, topWidgetDataNew);
            if (topWidgetDataNew == null || topWidgetDataNew.f20739a == null || topWidgetDataNew.f20739a.size() < 150) {
                TopWidgetDataManagerNew.this.d(TopWidgetDataManagerNew.this.j);
                boolean unused = TopWidgetDataManagerNew.this.l = false;
                return;
            }
            TopWidgetDataManagerNew.f(TopWidgetDataManagerNew.this);
            if (TopWidgetDataManagerNew.this.h >= 0) {
                TopWidgetDataManagerNew.this.b(TopWidgetDataManagerNew.this.j, (AsyncCallback<TopWidgetDataNew, Error>) TopWidgetDataManagerNew.this.r);
                return;
            }
            TopWidgetDataManagerNew.this.d(TopWidgetDataManagerNew.this.j);
            boolean unused2 = TopWidgetDataManagerNew.this.l = false;
        }

        public void onFailure(Error error) {
            boolean unused = TopWidgetDataManagerNew.this.l = false;
            TopWidgetDataManagerNew.this.g.remove(TopWidgetDataManagerNew.this.j);
            TopWidgetDataManagerNew.this.c(TopWidgetDataManagerNew.this.j);
        }
    };
    private List<OnDataChangeListener> s = new CopyOnWriteArrayList();

    public interface OnDataChangeListener {
        void a(boolean z);
    }

    static /* synthetic */ int f(TopWidgetDataManagerNew topWidgetDataManagerNew) {
        int i2 = topWidgetDataManagerNew.h;
        topWidgetDataManagerNew.h = i2 - 1;
        return i2;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (this.g.get(HomeManager.a().l()) == null || this.g.get(HomeManager.a().l()).f20739a == null || this.g.get(HomeManager.a().l()).f20739a.isEmpty()) {
            String string = this.e.getString("home_id", "");
            if (!TextUtils.isEmpty(str) && TextUtils.equals(str, string)) {
                this.g.remove(str);
                try {
                    this.g.put(str, TopWidgetDataNew.a(new JSONObject(this.e.getString("data", ""))));
                    a(true);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        String str = this.j;
        if (this.g.get(str) != null && this.g.get(str).f20739a != null) {
            List<TopWidgetDataNew.Detail> list = this.g.get(str).f20739a;
            int size = list.size() - 1;
            while (size >= 0) {
                if (list.get(size) == null || TextUtils.isEmpty(list.get(size).b) || SmartHomeDeviceManager.a().b(list.get(size).b) != null) {
                    size--;
                } else {
                    return;
                }
            }
        }
    }

    private TopWidgetDataManagerNew(Context context) {
        this.e = context.getSharedPreferences(d, 0);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.o, intentFilter);
        SmartHomeDeviceManager.a().a(this.f20724a);
        b(HomeManager.a().l());
    }

    public void a() {
        if (!TextUtils.isEmpty(HomeManager.a().l())) {
            d(HomeManager.a().l());
        }
        d();
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.o);
        SmartHomeDeviceManager.a().c(this.f20724a);
    }

    public static TopWidgetDataManagerNew b() {
        if (f == null) {
            synchronized (TopWidgetDataManagerNew.class) {
                if (f == null) {
                    f = new TopWidgetDataManagerNew(SHApplication.getApplication());
                }
            }
        }
        return f;
    }

    /* access modifiers changed from: private */
    public void b(String str, AsyncCallback<TopWidgetDataNew, Error> asyncCallback) {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (this.g.containsKey(str)) {
            currentTimeMillis = this.g.get(str).b;
        }
        a(str, currentTimeMillis, 0, asyncCallback);
    }

    private void c(String str, AsyncCallback<TopWidgetDataNew, Error> asyncCallback) {
        if (SHApplication.getStateNotifier().a() == 4) {
            a(str, (int) (System.currentTimeMillis() / 1000), 0, asyncCallback);
        }
    }

    private void a(int i2, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.equals(this.j, str)) {
                this.l = false;
                this.k = false;
            }
            if (!this.l) {
                this.k = false;
                int i3 = 1;
                this.l = true;
                int i4 = i2 / 150;
                if (i2 % 150 == 0) {
                    i3 = 0;
                }
                this.i = i4 + i3;
                this.h = this.i;
                this.j = str;
                if (this.h > 0) {
                    c(this.j, this.r);
                }
            }
        }
    }

    public void c() {
        if (TextUtils.isEmpty(HomeManager.a().l())) {
            LogUtil.a("TopWidgetDataManagerNew", "home id is null!" + System.currentTimeMillis());
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.p, new IntentFilter(HomeManager.t));
            return;
        }
        b(HomeManager.a().l());
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        this.k = false;
        if (this.g.containsKey(str)) {
            this.g.remove(str);
        }
        a(false);
    }

    /* access modifiers changed from: private */
    public void a(String str, TopWidgetDataNew topWidgetDataNew) {
        this.k = false;
        if (this.g.containsKey(str)) {
            this.g.remove(str);
        }
        if (topWidgetDataNew != null) {
            if (this.g.containsKey(str)) {
                if (!(topWidgetDataNew == null || topWidgetDataNew.f20739a == null)) {
                    for (TopWidgetDataNew.Detail next : topWidgetDataNew.f20739a) {
                        if (!(next == null || TextUtils.isEmpty(next.b) || SmartHomeDeviceManager.a().b(next.b) == null)) {
                            this.g.get(str).f20739a.add(next);
                        }
                    }
                }
                this.g.get(str).b = topWidgetDataNew.b;
            } else {
                this.g.put(str, topWidgetDataNew);
            }
            this.g.get(str).a();
        }
        a(true);
    }

    private AsyncHandle a(String str, int i2, int i3, AsyncCallback<TopWidgetDataNew, Error> asyncCallback) {
        if (this.k) {
            return null;
        }
        this.k = true;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("home_id", Long.parseLong(str));
            jSONObject.put("timestamp", i2);
            jSONObject.put("limit", 150);
            jSONObject.put("tag", i3);
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/home/get_device_description").b((List<KeyValuePair>) arrayList).a(), new JsonParser<TopWidgetDataNew>() {
            /* renamed from: a */
            public TopWidgetDataNew parse(JSONObject jSONObject) throws JSONException {
                return TopWidgetDataNew.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, int i2, int i3, List<String> list, AsyncCallback<TopWidgetDataNew, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("home_id", Long.parseLong(str));
            jSONObject.put("timestamp", i2);
            jSONObject.put("limit", 150);
            jSONObject.put("tag", i3);
            Home j2 = HomeManager.a().j(str);
            if (j2 != null && !j2.p()) {
                jSONObject.put("owner_id", j2.o());
            }
            if (list != null && !list.isEmpty()) {
                jSONObject.put("prop_event_device", new JSONArray(list));
            }
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/home/get_device_description").b((List<KeyValuePair>) arrayList).a(), new JsonParser<TopWidgetDataNew>() {
            /* renamed from: a */
            public TopWidgetDataNew parse(JSONObject jSONObject) throws JSONException {
                return TopWidgetDataNew.a(jSONObject);
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, int i2, List<String> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("home_id", Long.parseLong(str));
            Home j2 = HomeManager.a().j(str);
            if (j2 != null && !j2.p()) {
                jSONObject.put("owner_id", j2.o());
            }
            jSONObject.put("timestamp", i2);
            if (list != null && !list.isEmpty()) {
                jSONObject.put("prop_event_device", new JSONArray(list));
            }
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/home/get_env_data").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HomeManager.E, Long.parseLong(str));
        } catch (JSONException unused) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/get_env_device").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, List<EnvDevice> list, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HomeManager.E, Long.parseLong(str));
            JSONArray jSONArray = new JSONArray();
            if (list != null && !list.isEmpty()) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    EnvDevice envDevice = list.get(i2);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("prop_name", envDevice.f20709a);
                    jSONObject2.put("did", envDevice.b);
                    jSONArray.put(jSONObject2);
                }
            }
            jSONObject.put("default_info", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/homeroom/set_env_device").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void d() {
        this.q.removeMessages(1);
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("user_banner_prop");
        try {
            jSONObject.put("pushId", SHApplication.getPushManager().g());
            jSONObject.put(j.b, jSONArray);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/mipush/user_event_unsub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, (AsyncCallback) null);
    }

    public void e() {
        a(180, (SubscribeCallback) new SubscribeCallback() {
            public void a(Error error) {
            }

            public void a(String str, String str2, JSONArray jSONArray) {
            }

            public void a(String str) {
                TopWidgetDataManagerNew.this.q.removeMessages(1);
                TopWidgetDataManagerNew.this.q.sendMessageDelayed(TopWidgetDataManagerNew.this.q.obtainMessage(1), 180000);
            }
        });
    }

    private void a(int i2, final SubscribeCallback subscribeCallback) {
        if (subscribeCallback != null) {
            if (!CoreApi.a().q()) {
                subscribeCallback.a(new Error(-1, "not logged in"));
            } else if (i2 <= 0) {
                subscribeCallback.a(new Error(-1, "expire <=0"));
            } else {
                if (i2 > 180) {
                    i2 = 180;
                }
                a(SHApplication.getAppContext(), i2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject != null && subscribeCallback != null) {
                            subscribeCallback.a(jSONObject.toString());
                        }
                    }

                    public void onFailure(Error error) {
                        if (subscribeCallback != null) {
                            subscribeCallback.a(error);
                        }
                    }
                });
            }
        }
    }

    private void a(Context context, int i2, AsyncCallback<JSONObject, Error> asyncCallback) {
        if (TextUtils.isEmpty(SHApplication.getPushManager().g())) {
            this.q.removeMessages(1);
            this.q.sendEmptyMessageDelayed(1, 500);
            return;
        }
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("user_banner_prop");
        try {
            jSONObject.put("pushId", SHApplication.getPushManager().g());
            jSONObject.put("expire", i2);
            jSONObject.put(j.b, jSONArray);
            jSONObject.put(OpenSdkPlayStatisticUpload.r, 0);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/mipush/user_event_sub").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public void a(OnDataChangeListener onDataChangeListener) {
        if (!this.s.contains(onDataChangeListener)) {
            this.s.add(onDataChangeListener);
        }
    }

    public void b(OnDataChangeListener onDataChangeListener) {
        this.s.remove(onDataChangeListener);
    }

    private void g() {
        this.s.clear();
    }

    private void a(boolean z) {
        for (OnDataChangeListener next : this.s) {
            if (next != null) {
                next.a(z);
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(String str) {
        this.e.edit().putString("home_id", str).apply();
        TopWidgetDataNew topWidgetDataNew = this.g.get(HomeManager.a().l());
        if (topWidgetDataNew != null) {
            this.e.edit().putString("data", topWidgetDataNew.b().toString()).apply();
        }
    }

    public void a(List<TopWidgetDataNew.Detail> list) {
        Home q2;
        TopWidgetDataNew topWidgetDataNew;
        for (TopWidgetDataNew.Detail next : list) {
            if (!TextUtils.isEmpty(next.b) && (q2 = HomeManager.a().q(next.b)) != null && !TextUtils.isEmpty(q2.j())) {
                if (this.g.containsKey(q2.j())) {
                    topWidgetDataNew = this.g.get(q2.j());
                } else {
                    topWidgetDataNew = new TopWidgetDataNew();
                }
                topWidgetDataNew.f20739a.addAll(list);
                topWidgetDataNew.a();
                this.g.put(q2.j(), topWidgetDataNew);
            }
        }
        a(true);
    }

    public List<TopWidgetDataNew.Detail> a(String str) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.g.get(str) == null) {
            return new ArrayList();
        }
        for (int size = this.g.get(str).f20739a.size() - 1; size >= 0; size--) {
            if (this.g.get(str).f20739a.get(size).e != null && this.g.get(str).f20739a.get(size).e.d > 0 && currentTimeMillis - ((long) this.g.get(str).f20739a.get(size).c) > this.g.get(str).f20739a.get(size).e.d) {
                this.g.get(str).f20739a.remove(size);
            }
        }
        return this.g.get(str).f20739a;
    }
}
