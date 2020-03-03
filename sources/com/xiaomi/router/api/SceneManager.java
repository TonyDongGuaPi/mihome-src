package com.xiaomi.router.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.geofence.manager.GrayLogDelegate;
import com.xiaomi.smarthome.scene.geofence.manager.MIUIGeoFenceHelper;
import com.xiaomi.smarthome.scene.geofence.manager.MIUIGeoFenceManager;
import com.xiaomi.smarthome.scene.geofence.manager.model.GeoFenceItem;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneManager {
    private static final int T = 30000;
    private static SceneManager U = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f13036a = "phone_call";
    public static final String b = "phone_sms";
    public static final int c = 1;
    public static final int d = 12;
    public static final int e = 13;
    public static final int f = 14;
    public static final int g = 5;
    public static final int h = 4;
    public static final int i = 6;
    public static final int j = 7;
    public static final int k = 8;
    public static final String l = "scene_list";
    public static final String m = "voice_scene_list";
    public static final int n = 300;
    public static int o = 60;
    private boolean A = false;
    /* access modifiers changed from: private */
    public boolean B = false;
    /* access modifiers changed from: private */
    public long C = 0;
    private HashMap<Integer, List<Integer>> D = new HashMap<>();
    private HashMap<Integer, List<Integer>> E = new HashMap<>();
    private String F = null;
    /* access modifiers changed from: private */
    public Map<String, CommonSceneOnline> G = new HashMap();
    private HandlerThread H = new MessageHandlerThread(PageUrl.j);
    private List<SceneApi.SmartHomeScene> I = new ArrayList();
    private List<SceneApi.SmartHomeScene> J = new ArrayList();
    private Map<String, SceneApi.SmartHomeScene> K = new HashMap();
    private WorkerHandler L;
    private SharedPreferences M;
    private boolean N = true;
    /* access modifiers changed from: private */
    public Comparator<SceneApi.SmartHomeScene> O = new Comparator<SceneApi.SmartHomeScene>() {
        /* renamed from: a */
        public int compare(SceneApi.SmartHomeScene smartHomeScene, SceneApi.SmartHomeScene smartHomeScene2) {
            if (smartHomeScene == null || smartHomeScene.l == null || smartHomeScene.l.size() == 0 || smartHomeScene.l.get(0).b == null || smartHomeScene.l.get(0).b.f21527a == null || smartHomeScene2 == null || smartHomeScene2.l == null || smartHomeScene2.l.size() == 0 || smartHomeScene2.l.get(0).b == null || smartHomeScene2.l.get(0).b.f21527a == null) {
                return 1;
            }
            return smartHomeScene.l.get(0).b.f21527a.a(smartHomeScene2.l.get(0).b.f21527a);
        }
    };
    private ArrayList<WeakReference<IScenceListener>> P = new ArrayList<>();
    private ArrayList<IScenceListener> Q = new ArrayList<>();
    /* access modifiers changed from: private */
    public long R = 0;
    /* access modifiers changed from: private */
    public long S = 0;
    CoreApi.IsCoreReadyCallback p = new CoreApi.IsCoreReadyCallback() {
        public void onCoreReady() {
            SceneManager.this.f();
        }
    };
    CoreApi.IsCoreReadyCallback q = new CoreApi.IsCoreReadyCallback() {
        public void onCoreReady() {
            if (!SceneManager.this.x) {
                if (!SceneManager.this.y || System.currentTimeMillis() - SceneManager.this.R >= 5000) {
                    boolean unused = SceneManager.this.x = true;
                    boolean unused2 = SceneManager.this.B = false;
                    SceneManager.this.b();
                    return;
                }
                SceneManager.this.c(5);
            }
        }
    };
    /* access modifiers changed from: private */
    public List<SceneApi.SmartHomeScene> r = new ArrayList();
    /* access modifiers changed from: private */
    public List<SceneApi.SmartHomeScene> s = new ArrayList();
    private List<SceneApi.SmartHomeScene> t = new ArrayList();
    private List<SceneApi.SmartHomeScene> u = new ArrayList();
    private List<SceneApi.SmartHomeScene> v = new ArrayList();
    /* access modifiers changed from: private */
    public Handler w = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            SceneLogUtil.a("SceneManager mHandler handleMessage  msg.what: " + message.what);
            int i = message.what;
            if (i == 1) {
                SceneManager.this.b();
            } else if (i != 12) {
                switch (i) {
                    case 4:
                        SceneManager.this.c(4);
                        break;
                    case 5:
                        boolean unused = SceneManager.this.x = false;
                        SceneManager.this.c(5);
                        break;
                    case 6:
                        SceneManager.this.g();
                        break;
                }
            } else {
                SceneManager.this.c(12);
            }
            super.handleMessage(message);
        }
    };
    /* access modifiers changed from: private */
    public boolean x = false;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public boolean z = false;

    public interface IScenceListener {
        void onRefreshScenceFailed(int i);

        void onRefreshScenceSuccess(int i);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ JSONObject b(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    public boolean e(SceneApi.SmartHomeScene smartHomeScene) {
        return false;
    }

    public RecommendSceneItem t() {
        return null;
    }

    public RecommendSceneItem u() {
        return null;
    }

    public void a(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene != null && smartHomeScene.l != null && !smartHomeScene.l.isEmpty()) {
            for (int i2 = 0; i2 < smartHomeScene.l.size(); i2++) {
                if ((smartHomeScene.l.get(i2).f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE || smartHomeScene.l.get(i2).f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) && smartHomeScene.l.get(i2).i != null) {
                    long j2 = smartHomeScene.l.get(i2).i.p;
                    if (j2 > 0 && GeoFenceItem.a(smartHomeScene.l.get(i2).i) != null) {
                        String m2 = AccountManager.a().m();
                        String str = ServerCompact.a(SHApplication.getAppContext()).b;
                        if (this.K.containsKey(MIUIGeoFenceHelper.a(m2, str, "scene-" + j2))) {
                            GrayLogDelegate.a("GEO_FENCE", "already created，update?" + smartHomeScene.f + ", lat:" + smartHomeScene.l.get(i2).i.r + ", lon:" + smartHomeScene.l.get(i2).i.s);
                        }
                    }
                }
            }
        }
    }

    public void a(List<SceneApi.SmartHomeScene> list) {
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list.get(i2).l != null && !list.get(i2).l.isEmpty()) {
                    for (int i3 = 0; i3 < list.get(i2).l.size(); i3++) {
                        if ((list.get(i2).l.get(i3).f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE || list.get(i2).l.get(i3).f21522a == SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE) && list.get(i2).l.get(i3).i != null) {
                            long j2 = list.get(i2).l.get(i3).i.p;
                            if (j2 > 0) {
                                String m2 = AccountManager.a().m();
                                String str = ServerCompact.a(SHApplication.getAppContext()).b;
                                String a2 = MIUIGeoFenceHelper.a(m2, str, "scene-" + j2);
                                this.K.remove(a2);
                                MIUIGeoFenceManager.b().b(a2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(int i2, final String str, Location location, final AsyncCallback asyncCallback) {
        GrayLogDelegate.a("GEO_FENCE", "trggerFence，prepare to execute----");
        final StringBuilder sb = new StringBuilder();
        sb.append(str + "****" + i2 + "****" + location);
        if (!MIUIGeoFenceManager.b().a()) {
            sb.append("\nPhone not supported");
            GrayLogDelegate.a("GEO_FENCE", sb.toString());
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-90, "not support"));
            }
        } else if (!this.y) {
            sb.append("\nscene list init fail");
            GrayLogDelegate.a("GEO_FENCE", sb.toString());
            c((String) null);
            a((IScenceListener) new IScenceListener() {
                public void onRefreshScenceSuccess(int i) {
                    if (i == 5) {
                        sb.append("\nget scene list success");
                        SceneManager.this.a(str, sb.toString(), asyncCallback);
                        SceneManager.this.b((IScenceListener) this);
                    }
                }

                public void onRefreshScenceFailed(int i) {
                    if (i == 5) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(new Error(-90, "not init ,refresh fail"));
                        }
                        SceneManager.this.b((IScenceListener) this);
                    }
                }
            });
        } else {
            a(str, sb.toString(), asyncCallback);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, AsyncCallback asyncCallback) {
        String str3 = null;
        final SceneApi.SmartHomeScene smartHomeScene = this.K == null ? null : this.K.get(str);
        if (smartHomeScene != null && smartHomeScene.n && smartHomeScene.l != null && smartHomeScene.k != null) {
            SceneApi.Condition.LAUNCH_TYPE launch_type = SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE;
            int i2 = 0;
            while (true) {
                if (i2 >= smartHomeScene.l.size()) {
                    break;
                } else if (smartHomeScene.l.get(i2) == null || smartHomeScene.l.get(i2).i == null || ((launch_type = smartHomeScene.l.get(i2).f21522a) != SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE && launch_type != SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE)) {
                    i2++;
                } else {
                    str3 = smartHomeScene.l.get(i2).i.q;
                }
            }
            str3 = smartHomeScene.l.get(i2).i.q;
            String str4 = TextUtils.isEmpty(str3) ? launch_type == SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE ? SceneApi.ConditionIZatGeoFencing.m : SceneApi.ConditionIZatGeoFencing.l : str3;
            if (!TextUtils.isEmpty(str4)) {
                final String str5 = str4;
                final String str6 = str2;
                final AsyncCallback asyncCallback2 = asyncCallback;
                RemoteSceneApi.a().a(SHApplication.getAppContext(), smartHomeScene.f, str4, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        GrayLogDelegate.a("GEO_FENCE", str5 + "onTrigger,scene/start success---" + smartHomeScene.f + InternalFrame.ID + str6);
                        if (asyncCallback2 != null) {
                            asyncCallback2.onSuccess(null);
                        }
                    }

                    public void onFailure(Error error) {
                        GrayLogDelegate.a("GEO_FENCE", str5 + "onTrigger,scene/start fail---" + smartHomeScene.f + InternalFrame.ID + error.a());
                        if (asyncCallback2 != null) {
                            asyncCallback2.onFailure(error);
                        }
                    }
                });
                return;
            }
            GrayLogDelegate.a("GEO_FENCE", "onTrigger,but no this scene： " + smartHomeScene.f);
            if (asyncCallback != null) {
                asyncCallback.onFailure(new Error(-90, "empty key"));
            }
        }
    }

    class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            SceneLogUtil.a("SceneManager  WorkerHandler handleMessage  msg.what: " + message.what);
            switch (message.what) {
                case 7:
                    if (SceneManager.this.C()) {
                        SceneManager.this.w.sendEmptyMessage(4);
                        return;
                    }
                    return;
                case 8:
                    SceneManager.this.c(SceneManager.l, (String) message.obj);
                    return;
                case 13:
                    if (SceneManager.this.D()) {
                        SceneManager.this.w.sendEmptyMessage(4);
                        return;
                    }
                    return;
                case 14:
                    SceneManager.this.c(SceneManager.m, (String) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    private SceneManager() {
        this.H.start();
        this.L = new WorkerHandler(this.H.getLooper());
        MIUIGeoFenceManager.b().c();
    }

    public void a(String str) {
        Message obtainMessage = this.L.obtainMessage();
        obtainMessage.what = 14;
        obtainMessage.obj = str;
        this.L.sendMessage(obtainMessage);
    }

    public void b(String str) {
        Message obtainMessage = this.L.obtainMessage();
        obtainMessage.what = 8;
        obtainMessage.obj = str;
        this.L.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: private */
    public boolean C() {
        if (this.M == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.M = appContext.getSharedPreferences(a2 + "scene_list_cache", 0);
            }
        }
        if (this.M == null) {
            return false;
        }
        String string = this.M.getString(l, "");
        try {
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            List<SceneApi.SmartHomeScene> a3 = a(new JSONObject(string), false);
            this.I.clear();
            for (int i2 = 0; i2 < a3.size(); i2++) {
                if (!b(a3.get(i2).l)) {
                    this.I.add(a3.get(i2));
                }
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public boolean D() {
        if (this.M == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.M = appContext.getSharedPreferences(a2 + "scene_list_cache", 0);
            }
        }
        if (this.M == null) {
            return false;
        }
        String string = this.M.getString(m, "");
        try {
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            List<SceneApi.SmartHomeScene> a3 = a(new JSONObject(string), false);
            this.J.clear();
            CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
            for (int i2 = 0; i2 < a3.size(); i2++) {
                if (!(a3.get(i2).l == null || a3.get(i2).l.size() <= 0 || a3.get(i2).l.get(0).b == null || a3.get(i2).l.get(0).b.f21527a == null || !a(corntabParam, a3.get(i2).l.get(0).b.f21527a))) {
                    this.J.add(a3.get(i2));
                }
            }
            Collections.sort(this.J, this.O);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void c(String str, String str2) {
        if (this.M == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            if (!TextUtils.isEmpty(a2)) {
                Context appContext = SHApplication.getAppContext();
                this.M = appContext.getSharedPreferences(a2 + "scene_list_cache", 0);
            }
        }
        if (this.M != null) {
            SharedPreferences.Editor edit = this.M.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    private List<SceneApi.SmartHomeScene> a(JSONObject jSONObject, boolean z2) {
        SceneApi.SmartHomeScene smartHomeScene;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; jSONObject.has(String.valueOf(i2)); i2++) {
            try {
                smartHomeScene = SceneApi.SmartHomeScene.a(jSONObject.optJSONObject(String.valueOf(i2)), z2);
            } catch (JSONException unused) {
                smartHomeScene = null;
            }
            if (smartHomeScene != null) {
                arrayList.add(smartHomeScene);
            }
        }
        return arrayList;
    }

    public void a() {
        if (SHApplication.getStateNotifier().a() == 4) {
            if (CoreApi.a().l()) {
                this.p.onCoreReady();
            } else {
                CoreApi.a().a(SHApplication.getAppContext(), this.p);
            }
        }
    }

    public void c(String str) {
        if (SHApplication.getStateNotifier().a() == 4) {
            if (!this.y) {
                this.L.sendEmptyMessage(7);
            }
            SceneLogUtil.a("SceneManager ----------updateScene --------------callbackmScenes.size()" + this.r.size() + this.x);
            if (CoreApi.a().l()) {
                this.q.onCoreReady();
            } else {
                CoreApi.a().a(SHApplication.getAppContext(), this.q);
            }
        } else {
            this.w.sendEmptyMessage(5);
        }
    }

    public void a(String str, IScenceListener iScenceListener) {
        if (SHApplication.getStateNotifier().a() == 4) {
            if (iScenceListener != null) {
                this.Q.add(iScenceListener);
            }
            SceneLogUtil.a("SceneManager ----------updateScene --------------callbackmScenes.size()" + this.r.size() + this.x);
            if (SHApplication.getStateNotifier().a() == 4) {
                if (!this.y) {
                    this.L.sendEmptyMessage(7);
                }
                if (!this.x) {
                    this.x = true;
                    this.B = false;
                    b();
                    return;
                }
                return;
            }
            this.w.sendEmptyMessage(5);
        }
    }

    public void a(long j2) {
        if (SHApplication.getStateNotifier().a() == 4) {
            SceneLogUtil.a("SceneManager ----------updateScene --------------timeStamp" + this.x);
            if (SHApplication.getStateNotifier().a() == 4) {
                if (!this.y) {
                    this.L.sendEmptyMessage(7);
                }
                if (!this.x) {
                    f();
                    if (this.y && this.C - j2 > 86400000) {
                        this.w.sendEmptyMessage(5);
                        return;
                    }
                    return;
                }
                return;
            }
            this.w.sendEmptyMessage(5);
        }
    }

    public void a(String str, final IXmPluginHostActivity.AsyncCallback<List<RecommendSceneItem>> asyncCallback) {
        RemoteSceneApi.a().a(SHApplication.getAppContext(), str, (AsyncCallback<List<RecommendSceneItem>, Error>) new AsyncCallback<List<RecommendSceneItem>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<RecommendSceneItem> list) {
                asyncCallback.onSuccess(list);
            }

            public void onFailure(Error error) {
                asyncCallback.onSuccess(null);
            }
        });
    }

    public void b() {
        RemoteSceneApi.a().a(SHApplication.getAppContext(), 15, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<SceneApi.SmartHomeScene> list) {
                synchronized (SceneManager.x()) {
                    long unused = SceneManager.this.R = System.currentTimeMillis();
                    SceneManager.this.r.clear();
                    for (int i = 0; i < list.size(); i++) {
                        if (!SceneManager.this.b(list.get(i).l)) {
                            SceneManager.this.r.add(list.get(i));
                        }
                    }
                    SceneManager.this.w();
                    SceneManager.this.c();
                }
                boolean unused2 = SceneManager.this.y = true;
                long unused3 = SceneManager.this.C = System.currentTimeMillis();
                SceneManager.this.c(1);
                GrayLogDelegate.a("Scenemanager", "update 15 from server");
            }

            public void onFailure(Error error) {
                SceneManager.this.d(1);
            }
        });
    }

    public void c() {
        GeoFenceItem a2;
        if (!MIUIGeoFenceManager.b().a()) {
            GrayLogDelegate.a("Scenemanager", "has geofence，but not support");
            return;
        }
        HashMap hashMap = new HashMap();
        int i2 = 0;
        while (true) {
            if (i2 >= (this.r == null ? 0 : this.r.size())) {
                break;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= ((this.r.get(i2) == null || this.r.get(i2).l == null) ? 0 : this.r.get(i2).l.size())) {
                    break;
                }
                SceneApi.ConditionIZatGeoFencing conditionIZatGeoFencing = this.r.get(i2).l.get(i3).i;
                if (conditionIZatGeoFencing != null && !TextUtils.isEmpty(conditionIZatGeoFencing.q) && conditionIZatGeoFencing.p > 0 && (a2 = GeoFenceItem.a(conditionIZatGeoFencing)) != null) {
                    String m2 = AccountManager.a().m();
                    String str = ServerCompact.a(SHApplication.getAppContext()).b;
                    String a3 = MIUIGeoFenceHelper.a(m2, str, "scene-" + conditionIZatGeoFencing.p);
                    this.K.put(a3, this.r.get(i2));
                    hashMap.put(a3, a2);
                }
                i3++;
            }
            i2++;
        }
        GrayLogDelegate.a("Scenemanager", "sync fence items size： " + hashMap.size());
        if (!hashMap.isEmpty()) {
            MIUIGeoFenceManager.b().a((Map<String, GeoFenceItem>) hashMap);
            GrayLogDelegate.a("Scenemanager", "sync fence item end");
        }
    }

    public void d() {
        if (SHApplication.getStateNotifier().a() == 4) {
            if (!this.z) {
                this.L.sendEmptyMessage(13);
            }
            RemoteSceneApi.a().a(SHApplication.getAppContext(), 50, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<SceneApi.SmartHomeScene> list) {
                    boolean unused = SceneManager.this.z = true;
                    synchronized (SceneManager.x()) {
                        SceneManager.this.s.clear();
                        if (list != null) {
                            CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
                            for (int i = 0; i < list.size(); i++) {
                                if (!(list.get(i).l == null || list.get(i).l.size() <= 0 || list.get(i).l.get(0).b == null || list.get(i).l.get(0).b.f21527a == null)) {
                                    if (SceneManager.this.a(corntabParam, list.get(i).l.get(0).b.f21527a)) {
                                        SceneManager.this.s.add(list.get(i));
                                    }
                                }
                            }
                            Collections.sort(SceneManager.this.s, SceneManager.this.O);
                        }
                    }
                    SceneManager.this.w.sendEmptyMessage(12);
                }

                public void onFailure(Error error) {
                    SceneManager.this.d(1);
                }
            });
            return;
        }
        this.w.sendEmptyMessage(12);
    }

    public boolean a(CorntabUtils.CorntabParam corntabParam, CorntabUtils.CorntabParam corntabParam2) {
        return corntabParam2.a(corntabParam) == 1;
    }

    /* access modifiers changed from: private */
    public boolean b(List<SceneApi.Condition> list) {
        if (list == null) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (SceneApi.Condition.LAUNCH_TYPE.PHONE_SMS.equals(list.get(i2).f21522a) || SceneApi.Condition.LAUNCH_TYPE.PHONE_CALL.equals(list.get(i2).f21522a)) {
                return true;
            }
        }
        return false;
    }

    public void a(JSONObject jSONObject) {
        this.D.clear();
        this.E.clear();
        JSONArray optJSONArray = jSONObject.optJSONArray("filterInfo");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                int optInt = optJSONObject.optInt("tr_id");
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("launch");
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    arrayList.add(Integer.valueOf(optJSONArray2.optInt(i3)));
                }
                this.D.put(Integer.valueOf(optInt), arrayList);
                JSONArray optJSONArray3 = optJSONObject.optJSONArray("action_list");
                ArrayList arrayList2 = new ArrayList();
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    arrayList2.add(Integer.valueOf(optJSONArray3.optInt(i4)));
                }
                this.E.put(Integer.valueOf(optInt), arrayList2);
            }
        }
    }

    public List<Integer> a(int i2) {
        return this.D.get(Integer.valueOf(i2));
    }

    public List<Integer> b(int i2) {
        return this.E.get(Integer.valueOf(i2));
    }

    public boolean b(SceneApi.SmartHomeScene smartHomeScene) {
        return smartHomeScene != null && smartHomeScene.i == 151;
    }

    public boolean c(SceneApi.SmartHomeScene smartHomeScene) {
        return smartHomeScene != null && smartHomeScene.i == 152;
    }

    public boolean a(RecommendSceneItem recommendSceneItem) {
        return recommendSceneItem != null && recommendSceneItem.mRecommId == 151;
    }

    public boolean b(RecommendSceneItem recommendSceneItem) {
        return recommendSceneItem != null && recommendSceneItem.mRecommId == 152;
    }

    public CommonSceneOnline a(String str, String str2) {
        Map<String, CommonSceneOnline> map = this.G;
        return map.get(str + str2);
    }

    public Map<String, CommonSceneOnline> e() {
        return this.G;
    }

    /* access modifiers changed from: package-private */
    public void f() {
        if (SHApplication.getStateNotifier().a() == 4) {
            if (this.G == null || this.G.size() <= 0 || System.currentTimeMillis() - this.S >= 30000) {
                a("/scene/tplv2", (Map<String, CommonSceneOnline>) new HashMap(), (String) null, (AsyncCallback<Map<String, CommonSceneOnline>, Error>) new AsyncCallback<Map<String, CommonSceneOnline>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Map<String, CommonSceneOnline> map) {
                        long unused = SceneManager.this.S = System.currentTimeMillis();
                        SceneManager.this.G.clear();
                        SceneManager.this.G.putAll(map);
                        SceneManager.this.c(6);
                    }

                    public void onFailure(Error error) {
                        SceneManager.this.d(6);
                    }
                });
            } else {
                c(6);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, final Map<String, CommonSceneOnline> map, String str2, final AsyncCallback<Map<String, CommonSceneOnline>, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        if (str2 != null) {
            try {
                jSONObject.put("start_did", str2);
            } catch (JSONException e2) {
                Log.e("SceneManager", Log.getStackTraceString(e2));
            }
        }
        jSONObject.put("limit", 300);
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b(str).b((List<KeyValuePair>) arrayList).a(), $$Lambda$SceneManager$Un2yrQTzgPzXejggsVE2Up3bGCI.INSTANCE, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                JSONArray optJSONArray;
                JSONArray optJSONArray2;
                if (jSONObject.has("max_action_num")) {
                    SceneManager.o = jSONObject.optInt("max_action_num");
                    if (SceneManager.o <= 0) {
                        SceneManager.o = 60;
                    }
                }
                if (jSONObject.has("tpl") && (optJSONArray2 = jSONObject.optJSONArray("tpl")) != null) {
                    for (int i = 0; i < optJSONArray2.length(); i++) {
                        CommonSceneOnline a2 = CommonSceneOnline.a(optJSONArray2.optJSONObject(i));
                        Map map = map;
                        map.put(a2.f21198a + a2.b, a2);
                    }
                }
                if (jSONObject.has("filter") && (optJSONArray = jSONObject.optJSONArray("filter")) != null && optJSONArray.length() > 0) {
                    SceneManager.this.a(optJSONArray.optJSONObject(0));
                }
                if (jSONObject.optBoolean("has_more")) {
                    String optString = jSONObject.optString("max_did");
                    if (GlobalSetting.q) {
                        LogUtilGrey.a("SceneManager", "updateMoreSceneTemplate onSuccess hasmore max_id:" + optString);
                    }
                    SceneManager.this.a("/scene/tpl_paging", (Map<String, CommonSceneOnline>) map, optString, (AsyncCallback<Map<String, CommonSceneOnline>, Error>) asyncCallback);
                    return;
                }
                if (GlobalSetting.q) {
                    LogUtilGrey.a("SceneManager", "updateMoreSceneTemplate onSuccess " + map.size());
                }
                asyncCallback.onSuccess(map);
            }

            public void onFailure(Error error) {
                asyncCallback.onFailure(error);
                LogUtilGrey.a("SceneManager", "updateMoreSceneTemplate onFailure");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void g() {
        f();
    }

    public List<SceneApi.SmartHomeScene> h() {
        return this.r;
    }

    public List<SceneApi.SmartHomeScene> i() {
        return this.s;
    }

    public List<SceneApi.SmartHomeScene> d(String str) {
        ArrayList arrayList = new ArrayList();
        if (!this.y || this.r == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            if (this.r.get(i2).l != null) {
                int i3 = 0;
                while (true) {
                    if (i3 < this.r.get(i2).l.size()) {
                        if (this.r.get(i2).l.get(i3).f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE && this.r.get(i2).l.get(i3).c != null && TextUtils.equals(str, this.r.get(i2).l.get(i3).c.f21523a)) {
                            arrayList.add(this.r.get(i2));
                            break;
                        }
                        i3++;
                    } else {
                        break;
                    }
                }
                if (i3 == this.r.get(i2).l.size()) {
                    int i4 = 0;
                    while (true) {
                        if (i4 < this.r.get(i2).k.size()) {
                            if (this.r.get(i2).k.get(i4).f21521a == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value && this.r.get(i2).k.get(i4).g != null && TextUtils.equals(str, this.r.get(i2).k.get(i4).g.e)) {
                                arrayList.add(this.r.get(i2));
                                break;
                            }
                            i4++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public SceneApi.SmartHomeScene e(String str) {
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            if (this.r.get(i2) != null && TextUtils.equals(this.r.get(i2).f, str)) {
                return this.r.get(i2);
            }
        }
        return null;
    }

    public SceneApi.SmartHomeScene f(String str) {
        for (int i2 = 0; i2 < this.s.size(); i2++) {
            if (this.s.get(i2) != null && TextUtils.equals(this.s.get(i2).f, str)) {
                return this.s.get(i2);
            }
        }
        return null;
    }

    public boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            if (TextUtils.equals(str, this.r.get(i2).f)) {
                this.r.remove(i2);
                return true;
            }
        }
        for (int i3 = 0; i3 < this.s.size(); i3++) {
            if (TextUtils.equals(str, this.s.get(i3).f)) {
                this.s.remove(i3);
                return true;
            }
        }
        return false;
    }

    public void d(SceneApi.SmartHomeScene smartHomeScene) {
        this.r.add(smartHomeScene);
    }

    public void a(String str, SceneApi.SmartHomeScene smartHomeScene) {
        for (int i2 = 0; i2 < this.r.size(); i2++) {
            if (TextUtils.equals(str, this.r.get(i2).f)) {
                this.r.remove(i2);
                this.r.add(i2, smartHomeScene);
                return;
            }
        }
    }

    public List<SceneApi.SmartHomeScene> j() {
        return this.I;
    }

    public List<SceneApi.SmartHomeScene> k() {
        return this.J;
    }

    public boolean l() {
        return this.N;
    }

    public boolean m() {
        return this.x;
    }

    public boolean n() {
        return this.y;
    }

    public boolean o() {
        return this.z;
    }

    public boolean p() {
        return this.A;
    }

    public void a(boolean z2) {
        this.y = z2;
    }

    public boolean q() {
        return this.B;
    }

    public void a(IScenceListener iScenceListener) {
        IScenceListener iScenceListener2;
        if (iScenceListener != null) {
            int i2 = 0;
            while (i2 < this.P.size()) {
                WeakReference weakReference = this.P.get(i2);
                if (weakReference == null || (iScenceListener2 = (IScenceListener) weakReference.get()) == null || iScenceListener2 != iScenceListener) {
                    i2++;
                } else {
                    LogUtil.a("SceneManager", "registerLiteSceneListener return");
                    return;
                }
            }
            this.P.add(new WeakReference(iScenceListener));
        }
    }

    public void b(IScenceListener iScenceListener) {
        if (iScenceListener != null) {
            for (int i2 = 0; i2 < this.P.size(); i2++) {
                if (this.P.get(i2).get() == iScenceListener) {
                    this.P.remove(i2);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(int i2) {
        SceneLogUtil.a("SceneManager notifySuccess  type: " + i2);
        if (i2 != 1) {
            int i3 = 0;
            if (i2 != 12) {
                switch (i2) {
                    case 4:
                        this.N = true;
                        while (i3 < this.P.size()) {
                            if (this.P.get(i3).get() != null) {
                                ((IScenceListener) this.P.get(i3).get()).onRefreshScenceSuccess(i2);
                            }
                            i3++;
                        }
                        return;
                    case 5:
                        this.B = true;
                        for (int i4 = 0; i4 < this.P.size(); i4++) {
                            if (this.P.get(i4).get() != null) {
                                ((IScenceListener) this.P.get(i4).get()).onRefreshScenceSuccess(i2);
                            }
                        }
                        for (int i5 = 0; i5 < this.Q.size(); i5++) {
                            IScenceListener iScenceListener = this.Q.get(i5);
                            if (iScenceListener != null) {
                                iScenceListener.onRefreshScenceSuccess(i2);
                            }
                        }
                        this.N = false;
                        this.Q.clear();
                        return;
                    case 6:
                        this.A = true;
                        return;
                    default:
                        return;
                }
            } else {
                while (i3 < this.P.size()) {
                    if (this.P.get(i3).get() != null) {
                        ((IScenceListener) this.P.get(i3).get()).onRefreshScenceSuccess(i2);
                    }
                    i3++;
                }
            }
        } else {
            this.w.sendEmptyMessage(5);
        }
    }

    /* access modifiers changed from: package-private */
    public void d(int i2) {
        this.x = false;
        this.B = false;
        for (int i3 = 0; i3 < this.P.size(); i3++) {
            if (this.P.get(i3).get() != null) {
                ((IScenceListener) this.P.get(i3).get()).onRefreshScenceFailed(i2);
            }
        }
        for (int i4 = 0; i4 < this.Q.size(); i4++) {
            this.Q.get(i4).onRefreshScenceFailed(i2);
        }
        this.Q.clear();
    }

    public List<SceneApi.SmartHomeScene> r() {
        return this.u;
    }

    public List<SceneApi.SmartHomeScene> s() {
        return this.v;
    }

    public List<SceneApi.SmartHomeScene> v() {
        return this.t;
    }

    public List<SceneApi.SmartHomeScene> b(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            for (SceneApi.SmartHomeScene next : this.r) {
                for (SceneApi.Condition next2 : next.l) {
                    if (next2 != null && next2.f != null && next2.f.f21523a.equalsIgnoreCase(str) && next2.f.j.equalsIgnoreCase(str2)) {
                        arrayList.add(next);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized void w() {
        this.u.clear();
        this.v.clear();
        this.t.clear();
        this.t.addAll(this.r);
        for (SceneApi.SmartHomeScene next : this.r) {
            if (b(next)) {
                this.v.add(next);
            }
            if (c(next)) {
                this.u.add(next);
            }
        }
    }

    public static synchronized SceneManager x() {
        SceneManager sceneManager;
        synchronized (SceneManager.class) {
            if (U == null) {
                U = new SceneManager();
            }
            sceneManager = U;
        }
        return sceneManager;
    }

    public void y() {
        SceneManager sceneManager = U;
        U = new SceneManager();
        U.Q.addAll(sceneManager.Q);
        U.P.addAll(sceneManager.P);
    }

    public void z() {
        A();
        MIUIGeoFenceManager.b().d();
        U = null;
        try {
            if (this.L != null) {
                this.L.removeCallbacksAndMessages((Object) null);
            }
            this.H.quit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void A() {
        this.r.clear();
        this.G.clear();
        this.y = false;
        this.B = false;
        this.C = 0;
        this.A = false;
    }

    public String B() {
        return this.F;
    }

    public List<SceneInfo> h(String str) {
        List<SceneApi.SmartHomeScene> h2 = h();
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            for (SceneApi.SmartHomeScene next : h2) {
                boolean z2 = false;
                if (next.i < 1000 || next.i > 1003) {
                    Iterator<SceneApi.Condition> it = next.l.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        SceneApi.Condition next2 = it.next();
                        if (next2.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE && next2.c != null && TextUtils.equals(next2.c.f21523a, str)) {
                            arrayList.add(f(next));
                            z2 = true;
                            break;
                        }
                    }
                    if (!z2) {
                        Iterator<SceneApi.Action> it2 = next.k.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            SceneApi.Action next3 = it2.next();
                            if (!TextUtils.isEmpty(next3.g.e) && next3.g.e.equalsIgnoreCase(str)) {
                                arrayList.add(f(next));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static SceneInfo f(SceneApi.SmartHomeScene smartHomeScene) {
        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.mSceneIdV2 = smartHomeScene.f;
        try {
            sceneInfo.mSceneId = (int) Long.parseLong(smartHomeScene.f);
        } catch (Exception unused) {
            sceneInfo.mSceneId = -1;
        }
        sceneInfo.mName = smartHomeScene.g;
        sceneInfo.mStatus = smartHomeScene.r;
        sceneInfo.mType = smartHomeScene.s;
        sceneInfo.mEnable = smartHomeScene.n;
        sceneInfo.mLaunchList = new ArrayList();
        for (SceneApi.Condition next : smartHomeScene.l) {
            SceneInfo.SceneLaunch sceneLaunch = new SceneInfo.SceneLaunch();
            switch (next.f21522a) {
                case CLICK:
                    sceneLaunch.mLaunchType = 0;
                    break;
                case DEVICE:
                    sceneLaunch.mLaunchType = 2;
                    sceneLaunch.mDeviceModel = next.c.d;
                    break;
                case TIMER:
                    sceneLaunch.mLaunchType = 1;
                    break;
                case COME_HOME:
                    sceneLaunch.mLaunchType = 4;
                    break;
                case LEAVE_HOME:
                    sceneLaunch.mLaunchType = 3;
                    break;
                case PHONE_CALL:
                    sceneLaunch.mLaunchType = 7;
                    break;
                case PHONE_SMS:
                    sceneLaunch.mLaunchType = 8;
                    break;
                case HUMIDITY:
                    sceneLaunch.mLaunchType = 14;
                    break;
                case AQI:
                    sceneLaunch.mLaunchType = 15;
                    break;
                case SUN_RISE:
                    sceneLaunch.mLaunchType = 16;
                    break;
                case SUN_SET:
                    sceneLaunch.mLaunchType = 17;
                    break;
                case TEMPERATURE:
                    sceneLaunch.mLaunchType = 18;
                    break;
                case COME_LOC:
                    sceneLaunch.mLaunchType = 19;
                    break;
                case LEAVE_LOC:
                    sceneLaunch.mLaunchType = 20;
                    break;
                case ENTER_FENCE:
                    sceneLaunch.mLaunchType = 21;
                    break;
                case LEAVE_FENCE:
                    sceneLaunch.mLaunchType = 22;
                    break;
            }
            sceneLaunch.mLaunchName = SmartHomeSceneUtility.d(SHApplication.getAppContext(), next);
            if (next.c != null) {
                sceneLaunch.mDid = next.c.f21523a;
            }
            if (next.c != null && (next.c instanceof SceneApi.ConditionDeviceCommon)) {
                sceneLaunch.mExtra = ((SceneApi.ConditionDeviceCommon) next.c).m;
            }
            sceneLaunch.mEventString = (next.c == null || next.c.j == null) ? "" : next.c.j;
            if (next.c != null && (next.c instanceof SceneApi.ConditionDeviceCommon)) {
                try {
                    sceneLaunch.mEventValue = ((SceneApi.ConditionDeviceCommon) next.c).l;
                } catch (Exception unused2) {
                }
            }
            sceneInfo.mLaunchList.add(sceneLaunch);
            sceneInfo.mLaunch = sceneLaunch;
        }
        sceneInfo.mActions = new ArrayList();
        for (SceneApi.Action next2 : smartHomeScene.k) {
            SceneInfo.SceneAction sceneAction = new SceneInfo.SceneAction();
            if (next2.b != null) {
                sceneAction.mDeviceName = next2.b;
            } else {
                sceneAction.mDeviceName = "";
            }
            sceneAction.mActionName = next2.c;
            sceneAction.mDeviceModel = next2.e;
            sceneAction.mDelayTime = next2.g.g;
            if (next2.g.c == null) {
                sceneAction.mActionString = next2.c;
            } else {
                sceneAction.mActionString = next2.g.c;
            }
            sceneAction.mDid = next2.g.e;
            try {
                sceneAction.mActionValue = next2.g.f;
            } catch (Exception unused3) {
            }
            if (next2.g instanceof SceneApi.SHLiteScenePayload) {
                sceneAction.mActionType = 12;
            } else if (next2.g instanceof SceneApi.SHSceneAutoPayload) {
                sceneAction.mActionType = 10;
            } else if (next2.g instanceof SceneApi.SHSceneDelayPayload) {
                sceneAction.mActionType = 9;
            } else if (next2.g instanceof SceneApi.SHSceneItemPayloadCommon) {
                sceneAction.mActionType = 13;
            } else if (next2.g instanceof SceneApi.SHScenePushPayload) {
                sceneAction.mActionType = 11;
            }
            sceneInfo.mActions.add(sceneAction);
        }
        return sceneInfo;
    }

    public boolean g(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene.l == null || smartHomeScene.l.size() == 0) {
            return false;
        }
        int i2 = 0;
        while (i2 < smartHomeScene.l.size()) {
            if (smartHomeScene.l.get(i2).f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC || smartHomeScene.l.get(i2).f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) {
                return false;
            }
            if ((smartHomeScene.l.get(i2).f21522a != SceneApi.Condition.LAUNCH_TYPE.ENTER_FENCE && smartHomeScene.l.get(i2).f21522a != SceneApi.Condition.LAUNCH_TYPE.LEAVE_FENCE) || smartHomeScene.l.get(i2).i == null || TextUtils.isEmpty(smartHomeScene.l.get(i2).i.q) || (!TextUtils.equals(SceneApi.ConditionIZatGeoFencing.m, smartHomeScene.l.get(i2).i.q) && !TextUtils.equals(SceneApi.ConditionIZatGeoFencing.l, smartHomeScene.l.get(i2).i.q))) {
                i2++;
            } else if (MIUIGeoFenceManager.b().a()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
