package com.xiaomi.jr.hybrid;

import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import org.json.JSONObject;

public class Response {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1445a = 0;
    public static final int b = 100;
    public static final int c = 200;
    public static final int d = 201;
    public static final int e = 202;
    public static final int f = 203;
    public static final int g = 204;
    public static final int h = 205;
    public static final int i = 206;
    public static final Response j = new Response(0, (String) null);
    @SerializedName("code")
    private int k;
    @SerializedName("content")
    private Object l;
    @SerializedName("error")
    private String m;

    public Response(Object obj) {
        this(0, obj, (String) null);
    }

    public Response(int i2, String str) {
        this(i2, (Object) null, str);
    }

    public Response(int i2, Object obj, String str) {
        this.k = i2;
        this.l = obj;
        this.m = str;
    }

    public void a(int i2) {
        this.k = i2;
    }

    public int a() {
        return this.k;
    }

    public void a(Object obj) {
        if (obj != null) {
            this.k = 0;
        }
        this.l = obj;
    }

    public Object b() {
        return this.l;
    }

    public void a(String str) {
        if (this.k == 0) {
            this.k = 200;
        }
        this.m = str;
    }

    public void a(int i2, String str) {
        this.k = i2;
        this.m = str;
    }

    public String c() {
        return this.m;
    }

    public String toString() {
        return HybridUtils.a().toJson((Object) this);
    }

    public static class InvalidParamResponse extends Response {
        public InvalidParamResponse(Request request) {
            this(request, (String) null);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InvalidParamResponse(com.xiaomi.jr.hybrid.Request r3, java.lang.String r4) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "invalid param="
                r0.append(r1)
                java.lang.String r1 = r3.d()
                r0.append(r1)
                java.lang.String r1 = " in action="
                r0.append(r1)
                java.lang.String r3 = r3.b()
                r0.append(r3)
                if (r4 == 0) goto L_0x0031
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r1 = ", "
                r3.append(r1)
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                goto L_0x0033
            L_0x0031:
                java.lang.String r3 = ""
            L_0x0033:
                r0.append(r3)
                java.lang.String r3 = r0.toString()
                r4 = 206(0xce, float:2.89E-43)
                r2.<init>(r4, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.hybrid.Response.InvalidParamResponse.<init>(com.xiaomi.jr.hybrid.Request, java.lang.String):void");
        }
    }

    public static class RuntimeErrorResponse extends Response {
        public RuntimeErrorResponse(Request request, Exception exc) {
            this(request, exc.getMessage());
        }

        public RuntimeErrorResponse(Request request, String str) {
            super(200, "exception in perform " + request.b() + ": " + str);
        }
    }

    public static class PermissionDeniedResponse extends Response {
        public PermissionDeniedResponse(String str) {
            super(203, b(str));
        }

        private static String b(String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("permission", str);
            } catch (JSONException unused) {
            }
            return jSONObject.toString();
        }
    }
}
