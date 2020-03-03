package com.xiaomi.push;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class cy {

    /* renamed from: a  reason: collision with root package name */
    private String f12683a;
    private final ArrayList<cx> b = new ArrayList<>();

    public cy() {
    }

    public cy(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f12683a = str;
            return;
        }
        throw new IllegalArgumentException("the host is empty");
    }

    public synchronized cx a() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            cx cxVar = this.b.get(size);
            if (cxVar.a()) {
                db.a().a(cxVar.e());
                return cxVar;
            }
        }
        return null;
    }

    public synchronized cy a(JSONObject jSONObject) {
        this.f12683a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.b.add(new cx(this.f12683a).a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized void a(cx cxVar) {
        int i = 0;
        while (true) {
            if (i >= this.b.size()) {
                break;
            } else if (this.b.get(i).a(cxVar)) {
                this.b.set(i, cxVar);
                break;
            } else {
                i++;
            }
        }
        if (i >= this.b.size()) {
            this.b.add(cxVar);
        }
    }

    public synchronized void a(boolean z) {
        ArrayList<cx> arrayList;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            cx cxVar = this.b.get(size);
            if (z) {
                if (cxVar.c()) {
                    arrayList = this.b;
                }
            } else if (!cxVar.b()) {
                arrayList = this.b;
            }
            arrayList.remove(size);
        }
    }

    public ArrayList<cx> b() {
        return this.b;
    }

    public String c() {
        return this.f12683a;
    }

    public synchronized JSONObject d() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("host", this.f12683a);
        JSONArray jSONArray = new JSONArray();
        Iterator<cx> it = this.b.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().f());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f12683a);
        sb.append("\n");
        Iterator<cx> it = this.b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
