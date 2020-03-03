package com.xiaomi.smarthome.newui.card;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.file.FileUtil;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardAbstractManager;
import com.xiaomi.smarthome.newui.card.DeviceCardApi;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import com.xiaomi.smarthome.newui.card.profile.ProfileCardType;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.service.CardActive;
import com.xiaomi.smarthome.stat.STAT;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ControlCardInfoManager extends CardAbstractManager {
    public static final int c = 2;
    public static final String d = "card_info_ready";
    public static final String e = "did";
    public static final String f = "action_water_record_update";
    public static final String g = "event.pure_water_record";
    public static final String h = "light_device_card_info_ready";
    private static final int i = 8;
    private static final String j = "card_control_config";
    private static final String k = "12";
    private static final String l = "ControlCardInfoManager";
    private static final int m = 100;
    private static final int n = 201;
    private static final int o = 300;
    private static final String p = "CardItemIcons_old3x";
    private static ControlCardInfoManager q = null;
    private static final String y = "mijia_card_config.txt";
    private BroadcastReceiver A = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("action_on_login_success".equals(intent.getAction())) {
                ControlCardInfoManager.this.i();
            }
        }
    };
    private SmartHomeDeviceManager.IClientDeviceListener B = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            LogUtil.b("mijia-card", "onRefreshClientDeviceSuccess type:" + i + "  ");
            if (i == 3 && ServiceApplication.getStateNotifier().a() == 4) {
                ControlCardInfoManager.this.a(true, (AsyncCallback<Object, Error>) null, "onRefreshClientDeviceSuccess");
            }
        }
    };
    private final AppConfigHelper r;
    /* access modifiers changed from: private */
    public Map<String, ProfileCard> s = new HashMap();
    private DevicePropSubscriber t;
    private WorkerHandler u;
    private final Object v = new Object();
    private AtomicBoolean w = new AtomicBoolean(false);
    private Map<String, Boolean> x = new HashMap();
    private Set<OnLoadListener> z = new HashSet();

    public interface OnLoadListener {
        void a();
    }

    public static ControlCardInfoManager f() {
        if (q == null) {
            synchronized (ControlCardInfoManager.class) {
                if (q == null) {
                    q = new ControlCardInfoManager();
                }
            }
        }
        return q;
    }

    private ControlCardInfoManager() {
        if (GlobalSetting.q || GlobalSetting.s) {
            GlobalSetting.F = SharePrefsManager.b(CommonApplication.getAppContext(), AppConfigHelper.f13358a, AppConfigHelper.c, false);
        }
        MessageHandlerThread messageHandlerThread = new MessageHandlerThread(l);
        messageHandlerThread.start();
        this.u = new WorkerHandler(messageHandlerThread.getLooper());
        Context appContext = CommonApplication.getAppContext();
        this.r = new AppConfigHelper(appContext);
        if (appContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("action_on_login_success");
            intentFilter.addAction("action_on_logout");
            LocalBroadcastManager.getInstance(appContext).registerReceiver(this.A, intentFilter);
            SmartHomeDeviceManager.a().a(this.B);
            LogUtil.c("mijia-card", " ConfigCardVersion:8");
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        this.r.a("card_default_resource", "1", "en", "cardControl/cardLanguageConfig.json", new AppConfigHelper.OnCacheHandler<JSONObject>() {
            public boolean a(JSONObject jSONObject) throws Exception {
                return ControlCardInfoManager.this.b(jSONObject);
            }

            public boolean b(JSONObject jSONObject) throws Exception {
                return ControlCardInfoManager.this.b(jSONObject);
            }
        }, new AppConfigHelper.JsonAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject, Response response) {
                boolean unused = ControlCardInfoManager.this.b(jSONObject);
                LogUtil.c("mijia-card", "ControlCardInfoManager.loadImage image path:" + ControlCardInfoManager.this.d());
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                LogUtil.b("mijia-card", "MiotSpecCardManager.loadLanguageConfig onFailure");
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean b(JSONObject jSONObject) {
        JSONObject jSONObject2;
        try {
            String optString = jSONObject.optJSONObject("result").optString("content");
            if (!AppConfigHelper.a(optString)) {
                return false;
            }
            JSONObject optJSONObject = new JSONObject(optString).optJSONObject("download_url");
            if (optJSONObject == null) {
                jSONObject2 = null;
            } else {
                jSONObject2 = optJSONObject.optJSONObject(p);
            }
            if (jSONObject2 != null) {
                a((Handler) this.u, jSONObject2);
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e("mijia-card", "parseImage", th);
        }
    }

    public String d() {
        return super.d() + CardItemUtils.a(p) + File.separator;
    }

    private class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            String str;
            boolean z;
            super.handleMessage(message);
            if (CommonApplication.getAppContext() != null) {
                int i = message.what;
                if (i == 100) {
                    LogUtil.c("mijia-card", "ControlCardInfoManager.handleMessage refresh");
                    ControlCardInfoManager.this.a();
                    ControlCardInfoManager.this.n();
                    ControlCardInfoManager.this.m();
                } else if (i == 201) {
                    LogUtil.c("mijia-card", "ControlCardInfoManager.handleMessage loadprops");
                    AsyncCallback asyncCallback = null;
                    if (message.obj == null || Array.getLength(message.obj) != 3) {
                        str = null;
                        z = true;
                    } else {
                        z = ((Boolean) ((Object[]) message.obj)[0]).booleanValue();
                        asyncCallback = (AsyncCallback) ((Object[]) message.obj)[1];
                        str = (String) ((Object[]) message.obj)[2];
                    }
                    ControlCardInfoManager.this.a((AsyncCallback<Object, Error>) asyncCallback, str);
                    if (z) {
                        ControlCardInfoManager.this.o();
                    }
                }
            }
        }
    }

    public ProfileCard e(String str) {
        if (GlobalSetting.F) {
            return null;
        }
        return this.s.get(str);
    }

    public Map<String, Map<String, Object>> g() {
        return this.f20467a;
    }

    public Map<String, Map<String, Long>> h() {
        return this.b;
    }

    public void i() {
        this.u.sendEmptyMessage(100);
    }

    /* access modifiers changed from: private */
    public void n() {
        this.r.a(j, k, "zh_CN", "cardControl/configDes.json", new AppConfigHelper.OnCacheHandler<JSONObject>() {
            public boolean a(JSONObject jSONObject) throws Exception {
                String optString = jSONObject.optJSONObject("result").optString("content");
                if (!AppConfigHelper.a(optString)) {
                    return false;
                }
                JSONArray jSONArray = new JSONArray(optString);
                if (jSONArray.length() == 0) {
                    return false;
                }
                ControlCardInfoManager.this.s.clear();
                ControlCardInfoManager.this.s.putAll(ControlCardInfoManager.this.a(jSONArray));
                ControlCardInfoManager.this.l();
                LogUtil.c("mijia-card", "ControlCardInfoManager.loadConfigFromServer processCache mAllCardInfo.size:" + ControlCardInfoManager.this.s.size());
                return true;
            }

            public boolean b(JSONObject jSONObject) throws Exception {
                return a(jSONObject);
            }
        }, new AppConfigHelper.JsonAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject, Response response) {
                try {
                    String optString = jSONObject.optJSONObject("result").optString("content");
                    if (AppConfigHelper.a(optString)) {
                        JSONArray jSONArray = new JSONArray(optString);
                        if (jSONArray.length() != 0) {
                            ControlCardInfoManager.this.s.clear();
                            ControlCardInfoManager.this.s.putAll(ControlCardInfoManager.this.a(jSONArray));
                            LogUtil.c("mijia-card", "ControlCardInfoManager.loadConfigFromServer onSuccess mAllCardInfo.size:" + ControlCardInfoManager.this.s.size());
                            ControlCardInfoManager.this.l();
                        }
                    }
                } catch (Exception e) {
                    LogUtil.b("mijia-card", Log.getStackTraceString(e));
                }
                ControlCardInfoManager.this.j();
                ControlCardInfoManager.this.a(true, (AsyncCallback<Object, Error>) null, "processResponse");
                ControlCardInfoManager.this.q();
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                ControlCardInfoManager.this.j();
                ControlCardInfoManager.this.l();
                ControlCardInfoManager.this.q();
                LogUtil.b("mijia-card", "ControlCardInfoManager.loadConfigFromServer onFailure");
            }
        });
    }

    public Map<String, ProfileCard> j() {
        String str;
        if (!GlobalSetting.r && DevelopSharePreManager.a().e()) {
            File file = new File(Environment.getExternalStorageDirectory(), File.separator + y);
            if (file.exists()) {
                try {
                    str = new String(FileUtil.d(CommonApplication.getAppContext(), Uri.fromFile(file)));
                    try {
                        return f(str);
                    } catch (Exception e2) {
                        e = e2;
                        LogUtil.b("mijia-card", str + " " + Log.getStackTraceString(e));
                        return null;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str = null;
                    LogUtil.b("mijia-card", str + " " + Log.getStackTraceString(e));
                    return null;
                }
            }
        }
        return null;
    }

    public Map<String, ProfileCard> f(String str) {
        Map<String, ProfileCard> map;
        Exception e2;
        if (str == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(new JSONObject(str));
            map = a(jSONArray);
            try {
                LogUtil.b("mijia-card", "ControlCardInfoManager.loadSdcardConfig success models:" + map.keySet());
                this.s.putAll(map);
            } catch (Exception e3) {
                e2 = e3;
            }
        } catch (Exception e4) {
            e2 = e4;
            map = null;
            LogUtilGrey.a("mijia-card", Log.getStackTraceString(e2));
            return map;
        }
        return map;
    }

    /* access modifiers changed from: private */
    public Map<String, ProfileCard> a(JSONArray jSONArray) {
        HashMap hashMap = new HashMap();
        new HashMap();
        if (jSONArray == null || jSONArray.length() == 0) {
            return hashMap;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            ProfileCard profileCard = new ProfileCard();
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                ArrayList<String> arrayList = new ArrayList<>();
                JSONArray optJSONArray = optJSONObject.optJSONArray("models");
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    arrayList.add(optJSONArray.optString(i3));
                }
                HashMap hashMap2 = new HashMap();
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("props");
                if (optJSONArray2 != null) {
                    for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                        PropItem propItem = new PropItem(optJSONArray2.optJSONObject(i4));
                        if (propItem.f20557a != null) {
                            hashMap2.put(propItem.f20557a, propItem);
                        }
                    }
                }
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("cards");
                if (optJSONObject2 != null) {
                    profileCard.b = optJSONObject2.optInt("layout_type");
                    boolean z2 = true;
                    if (optJSONObject2.optInt("lowPower", 0) == 1) {
                        profileCard.g = true;
                    }
                    profileCard.e = optJSONObject2.optString("min_firmware_version");
                    if (optJSONObject2.optInt("need_ble_parse", 1) != 1) {
                        z2 = false;
                    }
                    profileCard.f = z2;
                    if (optJSONObject2.optInt("min_support_version", 0) <= 8) {
                        JSONArray optJSONArray3 = optJSONObject2.optJSONArray("card_items");
                        if (optJSONArray3 != null) {
                            profileCard.c.addAll(a((Map<String, PropItem>) hashMap2, optJSONArray3));
                        }
                        for (ProfileCardType profileCardType : profileCard.c) {
                            if (profileCardType.o != 0) {
                                profileCard.h.add(profileCardType);
                            }
                        }
                        JSONArray optJSONArray4 = optJSONObject2.optJSONArray("grid_items");
                        if (optJSONArray4 != null) {
                            profileCard.h = a((Map<String, PropItem>) hashMap2, optJSONArray4);
                            LogUtil.c("mijia-card", "ControlCardInfoManager.parse gridCardItems model:" + arrayList + " gridCardItems.size:" + profileCard.h.size());
                        }
                    } else {
                        LogUtil.b("mijia-card", "8 current version not support  model:" + arrayList);
                    }
                }
                for (String put : arrayList) {
                    hashMap.put(put, profileCard);
                }
            }
        }
        return hashMap;
    }

    private List<ProfileCardType> a(Map<String, PropItem> map, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(ProfileCardType.a(map, jSONArray.optJSONObject(i2)));
            }
            if (arrayList.size() == 0) {
                arrayList.add(new ProfileCardType(map, new JSONObject()));
            }
        }
        return arrayList;
    }

    public void a(final List<String> list, final AsyncCallback<Object, Error> asyncCallback, final String str) {
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                ControlCardInfoManager.this.a((AsyncCallback<Object, Error>) asyncCallback, str, (List<String>) list);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final AsyncCallback<Object, Error> asyncCallback, String str, List<String> list) {
        if (list != null && list.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (String b : list) {
                try {
                    Device b2 = SmartHomeDeviceManager.a().b(b);
                    if (b2 != null) {
                        ProfileCard e2 = e(b2.model);
                        if (e2 != null) {
                            List<ProfileCardType> list2 = e2.c;
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("did", list.get(0));
                            JSONArray jSONArray2 = new JSONArray();
                            for (ProfileCardType a2 : list2) {
                                PropItem a3 = a2.a();
                                if (a3 != null) {
                                    jSONArray2.put(a3.f20557a);
                                    if (a3.d.size() > 0) {
                                        for (String put : a3.d.keySet()) {
                                            jSONArray2.put(put);
                                        }
                                    }
                                }
                            }
                            jSONObject.put("props", jSONArray2);
                            jSONArray.put(jSONObject);
                        }
                    }
                } catch (JSONException e3) {
                    Log.e("mijia-card", AppMeasurement.Param.FATAL, e3);
                }
            }
            Context appContext = CommonApplication.getAppContext();
            AnonymousClass8 r1 = new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    LogUtil.c("mijia-card", "ControlCardInfoManager.batchGetDeviceProps result = " + str);
                    ControlCardInfoManager.this.g(str);
                    if (asyncCallback != null) {
                        asyncCallback.sendSuccessMessage(null);
                    }
                }

                public void onFailure(Error error) {
                    LogUtil.b("mijia-card", "ControlCardInfoManager.batchGetDeviceProps result error: " + error);
                }
            };
            DeviceCardApi.a(appContext, jSONArray, (AsyncCallback<String, Error>) r1, "ControlCardInfoManager:" + str);
        }
    }

    public void a(boolean z2, AsyncCallback<Object, Error> asyncCallback, String str) {
        Message obtain = Message.obtain();
        obtain.what = 201;
        obtain.obj = new Object[]{Boolean.valueOf(z2), asyncCallback, str};
        this.u.sendMessage(obtain);
    }

    /* access modifiers changed from: private */
    public void a(final AsyncCallback<Object, Error> asyncCallback, String str) {
        List<Device> f2 = SmartHomeDeviceManager.a().f();
        if (f2 == null || f2.size() == 0 || this.s.size() == 0) {
            q();
            if (asyncCallback != null) {
                asyncCallback.onSuccess(null);
            }
            LogUtil.c("mijia-card", "ControlCardInfoManager.loadPropsFromServer device size 0 or cardconfig size 0");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        final ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < f2.size()) {
            try {
                Device device = f2.get(i2);
                if (device != null && !TextUtils.isEmpty(device.did)) {
                    if (!TextUtils.isEmpty(device.model)) {
                        ProfileCard e2 = e(device.model);
                        if (e2 != null) {
                            List<ProfileCardType> list = e2.h;
                            if (list != null) {
                                if (list.size() != 0) {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("did", device.did);
                                    JSONArray jSONArray2 = new JSONArray();
                                    for (Card.CardType next : list) {
                                        jSONArray2.put(next.c);
                                        PropItem a2 = next.a();
                                        if (a2 != null && a2.d.size() > 0) {
                                            for (String put : a2.d.keySet()) {
                                                jSONArray2.put(put);
                                            }
                                        }
                                    }
                                    jSONObject.put("props", jSONArray2);
                                    jSONArray.put(jSONObject);
                                    if (jSONArray.length() >= 300) {
                                        arrayList.add(jSONArray);
                                        jSONArray = new JSONArray();
                                    }
                                }
                            }
                        }
                    }
                }
                i2++;
            } catch (JSONException e3) {
                LogUtil.b("mijia-card", Log.getStackTraceString(e3));
            }
        }
        if (jSONArray.length() > 0) {
            arrayList.add(jSONArray);
        }
        if (arrayList.size() == 0) {
            q();
            if (asyncCallback != null) {
                asyncCallback.onSuccess(null);
            }
            LogUtil.b("mijia-card", "ControlCardInfoManager.loadPropsFromServer rquest size  0");
            return;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(arrayList.size());
        LogUtil.c("mijia-card", "ControlCardInfoManager.loadPropsFromServer rquest size:" + atomicInteger.get());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Context appContext = CommonApplication.getAppContext();
            AnonymousClass9 r5 = new AsyncCallback<String, Error>() {
                private ArrayList<String> e = new ArrayList<>();
                private Error f;

                /* renamed from: a */
                public void onSuccess(String str) {
                    if (str != null) {
                        this.e.add(str);
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        LogUtil.c("mijia-card", "ControlCardInfoManager.loadPropsFromServer onSuccess size:" + this.e.size());
                        if (this.e.size() > 0) {
                            if (this.e.size() == arrayList.size()) {
                                ControlCardInfoManager.this.f20467a.clear();
                                ControlCardInfoManager.this.b.clear();
                            }
                            Iterator<String> it = this.e.iterator();
                            while (it.hasNext()) {
                                ControlCardInfoManager.this.g(it.next());
                            }
                            ControlCardInfoManager.this.p();
                            if (asyncCallback != null) {
                                asyncCallback.sendSuccessMessage(null);
                            }
                        } else if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(this.f);
                        }
                        ControlCardInfoManager.this.b();
                        ControlCardInfoManager.this.q();
                    }
                }

                public void onFailure(Error error) {
                    LogUtil.c("mijia-card", "ControlCardInfoManager.loadPropsFromServer onFailure index:" + atomicInteger.decrementAndGet());
                    this.f = error;
                    onSuccess((String) null);
                }
            };
            DeviceCardApi.a(appContext, (JSONArray) it.next(), (AsyncCallback<String, Error>) r5, "ControlCardInfoManager:" + str);
        }
    }

    public void a(final Device device, final ProfileCardType profileCardType, String str, Param param, Object obj, final AsyncCallback<JSONObject, Error> asyncCallback) {
        PropItem a2 = profileCardType.a();
        if (!(a2 == null || a2.k == null)) {
            Object a3 = a(device.did, profileCardType.c, a2.k.b(obj));
            if (obj != null && obj.equals(a3)) {
                LogUtil.b("mijia-card", "ControlCardInfoManager.setDeviceProp no change value:" + obj);
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                    return;
                }
                return;
            }
        }
        DeviceCardApi.ProfileRpcApi.instance.rpcAsync(device.did, device.token, str, param, obj, new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (profileCardType.i != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(device.did);
                    ControlCardInfoManager.this.a((List<String>) arrayList, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
                        public void onSuccess(Object obj) {
                            if (asyncCallback != null) {
                                asyncCallback.sendSuccessMessage(null);
                            }
                        }

                        public void onFailure(Error error) {
                            if (asyncCallback != null) {
                                asyncCallback.sendFailureMessage(error);
                            }
                        }
                    }, "updatePropValue");
                } else if (asyncCallback != null) {
                    asyncCallback.sendSuccessMessage(null);
                }
            }
        });
        if (!TextUtils.isEmpty(MIUI10CardActivity.sRoomName)) {
            STAT.d.c(device.model, MIUI10CardActivity.sRoomName, DeviceUtils.c(device));
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        List<Device> f2 = SmartHomeDeviceManager.a().f();
        LogUtil.c("mijia-card", "ControlCardInfoManager.subscribeGridProp onlineDevices:" + f2.size());
        if (this.t == null) {
            this.t = new DevicePropSubscriber();
        }
        this.t.a(f2, (DevicePropSubscriber.DeviceSubscriberInterface) new CardAbstractManager.CardDeviceSubscriberInterface(f()));
    }

    /* access modifiers changed from: private */
    public void g(String str) {
        new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(next);
                if (optJSONObject != null) {
                    Iterator<String> keys2 = optJSONObject.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        if (next2.equals(g)) {
                            a(next, g, optJSONObject.opt(g));
                        } else {
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject(next2);
                            String str2 = "";
                            long j2 = 0;
                            if (optJSONObject2 != null) {
                                str2 = optJSONObject2.optString("value");
                                j2 = optJSONObject2.optLong("timestamp", 0);
                            }
                            a(next, next2, (Object) str2);
                            a(next, next2, j2);
                        }
                    }
                }
            }
        } catch (JSONException e2) {
            Log.e("mijia-card", AppMeasurement.Param.FATAL, e2);
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        if (!this.w.get()) {
            this.w.set(true);
            this.x.clear();
            ArrayList<String> arrayList = new ArrayList<>();
            for (Map.Entry entry : this.f20467a.entrySet()) {
                if (((Map) entry.getValue()).get(g) != null) {
                    arrayList.add(entry.getKey());
                    this.x.put(entry.getKey(), false);
                }
            }
            if (arrayList.size() > 0) {
                for (String h2 : arrayList) {
                    h(h2);
                }
                return;
            }
            this.w.set(false);
            q();
        }
    }

    private void h(final String str) {
        PluginHostApi.instance().callMethod(str, "{\"id\":100,\"method\":\"get_prop\",\"params\":[]}", new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                JSONArray optJSONArray;
                if (str != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.isNull("result") && (optJSONArray = jSONObject.optJSONArray("result")) != null && optJSONArray.length() > 2) {
                            ControlCardInfoManager.this.a(str, ControlCardInfoManager.g, optJSONArray.opt(1));
                        }
                    } catch (JSONException e) {
                        Log.e("mijia-card", AppMeasurement.Param.FATAL, e);
                    }
                }
                ControlCardInfoManager.this.i(str);
            }

            public void onFailure(int i, String str) {
                ControlCardInfoManager.this.i(str);
            }
        }, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return str;
            }
        });
    }

    /* access modifiers changed from: private */
    public void i(String str) {
        synchronized (this.v) {
            boolean z2 = true;
            this.x.put(str, true);
            Iterator<Map.Entry<String, Boolean>> it = this.x.entrySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!((Boolean) it.next().getValue()).booleanValue()) {
                        z2 = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z2) {
                this.w.set(false);
                this.x.clear();
                LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(new Intent(f));
                q();
            }
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(new Intent(h));
    }

    /* access modifiers changed from: protected */
    public File c() {
        if (!CoreApi.a().q()) {
            return null;
        }
        File file = new File(FileUtils.a(CommonApplication.getAppContext()) + File.separator + CoreApi.a().s() + "_control_props_profile");
        if (!file.exists()) {
            file.getParentFile().mkdir();
        }
        return file;
    }

    public void k() {
        if (CommonApplication.getAppContext() != null && !CardActive.instance.isActive()) {
            if (this.t != null) {
                this.t.a();
                this.t = null;
            }
            this.u.post(new Runnable() {
                public final void run() {
                    ControlCardInfoManager.this.b();
                }
            });
            this.s.clear();
        }
    }

    public void l() {
        if (this.z != null) {
            for (OnLoadListener a2 : this.z) {
                a2.a();
            }
            this.z = null;
        }
    }

    public void a(OnLoadListener onLoadListener) {
        if (onLoadListener == null) {
            return;
        }
        if (this.z == null) {
            onLoadListener.a();
        } else {
            this.z.add(onLoadListener);
        }
    }
}
