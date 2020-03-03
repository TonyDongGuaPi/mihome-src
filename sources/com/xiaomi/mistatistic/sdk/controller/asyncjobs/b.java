package com.xiaomi.mistatistic.sdk.controller.asyncjobs;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.xiaomi.mistatistic.sdk.controller.d;
import com.xiaomi.mistatistic.sdk.controller.f;
import com.xiaomi.mistatistic.sdk.controller.h;
import com.xiaomi.mistatistic.sdk.controller.l;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.verificationsdk.internal.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements d.a {

    /* renamed from: a  reason: collision with root package name */
    private static int f12011a;
    private long b;
    private a c;
    private HashMap<String, JSONObject> d = new HashMap<>();
    private HashMap<String, JSONObject> e = new HashMap<>();
    private ArrayList<String> f = new ArrayList<>();
    private JSONObject g = null;
    private long h = System.currentTimeMillis();

    public interface a {
        void a(String str, long j, long j2, int i);
    }

    /* renamed from: com.xiaomi.mistatistic.sdk.controller.asyncjobs.b$b  reason: collision with other inner class name */
    public class C0087b {

        /* renamed from: a  reason: collision with root package name */
        int f12012a;
        /* access modifiers changed from: private */
        public JSONArray c;
        /* access modifiers changed from: private */
        public long d;
        /* access modifiers changed from: private */
        public long e;

        public C0087b(JSONArray jSONArray, long j, long j2, int i) {
            this.c = jSONArray;
            this.d = j;
            this.e = j2;
            this.f12012a = i;
        }
    }

    public b(long j, a aVar) {
        this.b = j;
        this.c = aVar;
    }

    public void a() {
        try {
            C0087b a2 = a(Long.MAX_VALUE);
            if (a2.c != null) {
                this.c.a(a2.c.toString(), a2.d, a2.e, a2.f12012a);
            } else {
                this.c.a("", a2.d, a2.e, a2.f12012a);
            }
            if (a2.f12012a >= 500) {
                h.a(String.format("Packing %d events over MAX_PACKING_EVENT %d", new Object[]{Integer.valueOf(a2.f12012a), 500}));
                if (f12011a < 50) {
                    new l().a();
                    f12011a++;
                    return;
                }
                h.d("Packing, exceeded MAX_UPLOAD_TIMES 50");
                return;
            }
            f12011a = 0;
        } catch (Exception e2) {
            h.a("remote data packing job execute exception:", (Throwable) e2);
            this.c.a("", 0, 0, 0);
        }
    }

    public C0087b a(long j) throws JSONException {
        long j2;
        long j3;
        JSONArray jSONArray;
        long j4;
        long j5;
        long j6;
        JSONArray jSONArray2 = new JSONArray();
        f fVar = new f();
        fVar.b();
        b();
        this.h = System.currentTimeMillis();
        List<StatEventPojo> a2 = fVar.a(j);
        long j7 = 0;
        if (a2 != null) {
            try {
                if (a2.size() > 0) {
                    int i = 0;
                    h.a(String.format("Packing, get %d events from local DB", new Object[]{Integer.valueOf(a2.size())}));
                    j5 = 0;
                    j6 = 0;
                    while (i < a2.size()) {
                        try {
                            StatEventPojo statEventPojo = a2.get(i);
                            h.a("Packing: " + statEventPojo.toString());
                            if (j5 == 0) {
                                long j8 = statEventPojo.b;
                                try {
                                    this.h = j8;
                                    j5 = j8;
                                } catch (SQLiteException e2) {
                                    e = e2;
                                    j5 = j8;
                                    j4 = j6;
                                    h.a("packing exception:", (Throwable) e);
                                    jSONArray = jSONArray2;
                                    j2 = j5;
                                    j3 = j4;
                                    return new C0087b(jSONArray, j3, j2, 0);
                                }
                            }
                            j4 = statEventPojo.b;
                            try {
                                if (this.b > 0 && this.h - statEventPojo.b > this.b && this.g != null) {
                                    b();
                                    this.h = statEventPojo.b;
                                }
                                if (this.g == null) {
                                    this.g = new JSONObject();
                                    this.g.put("endTS", statEventPojo.b);
                                    this.g.put("content", new JSONArray());
                                    jSONArray2.put(this.g);
                                }
                                if (RemoteDataUploadManager.f.equals(statEventPojo.f12065a)) {
                                    a(statEventPojo);
                                } else if ("mistat_pv".equals(statEventPojo.f12065a)) {
                                    b(statEventPojo);
                                } else if ("mistat_pt".equals(statEventPojo.f12065a)) {
                                    c(statEventPojo);
                                } else {
                                    d(statEventPojo);
                                }
                                this.g.put("startTS", statEventPojo.b);
                                i++;
                                j6 = j4;
                            } catch (SQLiteException e3) {
                                e = e3;
                                h.a("packing exception:", (Throwable) e);
                                jSONArray = jSONArray2;
                                j2 = j5;
                                j3 = j4;
                                return new C0087b(jSONArray, j3, j2, 0);
                            }
                        } catch (SQLiteException e4) {
                            e = e4;
                            j4 = j6;
                            h.a("packing exception:", (Throwable) e);
                            jSONArray = jSONArray2;
                            j2 = j5;
                            j3 = j4;
                            return new C0087b(jSONArray, j3, j2, 0);
                        }
                    }
                    h.a("Packing complete, total " + a2.size() + " records were packed and to be uploaded");
                    j7 = j5;
                    j2 = j7;
                    j3 = j6;
                    jSONArray = jSONArray2;
                    return new C0087b(jSONArray, j3, j2, 0);
                }
            } catch (SQLiteException e5) {
                e = e5;
                j5 = 0;
                j4 = 0;
                h.a("packing exception:", (Throwable) e);
                jSONArray = jSONArray2;
                j2 = j5;
                j3 = j4;
                return new C0087b(jSONArray, j3, j2, 0);
            }
        }
        h.a("No data available to be packed");
        jSONArray2 = null;
        j6 = 0;
        j2 = j7;
        j3 = j6;
        jSONArray = jSONArray2;
        return new C0087b(jSONArray, j3, j2, 0);
    }

    private void b() {
        this.g = null;
        this.d.clear();
        this.f.clear();
        this.e.clear();
    }

    private void a(StatEventPojo statEventPojo) throws JSONException {
        JSONObject jSONObject = this.d.get(RemoteDataUploadManager.f);
        if (jSONObject == null) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("category", RemoteDataUploadManager.f);
            jSONObject2.put(MibiConstants.gf, jSONArray);
            this.d.put(RemoteDataUploadManager.f, jSONObject2);
            this.g.getJSONArray("content").put(jSONObject2);
            jSONObject = jSONObject2;
        }
        JSONObject jSONObject3 = new JSONObject();
        String[] split = statEventPojo.e.split(",");
        long parseLong = Long.parseLong(split[0]);
        long parseLong2 = Long.parseLong(split[1]);
        jSONObject3.put("start", parseLong);
        jSONObject3.put("end", parseLong2);
        jSONObject3.put(Constants.d, statEventPojo.f);
        jSONObject.getJSONArray(MibiConstants.gf).put(jSONObject3);
    }

    private void b(StatEventPojo statEventPojo) throws JSONException {
        JSONObject jSONObject = this.d.get("mistat_pv");
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            jSONObject.put("category", "mistat_pv");
            jSONObject.put(MibiConstants.gf, jSONArray);
            jSONObject.put("source", jSONArray2);
            this.d.put("mistat_pv", jSONObject);
            this.g.getJSONArray("content").put(jSONObject);
        }
        String[] split = statEventPojo.e.trim().split(",");
        String[] strArr = new String[split.length];
        if (split != null && split.length > 0) {
            for (int i = 0; i < split.length; i++) {
                int indexOf = this.f.indexOf(split[i]);
                if (indexOf >= 0) {
                    strArr[i] = String.valueOf(indexOf + 1);
                } else {
                    strArr[i] = String.valueOf(this.f.size() + 1);
                    this.f.add(split[i]);
                }
            }
        }
        jSONObject.getJSONArray(MibiConstants.gf).put(TextUtils.join(",", strArr));
        jSONObject.put("index", TextUtils.join(",", this.f));
        if (TextUtils.isEmpty(statEventPojo.f)) {
            jSONObject.getJSONArray("source").put("");
        } else {
            jSONObject.getJSONArray("source").put(statEventPojo.f);
        }
    }

    private void c(StatEventPojo statEventPojo) throws JSONException {
        JSONObject jSONObject = this.d.get("mistat_pt");
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("category", "mistat_pt");
            jSONObject.put(MibiConstants.gf, jSONArray);
            this.d.put("mistat_pt", jSONObject);
            this.g.getJSONArray("content").put(jSONObject);
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray(MibiConstants.gf);
        for (int i = 0; i < jSONArray2.length(); i++) {
            JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
            if (TextUtils.equals(jSONObject2.getString("key"), statEventPojo.c)) {
                jSONObject2.put("value", jSONObject2.getString("value") + "," + statEventPojo.e);
                return;
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("key", statEventPojo.c);
        jSONObject3.put("value", statEventPojo.e);
        jSONObject.getJSONArray(MibiConstants.gf).put(jSONObject3);
    }

    private void d(StatEventPojo statEventPojo) throws JSONException {
        JSONObject jSONObject = this.d.get(statEventPojo.f12065a);
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("category", statEventPojo.f12065a);
            jSONObject.put(MibiConstants.gf, jSONArray);
            this.d.put(statEventPojo.f12065a, jSONObject);
            this.g.getJSONArray("content").put(jSONObject);
        }
        if ("event".equals(statEventPojo.d) && TextUtils.isEmpty(statEventPojo.f)) {
            JSONObject jSONObject2 = this.e.get(statEventPojo.c);
            if (jSONObject2 != null) {
                jSONObject2.put("value", jSONObject2.getLong("value") + Long.parseLong(statEventPojo.e));
                return;
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("key", statEventPojo.c);
            jSONObject3.put("type", statEventPojo.d);
            jSONObject3.put("value", Long.parseLong(statEventPojo.e));
            jSONObject.getJSONArray(MibiConstants.gf).put(jSONObject3);
            this.e.put(statEventPojo.c, jSONObject3);
        } else if ("mistat_extra".equals(statEventPojo.f12065a)) {
            jSONObject.getJSONArray(MibiConstants.gf).put(statEventPojo.e);
        } else {
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("key", statEventPojo.c);
            jSONObject4.put("type", statEventPojo.d);
            if ("count".equals(statEventPojo.d) || "numeric".equals(statEventPojo.d)) {
                jSONObject4.put("value", Long.parseLong(statEventPojo.e));
            } else {
                jSONObject4.put("value", statEventPojo.e);
            }
            if (!TextUtils.isEmpty(statEventPojo.f)) {
                jSONObject4.put("params", new JSONObject(statEventPojo.f));
            }
            jSONObject.getJSONArray(MibiConstants.gf).put(jSONObject4);
        }
    }
}
