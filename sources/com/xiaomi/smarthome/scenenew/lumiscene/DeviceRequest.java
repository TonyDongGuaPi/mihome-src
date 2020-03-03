package com.xiaomi.smarthome.scenenew.lumiscene;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceRequest {

    /* renamed from: a  reason: collision with root package name */
    public static final int f21948a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public int d;
    public int e = 0;
    public String f;
    public String g;
    public HashMap<String, String> h = new HashMap<>();
    public JSONObject i;
    public JSONArray j;
    public String k = "";
    public LmBaseDevice l;
    public String m;
    public String n;
    public String o;

    public String a() {
        return this.m;
    }

    public void a(String str) {
        this.m = str;
    }

    public String b() {
        if (this.l == null) {
            return this.n;
        }
        return this.l.getModel();
    }

    public void b(String str) {
        this.n = str;
    }

    public String c() {
        if (this.l == null) {
            return this.o;
        }
        return this.l.getDid();
    }

    public void c(String str) {
        this.o = str;
    }
}
