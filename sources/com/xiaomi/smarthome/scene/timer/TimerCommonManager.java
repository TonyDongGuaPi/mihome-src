package com.xiaomi.smarthome.scene.timer;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimerCommonManager {
    protected static final String A = "timer_type";
    protected static final String B = "on_filter";
    protected static final String C = "off_filter";
    private static final String G = "TimerCommonManager";
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
    private static final String T = "on_time";
    private static TimerCommonManager U = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f21696a = "common.timer.shared.prefs";
    public static final String b = "common.setting";
    public static final String c = "common.countdown.minute";
    public static final String d = "common.countdown.firstin";
    public static final String e = "common.timer";
    public static final String f = "action.add.common.timer";
    public static final String g = "action_common_timer_name";
    public static final String h = "timer_identify_rn";
    public static final String i = "common_timer_display_name";
    public static final String j = "common_timer_device_id";
    public static final String k = "off_method";
    public static final String l = "target_time";
    public static final String m = "off_param";
    public static final String n = "off_time";
    public static final String o = "on_method";
    public static final String p = "on_param";
    public static final String q = "device_power_on";
    public static final String r = "custom_title_text";
    public static final String s = "shouldIncludeGroup";
    public static final String t = "off_gone";
    public static final String u = "on_gone";
    public static final String v = "timer_manager";
    public static final String w = "timer_type";
    public static final int x = 0;
    public static final int y = 1;
    public static final int z = 2;
    protected Device D;
    protected List<CommonTimer> E;
    protected List<CommonTimer> F;
    private String V;
    private String W;
    private SharedPreferences X;
    /* access modifiers changed from: private */
    public boolean Y;
    /* access modifiers changed from: private */
    public List<PlugSceneListener> Z;

    public interface PlugSceneListener {
        void onGetSceneFailed(int i);

        void onGetSceneSuccess();

        void onSetSceneFailed(Error error);

        void onSetSceneSuccess(CommonTimer commonTimer);
    }

    public void c(CommonTimer commonTimer) {
    }

    public List<List<CommonTimer>> d() {
        return null;
    }

    public static TimerCommonManager i() {
        if (U == null) {
            U = new TimerCommonManager();
        }
        return U;
    }

    protected TimerCommonManager() {
        this.Y = false;
        this.Z = new ArrayList();
        this.E = new ArrayList();
        this.F = new ArrayList();
        this.E = new ArrayList();
        this.F = new ArrayList();
    }

    public void a(PlugSceneListener plugSceneListener) {
        if (!this.Z.contains(plugSceneListener)) {
            this.Z.add(plugSceneListener);
        }
    }

    public void b(PlugSceneListener plugSceneListener) {
        this.Z.remove(plugSceneListener);
    }

    public void a(Device device, String str, String str2) {
        this.D = device;
        this.V = str;
        this.W = str2;
        this.E.clear();
        try {
            Context appContext = SHApplication.getAppContext();
            this.X = appContext.getSharedPreferences(f21696a + this.D.userId + this.D.did + this.V, 0);
        } catch (Exception unused) {
            this.X = SHApplication.getAppContext().getSharedPreferences(Base64.encodeToString((f21696a + this.D.userId + this.D.did + this.V).getBytes(), 0), 0);
        }
        try {
            e();
        } catch (JSONException unused2) {
        }
    }

    public boolean j() {
        if (this.D != null && !TextUtils.isEmpty(this.D.model)) {
            return TimerCommonConfigManager.a().a(this.D.model);
        }
        return false;
    }

    public List<CommonTimer> c() {
        return this.E;
    }

    public List<CommonTimer> b() {
        if (this.E == null || this.E.size() <= 0) {
            return this.E;
        }
        ArrayList arrayList = new ArrayList();
        for (CommonTimer next : this.E) {
            if (next != null) {
                if (TextUtils.isEmpty(next.m)) {
                    arrayList.add(next);
                } else if ("0".equals(next.m)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public List<PlugTimer> k() {
        List<CommonTimer> b2 = b();
        if (b2 == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (CommonTimer next : b2) {
            if (next != null && !TextUtils.isEmpty(next.m) && "1".equals(next.m)) {
                arrayList.add(CommonTimer.a(next));
            }
        }
        return arrayList;
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

    public void a(int[] iArr) {
        int length = iArr.length;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 > 0) {
                sb.append(",");
            }
            sb.append(iArr[i2]);
        }
        String sb2 = sb.toString();
        if (sb2 != null) {
            SharedPreferences.Editor edit = this.X.edit();
            edit.remove(c);
            edit.putString(c, sb2);
            edit.apply();
        }
    }

    public boolean l() {
        return this.X.getBoolean(d, true);
    }

    public void m() {
        SharedPreferences.Editor edit = this.X.edit();
        edit.putBoolean(d, false);
        edit.apply();
    }

    public List<Integer> n() {
        String string = this.X.getString(c, (String) null);
        if (TextUtils.isEmpty(string)) {
            return Collections.EMPTY_LIST;
        }
        String[] split = string.split(",");
        ArrayList arrayList = new ArrayList();
        for (String valueOf : split) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void e() throws JSONException {
        String string = this.X.getString(b, (String) null);
        if (string != null) {
            List<CommonTimer> c2 = c(new JSONObject(string));
            if (c2 != null) {
                this.E.clear();
                this.E.addAll(c2);
                this.F.clear();
                this.F.addAll(c2);
            }
        } else {
            this.E.clear();
            this.F.clear();
        }
        for (PlugSceneListener onGetSceneSuccess : this.Z) {
            onGetSceneSuccess.onGetSceneSuccess();
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        SharedPreferences.Editor edit = this.X.edit();
        edit.remove(b);
        if (str != null) {
            edit.putString(b, str);
        }
        edit.apply();
    }

    public void f() {
        b((String) null);
    }

    public void b(String str) {
        if (!this.Y) {
            this.Y = true;
            boolean z2 = !TextUtils.isEmpty(str);
            RemoteSceneApi a2 = RemoteSceneApi.a();
            Context appContext = SHApplication.getAppContext();
            String str2 = this.D.did;
            if (!z2) {
                str = this.V;
            }
            a2.b(appContext, 8, str2, str, this.W, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    if (jSONObject != null) {
                        TimerCommonManager.this.a(jSONObject);
                        TimerCommonManager.this.a(TimerCommonManager.this.g().toString());
                    } else {
                        TimerCommonManager.this.a((String) null);
                    }
                    for (PlugSceneListener onGetSceneSuccess : TimerCommonManager.this.Z) {
                        onGetSceneSuccess.onGetSceneSuccess();
                    }
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    for (PlugSceneListener onGetSceneFailed : TimerCommonManager.this.Z) {
                        onGetSceneFailed.onGetSceneFailed(error.a());
                    }
                }
            }, z2);
        }
    }

    public void a(JSONObject jSONObject) {
        List<CommonTimer> b2 = b(jSONObject);
        if (b2 != null) {
            this.E.clear();
            this.E.addAll(b2);
            this.F.clear();
            this.F.addAll(b2);
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
                CommonTimer commonTimer = new CommonTimer();
                commonTimer.f21632a = optJSONObject.optString("us_id");
                commonTimer.b = optJSONObject.optString(K);
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("setting");
                commonTimer.c = optJSONObject2.optString(O).equals("1");
                commonTimer.d = optJSONObject2.optString(P).equals("1");
                commonTimer.e = optJSONObject2.optString(R).equals("1");
                commonTimer.f = optJSONObject2.optString(o);
                commonTimer.g = a(optJSONObject2, p);
                commonTimer.h = CorntabUtils.a(optJSONObject2.optString(T));
                if (commonTimer.h == null) {
                    commonTimer.h = new CorntabUtils.CorntabParam();
                } else {
                    commonTimer.h = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.h);
                }
                commonTimer.i = optJSONObject2.optString(Q).equals("1");
                commonTimer.j = optJSONObject2.optString(k);
                commonTimer.k = a(optJSONObject2, m);
                commonTimer.l = CorntabUtils.a(optJSONObject2.optString(n));
                if (commonTimer.l == null) {
                    commonTimer.l = new CorntabUtils.CorntabParam();
                } else {
                    commonTimer.l = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.l);
                }
                String optString = optJSONObject2.optString(B);
                if (!TextUtils.isEmpty(optString)) {
                    commonTimer.h.h = optString;
                }
                String optString2 = optJSONObject2.optString(C);
                if (!TextUtils.isEmpty(optString2)) {
                    commonTimer.l.h = optString2;
                }
                commonTimer.q = optJSONObject.optInt("status", 0);
                String optString3 = optJSONObject2.optString("timer_type");
                if (!TextUtils.isEmpty(optString3)) {
                    commonTimer.m = optString3;
                }
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.timer.TimerCommonManager.c(java.lang.String):java.lang.Object");
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
            for (CommonTimer next : this.E) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("us_id", next.f21632a);
                jSONObject2.put(K, next.b);
                jSONObject2.put(O, next.c ? "1" : "0");
                jSONObject2.put(P, next.d ? "1" : "0");
                jSONObject2.put(Q, next.i ? "1" : "0");
                jSONObject2.put(R, next.e ? "1" : "0");
                jSONObject2.put(k, c(next.j));
                jSONObject2.put(m, c(next.k));
                jSONObject2.put(n, CorntabUtils.a(next.l));
                jSONObject2.put(o, c(next.f));
                jSONObject2.put(p, c(next.g));
                jSONObject2.put(T, CorntabUtils.a(next.h));
                jSONObject2.put("status", next.q);
                jSONObject2.put("timer_type", next.m);
                if (!TextUtils.isEmpty(next.h.h)) {
                    jSONObject2.put(B, next.h.h);
                }
                if (!TextUtils.isEmpty(next.l.h)) {
                    jSONObject2.put(C, next.l.h);
                }
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
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    CommonTimer commonTimer = new CommonTimer();
                    commonTimer.f21632a = optJSONObject.optString("us_id");
                    commonTimer.b = optJSONObject.optString(K);
                    commonTimer.c = optJSONObject.optString(O).equals("1");
                    commonTimer.d = optJSONObject.optString(P).equals("1");
                    commonTimer.e = optJSONObject.optString(R).equals("1");
                    commonTimer.f = optJSONObject.optString(o);
                    commonTimer.g = a(optJSONObject, p);
                    commonTimer.h = CorntabUtils.a(optJSONObject.getString(T));
                    if (commonTimer.h == null) {
                        commonTimer.h = new CorntabUtils.CorntabParam();
                    } else {
                        commonTimer.h = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.h);
                    }
                    commonTimer.i = optJSONObject.optString(Q).equals("1");
                    commonTimer.j = optJSONObject.optString(k);
                    commonTimer.k = a(optJSONObject, m);
                    commonTimer.l = CorntabUtils.a(optJSONObject.optString(n));
                    if (commonTimer.l == null) {
                        commonTimer.l = new CorntabUtils.CorntabParam();
                    } else {
                        commonTimer.l = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), commonTimer.l);
                    }
                    String optString = optJSONObject.optString("timer_type");
                    if (!TextUtils.isEmpty(optString)) {
                        commonTimer.m = optString;
                    }
                    String optString2 = optJSONObject.optString(B);
                    if (!TextUtils.isEmpty(optString2)) {
                        commonTimer.h.h = optString2;
                    }
                    String optString3 = optJSONObject.optString(C);
                    if (!TextUtils.isEmpty(optString3)) {
                        commonTimer.l.h = optString3;
                    }
                    commonTimer.q = optJSONObject.optInt("status", 0);
                    arrayList.add(commonTimer);
                }
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    public List<PlugTimer> o() {
        if (this.E == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (CommonTimer next : this.E) {
            if (next != null && !TextUtils.isEmpty(next.m) && "1".equals(next.m)) {
                arrayList.add(CommonTimer.a(next));
            }
        }
        return arrayList;
    }

    public void a(CommonTimer commonTimer, PlugSceneListener plugSceneListener) {
        a(commonTimer, plugSceneListener, "");
    }

    public void a(final CommonTimer commonTimer, final PlugSceneListener plugSceneListener, String str) {
        String str2;
        String str3;
        if (this.Y) {
            if (plugSceneListener != null) {
                plugSceneListener.onSetSceneFailed(new Error(-1, "isLoading"));
            }
            for (PlugSceneListener onSetSceneFailed : this.Z) {
                onSetSceneFailed.onSetSceneFailed(new Error(-1, "isLoading"));
            }
            return;
        }
        this.Y = true;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(O, commonTimer.c ? "1" : "0");
            jSONObject.put(P, commonTimer.d ? "1" : "0");
            jSONObject.put(Q, commonTimer.i ? "1" : "0");
            jSONObject.put(R, commonTimer.e ? "1" : "0");
            jSONObject.put(k, commonTimer.j);
            jSONObject.put(m, c(commonTimer.k));
            jSONObject.put(n, CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), commonTimer.l)));
            jSONObject.put(o, commonTimer.f);
            jSONObject.put(p, c(commonTimer.g));
            jSONObject.put(T, CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), commonTimer.h)));
            if (!TextUtils.isEmpty(commonTimer.m)) {
                jSONObject.put("timer_type", commonTimer.m);
            }
            if (!TextUtils.isEmpty(commonTimer.h.h)) {
                jSONObject.put(B, commonTimer.h.h);
            } else {
                jSONObject.put(B, "");
            }
            if (!TextUtils.isEmpty(commonTimer.l.h)) {
                jSONObject.put(C, commonTimer.l.h);
            } else {
                jSONObject.put(C, "");
            }
        } catch (JSONException unused) {
        }
        JSONArray jSONArray = new JSONArray();
        if (this.D == null) {
            this.Y = false;
            if (plugSceneListener != null) {
                plugSceneListener.onSetSceneFailed(new Error(-1, "device is null"));
            }
            for (PlugSceneListener onSetSceneFailed2 : this.Z) {
                onSetSceneFailed2.onSetSceneFailed(new Error(-1, "device is null"));
            }
            return;
        }
        jSONArray.put(this.D.did);
        String str4 = this.W;
        if (TextUtils.isEmpty(str4)) {
            if (commonTimer.e && commonTimer.i) {
                str4 = this.D.name + "-" + SHApplication.getAppContext().getString(R.string.timer_on_off);
            } else if (commonTimer.e) {
                str4 = this.D.name + "-" + SHApplication.getAppContext().getString(R.string.timer_on);
            } else if (commonTimer.i) {
                str4 = this.D.name + "-" + SHApplication.getAppContext().getString(R.string.timer_off);
            }
        }
        String str5 = this.D.did;
        boolean isEmpty = true ^ TextUtils.isEmpty(str);
        if (isEmpty) {
            if (!TextUtils.isEmpty(this.W)) {
                str4 = this.W;
            }
            str3 = str;
            str2 = str4;
        } else {
            str2 = str4;
            str3 = str5;
        }
        LogUtil.c(G, "identifyFromRn is " + isEmpty + ",  mTimerDisplayName is " + this.W);
        RemoteSceneApi.a().a(SHApplication.getAppContext(), 8, commonTimer.f21632a, this.D.did, str3, str2, jSONObject, jSONArray, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                boolean unused = TimerCommonManager.this.Y = false;
                try {
                    commonTimer.f21632a = jSONObject.getString("us_id");
                    commonTimer.q = jSONObject.optInt("status", 0);
                } catch (JSONException unused2) {
                }
                TimerCommonManager.this.E.clear();
                TimerCommonManager.this.E.addAll(TimerCommonManager.this.F);
                if (plugSceneListener != null) {
                    plugSceneListener.onSetSceneSuccess(commonTimer);
                }
                for (PlugSceneListener onSetSceneSuccess : TimerCommonManager.this.Z) {
                    onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                }
                TimerCommonManager.this.a(TimerCommonManager.this.g().toString());
            }

            public void onFailure(Error error) {
                boolean unused = TimerCommonManager.this.Y = false;
                TimerCommonManager.this.F.clear();
                TimerCommonManager.this.F.addAll(TimerCommonManager.this.E);
                if (plugSceneListener != null) {
                    plugSceneListener.onSetSceneFailed(error);
                }
                for (PlugSceneListener onSetSceneFailed : TimerCommonManager.this.Z) {
                    onSetSceneFailed.onSetSceneFailed(error);
                }
            }
        });
    }

    public void a(CommonTimer commonTimer, PlugSceneListener plugSceneListener, PlugTimer plugTimer) {
        a(commonTimer, plugSceneListener, plugTimer, (String) null);
    }

    public void a(final CommonTimer commonTimer, final PlugSceneListener plugSceneListener, final PlugTimer plugTimer, String str) {
        String str2;
        String str3;
        if (!this.Y) {
            this.Y = true;
            if (this.D.isVirtualDevice()) {
                this.Y = false;
                commonTimer.f21632a = String.valueOf((int) ((Math.random() * 1000.0d) + 1.0d));
                this.E.clear();
                this.E.addAll(this.F);
                if (plugTimer != null) {
                    plugTimer.j = commonTimer.f21632a;
                }
                for (PlugSceneListener onSetSceneSuccess : this.Z) {
                    onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                }
                a(g().toString());
                if (plugSceneListener != null) {
                    plugSceneListener.onSetSceneSuccess(commonTimer);
                    return;
                }
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(O, commonTimer.c ? "1" : "0");
                jSONObject.put(P, commonTimer.d ? "1" : "0");
                jSONObject.put(Q, commonTimer.i ? "1" : "0");
                jSONObject.put(R, commonTimer.e ? "1" : "0");
                jSONObject.put(k, commonTimer.j);
                jSONObject.put(m, c(commonTimer.k));
                jSONObject.put(n, CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), commonTimer.l)));
                jSONObject.put(o, commonTimer.f);
                jSONObject.put(p, c(commonTimer.g));
                jSONObject.put(T, CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), commonTimer.h)));
                String str4 = commonTimer.m;
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject.put("timer_type", str4);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.D.did);
            String str5 = TextUtils.isEmpty(this.W) ? "" : this.W;
            if (TextUtils.equals("display_name", str5)) {
                str5 = "";
            }
            String str6 = this.D.did;
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(this.W)) {
                    str5 = this.W;
                }
                str3 = str;
                str2 = str5;
            } else {
                str2 = str5;
                str3 = str6;
            }
            RemoteSceneApi.a().a(SHApplication.getAppContext(), 8, commonTimer.f21632a, this.D.did, str3, str2, jSONObject, jSONArray, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    try {
                        commonTimer.f21632a = jSONObject.getString("us_id");
                        commonTimer.q = jSONObject.optInt("status", 0);
                        if (plugTimer != null) {
                            plugTimer.j = commonTimer.f21632a;
                            plugTimer.i = commonTimer.q;
                        }
                    } catch (JSONException unused2) {
                    }
                    TimerCommonManager.this.E.clear();
                    TimerCommonManager.this.E.addAll(TimerCommonManager.this.F);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneSuccess(commonTimer);
                    }
                    for (PlugSceneListener onSetSceneSuccess : TimerCommonManager.this.Z) {
                        onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                    }
                    TimerCommonManager.this.a(TimerCommonManager.this.g().toString());
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    TimerCommonManager.this.F.clear();
                    TimerCommonManager.this.F.addAll(TimerCommonManager.this.E);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneFailed(error);
                    }
                    for (PlugSceneListener onSetSceneFailed : TimerCommonManager.this.Z) {
                        onSetSceneFailed.onSetSceneFailed(error);
                    }
                }
            });
        }
    }

    public void d(CommonTimer commonTimer) {
        a(commonTimer, "");
    }

    public void a(CommonTimer commonTimer, String str) {
        a(commonTimer, (PlugSceneListener) null, str);
    }

    public void b(final CommonTimer commonTimer, final PlugSceneListener plugSceneListener) {
        if (!this.Y) {
            this.Y = true;
            RemoteSceneApi.a().c(SHApplication.getAppContext(), commonTimer.f21632a, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    TimerCommonManager.this.E.clear();
                    TimerCommonManager.this.E.addAll(TimerCommonManager.this.F);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneSuccess(commonTimer);
                    }
                    for (PlugSceneListener onSetSceneSuccess : TimerCommonManager.this.Z) {
                        onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                    }
                    TimerCommonManager.this.a(TimerCommonManager.this.g().toString());
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    TimerCommonManager.this.F.clear();
                    TimerCommonManager.this.F.addAll(TimerCommonManager.this.E);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneFailed(error);
                    }
                    for (PlugSceneListener onSetSceneSuccess : TimerCommonManager.this.Z) {
                        onSetSceneSuccess.onSetSceneSuccess(commonTimer);
                    }
                }
            });
        }
    }

    public void a(List<CommonTimer> list, final PlugSceneListener plugSceneListener) {
        if (!this.Y) {
            this.Y = true;
            ArrayList arrayList = new ArrayList();
            for (CommonTimer commonTimer : list) {
                arrayList.add(commonTimer.f21632a);
            }
            RemoteSceneApi.a().a(SHApplication.getAppContext(), (List<String>) arrayList, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    TimerCommonManager.this.E.clear();
                    TimerCommonManager.this.E.addAll(TimerCommonManager.this.F);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneSuccess((CommonTimer) null);
                    }
                    TimerCommonManager.this.a(TimerCommonManager.this.g().toString());
                }

                public void onFailure(Error error) {
                    boolean unused = TimerCommonManager.this.Y = false;
                    TimerCommonManager.this.F.clear();
                    TimerCommonManager.this.F.addAll(TimerCommonManager.this.E);
                    if (plugSceneListener != null) {
                        plugSceneListener.onSetSceneFailed(error);
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

    public void b(CommonTimer commonTimer, CommonTimer commonTimer2) {
        a(commonTimer, commonTimer2, "");
    }

    public void a(CommonTimer commonTimer, CommonTimer commonTimer2, String str) {
        a(commonTimer, commonTimer2, (PlugSceneListener) null, str);
    }

    public void a(CommonTimer commonTimer, CommonTimer commonTimer2, PlugSceneListener plugSceneListener) {
        a(commonTimer, commonTimer2, plugSceneListener, (String) null);
    }

    public void a(CommonTimer commonTimer, CommonTimer commonTimer2, PlugSceneListener plugSceneListener, String str) {
        c(commonTimer);
        c(commonTimer2);
        if (this.F.isEmpty()) {
            this.F.add(commonTimer2);
        } else {
            for (int i2 = 0; i2 < this.F.size(); i2++) {
                if (TextUtils.equals(this.F.get(i2).f21632a, commonTimer.f21632a)) {
                    this.F.remove(i2);
                }
            }
            this.F.add(commonTimer2);
        }
        Collections.sort(this.F, new CompTimer());
        a(commonTimer2, plugSceneListener, str);
    }

    public void a(PlugTimer plugTimer, PlugTimer plugTimer2, String str, String str2, String str3, String str4, PlugSceneListener plugSceneListener) {
        a(plugTimer, plugTimer2, str, str2, str3, str4, plugSceneListener, (String) null);
    }

    public void a(PlugTimer plugTimer, PlugTimer plugTimer2, String str, String str2, String str3, String str4, PlugSceneListener plugSceneListener, String str5) {
        CommonTimer commonTimer = null;
        if (plugTimer != null) {
            int i2 = 0;
            while (true) {
                try {
                    if (i2 >= this.F.size()) {
                        break;
                    }
                    CommonTimer commonTimer2 = this.F.get(i2);
                    if (commonTimer2 != null) {
                        if (plugTimer.j.equals(commonTimer2.f21632a)) {
                            commonTimer = commonTimer2;
                            break;
                        }
                    }
                    i2++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.F.remove(commonTimer);
        CommonTimer a2 = CommonTimer.a(plugTimer2, str, str3, str2, str4);
        this.F.add(a2);
        a(a2, plugSceneListener, plugTimer2, str5);
    }

    public void a(CommonTimer commonTimer, boolean z2) {
        a(commonTimer, z2, "");
    }

    public void a(CommonTimer commonTimer, boolean z2, String str) {
        a(commonTimer, z2, (PlugSceneListener) null, str);
    }

    public void a(CommonTimer commonTimer, boolean z2, PlugSceneListener plugSceneListener) {
        a(commonTimer, z2, plugSceneListener, (String) null);
    }

    public void a(CommonTimer commonTimer, boolean z2, PlugSceneListener plugSceneListener, String str) {
        CommonTimer commonTimer2 = (CommonTimer) commonTimer.clone();
        commonTimer2.d = z2;
        if (z2) {
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

    public void c(CommonTimer commonTimer, PlugSceneListener plugSceneListener) {
        this.F.remove(commonTimer);
        b(commonTimer, plugSceneListener);
    }

    public void b(List<CommonTimer> list, PlugSceneListener plugSceneListener) {
        this.F.remove(list);
        a(list, plugSceneListener);
    }

    public void a(PlugTimer plugTimer, PlugSceneListener plugSceneListener) {
        int i2 = 0;
        CommonTimer commonTimer = null;
        while (i2 < this.F.size()) {
            try {
                CommonTimer commonTimer2 = this.F.get(i2);
                if (commonTimer2 != null) {
                    if (plugTimer.j.equals(commonTimer2.f21632a)) {
                        commonTimer = commonTimer2;
                    }
                }
                i2++;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.F.remove(commonTimer);
        b(commonTimer, plugSceneListener);
    }

    public static float a(Calendar calendar) {
        return ((((float) calendar.get(11)) * 60.0f) + ((float) calendar.get(12))) / 1440.0f;
    }

    public static List<Pair<Float, Float>> a(List<PlugTimer> list) {
        PlugTimer plugTimer;
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (PlugTimer next : list) {
            Calendar instance = Calendar.getInstance();
            if (next.b && !(next.e == null && next.h == null)) {
                if (!next.c) {
                    plugTimer = (PlugTimer) next.clone();
                    plugTimer.e = (CorntabUtils.CorntabParam) next.e.clone();
                    plugTimer.e.c = 0;
                    plugTimer.e.b = 0;
                } else if (!next.f) {
                    plugTimer = (PlugTimer) next.clone();
                    plugTimer.h = (CorntabUtils.CorntabParam) next.h.clone();
                    plugTimer.h.c = 23;
                    plugTimer.h.b = 59;
                } else {
                    plugTimer = next;
                }
                float f2 = 1.0f;
                if (plugTimer.e.d() == 0) {
                    Calendar instance2 = Calendar.getInstance();
                    instance2.set(instance.get(1), plugTimer.e.e - 1, plugTimer.e.d, plugTimer.e.c, plugTimer.e.b);
                    float f3 = 2.0f;
                    if (DateUtils.a(instance, instance2)) {
                        float a2 = a(instance2);
                        if (!next.f) {
                            f2 = 2.0f;
                        } else {
                            instance2.set(instance.get(1), plugTimer.h.e - 1, plugTimer.h.d, plugTimer.h.c, plugTimer.h.b);
                            if (DateUtils.a(instance, instance2)) {
                                f2 = a(instance2);
                            } else {
                                instance.add(6, 1);
                                if (DateUtils.a(instance, instance2)) {
                                    f2 = 1.0f + a(instance2);
                                }
                            }
                        }
                        arrayList.add(new Pair(Float.valueOf(a2), Float.valueOf(f2)));
                    } else {
                        instance.add(5, 1);
                        if (DateUtils.a(instance, instance2)) {
                            float a3 = a(instance2) + 1.0f;
                            if (next.f) {
                                instance2.set(instance.get(1), plugTimer.h.e - 1, plugTimer.h.d, plugTimer.h.c, plugTimer.h.b);
                                if (DateUtils.a(instance, instance2)) {
                                    f3 = a(instance2) + 1.0f;
                                }
                            }
                            arrayList.add(new Pair(Float.valueOf(a3), Float.valueOf(f3)));
                        } else {
                            instance.add(5, -1);
                            instance2.set(instance.get(1), plugTimer.h.e - 1, plugTimer.h.d, plugTimer.h.c, plugTimer.h.b);
                            if (DateUtils.a(instance, instance2)) {
                                f3 = a(instance2);
                            }
                            arrayList.add(new Pair(Float.valueOf(0.0f), Float.valueOf(f3)));
                        }
                    }
                } else if (plugTimer.e.d() == 62) {
                    Pair<Float, Float> a4 = a(instance, plugTimer);
                    int i2 = instance.get(7);
                    if (i2 == 7) {
                        if (((double) ((Float) a4.second).floatValue()) > 1.0d) {
                            arrayList.add(a4);
                        }
                    } else if (i2 == 1) {
                        arrayList.add(new Pair(Float.valueOf(((Float) a4.first).floatValue() + 1.0f), Float.valueOf(((Float) a4.second).floatValue() + 1.0f)));
                    } else if (a(instance) <= ((Float) a4.second).floatValue()) {
                        arrayList.add(a4);
                    } else if (i2 != 6) {
                        arrayList.add(new Pair(Float.valueOf(((Float) a4.first).floatValue() + 1.0f), Float.valueOf(((Float) a4.second).floatValue() + 1.0f)));
                    }
                } else if (plugTimer.e.d() == 65) {
                    Pair<Float, Float> a5 = a(instance, plugTimer);
                    int i3 = instance.get(7);
                    if (i3 == 6) {
                        arrayList.add(new Pair(Float.valueOf(((Float) a5.first).floatValue() + 1.0f), Float.valueOf(((Float) a5.second).floatValue() + 1.0f)));
                    } else if (i3 == 7) {
                        if (a(instance) <= ((Float) a5.second).floatValue()) {
                            arrayList.add(a5);
                        } else {
                            arrayList.add(new Pair(Float.valueOf(((Float) a5.first).floatValue() + 1.0f), Float.valueOf(((Float) a5.second).floatValue() + 1.0f)));
                        }
                    } else if (i3 == 1) {
                        if (a(instance) <= ((Float) a5.second).floatValue()) {
                            arrayList.add(a5);
                        }
                    } else if (i3 == 2 && a(instance) <= ((Float) a5.second).floatValue()) {
                        arrayList.add(a5);
                    }
                } else if (plugTimer.e.d() == 127) {
                    Pair<Float, Float> a6 = a(instance, plugTimer);
                    if (a(instance) <= ((Float) a6.second).floatValue()) {
                        arrayList.add(a6);
                    } else {
                        arrayList.add(new Pair(Float.valueOf(((Float) a6.first).floatValue() + 1.0f), Float.valueOf(((Float) a6.second).floatValue() + 1.0f)));
                    }
                } else if (plugTimer.e.g != null) {
                    int i4 = instance.get(7);
                    boolean[] zArr = plugTimer.e.g;
                    if (plugTimer.e.g.length < 7) {
                        zArr = Arrays.copyOf(zArr, 7);
                    }
                    a(i4, zArr, a(instance, plugTimer), a(instance), arrayList);
                }
            }
        }
        return arrayList;
    }

    private static Pair<Float, Float> a(Calendar calendar, PlugTimer plugTimer) {
        Calendar instance = Calendar.getInstance();
        Calendar calendar2 = instance;
        calendar2.set(calendar.get(1), calendar.get(2), calendar.get(6), plugTimer.e.c, plugTimer.e.b);
        float a2 = a(instance);
        calendar2.set(calendar.get(1), calendar.get(2), calendar.get(6), plugTimer.h.c, plugTimer.h.b);
        float a3 = a(instance);
        if (a3 < a2) {
            a3 += 1.0f;
        }
        return new Pair<>(Float.valueOf(a2), Float.valueOf(a3));
    }

    private static void a(int i2, boolean[] zArr, Pair<Float, Float> pair, float f2, List<Pair<Float, Float>> list) {
        int i3 = i2 - 1;
        if (!zArr[i3]) {
            if (zArr[i3 > 0 ? i2 - 2 : i2 + 5] && ((Float) pair.second).floatValue() > 1.0f) {
                double d2 = (double) f2;
                double floatValue = (double) ((Float) pair.second).floatValue();
                Double.isNaN(floatValue);
                if (d2 <= floatValue - 1.0d) {
                    list.add(new Pair(Float.valueOf(0.0f), Float.valueOf(((Float) pair.second).floatValue() - 1.0f)));
                    return;
                }
            }
            if (zArr[i2 % 7]) {
                list.add(new Pair(Float.valueOf(((Float) pair.first).floatValue() + 1.0f), Float.valueOf(((Float) pair.second).floatValue() + 1.0f)));
            }
        } else if (f2 <= ((Float) pair.second).floatValue()) {
            list.add(pair);
        } else if (zArr[i2 % 7]) {
            list.add(new Pair(Float.valueOf(((Float) pair.first).floatValue() + 1.0f), Float.valueOf(((Float) pair.second).floatValue() + 1.0f)));
        }
    }
}
