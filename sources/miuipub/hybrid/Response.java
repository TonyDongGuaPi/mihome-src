package miuipub.hybrid;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2951a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 100;
    public static final int f = 200;
    public static final int g = 201;
    public static final int h = 202;
    public static final int i = 203;
    public static final int j = 204;
    public static final int k = 205;
    private static final String l = "code";
    private static final String m = "content";
    private int n;
    private String o;
    private JSONObject p;

    public Response(int i2) {
        this(i2, "");
    }

    public Response(String str) {
        this(0, str);
    }

    public Response(int i2, String str) {
        this.p = new JSONObject();
        this.n = i2;
        this.o = str;
        try {
            this.p.put("code", this.n);
            this.p.put("content", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public Response(JSONObject jSONObject) {
        this(0, jSONObject);
    }

    public Response(int i2, JSONObject jSONObject) {
        this.p = new JSONObject();
        this.n = i2;
        this.o = jSONObject.toString();
        try {
            this.p.put("code", this.n);
            this.p.put("content", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public int a() {
        return this.n;
    }

    public String b() {
        return this.o;
    }

    public JSONObject c() {
        return this.p;
    }

    public String toString() {
        return this.p.toString();
    }
}
