package com.xiaomi.smarthome.miio.consumables;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.redpoint.RedPointController;
import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.miio.consumables.ConsumableDataManager;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsumableDataManager {

    /* renamed from: a  reason: collision with root package name */
    private static final int f13546a = 2;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 3;
    private static final int g = 1;
    private static final int h = 2;
    private static final long i = 604800000;
    private static final String j = "consumable_cache";
    private static final String k = "version";
    private static final String l = "timestamp";
    private static final String m = "_consumable_list";
    private static final int n = 1;
    private static volatile ConsumableDataManager o;
    /* access modifiers changed from: private */
    public RedPointController p;
    private SharedPreferences q;
    /* access modifiers changed from: private */
    public HashMap<String, List<DeviceConsumble>> r = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<String, List<DeviceConsumble>> s = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<String, Integer> t = new HashMap<>();
    private Handler u;
    private Handler v;
    private HandlerThread w = new MessageHandlerThread("ConsumableDataManager");
    private BroadcastReceiver x = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("action_on_login_success".equals(action)) {
                ConsumableDataManager.this.d();
            } else if ("action_on_logout".equals(action)) {
                ConsumableDataManager.this.c();
            }
        }
    };
    /* access modifiers changed from: private */
    public Set<String> y = new HashSet();

    public interface IConsumableListener {
        void a(int i, String str, List<DeviceConsumble> list);

        void a(String str);

        void a(String str, String str2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject a(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject b(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject c(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.v.sendEmptyMessage(i2);
    }

    private ConsumableDataManager() {
        this.w.start();
        this.u = new Handler(this.w.getLooper()) {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1:
                        String str = (String) message.obj;
                        JSONArray jSONArray = null;
                        ArrayList arrayList = new ArrayList();
                        try {
                            jSONArray = new JSONArray(str);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (jSONArray != null) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                arrayList.add(Home.a(jSONArray.optJSONObject(i)));
                            }
                        }
                        ConsumableDataManager.this.a((List<Home>) arrayList);
                        ConsumableDataManager.this.a(1);
                        return;
                    case 2:
                        String str2 = (String) message.obj;
                        if (!TextUtils.isEmpty(str2)) {
                            String[] split = str2.split("%%%");
                            if (split.length > 0) {
                                String str3 = split[0];
                                if (str3.length() + 3 < str2.length()) {
                                    ConsumableDataManager.this.a(str3, str2.substring(str3.length() + 3));
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.v = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                List<Home> f = HomeManager.a().f();
                switch (message.what) {
                    case 1:
                        for (int i = 0; i < f.size(); i++) {
                            String j = f.get(i).j();
                            if (!ConsumableDataManager.this.t.containsKey(j)) {
                                ConsumableDataManager.this.t.put(j, -1);
                            }
                            if (((Integer) ConsumableDataManager.this.t.get(f.get(i).j())).intValue() <= 0) {
                                LogUtil.a("ConsumableData", "data from cache to mData");
                                if (ConsumableDataManager.this.r.containsKey(j)) {
                                    ((List) ConsumableDataManager.this.r.get(j)).clear();
                                } else {
                                    ConsumableDataManager.this.r.put(j, new ArrayList());
                                }
                                if (ConsumableDataManager.this.s.containsKey(j)) {
                                    ((List) ConsumableDataManager.this.r.get(j)).addAll((Collection) ConsumableDataManager.this.s.get(j));
                                }
                                if (ConsumableDataManager.this.t.containsKey(j)) {
                                    ConsumableDataManager.this.t.remove(j);
                                }
                                ConsumableDataManager.this.t.put(j, 0);
                            }
                        }
                        return;
                    case 2:
                        break;
                    default:
                        return;
                }
                for (int i2 = 0; i2 < f.size(); i2++) {
                    String j2 = f.get(i2).j();
                    if (!ConsumableDataManager.this.t.containsKey(j2)) {
                        ConsumableDataManager.this.t.put(j2, -1);
                    }
                    if (((Integer) ConsumableDataManager.this.t.get(j2)).intValue() <= 0) {
                        LogUtil.a("ConsumableData", "读缓存，有展示机会");
                        ConsumableDataManager.this.d();
                        return;
                    }
                }
            }
        };
        d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.x, intentFilter);
    }

    /* access modifiers changed from: private */
    public void d() {
        Message obtainMessage = this.u.obtainMessage();
        obtainMessage.what = 1;
        List<Home> f2 = HomeManager.a().f();
        JSONArray jSONArray = new JSONArray();
        if (f2 != null) {
            for (int i2 = 0; i2 < f2.size(); i2++) {
                jSONArray.put(f2.get(i2).q());
            }
        }
        obtainMessage.obj = jSONArray.toString();
        this.u.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: private */
    public void a(List<Home> list) {
        JSONArray optJSONArray;
        if (!list.isEmpty() && !TextUtils.isEmpty(CoreApi.a().s())) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                String j2 = list.get(i2).j();
                try {
                    String a2 = MD5Util.a(CoreApi.a().s());
                    if (!TextUtils.isEmpty(a2)) {
                        Context appContext = SHApplication.getAppContext();
                        this.q = appContext.getSharedPreferences(a2 + j2 + m, 0);
                    }
                } catch (Exception unused) {
                }
                if (this.q != null) {
                    String string = this.q.getString(j, "");
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            JSONObject jSONObject = new JSONObject(string);
                            if (jSONObject.has("items") && (optJSONArray = jSONObject.optJSONArray("items")) != null) {
                                if (this.s.containsKey(j2)) {
                                    this.s.get(j2).clear();
                                } else {
                                    this.s.put(j2, new ArrayList());
                                }
                                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                                    DeviceConsumble a3 = DeviceConsumble.a(optJSONArray.optJSONObject(i3));
                                    if (a3 != null) {
                                        this.s.get(j2).add(a3);
                                    }
                                }
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    } else if (this.s.containsKey(j2)) {
                        this.s.get(j2).clear();
                    } else {
                        this.s.put(j2, new ArrayList());
                    }
                }
            }
        }
    }

    private List<DeviceConsumble> b(String str) {
        JSONArray optJSONArray;
        if (TextUtils.isEmpty(CoreApi.a().s()) || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.q = appContext.getSharedPreferences(a2 + str + m, 0);
            }
        } catch (Exception unused) {
        }
        if (this.q == null) {
            return null;
        }
        String string = this.q.getString(j, "");
        if (TextUtils.isEmpty(string)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("items") && (optJSONArray = jSONObject.optJSONArray("items")) != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    DeviceConsumble a3 = DeviceConsumble.a(optJSONArray.optJSONObject(i2));
                    if (a3 != null) {
                        arrayList.add(a3);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(CoreApi.a().s()) && !TextUtils.isEmpty(str)) {
            try {
                String a2 = MD5Util.a(CoreApi.a().s());
                if (!TextUtils.isEmpty(a2)) {
                    Context appContext = SHApplication.getAppContext();
                    this.q = appContext.getSharedPreferences(a2 + str + m, 0);
                }
            } catch (Exception unused) {
            }
            if (this.q != null && !TextUtils.isEmpty(str2)) {
                SharedPreferences.Editor edit = this.q.edit();
                edit.putString(j, str2);
                edit.putInt("version", 1);
                edit.apply();
                this.v.sendEmptyMessage(2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, List<DeviceConsumble> list) {
        if (list != null) {
            List<DeviceConsumble> b2 = b(str);
            if (b2 != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (list.get(size).d) {
                        for (int i2 = 0; i2 < b2.size(); i2++) {
                            if (TextUtils.equals(b2.get(i2).c, list.get(size).c)) {
                                list.remove(size);
                                list.add(size, b2.get(i2));
                            }
                        }
                    }
                }
            }
            if (list != null && list.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i3 = 0; i3 < list.size(); i3++) {
                    JSONObject a2 = DeviceConsumble.a(list.get(i3));
                    if (a2 != null) {
                        jSONArray.put(a2);
                    }
                }
                LogUtil.a("ConsumableData", "写入缓存的大小：【 " + jSONArray.length() + " 】");
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("items", jSONArray);
                    Message obtainMessage = this.u.obtainMessage();
                    obtainMessage.what = 2;
                    obtainMessage.obj = str + "%%%" + jSONObject.toString();
                    this.u.sendMessage(obtainMessage);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static ConsumableDataManager a() {
        if (o == null) {
            synchronized (ConsumableDataManager.class) {
                o = new ConsumableDataManager();
            }
        }
        return o;
    }

    public void b() {
        try {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.q = appContext.getSharedPreferences(a2 + m, 0);
            }
        } catch (Exception unused) {
        }
        if (this.q != null) {
            SharedPreferences.Editor edit = this.q.edit();
            edit.putLong("timestamp", System.currentTimeMillis());
            edit.apply();
        }
    }

    private boolean e() {
        try {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.q = appContext.getSharedPreferences(a2 + m, 0);
            }
        } catch (Exception unused) {
        }
        if (this.q == null) {
            return true;
        }
        if (System.currentTimeMillis() - this.q.getLong("timestamp", 0) >= 604800000) {
            return true;
        }
        return false;
    }

    private void f() {
        try {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.q = appContext.getSharedPreferences(a2 + m, 0);
            }
        } catch (Exception unused) {
        }
        if (this.q != null) {
            SharedPreferences.Editor edit = this.q.edit();
            edit.remove("timestamp");
            edit.apply();
        }
    }

    public void a(Context context) {
        final boolean e2 = e();
        if (e2) {
            f();
        }
        g();
        if (SHApplication.getStateNotifier().a() == 4) {
            a(context, HomeManager.a().l(), true, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean z;
                    JSONArray optJSONArray;
                    if (!jSONObject.has("items") || (optJSONArray = jSONObject.optJSONArray("items")) == null) {
                        z = false;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        z = false;
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            DeviceConsumble a2 = DeviceConsumble.a(optJSONArray.optJSONObject(i));
                            if (a2 != null && a2.b != null) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= a2.b.size()) {
                                        break;
                                    } else if ((a2.b.get(i2).f13545a < 0.0d || a2.b.get(i2).f13545a > 5.0d) && !TextUtils.equals("LOW", a2.b.get(i2).b)) {
                                        i2++;
                                    }
                                }
                                z = true;
                            }
                            arrayList.add(a2);
                        }
                        LogUtil.a("ConsumableData", "小红点请求完毕");
                        ConsumableDataManager.this.a(HomeManager.a().l(), (List<DeviceConsumble>) arrayList);
                    }
                    if (!e2 || !z) {
                        if (ConsumableDataManager.this.p != null) {
                            ConsumableDataManager.this.p.a(RedPointManagerNew.g, false);
                        }
                    } else if (ConsumableDataManager.this.p != null) {
                        ConsumableDataManager.this.p.a(RedPointManagerNew.g, true);
                    }
                }

                public void onFailure(Error error) {
                    if (ConsumableDataManager.this.p != null) {
                        ConsumableDataManager.this.p.a(RedPointManagerNew.g, false);
                    }
                }
            });
        } else if (this.p != null) {
            this.p.a(RedPointManagerNew.g, false);
        }
    }

    private void g() {
        if (this.p == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(RedPointManagerNew.g);
            this.p = new RedPointController(arrayList, false);
        }
    }

    private void a(Context context, String str, boolean z, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(c.m, 2);
            Home j2 = HomeManager.a().j(str);
            jSONObject.put("skipRpc", z);
            if (j2 != null) {
                try {
                    jSONObject.put("home_id", Long.parseLong(j2.j()));
                } catch (Exception unused) {
                }
                jSONObject.put("owner_id", j2.o());
                arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                Context context2 = context;
                CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/home/consumable_items").b((List<KeyValuePair>) arrayList).a(), $$Lambda$ConsumableDataManager$8_exb_ldtoOSK38BVG9t31smufI.INSTANCE, Crypto.RC4, asyncCallback);
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-1, "home is null"));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void b(Context context, String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        Home j2;
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put(c.m, 2);
            if (!TextUtils.isEmpty(str2) && (j2 = HomeManager.a().j(str2)) != null) {
                try {
                    jSONObject.put("home_id", Long.parseLong(j2.j()));
                } catch (Exception unused) {
                }
                jSONObject.put("owner_id", j2.o());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/home/device_consumable_items").b((List<KeyValuePair>) arrayList).a(), $$Lambda$ConsumableDataManager$pb1REiSzglVs2xeB00LVCNe2nc.INSTANCE, Crypto.RC4, asyncCallback);
    }

    public void a(Context context, String str, String str2, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put(XmBluetoothRecord.TYPE_PROP, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/v2/home/consume_reset").b((List<KeyValuePair>) arrayList).a(), $$Lambda$ConsumableDataManager$MODu0P0CjE2o5LhHQS95WNfaV7c.INSTANCE, Crypto.RC4, asyncCallback);
    }

    /* access modifiers changed from: private */
    public void a(Context context, String str, String str2, IConsumableListener iConsumableListener, int i2) {
        if (!TextUtils.isEmpty(str)) {
            if (i2 == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("3次rpc请求，【 ");
                sb.append(SmartHomeDeviceManager.a().b(str2) == null ? "null" : SmartHomeDeviceManager.a().b(str2).name);
                sb.append("】 数据失败!!!!!!!");
                LogUtil.a("ConsumableData", sb.toString());
                this.y.remove(str2);
                if (this.y.size() == 0 && iConsumableListener != null) {
                    iConsumableListener.a(3, str, this.r.get(str));
                    iConsumableListener.a(str);
                    return;
                }
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(SmartHomeDeviceManager.a().b(str2) == null ? "null" : SmartHomeDeviceManager.a().b(str2).name);
            sb2.append("开始  第【 ");
            sb2.append((3 - i2) + 1);
            sb2.append(" 】次 RPC请求");
            LogUtil.a("ConsumableData", sb2.toString());
            final String str3 = str2;
            final int i3 = i2;
            final Context context2 = context;
            final String str4 = str;
            final IConsumableListener iConsumableListener2 = iConsumableListener;
            b(context, str2, str, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    DeviceConsumble a2 = DeviceConsumble.a(jSONObject);
                    if (a2 == null || a2.d) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("------");
                        sb.append(SmartHomeDeviceManager.a().b(str3) == null ? "null" : SmartHomeDeviceManager.a().b(str3).name);
                        sb.append("第【 ");
                        sb.append((3 - i3) + 1);
                        sb.append(" 】次 RPC请求到无效数据");
                        LogUtil.a("ConsumableData", sb.toString());
                        ConsumableDataManager.this.a(context2, str4, str3, iConsumableListener2, i3 - 1);
                        return;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("------");
                    sb2.append(SmartHomeDeviceManager.a().b(str3) == null ? "null" : SmartHomeDeviceManager.a().b(str3).name);
                    sb2.append("第【 ");
                    sb2.append((3 - i3) + 1);
                    sb2.append(" 】次 RPC请求到数据");
                    LogUtil.a("ConsumableData", sb2.toString());
                    ConsumableDataManager.this.a(str4, a2);
                    if (iConsumableListener2 != null) {
                        iConsumableListener2.a(2, str4, (List) ConsumableDataManager.this.r.get(str4));
                    }
                    ConsumableDataManager.this.y.remove(str3);
                    if (ConsumableDataManager.this.y.size() == 0 && iConsumableListener2 != null) {
                        iConsumableListener2.a(3, str4, (List) ConsumableDataManager.this.r.get(str4));
                        iConsumableListener2.a(str4);
                    }
                }

                public void onFailure(Error error) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(SmartHomeDeviceManager.a().b(str3) == null ? "null" : SmartHomeDeviceManager.a().b(str3).name);
                    sb.append("第【 ");
                    sb.append((3 - i3) + 1);
                    sb.append(" 】次 RPC请求失败！（网络等）");
                    LogUtil.a("ConsumableData", sb.toString());
                    ConsumableDataManager.this.a(context2, str4, str3, iConsumableListener2, i3 - 1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, DeviceConsumble deviceConsumble) {
        if (this.r.containsKey(str)) {
            List list = this.r.get(str);
            int i2 = -1;
            int i3 = 0;
            while (true) {
                if (i3 >= list.size()) {
                    break;
                } else if (TextUtils.equals(((DeviceConsumble) list.get(i3)).c, deviceConsumble.c)) {
                    list.remove(i3);
                    i2 = i3;
                    break;
                } else {
                    i3++;
                }
            }
            if (i2 >= 0 && i2 < list.size()) {
                list.add(i2, deviceConsumble);
            } else if (i2 == list.size()) {
                list.add(deviceConsumble);
            }
        }
    }

    public void a(final Context context, final String str, boolean z, final IConsumableListener iConsumableListener) {
        this.y.clear();
        Observable.create(new ObservableOnSubscribe(iConsumableListener, str, context, z) {
            private final /* synthetic */ ConsumableDataManager.IConsumableListener f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ Context f$3;
            private final /* synthetic */ boolean f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                ConsumableDataManager.this.a(this.f$1, this.f$2, this.f$3, this.f$4, observableEmitter);
            }
        }).subscribe(new Observer<String>() {
            public void onSubscribe(Disposable disposable) {
            }

            /* renamed from: a */
            public void onNext(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(SmartHomeDeviceManager.a().b(str) == null ? "null" : SmartHomeDeviceManager.a().b(str).name);
                sb.append(" 进入rpc请求步骤");
                LogUtil.a("ConsumableData", sb.toString());
                ConsumableDataManager.this.a(context, str, str, iConsumableListener, 3);
            }

            public void onError(Throwable th) {
                LogUtil.a("ConsumableData", "请求列表出错");
                if (iConsumableListener != null) {
                    iConsumableListener.a(th.getMessage(), str);
                    iConsumableListener.a(str);
                }
            }

            public void onComplete() {
                LogUtil.a("ConsumableData", "refresh complete");
                ConsumableDataManager.this.a(str, (List<DeviceConsumble>) (List) ConsumableDataManager.this.r.get(str));
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(IConsumableListener iConsumableListener, String str, Context context, boolean z, ObservableEmitter observableEmitter) throws Exception {
        if (iConsumableListener != null) {
            List list = this.r.get(str);
            iConsumableListener.a(0, str, list == null ? new ArrayList() : list);
            if (!this.t.containsKey(str)) {
                this.t.put(str, -1);
            }
            if (this.t.get(str).intValue() >= 0 && list != null && list.size() > 0) {
                iConsumableListener.a(str);
            }
        }
        LogUtil.a("ConsumableData", "URL onSubscribe()");
        final boolean z2 = z;
        final ObservableEmitter observableEmitter2 = observableEmitter;
        final String str2 = str;
        final IConsumableListener iConsumableListener2 = iConsumableListener;
        a(context, str, z, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                JSONArray optJSONArray;
                ArrayList arrayList = new ArrayList();
                if (jSONObject.has("items") && (optJSONArray = jSONObject.optJSONArray("items")) != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        DeviceConsumble a2 = DeviceConsumble.a(optJSONArray.optJSONObject(i));
                        Device b2 = SmartHomeDeviceManager.a().b(a2.c);
                        if (b2 == null || (b2 != null && b2.isOnline)) {
                            a2.f = true;
                        } else {
                            a2.f = false;
                        }
                        if (a2 != null) {
                            arrayList.add(a2);
                            if (!z2 && a2.d && a2.f) {
                                ConsumableDataManager.this.y.add(a2.c);
                                observableEmitter2.onNext(a2.c);
                            }
                        }
                    }
                    if (ConsumableDataManager.this.t.containsKey(str2)) {
                        ConsumableDataManager.this.t.remove(str2);
                    }
                    ConsumableDataManager.this.t.put(str2, 1);
                }
                if (ConsumableDataManager.this.y.size() == 0) {
                    LogUtil.a("ConsumableData", "无需rpc请求");
                    if (ConsumableDataManager.this.r.containsKey(str2)) {
                        ((List) ConsumableDataManager.this.r.get(str2)).clear();
                    } else {
                        ConsumableDataManager.this.r.put(str2, new ArrayList());
                    }
                    ((List) ConsumableDataManager.this.r.get(str2)).addAll(arrayList);
                    observableEmitter2.onComplete();
                } else {
                    if (ConsumableDataManager.this.r.containsKey(str2)) {
                        ((List) ConsumableDataManager.this.r.get(str2)).clear();
                    } else {
                        ConsumableDataManager.this.r.put(str2, new ArrayList());
                    }
                    List list = (List) ConsumableDataManager.this.r.get(str2);
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        if (((DeviceConsumble) arrayList.get(size)).d) {
                            for (int i2 = 0; i2 < list.size(); i2++) {
                                if (TextUtils.equals(((DeviceConsumble) list.get(i2)).c, ((DeviceConsumble) arrayList.get(size)).c)) {
                                    arrayList.remove(size);
                                    arrayList.add(size, list.get(i2));
                                }
                            }
                        }
                    }
                    ((List) ConsumableDataManager.this.r.get(str2)).clear();
                    ((List) ConsumableDataManager.this.r.get(str2)).addAll(arrayList);
                }
                if (iConsumableListener2 != null) {
                    iConsumableListener2.a(1, str2, (List) ConsumableDataManager.this.r.get(str2));
                }
                if (iConsumableListener2 != null) {
                    iConsumableListener2.a(str2);
                }
            }

            public void onFailure(Error error) {
                ObservableEmitter observableEmitter = observableEmitter2;
                observableEmitter.onError(new Exception(error.b() + " when data from " + 1));
            }
        });
    }

    public List<DeviceConsumble> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList();
        }
        if (this.r.containsKey(str)) {
            return this.r.get(str);
        }
        return new ArrayList();
    }

    public void c() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.x);
        this.p = null;
        o = null;
    }
}
