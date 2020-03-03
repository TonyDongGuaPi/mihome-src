package com.xiaomi.smarthome.framework.api.profile;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserConfigCompactManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1532a = "UserConfigCompactManager";
    private static final String b = "user/get_user_config";
    private static final int c = 123;
    private static UserConfigCompactManager d = null;
    /* access modifiers changed from: private */
    public static List<RequestBody> e = new CopyOnWriteArrayList();
    private static final int p = 5000;
    private JsonParser<ArrayList<JSONObject>> f = new JsonParser<ArrayList<JSONObject>>() {
        /* renamed from: a */
        public ArrayList<JSONObject> parse(JSONObject jSONObject) throws JSONException {
            ArrayList<JSONObject> arrayList = new ArrayList<>();
            JSONArray optJSONArray = jSONObject.optJSONArray("result");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        arrayList.add(jSONObject2);
                    }
                }
            }
            return arrayList;
        }
    };
    private JsonParser<UserConfig> g = new JsonParser<UserConfig>() {
        /* renamed from: a */
        public UserConfig parse(JSONObject jSONObject) throws JSONException {
            return UserConfig.a(jSONObject);
        }
    };
    private JsonParser<UserConfig.UserConfigData> h = new JsonParser<UserConfig.UserConfigData>() {
        /* renamed from: a */
        public UserConfig.UserConfigData parse(JSONObject jSONObject) throws JSONException {
            return UserConfig.UserConfigData.a(jSONObject);
        }
    };
    /* access modifiers changed from: private */
    public Map<Integer, Boolean> i = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Integer, Error> j = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public volatile AtomicBoolean k = new AtomicBoolean(true);
    private AtomicBoolean l = new AtomicBoolean(true);
    private Map<Integer, ConcurrentHashMap<String, JSONObject>> m = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<RequestBody, AsyncCallback> n = new ConcurrentHashMap();
    private Handler o;
    private BroadcastReceiver q = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals("action_on_login_success")) {
                    LogUtil.a(UserConfigCompactManager.f1532a, "login status change: success");
                    if (!UserConfigCompactManager.this.k.get()) {
                        UserConfigCompactManager.this.k.set(false);
                        UserConfigCompactManager.this.d();
                    }
                } else if (action.equals("action_on_logout")) {
                    LogUtil.a(UserConfigCompactManager.f1532a, "login status change: failed");
                    UserConfigCompactManager.this.k.set(false);
                    UserConfigCompactManager.this.d();
                }
            }
        }
    };
    private AtomicBoolean r = new AtomicBoolean(false);

    static {
        e.add(new RequestBody(1, new String[]{"1", "2"}));
        e.add(new RequestBody(0, new String[]{"0", "5", "7", "8", UserConfig.g, Tags.LuckyShake.VALUE_SUCCESS_CODE, "1001", "2001", "3001", "4000", "4001", "4002", "4003"}));
        e.add(new RequestBody(7, new String[]{"100", "1001", "3001", "3002", "4001", "4002", "4003", "5001", "5002", "5003", "6001"}));
    }

    private UserConfigCompactManager() {
        e();
    }

    public static UserConfigCompactManager a() {
        if (d == null) {
            synchronized (UserConfigCompactManager.class) {
                if (d == null) {
                    d = new UserConfigCompactManager();
                }
            }
        }
        return d;
    }

    /* access modifiers changed from: private */
    public void d() {
        LogUtil.a(f1532a, "reset");
        this.l.set(true);
        this.n.clear();
        this.m.clear();
        this.i.clear();
        this.r.set(false);
        if (this.o != null) {
            this.o.removeCallbacksAndMessages((Object) null);
            this.o = null;
        }
        for (RequestBody requestBody : e) {
            requestBody.d = false;
        }
    }

    public void b() {
        try {
            if (!this.k.get()) {
                this.k.set(true);
                d();
                LogUtil.a(f1532a, "onCreate: ");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).registerReceiver(this.q, intentFilter);
    }

    @SuppressLint({"HandlerLeak"})
    private Handler f() {
        if (this.o == null) {
            this.o = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message message) {
                    LogUtil.a(UserConfigCompactManager.f1532a, "release all");
                    if (message.what == 123) {
                        synchronized (UserConfigCompactManager.this) {
                            UserConfigCompactManager.this.k.set(false);
                            if (!UserConfigCompactManager.this.n.isEmpty()) {
                                for (RequestBody requestBody : UserConfigCompactManager.e) {
                                    LogUtil.a(UserConfigCompactManager.f1532a, "deliver left callback : " + requestBody);
                                    UserConfigCompactManager.this.a(requestBody.f16472a, (Error) UserConfigCompactManager.this.j.get(Integer.valueOf(requestBody.f16472a)));
                                }
                            }
                            UserConfigCompactManager.this.d();
                        }
                    }
                }
            };
        }
        return this.o;
    }

    public synchronized boolean a(Context context, int i2, String[] strArr, AsyncCallback<ArrayList<UserConfig>, Error> asyncCallback) {
        if (this.k.get()) {
            a(context);
            if (a(new RequestBody(i2, strArr), (AsyncCallback) asyncCallback)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0022, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(android.content.Context r3, int r4, org.json.JSONArray r5, com.xiaomi.smarthome.frame.AsyncCallback<java.util.Map<java.lang.Integer, com.xiaomi.smarthome.framework.api.UserConfig.UserConfigData>, com.xiaomi.smarthome.frame.Error> r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.concurrent.atomic.AtomicBoolean r0 = r2.k     // Catch:{ all -> 0x0023 }
            boolean r0 = r0.get()     // Catch:{ all -> 0x0023 }
            r1 = 0
            if (r0 == 0) goto L_0x0021
            r2.a((android.content.Context) r3)     // Catch:{ all -> 0x0023 }
            com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager$RequestBody r3 = new com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager$RequestBody     // Catch:{ JSONException -> 0x001b }
            r3.<init>((int) r4, (org.json.JSONArray) r5)     // Catch:{ JSONException -> 0x001b }
            boolean r3 = r2.a((com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.RequestBody) r3, (com.xiaomi.smarthome.frame.AsyncCallback) r6)     // Catch:{ JSONException -> 0x001b }
            if (r3 == 0) goto L_0x0021
            r3 = 1
            monitor-exit(r2)
            return r3
        L_0x001b:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x0023 }
            monitor-exit(r2)
            return r1
        L_0x0021:
            monitor-exit(r2)
            return r1
        L_0x0023:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.a(android.content.Context, int, org.json.JSONArray, com.xiaomi.smarthome.frame.AsyncCallback):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(android.content.Context r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.xiaomi.smarthome.AppStateNotifier r0 = com.xiaomi.smarthome.application.ServiceApplication.getStateNotifier()     // Catch:{ all -> 0x0028 }
            int r0 = r0.a()     // Catch:{ all -> 0x0028 }
            r1 = 4
            if (r0 == r1) goto L_0x000e
            monitor-exit(r2)
            return
        L_0x000e:
            java.util.concurrent.atomic.AtomicBoolean r0 = r2.l     // Catch:{ all -> 0x0028 }
            boolean r0 = r0.get()     // Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x0026
            java.util.concurrent.atomic.AtomicBoolean r0 = r2.l     // Catch:{ all -> 0x0028 }
            r1 = 0
            r0.set(r1)     // Catch:{ all -> 0x0028 }
            r2.b((android.content.Context) r3)     // Catch:{ all -> 0x0028 }
            java.lang.String r3 = "UserConfigCompactManager"
            java.lang.String r0 = "ready to compact"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r3, r0)     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r2)
            return
        L_0x0028:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.a(android.content.Context):void");
    }

    private boolean a(RequestBody requestBody, AsyncCallback asyncCallback) {
        for (RequestBody next : e) {
            if (next.f16472a == requestBody.f16472a && next.b.containsAll(requestBody.b)) {
                LogUtil.a(f1532a, "put: " + requestBody.toString());
                this.n.put(requestBody, asyncCallback);
                a(requestBody);
                return true;
            }
        }
        return false;
    }

    private void a(RequestBody requestBody) {
        Boolean bool = this.i.get(Integer.valueOf(requestBody.f16472a));
        if (bool == null) {
            LogUtil.a(f1532a, "Deliver Is Not Ready, pending" + requestBody);
        } else if (bool.booleanValue()) {
            a(requestBody.f16472a, (Error) null);
        } else {
            a(requestBody.f16472a, this.j.get(Integer.valueOf(requestBody.f16472a)));
        }
    }

    private void b(Context context) {
        for (RequestBody a2 : e) {
            a(context, a2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.RequestBody r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = r3.r     // Catch:{ all -> 0x0049 }
            boolean r0 = r0.get()     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r3)
            return
        L_0x000b:
            r0 = 1
            r4.d = r0     // Catch:{ all -> 0x0049 }
            java.util.List<com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager$RequestBody> r4 = e     // Catch:{ all -> 0x0049 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0049 }
        L_0x0014:
            boolean r1 = r4.hasNext()     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0026
            java.lang.Object r1 = r4.next()     // Catch:{ all -> 0x0049 }
            com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager$RequestBody r1 = (com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.RequestBody) r1     // Catch:{ all -> 0x0049 }
            boolean r1 = r1.d     // Catch:{ all -> 0x0049 }
            if (r1 != 0) goto L_0x0014
            r4 = 0
            goto L_0x0027
        L_0x0026:
            r4 = 1
        L_0x0027:
            if (r4 == 0) goto L_0x0047
            java.util.concurrent.atomic.AtomicBoolean r4 = r3.r     // Catch:{ all -> 0x0049 }
            r4.set(r0)     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "UserConfigCompactManager"
            java.lang.String r0 = "release after 5s "
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r4, r0)     // Catch:{ all -> 0x0049 }
            android.os.Handler r4 = r3.f()     // Catch:{ all -> 0x0049 }
            r0 = 123(0x7b, float:1.72E-43)
            r4.removeMessages(r0)     // Catch:{ all -> 0x0049 }
            android.os.Handler r4 = r3.f()     // Catch:{ all -> 0x0049 }
            r1 = 5000(0x1388, double:2.4703E-320)
            r4.sendEmptyMessageDelayed(r0, r1)     // Catch:{ all -> 0x0049 }
        L_0x0047:
            monitor-exit(r3)
            return
        L_0x0049:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.b(com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager$RequestBody):void");
    }

    private AsyncHandle a(Context context, final RequestBody requestBody) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("component_id", requestBody.f16472a);
            JSONArray jSONArray = new JSONArray();
            for (String put : requestBody.b) {
                jSONArray.put(put);
            }
            jSONObject.put(QuickTimeAtomTypes.h, jSONArray);
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/user/get_user_config").b((List<KeyValuePair>) arrayList).a(), this.f, Crypto.RC4, new AsyncCallback<ArrayList<JSONObject>, Error>() {
                /* renamed from: b */
                public void onCache(ArrayList<JSONObject> arrayList) {
                }

                /* renamed from: a */
                public void onSuccess(ArrayList<JSONObject> arrayList) {
                    LogUtil.a(UserConfigCompactManager.f1532a, "delegate request: " + requestBody);
                    LogUtil.a(UserConfigCompactManager.f1532a, "delegate onSuccess: " + UserConfigCompactManager.this.a((Object) arrayList));
                    UserConfigCompactManager.this.a(requestBody, arrayList);
                    UserConfigCompactManager.this.a(requestBody.f16472a, (Error) null);
                    UserConfigCompactManager.this.i.put(Integer.valueOf(requestBody.f16472a), true);
                    UserConfigCompactManager.this.b(requestBody);
                }

                public void onFailure(Error error) {
                    LogUtil.a(UserConfigCompactManager.f1532a, "delegate request: " + requestBody.toString());
                    LogUtil.a(UserConfigCompactManager.f1532a, "delegate failed: " + error.b());
                    UserConfigCompactManager.this.a(requestBody.f16472a, error);
                    UserConfigCompactManager.this.i.put(Integer.valueOf(requestBody.f16472a), false);
                    UserConfigCompactManager.this.j.put(Integer.valueOf(requestBody.f16472a), error);
                    UserConfigCompactManager.this.b(requestBody);
                }
            });
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new AsyncHandle(null);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(int i2, Error error) {
        ArrayList<RequestBody> arrayList = new ArrayList<>();
        for (Map.Entry next : this.n.entrySet()) {
            RequestBody requestBody = (RequestBody) next.getKey();
            if (requestBody.f16472a == i2) {
                arrayList.add(requestBody);
                AsyncCallback asyncCallback = (AsyncCallback) next.getValue();
                if (asyncCallback == null) {
                    continue;
                } else if (error == null) {
                    try {
                        Object c2 = c(requestBody);
                        LogUtil.a(f1532a, "real request: " + requestBody.toString());
                        LogUtil.a(f1532a, "real response success: " + a(c2));
                        asyncCallback.sendSuccessMessage(c2);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        asyncCallback.sendFailureMessage(new Error(-1000, e2.getMessage()));
                        LogUtil.a(f1532a, "real request: " + requestBody.toString());
                        LogUtil.a(f1532a, "real response failed: " + e2.getMessage());
                    }
                } else {
                    LogUtil.a(f1532a, "real request: " + requestBody.toString());
                    LogUtil.a(f1532a, "real response failed: " + error.b());
                    asyncCallback.sendFailureMessage(new Error(-9999, error.b()));
                }
            }
        }
        if (arrayList.size() != 0) {
            for (RequestBody remove : arrayList) {
                this.n.remove(remove);
            }
        }
    }

    private void g() {
        boolean z;
        if (this.n.isEmpty()) {
            Iterator<RequestBody> it = e.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (this.i.get(Integer.valueOf(it.next().f16472a)) == null) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                LogUtil.a(f1532a, "clear all ... @thread id : " + Thread.currentThread().getId());
                this.k.set(false);
                d();
            }
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized void a(com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.RequestBody r4, java.util.ArrayList<org.json.JSONObject> r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            monitor-enter(r3)     // Catch:{ all -> 0x0043 }
            java.util.Map<java.lang.Integer, java.util.concurrent.ConcurrentHashMap<java.lang.String, org.json.JSONObject>> r0 = r3.m     // Catch:{ all -> 0x0040 }
            int r1 = r4.f16472a     // Catch:{ all -> 0x0040 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0040 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0040 }
            java.util.concurrent.ConcurrentMap r0 = (java.util.concurrent.ConcurrentMap) r0     // Catch:{ all -> 0x0040 }
            if (r0 != 0) goto L_0x0025
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap     // Catch:{ all -> 0x0040 }
            r0.<init>()     // Catch:{ all -> 0x0040 }
            java.util.Map<java.lang.Integer, java.util.concurrent.ConcurrentHashMap<java.lang.String, org.json.JSONObject>> r1 = r3.m     // Catch:{ all -> 0x0040 }
            int r4 = r4.f16472a     // Catch:{ all -> 0x0040 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0040 }
            r2 = r0
            java.util.concurrent.ConcurrentHashMap r2 = (java.util.concurrent.ConcurrentHashMap) r2     // Catch:{ all -> 0x0040 }
            r1.put(r4, r2)     // Catch:{ all -> 0x0040 }
        L_0x0025:
            monitor-exit(r3)     // Catch:{ all -> 0x0040 }
            java.util.Iterator r4 = r5.iterator()     // Catch:{ all -> 0x0043 }
        L_0x002a:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0043 }
            if (r5 == 0) goto L_0x003e
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0043 }
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ all -> 0x0043 }
            java.lang.String r1 = r3.a((org.json.JSONObject) r5)     // Catch:{ all -> 0x0043 }
            r0.put(r1, r5)     // Catch:{ all -> 0x0043 }
            goto L_0x002a
        L_0x003e:
            monitor-exit(r3)
            return
        L_0x0040:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0040 }
            throw r4     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager.a(com.xiaomi.smarthome.framework.api.profile.UserConfigCompactManager$RequestBody, java.util.ArrayList):void");
    }

    private String a(JSONObject jSONObject) {
        return jSONObject.optString("key");
    }

    private synchronized Object c(RequestBody requestBody) throws JSONException {
        if (requestBody.c == 1) {
            Map map = this.m.get(Integer.valueOf(requestBody.f16472a));
            ArrayList arrayList = new ArrayList();
            for (String next : requestBody.b) {
                JSONObject jSONObject = (JSONObject) map.get(next);
                if (jSONObject == null) {
                    LogUtil.b(f1532a, "config data is null type 1 with componentId: " + requestBody.f16472a + " ;key: " + next + " ;should not happened or user have never use this field.");
                } else {
                    arrayList.add(this.g.parse(jSONObject));
                }
            }
            return arrayList;
        } else if (requestBody.c != 2) {
            return null;
        } else {
            Map map2 = this.m.get(Integer.valueOf(requestBody.f16472a));
            if (map2 == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (String next2 : requestBody.b) {
                JSONObject jSONObject2 = (JSONObject) map2.get(next2);
                if (jSONObject2 == null) {
                    LogUtil.b(f1532a, "config data is null type 2 with componentId: " + requestBody.f16472a + " ;key: " + next2 + " ; should not happened or user have never use this field.");
                } else {
                    hashMap.put(Integer.valueOf(Integer.parseInt(next2)), this.h.parse(jSONObject2));
                }
            }
            return hashMap;
        }
    }

    static class RequestBody {
        private static final int e = 1;
        private static final int f = 2;

        /* renamed from: a  reason: collision with root package name */
        int f16472a;
        Set<String> b;
        int c;
        boolean d = false;

        RequestBody(int i, String[] strArr) {
            this.f16472a = i;
            this.b = new HashSet(Arrays.asList(strArr));
            this.c = 1;
        }

        RequestBody(int i, JSONArray jSONArray) throws JSONException {
            int length = jSONArray.length();
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = jSONArray.getString(i2);
            }
            this.f16472a = i;
            this.b = new HashSet(Arrays.asList(strArr));
            this.c = 2;
        }

        public String toString() {
            return "RequestBody{componentId=" + this.f16472a + ", keys=" + this.b + ", type=" + this.c + Operators.BLOCK_END;
        }
    }

    /* access modifiers changed from: private */
    public String a(Object obj) {
        Object obj2;
        if (obj == null) {
            return "obj is null";
        }
        if (obj instanceof List) {
            StringBuilder sb = new StringBuilder();
            for (Object next : (List) obj) {
                if (next != null) {
                    if (next instanceof UserConfig) {
                        try {
                            sb.append(UserConfig.a((UserConfig) next));
                            sb.append(" ");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                            sb.append(" ");
                        }
                    } else {
                        sb.append(next.toString());
                        sb.append(" ");
                    }
                }
            }
            if (TextUtils.isEmpty(sb.toString())) {
                return "list is empty";
            }
            return sb.toString();
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            Set keySet = map.keySet();
            StringBuilder sb2 = new StringBuilder();
            for (Object next2 : keySet) {
                if (!(next2 == null || (obj2 = map.get(next2)) == null)) {
                    if (obj2 instanceof UserConfig.UserConfigData) {
                        sb2.append(((UserConfig.UserConfigData) obj2).c);
                        sb2.append(" ");
                    } else {
                        sb2.append(obj2.toString());
                        sb2.append(" ");
                    }
                }
            }
            if (TextUtils.isEmpty(sb2.toString())) {
                return "map is empty";
            }
            return sb2.toString();
        } else if (TextUtils.isEmpty(obj.toString())) {
            return "obj.toString() is empty";
        } else {
            return obj.toString();
        }
    }
}
