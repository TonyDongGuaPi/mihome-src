package com.xiaomi.smarthome.lite.scene;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.UserConfigKeyManager;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteSceneManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19390a = 5;
    public static final int b = 4;
    public static final String c = "lite_scene_list";
    public static final int d = 5000;
    /* access modifiers changed from: private */
    public static final String e = "com.xiaomi.smarthome.lite.scene.LiteSceneManager";
    private static final int f = 1;
    private static final int g = 1;
    private static final int h = 12;
    private static final int i = 7;
    private static final int j = 10;
    /* access modifiers changed from: private */
    public static volatile LiteSceneManager x;
    /* access modifiers changed from: private */
    public Handler k = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            String m = LiteSceneManager.e;
            LogUtil.a(m, "mHandler.handleMessage" + message.what);
            int i = message.what;
            if (i == 1) {
                LiteSceneManager.this.d();
            } else if (i == 4) {
                LiteSceneManager.this.a(4);
            } else if (i == 12) {
                LiteSceneManager.this.c();
            }
            super.handleMessage(message);
        }
    };
    private boolean l = false;
    /* access modifiers changed from: private */
    public boolean m = false;
    private boolean n = false;
    private HandlerThread o = new HandlerThread("lite_scene");
    /* access modifiers changed from: private */
    public List<SceneApi.SmartHomeScene> p = new ArrayList();
    private List<SceneApi.SmartHomeScene> q = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> r = new ArrayList();
    private WorkerHandler s;
    private SharedPreferences t;
    /* access modifiers changed from: private */
    public ArrayList<WeakReference<IScenceListener>> u = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<IScenceListener> v = new ArrayList<>();
    private long w = 0;

    public interface IScenceListener {
        void a(int i);

        void b(int i);
    }

    class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 7) {
                if (i == 10) {
                    LiteSceneManager.this.a(LiteSceneManager.c, (String) message.obj);
                }
            } else if (LiteSceneManager.this.o()) {
                LiteSceneManager.this.k.sendEmptyMessage(4);
            }
        }
    }

    private LiteSceneManager() {
        this.o.start();
        this.s = new WorkerHandler(this.o.getLooper());
    }

    public void a() {
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < this.p.size(); i2++) {
            try {
                jSONArray.put(this.p.get(i2).a());
            } catch (Exception unused) {
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", 1);
            jSONObject.put(c, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Message obtainMessage = this.k.obtainMessage();
        obtainMessage.what = 10;
        obtainMessage.obj = jSONObject.toString();
        this.s.sendMessage(obtainMessage);
    }

    /* access modifiers changed from: private */
    public boolean o() {
        if (this.t == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            Context appContext = SHApplication.getAppContext();
            this.t = appContext.getSharedPreferences(a2 + "lite_scene_list_cache", 0);
        }
        if (this.t == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.t.getString(c, ""));
            int optInt = jSONObject.optInt("version", -1);
            if (optInt < 1) {
                this.t.edit().clear().commit();
                if (optInt == -1) {
                    Context appContext2 = SHApplication.getAppContext();
                    SharedPreferences sharedPreferences = appContext2.getSharedPreferences("lite_scene_order_new_sp_name_" + CoreApi.a().s(), 0);
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().clear().commit();
                    }
                }
            }
            this.q.clear();
            JSONArray optJSONArray = jSONObject.optJSONArray(c);
            if (optJSONArray != null) {
                this.q.addAll(a(optJSONArray, true));
            }
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.t == null) {
            String a2 = MD5Util.a(CoreApi.a().s());
            Context appContext = SHApplication.getAppContext();
            this.t = appContext.getSharedPreferences(a2 + "lite_scene_list_cache", 0);
        }
        if (this.t != null) {
            SharedPreferences.Editor edit = this.t.edit();
            edit.putString(str, str2);
            edit.commit();
        }
    }

    private List<SceneApi.SmartHomeScene> a(JSONArray jSONArray, boolean z) {
        SceneApi.SmartHomeScene smartHomeScene;
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                smartHomeScene = SceneApi.SmartHomeScene.a(jSONArray.optJSONObject(i2), z);
            } catch (JSONException unused) {
                smartHomeScene = null;
            }
            if (smartHomeScene != null) {
                arrayList.add(smartHomeScene);
            }
        }
        return arrayList;
    }

    public void a(IScenceListener iScenceListener) {
        if (iScenceListener != null) {
            for (int i2 = 0; i2 < this.u.size(); i2++) {
                if (this.u.get(i2).get() == iScenceListener) {
                    LogUtil.a(e, "registerLiteSceneListener return");
                    return;
                }
            }
            this.u.add(new WeakReference(iScenceListener));
        }
        b();
    }

    public void b() {
        long currentTimeMillis = System.currentTimeMillis();
        String str = e;
        LogUtil.a(str, "SHApplication.getStateNotifier().getCurrentState()" + SHApplication.getStateNotifier().a() + "   mIsRefreshing  " + this.l);
        String str2 = e;
        LogUtil.a(str2, "now====" + currentTimeMillis + "   lastRefreshTime  " + this.w + "   now - lastRefreshTime " + (currentTimeMillis - this.w));
        if (SHApplication.getStateNotifier().a() != 4 || this.l) {
            return;
        }
        if (!this.m || currentTimeMillis - this.w >= 5000) {
            this.l = true;
            this.m = false;
            this.w = System.currentTimeMillis();
            String str3 = e;
            Log.e(str3, "Start Time - " + System.currentTimeMillis());
            this.k.sendEmptyMessage(1);
            if (this.p.size() == 0 && this.q.size() == 0) {
                this.s.sendEmptyMessage(7);
                return;
            }
            return;
        }
        a(5);
    }

    public void c() {
        if (this.p == null || this.p.size() == 0) {
            a(5);
        } else {
            UserConfigKeyManager.a().a(1, 1, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    String m = LiteSceneManager.e;
                    LogUtil.a(m, "getLiteSceneOrderFromServer  " + str.toString());
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (str.contains("value")) {
                                LiteSceneManager.this.r.clear();
                                JSONArray optJSONArray = jSONObject.optJSONArray("value");
                                if (optJSONArray != null) {
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        LiteSceneManager.this.r.add(optJSONArray.optString(i));
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    LiteSceneManager.this.a(5);
                }

                public void onFailure(Error error) {
                    LiteSceneManager.this.a(5);
                }
            });
        }
    }

    public void a(final AsyncCallback asyncCallback) {
        try {
            LogUtil.a(e, "saveLiteSceneOrderToServer");
            UserConfig userConfig = new UserConfig();
            userConfig.B = 1;
            userConfig.C = "1";
            userConfig.D = new ArrayList<>();
            JSONObject p2 = p();
            JSONArray jSONArray = new JSONArray();
            UserApi.a(p2, 1, 100, 2048, jSONArray, "1");
            UserConfigApi.a().a(SHApplication.getAppContext(), jSONArray, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    LiteSceneManager.this.r.clear();
                    for (int i = 0; i < LiteSceneManager.this.p.size(); i++) {
                        List c = LiteSceneManager.this.r;
                        c.add(((SceneApi.SmartHomeScene) LiteSceneManager.this.p.get(i)).f + "");
                    }
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(jSONObject);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                }
            });
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private JSONObject p() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (int i2 = 0; i2 < this.p.size(); i2++) {
            SceneApi.SmartHomeScene smartHomeScene = this.p.get(i2);
            if (smartHomeScene instanceof SceneApi.SmartHomeScene) {
                jSONArray.put(smartHomeScene.f);
            }
        }
        try {
            jSONObject.put("value", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void d() {
        RemoteSceneApi.a().a(SHApplication.getAppContext(), 30, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<SceneApi.SmartHomeScene> list) {
                synchronized (LiteSceneManager.x) {
                    LiteSceneManager.this.p.clear();
                    LiteSceneManager.this.p.addAll(list);
                }
                LiteSceneManager.this.a(1);
            }

            public void onFailure(Error error) {
                LiteSceneManager.this.b(1);
            }
        });
    }

    public List<SceneApi.SmartHomeScene> e() {
        return this.p;
    }

    public List<SceneApi.SmartHomeScene> f() {
        return this.q;
    }

    public void a(List<SceneApi.SmartHomeScene> list) {
        this.p.clear();
        this.p.addAll(list);
    }

    public SceneApi.SmartHomeScene a(String str) {
        for (SceneApi.SmartHomeScene next : this.p) {
            if (TextUtils.equals(next.f, str)) {
                return next;
            }
        }
        return null;
    }

    public void a(SceneApi.SmartHomeScene smartHomeScene, AsyncCallback asyncCallback) {
        if (this.p.contains(smartHomeScene)) {
            this.p.remove(smartHomeScene);
            a((AsyncCallback) null);
        } else if (asyncCallback != null) {
            asyncCallback.onSuccess(null);
        }
    }

    public boolean b(String str) {
        boolean z;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= this.p.size()) {
                z = false;
                break;
            } else if (TextUtils.equals(str, this.p.get(i3).f)) {
                this.p.remove(i3);
                z = true;
                break;
            } else {
                i3++;
            }
        }
        while (true) {
            if (i2 >= this.q.size()) {
                break;
            } else if (TextUtils.equals(str, this.q.get(i2).f)) {
                this.q.remove(i2);
                break;
            } else {
                i2++;
            }
        }
        return z;
    }

    public void a(String str, SceneApi.SmartHomeScene smartHomeScene) {
        for (int i2 = 0; i2 < this.p.size(); i2++) {
            if (TextUtils.equals(str, this.p.get(i2).f)) {
                this.p.remove(i2);
                this.p.add(i2, smartHomeScene);
                return;
            }
        }
    }

    public void a(SceneApi.SmartHomeScene smartHomeScene) {
        this.p.add(smartHomeScene);
    }

    public void b(SceneApi.SmartHomeScene smartHomeScene, AsyncCallback asyncCallback) {
        a(smartHomeScene);
        a(asyncCallback);
    }

    public boolean g() {
        return this.l;
    }

    public boolean h() {
        return this.n;
    }

    public boolean i() {
        return this.m;
    }

    public void b(IScenceListener iScenceListener) {
        if (iScenceListener != null) {
            for (int i2 = 0; i2 < this.u.size(); i2++) {
                if (this.u.get(i2).get() == iScenceListener) {
                    LogUtil.a(e, "registerLiteSceneListener return");
                    return;
                }
            }
            this.u.add(new WeakReference(iScenceListener));
        }
    }

    public boolean c(IScenceListener iScenceListener) {
        if (iScenceListener == null) {
            return false;
        }
        for (int i2 = 0; i2 < this.u.size(); i2++) {
            if (this.u.get(i2).get() == iScenceListener) {
                this.u.remove(i2);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(final int i2) {
        SceneLogUtil.a("LiteSceneManagernotifySuccess" + i2);
        if (i2 != 1) {
            switch (i2) {
                case 4:
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            for (int i = 0; i < LiteSceneManager.this.u.size(); i++) {
                                if (((WeakReference) LiteSceneManager.this.u.get(i)).get() != null) {
                                    ((IScenceListener) ((WeakReference) LiteSceneManager.this.u.get(i)).get()).a(i2);
                                }
                            }
                        }
                    });
                    break;
                case 5:
                    q();
                    a();
                    this.m = true;
                    this.n = true;
                    this.l = false;
                    String str = e;
                    Log.e(str, "End Time - " + System.currentTimeMillis());
                    break;
            }
        } else {
            this.k.sendEmptyMessage(12);
        }
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                SceneLogUtil.a("LiteSceneManagernotifySuccess" + i2 + "----REFRESH_LASTmIsUpdateSuccess" + LiteSceneManager.this.m);
                for (int i = 0; i < LiteSceneManager.this.u.size(); i++) {
                    if (((WeakReference) LiteSceneManager.this.u.get(i)).get() != null) {
                        ((IScenceListener) ((WeakReference) LiteSceneManager.this.u.get(i)).get()).a(i2);
                    }
                }
                for (int i2 = 0; i2 < LiteSceneManager.this.v.size(); i2++) {
                    ((IScenceListener) LiteSceneManager.this.v.get(i2)).a(i2);
                }
                LiteSceneManager.this.v.clear();
            }
        });
    }

    private void q() {
        if (this.p != null && this.p.size() != 0 && this.r != null && this.r.size() != 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(this.r);
            for (int size = arrayList2.size() - 1; size >= 0; size--) {
                if (TextUtils.isEmpty((CharSequence) arrayList2.get(size)) || TextUtils.equals("0", (CharSequence) arrayList2.get(size))) {
                    arrayList2.remove(size);
                }
            }
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                String str = (String) arrayList2.get(i2);
                int i3 = 0;
                while (true) {
                    if (i3 >= this.p.size()) {
                        break;
                    }
                    SceneApi.SmartHomeScene smartHomeScene = this.p.get(i3);
                    if (TextUtils.equals(str, smartHomeScene.f)) {
                        arrayList.add(smartHomeScene);
                        this.p.remove(smartHomeScene);
                        break;
                    }
                    i3++;
                }
            }
            this.p.addAll(0, arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i2) {
        String str = e;
        LogUtil.a(str, "notifyFailed" + i2);
        this.l = false;
        this.m = false;
        for (int i3 = 0; i3 < this.u.size(); i3++) {
            if (this.u.get(i3).get() != null) {
                ((IScenceListener) this.u.get(i3).get()).b(i2);
            }
        }
        for (int i4 = 0; i4 < this.v.size(); i4++) {
            this.v.get(i4).b(i2);
        }
        this.v.clear();
    }

    public static synchronized LiteSceneManager j() {
        LiteSceneManager liteSceneManager;
        synchronized (LiteSceneManager.class) {
            if (x == null) {
                x = new LiteSceneManager();
            }
            liteSceneManager = x;
        }
        return liteSceneManager;
    }

    public void k() {
        LogUtil.a(e, "clear");
        LiteSceneManager liteSceneManager = x;
        x = new LiteSceneManager();
        x.u.addAll(liteSceneManager.u);
    }

    public void l() {
        LogUtil.a(e, "destory");
        x = null;
        try {
            if (this.s != null) {
                this.s.removeCallbacksAndMessages((Object) null);
            }
            this.o.quit();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
