package com.xiaomi.smarthome.scene.timer;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.timer.TimerCommonManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimerCommonGroupManager extends TimerCommonManager {
    private static final String G = "TimerCommonGroupManager";
    private static final String H = "us_id";
    private static final String I = "home_id";
    private static final String J = "uid";
    private static final String K = "identify";
    private static final String L = "st_id";
    private static final String M = "name";
    private static final String N = "setting";
    private static final String O = "enable_push";
    private static final String P = "enable_timer";
    private static final String Q = "enable_timer_off";
    private static final String R = "enable_timer_on";
    private static final String S = "status";
    private static final String T = "timer_type";
    private static final String U = "on_time";
    private static TimerCommonGroupManager V;
    private String W;
    private String X;
    private SharedPreferences Y;
    /* access modifiers changed from: private */
    public boolean Z = false;
    /* access modifiers changed from: private */
    public List<TimerCommonManager.PlugSceneListener> aa = new ArrayList();
    private List<List<CommonTimer>> ab = new ArrayList();

    public static TimerCommonGroupManager a() {
        if (V == null) {
            V = new TimerCommonGroupManager();
        }
        return V;
    }

    private TimerCommonGroupManager() {
        this.E = new ArrayList();
        this.F = new ArrayList();
    }

    public void a(TimerCommonManager.PlugSceneListener plugSceneListener) {
        super.a(plugSceneListener);
        if (!this.aa.contains(plugSceneListener)) {
            this.aa.add(plugSceneListener);
        }
    }

    public void b(TimerCommonManager.PlugSceneListener plugSceneListener) {
        this.aa.remove(plugSceneListener);
    }

    public void a(Device device, String str, String str2) {
        this.D = device;
        this.W = str;
        this.X = str2;
        this.E.clear();
        Context appContext = SHApplication.getAppContext();
        this.Y = appContext.getSharedPreferences(TimerCommonManager.f21696a + this.D.userId + this.D.did + this.W + "_group", 0);
        try {
            e();
        } catch (JSONException unused) {
        }
    }

    public List<CommonTimer> b() {
        if (this.E == null || this.E.size() <= 0) {
            return this.E;
        }
        ArrayList arrayList = new ArrayList();
        for (CommonTimer commonTimer : this.E) {
            if (commonTimer != null) {
                if (TextUtils.isEmpty(commonTimer.m)) {
                    arrayList.add(commonTimer);
                } else if ("0".equals(commonTimer.m)) {
                    arrayList.add(commonTimer);
                }
            }
        }
        return arrayList;
    }

    public List<CommonTimer> c() {
        return this.E;
    }

    public List<List<CommonTimer>> d() {
        return this.ab;
    }

    private class CompTimer implements Comparator<CommonTimer> {
        private CompTimer() {
        }

        /* renamed from: a */
        public int compare(CommonTimer commonTimer, CommonTimer commonTimer2) {
            int i;
            int i2;
            if (commonTimer.e) {
                i = (commonTimer.h.c * 60) + commonTimer.h.b;
            } else {
                i = (commonTimer.l.c * 60) + commonTimer.l.b;
            }
            if (commonTimer2.e) {
                i2 = (commonTimer2.h.c * 60) + commonTimer2.h.b;
            } else {
                i2 = (commonTimer2.l.c * 60) + commonTimer2.l.b;
            }
            if (i < i2) {
                return -1;
            }
            return i == i2 ? 0 : 1;
        }
    }

    public void e() throws JSONException {
        String string = this.Y.getString(TimerCommonManager.b, (String) null);
        if (string != null) {
            Log.d(G, "readSharedPrefs reading :");
            List<CommonTimer> c = c(new JSONObject(string));
            if (c != null) {
                this.E.clear();
                this.E.addAll(c);
                this.F.clear();
                this.F.addAll(c);
            }
        } else {
            Log.d(G, "readSharedPrefs failure");
            this.E.clear();
            this.F.clear();
        }
        for (TimerCommonManager.PlugSceneListener onGetSceneSuccess : this.aa) {
            onGetSceneSuccess.onGetSceneSuccess();
        }
    }

    public void a(String str) {
        SharedPreferences.Editor edit = this.Y.edit();
        edit.remove(TimerCommonManager.b);
        if (str != null) {
            edit.putString(TimerCommonManager.b, str);
        }
        edit.apply();
    }

    public void f() {
        b((String) null);
    }

    public void b(String str) {
        if (!this.Z) {
            this.Z = true;
            boolean z = !TextUtils.isEmpty(str);
            Log.d(G, "getScene start:");
            RemoteSceneApi a2 = RemoteSceneApi.a();
            Context appContext = SHApplication.getAppContext();
            String str2 = this.D.did;
            if (!z) {
                str = this.W;
            }
            a2.a(appContext, 8, str2, str, this.X, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean unused = TimerCommonGroupManager.this.Z = false;
                    Log.d(TimerCommonGroupManager.G, "getScene result: " + jSONObject.toString());
                    if (jSONObject != null) {
                        TimerCommonGroupManager.this.a(jSONObject);
                        TimerCommonGroupManager.this.a(TimerCommonGroupManager.this.g().toString());
                    } else {
                        TimerCommonGroupManager.this.a((String) null);
                    }
                    for (TimerCommonManager.PlugSceneListener onGetSceneSuccess : TimerCommonGroupManager.this.aa) {
                        onGetSceneSuccess.onGetSceneSuccess();
                    }
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonGroupManager.this.Z = false;
                    Log.d(TimerCommonGroupManager.G, "getScene error: " + error.a());
                    for (TimerCommonManager.PlugSceneListener onGetSceneFailed : TimerCommonGroupManager.this.aa) {
                        onGetSceneFailed.onGetSceneFailed(error.a());
                    }
                }
            }, z);
        }
    }

    private CommonTimer d(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        CommonTimer commonTimer = new CommonTimer();
        commonTimer.f21632a = jSONObject.optString("us_id");
        commonTimer.b = jSONObject.optString(K);
        JSONObject optJSONObject = jSONObject.optJSONObject("setting");
        commonTimer.c = optJSONObject.optString(O).equals("1");
        commonTimer.d = optJSONObject.optString(P).equals("1");
        commonTimer.e = optJSONObject.optString(R).equals("1");
        commonTimer.f = optJSONObject.optString(TimerCommonManager.o);
        commonTimer.g = a(optJSONObject, TimerCommonManager.p);
        commonTimer.h = CorntabUtils.a(optJSONObject.optString(U));
        if (commonTimer.h == null) {
            commonTimer.h = new CorntabUtils.CorntabParam();
        } else {
            commonTimer.h = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.h);
        }
        commonTimer.i = optJSONObject.optString(Q).equals("1");
        commonTimer.j = optJSONObject.optString(TimerCommonManager.k);
        commonTimer.k = a(optJSONObject, TimerCommonManager.m);
        commonTimer.l = CorntabUtils.a(optJSONObject.optString(TimerCommonManager.n));
        if (commonTimer.l == null) {
            commonTimer.l = new CorntabUtils.CorntabParam();
        } else {
            commonTimer.l = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.l);
        }
        String optString = optJSONObject.optString("on_filter");
        if (!TextUtils.isEmpty(optString)) {
            commonTimer.h.h = optString;
        }
        String optString2 = optJSONObject.optString("off_filter");
        if (!TextUtils.isEmpty(optString2)) {
            commonTimer.l.h = optString2;
        }
        commonTimer.q = jSONObject.optInt("status", 0);
        String optString3 = jSONObject.optString("timer_type");
        if (!TextUtils.isEmpty(optString3)) {
            commonTimer.m = optString3;
        }
        return commonTimer;
    }

    public void a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                JSONArray optJSONArray = jSONObject.optJSONArray(keys.next());
                if (optJSONArray != null) {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            CommonTimer d = d(optJSONObject);
                            if (TextUtils.equals(d.b, this.D.did)) {
                                arrayList.add(d);
                            } else {
                                arrayList3.add(d);
                            }
                        }
                    }
                    if (arrayList3.size() > 0) {
                        Collections.sort(arrayList3, new CompTimer());
                        arrayList2.add(arrayList3);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(arrayList, new CompTimer());
        this.E = arrayList;
        this.F.clear();
        this.F.addAll(arrayList);
        this.ab = arrayList2;
    }

    public void a(CommonTimer commonTimer, JSONObject jSONObject) {
        try {
            Log.d(G, "parseComonTime:" + jSONObject.toString());
            commonTimer.f21632a = jSONObject.optString("us_id");
            commonTimer.b = jSONObject.optString(K);
            JSONObject optJSONObject = jSONObject.optJSONObject("setting");
            commonTimer.c = optJSONObject.optString(O).equals("1");
            commonTimer.d = optJSONObject.optString(P).equals("1");
            commonTimer.e = optJSONObject.optString(R).equals("1");
            commonTimer.f = optJSONObject.optString(TimerCommonManager.o);
            commonTimer.g = a(optJSONObject, TimerCommonManager.p);
            commonTimer.h = CorntabUtils.a(optJSONObject.getString(U));
            if (commonTimer.h == null) {
                commonTimer.h = new CorntabUtils.CorntabParam();
            }
            commonTimer.i = optJSONObject.optString(Q).equals("1");
            commonTimer.j = optJSONObject.optString(TimerCommonManager.k);
            commonTimer.k = a(optJSONObject, TimerCommonManager.m);
            commonTimer.l = CorntabUtils.a(optJSONObject.optString(TimerCommonManager.n));
            if (commonTimer.l == null) {
                commonTimer.l = new CorntabUtils.CorntabParam();
            }
        } catch (JSONException unused) {
        }
    }

    public List<CommonTimer> b(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (true) {
                if (!keys.hasNext()) {
                    break;
                }
                JSONObject optJSONObject = jSONObject.optJSONObject(keys.next());
                if (optJSONObject == null) {
                    break;
                }
                Log.d(G, "jsonObj:" + optJSONObject.toString());
                CommonTimer commonTimer = new CommonTimer();
                commonTimer.f21632a = optJSONObject.optString("us_id");
                commonTimer.b = optJSONObject.optString(K);
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("setting");
                commonTimer.c = optJSONObject2.optString(O).equals("1");
                commonTimer.d = optJSONObject2.optString(P).equals("1");
                commonTimer.e = optJSONObject2.optString(R).equals("1");
                commonTimer.f = optJSONObject2.optString(TimerCommonManager.o);
                commonTimer.g = a(optJSONObject2, TimerCommonManager.p);
                commonTimer.h = CorntabUtils.a(optJSONObject2.optString(U));
                if (commonTimer.h == null) {
                    commonTimer.h = new CorntabUtils.CorntabParam();
                } else {
                    commonTimer.h = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.h);
                }
                commonTimer.i = optJSONObject2.optString(Q).equals("1");
                commonTimer.j = optJSONObject2.optString(TimerCommonManager.k);
                commonTimer.k = a(optJSONObject2, TimerCommonManager.m);
                commonTimer.l = CorntabUtils.a(optJSONObject2.optString(TimerCommonManager.n));
                if (commonTimer.l == null) {
                    commonTimer.l = new CorntabUtils.CorntabParam();
                } else {
                    commonTimer.l = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.l);
                }
                commonTimer.q = optJSONObject.optInt("status", 0);
                arrayList.add(commonTimer);
            }
        } catch (Exception unused) {
        }
        Collections.sort(arrayList, new CompTimer());
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public String a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        JSONObject optJSONObject;
        String optString = jSONObject.optString(str);
        if (optString == null && (optJSONObject = jSONObject.optJSONObject(str)) != null) {
            optString = optJSONObject.toString();
        }
        if (optString == null && (optJSONArray = jSONObject.optJSONArray(str)) != null && optJSONArray.length() > 0) {
            optString = optJSONArray.toString();
        }
        return optString == null ? "" : optString;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        return new org.json.JSONArray(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c(java.lang.String r2) {
        /*
            r1 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 == 0) goto L_0x0009
            java.lang.String r2 = ""
            return r2
        L_0x0009:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x000f }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x000f }
            return r0
        L_0x000f:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0015 }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0015 }
            return r0
        L_0x0015:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.timer.TimerCommonGroupManager.c(java.lang.String):java.lang.Object");
    }

    private void a(CorntabUtils.CorntabParam corntabParam, CorntabUtils.CorntabParam corntabParam2) {
        corntabParam2.d = corntabParam.d;
        corntabParam2.e = corntabParam.e;
        corntabParam2.g = corntabParam.g;
    }

    public JSONObject g() {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            for (CommonTimer commonTimer : this.E) {
                Log.d(G, "timerToJson:" + commonTimer.b + commonTimer.f21632a);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("us_id", commonTimer.f21632a);
                jSONObject2.put(K, commonTimer.b);
                jSONObject2.put(O, commonTimer.c ? "1" : "0");
                jSONObject2.put(P, commonTimer.d ? "1" : "0");
                jSONObject2.put(Q, commonTimer.i ? "1" : "0");
                jSONObject2.put(R, commonTimer.e ? "1" : "0");
                jSONObject2.put(TimerCommonManager.k, c(commonTimer.j));
                jSONObject2.put(TimerCommonManager.m, c(commonTimer.k));
                jSONObject2.put(TimerCommonManager.n, CorntabUtils.a(commonTimer.l));
                jSONObject2.put(TimerCommonManager.o, c(commonTimer.f));
                jSONObject2.put(TimerCommonManager.p, c(commonTimer.g));
                jSONObject2.put(U, CorntabUtils.a(commonTimer.h));
                jSONObject2.put("status", commonTimer.q);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("setting", jSONArray);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public List<CommonTimer> c(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("setting");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    CommonTimer commonTimer = new CommonTimer();
                    commonTimer.f21632a = optJSONObject.optString("us_id");
                    commonTimer.b = optJSONObject.optString(K);
                    commonTimer.c = optJSONObject.optString(O).equals("1");
                    commonTimer.d = optJSONObject.optString(P).equals("1");
                    commonTimer.e = optJSONObject.optString(R).equals("1");
                    commonTimer.f = optJSONObject.optString(TimerCommonManager.o);
                    commonTimer.g = a(optJSONObject, TimerCommonManager.p);
                    commonTimer.h = CorntabUtils.a(optJSONObject.getString(U));
                    if (commonTimer.h == null) {
                        commonTimer.h = new CorntabUtils.CorntabParam();
                    } else {
                        commonTimer.h = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.h);
                    }
                    commonTimer.i = optJSONObject.optString(Q).equals("1");
                    commonTimer.j = optJSONObject.optString(TimerCommonManager.k);
                    commonTimer.k = a(optJSONObject, TimerCommonManager.m);
                    commonTimer.l = CorntabUtils.a(optJSONObject.optString(TimerCommonManager.n));
                    if (commonTimer.l == null) {
                        commonTimer.l = new CorntabUtils.CorntabParam();
                    } else {
                        commonTimer.l = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.l);
                    }
                    commonTimer.q = optJSONObject.optInt("status", 0);
                    arrayList.add(commonTimer);
                }
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    public void a(final CommonTimer commonTimer, final TimerCommonManager.PlugSceneListener plugSceneListener) {
        if (!this.Z) {
            this.Z = true;
            Log.d(G, "setScene start:");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(O, commonTimer.c ? "1" : "0");
                jSONObject.put(P, commonTimer.d ? "1" : "0");
                jSONObject.put(Q, commonTimer.i ? "1" : "0");
                jSONObject.put(R, commonTimer.e ? "1" : "0");
                jSONObject.put(TimerCommonManager.k, commonTimer.j);
                jSONObject.put(TimerCommonManager.m, c(commonTimer.k));
                jSONObject.put(TimerCommonManager.n, CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), commonTimer.l)));
                jSONObject.put(TimerCommonManager.o, commonTimer.f);
                jSONObject.put(TimerCommonManager.p, c(commonTimer.g));
                jSONObject.put(U, CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), commonTimer.h)));
            } catch (JSONException unused) {
            }
            Log.d(G, "setScene data: " + jSONObject.toString());
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.D.did);
            RemoteSceneApi.a().a(SHApplication.getAppContext(), 8, commonTimer.f21632a, this.D.did, this.D.did, this.X, jSONObject, jSONArray, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean unused = TimerCommonGroupManager.this.Z = false;
                    Log.d(TimerCommonGroupManager.G, "setScene success:" + jSONObject.toString());
                    try {
                        commonTimer.f21632a = jSONObject.getString("us_id");
                        commonTimer.q = jSONObject.optInt("status", 0);
                    } catch (JSONException unused2) {
                    }
                    for (CommonTimer commonTimer : TimerCommonGroupManager.this.F) {
                        Log.d(TimerCommonGroupManager.G, "result us_id:" + commonTimer.f21632a);
                    }
                    TimerCommonGroupManager.this.E.clear();
                    TimerCommonGroupManager.this.E.addAll(TimerCommonGroupManager.this.F);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneSuccess(commonTimer);
                    }
                    for (TimerCommonManager.PlugSceneListener onSetSceneSuccess : TimerCommonGroupManager.this.aa) {
                        onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                    }
                    TimerCommonGroupManager.this.a(TimerCommonGroupManager.this.g().toString());
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonGroupManager.this.Z = false;
                    Log.d(TimerCommonGroupManager.G, "setScene failed: " + error.a() + "mIsLoading" + TimerCommonGroupManager.this.Z);
                    TimerCommonGroupManager.this.F.clear();
                    TimerCommonGroupManager.this.F.addAll(TimerCommonGroupManager.this.E);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneFailed(error);
                    }
                    for (TimerCommonManager.PlugSceneListener onSetSceneFailed : TimerCommonGroupManager.this.aa) {
                        onSetSceneFailed.onSetSceneFailed(error);
                    }
                }
            });
        }
    }

    public void a(CommonTimer commonTimer, String str) {
        a(commonTimer, (TimerCommonManager.PlugSceneListener) null, str);
    }

    public void b(final CommonTimer commonTimer, final TimerCommonManager.PlugSceneListener plugSceneListener) {
        Log.d(G, "deleteTimerScene");
        if (!this.Z) {
            this.Z = true;
            Log.d(G, "mIsLoading" + this.Z);
            RemoteSceneApi.a().c(SHApplication.getAppContext(), commonTimer.f21632a, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean unused = TimerCommonGroupManager.this.Z = false;
                    Log.d(TimerCommonGroupManager.G, "delete success");
                    TimerCommonGroupManager.this.E.clear();
                    TimerCommonGroupManager.this.E.addAll(TimerCommonGroupManager.this.F);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneSuccess(commonTimer);
                    }
                    for (TimerCommonManager.PlugSceneListener onSetSceneSuccess : TimerCommonGroupManager.this.aa) {
                        onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                    }
                    TimerCommonGroupManager.this.a(TimerCommonGroupManager.this.g().toString());
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonGroupManager.this.Z = false;
                    Log.d(TimerCommonGroupManager.G, "delete failed: " + error.a());
                    TimerCommonGroupManager.this.F.clear();
                    TimerCommonGroupManager.this.F.addAll(TimerCommonGroupManager.this.E);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneFailed(error);
                    }
                    for (TimerCommonManager.PlugSceneListener onSetSceneSuccess : TimerCommonGroupManager.this.aa) {
                        onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                    }
                }
            });
        }
    }

    public int h() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        return (instance.get(7) + 5) % 7;
    }

    public boolean a(CommonTimer commonTimer, CommonTimer commonTimer2) {
        return (commonTimer.l.c * 60) + commonTimer.l.b >= (commonTimer2.h.c * 60) + commonTimer2.h.b && (commonTimer.h.c * 60) + commonTimer.h.b <= (commonTimer2.l.c * 60) + commonTimer2.l.b;
    }

    public boolean a(CommonTimer commonTimer) {
        if (!commonTimer.e || !commonTimer.i || (commonTimer.h.c * 60) + commonTimer.h.b != (commonTimer.l.c * 60) + commonTimer.l.b) {
            return true;
        }
        return false;
    }

    public void b(CommonTimer commonTimer) {
        b(commonTimer, (String) null);
    }

    public void b(CommonTimer commonTimer, String str) {
        this.F.add(commonTimer);
        a(commonTimer, str);
    }

    public void c(CommonTimer commonTimer) {
        Log.d(G, "us_id:" + commonTimer.f21632a + "name:" + commonTimer.b + "onMethod:" + commonTimer.f + "onParams:" + commonTimer.g + "offMethod:" + commonTimer.j + "offParams:" + commonTimer.k + "enablePush:" + commonTimer.c + " enable:" + commonTimer.d + " on:" + commonTimer.e + " onRepeat:" + commonTimer.h.c(SHApplication.getAppContext()) + " onHour:" + commonTimer.h.c + " onMin:" + commonTimer.h.b + " off:" + commonTimer.i + " offRepeat:" + commonTimer.l.c(SHApplication.getAppContext()) + " offHour:" + commonTimer.l.c + " offMin:" + commonTimer.l.b);
    }

    public void b(CommonTimer commonTimer, CommonTimer commonTimer2) {
        a(commonTimer, commonTimer2, "");
    }

    public void a(CommonTimer commonTimer, CommonTimer commonTimer2, String str) {
        a(commonTimer, commonTimer2, (TimerCommonManager.PlugSceneListener) null, str);
    }

    public void a(CommonTimer commonTimer, CommonTimer commonTimer2, TimerCommonManager.PlugSceneListener plugSceneListener) {
        a(commonTimer, commonTimer2, plugSceneListener, (String) null);
    }

    public void a(CommonTimer commonTimer, CommonTimer commonTimer2, TimerCommonManager.PlugSceneListener plugSceneListener, String str) {
        Log.d(G, "originTimer: ");
        c(commonTimer);
        Log.d(G, "newTimer: ");
        c(commonTimer2);
        if (this.F.isEmpty()) {
            this.F.add(commonTimer2);
        } else {
            for (int i = 0; i < this.F.size(); i++) {
                if (TextUtils.equals(((CommonTimer) this.F.get(i)).f21632a, commonTimer.f21632a)) {
                    this.F.remove(i);
                }
            }
            this.F.add(commonTimer2);
        }
        Collections.sort(this.F, new CompTimer());
        a(commonTimer2, plugSceneListener, str);
    }

    public void a(CommonTimer commonTimer, boolean z) {
        a(commonTimer, z, "");
    }

    public void a(CommonTimer commonTimer, boolean z, String str) {
        a(commonTimer, z, (TimerCommonManager.PlugSceneListener) null, str);
    }

    public void a(CommonTimer commonTimer, boolean z, TimerCommonManager.PlugSceneListener plugSceneListener) {
        a(commonTimer, z, plugSceneListener, (String) null);
    }

    public void a(CommonTimer commonTimer, boolean z, TimerCommonManager.PlugSceneListener plugSceneListener, String str) {
        CommonTimer commonTimer2 = (CommonTimer) commonTimer.clone();
        commonTimer2.d = z;
        if (z) {
            e(commonTimer2);
        }
        a(commonTimer, commonTimer2, plugSceneListener, str);
    }

    private void e(CommonTimer commonTimer) {
        Calendar instance = Calendar.getInstance();
        if (!(!commonTimer.e || commonTimer.h == null || commonTimer.h.d == -1)) {
            if ((commonTimer.h.c * 60) + commonTimer.h.b <= (instance.get(11) * 60) + instance.get(12)) {
                instance.add(5, 1);
            }
            commonTimer.h.d = instance.get(5);
            commonTimer.h.e = instance.get(2) + 1;
        }
        if (commonTimer.i && commonTimer.l != null && commonTimer.l.d != -1) {
            if ((commonTimer.l.c * 60) + commonTimer.l.b <= (instance.get(11) * 60) + instance.get(12)) {
                instance.add(5, 1);
            }
            commonTimer.l.d = instance.get(5);
            commonTimer.l.e = instance.get(2) + 1;
        }
    }

    public void c(CommonTimer commonTimer, TimerCommonManager.PlugSceneListener plugSceneListener) {
        this.F.remove(commonTimer);
        b(commonTimer, plugSceneListener);
    }
}
