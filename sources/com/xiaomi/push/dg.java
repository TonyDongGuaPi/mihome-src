package com.xiaomi.push;

import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

class dg implements Comparable<dg> {

    /* renamed from: a  reason: collision with root package name */
    String f12688a;
    protected int b;
    private final LinkedList<cw> c;
    private long d;

    public dg() {
        this((String) null, 0);
    }

    public dg(String str) {
        this(str, 0);
    }

    public dg(String str, int i) {
        this.c = new LinkedList<>();
        this.d = 0;
        this.f12688a = str;
        this.b = i;
    }

    /* renamed from: a */
    public int compareTo(dg dgVar) {
        if (dgVar == null) {
            return 1;
        }
        return dgVar.b - this.b;
    }

    public synchronized dg a(JSONObject jSONObject) {
        this.d = jSONObject.getLong(TtmlNode.TAG_TT);
        this.b = jSONObject.getInt("wt");
        this.f12688a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.c.add(new cw().a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized JSONObject a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put(TtmlNode.TAG_TT, this.d);
        jSONObject.put("wt", this.b);
        jSONObject.put("host", this.f12688a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            jSONArray.put(((cw) it.next()).b());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public synchronized void a(cw cwVar) {
        if (cwVar != null) {
            this.c.add(cwVar);
            int a2 = cwVar.a();
            if (a2 > 0) {
                this.b += cwVar.a();
            } else {
                int i = 0;
                int size = this.c.size() - 1;
                while (size >= 0 && this.c.get(size).a() < 0) {
                    i++;
                    size--;
                }
                this.b += a2 * i;
            }
            if (this.c.size() > 30) {
                this.b -= this.c.remove().a();
            }
        }
    }

    public String toString() {
        return this.f12688a + ":" + this.b;
    }
}
