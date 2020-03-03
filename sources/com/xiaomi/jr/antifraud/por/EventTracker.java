package com.xiaomi.jr.antifraud.por;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventTracker {
    private static EventTracker c = new EventTracker();

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<Event> f1386a = new ArrayList<>();
    private long b;

    public static class Event {

        /* renamed from: a  reason: collision with root package name */
        public long f10300a = System.currentTimeMillis();
        public String b;

        /* access modifiers changed from: protected */
        public void a(JSONObject jSONObject) throws JSONException {
        }

        public Event(String str) {
            this.b = str;
        }

        public Event a(long j) {
            this.f10300a = j;
            return this;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("time", this.f10300a);
                jSONObject.put("event", this.b);
                a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    public static EventTracker a() {
        return c;
    }

    public long b() {
        return System.currentTimeMillis() - this.b;
    }

    public void a(Context context) {
        NetworkMonitor.a(context);
    }

    public void c() {
        NetworkMonitor.a();
    }

    public void a(Event event) {
        this.f1386a.add(event);
    }

    public JSONArray d() {
        JSONArray jSONArray = new JSONArray();
        Iterator<Event> it = this.f1386a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        this.f1386a.clear();
        this.b = System.currentTimeMillis();
        return jSONArray;
    }
}
