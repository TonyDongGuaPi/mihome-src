package a.a;

import org.json.JSONException;
import org.json.JSONObject;

public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final int f421a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 100;
    public static final int f = 200;
    public static final int g = 201;
    public static final int h = 202;
    public static final int i = 203;
    public static final int j = 204;
    private static final String k = "code";
    private static final String l = "content";
    private int m;
    private String n;
    private JSONObject o;

    public f(int i2) {
        this(i2, "");
    }

    public f(int i2, String str) {
        this.o = new JSONObject();
        this.m = i2;
        this.n = str;
        try {
            this.o.put("code", this.m);
            this.o.put("content", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public f(int i2, JSONObject jSONObject) {
        this.o = new JSONObject();
        this.m = i2;
        this.n = jSONObject.toString();
        try {
            this.o.put("code", this.m);
            this.o.put("content", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public f(String str) {
        this(0, str);
    }

    public f(JSONObject jSONObject) {
        this(0, jSONObject);
    }

    public int a() {
        return this.m;
    }

    public String b() {
        return this.n;
    }

    public JSONObject c() {
        return this.o;
    }

    public String toString() {
        return this.o.toString();
    }
}
